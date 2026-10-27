package com.byd.auto_emanual;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.byd.auto_emanual.fragment.BaseFragment;
import com.byd.auto_emanual.fragment.ManualFragment;
import com.byd.auto_emanual.fragment.RecommendFragment;
import com.byd.auto_emanual.fragment.SearchFragment;
import com.byd.auto_emanual.fragment.SettingsFragment;
import com.byd.auto_emanual.fragment.StartFragment;
import com.byd.auto_emanual.util.ListUtil;
import com.byd.auto_emanual.util.Utils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FragmentHolder {
    private static List<BaseFragment> fragments = new ArrayList();

    public static BaseFragment getFragment(int position) {
        return fragments.get(position);
    }

    public static List<BaseFragment> getAllFragments() {
        return fragments;
    }

    public static void createAll() {
        fragments.clear();
        if (!Utils.notShowVideo(MApp.context)) {
            fragments.add(new RecommendFragment());
            fragments.add(new StartFragment());
        }
        fragments.add(new ManualFragment());
        fragments.add(new SearchFragment());
        fragments.add(new SettingsFragment());
        getFeaturePosition();
    }

    public static int getFeaturePosition() {
        int i = 0;
        for (int i2 = 0; i2 < fragments.size(); i2++) {
            if (fragments.get(i2) instanceof RecommendFragment) {
                i = i2;
            }
        }
        return i;
    }

    public static void addFragment(MainActivity a) {
        FragmentManager supportFragmentManager = a.getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        supportFragmentManager.executePendingTransactions();
        for (BaseFragment baseFragment : getAllFragments()) {
            if (!baseFragment.isAdded()) {
                fragmentTransactionBeginTransaction.add(R.id.fragment_container, baseFragment, baseFragment.getClass().getSimpleName());
                fragmentTransactionBeginTransaction.hide(baseFragment);
            }
        }
        fragmentTransactionBeginTransaction.commit();
    }

    public static int getFragmentCount() {
        return fragments.size();
    }

    public static <T> T getFragment(Class<T> clazz) {
        if (ListUtil.isNullOrEmpty(fragments)) {
            return null;
        }
        for (BaseFragment baseFragment : fragments) {
            if (clazz.isInstance(baseFragment)) {
                return clazz.cast(baseFragment);
            }
        }
        return null;
    }
}
