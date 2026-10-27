package com.amazon.music.explore.views.swipeablePages.pages;

import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeablePageElement;
import Touch.SwipeablePagesTemplateInterface.v1_0.SwipeableSectionsElement;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.amazon.music.explore.R;
import com.amazon.music.explore.picasso.Blur2Transformation;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.TouchEventHandler;
import com.amazon.music.explore.views.swipeablePages.binders.SwipeableSectionsBinder;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.SwipeableSectionsVerticalContainer;
import com.amazon.music.explore.views.swipeablePages.pages.verticalContainer.VerticalContainerView;
import com.amazon.music.explore.views.swipeablePages.peek.PeekProgressBar;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeableSectionsView extends SwipeablePageView {
    private AnimatorSet animatorSet;
    private SwipeablePageImageView background;
    private SwipeablePageImageView backgroundCopy;
    private SwipeablePageImageView backgroundOverlay;
    private final Blur2Transformation blurTransformation;
    private int currentSection;
    private SwipeableSectionsElement pageElement;
    private int progressBarMax;
    private List<VerticalContainerView> sections;
    private final TouchEventHandler touchEventHandler;
    private SwipeableSectionsVerticalContainer verticalContainer;

    public SwipeableSectionsView(String str, Context context, MethodsDispatcher methodsDispatcher, PeekProgressBar peekProgressBar, SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        super(str, context, methodsDispatcher, peekProgressBar, swipeablePagesStyleSheet);
        this.sections = new ArrayList();
        this.currentSection = 0;
        this.touchEventHandler = createTouchEventHandler();
        this.blurTransformation = new Blur2Transformation(context);
        init();
    }

    private void init() {
        View.inflate(this.context, R.layout.swipeable_sections_view, this);
        this.verticalContainer = (SwipeableSectionsVerticalContainer) findViewById(R.id.swipeable_sections_vertical_container);
        this.background = (SwipeablePageImageView) findViewById(R.id.swipeable_sections_background);
        this.backgroundOverlay = (SwipeablePageImageView) findViewById(R.id.swipeable_sections_background_overlay);
        this.backgroundCopy = (SwipeablePageImageView) findViewById(R.id.swipeable_sections_background_copy);
        setStyleSheets();
        initializeVerticalContainer();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.onTouchedY = motionEvent.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (actionMasked == 2) {
            if (this.currentSection == 0 && motionEvent.getY() > this.onTouchedY) {
                if (!isFirstPage()) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else if (Math.abs(motionEvent.getY() - this.onTouchedY) > 100.0f) {
                    onClose();
                    return true;
                }
            } else if (this.currentSection == this.sections.size() - 1 && motionEvent.getY() < this.onTouchedY) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void bind(SwipeablePageElement swipeablePageElement) {
        SwipeableSectionsElement swipeableSectionsElement = (SwipeableSectionsElement) swipeablePageElement;
        this.pageElement = swipeableSectionsElement;
        SwipeableSectionsBinder.bind(this, swipeableSectionsElement, this.styleSheet);
        this.progressBarMax = ((int) this.verticalContainer.getSectionDuration()) * this.sections.size();
        showSection(this.currentSection);
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void reset() {
        this.currentSection = 0;
        showSection(0);
        this.verticalContainer.stopTimer();
        AnimatorSet animatorSet = this.animatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.animatorSet.cancel();
        }
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public void begin() {
        if (!this.hasBeenViewed) {
            this.methodsDispatcher.dispatchMethods(this.ownerId, this.pageElement.onViewed());
            this.hasBeenViewed = true;
        }
        onVerticalContainerViewed();
        this.verticalContainer.restartTimer();
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public int getMaxProgress() {
        return this.progressBarMax;
    }

    @Override // com.amazon.music.explore.views.swipeablePages.pages.SwipeablePageView
    public SwipeableSectionsVerticalContainer getVerticalContainer() {
        return this.verticalContainer;
    }

    public void setSections(List<VerticalContainerView> list) {
        this.sections = list;
    }

    private void setStyleSheets() {
        this.background.setStyleSheet(this.styleSheet);
        this.backgroundCopy.setStyleSheet(this.styleSheet);
        this.backgroundOverlay.setStyleSheet(this.styleSheet);
    }

    private void initializeVerticalContainer() {
        this.verticalContainer.setTouchEventHandler(this.touchEventHandler);
        this.verticalContainer.setProgressBar(this.progressBar);
        this.verticalContainer.setStyleSheet(this.styleSheet);
    }

    private void showSection(int i) {
        this.verticalContainer.removeAllViews();
        this.verticalContainer.addView(this.sections.get(i));
        updateBackgroundImage();
        this.verticalContainer.centerInParent(getVerticalContainerHeight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNextSection() {
        if (this.currentSection < this.sections.size() - 1) {
            int i = this.currentSection + 1;
            this.currentSection = i;
            showSection(i);
            onVerticalContainerViewed();
            this.animations.fadeInFromBottom(this.verticalContainer).start();
            this.verticalContainer.restartTimer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPreviousSection() {
        int i = this.currentSection - 1;
        this.currentSection = i;
        showSection(i);
        onVerticalContainerViewed();
        this.animations.fadeInFromTop(this.verticalContainer).start();
        this.verticalContainer.restartTimer();
    }

    private int getVerticalContainerHeight() {
        this.verticalContainer.measure(0, 0);
        return this.verticalContainer.getMeasuredHeight();
    }

    private void onVerticalContainerViewed() {
        VerticalContainerView verticalContainerView = this.sections.get(this.currentSection);
        if (verticalContainerView.hasBeenViewed()) {
            return;
        }
        verticalContainerView.dispatchMethods(this.ownerId, this.methodsDispatcher, verticalContainerView.onViewedMethods);
        verticalContainerView.setHasBeenViewed(true);
    }

    private void updateBackgroundImage() {
        VerticalContainerView verticalContainerView = this.sections.get(this.currentSection);
        String backgroundImage = verticalContainerView.getBackgroundImage();
        if (StringUtils.isNotEmpty(backgroundImage)) {
            RequestCreator requestCreatorLoad = Picasso.get().load(backgroundImage);
            if (verticalContainerView.shouldBlurBackground()) {
                requestCreatorLoad.transform(this.blurTransformation);
            }
            fadeInBackgroundImage(requestCreatorLoad);
        }
    }

    private void fadeInBackgroundImage(RequestCreator requestCreator) {
        this.backgroundCopy.setImageDrawable(this.background.getDrawable());
        this.background.setAlpha(0.0f);
        requestCreator.into(this.background);
        this.animations.fadeInBackgroundImage(this.background).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator.AnimatorListener nextSectionAnimatorListener() {
        return new Animator.AnimatorListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.SwipeableSectionsView.1
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
                SwipeableSectionsView.this.showNextSection();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator.AnimatorListener previousSectionAnimatorListener() {
        return new Animator.AnimatorListener() { // from class: com.amazon.music.explore.views.swipeablePages.pages.SwipeableSectionsView.2
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
                SwipeableSectionsView.this.showPreviousSection();
            }
        };
    }

    private TouchEventHandler createTouchEventHandler() {
        return new TouchEventHandler(this.context) { // from class: com.amazon.music.explore.views.swipeablePages.pages.SwipeableSectionsView.3
            @Override // com.amazon.music.explore.views.swipeablePages.TouchEventHandler
            public void onSwipeUp() {
                if (SwipeableSectionsView.this.currentSection < SwipeableSectionsView.this.sections.size() - 1) {
                    if (SwipeableSectionsView.this.verticalContainer.isSwipeable()) {
                        SwipeableSectionsView.this.showNextSection();
                        SwipeableSectionsView swipeableSectionsView = SwipeableSectionsView.this;
                        swipeableSectionsView.progressBar.setProgressTo(((int) swipeableSectionsView.verticalContainer.getSectionDuration()) * SwipeableSectionsView.this.currentSection);
                    } else {
                        SwipeableSectionsView swipeableSectionsView2 = SwipeableSectionsView.this;
                        swipeableSectionsView2.animatorSet = swipeableSectionsView2.animations.fadeOutToTop(swipeableSectionsView2.verticalContainer);
                        SwipeableSectionsView.this.animatorSet.addListener(SwipeableSectionsView.this.nextSectionAnimatorListener());
                        SwipeableSectionsView.this.animatorSet.start();
                    }
                }
            }

            @Override // com.amazon.music.explore.views.swipeablePages.TouchEventHandler
            public void onSwipeDown() {
                if (SwipeableSectionsView.this.currentSection > 0) {
                    if (SwipeableSectionsView.this.verticalContainer.isSwipeable()) {
                        SwipeableSectionsView.this.showPreviousSection();
                        SwipeableSectionsView swipeableSectionsView = SwipeableSectionsView.this;
                        swipeableSectionsView.progressBar.setProgressTo(((int) swipeableSectionsView.verticalContainer.getSectionDuration()) * SwipeableSectionsView.this.currentSection);
                    } else {
                        SwipeableSectionsView swipeableSectionsView2 = SwipeableSectionsView.this;
                        swipeableSectionsView2.animatorSet = swipeableSectionsView2.animations.fadeOutToBottom(swipeableSectionsView2.verticalContainer);
                        SwipeableSectionsView.this.animatorSet.addListener(SwipeableSectionsView.this.previousSectionAnimatorListener());
                        SwipeableSectionsView.this.animatorSet.start();
                    }
                }
            }
        };
    }
}
