package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.tg2;
import p.u4;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class SubscriptionHelper implements tg2 {
    public static final SubscriptionHelper c;
    public static final /* synthetic */ SubscriptionHelper[] e;

    static {
        SubscriptionHelper subscriptionHelper = new SubscriptionHelper("CANCELLED", 0);
        c = subscriptionHelper;
        e = new SubscriptionHelper[]{subscriptionHelper};
    }

    public static boolean b(AtomicReference atomicReference) {
        tg2 tg2Var;
        tg2 tg2Var2 = (tg2) atomicReference.get();
        SubscriptionHelper subscriptionHelper = c;
        if (tg2Var2 == subscriptionHelper || (tg2Var = (tg2) atomicReference.getAndSet(subscriptionHelper)) == subscriptionHelper) {
            return false;
        }
        if (tg2Var == null) {
            return true;
        }
        tg2Var.cancel();
        return true;
    }

    public static void c(AtomicReference atomicReference, AtomicLong atomicLong, long j) {
        tg2 tg2Var = (tg2) atomicReference.get();
        if (tg2Var != null) {
            tg2Var.a(j);
            return;
        }
        if (f(j)) {
            ci1.d(atomicLong, j);
            tg2 tg2Var2 = (tg2) atomicReference.get();
            if (tg2Var2 != null) {
                long andSet = atomicLong.getAndSet(0L);
                if (andSet != 0) {
                    tg2Var2.a(andSet);
                }
            }
        }
    }

    public static void d(AtomicReference atomicReference, AtomicLong atomicLong, tg2 tg2Var) {
        if (e(atomicReference, tg2Var)) {
            long andSet = atomicLong.getAndSet(0L);
            if (andSet != 0) {
                tg2Var.a(andSet);
            }
        }
    }

    public static boolean e(AtomicReference atomicReference, tg2 tg2Var) {
        Objects.requireNonNull(tg2Var, "s is null");
        while (!atomicReference.compareAndSet(null, tg2Var)) {
            if (atomicReference.get() != null) {
                tg2Var.cancel();
                if (atomicReference.get() == c) {
                    return false;
                }
                RxJavaPlugins.b(new ProtocolViolationException("Subscription already set!"));
                return false;
            }
        }
        return true;
    }

    public static boolean f(long j) {
        if (j > 0) {
            return true;
        }
        RxJavaPlugins.b(new IllegalArgumentException(u4.g("n > 0 required but it was ", j)));
        return false;
    }

    public static boolean g(tg2 tg2Var, tg2 tg2Var2) {
        if (tg2Var2 == null) {
            RxJavaPlugins.b(new NullPointerException("next is null"));
            return false;
        }
        if (tg2Var == null) {
            return true;
        }
        tg2Var2.cancel();
        RxJavaPlugins.b(new ProtocolViolationException("Subscription already set!"));
        return false;
    }

    public static SubscriptionHelper valueOf(String str) {
        return (SubscriptionHelper) Enum.valueOf(SubscriptionHelper.class, str);
    }

    public static SubscriptionHelper[] values() {
        return (SubscriptionHelper[]) e.clone();
    }

    @Override // p.tg2
    public final void a(long j) {
    }

    @Override // p.tg2
    public final void cancel() {
    }
}
