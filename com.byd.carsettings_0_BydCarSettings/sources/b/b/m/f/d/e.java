package b.b.m.f.d;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import androidx.annotation.NonNull;

/* JADX INFO: compiled from: NoRespondDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends Dialog {
    public e(@NonNull Context context, int i) {
        super(context, i);
        a(context);
    }

    public final void a(Context context) {
        setCancelable(false);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        b.b.m.c.a.a("NoRespondDialog", "---dispatchKeyEvent----keyCode: " + keyCode);
        if (keyCode != 4 && keyCode != 3) {
            return super.dispatchKeyEvent(keyEvent);
        }
        b.b.m.c.a.c("NoRespondDialog", "---dispatchKeyEvent----KEYCODE_BACK or KEYCODE_HOME---");
        return false;
    }
}
