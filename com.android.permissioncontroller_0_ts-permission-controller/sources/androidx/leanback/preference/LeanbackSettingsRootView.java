package androidx.leanback.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes3.dex */
public class LeanbackSettingsRootView extends FrameLayout {
    private View.OnKeyListener mOnBackKeyListener;

    public LeanbackSettingsRootView(Context context) {
        super(context);
    }

    public LeanbackSettingsRootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LeanbackSettingsRootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnBackKeyListener(View.OnKeyListener backKeyListener) {
        this.mOnBackKeyListener = backKeyListener;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        View.OnKeyListener onKeyListener;
        boolean handled = false;
        if (event.getAction() == 1 && event.getKeyCode() == 4 && (onKeyListener = this.mOnBackKeyListener) != null) {
            handled = onKeyListener.onKey(this, event.getKeyCode(), event);
        }
        return handled || super.dispatchKeyEvent(event);
    }
}
