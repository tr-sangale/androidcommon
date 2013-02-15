package org.opendatakit.common.android.provider;

import android.provider.BaseColumns;

public class ColumnDefinitionsColumns implements BaseColumns {

  // table_id cannot be null
  public static final String TABLE_ID = "table_id";
  // element_key cannot be null
  public static final String ELEMENT_KEY = "element_key";
  // element_name cannot be null
  public static final String ELEMENT_NAME = "element_name";
  // element_type cannot be null
  public static final String ELEMENT_TYPE = "element_type";
  // list_child_element_keys can be null
  // json array of [element_key] entries
  public static final String LIST_CHILD_ELEMENT_KEYS = "list_child_element_keys";
  // is_persisted cannot be null
  // integer interpreted as a boolean value
  public static final String IS_PERSISTED = "is_persisted";
  // joins can be null
  // json array of objects:
  // [{table_id: tid, element_key: elem}, ...]
  public static final String JOINS = "joins";

  // This class cannot be instantiated
  private ColumnDefinitionsColumns() {
  }

  /**
   * Get the create sql for the column_definitions table.
   *
   * @return
   */
  public static String getTableCreateSql(String tableName) {
    //@formatter:off
		return "CREATE TABLE IF NOT EXISTS " + tableName + "("
				+ TABLE_ID + " TEXT NOT NULL, "
				+ ELEMENT_KEY + " TEXT NOT NULL, "
				+ ELEMENT_NAME + " TEXT NOT NULL, "
				+ ELEMENT_TYPE + " TEXT NOT NULL, "
				+ LIST_CHILD_ELEMENT_KEYS + " TEXT NULL, "
				+ IS_PERSISTED	+ " INTEGER NOT NULL, "
				+ JOINS + " TEXT NULL, " +
				// ", " + DB_JOIN_TABLE_ID + " TEXT NULL" +
				// ", " + DB_JOIN_ELEMENT_KEY + " TEXT NULL" +
				"PRIMARY KEY ( " + TABLE_ID + ", " + ELEMENT_KEY + ") )";
		//@formatter:on
  }
}