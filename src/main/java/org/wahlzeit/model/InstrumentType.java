package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.wahlzeit.services.DataObject;

public class InstrumentType extends DataObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -530853351122920949L;
	protected String typeName;
	protected double pitchRangeStart;
	protected double pitchRangeEnd;
	protected String countryOfOrigin;
	protected InstrumentType superType = null;
	protected Set<InstrumentType> subTypes = new HashSet<InstrumentType>();
	//protected Set<Instrument> instances = new HashSet<Instrument>(); 
	
	/**
	 * @methodType constructor 
	 */
	public InstrumentType(String typeName){
		this.typeName = typeName;
	}
	
	/**
	 * @methodType factory
	 */
	public Instrument createInstance(){
		return new Instrument(this);
	}

	/**
	 * @methodType get
	 */
	public InstrumentType getSuperType(){
		return superType;
	}
	
	/**
	 * @methodType get
	 */
	public Iterator<InstrumentType> getSubTypeIterator(){
		return subTypes.iterator();
	}
	
	/**
	 * @methodType set
	 */
	public void addSubType(InstrumentType it){
		assert (it != null) : "Tried to set null sub-types";
		subTypes.add(it);
		it.setSuperType(this);
	}
	
	/**
	 * @methodType boolean query
	 */
	public boolean isInstance(Instrument instrument){
		assert (instrument != null) : "Null instrument object given.";
		if(instrument.getType() == this){
			return true;
		}
		
		for(InstrumentType type : subTypes){
			if (type.isInstance(instrument)){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @methodType set
	 */
	protected void setSuperType(InstrumentType superType){
		assert (superType != null) : "Given super type is null.";
		this.superType = superType;
	}

	/**
	 * @methodType get
	 */
	public double getPitchRangeStart() {
		return pitchRangeStart;
	}

	/**
	 * @methodType set
	 */
	public void setPitchRangeStart(double pitchRangeStart) {
		this.pitchRangeStart = pitchRangeStart;
	}

	/**
	 * @methodType get
	 */
	public double getPitchRangeEnd() {
		return pitchRangeEnd;
	}

	/**
	 * @methodType set
	 */
	public void setPitchRangeEnd(double pitchRangeEnd) {
		this.pitchRangeEnd = pitchRangeEnd;
	}

	/**
	 * @methodType get
	 */
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	/**
	 * @methodType set
	 */
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	
	
}
