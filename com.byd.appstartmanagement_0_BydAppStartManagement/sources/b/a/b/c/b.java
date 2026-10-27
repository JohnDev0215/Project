package b.a.b.c;

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
        } catch (Exception e) {
            Log.e("getAttribute", "getAttribute,error:" + e.getMessage());
            return 0;
        }
    }
}
