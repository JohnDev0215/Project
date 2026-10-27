package p;

import android.app.Notification;

/* JADX INFO: loaded from: classes.dex */
public final class ew0 {
    public final int a;
    public final int b;
    public final Notification c;

    public ew0(int i, int i2, Notification notification) {
        this.a = i;
        this.c = notification;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ew0.class != obj.getClass()) {
            return false;
        }
        ew0 ew0Var = (ew0) obj;
        if (this.a == ew0Var.a && this.b == ew0Var.b) {
            return this.c.equals(ew0Var.c);
        }
        return false;
    }

    public final int hashCode() {
        return this.c.hashCode() + (((this.a * 31) + this.b) * 31);
    }

    public final String toString() {
        return "ForegroundInfo{mNotificationId=" + this.a + ", mForegroundServiceType=" + this.b + ", mNotification=" + this.c + '}';
    }
}
