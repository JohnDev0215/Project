package com.android.car.developeroptions.applications;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.accessibility.ListDialogPreference;

/* JADX INFO: loaded from: classes.dex */
public class AppDomainsPreference extends ListDialogPreference {
    private int mNumEntries;

    public AppDomainsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.app_domains_dialog);
        setListItemLayoutResource(R.layout.app_domains_item);
    }

    @Override // com.android.car.developeroptions.accessibility.ListDialogPreference
    public void setTitles(CharSequence[] charSequenceArr) {
        this.mNumEntries = charSequenceArr != null ? charSequenceArr.length : 0;
        super.setTitles(charSequenceArr);
    }

    @Override // com.android.car.developeroptions.accessibility.ListDialogPreference, androidx.preference.Preference
    public CharSequence getSummary() {
        Context context = getContext();
        if (this.mNumEntries == 0) {
            return context.getString(R.string.domain_urls_summary_none);
        }
        return context.getString(this.mNumEntries == 1 ? R.string.domain_urls_summary_one : R.string.domain_urls_summary_some, super.getSummary());
    }

    @Override // com.android.car.developeroptions.accessibility.ListDialogPreference
    protected void onBindListItem(View view, int i) {
        CharSequence titleAt = getTitleAt(i);
        if (titleAt != null) {
            ((TextView) view.findViewById(R.id.domain_name)).setText(titleAt);
        }
    }
}
