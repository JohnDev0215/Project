package com.bugsnag.android;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class StrictModeHandler {

    @SuppressLint({"UseSparseArrays"})
    private static final Map<Integer, String> POLICY_CODE_MAP;

    StrictModeHandler() {
    }

    static {
        HashMap map = new HashMap();
        POLICY_CODE_MAP = map;
        map.put(1, "DiskWrite");
        map.put(2, "DiskRead");
        map.put(4, "NetworkOperation");
        map.put(8, "CustomSlowCall");
        map.put(16, "ResourceMismatch");
        map.put(256, "CursorLeak");
        map.put(512, "CloseableLeak");
        map.put(1024, "ActivityLeak");
        map.put(2048, "InstanceLeak");
        map.put(4096, "RegistrationLeak");
        map.put(8192, "FileUriLeak");
        map.put(16384, "CleartextNetwork");
    }

    boolean isStrictModeThrowable(Throwable th) {
        return getRootCause(th).getClass().getName().toLowerCase(Locale.US).startsWith("android.os.strictmode");
    }

    @Nullable
    String getViolationDescription(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        int iLastIndexOf = str.lastIndexOf("violation=");
        if (iLastIndexOf == -1) {
            return null;
        }
        String strReplace = str.substring(iLastIndexOf).replace("violation=", "");
        if (!TextUtils.isDigitsOnly(strReplace)) {
            return null;
        }
        return POLICY_CODE_MAP.get(Integer.valueOf(strReplace));
    }

    private Throwable getRootCause(Throwable th) {
        return ThrowableUtils.safeUnrollCauses(th).get(r2.size() - 1);
    }
}
