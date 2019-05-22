package org.sct.legendgang.exception;

import lombok.Getter;

/**
 * @author SCT_Alchemy
 * @date 2019-05-22 20:52
 */

public class DataUpdateException extends Exception{

    @Getter private String message;

    public DataUpdateException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public DataUpdateException(String message, String message1) {
        super(message);
        this.message = message1;
    }

}
