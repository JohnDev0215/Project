package widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class DialogTouchLinearLayout extends LinearLayout {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f8756d;

    public interface a {
        void removeView(View view);
    }

    public DialogTouchLinearLayout(Context context) {
        super(context);
    }

    public final boolean a(View view, int i8, int i9) {
        if (view == null) {
            return false;
        }
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i10 = iArr[0];
        int i11 = iArr[1];
        return i9 >= i11 && i9 <= view.getMeasuredHeight() + i11 && i8 >= i10 && i8 <= view.getMeasuredWidth() + i10;
    }

    public void b(WindowManager.LayoutParams layoutParams, a aVar) {
        this.f8756d = aVar;
        if (layoutParams != null) {
            int i8 = layoutParams.flags & (-33);
            layoutParams.flags = i8;
            layoutParams.flags = i8 & (-9);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.f8756d == null || keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        this.f8756d.removeView(this);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f8756d != null && motionEvent.getAction() == 1) {
            if (a(this, (int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                BydLogHelper.logV("DialogTouchLinearLayout", " onTouchEvent true");
                return true;
            }
            this.f8756d.removeView(this);
        }
        return super.onTouchEvent(motionEvent);
    }

    public DialogTouchLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
