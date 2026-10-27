package com.facebook.react.devsupport;

import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class JSException extends Exception {
    private final String mStack;

    @DoNotStrip
    public JSException(String str, String str2, Throwable th) {
        super(str, th);
        this.mStack = str2;
    }
}
