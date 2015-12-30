package org.wahlzeit.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.googlecode.objectify.annotation.Subclass;

/**
 * Class containing a position expressed as latitude and longitude
 *
 */
@Subclass(index = true)
public class InstrumentPhoto extends Photo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8613558234551114986L;
	protected String name;
	protected Set<Instrument> instruments = Collections.synchronizedSet(new HashSet<Instrument>());

	/**
	 * @methodType constructor
	 * 
	 * @param myId
	 * @param name
	 */
	public InstrumentPhoto(PhotoId myId, String name) {
		super(myId);
		this.name = name;
	}

	/**
	 * @methodType constructor
	 * 
	 * @param name
	 */
	public InstrumentPhoto(String name) {
		super();
		this.name = name;
	}

	/**
	 * @methodType constructor
	 * @methodProperties convenience
	 */
	public InstrumentPhoto() {
		this("");
	}

	/**
	 * @methodType constructor
	 * @methodProperties convenience
	 */
	public InstrumentPhoto(PhotoId myId) {
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @methodType get
	 * 
	 */
	public Iterator<Instrument> getInstrumentIterator() {
		return instruments.iterator();
	}

	/**
	 * @methodType set
	 */
	public void addInstrument(Instrument inst){
		instruments.add(inst);
	}
}
