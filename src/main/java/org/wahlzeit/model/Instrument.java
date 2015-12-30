package org.wahlzeit.model;

public class Instrument {
	protected InstrumentType type = null;
	protected String serialNumber;
	protected int age;
	protected int id;
	private static int idCounter = 0;

	/**
	 * @methodType constructor
	 */
	public Instrument(InstrumentType type, String serialNumber, int age) {
		this.type = type;
		this.age = age;
		this.serialNumber = serialNumber;
		synchronized (Instrument.class) {
			this.id = idCounter++;
		}
	}

	/**
	 * @methodType constructor
	 */
	public Instrument(InstrumentType type) {
		this(type, null, 0);
	}

	/**
	 * @methodType get
	 */
	public int getId() {
		return id;
	}

	/**
	 * @methodType get
	 */
	public InstrumentType getType() {
		return type;
	}

	/**
	 * @methodType set
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * @methodType get
	 */
	public String getSerialNumber() {
		return this.serialNumber;
	}

	/**
	 * @methodType get
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @methodType set
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
