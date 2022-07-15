package com.example.building.common.code;

import com.example.building.common.base.code.CodeEnum;

/**
 * The enum for BookingSts.
 */
public enum BookingSts implements CodeEnum<BookingSts> {
    /** Accepted (1) */
    ACCEPTED("1"),
    /** Processing (2) */
    PROCESSING("2"),
    /** Processed (3) */
    PROCESSED("3"),
    /** Canceled (4) */
    CANCELED("4"),
    ;

    /** Enum値 */
    @lombok.Getter
    private String value;

    /** コンストラクタ */
    private BookingSts(final String value) {
        this.value = value;
    }
}
