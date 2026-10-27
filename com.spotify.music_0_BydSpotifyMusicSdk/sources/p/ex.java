package p;

import com.spotify.rcs.resolver.grpc.v0.Context;
import com.spotify.rcs.resolver.grpc.v0.Fetch;
import com.spotify.rcs.resolver.grpc.v0.ResolveRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ex {
    public static ResolveRequest a(xa xaVar, lw1 lw1Var) {
        v03.j(xaVar, "appMetadata");
        v03.j(lw1Var, "policyInputs");
        Set setKeySet = lw1Var.b().keySet();
        if ((setKeySet instanceof Collection) && setKeySet.isEmpty()) {
            qk2.a().getClass();
            hk0.B(new Object[0]);
            lw1Var.a(new ft("4.4.1.0"));
        } else {
            Iterator it = setKeySet.iterator();
            while (it.hasNext()) {
                if (v03.b((String) it.next(), "/remote-config/rc-client-version")) {
                    break;
                }
            }
            qk2.a().getClass();
            hk0.B(new Object[0]);
            lw1Var.a(new ft("4.4.1.0"));
        }
        TreeMap treeMapB = lw1Var.b();
        ArrayList arrayList = new ArrayList(treeMapB.size());
        for (Map.Entry entry : treeMapB.entrySet()) {
            com.spotify.rcs.resolver.grpc.v0.h hVarG = Context.ContextEntry.g();
            hVarG.d((String) entry.getKey());
            hVarG.e((String) entry.getValue());
            arrayList.add((Context.ContextEntry) hVarG.build());
        }
        y32 y32VarH = ResolveRequest.h();
        y32VarH.f();
        com.spotify.rcs.resolver.grpc.v0.i iVarF = Fetch.f();
        iVarF.d(Fetch.Type.ASYNC);
        com.google.protobuf.i iVarBuild = iVarF.build();
        v03.i(iVarBuild, "newBuilder().also {\n    …D\n        }\n    }.build()");
        y32VarH.e((Fetch) iVarBuild);
        com.spotify.rcs.resolver.grpc.v0.g gVarF = Context.f();
        gVarF.d(arrayList);
        y32VarH.d(gVarF);
        com.google.protobuf.i iVarBuild2 = y32VarH.build();
        v03.i(iVarBuild2, "newBuilder()\n           …   )\n            .build()");
        return (ResolveRequest) iVarBuild2;
    }
}
