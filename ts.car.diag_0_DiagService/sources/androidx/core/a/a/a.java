package androidx.core.a.a;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final WeakHashMap<Context, a> f408a = new WeakHashMap<>();

    private a(Context context) {
    }

    @NonNull
    public static a a(@NonNull Context context) {
        a aVar;
        synchronized (f408a) {
            aVar = f408a.get(context);
            if (aVar == null) {
                aVar = new a(context);
                f408a.put(context, aVar);
            }
        }
        return aVar;
    }
}
