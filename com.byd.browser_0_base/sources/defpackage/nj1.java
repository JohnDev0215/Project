package defpackage;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.k81;
import defpackage.pj1;
import defpackage.xz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.ApplicationStatus;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: MultiInstanceManagerApi31.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nj1 extends kj1 implements ApplicationStatus.c {
    public final int u;
    public final zp1<bi1> v;
    public int w;
    public oj1 x;
    public Tab y;
    public final a z;

    /* JADX INFO: compiled from: MultiInstanceManagerApi31.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void r1(Tab tab) {
            if (tab.isIncognito()) {
                return;
            }
            nj1.z(nj1.this.w, tab.getTitle());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void x0(TabImpl tabImpl) {
            if (tabImpl.isIncognito()) {
                return;
            }
            int i = nj1.this.w;
            SharedPreferencesManager.getInstance().o(ar.k.b(String.valueOf(i)), tabImpl.v().j());
        }
    }

    public nj1(Activity activity, aq1 aq1Var, uj1 uj1Var, r2 r2Var, aq1 aq1Var2, jf1 jf1Var) {
        super(activity, aq1Var, uj1Var, r2Var, jf1Var);
        this.w = -1;
        this.z = new a();
        this.u = vj1.h();
        this.v = aq1Var2;
    }

    public static Activity r(int i) {
        n53 n53VarR = oj2.r();
        for (Activity activity : ApplicationStatus.b()) {
            if (i == ((o53) n53VarR).b(activity)) {
                return activity;
            }
        }
        return null;
    }

    public static int u(int i) {
        return SharedPreferencesManager.getInstance().d(-1, ar.e.b(String.valueOf(i)));
    }

    public static boolean v(int i) {
        return vj1.r(i) != 0;
    }

    public static void w(int i) {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        sharedPreferencesManager.removeKey(ar.k.b(String.valueOf(i)));
        sharedPreferencesManager.removeKey(ar.i.b(String.valueOf(i)));
        sharedPreferencesManager.removeKey(ar.h.b(String.valueOf(i)));
        sharedPreferencesManager.removeKey(ar.f.b(String.valueOf(i)));
        sharedPreferencesManager.removeKey(ar.g.b(String.valueOf(i)));
        vj1 vj1Var = vj1.h;
        sharedPreferencesManager.removeKey(ar.j.b(String.valueOf(i)));
    }

    public static void y(int i, y13 y13Var) {
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        int count = y13Var.g(false).getCount();
        sharedPreferencesManager.m(count, ar.h.b(String.valueOf(i)));
        sharedPreferencesManager.m(y13Var.g(true).getCount(), ar.f.b(String.valueOf(i)));
        if (count == 0) {
            SharedPreferencesManager.getInstance().o(ar.k.b(String.valueOf(i)), "");
            z(i, "");
        }
    }

    public static void z(int i, String str) {
        SharedPreferencesManager.getInstance().o(ar.i.b(String.valueOf(i)), str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.kj1, jf1.a
    public final boolean a(int i, boolean z) {
        if (i != R$id.manage_all_windows_menu_id) {
            return super.a(i, z);
        }
        ArrayList arrayListS = s();
        Activity activity = this.g;
        bi1 bi1Var = this.v.get();
        LargeIconBridge largeIconBridge = new LargeIconBridge(t());
        lj1 lj1Var = new lj1(0, this);
        ko0 ko0Var = new ko0(15, this);
        zc0 zc0Var = new zc0(17, this);
        int i2 = 1;
        boolean z2 = arrayListS.size() < vj1.h();
        final g11 g11Var = new g11(activity, bi1Var, largeIconBridge, lj1Var, ko0Var, zc0Var);
        g11 g11Var2 = g11.n;
        int i3 = 5;
        if (g11Var2 != null) {
            g11Var2.e.b(5, g11Var2.j);
        }
        t63 t63Var = t63.j;
        if (t63Var != null) {
            t63Var.f.b(5, t63Var.g);
        }
        g11.n = g11Var;
        int i4 = 0;
        while (true) {
            int size = arrayListS.size();
            ya1 ya1Var = g11Var.f;
            Context context = g11Var.a;
            if (i4 >= size) {
                g11Var.l = new PropertyModel(h11.i);
                g11Var.a(z2);
                ya1Var.t(new xa1(1, g11Var.l));
                f11 f11Var = new f11(g11Var);
                Resources resources = context.getResources();
                String string = resources.getString(R$string.instance_switcher_header);
                PropertyModel.b bVar = new PropertyModel.b(di1.B);
                bVar.e(di1.a, f11Var);
                bVar.b(di1.q, true);
                bVar.e(di1.h, g11Var.h);
                bVar.e(di1.c, string);
                bVar.e(di1.j, null);
                bVar.d(di1.m, resources, R$string.cancel);
                bVar.b(di1.w, true);
                PropertyModel propertyModelA = bVar.a();
                g11Var.j = propertyModelA;
                g11Var.e.j(propertyModelA, 1, false);
                n3.X0("MobileMenuWindowManager");
                oj2.t(t()).notifyEvent("instance_switcher_iph_used");
                return true;
            }
            final b11 b11Var = (b11) arrayListS.get(i4);
            th3 th3Var = g11Var.g;
            Object objB = th3Var.b(b11Var);
            Object objA = th3Var.a(b11Var);
            boolean z3 = b11Var.c == i2 ? i2 : 0;
            PropertyModel.b bVar2 = new PropertyModel.b(h11.i);
            bVar2.e(h11.d, objB);
            bVar2.e(h11.e, objA);
            bVar2.b(h11.a, z3);
            bVar2.c(h11.f, b11Var.a);
            bVar2.e(h11.g, new wd2(g11Var, i3, b11Var));
            if (z3 == 0) {
                ya1 ya1Var2 = new ya1();
                ya1Var2.t(zd.c(R$string.instance_switcher_close_window, 0, 0));
                zd zdVar = new zd(context, ya1Var2, new k81.a() { // from class: d11
                    @Override // k81.a
                    public final void a(PropertyModel propertyModel) {
                        String quantityString;
                        g11 g11Var3 = g11Var;
                        g11Var3.getClass();
                        if (propertyModel.h(o81.a) == R$string.instance_switcher_close_window) {
                            b11 b11Var2 = b11Var;
                            if ((th3.d(b11Var2) == 0 && b11Var2.c == 3) ? true : SharedPreferencesManager.getInstance().readBoolean("Chrome.MultiWindow.CloseWindowSkipConfirm", false)) {
                                g11Var3.b(b11Var2);
                                return;
                            }
                            g11Var3.k = b11Var2;
                            int i5 = R$style.Theme_Chromium_Multiwindow_CloseConfirmDialog;
                            Context context2 = g11Var3.a;
                            Dialog dialog = new Dialog(context2, i5);
                            dialog.setCanceledOnTouchOutside(false);
                            dialog.setContentView(R$layout.close_confirmation_dialog);
                            Resources resources2 = context2.getResources();
                            ImageView imageView = (ImageView) dialog.findViewById(R$id.title_icon);
                            imageView.setImageDrawable(g11Var3.i);
                            imageView.setOnClickListener(new e11(dialog, 0));
                            ((TextView) dialog.findViewById(R$id.title)).setText(resources2.getString(R$string.instance_switcher_close_confirm_header));
                            TextView textView = (TextView) dialog.findViewById(R$id.message);
                            th3 th3Var2 = g11Var3.g;
                            th3Var2.getClass();
                            int iD = th3.d(b11Var2);
                            int i6 = b11Var2.b == -1 ? 0 : b11Var2.g;
                            Resources resources3 = th3Var2.a.getResources();
                            if (b11Var2.h && i6 > 0) {
                                int i7 = b11Var2.f;
                                quantityString = i7 == 0 ? resources3.getQuantityString(R$plurals.instance_switcher_close_confirm_deleted_incognito, i6, Integer.valueOf(i6)) : resources3.getQuantityString(R$plurals.instance_switcher_close_confirm_deleted_incognito_mixed, i7, Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i6));
                            } else if (iD == 0) {
                                quantityString = resources3.getString(R$string.instance_switcher_close_confirm_deleted_tabs_zero);
                            } else {
                                String str = b11Var2.e;
                                if (iD == 1) {
                                    quantityString = resources3.getString(R$string.instance_switcher_close_confirm_deleted_tabs_one, str);
                                } else {
                                    int i8 = iD - 1;
                                    quantityString = resources3.getQuantityString(R$plurals.instance_switcher_close_confirm_deleted_tabs_many, i8, str, Integer.valueOf(i8), str);
                                }
                            }
                            textView.setText(quantityString);
                            TextView textView2 = (TextView) dialog.findViewById(R$id.positive_button);
                            textView2.setText(resources2.getString(R$string.close));
                            textView2.setOnClickListener(new c30(g11Var3, 2, dialog));
                            TextView textView3 = (TextView) dialog.findViewById(R$id.negative_button);
                            textView3.setText(resources2.getString(R$string.cancel));
                            textView3.setOnClickListener(new n40(g11Var3, 4, dialog));
                            dialog.show();
                        }
                    }
                });
                zdVar.a(new qe3(16));
                bVar2.e(h11.h, new py0(1, zdVar));
            }
            PropertyModel propertyModelA2 = bVar2.a();
            th3Var.c(propertyModelA2, h11.c, b11Var);
            ya1Var.t(new xa1(0, propertyModelA2));
            i4++;
            i2 = 1;
        }
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (vj1.p()) {
            if (i == 3 || i == 5) {
                SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                long jE = sharedPreferencesManager.e(0L, "Chrome.MultiInstance.MaxCountTime");
                int i2 = 0;
                int iD = sharedPreferencesManager.d(0, "Chrome.MultiInstance.MaxInstanceCount");
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - jE > 86400000) {
                    if (jE != 0) {
                        oj2.U(iD, this.u + 1, "Android.MultiInstance.MaxInstanceCount");
                    }
                    sharedPreferencesManager.n(jCurrentTimeMillis, "Chrome.MultiInstance.MaxCountTime");
                } else {
                    i2 = iD;
                }
                int iG = vj1.g();
                if (iG > i2) {
                    sharedPreferencesManager.m(iG, "Chrome.MultiInstance.MaxInstanceCount");
                }
            }
        }
    }

    @Override // defpackage.kj1
    public final int e(int i, int i2, boolean z) {
        x();
        int i3 = this.u;
        if (i >= 0 && i < i3) {
            return i;
        }
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i5 >= i3) {
                i5 = -1;
                break;
            }
            if (i2 == u(i5)) {
                break;
            }
            i5++;
        }
        if (i5 != -1) {
            return i5;
        }
        if (z) {
            while (i4 < i3) {
                if (!v(i4)) {
                    return i4;
                }
                i4++;
            }
            return -1;
        }
        int i6 = -1;
        while (i4 < i3) {
            if (u(i4) == -1 && (i6 == -1 || vj1.r(i4) > vj1.r(i6))) {
                i6 = i4;
            }
            i4++;
        }
        return i6;
    }

    @Override // defpackage.kj1, defpackage.hy1
    public final void f() {
        super.f();
        int i = this.w;
        vj1 vj1Var = vj1.h;
        SharedPreferencesManager.getInstance().n(System.currentTimeMillis(), ar.j.b(String.valueOf(i)));
    }

    @Override // defpackage.kj1
    public final void i(int i, int i2) {
        this.w = i;
        SharedPreferencesManager.getInstance().m(i2, ar.e.b(String.valueOf(i)));
        a23 a23Var = this.h.get().b;
        this.x = new oj1(this, a23Var, a23Var);
        int i3 = this.w;
        vj1 vj1Var = vj1.h;
        SharedPreferencesManager.getInstance().n(System.currentTimeMillis(), ar.j.b(String.valueOf(i3)));
        int iG = vj1.g();
        int i4 = this.u + 1;
        oj2.U(iG, i4, "Android.MultiInstance.NumInstances");
        Iterator it = ApplicationStatus.b().iterator();
        int i5 = 0;
        while (it.hasNext()) {
            if (((Activity) it.next()) instanceof ChromeTabbedActivity) {
                i5++;
            }
        }
        oj2.U(i5, i4, "Android.MultiInstance.NumActivities");
        Activity activity = this.g;
        ActivityManager activityManager = (ActivityManager) activity.getSystemService("activity");
        if (activityManager != null) {
            hs hsVar = new hs(6, activityManager);
            pq2 pq2Var = new pq2(20, "com.google.android.apps.chrome.Main");
            if (pj1.i == null) {
                pj1.i = new pj1(hsVar, pq2Var);
            }
            pj1 pj1Var = pj1.i;
            pj1Var.g.a(new pj1.b() { // from class: mj1
                @Override // pj1.b
                public final void a(boolean z) {
                    this.a.getClass();
                    if (vj1.p()) {
                        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
                        long jF = sharedPreferencesManager.f("Chrome.MultiInstance.StartTime");
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (jF == 0 && z) {
                            n3.X0("Android.MultiInstance.Enter");
                            sharedPreferencesManager.n(jCurrentTimeMillis, "Chrome.MultiInstance.StartTime");
                        } else {
                            if (jF == 0 || z) {
                                return;
                            }
                            n3.X0("Android.MultiInstance.Exit");
                            oj2.X(jCurrentTimeMillis - jF, "Android.MultiInstance.TotalDuration");
                            sharedPreferencesManager.n(0L, "Chrome.MultiInstance.StartTime");
                        }
                    }
                }
            });
        }
        ApplicationStatus.f(this, activity);
    }

    @Override // defpackage.kj1
    public final boolean k() {
        return false;
    }

    /* JADX WARN: Type inference failed for: r4v4, types: [T, rf] */
    /* JADX WARN: Type inference failed for: r5v3, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v0, types: [T, java.lang.String] */
    @Override // defpackage.kj1
    public final void o(Tab tab) {
        bi1 bi1Var = this.v.get();
        LargeIconBridge largeIconBridge = new LargeIconBridge(t());
        gp gpVar = new gp(this, 15, tab);
        ArrayList arrayListS = s();
        t63 t63Var = new t63(this.g, bi1Var, largeIconBridge, gpVar);
        g11 g11Var = g11.n;
        if (g11Var != null) {
            g11Var.e.b(5, g11Var.j);
        }
        t63 t63Var2 = t63.j;
        if (t63Var2 != null) {
            t63Var2.f.b(5, t63Var2.g);
        }
        t63.j = t63Var;
        Iterator it = arrayListS.iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                s63 s63Var = new s63(t63Var);
                Context context = t63Var.a;
                Resources resources = context.getResources();
                String string = context.getString(R$string.menu_move_to_other_window);
                PropertyModel.b bVar = new PropertyModel.b(di1.B);
                bVar.e(di1.a, s63Var);
                bVar.b(di1.q, true);
                bVar.e(di1.h, t63Var.e);
                bVar.e(di1.c, string);
                bVar.b(di1.l, true);
                bVar.c(di1.u, 1);
                bVar.d(di1.j, resources, R$string.target_selector_move);
                bVar.d(di1.m, resources, R$string.cancel);
                bVar.b(di1.w, true);
                PropertyModel propertyModelA = bVar.a();
                t63Var.g = propertyModelA;
                t63Var.f.j(propertyModelA, 1, false);
                return;
            }
            b11 b11Var = (b11) it.next();
            int i = b11Var.c;
            int i2 = b11Var.a;
            if (i == 1) {
                t63Var.h = b11Var;
                t63Var.i = i2;
            }
            th3 th3Var = t63Var.d;
            ?? B = th3Var.b(b11Var);
            ?? A = th3Var.a(b11Var);
            HashMap mapE = PropertyModel.e(u63.g);
            PropertyModel.r<String> rVar = u63.b;
            int i3 = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = B;
            mapE.put(rVar, gVar);
            PropertyModel.r<String> rVar2 = u63.c;
            PropertyModel.g gVar2 = new PropertyModel.g();
            gVar2.a = A;
            mapE.put(rVar2, gVar2);
            PropertyModel.p pVar = u63.d;
            PropertyModel.d dVar = new PropertyModel.d();
            dVar.a = i2;
            mapE.put(pVar, dVar);
            PropertyModel.n nVar = u63.f;
            if (b11Var.c != 1) {
                z = false;
            }
            PropertyModel.a aVar = new PropertyModel.a();
            aVar.a = z;
            mapE.put(nVar, aVar);
            PropertyModel.r<View.OnClickListener> rVar3 = u63.e;
            ?? rfVar = new rf(t63Var, 3, b11Var);
            PropertyModel.g gVar3 = new PropertyModel.g();
            gVar3.a = rfVar;
            mapE.put(rVar3, gVar3);
            PropertyModel propertyModel = new PropertyModel(mapE);
            th3Var.c(propertyModel, u63.a, b11Var);
            t63Var.c.t(new xa1(0, propertyModel));
        }
    }

    @Override // defpackage.kj1, defpackage.v80
    public final void onDestroy() {
        oj1 oj1Var = this.x;
        if (oj1Var != null) {
            oj1Var.a();
        }
        x();
        if (this.w != -1) {
            ApplicationStatus.i(this);
        }
        super.onDestroy();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0055  */
    @Override // defpackage.kj1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(java.lang.String r5) {
        /*
            r4 = this;
            android.content.Intent r0 = new android.content.Intent
            android.app.Activity r1 = r4.g
            java.lang.Class<org.chromium.chrome.browser.ChromeTabbedActivity> r2 = org.chromium.chrome.browser.ChromeTabbedActivity.class
            r0.<init>(r1, r2)
            r3 = 0
            defpackage.kj1.s = r3
            defpackage.vj1.s(r0, r1, r2)
            r2 = 268435456(0x10000000, float:2.524355E-29)
            r0.addFlags(r2)
            r2 = 134217728(0x8000000, float:3.85186E-34)
            r0.addFlags(r2)
            java.lang.String r2 = "com.android.chrome.prefer_new"
            r3 = 1
            r0.putExtra(r2, r3)
            defpackage.q11.a(r0)
            tj1 r4 = r4.i
            uj1 r4 = (defpackage.uj1) r4
            r4.getClass()
            vj1 r2 = defpackage.vj1.h
            r2.getClass()
            boolean r3 = defpackage.vj1.b()
            if (r3 != 0) goto L55
            r4.getClass()
            r2.getClass()
            android.app.Activity r3 = r4.a
            boolean r3 = defpackage.vj1.o(r3)
            if (r3 != 0) goto L55
            r4.getClass()
            r2.getClass()
            android.app.Activity r2 = r4.a
            boolean r2 = defpackage.vj1.n(r2)
            if (r2 == 0) goto L51
            goto L55
        L51:
            r1.startActivity(r0)
            goto L63
        L55:
            r2 = 4096(0x1000, float:5.74E-42)
            r0.addFlags(r2)
            android.app.Activity r4 = r4.a
            android.os.Bundle r4 = defpackage.vj1.j(r4)
            r1.startActivity(r0, r4)
        L63:
            defpackage.n3.X0(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nj1.p(java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList s() {
        /*
            r17 = this;
            r0 = r17
            r17.x()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.util.SparseBooleanArray r2 = defpackage.vj1.k()
            r3 = -1
            r4 = 0
            r14 = r4
        L11:
            int r5 = r0.u
            r6 = 1
            if (r14 >= r5) goto Laf
            boolean r5 = v(r14)
            if (r5 != 0) goto L1e
            goto Lab
        L1e:
            android.app.Activity r5 = r(r14)
            if (r5 == 0) goto L3a
            android.app.Activity r7 = r0.g
            if (r5 != r7) goto L2e
            int r3 = r1.size()
            r8 = r6
            goto L3c
        L2e:
            int r5 = r5.getTaskId()
            boolean r5 = r2.get(r5)
            if (r5 == 0) goto L3a
            r5 = 2
            goto L3b
        L3a:
            r5 = 3
        L3b:
            r8 = r5
        L3c:
            int r7 = u(r14)
            b11 r15 = new b11
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r5 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            i41 r6 = defpackage.ar.k
            java.lang.String r9 = java.lang.String.valueOf(r14)
            java.lang.String r6 = r6.b(r9)
            r9 = 0
            java.lang.String r10 = r5.g(r6, r9)
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r5 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            i41 r6 = defpackage.ar.i
            java.lang.String r11 = java.lang.String.valueOf(r14)
            java.lang.String r6 = r6.b(r11)
            java.lang.String r11 = r5.g(r6, r9)
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r5 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            i41 r6 = defpackage.ar.h
            java.lang.String r9 = java.lang.String.valueOf(r14)
            java.lang.String r6 = r6.b(r9)
            int r12 = r5.d(r4, r6)
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r5 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            i41 r6 = defpackage.ar.f
            java.lang.String r9 = java.lang.String.valueOf(r14)
            java.lang.String r6 = r6.b(r9)
            int r13 = r5.d(r4, r6)
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r5 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            i41 r6 = defpackage.ar.g
            java.lang.String r9 = java.lang.String.valueOf(r14)
            java.lang.String r6 = r6.b(r9)
            boolean r16 = r5.readBoolean(r6, r4)
            r5 = r15
            r6 = r14
            r9 = r10
            r10 = r11
            r11 = r12
            r12 = r13
            r13 = r16
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13)
            r1.add(r15)
        Lab:
            int r14 = r14 + 1
            goto L11
        Laf:
            if (r3 == 0) goto Lc0
            int r0 = r1.size()
            if (r0 <= r6) goto Lc0
            java.lang.Object r0 = r1.remove(r3)
            b11 r0 = (defpackage.b11) r0
            r1.add(r4, r0)
        Lc0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nj1.s():java.util.ArrayList");
    }

    public final Profile t() {
        return this.h.get().b.z().f();
    }

    public final void x() {
        ActivityManager.RecentTaskInfo recentTaskInfoC;
        HashSet hashSet = new HashSet();
        for (ActivityManager.AppTask appTask : ((ActivityManager) this.g.getSystemService("activity")).getAppTasks()) {
            if (TextUtils.equals(vj1.e(appTask), ChromeTabbedActivity.class.getName()) && (recentTaskInfoC = k5.c(appTask)) != null) {
                hashSet.add(Integer.valueOf(recentTaskInfoC.id));
            }
        }
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        i41 i41Var = ar.e;
        sharedPreferencesManager.a.getClass();
        Map<String, ?> all = xz.a.a.getAll();
        HashMap map = new HashMap();
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith(i41Var.a)) {
                map.put(key, entry.getValue());
            }
        }
        for (Map.Entry entry2 : map.entrySet()) {
            if (!hashSet.contains(entry2.getValue())) {
                SharedPreferencesManager.getInstance().removeKey((String) entry2.getKey());
            }
        }
        for (int i = 0; i < this.u; i++) {
            if (v(i)) {
                if (!((SharedPreferencesManager.getInstance().d(0, ar.h.b(String.valueOf(i))) == 0 && u(i) == -1) ? false : true)) {
                    w(i);
                }
            }
        }
    }
}
