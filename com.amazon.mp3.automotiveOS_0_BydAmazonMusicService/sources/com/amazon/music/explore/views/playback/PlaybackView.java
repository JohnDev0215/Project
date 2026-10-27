package com.amazon.music.explore.views.playback;

import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.IconButtonElement;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.TextView;
import com.amazon.music.explore.ExploreResources;
import com.amazon.music.explore.ExploreStyleSheet;
import com.amazon.music.explore.R;
import com.amazon.music.explore.fragment.PlaybackFragment;
import com.amazon.music.explore.metrics.SpotlightImageLatencyFlexEventEmitter;
import com.amazon.music.explore.providers.AnimationProvider;
import com.amazon.music.explore.views.swipeablePages.TouchEventHandler;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.callback.PlaybackViewCallback;
import com.amazon.music.skyfire.ui.managers.CountDownTimerManager;
import com.amazon.music.skyfire.ui.managers.ObjectAnimatorManager;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.amazon.music.skyfire.ui.widgets.views.IconButtonView;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackView extends FrameLayout implements PlaybackViewCallback {
    private static final float CAPTION_MAX_SCALE_FACTOR = 0.7f;
    private static final float CAPTION_SCREEN_WIDTH_PERCENTAGE = 0.6f;
    private static final long PAUSE_OVERLAY_FADE_DURATION_MS = 200;
    private static final long SLIDING_DURATION_MS = 200;
    private static final int TIMER_INTERVAL_MS = 100;
    private LinearLayout actionButtons;
    private int activePointerId;
    private final AnimationProvider animationProvider;
    private ImageView backgroundImage;
    private boolean canSwipe;
    private TextView caption;
    private IconButtonView closeButton;
    private boolean hasPlaybackFinished;
    private boolean hasPlaybackStarted;
    private final MethodsDispatcher methodsDispatcher;
    private final MetricsProvider metricsProvider;
    private float onTouchedPointerY;
    private TextView overlineText;
    private final String ownerId;
    private ObjectAnimatorManager pauseAnimator;
    private LinearLayout pausePlaybackArea;
    private RelativeLayout pausedOverlay;
    private IconButtonView pausedOverlayButton;
    private ImageView placeholderImage;
    private ObjectAnimatorManager playAnimator;
    private final PlaybackFragment playbackFragment;
    private final PlaybackProvider playbackProvider;
    private SeekBar progressBar;
    private CountDownTimerManager progressBarManager;
    private TextView secondaryText;
    private final ObjectAnimatorManager slidingAnimator;
    private RelativeLayout swipeableArea;
    private final PlaybackTemplate template;
    private final TouchEventHandler touchEventHandler;
    private FrameLayout viewContainer;
    private boolean willBeRemoved;

    public PlaybackView(Context context, String str, MethodsDispatcher methodsDispatcher, PlaybackTemplate playbackTemplate, PlaybackFragment playbackFragment, PlaybackProvider playbackProvider, AnimationProvider animationProvider, MetricsProvider metricsProvider) {
        super(context);
        this.canSwipe = true;
        this.willBeRemoved = false;
        this.hasPlaybackStarted = false;
        this.hasPlaybackFinished = false;
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.template = playbackTemplate;
        this.playbackFragment = playbackFragment;
        this.playbackProvider = playbackProvider;
        this.animationProvider = animationProvider;
        this.metricsProvider = metricsProvider;
        this.slidingAnimator = new ObjectAnimatorManager(this, "y");
        this.touchEventHandler = new TouchEventHandler(context);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.playback_view, this);
        this.backgroundImage = (ImageView) findViewById(R.id.playback_background_image);
        this.placeholderImage = (ImageView) findViewById(R.id.playback_placeholder_background);
        this.viewContainer = (FrameLayout) findViewById(R.id.playback_view_container);
        this.caption = (TextView) findViewById(R.id.playback_caption);
        this.overlineText = (TextView) findViewById(R.id.playback_overline_text);
        this.secondaryText = (TextView) findViewById(R.id.playback_secondary_text);
        this.swipeableArea = (RelativeLayout) findViewById(R.id.playback_swipeable_area);
        this.actionButtons = (LinearLayout) findViewById(R.id.playback_action_buttons);
        initializeSubViews();
        bind();
        IntroAnimations.start(this.viewContainer, this.caption, this.backgroundImage);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.canSwipe;
    }

    public void dismissAndPlay(final TriggerExternalGenericPlaybackMethod triggerExternalGenericPlaybackMethod) {
        setWillBeRemoved(true);
        this.slidingAnimator.setFloatValues(-getHeight());
        this.slidingAnimator.setDuration(200L);
        this.slidingAnimator.setListener(new Animator.AnimatorListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView.1
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
                Activity activity = (Activity) PlaybackView.this.getContext();
                if (!PlaybackView.this.playbackFragment.isStopped()) {
                    activity.onBackPressed();
                }
                if (CollectionUtils.isEmpty(triggerExternalGenericPlaybackMethod.content())) {
                    return;
                }
                PlaybackView.this.playbackProvider.play(triggerExternalGenericPlaybackMethod.content().get(0), PlaybackView.this.getContext());
            }
        });
        this.slidingAnimator.start();
    }

    @Override // com.amazon.music.skyfire.ui.callback.PlaybackViewCallback
    public void startProgressBar(long j) {
        this.hasPlaybackStarted = true;
        handleSwipe();
        this.progressBar.setMax((int) j);
        CountDownTimerManager countDownTimerManager = new CountDownTimerManager(this.progressBar.getMax(), 100L) { // from class: com.amazon.music.explore.views.playback.PlaybackView.2
            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onTicked(long j2) {
                PlaybackView.this.progressBar.setProgress(PlaybackView.this.progressBar.getProgress() + 100);
            }

            @Override // com.amazon.music.skyfire.ui.managers.CountDownTimerManager
            public void onFinished() {
                PlaybackView.this.progressBar.setProgress(PlaybackView.this.progressBar.getMax());
            }
        };
        this.progressBarManager = countDownTimerManager;
        countDownTimerManager.start();
    }

    public void onRotate() {
        IntroAnimations.onRotate(this.backgroundImage);
        setCaptionWidth();
    }

    public void pause() {
        CountDownTimerManager countDownTimerManager = this.progressBarManager;
        if (countDownTimerManager != null) {
            countDownTimerManager.stop();
        }
        this.playbackProvider.pause();
        this.pausedOverlay.setVisibility(0);
        this.pauseAnimator.start();
    }

    public void resume() {
        this.playbackProvider.resume();
        CountDownTimerManager countDownTimerManager = this.progressBarManager;
        if (countDownTimerManager != null) {
            countDownTimerManager.start();
        }
        this.playAnimator.start();
    }

    @Override // com.amazon.music.skyfire.ui.callback.PlaybackViewCallback
    public void onAudioEnded() {
        this.canSwipe = false;
        this.methodsDispatcher.dispatchMethods(this.ownerId, this.template.onAudioEnded());
        this.hasPlaybackFinished = true;
    }

    @Override // com.amazon.music.skyfire.ui.callback.PlaybackViewCallback
    public boolean willBeRemoved() {
        return this.willBeRemoved;
    }

    public boolean hasPlaybackStarted() {
        return this.hasPlaybackStarted;
    }

    public boolean hasPlaybackFinished() {
        return this.hasPlaybackFinished;
    }

    public void setWillBeRemoved(boolean z) {
        this.willBeRemoved = z;
    }

    public void close() {
        setWillBeRemoved(true);
        this.playbackProvider.stopPlaybackAndClearQueue();
        ((Activity) getContext()).finish();
        this.animationProvider.slideOut((Activity) getContext());
    }

    public void clear() {
        this.slidingAnimator.stop();
        CountDownTimerManager countDownTimerManager = this.progressBarManager;
        if (countDownTimerManager != null) {
            countDownTimerManager.stop();
        }
        IntroAnimations.removeAnimators();
    }

    private void onSwipedUp() {
        this.canSwipe = false;
        this.methodsDispatcher.dispatchMethods(this.ownerId, this.template.onSwipedUp());
    }

    private void bind() {
        setImage(this.backgroundImage, this.template.backgroundImage());
        setText(this.caption, this.template.primaryText());
        setText(this.secondaryText, this.template.secondaryText());
        setText(this.overlineText, this.template.tertiaryText());
        setActionButtons(this.template.actionButtons());
        setFontFamily();
        useOptimallySizedFontForCaption();
    }

    private void setImage(ImageView imageView, String str) {
        if (StringUtils.isEmpty(str)) {
            imageView.setVisibility(8);
            return;
        }
        IntroAnimations.resizeBackgroundImage(imageView);
        final SpotlightImageLatencyFlexEventEmitter spotlightImageLatencyFlexEventEmitter = new SpotlightImageLatencyFlexEventEmitter(this.metricsProvider);
        Picasso.get().load(str).into(imageView, new Callback() { // from class: com.amazon.music.explore.views.playback.PlaybackView.3
            @Override // com.squareup.picasso.Callback
            public void onError(Exception exc) {
            }

            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                spotlightImageLatencyFlexEventEmitter.emitFlexEvent();
            }
        });
        spotlightImageLatencyFlexEventEmitter.startTiming();
        imageView.setVisibility(0);
    }

    private void setText(TextView textView, String str) {
        if (StringUtils.isEmpty(str)) {
            textView.setVisibility(8);
        } else {
            textView.setText(str);
            textView.setVisibility(0);
        }
    }

    private void setFontFamily() {
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_bold));
        this.caption.setTypeface(Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_display_condensed)));
        this.overlineText.setTypeface(typefaceCreateFromAsset);
        this.secondaryText.setTypeface(typefaceCreateFromAsset);
    }

    private void setActionButtons(List<ButtonElement> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof IconButtonElement) {
                IconButtonView iconButtonView = new IconButtonView(getContext(), getResources().getDimensionPixelSize(R.dimen.playback_icon_button_size));
                iconButtonView.bind((IconButtonElement) list.get(i), this.ownerId, this.methodsDispatcher);
                iconButtonView.setButtonContentDescription(ExploreResources.PLAYBACK_ACTION_BUTTON_PREFIX + i);
                setIconButtonLabelStyle(iconButtonView);
                this.actionButtons.addView(iconButtonView);
                if (i < list.size() - 1) {
                    Space space = new Space(getContext());
                    space.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 1.0f));
                    this.actionButtons.addView(space);
                }
            }
        }
        centerMiddleButtonAndLabel();
    }

    private void setIconButtonLabelStyle(IconButtonView iconButtonView) {
        TextView label = iconButtonView.getLabel();
        label.setTypeface(Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular)));
        label.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.playback_icon_button_label_size));
        label.setLineSpacing(getResources().getDimensionPixelSize(R.dimen.playback_icon_button_line_spacing), 1.0f);
        if (getResources().getConfiguration().orientation == 2) {
            ((LinearLayout.LayoutParams) label.getLayoutParams()).topMargin = getResources().getDimensionPixelSize(R.dimen.spacer_mini);
        }
    }

    private void centerMiddleButtonAndLabel() {
        this.actionButtons.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                IconButtonView iconButtonView = (IconButtonView) PlaybackView.this.actionButtons.getChildAt(0);
                IconButtonView iconButtonView2 = (IconButtonView) PlaybackView.this.actionButtons.getChildAt(PlaybackView.this.actionButtons.getChildCount() - 1);
                if (iconButtonView != iconButtonView2) {
                    if (iconButtonView.getWidth() > iconButtonView2.getWidth()) {
                        iconButtonView2.setMinimumWidth(iconButtonView.getWidth());
                    } else {
                        iconButtonView.setMinimumWidth(iconButtonView2.getWidth());
                    }
                }
                PlaybackView.this.actionButtons.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void useOptimallySizedFontForCaption() {
        TextView textView = this.caption;
        textView.setText(textView.getText().toString().toUpperCase());
        float dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.playback_caption_size);
        ExploreStyleSheet.getOptimallySizedFontForLongestWord(this.caption, dimensionPixelOffset, CAPTION_MAX_SCALE_FACTOR * dimensionPixelOffset);
    }

    private void initializeSubViews() {
        initializeCloseButton();
        initalizePauseArea();
        initializePausedOverlay();
        initializeProgressBar();
        setCaptionWidth();
        this.playbackFragment.applyLoadingImage(this.placeholderImage);
    }

    private void initializeCloseButton() {
        IconButtonView iconButtonView = (IconButtonView) findViewById(R.id.playback_close);
        this.closeButton = iconButtonView;
        iconButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initializeCloseButton$0(view);
            }
        });
        this.closeButton.setImage("https://m.media-amazon.com/images/G/01/music-explore/ic_action_cancel.png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeCloseButton$0(View view) {
        close();
    }

    private void initalizePauseArea() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.pause_playback_area);
        this.pausePlaybackArea = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initalizePauseArea$1(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initalizePauseArea$1(View view) {
        pause();
    }

    private void setCaptionWidth() {
        this.caption.setWidth((int) (ScreenUtils.getScreenWidth(getContext()) * CAPTION_SCREEN_WIDTH_PERCENTAGE));
    }

    private void initializePausedOverlay() {
        this.pausedOverlay = (RelativeLayout) findViewById(R.id.playback_paused_overlay);
        this.pausedOverlayButton = (IconButtonView) findViewById(R.id.playback_paused_overlay_button);
        createPauseAnimator();
        createPlayAnimator();
        this.pausedOverlay.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initializePausedOverlay$2(view);
            }
        });
        this.pausedOverlayButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initializePausedOverlay$3(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePausedOverlay$2(View view) {
        resume();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePausedOverlay$3(View view) {
        resume();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void initializeProgressBar() {
        SeekBar seekBar = (SeekBar) findViewById(R.id.playback_progress_bar);
        this.progressBar = seekBar;
        seekBar.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView$$ExternalSyntheticLambda5
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$initializeProgressBar$4(view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initializeProgressBar$4(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.progressBarManager.stop();
            return false;
        }
        if (motionEvent.getActionMasked() != 1) {
            return false;
        }
        int progress = this.progressBar.getProgress();
        int max = this.progressBar.getMax() - progress;
        this.playbackProvider.seekTo(progress);
        if (this.pausedOverlay.getVisibility() == 0) {
            this.progressBarManager.reset(max);
            return false;
        }
        this.progressBarManager.restart(max);
        return false;
    }

    private void createPauseAnimator() {
        ObjectAnimatorManager objectAnimatorManager = new ObjectAnimatorManager(this.pausedOverlay, "alpha");
        this.pauseAnimator = objectAnimatorManager;
        objectAnimatorManager.setDuration(200L);
        this.pauseAnimator.setFloatValues(0.0f, 1.0f);
    }

    private void createPlayAnimator() {
        ObjectAnimatorManager objectAnimatorManager = new ObjectAnimatorManager(this.pausedOverlay, "alpha");
        this.playAnimator = objectAnimatorManager;
        objectAnimatorManager.setDuration(200L);
        this.playAnimator.setFloatValues(1.0f, 0.0f);
        this.playAnimator.setListener(new Animator.AnimatorListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView.5
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
                PlaybackView.this.pausedOverlay.setVisibility(8);
            }
        });
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void handleSwipe() {
        this.swipeableArea.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.explore.views.playback.PlaybackView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$handleSwipe$5(view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$handleSwipe$5(View view, MotionEvent motionEvent) {
        this.touchEventHandler.onTouch(view, motionEvent);
        if (this.touchEventHandler.wasFlingedUp()) {
            onSwipedUp();
            return false;
        }
        if (this.touchEventHandler.wasSingleTapped()) {
            pause();
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.onTouchedPointerY = motionEvent.getY();
            this.activePointerId = motionEvent.getPointerId(0);
        } else if (actionMasked != 1) {
            if (actionMasked == 2) {
                translateView(motionEvent);
            } else if (actionMasked == 6) {
                handleMultiTouch(motionEvent);
            }
        } else if (getY() <= (-getHeight()) * 0.2f) {
            onSwipedUp();
        } else {
            this.slidingAnimator.setFloatValues(0.0f);
            this.slidingAnimator.setDuration(200L);
            this.slidingAnimator.start();
        }
        return true;
    }

    private void translateView(MotionEvent motionEvent) {
        float y = motionEvent.getY(motionEvent.findPointerIndex(this.activePointerId)) - this.onTouchedPointerY;
        if (getY() + y > 0.0f) {
            setY(0.0f);
        } else {
            setY(getY() + y);
        }
    }

    private void handleMultiTouch(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.activePointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.onTouchedPointerY = motionEvent.getY(i);
            this.activePointerId = motionEvent.getPointerId(i);
        }
    }
}
