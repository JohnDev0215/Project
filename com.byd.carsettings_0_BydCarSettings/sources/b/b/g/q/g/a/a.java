package b.b.g.q.g.a;

import android.content.Context;
import android.util.TypedValue;
import java.util.Objects;

/* JADX INFO: compiled from: Utils.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f1787a = new Object();

    public static <T> T a(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static int b(Context context, int i) {
        return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
    }

    public static int c(Context context, int i) {
        return (int) TypedValue.applyDimension(2, i, context.getResources().getDisplayMetrics());
    }
}
