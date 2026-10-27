package com.neusoft.optimus.utility.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import com.neusoft.optimus.utility.log.LogTool;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentNavigator {
    private static final String cTag = "AbstractFragmentNavigator";
    public int _curId;
    private FragmentManager _fragmentManager;
    private boolean _isAllLoaded;
    private List<Integer> _plugIn;
    private int _resId;

    protected abstract List<Integer> buildPlugIn();

    protected abstract Fragment createFragment(int i, FragmentNavigator fragmentNavigator);

    public abstract int getPagerByMediumId(int i);

    protected void insertViewBegin() {
    }

    public abstract void updateMediumMap(int i, int i2);

    public FragmentNavigator() {
        this(true);
    }

    public FragmentNavigator(boolean z) {
        this._curId = -1;
        this._isAllLoaded = z;
    }

    public void initialize(FragmentManager fragmentManager, int i) {
        this._fragmentManager = fragmentManager;
        this._resId = i;
    }

    public void finalized() {
        this._fragmentManager = null;
    }

    protected FragmentManager getManager() {
        return this._fragmentManager;
    }

    public void insertView(int i, Fragment fragment) {
        if (this._isAllLoaded) {
            insertViewAll(i, fragment);
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

    protected void insertViewFinish() {
        this._fragmentManager.executePendingTransactions();
    }

    public void gotoView(int i) {
        boolean z;
        LogTool.d(cTag, "gotoView curFragment[%d] ==> nextFragment[%d]", Integer.valueOf(this._curId), Integer.valueOf(i));
        if (i == this._curId) {
            LogTool.e(cTag, "gotoView The next id is not changed, return.");
            return;
        }
        Fragment fragmentFindFragmentByTag = this._fragmentManager.findFragmentByTag(Integer.toString(i));
        if (fragmentFindFragmentByTag == null) {
            LogTool.d(cTag, "gotoView nextFragment[%d] No find in Manager, do create.", Integer.valueOf(i));
            fragmentFindFragmentByTag = createFragment(i, this);
            if (fragmentFindFragmentByTag == null) {
                LogTool.e(cTag, "gotoView new fragment[%d] is error, return.", Integer.valueOf(i));
                return;
            }
            z = true;
        } else {
            z = false;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = this._fragmentManager.beginTransaction();
        Fragment fragmentFindFragmentByTag2 = this._fragmentManager.findFragmentByTag(Integer.toString(this._curId));
        if (fragmentFindFragmentByTag2 != null) {
            fragmentTransactionBeginTransaction.hide(fragmentFindFragmentByTag2);
        } else {
            LogTool.d(cTag, "gotoView curFragment[%d] == null, No need hide.", Integer.valueOf(this._curId));
        }
        if (z && !fragmentFindFragmentByTag.isAdded()) {
            LogTool.d(cTag, "gotoView nextFragment[%d] is not added, do add.", Integer.valueOf(i));
            fragmentTransactionBeginTransaction.add(this._resId, fragmentFindFragmentByTag, Integer.toString(i));
        } else {
            fragmentTransactionBeginTransaction.show(fragmentFindFragmentByTag);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        this._curId = i;
    }

    public void buildFragment() {
        List<Integer> listBuildPlugIn = buildPlugIn();
        this._plugIn = listBuildPlugIn;
        Iterator<Integer> it = listBuildPlugIn.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            Fragment fragmentFindFragmentByTag = this._fragmentManager.findFragmentByTag(Integer.toString(iIntValue));
            if (fragmentFindFragmentByTag != null) {
                LogTool.d(cTag, "buildFragment hide fragment id = " + iIntValue);
                this._fragmentManager.beginTransaction().hide(fragmentFindFragmentByTag).commitAllowingStateLoss();
            }
        }
        if (this._isAllLoaded) {
            insertViewBegin();
            Iterator<Integer> it2 = this._plugIn.iterator();
            while (it2.hasNext()) {
                int iIntValue2 = it2.next().intValue();
                if (this._fragmentManager.findFragmentByTag(Integer.toString(iIntValue2)) == null) {
                    insertView(iIntValue2, createFragment(iIntValue2, this));
                }
            }
            insertViewFinish();
        }
    }

    public Fragment getFragment(int i) {
        FragmentManager fragmentManager = this._fragmentManager;
        if (fragmentManager == null) {
            return null;
        }
        return fragmentManager.findFragmentByTag(Integer.toString(i));
    }
}
