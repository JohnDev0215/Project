package H1;

import android.view.inputmethod.EditorInfo;
import androidx.core.widget.NestedScrollView;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static void a(NestedScrollView nestedScrollView, float f10) {
        try {
            nestedScrollView.setFrameContentVelocity(f10);
        } catch (LinkageError unused) {
        }
    }

    public static void b(EditorInfo editorInfo, boolean z6) {
        editorInfo.setStylusHandwritingEnabled(z6);
    }
}
