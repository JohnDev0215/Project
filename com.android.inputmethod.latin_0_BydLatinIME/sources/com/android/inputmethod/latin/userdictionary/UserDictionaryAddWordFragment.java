package com.android.inputmethod.latin.userdictionary;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.userdictionary.UserDictionaryAddWordContents;
import com.android.inputmethod.latin.userdictionary.UserDictionaryLocalePicker;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class UserDictionaryAddWordFragment extends Fragment implements AdapterView.OnItemSelectedListener, UserDictionaryLocalePicker.LocationChangedListener {
    private static final int OPTIONS_MENU_ADD = 1;
    private static final int OPTIONS_MENU_DELETE = 2;
    private UserDictionaryAddWordContents mContents;
    private boolean mIsDeleting = false;
    private View mRootView;

    @Override // android.app.Fragment
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().getActionBar().setTitle(R.string.edit_personal_dictionary);
        setRetainInstance(true);
    }

    @Override // android.app.Fragment
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedState) {
        this.mRootView = inflater.inflate(R.layout.user_dictionary_add_word_fullscreen, (ViewGroup) null);
        this.mIsDeleting = false;
        if (this.mContents == null) {
            this.mContents = new UserDictionaryAddWordContents(this.mRootView, getArguments());
        } else {
            this.mContents = new UserDictionaryAddWordContents(this.mRootView, this.mContents);
        }
        getActivity().getActionBar().setSubtitle(UserDictionarySettingsUtils.getLocaleDisplayName(getActivity(), this.mContents.getCurrentUserDictionaryLocale()));
        return this.mRootView;
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        menu.add(0, 1, 0, R.string.user_dict_settings_add_menu_title).setIcon(R.drawable.ic_menu_add).setShowAsAction(5);
        menu.add(0, 2, 0, R.string.user_dict_settings_delete).setIcon(android.R.drawable.ic_menu_delete).setShowAsAction(5);
    }

    @Override // android.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            getActivity().onBackPressed();
            return true;
        }
        if (item.getItemId() != 2) {
            return false;
        }
        this.mContents.delete(getActivity());
        this.mIsDeleting = true;
        getActivity().onBackPressed();
        return true;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        updateSpinner();
    }

    private void updateSpinner() {
        ArrayList<UserDictionaryAddWordContents.LocaleRenderer> localesList = this.mContents.getLocalesList(getActivity());
        Spinner spinner = (Spinner) this.mRootView.findViewById(R.id.user_dictionary_add_locale);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, localesList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.mIsDeleting) {
            return;
        }
        this.mContents.apply(getActivity(), null);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(final AdapterView<?> parent, final View view, final int pos, final long id) {
        UserDictionaryAddWordContents.LocaleRenderer localeRenderer = (UserDictionaryAddWordContents.LocaleRenderer) parent.getItemAtPosition(pos);
        if (localeRenderer.isMoreLanguages()) {
            ((PreferenceActivity) getActivity()).startPreferenceFragment(new UserDictionaryLocalePicker(), true);
        } else {
            this.mContents.updateLocale(localeRenderer.getLocaleString());
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(final AdapterView<?> parent) {
        this.mContents.updateLocale(getArguments().getString("locale"));
    }

    @Override // com.android.inputmethod.latin.userdictionary.UserDictionaryLocalePicker.LocationChangedListener
    public void onLocaleSelected(final Locale locale) {
        this.mContents.updateLocale(locale.toString());
        getActivity().onBackPressed();
    }
}
