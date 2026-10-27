package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.net.NetworkTemplate;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.datausage.TemplatePreference;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.CustomDialogPreferenceCompat;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CellDataPreference extends CustomDialogPreferenceCompat implements TemplatePreference {
    public boolean mChecked;
    private final DataStateListener mDataStateListener;
    public boolean mMultiSimDialog;
    final SubscriptionManager.OnSubscriptionsChangedListener mOnSubscriptionsChangeListener;
    public int mSubId;
    SubscriptionManager mSubscriptionManager;
    private TelephonyManager mTelephonyManager;

    public CellDataPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, TypedArrayUtils.getAttr(context, R.attr.switchPreferenceStyle, android.R.attr.switchPreferenceStyle));
        this.mSubId = -1;
        this.mOnSubscriptionsChangeListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.car.developeroptions.datausage.CellDataPreference.1
            @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
            public void onSubscriptionsChanged() {
                CellDataPreference.this.updateEnabled();
            }
        };
        this.mDataStateListener = new DataStateListener() { // from class: com.android.car.developeroptions.datausage.CellDataPreference.2
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                CellDataPreference.this.updateChecked();
            }
        };
    }

    @Override // androidx.preference.Preference
    protected void onRestoreInstanceState(Parcelable parcelable) {
        CellDataState cellDataState = (CellDataState) parcelable;
        super.onRestoreInstanceState(cellDataState.getSuperState());
        this.mTelephonyManager = TelephonyManager.from(getContext());
        this.mChecked = cellDataState.mChecked;
        this.mMultiSimDialog = cellDataState.mMultiSimDialog;
        if (this.mSubId == -1) {
            this.mSubId = cellDataState.mSubId;
            setKey(getKey() + this.mSubId);
        }
        notifyChanged();
    }

    @Override // androidx.preference.Preference
    protected Parcelable onSaveInstanceState() {
        CellDataState cellDataState = new CellDataState(super.onSaveInstanceState());
        cellDataState.mChecked = this.mChecked;
        cellDataState.mMultiSimDialog = this.mMultiSimDialog;
        cellDataState.mSubId = this.mSubId;
        return cellDataState;
    }

    @Override // androidx.preference.Preference
    public void onAttached() {
        super.onAttached();
        this.mDataStateListener.setListener(true, this.mSubId, getContext());
        SubscriptionManager subscriptionManager = this.mSubscriptionManager;
        if (subscriptionManager != null) {
            subscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        }
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        this.mDataStateListener.setListener(false, this.mSubId, getContext());
        SubscriptionManager subscriptionManager = this.mSubscriptionManager;
        if (subscriptionManager != null) {
            subscriptionManager.removeOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        }
        super.onDetached();
    }

    @Override // com.android.car.developeroptions.datausage.TemplatePreference
    public void setTemplate(NetworkTemplate networkTemplate, int i, TemplatePreference.NetworkServices networkServices) {
        if (i == -1) {
            throw new IllegalArgumentException("CellDataPreference needs a SubscriptionInfo");
        }
        this.mSubscriptionManager = SubscriptionManager.from(getContext());
        this.mTelephonyManager = TelephonyManager.from(getContext());
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mOnSubscriptionsChangeListener);
        if (this.mSubId == -1) {
            this.mSubId = i;
            setKey(getKey() + i);
        }
        updateEnabled();
        updateChecked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChecked() {
        setChecked(this.mTelephonyManager.getDataEnabled(this.mSubId));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEnabled() {
        setEnabled(this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId) != null);
    }

    @Override // androidx.preference.Preference
    protected void performClick(View view) {
        Context context = getContext();
        FeatureFactory.getFactory(context).getMetricsFeatureProvider().action(context, 178, !this.mChecked);
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        if (this.mChecked) {
            if (!Utils.showSimCardTile(getContext()) || (defaultDataSubscriptionInfo != null && activeSubscriptionInfo != null && activeSubscriptionInfo.getSubscriptionId() == defaultDataSubscriptionInfo.getSubscriptionId())) {
                setMobileDataEnabled(false);
                if (defaultDataSubscriptionInfo == null || activeSubscriptionInfo == null || activeSubscriptionInfo.getSubscriptionId() != defaultDataSubscriptionInfo.getSubscriptionId()) {
                    return;
                }
                disableDataForOtherSubscriptions(this.mSubId);
                return;
            }
            this.mMultiSimDialog = false;
            super.performClick(view);
            return;
        }
        if (Utils.showSimCardTile(getContext())) {
            this.mMultiSimDialog = true;
            if (defaultDataSubscriptionInfo != null && activeSubscriptionInfo != null && activeSubscriptionInfo.getSubscriptionId() == defaultDataSubscriptionInfo.getSubscriptionId()) {
                setMobileDataEnabled(true);
                disableDataForOtherSubscriptions(this.mSubId);
                return;
            } else {
                super.performClick(view);
                return;
            }
        }
        setMobileDataEnabled(true);
    }

    private void setMobileDataEnabled(boolean z) {
        this.mTelephonyManager.setDataEnabled(this.mSubId, z);
        setChecked(z);
    }

    private void setChecked(boolean z) {
        if (this.mChecked == z) {
            return;
        }
        this.mChecked = z;
        notifyChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewFindViewById = preferenceViewHolder.findViewById(android.R.id.switch_widget);
        viewFindViewById.setClickable(false);
        ((Checkable) viewFindViewById).setChecked(this.mChecked);
    }

    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        if (this.mMultiSimDialog) {
            showMultiSimDialog(builder, onClickListener);
        } else {
            showDisableDialog(builder, onClickListener);
        }
    }

    private void showDisableDialog(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        builder.setTitle((CharSequence) null);
        builder.setMessage(R.string.data_usage_disable_mobile);
        builder.setPositiveButton(android.R.string.ok, onClickListener);
        builder.setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null);
    }

    private void showMultiSimDialog(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        String string;
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubId);
        SubscriptionInfo defaultDataSubscriptionInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        if (defaultDataSubscriptionInfo == null) {
            string = getContext().getResources().getString(R.string.sim_selection_required_pref);
        } else {
            string = defaultDataSubscriptionInfo.getDisplayName().toString();
        }
        builder.setTitle(R.string.sim_change_data_title);
        Context context = getContext();
        Object[] objArr = new Object[2];
        objArr[0] = String.valueOf(activeSubscriptionInfo != null ? activeSubscriptionInfo.getDisplayName() : null);
        objArr[1] = string;
        builder.setMessage(context.getString(R.string.sim_change_data_message, objArr));
        builder.setPositiveButton(R.string.okay, onClickListener);
        builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
    }

    private void disableDataForOtherSubscriptions(int i) {
        List<SubscriptionInfo> activeSubscriptionInfoList = this.mSubscriptionManager.getActiveSubscriptionInfoList(true);
        if (activeSubscriptionInfoList != null) {
            for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                if (subscriptionInfo.getSubscriptionId() != i) {
                    this.mTelephonyManager.setDataEnabled(subscriptionInfo.getSubscriptionId(), false);
                }
            }
        }
    }

    @Override // com.android.settingslib.CustomDialogPreferenceCompat
    protected void onClick(DialogInterface dialogInterface, int i) {
        if (i != -1) {
            return;
        }
        if (this.mMultiSimDialog) {
            this.mSubscriptionManager.setDefaultDataSubId(this.mSubId);
            setMobileDataEnabled(true);
            disableDataForOtherSubscriptions(this.mSubId);
            return;
        }
        setMobileDataEnabled(false);
    }

    public static abstract class DataStateListener extends ContentObserver {
        public DataStateListener() {
            super(new Handler(Looper.getMainLooper()));
        }

        public void setListener(boolean z, int i, Context context) {
            if (z) {
                Uri uriFor = Settings.Global.getUriFor("mobile_data");
                if (TelephonyManager.getDefault().getSimCount() != 1) {
                    uriFor = Settings.Global.getUriFor("mobile_data" + i);
                }
                context.getContentResolver().registerContentObserver(uriFor, false, this);
                return;
            }
            context.getContentResolver().unregisterContentObserver(this);
        }
    }

    public static class CellDataState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<CellDataState> CREATOR = new Parcelable.Creator<CellDataState>() { // from class: com.android.car.developeroptions.datausage.CellDataPreference.CellDataState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CellDataState createFromParcel(Parcel parcel) {
                return new CellDataState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CellDataState[] newArray(int i) {
                return new CellDataState[i];
            }
        };
        public boolean mChecked;
        public boolean mMultiSimDialog;
        public int mSubId;

        public CellDataState(Parcelable parcelable) {
            super(parcelable);
        }

        public CellDataState(Parcel parcel) {
            super(parcel);
            this.mChecked = parcel.readByte() != 0;
            this.mMultiSimDialog = parcel.readByte() != 0;
            this.mSubId = parcel.readInt();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.mChecked ? (byte) 1 : (byte) 0);
            parcel.writeByte(this.mMultiSimDialog ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.mSubId);
        }
    }
}
