package com.neusoft.optimus.utility.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractFragmentNavigator<Presenter> {
    private FragmentManager _fragmentManager;
    private Map<Integer, Fragment> _frmStorage;
    private boolean _isAllLoaded;
    private List<Integer> _plugIn;
    private Fragment _previousFragment;
    private int _resId;

    protected abstract List<Integer> buildPlugIn();

    protected abstract Fragment createFragment(int i, Presenter presenter, AbstractFragmentNavigator abstractFragmentNavigator);

    protected void insertViewBegin() {
    }

    public AbstractFragmentNavigator() {
        this(true);
    }

    public AbstractFragmentNavigator(boolean z) {
        this._frmStorage = new HashMap();
        this._isAllLoaded = z;
    }

    public void initialize(FragmentManager fragmentManager, int i) {
        this._fragmentManager = fragmentManager;
        this._resId = i;
    }

    public void finalized() {
        this._fragmentManager = null;
        this._previousFragment = null;
        this._frmStorage.clear();
    }

    protected FragmentManager getManager() {
        return this._fragmentManager;
    }

    public void insertView(int i, Fragment fragment) {
        if (this._isAllLoaded) {
            insertViewAll(i, fragment);
        } else {
            insertViewUnAll(i, fragment);
        }
    }

    private void insertViewAll(int i, Fragment fragment) {
        if (fragment.isAdded()) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = this._fragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.add(this._resId, fragment, Integer.toString(i));
        fragmentTransactionBeginTransaction.hide(fragment);
        fragmentTransactionBeginTransaction.commit();
    }

    private void insertViewUnAll(int i, Fragment fragment) {
        this._frmStorage.put(Integer.valueOf(i), fragment);
    }

    protected void insertViewFinish() {
        this._fragmentManager.executePendingTransactions();
    }

    public void gotoView(int i) {
        if (this._isAllLoaded) {
            gotoViewAll(i);
        } else {
            gotoViewUnAll(i);
        }
    }

    private void gotoViewUnAll(int i) {
        Fragment fragment = this._frmStorage.get(Integer.valueOf(i));
        if (fragment == null) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = this._fragmentManager.beginTransaction();
        Fragment fragment2 = this._previousFragment;
        if (fragment2 != null) {
            fragmentTransactionBeginTransaction.hide(fragment2);
        }
        if (fragment.isAdded()) {
            fragmentTransactionBeginTransaction.show(fragment);
        } else {
            fragmentTransactionBeginTransaction.add(this._resId, fragment);
        }
        fragmentTransactionBeginTransaction.commit();
        this._previousFragment = fragment;
    }

    private void gotoViewAll(int i) {
        Fragment fragmentFindFragmentByTag = this._fragmentManager.findFragmentByTag(Integer.toString(i));
        if (fragmentFindFragmentByTag == null) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = this._fragmentManager.beginTransaction();
        Fragment fragment = this._previousFragment;
        if (fragment != null) {
            fragmentTransactionBeginTransaction.hide(fragment);
        }
        fragmentTransactionBeginTransaction.show(fragmentFindFragmentByTag);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        this._previousFragment = fragmentFindFragmentByTag;
    }

    public List<Integer> acquirePlugIn() {
        return this._plugIn;
    }

    public void buildFragment(Presenter presenter) {
        this._plugIn = buildPlugIn();
        insertViewBegin();
        Iterator<Integer> it = this._plugIn.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            insertView(iIntValue, createFragment(iIntValue, presenter, this));
        }
        insertViewFinish();
    }
}
