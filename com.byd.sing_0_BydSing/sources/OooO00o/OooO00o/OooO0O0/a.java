package OooO00o.OooO00o.OooO0O0;

import android.app.Application;
import com.blankj.utilcode.util.w;
import com.loostone.libbottom.util.JniUtil;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f39a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Application f40b;

    static {
        JniUtil.f3799OooO00o.init();
    }

    public final Application a() {
        Application application = f40b;
        if (application != null) {
            return application;
        }
        Application applicationA = w.a();
        h.c(applicationA, "getApp()");
        return applicationA;
    }

    public final void b(Application application) {
        h.d(application, "app");
        f40b = application;
    }
}
