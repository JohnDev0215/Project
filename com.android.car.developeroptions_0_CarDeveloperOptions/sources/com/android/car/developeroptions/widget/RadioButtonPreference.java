package com.android.car.developeroptions.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class RadioButtonPreference extends CheckBoxPreference {
    private View appendix;
    private int appendixVisibility;
    private OnClickListener mListener;

    public interface OnClickListener {
        void onRadioButtonClicked(RadioButtonPreference radioButtonPreference);
    }

    public RadioButtonPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mListener = null;
        this.appendixVisibility = -1;
        setWidgetLayoutResource(R.layout.preference_widget_radiobutton);
        setLayoutResource(R.layout.preference_radio);
        setIconSpaceReserved(false);
    }

    public RadioButtonPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, android.R.attr.preferenceStyle));
    }

    public RadioButtonPreference(Context context) {
        this(context, null);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mListener = onClickListener;
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    public void onClick() {
        OnClickListener onClickListener = this.mListener;
        if (onClickListener != null) {
            onClickListener.onRadioButtonClicked(this);
        }
    }

    @Override // androidx.preference.CheckBoxPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        int i;
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(R.id.summary_container);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(TextUtils.isEmpty(getSummary()) ? 8 : 0);
            View viewFindViewById2 = preferenceViewHolder.findViewById(R.id.appendix);
            this.appendix = viewFindViewById2;
            if (viewFindViewById2 != null && (i = this.appendixVisibility) != -1) {
                viewFindViewById2.setVisibility(i);
            }
        }
        TextView textView = (TextView) preferenceViewHolder.findViewById(android.R.id.title);
        if (textView != null) {
            textView.setSingleLine(false);
            textView.setMaxLines(3);
        }
    }

    public void setAppendixVisibility(int i) {
        View view = this.appendix;
        if (view != null) {
            view.setVisibility(i);
        }
        this.appendixVisibility = i;
    }
}
