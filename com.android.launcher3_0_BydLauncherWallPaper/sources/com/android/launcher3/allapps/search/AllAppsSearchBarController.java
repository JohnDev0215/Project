package com.android.launcher3.allapps.search;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.android.launcher3.ExtendedEditText;
import com.android.launcher3.Launcher;
import com.android.launcher3.Utilities;
import com.android.launcher3.model.AppLaunchTracker;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageManagerHelper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsSearchBarController implements TextWatcher, TextView.OnEditorActionListener, ExtendedEditText.OnBackKeyListener, View.OnFocusChangeListener {
    protected Callbacks mCb;
    protected ExtendedEditText mInput;
    protected Launcher mLauncher;
    protected String mQuery;
    protected SearchAlgorithm mSearchAlgorithm;

    public interface Callbacks {
        void clearSearchResult();

        void onSearchResult(String query, ArrayList<ComponentKey> apps);
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    public void setVisibility(int visibility) {
        this.mInput.setVisibility(visibility);
    }

    public final void initialize(SearchAlgorithm searchAlgorithm, ExtendedEditText input, Launcher launcher, Callbacks cb) {
        this.mCb = cb;
        this.mLauncher = launcher;
        this.mInput = input;
        input.addTextChangedListener(this);
        this.mInput.setOnEditorActionListener(this);
        this.mInput.setOnBackKeyListener(this);
        this.mInput.setOnFocusChangeListener(this);
        this.mSearchAlgorithm = searchAlgorithm;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(final Editable s) {
        String string = s.toString();
        this.mQuery = string;
        if (string.isEmpty()) {
            this.mSearchAlgorithm.cancel(true);
            this.mCb.clearSearchResult();
        } else {
            this.mSearchAlgorithm.cancel(false);
            this.mSearchAlgorithm.doSearch(this.mQuery, this.mCb);
        }
    }

    public void refreshSearchResult() {
        if (TextUtils.isEmpty(this.mQuery)) {
            return;
        }
        this.mSearchAlgorithm.cancel(false);
        this.mSearchAlgorithm.doSearch(this.mQuery, this.mCb);
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId != 3) {
            return false;
        }
        String string = v.getText().toString();
        if (string.isEmpty()) {
            return false;
        }
        Launcher launcher = this.mLauncher;
        return launcher.lambda$startActivitySafely$0$Launcher(v, PackageManagerHelper.getMarketSearchIntent(launcher, string), null, AppLaunchTracker.CONTAINER_SEARCH);
    }

    @Override // com.android.launcher3.ExtendedEditText.OnBackKeyListener
    public boolean onBackKey() {
        if (!Utilities.trim(this.mInput.getEditableText().toString()).isEmpty()) {
            return false;
        }
        reset();
        return true;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            return;
        }
        this.mInput.hideKeyboard();
    }

    public void reset() {
        this.mCb.clearSearchResult();
        this.mInput.reset();
        this.mQuery = null;
    }

    public void focusSearchField() {
        this.mInput.showKeyboard();
    }

    public boolean isSearchFieldFocused() {
        return this.mInput.isFocused();
    }
}
