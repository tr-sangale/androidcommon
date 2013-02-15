package org.opendatakit.common.android.provider;

import android.provider.BaseColumns;

/**
 * Metadata Columns added to the user-defined data tables.
 *
 * @author mitchellsundt@gmail.com
 *
 */
public class DataTableColumns implements BaseColumns {

  // tablename is chosen by user...
  public static final String ID = "id";
  public static final String ROW_ID = "id";
  public static final String URI_USER = "uri_user";
  public static final String LAST_MODIFIED_TIME = "last_mod_time";
  public static final String SYNC_TAG = "sync_tag";
  public static final String SYNC_STATE = "sync_state";
  public static final String TRANSACTIONING = "transactioning";
  public static final String TIMESTAMP = "timestamp";
  public static final String SAVED = "saved";
  /*
   * For ODKTables generated rows (as opposed to ODK Collect), the thought is
   * that this instance name would just be the iso86 pretty print date of
   * creation.
   */
  public static final String INSTANCE_NAME = "instance_name";
  public static final String LOCALE = "locale";

  // This class cannot be instantiated
  private DataTableColumns() {
  }

}