package p;

import com.spotify.useraccount.v1.AccountAttribute;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zx1 {
    public static LinkedHashMap a(Map map) {
        String strValueOf;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            if (((AccountAttribute) entry.getValue()).i() != AccountAttribute.ValueCase.VALUE_NOT_SET) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(he1.B(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            Object key = entry2.getKey();
            AccountAttribute accountAttribute = (AccountAttribute) entry2.getValue();
            v03.j(accountAttribute, "<this>");
            AccountAttribute.ValueCase valueCaseI = accountAttribute.i();
            int i = valueCaseI == null ? -1 : yx1.a[valueCaseI.ordinal()];
            if (i == -1) {
                throw new IllegalStateException("valuecase is null".toString());
            }
            if (i == 1) {
                strValueOf = accountAttribute.e() ? "1" : "0";
            } else if (i == 2) {
                strValueOf = String.valueOf(accountAttribute.g());
            } else {
                if (i != 3) {
                    if (i != 4) {
                        throw new NoWhenBranchMatchedException();
                    }
                    throw new IllegalStateException("value not set".toString());
                }
                strValueOf = accountAttribute.h().toString();
            }
            linkedHashMap2.put(key, strValueOf);
        }
        return linkedHashMap2;
    }
}
