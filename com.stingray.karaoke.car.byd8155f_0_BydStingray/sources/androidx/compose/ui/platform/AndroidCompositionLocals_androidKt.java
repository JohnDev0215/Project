package androidx.compose.ui.platform;

import A.C0004b;
import A.C0044v0;
import A.F;
import A.L0;
import A7.m;
import B8.p;
import F0.AbstractC0227s0;
import F0.C0210j0;
import F0.C0215m;
import F0.C0219o;
import F0.C0231u0;
import F0.C0233v0;
import F0.U;
import F0.W;
import F0.Y;
import F0.Z;
import J0.c;
import J0.d;
import T.AbstractC0750k0;
import T.C0735d;
import T.C0752l0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import T.R0;
import T.S;
import T.X;
import a2.AbstractC0911e;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import b0.b;
import b0.g;
import com.stingray.drivemodedetector.R;
import d0.k;
import d0.l;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import m7.q;
import r.C2257p;
import t3.e;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\" \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00008FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\t²\u0006\u000e\u0010\b\u001a\u00020\u00078\n@\nX\u008a\u008e\u0002"}, d2 = {"LT/k0;", "Landroidx/lifecycle/A;", "getLocalLifecycleOwner", "()LT/k0;", "getLocalLifecycleOwner$annotations", "()V", "LocalLifecycleOwner", "Landroid/content/res/Configuration;", "configuration", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidCompositionLocals_androidKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0773y f11687a = new C0773y(S.f8224q, U.f2128n);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final R0 f11688b = new R0(U.o);
    public static final R0 c = new R0(U.f2129p);
    public static final R0 d = new R0(U.f2130q);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final R0 f11689e = new R0(U.f2131r);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final R0 f11690f = new R0(U.f2132s);

    public static final void a(AndroidComposeView androidComposeView, b bVar, C0761q c0761q, int i7) {
        LinkedHashMap linkedHashMap;
        boolean z6;
        int i10 = 1;
        c0761q.S(1396852028);
        int i11 = (i7 & 6) == 0 ? (c0761q.h(androidComposeView) ? 4 : 2) | i7 : i7;
        if ((i7 & 48) == 0) {
            i11 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            Context context = androidComposeView.getContext();
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (objG == obj) {
                objG = C0735d.O(new Configuration(context.getResources().getConfiguration()), S.f8224q);
                c0761q.a0(objG);
            }
            X x8 = (X) objG;
            Object objG2 = c0761q.G();
            if (objG2 == obj) {
                objG2 = new L0(x8, i10);
                c0761q.a0(objG2);
            }
            androidComposeView.setConfigurationChangeObserver((InterfaceC2782k) objG2);
            Object objG3 = c0761q.G();
            if (objG3 == obj) {
                objG3 = new C0210j0(context);
                c0761q.a0(objG3);
            }
            C0210j0 c0210j0 = (C0210j0) objG3;
            C0215m viewTreeOwners = androidComposeView.getViewTreeOwners();
            if (viewTreeOwners == null) {
                throw new IllegalStateException("Called when the ViewTreeOwnersAvailability is not yet in Available state");
            }
            Object objG4 = c0761q.G();
            e eVar = viewTreeOwners.f2222b;
            if (objG4 == obj) {
                Object parent = androidComposeView.getParent();
                m.d(parent, "null cannot be cast to non-null type android.view.View");
                View view = (View) parent;
                Object tag = view.getTag(R.id.compose_view_saveable_id_tag);
                String strValueOf = tag instanceof String ? (String) tag : null;
                if (strValueOf == null) {
                    strValueOf = String.valueOf(view.getId());
                }
                String str = k.class.getSimpleName() + ':' + strValueOf;
                C2257p c2257pB = eVar.b();
                Bundle bundleB = c2257pB.b(str);
                if (bundleB != null) {
                    linkedHashMap = new LinkedHashMap();
                    for (String str2 : bundleB.keySet()) {
                        ArrayList parcelableArrayList = bundleB.getParcelableArrayList(str2);
                        m.d(parcelableArrayList, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Any?>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.Any?> }");
                        linkedHashMap.put(str2, parcelableArrayList);
                        bundleB = bundleB;
                    }
                } else {
                    linkedHashMap = null;
                }
                C0219o c0219o = C0219o.f2237q;
                R0 r02 = d0.m.f13274a;
                l lVar = new l(linkedHashMap, c0219o);
                try {
                    c2257pB.d(str, new C0233v0(lVar, 0));
                    z6 = true;
                } catch (IllegalArgumentException unused) {
                    z6 = false;
                }
                Object c0231u0 = new C0231u0(lVar, new p(z6, c2257pB, str));
                c0761q.a0(c0231u0);
                objG4 = c0231u0;
            }
            Object obj2 = (C0231u0) objG4;
            q qVar = q.f19267a;
            boolean zH = c0761q.h(obj2);
            Object objG5 = c0761q.G();
            if (zH || objG5 == obj) {
                objG5 = new F(obj2, 10);
                c0761q.a0(objG5);
            }
            C0735d.d(qVar, (InterfaceC2782k) objG5, c0761q);
            Configuration configuration = (Configuration) x8.getValue();
            Object objG6 = c0761q.G();
            if (objG6 == obj) {
                objG6 = new c();
                c0761q.a0(objG6);
            }
            c cVar = (c) objG6;
            Object objG7 = c0761q.G();
            Object obj3 = objG7;
            if (objG7 == obj) {
                Configuration configuration2 = new Configuration();
                if (configuration != null) {
                    configuration2.setTo(configuration);
                }
                c0761q.a0(configuration2);
                obj3 = configuration2;
            }
            Configuration configuration3 = (Configuration) obj3;
            Object objG8 = c0761q.G();
            if (objG8 == obj) {
                objG8 = new Y(configuration3, cVar);
                c0761q.a0(objG8);
            }
            Y y10 = (Y) objG8;
            boolean zH2 = c0761q.h(context);
            Object objG9 = c0761q.G();
            if (zH2 || objG9 == obj) {
                objG9 = new C0004b(context, 8, y10);
                c0761q.a0(objG9);
            }
            C0735d.d(cVar, (InterfaceC2782k) objG9, c0761q);
            Object objG10 = c0761q.G();
            if (objG10 == obj) {
                objG10 = new d();
                c0761q.a0(objG10);
            }
            d dVar = (d) objG10;
            Object objG11 = c0761q.G();
            if (objG11 == obj) {
                objG11 = new Z(dVar);
                c0761q.a0(objG11);
            }
            Z z10 = (Z) objG11;
            boolean zH3 = c0761q.h(context);
            Object objG12 = c0761q.G();
            if (zH3 || objG12 == obj) {
                objG12 = new C0004b(context, 9, z10);
                c0761q.a0(objG12);
            }
            C0735d.d(dVar, (InterfaceC2782k) objG12, c0761q);
            AbstractC0750k0 abstractC0750k0 = AbstractC0227s0.f2271t;
            C0735d.b(new C0752l0[]{f11687a.a((Configuration) x8.getValue()), f11688b.a(context), AbstractC0911e.f11054a.a(viewTreeOwners.f2221a), f11689e.a(eVar), d0.m.f13274a.a(obj2), f11690f.a(androidComposeView.getView()), c.a(cVar), d.a(dVar), abstractC0750k0.a(Boolean.valueOf(((Boolean) c0761q.k(abstractC0750k0)).booleanValue() | androidComposeView.getScrollCaptureInProgress$ui_release()))}, g.b(1471621628, new C0044v0(androidComposeView, c0210j0, bVar, 2), c0761q), c0761q, 56);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W(androidComposeView, bVar, i7, 0);
        }
    }

    public static final void b(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }

    public static final AbstractC0750k0 getLocalLifecycleOwner() {
        return AbstractC0911e.f11054a;
    }
}
