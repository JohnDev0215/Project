package com.amazon.mp3.prime.browse;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.adapter.LibraryPagerAdapter;
import com.amazon.mp3.library.adapter.ScrollableFragmentPagerAdapter;
import com.amazon.mp3.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class PrimeMusicPagerAdapter extends ScrollableFragmentPagerAdapter implements TabBarPagerAdapter {
    private static final String TAG = "PrimeMusicPagerAdapter";
    private List<PrimeMusicTab> mAdapterItems;
    private final Bundle mArguments;
    private final Context mContext;
    private int mTabPositionOnStart;

    PrimeMusicPagerAdapter(Bundle arguments, Context context, FragmentManager fm, List<PrimeMusicTab> adapterItems) {
        super(fm);
        this.mArguments = arguments;
        this.mContext = context;
        this.mAdapterItems = adapterItems;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        if (position >= getCount()) {
            position = getDefaultTabPosition();
        }
        Fragment fragment = getFragment(this.mAdapterItems.get(position));
        setListViewCreatedListener((PrimeMusicPagerFragment) fragment, position);
        return fragment;
    }

    @Override // com.amazon.mp3.library.adapter.ScrollableFragmentPagerAdapter
    public void scrollFragmentToTop(int position) {
        PrimeMusicPagerFragment primeMusicPagerFragment = (PrimeMusicPagerFragment) getFragmentAt(position);
        if (primeMusicPagerFragment != null) {
            primeMusicPagerFragment.scrollToTop();
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mAdapterItems.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        if (position >= getCount()) {
            return null;
        }
        return this.mContext.getResources().getString(this.mAdapterItems.get(position).getTitleResourceId());
    }

    @Override // com.amazon.mp3.library.adapter.ScrollableFragmentPagerAdapter, androidx.fragment.app.FragmentPagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override // com.amazon.mp3.prime.browse.TabBarPagerAdapter
    public void setTabPositionOnStart(int position) {
        this.mTabPositionOnStart = position;
    }

    protected void setListViewCreatedListener(final PrimeMusicPagerFragment fragment, int position) {
        if (this.mTabPositionOnStart == position) {
            fragment.setOnListViewCreatedListener(new LibraryPagerAdapter.OnListViewCreatedListener() { // from class: com.amazon.mp3.prime.browse.PrimeMusicPagerAdapter.1
                @Override // com.amazon.mp3.library.adapter.LibraryPagerAdapter.OnListViewCreatedListener
                public void onListViewCreated(ViewGroup listView) {
                    fragment.setupTouchListener(null);
                }
            });
        }
    }

    @Override // com.amazon.mp3.prime.browse.TabBarPagerAdapter
    public int getDefaultTabPosition() {
        return this.mAdapterItems.indexOf(PrimeTabFragmentHost.DEFAULT_TAB);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.browse.PrimeMusicPagerAdapter$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$browse$PrimeMusicTab;

        static {
            int[] iArr = new int[PrimeMusicTab.values().length];
            $SwitchMap$com$amazon$mp3$prime$browse$PrimeMusicTab = iArr;
            try {
                iArr[PrimeMusicTab.BROWSE_HOME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private Fragment getFragment(final PrimeMusicTab tab) {
        Fragment fragmentCreateFragment;
        if (AnonymousClass2.$SwitchMap$com$amazon$mp3$prime$browse$PrimeMusicTab[tab.ordinal()] == 1) {
            fragmentCreateFragment = new BrushFragmentNavigation.Builder("home").createFragment();
        } else {
            fragmentCreateFragment = getFragment(PrimeTabFragmentHost.DEFAULT_TAB);
            Log.warning(TAG, "Adapter accessing unknown fragment: " + tab);
        }
        fragmentCreateFragment.setArguments(this.mArguments);
        return fragmentCreateFragment;
    }
}
