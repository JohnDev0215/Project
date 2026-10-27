package com.amazon.avod.ads.api;

import androidx.core.view.PointerIconCompat;

/* JADX INFO: loaded from: classes2.dex */
public enum AdBreakErrorCode {
    UNDEFINED_ERROR(900),
    VMAP_SCHEMA_ERROR(1000),
    VMAP_VERSION_UNSUPPORTED(PointerIconCompat.TYPE_CONTEXT_MENU),
    VMAP_PARSING_ERROR(PointerIconCompat.TYPE_HAND),
    ADBREAK_TYPE_UNSUPPORTED(PointerIconCompat.TYPE_HELP),
    GENERAL_DOCUMENT_ERROR(PointerIconCompat.TYPE_WAIT),
    TEMPLATE_TYPE_UNSUPPORTED(1005),
    DOCUMENT_EXTRACTION_ERROR(PointerIconCompat.TYPE_CELL),
    DOCUMENT_RETRIVAL_TIMEOUT(PointerIconCompat.TYPE_CROSSHAIR),
    DOCUMENT_RETRIVAL_ERROR(PointerIconCompat.TYPE_TEXT);

    private int mErrorCode;

    AdBreakErrorCode(int i) {
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }
}
