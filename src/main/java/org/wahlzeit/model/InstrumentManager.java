package org.wahlzeit.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InstrumentManager {
	protected Map<String, InstrumentType> iTypes = Collections.synchronizedMap(new HashMap<String, InstrumentType>());
	protected Map<Integer, Instrument> instruments = Collections.synchronizedMap(new HashMap<Integer, Instrument>());

	/**
	 * @methodType factory
	 */
	public Instrument createInstrument(String typeName) {
		InstrumentType it = getInstrumentType(typeName);
		assert (it != null) : "invalid InstrumentTypeName.";
		Instrument result = it.createInstance();
		instruments.put(result.getId(), result);
		return result;
	}

	/**
	 * @methodType factory
	 */
	public InstrumentType createInstrumentType(String typeName) {
		InstrumentType it = getInstrumentType(typeName);
		if (it == null) {
			it = new InstrumentType(typeName);
			addInstrumentType(typeName, it);
			return it;
		} else {
			return it;
		}
	}

	/**
	 * @methodType get
	 */
	protected InstrumentType getInstrumentType(String typeName) {
		return iTypes.get(typeName);
	}

	/**
	 * @methodType set
	 */
	protected void addInstrumentType(String typeName, InstrumentType it) {
		iTypes.put(typeName, it);
	}

	/**
	 * @methodType get
	 */
	public Instrument getById(int id) {
		return instruments.get(id);
	}
}
