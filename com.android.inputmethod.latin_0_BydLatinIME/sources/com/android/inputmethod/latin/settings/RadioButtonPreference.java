package com.android.inputmethod.latin.settings;

import android.R;
import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class RadioButtonPreference extends Preference {
    private final View.OnClickListener mClickListener;
    private boolean mIsSelected;
    private OnRadioButtonClickedListener mListener;
    private RadioButton mRadioButton;

    interface OnRadioButtonClickedListener {
        void onRadioButtonClicked(RadioButtonPreference preference);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Preference another) {
        return super.compareTo(another);
    }

    public RadioButtonPreference(final Context context) {
        this(context, null);
    }

    public RadioButtonPreference(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.preferenceStyle);
    }

    public RadioButtonPreference(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mClickListener = new View.OnClickListener() { // from class: com.android.inputmethod.latin.settings.RadioButtonPreference.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                RadioButtonPreference.this.callListenerOnRadioButtonClicked();
            }
        };
        setWidgetLayoutResource(com.android.inputmethod.latin.R.layout.radio_button_preference_widget);
    }

    public void setOnRadioButtonClickedListener(final OnRadioButtonClickedListener listener) {
        this.mListener = listener;
    }

    void callListenerOnRadioButtonClicked() {
        OnRadioButtonClickedListener onRadioButtonClickedListener = this.mListener;
        if (onRadioButtonClickedListener != null) {
            onRadioButtonClickedListener.onRadioButtonClicked(this);
        }
    }

    @Override // android.preference.Preference
    protected void onBindView(final View view) {
        super.onBindView(view);
        RadioButton radioButton = (RadioButton) view.findViewById(com.android.inputmethod.latin.R.id.radio_button);
        this.mRadioButton = radioButton;
        radioButton.setChecked(this.mIsSelected);
        this.mRadioButton.setOnClickListener(this.mClickListener);
        view.setOnClickListener(this.mClickListener);
    }

    public boolean isSelected() {
        return this.mIsSelected;
    }

    public void setSelected(final boolean selected) {
        if (selected == this.mIsSelected) {
            return;
        }
        this.mIsSelected = selected;
        RadioButton radioButton = this.mRadioButton;
        if (radioButton != null) {
            radioButton.setChecked(selected);
        }
        notifyChanged();
    }
}
