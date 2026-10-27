package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.ResultReceiver;
import android.os.StrictMode;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import java.lang.ref.WeakReference;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WA implements ND {
    public final Context a;
    public WindowAndroid b;
    public MD c;
    public Runnable d;

    public WA(Context context, WindowAndroid windowAndroid, MD md) {
        this.a = context;
        this.b = windowAndroid;
        this.c = md;
    }

    public static Activity a(WindowAndroid windowAndroid) {
        WeakReference weakReferenceQ;
        if (windowAndroid == null || (weakReferenceQ = windowAndroid.Q()) == null) {
            return null;
        }
        return (Activity) weakReferenceQ.get();
    }

    public final InputMethodManager b() {
        Context contextA = a(this.b);
        if (contextA == null) {
            contextA = this.a;
        }
        return (InputMethodManager) contextA.getSystemService("input_method");
    }

    public boolean c(View view) {
        InputMethodManager inputMethodManagerB = b();
        return inputMethodManagerB != null && inputMethodManagerB.isActive(view);
    }

    public final void d(View view, int i, ResultReceiver resultReceiver) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            InputMethodManager inputMethodManagerB = b();
            if (inputMethodManagerB != null) {
                inputMethodManagerB.showSoftInput(view, i, resultReceiver);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        }
    }
}
