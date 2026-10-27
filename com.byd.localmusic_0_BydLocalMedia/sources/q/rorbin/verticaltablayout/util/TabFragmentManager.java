package q.rorbin.verticaltablayout.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Iterator;
import java.util.List;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.TabView;

/* JADX INFO: loaded from: classes5.dex */
public class TabFragmentManager {
    private int mContainerResid;
    private List<Fragment> mFragments;
    private VerticalTabLayout.OnTabSelectedListener mListener;
    private FragmentManager mManager;
    private VerticalTabLayout mTabLayout;

    public TabFragmentManager(FragmentManager fragmentManager, List<Fragment> list, VerticalTabLayout verticalTabLayout) {
        this.mManager = fragmentManager;
        this.mFragments = list;
        this.mTabLayout = verticalTabLayout;
        OnFragmentTabSelectedListener onFragmentTabSelectedListener = new OnFragmentTabSelectedListener();
        this.mListener = onFragmentTabSelectedListener;
        this.mTabLayout.addOnTabSelectedListener(onFragmentTabSelectedListener);
    }

    public TabFragmentManager(FragmentManager fragmentManager, int i, List<Fragment> list, VerticalTabLayout verticalTabLayout) {
        this(fragmentManager, list, verticalTabLayout);
        this.mContainerResid = i;
        changeFragment();
    }

    public void changeFragment() {
        int i;
        FragmentTransaction fragmentTransactionBeginTransaction = this.mManager.beginTransaction();
        int selectedTabPosition = this.mTabLayout.getSelectedTabPosition();
        List<Fragment> fragments = this.mManager.getFragments();
        for (int i2 = 0; i2 < this.mFragments.size(); i2++) {
            Fragment fragment = this.mFragments.get(i2);
            if ((fragments == null || !fragments.contains(fragment)) && (i = this.mContainerResid) != 0) {
                fragmentTransactionBeginTransaction.add(i, fragment);
            }
            if ((this.mFragments.size() > selectedTabPosition && i2 == selectedTabPosition) || (this.mFragments.size() <= selectedTabPosition && i2 == this.mFragments.size() - 1)) {
                fragmentTransactionBeginTransaction.show(fragment);
            } else {
                fragmentTransactionBeginTransaction.hide(fragment);
            }
        }
        fragmentTransactionBeginTransaction.commit();
        this.mManager.executePendingTransactions();
    }

    public void detach() {
        FragmentTransaction fragmentTransactionBeginTransaction = this.mManager.beginTransaction();
        Iterator<Fragment> it = this.mFragments.iterator();
        while (it.hasNext()) {
            fragmentTransactionBeginTransaction.remove(it.next());
        }
        fragmentTransactionBeginTransaction.commit();
        this.mManager.executePendingTransactions();
        this.mManager = null;
        this.mFragments = null;
        this.mTabLayout.removeOnTabSelectedListener(this.mListener);
        this.mListener = null;
        this.mTabLayout = null;
    }

    private class OnFragmentTabSelectedListener implements VerticalTabLayout.OnTabSelectedListener {
        @Override // q.rorbin.verticaltablayout.VerticalTabLayout.OnTabSelectedListener
        public void onTabReselected(TabView tabView, int i) {
        }

        private OnFragmentTabSelectedListener() {
        }

        @Override // q.rorbin.verticaltablayout.VerticalTabLayout.OnTabSelectedListener
        public void onTabSelected(TabView tabView, int i) {
            TabFragmentManager.this.changeFragment();
        }
    }
}
