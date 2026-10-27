package com.apollographql.apollo3.network.ws;

import com.spotify.connectivity.productstate.RxProductState;
import java.util.Map;
import p.e01;
import p.e80;
import p.fw2;
import p.ry0;
import p.v03;
import p.vq1;
import p.wq1;
import p.xq1;

/* JADX INFO: loaded from: classes.dex */
public final class c extends g {
    public final long c;
    public final ry0 d;
    public final WsFrameType e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(e80 e80Var, fw2 fw2Var, long j, ry0 ry0Var, WsFrameType wsFrameType) {
        super(e80Var, fw2Var);
        v03.j(ry0Var, "connectionPayload");
        v03.j(wsFrameType, "frameType");
        this.c = j;
        this.d = ry0Var;
        this.e = wsFrameType;
    }

    @Override // com.apollographql.apollo3.network.ws.g
    public final void a(Map map) {
        v03.j(map, "messageMap");
        Object obj = map.get(RxProductState.Keys.KEY_TYPE);
        boolean zB = v03.b(obj, "data");
        fw2 fw2Var = this.b;
        if (zB) {
            Object obj2 = map.get("id");
            if (obj2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
            String str = (String) obj2;
            Object obj3 = map.get("payload");
            if (obj3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
            }
            fw2Var.getClass();
            fw2Var.a.g.n(new xq1(str, (Map) obj3));
            return;
        }
        if (!v03.b(obj, "error")) {
            if (v03.b(obj, "complete")) {
                Object obj4 = map.get("id");
                if (obj4 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                fw2Var.getClass();
                fw2Var.a.g.n(new vq1((String) obj4));
                return;
            }
            return;
        }
        Object obj5 = map.get("id");
        if (!(obj5 instanceof String)) {
            fw2Var.a.g.n(new e01((Map) map.get("payload")));
            return;
        }
        String str2 = (String) obj5;
        Map map2 = (Map) map.get("payload");
        fw2Var.getClass();
        v03.j(str2, "id");
        fw2Var.a.g.n(new wq1(str2, map2));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(p.c00 r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.c.e(p.c00):java.lang.Object");
    }
}
