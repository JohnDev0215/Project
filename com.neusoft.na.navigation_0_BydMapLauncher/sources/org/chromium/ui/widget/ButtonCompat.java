package org.chromium.ui.widget;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.widget.Button;
import org.chromium.ui.R;

/* JADX INFO: loaded from: classes4.dex */
public class ButtonCompat extends Button {
    private static final int DISABLED_COLOR = 1112493903;
    private static final float PRE_L_PRESSED_BRIGHTNESS = 0.85f;
    private int mColor;

    public static Button createBorderlessButton(Context context) {
        return new Button(new ContextThemeWrapper(context, R.style.ButtonCompatBorderlessOverlay));
    }

    public ButtonCompat(Context context, int i, boolean z) {
        this(context, i, z, null);
    }

    public ButtonCompat(Context context, AttributeSet attributeSet) {
        this(context, getColorFromAttributeSet(context, attributeSet), getRaisedStatusFromAttributeSet(context, attributeSet), attributeSet);
    }

    private ButtonCompat(Context context, int i, boolean z, AttributeSet attributeSet) {
        super(new ContextThemeWrapper(context, R.style.ButtonCompatOverlay), attributeSet);
        getBackground().mutate();
        setButtonColor(i);
        setRaised(z);
    }

    public void setButtonColor(int i) {
        if (i == this.mColor) {
            return;
        }
        this.mColor = i;
        updateButtonBackgroundL();
    }

    private void setRaised(boolean z) {
        if (z) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, new int[]{android.R.attr.stateListAnimator}, 0, android.R.style.Widget.Material.Button);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            typedArrayObtainStyledAttributes.recycle();
            setStateListAnimator(resourceId != 0 ? AnimatorInflater.loadStateListAnimator(getContext(), resourceId) : null);
            return;
        }
        setElevation(0.0f);
        setStateListAnimator(null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    private void updateButtonBackgroundL() {
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{DISABLED_COLOR, this.mColor});
        GradientDrawable gradientDrawable = (GradientDrawable) ((RippleDrawable) getBackground()).getDrawable(0);
        gradientDrawable.mutate();
        gradientDrawable.setColor(colorStateList);
    }

    private void updateButtonBackgroundPreL() {
        ((GradientDrawable) getBackground()).setColor(getBackgroundColorPreL());
    }

    private int getBackgroundColorPreL() {
        for (int i : getDrawableState()) {
            if (i == 16842919 || i == 16842908 || i == 16842913) {
                return Color.rgb(Math.round(Color.red(this.mColor) * PRE_L_PRESSED_BRIGHTNESS), Math.round(Color.green(this.mColor) * PRE_L_PRESSED_BRIGHTNESS), Math.round(Color.blue(this.mColor) * PRE_L_PRESSED_BRIGHTNESS));
            }
        }
        for (int i2 : getDrawableState()) {
            if (i2 == 16842910) {
                return this.mColor;
            }
        }
        return DISABLED_COLOR;
    }

    private static int getColorFromAttributeSet(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ButtonCompat, 0, 0);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.ButtonCompat_buttonColor, -1);
        typedArrayObtainStyledAttributes.recycle();
        return color;
    }

    private static boolean getRaisedStatusFromAttributeSet(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ButtonCompat, 0, 0);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.ButtonCompat_buttonRaised, true);
        typedArrayObtainStyledAttributes.recycle();
        return z;
    }
}
