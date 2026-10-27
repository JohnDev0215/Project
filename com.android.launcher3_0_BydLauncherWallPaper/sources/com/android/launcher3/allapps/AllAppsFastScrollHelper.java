package com.android.launcher3.allapps;

import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.allapps.AllAppsGridAdapter;
import com.android.launcher3.allapps.AlphabeticalAppsList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsFastScrollHelper implements AllAppsGridAdapter.BindViewCallback {
    private static final int INITIAL_TOUCH_SETTLING_DURATION = 100;
    private static final int REPEAT_TOUCH_SETTLING_DURATION = 200;
    private AlphabeticalAppsList mApps;
    String mCurrentFastScrollSection;
    int mFastScrollFrameIndex;
    private boolean mHasFastScrollTouchSettled;
    private boolean mHasFastScrollTouchSettledAtLeastOnce;
    private AllAppsRecyclerView mRv;
    String mTargetFastScrollSection;
    int mTargetFastScrollPosition = -1;
    private HashSet<RecyclerView.ViewHolder> mTrackedFastScrollViews = new HashSet<>();
    final int[] mFastScrollFrames = new int[10];
    Runnable mSmoothSnapNextFrameRunnable = new Runnable() { // from class: com.android.launcher3.allapps.AllAppsFastScrollHelper.1
        @Override // java.lang.Runnable
        public void run() {
            if (AllAppsFastScrollHelper.this.mFastScrollFrameIndex < AllAppsFastScrollHelper.this.mFastScrollFrames.length) {
                AllAppsFastScrollHelper.this.mRv.scrollBy(0, AllAppsFastScrollHelper.this.mFastScrollFrames[AllAppsFastScrollHelper.this.mFastScrollFrameIndex]);
                AllAppsFastScrollHelper.this.mFastScrollFrameIndex++;
                AllAppsFastScrollHelper.this.mRv.postOnAnimation(AllAppsFastScrollHelper.this.mSmoothSnapNextFrameRunnable);
            }
        }
    };
    Runnable mFastScrollToTargetSectionRunnable = new Runnable() { // from class: com.android.launcher3.allapps.AllAppsFastScrollHelper.2
        @Override // java.lang.Runnable
        public void run() {
            AllAppsFastScrollHelper allAppsFastScrollHelper = AllAppsFastScrollHelper.this;
            allAppsFastScrollHelper.mCurrentFastScrollSection = allAppsFastScrollHelper.mTargetFastScrollSection;
            AllAppsFastScrollHelper.this.mHasFastScrollTouchSettled = true;
            AllAppsFastScrollHelper.this.mHasFastScrollTouchSettledAtLeastOnce = true;
            AllAppsFastScrollHelper.this.updateTrackedViewsFastScrollFocusState();
        }
    };

    public AllAppsFastScrollHelper(AllAppsRecyclerView rv, AlphabeticalAppsList apps) {
        this.mRv = rv;
        this.mApps = apps;
    }

    public void onSetAdapter(AllAppsGridAdapter adapter) {
        adapter.setBindViewCallback(this);
    }

    public boolean smoothScrollToSection(int scrollY, int availableScrollHeight, AlphabeticalAppsList.FastScrollSectionInfo info) {
        if (this.mTargetFastScrollPosition == info.fastScrollToItem.position) {
            return false;
        }
        this.mTargetFastScrollPosition = info.fastScrollToItem.position;
        smoothSnapToPosition(scrollY, availableScrollHeight, info);
        return true;
    }

    private void smoothSnapToPosition(int scrollY, int availableScrollHeight, AlphabeticalAppsList.FastScrollSectionInfo info) {
        this.mRv.removeCallbacks(this.mSmoothSnapNextFrameRunnable);
        this.mRv.removeCallbacks(this.mFastScrollToTargetSectionRunnable);
        trackAllChildViews();
        if (this.mHasFastScrollTouchSettled) {
            this.mCurrentFastScrollSection = info.sectionName;
            this.mTargetFastScrollSection = null;
            updateTrackedViewsFastScrollFocusState();
        } else {
            this.mCurrentFastScrollSection = null;
            this.mTargetFastScrollSection = info.sectionName;
            this.mHasFastScrollTouchSettled = false;
            updateTrackedViewsFastScrollFocusState();
            this.mRv.postDelayed(this.mFastScrollToTargetSectionRunnable, this.mHasFastScrollTouchSettledAtLeastOnce ? 200L : 100L);
        }
        List<AlphabeticalAppsList.FastScrollSectionInfo> fastScrollerSections = this.mApps.getFastScrollerSections();
        int iMin = (fastScrollerSections.size() <= 0 || fastScrollerSections.get(0) != info) ? Math.min(availableScrollHeight, this.mRv.getCurrentScrollY(info.fastScrollToItem.position, 0)) : 0;
        int length = this.mFastScrollFrames.length;
        int i = iMin - scrollY;
        float fSignum = Math.signum(i);
        int iCeil = (int) (((double) fSignum) * Math.ceil(Math.abs(i) / length));
        for (int i2 = 0; i2 < length; i2++) {
            this.mFastScrollFrames[i2] = (int) (Math.min(Math.abs(iCeil), Math.abs(i)) * fSignum);
            i -= iCeil;
        }
        this.mFastScrollFrameIndex = 0;
        this.mRv.postOnAnimation(this.mSmoothSnapNextFrameRunnable);
    }

    public void onFastScrollCompleted() {
        this.mRv.removeCallbacks(this.mSmoothSnapNextFrameRunnable);
        this.mRv.removeCallbacks(this.mFastScrollToTargetSectionRunnable);
        this.mHasFastScrollTouchSettled = false;
        this.mHasFastScrollTouchSettledAtLeastOnce = false;
        this.mCurrentFastScrollSection = null;
        this.mTargetFastScrollSection = null;
        this.mTargetFastScrollPosition = -1;
        updateTrackedViewsFastScrollFocusState();
        this.mTrackedFastScrollViews.clear();
    }

    @Override // com.android.launcher3.allapps.AllAppsGridAdapter.BindViewCallback
    public void onBindView(AllAppsGridAdapter.ViewHolder holder) {
        if (this.mCurrentFastScrollSection == null && this.mTargetFastScrollSection == null) {
            return;
        }
        this.mTrackedFastScrollViews.add(holder);
    }

    private void trackAllChildViews() {
        int childCount = this.mRv.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AllAppsRecyclerView allAppsRecyclerView = this.mRv;
            RecyclerView.ViewHolder childViewHolder = allAppsRecyclerView.getChildViewHolder(allAppsRecyclerView.getChildAt(i));
            if (childViewHolder != null) {
                this.mTrackedFastScrollViews.add(childViewHolder);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTrackedViewsFastScrollFocusState() {
        AlphabeticalAppsList.AdapterItem adapterItem;
        for (RecyclerView.ViewHolder viewHolder : this.mTrackedFastScrollViews) {
            int adapterPosition = viewHolder.getAdapterPosition();
            boolean z = false;
            if (this.mCurrentFastScrollSection != null && adapterPosition > -1 && adapterPosition < this.mApps.getAdapterItems().size() && (adapterItem = this.mApps.getAdapterItems().get(adapterPosition)) != null && this.mCurrentFastScrollSection.equals(adapterItem.sectionName) && adapterItem.position == this.mTargetFastScrollPosition) {
                z = true;
            }
            viewHolder.itemView.setActivated(z);
        }
    }
}
