package io.reactivex.rxjava3.internal.util;

import java.io.Serializable;
import java.util.Objects;
import spi.bk;
import spi.e;
import spi.os;
import spi.ps;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public enum NotificationLite {
    COMPLETE;

    public static final class a implements Serializable {
        private static final long serialVersionUID = -7482590109178395495L;
        public final q6 a;

        public a(q6 q6Var) {
            this.a = q6Var;
        }

        public final String toString() {
            StringBuilder sbK = e.k("NotificationLite.Disposable[");
            sbK.append(this.a);
            sbK.append("]");
            return sbK.toString();
        }
    }

    public static final class b implements Serializable {
        private static final long serialVersionUID = -8759979445933046293L;
        public final Throwable a;

        public b(Throwable th) {
            this.a = th;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof b) {
                return Objects.equals(this.a, ((b) obj).a);
            }
            return false;
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        public final String toString() {
            StringBuilder sbK = e.k("NotificationLite.Error[");
            sbK.append(this.a);
            sbK.append("]");
            return sbK.toString();
        }
    }

    public static final class c implements Serializable {
        private static final long serialVersionUID = -1322257508628817540L;
        public final ps a;

        public c(ps psVar) {
            this.a = psVar;
        }

        public final String toString() {
            StringBuilder sbK = e.k("NotificationLite.Subscription[");
            sbK.append(this.a);
            sbK.append("]");
            return sbK.toString();
        }
    }

    public static <T> boolean accept(Object obj, bk<? super T> bkVar) {
        if (obj == COMPLETE) {
            bkVar.onComplete();
            return true;
        }
        if (obj instanceof b) {
            bkVar.onError(((b) obj).a);
            return true;
        }
        bkVar.onNext(obj);
        return false;
    }

    public static <T> boolean accept(Object obj, os<? super T> osVar) {
        if (obj == COMPLETE) {
            osVar.onComplete();
            return true;
        }
        if (obj instanceof b) {
            osVar.onError(((b) obj).a);
            return true;
        }
        osVar.onNext(obj);
        return false;
    }

    public static <T> boolean acceptFull(Object obj, bk<? super T> bkVar) {
        if (obj == COMPLETE) {
            bkVar.onComplete();
            return true;
        }
        if (obj instanceof b) {
            bkVar.onError(((b) obj).a);
            return true;
        }
        if (obj instanceof a) {
            bkVar.onSubscribe(((a) obj).a);
            return false;
        }
        bkVar.onNext(obj);
        return false;
    }

    public static <T> boolean acceptFull(Object obj, os<? super T> osVar) {
        if (obj == COMPLETE) {
            osVar.onComplete();
            return true;
        }
        if (obj instanceof b) {
            osVar.onError(((b) obj).a);
            return true;
        }
        if (obj instanceof c) {
            osVar.onSubscribe(((c) obj).a);
            return false;
        }
        osVar.onNext(obj);
        return false;
    }

    public static Object complete() {
        return COMPLETE;
    }

    public static Object disposable(q6 q6Var) {
        return new a(q6Var);
    }

    public static Object error(Throwable th) {
        return new b(th);
    }

    public static q6 getDisposable(Object obj) {
        return ((a) obj).a;
    }

    public static Throwable getError(Object obj) {
        return ((b) obj).a;
    }

    public static ps getSubscription(Object obj) {
        return ((c) obj).a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T getValue(Object obj) {
        return obj;
    }

    public static boolean isComplete(Object obj) {
        return obj == COMPLETE;
    }

    public static boolean isDisposable(Object obj) {
        return obj instanceof a;
    }

    public static boolean isError(Object obj) {
        return obj instanceof b;
    }

    public static boolean isSubscription(Object obj) {
        return obj instanceof c;
    }

    public static <T> Object next(T t) {
        return t;
    }

    public static Object subscription(ps psVar) {
        return new c(psVar);
    }

    @Override // java.lang.Enum
    public String toString() {
        return "NotificationLite.Complete";
    }
}
