package com.android.car.settings.accounts;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.android.car.apps.common.util.Themes;
import com.android.car.settings.accounts.AccountSyncHelper;
import com.android.car.ui.preference.CarUiSwitchPreference;

/* JADX INFO: loaded from: classes5.dex */
public class SyncPreference extends CarUiSwitchPreference {
    private boolean mOneTimeSyncMode;
    private String mPackageName;
    private AccountSyncHelper.SyncState mSyncState;
    private int mUid;

    public SyncPreference(Context context, String authority) {
        super(context);
        this.mSyncState = AccountSyncHelper.SyncState.NONE;
        this.mOneTimeSyncMode = false;
        setKey(authority);
        setPersistent(false);
        updateIcon();
    }

    @Override // com.android.car.ui.preference.CarUiSwitchPreference, androidx.preference.SwitchPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder view) {
        super.onBindViewHolder(view);
        View switchView = view.findViewById(R.id.switch_widget);
        if (this.mOneTimeSyncMode) {
            switchView.setVisibility(8);
            TextView summary = (TextView) view.findViewById(R.id.summary);
            summary.setText(getContext().getString(com.android.car.settings.R.string.sync_one_time_sync, getSummary()));
            return;
        }
        switchView.setVisibility(0);
    }

    /* JADX INFO: renamed from: com.android.car.settings.accounts.SyncPreference$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$settings$accounts$AccountSyncHelper$SyncState;

        static {
            int[] iArr = new int[AccountSyncHelper.SyncState.values().length];
            $SwitchMap$com$android$car$settings$accounts$AccountSyncHelper$SyncState = iArr;
            try {
                iArr[AccountSyncHelper.SyncState.ACTIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$car$settings$accounts$AccountSyncHelper$SyncState[AccountSyncHelper.SyncState.PENDING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$car$settings$accounts$AccountSyncHelper$SyncState[AccountSyncHelper.SyncState.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private void updateIcon() {
        int i = AnonymousClass1.$SwitchMap$com$android$car$settings$accounts$AccountSyncHelper$SyncState[this.mSyncState.ordinal()];
        if (i == 1) {
            setIcon(com.android.car.settings.R.drawable.ic_sync_anim);
            getIcon().setTintList(Themes.getAttrColorStateList(getContext(), com.android.car.settings.R.attr.iconColor));
        } else if (i == 2) {
            setIcon(com.android.car.settings.R.drawable.ic_sync);
            getIcon().setTintList(Themes.getAttrColorStateList(getContext(), com.android.car.settings.R.attr.iconColor));
        } else if (i == 3) {
            setIcon(com.android.car.settings.R.drawable.ic_sync_problem);
            getIcon().setTintList(Themes.getAttrColorStateList(getContext(), com.android.car.settings.R.attr.iconColor));
        } else {
            setIcon((Drawable) null);
            setIconSpaceReserved(true);
        }
    }

    public void setSyncState(AccountSyncHelper.SyncState state) {
        this.mSyncState = state;
        updateIcon();
    }

    public boolean isOneTimeSyncMode() {
        return this.mOneTimeSyncMode;
    }

    public void setOneTimeSyncMode(boolean oneTimeSyncMode) {
        this.mOneTimeSyncMode = oneTimeSyncMode;
        notifyChanged();
    }

    public int getUid() {
        return this.mUid;
    }

    public void setUid(int uid) {
        this.mUid = uid;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }
}
