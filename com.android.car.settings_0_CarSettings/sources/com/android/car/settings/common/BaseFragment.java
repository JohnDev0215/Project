package com.android.car.settings.common;

import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.android.car.settings.R;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.baselayout.InsetsChangedListener;
import com.android.car.ui.core.CarUi;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.toolbar.ToolbarController;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseFragment extends Fragment implements CarUxRestrictionsManager.OnUxRestrictionsChangedListener, InsetsChangedListener {
    protected abstract int getLayoutId();

    public final FragmentHost getFragmentHost() {
        return (FragmentHost) requireActivity();
    }

    protected final CarUxRestrictions getCurrentRestrictions() {
        return ((UxRestrictionsProvider) getActivity()).getCarUxRestrictions();
    }

    protected boolean canBeShown(CarUxRestrictions carUxRestrictions) {
        return !CarUxRestrictionsHelper.isNoSetup(carUxRestrictions);
    }

    @Override // android.car.drivingstate.CarUxRestrictionsManager.OnUxRestrictionsChangedListener
    public void onUxRestrictionsChanged(CarUxRestrictions restrictionInfo) {
    }

    protected int getTitleId() {
        return R.string.settings_label;
    }

    protected List<MenuItem> getToolbarMenuItems() {
        return null;
    }

    protected Toolbar.State getToolbarState() {
        return Toolbar.State.SUBPAGE;
    }

    protected Toolbar.NavButtonMode getToolbarNavButtonStyle() {
        return Toolbar.NavButtonMode.BACK;
    }

    protected final ToolbarController getToolbar() {
        return CarUi.requireToolbar(requireActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(getActivity() instanceof FragmentHost)) {
            throw new IllegalStateException("Must attach to a FragmentHost");
        }
        if (!(getActivity() instanceof UxRestrictionsProvider)) {
            throw new IllegalStateException("Must attach to a UxRestrictionsProvider");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getLayoutId();
        return inflater.inflate(layoutId, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ToolbarController toolbar = getToolbar();
        if (toolbar != null) {
            List<MenuItem> items = getToolbarMenuItems();
            if (items != null) {
                if (items.size() == 1) {
                    items.get(0).setId(R.id.toolbar_menu_item_0);
                } else if (items.size() == 2) {
                    items.get(0).setId(R.id.toolbar_menu_item_0);
                    items.get(1).setId(R.id.toolbar_menu_item_1);
                }
            }
            toolbar.setTitle(getTitleId());
            toolbar.setMenuItems(items);
            toolbar.setState(getToolbarState());
            toolbar.setNavButtonMode(getToolbarNavButtonStyle());
        }
    }

    @Override // com.android.car.ui.baselayout.InsetsChangedListener
    public void onCarUiInsetsChanged(Insets insets) {
        View view = requireView();
        View recyclerView = view.findViewById(R.id.recycler_view);
        if (recyclerView != null) {
            recyclerView.setPadding(0, insets.getTop(), 0, insets.getBottom());
            view.setPadding(insets.getLeft(), 0, insets.getRight(), 0);
        } else {
            view.setPadding(insets.getLeft(), insets.getTop(), insets.getRight(), insets.getBottom());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        onUxRestrictionsChanged(getCurrentRestrictions());
        onCarUiInsetsChanged(CarUi.requireInsets(requireActivity()));
    }

    protected void onBackPressed() {
        getFragmentHost().goBack();
    }
}
