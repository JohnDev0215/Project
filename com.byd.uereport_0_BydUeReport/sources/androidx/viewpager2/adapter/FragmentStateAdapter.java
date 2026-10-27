package androidx.viewpager2.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Iterator;
import n0.h;
import o0.t;
import v.b;
import v.d;
import v0.g;
import v0.j;
import x0.f;

/* JADX INFO: loaded from: classes.dex */
public abstract class FragmentStateAdapter extends RecyclerView.g<FragmentViewHolder> implements StatefulAdapter {
    private static final long GRACE_WINDOW_TIME_MS = 10000;
    private static final String KEY_PREFIX_FRAGMENT = "f#";
    private static final String KEY_PREFIX_STATE = "s#";
    public final g mFragmentManager;
    private FragmentMaxLifecycleEnforcer mFragmentMaxLifecycleEnforcer;
    public final d<Fragment> mFragments;
    private boolean mHasStaleFragments;
    public boolean mIsInGracePeriod;
    private final d<Integer> mItemIdToViewHolder;
    public final Lifecycle mLifecycle;
    private final d<Fragment.SavedState> mSavedStates;

    public static abstract class DataSetChangeObserver extends RecyclerView.i {
        private DataSetChangeObserver() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public abstract void onChanged();

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void onItemRangeChanged(int i8, int i9) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void onItemRangeInserted(int i8, int i9) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void onItemRangeMoved(int i8, int i9, int i10) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void onItemRangeRemoved(int i8, int i9) {
            onChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.i
        public final void onItemRangeChanged(int i8, int i9, Object obj) {
            onChanged();
        }
    }

    public class FragmentMaxLifecycleEnforcer {
        private RecyclerView.i mDataObserver;
        private x0.d mLifecycleObserver;
        private ViewPager2.OnPageChangeCallback mPageChangeCallback;
        private long mPrimaryItemId = -1;
        private ViewPager2 mViewPager;

        public FragmentMaxLifecycleEnforcer() {
        }

        private ViewPager2 inferViewPager(RecyclerView recyclerView) {
            ViewParent parent = recyclerView.getParent();
            if (parent instanceof ViewPager2) {
                return (ViewPager2) parent;
            }
            throw new IllegalStateException("Expected ViewPager2 instance. Got: " + parent);
        }

        public void register(RecyclerView recyclerView) {
            this.mViewPager = inferViewPager(recyclerView);
            ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.1
                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageScrollStateChanged(int i8) {
                    FragmentMaxLifecycleEnforcer.this.updateFragmentMaxLifecycle(false);
                }

                @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
                public void onPageSelected(int i8) {
                    FragmentMaxLifecycleEnforcer.this.updateFragmentMaxLifecycle(false);
                }
            };
            this.mPageChangeCallback = onPageChangeCallback;
            this.mViewPager.registerOnPageChangeCallback(onPageChangeCallback);
            DataSetChangeObserver dataSetChangeObserver = new DataSetChangeObserver() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.2
                @Override // androidx.viewpager2.adapter.FragmentStateAdapter.DataSetChangeObserver, androidx.recyclerview.widget.RecyclerView.i
                public void onChanged() {
                    FragmentMaxLifecycleEnforcer.this.updateFragmentMaxLifecycle(true);
                }
            };
            this.mDataObserver = dataSetChangeObserver;
            FragmentStateAdapter.this.registerAdapterDataObserver(dataSetChangeObserver);
            x0.d dVar = new x0.d() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.FragmentMaxLifecycleEnforcer.3
                @Override // x0.d
                public void onStateChanged(f fVar, Lifecycle.Event event) {
                    FragmentMaxLifecycleEnforcer.this.updateFragmentMaxLifecycle(false);
                }
            };
            this.mLifecycleObserver = dVar;
            FragmentStateAdapter.this.mLifecycle.a(dVar);
        }

        public void unregister(RecyclerView recyclerView) {
            inferViewPager(recyclerView).unregisterOnPageChangeCallback(this.mPageChangeCallback);
            FragmentStateAdapter.this.unregisterAdapterDataObserver(this.mDataObserver);
            FragmentStateAdapter.this.mLifecycle.c(this.mLifecycleObserver);
            this.mViewPager = null;
        }

        public void updateFragmentMaxLifecycle(boolean z7) {
            int currentItem;
            Fragment fragmentF;
            if (FragmentStateAdapter.this.shouldDelayFragmentTransactions() || this.mViewPager.getScrollState() != 0 || FragmentStateAdapter.this.mFragments.i() || FragmentStateAdapter.this.getItemCount() == 0 || (currentItem = this.mViewPager.getCurrentItem()) >= FragmentStateAdapter.this.getItemCount()) {
                return;
            }
            long itemId = FragmentStateAdapter.this.getItemId(currentItem);
            if ((itemId != this.mPrimaryItemId || z7) && (fragmentF = FragmentStateAdapter.this.mFragments.f(itemId)) != null && fragmentF.isAdded()) {
                this.mPrimaryItemId = itemId;
                j jVarA = FragmentStateAdapter.this.mFragmentManager.a();
                Fragment fragment = null;
                for (int i8 = 0; i8 < FragmentStateAdapter.this.mFragments.n(); i8++) {
                    long j8 = FragmentStateAdapter.this.mFragments.j(i8);
                    Fragment fragmentO = FragmentStateAdapter.this.mFragments.o(i8);
                    if (fragmentO.isAdded()) {
                        if (j8 != this.mPrimaryItemId) {
                            jVarA.m(fragmentO, Lifecycle.State.STARTED);
                        } else {
                            fragment = fragmentO;
                        }
                        fragmentO.setMenuVisibility(j8 == this.mPrimaryItemId);
                    }
                }
                if (fragment != null) {
                    jVarA.m(fragment, Lifecycle.State.RESUMED);
                }
                if (jVarA.k()) {
                    return;
                }
                jVarA.g();
            }
        }
    }

    public FragmentStateAdapter(FragmentActivity fragmentActivity) {
        this(fragmentActivity.getSupportFragmentManager(), fragmentActivity.getLifecycle());
    }

    private static String createKey(String str, long j8) {
        return str + j8;
    }

    private void ensureFragment(int i8) {
        long itemId = getItemId(i8);
        if (this.mFragments.d(itemId)) {
            return;
        }
        Fragment fragmentCreateFragment = createFragment(i8);
        fragmentCreateFragment.setInitialSavedState(this.mSavedStates.f(itemId));
        this.mFragments.k(itemId, fragmentCreateFragment);
    }

    private boolean isFragmentViewBound(long j8) {
        View view;
        if (this.mItemIdToViewHolder.d(j8)) {
            return true;
        }
        Fragment fragmentF = this.mFragments.f(j8);
        return (fragmentF == null || (view = fragmentF.getView()) == null || view.getParent() == null) ? false : true;
    }

    private static boolean isValidKey(String str, String str2) {
        return str.startsWith(str2) && str.length() > str2.length();
    }

    private Long itemForViewHolder(int i8) {
        Long lValueOf = null;
        for (int i9 = 0; i9 < this.mItemIdToViewHolder.n(); i9++) {
            if (this.mItemIdToViewHolder.o(i9).intValue() == i8) {
                if (lValueOf != null) {
                    throw new IllegalStateException("Design assumption violated: a ViewHolder can only be bound to one item at a time.");
                }
                lValueOf = Long.valueOf(this.mItemIdToViewHolder.j(i9));
            }
        }
        return lValueOf;
    }

    private static long parseIdFromKey(String str, String str2) {
        return Long.parseLong(str.substring(str2.length()));
    }

    private void removeFragment(long j8) {
        ViewParent parent;
        Fragment fragmentF = this.mFragments.f(j8);
        if (fragmentF == null) {
            return;
        }
        if (fragmentF.getView() != null && (parent = fragmentF.getView().getParent()) != null) {
            ((FrameLayout) parent).removeAllViews();
        }
        if (!containsItem(j8)) {
            this.mSavedStates.l(j8);
        }
        if (!fragmentF.isAdded()) {
            this.mFragments.l(j8);
            return;
        }
        if (shouldDelayFragmentTransactions()) {
            this.mHasStaleFragments = true;
            return;
        }
        if (fragmentF.isAdded() && containsItem(j8)) {
            this.mSavedStates.k(j8, this.mFragmentManager.m(fragmentF));
        }
        this.mFragmentManager.a().l(fragmentF).g();
        this.mFragments.l(j8);
    }

    private void scheduleGracePeriodEnd() {
        final Handler handler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.4
            @Override // java.lang.Runnable
            public void run() {
                FragmentStateAdapter fragmentStateAdapter = FragmentStateAdapter.this;
                fragmentStateAdapter.mIsInGracePeriod = false;
                fragmentStateAdapter.gcFragments();
            }
        };
        this.mLifecycle.a(new x0.d() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.5
            @Override // x0.d
            public void onStateChanged(f fVar, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    handler.removeCallbacks(runnable);
                    fVar.getLifecycle().c(this);
                }
            }
        });
        handler.postDelayed(runnable, GRACE_WINDOW_TIME_MS);
    }

    private void scheduleViewAttach(final Fragment fragment, final FrameLayout frameLayout) {
        this.mFragmentManager.l(new g.a() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.3
            @Override // v0.g.a
            public void onFragmentViewCreated(g gVar, Fragment fragment2, View view, Bundle bundle) {
                if (fragment2 == fragment) {
                    gVar.o(this);
                    FragmentStateAdapter.this.addViewToContainer(view, frameLayout);
                }
            }
        }, false);
    }

    public void addViewToContainer(View view, FrameLayout frameLayout) {
        if (frameLayout.getChildCount() > 1) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (view.getParent() == frameLayout) {
            return;
        }
        if (frameLayout.getChildCount() > 0) {
            frameLayout.removeAllViews();
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        frameLayout.addView(view);
    }

    public boolean containsItem(long j8) {
        return j8 >= 0 && j8 < ((long) getItemCount());
    }

    public abstract Fragment createFragment(int i8);

    public void gcFragments() {
        if (!this.mHasStaleFragments || shouldDelayFragmentTransactions()) {
            return;
        }
        b bVar = new b();
        for (int i8 = 0; i8 < this.mFragments.n(); i8++) {
            long j8 = this.mFragments.j(i8);
            if (!containsItem(j8)) {
                bVar.add(Long.valueOf(j8));
                this.mItemIdToViewHolder.l(j8);
            }
        }
        if (!this.mIsInGracePeriod) {
            this.mHasStaleFragments = false;
            for (int i9 = 0; i9 < this.mFragments.n(); i9++) {
                long j9 = this.mFragments.j(i9);
                if (!isFragmentViewBound(j9)) {
                    bVar.add(Long.valueOf(j9));
                }
            }
        }
        Iterator<E> it = bVar.iterator();
        while (it.hasNext()) {
            removeFragment(((Long) it.next()).longValue());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public long getItemId(int i8) {
        return i8;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        h.a(this.mFragmentMaxLifecycleEnforcer == null);
        FragmentMaxLifecycleEnforcer fragmentMaxLifecycleEnforcer = new FragmentMaxLifecycleEnforcer();
        this.mFragmentMaxLifecycleEnforcer = fragmentMaxLifecycleEnforcer;
        fragmentMaxLifecycleEnforcer.register(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.mFragmentMaxLifecycleEnforcer.unregister(recyclerView);
        this.mFragmentMaxLifecycleEnforcer = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final boolean onFailedToRecycleView(FragmentViewHolder fragmentViewHolder) {
        return true;
    }

    public void placeFragmentInViewHolder(final FragmentViewHolder fragmentViewHolder) {
        Fragment fragmentF = this.mFragments.f(fragmentViewHolder.getItemId());
        if (fragmentF == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        FrameLayout container = fragmentViewHolder.getContainer();
        View view = fragmentF.getView();
        if (!fragmentF.isAdded() && view != null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        if (fragmentF.isAdded() && view == null) {
            scheduleViewAttach(fragmentF, container);
            return;
        }
        if (fragmentF.isAdded() && view.getParent() != null) {
            if (view.getParent() != container) {
                addViewToContainer(view, container);
                return;
            }
            return;
        }
        if (fragmentF.isAdded()) {
            addViewToContainer(view, container);
            return;
        }
        if (shouldDelayFragmentTransactions()) {
            if (this.mFragmentManager.h()) {
                return;
            }
            this.mLifecycle.a(new x0.d() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.2
                @Override // x0.d
                public void onStateChanged(f fVar, Lifecycle.Event event) {
                    if (FragmentStateAdapter.this.shouldDelayFragmentTransactions()) {
                        return;
                    }
                    fVar.getLifecycle().c(this);
                    if (t.P(fragmentViewHolder.getContainer())) {
                        FragmentStateAdapter.this.placeFragmentInViewHolder(fragmentViewHolder);
                    }
                }
            });
            return;
        }
        scheduleViewAttach(fragmentF, container);
        this.mFragmentManager.a().c(fragmentF, "f" + fragmentViewHolder.getItemId()).m(fragmentF, Lifecycle.State.STARTED).g();
        this.mFragmentMaxLifecycleEnforcer.updateFragmentMaxLifecycle(false);
    }

    @Override // androidx.viewpager2.adapter.StatefulAdapter
    public final void restoreState(Parcelable parcelable) {
        if (!this.mSavedStates.i() || !this.mFragments.i()) {
            throw new IllegalStateException("Expected the adapter to be 'fresh' while restoring state.");
        }
        Bundle bundle = (Bundle) parcelable;
        if (bundle.getClassLoader() == null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        for (String str : bundle.keySet()) {
            if (isValidKey(str, KEY_PREFIX_FRAGMENT)) {
                this.mFragments.k(parseIdFromKey(str, KEY_PREFIX_FRAGMENT), this.mFragmentManager.e(bundle, str));
            } else {
                if (!isValidKey(str, KEY_PREFIX_STATE)) {
                    throw new IllegalArgumentException("Unexpected key in savedState: " + str);
                }
                long idFromKey = parseIdFromKey(str, KEY_PREFIX_STATE);
                Fragment.SavedState savedState = (Fragment.SavedState) bundle.getParcelable(str);
                if (containsItem(idFromKey)) {
                    this.mSavedStates.k(idFromKey, savedState);
                }
            }
        }
        if (this.mFragments.i()) {
            return;
        }
        this.mHasStaleFragments = true;
        this.mIsInGracePeriod = true;
        gcFragments();
        scheduleGracePeriodEnd();
    }

    @Override // androidx.viewpager2.adapter.StatefulAdapter
    public final Parcelable saveState() {
        Bundle bundle = new Bundle(this.mFragments.n() + this.mSavedStates.n());
        for (int i8 = 0; i8 < this.mFragments.n(); i8++) {
            long j8 = this.mFragments.j(i8);
            Fragment fragmentF = this.mFragments.f(j8);
            if (fragmentF != null && fragmentF.isAdded()) {
                this.mFragmentManager.k(bundle, createKey(KEY_PREFIX_FRAGMENT, j8), fragmentF);
            }
        }
        for (int i9 = 0; i9 < this.mSavedStates.n(); i9++) {
            long j9 = this.mSavedStates.j(i9);
            if (containsItem(j9)) {
                bundle.putParcelable(createKey(KEY_PREFIX_STATE, j9), this.mSavedStates.f(j9));
            }
        }
        return bundle;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final void setHasStableIds(boolean z7) {
        throw new UnsupportedOperationException("Stable Ids are required for the adapter to function properly, and the adapter takes care of setting the flag.");
    }

    public boolean shouldDelayFragmentTransactions() {
        return this.mFragmentManager.i();
    }

    public FragmentStateAdapter(Fragment fragment) {
        this(fragment.getChildFragmentManager(), fragment.getLifecycle());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final void onBindViewHolder(final FragmentViewHolder fragmentViewHolder, int i8) {
        long itemId = fragmentViewHolder.getItemId();
        int id = fragmentViewHolder.getContainer().getId();
        Long lItemForViewHolder = itemForViewHolder(id);
        if (lItemForViewHolder != null && lItemForViewHolder.longValue() != itemId) {
            removeFragment(lItemForViewHolder.longValue());
            this.mItemIdToViewHolder.l(lItemForViewHolder.longValue());
        }
        this.mItemIdToViewHolder.k(itemId, Integer.valueOf(id));
        ensureFragment(i8);
        final FrameLayout container = fragmentViewHolder.getContainer();
        if (t.P(container)) {
            if (container.getParent() != null) {
                throw new IllegalStateException("Design assumption violated.");
            }
            container.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: androidx.viewpager2.adapter.FragmentStateAdapter.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
                    if (container.getParent() != null) {
                        container.removeOnLayoutChangeListener(this);
                        FragmentStateAdapter.this.placeFragmentInViewHolder(fragmentViewHolder);
                    }
                }
            });
        }
        gcFragments();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final FragmentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i8) {
        return FragmentViewHolder.create(viewGroup);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final void onViewAttachedToWindow(FragmentViewHolder fragmentViewHolder) {
        placeFragmentInViewHolder(fragmentViewHolder);
        gcFragments();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public final void onViewRecycled(FragmentViewHolder fragmentViewHolder) {
        Long lItemForViewHolder = itemForViewHolder(fragmentViewHolder.getContainer().getId());
        if (lItemForViewHolder != null) {
            removeFragment(lItemForViewHolder.longValue());
            this.mItemIdToViewHolder.l(lItemForViewHolder.longValue());
        }
    }

    public FragmentStateAdapter(g gVar, Lifecycle lifecycle) {
        this.mFragments = new d<>();
        this.mSavedStates = new d<>();
        this.mItemIdToViewHolder = new d<>();
        this.mIsInGracePeriod = false;
        this.mHasStaleFragments = false;
        this.mFragmentManager = gVar;
        this.mLifecycle = lifecycle;
        super.setHasStableIds(true);
    }
}
