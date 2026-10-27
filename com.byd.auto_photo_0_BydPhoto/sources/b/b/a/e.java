package b.b.a;

import android.annotation.SuppressLint;
import android.os.Build;
import b.b.a.c;

/* JADX INFO: loaded from: classes2.dex */
final class e implements c.b {
    e() {
    }

    @Override // b.b.a.c.b
    public String a(String str) {
        return (str.startsWith("lib") && str.endsWith(".so")) ? str : System.mapLibraryName(str);
    }

    @Override // b.b.a.c.b
    public String b(String str) {
        return str.substring(3, str.length() - 3);
    }

    @Override // b.b.a.c.b
    public void c(String str) {
        System.loadLibrary(str);
    }

    @Override // b.b.a.c.b
    public String[] d() {
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = Build.SUPPORTED_ABIS;
            if (strArr.length > 0) {
                return strArr;
            }
        }
        String str = Build.CPU_ABI2;
        return !f.a(str) ? new String[]{Build.CPU_ABI, str} : new String[]{Build.CPU_ABI};
    }

    @Override // b.b.a.c.b
    @SuppressLint({"UnsafeDynamicallyLoadedCode"})
    public void e(String str) {
        System.load(str);
    }
}
