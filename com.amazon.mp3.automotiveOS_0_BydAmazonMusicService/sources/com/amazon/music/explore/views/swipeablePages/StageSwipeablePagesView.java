package com.amazon.music.explore.views.swipeablePages;

import Touch.SwipeablePagesTemplateInterface.v1_0.ImpressionEndReason;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePagesTemplate;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionsElement;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView;
import com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsViewHolder;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageRelativeLayout;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView;
import com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageViewHolder;
import com.amazon.music.explore.views.swipeablePages.pages.manager.BottomTabStateManager;
import com.amazon.music.explore.views.swipeablePages.pages.manager.StageProgressManager;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.VerticalContainerView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes4.dex */
public class StageSwipeablePagesView extends SwipeablePageRelativeLayout implements StageProgressManager, BottomTabStateManager {
    private static final int MAX_CLICK_DISTANCE = 15;
    private LinearLayout bottomTabBarLayout;
    private StageSwipeablePagesBottomTabManager bottomTabBarManager;
    private final Context context;
    private int countOfElementsOfPreviousSections;
    private SwipeablePageView currentPage;
    private int currentPageIndex;
    private int currentSectionIndex;
    private boolean hasUserInteracted;
    private PublishSubject<ImpressionEndReason> impressionEndReasonPublishSubject;
    private boolean isFullScreen;
    private boolean isLayoutChanged;
    private boolean isViewBeingShownForTheFirstTime;
    private LinearLayoutManager layoutManager;
    private final MethodsDispatcher methodsDispatcher;
    private long onDownTS;
    private float onDownX;
    private float onDownY;
    private long onUpTS;
    private float onUpX;
    private float onUpY;
    private String ownerId;
    private StageSwipeablePagesAdapter pagesAdapter;
    private SwipeablePagesStyleSheet styleSheet;
    private RecyclerView swipeablePages;
    private final SwipeablePagesTemplate template;
    private final PublishSubject<Boolean> xrayFinishEvent;
    private final PublishSubject<Pair<Integer, Integer>> xrayStateEvent;

    public StageSwipeablePagesView(String str, Context context, MethodsDispatcher methodsDispatcher, SwipeablePagesTemplate swipeablePagesTemplate) {
        super(context);
        this.isFullScreen = false;
        this.currentPageIndex = 0;
        this.currentSectionIndex = 0;
        this.countOfElementsOfPreviousSections = 0;
        this.isViewBeingShownForTheFirstTime = true;
        this.hasUserInteracted = false;
        this.isLayoutChanged = false;
        this.impressionEndReasonPublishSubject = PublishSubject.create();
        this.xrayFinishEvent = PublishSubject.create();
        this.xrayStateEvent = PublishSubject.create();
        this.context = context;
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.template = swipeablePagesTemplate;
    }

    public StageSwipeablePagesView(String str, Context context, MethodsDispatcher methodsDispatcher, SwipeablePagesTemplate swipeablePagesTemplate, int i, int i2, boolean z) {
        super(context);
        this.isFullScreen = false;
        this.currentPageIndex = 0;
        this.currentSectionIndex = 0;
        this.countOfElementsOfPreviousSections = 0;
        this.isViewBeingShownForTheFirstTime = true;
        this.hasUserInteracted = false;
        this.isLayoutChanged = false;
        this.impressionEndReasonPublishSubject = PublishSubject.create();
        this.xrayFinishEvent = PublishSubject.create();
        this.xrayStateEvent = PublishSubject.create();
        this.context = context;
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.template = swipeablePagesTemplate;
        this.currentPageIndex = i;
        this.currentSectionIndex = i2;
        this.hasUserInteracted = z;
    }

    public void initStyleSheet(SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        this.styleSheet = swipeablePagesStyleSheet;
        init();
    }

    private void init() {
        View.inflate(this.context, R.layout.stage_swipable_pages_view, this);
        this.swipeablePages = (RecyclerView) findViewById(R.id.stage_swipeable_pages);
        this.bottomTabBarLayout = (LinearLayout) findViewById(R.id.bottom_swipe_bar);
        this.pagesAdapter = new StageSwipeablePagesAdapter(this.ownerId, this.context, this.methodsDispatcher, this.template.pages(), this, this.styleSheet, this.isFullScreen, this.xrayStateEvent);
        this.bottomTabBarManager = new StageSwipeablePagesBottomTabManager(this.bottomTabBarLayout, this, this.template.pages());
        ScrollDisabledLinearLayoutManager scrollDisabledLinearLayoutManager = new ScrollDisabledLinearLayoutManager(this.context);
        this.layoutManager = scrollDisabledLinearLayoutManager;
        this.swipeablePages.setLayoutManager(scrollDisabledLinearLayoutManager);
        this.swipeablePages.setHasFixedSize(true);
        this.swipeablePages.setAdapter(this.pagesAdapter);
        this.impressionEndReasonPublishSubject.observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$init$0((ImpressionEndReason) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(ImpressionEndReason impressionEndReason) {
        impressionEnded(impressionEndReason, false);
    }

    public Boolean shouldShowInAutoSequencing() {
        if (!this.template.pages().isEmpty()) {
            return ((SwipeableSectionsElement) this.template.pages().get(0)).showInAutoSequencePreview();
        }
        return Boolean.FALSE;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.manager.StageProgressManager
    public void setCurrentPageIndex(int i) {
        this.currentPageIndex = i;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.manager.StageProgressManager
    public int getCurrentPageIndex() {
        return this.currentPageIndex;
    }

    public int getCurrentSectionIndex() {
        return this.currentSectionIndex;
    }

    public void setIsFullScreen(boolean z) {
        if (this.isFullScreen != z) {
            this.isLayoutChanged = true;
        }
        this.isFullScreen = z;
        if (!z) {
            this.bottomTabBarLayout.setVisibility(8);
        }
        saveCurrentSectionInfo();
        this.pagesAdapter.setIsFullScreen(z);
        resetView();
    }

    public void resetView() {
        resetCurrentPage();
        this.swipeablePages.setAdapter(null);
        this.swipeablePages.setLayoutManager(null);
        this.swipeablePages.setAdapter(this.pagesAdapter);
        this.swipeablePages.setLayoutManager(this.layoutManager);
        this.pagesAdapter.notifyDataSetChanged();
    }

    public void setHasUserInteracted(boolean z) {
        this.hasUserInteracted = z;
    }

    public boolean getHasUserInteracted() {
        return this.hasUserInteracted;
    }

    public SwipeablePagesTemplate getTemplate() {
        return this.template;
    }

    public PublishSubject<Boolean> getXrayFinishEvent() {
        return this.xrayFinishEvent;
    }

    public PublishSubject<ImpressionEndReason> getImpressionEndReasonPublishSubject() {
        return this.impressionEndReasonPublishSubject;
    }

    public PublishSubject<Pair<Integer, Integer>> getXrayStateEvent() {
        return this.xrayStateEvent;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.manager.StageProgressManager
    public void goToPage(final int i, final StageSwipeableSectionsView.Direction direction) {
        StageSwipeablePagesAdapter stageSwipeablePagesAdapter = this.pagesAdapter;
        if (stageSwipeablePagesAdapter == null || this.swipeablePages == null || i >= stageSwipeablePagesAdapter.getItemCount() || i < 0) {
            return;
        }
        this.swipeablePages.scrollToPosition(i);
        this.swipeablePages.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                SwipeablePageView viewForIndex = StageSwipeablePagesView.this.getViewForIndex(i);
                if (viewForIndex instanceof StageSwipeableSectionsView) {
                    StageSwipeableSectionsView stageSwipeableSectionsView = (StageSwipeableSectionsView) viewForIndex;
                    boolean zIsShownInAutoSequencing = stageSwipeableSectionsView.isShownInAutoSequencing();
                    if (StageSwipeablePagesView.this.hasUserInteracted || zIsShownInAutoSequencing) {
                        if (!StageSwipeablePagesView.this.isFullScreen || StageSwipeablePagesView.this.isLayoutChanged) {
                            if (StageSwipeablePagesView.this.isFullScreen) {
                                StageSwipeablePagesView.this.bottomTabBarManager.highlightBottomTab(StageSwipeablePagesView.this.getCurrentPageIndex());
                                StageSwipeablePagesView.this.bottomTabBarLayout.setVisibility(0);
                                StageSwipeablePagesView.this.isLayoutChanged = false;
                            }
                        } else {
                            StageSwipeablePagesView.this.bottomTabBarManager.highlightBottomTab(i);
                        }
                        if (!StageSwipeablePagesView.this.isViewBeingShownForTheFirstTime) {
                            StageSwipeablePagesView.this.resetCurrentSectionIndex();
                        }
                        StageSwipeablePagesView.this.isViewBeingShownForTheFirstTime = false;
                        stageSwipeableSectionsView.setCurrentSection(StageSwipeablePagesView.this.getCurrentSectionIndex());
                        stageSwipeableSectionsView.beginInDirection(direction);
                        StageSwipeablePagesView.this.currentPage = viewForIndex;
                        StageSwipeablePagesView.this.setCurrentPageIndex(i);
                    } else {
                        StageSwipeablePagesView.this.xrayFinishEvent.onNext(Boolean.TRUE);
                    }
                }
                StageSwipeablePagesView.this.swipeablePages.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.manager.StageProgressManager
    public void goToNextPage(float f, boolean z) {
        StageSwipeableSectionsView.Direction direction;
        this.currentSectionIndex = 0;
        this.countOfElementsOfPreviousSections = 0;
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (viewForIndex instanceof StageSwipeableSectionsView) {
            if (this.isFullScreen) {
                direction = StageSwipeableSectionsView.Direction.BOTTOM_TO_TOP;
            } else {
                direction = StageSwipeableSectionsView.Direction.NONE;
            }
            AnimatorSet animatorSetHideCurrentSectionInDirection = ((StageSwipeableSectionsView) viewForIndex).hideCurrentSectionInDirection(direction, f);
            animatorSetHideCurrentSectionInDirection.addListener(nextPageAnimatorListener(viewForIndex, direction, z));
            animatorSetHideCurrentSectionInDirection.start();
        }
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.manager.StageProgressManager
    public void goToPreviousPage(float f) {
        StageSwipeableSectionsView.Direction direction;
        this.currentSectionIndex = 0;
        this.countOfElementsOfPreviousSections = 0;
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (viewForIndex instanceof StageSwipeableSectionsView) {
            if (this.isFullScreen) {
                direction = StageSwipeableSectionsView.Direction.TOP_TO_BOTTOM;
            } else {
                direction = StageSwipeableSectionsView.Direction.NONE;
            }
            AnimatorSet animatorSetHideCurrentSectionInDirection = ((StageSwipeableSectionsView) viewForIndex).hideCurrentSectionInDirection(direction, f);
            animatorSetHideCurrentSectionInDirection.addListener(previousPageAnimatorListener(viewForIndex, direction));
            animatorSetHideCurrentSectionInDirection.start();
        }
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.manager.BottomTabStateManager
    public void goToPage(int i, int i2) {
        impressionEnded(ImpressionEndReason.MANUAL, false);
        this.currentSectionIndex = 0;
        this.countOfElementsOfPreviousSections = 0;
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (viewForIndex instanceof StageSwipeableSectionsView) {
            AnimatorSet animatorSetHideCurrentSectionInDirection = ((StageSwipeableSectionsView) viewForIndex).hideCurrentSectionInDirection(StageSwipeableSectionsView.Direction.NONE, -1.0f);
            animatorSetHideCurrentSectionInDirection.addListener(randomPageAnimatorListener(viewForIndex, i));
            animatorSetHideCurrentSectionInDirection.start();
        }
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.manager.BottomTabStateManager
    public void setActive(TextView textView, boolean z) {
        if (z) {
            textView.setTextColor(ContextCompat.getColor(this.context, R.color.primary));
            textView.setSelected(true);
        } else {
            textView.setTextColor(ContextCompat.getColor(this.context, R.color.primary_glass_4));
            textView.setSelected(false);
        }
    }

    private SectionChangeAnimatorListener nextPageAnimatorListener(final SwipeablePageView swipeablePageView, final StageSwipeableSectionsView.Direction direction, final boolean z) {
        return new SectionChangeAnimatorListener() { // from class: com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView.SectionChangeAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (swipeablePageView instanceof StageSwipeableSectionsView) {
                    if (z) {
                        StageSwipeablePagesView.this.impressionEnded(ImpressionEndReason.MANUAL, false);
                    } else {
                        StageSwipeablePagesView.this.impressionEnded(ImpressionEndReason.AUTO, false);
                    }
                }
                swipeablePageView.reset();
                if (StageSwipeablePagesView.this.getCurrentPageIndex() + 1 >= StageSwipeablePagesView.this.pagesAdapter.getItemCount()) {
                    if (StageSwipeablePagesView.this.isFullScreen) {
                        StageSwipeablePagesView.this.goToPage(0, StageSwipeableSectionsView.Direction.BOTTOM_TO_TOP);
                        return;
                    } else {
                        StageSwipeablePagesView.this.setCurrentPageIndex(0);
                        StageSwipeablePagesView.this.xrayFinishEvent.onNext(Boolean.TRUE);
                        return;
                    }
                }
                StageSwipeablePagesView stageSwipeablePagesView = StageSwipeablePagesView.this;
                stageSwipeablePagesView.goToPage(stageSwipeablePagesView.getCurrentPageIndex() + 1, direction);
            }
        };
    }

    private SectionChangeAnimatorListener previousPageAnimatorListener(final SwipeablePageView swipeablePageView, final StageSwipeableSectionsView.Direction direction) {
        return new SectionChangeAnimatorListener() { // from class: com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView.SectionChangeAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (swipeablePageView instanceof StageSwipeableSectionsView) {
                    StageSwipeablePagesView.this.impressionEnded(ImpressionEndReason.MANUAL, false);
                }
                swipeablePageView.reset();
                if (StageSwipeablePagesView.this.getCurrentPageIndex() <= 0) {
                    if (StageSwipeablePagesView.this.isFullScreen) {
                        StageSwipeablePagesView.this.goToPage(r3.pagesAdapter.getItemCount() - 1, StageSwipeableSectionsView.Direction.TOP_TO_BOTTOM);
                        return;
                    }
                    return;
                }
                StageSwipeablePagesView.this.goToPage(r3.getCurrentPageIndex() - 1, direction);
            }
        };
    }

    private SectionChangeAnimatorListener randomPageAnimatorListener(final SwipeablePageView swipeablePageView, final int i) {
        return new SectionChangeAnimatorListener() { // from class: com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView.SectionChangeAnimatorListener, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                swipeablePageView.clearAnimation();
                swipeablePageView.reset();
                if (StageSwipeablePagesView.this.isFullScreen) {
                    StageSwipeablePagesView.this.goToPage(i, StageSwipeableSectionsView.Direction.NONE);
                }
            }
        };
    }

    private void saveCurrentSectionInfo() {
        StageSwipeableSectionsView stageSwipeableSectionsView;
        List<VerticalContainerView> sections;
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (!(viewForIndex instanceof StageSwipeableSectionsView) || (sections = (stageSwipeableSectionsView = (StageSwipeableSectionsView) viewForIndex).getSections()) == null) {
            return;
        }
        this.currentSectionIndex = stageSwipeableSectionsView.getCurrentSection();
        int childCount = 0;
        for (int i = 0; i < this.currentSectionIndex; i++) {
            childCount = sections.get(i).getChildCount() == 1 ? childCount + 1 : childCount + (sections.get(i).getChildCount() - 1);
        }
        this.countOfElementsOfPreviousSections = childCount;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetCurrentSectionIndex() {
        List<VerticalContainerView> sections;
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (!(viewForIndex instanceof StageSwipeableSectionsView) || (sections = ((StageSwipeableSectionsView) viewForIndex).getSections()) == null) {
            return;
        }
        int i = -1;
        int childCount = 0;
        while (childCount <= this.countOfElementsOfPreviousSections && i < sections.size()) {
            i++;
            childCount = sections.get(i).getChildCount() == 1 ? childCount + 1 : childCount + (sections.get(i).getChildCount() - 1);
        }
        this.currentSectionIndex = i;
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            if (this.isFullScreen) {
                goToPage(getCurrentPageIndex(), StageSwipeableSectionsView.Direction.BOTTOM_TO_TOP);
                return;
            } else {
                goToPage(getCurrentPageIndex(), StageSwipeableSectionsView.Direction.NONE);
                return;
            }
        }
        resetCurrentPage();
    }

    public boolean handleTouch(MotionEvent motionEvent) {
        StageSwipeableSectionsViewHolder stageSwipeableSectionsViewHolder = (StageSwipeableSectionsViewHolder) this.swipeablePages.findViewHolderForAdapterPosition(getCurrentPageIndex());
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.onDownX = motionEvent.getX(0);
            this.onDownY = motionEvent.getY(0);
            this.onDownTS = System.currentTimeMillis();
            stageSwipeableSectionsViewHolder.getView().stopTimer();
        } else if (actionMasked == 1) {
            this.onUpX = motionEvent.getX(0);
            this.onUpY = motionEvent.getY(0);
            this.onUpTS = System.currentTimeMillis();
            stageSwipeableSectionsViewHolder.getView().resumeTimer();
            if ((this.onUpTS - this.onDownTS) / 1000.0d >= 0.5d) {
                onLongPressed();
            }
            float fAbs = Math.abs(this.onUpX - this.onDownX) / (this.onUpTS - this.onDownTS);
            Math.abs(this.onUpY - this.onDownY);
            int i = AnonymousClass5.$SwitchMap$com$amazon$music$explore$views$swipeablePages$StageSwipeablePagesView$SwipeDirection[getDirection(this.onDownX, this.onDownY, this.onUpX, this.onUpY).ordinal()];
            if (i == 1) {
                SwipeablePageView swipeablePageView = this.currentPage;
                if (!(swipeablePageView instanceof StageSwipeableSectionsView)) {
                    return true;
                }
                ((StageSwipeableSectionsView) swipeablePageView).showNextSection(fAbs, true);
                return true;
            }
            if (i == 2) {
                SwipeablePageView swipeablePageView2 = this.currentPage;
                if (!(swipeablePageView2 instanceof StageSwipeableSectionsView)) {
                    return true;
                }
                ((StageSwipeableSectionsView) swipeablePageView2).showPreviousSection(fAbs);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$explore$views$swipeablePages$StageSwipeablePagesView$SwipeDirection;

        static {
            int[] iArr = new int[SwipeDirection.values().length];
            $SwitchMap$com$amazon$music$explore$views$swipeablePages$StageSwipeablePagesView$SwipeDirection = iArr;
            try {
                iArr[SwipeDirection.UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$StageSwipeablePagesView$SwipeDirection[SwipeDirection.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void resetCurrentPage() {
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (viewForIndex instanceof StageSwipeableSectionsView) {
            saveCurrentSectionInfo();
            viewForIndex.reset();
        }
    }

    public ImpressionEndReason evaluateEndReason() {
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (viewForIndex instanceof StageSwipeableSectionsView) {
            StageSwipeableSectionsView stageSwipeableSectionsView = (StageSwipeableSectionsView) viewForIndex;
            if (stageSwipeableSectionsView.isUserNavigation) {
                stageSwipeableSectionsView.isUserNavigation = false;
                return ImpressionEndReason.USER_NAVIGATION;
            }
            return ImpressionEndReason.SWITCH_TO_PREVIEW;
        }
        return ImpressionEndReason.SWITCH_TO_PREVIEW;
    }

    public void impressionEnded(ImpressionEndReason impressionEndReason, boolean z) {
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (viewForIndex instanceof StageSwipeableSectionsView) {
            ((StageSwipeableSectionsView) viewForIndex).onVerticalContainerExited(impressionEndReason, z);
        }
    }

    private void onLongPressed() {
        ((StageSwipeableSectionsView) getViewForIndex(getCurrentPageIndex())).onLongPressed();
    }

    public void onPreviewClicked() {
        SwipeablePageView viewForIndex = getViewForIndex(getCurrentPageIndex());
        if (viewForIndex instanceof StageSwipeableSectionsView) {
            ((StageSwipeableSectionsView) viewForIndex).onPreviewClicked();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SwipeablePageView getViewForIndex(int i) {
        SwipeablePageViewHolder swipeablePageViewHolder = (SwipeablePageViewHolder) this.swipeablePages.findViewHolderForAdapterPosition(i);
        if (swipeablePageViewHolder != null) {
            return swipeablePageViewHolder.getView();
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isFullScreen) {
            return handleTouch(motionEvent);
        }
        return true;
    }

    private SwipeDirection getDirection(float f, float f2, float f3, float f4) {
        if (distance(f, f2, f3, f4) <= 15.0f) {
            return SwipeDirection.NONE;
        }
        return SwipeDirection.fromPoints(f, f2, f3, f4);
    }

    public enum SwipeDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        NONE;

        private static final int ANGLE_DEGREES_0 = 0;
        private static final int ANGLE_DEGREES_135 = 135;
        private static final int ANGLE_DEGREES_180 = 180;
        private static final int ANGLE_DEGREES_225 = 225;
        private static final int ANGLE_DEGREES_315 = 315;
        private static final int ANGLE_DEGREES_360 = 360;
        private static final int ANGLE_DEGREES_45 = 45;

        private static boolean inRange(double d, float f, float f2) {
            return d >= ((double) f) && d < ((double) f2);
        }

        public static SwipeDirection fromPoints(float f, float f2, float f3, float f4) {
            double angle = getAngle(f, f2, f3, f4);
            if (inRange(angle, 45.0f, 135.0f)) {
                return UP;
            }
            if (inRange(angle, 0.0f, 45.0f) || inRange(angle, 315.0f, 360.0f)) {
                return RIGHT;
            }
            if (inRange(angle, 225.0f, 315.0f)) {
                return DOWN;
            }
            return LEFT;
        }

        private static double getAngle(float f, float f2, float f3, float f4) {
            return ((((Math.atan2(f2 - f4, f3 - f) + 3.141592653589793d) * 180.0d) / 3.141592653589793d) + 180.0d) % 360.0d;
        }
    }

    private float distance(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return pxToDp((float) Math.sqrt((f5 * f5) + (f6 * f6)));
    }

    private float pxToDp(float f) {
        return f / getResources().getDisplayMetrics().density;
    }

    private class ScrollDisabledLinearLayoutManager extends LinearLayoutManager {
        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollHorizontally() {
            return false;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollVertically() {
            return false;
        }

        public ScrollDisabledLinearLayoutManager(Context context) {
            super(context);
        }
    }

    private class SectionChangeAnimatorListener implements Animator.AnimatorListener {
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        private SectionChangeAnimatorListener() {
        }
    }
}
