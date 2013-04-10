/*
 * Copyright (C) 2013 University of Washington
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.opendatakit.common.android.provider.impl;

import java.io.File;

import org.opendatakit.common.android.utilities.ODKFileUtils;

import android.os.FileObserver;
import android.util.Log;

/**
 * Monitors the /odk/appName/forms/formDir/formDef.json file for changes. If a
 * change is detected, fires off a rescan of the appName containing that file.
 *
 * @author mitchellsundt@gmail.com
 *
 */
class AppNameFormsFormDefJsonObserver extends FileObserver {
  private static final String t = "AppNameFormsFormDefJsonObserver";

  private AppNameFormsFormDirObserver parent;
  private boolean active = true;
  private long lastModificationTime = -1L;

  public AppNameFormsFormDefJsonObserver(AppNameFormsFormDirObserver parent) {
    super(parent.getFormDirPath() + File.separator + ODKFileUtils.FORMDEF_JSON_FILENAME,
        ODKFolderObserver.LIKELY_CHANGE_OF_FILE);
    this.parent = parent;
    this.startWatching();

    update();
  }

  private void update() {
    File formDirFolder = new File(parent.getFormDirPath());
    File formDefJson = new File(formDirFolder, ODKFileUtils.FORMDEF_JSON_FILENAME);

    if (formDefJson.exists() && formDefJson.isFile()) {
      String action = (lastModificationTime == -1L) ? "monitoring added: " : "changed: ";
      long modTime = formDefJson.lastModified();
      if (modTime != lastModificationTime) {
        lastModificationTime = modTime;
        parent.launchFormsDiscovery(action + formDefJson.getAbsolutePath());
      }
    } else {
      parent.removeFormDefJsonWatch();
    }
  }

  public void stop() {
    File formDirFolder = new File(parent.getFormDirPath());
    File formDefJson = new File(formDirFolder, ODKFileUtils.FORMDEF_JSON_FILENAME);

    active = false;
    this.stopWatching();
    Log.i(t, "stop() " + formDefJson.getAbsolutePath());
  }

  @Override
  public void onEvent(int event, String path) {
    Log.i(t, "onEvent: " + path + " event: " + ODKFolderObserver.eventMap(event));
    if (!active) {
      return;
    }

    if ((event & FileObserver.DELETE_SELF) != 0) {
      stop();
      parent.removeFormDefJsonWatch();
      return;
    }

    if ((event & FileObserver.MOVE_SELF) != 0) {
      stop();
      parent.removeFormDefJsonWatch();
      return;
    }

    update();
  }
}