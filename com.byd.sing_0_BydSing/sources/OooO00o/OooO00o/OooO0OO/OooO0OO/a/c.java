package OooO00o.OooO00o.OooO0Oo.OooO0Oo.a;

import android.content.Context;
import com.blankj.utilcode.util.j;
import com.loostone.libtuning.component.provider.TuningProvider;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f119a;

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

    public c(Context context) {
        h.d(context, "context");
        this.f119a = context;
    }

    public static /* synthetic */ String d(c cVar, String str, String str2, String str3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getValue");
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        return cVar.e(str, str2, str3);
    }

    public final String e(String str, String str2, String str3) {
        h.d(str, "startKey");
        h.d(str2, "endKey");
        String strA = OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c.a(this.f119a, h.i(str, str2));
        return strA == null ? str3 : strA;
    }

    public final JSONObject f(Object obj) {
        JSONObject jSONObject;
        h.d(obj, "obj");
        try {
            jSONObject = new JSONObject(j.i(obj));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        return jSONObject;
    }

    public final Context g() {
        return this.f119a;
    }

    public final void h(String str, String str2, String str3) {
        h.d(str, "startKey");
        h.d(str2, "endKey");
        h.d(str3, TuningProvider.VALUE);
        OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c.c(this.f119a, h.i(str, str2), str3);
    }

    public /* synthetic */ c(Context context, int i) {
        this((i & 1) != 0 ? OooO00o.OooO00o.OooO0O0.a.f39a.a() : context);
    }
}
