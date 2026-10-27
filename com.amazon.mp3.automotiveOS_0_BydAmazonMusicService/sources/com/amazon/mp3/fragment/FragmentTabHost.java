package com.amazon.mp3.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.FragmentTabWidget;
import com.amazon.mp3.view.ArrowHorizontalScrollView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public class FragmentTabHost extends RelativeLayout implements FragmentTabWidget.OnTabClickListener {
    private ArrayList<FragmentTab> mFragmentTabs;
    private View mInflatedViewStub;
    private boolean mLeftAligned;
    private int mSelectedTab;
    private FragmentTabWidget mTabBar;
    private ArrowHorizontalScrollView mTabLayoutHorizontalScroller;
    private boolean mTabsLocked;

    public static int getInflatedViewStubId() {
        return R.id.DetailViewStub;
    }

    public FragmentTabHost(Activity activity) {
        super(activity);
        this.mSelectedTab = -1;
        this.mTabsLocked = false;
        this.mLeftAligned = false;
        init();
    }

    public FragmentTabWidget getTabWidget() {
        return this.mTabBar;
    }

    public Fragment getSelectedTabFragment() {
        FragmentTab fragmentTab;
        if (this.mFragmentTabs == null || getSelectedTab() <= -1 || getSelectedTab() >= this.mFragmentTabs.size() || (fragmentTab = this.mFragmentTabs.get(getSelectedTab())) == null) {
            return null;
        }
        return fragmentTab.mFragment;
    }

    public void setSelectedTab(final int index) {
        if (index < 0 || index >= this.mFragmentTabs.size()) {
            throw new IndexOutOfBoundsException();
        }
        scrollToCenter(index);
        if (index == this.mSelectedTab) {
            return;
        }
        this.mSelectedTab = index;
        this.mTabBar.selectTab(index);
        updateVisibleFragment(false);
        getFragmentTabActivity().onSelectedTabChanged(this.mSelectedTab);
    }

    public int addTab(Fragment fragment, int titleId) {
        return addTab(fragment, titleId, R.drawable.tab_widget_background, -2, getResources().getDimensionPixelSize(R.dimen.headerbar_tabs_vertical_margin));
    }

    public int addTab(Fragment fragment, int titleId, int background, int width, int height) {
        this.mFragmentTabs.add(new FragmentTab(fragment, titleId));
        int size = this.mFragmentTabs.size() - 1;
        this.mTabBar.addTab(titleId, size, background, width, height);
        this.mTabBar.setVisibility(this.mFragmentTabs.size() > 1 ? 0 : 8);
        return size;
    }

    public void setLeftAligned(boolean value) {
        View viewFindViewById;
        this.mLeftAligned = value;
        if (value && (viewFindViewById = getRootView().findViewById(R.id.TabLayoutHorizontalScroller)) != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams.addRule(14, 0);
            layoutParams.setMargins(AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.listview_horizontal_padding), 0, 0, 0);
            viewFindViewById.setLayoutParams(layoutParams);
        }
        getTabWidget().setLeftAligned(value);
    }

    public void lockTabs() {
        this.mTabsLocked = true;
    }

    public void unlockTabs() {
        this.mTabsLocked = false;
    }

    @Override // com.amazon.mp3.fragment.FragmentTabWidget.OnTabClickListener
    public void onTabClicked(int position) {
        if (this.mTabsLocked) {
            return;
        }
        setSelectedTab(position);
    }

    private void scrollToCenter(int tabPosition) {
        View childAt = this.mTabBar.getChildAt(tabPosition);
        int left = (childAt.getLeft() + (((childAt.getWidth() + childAt.getPaddingLeft()) + childAt.getPaddingRight()) / 2)) - (getFragmentTabActivity().getWindowManager().getDefaultDisplay().getWidth() / 2);
        if (left < 0) {
            left = 0;
        }
        this.mTabLayoutHorizontalScroller.scrollTo(left, 0);
    }

    public View getViewStub() {
        return this.mInflatedViewStub;
    }

    public int getSelectedTab() {
        return this.mSelectedTab;
    }

    void saveInstanceState(Bundle outState) {
        if (outState != null) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            Iterator<FragmentTab> it = this.mFragmentTabs.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(it.next().mTitleId));
            }
            outState.putIntegerArrayList("com.amazon.mp3.library.view.EXTRA_TAB_TITLE_ID_LIST", arrayList);
            outState.putInt("com.amazon.mp3.library.view.EXTRA_CURRENT_TAB", this.mSelectedTab);
            outState.putBoolean("com.amazon.mp3.library.view.EXTRA_LEFT_ALIGNED", this.mLeftAligned);
        }
    }

    void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        boolean z = savedInstanceState.getBoolean("com.amazon.mp3.library.view.EXTRA_LEFT_ALIGNED", false);
        this.mLeftAligned = z;
        setLeftAligned(z);
        ArrayList<Integer> integerArrayList = savedInstanceState.getIntegerArrayList("com.amazon.mp3.library.view.EXTRA_TAB_TITLE_ID_LIST");
        if (integerArrayList != null) {
            FragmentManager supportFragmentManager = getFragmentTabActivity().getSupportFragmentManager();
            Iterator<Integer> it = integerArrayList.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                addTab(supportFragmentManager.findFragmentByTag(Integer.toString(iIntValue)), iIntValue);
            }
        }
        if (this.mFragmentTabs.size() > 0) {
            setSelectedTab(savedInstanceState.getInt("com.amazon.mp3.library.view.EXTRA_CURRENT_TAB", 0));
        }
    }

    private void updateVisibleFragment(boolean forceRecreate) {
        FragmentTabActivity fragmentTabActivity = getFragmentTabActivity();
        FragmentManager supportFragmentManager = fragmentTabActivity.getSupportFragmentManager();
        if (supportFragmentManager != null) {
            supportFragmentManager.executePendingTransactions();
            FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
            for (int i = 0; i < this.mFragmentTabs.size(); i++) {
                try {
                    FragmentTab fragmentTab = this.mFragmentTabs.get(i);
                    String string = Integer.toString(fragmentTab.mTitleId);
                    Fragment fragmentFindFragmentByTag = supportFragmentManager.findFragmentByTag(string);
                    if (i == this.mSelectedTab) {
                        if (forceRecreate && fragmentFindFragmentByTag != null) {
                            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
                            fragmentTab.mFragment = null;
                        }
                        if (fragmentFindFragmentByTag != null && !forceRecreate) {
                            fragmentTransactionBeginTransaction.show(fragmentFindFragmentByTag);
                        } else {
                            if (fragmentTab.mFragment == null) {
                                fragmentTab.mFragment = fragmentTabActivity.lazyCreateFragmentForTab(i);
                            }
                            fragmentTransactionBeginTransaction.add(R.id.TabContentView, fragmentTab.mFragment, string);
                        }
                    } else if (fragmentFindFragmentByTag != null) {
                        fragmentTransactionBeginTransaction.hide(fragmentFindFragmentByTag);
                    }
                } finally {
                    fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                }
            }
        }
    }

    private void setSelectedFragmentHidden(boolean hidden) {
        FragmentManager supportFragmentManager = getFragmentTabActivity().getSupportFragmentManager();
        if (supportFragmentManager != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
            try {
                Fragment selectedTabFragment = getSelectedTabFragment();
                if (selectedTabFragment != null) {
                    if (hidden) {
                        fragmentTransactionBeginTransaction.hide(selectedTabFragment);
                    } else {
                        fragmentTransactionBeginTransaction.show(selectedTabFragment);
                    }
                }
            } finally {
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        }
    }

    private void init() {
        addView(View.inflate(getContext(), R.layout.library_fragment_tab_activity, null));
        if (findViewById(R.id.TabContentView) == null) {
            throw new RuntimeException("Your TabHost must have a FrameLayout whose id attribute is 'R.id.TabContentView'");
        }
        this.mFragmentTabs = new ArrayList<>();
        this.mTabBar = (FragmentTabWidget) findViewById(R.id.TabLayoutTabWidget);
        ArrowHorizontalScrollView arrowHorizontalScrollView = (ArrowHorizontalScrollView) findViewById(R.id.TabLayoutHorizontalScroller);
        this.mTabLayoutHorizontalScroller = arrowHorizontalScrollView;
        arrowHorizontalScrollView.setLeftArrow(findViewById(R.id.LeftArrow));
        this.mTabLayoutHorizontalScroller.setRightArrow(findViewById(R.id.RightArrow));
        this.mTabLayoutHorizontalScroller.setContent(this.mTabBar);
        this.mTabBar.setOnTabClickListener(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(final int visibility) {
        super.onWindowVisibilityChanged(visibility);
        this.mTabLayoutHorizontalScroller.setOverScrollMode(visibility == 0 ? 0 : 2);
    }

    private static class FragmentTab {
        private Fragment mFragment;
        private final int mTitleId;

        public FragmentTab(Fragment fragment, int titleId) {
            this.mFragment = fragment;
            this.mTitleId = titleId;
        }
    }

    private FragmentTabActivity getFragmentTabActivity() {
        return (FragmentTabActivity) getContext();
    }

    public void hideOrShowElements(boolean hidden) {
        int i = hidden ? 4 : 0;
        this.mTabBar.setVisibility(i);
        this.mTabLayoutHorizontalScroller.setVisibility(i);
        setSelectedFragmentHidden(hidden);
    }
}
