package org.apache.http;

import java.nio.charset.CharacterCodingException;

/* JADX INFO: loaded from: classes7.dex */
public class MessageConstraintException extends CharacterCodingException {
    private final String message;

    public MessageConstraintException(String str) {
        this.message = str;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
