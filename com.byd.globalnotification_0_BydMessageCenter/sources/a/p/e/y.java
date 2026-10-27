package a.p.e;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class y extends d0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public w f1267c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public w f1268d;

    @Override // a.p.e.d0
    public int[] a(RecyclerView.m mVar, View view) {
        int[] iArr = new int[2];
        if (mVar.f()) {
            iArr[0] = c(view, e(mVar));
        } else {
            iArr[0] = 0;
        }
        if (mVar.g()) {
            iArr[1] = c(view, f(mVar));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    public final int c(View view, w wVar) {
        return ((wVar.c(view) / 2) + wVar.e(view)) - ((wVar.l() / 2) + wVar.k());
    }

    public final View d(RecyclerView.m mVar, w wVar) {
        int iZ = mVar.z();
        View view = null;
        if (iZ == 0) {
            return null;
        }
        int iL = (wVar.l() / 2) + wVar.k();
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < iZ; i2++) {
            View viewY = mVar.y(i2);
            int iAbs = Math.abs(((wVar.c(viewY) / 2) + wVar.e(viewY)) - iL);
            if (iAbs < i) {
                view = viewY;
                i = iAbs;
            }
        }
        return view;
    }

    public final w e(RecyclerView.m mVar) {
        w wVar = this.f1268d;
        if (wVar == null || wVar.f1264a != mVar) {
            this.f1268d = new u(mVar);
        }
        return this.f1268d;
    }

    public final w f(RecyclerView.m mVar) {
        w wVar = this.f1267c;
        if (wVar == null || wVar.f1264a != mVar) {
            this.f1267c = new v(mVar);
        }
        return this.f1267c;
    }
}
