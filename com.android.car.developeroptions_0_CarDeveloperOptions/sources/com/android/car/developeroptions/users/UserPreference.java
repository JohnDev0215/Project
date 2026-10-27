package com.android.car.developeroptions.users;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.RestrictedPreference;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class UserPreference extends RestrictedPreference {
    public static final Comparator<UserPreference> SERIAL_NUMBER_COMPARATOR = new Comparator() { // from class: com.android.car.developeroptions.users.-$$Lambda$UserPreference$i09TtzbMvjUp7tYlewUQQMXrhXU
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return UserPreference.lambda$static$0((UserPreference) obj, (UserPreference) obj2);
        }
    };
    private View.OnClickListener mDeleteClickListener;
    private int mSerialNumber;
    private View.OnClickListener mSettingsClickListener;
    private int mUserId;

    static /* synthetic */ int lambda$static$0(UserPreference userPreference, UserPreference userPreference2) {
        if (userPreference == null) {
            return -1;
        }
        if (userPreference2 == null) {
            return 1;
        }
        int serialNumber = userPreference.getSerialNumber();
        int serialNumber2 = userPreference2.getSerialNumber();
        if (serialNumber < serialNumber2) {
            return -1;
        }
        return serialNumber > serialNumber2 ? 1 : 0;
    }

    public UserPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -10, null, null);
    }

    UserPreference(Context context, AttributeSet attributeSet, int i, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context, attributeSet);
        this.mSerialNumber = -1;
        this.mUserId = -10;
        if (onClickListener2 != null || onClickListener != null) {
            setWidgetLayoutResource(R.layout.restricted_preference_user_delete_widget);
        }
        this.mDeleteClickListener = onClickListener2;
        this.mSettingsClickListener = onClickListener;
        this.mUserId = i;
        useAdminDisabledSummary(true);
    }

    private void dimIcon(boolean z) {
        Drawable icon = getIcon();
        if (icon != null) {
            icon.mutate().setAlpha(z ? 102 : 255);
            setIcon(icon);
        }
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference
    protected boolean shouldHideSecondTarget() {
        if (isDisabledByAdmin()) {
            return true;
        }
        return !canDeleteUser() && this.mSettingsClickListener == null;
    }

    @Override // com.android.settingslib.RestrictedPreference, com.android.settingslib.TwoTargetPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        boolean zIsDisabledByAdmin = isDisabledByAdmin();
        dimIcon(zIsDisabledByAdmin);
        View viewFindViewById = preferenceViewHolder.findViewById(R.id.user_delete_widget);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(zIsDisabledByAdmin ? 8 : 0);
        }
        if (zIsDisabledByAdmin) {
            return;
        }
        View viewFindViewById2 = preferenceViewHolder.findViewById(R.id.divider_delete);
        View viewFindViewById3 = preferenceViewHolder.findViewById(R.id.divider_manage);
        View viewFindViewById4 = preferenceViewHolder.findViewById(R.id.trash_user);
        if (viewFindViewById4 != null) {
            if (canDeleteUser()) {
                viewFindViewById4.setVisibility(0);
                viewFindViewById2.setVisibility(0);
                viewFindViewById4.setOnClickListener(this.mDeleteClickListener);
                viewFindViewById4.setTag(this);
            } else {
                viewFindViewById4.setVisibility(8);
                viewFindViewById2.setVisibility(8);
            }
        }
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(R.id.manage_user);
        if (imageView != null) {
            if (this.mSettingsClickListener != null) {
                imageView.setVisibility(0);
                viewFindViewById3.setVisibility(this.mDeleteClickListener != null ? 8 : 0);
                imageView.setOnClickListener(this.mSettingsClickListener);
                imageView.setTag(this);
                return;
            }
            imageView.setVisibility(8);
            viewFindViewById3.setVisibility(8);
        }
    }

    private boolean canDeleteUser() {
        return (this.mDeleteClickListener == null || RestrictedLockUtilsInternal.hasBaseUserRestriction(getContext(), "no_remove_user", UserHandle.myUserId())) ? false : true;
    }

    private int getSerialNumber() {
        if (this.mUserId == UserHandle.myUserId()) {
            return Integer.MIN_VALUE;
        }
        if (this.mSerialNumber < 0) {
            int i = this.mUserId;
            if (i == -10) {
                return Integer.MAX_VALUE;
            }
            if (i == -11) {
                return 2147483646;
            }
            int userSerialNumber = ((UserManager) getContext().getSystemService("user")).getUserSerialNumber(this.mUserId);
            this.mSerialNumber = userSerialNumber;
            if (userSerialNumber < 0) {
                return this.mUserId;
            }
        }
        return this.mSerialNumber;
    }

    public int getUserId() {
        return this.mUserId;
    }
}
