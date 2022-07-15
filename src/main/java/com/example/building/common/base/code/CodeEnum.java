package com.example.building.common.base.code;

import java.util.Arrays;
import java.util.Objects;

/**
 * 区分コードのインタフェース
 * @param <E>
 */
public interface CodeEnum<E extends Enum<E>> {

    /** value値を返却する */
    String getValue();

    public default boolean is(final Object input) {
        return Objects.equals(getValue(), input);
    }

    /** Enumに変換する */
    @SuppressWarnings("unchecked")
    public default E toEnum() {
        return (E) this;
    }

    /** [staticメソッド]　指定されたCodeEnumを実装したEnumの、指定されたコード値の列挙子を返却する */
    public static <E extends Enum<E>> E of(Class<? extends CodeEnum<E>> clazz, String value) {
        return Arrays.stream(clazz.getEnumConstants()) //
                .filter(e -> Objects.equals(e.getValue(), value)) //
                .map(CodeEnum::toEnum) //
                .findFirst() //
                .orElse(null);
    }

    /** Stringの値は、Enumの値かどうかをチェック */
    public static boolean anyMatch(Class<? extends CodeEnum<?>> clazz, String value) {
        return Arrays.stream(clazz.getEnumConstants()) //
                .anyMatch(e -> e.getValue().equals(value)) //
        ;
    }

}
