package io.reactivex.rxjava3.internal.disposables;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class DisposableHelper implements Disposable {
    public static final DisposableHelper c;
    public static final /* synthetic */ DisposableHelper[] e;

    static {
        DisposableHelper disposableHelper = new DisposableHelper("DISPOSED", 0);
        c = disposableHelper;
        e = new DisposableHelper[]{disposableHelper};
    }

    public static boolean a(AtomicReference atomicReference) {
        Disposable disposable;
        Disposable disposable2 = (Disposable) atomicReference.get();
        DisposableHelper disposableHelper = c;
        if (disposable2 == disposableHelper || (disposable = (Disposable) atomicReference.getAndSet(disposableHelper)) == disposableHelper) {
            return false;
        }
        if (disposable == null) {
            return true;
        }
        disposable.dispose();
        return true;
    }

    public static boolean b(Disposable disposable) {
        return disposable == c;
    }

    public static boolean c(AtomicReference atomicReference, Disposable disposable) {
        while (true) {
            Disposable disposable2 = (Disposable) atomicReference.get();
            if (disposable2 == c) {
                if (disposable == null) {
                    return false;
                }
                disposable.dispose();
                return false;
            }
            while (!atomicReference.compareAndSet(disposable2, disposable)) {
                if (atomicReference.get() != disposable2) {
                    break;
                }
            }
            return true;
        }
    }

    public static boolean d(AtomicReference atomicReference, Disposable disposable) {
        while (true) {
            Disposable disposable2 = (Disposable) atomicReference.get();
            if (disposable2 == c) {
                if (disposable == null) {
                    return false;
                }
                disposable.dispose();
                return false;
            }
            while (!atomicReference.compareAndSet(disposable2, disposable)) {
                if (atomicReference.get() != disposable2) {
                    break;
                }
            }
            if (disposable2 == null) {
                return true;
            }
            disposable2.dispose();
            return true;
        }
    }

    public static boolean f(AtomicReference atomicReference, Disposable disposable) {
        Objects.requireNonNull(disposable, "d is null");
        while (!atomicReference.compareAndSet(null, disposable)) {
            if (atomicReference.get() != null) {
                disposable.dispose();
                if (atomicReference.get() == c) {
                    return false;
                }
                RxJavaPlugins.b(new ProtocolViolationException("Disposable already set!"));
                return false;
            }
        }
        return true;
    }

    public static boolean g(Disposable disposable, Disposable disposable2) {
        if (disposable2 == null) {
            RxJavaPlugins.b(new NullPointerException("next is null"));
            return false;
        }
        if (disposable == null) {
            return true;
        }
        disposable2.dispose();
        RxJavaPlugins.b(new ProtocolViolationException("Disposable already set!"));
        return false;
    }

    public static DisposableHelper valueOf(String str) {
        return (DisposableHelper) Enum.valueOf(DisposableHelper.class, str);
    }

    public static DisposableHelper[] values() {
        return (DisposableHelper[]) e.clone();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return true;
    }
}
