package com.facebook.react.common;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public class JavascriptException extends RuntimeException {

    @Nullable
    private String extraDataAsJson;

    public JavascriptException(String str) {
        super(str);
    }

    public JavascriptException setExtraDataAsJson(@Nullable String str) {
        this.extraDataAsJson = str;
        return this;
    }
}
