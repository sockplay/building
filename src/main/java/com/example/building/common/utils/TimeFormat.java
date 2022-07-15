package com.example.building.common.utils;

/**
 * Definition class for timestamp format
 */
public enum TimeFormat {
    HHmmss("HHmmss"), //
    HHmmssSSS_COLON("HH:mm:ss.SSS"), //
    HHmmss_COLON("HH:mm:ss"),
    HHmm("HHmm"),//
    HHmm_COLON("HH:mm"),//
    ;

    private String value;

    private TimeFormat(String value) {
        this.value = value;
    }

    final public String getValue() {
        return this.value;
    }
}
