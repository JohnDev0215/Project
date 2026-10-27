package q2;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

/* JADX INFO: compiled from: CommonUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(Context context, int i8) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(i8, typedValue, true);
            return typedValue.resourceId;
        } catch (Exception e8) {
            e8.printStackTrace();
            Log.e("getAttribute", "getAttribute,error:" + e8.getMessage());
            return 0;
        }
    }
}
