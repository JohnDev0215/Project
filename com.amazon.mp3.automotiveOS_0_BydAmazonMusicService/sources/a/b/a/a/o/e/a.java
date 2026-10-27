package a.b.a.a.o.e;

import android.view.View;
import com.netinsight.sye.syeClient.view.BasicSyePlayerView;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final C0022a b = new C0022a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map<View, b> f130a = Collections.synchronizedMap(new WeakHashMap());

    /* JADX INFO: renamed from: a.b.a.a.o.e.a$a, reason: collision with other inner class name */
    public static final class C0022a {
        @JvmStatic
        public final b a(View view) {
            if (view instanceof BasicSyePlayerView) {
                view = ((BasicSyePlayerView) view).getView();
            }
            return a.f130a.get(view);
        }
    }
}
