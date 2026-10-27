package com.android.phone.common.dialpad;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.android.phone.common.R$dimen;
import com.android.phone.common.R$id;
import com.android.phone.common.R$styleable;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DialpadView extends LinearLayout {
    private final int[] mButtonIds;
    private EditText mDigits;
    private ViewGroup mRateContainer;
    private ColorStateList mRippleColor;

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        return true;
    }

    public DialpadView(Context context) {
        this(context, null);
    }

    public DialpadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DialpadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mButtonIds = new int[]{R$id.zero, R$id.one, R$id.two, R$id.three, R$id.four, R$id.five, R$id.six, R$id.seven, R$id.eight, R$id.nine, R$id.star, R$id.pound};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Dialpad);
        this.mRippleColor = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.Dialpad_dialpad_key_button_touch_tint);
        typedArrayObtainStyledAttributes.recycle();
        getResources().getDimensionPixelSize(R$dimen.dialpad_key_button_translate_y);
        int i2 = getResources().getConfiguration().orientation;
        TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        setupKeypad();
        this.mDigits = (EditText) findViewById(R$id.digits);
        findViewById(R$id.dialpad_overflow);
        ViewGroup viewGroup = (ViewGroup) findViewById(R$id.rate_container);
        this.mRateContainer = viewGroup;
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            this.mDigits.setSelected(true);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0118 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setupKeypad() {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.common.dialpad.DialpadView.setupKeypad():void");
    }

    private Drawable getDrawableCompat(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(i);
        }
        return context.getResources().getDrawable(i);
    }
}
