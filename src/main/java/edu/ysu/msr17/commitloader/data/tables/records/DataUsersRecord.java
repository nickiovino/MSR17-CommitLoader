/**
 * This class is generated by jOOQ
 */
package edu.ysu.msr17.commitloader.data.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.4.4" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DataUsersRecord extends org.jooq.impl.UpdatableRecordImpl<edu.ysu.msr17.commitloader.data.tables.records.DataUsersRecord> implements org.jooq.Record4<org.jooq.types.UInteger, java.lang.String, java.lang.String, org.jooq.types.UInteger> {

	private static final long serialVersionUID = 620213949;

	/**
	 * Setter for <code>travistorrent.data_users.id</code>.
	 */
	public DataUsersRecord setId(org.jooq.types.UInteger value) {
		setValue(0, value);
		return this;
	}

	/**
	 * Getter for <code>travistorrent.data_users.id</code>.
	 */
	public org.jooq.types.UInteger getId() {
		return (org.jooq.types.UInteger) getValue(0);
	}

	/**
	 * Setter for <code>travistorrent.data_users.name</code>.
	 */
	public DataUsersRecord setName(java.lang.String value) {
		setValue(1, value);
		return this;
	}

	/**
	 * Getter for <code>travistorrent.data_users.name</code>.
	 */
	public java.lang.String getName() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>travistorrent.data_users.email</code>.
	 */
	public DataUsersRecord setEmail(java.lang.String value) {
		setValue(2, value);
		return this;
	}

	/**
	 * Getter for <code>travistorrent.data_users.email</code>.
	 */
	public java.lang.String getEmail() {
		return (java.lang.String) getValue(2);
	}

	/**
	 * Setter for <code>travistorrent.data_users.gh_id</code>.
	 */
	public DataUsersRecord setGhId(org.jooq.types.UInteger value) {
		setValue(3, value);
		return this;
	}

	/**
	 * Getter for <code>travistorrent.data_users.gh_id</code>.
	 */
	public org.jooq.types.UInteger getGhId() {
		return (org.jooq.types.UInteger) getValue(3);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<org.jooq.types.UInteger> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<org.jooq.types.UInteger, java.lang.String, java.lang.String, org.jooq.types.UInteger> fieldsRow() {
		return (org.jooq.Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row4<org.jooq.types.UInteger, java.lang.String, java.lang.String, org.jooq.types.UInteger> valuesRow() {
		return (org.jooq.Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.jooq.types.UInteger> field1() {
		return edu.ysu.msr17.commitloader.data.tables.DataUsers.DATA_USERS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return edu.ysu.msr17.commitloader.data.tables.DataUsers.DATA_USERS.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field3() {
		return edu.ysu.msr17.commitloader.data.tables.DataUsers.DATA_USERS.EMAIL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.jooq.types.UInteger> field4() {
		return edu.ysu.msr17.commitloader.data.tables.DataUsers.DATA_USERS.GH_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.types.UInteger value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value3() {
		return getEmail();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.types.UInteger value4() {
		return getGhId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataUsersRecord value1(org.jooq.types.UInteger value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataUsersRecord value2(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataUsersRecord value3(java.lang.String value) {
		setEmail(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataUsersRecord value4(org.jooq.types.UInteger value) {
		setGhId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataUsersRecord values(org.jooq.types.UInteger value1, java.lang.String value2, java.lang.String value3, org.jooq.types.UInteger value4) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached DataUsersRecord
	 */
	public DataUsersRecord() {
		super(edu.ysu.msr17.commitloader.data.tables.DataUsers.DATA_USERS);
	}

	/**
	 * Create a detached, initialised DataUsersRecord
	 */
	public DataUsersRecord(org.jooq.types.UInteger id, java.lang.String name, java.lang.String email, org.jooq.types.UInteger ghId) {
		super(edu.ysu.msr17.commitloader.data.tables.DataUsers.DATA_USERS);

		setValue(0, id);
		setValue(1, name);
		setValue(2, email);
		setValue(3, ghId);
	}
}
