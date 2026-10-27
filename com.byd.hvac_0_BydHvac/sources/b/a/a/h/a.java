package b.a.a.h;

import android.content.Context;
import android.os.Message;
import androidx.core.view.InputDeviceCompat;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static j f235a;

    public static boolean a(int i) {
        j jVar = f235a;
        if (jVar != null) {
            return jVar.hasMessages(i);
        }
        return false;
    }

    public static void b(Context context) {
        f235a = new j();
    }

    public static boolean c(b.a.a.e.a aVar) {
        return (aVar == null || aVar.d() == null || ((Integer) aVar.d()).intValue() != -256) ? false : true;
    }

    public static void d(int i, int i2) {
        if (a(i)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(i, i2));
    }

    public static void e(int i, int i2, int i3) {
        if (a(i)) {
            return;
        }
        EventBus.getDefault().post(new b.a.a.e.a(i, i2, i3));
    }

    public static void f(int i) {
        j jVar = f235a;
        if (jVar != null) {
            jVar.removeMessages(i);
        }
    }

    public static void g(int i) {
        h(i, 0);
    }

    public static void h(int i, int i2) {
        i(i, i2, 2000);
    }

    public static void i(int i, int i2, int i3) {
        j jVar = f235a;
        if (jVar != null) {
            if (jVar.hasMessages(i)) {
                f235a.removeMessages(i);
            }
            Message messageObtainMessage = f235a.obtainMessage();
            messageObtainMessage.what = i;
            messageObtainMessage.arg1 = i2;
            messageObtainMessage.obj = Integer.valueOf(InputDeviceCompat.SOURCE_ANY);
            f235a.sendMessageDelayed(messageObtainMessage, i3);
        }
    }
}
