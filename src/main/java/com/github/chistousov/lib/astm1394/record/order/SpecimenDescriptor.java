package com.github.chistousov.lib.astm1394.record.order;

import com.github.chistousov.lib.astm1394.record.Component;

/**
 * <p>
 * Enum stores - This field may contain two separate elements - the pattern type and the pattern source - as defined in sections 8.4.16.1 and 8.4.16.2. Components must be separated by component separators. 8.4.16.1 Specimen type Sample cultures of type or source: blood, urine, serum, hair, wound, biopsy, sputum, etc. 8.4.16.2 Sample source This is always the second component of the sample descriptor field and is used specifically. to determine the body site of the sample source (eg, left arm, left arm, right lung).
 * (Перечисление хранит - Это поле может содержать два отдельных элемента - тип образца и источник образца - как определено в разделах 8.4.16.1 и 8.4.16.2. Компоненты должны быть разделены разделителями компонентов. 8.4.16.1 Тип образца Образцы образцов культур типа или источников: кровь, моча, сыворотка, волосы, рана, биопсия, мокрота и т. Д. 8.4.16.2 Источник образца Это всегда второй компонент поля дескриптора образца и используется специально. для определения участка тела источника образца (например, левая рука, левая рука, правое легкое).)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public class SpecimenDescriptor {
	
	/*
	 * Образцами культур или источников могут быть кровь, моча, сыворотка, волосы, рана, биопсия, мокрота и т. Д.
	 */
	private Component<String> specimenType;
	

	/*
	 * Это всегда второй компонент поля дескриптора образца, который используется специально для определения участка тела источника образца (например, левая рука, левая рука, правое легкое).
	 */
	private Component<String> specimenSource;

	public SpecimenDescriptor(String specimenType, String specimenSource){
		this.specimenType = new Component<>(String.class, specimenType);
		this.specimenSource = new Component<>(String.class, specimenSource);
	}

	public String getSpecimenSource() {
		return specimenSource.getValue();
	}

	public void setSpecimenSource(String specimenSource) {
		this.specimenSource = new Component<>(String.class, specimenSource);
	}

	public String getSpecimenType() {
		return specimenType.getValue();
	}

	public void setSpecimenType(String specimenType) {
		this.specimenType = new Component<>(String.class, specimenType);
	}
}
