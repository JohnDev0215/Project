package org.chromium.chrome.browser.customtabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import defpackage.dx2;
import org.chromium.components.browser_ui.widget.BoundedLinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class CustomTabBottomBarView extends BoundedLinearLayout {
    public final Context j;
    public a k;

    public class a extends dx2 {
        public a(Context context, dx2.a aVar) {
            super(context, aVar);
        }
    }

    public CustomTabBottomBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = context;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        a aVar = this.k;
        if (aVar == null) {
            return false;
        }
        return aVar.a(motionEvent);
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.k == null) {
            return false;
        }
        if (motionEvent.getActionMasked() == 0) {
            return true;
        }
        return this.k.a(motionEvent);
    }

    public void setSwipeHandler(dx2.a aVar) {
        if (aVar == null) {
            this.k = null;
        } else {
            this.k = new a(this.j, aVar);
        }
    }
}
