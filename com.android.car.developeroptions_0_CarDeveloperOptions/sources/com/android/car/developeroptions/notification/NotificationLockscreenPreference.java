package com.android.car.developeroptions.notification;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.RestrictedListPreference;
import com.android.car.developeroptions.Utils;
import com.android.settingslib.RestrictedLockUtils;

/* JADX INFO: loaded from: classes.dex */
public class NotificationLockscreenPreference extends RestrictedListPreference {
    private RestrictedLockUtils.EnforcedAdmin mAdminRestrictingRemoteInput;
    private boolean mAllowRemoteInput;
    private Listener mListener;
    private boolean mRemoteInputCheckBoxEnabled;
    private boolean mShowRemoteInput;
    private int mUserId;

    @Override // com.android.car.developeroptions.CustomListPreference
    protected boolean isAutoClosePreference() {
        return false;
    }

    public NotificationLockscreenPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRemoteInputCheckBoxEnabled = true;
        this.mUserId = UserHandle.myUserId();
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    protected void onClick() {
        Context context = getContext();
        if (Utils.startQuietModeDialogIfNecessary(context, UserManager.get(context), this.mUserId)) {
            return;
        }
        super.onClick();
    }

    @Override // com.android.car.developeroptions.RestrictedListPreference, com.android.car.developeroptions.CustomListPreference
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder, DialogInterface.OnClickListener onClickListener) {
        this.mListener = new Listener(onClickListener);
        builder.setSingleChoiceItems(createListAdapter(builder.getContext()), getSelectedValuePos(), this.mListener);
        this.mShowRemoteInput = getEntryValues().length == 3;
        this.mAllowRemoteInput = Settings.Secure.getInt(getContext().getContentResolver(), "lock_screen_allow_remote_input", 0) != 0;
        builder.setView(R.layout.lockscreen_remote_input);
    }

    @Override // com.android.car.developeroptions.CustomListPreference
    protected void onDialogCreated(Dialog dialog) {
        super.onDialogCreated(dialog);
        dialog.create();
        CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.lockscreen_remote_input);
        checkBox.setChecked(!this.mAllowRemoteInput);
        checkBox.setOnCheckedChangeListener(this.mListener);
        checkBox.setEnabled(this.mAdminRestrictingRemoteInput == null);
        dialog.findViewById(R.id.restricted_lock_icon_remote_input).setVisibility(this.mAdminRestrictingRemoteInput == null ? 8 : 0);
        if (this.mAdminRestrictingRemoteInput != null) {
            checkBox.setClickable(false);
            dialog.findViewById(android.R.id.calendar).setOnClickListener(this.mListener);
        }
    }

    @Override // com.android.car.developeroptions.CustomListPreference
    protected void onDialogStateRestored(Dialog dialog, Bundle bundle) {
        super.onDialogStateRestored(dialog, bundle);
        int checkedItemPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
        View viewFindViewById = dialog.findViewById(android.R.id.calendar);
        viewFindViewById.setVisibility(checkboxVisibilityForSelectedIndex(checkedItemPosition, this.mShowRemoteInput));
        this.mListener.setView(viewFindViewById);
    }

    @Override // com.android.car.developeroptions.CustomListPreference
    protected void onDialogClosed(boolean z) {
        super.onDialogClosed(z);
        Settings.Secure.putInt(getContext().getContentResolver(), "lock_screen_allow_remote_input", this.mAllowRemoteInput ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int checkboxVisibilityForSelectedIndex(int i, boolean z) {
        return (i == 1 && z && this.mRemoteInputCheckBoxEnabled) ? 0 : 8;
    }

    private class Listener implements DialogInterface.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
        private final DialogInterface.OnClickListener mInner;
        private View mView;

        public Listener(DialogInterface.OnClickListener onClickListener) {
            this.mInner = onClickListener;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            this.mInner.onClick(dialogInterface, i);
            int checkedItemPosition = ((AlertDialog) dialogInterface).getListView().getCheckedItemPosition();
            View view = this.mView;
            if (view != null) {
                NotificationLockscreenPreference notificationLockscreenPreference = NotificationLockscreenPreference.this;
                view.setVisibility(notificationLockscreenPreference.checkboxVisibilityForSelectedIndex(checkedItemPosition, notificationLockscreenPreference.mShowRemoteInput));
            }
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            NotificationLockscreenPreference.this.mAllowRemoteInput = !z;
        }

        public void setView(View view) {
            this.mView = view;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() == 16908901) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(NotificationLockscreenPreference.this.getContext(), NotificationLockscreenPreference.this.mAdminRestrictingRemoteInput);
            }
        }
    }
}
