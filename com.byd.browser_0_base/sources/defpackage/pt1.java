package defpackage;

import J.N;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ll;
import java.util.ArrayList;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: OverscrollGlowOverlay.java */
/* JADX INFO: loaded from: classes.dex */
public final class pt1 extends sn0 implements je2 {
    public final rt1 f;
    public final Runnable g;
    public boolean h;
    public float i;

    public pt1(g3 g3Var, ViewGroup viewGroup, ll.c cVar) {
        super(viewGroup);
        this.f = new rt1(g3Var, viewGroup);
        this.g = cVar;
    }

    @Override // defpackage.je2
    public final boolean G(long j) {
        return true;
    }

    @Override // defpackage.je2
    public final ke2 U(RectF rectF, ResourceManager resourceManager) {
        float f = this.i;
        rt1 rt1Var = this.f;
        float f2 = -(f - rt1Var.h);
        rt1Var.h = f;
        if (!(f2 == 0.0f ? true : N.MHbgPcOD(rt1Var.g, rt1Var, resourceManager, f, f2))) {
            this.h = false;
            this.i = 0.0f;
        }
        return rt1Var;
    }

    @Override // defpackage.je2
    public final boolean a() {
        return false;
    }

    @Override // defpackage.sn0
    public final void e(float f) {
        this.i = f;
        if (this.h) {
            this.g.run();
        }
    }

    @Override // defpackage.sn0
    public final void f(float f, float f2) {
        rt1 rt1Var = this.f;
        rt1Var.h = 0.0f;
        long j = rt1Var.g;
        View view = rt1Var.f;
        N.M2aBK_gn(j, rt1Var, f, f2, view.getWidth(), view.getHeight());
        this.h = true;
        this.i = 0.0f;
    }

    @Override // defpackage.sn0
    public final void h() {
        rt1 rt1Var = this.f;
        N.MHbgPcOD(rt1Var.g, rt1Var, null, 0.0f, 0.0f);
        rt1Var.h = 0.0f;
        this.i = 0.0f;
    }

    @Override // defpackage.sn0
    public final void j() {
        this.h = false;
        this.i = 0.0f;
        rt1 rt1Var = this.f;
        N.Mmy$6vNW(rt1Var.g, rt1Var);
    }

    @Override // defpackage.je2
    public final boolean o0() {
        return false;
    }

    @Override // defpackage.je2
    public final boolean q1() {
        return this.h;
    }

    @Override // defpackage.je2
    public final EventFilter x() {
        return null;
    }

    @Override // defpackage.je2
    public final void N() {
    }

    @Override // defpackage.je2
    public final void f0(ArrayList arrayList) {
    }

    @Override // defpackage.je2
    public final void M0(float f, float f2, float f3, int i) {
    }
}
