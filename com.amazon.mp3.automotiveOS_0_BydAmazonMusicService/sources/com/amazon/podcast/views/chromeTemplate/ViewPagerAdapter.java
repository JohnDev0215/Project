package com.amazon.podcast.views.chromeTemplate;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/* JADX INFO: loaded from: classes5.dex */
final class ViewPagerAdapter extends FragmentPagerAdapter {
    private final ChromeShardsHolder shardsHolder;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(final Object object) {
        return -2;
    }

    public ViewPagerAdapter(final FragmentManager fm, final ChromeShardsHolder shardsHolder) {
        super(fm);
        this.shardsHolder = shardsHolder;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(final int position) {
        ChromeShardsHolder chromeShardsHolder = this.shardsHolder;
        if (chromeShardsHolder == null) {
            return null;
        }
        return chromeShardsHolder.fragment(position);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        ChromeShardsHolder chromeShardsHolder = this.shardsHolder;
        if (chromeShardsHolder == null) {
            return 0;
        }
        return chromeShardsHolder.count();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(final int position) {
        ChromeShardsHolder chromeShardsHolder = this.shardsHolder;
        return chromeShardsHolder == null ? "" : chromeShardsHolder.shardText(position);
    }
}
