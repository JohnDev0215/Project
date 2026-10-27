package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* JADX INFO: loaded from: classes.dex */
class AppCompatHintHelper {
    private AppCompatHintHelper() {
    }

    public static void a(View view, EditorInfo editorInfo, InputConnection inputConnection) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            if (parent instanceof WithHint) {
                editorInfo.hintText = ((WithHint) parent).a();
                return;
            }
        }
    }
}
