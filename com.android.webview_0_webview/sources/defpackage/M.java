package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionBarContextView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class M extends ViewGroup {
    public int s;
    public boolean t;
    public boolean u;

    public M(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(AbstractC0245Nm.c, typedValue, true) || typedValue.resourceId == 0) {
            return;
        }
        new ContextThemeWrapper(context, typedValue.resourceId);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, AbstractC0678dn.a, AbstractC0245Nm.f, 0);
        ((ActionBarContextView) this).s = typedArrayObtainStyledAttributes.getLayoutDimension(13, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.u = false;
        }
        if (!this.u) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.u = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.u = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.t = false;
        }
        if (!this.t) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.t = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.t = false;
        }
        return true;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            super.setVisibility(i);
        }
    }
}
