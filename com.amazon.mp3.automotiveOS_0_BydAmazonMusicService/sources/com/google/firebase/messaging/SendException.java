package com.google.firebase.messaging;

import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public final class SendException extends Exception {
    private final int errorCode;

    SendException(String str) {
        super(str);
        this.errorCode = parseErrorCode(str);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    private int parseErrorCode(String str) {
        if (str == null) {
            return 0;
        }
        String lowerCase = str.toLowerCase(Locale.US);
        lowerCase.hashCode();
        switch (lowerCase) {
        }
        return 0;
    }
}
