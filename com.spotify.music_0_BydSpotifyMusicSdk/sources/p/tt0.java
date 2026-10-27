package p;

import com.spotify.remoteconfig.internal.ProductStateProto;
import com.spotify.ucs.proto.v0.UcsResponseWrapper;
import com.spotify.useraccount.v1.AccountAttribute;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class tt0 implements Callable {
    public final /* synthetic */ ut0 c;
    public final /* synthetic */ UcsResponseWrapper.UcsResponse e;

    public tt0(ut0 ut0Var, UcsResponseWrapper.UcsResponse ucsResponse) {
        this.c = ut0Var;
        this.e = ucsResponse;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        com.google.protobuf.i iVarBuild;
        ut0 ut0Var = this.c;
        Map mapE = this.e.g().e();
        v03.i(mapE, "this\n            .accoun…    .accountAttributesMap");
        ut0Var.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap(he1.B(mapE.size()));
        for (Map.Entry entry : mapE.entrySet()) {
            Object key = entry.getKey();
            AccountAttribute.ValueCase valueCaseI = ((AccountAttribute) entry.getValue()).i();
            int i = valueCaseI == null ? -1 : rt0.a[valueCaseI.ordinal()];
            if (i == 1) {
                q3 q3VarI = com.spotify.remoteconfig.internal.AccountAttribute.i();
                q3VarI.d(((AccountAttribute) entry.getValue()).e());
                iVarBuild = q3VarI.build();
            } else if (i == 2) {
                q3 q3VarI2 = com.spotify.remoteconfig.internal.AccountAttribute.i();
                q3VarI2.e(((AccountAttribute) entry.getValue()).g());
                iVarBuild = q3VarI2.build();
            } else if (i != 3) {
                iVarBuild = com.spotify.remoteconfig.internal.AccountAttribute.i().build();
            } else {
                q3 q3VarI3 = com.spotify.remoteconfig.internal.AccountAttribute.i();
                q3VarI3.f(((AccountAttribute) entry.getValue()).h());
                iVarBuild = q3VarI3.build();
            }
            linkedHashMap.put(key, (com.spotify.remoteconfig.internal.AccountAttribute) iVarBuild);
        }
        ay1 ay1VarF = ProductStateProto.f();
        ay1VarF.d(linkedHashMap);
        ProductStateProto productStateProto = (ProductStateProto) ay1VarF.build();
        v03.i(productStateProto, "productStateProto");
        qk2.a().getClass();
        hk0.t(new Object[0]);
        eg2 eg2Var = (eg2) this.c.a;
        eg2Var.getClass();
        ey1 ey1Var = eg2Var.e;
        synchronized (ey1Var) {
            hk0 hk0VarA = qk2.a();
            Objects.toString(ey1Var.a);
            hk0VarA.getClass();
            hk0.A(new Object[0]);
            byte[] byteArray = productStateProto.toByteArray();
            v03.i(byteArray, "data.toByteArray()");
            ey1Var.a(byteArray);
        }
        return lq2.a;
    }
}
