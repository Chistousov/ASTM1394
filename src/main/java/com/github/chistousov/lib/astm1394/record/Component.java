package com.github.chistousov.lib.astm1394.record;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * <p>
 * A generic class that encapsulates some logic for working with a component (component) 
 * (Класс-дженерик, инкапсулирующий некоторую логику работы с компонентом (component))
 * </p>
 * @author Nikita Chistousov (chistousov.nik@yandex.ru)
 * @since 8
 */
@SuppressWarnings("unchecked")
public class Component<T> {
    private T value;

	private Function<T, String> toStringHandler;

	private Class<T> persistentClass;

    public Component(Supplier<T> handler, Function<T, String> toStringHandler){
        this.value = handler.get();
		this.toStringHandler = toStringHandler;
    }

    public Component(Class<T> persistentClass, String value) {
		this.persistentClass = persistentClass;
        
		try{
            if(persistentClass.equals(String.class)){
                this.value = (T)value;
            }  else if (persistentClass.equals(Short.class)){
                this.value = (T)Short.valueOf(value);
            } else if (persistentClass.equals(Integer.class)){
                this.value = (T)Integer.valueOf(value);
            } else if (persistentClass.equals(Long.class)){
                this.value = (T)Long.valueOf(value);
            } else if (persistentClass.equals(LocalDateTime.class)){
                this.value = (T)LocalDateTime.parse(value, GeneralConsiderations.DATE_TIME_FORMATTER);
            } else if (persistentClass.equals(LocalDate.class)){
                this.value = (T)LocalDate.parse(value, GeneralConsiderations.DATE_FORMATTER);
            }
        } catch(Exception ex){
            this.value = null;
        }
    }

    public T getValue() {
        return value;
    }

	@Override
	public String toString() {
		if(this.value != null){
			if(this.toStringHandler != null){
				return this.toStringHandler.apply(this.value);
			} else if(this.persistentClass.equals(String.class)){
                return this.value.toString();
            } else if (this.persistentClass.equals(Short.class)){
                return this.value.toString();
            } else if (persistentClass.equals(Integer.class)){
                return this.value.toString();
            } else if (persistentClass.equals(Long.class)){
				return this.value.toString();
            } else if (persistentClass.equals(LocalDateTime.class)){
                return ((LocalDateTime)this.value).format(GeneralConsiderations.DATE_TIME_FORMATTER);
            } else if (persistentClass.equals(LocalDate.class)){
                return ((LocalDate)this.value).format(GeneralConsiderations.DATE_FORMATTER);
            }
			
		}
		return "";
	}
}
