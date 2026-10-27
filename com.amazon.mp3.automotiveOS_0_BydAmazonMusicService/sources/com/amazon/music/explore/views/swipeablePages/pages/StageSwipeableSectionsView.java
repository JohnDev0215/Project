package com.amazon.music.explore.views.swipeablePages.pages;

import CoreInterface.v1_0.Element;
import Touch.SwipeablePagesTemplateInterface.v1_0.ImpressionEndReason;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionsElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.VerticalContainerElement;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.util.Pair;
import com.amazon.music.explore.R;
import com.amazon.music.explore.picasso.Blur2Transformation;
import com.amazon.music.explore.skyfire.ExploreClientInformationCache;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.TouchEventHandler;
import com.amazon.music.explore.views.swipeablePages.binders.SwipeableSectionsBinder;
import com.amazon.music.explore.views.swipeablePages.pages.manager.StageProgressManager;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.StageSwipeableSectionsVerticalContainer;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.StageVerticalSectionTimerManager;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.VerticalContainerView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.amazonaws.util.DateUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import rx.subjects.PublishSubject;

/* JADX INFO: loaded from: classes4.dex */
public class StageSwipeableSectionsView extends SwipeablePageView implements SwipeablePageCallback {
    private static final int STAGE_CONTAINER_DURATION_MS = 8500;
    private static final int STAGE_EXIT_DURATION_MS = 1000;
    private static final int STAGE_FULL_SCREEN_ENTRANCE_DURATION_MS = 200;
    private static final int STAGE_FULL_SCREEN_EXIT_DURATION_MS = 200;
    private AnimatorSet animatorSet;
    private StageSwipeablePageImageView background;
    private StageSwipeablePageImageView backgroundCopy;
    private StageSwipeablePageImageView backgroundOverlay;
    private final Blur2Transformation blurTransformation;
    private int currentSection;
    public String entryTimeHolder;
    private FrameLayout imagesContainer;
    private boolean isFullScreen;
    public boolean isUserNavigation;
    private SwipeablePageElement pageElement;
    private Integer position;
    private int previousSection;
    private List<VerticalContainerView> sections;
    private StageProgressManager stageProgressManager;
    private StageVerticalSectionTimerManager stageVerticalSectionTimerManager;
    private StageXrayFullScreenProgressBarsView stageXrayFullScreenProgressBars;
    private TouchEventHandler touchEventHandler;
    private StageSwipeableSectionsVerticalContainer verticalContainer;
    private PublishSubject<Pair<Integer, Integer>> xrayStateEvent;

    public enum Direction {
        TOP_TO_BOTTOM,
        BOTTOM_TO_TOP,
        NONE
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void begin() {
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public int getMaxProgress() {
        return 0;
    }

    public StageSwipeableSectionsView(String str, Context context, MethodsDispatcher methodsDispatcher, StageProgressManager stageProgressManager, SwipeablePagesStyleSheet swipeablePagesStyleSheet, boolean z) {
        super(str, context, methodsDispatcher, null, swipeablePagesStyleSheet);
        this.currentSection = 0;
        this.previousSection = 0;
        this.sections = new ArrayList();
        this.isUserNavigation = false;
        this.stageProgressManager = stageProgressManager;
        this.isFullScreen = z;
        this.blurTransformation = new Blur2Transformation(context);
        init();
    }

    public void setPosition(int i) {
        this.position = Integer.valueOf(i);
    }

    public void setXrayStateEvent(PublishSubject<Pair<Integer, Integer>> publishSubject) {
        this.xrayStateEvent = publishSubject;
    }

    public Integer getPosition() {
        return this.position;
    }

    private void init() {
        View.inflate(this.context, R.layout.stage_swipeable_sections_view, this);
        this.touchEventHandler = createTouchEventHandler();
        this.verticalContainer = (StageSwipeableSectionsVerticalContainer) findViewById(R.id.stage_swipeable_sections_vertical_container);
        this.background = (StageSwipeablePageImageView) findViewById(R.id.stage_swipeable_sections_background);
        this.backgroundOverlay = (StageSwipeablePageImageView) findViewById(R.id.stage_swipeable_sections_background_overlay);
        this.backgroundCopy = (StageSwipeablePageImageView) findViewById(R.id.stage_swipeable_sections_background_copy);
        this.stageXrayFullScreenProgressBars = (StageXrayFullScreenProgressBarsView) findViewById(R.id.stage_x_ray_full_screen_progress_bar);
        this.stageVerticalSectionTimerManager = new StageVerticalSectionTimerManager(this.isFullScreen);
        this.imagesContainer = (FrameLayout) findViewById(R.id.stage_swipeable_pages_images_container);
        initializeVerticalContainer();
        if (this.isFullScreen) {
            updateBackgroundImage();
        }
    }

    private void initializeVerticalContainer() {
        this.stageVerticalSectionTimerManager.setTouchEventHandler(this.touchEventHandler);
        this.verticalContainer.setStyleSheet(this.styleSheet);
    }

    private void initializeContainerIndicator() {
        this.stageXrayFullScreenProgressBars.setProgressBars(this.sections.size());
        this.stageVerticalSectionTimerManager.setProgressBars(this.stageXrayFullScreenProgressBars.getProgressBars());
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void bind(SwipeablePageElement swipeablePageElement) {
        this.pageElement = swipeablePageElement;
        SwipeableSectionsBinder.bindStage(this, swipeablePageElement, this.styleSheet, STAGE_CONTAINER_DURATION_MS, this.isFullScreen);
        this.stageVerticalSectionTimerManager.initializeTimerManager();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void reset() {
        this.stageVerticalSectionTimerManager.stopTimer();
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.animatorSet.cancel();
        }
    }

    public void beginInDirection(Direction direction) {
        if (!this.hasBeenViewed) {
            this.methodsDispatcher.dispatchMethods(this.ownerId, this.pageElement.onViewed());
            this.hasBeenViewed = true;
        }
        if (this.isFullScreen) {
            initializeContainerIndicator();
        }
        this.stageVerticalSectionTimerManager.restartTimer();
        showCurrentSectionInDirection(direction);
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public StageSwipeableSectionsVerticalContainer getVerticalContainer() {
        return this.verticalContainer;
    }

    private String getDateTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ALTERNATE_ISO8601_DATE_PATTERN);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(date);
    }

    private void onVerticalContainerViewed() {
        this.entryTimeHolder = getDateTime();
    }

    public void onVerticalContainerExited(ImpressionEndReason impressionEndReason, boolean z) {
        VerticalContainerView verticalContainerView;
        if ((impressionEndReason == ImpressionEndReason.MANUAL || impressionEndReason == ImpressionEndReason.AUTO) && z) {
            verticalContainerView = this.sections.get(this.previousSection);
        } else {
            verticalContainerView = this.sections.get(this.currentSection);
        }
        String dateTime = getDateTime();
        ExploreClientInformationCache exploreClientInformationCache = ExploreClientInformationCache.INSTANCE;
        exploreClientInformationCache.impressionTimestamp = this.entryTimeHolder;
        exploreClientInformationCache.impressionEndTimestamp = dateTime;
        exploreClientInformationCache.impressionEndReason = impressionEndReason;
        if (this.isFullScreen) {
            verticalContainerView.dispatchMethods(this.ownerId, this.methodsDispatcher, verticalContainerView.onViewedMethods);
        } else {
            verticalContainerView.dispatchMethods(this.ownerId, this.methodsDispatcher, verticalContainerView.onViewedPreviewMethods);
        }
        verticalContainerView.setHasBeenViewed(true);
    }

    public void onLongPressed() {
        VerticalContainerView verticalContainerView = this.sections.get(this.currentSection);
        verticalContainerView.dispatchMethods(this.ownerId, this.methodsDispatcher, verticalContainerView.onLongPressedMethods);
    }

    public void onPreviewClicked() {
        VerticalContainerView verticalContainerView = this.sections.get(this.currentSection);
        verticalContainerView.dispatchMethods(this.ownerId, this.methodsDispatcher, verticalContainerView.onPreviewClickedMethods);
    }

    public void setCurrentSection(int i) {
        this.currentSection = i;
        this.stageVerticalSectionTimerManager.setCurrentSection(i);
        this.stageXrayFullScreenProgressBars.setCurrentSection(i);
    }

    public int getCurrentSection() {
        return this.currentSection;
    }

    public List<VerticalContainerView> getSections() {
        return this.sections;
    }

    private void updateBackgroundImage() {
        this.imagesContainer.setVisibility(0);
    }

    public void setSections(List<VerticalContainerView> list) {
        this.sections = list;
    }

    public boolean isShownInAutoSequencing() {
        SwipeablePageElement swipeablePageElement = this.pageElement;
        if (swipeablePageElement instanceof SwipeableSectionsElement) {
            return ((SwipeableSectionsElement) swipeablePageElement).showInAutoSequencePreview().booleanValue();
        }
        return false;
    }

    public boolean canVerticalContainerOverflow() {
        List<Element> listSections;
        SwipeablePageElement swipeablePageElement = this.pageElement;
        if (!(swipeablePageElement instanceof SwipeableSectionsElement) || (listSections = ((SwipeableSectionsElement) swipeablePageElement).sections()) == null || listSections.size() <= 0 || !(listSections.get(0) instanceof VerticalContainerElement)) {
            return false;
        }
        return ((VerticalContainerElement) listSections.get(0)).canSpillOverToNextSection().booleanValue();
    }

    private float getVerticalContainerTranslationDelta() {
        return getVerticalContainerHeight();
    }

    private void showSection(int i, Direction direction) {
        this.verticalContainer.removeAllViews();
        this.verticalContainer.clearAnimation();
        Direction direction2 = Direction.NONE;
        if (direction == direction2 && this.isFullScreen) {
            this.verticalContainer.animate().translationX(0.0f).translationY(0.0f).setDuration(0L);
        }
        this.verticalContainer.addView(this.sections.get(i));
        if (direction == Direction.TOP_TO_BOTTOM) {
            this.verticalContainer.setY((ScreenUtils.getAvailableScreenHeight(this.context) / 2.0f) - (getVerticalContainerHeight() * 1.5f));
        } else if (direction == Direction.BOTTOM_TO_TOP) {
            this.verticalContainer.setY((ScreenUtils.getAvailableScreenHeight(this.context) / 2.0f) + (getVerticalContainerHeight() / 2.0f));
        } else if (direction == direction2 && this.isFullScreen) {
            this.verticalContainer.setY((int) ((ScreenUtils.getAvailableScreenHeight(this.context) / 2.0f) - (getVerticalContainerHeight() / 2.0f)));
        }
        this.xrayStateEvent.onNext(new Pair<>(this.position, Integer.valueOf(i)));
    }

    public void showNextSection(float f, boolean z) {
        this.stageVerticalSectionTimerManager.setCurrentSectionProgress(STAGE_CONTAINER_DURATION_MS);
        if (this.currentSection < this.sections.size() - 1) {
            this.stageVerticalSectionTimerManager.restartTimer();
            int i = this.currentSection;
            this.previousSection = i;
            int i2 = i + 1;
            this.currentSection = i2;
            this.stageVerticalSectionTimerManager.setCurrentSection(i2);
            this.stageXrayFullScreenProgressBars.setCurrentSection(this.currentSection);
            Direction direction = this.isFullScreen ? Direction.BOTTOM_TO_TOP : Direction.NONE;
            AnimatorSet animatorSetHideCurrentSectionInDirection = hideCurrentSectionInDirection(direction, f);
            this.animatorSet = animatorSetHideCurrentSectionInDirection;
            animatorSetHideCurrentSectionInDirection.addListener(nextSectionAnimatorListener(direction, z));
            this.animatorSet.start();
            return;
        }
        this.stageProgressManager.goToNextPage(f, z);
    }

    public void showPreviousSection(float f) {
        this.stageVerticalSectionTimerManager.setCurrentSectionProgress(0);
        if (this.currentSection > 0) {
            this.stageVerticalSectionTimerManager.restartTimer();
            int i = this.currentSection;
            this.previousSection = i;
            int i2 = i - 1;
            this.currentSection = i2;
            this.stageVerticalSectionTimerManager.setCurrentSection(i2);
            this.stageXrayFullScreenProgressBars.setCurrentSection(this.currentSection);
            Direction direction = this.isFullScreen ? Direction.TOP_TO_BOTTOM : Direction.NONE;
            AnimatorSet animatorSetHideCurrentSectionInDirection = hideCurrentSectionInDirection(direction, f);
            this.animatorSet = animatorSetHideCurrentSectionInDirection;
            animatorSetHideCurrentSectionInDirection.addListener(previousSectionAnimatorListener(direction));
            this.animatorSet.start();
            return;
        }
        this.stageProgressManager.goToPreviousPage(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCurrentSectionInDirection(Direction direction) {
        cancelCurrentAnimation();
        showSection(this.currentSection, direction);
        int i = AnonymousClass4.$SwitchMap$com$amazon$music$explore$views$swipeablePages$pages$StageSwipeableSectionsView$Direction[direction.ordinal()];
        if (i == 1) {
            this.animatorSet = this.animations.slideInFadeInEntrance(this.verticalContainer, getVerticalContainerTranslationDelta(), 200);
        } else if (i == 2) {
            this.animatorSet = this.animations.slideInFadeInEntrance(this.verticalContainer, getVerticalContainerTranslationDelta() * (-1.0f), 200);
        } else if (this.isFullScreen) {
            this.animatorSet = this.animations.fadeInCubicInEntry(this.verticalContainer, 200L);
        } else {
            this.animatorSet = this.animations.fadeInSlideInEntry(this.verticalContainer, 8500L);
        }
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.start();
        }
        onVerticalContainerViewed();
    }

    /* JADX INFO: renamed from: com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$explore$views$swipeablePages$pages$StageSwipeableSectionsView$Direction;

        static {
            int[] iArr = new int[Direction.values().length];
            $SwitchMap$com$amazon$music$explore$views$swipeablePages$pages$StageSwipeableSectionsView$Direction = iArr;
            try {
                iArr[Direction.TOP_TO_BOTTOM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$explore$views$swipeablePages$pages$StageSwipeableSectionsView$Direction[Direction.BOTTOM_TO_TOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public AnimatorSet hideCurrentSectionInDirection(Direction direction, float f) {
        cancelCurrentAnimation();
        int verticalContainerHeight = f > 0.0f ? (int) (getVerticalContainerHeight() / f) : 200;
        int i = AnonymousClass4.$SwitchMap$com$amazon$music$explore$views$swipeablePages$pages$StageSwipeableSectionsView$Direction[direction.ordinal()];
        if (i == 1) {
            this.animatorSet = this.animations.slideOutFadeOutExit(this.verticalContainer, getVerticalContainerTranslationDelta(), Math.min(verticalContainerHeight, 200));
        } else if (i == 2) {
            this.animatorSet = this.animations.slideOutFadeOutExit(this.verticalContainer, getVerticalContainerTranslationDelta() * (-1.0f), Math.min(verticalContainerHeight, 200));
        } else if (this.isFullScreen) {
            this.animatorSet = this.animations.fadeOutCubicInExit(this.verticalContainer, 200L);
        } else {
            this.animatorSet = this.animations.fadeOutCubicInExit(this.verticalContainer, 1000L);
        }
        return this.animatorSet;
    }

    private void cancelCurrentAnimation() {
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            if (this.animatorSet.isRunning()) {
                this.animatorSet.cancel();
            }
            this.animatorSet = null;
        }
    }

    private int getVerticalContainerHeight() {
        this.verticalContainer.measure(0, 0);
        return this.verticalContainer.getMeasuredHeight();
    }

    private Animator.AnimatorListener nextSectionAnimatorListener(final Direction direction, final boolean z) {
        return new Animator.AnimatorListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (z) {
                    StageSwipeableSectionsView.this.onVerticalContainerExited(ImpressionEndReason.MANUAL, true);
                } else {
                    StageSwipeableSectionsView.this.onVerticalContainerExited(ImpressionEndReason.AUTO, true);
                }
                StageSwipeableSectionsView.this.showCurrentSectionInDirection(direction);
            }
        };
    }

    private Animator.AnimatorListener previousSectionAnimatorListener(final Direction direction) {
        return new Animator.AnimatorListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                StageSwipeableSectionsView.this.onVerticalContainerExited(ImpressionEndReason.MANUAL, true);
                StageSwipeableSectionsView.this.showCurrentSectionInDirection(direction);
            }
        };
    }

    private TouchEventHandler createTouchEventHandler() {
        return new TouchEventHandler(this.context) { // from class: com.amazon.music.explore.views.swipeablePages.pages.StageSwipeableSectionsView.3
            @Override // com.amazon.music.explore.views.swipeablePages.TouchEventHandler
            public void onSwipeRight() {
                StageSwipeableSectionsView.this.showPreviousSection(0.0f);
            }

            @Override // com.amazon.music.explore.views.swipeablePages.TouchEventHandler
            public void onSwipeLeft() {
                StageSwipeableSectionsView.this.showNextSection(0.0f, false);
            }
        };
    }

    public void stopTimer() {
        this.stageVerticalSectionTimerManager.stopTimer();
    }

    public void resumeTimer() {
        this.stageVerticalSectionTimerManager.startTimer();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageCallback
    public void setIsUserNavigation(boolean z) {
        this.isUserNavigation = z;
    }
}
