package com.android.settingslib.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceViewHolder;

/* JADX INFO: loaded from: classes3.dex */
public class RadioButtonPreference extends CheckBoxPreference {
    private View mAppendix;
    private int mAppendixVisibility;
    private OnClickListener mListener;

    public interface OnClickListener {
        void onRadioButtonClicked(RadioButtonPreference radioButtonPreference);
    }

    public RadioButtonPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mListener = null;
        this.mAppendixVisibility = -1;
        init();
    }

    public RadioButtonPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mListener = null;
        this.mAppendixVisibility = -1;
        init();
    }

    public RadioButtonPreference(Context context) {
        this(context, null);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mListener = listener;
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    public void onClick() {
        OnClickListener onClickListener = this.mListener;
        if (onClickListener != null) {
            onClickListener.onRadioButtonClicked(this);
        }
    }

    @Override // androidx.preference.CheckBoxPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        int i;
        super.onBindViewHolder(holder);
        View summaryContainer = holder.findViewById(R.id.summary_container);
        if (summaryContainer != null) {
            summaryContainer.setVisibility(TextUtils.isEmpty(getSummary()) ? 8 : 0);
            View viewFindViewById = holder.findViewById(R.id.appendix);
            this.mAppendix = viewFindViewById;
            if (viewFindViewById != null && (i = this.mAppendixVisibility) != -1) {
                viewFindViewById.setVisibility(i);
            }
        }
        TextView title = (TextView) holder.findViewById(android.R.id.title);
        if (title != null) {
            title.setSingleLine(false);
            title.setMaxLines(3);
        }
    }

    public void setAppendixVisibility(int visibility) {
        View view = this.mAppendix;
        if (view != null) {
            view.setVisibility(visibility);
        }
        this.mAppendixVisibility = visibility;
    }

    private void init() {
        setWidgetLayoutResource(R.layout.preference_widget_radiobutton);
        setLayoutResource(R.layout.preference_radio);
        setIconSpaceReserved(false);
    }
}
