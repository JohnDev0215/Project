package defpackage;

import android.os.Build;
import android.view.inputmethod.InputMethodManager;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class KB implements Runnable {
    public final /* synthetic */ QB s;

    public KB(QB qb) {
        this.s = qb;
    }

    @Override // java.lang.Runnable
    public void run() {
        InputMethodManager inputMethodManagerB;
        WA wa = (WA) this.s.e.t;
        Objects.requireNonNull(wa);
        if (Build.VERSION.SDK_INT <= 23 && (inputMethodManagerB = wa.b()) != null) {
            try {
                InputMethodManager.class.getMethod("notifyUserAction", new Class[0]).invoke(inputMethodManagerB, new Object[0]);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            }
        }
    }
}
