package com.android.car.developeroptions.vpn2;

import android.content.Context;
import android.content.res.Resources;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.widget.GearPreference;

/* JADX INFO: loaded from: classes.dex */
public abstract class ManageablePreference extends GearPreference {
    public static int STATE_NONE = -1;
    boolean mIsAlwaysOn;
    int mState;
    int mUserId;

    public ManageablePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsAlwaysOn = false;
        this.mState = STATE_NONE;
        setPersistent(false);
        setOrder(0);
        setUserId(UserHandle.myUserId());
    }

    public int getUserId() {
        return this.mUserId;
    }

    public void setUserId(int i) {
        this.mUserId = i;
        checkRestrictionAndSetDisabled("no_config_vpn", i);
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int i) {
        if (this.mState != i) {
            this.mState = i;
            updateSummary();
            notifyHierarchyChanged();
        }
    }

    public void setAlwaysOn(boolean z) {
        if (this.mIsAlwaysOn != z) {
            this.mIsAlwaysOn = z;
            updateSummary();
        }
    }

    protected void updateSummary() {
        Resources resources = getContext().getResources();
        String[] stringArray = resources.getStringArray(R.array.vpn_states);
        int i = this.mState;
        String string = i == STATE_NONE ? "" : stringArray[i];
        if (this.mIsAlwaysOn) {
            String string2 = resources.getString(R.string.vpn_always_on_summary_active);
            string = TextUtils.isEmpty(string) ? string2 : resources.getString(R.string.join_two_unrelated_items, string, string2);
        }
        setSummary(string);
    }
}
