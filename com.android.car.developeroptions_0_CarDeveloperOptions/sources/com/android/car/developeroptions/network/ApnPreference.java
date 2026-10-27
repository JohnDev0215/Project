package com.android.car.developeroptions.network;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class ApnPreference extends Preference implements CompoundButton.OnCheckedChangeListener {
    private static CompoundButton mCurrentChecked;
    private static String mSelectedKey;
    private boolean mHideDetails;
    private boolean mProtectFromCheckedChange;
    private boolean mSelectable;
    private int mSubId;

    public ApnPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSubId = -1;
        this.mProtectFromCheckedChange = false;
        this.mSelectable = true;
        this.mHideDetails = false;
    }

    public ApnPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.apnPreferenceStyle);
    }

    public ApnPreference(Context context) {
        this(context, null);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(R.id.apn_radiobutton);
        if (viewFindViewById == null || !(viewFindViewById instanceof RadioButton)) {
            return;
        }
        RadioButton radioButton = (RadioButton) viewFindViewById;
        if (this.mSelectable) {
            radioButton.setOnCheckedChangeListener(this);
            boolean zEquals = getKey().equals(mSelectedKey);
            if (zEquals) {
                mCurrentChecked = radioButton;
                mSelectedKey = getKey();
            }
            this.mProtectFromCheckedChange = true;
            radioButton.setChecked(zEquals);
            this.mProtectFromCheckedChange = false;
            radioButton.setVisibility(0);
            return;
        }
        radioButton.setVisibility(8);
    }

    public void setChecked() {
        mSelectedKey = getKey();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Log.i("ApnPreference", "ID: " + getKey() + " :" + z);
        if (this.mProtectFromCheckedChange) {
            return;
        }
        if (z) {
            CompoundButton compoundButton2 = mCurrentChecked;
            if (compoundButton2 != null) {
                compoundButton2.setChecked(false);
            }
            mCurrentChecked = compoundButton;
            String key = getKey();
            mSelectedKey = key;
            callChangeListener(key);
            return;
        }
        mCurrentChecked = null;
        mSelectedKey = null;
    }

    @Override // androidx.preference.Preference
    protected void onClick() {
        super.onClick();
        Context context = getContext();
        if (context != null) {
            if (this.mHideDetails) {
                Toast.makeText(context, context.getString(R.string.cannot_change_apn_toast), 1).show();
                return;
            }
            Intent intent = new Intent("android.intent.action.EDIT", ContentUris.withAppendedId(Telephony.Carriers.CONTENT_URI, Integer.parseInt(getKey())));
            intent.putExtra("sub_id", this.mSubId);
            context.startActivity(intent);
        }
    }

    @Override // androidx.preference.Preference
    public void setSelectable(boolean z) {
        this.mSelectable = z;
    }

    public void setSubId(int i) {
        this.mSubId = i;
    }

    public void setHideDetails() {
        this.mHideDetails = true;
    }
}
