package com.android.car.settings.wifi.details;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiPreference;

/* JADX INFO: loaded from: classes5.dex */
public class WifiDetailsPreference extends CarUiPreference {
    private String mDetailText;

    public WifiDetailsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setWidgetLayoutResource(R.layout.summary_preference_widget);
        setShowChevron(false);
    }

    WifiDetailsPreference(Context context) {
        super(context);
    }

    public void setDetailText(String text) {
        if (TextUtils.equals(this.mDetailText, text)) {
            return;
        }
        this.mDetailText = text;
        notifyChanged();
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        TextView textView = (TextView) view.findViewById(R.id.widget_summary);
        textView.setText(this.mDetailText);
    }

    String getDetailText() {
        return this.mDetailText;
    }
}
