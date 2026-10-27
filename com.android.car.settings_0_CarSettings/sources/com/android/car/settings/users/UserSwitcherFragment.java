package com.android.car.settings.users;

import android.car.drivingstate.CarUxRestrictions;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseFragment;
import com.android.car.settings.common.CarUxRestrictionsHelper;

/* JADX INFO: loaded from: classes5.dex */
public class UserSwitcherFragment extends BaseFragment {
    private UserGridRecyclerView mUserGridView;

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return R.layout.user_switcher;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getTitleId() {
        return R.string.users_list_title;
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mUserGridView = (UserGridRecyclerView) getView().findViewById(R.id.user_grid);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), getContext().getResources().getInteger(R.integer.user_switcher_num_col));
        this.mUserGridView.setFragment(this);
        this.mUserGridView.setLayoutManager(layoutManager);
        this.mUserGridView.buildAdapter();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.android.car.settings.common.BaseFragment
    public boolean canBeShown(CarUxRestrictions carUxRestrictions) {
        return true;
    }

    @Override // com.android.car.settings.common.BaseFragment, android.car.drivingstate.CarUxRestrictionsManager.OnUxRestrictionsChangedListener
    public void onUxRestrictionsChanged(CarUxRestrictions restrictionInfo) {
        applyRestriction(CarUxRestrictionsHelper.isNoSetup(restrictionInfo));
    }

    private void applyRestriction(boolean restricted) {
        UserGridRecyclerView userGridRecyclerView = this.mUserGridView;
        if (userGridRecyclerView != null) {
            if (restricted) {
                userGridRecyclerView.disableAddUser();
            } else {
                userGridRecyclerView.enableAddUser();
            }
        }
    }
}
