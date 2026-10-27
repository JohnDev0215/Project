package j6;

import S8.AbstractC0727y;
import S8.E;
import V8.AbstractC0828q;
import V8.M;
import V8.S;
import V8.f0;
import android.content.Context;
import com.stingray.kca.model.AssetTypeV2;
import java.util.LinkedHashMap;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final J5.c f17057a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final X8.c f17058b;
    public final X8.c c;
    public final S d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final M f17059e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f0 f17060f;

    public n(Context context, J5.c cVar) {
        A7.m.f(context, "appContext");
        A7.m.f(cVar, "kcaService");
        this.f17057a = cVar;
        Z8.e eVar = E.f8084a;
        this.f17058b = AbstractC0727y.a(Z8.d.f11010n);
        this.c = AbstractC0727y.a(X8.m.f10573a);
        S sB = AbstractC0828q.b(0, 0, null, 7);
        this.d = sB;
        this.f17059e = new M(sB);
        this.f17060f = AbstractC0828q.c(new LinkedHashMap());
    }

    public static final Object a(n nVar, a aVar, s7.i iVar) {
        Object objF = nVar.d.f(new o(aVar), iVar);
        return objF == EnumC2303a.l ? objF : m7.q.f19267a;
    }

    public static final Object b(n nVar, String str, s7.i iVar) {
        Object objF = nVar.d.f(new q(str), iVar);
        return objF == EnumC2303a.l ? objF : m7.q.f19267a;
    }

    public static Object d(n nVar, String str, AbstractC2410c abstractC2410c) {
        AssetTypeV2 assetTypeV2 = AssetTypeV2.SONG;
        nVar.getClass();
        Z8.e eVar = E.f8084a;
        return AbstractC0727y.F(Z8.d.f11010n, new i(nVar, str, assetTypeV2, null), abstractC2410c);
    }

    public final synchronized void c(String str, AssetTypeV2 assetTypeV2, InterfaceC2772a interfaceC2772a, boolean z6) {
        A7.m.f(str, "assetId");
        A7.m.f(assetTypeV2, "assetType");
        A7.m.f(interfaceC2772a, "onLimitReached");
        if (assetTypeV2 != AssetTypeV2.SONG) {
            return;
        }
        AbstractC0727y.w(this.f17058b, null, null, new c(this, str, assetTypeV2, z6, interfaceC2772a, null), 3);
    }

    public final synchronized void e(AssetTypeV2 assetTypeV2, String str, boolean z6) {
        A7.m.f(str, "assetId");
        A7.m.f(assetTypeV2, "assetType");
        if (assetTypeV2 != AssetTypeV2.SONG) {
            return;
        }
        AbstractC0727y.w(this.f17058b, null, null, new l(this, assetTypeV2, str, z6, null), 3);
    }
}
