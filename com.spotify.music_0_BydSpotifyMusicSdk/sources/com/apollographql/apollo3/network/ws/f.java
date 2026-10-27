package com.apollographql.apollo3.network.ws;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.i;
import p.cd2;
import p.cw2;
import p.cz2;
import p.dv0;
import p.dw2;
import p.ew2;
import p.fw2;
import p.hn1;
import p.hz0;
import p.k52;
import p.m7;
import p.p61;
import p.q30;
import p.tz2;
import p.u02;
import p.ug2;
import p.v03;
import p.vr2;
import p.wc0;

/* JADX INFO: loaded from: classes.dex */
public final class f implements hn1 {
    public final String a;
    public final List b;
    public final cw2 c;
    public final long d;
    public final cz2 e;
    public final hz0 f;
    public final kotlinx.coroutines.channels.a g = q30.a(Integer.MAX_VALUE, null, 6);
    public final kotlinx.coroutines.flow.g h;
    public final u02 i;
    public final k52 j;
    public final fw2 k;

    public f(String str, ArrayList arrayList, cw2 cw2Var, long j, cz2 cz2Var, hz0 hz0Var) {
        this.a = str;
        this.b = arrayList;
        this.c = cw2Var;
        this.d = j;
        this.e = cz2Var;
        this.f = hz0Var;
        BufferOverflow bufferOverflow = BufferOverflow.c;
        tz2 tz2Var = cd2.a;
        kotlinx.coroutines.flow.g gVar = new kotlinx.coroutines.flow.g(0, Integer.MAX_VALUE, bufferOverflow);
        this.h = gVar;
        this.i = new u02(gVar);
        synchronized (gVar) {
            if (gVar.l == null) {
                int i = gVar.e;
                ug2 ug2Var = new ug2(1, Integer.MAX_VALUE, BufferOverflow.e);
                ug2Var.l(Integer.valueOf(i));
                gVar.l = ug2Var;
            }
        }
        k52 k52Var = new k52();
        this.j = k52Var;
        vr2.l(q30.b((kotlinx.coroutines.f) k52Var.b), null, null, new WebSocketNetworkTransport$1(this, null), 3);
        this.k = new fw2(this);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:89|(1:162)|90|91|166|92|93|160|94|(6:97|98|121|(0)(0)|147|(0)(0))|96|178) */
    /* JADX WARN: Can't wrap try/catch for region: R(12:89|162|90|91|166|92|93|160|94|(6:97|98|121|(0)(0)|147|(0)(0))|96|178) */
    /* JADX WARN: Can't wrap try/catch for region: R(16:(1:156)|64|65|66|67|(2:158|69)|75|(3:78|(5:177|80|85|86|(2:88|118)(12:89|162|90|91|166|92|93|160|94|(6:97|98|121|(0)(0)|147|(0)(0))|96|178))(1:81)|76)|176|82|83|164|84|85|86|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x031e, code lost:
    
        r2 = r4;
        r4 = r12;
        r25 = r5;
        r6 = r0;
        r0 = r14;
        r5 = 0;
        r14 = r13;
        r12 = r25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0329, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x032a, code lost:
    
        r5 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0350, code lost:
    
        r11 = r2;
        r10 = r4;
        r2 = r9;
        r9 = r14;
        r4 = r3;
        r3 = r1;
        r1 = r0;
        r0 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x0367, code lost:
    
        r12 = r1;
        r1 = r3;
        r3 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x036c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x036d, code lost:
    
        r5 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x023c, code lost:
    
        if (r7.isEmpty() != false) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x031d, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:158:0x0238, B:75:0x024e], limit reached: 175 */
    /* JADX WARN: Path cross not found for [B:82:0x0270, B:75:0x024e], limit reached: 175 */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x049b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:179:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0303  */
    /* JADX WARN: Type inference failed for: r0v26, types: [T, p.uf2] */
    /* JADX WARN: Type inference failed for: r0v34, types: [T, p.uf2] */
    /* JADX WARN: Type inference failed for: r8v6, types: [T, com.apollographql.apollo3.network.ws.c] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:106:0x0350 -> B:107:0x0358). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:155:0x04cc -> B:28:0x015a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x01ff -> B:28:0x015a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.coroutines.intrinsics.CoroutineSingletons b(com.apollographql.apollo3.network.ws.f r27, p.e20 r28, p.c00 r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.f.b(com.apollographql.apollo3.network.ws.f, p.e20, p.c00):kotlin.coroutines.intrinsics.CoroutineSingletons");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void c(Ref$ObjectRef ref$ObjectRef, Ref$ObjectRef ref$ObjectRef2, Ref$ObjectRef ref$ObjectRef3) {
        g gVar = (g) ref$ObjectRef.element;
        if (gVar != null) {
            gVar.a.b.b(1000, null);
        }
        ref$ObjectRef.element = null;
        p61 p61Var = (p61) ref$ObjectRef2.element;
        if (p61Var != null) {
            p61Var.i(null);
        }
        ref$ObjectRef2.element = null;
        p61 p61Var2 = (p61) ref$ObjectRef3.element;
        if (p61Var2 != null) {
            p61Var2.i(null);
        }
        ref$ObjectRef3.element = null;
    }

    @Override // p.hn1
    public final dv0 a(m7 m7Var) {
        v03.j(m7Var, "request");
        return new kotlinx.coroutines.flow.f(new ew2(com.apollographql.apollo3.internal.b.b(new dw2(new i(this.i, new WebSocketNetworkTransport$execute$1(this, m7Var, null)), m7Var), new WebSocketNetworkTransport$execute$3(m7Var, null)), m7Var, new wc0(1)), new WebSocketNetworkTransport$execute$5(this, m7Var, null));
    }
}
