package L5;

import A7.k;
import A7.m;
import A9.j;
import A9.l;
import P8.n;
import Q8.o;
import S8.InterfaceC0725w;
import c9.A;
import c9.H;
import c9.r;
import c9.u;
import c9.v;
import c9.w;
import c9.y;
import com.stingray.kca.model.KaraokeItem;
import g1.AbstractC1274a;
import h8.AbstractC1386c;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;
import m7.q;
import n7.AbstractC1985B;
import n7.z;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import r9.C2322l;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class a extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f4014m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ F6.b f4015n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(i iVar, String str, F6.b bVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = iVar;
        this.f4014m = str;
        this.f4015n = bVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new a(this.l, this.f4014m, this.f4015n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        a aVar = (a) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        q qVar = q.f19267a;
        aVar.invokeSuspend(qVar);
        return qVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        w wVarC;
        N0.q qVar;
        AbstractC1274a abstractC1274aW;
        AbstractC1274a rVar;
        AbstractC1274a abstractC1274aW2;
        AbstractC1274a rVar2;
        AbstractC1274a abstractC1274aW3;
        AbstractC1274a rVar3;
        A9.b cVar;
        A9.b eVar;
        Object objD;
        AbstractC1274a abstractC1274aW4;
        AbstractC1274a rVar4;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        i iVar = this.l;
        r6.f fVarC = iVar.f4032b.c();
        int i7 = r6.f.f21871e;
        fVarC.getClass();
        String str = this.f4014m;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("Api-Version", "2.0");
        linkedHashMap2.put("Accept", "application/json");
        A9.f fVar = new A9.f(A9.g.l, o.x0("/api/artist/{artistId}", "{artistId}", str.toString()), linkedHashMap2, linkedHashMap, null);
        try {
            v vVar = new v();
            vVar.f(null, "https://karaoke-client-api-prod.stingray.com/");
            wVarC = vVar.c();
        } catch (IllegalArgumentException unused) {
            wVarC = null;
        }
        if (wVarC == null) {
            throw new IllegalStateException("baseUrl is invalid.");
        }
        A9.a.a(fVarC, fVar);
        v vVarF = wVarC.f();
        vVarF.a(Q8.h.h1(fVar.f479b, '/'));
        for (Map.Entry entry : fVar.d.entrySet()) {
            Iterator it = ((Iterable) entry.getValue()).iterator();
            while (it.hasNext()) {
                vVarF.b((String) entry.getKey(), (String) it.next());
            }
        }
        w wVarC2 = vVarF.c();
        LinkedHashMap linkedHashMap3 = fVar.c;
        CharSequence charSequence = (CharSequence) linkedHashMap3.get("Content-Type");
        if (charSequence == null || charSequence.length() == 0) {
            linkedHashMap3.put("Content-Type", "application/json");
        }
        CharSequence charSequence2 = (CharSequence) linkedHashMap3.get("Accept");
        if (charSequence2 == null || charSequence2.length() == 0) {
            linkedHashMap3.put("Accept", "application/json");
        }
        CharSequence charSequence3 = (CharSequence) linkedHashMap3.get("Content-Type");
        if (charSequence3 == null || charSequence3.length() == 0) {
            throw new IllegalStateException("Missing Content-Type header. This is required.");
        }
        CharSequence charSequence4 = (CharSequence) linkedHashMap3.get("Accept");
        if (charSequence4 == null || charSequence4.length() == 0) {
            throw new IllegalStateException("Missing Accept header. This is required.");
        }
        Object obj2 = linkedHashMap3.get("Content-Type");
        m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str2 = (String) obj2;
        String strJ = k.j("getDefault(...)", Q8.h.b1(str2, ";", str2), "toLowerCase(...)");
        int iOrdinal = fVar.f478a.ordinal();
        Object obj3 = fVar.f480e;
        switch (iOrdinal) {
            case 0:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                break;
            case 1:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                if (strJ.equals("multipart/form-data")) {
                    String strF = k.f("randomUUID().toString()");
                    C2322l c2322l = C2322l.o;
                    C2322l c2322lP = m6.b.p(strF);
                    y yVar = A.f12436i;
                    ArrayList arrayList = new ArrayList();
                    y yVar2 = A.f12436i;
                    m.f(yVar2, "type");
                    if (!yVar2.f12628b.equals("multipart")) {
                        throw new IllegalArgumentException(k.h("multipart != ", yVar2).toString());
                    }
                    m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it2 = ((Map) obj3).entrySet().iterator();
                    if (it2.hasNext()) {
                        Map.Entry entry2 = (Map.Entry) it2.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry2);
                    }
                    if (arrayList.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    rVar = new A(c2322lP, yVar2, d9.b.x(arrayList));
                } else {
                    if (obj3 instanceof File) {
                        File file = (File) obj3;
                        Pattern pattern = y.d;
                        abstractC1274aW = k.c(file, "<this>", n.L(strJ), file);
                    } else if (strJ.equals("application/x-www-form-urlencoded")) {
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList arrayListM = k.m(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                        Iterator it3 = ((Map) obj3).entrySet().iterator();
                        if (it3.hasNext()) {
                            Map.Entry entry3 = (Map.Entry) it3.next();
                            throw android.hardware.bydauto.ac.tsimpl.a.c(entry3);
                        }
                        rVar = new r(arrayList2, arrayListM);
                    } else {
                        if (!o.z0(strJ, "application/", false) || !o.s0(strJ, "json", false)) {
                            if (strJ.equals("application/xml")) {
                                throw new UnsupportedOperationException("xml not currently supported.");
                            }
                            throw new UnsupportedOperationException("requestBody currently only supports JSON body and File body.");
                        }
                        if (obj3 == null) {
                            abstractC1274aW = d9.b.d;
                        } else {
                            String strE = k.e(q.class, obj3);
                            Pattern pattern2 = y.d;
                            abstractC1274aW = com.bumptech.glide.d.w(strE, n.L(strJ));
                        }
                    }
                    rVar = abstractC1274aW;
                }
                qVar.w(rVar);
                break;
            case 2:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                qVar.F();
                break;
            case 3:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                qVar.J("OPTIONS", null);
                break;
            case 4:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                if (strJ.equals("multipart/form-data")) {
                    String strF2 = k.f("randomUUID().toString()");
                    C2322l c2322l2 = C2322l.o;
                    C2322l c2322lP2 = m6.b.p(strF2);
                    y yVar3 = A.f12436i;
                    ArrayList arrayList3 = new ArrayList();
                    y yVar4 = A.f12436i;
                    m.f(yVar4, "type");
                    if (!yVar4.f12628b.equals("multipart")) {
                        throw new IllegalArgumentException(k.h("multipart != ", yVar4).toString());
                    }
                    m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it4 = ((Map) obj3).entrySet().iterator();
                    if (it4.hasNext()) {
                        Map.Entry entry4 = (Map.Entry) it4.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry4);
                    }
                    if (arrayList3.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    rVar2 = new A(c2322lP2, yVar4, d9.b.x(arrayList3));
                } else {
                    if (obj3 instanceof File) {
                        File file2 = (File) obj3;
                        Pattern pattern3 = y.d;
                        abstractC1274aW2 = k.c(file2, "<this>", n.L(strJ), file2);
                    } else if (strJ.equals("application/x-www-form-urlencoded")) {
                        ArrayList arrayList4 = new ArrayList();
                        ArrayList arrayListM2 = k.m(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                        Iterator it5 = ((Map) obj3).entrySet().iterator();
                        if (it5.hasNext()) {
                            Map.Entry entry5 = (Map.Entry) it5.next();
                            throw android.hardware.bydauto.ac.tsimpl.a.c(entry5);
                        }
                        rVar2 = new r(arrayList4, arrayListM2);
                    } else {
                        if (!o.z0(strJ, "application/", false) || !o.s0(strJ, "json", false)) {
                            if (strJ.equals("application/xml")) {
                                throw new UnsupportedOperationException("xml not currently supported.");
                            }
                            throw new UnsupportedOperationException("requestBody currently only supports JSON body and File body.");
                        }
                        if (obj3 == null) {
                            abstractC1274aW2 = d9.b.d;
                        } else {
                            String strE2 = k.e(q.class, obj3);
                            Pattern pattern4 = y.d;
                            abstractC1274aW2 = com.bumptech.glide.d.w(strE2, n.L(strJ));
                        }
                    }
                    rVar2 = abstractC1274aW2;
                }
                qVar.L(rVar2);
                break;
            case 5:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                if (strJ.equals("multipart/form-data")) {
                    String strF3 = k.f("randomUUID().toString()");
                    C2322l c2322l3 = C2322l.o;
                    C2322l c2322lP3 = m6.b.p(strF3);
                    y yVar5 = A.f12436i;
                    ArrayList arrayList5 = new ArrayList();
                    y yVar6 = A.f12436i;
                    m.f(yVar6, "type");
                    if (!yVar6.f12628b.equals("multipart")) {
                        throw new IllegalArgumentException(k.h("multipart != ", yVar6).toString());
                    }
                    m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it6 = ((Map) obj3).entrySet().iterator();
                    if (it6.hasNext()) {
                        Map.Entry entry6 = (Map.Entry) it6.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry6);
                    }
                    if (arrayList5.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    rVar3 = new A(c2322lP3, yVar6, d9.b.x(arrayList5));
                } else {
                    if (obj3 instanceof File) {
                        File file3 = (File) obj3;
                        Pattern pattern5 = y.d;
                        abstractC1274aW3 = k.c(file3, "<this>", n.L(strJ), file3);
                    } else if (strJ.equals("application/x-www-form-urlencoded")) {
                        ArrayList arrayList6 = new ArrayList();
                        ArrayList arrayListM3 = k.m(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                        Iterator it7 = ((Map) obj3).entrySet().iterator();
                        if (it7.hasNext()) {
                            Map.Entry entry7 = (Map.Entry) it7.next();
                            throw android.hardware.bydauto.ac.tsimpl.a.c(entry7);
                        }
                        rVar3 = new r(arrayList6, arrayListM3);
                    } else {
                        if (!o.z0(strJ, "application/", false) || !o.s0(strJ, "json", false)) {
                            if (strJ.equals("application/xml")) {
                                throw new UnsupportedOperationException("xml not currently supported.");
                            }
                            throw new UnsupportedOperationException("requestBody currently only supports JSON body and File body.");
                        }
                        if (obj3 == null) {
                            abstractC1274aW3 = d9.b.d;
                        } else {
                            String strE3 = k.e(q.class, obj3);
                            Pattern pattern6 = y.d;
                            abstractC1274aW3 = com.bumptech.glide.d.w(strE3, n.L(strJ));
                        }
                    }
                    rVar3 = abstractC1274aW3;
                }
                qVar.M(rVar3);
                break;
            case 6:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                if (strJ.equals("multipart/form-data")) {
                    String strF4 = k.f("randomUUID().toString()");
                    C2322l c2322l4 = C2322l.o;
                    C2322l c2322lP4 = m6.b.p(strF4);
                    y yVar7 = A.f12436i;
                    ArrayList arrayList7 = new ArrayList();
                    y yVar8 = A.f12436i;
                    m.f(yVar8, "type");
                    if (!yVar8.f12628b.equals("multipart")) {
                        throw new IllegalArgumentException(k.h("multipart != ", yVar8).toString());
                    }
                    m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it8 = ((Map) obj3).entrySet().iterator();
                    if (it8.hasNext()) {
                        Map.Entry entry8 = (Map.Entry) it8.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry8);
                    }
                    if (arrayList7.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    rVar4 = new A(c2322lP4, yVar8, d9.b.x(arrayList7));
                } else {
                    if (obj3 instanceof File) {
                        File file4 = (File) obj3;
                        Pattern pattern7 = y.d;
                        abstractC1274aW4 = k.c(file4, "<this>", n.L(strJ), file4);
                    } else if (strJ.equals("application/x-www-form-urlencoded")) {
                        ArrayList arrayList8 = new ArrayList();
                        ArrayList arrayListM4 = k.m(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                        Iterator it9 = ((Map) obj3).entrySet().iterator();
                        if (it9.hasNext()) {
                            Map.Entry entry9 = (Map.Entry) it9.next();
                            throw android.hardware.bydauto.ac.tsimpl.a.c(entry9);
                        }
                        rVar4 = new r(arrayList8, arrayListM4);
                    } else {
                        if (!o.z0(strJ, "application/", false) || !o.s0(strJ, "json", false)) {
                            if (strJ.equals("application/xml")) {
                                throw new UnsupportedOperationException("xml not currently supported.");
                            }
                            throw new UnsupportedOperationException("requestBody currently only supports JSON body and File body.");
                        }
                        if (obj3 == null) {
                            abstractC1274aW4 = d9.b.d;
                        } else {
                            String strE4 = k.e(q.class, obj3);
                            Pattern pattern8 = y.d;
                            abstractC1274aW4 = com.bumptech.glide.d.w(strE4, n.L(strJ));
                        }
                    }
                    rVar4 = abstractC1274aW4;
                }
                qVar.O(rVar4);
                break;
            default:
                throw new A9.k();
        }
        for (Map.Entry entry10 : linkedHashMap3.entrySet()) {
            qVar.o((String) entry10.getKey(), (String) entry10.getValue());
        }
        H hF = fVarC.f475a.a(qVar.r()).f();
        String strG = H.g(hF, "Content-Type");
        String strJ2 = strG != null ? k.j("getDefault(...)", Q8.h.b1(strG, ";", strG), "toLowerCase(...)") : null;
        boolean zI = hF.i();
        u uVar = hF.f12517q;
        if (zI) {
            eVar = new A9.e(uVar.r());
        } else {
            boolean zS = AbstractC1274a.S(hF);
            String str3 = hF.f12515n;
            if (zS) {
                eVar = new A9.e(str3, uVar.r());
            } else {
                boolean zJ = hF.j();
                B3.m mVar = hF.f12518r;
                if (zJ) {
                    if (mVar == null) {
                        objD = null;
                    } else if (KaraokeItem.class.equals(File.class)) {
                        File file5 = Files.createTempFile("tmp.net.medicineone.teleconsultationandroid.openapi.openapicommon", null, new FileAttribute[0]).toFile();
                        file5.deleteOnExit();
                        InputStream inputStreamG = mVar.g();
                        try {
                            io.sentry.instrumentation.file.g gVarM = h6.m.m(new FileOutputStream(file5), file5);
                            try {
                                z.d(inputStreamG, gVarM);
                                AbstractC1985B.c(gVarM, null);
                                AbstractC1985B.c(inputStreamG, null);
                                objD = (KaraokeItem) file5;
                            } finally {
                            }
                        } finally {
                        }
                    } else {
                        String strY = mVar.y();
                        if (strY.length() == 0) {
                            objD = null;
                        } else {
                            if (strJ2 != null && (!o.z0(strJ2, "application/", false) || !o.s0(strJ2, "json", false))) {
                                throw new UnsupportedOperationException("responseBody currently only supports JSON body.");
                            }
                            objD = k.d(KaraokeItem.class, A9.i.a(), strY);
                        }
                    }
                    cVar = new l(objD, uVar.r());
                } else {
                    boolean zQ = AbstractC1274a.Q(hF);
                    int i10 = hF.o;
                    cVar = zQ ? new A9.c(str3, mVar != null ? mVar.y() : null, i10, uVar.r()) : new j(str3, mVar != null ? mVar.y() : null, i10, uVar.r());
                }
                eVar = cVar;
            }
        }
        int iOrdinal2 = ((A9.h) eVar.f476m).ordinal();
        if (iOrdinal2 == 0) {
            Object obj4 = ((l) eVar).f491n;
            m.d(obj4, "null cannot be cast to non-null type com.stingray.kca.model.KaraokeItem");
            i.a(iVar, (KaraokeItem) obj4, this.f4015n);
            return q.f19267a;
        }
        if (iOrdinal2 == 1) {
            throw new UnsupportedOperationException("Client does not support Informational responses.");
        }
        if (iOrdinal2 == 2) {
            throw new UnsupportedOperationException("Client does not support Redirection responses.");
        }
        if (iOrdinal2 != 3) {
            if (iOrdinal2 != 4) {
                throw new A9.k();
            }
            j jVar = (j) eVar;
            String str4 = jVar.f490n;
            throw new A9.k(k.k(new StringBuilder("Server error : "), jVar.o, " ", str4 != null ? str4 : ""));
        }
        A9.c cVar2 = (A9.c) eVar;
        String str5 = cVar2.f477n;
        String str6 = str5 != null ? str5 : "";
        StringBuilder sb = new StringBuilder("Client error : ");
        int i11 = cVar2.o;
        throw new A9.d(k.k(sb, i11, " ", str6), i11);
    }
}
