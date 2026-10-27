package com.amplifyframework.predictions.models;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum LabelType implements IdentifyAction {
    LABELS,
    MODERATION_LABELS,
    ALL;

    @Override // com.amplifyframework.predictions.models.IdentifyAction
    @NonNull
    public final IdentifyActionType getType() {
        return IdentifyActionType.DETECT_LABELS;
    }
}
