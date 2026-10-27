package com.android.car.ui.preference;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;
import androidx.preference.Preference;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.a;
import com.android.car.ui.rotary.FocusArea;
import com.android.car.ui.toolbar.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import spi.b2;
import spi.b6;
import spi.eb;
import spi.s7;
import spi.z4;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class MultiSelectListPreferenceFragment extends Fragment implements z4 {
    public CarUiMultiSelectListPreference a;
    public Set<String> b;
    public boolean c;

    @Override // spi.z4
    public final void b(@NonNull Insets insets) {
        View viewRequireView = requireView();
        b2.n(viewRequireView, R$id.list).setPadding(0, insets.getTop(), 0, insets.getBottom());
        viewRequireView.setPadding(insets.getLeft(), 0, insets.getRight(), 0);
        FocusArea focusArea = (FocusArea) viewRequireView.findViewById(R$id.car_ui_focus_area);
        if (focusArea != null) {
            focusArea.b(0, insets.getTop(), 0, insets.getBottom());
            focusArea.a(0, insets.getTop(), 0, insets.getBottom());
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R$layout.car_ui_list_preference, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStart() {
        Insets insetsD;
        super.onStart();
        if (!(getTargetFragment() instanceof PreferenceFragment) || (insetsD = ((PreferenceFragment) getTargetFragment()).d()) == null) {
            return;
        }
        b(insetsD);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        if (this.c || !this.a.callChangeListener(this.b)) {
            return;
        }
        this.a.setValues(this.b);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        CarUiRecyclerView carUiRecyclerView = (CarUiRecyclerView) b2.n(view, R$id.list);
        this.c = getResources().getBoolean(R$bool.car_ui_preference_list_instant_change_callback);
        j jVarE = getTargetFragment() instanceof PreferenceFragment ? ((PreferenceFragment) getTargetFragment()).e() : null;
        if (getArguments() == null) {
            throw new IllegalStateException("Preference arguments cannot be null");
        }
        String string = getArguments().getString("key");
        DialogPreference.TargetFragment targetFragment = (DialogPreference.TargetFragment) getTargetFragment();
        if (string == null) {
            throw new IllegalStateException("MultiSelectListPreference key not found in Fragment arguments");
        }
        if (targetFragment == null) {
            throw new IllegalStateException("Target fragment must be registered before displaying MultiSelectListPreference screen.");
        }
        Preference preferenceFindPreference = targetFragment.findPreference(string);
        if (!(preferenceFindPreference instanceof CarUiMultiSelectListPreference)) {
            throw new IllegalStateException("Cannot use MultiSelectListPreferenceFragment with a preference that is not of type CarUiMultiSelectListPreference");
        }
        this.a = (CarUiMultiSelectListPreference) preferenceFindPreference;
        carUiRecyclerView.setClipToPadding(false);
        if (jVarE != null) {
            jVarE.setTitle(this.a.getTitle());
            jVarE.setSubtitle("");
            if (jVarE.isStateSet()) {
                jVarE.setState(eb.SUBPAGE);
            } else {
                jVarE.setNavButtonMode(s7.BACK);
            }
            jVarE.setLogo(null);
            jVarE.setMenuItems(null);
            jVarE.setTabs(Collections.emptyList());
        }
        this.b = new HashSet(this.a.getValues());
        CharSequence[] entries = this.a.getEntries();
        CharSequence[] entryValues = this.a.getEntryValues();
        if (entries == null || entryValues == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        if (entries.length != entryValues.length) {
            throw new IllegalStateException("MultiSelectListPreference entries array length does not match entryValues array length.");
        }
        ArrayList arrayList = new ArrayList();
        boolean[] selectedItems = this.a.getSelectedItems();
        for (int i = 0; i < entries.length; i++) {
            String string2 = entries[i].toString();
            String string3 = entryValues[i].toString();
            a aVar = new a(a.EnumC0011a.CHECK_BOX);
            aVar.c(string2);
            aVar.a(selectedItems[i]);
            aVar.setOnCheckedChangeListener(new b6(this, string3));
            arrayList.add(aVar);
        }
        carUiRecyclerView.setAdapter(new CarUiListItemAdapter(arrayList));
    }
}
