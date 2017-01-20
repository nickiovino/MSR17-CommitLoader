/**
 * This class is generated by jOOQ
 */
package edu.ysu.msr17.commitloader.data.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DataBuildsCondensed extends org.jooq.impl.TableImpl<edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord> {

	private static final long serialVersionUID = 1360405029;

	/**
	 * The singleton instance of <code>travistorrent.data_builds_condensed</code>
	 */
	public static final edu.ysu.msr17.commitloader.data.tables.DataBuildsCondensed DATA_BUILDS_CONDENSED = new edu.ysu.msr17.commitloader.data.tables.DataBuildsCondensed();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord> getRecordType() {
		return edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord.class;
	}

	/**
	 * The column <code>travistorrent.data_builds_condensed.gh_project_name</code>.
	 */
	public final org.jooq.TableField<edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord, java.lang.String> GH_PROJECT_NAME = createField("gh_project_name", org.jooq.impl.SQLDataType.VARCHAR.length(100), this, "");

	/**
	 * The column <code>travistorrent.data_builds_condensed.git_trigger_commit</code>.
	 */
	public final org.jooq.TableField<edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord, java.lang.String> GIT_TRIGGER_COMMIT = createField("git_trigger_commit", org.jooq.impl.SQLDataType.CHAR.length(40), this, "");

	/**
	 * Create a <code>travistorrent.data_builds_condensed</code> table reference
	 */
	public DataBuildsCondensed() {
		this("data_builds_condensed", null);
	}

	/**
	 * Create an aliased <code>travistorrent.data_builds_condensed</code> table reference
	 */
	public DataBuildsCondensed(java.lang.String alias) {
		this(alias, edu.ysu.msr17.commitloader.data.tables.DataBuildsCondensed.DATA_BUILDS_CONDENSED);
	}

	private DataBuildsCondensed(java.lang.String alias, org.jooq.Table<edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord> aliased) {
		this(alias, aliased, null);
	}

	private DataBuildsCondensed(java.lang.String alias, org.jooq.Table<edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, edu.ysu.msr17.commitloader.data.Travistorrent.TRAVISTORRENT, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<edu.ysu.msr17.commitloader.data.tables.records.DataBuildsCondensedRecord>>asList(edu.ysu.msr17.commitloader.data.Keys.KEY_DATA_BUILDS_CONDENSED_GH_PROJECT_NAME__GIT_TRIGGER_COMMIT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public edu.ysu.msr17.commitloader.data.tables.DataBuildsCondensed as(java.lang.String alias) {
		return new edu.ysu.msr17.commitloader.data.tables.DataBuildsCondensed(alias, this);
	}

	/**
	 * Rename this table
	 */
	public edu.ysu.msr17.commitloader.data.tables.DataBuildsCondensed rename(java.lang.String name) {
		return new edu.ysu.msr17.commitloader.data.tables.DataBuildsCondensed(name, null);
	}
}