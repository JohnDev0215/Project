package com.amplifyframework.predictions.operation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.async.AmplifyOperation;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.predictions.models.IdentifyAction;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public abstract class IdentifyOperation<R> extends AmplifyOperation<R> {
    private final IdentifyAction identifyAction;

    public IdentifyOperation(@NonNull IdentifyAction identifyAction, @Nullable R r) {
        super(CategoryType.PREDICTIONS, r);
        Objects.requireNonNull(identifyAction);
        this.identifyAction = identifyAction;
    }

    @NonNull
    public IdentifyAction getIdentifyAction() {
        return this.identifyAction;
    }
}
