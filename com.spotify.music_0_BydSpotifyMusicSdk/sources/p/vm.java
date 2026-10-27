package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.bootstrap.v1.proto.Bootstrap$BootstrapResponse;
import com.spotify.bootstrap.v1.proto.Bootstrap$UcsResponseWrapperV0;
import com.spotify.ucs.v0.proto.Ucs$UcsResponseWrapper;
import com.spotify.useraccount.v1.AccountAttribute;
import com.spotify.useraccount.v1.proto.Useraccount$AccountAttribute;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Result;

/* JADX INFO: loaded from: classes.dex */
public final class vm implements io.reactivex.rxjava3.functions.o {
    public static final vm c = new vm();

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        Bootstrap$BootstrapResponse bootstrap$BootstrapResponse;
        byte[] byteArray;
        Object objB;
        u42 u42Var = (u42) obj;
        if (u42Var.a.i() && (bootstrap$BootstrapResponse = (Bootstrap$BootstrapResponse) u42Var.b) != null) {
            if (bootstrap$BootstrapResponse.e() == Bootstrap$BootstrapResponse.UcsResponseCase.UCS_RESPONSE_V0 && bootstrap$BootstrapResponse.f().g() == Bootstrap$UcsResponseWrapperV0.ResultCase.SUCCESS) {
                Bootstrap$UcsResponseWrapperV0.UcsResponseWrapperSuccess ucsResponseWrapperSuccessH = bootstrap$BootstrapResponse.f().h();
                if (ucsResponseWrapperSuccessH.g() && ucsResponseWrapperSuccessH.e().hasSuccess()) {
                    Ucs$UcsResponseWrapper.UcsResponse ucsResponseG = ucsResponseWrapperSuccessH.e().g();
                    if (ucsResponseG.h()) {
                        if (ucsResponseG.i()) {
                            byteArray = ucsResponseG.g().f().toByteArray();
                            v03.i(byteArray, "{\n                    uc…Array()\n                }");
                        } else {
                            byteArray = new byte[0];
                        }
                        Map mapE = ucsResponseG.e().e();
                        v03.i(mapE, "ucsResponse.accountAttri…cess.accountAttributesMap");
                        LinkedHashMap linkedHashMap = new LinkedHashMap(he1.B(mapE.size()));
                        for (Map.Entry entry : mapE.entrySet()) {
                            Object key = entry.getKey();
                            try {
                                objB = AccountAttribute.j(((Useraccount$AccountAttribute) entry.getValue()).toByteArray());
                            } catch (Throwable th) {
                                objB = kotlin.a.b(th);
                            }
                            if (Result.a(objB) != null) {
                                Logger.g("Unable to migrate Bootstrap AccountAttribute to RC", new Object[0]);
                                objB = AccountAttribute.f();
                            }
                            linkedHashMap.put(key, (AccountAttribute) objB);
                        }
                        LinkedHashMap linkedHashMapA = zx1.a(linkedHashMap);
                        byte[] byteArray2 = ucsResponseG.e().toByteArray();
                        v03.i(byteArray2, "ucsResponse.accountAttributesSuccess.toByteArray()");
                        return new xm(byteArray, linkedHashMapA, byteArray2);
                    }
                    if (ucsResponseG.i()) {
                        byte[] byteArray3 = ucsResponseG.g().f().toByteArray();
                        v03.i(byteArray3, "config.toByteArray()");
                        return new wm(byteArray3);
                    }
                }
                Logger.g("No successful customization was delivered via bootstrap.", new Object[0]);
            }
            Logger.b("Could not parse to any bootstrap data result", new Object[0]);
            return new tm();
        }
        return new tm();
    }
}
