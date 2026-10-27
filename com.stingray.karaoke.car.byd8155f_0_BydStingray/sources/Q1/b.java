package Q1;

import A7.m;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final boolean a(Context context, String str) {
        m.f(context, "context");
        m.f(str, "name");
        return context.deleteSharedPreferences(str);
    }
}
