package com.amazon.avod.ads.parser.parsers;

/* JADX INFO: loaded from: classes2.dex */
public enum VmapError {
    ADSOURCE_MULTIPLE_CHILD_TAGS("AdSource: There should be be exactly 1 child tag for <AdSource>");

    private final String mErrorMessage;

    VmapError(String str) {
        this.mErrorMessage = str;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }
}
