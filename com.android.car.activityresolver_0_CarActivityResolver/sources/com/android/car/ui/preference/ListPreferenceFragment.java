package com.android.car.ui.preference;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.ui.FocusArea;
import com.android.car.ui.R;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.baselayout.InsetsChangedListener;
import com.android.car.ui.core.CarUi;
import com.android.car.ui.recyclerview.CarUiContentListItem;
import com.android.car.ui.recyclerview.CarUiListItem;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.toolbar.ToolbarController;
import com.android.car.ui.utils.CarUiUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ListPreferenceFragment extends Fragment implements InsetsChangedListener {
    private static final String ARG_FULLSCREEN = "fullscreen";
    private boolean mFullScreen;
    private ListPreference mPreference;
    private int mSelectedIndex = -1;
    private CarUiContentListItem mSelectedItem;
    private boolean mUseInstantPreferenceChangeCallback;

    static ListPreferenceFragment newInstance(String key, boolean fullScreen) {
        ListPreferenceFragment fragment = new ListPreferenceFragment();
        Bundle b = new Bundle(1);
        b.putString("key", key);
        b.putBoolean(ARG_FULLSCREEN, fullScreen);
        fragment.setArguments(b);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (CarUi.getToolbar(getActivity()) == null) {
            return inflater.inflate(R.layout.car_ui_list_preference_with_toolbar, container, false);
        }
        return inflater.inflate(R.layout.car_ui_list_preference, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final CarUiRecyclerView carUiRecyclerView = (CarUiRecyclerView) CarUiUtils.requireViewByRefId(view, R.id.list);
        this.mFullScreen = requireArguments().getBoolean(ARG_FULLSCREEN, true);
        this.mUseInstantPreferenceChangeCallback = getResources().getBoolean(R.bool.car_ui_preference_list_instant_change_callback);
        ToolbarController toolbar = this.mFullScreen ? CarUi.getToolbar(getActivity()) : null;
        if (toolbar == null) {
            Toolbar toolbarView = (Toolbar) CarUiUtils.findViewByRefId(view, R.id.toolbar);
            toolbar = toolbarView;
            if (toolbarView != null) {
                carUiRecyclerView.setPadding(0, toolbarView.getHeight(), 0, 0);
                toolbarView.registerToolbarHeightChangeListener(new Toolbar.OnHeightChangedListener() { // from class: com.android.car.ui.preference.-$$Lambda$ListPreferenceFragment$HKa8RZ4bluJ2OmDLBl8YS7nrnUE
                    @Override // com.android.car.ui.toolbar.Toolbar.OnHeightChangedListener
                    public final void onHeightChanged(int i) {
                        ListPreferenceFragment.lambda$onViewCreated$0(carUiRecyclerView, view, i);
                    }
                });
            }
        }
        carUiRecyclerView.setClipToPadding(false);
        ListPreference listPreference = getListPreference();
        this.mPreference = listPreference;
        if (toolbar != null) {
            toolbar.setTitle(listPreference.getTitle());
            toolbar.setState(Toolbar.State.SUBPAGE);
        }
        CharSequence[] entries = this.mPreference.getEntries();
        CharSequence[] entryValues = this.mPreference.getEntryValues();
        if (entries == null || entryValues == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        if (entries.length != entryValues.length) {
            throw new IllegalStateException("ListPreference entries array length does not match entryValues array length.");
        }
        ListPreference listPreference2 = this.mPreference;
        this.mSelectedIndex = listPreference2.findIndexOfValue(listPreference2.getValue());
        final List<CarUiListItem> listItems = new ArrayList<>();
        final CarUiListItemAdapter adapter = new CarUiListItemAdapter(listItems);
        for (int i = 0; i < entries.length; i++) {
            String entry = entries[i].toString();
            CarUiContentListItem item = new CarUiContentListItem(CarUiContentListItem.Action.RADIO_BUTTON);
            item.setTitle(entry);
            if (i == this.mSelectedIndex) {
                item.setChecked(true);
                this.mSelectedItem = item;
            }
            item.setOnCheckedChangeListener(new CarUiContentListItem.OnCheckedChangeListener() { // from class: com.android.car.ui.preference.-$$Lambda$ListPreferenceFragment$rJYZvC0kZuHGeJHuQaBshexRSFM
                @Override // com.android.car.ui.recyclerview.CarUiContentListItem.OnCheckedChangeListener
                public final void onCheckedChanged(CarUiContentListItem carUiContentListItem, boolean z) {
                    this.f$0.lambda$onViewCreated$1$ListPreferenceFragment(adapter, listItems, carUiContentListItem, z);
                }
            });
            listItems.add(item);
        }
        carUiRecyclerView.setAdapter(adapter);
    }

    static /* synthetic */ void lambda$onViewCreated$0(CarUiRecyclerView carUiRecyclerView, View view, int newHeight) {
        if (carUiRecyclerView.getPaddingTop() == newHeight) {
            return;
        }
        int oldHeight = carUiRecyclerView.getPaddingTop();
        carUiRecyclerView.setPadding(0, newHeight, 0, 0);
        carUiRecyclerView.scrollBy(0, oldHeight - newHeight);
        FocusArea focusArea = (FocusArea) view.findViewById(R.id.car_ui_focus_area);
        if (focusArea != null) {
            focusArea.setHighlightPadding(0, newHeight, 0, 0);
            focusArea.setBoundsOffset(0, newHeight, 0, 0);
        }
    }

    public /* synthetic */ void lambda$onViewCreated$1$ListPreferenceFragment(CarUiListItemAdapter adapter, List listItems, CarUiContentListItem listItem, boolean isChecked) {
        if (!isChecked) {
            return;
        }
        CarUiContentListItem carUiContentListItem = this.mSelectedItem;
        if (carUiContentListItem != null) {
            carUiContentListItem.setChecked(false);
            adapter.notifyItemChanged(listItems.indexOf(this.mSelectedItem));
        }
        this.mSelectedItem = listItem;
        this.mSelectedIndex = listItems.indexOf(listItem);
        if (this.mUseInstantPreferenceChangeCallback) {
            updatePreference();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Insets insets = CarUi.getInsets(getActivity());
        if (insets != null) {
            onCarUiInsetsChanged(insets);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        if (!this.mUseInstantPreferenceChangeCallback) {
            updatePreference();
        }
    }

    private void updatePreference() {
        ListPreference listPreference;
        if (this.mSelectedIndex >= 0 && (listPreference = this.mPreference) != null) {
            String entryValue = listPreference.getEntryValues()[this.mSelectedIndex].toString();
            if (this.mPreference.callChangeListener(entryValue)) {
                this.mPreference.setValue(entryValue);
            }
        }
    }

    private ListPreference getListPreference() {
        String key = requireArguments().getString("key");
        DialogPreference.TargetFragment fragment = (DialogPreference.TargetFragment) getTargetFragment();
        if (key == null) {
            throw new IllegalStateException("ListPreference key not found in Fragment arguments");
        }
        if (fragment == null) {
            throw new IllegalStateException("Target fragment must be registered before displaying ListPreference screen.");
        }
        Preference preference = fragment.findPreference(key);
        if (!(preference instanceof ListPreference)) {
            throw new IllegalStateException("Cannot use ListPreferenceFragment with a preference that is not of type ListPreference");
        }
        return (ListPreference) preference;
    }

    @Override // com.android.car.ui.baselayout.InsetsChangedListener
    public void onCarUiInsetsChanged(Insets insets) {
        if (!this.mFullScreen) {
            return;
        }
        View view = requireView();
        CarUiUtils.requireViewByRefId(view, R.id.list).setPadding(0, insets.getTop(), 0, insets.getBottom());
        view.setPadding(insets.getLeft(), 0, insets.getRight(), 0);
        FocusArea focusArea = (FocusArea) view.findViewById(R.id.car_ui_focus_area);
        if (focusArea != null) {
            focusArea.setHighlightPadding(0, insets.getTop(), 0, insets.getBottom());
            focusArea.setBoundsOffset(0, insets.getTop(), 0, insets.getBottom());
        }
    }
}
