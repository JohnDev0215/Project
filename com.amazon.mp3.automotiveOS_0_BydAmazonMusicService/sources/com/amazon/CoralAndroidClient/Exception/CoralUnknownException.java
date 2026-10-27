package com.amazon.CoralAndroidClient.Exception;

/* JADX INFO: loaded from: classes2.dex */
public class CoralUnknownException extends CoralException {
    private final String mData;
    private final String mType;

    public CoralUnknownException(String str, String str2) {
        super(str);
        this.mType = str;
        this.mData = str2;
    }
}
