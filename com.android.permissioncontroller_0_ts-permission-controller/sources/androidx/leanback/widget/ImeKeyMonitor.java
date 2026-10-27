package androidx.leanback.widget;

import android.view.KeyEvent;
import android.widget.EditText;

/* JADX INFO: loaded from: classes3.dex */
public interface ImeKeyMonitor {

    public interface ImeKeyListener {
        boolean onKeyPreIme(EditText editText, int i, KeyEvent keyEvent);
    }

    void setImeKeyListener(ImeKeyListener imeKeyListener);
}
