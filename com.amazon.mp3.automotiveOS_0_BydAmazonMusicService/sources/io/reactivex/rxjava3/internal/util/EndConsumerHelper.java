package io.reactivex.rxjava3.internal.util;

import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes7.dex */
public final class EndConsumerHelper {
    private EndConsumerHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static boolean setOnce(AtomicReference<Disposable> upstream, Disposable next, Class<?> observer) {
        Objects.requireNonNull(next, "next is null");
        if (CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(upstream, null, next)) {
            return true;
        }
        next.dispose();
        if (upstream.get() == DisposableHelper.DISPOSED) {
            return false;
        }
        reportDoubleSubscription(observer);
        return false;
    }

    public static String composeMessage(String consumer) {
        return "It is not allowed to subscribe with a(n) " + consumer + " multiple times. Please create a fresh instance of " + consumer + " and subscribe that to the target source instead.";
    }

    public static void reportDoubleSubscription(Class<?> consumer) {
        RxJavaPlugins.onError(new ProtocolViolationException(composeMessage(consumer.getName())));
    }
}
