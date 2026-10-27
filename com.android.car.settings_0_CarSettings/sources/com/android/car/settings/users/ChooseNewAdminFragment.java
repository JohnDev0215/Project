package com.android.car.settings.users;

import android.content.Context;
import android.content.pm.UserInfo;
import android.os.Bundle;
import com.android.car.settings.R;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.ui.toolbar.MenuItem;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class ChooseNewAdminFragment extends SettingsFragment {
    private MenuItem mCancelButton;

    public static ChooseNewAdminFragment newInstance(UserInfo adminInfo) {
        ChooseNewAdminFragment usersListFragment = new ChooseNewAdminFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.intent.extra.USER", adminInfo);
        usersListFragment.setArguments(bundle);
        return usersListFragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Collections.singletonList(this.mCancelButton);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mCancelButton = new MenuItem.Builder(getContext()).setTitle(R.string.cancel).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.users.-$$Lambda$ChooseNewAdminFragment$eqD9mRbwUojKPgwCuX7Mc97Wi7w
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$0$ChooseNewAdminFragment(menuItem);
            }
        }).build();
    }

    public /* synthetic */ void lambda$onCreate$0$ChooseNewAdminFragment(MenuItem i) {
        requireActivity().onBackPressed();
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.choose_new_admin_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle arguments = getArguments();
        Objects.requireNonNull(arguments);
        UserInfo adminInfo = arguments.getParcelable("android.intent.extra.USER");
        ((ChooseNewAdminPreferenceController) use(ChooseNewAdminPreferenceController.class, R.string.pk_choose_new_admin)).setAdminInfo(adminInfo);
    }
}
