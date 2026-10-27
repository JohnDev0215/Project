package com.android.car.apps.common;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.car.ui.utils.CarUxRestrictionsUtil;

/* JADX INFO: loaded from: classes5.dex */
public class UxrTextView extends TextView {
    private CarUxRestrictions mCarUxRestrictions;
    private CarUxRestrictionsUtil mCarUxRestrictionsUtil;
    private CarUxRestrictionsUtil.OnUxRestrictionsChangedListener mListener;

    public UxrTextView(Context context) {
        super(context);
        init(context);
    }

    public UxrTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UxrTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public UxrTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.mCarUxRestrictionsUtil = CarUxRestrictionsUtil.getInstance(context);
        this.mListener = new CarUxRestrictionsUtil.OnUxRestrictionsChangedListener() { // from class: com.android.car.apps.common.-$$Lambda$UxrTextView$29cSm5i-5c2b3vkdGR2UKScs8Gg
            @Override // com.android.car.ui.utils.CarUxRestrictionsUtil.OnUxRestrictionsChangedListener
            public final void onRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
                this.f$0.updateCarUxRestrictions(carUxRestrictions);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCarUxRestrictions(CarUxRestrictions carUxRestrictions) {
        this.mCarUxRestrictions = carUxRestrictions;
        setTransformationMethod(new UXRTransformationMethod());
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mCarUxRestrictionsUtil.register(this.mListener);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mCarUxRestrictionsUtil.unregister(this.mListener);
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean b) {
    }

    @Override // android.widget.TextView
    public void setSingleLine(boolean b) {
    }

    @Override // android.widget.TextView
    public void setInputType(int i) {
    }

    private class UXRTransformationMethod implements TransformationMethod {
        private UXRTransformationMethod() {
        }

        @Override // android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence source, View view) {
            if (source == null) {
                return "";
            }
            return CarUxRestrictionsUtil.complyString(UxrTextView.this.getContext(), source.toString(), UxrTextView.this.mCarUxRestrictions);
        }

        @Override // android.text.method.TransformationMethod
        public void onFocusChanged(View view, CharSequence sourceText, boolean focused, int direction, Rect previouslyFocusedRect) {
        }
    }
}
