package org.chromium.components.signin.base;

import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AccountCapabilities {
    public final HashMap a;

    @CalledByNative
    public AccountCapabilities(String[] strArr, boolean[] zArr) {
        this.a = new HashMap();
        for (int i = 0; i < strArr.length; i++) {
            this.a.put(strArr[i], Boolean.valueOf(zArr[i]));
        }
    }

    @CalledByNative
    public final int getCapabilityByName(String str) {
        HashMap map = this.a;
        if (map.containsKey(str)) {
            return ((Boolean) map.get(str)).booleanValue() ? 1 : 0;
        }
        return -1;
    }

    public AccountCapabilities(HashMap<String, Boolean> map) {
        this.a = map;
    }
}
