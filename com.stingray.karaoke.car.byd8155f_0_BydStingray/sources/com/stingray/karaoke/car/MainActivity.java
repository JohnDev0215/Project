package com.stingray.karaoke.car;

import A7.B;
import G2.a;
import H7.InterfaceC0318c;
import I5.A;
import I5.m;
import I5.o;
import I5.v;
import K5.t;
import L5.i;
import N0.q;
import N1.C0420a;
import N5.P;
import N5.Z;
import N5.q0;
import N5.t0;
import Q6.b;
import V8.f0;
import W5.e;
import X5.D0;
import Z5.c;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioFocusRequest;
import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.C;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.InterfaceC0986y;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.h0;
import b2.C0993c;
import c6.C1034a;
import com.bumptech.glide.d;
import com.stingray.companion.CompanionManager;
import com.stingray.drivemodedetector.DriveModeDetector;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.navigation.StingrayKaraokeTabScreen;
import f6.g;
import f6.h;
import h.l;
import h.n;
import h2.AbstractC1347d;
import h8.AbstractC1387d;
import i.AbstractC1396c;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import k.C1585e;
import k.C1586f;
import k.C1587g;
import k3.C1672v;
import k3.C1674w;
import k6.C1691C;
import k6.C1696d;
import k6.C1699g;
import k6.C1704l;
import k6.C1705m;
import k6.r;
import kotlin.Metadata;
import n6.C1967H;
import n6.C1972e;
import n7.AbstractC1999n;
import n7.u;
import n7.z;
import u2.I;
import w.AbstractC2565A;
import w.F;
import w.G;
import x.AbstractC2613B;
import x.AbstractC2614C;
import x.AbstractC2631e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0011²\u0006\f\u0010\u0005\u001a\u00020\u00048\nX\u008a\u0084\u0002²\u0006\f\u0010\u0006\u001a\u00020\u00048\nX\u008a\u0084\u0002²\u0006\f\u0010\u0007\u001a\u00020\u00048\nX\u008a\u0084\u0002²\u0006\f\u0010\b\u001a\u00020\u00048\nX\u008a\u0084\u0002²\u0006\f\u0010\t\u001a\u00020\u00048\nX\u008a\u0084\u0002²\u0006\u0012\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\nX\u008a\u0084\u0002²\u0006\u0018\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\r8\nX\u008a\u0084\u0002²\u0006\f\u0010\u000f\u001a\u00020\u00048\nX\u008a\u0084\u0002²\u0006\f\u0010\u0010\u001a\u00020\u00048\nX\u008a\u0084\u0002"}, d2 = {"Lcom/stingray/karaoke/car/MainActivity;", "Lh/n;", "<init>", "()V", "", "userShowLegalDialog", "isPlayerVisible", "showDisplayInfo", "showLogBox", "showLogDetail", "", "", "logMessageList", "", "currentLogDetailProps", "isLegalTextHidden", "isPassengerRestrictionEnabled", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MainActivity extends n implements b {

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public static final /* synthetic */ int f12974j0 = 0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public C0420a f12975F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public volatile O6.b f12976G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final Object f12977H = new Object();

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public boolean f12978I = false;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final q f12979J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final q f12980K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final q f12981L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final q f12982M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public final q f12983N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final q f12984O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final q f12985P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public AlertDialog f12986Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f12987R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public o f12988S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final F f12989T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final G f12990U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public Z f12991V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public C1704l f12992W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public C6.b f12993X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public CompanionManager f12994Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public C1696d f12995Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public C1699g f12996a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public C1705m f12997b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public e f12998c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public r f12999d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public i f13000e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public C1972e f13001f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public t f13002g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public C1691C f13003h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final C1587g f13004i0;

    public MainActivity() {
        i(new m(this, 0));
        A a10 = new A(this, 10);
        B b10 = A7.A.f443a;
        this.f12979J = new q(b10.b(c.class), new A(this, 13), a10, new A(this, 14));
        this.f12980K = new q(b10.b(h.class), new A(this, 16), new A(this, 15), new A(this, 17));
        this.f12981L = new q(b10.b(g.class), new A(this, 19), new A(this, 18), new A(this, 20));
        this.f12982M = new q(b10.b(e6.h.class), new A(this, 1), new A(this, 0), new A(this, 2));
        this.f12983N = new q(b10.b(C1034a.class), new A(this, 4), new A(this, 3), new A(this, 5));
        this.f12984O = new q(b10.b(f6.o.class), new A(this, 7), new A(this, 6), new A(this, 8));
        this.f12985P = new q(b10.b(D0.class), new A(this, 11), new A(this, 9), new A(this, 12));
        I i7 = AbstractC2614C.c;
        this.f12989T = AbstractC2565A.b(AbstractC2631e.q(500, 0, i7, 2), 2).a(AbstractC2565A.e(AbstractC2631e.q(500, 0, AbstractC2613B.f23388b, 2), new A3.c(8)));
        this.f12990U = AbstractC2565A.c(AbstractC2631e.q(500, 0, i7, 2), 2).a(AbstractC2565A.f(AbstractC2631e.q(500, 0, AbstractC2613B.f23387a, 2), new A3.c(8)));
        final Y4.e eVar = new Y4.e(26);
        final a aVar = new a(this, 2);
        final l lVar = this.f15122t;
        A7.m.f(lVar, "registry");
        final String str = "activity_rq#" + this.f15121s.getAndIncrement();
        A7.m.f(str, "key");
        C c = this.l;
        if (c.f11908p.compareTo(EnumC0981t.o) >= 0) {
            throw new IllegalStateException(("LifecycleOwner " + this + " is attempting to register while current state is " + c.f11908p + ". LifecycleOwners must call register before they are STARTED.").toString());
        }
        LinkedHashMap linkedHashMap = lVar.f15105b;
        if (((Integer) linkedHashMap.get(str)) == null) {
            for (Number number : (P8.a) P8.m.e0(C1586f.f17607m)) {
                int iIntValue = number.intValue();
                LinkedHashMap linkedHashMap2 = lVar.f15104a;
                if (!linkedHashMap2.containsKey(Integer.valueOf(iIntValue))) {
                    int iIntValue2 = number.intValue();
                    linkedHashMap2.put(Integer.valueOf(iIntValue2), str);
                    linkedHashMap.put(str, Integer.valueOf(iIntValue2));
                }
            }
            throw new NoSuchElementException("Sequence contains no element matching the predicate.");
        }
        LinkedHashMap linkedHashMap3 = lVar.c;
        C1585e c1585e = (C1585e) linkedHashMap3.get(str);
        c1585e = c1585e == null ? new C1585e(c) : c1585e;
        InterfaceC0986y interfaceC0986y = new InterfaceC0986y() { // from class: k.c
            @Override // androidx.lifecycle.InterfaceC0986y
            public final void b(androidx.lifecycle.A a11, EnumC0980s enumC0980s) {
                Integer num;
                l lVar2 = lVar;
                A7.m.f(lVar2, "this$0");
                String str2 = str;
                G2.a aVar2 = aVar;
                Y4.e eVar2 = eVar;
                EnumC0980s enumC0980s2 = EnumC0980s.ON_START;
                LinkedHashMap linkedHashMap4 = lVar2.f15106e;
                Bundle bundle = lVar2.f15108g;
                LinkedHashMap linkedHashMap5 = lVar2.f15107f;
                if (enumC0980s2 == enumC0980s) {
                    linkedHashMap4.put(str2, new C1584d(aVar2, eVar2));
                    if (linkedHashMap5.containsKey(str2)) {
                        Object obj = linkedHashMap5.get(str2);
                        linkedHashMap5.remove(str2);
                        aVar2.l(obj);
                    }
                    C1581a c1581a = (C1581a) l9.l.P(str2, bundle);
                    if (c1581a != null) {
                        bundle.remove(str2);
                        aVar2.l(eVar2.F(c1581a.f17600m, c1581a.l));
                        return;
                    }
                    return;
                }
                if (EnumC0980s.ON_STOP == enumC0980s) {
                    linkedHashMap4.remove(str2);
                    return;
                }
                if (EnumC0980s.ON_DESTROY == enumC0980s) {
                    if (!lVar2.d.contains(str2) && (num = (Integer) lVar2.f15105b.remove(str2)) != null) {
                        lVar2.f15104a.remove(num);
                    }
                    linkedHashMap4.remove(str2);
                    if (linkedHashMap5.containsKey(str2)) {
                        StringBuilder sbJ = AbstractC1347d.j("Dropping pending result for request ", str2, ": ");
                        sbJ.append(linkedHashMap5.get(str2));
                        h6.n.t("ActivityResultRegistry", sbJ.toString());
                        linkedHashMap5.remove(str2);
                    }
                    if (bundle.containsKey(str2)) {
                        h6.n.t("ActivityResultRegistry", "Dropping pending result for request " + str2 + ": " + ((C1581a) l9.l.P(str2, bundle)));
                        bundle.remove(str2);
                    }
                    LinkedHashMap linkedHashMap6 = lVar2.c;
                    C1585e c1585e2 = (C1585e) linkedHashMap6.get(str2);
                    if (c1585e2 != null) {
                        ArrayList arrayList = c1585e2.f17606b;
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            c1585e2.f17605a.V0((InterfaceC0986y) it.next());
                        }
                        arrayList.clear();
                        linkedHashMap6.remove(str2);
                    }
                }
            }
        };
        c1585e.f17605a.M0(interfaceC0986y);
        c1585e.f17606b.add(interfaceC0986y);
        linkedHashMap3.put(str, c1585e);
        this.f13004i0 = new C1587g(lVar, str, eVar);
    }

    @Override // Q6.b
    public final Object c() {
        return m().c();
    }

    @Override // h.n, androidx.lifecycle.InterfaceC0977o
    public final h0 d() {
        return w0.c.N(this, super.d());
    }

    public final O6.b m() {
        if (this.f12976G == null) {
            synchronized (this.f12977H) {
                try {
                    if (this.f12976G == null) {
                        this.f12976G = new O6.b(this, 0);
                    }
                } finally {
                }
            }
        }
        return this.f12976G;
    }

    public final e n() {
        e eVar = this.f12998c0;
        if (eVar != null) {
            return eVar;
        }
        A7.m.j("appNavigationManager");
        throw null;
    }

    public final i o() {
        i iVar = this.f13000e0;
        if (iVar != null) {
            return iVar;
        }
        A7.m.j("deepLinkManager");
        throw null;
    }

    @Override // h.n, android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        A7.m.f(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        AlertDialog alertDialog = this.f12986Q;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.f12986Q = null;
            w();
        }
    }

    @Override // h.n, android.app.Activity
    public final void onCreate(Bundle bundle) {
        u(bundle);
        i iVarO = o();
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null && intent.getDataString() != null) {
            String dataString = intent.getDataString();
            A7.m.c(dataString);
            iVarO.f4035g = dataString;
        }
        this.f12987R = bundle != null && bundle.getBoolean("SplashCompleted");
        o oVar = new o();
        this.f12988S = oVar;
        ProcessLifecycleOwner.f11939s.f11943q.M0(oVar);
        d.q(a(), this, new A7.F(this, 2));
        C1972e c1972e = this.f13001f0;
        if (c1972e == null) {
            A7.m.j("gameLoopManager");
            throw null;
        }
        Map mapS = z.s(new m7.i(1, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_X1_1), getString(R.string.Encouragement_X1_2), getString(R.string.Encouragement_X1_3), getString(R.string.Encouragement_X1_4), getString(R.string.Encouragement_X1_5), getString(R.string.Encouragement_X1_6)))), new m7.i(2, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_X2_1), getString(R.string.Encouragement_X2_2), getString(R.string.Encouragement_X2_3), getString(R.string.Encouragement_X2_4), getString(R.string.Encouragement_X2_5), getString(R.string.Encouragement_X2_6)))), new m7.i(3, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_X3_1), getString(R.string.Encouragement_X3_2), getString(R.string.Encouragement_X3_3), getString(R.string.Encouragement_X3_4), getString(R.string.Encouragement_X3_5), getString(R.string.Encouragement_X3_6)))), new m7.i(4, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_X4_1), getString(R.string.Encouragement_X4_2), getString(R.string.Encouragement_X4_3), getString(R.string.Encouragement_X4_4), getString(R.string.Encouragement_X4_5), getString(R.string.Encouragement_X4_6)))));
        C1967H c1967h = c1972e.f20059b;
        c1967h.getClass();
        LinkedHashMap linkedHashMap = c1967h.d;
        linkedHashMap.clear();
        linkedHashMap.putAll(mapS);
        C1972e c1972e2 = this.f13001f0;
        if (c1972e2 == null) {
            A7.m.j("gameLoopManager");
            throw null;
        }
        Map mapS2 = z.s(new m7.i(0, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_0Star_1), getString(R.string.Encouragement_0Star_2), getString(R.string.Encouragement_0Star_3), getString(R.string.Encouragement_0Star_4), getString(R.string.Encouragement_0Star_5), getString(R.string.Encouragement_0Star_6)))), new m7.i(1, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_1Star_1), getString(R.string.Encouragement_1Star_2), getString(R.string.Encouragement_1Star_3), getString(R.string.Encouragement_1Star_4), getString(R.string.Encouragement_1Star_5), getString(R.string.Encouragement_1Star_6)))), new m7.i(2, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_2Stars_1), getString(R.string.Encouragement_2Stars_2), getString(R.string.Encouragement_2Stars_3), getString(R.string.Encouragement_2Stars_4), getString(R.string.Encouragement_2Stars_5), getString(R.string.Encouragement_2Stars_6)))), new m7.i(3, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_3Stars_1), getString(R.string.Encouragement_3Stars_2), getString(R.string.Encouragement_3Stars_3), getString(R.string.Encouragement_3Stars_4), getString(R.string.Encouragement_3Stars_5), getString(R.string.Encouragement_3Stars_6)))), new m7.i(4, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_4Stars_1), getString(R.string.Encouragement_4Stars_2), getString(R.string.Encouragement_4Stars_3), getString(R.string.Encouragement_4Stars_4), getString(R.string.Encouragement_4Stars_5), getString(R.string.Encouragement_4Stars_6)))), new m7.i(5, AbstractC1387d.t(AbstractC1999n.D(getString(R.string.Encouragement_5Stars_1), getString(R.string.Encouragement_5Stars_2), getString(R.string.Encouragement_5Stars_3), getString(R.string.Encouragement_5Stars_4), getString(R.string.Encouragement_5Stars_5), getString(R.string.Encouragement_5Stars_6)))));
        C1967H c1967h2 = c1972e2.f20059b;
        c1967h2.getClass();
        LinkedHashMap linkedHashMap2 = c1967h2.f20005e;
        linkedHashMap2.clear();
        linkedHashMap2.putAll(mapS2);
        AbstractC1396c.a(this, new b0.b(1570643408, new v(this, 3), true));
    }

    @Override // android.app.Activity
    public final void onDestroy() throws IllegalAccessException, InvocationTargetException {
        if (!isChangingConfigurations()) {
            Z zR = r();
            f0 f0Var = zR.f4655e0;
            if (((Boolean) f0Var.getValue()).booleanValue()) {
                q0 q0Var = zR.c;
                q0Var.f4742j = null;
                q0Var.f4743k = -1;
                q0Var.f4740h.clear();
                q0Var.f4744m.i(null);
                f0 f0Var2 = q0Var.l;
                f0Var2.getClass();
                f0Var2.m(null, -1);
                u uVar = u.l;
                f0 f0Var3 = q0Var.f4745n;
                f0Var3.getClass();
                f0Var3.m(null, uVar);
                f0 f0Var4 = q0Var.o;
                f0Var4.getClass();
                f0Var4.m(null, 0);
                AudioFocusRequest audioFocusRequest = zR.f4671t;
                if (audioFocusRequest == null) {
                    A7.m.j("audioFocusRequest");
                    throw null;
                }
                zR.f4628G.abandonAudioFocusRequest(audioFocusRequest);
                C1672v c1672v = zR.f4667p;
                if (c1672v == null) {
                    A7.m.j("mediaController");
                    throw null;
                }
                c1672v.stop();
                C1674w c1674w = zR.f4669r;
                if (c1674w == null) {
                    A7.m.j("playerFuture");
                    throw null;
                }
                C1672v.W0(c1674w);
                P p10 = zR.f4668q;
                if (p10 == null) {
                    A7.m.j("companionListener");
                    throw null;
                }
                zR.f4650b.removeListener(p10);
                zR.C(PlayerStatus.STOPPED);
                zR.f4636O.l(null);
                Boolean bool = Boolean.FALSE;
                f0 f0Var5 = zR.f4657f0;
                f0Var5.getClass();
                f0Var5.m(null, bool);
                f0 f0Var6 = zR.f4632K;
                f0Var6.getClass();
                f0Var6.m(null, bool);
                f0 f0Var7 = zR.f4640S;
                f0Var7.getClass();
                f0Var7.m(null, bool);
                f0 f0Var8 = zR.f4638Q;
                f0Var8.getClass();
                f0Var8.m(null, bool);
                f0Var.m(null, bool);
            }
            e eVarN = n();
            eVarN.c.clear();
            eVarN.j(StingrayKaraokeTabScreen.Homepage);
            Boolean bool2 = Boolean.FALSE;
            f0 f0Var9 = eVarN.f9820e;
            f0Var9.getClass();
            f0Var9.m(null, bool2);
            eVarN.d.clear();
            k6.o.f18285b.clear();
            k6.o.c.clear();
            t().j();
            ((g) this.f12981L.getValue()).j();
            ((e6.h) this.f12982M.getValue()).j();
            ((C1034a) this.f12983N.getValue()).j();
            ((f6.o) this.f12984O.getValue()).j();
            s().j();
            q().j();
        }
        AlertDialog alertDialog = this.f12986Q;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.f12986Q = null;
        }
        o oVar = this.f12988S;
        if (oVar != null) {
            ProcessLifecycleOwner.f11939s.f11943q.V0(oVar);
        }
        C1705m c1705m = this.f12997b0;
        if (c1705m == null) {
            A7.m.j("languageManager");
            throw null;
        }
        h2.r rVar = c1705m.f18281b;
        if (rVar != null) {
            c1705m.f18280a.unregisterReceiver(rVar);
            c1705m.f18281b = null;
        }
        t0 t0Var = q().f14620q;
        t0Var.c.removeListener(t0Var);
        v();
    }

    @Override // h.n, android.app.Activity
    public final void onNewIntent(Intent intent) {
        A7.m.f(intent, "intent");
        super.onNewIntent(intent);
        A7.m.f("onNewIntent: " + intent, "text");
        o().e(intent);
    }

    @Override // android.app.Activity
    public final void onResume() {
        super.onResume();
        C1704l c1704l = this.f12992W;
        if (c1704l != null) {
            c1704l.f18273b.setCurrentActivityContext(this);
        } else {
            A7.m.j("driveModeManager");
            throw null;
        }
    }

    @Override // h.n, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        A7.m.f(bundle, "outState");
        bundle.putBoolean("SplashCompleted", this.f12987R);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z6) {
        View currentFocus;
        super.onWindowFocusChanged(z6);
        if (z6 || (currentFocus = getCurrentFocus()) == null) {
            return;
        }
        currentFocus.clearFocus();
    }

    public final C1696d p() {
        C1696d c1696d = this.f12995Z;
        if (c1696d != null) {
            return c1696d;
        }
        A7.m.j("devSettingsManager");
        throw null;
    }

    public final h q() {
        return (h) this.f12980K.getValue();
    }

    public final Z r() {
        Z z6 = this.f12991V;
        if (z6 != null) {
            return z6;
        }
        A7.m.j("playerManager");
        throw null;
    }

    public final D0 s() {
        return (D0) this.f12985P.getValue();
    }

    public final c t() {
        return (c) this.f12979J.getValue();
    }

    public final void u(Bundle bundle) {
        super.onCreate(bundle);
        if (getApplication() instanceof b) {
            O6.b bVar = (O6.b) m().o;
            n nVar = bVar.f5162m;
            l3.P p10 = new l3.P(nVar.f(), new N6.c((n) bVar.o, 1), nVar.e());
            InterfaceC0318c interfaceC0318cL = z.l(O6.d.class);
            String strA = interfaceC0318cL.a();
            if (strA == null) {
                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
            }
            C0420a c0420a = ((O6.d) p10.s(interfaceC0318cL, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(strA))).c;
            this.f12975F = c0420a;
            if (((C0993c) c0420a.l) == null) {
                c0420a.l = (C0993c) e();
            }
        }
    }

    public final void v() {
        super.onDestroy();
        C0420a c0420a = this.f12975F;
        if (c0420a != null) {
            c0420a.l = null;
        }
    }

    public final void w() {
        try {
            final int i7 = 0;
            AlertDialog.Builder positiveButton = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.GearboxPermission_Title)).setMessage(R.string.GearboxPermission_Text).setPositiveButton(R.string.GearboxPermission_GrantButton, new DialogInterface.OnClickListener(this) { // from class: I5.n

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ MainActivity f3014b;

                {
                    this.f3014b = this;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) throws Throwable {
                    MainActivity mainActivity = this.f3014b;
                    switch (i7) {
                        case 0:
                            int i11 = MainActivity.f12974j0;
                            C1704l c1704l = mainActivity.f12992W;
                            if (c1704l == null) {
                                A7.m.j("driveModeManager");
                                throw null;
                            }
                            C1587g c1587g = mainActivity.f13004i0;
                            A7.m.f(c1587g, "activityResultLauncher");
                            DriveModeDetector driveModeDetector = c1704l.f18273b;
                            if (driveModeDetector.checkForPermissions(mainActivity)) {
                                c1704l.a();
                            } else {
                                driveModeDetector.askForPermissions(c1587g);
                            }
                            mainActivity.f12986Q = null;
                            return;
                        default:
                            int i12 = MainActivity.f12974j0;
                            mainActivity.finish();
                            return;
                    }
                }
            });
            final int i10 = 1;
            AlertDialog alertDialogCreate = positiveButton.setNegativeButton(R.string.GearboxPermission_CloseButton, new DialogInterface.OnClickListener(this) { // from class: I5.n

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ MainActivity f3014b;

                {
                    this.f3014b = this;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i102) throws Throwable {
                    MainActivity mainActivity = this.f3014b;
                    switch (i10) {
                        case 0:
                            int i11 = MainActivity.f12974j0;
                            C1704l c1704l = mainActivity.f12992W;
                            if (c1704l == null) {
                                A7.m.j("driveModeManager");
                                throw null;
                            }
                            C1587g c1587g = mainActivity.f13004i0;
                            A7.m.f(c1587g, "activityResultLauncher");
                            DriveModeDetector driveModeDetector = c1704l.f18273b;
                            if (driveModeDetector.checkForPermissions(mainActivity)) {
                                c1704l.a();
                            } else {
                                driveModeDetector.askForPermissions(c1587g);
                            }
                            mainActivity.f12986Q = null;
                            return;
                        default:
                            int i12 = MainActivity.f12974j0;
                            mainActivity.finish();
                            return;
                    }
                }
            }).setCancelable(false).create();
            this.f12986Q = alertDialogCreate;
            if (alertDialogCreate != null) {
                alertDialogCreate.show();
            }
        } catch (Exception e10) {
            A7.m.f("showPermissionDeniedDialog exception: " + e10, "text");
        }
    }
}
