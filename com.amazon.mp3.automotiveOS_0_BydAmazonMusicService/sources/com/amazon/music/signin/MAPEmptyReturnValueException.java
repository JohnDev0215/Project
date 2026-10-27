package com.amazon.music.signin;

import android.os.Bundle;
import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MAPEmptyReturnValueException extends Exception {
    private Bundle responseBundle;

    public MAPEmptyReturnValueException(@NonNull Bundle bundle) {
        Validate.notNull(bundle, "responseBundle cannot be null", new Object[0]);
        this.responseBundle = bundle;
    }

    public Bundle getResponseBundle() {
        return this.responseBundle;
    }
}
