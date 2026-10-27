package defpackage;

import android.graphics.Rect;

/* JADX INFO: compiled from: RectProvider.java */
/* JADX INFO: loaded from: classes3.dex */
public class z92 {
    public final Rect e;
    public a f;

    /* JADX INFO: compiled from: RectProvider.java */
    public interface a {
    }

    public z92() {
        this.e = new Rect();
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public void b() {
        this.f = null;
    }

    public z92(Rect rect) {
        Rect rect2 = new Rect();
        this.e = rect2;
        rect2.set(rect);
    }
}
