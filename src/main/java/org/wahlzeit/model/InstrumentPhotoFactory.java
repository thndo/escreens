package org.wahlzeit.model;

public class InstrumentPhotoFactory extends PhotoFactory {
	/**
	 * @methodType factory
	 * @methodProperties 
	 */
	@Override
	public Photo createPhoto() {
		return new InstrumentPhoto();
	}

	/**
	 * @methodType factory
	 * @methodProperties
	 * 
	 * Creates a new photo with the specified id
	 */
	@Override
	public Photo createPhoto(PhotoId id) {
		return new InstrumentPhoto(id);
	}
}