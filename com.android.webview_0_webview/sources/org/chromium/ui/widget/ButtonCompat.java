package org.chromium.ui.widget;

import android.R;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import defpackage.AbstractC0281Pm;
import defpackage.AbstractC0299Qm;
import defpackage.AbstractC0622cn;
import defpackage.AbstractC0678dn;
import defpackage.C1383qP;
import defpackage.X;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ButtonCompat extends X {
    public C1383qP u;

    public ButtonCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, AbstractC0622cn.d3);
    }

    @Override // defpackage.X, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C1383qP c1383qP = this.u;
        if (c1383qP != null) {
            Objects.requireNonNull(c1383qP);
        }
    }

    public ButtonCompat(Context context, AttributeSet attributeSet, int i) {
        super(new ContextThemeWrapper(context, i), attributeSet, R.attr.buttonStyle);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, AbstractC0678dn.y, R.attr.buttonStyle, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, AbstractC0281Pm.M);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(2, AbstractC0281Pm.d3);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(1, true);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, getResources().getDimensionPixelSize(AbstractC0299Qm.L0));
        typedArrayObtainStyledAttributes.recycle();
        this.u = new C1383qP(this, resourceId, resourceId2, getResources().getDimensionPixelSize(AbstractC0299Qm.M0), dimensionPixelSize);
        if (!z) {
            setElevation(0.0f);
            setStateListAnimator(null);
        } else {
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(null, new int[]{R.attr.stateListAnimator}, 0, R.style.Widget.Material.Button);
            int resourceId3 = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            setStateListAnimator(resourceId3 != 0 ? AnimatorInflater.loadStateListAnimator(getContext(), resourceId3) : null);
        }
    }
}
