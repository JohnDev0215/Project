package d.a;

import io.reactivex.internal.util.NotificationLite;

/* JADX INFO: compiled from: Notification.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f<Object> f3160a = new f<>(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f3161b;

    public f(Object obj) {
        this.f3161b = obj;
    }

    public static <T> f<T> a() {
        return (f<T>) f3160a;
    }

    public static <T> f<T> b(Throwable th) {
        d.a.p.b.b.d(th, "error is null");
        return new f<>(NotificationLite.error(th));
    }

    public static <T> f<T> c(T t) {
        d.a.p.b.b.d(t, "value is null");
        return new f<>(t);
    }

    public Throwable d() {
        Object obj = this.f3161b;
        if (NotificationLite.isError(obj)) {
            return NotificationLite.getError(obj);
        }
        return null;
    }

    public boolean e() {
        return NotificationLite.isError(this.f3161b);
    }

    public boolean equals(Object obj) {
        if (obj instanceof f) {
            return d.a.p.b.b.c(this.f3161b, ((f) obj).f3161b);
        }
        return false;
    }

    public boolean f() {
        Object obj = this.f3161b;
        return (obj == null || NotificationLite.isError(obj)) ? false : true;
    }

    public int hashCode() {
        Object obj = this.f3161b;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public String toString() {
        Object obj = this.f3161b;
        if (obj == null) {
            return "OnCompleteNotification";
        }
        if (NotificationLite.isError(obj)) {
            return "OnErrorNotification[" + NotificationLite.getError(obj) + "]";
        }
        return "OnNextNotification[" + this.f3161b + "]";
    }
}
