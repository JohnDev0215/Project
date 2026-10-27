package defpackage;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.FloatProperty;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import defpackage.fv2;
import defpackage.j13;
import defpackage.lw;
import java.util.ArrayList;
import org.chromium.chrome.R$attr;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: compiled from: StripLayoutHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class vu2 implements fv2.j {
    public static final float a0;
    public boolean A;
    public float B;
    public float C;
    public float D;
    public long E;
    public long F;
    public long G;
    public float H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public Long f100J;
    public Long K;
    public fv2 L;
    public ya3 M;
    public float N;
    public float O;
    public float P;
    public float Q;
    public final float R;
    public final boolean S;
    public boolean T;
    public boolean U;
    public boolean V;
    public Context W;
    public boolean X;
    public boolean Y;
    public final boolean Z;
    public final n61 a;
    public final j61 b;
    public TabModel c;
    public fz2 d;
    public vd1 e;
    public final rm f;
    public final wf2 g;
    public fv2[] h;
    public fv2[] i;
    public fv2[] j;
    public final e k;
    public final f l;
    public Animator m;
    public final ya3 n;
    public final lw o;
    public final float p;
    public final float q;
    public final float r;
    public final float s;
    public final float t;
    public final ListPopupWindow u;
    public StackScroller v;
    public float w;
    public float x;
    public float y;
    public int z;

    /* JADX INFO: compiled from: StripLayoutHelper.java */
    public class a extends FloatProperty<vu2> {
        public a() {
            super("scrollOffset");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((vu2) obj).w);
        }

        @Override // android.util.FloatProperty
        public final void setValue(vu2 vu2Var, float f) {
            vu2Var.w = f;
        }
    }

    /* JADX INFO: compiled from: StripLayoutHelper.java */
    public class b implements lw.c {
        public b() {
        }

        @Override // lw.c
        public final void a(long j) {
            vu2 vu2Var = vu2.this;
            TabModel tabModel = vu2Var.c;
            if (tabModel == null) {
                return;
            }
            if (!tabModel.isIncognito()) {
                vu2Var.c.c();
            }
            vu2Var.d.f(2);
        }
    }

    /* JADX INFO: compiled from: StripLayoutHelper.java */
    public class c implements AdapterView.OnItemClickListener {
        public c() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            vu2 vu2Var = vu2.this;
            vu2Var.u.dismiss();
            if (i == 0) {
                vu2Var.c.e(false);
                n3.X0("MobileToolbarCloseAllTabs");
            }
        }
    }

    /* JADX INFO: compiled from: StripLayoutHelper.java */
    public class d extends AnimatorListenerAdapter {
        public final /* synthetic */ fv2 e;
        public final /* synthetic */ boolean f;

        public d(fv2 fv2Var, boolean z) {
            this.e = fv2Var;
            this.f = z;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            this.e.j = this.f;
        }
    }

    /* JADX INFO: compiled from: StripLayoutHelper.java */
    @SuppressLint({"HandlerLeak"})
    public class e extends Handler {
        public e() {
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            vu2 vu2Var = vu2.this;
            if (i == 1) {
                vu2Var.d(true, false);
                ((h61) vu2Var.a).T(null);
            } else {
                if (i != 2) {
                    return;
                }
                ((h61) vu2Var.a).T(null);
            }
        }
    }

    /* JADX INFO: compiled from: StripLayoutHelper.java */
    public class f implements j13.c {
        public f() {
        }
    }

    static {
        new a();
        a0 = (float) Math.tan(0.7853981633974483d);
    }

    public vu2(Context context, n61 n61Var, j61 j61Var, boolean z, lw lwVar) {
        rm rmVar = new rm();
        this.f = rmVar;
        wf2 wf2Var = new wf2();
        this.g = wf2Var;
        boolean z2 = false;
        this.h = new fv2[0];
        this.i = new fv2[0];
        this.j = new fv2[0];
        this.k = new e();
        this.l = new f();
        this.z = 0;
        sk skVar = bq.f0;
        this.p = skVar.a() ? 28.0f : 24.0f;
        if (!skVar.a()) {
            this.q = 24.0f;
        } else if (oj2.F()) {
            this.q = 36.0f;
        } else {
            this.q = 38.0f;
        }
        this.o = lwVar;
        boolean zA = bq.e0.a();
        this.Z = zA;
        if (skVar.a()) {
            this.R = (48.0f - this.q) / 2.0f;
        } else {
            this.R = 24.0f;
        }
        this.Q = LocalizationUtils.isLayoutRtl() ? 0.0f : this.R + this.q;
        this.P = LocalizationUtils.isLayoutRtl() ? this.R + this.q : 0.0f;
        this.r = (float) oj2.k.c();
        this.s = 265.0f;
        this.t = 50.0f;
        this.a = n61Var;
        this.b = j61Var;
        b bVar = new b();
        if (skVar.a()) {
            if (oj2.F()) {
                this.n = new ya3(context, 36.0f, 36.0f, bVar);
            } else {
                this.n = new ya3(context, 38.0f, 38.0f, bVar);
            }
            oj2.n(R$attr.colorPrimaryContainer, context, "TabUiThemeProvider");
            gv.g(ii2.c(context), 51);
            sk skVar2 = bq.d;
            int i = skVar2.a() ? R$color.default_bg_color_dark_elev_2_gm3_baseline : R$color.default_bg_color_dark_elev_2_baseline;
            int i2 = skVar2.a() ? R$color.default_bg_color_dark_elev_3_gm3_baseline : R$color.default_bg_color_dark_elev_3_baseline;
            if (oj2.F()) {
                context.getResources().getColor(i);
            } else {
                context.getResources().getColor(i2);
            }
            context.getResources().getColor(skVar2.a() ? R$color.default_bg_color_dark_elev_5_gm3_baseline : R$color.default_bg_color_dark_elev_5_baseline);
            if (z7.I(context)) {
                if (oj2.F()) {
                    yp.c(context, R$dimen.default_elevation_1);
                } else if (skVar2.a()) {
                    yp.c(context, R$dimen.default_elevation_2);
                } else {
                    gv.g(ii2.c(context), 38);
                }
                yp.c(context, R$dimen.default_elevation_5);
            }
            this.n.getClass();
            ya3 ya3Var = this.n;
            int i3 = R$color.default_icon_color_tint_list;
            int i4 = R$color.modern_white;
            ya3Var.g(i3, i3, i4, i4);
            this.n.f(0.0f);
        } else {
            ya3 ya3Var2 = new ya3(context, 24.0f, 24.0f, bVar);
            this.n = ya3Var2;
            ya3Var2.g(R$color.new_tab_button_tint_list, R$color.new_tab_button_pressed_tint_list, R$color.modern_white, R$color.default_icon_color_blue_light);
            ya3Var2.f(10.0f);
        }
        ya3 ya3Var3 = this.n;
        ya3Var3.h = z;
        ya3Var3.e = 12.0f;
        Resources resources = context.getResources();
        ya3 ya3Var4 = this.n;
        String string = resources.getString(R$string.accessibility_toolbar_btn_new_tab);
        String string2 = resources.getString(R$string.accessibility_toolbar_btn_new_incognito_tab);
        ya3Var4.j = string;
        ya3Var4.k = string2;
        this.W = context;
        this.S = z;
        ListPopupWindow listPopupWindow = new ListPopupWindow(this.W);
        this.u = listPopupWindow;
        Context context2 = this.W;
        String[] strArr = new String[1];
        strArr[0] = context2.getString(!z ? R$string.menu_close_all_tabs : R$string.menu_close_all_incognito_tabs);
        listPopupWindow.setAdapter(new ArrayAdapter(context2, R.layout.simple_list_item_1, strArr));
        listPopupWindow.setOnItemClickListener(new c());
        listPopupWindow.setWidth(this.W.getResources().getDimensionPixelSize(R$dimen.menu_width));
        listPopupWindow.setModal(true);
        if (DeviceFormFactor.a(context) && !zA) {
            z2 = true;
        }
        this.T = z2;
        this.e = z2 ? rmVar : wf2Var;
        this.U = true;
    }

    public final void A(long j, int i, int i2, boolean z, boolean z2, boolean z3) {
        boolean z4;
        n61 n61Var;
        if (f(i) != null) {
            return;
        }
        c(false);
        fv2 fv2VarF = f(i);
        n61 n61Var2 = this.a;
        if (fv2VarF != null && !z3) {
            g();
            kw kwVarG = kw.g(((h61) n61Var2).G, fv2VarF, fv2.D, fv2VarF.v, 0.0f, 150L);
            this.m = kwVarG;
            kwVarG.start();
        }
        fv2 fv2VarF2 = f(i2);
        if (!z) {
            fv2VarF2 = fv2VarF;
        }
        boolean z5 = !z;
        boolean z6 = this.T;
        boolean z7 = true;
        boolean z8 = this.Z;
        if (z6) {
            z4 = false;
        } else {
            int iIndex = this.c.index();
            if (z8 && !z && iIndex >= 0) {
                fv2[] fv2VarArr = this.h;
                if (iIndex < fv2VarArr.length) {
                    fv2VarF = fv2VarArr[iIndex];
                }
            }
            fv2VarF2 = fv2VarF;
            z4 = true;
            z5 = true;
        }
        boolean z9 = (z8 && z2) || z3;
        if (fv2VarF2 == null || z9) {
            n61Var = n61Var2;
        } else {
            float fB = b(fv2VarF2, z4, z5, z);
            if (!this.T) {
                if (this.n.g && !z8) {
                    z7 = false;
                }
                t(fB, z7, j);
            } else if (fB != 0.0f) {
                n61Var = n61Var2;
                this.v.b(Math.round(this.w), (int) fB, i(), j);
            }
            n61Var = n61Var2;
        }
        if (z3) {
            a(j, false);
        }
        ((h61) n61Var).T(null);
    }

    public final void B(int i) {
        fv2 fv2VarF = f(i);
        if (fv2VarF != null) {
            j13 j13Var = fv2VarF.c;
            if (j13Var.d) {
                Handler handler = j13Var.a;
                j13.b bVar = j13Var.f;
                handler.removeCallbacks(bVar);
                handler.postDelayed(bVar, 100L);
            }
        }
    }

    public final void C(long j, int i, int i2, boolean z) {
        fv2 fv2VarF = f(i);
        if (fv2VarF == null) {
            A(j, i, i2, true, false, false);
            return;
        }
        K();
        D();
        if (!z && !this.A) {
            a(j, true);
        }
        ((h61) this.a).T(null);
        Tab tabL = l(i);
        if (tabL != null) {
            r(fv2VarF, tabL.getTitle(), tabL.isHidden());
        }
        fv2 fv2VarF2 = f(i2);
        Tab tabL2 = l(i2);
        if (tabL2 != null) {
            r(fv2VarF2, tabL2.getTitle(), tabL2.isHidden());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void D() {
        /*
            r9 = this;
            boolean r0 = r9.Z
            if (r0 != 0) goto L5
            return
        L5:
            org.chromium.chrome.browser.tabmodel.TabModel r0 = r9.c
            int r1 = r0.index()
            org.chromium.chrome.browser.tab.Tab r0 = r0.getTabAt(r1)
            org.chromium.chrome.browser.tabmodel.TabModel r1 = r9.c
            int r1 = r1.getCount()
            if (r0 != 0) goto L18
            return
        L18:
            r2 = 0
            r3 = r2
        L1a:
            if (r3 >= r1) goto L67
            fv2[] r4 = r9.h
            r4 = r4[r3]
            float r5 = r9.r
            r6 = 1125908480(0x431c0000, float:156.0)
            int r7 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            r8 = 1
            if (r7 != 0) goto L3a
            boolean r4 = r9.w(r4, r3)
            fv2[] r5 = r9.h
            r5 = r5[r3]
            boolean r6 = r9.U
            r6 = r6 ^ r8
            r5.i = r4
            r5.e(r6)
            goto L64
        L3a:
            r7 = 1121452032(0x42d80000, float:108.0)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 != 0) goto L64
            float r5 = r4.u
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 >= 0) goto L57
            int r5 = r0.getId()
            int r6 = r4.a
            if (r6 != r5) goto L55
            boolean r4 = r9.w(r4, r3)
            if (r4 == 0) goto L55
            goto L57
        L55:
            r4 = r2
            goto L58
        L57:
            r4 = r8
        L58:
            fv2[] r5 = r9.h
            r5 = r5[r3]
            boolean r6 = r9.U
            r6 = r6 ^ r8
            r5.i = r4
            r5.e(r6)
        L64:
            int r3 = r3 + 1
            goto L1a
        L67:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vu2.D():void");
    }

    public final void E(fv2 fv2Var, boolean z, ArrayList<Animator> arrayList) {
        float f2 = z ? 4.0f : 0.0f;
        float f3 = z ? 0.0f : 4.0f;
        if (arrayList == null) {
            fv2Var.k = f3;
            fv2Var.j = z;
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        h61 h61Var = (h61) this.a;
        ew ewVar = h61Var.G;
        fv2.e eVar = fv2.G;
        kw kwVarH = kw.h(ewVar, fv2Var, eVar, f2, -12.0f, 75L, h21.b);
        kw kwVarH2 = kw.h(h61Var.G, fv2Var, eVar, -12.0f, f3, 75L, h21.c);
        kwVarH.addListener(new d(fv2Var, z));
        arrayList2.add(kwVarH);
        arrayList2.add(kwVarH2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(arrayList2);
        arrayList.add(animatorSet);
    }

    public final kw F(boolean z) {
        float f2;
        sk skVar = bq.f0;
        boolean zA = skVar.a();
        float f3 = this.q;
        ya3 ya3Var = this.n;
        if (!zA) {
            if (!this.A) {
                fv2[] fv2VarArr = this.h;
                if (fv2VarArr.length != 0) {
                    vd1 vd1Var = this.e;
                    float f4 = this.p;
                    float f5 = this.P;
                    float f6 = this.Q;
                    float f7 = this.N;
                    boolean zIsLayoutRtl = LocalizationUtils.isLayoutRtl();
                    if (skVar.a()) {
                        f2 = (48.0f - f3) / 2.0f;
                        if (!zIsLayoutRtl) {
                            f2 = -f2;
                        }
                    } else {
                        f2 = zIsLayoutRtl ? 12.0f : -12.0f;
                    }
                    float fC = vd1Var.c(fv2VarArr, f4, f5, f6, f7, f3, Math.abs(f2), this.y, z);
                    boolean zIsLayoutRtl2 = LocalizationUtils.isLayoutRtl();
                    if ((zIsLayoutRtl2 && f3 + fC < 0.0f) || (!zIsLayoutRtl2 && fC > this.N)) {
                        ya3Var.g = false;
                        return null;
                    }
                    ya3Var.g = true;
                    if (z) {
                        return kw.g(((h61) this.a).G, ya3Var, lw.m, ya3Var.a.left, fC, 250L);
                    }
                    ya3Var.e(fC);
                }
            }
            return null;
        }
        if (LocalizationUtils.isLayoutRtl()) {
            ya3Var.e(this.P - f3);
        } else {
            ya3Var.e(this.N - this.Q);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:99:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void G(float r19) {
        /*
            Method dump skipped, instruction units count: 444
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vu2.G(float):void");
    }

    public final void H() {
        float length;
        float f2 = (this.N - this.P) - this.Q;
        boolean z = this.T;
        int i = 0;
        float f3 = this.p;
        if (z) {
            length = 0.0f;
            while (true) {
                fv2[] fv2VarArr = this.h;
                if (i >= fv2VarArr.length) {
                    break;
                }
                fv2 fv2Var = fv2VarArr[i];
                length += fv2Var.i() * (fv2Var.u - f3);
                i++;
            }
        } else {
            length = this.h.length * (this.y - f3);
            if (this.A || this.Y) {
                length += 0.0f;
                while (true) {
                    fv2[] fv2VarArr2 = this.h;
                    if (i >= fv2VarArr2.length) {
                        break;
                    }
                    length += fv2VarArr2[i].r;
                    i++;
                }
            }
        }
        float fMin = Math.min(0.0f, f2 - (length + f3));
        this.x = fMin;
        if (fMin > -0.001f) {
            this.x = 0.0f;
        }
        I(this.w);
    }

    public final void I(float f2) {
        float f3 = this.w;
        this.w = a8.o(f2, this.x - this.D, 0.0f);
        if (this.A && this.v.a()) {
            float f4 = f3 - this.w;
            if (LocalizationUtils.isLayoutRtl()) {
                f4 = -f4;
            }
            G(f4);
        }
    }

    public final void J() {
        int iIndex;
        TabModel tabModel = this.c;
        if (tabModel == null) {
            return;
        }
        if (this.h == null || tabModel.getCount() != this.h.length) {
            c(false);
        }
        H();
        e();
        this.e.F(this.c.index(), this.h, this.p, this.P, this.Q, this.N, this.A, this.X, this.y);
        this.e.C(this.c.index(), this.h, this.N);
        int i = 0;
        int i2 = 0;
        while (true) {
            fv2[] fv2VarArr = this.i;
            if (i >= fv2VarArr.length) {
                break;
            }
            if (fv2VarArr[i].g) {
                i2++;
            }
            i++;
        }
        if (this.j.length != i2) {
            this.j = new fv2[i2];
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            fv2[] fv2VarArr2 = this.i;
            if (i3 >= fv2VarArr2.length) {
                break;
            }
            fv2 fv2Var = fv2VarArr2[i3];
            if (fv2Var.g) {
                this.j[i4] = fv2Var;
                i4++;
            }
            i3++;
        }
        if (!this.X) {
            F(false);
        }
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.b;
        CompositorViewHolder.d dVar = compositorViewHolder.w;
        if (dVar != null) {
            dVar.y(dVar.k, 65536);
            compositorViewHolder.w.p(-1, 1);
        }
        D();
        if (!bq.f0.a() || (iIndex = this.c.index()) < 0) {
            return;
        }
        fv2[] fv2VarArr3 = this.h;
        if (iIndex >= fv2VarArr3.length) {
            return;
        }
        int i5 = fv2VarArr3[iIndex].a;
        fv2VarArr3[0].getClass();
        fv2 fv2Var2 = this.h[0];
        if (fv2Var2.h() != 0.55f) {
            fv2Var2.l = fv2Var2.a == i5 ? 1.0f : 0.0f;
        }
        if (this.A && this.h[0].h() == 0.0f) {
            float f2 = this.h[0].r;
        }
        this.h[0].getClass();
        int i6 = 1;
        while (true) {
            fv2[] fv2VarArr4 = this.h;
            if (i6 >= fv2VarArr4.length) {
                return;
            }
            fv2 fv2Var3 = fv2VarArr4[i6 - 1];
            fv2 fv2Var4 = fv2VarArr4[i6];
            if (fv2Var4.h() != 0.55f) {
                fv2Var4.l = fv2Var4.a == i5 ? 1.0f : 0.0f;
            }
            if ((!this.A || fv2Var4.h() != 0.0f || (fv2Var3.r <= 0.0f && fv2Var3.h() != 0.0f)) && !this.A) {
                int i7 = fv2Var3.a;
            }
            if ((!this.A || fv2Var4.h() != 0.0f || (fv2Var4.r <= 0.0f && i6 != this.h.length - 1)) && !this.A) {
                int length = this.h.length;
            }
            i6++;
        }
    }

    public final void K() {
        fv2[] fv2VarArr = this.h;
        if (fv2VarArr.length != this.i.length) {
            this.i = new fv2[fv2VarArr.length];
        }
        vd1 vd1Var = this.e;
        int iIndex = this.c.index();
        fv2[] fv2VarArr2 = this.h;
        fv2[] fv2VarArr3 = this.i;
        vd1Var.getClass();
        int i = 0;
        int iQ = a8.q(iIndex, 0, fv2VarArr2.length);
        int i2 = 0;
        while (i < iQ) {
            fv2VarArr3[i2] = fv2VarArr2[i];
            i++;
            i2++;
        }
        int length = fv2VarArr2.length - 1;
        while (length >= iQ) {
            fv2VarArr3[i2] = fv2VarArr2[length];
            length--;
            i2++;
        }
    }

    public final void a(long j, boolean z) {
        fv2 fv2VarF;
        if (this.T) {
            return;
        }
        TabModel tabModel = this.c;
        Tab tabAt = tabModel.getTabAt(tabModel.index());
        if (tabAt == null || (fv2VarF = f(tabAt.getId())) == null || m(fv2VarF)) {
            return;
        }
        t(b(fv2VarF, true, true, true), z, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float b(defpackage.fv2 r8, boolean r9, boolean r10, boolean r11) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L4
            return r0
        L4:
            org.chromium.chrome.browser.tabmodel.TabModel r1 = r7.c
            int r1 = r1.index()
            org.chromium.chrome.browser.tabmodel.TabModel r2 = r7.c
            int r8 = r8.a
            int r8 = defpackage.z7.G(r2, r8)
            if (r1 != r8) goto L17
            if (r9 != 0) goto L17
            return r0
        L17:
            float r9 = r7.j()
            float r2 = r7.y
            float r3 = r7.p
            float r2 = r2 - r3
            int r4 = -r8
            float r4 = (float) r4
            float r4 = r4 * r2
            int r5 = r8 + 1
            float r5 = (float) r5
            float r5 = r5 * r2
            float r9 = r9 - r5
            if (r1 < 0) goto L32
            fv2[] r5 = r7.h
            int r6 = r5.length
            if (r1 >= r6) goto L32
            r5 = r5[r1]
            goto L33
        L32:
            r5 = 0
        L33:
            boolean r6 = r7.Z
            if (r6 == 0) goto L42
            if (r5 == 0) goto L42
            boolean r5 = r7.m(r5)
            if (r5 == 0) goto L42
            if (r11 != 0) goto L42
            return r0
        L42:
            if (r8 >= r1) goto L46
            float r9 = r9 - r2
            goto L49
        L46:
            if (r8 <= r1) goto L49
            float r4 = r4 + r2
        L49:
            boolean r8 = r7.T
            if (r8 == 0) goto L5e
            float r7 = r7.w
            int r8 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r8 >= 0) goto L57
            if (r10 == 0) goto L57
            float r4 = r4 - r7
            return r4
        L57:
            int r8 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r8 <= 0) goto L5d
            float r9 = r9 - r7
            return r9
        L5d:
            return r0
        L5e:
            if (r6 == 0) goto L73
            float r7 = r7.w
            float r4 = r4 - r7
            float r9 = r9 - r7
            float r9 = r9 - r3
            float r7 = java.lang.Math.abs(r4)
            float r8 = java.lang.Math.abs(r9)
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 >= 0) goto L72
            return r4
        L72:
            return r9
        L73:
            float r7 = r7.w
            float r4 = r4 - r7
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vu2.b(fv2, boolean, boolean, boolean):float");
    }

    public final void c(boolean z) {
        int count = this.c.getCount();
        fv2[] fv2VarArr = new fv2[count];
        for (int i = 0; i < count; i++) {
            Tab tabAt = this.c.getTabAt(i);
            int id = tabAt.getId();
            fv2 fv2VarF = f(id);
            if (fv2VarF == null) {
                fv2 fv2Var = new fv2(this.W, id, this, this.l, this.b, this.a, this.S);
                fv2Var.v = this.O;
                fv2Var.j();
                fv2Var.w.bottom = fv2Var.t + fv2Var.v;
                o(fv2Var);
                if (bq.f0.a()) {
                    TabModel tabModel = this.c;
                    Tab tabAt2 = tabModel.getTabAt(tabModel.index());
                    if (tabAt2 != null && id == tabAt2.getId()) {
                        fv2Var.l = 1.0f;
                    }
                }
                fv2VarF = fv2Var;
            }
            fv2VarArr[i] = fv2VarF;
            r(fv2VarF, tabAt.getTitle(), tabAt.isHidden());
        }
        int length = this.h.length;
        this.h = fv2VarArr;
        if (count != length) {
            if (z) {
                q(true);
            } else {
                d(true, false);
            }
        }
        K();
    }

    public final ArrayList d(boolean z, boolean z2) {
        this.k.removeMessages(1);
        this.y = a8.o(((this.p * (r0 - 1)) + ((this.N - this.P) - this.Q)) / Math.max(this.h.length, 1), this.r, this.s);
        g();
        ArrayList arrayList = z ? new ArrayList() : null;
        int i = 0;
        while (true) {
            fv2[] fv2VarArr = this.h;
            if (i >= fv2VarArr.length) {
                break;
            }
            fv2 fv2Var = fv2VarArr[i];
            if (!fv2Var.h) {
                if (arrayList != null) {
                    arrayList.add(kw.g(((h61) this.a).G, fv2Var, fv2.E, fv2Var.u, this.y, 250L));
                } else {
                    fv2Var.u = this.y;
                    fv2Var.j();
                    fv2Var.w.right = fv2Var.s + fv2Var.u;
                }
            }
            i++;
        }
        if (arrayList != null) {
            if (z2) {
                return arrayList;
            }
            x(arrayList, null);
        }
        return null;
    }

    public final void e() {
        float f2 = !LocalizationUtils.isLayoutRtl() ? this.w + this.P + 0.0f : (((this.N - this.y) - this.w) - this.Q) - 0.0f;
        int i = 0;
        while (true) {
            fv2[] fv2VarArr = this.h;
            if (i >= fv2VarArr.length) {
                return;
            }
            fv2 fv2Var = fv2VarArr[i];
            fv2Var.o = f2;
            float fI = fv2Var.i() * (this.y - this.p);
            if (this.A || this.Y) {
                fI += fv2Var.r;
            }
            if (LocalizationUtils.isLayoutRtl()) {
                fI = -fI;
            }
            f2 += fI;
            i++;
        }
    }

    public final fv2 f(int i) {
        if (this.h == null) {
            return null;
        }
        int i2 = 0;
        while (true) {
            fv2[] fv2VarArr = this.h;
            if (i2 >= fv2VarArr.length) {
                return null;
            }
            fv2 fv2Var = fv2VarArr[i2];
            if (fv2Var.a == i) {
                return fv2Var;
            }
            i2++;
        }
    }

    public final void g() {
        Animator animator = this.m;
        if (animator == null) {
            return;
        }
        animator.end();
        this.m = null;
        ArrayList<fv2> arrayList = new ArrayList();
        int i = 0;
        while (true) {
            fv2[] fv2VarArr = this.h;
            if (i >= fv2VarArr.length) {
                break;
            }
            fv2 fv2Var = fv2VarArr[i];
            if (fv2Var.h) {
                arrayList.add(fv2Var);
            }
            i++;
        }
        for (fv2 fv2Var2 : arrayList) {
            TabModel tabModel = this.c;
            Tab tabF = z7.F(tabModel, fv2Var2.a);
            if (tabF != null) {
                tabModel.m(tabF, true, true);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        ((h61) this.a).T(null);
    }

    public final float h(boolean z) {
        if (z) {
            return 96.0f;
        }
        return this.o.g ? 120.0f : 72.0f;
    }

    public final int i() {
        if (!this.Z) {
            return 250;
        }
        float fAbs = Math.abs(this.w);
        if (fAbs <= 960.0f) {
            return 250;
        }
        return fAbs <= 1920.0f ? 350 : 450;
    }

    public final float j() {
        if (this.Z) {
            return (this.N - h(false)) - (LocalizationUtils.isLayoutRtl() ? this.Q : this.P);
        }
        return (this.N - this.P) - this.Q;
    }

    public final fv2 k(float f2) {
        for (int length = this.i.length - 1; length >= 0; length--) {
            fv2 fv2Var = this.i[length];
            if (fv2Var.g) {
                float f3 = fv2Var.s;
                if (f3 <= f2 && f2 <= f3 + fv2Var.u) {
                    return fv2Var;
                }
            }
        }
        return null;
    }

    public final Tab l(int i) {
        return z7.F(this.c, i);
    }

    public final boolean m(fv2 fv2Var) {
        if (!this.Z) {
            if (fv2Var.g) {
                float f2 = fv2Var.s;
                if (f2 >= 0.0f && f2 + fv2Var.u <= this.N) {
                    return true;
                }
            }
            return false;
        }
        if (LocalizationUtils.isLayoutRtl()) {
            return fv2Var.g && fv2Var.s >= h(false) && fv2Var.s + fv2Var.u <= this.N;
        }
        if (fv2Var.g) {
            float f3 = fv2Var.s;
            if (f3 >= 0.0f && f3 + fv2Var.u <= j()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n(float r8, float r9, boolean r10, long r11) {
        /*
            r7 = this;
            float r0 = r7.N
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 != 0) goto Ld
            float r0 = r7.O
            int r0 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r0 != 0) goto Ld
            return
        Ld:
            r0 = 1
            boolean r1 = r7.Z
            r2 = 0
            if (r1 == 0) goto L38
            if (r10 == 0) goto L38
            org.chromium.chrome.browser.tabmodel.TabModel r10 = r7.c
            if (r10 == 0) goto L38
            int r10 = r10.index()
            if (r10 < 0) goto L38
            org.chromium.chrome.browser.tabmodel.TabModel r10 = r7.c
            int r10 = r10.index()
            fv2[] r3 = r7.h
            int r4 = r3.length
            if (r10 >= r4) goto L38
            org.chromium.chrome.browser.tabmodel.TabModel r10 = r7.c
            int r10 = r10.index()
            r10 = r3[r10]
            boolean r10 = r10.g
            if (r10 == 0) goto L38
            r10 = r0
            goto L39
        L38:
            r10 = r2
        L39:
            float r3 = r7.N
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 == 0) goto L41
            r3 = r0
            goto L42
        L41:
            r3 = r2
        L42:
            r7.N = r8
            r7.O = r9
            r9 = r2
        L47:
            fv2[] r4 = r7.h
            int r5 = r4.length
            if (r9 >= r5) goto L61
            r4 = r4[r9]
            float r5 = r7.O
            r4.v = r5
            r4.j()
            android.graphics.RectF r5 = r4.w
            float r6 = r4.t
            float r4 = r4.v
            float r6 = r6 + r4
            r5.bottom = r6
            int r9 = r9 + 1
            goto L47
        L61:
            if (r3 == 0) goto L72
            r7.d(r2, r2)
            r9 = 1142292480(0x44160000, float:600.0)
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 < 0) goto L6f
            if (r1 != 0) goto L6f
            r2 = r0
        L6f:
            r7.u(r2)
        L72:
            fv2[] r8 = r7.h
            int r8 = r8.length
            if (r8 <= 0) goto L7f
            n61 r8 = r7.a
            h61 r8 = (defpackage.h61) r8
            r9 = 0
            r8.T(r9)
        L7f:
            android.widget.ListPopupWindow r8 = r7.u
            r8.dismiss()
            if (r10 == 0) goto L89
            r7.a(r11, r0)
        L89:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vu2.n(float, float, boolean, long):void");
    }

    public final void o(fv2 fv2Var) {
        boolean z = !this.Z || this.y >= 156.0f;
        this.e.getClass();
        fv2Var.i = z;
        fv2Var.e(false);
    }

    public final void p(int i, int i2, int i3, boolean z) {
        int i4;
        int i5 = i2;
        fv2 fv2VarF = f(i);
        if (fv2VarF == null || i5 == i3) {
            return;
        }
        if (this.h != null) {
            i4 = 0;
            while (true) {
                fv2[] fv2VarArr = this.h;
                if (i4 >= fv2VarArr.length) {
                    break;
                } else if (fv2VarArr[i4].a == i) {
                    break;
                } else {
                    i4++;
                }
            }
        } else {
            i4 = -1;
        }
        if (i4 == i3) {
            return;
        }
        if (this.A && i4 != i5 && fv2VarF == this.L) {
            return;
        }
        if (z) {
            boolean z2 = i5 <= i3;
            float f2 = this.y - this.p;
            int i6 = z2 ? 1 : -1;
            float f3 = i6 * f2;
            if (LocalizationUtils.isLayoutRtl()) {
                f3 = -f3;
            }
            g();
            ArrayList arrayList = new ArrayList();
            while (true) {
                i5 += i6;
                if (z2 != (i5 < i3)) {
                    break;
                }
                fv2 fv2Var = this.h[i5];
                arrayList.add(kw.g(((h61) this.a).G, fv2Var, fv2.C, f3, 0.0f, 125L));
                fv2Var.p = f3;
            }
            x(arrayList, null);
        }
        fv2[] fv2VarArr2 = this.h;
        if (i4 <= i3) {
            if (i4 == i3 || i4 + 1 == i3) {
                return;
            }
            fv2 fv2Var2 = fv2VarArr2[i4];
            while (true) {
                int i7 = i3 - 1;
                if (i4 >= i7) {
                    fv2VarArr2[i7] = fv2Var2;
                    return;
                } else {
                    int i8 = i4 + 1;
                    fv2VarArr2[i4] = fv2VarArr2[i8];
                    i4 = i8;
                }
            }
        } else {
            if (i4 == i3) {
                return;
            }
            fv2 fv2Var3 = fv2VarArr2[i4];
            while (true) {
                i4--;
                if (i4 < i3) {
                    fv2VarArr2[i3] = fv2Var3;
                    return;
                }
                fv2VarArr2[i4 + 1] = fv2VarArr2[i4];
            }
        }
    }

    public final void q(boolean z) {
        e eVar = this.k;
        boolean zHasMessages = eVar.hasMessages(1);
        if (zHasMessages) {
            eVar.removeMessages(1);
        }
        if (zHasMessages || z) {
            eVar.sendEmptyMessageAtTime(1, 1500L);
        }
    }

    public final void r(fv2 fv2Var, String str, boolean z) {
        if (fv2Var == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            sb.append(", ");
        }
        sb.append(this.W.getResources().getString(this.S ? z ? R$string.accessibility_tabstrip_incognito_identifier : R$string.accessibility_tabstrip_incognito_identifier_selected : z ? R$string.accessibility_tabstrip_identifier : R$string.accessibility_tabstrip_identifier_selected));
        fv2Var.n = sb.toString();
        String string = xz.a.getString(R$string.accessibility_tabstrip_btn_close_tab, str);
        ya3 ya3Var = fv2Var.f;
        ya3Var.j = string;
        ya3Var.k = string;
    }

    public final void s(boolean z) {
        float f2 = z ? 1.0f : 0.0f;
        h61 h61Var = (h61) this.a;
        ew ewVar = h61Var.G;
        lw.a aVar = lw.l;
        ya3 ya3Var = this.n;
        float f3 = f2;
        kw.g(ewVar, ya3Var, aVar, ya3Var.d, f3, 150L).start();
        ew ewVar2 = h61Var.G;
        lw lwVar = this.o;
        kw.g(ewVar2, lwVar, aVar, lwVar.d, f3, 150L).start();
    }

    public final void t(float f2, boolean z, long j) {
        if (f2 == 0.0f) {
            return;
        }
        if (z) {
            this.v.b(Math.round(this.w), (int) f2, i(), j);
        } else {
            this.w += f2;
        }
    }

    public final void u(boolean z) {
        if (this.c == null || z == this.T) {
            return;
        }
        this.T = z;
        vd1 vd1Var = z ? this.f : this.g;
        if (vd1Var != this.e) {
            ((h61) this.a).T(null);
        }
        this.e = vd1Var;
        int i = 0;
        while (true) {
            fv2[] fv2VarArr = this.h;
            if (i >= fv2VarArr.length) {
                break;
            }
            o(fv2VarArr[i]);
            i++;
        }
        TabModel tabModel = this.c;
        if (tabModel.getTabAt(tabModel.index()) != null) {
            H();
            TabModel tabModel2 = this.c;
            this.w += b(f(tabModel2.getTabAt(tabModel2.index()).getId()), true, true, true);
        }
        J();
    }

    public final void v(fv2 fv2Var, boolean z) {
        if (fv2Var != this.L) {
            float f2 = z ? 0.65f : 1.0f;
            if (fv2Var.g) {
                kw.g(((h61) this.a).G, fv2Var, fv2.H, fv2Var.z, f2, 150L).start();
            } else {
                fv2Var.z = f2;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean w(defpackage.fv2 r7, int r8) {
        /*
            r6 = this;
            fv2[] r0 = r6.h
            int r0 = r0.length
            r1 = 1
            int r0 = r0 - r1
            r2 = 0
            if (r8 != r0) goto La
            r8 = r1
            goto Lb
        La:
            r8 = r2
        Lb:
            boolean r0 = org.chromium.ui.base.LocalizationUtils.isLayoutRtl()
            ya3 r3 = r6.n
            float r4 = r6.p
            if (r0 == 0) goto L48
            if (r8 == 0) goto L2b
            float r8 = r7.s
            float r8 = r8 + r4
            android.graphics.RectF r0 = r3.a
            float r3 = r0.left
            float r0 = r0.width()
            float r0 = r0 + r3
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 >= 0) goto L29
        L27:
            r8 = r1
            goto L37
        L29:
            r8 = r2
            goto L37
        L2b:
            float r8 = r7.s
            float r8 = r8 + r4
            float r0 = r6.h(r2)
            int r8 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r8 >= 0) goto L29
            goto L27
        L37:
            float r7 = r7.s
            float r0 = r6.N
            float r6 = r6.h(r1)
            float r0 = r0 - r6
            int r6 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r6 <= 0) goto L46
            r6 = r1
            goto L7e
        L46:
            r6 = r2
            goto L7e
        L48:
            float r0 = r7.s
            float r5 = r7.u
            float r0 = r0 + r5
            float r5 = r6.h(r1)
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L57
            r0 = r1
            goto L58
        L57:
            r0 = r2
        L58:
            if (r8 == 0) goto L69
            float r6 = r7.s
            float r7 = r7.u
            float r6 = r6 + r7
            float r6 = r6 - r4
            android.graphics.RectF r7 = r3.a
            float r7 = r7.left
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 <= 0) goto L7c
            goto L7a
        L69:
            float r8 = r7.s
            float r7 = r7.u
            float r8 = r8 + r7
            float r8 = r8 - r4
            float r7 = r6.N
            float r6 = r6.h(r2)
            float r7 = r7 - r6
            int r6 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r6 <= 0) goto L7c
        L7a:
            r6 = r1
            goto L7d
        L7c:
            r6 = r2
        L7d:
            r8 = r0
        L7e:
            if (r8 != 0) goto L83
            if (r6 != 0) goto L83
            goto L84
        L83:
            r1 = r2
        L84:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vu2.w(fv2, int):boolean");
    }

    public final void x(ArrayList arrayList, AnimatorListenerAdapter animatorListenerAdapter) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(arrayList);
        if (animatorListenerAdapter != null) {
            animatorSet.addListener(animatorListenerAdapter);
        }
        Animator animator = this.m;
        if (animator != null && animator.isRunning()) {
            this.m.end();
        }
        this.m = animatorSet;
        animatorSet.start();
    }

    public final void y(long j, float f2) {
        if (this.A) {
            return;
        }
        n3.X0("MobileToolbarStartReorderTab");
        ya3 ya3Var = this.M;
        if (ya3Var != null && ya3Var.f) {
            ya3Var.f = false;
        }
        this.M = null;
        fv2 fv2VarK = k(f2);
        this.L = fv2VarK;
        if (fv2VarK == null || fv2VarK.h) {
            return;
        }
        g();
        ArrayList arrayList = new ArrayList();
        this.E = 0L;
        this.G = 0L;
        this.H = 0.0f;
        this.z = 0;
        this.B = f2;
        this.C = this.y / 2.0f;
        this.A = true;
        this.I = false;
        TabModel tabModel = this.c;
        tabModel.q(z7.G(tabModel, this.L.a), 3, false);
        if (!bq.f0.a()) {
            int i = 0;
            while (true) {
                fv2[] fv2VarArr = this.h;
                if (i >= fv2VarArr.length) {
                    break;
                }
                v(fv2VarArr[i], true);
                i++;
            }
        }
        s(false);
        if (this.T) {
            this.v.b(Math.round(this.w), (int) b(this.L, true, true, true), i(), j);
        }
        this.L.getClass();
        if (oj2.F()) {
            E(this.L, false, arrayList);
        }
        x(arrayList, new yu2(this, false));
        ((h61) this.a).T(null);
    }

    public final void z(int i) {
        if (f(i) == null) {
            return;
        }
        fv2[] fv2VarArr = this.h;
        c(!(fv2VarArr[fv2VarArr.length - 1].a == i));
        ((h61) this.a).T(null);
    }
}
