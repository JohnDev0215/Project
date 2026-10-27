package defpackage;

import J.N;
import android.content.Context;
import android.graphics.RectF;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.layouts.scene_layer.SceneLayer;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: TabListSceneLayer.java */
/* JADX INFO: loaded from: classes.dex */
public final class g13 extends SceneLayer {
    public long f;
    public y13 g;
    public final int[] h = new int[4];
    public boolean i;

    @Override // org.chromium.chrome.browser.layouts.scene_layer.SceneLayer
    public final void d() {
        if (this.f == 0) {
            this.f = N.MwkJn6dB(this);
        }
    }

    public final void e(Context context, RectF rectF, y51 y51Var, TabContentManager tabContentManager, ResourceManager resourceManager, fj fjVar, int i, float f, int i2) {
        float f2;
        int i3;
        y13 y13Var;
        g13 g13Var = this;
        Context context2 = context;
        fj fjVar2 = fjVar;
        if (g13Var.f == 0) {
            return;
        }
        float f3 = context.getResources().getDisplayMetrics().density;
        int color = (oj2.z(context) && (y13Var = g13Var.g) != null && y13Var.r()) ? context2.getColor(R$color.default_bg_color_dark) : ii2.a(context);
        m61[] m61VarArr = y51Var.o;
        int length = m61VarArr != null ? m61VarArr.length : 0;
        boolean z = g13Var.i;
        if (!z) {
            long j = g13Var.f;
            if (j != 0 && !z) {
                N.MxcqGWbf(j, g13Var, tabContentManager, resourceManager);
                g13Var.i = true;
            }
        }
        N.MQUD003X(g13Var.f, g13Var);
        N.Mf2p86oA(g13Var.f, this, color, rectF.left, rectF.top, rectF.width(), rectF.height());
        if (i != -1) {
            N.MdnzXRBb(g13Var.f, this, i, f, i2);
        }
        float f4 = z7.h0(color) ? 1.0f : 0.8f;
        int i4 = 0;
        while (i4 < length) {
            m61 m61Var = m61VarArr[i4];
            float fG = m61Var.g(m61.K);
            int i5 = R$drawable.modern_location_bar;
            PropertyModel.n nVar = m61.f;
            int iA = yp.a(context2, m61Var.k(nVar));
            m61Var.q();
            int[] iArr = g13Var.h;
            if (fjVar2 != null) {
                float f5 = fjVar2.m + fjVar2.q;
                i3 = fjVar2.s;
                f2 = f5;
            } else {
                f2 = 0.0f;
                i3 = 0;
            }
            long j2 = g13Var.f;
            int iQ = m61Var.q();
            int i6 = R$id.control_container;
            int i7 = R$drawable.tabswitcher_border_frame_shadow;
            int i8 = R$drawable.tabswitcher_border_frame_decoration;
            int i9 = R$drawable.tabswitcher_border_frame;
            int i10 = R$drawable.tabswitcher_border_frame_inner_shadow;
            boolean zK = m61Var.k(m61.B);
            int iH = m61Var.h(m61.M);
            boolean zK2 = m61Var.k(nVar);
            float fG2 = m61Var.g(m61.j) * f3;
            float fG3 = m61Var.g(m61.k) * f3;
            float fS = m61Var.s();
            PropertyModel.o oVar = m61.g;
            float fG4 = m61Var.g(oVar) * fS * f3;
            float fG5 = m61Var.g(oVar) * m61Var.r() * f3;
            float fS2 = m61Var.s() * f3;
            float fR = m61Var.r() * f3;
            float fG6 = m61Var.g(m61.l) * f3;
            float fG7 = m61Var.g(m61.m) * f3;
            float fMin = Math.min(m61Var.g(m61.n), m61Var.s() * m61Var.g(oVar)) * f3;
            float fMin2 = Math.min(m61Var.g(m61.o), m61Var.g(oVar) * m61Var.r()) * f3;
            PropertyModel.o oVar2 = m61.p;
            float fG8 = m61Var.g(oVar2);
            PropertyModel.o oVar3 = m61.r;
            float fMin3 = Math.min(m61Var.g(oVar3), m61Var.g(oVar2)) * fG;
            float fG9 = m61Var.g(oVar3);
            PropertyModel.o oVar4 = m61.E;
            N.Mp1Kxnqn(j2, this, iQ, iArr, false, i6, i7, i8, i9, i10, zK, iH, zK2, fG2, fG3, fG4, fG5, fS2, fR, fG6, fG7, fMin, fMin2, fG8, fMin3, Math.min((1.0f - m61Var.g(oVar4)) * fG9, m61Var.g(oVar2)) * fG, fG, f4 * fG, m61Var.g(m61.x), m61Var.g(m61.s), m61Var.g(m61.q), m61Var.g(m61.y), m61Var.k(m61.C), iA, m61Var.h(m61.N), m61Var.k(m61.D), i5, m61Var.h(m61.O), m61Var.g(oVar4), f2, i3, m61Var.g(m61.H), m61Var.k(m61.F));
            i4++;
            g13Var = this;
            context2 = context;
            fjVar2 = fjVar;
            length = length;
            m61VarArr = m61VarArr;
            f3 = f3;
        }
        g13 g13Var2 = g13Var;
        N.Mn9kYrkw(g13Var2.f, g13Var2);
    }
}
