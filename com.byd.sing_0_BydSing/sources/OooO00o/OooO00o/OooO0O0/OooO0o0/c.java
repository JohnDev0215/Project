package OooO00o.OooO00o.OooO0O0.OooO0o0;

import com.android.volley.BuildConfig;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f35a = new c();

    public final int a(String str, int i) {
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i));
            if (objInvoke != null) {
                return ((Integer) objInvoke).intValue();
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public final String b(String str) {
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
            return objInvoke != null ? objInvoke.toString() : BuildConfig.FLAVOR;
        } catch (Exception e) {
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public final String c(String str, String str2) {
        try {
            Object objInvoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
            if (objInvoke != null) {
                return objInvoke.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str2 == null ? BuildConfig.FLAVOR : str2;
    }

    public final void d(String str, String str2) {
        try {
            Class.forName("android.os.SystemProperties").getMethod("set", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
