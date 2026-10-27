package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum IdentifyActionType implements IdentifyAction {
    DETECT_CELEBRITIES,
    DETECT_LABELS,
    DETECT_ENTITIES,
    DETECT_TEXT;

    @Override // com.amplifyframework.predictions.models.IdentifyAction
    @NonNull
    public final IdentifyActionType getType() {
        return this;
    }
}
