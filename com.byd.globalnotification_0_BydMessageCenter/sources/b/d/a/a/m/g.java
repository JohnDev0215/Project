package b.d.a.a.m;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
public class g<V extends View> extends CoordinatorLayout.c<V> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public h f2009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2010b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2011c;

    public g() {
        this.f2010b = 0;
        this.f2011c = 0;
    }

    public g(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2010b = 0;
        this.f2011c = 0;
    }

    public int B() {
        h hVar = this.f2009a;
        if (hVar != null) {
            return hVar.f2015d;
        }
        return 0;
    }

    public void C(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.q(v, i);
    }

    public boolean D(int i) {
        h hVar = this.f2009a;
        if (hVar == null) {
            this.f2010b = i;
            return false;
        }
        if (!hVar.f2017f || hVar.f2015d == i) {
            return false;
        }
        hVar.f2015d = i;
        hVar.a();
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
    public boolean k(CoordinatorLayout coordinatorLayout, V v, int i) {
        C(coordinatorLayout, v, i);
        if (this.f2009a == null) {
            this.f2009a = new h(v);
        }
        h hVar = this.f2009a;
        hVar.f2013b = hVar.f2012a.getTop();
        hVar.f2014c = hVar.f2012a.getLeft();
        this.f2009a.a();
        int i2 = this.f2010b;
        if (i2 != 0) {
            h hVar2 = this.f2009a;
            if (hVar2.f2017f && hVar2.f2015d != i2) {
                hVar2.f2015d = i2;
                hVar2.a();
            }
            this.f2010b = 0;
        }
        int i3 = this.f2011c;
        if (i3 == 0) {
            return true;
        }
        h hVar3 = this.f2009a;
        if (hVar3.g && hVar3.f2016e != i3) {
            hVar3.f2016e = i3;
            hVar3.a();
        }
        this.f2011c = 0;
        return true;
    }
}
