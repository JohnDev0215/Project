package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.http.HttpMethod;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Ref$ObjectRef;
import okio.ByteString;
import p.bk0;
import p.c70;
import p.d70;
import p.dv0;
import p.f21;
import p.fo;
import p.g21;
import p.h21;
import p.hn1;
import p.i21;
import p.iu0;
import p.jo;
import p.k21;
import p.k62;
import p.l21;
import p.m21;
import p.m7;
import p.n7;
import p.o7;
import p.ou;
import p.qo;
import p.rk0;
import p.tk0;
import p.tq1;
import p.v03;
import p.z90;

/* JADX INFO: loaded from: classes.dex */
public final class c implements hn1 {
    public static final /* synthetic */ int g = 0;
    public final l21 a;
    public final f21 b;
    public final List c;
    public final boolean d;
    public final z90 e = new z90(10);
    public final i21 f = new i21(this);

    public c(d70 d70Var, f21 f21Var, ArrayList arrayList, boolean z) {
        this.a = d70Var;
        this.b = f21Var;
        this.c = arrayList;
        this.d = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object b(com.apollographql.apollo3.network.http.c r4, final p.tq1 r5, final com.apollographql.apollo3.api.c r6, final p.m21 r7, p.c00 r8) {
        /*
            r4.getClass()
            boolean r0 = r8 instanceof com.apollographql.apollo3.network.http.HttpNetworkTransport$singleResponse$1
            if (r0 == 0) goto L16
            r0 = r8
            com.apollographql.apollo3.network.http.HttpNetworkTransport$singleResponse$1 r0 = (com.apollographql.apollo3.network.http.HttpNetworkTransport$singleResponse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.apollographql.apollo3.network.http.HttpNetworkTransport$singleResponse$1 r0 = new com.apollographql.apollo3.network.http.HttpNetworkTransport$singleResponse$1
            r0.<init>(r4, r8)
        L1b:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2c
            kotlin.a.d(r8)     // Catch: java.lang.Exception -> L2a
            goto L4a
        L2a:
            r4 = move-exception
            goto L57
        L2c:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L34:
            kotlin.a.d(r8)
            p.z90 r4 = r4.e     // Catch: java.lang.Exception -> L2a
            com.apollographql.apollo3.network.http.HttpNetworkTransport$singleResponse$response$1 r8 = new com.apollographql.apollo3.network.http.HttpNetworkTransport$singleResponse$response$1     // Catch: java.lang.Exception -> L2a
            r8.<init>()     // Catch: java.lang.Exception -> L2a
            r0.label = r3     // Catch: java.lang.Exception -> L2a
            r4.getClass()     // Catch: java.lang.Exception -> L2a
            java.lang.Object r8 = r8.invoke()     // Catch: java.lang.Exception -> L2a
            if (r8 != r1) goto L4a
            goto L56
        L4a:
            p.o7 r8 = (p.o7) r8     // Catch: java.lang.Exception -> L2a
            p.n7 r4 = r8.a()
            r4.g = r3
            p.o7 r1 = r4.a()
        L56:
            return r1
        L57:
            boolean r5 = r4 instanceof com.apollographql.apollo3.exception.ApolloException
            if (r5 == 0) goto L5e
            com.apollographql.apollo3.exception.ApolloException r4 = (com.apollographql.apollo3.exception.ApolloException) r4
            goto L66
        L5e:
            com.apollographql.apollo3.exception.ApolloParseException r5 = new com.apollographql.apollo3.exception.ApolloParseException
            java.lang.String r6 = "Failed to parse GraphQL http network response"
            r5.<init>(r6, r4)
            r4 = r5
        L66:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.c.b(com.apollographql.apollo3.network.http.c, p.tq1, com.apollographql.apollo3.api.c, p.m21, p.c00):java.lang.Object");
    }

    public static final o7 c(c cVar, o7 o7Var, UUID uuid, m21 m21Var) {
        cVar.getClass();
        n7 n7VarA = o7Var.a();
        v03.j(uuid, "requestUuid");
        n7VarA.b = uuid;
        System.currentTimeMillis();
        int i = m21Var.a;
        v03.j(m21Var.b, "headers");
        n7VarA.d = n7VarA.d.d(new h21());
        return n7VarA.a();
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [T, java.util.LinkedHashMap] */
    @Override // p.hn1
    public final dv0 a(m7 m7Var) throws EOFException {
        k21 k21Var;
        v03.j(m7Var, "request");
        qo qoVar = com.apollographql.apollo3.api.c.d;
        tk0 tk0Var = m7Var.c;
        rk0 rk0VarA = tk0Var.a(qoVar);
        v03.h(rk0VarA);
        com.apollographql.apollo3.api.c cVar = (com.apollographql.apollo3.api.c) rk0VarA;
        d70 d70Var = (d70) this.a;
        d70Var.getClass();
        com.apollographql.apollo3.api.c cVar2 = (com.apollographql.apollo3.api.c) tk0Var.a(qoVar);
        if (cVar2 == null) {
            cVar2 = com.apollographql.apollo3.api.c.e;
        }
        tq1 tq1Var = m7Var.a;
        List listC = v03.C(new g21("X-APOLLO-OPERATION-ID", tq1Var.c()), new g21("X-APOLLO-OPERATION-NAME", tq1Var.name()));
        List list = m7Var.e;
        if (list == null) {
            list = EmptyList.c;
        }
        ArrayList arrayListK0 = ou.k0(list, listC);
        Boolean bool = m7Var.f;
        boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
        Boolean bool2 = m7Var.g;
        boolean zBooleanValue2 = bool2 != null ? bool2.booleanValue() : true;
        HttpMethod httpMethod = HttpMethod.e;
        HttpMethod httpMethod2 = m7Var.d;
        if (httpMethod2 == null) {
            httpMethod2 = httpMethod;
        }
        int iOrdinal = httpMethod2.ordinal();
        String str = d70Var.a;
        if (iOrdinal == 0) {
            HttpMethod httpMethod3 = HttpMethod.c;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("operationName", tq1Var.name());
            fo foVar = new fo();
            iu0 iu0Var = new iu0(new jo(foVar));
            iu0Var.f();
            tq1Var.a(iu0Var, cVar2);
            iu0Var.k();
            if (!iu0Var.e.isEmpty()) {
                throw new IllegalStateException("FileUpload and Http GET are not supported at the same time".toString());
            }
            linkedHashMap.put("variables", foVar.Q0());
            if (zBooleanValue2) {
                linkedHashMap.put("query", tq1Var.d());
            }
            if (zBooleanValue) {
                fo foVar2 = new fo();
                jo joVar = new jo(foVar2);
                joVar.f();
                joVar.l0("persistedQuery");
                joVar.f();
                joVar.l0("version");
                joVar.A(1);
                joVar.l0("sha256Hash");
                joVar.N(tq1Var.c());
                joVar.k();
                joVar.k();
                linkedHashMap.put("extensions", foVar2.Q0());
            }
            v03.j(str, "<this>");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            boolean zI0 = kotlin.text.b.i0(str, "?");
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                if (zI0) {
                    sb.append('&');
                } else {
                    sb.append('?');
                    zI0 = true;
                }
                sb.append(bk0.V((String) entry.getKey()));
                sb.append('=');
                sb.append(bk0.V((String) entry.getValue()));
            }
            String string = sb.toString();
            v03.i(string, "StringBuilder().apply(builderAction).toString()");
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(arrayListK0);
            k21Var = new k21(httpMethod3, string, arrayList, null);
        } else {
            if (iOrdinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            String strD = zBooleanValue2 ? tq1Var.d() : null;
            v03.j(str, "url");
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(arrayListK0);
            v03.j(cVar2, "customScalarAdapters");
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            fo foVar3 = new fo();
            jo joVar2 = new jo(foVar3);
            int i = d70.b;
            ref$ObjectRef.element = z90.h(joVar2, tq1Var, cVar2, zBooleanValue, strD);
            ByteString byteStringV = foVar3.v(foVar3.e);
            k21Var = new k21(httpMethod, str, arrayList2, ((Map) ref$ObjectRef.element).isEmpty() ? new c70(byteStringV) : new com.apollographql.apollo3.api.http.a(ref$ObjectRef, byteStringV));
        }
        return new k62(new HttpNetworkTransport$execute$1(this, k21Var, m7Var, cVar, null));
    }
}
