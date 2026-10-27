package defpackage;

import android.os.IBinder;

/* JADX INFO: compiled from: WindowIdApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class we implements ye {
    public final IBinder a;

    public we(IBinder iBinder) {
        this.a = iBinder;
    }

    public boolean equals(Object obj) {
        return (obj instanceof we) && ((we) obj).a.equals(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
