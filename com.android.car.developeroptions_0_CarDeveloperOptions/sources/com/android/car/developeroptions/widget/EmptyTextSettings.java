package com.android.car.developeroptions.widget;

import android.R;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.car.developeroptions.SettingsPreferenceFragment;

/* JADX INFO: loaded from: classes.dex */
public abstract class EmptyTextSettings extends SettingsPreferenceFragment {
    private TextView mEmpty;

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        TextView textView = new TextView(getContext());
        this.mEmpty = textView;
        textView.setGravity(17);
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.textAppearanceMedium, typedValue, true);
        this.mEmpty.setTextAppearance(typedValue.resourceId);
        ((ViewGroup) view.findViewById(R.id.list_container)).addView(this.mEmpty, new ViewGroup.LayoutParams(-1, -1));
        setEmptyView(this.mEmpty);
    }

    protected void setEmptyText(int i) {
        this.mEmpty.setText(i);
    }
}
