package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.operators.d;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class EmptySubscription implements d {
    public static final EmptySubscription c;
    public static final /* synthetic */ EmptySubscription[] e;

    static {
        EmptySubscription emptySubscription = new EmptySubscription("INSTANCE", 0);
        c = emptySubscription;
        e = new EmptySubscription[]{emptySubscription};
    }

    public static EmptySubscription valueOf(String str) {
        return (EmptySubscription) Enum.valueOf(EmptySubscription.class, str);
    }

    public static EmptySubscription[] values() {
        return (EmptySubscription[]) e.clone();
    }

    @Override // p.tg2
    public final void a(long j) {
        SubscriptionHelper.f(j);
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        return i & 2;
    }

    @Override // p.tg2
    public final void cancel() {
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return true;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        return null;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "EmptySubscription";
    }
}
