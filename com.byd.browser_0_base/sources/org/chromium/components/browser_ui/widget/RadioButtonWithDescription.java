package org.chromium.components.browser_ui.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import defpackage.uh3;
import java.util.List;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes2.dex */
public class RadioButtonWithDescription extends RelativeLayout implements View.OnClickListener {
    public RadioButton e;
    public ChromeImageView f;
    public TextView g;
    public TextView h;
    public a i;
    public List<RadioButtonWithDescription> j;

    public interface a {
        void j(RadioButtonWithDescription radioButtonWithDescription);
    }

    public RadioButtonWithDescription(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(getLayoutResource(), (ViewGroup) this, true);
        c();
        if (attributeSet != null) {
            a(attributeSet);
        }
        setMinimumHeight(getResources().getDimensionPixelSize(R$dimen.min_touch_target_size));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.radio_button_with_description_lateral_padding);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R$dimen.radio_button_with_description_vertical_padding);
        setPaddingRelative(getPaddingStart() == 0 ? dimensionPixelSize : getPaddingStart(), getPaddingTop() == 0 ? dimensionPixelSize2 : getPaddingTop(), getPaddingEnd() != 0 ? getPaddingEnd() : dimensionPixelSize, getPaddingBottom() != 0 ? getPaddingBottom() : dimensionPixelSize2);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
            if (getEndStubLayoutResourceId() != -1) {
                View viewFindViewById = findViewById(R$id.radio_container);
                viewFindViewById.setBackgroundResource(typedValue.resourceId);
                viewFindViewById.setPaddingRelative(getPaddingStart(), viewFindViewById.getPaddingTop(), viewFindViewById.getPaddingEnd(), viewFindViewById.getPaddingBottom());
                setPaddingRelative(0, getPaddingTop(), getPaddingEnd(), getPaddingBottom());
            } else {
                setBackgroundResource(typedValue.resourceId);
            }
        }
        setOnClickListener(this);
        setFocusable(true);
    }

    public void a(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R$styleable.RadioButtonWithDescription, 0, 0);
        Drawable drawableD = uh3.d(getContext(), typedArrayObtainStyledAttributes, R$styleable.RadioButtonWithDescription_iconSrc);
        if (drawableD != null) {
            this.f.setImageDrawable(drawableD);
            this.f.setVisibility(0);
        }
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.RadioButtonWithDescription_primaryText);
        if (string != null) {
            this.g.setText(string);
        }
        String string2 = typedArrayObtainStyledAttributes.getString(R$styleable.RadioButtonWithDescription_descriptionText);
        if (string2 != null) {
            this.h.setText(string2);
            this.h.setVisibility(0);
        } else {
            ((RelativeLayout.LayoutParams) this.g.getLayoutParams()).addRule(15);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final boolean b() {
        return this.e.isChecked();
    }

    public void c() {
        this.e = getRadioButtonView();
        this.f = getIcon();
        this.g = getPrimaryTextView();
        this.h = getDescriptionTextView();
        int endStubLayoutResourceId = getEndStubLayoutResourceId();
        if (endStubLayoutResourceId != -1) {
            ViewStub viewStub = (ViewStub) findViewById(R$id.end_view_stub);
            viewStub.setLayoutResource(endStubLayoutResourceId);
            viewStub.inflate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public CharSequence getDescriptionText() {
        return this.h.getText();
    }

    public TextView getDescriptionTextView() {
        return (TextView) findViewById(R$id.description);
    }

    public int getEndStubLayoutResourceId() {
        return -1;
    }

    public ChromeImageView getIcon() {
        return (ChromeImageView) findViewById(R$id.icon);
    }

    public int getLayoutResource() {
        return R$layout.radio_button_with_description;
    }

    public CharSequence getPrimaryText() {
        return this.g.getText();
    }

    public TextView getPrimaryTextView() {
        return (TextView) findViewById(R$id.primary);
    }

    public RadioButton getRadioButtonView() {
        return (RadioButton) findViewById(R$id.radio_button);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        setChecked(true);
        a aVar = this.i;
        if (aVar != null) {
            aVar.j(this);
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("superState"));
        setChecked(bundle.getBoolean("isChecked"));
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putBoolean("isChecked", b());
        return bundle;
    }

    public void setChecked(boolean z) {
        setCheckedWithNoFocusChange(z);
        if (z) {
            requestFocus();
        }
    }

    public void setCheckedWithNoFocusChange(boolean z) {
        List<RadioButtonWithDescription> list = this.j;
        if (list != null && z) {
            for (RadioButtonWithDescription radioButtonWithDescription : list) {
                if (radioButtonWithDescription != this) {
                    radioButtonWithDescription.setChecked(false);
                }
            }
        }
        this.e.setChecked(z);
    }

    public void setDescriptionText(CharSequence charSequence) {
        this.h.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            ((RelativeLayout.LayoutParams) this.g.getLayoutParams()).addRule(15);
            this.h.setVisibility(8);
        } else {
            ((RelativeLayout.LayoutParams) this.g.getLayoutParams()).removeRule(15);
            this.h.setVisibility(0);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.h.setEnabled(z);
        this.g.setEnabled(z);
        this.e.setEnabled(z);
        if (this.f != null) {
            TypedValue typedValue = new TypedValue();
            getContext().getResources().getValue(R$dimen.default_disabled_alpha, typedValue, true);
            this.f.setAlpha(z ? 1.0f : typedValue.getFloat());
        }
    }

    public void setOnCheckedChangeListener(a aVar) {
        this.i = aVar;
    }

    public void setPrimaryText(CharSequence charSequence) {
        this.g.setText(charSequence);
    }

    public void setRadioButtonGroup(List<RadioButtonWithDescription> list) {
        this.j = list;
    }
}
