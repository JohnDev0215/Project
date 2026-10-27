package K5;

import E5.M;
import S8.InterfaceC0725w;
import V8.f0;
import c9.H;
import com.stingray.kca.model.KaraokeItem;
import g1.AbstractC1274a;
import h8.AbstractC1386c;
import h8.AbstractC1387d;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import n7.AbstractC1985B;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import r9.C2322l;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class A extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ F l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f3604m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public A(F f10, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = f10;
        this.f3604m = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new A(this.l, this.f3604m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((A) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        c9.w wVarC;
        N0.q qVar;
        AbstractC1274a abstractC1274aW;
        AbstractC1274a abstractC1274aW2;
        AbstractC1274a abstractC1274aW3;
        A9.b cVar;
        A9.b eVar;
        Object objB;
        AbstractC1274a abstractC1274aW4;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        F f10 = this.l;
        r6.f fVarC = f10.f3617a.c();
        Integer num = new Integer(25);
        Boolean boolValueOf = Boolean.valueOf(!((Boolean) ((f0) f10.d.f18294e.l).getValue()).booleanValue());
        int i7 = r6.f.f21871e;
        Integer num2 = 0;
        fVarC.getClass();
        String str = this.f3604m;
        A7.m.f(str, "songId");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("limit", AbstractC1387d.j(num.toString()));
        linkedHashMap.put("offset", AbstractC1387d.j(num2.toString()));
        linkedHashMap.put("allowExplicit", AbstractC1387d.j(boolValueOf.toString()));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("Api-Version", "2.0");
        linkedHashMap2.put("Accept", "application/json");
        A9.f fVar = new A9.f(A9.g.l, Q8.o.x0("/api/song/{songId}/related-songs", "{songId}", str.toString()), linkedHashMap2, linkedHashMap, null);
        try {
            c9.v vVar = new c9.v();
            vVar.f(null, "https://karaoke-client-api-prod.stingray.com/");
            wVarC = vVar.c();
        } catch (IllegalArgumentException unused) {
            wVarC = null;
        }
        if (wVarC == null) {
            throw new IllegalStateException("baseUrl is invalid.");
        }
        A9.a.a(fVarC, fVar);
        c9.v vVarF = wVarC.f();
        vVarF.a(Q8.h.h1(fVar.f479b, '/'));
        for (Map.Entry entry : fVar.d.entrySet()) {
            Iterator it = ((Iterable) entry.getValue()).iterator();
            while (it.hasNext()) {
                vVarF.b((String) entry.getKey(), (String) it.next());
            }
        }
        c9.w wVarC2 = vVarF.c();
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
        A7.m.d(obj2, "null cannot be cast to non-null type kotlin.String");
        String str2 = (String) obj2;
        String strJ = A7.k.j("getDefault(...)", Q8.h.b1(str2, ";", str2), "toLowerCase(...)");
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
                    String strF = A7.k.f("randomUUID().toString()");
                    C2322l c2322l = C2322l.o;
                    C2322l c2322lP = m6.b.p(strF);
                    c9.y yVar = c9.A.f12436i;
                    ArrayList arrayList = new ArrayList();
                    c9.y yVar2 = c9.A.f12436i;
                    A7.m.f(yVar2, "type");
                    if (!yVar2.f12628b.equals("multipart")) {
                        throw new IllegalArgumentException(A7.k.h("multipart != ", yVar2).toString());
                    }
                    A7.m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it2 = ((Map) obj3).entrySet().iterator();
                    if (it2.hasNext()) {
                        Map.Entry entry2 = (Map.Entry) it2.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry2);
                    }
                    if (arrayList.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    abstractC1274aW = new c9.A(c2322lP, yVar2, d9.b.x(arrayList));
                } else if (obj3 instanceof File) {
                    File file = (File) obj3;
                    Pattern pattern = c9.y.d;
                    abstractC1274aW = A7.k.c(file, "<this>", P8.n.L(strJ), file);
                } else if (strJ.equals("application/x-www-form-urlencoded")) {
                    ArrayList arrayList2 = new ArrayList();
                    ArrayList arrayListM = A7.k.m(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it3 = ((Map) obj3).entrySet().iterator();
                    if (it3.hasNext()) {
                        Map.Entry entry3 = (Map.Entry) it3.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry3);
                    }
                    abstractC1274aW = new c9.r(arrayList2, arrayListM);
                } else {
                    if (!Q8.o.z0(strJ, "application/", false) || !Q8.o.s0(strJ, "json", false)) {
                        if (strJ.equals("application/xml")) {
                            throw new UnsupportedOperationException("xml not currently supported.");
                        }
                        throw new UnsupportedOperationException("requestBody currently only supports JSON body and File body.");
                    }
                    if (obj3 == null) {
                        abstractC1274aW = d9.b.d;
                    } else {
                        String strE = A7.k.e(m7.q.class, obj3);
                        Pattern pattern2 = c9.y.d;
                        abstractC1274aW = com.bumptech.glide.d.w(strE, P8.n.L(strJ));
                    }
                }
                qVar.w(abstractC1274aW);
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
                    String strF2 = A7.k.f("randomUUID().toString()");
                    C2322l c2322l2 = C2322l.o;
                    C2322l c2322lP2 = m6.b.p(strF2);
                    c9.y yVar3 = c9.A.f12436i;
                    ArrayList arrayList3 = new ArrayList();
                    c9.y yVar4 = c9.A.f12436i;
                    A7.m.f(yVar4, "type");
                    if (!yVar4.f12628b.equals("multipart")) {
                        throw new IllegalArgumentException(A7.k.h("multipart != ", yVar4).toString());
                    }
                    A7.m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it4 = ((Map) obj3).entrySet().iterator();
                    if (it4.hasNext()) {
                        Map.Entry entry4 = (Map.Entry) it4.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry4);
                    }
                    if (arrayList3.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    abstractC1274aW2 = new c9.A(c2322lP2, yVar4, d9.b.x(arrayList3));
                } else if (obj3 instanceof File) {
                    File file2 = (File) obj3;
                    Pattern pattern3 = c9.y.d;
                    abstractC1274aW2 = A7.k.c(file2, "<this>", P8.n.L(strJ), file2);
                } else if (strJ.equals("application/x-www-form-urlencoded")) {
                    ArrayList arrayList4 = new ArrayList();
                    ArrayList arrayListM2 = A7.k.m(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it5 = ((Map) obj3).entrySet().iterator();
                    if (it5.hasNext()) {
                        Map.Entry entry5 = (Map.Entry) it5.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry5);
                    }
                    abstractC1274aW2 = new c9.r(arrayList4, arrayListM2);
                } else {
                    if (!Q8.o.z0(strJ, "application/", false) || !Q8.o.s0(strJ, "json", false)) {
                        if (strJ.equals("application/xml")) {
                            throw new UnsupportedOperationException("xml not currently supported.");
                        }
                        throw new UnsupportedOperationException("requestBody currently only supports JSON body and File body.");
                    }
                    if (obj3 == null) {
                        abstractC1274aW2 = d9.b.d;
                    } else {
                        String strE2 = A7.k.e(m7.q.class, obj3);
                        Pattern pattern4 = c9.y.d;
                        abstractC1274aW2 = com.bumptech.glide.d.w(strE2, P8.n.L(strJ));
                    }
                }
                qVar.L(abstractC1274aW2);
                break;
            case 5:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                if (strJ.equals("multipart/form-data")) {
                    String strF3 = A7.k.f("randomUUID().toString()");
                    C2322l c2322l3 = C2322l.o;
                    C2322l c2322lP3 = m6.b.p(strF3);
                    c9.y yVar5 = c9.A.f12436i;
                    ArrayList arrayList5 = new ArrayList();
                    c9.y yVar6 = c9.A.f12436i;
                    A7.m.f(yVar6, "type");
                    if (!yVar6.f12628b.equals("multipart")) {
                        throw new IllegalArgumentException(A7.k.h("multipart != ", yVar6).toString());
                    }
                    A7.m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it6 = ((Map) obj3).entrySet().iterator();
                    if (it6.hasNext()) {
                        Map.Entry entry6 = (Map.Entry) it6.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry6);
                    }
                    if (arrayList5.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    abstractC1274aW3 = new c9.A(c2322lP3, yVar6, d9.b.x(arrayList5));
                } else if (obj3 instanceof File) {
                    File file3 = (File) obj3;
                    Pattern pattern5 = c9.y.d;
                    abstractC1274aW3 = A7.k.c(file3, "<this>", P8.n.L(strJ), file3);
                } else if (strJ.equals("application/x-www-form-urlencoded")) {
                    ArrayList arrayList6 = new ArrayList();
                    ArrayList arrayListM3 = A7.k.m(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it7 = ((Map) obj3).entrySet().iterator();
                    if (it7.hasNext()) {
                        Map.Entry entry7 = (Map.Entry) it7.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry7);
                    }
                    abstractC1274aW3 = new c9.r(arrayList6, arrayListM3);
                } else {
                    if (!Q8.o.z0(strJ, "application/", false) || !Q8.o.s0(strJ, "json", false)) {
                        if (strJ.equals("application/xml")) {
                            throw new UnsupportedOperationException("xml not currently supported.");
                        }
                        throw new UnsupportedOperationException("requestBody currently only supports JSON body and File body.");
                    }
                    if (obj3 == null) {
                        abstractC1274aW3 = d9.b.d;
                    } else {
                        String strE3 = A7.k.e(m7.q.class, obj3);
                        Pattern pattern6 = c9.y.d;
                        abstractC1274aW3 = com.bumptech.glide.d.w(strE3, P8.n.L(strJ));
                    }
                }
                qVar.M(abstractC1274aW3);
                break;
            case 6:
                qVar = new N0.q();
                qVar.f4399m = wVarC2;
                if (strJ.equals("multipart/form-data")) {
                    String strF4 = A7.k.f("randomUUID().toString()");
                    C2322l c2322l4 = C2322l.o;
                    C2322l c2322lP4 = m6.b.p(strF4);
                    c9.y yVar7 = c9.A.f12436i;
                    ArrayList arrayList7 = new ArrayList();
                    c9.y yVar8 = c9.A.f12436i;
                    A7.m.f(yVar8, "type");
                    if (!yVar8.f12628b.equals("multipart")) {
                        throw new IllegalArgumentException(A7.k.h("multipart != ", yVar8).toString());
                    }
                    A7.m.d(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it8 = ((Map) obj3).entrySet().iterator();
                    if (it8.hasNext()) {
                        Map.Entry entry8 = (Map.Entry) it8.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry8);
                    }
                    if (arrayList7.isEmpty()) {
                        throw new IllegalStateException("Multipart body must have at least one part.");
                    }
                    abstractC1274aW4 = new c9.A(c2322lP4, yVar8, d9.b.x(arrayList7));
                } else if (obj3 instanceof File) {
                    File file4 = (File) obj3;
                    Pattern pattern7 = c9.y.d;
                    abstractC1274aW4 = A7.k.c(file4, "<this>", P8.n.L(strJ), file4);
                } else if (strJ.equals("application/x-www-form-urlencoded")) {
                    ArrayList arrayList8 = new ArrayList();
                    ArrayList arrayListM4 = A7.k.m(obj3, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, org.openapitools.client.infrastructure.PartConfig<*>>");
                    Iterator it9 = ((Map) obj3).entrySet().iterator();
                    if (it9.hasNext()) {
                        Map.Entry entry9 = (Map.Entry) it9.next();
                        throw android.hardware.bydauto.ac.tsimpl.a.c(entry9);
                    }
                    abstractC1274aW4 = new c9.r(arrayList8, arrayListM4);
                } else {
                    if (!Q8.o.z0(strJ, "application/", false) || !Q8.o.s0(strJ, "json", false)) {
                        if (strJ.equals("application/xml")) {
                            throw new UnsupportedOperationException("xml not currently supported.");
                        }
                        throw new UnsupportedOperationException("requestBody currently only supports JSON body and File body.");
                    }
                    if (obj3 == null) {
                        abstractC1274aW4 = d9.b.d;
                    } else {
                        String strE4 = A7.k.e(m7.q.class, obj3);
                        Pattern pattern8 = c9.y.d;
                        abstractC1274aW4 = com.bumptech.glide.d.w(strE4, P8.n.L(strJ));
                    }
                }
                qVar.O(abstractC1274aW4);
                break;
            default:
                throw new A9.k();
        }
        for (Map.Entry entry10 : linkedHashMap3.entrySet()) {
            qVar.o((String) entry10.getKey(), (String) entry10.getValue());
        }
        H hF = fVarC.f475a.a(qVar.r()).f();
        String strG = H.g(hF, "Content-Type");
        String strJ2 = strG != null ? A7.k.j("getDefault(...)", Q8.h.b1(strG, ";", strG), "toLowerCase(...)") : null;
        boolean zI = hF.i();
        c9.u uVar = hF.f12517q;
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
                        objB = null;
                    } else if (List.class.equals(File.class)) {
                        File file5 = Files.createTempFile("tmp.net.medicineone.teleconsultationandroid.openapi.openapicommon", null, new FileAttribute[0]).toFile();
                        file5.deleteOnExit();
                        InputStream inputStreamG = mVar.g();
                        try {
                            io.sentry.instrumentation.file.g gVarM = h6.m.m(new FileOutputStream(file5), file5);
                            try {
                                n7.z.d(inputStreamG, gVarM);
                                AbstractC1985B.c(gVarM, null);
                                AbstractC1985B.c(inputStreamG, null);
                                objB = A7.E.b(file5);
                            } finally {
                            }
                        } finally {
                        }
                    } else {
                        String strY = mVar.y();
                        if (strY.length() == 0) {
                            objB = null;
                        } else {
                            if (strJ2 != null && (!Q8.o.z0(strJ2, "application/", false) || !Q8.o.s0(strJ2, "json", false))) {
                                throw new UnsupportedOperationException("responseBody currently only supports JSON body.");
                            }
                            E5.H hA = A9.i.a();
                            H7.A a10 = H7.A.c;
                            objB = M.a(hA, A7.A.f443a.d(A7.A.a(com.bumptech.glide.c.m0(A7.A.c(KaraokeItem.class))))).b(strY);
                        }
                    }
                    cVar = new A9.l(objB, uVar.r());
                } else {
                    boolean zQ = AbstractC1274a.Q(hF);
                    int i10 = hF.o;
                    cVar = zQ ? new A9.c(str3, mVar != null ? mVar.y() : null, i10, uVar.r()) : new A9.j(str3, mVar != null ? mVar.y() : null, i10, uVar.r());
                }
                eVar = cVar;
            }
        }
        int iOrdinal2 = ((A9.h) eVar.f476m).ordinal();
        if (iOrdinal2 == 0) {
            Object obj4 = ((A9.l) eVar).f491n;
            A7.m.d(obj4, "null cannot be cast to non-null type kotlin.collections.MutableList<com.stingray.kca.model.KaraokeItem>");
            List listB = A7.E.b(obj4);
            HashSet hashSet = new HashSet();
            ArrayList arrayList9 = new ArrayList();
            for (Object obj5 : listB) {
                if (hashSet.add(((KaraokeItem) obj5).f13107a)) {
                    arrayList9.add(obj5);
                }
            }
            f10.f3618b.b(arrayList9);
            return arrayList9;
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
            A9.j jVar = (A9.j) eVar;
            String str4 = jVar.f490n;
            throw new A9.k(A7.k.k(new StringBuilder("Server error : "), jVar.o, " ", str4 != null ? str4 : ""));
        }
        A9.c cVar2 = (A9.c) eVar;
        String str5 = cVar2.f477n;
        String str6 = str5 != null ? str5 : "";
        StringBuilder sb = new StringBuilder("Client error : ");
        int i11 = cVar2.o;
        throw new A9.d(A7.k.k(sb, i11, " ", str6), i11);
    }
}
