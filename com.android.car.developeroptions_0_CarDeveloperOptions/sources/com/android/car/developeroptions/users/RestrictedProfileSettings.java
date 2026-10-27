package com.android.car.developeroptions.users;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.users.EditUserInfoController;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedProfileSettings extends AppRestrictionsFragment implements EditUserInfoController.OnContentChangedCallback {
    private ImageView mDeleteButton;
    private EditUserInfoController mEditUserInfoController = new EditUserInfoController();
    private View mHeaderView;
    private ImageView mUserIconView;
    private TextView mUserNameView;

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        if (i != 1) {
            return i != 2 ? 0 : 591;
        }
        return 590;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.mEditUserInfoController.onRestoreInstanceState(bundle);
        }
        init(bundle);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        View pinnedHeaderView = setPinnedHeaderView(R.layout.user_info_header);
        this.mHeaderView = pinnedHeaderView;
        pinnedHeaderView.setOnClickListener(this);
        this.mUserIconView = (ImageView) this.mHeaderView.findViewById(android.R.id.icon);
        this.mUserNameView = (TextView) this.mHeaderView.findViewById(android.R.id.title);
        ImageView imageView = (ImageView) this.mHeaderView.findViewById(R.id.delete);
        this.mDeleteButton = imageView;
        imageView.setOnClickListener(this);
        super.onActivityCreated(bundle);
    }

    @Override // com.android.car.developeroptions.users.AppRestrictionsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mEditUserInfoController.onSaveInstanceState(bundle);
    }

    @Override // com.android.car.developeroptions.users.AppRestrictionsFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        UserInfo existingUser = Utils.getExistingUser(this.mUserManager, this.mUser);
        if (existingUser == null) {
            finishFragment();
        } else {
            ((TextView) this.mHeaderView.findViewById(android.R.id.title)).setText(existingUser.name);
            ((ImageView) this.mHeaderView.findViewById(android.R.id.icon)).setImageDrawable(com.android.settingslib.Utils.getUserIcon(getActivity(), this.mUserManager, existingUser));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void startActivityForResult(Intent intent, int i) {
        this.mEditUserInfoController.startingActivityForResult();
        super.startActivityForResult(intent, i);
    }

    @Override // com.android.car.developeroptions.users.AppRestrictionsFragment, androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mEditUserInfoController.onActivityResult(i, i2, intent);
    }

    @Override // com.android.car.developeroptions.users.AppRestrictionsFragment, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mHeaderView) {
            showDialog(1);
        } else if (view == this.mDeleteButton) {
            showDialog(2);
        } else {
            super.onClick(view);
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (i == 1) {
            return this.mEditUserInfoController.createDialog(this, this.mUserIconView.getDrawable(), this.mUserNameView.getText(), R.string.profile_info_settings_title, this, this.mUser);
        }
        if (i == 2) {
            return UserDialogs.createRemoveDialog(getActivity(), this.mUser.getIdentifier(), new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.RestrictedProfileSettings.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    RestrictedProfileSettings.this.removeUser();
                }
            });
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeUser() {
        getView().post(new Runnable() { // from class: com.android.car.developeroptions.users.RestrictedProfileSettings.2
            @Override // java.lang.Runnable
            public void run() {
                RestrictedProfileSettings restrictedProfileSettings = RestrictedProfileSettings.this;
                restrictedProfileSettings.mUserManager.removeUser(restrictedProfileSettings.mUser.getIdentifier());
                RestrictedProfileSettings.this.finishFragment();
            }
        });
    }

    @Override // com.android.car.developeroptions.users.EditUserInfoController.OnContentChangedCallback
    public void onPhotoChanged(Drawable drawable) {
        this.mUserIconView.setImageDrawable(drawable);
    }

    @Override // com.android.car.developeroptions.users.EditUserInfoController.OnContentChangedCallback
    public void onLabelChanged(CharSequence charSequence) {
        this.mUserNameView.setText(charSequence);
    }
}
