package com.github.chistousov.lib.astm1394.record.result;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This field should indicate the normal status of the result. Symbols for Significant Up or Down Changes or Abnormal Values
 * (Перечисление хранит - Это поле должно указывать на нормальный статус результата. Символы для обозначения значительных изменений вверх или вниз или аномальных значений)
 * </p>
 *
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
public enum ResultAbnormalFlag {
	UNDEFINED("U", "Undefined", "неопределенный"),
	L("L", "below low normal", "ниже низкого нормального"),
	H("H", "above high normal", "выше нормы"),
	LL("LL", "below panic normal", "ниже панической нормали"),
	HH("HH", "above panic high", "выше панической нормали"),
	BELOWABSOLUTELOW("<", "below absolute low, that is off low scale on instrument", "ниже абсолютного минимума, то есть за пределами нижней шкалы на инструменте"),
	ABOVEABSOLUTEHIGH(">", "above absolute high, that is off high scale on an instrument", "выше абсолютного максимума, то есть за пределами высокой шкалы инструмента"),
	N("N", "normal", "нормальный"),
	A("A", "abnormal", "аномальный"),
	U("U", "significant change up", "значительное изменение вверх"),
	D("D", "significant change down", "значительное изменение вниз"),
	B("B", "better, use when direction not relevant or not defined", "лучше использовать, когда направление не актуально или не определено"),
	W("W", "worse, use when direction not relevant or not defined", "хуже, используйте, когда направление не актуально или не определено");
    
	private String id;
    private String fullName;
    private String fullNameRus;

    ResultAbnormalFlag(String id, String fullName, String fullNameRus){
        this.id = id;
        this.fullName = fullName;
        this.fullNameRus = fullNameRus;
    }
    
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
    
    public String getFullNameRus() {
        return fullNameRus;
    }

    @Override
    public String toString() {
        return fullNameRus + "(" + id + ")";
    }

	public String getIdForComponent(){
		if(this.id.equals("U")){
			return "";
		}
		return getId();
	}

    private static final Map<String, ResultAbnormalFlag> flags = new HashMap<>();

	static {
		flags.put(UNDEFINED.getId(), UNDEFINED);
		flags.put(L.getId(), L);
		flags.put(H.getId(), H);
		flags.put(LL.getId(), LL);
		flags.put(HH.getId(), HH);
		flags.put(BELOWABSOLUTELOW.getId(), BELOWABSOLUTELOW);
		flags.put(ABOVEABSOLUTEHIGH.getId(), ABOVEABSOLUTEHIGH);
		flags.put(N.getId(), N);
		flags.put(A.getId(), A);
		flags.put(U.getId(), U);
		flags.put(D.getId(), D);
		flags.put(B.getId(), B);
		flags.put(W.getId(), W);	
	}

	public static ResultAbnormalFlag getBy(String id) {
        ResultAbnormalFlag flag = flags.get(id);
        if(flag == null){
            flag = UNDEFINED;
        }
        return flag;
	}
}
