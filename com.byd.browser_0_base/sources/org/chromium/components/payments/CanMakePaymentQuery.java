package org.chromium.components.payments;

import defpackage.lz1;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class CanMakePaymentQuery {
    @CalledByNative
    public static String[] getMethodIdentifiers(Map<String, lz1> map) {
        return (String[]) map.keySet().toArray(new String[map.size()]);
    }

    @CalledByNative
    public static String getStringifiedMethodData(Map<String, lz1> map, String str) {
        return map.get(str).c;
    }
}
