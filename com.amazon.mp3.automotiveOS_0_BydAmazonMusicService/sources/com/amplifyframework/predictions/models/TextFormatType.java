package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum TextFormatType implements IdentifyAction {
    FORM,
    TABLE,
    PLAIN,
    ALL;

    @Override // com.amplifyframework.predictions.models.IdentifyAction
    @NonNull
    public final IdentifyActionType getType() {
        return IdentifyActionType.DETECT_TEXT;
    }
}
