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
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.baselayout.Insets;
import com.android.car.ui.preference.ListPreferenceFragment;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.a;
import com.android.car.ui.rotary.FocusArea;
import com.android.car.ui.toolbar.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import spi.b2;
import spi.eb;
import spi.g6;
import spi.s7;
import spi.z4;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class ListPreferenceFragment extends Fragment implements z4 {
    public static final /* synthetic */ int e = 0;
    public ListPreference a;
    public a b;
    public int c = -1;
    public boolean d;

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

    public final void c() {
        ListPreference listPreference;
        if (this.c < 0 || (listPreference = this.a) == null) {
            return;
        }
        String string = listPreference.getEntryValues()[this.c].toString();
        if (this.a.callChangeListener(string)) {
            this.a.setValue(string);
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
        if (this.d) {
            return;
        }
        c();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        CarUiRecyclerView carUiRecyclerView = (CarUiRecyclerView) b2.n(view, R$id.list);
        this.d = getResources().getBoolean(R$bool.car_ui_preference_list_instant_change_callback);
        j jVarE = getTargetFragment() instanceof PreferenceFragment ? ((PreferenceFragment) getTargetFragment()).e() : null;
        carUiRecyclerView.setClipToPadding(false);
        String string = requireArguments().getString("key");
        DialogPreference.TargetFragment targetFragment = (DialogPreference.TargetFragment) getTargetFragment();
        if (string == null) {
            throw new IllegalStateException("ListPreference key not found in Fragment arguments");
        }
        if (targetFragment == null) {
            throw new IllegalStateException("Target fragment must be registered before displaying ListPreference screen.");
        }
        Preference preferenceFindPreference = targetFragment.findPreference(string);
        if (!(preferenceFindPreference instanceof ListPreference)) {
            throw new IllegalStateException("Cannot use ListPreferenceFragment with a preference that is not of type ListPreference");
        }
        ListPreference listPreference = (ListPreference) preferenceFindPreference;
        this.a = listPreference;
        if (jVarE != null) {
            jVarE.setTitle(listPreference.getTitle());
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
        CharSequence[] entries = this.a.getEntries();
        CharSequence[] entryValues = this.a.getEntryValues();
        if (entries == null || entryValues == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        if (entries.length != entryValues.length) {
            throw new IllegalStateException("ListPreference entries array length does not match entryValues array length.");
        }
        ListPreference listPreference2 = this.a;
        this.c = listPreference2.findIndexOfValue(listPreference2.getValue());
        final ArrayList arrayList = new ArrayList();
        final CarUiListItemAdapter carUiListItemAdapter = new CarUiListItemAdapter(arrayList);
        for (int i = 0; i < entries.length; i++) {
            String string2 = entries[i].toString();
            a aVar = new a(a.EnumC0011a.RADIO_BUTTON);
            aVar.c(string2);
            if (i == this.c) {
                aVar.a(true);
                this.b = aVar;
            }
            aVar.setOnCheckedChangeListener(new a.c() { // from class: spi.a6
                @Override // com.android.car.ui.recyclerview.a.c
                public final void a(com.android.car.ui.recyclerview.a aVar2, boolean z) {
                    ListPreferenceFragment listPreferenceFragment = this.a;
                    CarUiListItemAdapter carUiListItemAdapter2 = carUiListItemAdapter;
                    List list = arrayList;
                    int i2 = ListPreferenceFragment.e;
                    Objects.requireNonNull(listPreferenceFragment);
                    if (z) {
                        com.android.car.ui.recyclerview.a aVar3 = listPreferenceFragment.b;
                        if (aVar3 != null) {
                            aVar3.a(false);
                            carUiListItemAdapter2.notifyItemChanged(list.indexOf(listPreferenceFragment.b));
                        }
                        listPreferenceFragment.b = aVar2;
                        listPreferenceFragment.c = list.indexOf(aVar2);
                        if (listPreferenceFragment.d) {
                            listPreferenceFragment.c();
                        }
                    }
                }
            });
            arrayList.add(aVar);
        }
        carUiRecyclerView.setAdapter(carUiListItemAdapter);
        carUiRecyclerView.scrollToPosition(this.c);
        carUiRecyclerView.post(new g6(this, carUiRecyclerView, 3));
    }
}
