package ru.gb.diplom.util;

import java.util.Objects;
import java.util.function.Consumer;

final public class EntityUtils {

    public static <T> void setter(T value, Consumer<T> setter){
        Objects.requireNonNull(setter);
        if(value != null){
            setter.accept(value);
        }
    }



}
