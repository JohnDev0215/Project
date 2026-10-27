package defpackage;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ContentCaptureDataBase.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class jy {
    public final long a;
    public final Rect b;
    public ArrayList<jy> c;

    public jy(long j, Rect rect) {
        this.a = j;
        this.b = rect;
    }

    public abstract String a();

    public String toString() {
        StringBuilder sb = new StringBuilder(" id:");
        sb.append(this.a);
        sb.append(" bounds:");
        sb.append(this.b);
        ArrayList<jy> arrayList = this.c;
        if ((arrayList == null || arrayList.isEmpty()) ? false : true) {
            sb.append(" children:");
            sb.append(this.c.size());
            Iterator<jy> it = this.c.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
            }
        }
        return sb.toString();
    }
}
