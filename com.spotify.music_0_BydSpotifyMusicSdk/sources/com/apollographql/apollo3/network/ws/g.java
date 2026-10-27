package com.apollographql.apollo3.network.ws;

import java.io.EOFException;
import java.util.Map;
import okio.ByteString;
import p.e80;
import p.f60;
import p.fo;
import p.fw2;
import p.jo;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class g {
    public final e80 a;
    public final fw2 b;

    public g(e80 e80Var, fw2 fw2Var) {
        this.a = e80Var;
        this.b = fw2Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x004b -> B:25:0x004c). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object c(com.apollographql.apollo3.network.ws.g r4, p.c00 r5) {
        /*
            boolean r0 = r5 instanceof com.apollographql.apollo3.network.ws.WsProtocol$run$1
            if (r0 == 0) goto L13
            r0 = r5
            com.apollographql.apollo3.network.ws.WsProtocol$run$1 r0 = (com.apollographql.apollo3.network.ws.WsProtocol$run$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.apollographql.apollo3.network.ws.WsProtocol$run$1 r0 = new com.apollographql.apollo3.network.ws.WsProtocol$run$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r4 = r0.L$1
            com.apollographql.apollo3.network.ws.g r4 = (com.apollographql.apollo3.network.ws.g) r4
            java.lang.Object r2 = r0.L$0
            com.apollographql.apollo3.network.ws.g r2 = (com.apollographql.apollo3.network.ws.g) r2
            kotlin.a.d(r5)     // Catch: java.lang.Exception -> L2f java.util.concurrent.CancellationException -> L31
            goto L4c
        L2f:
            r4 = move-exception
            goto L56
        L31:
            r4 = move-exception
            goto L6a
        L33:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L3b:
            kotlin.a.d(r5)
        L3e:
            r0.L$0 = r4     // Catch: java.util.concurrent.CancellationException -> L31 java.lang.Exception -> L53
            r0.L$1 = r4     // Catch: java.util.concurrent.CancellationException -> L31 java.lang.Exception -> L53
            r0.label = r3     // Catch: java.util.concurrent.CancellationException -> L31 java.lang.Exception -> L53
            java.lang.Object r5 = r4.b(r0)     // Catch: java.util.concurrent.CancellationException -> L31 java.lang.Exception -> L53
            if (r5 != r1) goto L4b
            return r1
        L4b:
            r2 = r4
        L4c:
            java.util.Map r5 = (java.util.Map) r5     // Catch: java.lang.Exception -> L2f java.util.concurrent.CancellationException -> L31
            r4.a(r5)     // Catch: java.lang.Exception -> L2f java.util.concurrent.CancellationException -> L31
            r4 = r2
            goto L3e
        L53:
            r5 = move-exception
            r2 = r4
            r4 = r5
        L56:
            p.fw2 r5 = r2.b
            r5.getClass()
            com.apollographql.apollo3.network.ws.f r5 = r5.a
            kotlinx.coroutines.channels.a r5 = r5.g
            p.xm1 r0 = new p.xm1
            r0.<init>(r4)
            r5.n(r0)
            p.lq2 r4 = p.lq2.a
            return r4
        L6a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.g.c(com.apollographql.apollo3.network.ws.g, p.c00):java.lang.Object");
    }

    public abstract void a(Map map);

    /* JADX WARN: Removed duplicated region for block: B:17:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0073 A[Catch: Exception -> 0x0076, TRY_LEAVE, TryCatch #0 {Exception -> 0x0076, blocks: (B:20:0x005a, B:22:0x0073), top: B:28:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0078 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x004e -> B:19:0x004f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(p.c00 r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1
            if (r0 == 0) goto L13
            r0 = r9
            com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1 r0 = (com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1 r0 = new com.apollographql.apollo3.network.ws.WsProtocol$receiveMessageMap$1
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L37
            if (r2 != r3) goto L2f
            java.lang.Object r2 = r0.L$1
            com.apollographql.apollo3.network.ws.g r2 = (com.apollographql.apollo3.network.ws.g) r2
            java.lang.Object r4 = r0.L$0
            com.apollographql.apollo3.network.ws.g r4 = (com.apollographql.apollo3.network.ws.g) r4
            kotlin.a.d(r9)
            goto L4f
        L2f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L37:
            kotlin.a.d(r9)
            r2 = r8
        L3b:
            p.e80 r9 = r2.a
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            p.fr r9 = r9.a
            p.yq r9 = r9.c
            java.lang.Object r9 = r9.g(r0)
            if (r9 != r1) goto L4e
            return r1
        L4e:
            r4 = r2
        L4f:
            java.lang.String r9 = (java.lang.String) r9
            r2.getClass()
            java.lang.String r2 = "<this>"
            p.v03.j(r9, r2)
            r2 = 0
            p.w1 r5 = p.c5.d     // Catch: java.lang.Exception -> L76
            p.lo r6 = new p.lo     // Catch: java.lang.Exception -> L76
            p.fo r7 = new p.fo     // Catch: java.lang.Exception -> L76
            r7.<init>()     // Catch: java.lang.Exception -> L76
            r7.d1(r9)     // Catch: java.lang.Exception -> L76
            r6.<init>(r7)     // Catch: java.lang.Exception -> L76
            com.apollographql.apollo3.api.c r9 = com.apollographql.apollo3.api.c.e     // Catch: java.lang.Exception -> L76
            java.lang.Object r9 = r5.a(r6, r9)     // Catch: java.lang.Exception -> L76
            boolean r5 = r9 instanceof java.util.Map     // Catch: java.lang.Exception -> L76
            if (r5 == 0) goto L76
            java.util.Map r9 = (java.util.Map) r9     // Catch: java.lang.Exception -> L76
            r2 = r9
        L76:
            if (r2 == 0) goto L79
            return r2
        L79:
            r2 = r4
            goto L3b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.g.b(p.c00):java.lang.Object");
    }

    public final void d(Map map, WsFrameType wsFrameType) throws EOFException {
        v03.j(map, "messageMap");
        v03.j(wsFrameType, "frameType");
        int iOrdinal = wsFrameType.ordinal();
        e80 e80Var = this.a;
        if (iOrdinal == 0) {
            fo foVar = new fo();
            f60.U(new jo(foVar), map);
            String strQ0 = foVar.Q0();
            e80Var.getClass();
            if (e80Var.b.c(strQ0)) {
                return;
            }
            e80Var.a.h(null);
            return;
        }
        if (iOrdinal != 1) {
            return;
        }
        fo foVar2 = new fo();
        f60.U(new jo(foVar2), map);
        ByteString byteStringV = foVar2.v(foVar2.e);
        e80Var.getClass();
        v03.j(byteStringV, "data");
        if (e80Var.b.a(byteStringV)) {
            return;
        }
        e80Var.a.h(null);
    }
}
