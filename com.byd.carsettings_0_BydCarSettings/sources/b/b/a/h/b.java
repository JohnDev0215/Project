package b.b.a.h;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

/* JADX INFO: compiled from: CommonUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int a(Context context, int i) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(i, typedValue, true);
            return typedValue.resourceId;
        } catch (Exception e2) {
            Log.e("getAttribute", "getAttribute,error:" + e2.getMessage());
            return 0;
        }
    }
}
