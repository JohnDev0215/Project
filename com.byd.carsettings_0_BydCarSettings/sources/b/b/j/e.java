package b.b.j;

import android.content.Context;
import android.os.Message;
import androidx.core.view.InputDeviceCompat;

/* JADX INFO: compiled from: NFCAutoServiceUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static g f1945a;

    public static void a(Context context) {
        f1945a = new g();
    }

    public static void b(int i) {
        g gVar = f1945a;
        if (gVar != null) {
            gVar.removeMessages(i);
        }
    }

    public static void c(int i, int i2, int i3) {
        g gVar = f1945a;
        if (gVar != null) {
            gVar.removeMessages(i);
            Message messageObtainMessage = f1945a.obtainMessage();
            messageObtainMessage.what = i;
            messageObtainMessage.arg1 = i2;
            messageObtainMessage.obj = Integer.valueOf(InputDeviceCompat.SOURCE_ANY);
            f1945a.sendMessageDelayed(messageObtainMessage, i3);
        }
    }
}
