package com.android.inputmethod.keyboard;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.android.inputmethod.accessibility.AccessibilityUtils;
import com.android.inputmethod.accessibility.MainKeyboardAccessibilityDelegate;
import com.android.inputmethod.keyboard.MoreKeysKeyboard;
import com.android.inputmethod.keyboard.MoreKeysPanel;
import com.android.inputmethod.keyboard.internal.DrawingPreviewPlacerView;
import com.android.inputmethod.keyboard.internal.DrawingProxy;
import com.android.inputmethod.keyboard.internal.GestureFloatingTextDrawingPreview;
import com.android.inputmethod.keyboard.internal.GestureTrailsDrawingPreview;
import com.android.inputmethod.keyboard.internal.KeyDrawParams;
import com.android.inputmethod.keyboard.internal.KeyPreviewChoreographer;
import com.android.inputmethod.keyboard.internal.KeyPreviewDrawParams;
import com.android.inputmethod.keyboard.internal.KeyPreviewView;
import com.android.inputmethod.keyboard.internal.MoreKeySpec;
import com.android.inputmethod.keyboard.internal.NonDistinctMultitouchHelper;
import com.android.inputmethod.keyboard.internal.SlidingKeyInputDrawingPreview;
import com.android.inputmethod.keyboard.internal.TimerHandler;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.CoordinateUtils;
import com.android.inputmethod.latin.settings.DebugSettings;
import com.android.inputmethod.latin.utils.TypefaceUtils;
import java.util.Iterator;
import java.util.WeakHashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class MainKeyboardView extends KeyboardView implements DrawingProxy, MoreKeysPanel.Controller {
    private static final float LANGUAGE_ON_SPACEBAR_TEXT_SHADOW_RADIUS_DISABLED = -1.0f;
    private static final float MINIMUM_XSCALE_OF_LANGUAGE_NAME = 0.8f;
    private static final String TAG = "MainKeyboardView";
    private MainKeyboardAccessibilityDelegate mAccessibilityDelegate;
    private int mAltCodeKeyWhileTypingAnimAlpha;
    private final ObjectAnimator mAltCodeKeyWhileTypingFadeinAnimator;
    private final ObjectAnimator mAltCodeKeyWhileTypingFadeoutAnimator;
    private final Paint mBackgroundDimAlphaPaint;
    private final boolean mConfigShowMoreKeysKeyboardAtTouchedPoint;
    private final DrawingPreviewPlacerView mDrawingPreviewPlacerView;
    private int mGestureFloatingPreviewTextLingerTimeout;
    private final GestureFloatingTextDrawingPreview mGestureFloatingTextDrawingPreview;
    private final GestureTrailsDrawingPreview mGestureTrailsDrawingPreview;
    private boolean mHasMultipleEnabledIMEsOrSubtypes;
    private final KeyDetector mKeyDetector;
    private final KeyPreviewChoreographer mKeyPreviewChoreographer;
    private final KeyPreviewDrawParams mKeyPreviewDrawParams;
    private KeyboardActionListener mKeyboardActionListener;
    private int mLanguageOnSpacebarAnimAlpha;
    private ObjectAnimator mLanguageOnSpacebarFadeoutAnimator;
    private final int mLanguageOnSpacebarFinalAlpha;
    private int mLanguageOnSpacebarFormatType;
    private final int mLanguageOnSpacebarHorizontalMargin;
    private final int mLanguageOnSpacebarTextColor;
    private final float mLanguageOnSpacebarTextRatio;
    private final int mLanguageOnSpacebarTextShadowColor;
    private final float mLanguageOnSpacebarTextShadowRadius;
    private float mLanguageOnSpacebarTextSize;
    private final WeakHashMap<Key, Keyboard> mMoreKeysKeyboardCache;
    private final View mMoreKeysKeyboardContainer;
    private final View mMoreKeysKeyboardForActionContainer;
    private MoreKeysPanel mMoreKeysPanel;
    private final NonDistinctMultitouchHelper mNonDistinctMultitouchHelper;
    private final int[] mOriginCoords;
    private final SlidingKeyInputDrawingPreview mSlidingKeyInputDrawingPreview;
    private Key mSpaceKey;
    private final TimerHandler mTimerHandler;

    public MainKeyboardView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.mainKeyboardViewStyle);
    }

    public MainKeyboardView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        this.mLanguageOnSpacebarAnimAlpha = 255;
        this.mAltCodeKeyWhileTypingAnimAlpha = 255;
        this.mOriginCoords = CoordinateUtils.newInstance();
        Paint paint = new Paint();
        this.mBackgroundDimAlphaPaint = paint;
        this.mMoreKeysKeyboardCache = new WeakHashMap<>();
        DrawingPreviewPlacerView drawingPreviewPlacerView = new DrawingPreviewPlacerView(context, attrs);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.MainKeyboardView, defStyle, R.style.MainKeyboardView);
        TimerHandler timerHandler = new TimerHandler(this, typedArrayObtainStyledAttributes.getInt(34, 0), typedArrayObtainStyledAttributes.getInt(19, 0));
        this.mTimerHandler = timerHandler;
        this.mKeyDetector = new KeyDetector(typedArrayObtainStyledAttributes.getDimension(35, 0.0f), typedArrayObtainStyledAttributes.getDimension(36, 0.0f));
        PointerTracker.init(typedArrayObtainStyledAttributes, timerHandler, this);
        this.mNonDistinctMultitouchHelper = context.getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.distinct") && !PreferenceManager.getDefaultSharedPreferences(context).getBoolean(DebugSettings.PREF_FORCE_NON_DISTINCT_MULTITOUCH, false) ? null : new NonDistinctMultitouchHelper();
        int i = typedArrayObtainStyledAttributes.getInt(2, 0);
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setAlpha(i);
        this.mLanguageOnSpacebarTextRatio = typedArrayObtainStyledAttributes.getFraction(49, 1, 1, 1.0f);
        this.mLanguageOnSpacebarTextColor = typedArrayObtainStyledAttributes.getColor(48, 0);
        this.mLanguageOnSpacebarTextShadowRadius = typedArrayObtainStyledAttributes.getFloat(51, -1.0f);
        this.mLanguageOnSpacebarTextShadowColor = typedArrayObtainStyledAttributes.getColor(50, 0);
        this.mLanguageOnSpacebarFinalAlpha = typedArrayObtainStyledAttributes.getInt(47, 255);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(46, 0);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        KeyPreviewDrawParams keyPreviewDrawParams = new KeyPreviewDrawParams(typedArrayObtainStyledAttributes);
        this.mKeyPreviewDrawParams = keyPreviewDrawParams;
        this.mKeyPreviewChoreographer = new KeyPreviewChoreographer(keyPreviewDrawParams);
        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(54, 0);
        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(53, resourceId4);
        this.mConfigShowMoreKeysKeyboardAtTouchedPoint = typedArrayObtainStyledAttributes.getBoolean(55, false);
        this.mGestureFloatingPreviewTextLingerTimeout = typedArrayObtainStyledAttributes.getInt(13, 0);
        GestureFloatingTextDrawingPreview gestureFloatingTextDrawingPreview = new GestureFloatingTextDrawingPreview(typedArrayObtainStyledAttributes);
        this.mGestureFloatingTextDrawingPreview = gestureFloatingTextDrawingPreview;
        gestureFloatingTextDrawingPreview.setDrawingView(drawingPreviewPlacerView);
        GestureTrailsDrawingPreview gestureTrailsDrawingPreview = new GestureTrailsDrawingPreview(typedArrayObtainStyledAttributes);
        this.mGestureTrailsDrawingPreview = gestureTrailsDrawingPreview;
        gestureTrailsDrawingPreview.setDrawingView(drawingPreviewPlacerView);
        SlidingKeyInputDrawingPreview slidingKeyInputDrawingPreview = new SlidingKeyInputDrawingPreview(typedArrayObtainStyledAttributes);
        this.mSlidingKeyInputDrawingPreview = slidingKeyInputDrawingPreview;
        slidingKeyInputDrawingPreview.setDrawingView(drawingPreviewPlacerView);
        typedArrayObtainStyledAttributes.recycle();
        this.mDrawingPreviewPlacerView = drawingPreviewPlacerView;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        this.mMoreKeysKeyboardContainer = layoutInflaterFrom.inflate(resourceId4, (ViewGroup) null);
        this.mMoreKeysKeyboardForActionContainer = layoutInflaterFrom.inflate(resourceId5, (ViewGroup) null);
        this.mLanguageOnSpacebarFadeoutAnimator = loadObjectAnimator(resourceId, this);
        this.mAltCodeKeyWhileTypingFadeoutAnimator = loadObjectAnimator(resourceId2, this);
        this.mAltCodeKeyWhileTypingFadeinAnimator = loadObjectAnimator(resourceId3, this);
        this.mKeyboardActionListener = KeyboardActionListener.EMPTY_LISTENER;
        this.mLanguageOnSpacebarHorizontalMargin = (int) getResources().getDimension(R.dimen.config_language_on_spacebar_horizontal_margin);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView
    public void setHardwareAcceleratedDrawingEnabled(final boolean enabled) {
        super.setHardwareAcceleratedDrawingEnabled(enabled);
        this.mDrawingPreviewPlacerView.setHardwareAcceleratedDrawingEnabled(enabled);
    }

    private ObjectAnimator loadObjectAnimator(final int resId, final Object target) {
        if (resId == 0) {
            return null;
        }
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(), resId);
        if (objectAnimator != null) {
            objectAnimator.setTarget(target);
        }
        return objectAnimator;
    }

    private static void cancelAndStartAnimators(final ObjectAnimator animatorToCancel, final ObjectAnimator animatorToStart) {
        if (animatorToCancel == null || animatorToStart == null) {
            return;
        }
        float animatedFraction = 0.0f;
        if (animatorToCancel.isStarted()) {
            animatorToCancel.cancel();
            animatedFraction = 1.0f - animatorToCancel.getAnimatedFraction();
        }
        animatorToStart.start();
        animatorToStart.setCurrentPlayTime((long) (animatorToStart.getDuration() * animatedFraction));
    }

    @Override // com.android.inputmethod.keyboard.internal.DrawingProxy
    public void startWhileTypingAnimation(final int fadeInOrOut) {
        if (fadeInOrOut == 0) {
            cancelAndStartAnimators(this.mAltCodeKeyWhileTypingFadeoutAnimator, this.mAltCodeKeyWhileTypingFadeinAnimator);
        } else {
            if (fadeInOrOut != 1) {
                return;
            }
            cancelAndStartAnimators(this.mAltCodeKeyWhileTypingFadeinAnimator, this.mAltCodeKeyWhileTypingFadeoutAnimator);
        }
    }

    public int getLanguageOnSpacebarAnimAlpha() {
        return this.mLanguageOnSpacebarAnimAlpha;
    }

    public void setLanguageOnSpacebarAnimAlpha(final int alpha) {
        this.mLanguageOnSpacebarAnimAlpha = alpha;
        invalidateKey(this.mSpaceKey);
    }

    public int getAltCodeKeyWhileTypingAnimAlpha() {
        return this.mAltCodeKeyWhileTypingAnimAlpha;
    }

    public void setAltCodeKeyWhileTypingAnimAlpha(final int alpha) {
        if (this.mAltCodeKeyWhileTypingAnimAlpha == alpha) {
            return;
        }
        this.mAltCodeKeyWhileTypingAnimAlpha = alpha;
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            return;
        }
        Iterator<Key> it = keyboard.mAltCodeKeysWhileTyping.iterator();
        while (it.hasNext()) {
            invalidateKey(it.next());
        }
    }

    public void setKeyboardActionListener(final KeyboardActionListener listener) {
        this.mKeyboardActionListener = listener;
        PointerTracker.setKeyboardActionListener(listener);
    }

    public int getKeyX(final int x) {
        return Constants.isValidCoordinate(x) ? this.mKeyDetector.getTouchX(x) : x;
    }

    public int getKeyY(final int y) {
        return Constants.isValidCoordinate(y) ? this.mKeyDetector.getTouchY(y) : y;
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView
    public void setKeyboard(final Keyboard keyboard) {
        this.mTimerHandler.cancelLongPressTimers();
        super.setKeyboard(keyboard);
        this.mKeyDetector.setKeyboard(keyboard, -getPaddingLeft(), (-getPaddingTop()) + getVerticalCorrection());
        PointerTracker.setKeyDetector(this.mKeyDetector);
        this.mMoreKeysKeyboardCache.clear();
        this.mSpaceKey = keyboard.getKey(32);
        this.mLanguageOnSpacebarTextSize = (keyboard.mMostCommonKeyHeight - keyboard.mVerticalGap) * this.mLanguageOnSpacebarTextRatio;
        if (AccessibilityUtils.getInstance().isAccessibilityEnabled()) {
            if (this.mAccessibilityDelegate == null) {
                this.mAccessibilityDelegate = new MainKeyboardAccessibilityDelegate(this, this.mKeyDetector);
            }
            this.mAccessibilityDelegate.setKeyboard(keyboard);
            return;
        }
        this.mAccessibilityDelegate = null;
    }

    public void setKeyPreviewPopupEnabled(final boolean previewEnabled, final int delay) {
        this.mKeyPreviewDrawParams.setPopupEnabled(previewEnabled, delay);
    }

    public void setKeyPreviewAnimationParams(final boolean hasCustomAnimationParams, final float showUpStartXScale, final float showUpStartYScale, final int showUpDuration, final float dismissEndXScale, final float dismissEndYScale, final int dismissDuration) {
        this.mKeyPreviewDrawParams.setAnimationParams(hasCustomAnimationParams, showUpStartXScale, showUpStartYScale, showUpDuration, dismissEndXScale, dismissEndYScale, dismissDuration);
    }

    private void locatePreviewPlacerView() {
        getLocationInWindow(this.mOriginCoords);
        this.mDrawingPreviewPlacerView.setKeyboardViewGeometry(this.mOriginCoords, getWidth(), getHeight());
    }

    private void installPreviewPlacerView() {
        View rootView = getRootView();
        if (rootView == null) {
            Log.w(TAG, "Cannot find root view");
            return;
        }
        ViewGroup viewGroup = (ViewGroup) rootView.findViewById(android.R.id.content);
        if (viewGroup == null) {
            Log.w(TAG, "Cannot find android.R.id.content view to add DrawingPreviewPlacerView");
        } else {
            viewGroup.addView(this.mDrawingPreviewPlacerView);
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.DrawingProxy
    public void onKeyPressed(@Nonnull final Key key, final boolean withPreview) {
        key.onPressed();
        invalidateKey(key);
        if (!withPreview || key.noKeyPreview()) {
            return;
        }
        showKeyPreview(key);
    }

    private void showKeyPreview(@Nonnull final Key key) {
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            return;
        }
        KeyPreviewDrawParams keyPreviewDrawParams = this.mKeyPreviewDrawParams;
        if (!keyPreviewDrawParams.isPopupEnabled()) {
            keyPreviewDrawParams.setVisibleOffset(-keyboard.mVerticalGap);
            return;
        }
        locatePreviewPlacerView();
        getLocationInWindow(this.mOriginCoords);
        this.mKeyPreviewChoreographer.placeAndShowKeyPreview(key, keyboard.mIconsSet, getKeyDrawParams(), getWidth(), this.mOriginCoords, this.mDrawingPreviewPlacerView, isHardwareAccelerated());
    }

    private void dismissKeyPreviewWithoutDelay(@Nonnull final Key key) {
        this.mKeyPreviewChoreographer.dismissKeyPreview(key, false);
        invalidateKey(key);
    }

    @Override // com.android.inputmethod.keyboard.internal.DrawingProxy
    public void onKeyReleased(@Nonnull final Key key, final boolean withAnimation) {
        key.onReleased();
        invalidateKey(key);
        if (key.noKeyPreview()) {
            return;
        }
        if (withAnimation) {
            dismissKeyPreview(key);
        } else {
            dismissKeyPreviewWithoutDelay(key);
        }
    }

    private void dismissKeyPreview(@Nonnull final Key key) {
        if (isHardwareAccelerated()) {
            this.mKeyPreviewChoreographer.dismissKeyPreview(key, true);
        } else {
            this.mTimerHandler.postDismissKeyPreview(key, this.mKeyPreviewDrawParams.getLingerTimeout());
        }
    }

    public void setSlidingKeyInputPreviewEnabled(final boolean enabled) {
        this.mSlidingKeyInputDrawingPreview.setPreviewEnabled(enabled);
    }

    @Override // com.android.inputmethod.keyboard.internal.DrawingProxy
    public void showSlidingKeyInputPreview(@Nullable final PointerTracker tracker) {
        locatePreviewPlacerView();
        if (tracker != null) {
            this.mSlidingKeyInputDrawingPreview.setPreviewPosition(tracker);
        } else {
            this.mSlidingKeyInputDrawingPreview.dismissSlidingKeyInputPreview();
        }
    }

    private void setGesturePreviewMode(final boolean isGestureTrailEnabled, final boolean isGestureFloatingPreviewTextEnabled) {
        this.mGestureFloatingTextDrawingPreview.setPreviewEnabled(isGestureFloatingPreviewTextEnabled);
        this.mGestureTrailsDrawingPreview.setPreviewEnabled(isGestureTrailEnabled);
    }

    public void showGestureFloatingPreviewText(@Nonnull final SuggestedWords suggestedWords, final boolean dismissDelayed) {
        locatePreviewPlacerView();
        this.mGestureFloatingTextDrawingPreview.setSuggetedWords(suggestedWords);
        if (dismissDelayed) {
            this.mTimerHandler.postDismissGestureFloatingPreviewText(this.mGestureFloatingPreviewTextLingerTimeout);
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.DrawingProxy
    public void dismissGestureFloatingPreviewTextWithoutDelay() {
        this.mGestureFloatingTextDrawingPreview.dismissGestureFloatingPreviewText();
    }

    @Override // com.android.inputmethod.keyboard.internal.DrawingProxy
    public void showGestureTrail(@Nonnull final PointerTracker tracker, final boolean showsFloatingPreviewText) {
        locatePreviewPlacerView();
        if (showsFloatingPreviewText) {
            this.mGestureFloatingTextDrawingPreview.setPreviewPosition(tracker);
        }
        this.mGestureTrailsDrawingPreview.setPreviewPosition(tracker);
    }

    public void setMainDictionaryAvailability(final boolean mainDictionaryAvailable) {
        PointerTracker.setMainDictionaryAvailability(mainDictionaryAvailable);
    }

    public void setGestureHandlingEnabledByUser(final boolean isGestureHandlingEnabledByUser, final boolean isGestureTrailEnabled, final boolean isGestureFloatingPreviewTextEnabled) {
        PointerTracker.setGestureHandlingEnabledByUser(isGestureHandlingEnabledByUser);
        setGesturePreviewMode(isGestureHandlingEnabledByUser && isGestureTrailEnabled, isGestureHandlingEnabledByUser && isGestureFloatingPreviewTextEnabled);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        installPreviewPlacerView();
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mDrawingPreviewPlacerView.removeAllViews();
    }

    @Override // com.android.inputmethod.keyboard.internal.DrawingProxy
    @Nullable
    public MoreKeysPanel showMoreKeysKeyboard(@Nonnull final Key key, @Nonnull final PointerTracker tracker) {
        int x;
        MoreKeySpec[] moreKeys = key.getMoreKeys();
        if (moreKeys == null) {
            return null;
        }
        Keyboard keyboardBuild = this.mMoreKeysKeyboardCache.get(key);
        boolean z = false;
        if (keyboardBuild == null) {
            keyboardBuild = new MoreKeysKeyboard.Builder(getContext(), key, getKeyboard(), this.mKeyPreviewDrawParams.isPopupEnabled() && !key.noKeyPreview() && moreKeys.length == 1 && this.mKeyPreviewDrawParams.getVisibleWidth() > 0, this.mKeyPreviewDrawParams.getVisibleWidth(), this.mKeyPreviewDrawParams.getVisibleHeight(), newLabelPaint(key)).build();
            this.mMoreKeysKeyboardCache.put(key, keyboardBuild);
        }
        View view = key.isActionKey() ? this.mMoreKeysKeyboardForActionContainer : this.mMoreKeysKeyboardContainer;
        MoreKeysKeyboardView moreKeysKeyboardView = (MoreKeysKeyboardView) view.findViewById(R.id.more_keys_keyboard_view);
        moreKeysKeyboardView.setKeyboard(keyboardBuild);
        view.measure(-2, -2);
        int[] iArrNewInstance = CoordinateUtils.newInstance();
        tracker.getLastCoordinates(iArrNewInstance);
        if (this.mKeyPreviewDrawParams.isPopupEnabled() && !key.noKeyPreview()) {
            z = true;
        }
        if (this.mConfigShowMoreKeysKeyboardAtTouchedPoint && !z) {
            x = CoordinateUtils.x(iArrNewInstance);
        } else {
            x = key.getX() + (key.getWidth() / 2);
        }
        moreKeysKeyboardView.showMoreKeysPanel(this, this, x, key.getY() + this.mKeyPreviewDrawParams.getVisibleOffset(), this.mKeyboardActionListener);
        return moreKeysKeyboardView;
    }

    public boolean isInDraggingFinger() {
        if (isShowingMoreKeysPanel()) {
            return true;
        }
        return PointerTracker.isAnyInDraggingFinger();
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
    public void onShowMoreKeysPanel(final MoreKeysPanel panel) {
        locatePreviewPlacerView();
        onDismissMoreKeysPanel();
        this.mSlidingKeyInputDrawingPreview.dismissSlidingKeyInputPreview();
        panel.showInParent(this.mDrawingPreviewPlacerView);
        this.mMoreKeysPanel = panel;
    }

    public boolean isShowingMoreKeysPanel() {
        MoreKeysPanel moreKeysPanel = this.mMoreKeysPanel;
        return moreKeysPanel != null && moreKeysPanel.isShowingInParent();
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
    public void onCancelMoreKeysPanel() {
        PointerTracker.dismissAllMoreKeysPanels();
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
    public void onDismissMoreKeysPanel() {
        if (isShowingMoreKeysPanel()) {
            this.mMoreKeysPanel.removeFromParent();
            this.mMoreKeysPanel = null;
        }
    }

    public void startDoubleTapShiftKeyTimer() {
        this.mTimerHandler.startDoubleTapShiftKeyTimer();
    }

    public void cancelDoubleTapShiftKeyTimer() {
        this.mTimerHandler.cancelDoubleTapShiftKeyTimer();
    }

    public boolean isInDoubleTapShiftKeyTimeout() {
        return this.mTimerHandler.isInDoubleTapShiftKeyTimeout();
    }

    @Override // android.view.View
    public boolean onTouchEvent(final MotionEvent event) {
        if (getKeyboard() == null) {
            return false;
        }
        if (this.mNonDistinctMultitouchHelper != null) {
            if (event.getPointerCount() > 1 && this.mTimerHandler.isInKeyRepeat()) {
                this.mTimerHandler.cancelKeyRepeatTimers();
            }
            this.mNonDistinctMultitouchHelper.processMotionEvent(event, this.mKeyDetector);
            return true;
        }
        return processMotionEvent(event);
    }

    public boolean processMotionEvent(final MotionEvent event) {
        PointerTracker pointerTracker = PointerTracker.getPointerTracker(event.getPointerId(event.getActionIndex()));
        if (isShowingMoreKeysPanel() && !pointerTracker.isShowingMoreKeysPanel() && PointerTracker.getActivePointerTrackerCount() == 1) {
            return true;
        }
        pointerTracker.processMotionEvent(event, this.mKeyDetector);
        return true;
    }

    public void cancelAllOngoingEvents() {
        this.mTimerHandler.cancelAllMessages();
        PointerTracker.setReleasedKeyGraphicsToAllKeys();
        this.mGestureFloatingTextDrawingPreview.dismissGestureFloatingPreviewText();
        this.mSlidingKeyInputDrawingPreview.dismissSlidingKeyInputPreview();
        PointerTracker.dismissAllMoreKeysPanels();
        PointerTracker.cancelAllPointerTrackers();
    }

    public void closing() {
        cancelAllOngoingEvents();
        this.mMoreKeysKeyboardCache.clear();
    }

    public void onHideWindow() {
        onDismissMoreKeysPanel();
        MainKeyboardAccessibilityDelegate mainKeyboardAccessibilityDelegate = this.mAccessibilityDelegate;
        if (mainKeyboardAccessibilityDelegate == null || !AccessibilityUtils.getInstance().isAccessibilityEnabled()) {
            return;
        }
        mainKeyboardAccessibilityDelegate.onHideWindow();
    }

    @Override // android.view.View
    public boolean onHoverEvent(final MotionEvent event) {
        MainKeyboardAccessibilityDelegate mainKeyboardAccessibilityDelegate = this.mAccessibilityDelegate;
        if (mainKeyboardAccessibilityDelegate != null && AccessibilityUtils.getInstance().isTouchExplorationEnabled()) {
            return mainKeyboardAccessibilityDelegate.onHoverEvent(event);
        }
        return super.onHoverEvent(event);
    }

    public void updateShortcutKey(final boolean available) {
        Key key;
        Keyboard keyboard = getKeyboard();
        if (keyboard == null || (key = keyboard.getKey(-7)) == null) {
            return;
        }
        key.setEnabled(available);
        invalidateKey(key);
    }

    public void startDisplayLanguageOnSpacebar(final boolean subtypeChanged, final int languageOnSpacebarFormatType, final boolean hasMultipleEnabledIMEsOrSubtypes) {
        if (subtypeChanged) {
            KeyPreviewView.clearTextCache();
        }
        this.mLanguageOnSpacebarFormatType = languageOnSpacebarFormatType;
        this.mHasMultipleEnabledIMEsOrSubtypes = hasMultipleEnabledIMEsOrSubtypes;
        ObjectAnimator objectAnimator = this.mLanguageOnSpacebarFadeoutAnimator;
        if (objectAnimator == null) {
            this.mLanguageOnSpacebarFormatType = 0;
        } else if (subtypeChanged && languageOnSpacebarFormatType != 0) {
            setLanguageOnSpacebarAnimAlpha(255);
            if (objectAnimator.isStarted()) {
                objectAnimator.cancel();
            }
            objectAnimator.start();
        } else if (!objectAnimator.isStarted()) {
            this.mLanguageOnSpacebarAnimAlpha = this.mLanguageOnSpacebarFinalAlpha;
        }
        invalidateKey(this.mSpaceKey);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView
    protected void onDrawKeyTopVisuals(final Key key, final Canvas canvas, final Paint paint, final KeyDrawParams params) {
        if (key.altCodeWhileTyping() && key.isEnabled()) {
            params.mAnimAlpha = this.mAltCodeKeyWhileTypingAnimAlpha;
        }
        super.onDrawKeyTopVisuals(key, canvas, paint, params);
        int code = key.getCode();
        if (code != 32) {
            if (code == -10) {
                drawKeyPopupHint(key, canvas, paint, params);
            }
        } else {
            if (this.mLanguageOnSpacebarFormatType != 0) {
                drawLanguageOnSpacebar(key, canvas, paint);
            }
            if (key.isLongPressEnabled() && this.mHasMultipleEnabledIMEsOrSubtypes) {
                drawKeyPopupHint(key, canvas, paint, params);
            }
        }
    }

    private boolean fitsTextIntoWidth(final int width, final String text, final Paint paint) {
        int i = width - (this.mLanguageOnSpacebarHorizontalMargin * 2);
        paint.setTextScaleX(1.0f);
        float stringWidth = TypefaceUtils.getStringWidth(text, paint);
        if (stringWidth < width) {
            return true;
        }
        float f = i;
        float f2 = f / stringWidth;
        if (f2 < MINIMUM_XSCALE_OF_LANGUAGE_NAME) {
            return false;
        }
        paint.setTextScaleX(f2);
        return TypefaceUtils.getStringWidth(text, paint) < f;
    }

    private String layoutLanguageOnSpacebar(final Paint paint, final RichInputMethodSubtype subtype, final int width) {
        if (this.mLanguageOnSpacebarFormatType == 2) {
            String fullDisplayName = subtype.getFullDisplayName();
            if (fitsTextIntoWidth(width, fullDisplayName, paint)) {
                return fullDisplayName;
            }
        }
        String middleDisplayName = subtype.getMiddleDisplayName();
        return fitsTextIntoWidth(width, middleDisplayName, paint) ? middleDisplayName : "";
    }

    private void drawLanguageOnSpacebar(final Key key, final Canvas canvas, final Paint paint) {
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            return;
        }
        int width = key.getWidth();
        int height = key.getHeight();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setTextSize(this.mLanguageOnSpacebarTextSize);
        String strLayoutLanguageOnSpacebar = layoutLanguageOnSpacebar(paint, keyboard.mId.mSubtype, width);
        float fDescent = paint.descent();
        float f = (height / 2) + (((-paint.ascent()) + fDescent) / 2.0f);
        float f2 = this.mLanguageOnSpacebarTextShadowRadius;
        if (f2 > 0.0f) {
            paint.setShadowLayer(f2, 0.0f, 0.0f, this.mLanguageOnSpacebarTextShadowColor);
        } else {
            paint.clearShadowLayer();
        }
        paint.setColor(this.mLanguageOnSpacebarTextColor);
        paint.setAlpha(this.mLanguageOnSpacebarAnimAlpha);
        canvas.drawText(strLayoutLanguageOnSpacebar, width / 2, f - fDescent, paint);
        paint.clearShadowLayer();
        paint.setTextScaleX(1.0f);
    }

    @Override // com.android.inputmethod.keyboard.KeyboardView
    public void deallocateMemory() {
        super.deallocateMemory();
        this.mDrawingPreviewPlacerView.deallocateMemory();
    }
}
