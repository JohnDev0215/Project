package org.extra.relinker;

import android.os.Build;
import org.extra.relinker.c;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements c.b {
    @Override // org.extra.relinker.c.b
    public String a(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // org.extra.relinker.c.b
    public void b(String str) {
        System.loadLibrary(str);
    }

    @Override // org.extra.relinker.c.b
    public String c(String str) {
        return str.substring(3, str.length() - 3);
    }

    @Override // org.extra.relinker.c.b
    public void d(String str) {
        System.load(str);
    }

    @Override // org.extra.relinker.c.b
    public String[] a() {
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr.length > 0) {
                return strArr;
            }
        }
        String str = Build.CPU_ABI2;
        return !h.a(str) ? new String[]{Build.CPU_ABI, str} : new String[]{Build.CPU_ABI};
    }
}
