package w;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Arrays;

/* JADX INFO: compiled from: ActivityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends x.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static InterfaceC0167a f9693b;

    /* JADX INFO: renamed from: w.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface InterfaceC0167a {
        boolean a(Activity activity, String[] strArr, int i7);
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface b {
        void b(int i7);
    }

    public static void e(Activity activity) {
        activity.finishAffinity();
    }

    public static void f(Activity activity) {
        activity.recreate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void g(Activity activity, String[] strArr, int i7) {
        InterfaceC0167a interfaceC0167a = f9693b;
        if (interfaceC0167a == null || !interfaceC0167a.a(activity, strArr, i7)) {
            for (String str : strArr) {
                if (TextUtils.isEmpty(str)) {
                    throw new IllegalArgumentException("Permission request for permissions " + Arrays.toString(strArr) + " must not contain null or empty values");
                }
            }
            if (activity instanceof b) {
                ((b) activity).b(i7);
            }
            activity.requestPermissions(strArr, i7);
        }
    }

    public static void h(Activity activity, Intent intent, int i7, Bundle bundle) {
        activity.startActivityForResult(intent, i7, bundle);
    }

    public static void i(Activity activity, IntentSender intentSender, int i7, Intent intent, int i8, int i9, int i10, Bundle bundle) throws IntentSender.SendIntentException {
        activity.startIntentSenderForResult(intentSender, i7, intent, i8, i9, i10, bundle);
    }
}
