package defpackage;

import android.view.ViewTreeObserver;
import com.google.android.material.floatingactionbutton.d;

/* JADX INFO: compiled from: FloatingActionButtonImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class tn0 implements ViewTreeObserver.OnPreDrawListener {
    public final /* synthetic */ d e;

    public tn0(d dVar) {
        this.e = dVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        d dVar = this.e;
        float rotation = dVar.v.getRotation();
        if (dVar.o == rotation) {
            return true;
        }
        dVar.o = rotation;
        dVar.p();
        return true;
    }
}
