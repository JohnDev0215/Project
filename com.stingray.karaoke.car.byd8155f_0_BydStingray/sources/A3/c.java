package A3;

import N0.H;
import N5.EnumC0445a;
import S8.AbstractC0721s;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.widget.ImageView;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.MainActivity;
import h8.AbstractC1387d;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import n3.C1934I;
import q7.InterfaceC2189c;
import q7.InterfaceC2192f;
import x.N;
import x4.C2677a;
import z4.AbstractC2766b;
import z4.C2765a;
import z4.C2768d;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements InterfaceC2782k {
    public final /* synthetic */ int l;

    public /* synthetic */ c(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        com.bumptech.glide.l lVarB;
        PackageInfo packageInfo;
        w4.a aVarG;
        x4.c c2677a;
        int i7 = 2;
        InterfaceC2189c interfaceC2189c = null;
        m7.q qVar = m7.q.f19267a;
        switch (this.l) {
            case 0:
                return qVar;
            case 1:
                return (f) obj;
            case 2:
                Map.Entry entry = (Map.Entry) obj;
                A7.m.f(entry, "it");
                return entry.getKey() + "=" + entry.getValue();
            case 3:
                Map.Entry entry2 = (Map.Entry) obj;
                return entry2.getKey() + "=" + entry2.getValue();
            case 4:
                Map.Entry entry3 = (Map.Entry) obj;
                return entry3.getKey() + "=" + entry3.getValue();
            case 5:
                Map.Entry entry4 = (Map.Entry) obj;
                return entry4.getKey() + "=" + entry4.getValue();
            case 6:
                Map.Entry entry5 = (Map.Entry) obj;
                return entry5.getKey() + "=" + entry5.getValue();
            case 7:
                Map.Entry entry6 = (Map.Entry) obj;
                A7.m.f(entry6, "it");
                return entry6.getKey() + "=" + entry6.getValue();
            case 8:
                Integer num = (Integer) obj;
                num.intValue();
                int i10 = MainActivity.f12974j0;
                return num;
            case 9:
                Context context = (Context) obj;
                A7.m.f(context, "context");
                LinkedHashSet linkedHashSet = R1.j.f7356a;
                A7.m.f(linkedHashSet, "keysToMigrate");
                return AbstractC1387d.j(new Q1.d(context, Q1.e.f6114a, new R1.i(linkedHashSet, null), new H5.d(3, interfaceC2189c, i7)));
            case 10:
                Context context2 = (Context) obj;
                A7.m.f(context2, "context");
                ImageView imageView = new ImageView(context2);
                imageView.setContentDescription("Trophy bird");
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            case 11:
                ImageView imageView2 = (ImageView) obj;
                A7.m.f(imageView2, "imageView");
                Context context3 = imageView2.getContext();
                A4.h.c(context3, "You cannot start a load on a not yet attached View or a Fragment where getActivity() returns null (which usually occurs when getActivity() is called before the Fragment is attached or after the Fragment is destroyed).");
                t4.c cVar = com.bumptech.glide.b.a(context3).f12668p;
                cVar.getClass();
                char[] cArr = A4.q.f432a;
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    A4.h.c(imageView2.getContext(), "Unable to obtain a request manager for a view without a Context");
                    lVarB = t4.c.a(imageView2.getContext()) == null ? cVar.b(imageView2.getContext().getApplicationContext()) : cVar.b(imageView2.getContext().getApplicationContext());
                } else {
                    lVarB = cVar.b(imageView2.getContext().getApplicationContext());
                }
                Integer numValueOf = Integer.valueOf(R.raw.background_trophy);
                lVarB.getClass();
                com.bumptech.glide.j jVar = new com.bumptech.glide.j(lVarB.l, lVarB, Drawable.class, lVarB.f12711m);
                com.bumptech.glide.j jVarX = jVar.x(numValueOf);
                Context context4 = jVar.B;
                com.bumptech.glide.j jVar2 = (com.bumptech.glide.j) jVarX.o(context4.getTheme());
                ConcurrentHashMap concurrentHashMap = AbstractC2766b.f24404a;
                String packageName = context4.getPackageName();
                ConcurrentHashMap concurrentHashMap2 = AbstractC2766b.f24404a;
                e4.e eVar = (e4.e) concurrentHashMap2.get(packageName);
                if (eVar == null) {
                    try {
                        packageInfo = context4.getPackageManager().getPackageInfo(context4.getPackageName(), 0);
                    } catch (PackageManager.NameNotFoundException e10) {
                        h6.n.i("AppVersionSignature", "Cannot resolve info for" + context4.getPackageName(), e10);
                        packageInfo = null;
                    }
                    C2768d c2768d = new C2768d(packageInfo != null ? String.valueOf(packageInfo.versionCode) : UUID.randomUUID().toString());
                    eVar = (e4.e) concurrentHashMap2.putIfAbsent(packageName, c2768d);
                    if (eVar == null) {
                        eVar = c2768d;
                    }
                    break;
                }
                com.bumptech.glide.j jVar3 = (com.bumptech.glide.j) jVar2.m(new C2765a(context4.getResources().getConfiguration().uiMode & 48, eVar));
                jVar3.getClass();
                A4.q.a();
                if (!w4.a.f(jVar3.l, 2048) && imageView2.getScaleType() != null) {
                    switch (com.bumptech.glide.i.f12695a[imageView2.getScaleType().ordinal()]) {
                        case 1:
                            aVarG = jVar3.clone().g(n4.n.d, new n4.h());
                            break;
                        case 2:
                            aVarG = jVar3.clone().g(n4.n.c, new n4.i());
                            aVarG.f23287z = true;
                            break;
                        case 3:
                        case 4:
                        case 5:
                            aVarG = jVar3.clone().g(n4.n.f19947b, new n4.u());
                            aVarG.f23287z = true;
                            break;
                        case 6:
                            aVarG = jVar3.clone().g(n4.n.c, new n4.i());
                            aVarG.f23287z = true;
                            break;
                        default:
                            aVarG = jVar3;
                            break;
                    }
                } else {
                    aVarG = jVar3;
                }
                jVar3.f12699E.c.getClass();
                Class cls = jVar3.f12698D;
                if (Bitmap.class.equals(cls)) {
                    c2677a = new C2677a(imageView2, 0);
                } else {
                    if (!Drawable.class.isAssignableFrom(cls)) {
                        throw new IllegalArgumentException("Unhandled class: " + cls + ", try .as*(Class).transcode(ResourceTranscoder)");
                    }
                    c2677a = new C2677a(imageView2, 1);
                }
                jVar3.w(c2677a, null, aVarG, A4.h.f419a);
                return qVar;
            case 12:
                Q8.f fVar = (Q8.f) obj;
                A7.m.f(fVar, "matchResult");
                if (fVar.c == null) {
                    fVar.c = new Q8.e(fVar);
                }
                Q8.e eVar2 = fVar.c;
                A7.m.c(eVar2);
                return android.hardware.bydauto.ac.tsimpl.a.i("\"", Q8.h.g1((String) eVar2.get(1)).toString(), "\"");
            case 13:
                A7.m.f((EnumC0445a) obj, "it");
                return qVar;
            case 14:
                float fFloatValue = ((Float) obj).floatValue();
                float f10 = 1;
                float f11 = f10 - fFloatValue;
                return Float.valueOf(f10 - (((f11 * f11) * f11) * f11));
            case 15:
                float fFloatValue2 = ((Float) obj).floatValue();
                return Float.valueOf(fFloatValue2 * fFloatValue2 * fFloatValue2 * fFloatValue2 * fFloatValue2);
            case 16:
                P8.k kVar = (P8.k) obj;
                A7.m.f(kVar, "it");
                return kVar.iterator();
            case 17:
                return obj;
            case 18:
                return Boolean.valueOf(obj == null);
            case 19:
                return Integer.valueOf(((Integer) obj).intValue() / 2);
            case 20:
                return Integer.valueOf(((Integer) obj).intValue() / 2);
            case 21:
                return Float.valueOf(((Float) obj).floatValue() * 0.33f);
            case 22:
                C1934I c1934i = (C1934I) obj;
                A7.m.f(c1934i, "$this$navigate");
                c1934i.c = true;
                c1934i.f19833b = true;
                return qVar;
            case 23:
                C1934I c1934i2 = (C1934I) obj;
                A7.m.f(c1934i2, "$this$navigate");
                c1934i2.c = true;
                c1934i2.f19833b = true;
                return qVar;
            case 24:
                A7.m.f((H) obj, "it");
                return qVar;
            case 25:
                L0.j jVar4 = (L0.j) obj;
                A7.m.f(jVar4, "$this$semantics");
                H7.w[] wVarArr = L0.t.f3964a;
                jVar4.l(L0.r.f3950m, qVar);
                return qVar;
            case 26:
                L0.j jVar5 = (L0.j) obj;
                A7.m.f(jVar5, "$this$semantics");
                H7.w[] wVarArr2 = L0.t.f3964a;
                jVar5.l(L0.r.f3950m, qVar);
                return qVar;
            case 27:
                InterfaceC2192f interfaceC2192f = (InterfaceC2192f) obj;
                if (interfaceC2192f instanceof AbstractC0721s) {
                    return (AbstractC0721s) interfaceC2192f;
                }
                return null;
            case 28:
                N n10 = (N) obj;
                A7.m.f(n10, "$this$keyframes");
                n10.f23440a = 400;
                n10.a(Float.valueOf(1.3f), 25);
                n10.a(Float.valueOf(1.0f), 400);
                return qVar;
            default:
                return String.valueOf(((Integer) obj).intValue());
        }
    }
}
