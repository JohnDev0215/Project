package F0;

import S8.AbstractC0727y;
import T.AbstractC0764s;
import V8.AbstractC0828q;
import V8.C0819h;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import com.stingray.drivemodedetector.R;
import java.util.LinkedHashMap;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public abstract class q1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinkedHashMap f2251a = new LinkedHashMap();

    public static final V8.d0 a(Context context) {
        V8.d0 d0Var;
        LinkedHashMap linkedHashMap = f2251a;
        synchronized (linkedHashMap) {
            try {
                Object objM = linkedHashMap.get(context);
                if (objM == null) {
                    ContentResolver contentResolver = context.getContentResolver();
                    Uri uriFor = Settings.Global.getUriFor("animator_duration_scale");
                    U8.l lVarA = AbstractC2673a.a(-1, 6, null);
                    objM = AbstractC0828q.m(new C0819h(new o1(contentResolver, uriFor, new p1(lVarA, Handler.createAsync(Looper.getMainLooper())), lVarA, context, null)), AbstractC0727y.b(), V8.V.a(), Float.valueOf(Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f)));
                    linkedHashMap.put(context, objM);
                }
                d0Var = (V8.d0) objM;
            } catch (Throwable th) {
                throw th;
            }
        }
        return d0Var;
    }

    public static final AbstractC0764s b(View view) {
        Object tag = view.getTag(R.id.androidx_compose_ui_view_composition_context);
        if (tag instanceof AbstractC0764s) {
            return (AbstractC0764s) tag;
        }
        return null;
    }
}
