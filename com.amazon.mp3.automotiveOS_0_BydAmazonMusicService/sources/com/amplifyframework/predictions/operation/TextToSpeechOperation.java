package com.amplifyframework.predictions.operation;

import androidx.annotation.Nullable;
import com.amplifyframework.core.async.AmplifyOperation;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public abstract class TextToSpeechOperation<R> extends AmplifyOperation<R> {
    public TextToSpeechOperation(@Nullable R r) {
        super(CategoryType.PREDICTIONS, r);
    }
}
