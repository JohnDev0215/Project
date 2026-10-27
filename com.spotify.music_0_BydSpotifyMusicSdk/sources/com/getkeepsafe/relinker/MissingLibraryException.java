package com.getkeepsafe.relinker;

import java.util.Arrays;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public class MissingLibraryException extends RuntimeException {
    /* JADX WARN: Illegal instructions before constructor call */
    public MissingLibraryException(String str, String[] strArr, String[] strArr2) {
        StringBuilder sbP = u4.p("Could not find '", str, "'. Looked for: ");
        sbP.append(Arrays.toString(strArr));
        sbP.append(", but only found: ");
        super(u4.m(sbP, Arrays.toString(strArr2), "."));
    }
}
