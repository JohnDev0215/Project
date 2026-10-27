package com.amazon.avod.fsm;

import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public final class SimpleTrigger<T> implements Trigger<T> {
    private final T mType;

    public SimpleTrigger(T t) {
        this.mType = (T) Preconditions.checkNotNull(t);
    }

    @Override // com.amazon.avod.fsm.Trigger
    public T getType() {
        return this.mType;
    }

    public String toString() {
        return String.format(Locale.US, "Trigger:%s", this.mType.toString());
    }
}
