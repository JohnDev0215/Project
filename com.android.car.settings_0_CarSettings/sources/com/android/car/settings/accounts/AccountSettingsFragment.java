package com.android.car.settings.accounts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.car.settings.users.UserHelper;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class AccountSettingsFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.account_settings_fragment, "android.settings.SYNC_SETTINGS");
    private MenuItem mAddAccountButton;

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.account_settings_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mAddAccountButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean canModifyAccounts = UserHelper.getInstance(getContext()).canCurrentProcessModifyAccounts();
        this.mAddAccountButton = new MenuItem.Builder(getContext()).setTitle(R.string.user_add_account_menu).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.accounts.-$$Lambda$AccountSettingsFragment$WOg3WF8C2GuL5GFUlQS_Q2MGV3Y
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$AccountSettingsFragment(menuItem);
            }
        }).setVisible(canModifyAccounts).build();
    }

    public /* synthetic */ void lambda$onCreate$0$AccountSettingsFragment(MenuItem i) {
        onAddAccountClicked();
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        String[] authorities = getActivity().getIntent().getStringArrayExtra("authorities");
        if (authorities != null) {
            ((AccountListPreferenceController) use(AccountListPreferenceController.class, R.string.pk_account_list)).setAuthorities(authorities);
        }
    }

    private void onAddAccountClicked() {
        AccountTypesHelper helper = new AccountTypesHelper(getContext());
        Intent activityIntent = requireActivity().getIntent();
        String[] authorities = activityIntent.getStringArrayExtra("authorities");
        if (authorities != null) {
            helper.setAuthorities(Arrays.asList(authorities));
        }
        String[] accountTypesForFilter = activityIntent.getStringArrayExtra("account_types");
        if (accountTypesForFilter != null) {
            helper.setAccountTypesFilter(new HashSet(Arrays.asList(accountTypesForFilter)));
        }
        Set<String> authorizedAccountTypes = helper.getAuthorizedAccountTypes();
        if (authorizedAccountTypes.size() == 1) {
            String accountType = authorizedAccountTypes.iterator().next();
            startActivity(AddAccountActivity.createAddAccountActivityIntent(getContext(), accountType));
        } else {
            startActivity(new Intent(getContext(), (Class<?>) CarSettingActivities.ChooseAccountActivity.class));
        }
    }
}
