package com.amazon.mp3.library.adapter;

import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ScrollableFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Object> mPages;

    public abstract void scrollFragmentToTop(int position);

    public ScrollableFragmentPagerAdapter(final FragmentManager fm) {
        super(fm);
        this.mPages = new ArrayList();
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(final ViewGroup container, final int position) {
        Object objInstantiateItem = super.instantiateItem(container, position);
        while (this.mPages.size() <= position) {
            this.mPages.add(null);
        }
        this.mPages.set(position, objInstantiateItem);
        return objInstantiateItem;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        super.destroyItem(container, position, object);
        if (this.mPages.size() > position) {
            this.mPages.set(position, null);
        }
    }

    @Nullable
    public Fragment getFragmentAt(int position) {
        if (this.mPages.size() <= position || position < 0) {
            return null;
        }
        return (Fragment) this.mPages.get(position);
    }
}
