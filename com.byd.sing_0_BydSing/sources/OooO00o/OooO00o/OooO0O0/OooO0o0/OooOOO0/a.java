package OooO00o.OooO00o.OooO0O0.OooO0o0.OooOOO0;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooOOO0.OooO00o;
import android.content.SharedPreferences;
import com.android.volley.BuildConfig;
import com.loostone.libbottom.util.JniUtil;
import com.loostone.libtuning.component.provider.TuningProvider;
import java.util.Map;
import kotlin.jvm.internal.h;
import kotlin.text.o;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    private final String storeName;

    public a(String str) {
        h.d(str, "storeName");
        this.storeName = str;
    }

    public static /* synthetic */ OooO00o booleanPref$default(a aVar, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: booleanPref");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return aVar.booleanPref(str, z);
    }

    public static /* synthetic */ OooO00o floatPref$default(a aVar, String str, float f, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: floatPref");
        }
        if ((i & 2) != 0) {
            f = 0.0f;
        }
        return aVar.floatPref(str, f);
    }

    private final String generateEncKey(String str) {
        return o.l(str, "aef", false, 2, null) ? JniUtil.f3799OooO00o.encryption(o.j(str, "_", "8", false, 4, null), str.length()) : str;
    }

    public static /* synthetic */ OooO00o intPref$default(a aVar, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: intPref");
        }
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return aVar.intPref(str, i);
    }

    public static /* synthetic */ OooO00o stringPref$default(a aVar, String str, String str2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: stringPref");
        }
        if ((i & 2) != 0) {
            str2 = BuildConfig.FLAVOR;
        }
        return aVar.stringPref(str, str2);
    }

    public final OooO00o<Boolean> booleanPref(String str, boolean z) {
        h.d(str, TuningProvider.KEY);
        return new OooO00o<>(this.storeName, generateEncKey(str), Boolean.valueOf(z), OooO00o.OooO0O0.T_BOOLEAN);
    }

    public final void encryptPrefs(String str) {
        h.d(str, "oldStoreName");
        OooO00o.OooO00o.OooO0O0.a aVar = OooO00o.OooO00o.OooO0O0.a.f39a;
        SharedPreferences sharedPreferences = aVar.a().getSharedPreferences(str, 0);
        Map<String, ?> all = sharedPreferences.getAll();
        OooO00o.OooO00o.OooO0O0.b.f41a.e("PrfModel", h.i("oldSharedPrefs size = ", all == null ? null : Integer.valueOf(all.size())));
        if (all != null) {
            SharedPreferences sharedPreferences2 = aVar.a().getSharedPreferences(this.storeName, 0);
            for (String str2 : all.keySet()) {
                Object obj = all.get(str2);
                if (obj instanceof Integer) {
                    SharedPreferences.Editor editorEdit = sharedPreferences2.edit();
                    h.c(str2, TuningProvider.KEY);
                    editorEdit.putInt(generateEncKey(str2), ((Number) obj).intValue()).apply();
                } else if (obj instanceof Long) {
                    SharedPreferences.Editor editorEdit2 = sharedPreferences2.edit();
                    h.c(str2, TuningProvider.KEY);
                    editorEdit2.putLong(generateEncKey(str2), ((Number) obj).longValue()).apply();
                } else if (obj instanceof Float) {
                    SharedPreferences.Editor editorEdit3 = sharedPreferences2.edit();
                    h.c(str2, TuningProvider.KEY);
                    editorEdit3.putFloat(generateEncKey(str2), ((Number) obj).floatValue()).apply();
                } else if (obj instanceof String) {
                    SharedPreferences.Editor editorEdit4 = sharedPreferences2.edit();
                    h.c(str2, TuningProvider.KEY);
                    editorEdit4.putString(generateEncKey(str2), (String) obj).apply();
                } else if (obj instanceof Boolean) {
                    SharedPreferences.Editor editorEdit5 = sharedPreferences2.edit();
                    h.c(str2, TuningProvider.KEY);
                    editorEdit5.putBoolean(generateEncKey(str2), ((Boolean) obj).booleanValue()).apply();
                }
            }
        }
        sharedPreferences.edit().clear().apply();
    }

    public final OooO00o<Float> floatPref(String str, float f) {
        h.d(str, TuningProvider.KEY);
        return new OooO00o<>(this.storeName, generateEncKey(str), Float.valueOf(f), OooO00o.OooO0O0.T_FLOAT);
    }

    public final OooO00o<Integer> intPref(String str, int i) {
        h.d(str, TuningProvider.KEY);
        return new OooO00o<>(this.storeName, generateEncKey(str), Integer.valueOf(i), OooO00o.OooO0O0.T_INT);
    }

    public final OooO00o<Long> longPref(String str, long j) {
        h.d(str, TuningProvider.KEY);
        return new OooO00o<>(this.storeName, generateEncKey(str), Long.valueOf(j), OooO00o.OooO0O0.T_LONG);
    }

    public final OooO00o<String> stringPref(String str, String str2) {
        h.d(str, TuningProvider.KEY);
        h.d(str2, "defaultValue");
        return new OooO00o<>(this.storeName, generateEncKey(str), str2, OooO00o.OooO0O0.T_STRING);
    }
}
