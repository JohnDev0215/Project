package com.google.common.base;

import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;

    protected abstract B doForward(A a2);

    protected Converter() {
        this(true);
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }

    @CheckForNull
    public final B convert(@CheckForNull A a2) {
        return correctedDoForward(a2);
    }

    @CheckForNull
    B correctedDoForward(@CheckForNull A a2) {
        if (!this.handleNullAutomatically) {
            return unsafeDoForward(a2);
        }
        if (a2 == null) {
            return null;
        }
        return (B) Preconditions.checkNotNull(doForward(a2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckForNull
    private B unsafeDoForward(@CheckForNull A a2) {
        return (B) doForward(NullnessCasts.uncheckedCastNullableTToT(a2));
    }

    @Override // com.google.common.base.Function
    @CheckForNull
    @Deprecated
    public final B apply(@CheckForNull A a2) {
        return convert(a2);
    }

    @Override // com.google.common.base.Function
    public boolean equals(@CheckForNull Object obj) {
        return super.equals(obj);
    }
}
