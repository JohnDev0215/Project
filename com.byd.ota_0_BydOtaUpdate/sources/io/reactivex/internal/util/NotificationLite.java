package io.reactivex.internal.util;

import d.a.i;
import d.a.m.b;
import g.b.c;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public enum NotificationLite {
    COMPLETE;

    public static final class DisposableNotification implements Serializable {
        private static final long serialVersionUID = -7482590109178395495L;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b f5916d;

        public DisposableNotification(b bVar) {
            this.f5916d = bVar;
        }

        public String toString() {
            return "NotificationLite.Disposable[" + this.f5916d + "]";
        }
    }

    public static final class ErrorNotification implements Serializable {
        private static final long serialVersionUID = -8759979445933046293L;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Throwable f5917e;

        public ErrorNotification(Throwable th) {
            this.f5917e = th;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ErrorNotification) {
                return d.a.p.b.b.c(this.f5917e, ((ErrorNotification) obj).f5917e);
            }
            return false;
        }

        public int hashCode() {
            return this.f5917e.hashCode();
        }

        public String toString() {
            return "NotificationLite.Error[" + this.f5917e + "]";
        }
    }

    public static final class SubscriptionNotification implements Serializable {
        private static final long serialVersionUID = -1322257508628817540L;
        public final c s;

        public SubscriptionNotification(c cVar) {
            this.s = cVar;
        }

        public String toString() {
            return "NotificationLite.Subscription[" + this.s + "]";
        }
    }

    public static <T> boolean accept(Object obj, g.b.b<? super T> bVar) {
        if (obj == COMPLETE) {
            bVar.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            bVar.onError(((ErrorNotification) obj).f5917e);
            return true;
        }
        bVar.onNext(obj);
        return false;
    }

    public static <T> boolean acceptFull(Object obj, g.b.b<? super T> bVar) {
        if (obj == COMPLETE) {
            bVar.onComplete();
            return true;
        }
        if (obj instanceof ErrorNotification) {
            bVar.onError(((ErrorNotification) obj).f5917e);
            return true;
        }
        if (obj instanceof SubscriptionNotification) {
            bVar.a(((SubscriptionNotification) obj).s);
            return false;
        }
        bVar.onNext(obj);
        return false;
    }

    public static Object complete() {
        return COMPLETE;
    }

    public static Object disposable(b bVar) {
        return new DisposableNotification(bVar);
    }

    public static Object error(Throwable th) {
        return new ErrorNotification(th);
    }

    public static b getDisposable(Object obj) {
        return ((DisposableNotification) obj).f5916d;
    }

    public static Throwable getError(Object obj) {
        return ((ErrorNotification) obj).f5917e;
    }

    public static c getSubscription(Object obj) {
        return ((SubscriptionNotification) obj).s;
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

    public static Object subscription(c cVar) {
        return new SubscriptionNotification(cVar);
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
            iVar.onError(((ErrorNotification) obj).f5917e);
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
            iVar.onError(((ErrorNotification) obj).f5917e);
            return true;
        }
        if (obj instanceof DisposableNotification) {
            iVar.onSubscribe(((DisposableNotification) obj).f5916d);
            return false;
        }
        iVar.onNext(obj);
        return false;
    }
}
