package com.android.inputmethod.keyboard;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import com.android.inputmethod.keyboard.internal.BatchInputArbiter;
import com.android.inputmethod.keyboard.internal.BogusMoveEventDetector;
import com.android.inputmethod.keyboard.internal.DrawingProxy;
import com.android.inputmethod.keyboard.internal.GestureEnabler;
import com.android.inputmethod.keyboard.internal.GestureStrokeDrawingParams;
import com.android.inputmethod.keyboard.internal.GestureStrokeDrawingPoints;
import com.android.inputmethod.keyboard.internal.GestureStrokeRecognitionParams;
import com.android.inputmethod.keyboard.internal.PointerTrackerQueue;
import com.android.inputmethod.keyboard.internal.TimerProxy;
import com.android.inputmethod.keyboard.internal.TypingTimeRecorder;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.CoordinateUtils;
import com.android.inputmethod.latin.common.InputPointers;
import com.android.inputmethod.latin.settings.Settings;
import com.android.inputmethod.latin.utils.ResourceUtils;
import java.util.ArrayList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class PointerTracker implements PointerTrackerQueue.Element, BatchInputArbiter.BatchInputArbiterListener {
    private static final boolean DEBUG_EVENT = false;
    private static final boolean DEBUG_LISTENER = false;
    private static boolean DEBUG_MODE = false;
    private static final boolean DEBUG_MOVE_EVENT = false;
    private static final int MULTIPLIER_FOR_LONG_PRESS_TIMEOUT_IN_SLIDING_INPUT = 3;
    private static final float PHANTOM_SUDDEN_MOVE_THRESHOLD = 0.25f;
    private static final String TAG = "PointerTracker";
    public static DrawingProxy sDrawingProxy;
    private static GestureStrokeDrawingParams sGestureStrokeDrawingParams;
    private static GestureStrokeRecognitionParams sGestureStrokeRecognitionParams;
    private static boolean sNeedsPhantomSuddenMoveEventHack;
    private static PointerTrackerParams sParams;
    private static TimerProxy sTimerProxy;
    private static TypingTimeRecorder sTypingTimeRecorder;
    private final BatchInputArbiter mBatchInputArbiter;
    private long mDownTime;
    private boolean mIsAllowedDraggingFinger;
    boolean mIsInDraggingFinger;
    boolean mIsInSlidingKeyInput;
    private boolean mIsTrackingForActionDisabled;
    private int mKeyX;
    private int mKeyY;
    private Keyboard mKeyboard;
    private boolean mKeyboardLayoutHasBeenChanged;
    private int mLastX;
    private int mLastY;
    private MoreKeysPanel mMoreKeysPanel;
    private int mPhantomSuddenMoveThreshold;
    public final int mPointerId;
    private long mUpTime;
    private static GestureEnabler sGestureEnabler = new GestureEnabler();
    private static final ArrayList<PointerTracker> sTrackers = new ArrayList<>();
    private static final PointerTrackerQueue sPointerTrackerQueue = new PointerTrackerQueue();
    private static KeyboardActionListener sListener = KeyboardActionListener.EMPTY_LISTENER;
    private static boolean sInGesture = false;
    private KeyDetector mKeyDetector = new KeyDetector();
    private final BogusMoveEventDetector mBogusMoveEventDetector = new BogusMoveEventDetector();
    private boolean mIsDetectingGesture = false;

    @Nonnull
    private int[] mDownCoordinates = CoordinateUtils.newInstance();
    private Key mCurrentKey = null;
    private int mCurrentRepeatingKeyCode = -1;
    private final GestureStrokeDrawingPoints mGestureStrokeDrawingPoints = new GestureStrokeDrawingPoints(sGestureStrokeDrawingParams);

    static final class PointerTrackerParams {
        public final int mKeyRepeatInterval;
        public final int mKeyRepeatStartTimeout;
        public final boolean mKeySelectionByDraggingFinger;
        public final int mLongPressShiftLockTimeout;
        public final int mSuppressKeyPreviewAfterBatchInputDuration;
        public final int mTouchNoiseThresholdDistance;
        public final int mTouchNoiseThresholdTime;

        public PointerTrackerParams(final TypedArray mainKeyboardViewAttr) {
            this.mKeySelectionByDraggingFinger = mainKeyboardViewAttr.getBoolean(45, false);
            this.mTouchNoiseThresholdTime = mainKeyboardViewAttr.getInt(62, 0);
            this.mTouchNoiseThresholdDistance = mainKeyboardViewAttr.getDimensionPixelSize(61, 0);
            this.mSuppressKeyPreviewAfterBatchInputDuration = mainKeyboardViewAttr.getInt(60, 0);
            this.mKeyRepeatStartTimeout = mainKeyboardViewAttr.getInt(44, 0);
            this.mKeyRepeatInterval = mainKeyboardViewAttr.getInt(43, 0);
            this.mLongPressShiftLockTimeout = mainKeyboardViewAttr.getInt(52, 0);
        }
    }

    public static void init(final TypedArray mainKeyboardViewAttr, final TimerProxy timerProxy, final DrawingProxy drawingProxy) {
        sParams = new PointerTrackerParams(mainKeyboardViewAttr);
        sGestureStrokeRecognitionParams = new GestureStrokeRecognitionParams(mainKeyboardViewAttr);
        sGestureStrokeDrawingParams = new GestureStrokeDrawingParams(mainKeyboardViewAttr);
        sTypingTimeRecorder = new TypingTimeRecorder(sGestureStrokeRecognitionParams.mStaticTimeThresholdAfterFastTyping, sParams.mSuppressKeyPreviewAfterBatchInputDuration);
        Resources resources = mainKeyboardViewAttr.getResources();
        sNeedsPhantomSuddenMoveEventHack = Boolean.parseBoolean(ResourceUtils.getDeviceOverrideValue(resources, R.array.phantom_sudden_move_event_device_list, Boolean.FALSE.toString()));
        BogusMoveEventDetector.init(resources);
        sTimerProxy = timerProxy;
        sDrawingProxy = drawingProxy;
    }

    public static void setMainDictionaryAvailability(final boolean mainDictionaryAvailable) {
        sGestureEnabler.setMainDictionaryAvailability(mainDictionaryAvailable);
    }

    public static void setGestureHandlingEnabledByUser(final boolean gestureHandlingEnabledByUser) {
        sGestureEnabler.setGestureHandlingEnabledByUser(gestureHandlingEnabledByUser);
    }

    public static PointerTracker getPointerTracker(final int id) {
        ArrayList<PointerTracker> arrayList = sTrackers;
        for (int size = arrayList.size(); size <= id; size++) {
            arrayList.add(new PointerTracker(size));
        }
        return arrayList.get(id);
    }

    public static boolean isAnyInDraggingFinger() {
        return sPointerTrackerQueue.isAnyInDraggingFinger();
    }

    public static void cancelAllPointerTrackers() {
        sPointerTrackerQueue.cancelAllPointerTrackers();
    }

    public static void setKeyboardActionListener(final KeyboardActionListener listener) {
        sListener = listener;
    }

    public static void setKeyDetector(final KeyDetector keyDetector) {
        Keyboard keyboard = keyDetector.getKeyboard();
        if (keyboard == null) {
            return;
        }
        int size = sTrackers.size();
        for (int i = 0; i < size; i++) {
            sTrackers.get(i).setKeyDetectorInner(keyDetector);
        }
        sGestureEnabler.setPasswordMode(keyboard.mId.passwordInput());
    }

    public static void setReleasedKeyGraphicsToAllKeys() {
        int size = sTrackers.size();
        for (int i = 0; i < size; i++) {
            sTrackers.get(i);
        }
    }

    public static void dismissAllMoreKeysPanels() {
        int size = sTrackers.size();
        for (int i = 0; i < size; i++) {
            sTrackers.get(i).dismissMoreKeysPanel();
        }
    }

    private PointerTracker(final int id) {
        this.mPointerId = id;
        this.mBatchInputArbiter = new BatchInputArbiter(id, sGestureStrokeRecognitionParams);
    }

    private boolean callListenerOnPressAndCheckKeyboardLayoutChange(final Key key, final int repeatCount) {
        if (!sInGesture && !this.mIsDetectingGesture && !this.mIsTrackingForActionDisabled) {
            if (!(this.mIsInDraggingFinger && key.isModifier()) && key.isEnabled()) {
                sListener.onPressKey(key.getCode(), repeatCount, getActivePointerTrackerCount() == 1);
                boolean z = this.mKeyboardLayoutHasBeenChanged;
                this.mKeyboardLayoutHasBeenChanged = false;
                sTimerProxy.startTypingStateTimer(key);
                return z;
            }
        }
        return false;
    }

    private void callListenerOnCodeInput(final Key key, final int primaryCode, final int x, final int y, final long eventTime, final boolean isKeyRepeat) {
        boolean z = this.mIsInDraggingFinger && key.isModifier();
        boolean z2 = key.altCodeWhileTyping() && sTimerProxy.isTypingState();
        if (z2) {
            primaryCode = key.getAltCode();
        }
        if (z) {
            return;
        }
        if (key.isEnabled() || z2) {
            sTypingTimeRecorder.onCodeInput(primaryCode, eventTime);
            if (primaryCode == -4) {
                sListener.onTextInput(key.getOutputText());
            } else if (primaryCode != -15) {
                if (this.mKeyboard.hasProximityCharsCorrection(primaryCode)) {
                    sListener.onCodeInput(primaryCode, x, y, isKeyRepeat);
                } else {
                    sListener.onCodeInput(primaryCode, -1, -1, isKeyRepeat);
                }
            }
        }
    }

    private void callListenerOnRelease(final Key key, final int primaryCode, final boolean withSliding) {
        if (sInGesture || this.mIsDetectingGesture || this.mIsTrackingForActionDisabled) {
            return;
        }
        if (!(this.mIsInDraggingFinger && key.isModifier()) && key.isEnabled()) {
            sListener.onReleaseKey(primaryCode, withSliding);
        }
    }

    private void callListenerOnFinishSlidingInput() {
        sListener.onFinishSlidingInput();
    }

    private void callListenerOnCancelInput() {
        sListener.onCancelInput();
    }

    private void setKeyDetectorInner(final KeyDetector keyDetector) {
        Keyboard keyboard = keyDetector.getKeyboard();
        if (keyboard == null) {
            return;
        }
        if (keyDetector == this.mKeyDetector && keyboard == this.mKeyboard) {
            return;
        }
        this.mKeyDetector = keyDetector;
        this.mKeyboard = keyboard;
        this.mKeyboardLayoutHasBeenChanged = true;
        int i = keyboard.mMostCommonKeyWidth;
        int i2 = this.mKeyboard.mMostCommonKeyHeight;
        this.mBatchInputArbiter.setKeyboardGeometry(i, this.mKeyboard.mOccupiedHeight);
        this.mPhantomSuddenMoveThreshold = (int) (i * 0.25f);
        this.mBogusMoveEventDetector.setKeyboardGeometry(i, i2);
    }

    @Override // com.android.inputmethod.keyboard.internal.PointerTrackerQueue.Element
    public boolean isInDraggingFinger() {
        return this.mIsInDraggingFinger;
    }

    @Nullable
    public Key getKey() {
        return this.mCurrentKey;
    }

    @Override // com.android.inputmethod.keyboard.internal.PointerTrackerQueue.Element
    public boolean isModifier() {
        Key key = this.mCurrentKey;
        return key != null && key.isModifier();
    }

    public Key getKeyOn(final int x, final int y) {
        return this.mKeyDetector.detectHitKey(x, y);
    }

    private void setReleasedKeyGraphics(@Nullable final Key key, final boolean withAnimation) {
        if (key == null) {
            return;
        }
        sDrawingProxy.onKeyReleased(key, withAnimation);
        if (key.isShift()) {
            for (Key key2 : this.mKeyboard.mShiftKeys) {
                if (key2 != key) {
                    sDrawingProxy.onKeyReleased(key2, false);
                }
            }
        }
        if (key.altCodeWhileTyping()) {
            int altCode = key.getAltCode();
            Key key3 = this.mKeyboard.getKey(altCode);
            if (key3 != null) {
                sDrawingProxy.onKeyReleased(key3, false);
            }
            for (Key key4 : this.mKeyboard.mAltCodeKeysWhileTyping) {
                if (key4 != key && key4.getAltCode() == altCode) {
                    sDrawingProxy.onKeyReleased(key4, false);
                }
            }
        }
    }

    private void setReleasedKeyGraphicsForLongPress(@Nullable final Key key, final boolean withAnimation) {
        if (key == null) {
            return;
        }
        if (key.isShift()) {
            for (Key key2 : this.mKeyboard.mShiftKeys) {
                if (key2 != key) {
                    sDrawingProxy.onKeyReleased(key2, false);
                }
            }
        }
        if (key.altCodeWhileTyping()) {
            int altCode = key.getAltCode();
            Key key3 = this.mKeyboard.getKey(altCode);
            if (key3 != null) {
                sDrawingProxy.onKeyReleased(key3, false);
            }
            for (Key key4 : this.mKeyboard.mAltCodeKeysWhileTyping) {
                if (key4 != key && key4.getAltCode() == altCode) {
                    sDrawingProxy.onKeyReleased(key4, false);
                }
            }
        }
    }

    private static boolean needsToSuppressKeyPreviewPopup(final long eventTime) {
        if (sGestureEnabler.shouldHandleGesture()) {
            return sTypingTimeRecorder.needsToSuppressKeyPreviewPopup(eventTime);
        }
        return false;
    }

    private void setPressedKeyGraphics(@Nullable final Key key, final long eventTime) {
        if (key == null) {
            return;
        }
        boolean z = key.altCodeWhileTyping() && sTimerProxy.isTypingState();
        if (key.isEnabled() || z) {
            sDrawingProxy.onKeyPressed(key, !(sInGesture || needsToSuppressKeyPreviewPopup(eventTime)));
            if (key.isShift()) {
                for (Key key2 : this.mKeyboard.mShiftKeys) {
                    if (key2 != key) {
                        sDrawingProxy.onKeyPressed(key2, false);
                    }
                }
            }
            if (z) {
                int altCode = key.getAltCode();
                Key key3 = this.mKeyboard.getKey(altCode);
                if (key3 != null) {
                    sDrawingProxy.onKeyPressed(key3, false);
                }
                for (Key key4 : this.mKeyboard.mAltCodeKeysWhileTyping) {
                    if (key4 != key && key4.getAltCode() == altCode) {
                        sDrawingProxy.onKeyPressed(key4, false);
                    }
                }
            }
        }
    }

    public GestureStrokeDrawingPoints getGestureStrokeDrawingPoints() {
        return this.mGestureStrokeDrawingPoints;
    }

    public void getLastCoordinates(@Nonnull final int[] outCoords) {
        CoordinateUtils.set(outCoords, this.mLastX, this.mLastY);
    }

    public long getDownTime() {
        return this.mDownTime;
    }

    public void getDownCoordinates(@Nonnull final int[] outCoords) {
        CoordinateUtils.copy(outCoords, this.mDownCoordinates);
    }

    private Key onDownKey(final int x, final int y, final long eventTime) {
        this.mDownTime = eventTime;
        CoordinateUtils.set(this.mDownCoordinates, x, y);
        this.mBogusMoveEventDetector.onDownKey();
        return onMoveToNewKey(onMoveKeyInternal(x, y), x, y);
    }

    private static int getDistance(final int x1, final int y1, final int x2, final int y2) {
        return (int) Math.hypot(x1 - x2, y1 - y2);
    }

    private Key onMoveKeyInternal(final int x, final int y) {
        this.mBogusMoveEventDetector.onMoveKey(getDistance(x, y, this.mLastX, this.mLastY));
        this.mLastX = x;
        this.mLastY = y;
        return this.mKeyDetector.detectHitKey(x, y);
    }

    private Key onMoveKey(final int x, final int y) {
        return onMoveKeyInternal(x, y);
    }

    private Key onMoveToNewKey(final Key newKey, final int x, final int y) {
        this.mCurrentKey = newKey;
        this.mKeyX = x;
        this.mKeyY = y;
        return newKey;
    }

    static int getActivePointerTrackerCount() {
        return sPointerTrackerQueue.size();
    }

    private boolean isOldestTrackerInQueue() {
        return sPointerTrackerQueue.getOldestElement() == this;
    }

    @Override // com.android.inputmethod.keyboard.internal.BatchInputArbiter.BatchInputArbiterListener
    public void onStartBatchInput() {
        sListener.onStartBatchInput();
        dismissAllMoreKeysPanels();
        sTimerProxy.cancelLongPressTimersOf(this);
    }

    private void showGestureTrail() {
        if (this.mIsTrackingForActionDisabled) {
            return;
        }
        sDrawingProxy.showGestureTrail(this, isOldestTrackerInQueue());
    }

    public void updateBatchInputByTimer(final long syntheticMoveEventTime) {
        this.mBatchInputArbiter.updateBatchInputByTimer(syntheticMoveEventTime, this);
    }

    @Override // com.android.inputmethod.keyboard.internal.BatchInputArbiter.BatchInputArbiterListener
    public void onUpdateBatchInput(final InputPointers aggregatedPointers, final long eventTime) {
        sListener.onUpdateBatchInput(aggregatedPointers);
    }

    @Override // com.android.inputmethod.keyboard.internal.BatchInputArbiter.BatchInputArbiterListener
    public void onStartUpdateBatchInputTimer() {
        sTimerProxy.startUpdateBatchInputTimer(this);
    }

    @Override // com.android.inputmethod.keyboard.internal.BatchInputArbiter.BatchInputArbiterListener
    public void onEndBatchInput(final InputPointers aggregatedPointers, final long eventTime) {
        sTypingTimeRecorder.onEndBatchInput(eventTime);
        sTimerProxy.cancelAllUpdateBatchInputTimers();
        if (this.mIsTrackingForActionDisabled) {
            return;
        }
        sListener.onEndBatchInput(aggregatedPointers);
    }

    private void cancelBatchInput() {
        cancelAllPointerTrackers();
        this.mIsDetectingGesture = false;
        if (sInGesture) {
            sInGesture = false;
            sListener.onCancelBatchInput();
        }
    }

    public void processMotionEvent(final MotionEvent me, final KeyDetector keyDetector) {
        int actionMasked = me.getActionMasked();
        long eventTime = me.getEventTime();
        if (actionMasked == 2) {
            boolean z = isShowingMoreKeysPanel() && getActivePointerTrackerCount() == 1;
            int pointerCount = me.getPointerCount();
            for (int i = 0; i < pointerCount; i++) {
                int pointerId = me.getPointerId(i);
                if (!z || pointerId == this.mPointerId) {
                    getPointerTracker(pointerId).onMoveEvent((int) me.getX(i), (int) me.getY(i), eventTime, me);
                }
            }
            return;
        }
        int actionIndex = me.getActionIndex();
        int x = (int) me.getX(actionIndex);
        int y = (int) me.getY(actionIndex);
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked == 3) {
                    onCancelEvent(x, y, eventTime);
                    return;
                } else if (actionMasked != 5) {
                    if (actionMasked != 6) {
                        return;
                    }
                }
            }
            onUpEvent(x, y, eventTime);
            return;
        }
        onDownEvent(x, y, eventTime, keyDetector);
    }

    private void onDownEvent(final int x, final int y, final long eventTime, final KeyDetector keyDetector) {
        int distance;
        setKeyDetectorInner(keyDetector);
        long j = eventTime - this.mUpTime;
        if (j < sParams.mTouchNoiseThresholdTime && (distance = getDistance(x, y, this.mLastX, this.mLastY)) < sParams.mTouchNoiseThresholdDistance) {
            if (DEBUG_MODE) {
                Log.w(TAG, String.format("[%d] onDownEvent: ignore potential noise: time=%d distance=%d", Integer.valueOf(this.mPointerId), Long.valueOf(j), Integer.valueOf(distance)));
            }
            cancelTrackingForAction();
            return;
        }
        Key keyOn = getKeyOn(x, y);
        this.mBogusMoveEventDetector.onActualDownEvent(x, y);
        if (keyOn != null && keyOn.isModifier()) {
            sPointerTrackerQueue.releaseAllPointers(eventTime);
        }
        sPointerTrackerQueue.add(this);
        onDownEventInternal(x, y, eventTime);
        if (sGestureEnabler.shouldHandleGesture()) {
            Keyboard keyboard = this.mKeyboard;
            boolean z = (keyboard == null || !keyboard.mId.isAlphabetKeyboard() || keyOn == null || keyOn.isModifier()) ? false : true;
            this.mIsDetectingGesture = z;
            if (z) {
                this.mBatchInputArbiter.addDownEventPoint(x, y, eventTime, sTypingTimeRecorder.getLastLetterTypingTime(), getActivePointerTrackerCount());
                this.mGestureStrokeDrawingPoints.onDownEvent(x, y, this.mBatchInputArbiter.getElapsedTimeSinceFirstDown(eventTime));
            }
        }
    }

    boolean isShowingMoreKeysPanel() {
        return this.mMoreKeysPanel != null;
    }

    private void dismissMoreKeysPanel() {
        if (isShowingMoreKeysPanel()) {
            this.mMoreKeysPanel.dismissMoreKeysPanel();
            this.mMoreKeysPanel = null;
        }
    }

    private void onDownEventInternal(final int x, final int y, final long eventTime) {
        Key keyOnDownKey = onDownKey(x, y, eventTime);
        this.mIsAllowedDraggingFinger = sParams.mKeySelectionByDraggingFinger || (keyOnDownKey != null && keyOnDownKey.isModifier()) || this.mKeyDetector.alwaysAllowsKeySelectionByDraggingFinger();
        this.mKeyboardLayoutHasBeenChanged = false;
        this.mIsTrackingForActionDisabled = false;
        resetKeySelectionByDraggingFinger();
        if (keyOnDownKey != null) {
            if (callListenerOnPressAndCheckKeyboardLayoutChange(keyOnDownKey, 0)) {
                keyOnDownKey = onDownKey(x, y, eventTime);
            }
            startRepeatKey(keyOnDownKey);
            startLongPressTimer(keyOnDownKey);
            setPressedKeyGraphics(keyOnDownKey, eventTime);
        }
    }

    private void startKeySelectionByDraggingFinger(final Key key) {
        if (!this.mIsInDraggingFinger) {
            this.mIsInSlidingKeyInput = key.isModifier();
        }
        this.mIsInDraggingFinger = true;
    }

    private void resetKeySelectionByDraggingFinger() {
        this.mIsInDraggingFinger = false;
        this.mIsInSlidingKeyInput = false;
        sDrawingProxy.showSlidingKeyInputPreview(null);
    }

    private void onGestureMoveEvent(final int x, final int y, final long eventTime, final boolean isMajorEvent, final Key key) {
        if (this.mIsDetectingGesture) {
            if (!this.mBatchInputArbiter.addMoveEventPoint(x, y, eventTime, isMajorEvent, this)) {
                cancelBatchInput();
                return;
            }
            this.mGestureStrokeDrawingPoints.onMoveEvent(x, y, this.mBatchInputArbiter.getElapsedTimeSinceFirstDown(eventTime));
            if (isShowingMoreKeysPanel()) {
                return;
            }
            if (!sInGesture && key != null && Character.isLetter(key.getCode()) && this.mBatchInputArbiter.mayStartBatchInput(this)) {
                sInGesture = true;
            }
            if (sInGesture) {
                if (key != null) {
                    this.mBatchInputArbiter.updateBatchInput(eventTime, this);
                }
                showGestureTrail();
            }
        }
    }

    private void onMoveEvent(final int x, final int y, final long eventTime, final MotionEvent me) {
        if (this.mIsTrackingForActionDisabled) {
            return;
        }
        if (sGestureEnabler.shouldHandleGesture() && me != null) {
            int iFindPointerIndex = me.findPointerIndex(this.mPointerId);
            int historySize = me.getHistorySize();
            for (int i = 0; i < historySize; i++) {
                onGestureMoveEvent((int) me.getHistoricalX(iFindPointerIndex, i), (int) me.getHistoricalY(iFindPointerIndex, i), me.getHistoricalEventTime(i), false, null);
            }
        }
        if (isShowingMoreKeysPanel()) {
            this.mMoreKeysPanel.onMoveEvent(this.mMoreKeysPanel.translateX(x), this.mMoreKeysPanel.translateY(y), this.mPointerId, eventTime);
            onMoveKey(x, y);
            if (this.mIsInSlidingKeyInput) {
                sDrawingProxy.showSlidingKeyInputPreview(this);
                return;
            }
            return;
        }
        onMoveEventInternal(x, y, eventTime);
    }

    private void processDraggingFingerInToNewKey(final Key newKey, final int x, final int y, final long eventTime) {
        if (callListenerOnPressAndCheckKeyboardLayoutChange(newKey, 0)) {
            newKey = onMoveKey(x, y);
        }
        onMoveToNewKey(newKey, x, y);
        if (this.mIsTrackingForActionDisabled) {
            return;
        }
        startLongPressTimer(newKey);
        setPressedKeyGraphics(newKey, eventTime);
    }

    private void processPhantomSuddenMoveHack(final Key key, final int x, final int y, final long eventTime, final Key oldKey, final int lastX, final int lastY) {
        if (DEBUG_MODE) {
            Log.w(TAG, String.format("[%d] onMoveEvent: phantom sudden move event (distance=%d) is translated to up[%d,%d,%s]/down[%d,%d,%s] events", Integer.valueOf(this.mPointerId), Integer.valueOf(getDistance(x, y, lastX, lastY)), Integer.valueOf(lastX), Integer.valueOf(lastY), Constants.printableCode(oldKey.getCode()), Integer.valueOf(x), Integer.valueOf(y), Constants.printableCode(key.getCode())));
        }
        onUpEventInternal(x, y, eventTime);
        onDownEventInternal(x, y, eventTime);
    }

    private void processProximateBogusDownMoveUpEventHack(final Key key, final int x, final int y, final long eventTime, final Key oldKey, final int lastX, final int lastY) {
        if (DEBUG_MODE) {
            Log.w(TAG, String.format("[%d] onMoveEvent: bogus down-move-up event (raidus=%.2f key diagonal) is  translated to up[%d,%d,%s]/down[%d,%d,%s] events", Integer.valueOf(this.mPointerId), Float.valueOf(this.mBogusMoveEventDetector.getDistanceFromDownEvent(x, y) / ((float) Math.hypot(this.mKeyboard.mMostCommonKeyWidth, this.mKeyboard.mMostCommonKeyHeight))), Integer.valueOf(lastX), Integer.valueOf(lastY), Constants.printableCode(oldKey.getCode()), Integer.valueOf(x), Integer.valueOf(y), Constants.printableCode(key.getCode())));
        }
        onUpEventInternal(x, y, eventTime);
        onDownEventInternal(x, y, eventTime);
    }

    private void processDraggingFingerOutFromOldKey(final Key oldKey) {
        setReleasedKeyGraphics(oldKey, true);
        callListenerOnRelease(oldKey, oldKey.getCode(), true);
        startKeySelectionByDraggingFinger(oldKey);
        sTimerProxy.cancelKeyTimersOf(this);
    }

    private void dragFingerFromOldKeyToNewKey(final Key key, final int x, final int y, final long eventTime, final Key oldKey, final int lastX, final int lastY) {
        processDraggingFingerOutFromOldKey(oldKey);
        startRepeatKey(key);
        if (this.mIsAllowedDraggingFinger) {
            processDraggingFingerInToNewKey(key, x, y, eventTime);
            return;
        }
        if (sNeedsPhantomSuddenMoveEventHack && getDistance(x, y, lastX, lastY) >= this.mPhantomSuddenMoveThreshold) {
            processPhantomSuddenMoveHack(key, x, y, eventTime, oldKey, lastX, lastY);
            return;
        }
        if (sTypingTimeRecorder.isInFastTyping(eventTime) && this.mBogusMoveEventDetector.isCloseToActualDownEvent(x, y)) {
            processProximateBogusDownMoveUpEventHack(key, x, y, eventTime, oldKey, lastX, lastY);
            return;
        }
        if (getActivePointerTrackerCount() > 1 && !sPointerTrackerQueue.hasModifierKeyOlderThan(this)) {
            if (DEBUG_MODE) {
                Log.w(TAG, String.format("[%d] onMoveEvent: detected sliding finger while multi touching", Integer.valueOf(this.mPointerId)));
            }
            onUpEvent(x, y, eventTime);
            cancelTrackingForAction();
            setReleasedKeyGraphics(oldKey, true);
            return;
        }
        if (!this.mIsDetectingGesture) {
            cancelTrackingForAction();
        }
        setReleasedKeyGraphics(oldKey, true);
    }

    private void dragFingerOutFromOldKey(final Key oldKey, final int x, final int y) {
        processDraggingFingerOutFromOldKey(oldKey);
        if (this.mIsAllowedDraggingFinger) {
            onMoveToNewKey(null, x, y);
        } else {
            if (this.mIsDetectingGesture) {
                return;
            }
            cancelTrackingForAction();
        }
    }

    private void onMoveEventInternal(final int x, final int y, final long eventTime) {
        int i = this.mLastX;
        int i2 = this.mLastY;
        Key key = this.mCurrentKey;
        Key keyOnMoveKey = onMoveKey(x, y);
        if (sGestureEnabler.shouldHandleGesture()) {
            onGestureMoveEvent(x, y, eventTime, true, keyOnMoveKey);
            if (sInGesture) {
                this.mCurrentKey = null;
                setReleasedKeyGraphics(key, true);
                return;
            }
        }
        if (keyOnMoveKey != null) {
            if (key != null && isMajorEnoughMoveToBeOnNewKey(x, y, eventTime, keyOnMoveKey)) {
                dragFingerFromOldKeyToNewKey(keyOnMoveKey, x, y, eventTime, key, i, i2);
            } else if (key == null) {
                processDraggingFingerInToNewKey(keyOnMoveKey, x, y, eventTime);
            }
        } else if (key != null && isMajorEnoughMoveToBeOnNewKey(x, y, eventTime, keyOnMoveKey)) {
            dragFingerOutFromOldKey(key, x, y);
        }
        if (this.mIsInSlidingKeyInput) {
            sDrawingProxy.showSlidingKeyInputPreview(this);
        }
    }

    private void onUpEvent(final int x, final int y, final long eventTime) {
        sTimerProxy.cancelUpdateBatchInputTimer(this);
        if (!sInGesture) {
            Key key = this.mCurrentKey;
            if (key != null && key.isModifier()) {
                sPointerTrackerQueue.releaseAllPointersExcept(this, eventTime);
            } else {
                sPointerTrackerQueue.releaseAllPointersOlderThan(this, eventTime);
            }
        }
        onUpEventInternal(x, y, eventTime);
        sPointerTrackerQueue.remove(this);
    }

    @Override // com.android.inputmethod.keyboard.internal.PointerTrackerQueue.Element
    public void onPhantomUpEvent(final long eventTime) {
        onUpEventInternal(this.mLastX, this.mLastY, eventTime);
        cancelTrackingForAction();
    }

    private void onUpEventInternal(final int x, final int y, final long eventTime) {
        sTimerProxy.cancelKeyTimersOf(this);
        boolean z = this.mIsInDraggingFinger;
        boolean z2 = this.mIsInSlidingKeyInput;
        resetKeySelectionByDraggingFinger();
        this.mIsDetectingGesture = false;
        Key key = this.mCurrentKey;
        this.mCurrentKey = null;
        int i = this.mCurrentRepeatingKeyCode;
        this.mCurrentRepeatingKeyCode = -1;
        setReleasedKeyGraphics(key, true);
        if (isShowingMoreKeysPanel()) {
            if (!this.mIsTrackingForActionDisabled) {
                this.mMoreKeysPanel.onUpEvent(this.mMoreKeysPanel.translateX(x), this.mMoreKeysPanel.translateY(y), this.mPointerId, eventTime);
            }
            dismissMoreKeysPanel();
            return;
        }
        if (sInGesture) {
            if (key != null) {
                callListenerOnRelease(key, key.getCode(), true);
            }
            if (this.mBatchInputArbiter.mayEndBatchInput(eventTime, getActivePointerTrackerCount(), this)) {
                sInGesture = false;
            }
            showGestureTrail();
            return;
        }
        if (this.mIsTrackingForActionDisabled) {
            return;
        }
        if (key == null || !key.isRepeatable() || key.getCode() != i || z) {
            detectAndSendKey(key, this.mKeyX, this.mKeyY, eventTime);
            if (z2) {
                callListenerOnFinishSlidingInput();
            }
        }
    }

    @Override // com.android.inputmethod.keyboard.internal.PointerTrackerQueue.Element
    public void cancelTrackingForAction() {
        if (isShowingMoreKeysPanel()) {
            return;
        }
        this.mIsTrackingForActionDisabled = true;
    }

    public boolean isInOperation() {
        return !this.mIsTrackingForActionDisabled;
    }

    public void onLongPressed() {
        Key key;
        sTimerProxy.cancelLongPressTimersOf(this);
        if (isShowingMoreKeysPanel() || (key = getKey()) == null) {
            return;
        }
        if (key.hasNoPanelAutoMoreKey()) {
            cancelKeyTracking();
            int i = key.getMoreKeys()[0].mCode;
            sListener.onPressKey(i, 0, true);
            sListener.onCodeInput(i, -1, -1, false);
            sListener.onReleaseKey(i, false);
            return;
        }
        int code = key.getCode();
        if (code == -10 && sListener.onCustomRequest(1)) {
            cancelKeyTracking();
            sListener.onReleaseKey(code, false);
            return;
        }
        setReleasedKeyGraphicsForLongPress(key, false);
        MoreKeysPanel moreKeysPanelShowMoreKeysKeyboard = sDrawingProxy.showMoreKeysKeyboard(key, this);
        if (moreKeysPanelShowMoreKeysKeyboard == null) {
            return;
        }
        moreKeysPanelShowMoreKeysKeyboard.onDownEvent(key, moreKeysPanelShowMoreKeysKeyboard.translateX(this.mLastX), moreKeysPanelShowMoreKeysKeyboard.translateY(this.mLastY), this.mPointerId, SystemClock.uptimeMillis());
        this.mMoreKeysPanel = moreKeysPanelShowMoreKeysKeyboard;
    }

    private void cancelKeyTracking() {
        resetKeySelectionByDraggingFinger();
        cancelTrackingForAction();
        setReleasedKeyGraphics(this.mCurrentKey, true);
        sPointerTrackerQueue.remove(this);
    }

    private void onCancelEvent(final int x, final int y, final long eventTime) {
        cancelBatchInput();
        cancelAllPointerTrackers();
        sPointerTrackerQueue.releaseAllPointers(eventTime);
        onCancelEventInternal();
    }

    private void onCancelEventInternal() {
        sTimerProxy.cancelKeyTimersOf(this);
        setReleasedKeyGraphics(this.mCurrentKey, true);
        resetKeySelectionByDraggingFinger();
        dismissMoreKeysPanel();
    }

    private boolean isMajorEnoughMoveToBeOnNewKey(final int x, final int y, final long eventTime, final Key newKey) {
        Key key = this.mCurrentKey;
        if (newKey == key) {
            return false;
        }
        if (key == null) {
            return true;
        }
        int keyHysteresisDistanceSquared = this.mKeyDetector.getKeyHysteresisDistanceSquared(this.mIsInSlidingKeyInput);
        int iSquaredDistanceToEdge = key.squaredDistanceToEdge(x, y);
        if (iSquaredDistanceToEdge >= keyHysteresisDistanceSquared) {
            if (DEBUG_MODE) {
                Log.d(TAG, String.format("[%d] isMajorEnoughMoveToBeOnNewKey: %.2f key width from key edge", Integer.valueOf(this.mPointerId), Float.valueOf(((float) Math.sqrt(iSquaredDistanceToEdge)) / this.mKeyboard.mMostCommonKeyWidth)));
            }
            return true;
        }
        if (this.mIsAllowedDraggingFinger || !sTypingTimeRecorder.isInFastTyping(eventTime) || !this.mBogusMoveEventDetector.hasTraveledLongDistance(x, y)) {
            return false;
        }
        if (DEBUG_MODE) {
            Log.d(TAG, String.format("[%d] isMajorEnoughMoveToBeOnNewKey: %.2f key diagonal from virtual down point", Integer.valueOf(this.mPointerId), Float.valueOf(this.mBogusMoveEventDetector.getAccumulatedDistanceFromDownKey() / ((float) Math.hypot(this.mKeyboard.mMostCommonKeyWidth, this.mKeyboard.mMostCommonKeyHeight)))));
        }
        return true;
    }

    private void startLongPressTimer(final Key key) {
        int longPressTimeout;
        sTimerProxy.cancelLongPressShiftKeyTimer();
        if (sInGesture || key == null || !key.isLongPressEnabled()) {
            return;
        }
        if (!(this.mIsInDraggingFinger && key.getMoreKeys() == null) && (longPressTimeout = getLongPressTimeout(key.getCode())) > 0) {
            sTimerProxy.startLongPressTimerOf(this, longPressTimeout);
        }
    }

    private int getLongPressTimeout(final int code) {
        if (code == -1) {
            return sParams.mLongPressShiftLockTimeout;
        }
        int i = Settings.getInstance().getCurrent().mKeyLongpressTimeout;
        return this.mIsInSlidingKeyInput ? i * 3 : i;
    }

    private void detectAndSendKey(final Key key, final int x, final int y, final long eventTime) {
        if (key == null) {
            callListenerOnCancelInput();
            return;
        }
        int code = key.getCode();
        callListenerOnCodeInput(key, code, x, y, eventTime, false);
        callListenerOnRelease(key, code, false);
    }

    private void startRepeatKey(final Key key) {
        if (sInGesture || key == null || !key.isRepeatable() || this.mIsInDraggingFinger) {
            return;
        }
        startKeyRepeatTimer(1);
    }

    public void onKeyRepeat(final int code, final int repeatCount) {
        Key key = getKey();
        if (key == null || key.getCode() != code) {
            this.mCurrentRepeatingKeyCode = -1;
            return;
        }
        this.mCurrentRepeatingKeyCode = code;
        this.mIsDetectingGesture = false;
        startKeyRepeatTimer(repeatCount + 1);
        callListenerOnPressAndCheckKeyboardLayoutChange(key, repeatCount);
        callListenerOnCodeInput(key, code, this.mKeyX, this.mKeyY, SystemClock.uptimeMillis(), true);
    }

    private void startKeyRepeatTimer(final int repeatCount) {
        sTimerProxy.startKeyRepeatTimerOf(this, repeatCount, repeatCount == 1 ? sParams.mKeyRepeatStartTimeout : sParams.mKeyRepeatInterval);
    }

    private void printTouchEvent(final String title, final int x, final int y, final long eventTime) {
        Key keyDetectHitKey = this.mKeyDetector.detectHitKey(x, y);
        String strPrintableCode = keyDetectHitKey == null ? "none" : Constants.printableCode(keyDetectHitKey.getCode());
        String str = TAG;
        Object[] objArr = new Object[7];
        objArr[0] = Integer.valueOf(this.mPointerId);
        objArr[1] = this.mIsTrackingForActionDisabled ? "-" : " ";
        objArr[2] = title;
        objArr[3] = Integer.valueOf(x);
        objArr[4] = Integer.valueOf(y);
        objArr[5] = Long.valueOf(eventTime);
        objArr[6] = strPrintableCode;
        Log.d(str, String.format("[%d]%s%s %4d %4d %5d %s", objArr));
    }
}
