package defpackage;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: WindowIdApi18.java */
/* JADX INFO: loaded from: classes.dex */
public class xe implements ye {
    public final WindowId a;

    public xe(View view) {
        this.a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof xe) && ((xe) obj).a.equals(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
