package defpackage;

import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.RectF;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.compositor.layouts.eventfilter.BlackHoleEventFilter;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: SimpleAnimationLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class um2 extends y51 {
    public kw t;
    public AnimatorSet u;
    public AnimatorSet v;
    public m61 w;
    public m61 x;
    public final g13 y;
    public final BlackHoleEventFilter z;

    public um2(Context context, h61 h61Var, j61 j61Var) {
        super(context, h61Var, j61Var);
        this.z = new BlackHoleEventFilter(context);
        this.y = new g13();
    }

    @Override // defpackage.y51
    public final void H(long j, boolean z) {
        Tab tabI;
        super.H(j, z);
        y13 y13Var = this.k;
        if (y13Var != null && this.l != null && (tabI = y13Var.i()) != null && tabI.isNativePage()) {
            this.l.a(tabI);
        }
        this.o = null;
        this.x = null;
        this.w = null;
    }

    @Override // defpackage.y51
    public final void L() {
        m61[] m61VarArr = this.o;
        if (m61VarArr == null) {
            return;
        }
        boolean z = false;
        for (int length = m61VarArr.length - 1; length >= 0; length--) {
            z = O(this.o[length]) || z;
        }
        if (z) {
            E();
        }
    }

    @Override // defpackage.y51
    public final void M(RectF rectF, TabContentManager tabContentManager, ResourceManager resourceManager, fj fjVar) {
        this.y.e(this.i, rectF, this, tabContentManager, resourceManager, fjVar, -1, 0.0f, 0);
    }

    public final void P(int i) {
        TabModel tabModelJ;
        m61[] m61VarArr = this.o;
        if ((m61VarArr != null && m61VarArr.length == 1 && m61VarArr[0].q() == i) || (tabModelJ = this.k.j(i)) == null) {
            return;
        }
        m61 m61VarC = c(i, tabModelJ.isIncognito());
        m61VarC.m(m61.r, 0.0f);
        this.o = new m61[]{m61VarC};
        K(new LinkedList(Arrays.asList(Integer.valueOf(i))));
    }

    @Override // defpackage.y51
    public final void h() {
        kw kwVar = this.t;
        if (kwVar != null) {
            kwVar.end();
        }
        AnimatorSet animatorSet = this.u;
        if (animatorSet != null) {
            animatorSet.end();
        }
        AnimatorSet animatorSet2 = this.v;
        if (animatorSet2 != null) {
            animatorSet2.end();
        }
    }

    @Override // defpackage.y51
    public final EventFilter k() {
        return this.z;
    }

    @Override // defpackage.y51
    public final int m() {
        return 8;
    }

    @Override // defpackage.y51
    public final SceneLayer n() {
        return this.y;
    }

    @Override // defpackage.y51
    public final int o() {
        return 3;
    }

    @Override // defpackage.y51
    public final boolean p() {
        return true;
    }

    @Override // defpackage.y51
    public final boolean q() {
        return true;
    }

    @Override // defpackage.y51
    public final void v(int i) {
        if (this.w != null) {
            TabModel tabModelJ = this.k.j(i);
            int i2 = 2;
            if (tabModelJ != null) {
                m61 m61VarC = c(i, tabModelJ.isIncognito());
                m61VarC.m(m61.K, 0.0f);
                this.o = new m61[]{m61VarC, this.w};
                K(new LinkedList(Arrays.asList(Integer.valueOf(i), Integer.valueOf(this.w.q()))));
            } else {
                this.o = new m61[]{this.w};
            }
            h();
            this.x = this.w;
            kw kwVarE = kw.e(j(), 0.0f, 0.7f * Math.min(this.e, this.f), 250L, new jt1(i2, this));
            this.t = kwVarE;
            kwVarE.i = pc.d;
            kwVarE.start();
            this.w = null;
            if (tabModelJ != null) {
                this.k.d(tabModelJ.isIncognito());
            }
        }
        I(i, false);
    }

    @Override // defpackage.y51
    public final void w(int i) {
        this.o = null;
        this.x = null;
        this.w = null;
        h();
        TabModel tabModelJ = this.k.j(i);
        if (tabModelJ == null) {
            this.o = null;
            this.w = null;
            return;
        }
        m61 m61VarC = c(i, tabModelJ.isIncognito());
        this.w = m61VarC;
        m61VarC.m(m61.r, 0.0f);
        this.o = new m61[]{this.w};
        K(new LinkedList(Arrays.asList(Integer.valueOf(i))));
    }

    @Override // defpackage.y51
    public final void y(int i, int i2, boolean z, boolean z2, float f, float f2) {
        char c;
        m61[] m61VarArr;
        float f3;
        float f4;
        P(i2);
        if (!z2 || (m61VarArr = this.o) == null || m61VarArr.length <= 0) {
            m61 m61VarC = c(i, z);
            m61[] m61VarArr2 = this.o;
            if (m61VarArr2 == null || m61VarArr2.length == 0) {
                c = 1;
                this.o = new m61[]{m61VarC};
            } else {
                c = 1;
                this.o = new m61[]{m61VarArr2[0], m61VarC};
            }
            Integer[] numArr = new Integer[2];
            numArr[0] = Integer.valueOf(i);
            numArr[c] = Integer.valueOf(i2);
            K(new LinkedList(Arrays.asList(numArr)));
            m61VarC.m(m61.r, 0.0f);
            m61VarC.m(m61.x, 1.0f);
            h();
            ew ewVarJ = j();
            kw kwVarI = kw.i(ewVarJ, m61VarC, m61.g, 0.0f, 1.0f, 300L);
            kw kwVarI2 = kw.i(ewVarJ, m61VarC, m61.p, 0.0f, 1.0f, 300L);
            kw kwVarI3 = kw.i(ewVarJ, m61VarC, m61.h, f, 0.0f, 300L);
            kw kwVarI4 = kw.i(ewVarJ, m61VarC, m61.i, f2, 0.0f, 300L);
            AnimatorSet animatorSet = new AnimatorSet();
            this.u = animatorSet;
            animatorSet.setInterpolator(pc.c);
            this.u.playTogether(kwVarI, kwVarI2, kwVarI3, kwVarI4);
            this.u.start();
            this.k.d(z);
            I(i, false);
            return;
        }
        m61 m61VarC2 = c(i, z);
        m61 m61Var = this.o[0];
        this.o = new m61[]{m61Var, m61VarC2};
        K(new LinkedList(Arrays.asList(Integer.valueOf(i), Integer.valueOf(i2))));
        h();
        PropertyModel.o oVar = m61.r;
        m61VarC2.m(oVar, 0.0f);
        float fMin = (Math.min(this.e, this.f) * 0.100000024f) / 2.0f;
        ew ewVarJ2 = j();
        ArrayList arrayList = new ArrayList(5);
        PropertyModel.o oVar2 = m61.g;
        arrayList.add(kw.i(ewVarJ2, m61Var, oVar2, 1.0f, 0.9f, 300L));
        PropertyModel.o oVar3 = m61.h;
        arrayList.add(kw.i(ewVarJ2, m61Var, oVar3, 0.0f, fMin, 300L));
        PropertyModel.o oVar4 = m61.i;
        arrayList.add(kw.i(ewVarJ2, m61Var, oVar4, 0.0f, fMin, 300L));
        PropertyModel.o oVar5 = m61.s;
        arrayList.add(kw.i(ewVarJ2, m61Var, oVar5, 1.1111112f, 1.0f, 300L));
        arrayList.add(kw.i(ewVarJ2, m61Var, oVar, 0.0f, 1.0f, 300L));
        AnimatorSet animatorSet2 = new AnimatorSet();
        pc pcVar = pc.c;
        animatorSet2.setInterpolator(pcVar);
        animatorSet2.playTogether(arrayList);
        if (this.j == 1) {
            f4 = fMin;
            f3 = this.f * 0.5f;
        } else {
            f3 = fMin;
            f4 = this.e * 0.5f;
        }
        ArrayList arrayList2 = new ArrayList(4);
        PropertyModel.o oVar6 = m61.p;
        arrayList2.add(kw.i(ewVarJ2, m61VarC2, oVar6, 0.0f, 1.0f, 150L));
        arrayList2.add(kw.i(ewVarJ2, m61VarC2, oVar2, 0.0f, 0.9f, 300L));
        arrayList2.add(kw.i(ewVarJ2, m61VarC2, oVar3, f, f4, 300L));
        arrayList2.add(kw.i(ewVarJ2, m61VarC2, oVar4, f2, f3, 300L));
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.setInterpolator(pc.e);
        animatorSet3.playTogether(arrayList2);
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playTogether(animatorSet3, animatorSet2);
        ArrayList arrayList3 = new ArrayList(7);
        arrayList3.add(kw.j(ewVarJ2, m61Var, oVar2, 0.9f, 1.0f, 300L, pcVar));
        arrayList3.add(kw.j(ewVarJ2, m61Var, oVar3, fMin, 0.0f, 300L, pcVar));
        arrayList3.add(kw.j(ewVarJ2, m61Var, oVar4, fMin, 0.0f, 300L, pcVar));
        arrayList3.add(kw.j(ewVarJ2, m61Var, oVar5, 1.0f, 1.1111112f, 300L, pcVar));
        arrayList3.add(kw.j(ewVarJ2, m61Var, oVar, 1.0f, 0.0f, 300L, pcVar));
        arrayList3.add(kw.i(ewVarJ2, m61VarC2, oVar6, 1.0f, 0.0f, 300L));
        if (this.j == 1) {
            arrayList3.add(kw.j(ewVarJ2, m61VarC2, oVar4, f3, this.f, 300L, pc.d));
        } else {
            arrayList3.add(kw.j(ewVarJ2, m61VarC2, oVar3, f4, this.e, 300L, pc.d));
        }
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.setStartDelay(150L);
        animatorSet5.addListener(new tm2(this, i2));
        animatorSet5.playTogether(arrayList3);
        AnimatorSet animatorSet6 = new AnimatorSet();
        this.v = animatorSet6;
        animatorSet6.playSequentially(animatorSet4, animatorSet5);
        this.v.start();
        this.k.d(z);
    }

    @Override // defpackage.y51
    public final void z(int i) {
        this.o = null;
        this.x = null;
        this.w = null;
        h();
        P(i);
    }
}
