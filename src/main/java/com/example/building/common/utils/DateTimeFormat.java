package com.example.building.common.utils;

/**
 * DateTimeFormat
 */
public enum DateTimeFormat {
    yyMMdd("yyMMdd"), //
    yyyyMMdd("yyyyMMdd"), //
    yyyyMMddHHmm_SLASH("yyyy/MM/dd HH:mm"), //
    yyyyMMddHHmmss_SLASH("yyyy/MM/dd HH:mm:ss"), //
    yyyyMMdd_SLASH("yyyy/MM/dd"), //
    yyyyMMdd_HYPHEN("yyyy-MM-dd"),//
    MMdd_SLASH("MM/dd"), //
    HHmmss("HH:mm:ss"), //
    yyyyMMddHHmmss("yyyyMMddHHmmss"), //
    yyyyMMddHHmmss_T("yyyyMMdd'T'HHmmss"), //
    yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"), //
    yyyyMMddHHmmssSSS_SLASH("yyyy/MM/dd HH:mm:ss.SSS"), //
    yyyyMMddHHmm_HYPHEN("yyyy-MM-dd HH:mm"), //
    yyyyMMddHHmm_T_HYPHEN("yyyy-MM-dd'T'HH:mm"), //
    yyyyMMddHHmmss_HYPHEN("yyyy-MM-dd HH:mm:ss"), //
    ;

    private String value;

    private DateTimeFormat(String value) {
        this.value = value;
    }

    final public String getValue() {
        return this.value;
    }
}
