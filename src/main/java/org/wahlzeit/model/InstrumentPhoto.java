package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

/**
 * Class containing a position expressed as latitude and longitude
 *
 */
@Subclass(index=true)
public class InstrumentPhoto extends Photo {
	protected String name;
	protected InstrumentType type;
	protected int age;
	
	/**
	 * @methodType constructor
	 * 
	 * @param myId
	 * @param name
	 * @param type
	 * @param age
	 */
	public InstrumentPhoto(PhotoId myId, String name, InstrumentType type, 
			int age) {
		super(myId);
		this.name = name;
		this.type = type;
		this.age = age;
	}
	
	/**
	 * @methodType constructor
	 * 
	 * @param name
	 * @param type
	 * @param age
	 */
	public InstrumentPhoto(String name, InstrumentType type, int age){
		super();
		this.name = name;
		this.type = type;
		this.age = age;
	}
	
	/**
	 * @methodType constructor
	 * @methodProperties convenience
	 * 
	 * @param name
	 * @param type
	 */
	public InstrumentPhoto(String name, InstrumentType type){
		this(name, type, 0);
	}
	
	/**
	 * @methodType constructor
	 * @methodProperties convenience
	 * 
	 * @param myId
	 * @param name
	 * @param type
	 */
	public InstrumentPhoto(PhotoId myId, String name, InstrumentType type){
		this(myId, name, type, 0);
	}
	
	/**
	 * @methodType constructor
	 * @methodProperties convenience
	 * 
	 * @param name
	 */
	public InstrumentPhoto(String name){
		this(name, InstrumentType.Unknown);
	}
	
	
	/**
	 * @methodType constructor
	 * @methodProperties convenience
	 * 
	 * @param myId
	 * @param name
	 */
	public InstrumentPhoto(PhotoId myId, String name){
		this(myId, name, InstrumentType.Unknown);
	}
	
	/**
	 * @methodType constructor
	 * @methodProperties convenience
	 */
	public InstrumentPhoto(){
		this("");
	}
	
	/**
	 * @methodType constructor
	 * @methodProperties convenience
	 */
	public InstrumentPhoto(PhotoId myId){
		this(myId, "");
	}
	
	/**
	 * @methodType get
	 * @methodProperties primitive
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @methodType set
	 * @methodProperties primitive
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @methodType get
	 * @methodProperties primitive
	 * 
	 * @return the type
	 */
	public InstrumentType getType() {
		return type;
	}

	/**
	 * @methodType set
	 * @methodProperties primitive
	 * 
	 * @param type the type to set
	 */
	public void setType(InstrumentType type) {
		this.type = type;
	}

	/**
	 * @methodType get
	 * @methodProperties primitive
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @methodType set
	 * @methodProperties primitive
	 * 
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

}
