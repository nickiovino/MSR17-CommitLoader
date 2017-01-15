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
public class DataJsonRecord extends org.jooq.impl.UpdatableRecordImpl<edu.ysu.msr17.commitloader.data.tables.records.DataJsonRecord> implements org.jooq.Record2<org.jooq.types.ULong, java.lang.String> {

	private static final long serialVersionUID = -600029528;

	/**
	 * Setter for <code>travistorrent.data_json.id</code>.
	 */
	public DataJsonRecord setId(org.jooq.types.ULong value) {
		setValue(0, value);
		return this;
	}

	/**
	 * Getter for <code>travistorrent.data_json.id</code>.
	 */
	public org.jooq.types.ULong getId() {
		return (org.jooq.types.ULong) getValue(0);
	}

	/**
	 * Setter for <code>travistorrent.data_json.data</code>.
	 */
	public DataJsonRecord setData(java.lang.String value) {
		setValue(1, value);
		return this;
	}

	/**
	 * Getter for <code>travistorrent.data_json.data</code>.
	 */
	public java.lang.String getData() {
		return (java.lang.String) getValue(1);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<org.jooq.types.ULong> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record2 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<org.jooq.types.ULong, java.lang.String> fieldsRow() {
		return (org.jooq.Row2) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row2<org.jooq.types.ULong, java.lang.String> valuesRow() {
		return (org.jooq.Row2) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<org.jooq.types.ULong> field1() {
		return edu.ysu.msr17.commitloader.data.tables.DataJson.DATA_JSON.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return edu.ysu.msr17.commitloader.data.tables.DataJson.DATA_JSON.DATA;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.types.ULong value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataJsonRecord value1(org.jooq.types.ULong value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataJsonRecord value2(java.lang.String value) {
		setData(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DataJsonRecord values(org.jooq.types.ULong value1, java.lang.String value2) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached DataJsonRecord
	 */
	public DataJsonRecord() {
		super(edu.ysu.msr17.commitloader.data.tables.DataJson.DATA_JSON);
	}

	/**
	 * Create a detached, initialised DataJsonRecord
	 */
	public DataJsonRecord(org.jooq.types.ULong id, java.lang.String data) {
		super(edu.ysu.msr17.commitloader.data.tables.DataJson.DATA_JSON);

		setValue(0, id);
		setValue(1, data);
	}
}
