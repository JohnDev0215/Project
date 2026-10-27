package com.amazon.podcast.nowplaying;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.amazon.podcast.R$string;

/* JADX INFO: loaded from: classes5.dex */
final class TabAdapter extends FragmentPagerAdapter {
    private final Context context;
    private Fragment fragment;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return 1;
    }

    public TabAdapter(final Context context, final FragmentManager fm) {
        super(fm);
        this.context = context;
        init();
    }

    private void init() {
        this.fragment = new NowPlayingAboutFragment();
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(final int position) {
        if (position != 0) {
            return null;
        }
        return this.fragment;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(final int position) {
        return this.context.getString(R$string.podcast_about_view_tab_label);
    }
}
