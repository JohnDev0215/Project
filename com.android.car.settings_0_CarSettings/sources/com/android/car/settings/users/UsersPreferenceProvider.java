package com.android.car.settings.users;

import android.content.Context;
import android.content.pm.UserInfo;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiPreference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes5.dex */
public class UsersPreferenceProvider {
    private final Context mContext;
    private boolean mIncludeCurrentUser = true;
    private boolean mIncludeGuest = true;
    private final UserClickListener mUserPreferenceClickListener;

    public interface UserClickListener {
        void onUserClicked(UserInfo userInfo);
    }

    public UsersPreferenceProvider(Context context, UserClickListener listener) {
        this.mContext = context;
        this.mUserPreferenceClickListener = listener;
    }

    public void setIncludeCurrentUser(boolean includeCurrentUser) {
        this.mIncludeCurrentUser = includeCurrentUser;
    }

    public void setIncludeGuest(boolean includeGuest) {
        this.mIncludeGuest = includeGuest;
    }

    public List<Preference> createUserList() {
        List<Preference> users = new ArrayList<>();
        final UserInfo currUserInfo = UserHelper.getInstance(this.mContext).getCurrentProcessUserInfo();
        if (this.mIncludeCurrentUser) {
            users.add(createUserPreference(currUserInfo));
        }
        List<UserInfo> infos = UserHelper.getInstance(this.mContext).getAllLivingUsers(new Predicate() { // from class: com.android.car.settings.users.-$$Lambda$UsersPreferenceProvider$yIp18eoElhyTQMQ9STghahQWdvE
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return UsersPreferenceProvider.lambda$createUserList$0(currUserInfo, (UserInfo) obj);
            }
        });
        for (UserInfo userInfo : infos) {
            users.add(createUserPreference(userInfo));
        }
        if (this.mIncludeGuest) {
            users.add(createGuestUserPreference());
        }
        return users;
    }

    static /* synthetic */ boolean lambda$createUserList$0(UserInfo currUserInfo, UserInfo userInfo) {
        return (userInfo.isGuest() || userInfo.id == currUserInfo.id) ? false : true;
    }

    private Preference createUserPreference(final UserInfo userInfo) {
        CarUiPreference preference = new CarUiPreference(this.mContext);
        preference.setIcon(new UserIconProvider().getRoundedUserIcon(userInfo, this.mContext));
        preference.setTitle(UserUtils.getUserDisplayName(this.mContext, userInfo));
        if (!userInfo.isInitialized()) {
            preference.setSummary(R.string.user_summary_not_set_up);
        }
        if (userInfo.isAdmin()) {
            preference.setSummary(isCurrentUser(userInfo) ? R.string.signed_in_admin_user : R.string.user_admin);
        }
        preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.settings.users.-$$Lambda$UsersPreferenceProvider$dq2Fs0UviupL1yNNnQBRmEgAbis
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference2) {
                return this.f$0.lambda$createUserPreference$1$UsersPreferenceProvider(userInfo, preference2);
            }
        });
        return preference;
    }

    public /* synthetic */ boolean lambda$createUserPreference$1$UsersPreferenceProvider(UserInfo userInfo, Preference pref) {
        UserClickListener userClickListener = this.mUserPreferenceClickListener;
        if (userClickListener == null) {
            return false;
        }
        userClickListener.onUserClicked(userInfo);
        return true;
    }

    private Preference createGuestUserPreference() {
        CarUiPreference preference = new CarUiPreference(this.mContext);
        preference.setIcon(new UserIconProvider().getRoundedGuestDefaultIcon(this.mContext.getResources()));
        preference.setTitle(R.string.user_guest);
        return preference;
    }

    private boolean isCurrentUser(UserInfo userInfo) {
        return UserHelper.getInstance(this.mContext).isCurrentProcessUser(userInfo);
    }
}
