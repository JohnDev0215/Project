package com.amazon.podcast.views.ptcTemplate;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/* JADX INFO: loaded from: classes5.dex */
final class PTCPagerAdapter extends FragmentPagerAdapter {
    private final PTCShardsHolder shardsHolder;

    public PTCPagerAdapter(final FragmentManager fm, final PTCShardsHolder shardsHolder) {
        super(fm);
        this.shardsHolder = shardsHolder;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(final int i) {
        return this.shardsHolder.fragment(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.shardsHolder.count();
    }
}
