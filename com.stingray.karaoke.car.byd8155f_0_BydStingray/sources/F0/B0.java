package F0;

import S0.InterfaceC0701m;
import androidx.compose.ui.platform.AndroidComposeView;
import java.lang.reflect.Method;
import q7.InterfaceC2193g;

/* JADX INFO: loaded from: classes.dex */
public final class B0 implements InterfaceC2193g, InterfaceC0701m {
    public static final /* synthetic */ B0 l = new B0();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final f1 f2008m = new f1();

    public static final boolean a() {
        Class cls = AndroidComposeView.f11616K0;
        try {
            if (AndroidComposeView.f11616K0 == null) {
                Class<?> cls2 = Class.forName("android.os.SystemProperties");
                AndroidComposeView.f11616K0 = cls2;
                AndroidComposeView.f11617L0 = cls2.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
            }
            Method method = AndroidComposeView.f11617L0;
            Object objInvoke = method != null ? method.invoke(null, "debug.layout", Boolean.FALSE) : null;
            Boolean bool = objInvoke instanceof Boolean ? (Boolean) objInvoke : null;
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
