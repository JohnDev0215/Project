package b.c.a.g;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements d {
    @Override // b.c.a.g.d
    public int a(Context context, int i) {
        return g(context, i, "array");
    }

    @Override // b.c.a.g.d
    public int b(Context context, int i) {
        return g(context, i, "drawable");
    }

    @Override // b.c.a.g.d
    public int c(Context context, int i) {
        return g(context, i, "string");
    }

    @Override // b.c.a.g.d
    public int d(Context context, int i) {
        try {
            return g(context, i, context.getResources().getResourceTypeName(i));
        } catch (Resources.NotFoundException e2) {
            Log.e("AbsUiStyleApi", e2.getMessage(), e2);
            return i;
        }
    }

    @Override // b.c.a.g.d
    public int e(Context context, int i) {
        return g(context, i, "layout");
    }

    @Override // b.c.a.g.d
    public int f(Context context, int i) {
        return g(context, i, "color");
    }

    public final int g(Context context, int i, String str) {
        int identifier;
        Resources resources = context.getResources();
        try {
            identifier = resources.getIdentifier(h() + resources.getResourceEntryName(i), str, context.getPackageName());
        } catch (Resources.NotFoundException e2) {
            Log.e("AbsUiStyleApi", e2.getMessage(), e2);
        }
        return identifier != 0 ? identifier : i;
    }

    public abstract String h();
}
