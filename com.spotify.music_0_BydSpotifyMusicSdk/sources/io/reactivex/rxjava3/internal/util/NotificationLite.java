package io.reactivex.rxjava3.internal.util;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.io.Serializable;
import java.util.Objects;
import p.rg2;
import p.tg2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class NotificationLite {
    public static final NotificationLite c;
    public static final /* synthetic */ NotificationLite[] e;

    public static final class DisposableNotification implements Serializable {
        private static final long serialVersionUID = -7482590109178395495L;
        final Disposable upstream;

        public DisposableNotification(Disposable disposable) {
            this.upstream = disposable;
        }

        public final String toString() {
            return "NotificationLite.Disposable[" + this.upstream + "]";
        }
    }

    public static final class ErrorNotification implements Serializable {
        private static final long serialVersionUID = -8759979445933046293L;
        final Throwable e;

        public ErrorNotification(Throwable th) {
            this.e = th;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof ErrorNotification) {
                return Objects.equals(this.e, ((ErrorNotification) obj).e);
            }
            return false;
        }

        public final int hashCode() {
            return this.e.hashCode();
        }

        public final String toString() {
            return "NotificationLite.Error[" + this.e + "]";
        }
    }

    public static final class SubscriptionNotification implements Serializable {
        private static final long serialVersionUID = -1322257508628817540L;
        final tg2 upstream;

        public SubscriptionNotification(tg2 tg2Var) {
            this.upstream = tg2Var;
        }

        public final String toString() {
            return "NotificationLite.Subscription[" + this.upstream + "]";
        }
    }

    static {
        NotificationLite notificationLite = new NotificationLite("COMPLETE", 0);
        c = notificationLite;
        e = new NotificationLite[]{notificationLite};
    }

    public static boolean a(Observer observer, Object obj) {
        if (obj == c) {
            observer.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            observer.onError(((ErrorNotification) obj).e);
            return true;
        }
        observer.onNext(obj);
        return false;
    }

    public static boolean b(Object obj, rg2 rg2Var) {
        if (obj == c) {
            rg2Var.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            rg2Var.onError(((ErrorNotification) obj).e);
            return true;
        }
        rg2Var.onNext(obj);
        return false;
    }

    public static boolean c(Observer observer, Object obj) {
        if (obj == c) {
            observer.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            observer.onError(((ErrorNotification) obj).e);
            return true;
        }
        if (obj instanceof DisposableNotification) {
            observer.onSubscribe(((DisposableNotification) obj).upstream);
            return false;
        }
        observer.onNext(obj);
        return false;
    }

    public static boolean d(Object obj, rg2 rg2Var) {
        if (obj == c) {
            rg2Var.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            rg2Var.onError(((ErrorNotification) obj).e);
            return true;
        }
        if (obj instanceof SubscriptionNotification) {
            rg2Var.onSubscribe(((SubscriptionNotification) obj).upstream);
            return false;
        }
        rg2Var.onNext(obj);
        return false;
    }

    public static Object e(Disposable disposable) {
        return new DisposableNotification(disposable);
    }

    public static Object f(Throwable th) {
        return new ErrorNotification(th);
    }

    public static Throwable g(Object obj) {
        return ((ErrorNotification) obj).e;
    }

    public static boolean h(Object obj) {
        return obj == c;
    }

    public static boolean i(Object obj) {
        return obj instanceof ErrorNotification;
    }

    public static Object j(tg2 tg2Var) {
        return new SubscriptionNotification(tg2Var);
    }

    public static NotificationLite valueOf(String str) {
        return (NotificationLite) Enum.valueOf(NotificationLite.class, str);
    }

    public static NotificationLite[] values() {
        return (NotificationLite[]) e.clone();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "NotificationLite.Complete";
    }
}
