package com.byd.music.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public String[] tabTitles;

    protected abstract List<Fragment> fragments();

    protected abstract List<String> tabTitles();

    public BaseFragmentPagerAdapter(FragmentManager fragmentManager, int i) {
        super(fragmentManager, i);
        this.fragments = new ArrayList();
        initFragments();
    }

    public void initFragments() {
        this.fragments.addAll(fragments());
        if (tabTitles() == null) {
            this.tabTitles = new String[0];
        } else {
            this.tabTitles = (String[]) tabTitles().toArray(new String[0]);
        }
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.fragments.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.tabTitles[i];
    }
}
