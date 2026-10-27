package i0;

import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* JADX INFO: compiled from: KeyEventDispatcher.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: compiled from: KeyEventDispatcher.java */
    public interface a {
        boolean h(KeyEvent keyEvent);
    }

    public static boolean a(View view, KeyEvent keyEvent) {
        return u.g(view, keyEvent);
    }

    public static boolean b(a aVar, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (aVar == null) {
            return false;
        }
        return aVar.h(keyEvent);
    }
}
