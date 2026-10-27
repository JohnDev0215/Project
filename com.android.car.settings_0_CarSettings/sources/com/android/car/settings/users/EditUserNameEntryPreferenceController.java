package com.android.car.settings.users;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.ui.preference.CarUiTwoActionIconPreference;

/* JADX INFO: loaded from: classes5.dex */
public class EditUserNameEntryPreferenceController extends UserDetailsBasePreferenceController<CarUiTwoActionIconPreference> {
    private final UserHelper mUserHelper;

    public EditUserNameEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mUserHelper = UserHelper.getInstance(getContext());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<CarUiTwoActionIconPreference> getPreferenceType() {
        return CarUiTwoActionIconPreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(CarUiTwoActionIconPreference preference) {
        preference.setOnSecondaryActionClickListener(new Runnable() { // from class: com.android.car.settings.users.-$$Lambda$EditUserNameEntryPreferenceController$kxYjSpWwJSO8YalghfdGdoEcV9Y
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateState$0$EditUserNameEntryPreferenceController();
            }
        });
        Drawable icon = new UserIconProvider().getRoundedUserIcon(getUserInfo(), getContext());
        preference.setIcon(icon);
        preference.setTitle(UserUtils.getUserDisplayName(getContext(), getUserInfo()));
        if (!this.mUserHelper.isCurrentProcessUser(getUserInfo())) {
            preference.setSecondaryActionVisible(false);
        }
        preference.setSummary(getSummary());
    }

    public /* synthetic */ void lambda$updateState$0$EditUserNameEntryPreferenceController() {
        getFragmentController().launchFragment(EditUsernameFragment.newInstance(getUserInfo()));
    }

    private CharSequence getSummary() {
        if (!getUserInfo().isInitialized()) {
            return getContext().getString(R.string.user_summary_not_set_up);
        }
        if (getUserInfo().isAdmin()) {
            if (this.mUserHelper.isCurrentProcessUser(getUserInfo())) {
                return getContext().getString(R.string.signed_in_admin_user);
            }
            return getContext().getString(R.string.user_admin);
        }
        return null;
    }
}
