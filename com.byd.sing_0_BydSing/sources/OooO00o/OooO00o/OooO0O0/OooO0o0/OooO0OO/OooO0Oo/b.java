package OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo;

import android.util.Log;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.t;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    static {
        new a(null);
    }

    public static final void b(b bVar, String str, int i) {
        h.d(bVar, "this$0");
        h.d(str, "$node");
        bVar.f(str, String.valueOf(i));
    }

    public final String a(String str) {
        t.a aVarA = t.a(h.i("cat /sys/class/pmkaraoke/", str), false, true);
        h.c(aVarA, "execCmd(\n                \"cat ${Constant.KO_PATH}$node\",\n                false, true\n            )");
        if (aVarA.f2619a == 0 && !h.a(aVarA.f2620b, BuildConfig.FLAVOR)) {
            return OooO00o.OooO00o.OooO0O0.OooO0o0.b.d(aVarA.f2620b);
        }
        if (h.a(aVarA.f2621c, BuildConfig.FLAVOR)) {
            return null;
        }
        Log.d("DNodeCommunication", h.i("shell get:", aVarA.f2621c));
        return null;
    }

    public final void c(final String str, final int i) {
        h.d(str, "node");
        ThreadUtils.p().execute(new Runnable() { // from class: OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0Oo.a
            @Override // java.lang.Runnable
            public final void run() {
                b.b(this.f547b, str, i);
            }
        });
    }

    public final boolean d(String str, String str2) {
        String str3;
        t.a aVarA = t.a("echo " + str2 + " > /sys/class/pmkaraoke/" + str, false, true);
        h.c(aVarA, "execCmd(\n                \"echo $value > ${Constant.KO_PATH}$node\",\n                false, true\n            )");
        if (aVarA.f2619a != 0 && (str3 = aVarA.f2621c) != null) {
            Log.d("DNodeCommunication", h.i("shell set:", str3));
        }
        return aVarA.f2619a == 0;
    }

    public final String e(String str) {
        h.d(str, "node");
        String strA = a(str);
        return strA == null ? BuildConfig.FLAVOR : strA;
    }

    public final void f(String str, String str2) {
        d(str, str2);
    }
}
