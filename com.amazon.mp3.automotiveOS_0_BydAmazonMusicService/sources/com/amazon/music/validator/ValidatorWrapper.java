package com.amazon.music.validator;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public final class ValidatorWrapper {
    private final Validator validator;

    ValidatorWrapper(@Nullable Validator validator) {
        this.validator = validator;
    }

    public boolean isAllowed(boolean z) {
        Validator validator = this.validator;
        return validator == null ? z : validator.isAllowed();
    }
}
