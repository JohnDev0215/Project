package a.b.p;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public class p extends PopupWindow {
    public p(Context context, AttributeSet attributeSet, int i, int i2) {
        int resourceId;
        super(context, attributeSet, i, i2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.b.j.PopupWindow, i, i2);
        if (typedArrayObtainStyledAttributes.hasValue(a.b.j.PopupWindow_overlapAnchor)) {
            setOverlapAnchor(typedArrayObtainStyledAttributes.getBoolean(a.b.j.PopupWindow_overlapAnchor, false));
        }
        int i3 = a.b.j.PopupWindow_android_popupBackground;
        setBackgroundDrawable((!typedArrayObtainStyledAttributes.hasValue(i3) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(i3, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(i3) : a.b.l.a.a.b(context, resourceId));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        super.showAsDropDown(view, i, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        super.update(view, i, i2, i3, i4);
    }
}
