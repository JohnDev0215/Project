package c.b.h.f;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public enum c {
    COMPLETE;

    public static final class a implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c.b.e.b f2324a;

        public String toString() {
            return "NotificationLite.Disposable[null]";
        }
    }

    public static final class b implements Serializable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Throwable f2325a;

        public b(Throwable th) {
            this.f2325a = th;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof b)) {
                return false;
            }
            Throwable th = this.f2325a;
            Throwable th2 = ((b) obj).f2325a;
            return th == th2 || (th != null && th.equals(th2));
        }

        public int hashCode() {
            return this.f2325a.hashCode();
        }

        public String toString() {
            StringBuilder sbD = b.a.a.a.a.d("NotificationLite.Error[");
            sbD.append(this.f2325a);
            sbD.append("]");
            return sbD.toString();
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return "NotificationLite.Complete";
    }
}
