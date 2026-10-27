package io.reactivex.internal.util;

import d.a.i;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public enum NotificationLite {
    COMPLETE;

    static final class DisposableNotification implements Serializable {
        private static final long serialVersionUID = -7482590109178395495L;
        final io.reactivex.disposables.b upstream;

        DisposableNotification(io.reactivex.disposables.b bVar) {
            this.upstream = bVar;
        }

        public String toString() {
            return "NotificationLite.Disposable[" + this.upstream + "]";
        }
    }

    static final class ErrorNotification implements Serializable {
        private static final long serialVersionUID = -8759979445933046293L;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final Throwable f4358e;

        ErrorNotification(Throwable th) {
            this.f4358e = th;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ErrorNotification) {
                return d.a.n.a.b.c(this.f4358e, ((ErrorNotification) obj).f4358e);
            }
            return false;
        }

        public int hashCode() {
            return this.f4358e.hashCode();
        }

        public String toString() {
            return "NotificationLite.Error[" + this.f4358e + "]";
        }
    }

    static final class SubscriptionNotification implements Serializable {
        private static final long serialVersionUID = -1322257508628817540L;
        final e.a.b upstream;

        SubscriptionNotification(e.a.b bVar) {
            this.upstream = bVar;
        }

        public String toString() {
            return "NotificationLite.Subscription[" + this.upstream + "]";
        }
    }

    public static <T> boolean accept(Object obj, e.a.a<? super T> aVar) {
        if (obj == COMPLETE) {
            aVar.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            aVar.onError(((ErrorNotification) obj).f4358e);
            return true;
        }
        aVar.onNext(obj);
        return false;
    }

    public static <T> boolean acceptFull(Object obj, e.a.a<? super T> aVar) {
        if (obj == COMPLETE) {
            aVar.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            aVar.onError(((ErrorNotification) obj).f4358e);
            return true;
        }
        if (obj instanceof SubscriptionNotification) {
            aVar.a(((SubscriptionNotification) obj).upstream);
            return false;
        }
        aVar.onNext(obj);
        return false;
    }

    public static Object complete() {
        return COMPLETE;
    }

    public static Object disposable(io.reactivex.disposables.b bVar) {
        return new DisposableNotification(bVar);
    }

    public static Object error(Throwable th) {
        return new ErrorNotification(th);
    }

    public static io.reactivex.disposables.b getDisposable(Object obj) {
        return ((DisposableNotification) obj).upstream;
    }

    public static Throwable getError(Object obj) {
        return ((ErrorNotification) obj).f4358e;
    }

    public static e.a.b getSubscription(Object obj) {
        return ((SubscriptionNotification) obj).upstream;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T getValue(Object obj) {
        return obj;
    }

    public static boolean isComplete(Object obj) {
        return obj == COMPLETE;
    }

    public static boolean isDisposable(Object obj) {
        return obj instanceof DisposableNotification;
    }

    public static boolean isError(Object obj) {
        return obj instanceof ErrorNotification;
    }

    public static boolean isSubscription(Object obj) {
        return obj instanceof SubscriptionNotification;
    }

    public static <T> Object next(T t) {
        return t;
    }

    public static Object subscription(e.a.b bVar) {
        return new SubscriptionNotification(bVar);
    }

    @Override // java.lang.Enum
    public String toString() {
        return "NotificationLite.Complete";
    }

    public static <T> boolean accept(Object obj, i<? super T> iVar) {
        if (obj == COMPLETE) {
            iVar.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            iVar.onError(((ErrorNotification) obj).f4358e);
            return true;
        }
        iVar.onNext(obj);
        return false;
    }

    public static <T> boolean acceptFull(Object obj, i<? super T> iVar) {
        if (obj == COMPLETE) {
            iVar.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            iVar.onError(((ErrorNotification) obj).f4358e);
            return true;
        }
        if (obj instanceof DisposableNotification) {
            iVar.onSubscribe(((DisposableNotification) obj).upstream);
            return false;
        }
        iVar.onNext(obj);
        return false;
    }
}
