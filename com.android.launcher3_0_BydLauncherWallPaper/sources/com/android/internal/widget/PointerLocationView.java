package com.android.internal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;
import android.view.ISystemGestureExclusionListener;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowInsets;
import android.view.WindowManagerGlobal;
import android.view.WindowManagerPolicyConstants;
import com.android.internal.content.NativeLibraryHelper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class PointerLocationView extends View implements InputManager.InputDeviceListener, WindowManagerPolicyConstants.PointerEventListener {
    private static final String ALT_STRATEGY_PROPERY_KEY = "debug.velocitytracker.alt";
    private static final String GESTURE_EXCLUSION_PROP = "debug.pointerlocation.showexclusion";
    private static final String TAG = "Pointer";
    private int mActivePointerId;
    private final VelocityTracker mAltVelocity;
    private boolean mCurDown;
    private int mCurNumPointers;
    private final Paint mCurrentPointPaint;
    private int mHeaderBottom;
    private int mHeaderPaddingTop;
    private final InputManager mIm;
    private int mMaxNumPointers;
    private final Paint mPaint;
    private final Paint mPathPaint;
    private final ArrayList<PointerState> mPointers;
    private boolean mPrintCoords;
    private RectF mReusableOvalRect;
    private final Region mSystemGestureExclusion;
    private ISystemGestureExclusionListener mSystemGestureExclusionListener;
    private final Paint mSystemGestureExclusionPaint;
    private final Path mSystemGestureExclusionPath;
    private final Region mSystemGestureExclusionRejected;
    private final Paint mSystemGestureExclusionRejectedPaint;
    private final Paint mTargetPaint;
    private final MotionEvent.PointerCoords mTempCoords;
    private final FasterStringBuilder mText;
    private final Paint mTextBackgroundPaint;
    private final Paint mTextLevelPaint;
    private final Paint.FontMetricsInt mTextMetrics;
    private final Paint mTextPaint;
    private final ViewConfiguration mVC;
    private final VelocityTracker mVelocity;

    public static class PointerState {
        private float mAltXVelocity;
        private float mAltYVelocity;
        private float mBoundingBottom;
        private float mBoundingLeft;
        private float mBoundingRight;
        private float mBoundingTop;
        private boolean mCurDown;
        private boolean mHasBoundingBox;
        private int mToolType;
        private int mTraceCount;
        private float mXVelocity;
        private float mYVelocity;
        private float[] mTraceX = new float[32];
        private float[] mTraceY = new float[32];
        private boolean[] mTraceCurrent = new boolean[32];
        private MotionEvent.PointerCoords mCoords = new MotionEvent.PointerCoords();
        private VelocityTracker.Estimator mEstimator = new VelocityTracker.Estimator();
        private VelocityTracker.Estimator mAltEstimator = new VelocityTracker.Estimator();

        public void clearTrace() {
            this.mTraceCount = 0;
        }

        public void addTrace(float f, float f2, boolean z) {
            float[] fArr = this.mTraceX;
            int length = fArr.length;
            int i = this.mTraceCount;
            if (i == length) {
                int i2 = length * 2;
                float[] fArr2 = new float[i2];
                System.arraycopy(fArr, 0, fArr2, 0, i);
                this.mTraceX = fArr2;
                float[] fArr3 = new float[i2];
                System.arraycopy(this.mTraceY, 0, fArr3, 0, this.mTraceCount);
                this.mTraceY = fArr3;
                boolean[] zArr = new boolean[i2];
                System.arraycopy(this.mTraceCurrent, 0, zArr, 0, this.mTraceCount);
                this.mTraceCurrent = zArr;
            }
            float[] fArr4 = this.mTraceX;
            int i3 = this.mTraceCount;
            fArr4[i3] = f;
            this.mTraceY[i3] = f2;
            this.mTraceCurrent[i3] = z;
            this.mTraceCount = i3 + 1;
        }
    }

    public PointerLocationView(Context context) {
        super(context);
        this.mTextMetrics = new Paint.FontMetricsInt();
        this.mHeaderPaddingTop = 0;
        ArrayList<PointerState> arrayList = new ArrayList<>();
        this.mPointers = arrayList;
        this.mTempCoords = new MotionEvent.PointerCoords();
        this.mSystemGestureExclusion = new Region();
        this.mSystemGestureExclusionRejected = new Region();
        this.mSystemGestureExclusionPath = new Path();
        this.mText = new FasterStringBuilder();
        this.mPrintCoords = true;
        this.mReusableOvalRect = new RectF();
        this.mSystemGestureExclusionListener = new AnonymousClass1();
        setFocusableInTouchMode(true);
        this.mIm = (InputManager) context.getSystemService(InputManager.class);
        this.mVC = ViewConfiguration.get(context);
        Paint paint = new Paint();
        this.mTextPaint = paint;
        paint.setAntiAlias(true);
        paint.setTextSize(getResources().getDisplayMetrics().density * 10.0f);
        paint.setARGB(255, 0, 0, 0);
        Paint paint2 = new Paint();
        this.mTextBackgroundPaint = paint2;
        paint2.setAntiAlias(false);
        paint2.setARGB(128, 255, 255, 255);
        Paint paint3 = new Paint();
        this.mTextLevelPaint = paint3;
        paint3.setAntiAlias(false);
        paint3.setARGB(192, 255, 0, 0);
        Paint paint4 = new Paint();
        this.mPaint = paint4;
        paint4.setAntiAlias(true);
        paint4.setARGB(255, 255, 255, 255);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(2.0f);
        Paint paint5 = new Paint();
        this.mCurrentPointPaint = paint5;
        paint5.setAntiAlias(true);
        paint5.setARGB(255, 255, 0, 0);
        paint5.setStyle(Paint.Style.STROKE);
        paint5.setStrokeWidth(2.0f);
        Paint paint6 = new Paint();
        this.mTargetPaint = paint6;
        paint6.setAntiAlias(false);
        paint6.setARGB(255, 0, 0, 192);
        Paint paint7 = new Paint();
        this.mPathPaint = paint7;
        paint7.setAntiAlias(false);
        paint7.setARGB(255, 0, 96, 255);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(1.0f);
        Paint paint8 = new Paint();
        this.mSystemGestureExclusionPaint = paint8;
        paint8.setARGB(25, 255, 0, 0);
        paint8.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint9 = new Paint();
        this.mSystemGestureExclusionRejectedPaint = paint9;
        paint9.setARGB(25, 0, 0, 255);
        paint9.setStyle(Paint.Style.FILL_AND_STROKE);
        arrayList.add(new PointerState());
        this.mActivePointerId = 0;
        this.mVelocity = VelocityTracker.obtain();
        String str = SystemProperties.get(ALT_STRATEGY_PROPERY_KEY);
        if (str.length() != 0) {
            Log.d(TAG, "Comparing default velocity tracker strategy with " + str);
            this.mAltVelocity = VelocityTracker.obtain(str);
        } else {
            this.mAltVelocity = null;
        }
    }

    public void setPrintCoords(boolean z) {
        this.mPrintCoords = z;
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        if (windowInsets.getDisplayCutout() != null) {
            this.mHeaderPaddingTop = windowInsets.getDisplayCutout().getSafeInsetTop();
        } else {
            this.mHeaderPaddingTop = 0;
        }
        return super.onApplyWindowInsets(windowInsets);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mTextPaint.getFontMetricsInt(this.mTextMetrics);
        this.mHeaderBottom = (this.mHeaderPaddingTop - this.mTextMetrics.ascent) + this.mTextMetrics.descent + 2;
    }

    private void drawOval(Canvas canvas, float f, float f2, float f3, float f4, float f5, Paint paint) {
        canvas.save(1);
        canvas.rotate((float) (((double) (f5 * 180.0f)) / 3.141592653589793d), f, f2);
        float f6 = f4 / 2.0f;
        this.mReusableOvalRect.left = f - f6;
        this.mReusableOvalRect.right = f + f6;
        float f7 = f3 / 2.0f;
        this.mReusableOvalRect.top = f2 - f7;
        this.mReusableOvalRect.bottom = f2 + f7;
        canvas.drawOval(this.mReusableOvalRect, paint);
        canvas.restore();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        float f;
        int width = getWidth();
        int i2 = width / 7;
        int i3 = (this.mHeaderPaddingTop - this.mTextMetrics.ascent) + 1;
        int i4 = this.mHeaderBottom;
        int size = this.mPointers.size();
        if (!this.mSystemGestureExclusion.isEmpty()) {
            this.mSystemGestureExclusionPath.reset();
            this.mSystemGestureExclusion.getBoundaryPath(this.mSystemGestureExclusionPath);
            canvas.drawPath(this.mSystemGestureExclusionPath, this.mSystemGestureExclusionPaint);
        }
        if (!this.mSystemGestureExclusionRejected.isEmpty()) {
            this.mSystemGestureExclusionPath.reset();
            this.mSystemGestureExclusionRejected.getBoundaryPath(this.mSystemGestureExclusionPath);
            canvas.drawPath(this.mSystemGestureExclusionPath, this.mSystemGestureExclusionRejectedPaint);
        }
        int i5 = this.mActivePointerId;
        if (i5 >= 0) {
            PointerState pointerState = this.mPointers.get(i5);
            float f2 = i4;
            canvas.drawRect(0.0f, this.mHeaderPaddingTop, i2 - 1, f2, this.mTextBackgroundPaint);
            float f3 = i3;
            canvas.drawText(this.mText.clear().append("P: ").append(this.mCurNumPointers).append(" / ").append(this.mMaxNumPointers).toString(), 1.0f, f3, this.mTextPaint);
            int i6 = pointerState.mTraceCount;
            if ((!this.mCurDown || !pointerState.mCurDown) && i6 != 0) {
                f = 1.0f;
                int i7 = i6 - 1;
                float f4 = pointerState.mTraceX[i7] - pointerState.mTraceX[0];
                float f5 = pointerState.mTraceY[i7] - pointerState.mTraceY[0];
                int i8 = i2 * 2;
                canvas.drawRect(i2, this.mHeaderPaddingTop, i8 - 1, f2, Math.abs(f4) < ((float) this.mVC.getScaledTouchSlop()) ? this.mTextBackgroundPaint : this.mTextLevelPaint);
                canvas.drawText(this.mText.clear().append("dX: ").append(f4, 1).toString(), i2 + 1, f3, this.mTextPaint);
                canvas.drawRect(i8, this.mHeaderPaddingTop, (i2 * 3) - 1, f2, Math.abs(f5) < ((float) this.mVC.getScaledTouchSlop()) ? this.mTextBackgroundPaint : this.mTextLevelPaint);
                canvas.drawText(this.mText.clear().append("dY: ").append(f5, 1).toString(), i8 + 1, f3, this.mTextPaint);
            } else {
                f = 1.0f;
                canvas.drawRect(i2, this.mHeaderPaddingTop, r4 - 1, f2, this.mTextBackgroundPaint);
                canvas.drawText(this.mText.clear().append("X: ").append(pointerState.mCoords.x, 1).toString(), i2 + 1, f3, this.mTextPaint);
                canvas.drawRect(i2 * 2, this.mHeaderPaddingTop, (i2 * 3) - 1, f2, this.mTextBackgroundPaint);
                canvas.drawText(this.mText.clear().append("Y: ").append(pointerState.mCoords.y, 1).toString(), r4 + 1, f3, this.mTextPaint);
            }
            canvas.drawRect(i2 * 3, this.mHeaderPaddingTop, r14 - 1, f2, this.mTextBackgroundPaint);
            canvas.drawText(this.mText.clear().append("Xv: ").append(pointerState.mXVelocity, 3).toString(), r13 + 1, f3, this.mTextPaint);
            canvas.drawRect(i2 * 4, this.mHeaderPaddingTop, r13 - 1, f2, this.mTextBackgroundPaint);
            canvas.drawText(this.mText.clear().append("Yv: ").append(pointerState.mYVelocity, 3).toString(), r14 + 1, f3, this.mTextPaint);
            float f6 = i2 * 5;
            int i9 = i2 * 6;
            canvas.drawRect(f6, this.mHeaderPaddingTop, i9 - 1, f2, this.mTextBackgroundPaint);
            float f7 = i2;
            canvas.drawRect(f6, this.mHeaderPaddingTop, ((pointerState.mCoords.pressure * f7) + f6) - f, f2, this.mTextLevelPaint);
            canvas.drawText(this.mText.clear().append("Prs: ").append(pointerState.mCoords.pressure, 2).toString(), r13 + 1, f3, this.mTextPaint);
            float f8 = i9;
            canvas.drawRect(f8, this.mHeaderPaddingTop, width, f2, this.mTextBackgroundPaint);
            canvas.drawRect(f8, this.mHeaderPaddingTop, ((pointerState.mCoords.size * f7) + f8) - f, f2, this.mTextLevelPaint);
            canvas.drawText(this.mText.clear().append("Size: ").append(pointerState.mCoords.size, 2).toString(), i9 + 1, f3, this.mTextPaint);
        }
        for (int i10 = 0; i10 < size; i10++) {
            PointerState pointerState2 = this.mPointers.get(i10);
            int i11 = pointerState2.mTraceCount;
            this.mPaint.setARGB(255, 128, 255, 255);
            float f9 = 0.0f;
            float f10 = 0.0f;
            boolean z = false;
            boolean z2 = false;
            int i12 = 0;
            while (i12 < i11) {
                float f11 = pointerState2.mTraceX[i12];
                float f12 = pointerState2.mTraceY[i12];
                if (Float.isNaN(f11)) {
                    i = i12;
                    z = false;
                } else {
                    if (z) {
                        i = i12;
                        float f13 = f9;
                        canvas.drawLine(f10, f9, f11, f12, this.mPathPaint);
                        canvas.drawPoint(f10, f13, pointerState2.mTraceCurrent[i] ? this.mCurrentPointPaint : this.mPaint);
                        z2 = true;
                    } else {
                        i = i12;
                    }
                    f10 = f11;
                    f9 = f12;
                    z = true;
                }
                i12 = i + 1;
            }
            float f14 = f9;
            if (z2) {
                this.mPaint.setARGB(255, 255, 64, 128);
                canvas.drawLine(f10, f14, f10 + (pointerState2.mXVelocity * 16.0f), f14 + (pointerState2.mYVelocity * 16.0f), this.mPaint);
                if (this.mAltVelocity != null) {
                    this.mPaint.setARGB(255, 64, 255, 128);
                    canvas.drawLine(f10, f14, f10 + (pointerState2.mAltXVelocity * 16.0f), f14 + (pointerState2.mAltYVelocity * 16.0f), this.mPaint);
                }
            }
            if (this.mCurDown && pointerState2.mCurDown) {
                canvas.drawLine(0.0f, pointerState2.mCoords.y, getWidth(), pointerState2.mCoords.y, this.mTargetPaint);
                canvas.drawLine(pointerState2.mCoords.x, 0.0f, pointerState2.mCoords.x, getHeight(), this.mTargetPaint);
                int i13 = (int) (pointerState2.mCoords.pressure * 255.0f);
                int i14 = 255 - i13;
                this.mPaint.setARGB(255, i13, 255, i14);
                canvas.drawPoint(pointerState2.mCoords.x, pointerState2.mCoords.y, this.mPaint);
                this.mPaint.setARGB(255, i13, i14, 128);
                drawOval(canvas, pointerState2.mCoords.x, pointerState2.mCoords.y, pointerState2.mCoords.touchMajor, pointerState2.mCoords.touchMinor, pointerState2.mCoords.orientation, this.mPaint);
                this.mPaint.setARGB(255, i13, 128, i14);
                drawOval(canvas, pointerState2.mCoords.x, pointerState2.mCoords.y, pointerState2.mCoords.toolMajor, pointerState2.mCoords.toolMinor, pointerState2.mCoords.orientation, this.mPaint);
                float f15 = pointerState2.mCoords.toolMajor * 0.7f;
                if (f15 < 20.0f) {
                    f15 = 20.0f;
                }
                this.mPaint.setARGB(255, i13, 255, 0);
                double d = f15;
                float fSin = (float) (Math.sin(pointerState2.mCoords.orientation) * d);
                float f16 = (float) ((-Math.cos(pointerState2.mCoords.orientation)) * d);
                if (pointerState2.mToolType == 2 || pointerState2.mToolType == 4) {
                    canvas.drawLine(pointerState2.mCoords.x, pointerState2.mCoords.y, pointerState2.mCoords.x + fSin, pointerState2.mCoords.y + f16, this.mPaint);
                } else {
                    canvas.drawLine(pointerState2.mCoords.x - fSin, pointerState2.mCoords.y - f16, pointerState2.mCoords.x + fSin, pointerState2.mCoords.y + f16, this.mPaint);
                }
                float fSin2 = (float) Math.sin(pointerState2.mCoords.getAxisValue(25));
                canvas.drawCircle(pointerState2.mCoords.x + (fSin * fSin2), pointerState2.mCoords.y + (f16 * fSin2), 3.0f, this.mPaint);
                if (pointerState2.mHasBoundingBox) {
                    canvas.drawRect(pointerState2.mBoundingLeft, pointerState2.mBoundingTop, pointerState2.mBoundingRight, pointerState2.mBoundingBottom, this.mPaint);
                }
            }
        }
    }

    private void logMotionEvent(String str, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int historySize = motionEvent.getHistorySize();
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < historySize; i++) {
            for (int i2 = 0; i2 < pointerCount; i2++) {
                int pointerId = motionEvent.getPointerId(i2);
                motionEvent.getHistoricalPointerCoords(i2, i, this.mTempCoords);
                logCoords(str, action, i2, this.mTempCoords, pointerId, motionEvent);
            }
        }
        for (int i3 = 0; i3 < pointerCount; i3++) {
            int pointerId2 = motionEvent.getPointerId(i3);
            motionEvent.getPointerCoords(i3, this.mTempCoords);
            logCoords(str, action, i3, this.mTempCoords, pointerId2, motionEvent);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void logCoords(java.lang.String r8, int r9, int r10, android.view.MotionEvent.PointerCoords r11, int r12, android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.PointerLocationView.logCoords(java.lang.String, int, int, android.view.MotionEvent$PointerCoords, int, android.view.MotionEvent):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x01e3  */
    @Override // android.view.WindowManagerPolicyConstants.PointerEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onPointerEvent(android.view.MotionEvent r23) {
        /*
            Method dump skipped, instruction units count: 624
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.PointerLocationView.onPointerEvent(android.view.MotionEvent):void");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        onPointerEvent(motionEvent);
        if (motionEvent.getAction() != 0 || isFocused()) {
            return true;
        }
        requestFocus();
        return true;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int source = motionEvent.getSource();
        if ((source & 2) != 0) {
            onPointerEvent(motionEvent);
            return true;
        }
        if ((source & 16) != 0) {
            logMotionEvent("Joystick", motionEvent);
            return true;
        }
        if ((source & 8) != 0) {
            logMotionEvent("Position", motionEvent);
            return true;
        }
        logMotionEvent("Generic", motionEvent);
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (shouldLogKey(i)) {
            int repeatCount = keyEvent.getRepeatCount();
            if (repeatCount == 0) {
                Log.i(TAG, "Key Down: " + keyEvent);
                return true;
            }
            Log.i(TAG, "Key Repeat #" + repeatCount + ": " + keyEvent);
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (shouldLogKey(i)) {
            Log.i(TAG, "Key Up: " + keyEvent);
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    private static boolean shouldLogKey(int i) {
        switch (i) {
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
                break;
            default:
                if (KeyEvent.isGamepadButton(i) || KeyEvent.isModifierKey(i)) {
                }
                break;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        logMotionEvent("Trackball", motionEvent);
        return true;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIm.registerInputDeviceListener(this, getHandler());
        if (shouldShowSystemGestureExclusion()) {
            try {
                WindowManagerGlobal.getWindowManagerService().registerSystemGestureExclusionListener(this.mSystemGestureExclusionListener, this.mContext.getDisplayId());
                int iSystemGestureExclusionOpacity = systemGestureExclusionOpacity();
                this.mSystemGestureExclusionPaint.setAlpha(iSystemGestureExclusionOpacity);
                this.mSystemGestureExclusionRejectedPaint.setAlpha(iSystemGestureExclusionOpacity);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            this.mSystemGestureExclusion.setEmpty();
        }
        logInputDevices();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIm.unregisterInputDeviceListener(this);
        try {
            WindowManagerGlobal.getWindowManagerService().unregisterSystemGestureExclusionListener(this.mSystemGestureExclusionListener, this.mContext.getDisplayId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceAdded(int i) {
        logInputDeviceState(i, "Device Added");
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceChanged(int i) {
        logInputDeviceState(i, "Device Changed");
    }

    @Override // android.hardware.input.InputManager.InputDeviceListener
    public void onInputDeviceRemoved(int i) {
        logInputDeviceState(i, "Device Removed");
    }

    private void logInputDevices() {
        for (int i : InputDevice.getDeviceIds()) {
            logInputDeviceState(i, "Device Enumerated");
        }
    }

    private void logInputDeviceState(int i, String str) {
        InputDevice inputDevice = this.mIm.getInputDevice(i);
        if (inputDevice != null) {
            Log.i(TAG, str + ": " + inputDevice);
        } else {
            Log.i(TAG, str + ": " + i);
        }
    }

    private static boolean shouldShowSystemGestureExclusion() {
        return systemGestureExclusionOpacity() > 0;
    }

    private static int systemGestureExclusionOpacity() {
        int i = SystemProperties.getInt(GESTURE_EXCLUSION_PROP, 0);
        if (i < 0 || i > 255) {
            return 0;
        }
        return i;
    }

    private static final class FasterStringBuilder {
        private char[] mChars = new char[64];
        private int mLength;

        public FasterStringBuilder clear() {
            this.mLength = 0;
            return this;
        }

        public FasterStringBuilder append(String str) {
            int length = str.length();
            str.getChars(0, length, this.mChars, reserve(length));
            this.mLength += length;
            return this;
        }

        public FasterStringBuilder append(int i) {
            return append(i, 0);
        }

        public FasterStringBuilder append(int i, int i2) {
            boolean z = i < 0;
            if (z && (i = -i) < 0) {
                append("-2147483648");
                return this;
            }
            int iReserve = reserve(11);
            char[] cArr = this.mChars;
            if (i == 0) {
                cArr[iReserve] = '0';
                this.mLength++;
                return this;
            }
            if (z) {
                cArr[iReserve] = '-';
                iReserve++;
            }
            int i3 = 1000000000;
            int i4 = 10;
            while (i < i3) {
                i3 /= 10;
                i4--;
                if (i4 < i2) {
                    cArr[iReserve] = '0';
                    iReserve++;
                }
            }
            while (true) {
                int i5 = i / i3;
                i -= i5 * i3;
                i3 /= 10;
                int i6 = iReserve + 1;
                cArr[iReserve] = (char) (i5 + 48);
                if (i3 == 0) {
                    this.mLength = i6;
                    return this;
                }
                iReserve = i6;
            }
        }

        public FasterStringBuilder append(float f, int i) {
            int i2 = 1;
            for (int i3 = 0; i3 < i; i3++) {
                i2 *= 10;
            }
            float f2 = i2;
            float fRint = (float) (Math.rint(f * f2) / ((double) i2));
            int i4 = (int) fRint;
            if (i4 == 0 && fRint < 0.0f) {
                append(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            }
            append(i4);
            if (i != 0) {
                append(".");
                double dAbs = Math.abs(fRint);
                append((int) (((float) (dAbs - Math.floor(dAbs))) * f2), i);
            }
            return this;
        }

        public String toString() {
            return new String(this.mChars, 0, this.mLength);
        }

        private int reserve(int i) {
            int i2 = this.mLength;
            int i3 = i + i2;
            char[] cArr = this.mChars;
            int length = cArr.length;
            if (i3 > length) {
                char[] cArr2 = new char[length * 2];
                System.arraycopy(cArr, 0, cArr2, 0, i2);
                this.mChars = cArr2;
            }
            return i2;
        }
    }

    /* JADX INFO: renamed from: com.android.internal.widget.PointerLocationView$1, reason: invalid class name */
    class AnonymousClass1 extends ISystemGestureExclusionListener.Stub {
        AnonymousClass1() {
        }

        @Override // android.view.ISystemGestureExclusionListener
        public void onSystemGestureExclusionChanged(int i, Region region, Region region2) {
            final Region regionObtain = Region.obtain(region);
            final Region regionObtain2 = Region.obtain();
            if (region2 != null) {
                regionObtain2.set(region2);
                regionObtain2.op(regionObtain, Region.Op.DIFFERENCE);
            }
            Handler handler = PointerLocationView.this.getHandler();
            if (handler != null) {
                handler.post(new Runnable() { // from class: com.android.internal.widget.-$$Lambda$PointerLocationView$1$oB_lZgu4R2w5yYnte51bJz8bZVw
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onSystemGestureExclusionChanged$0$PointerLocationView$1(regionObtain, regionObtain2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onSystemGestureExclusionChanged$0$PointerLocationView$1(Region region, Region region2) {
            PointerLocationView.this.mSystemGestureExclusion.set(region);
            PointerLocationView.this.mSystemGestureExclusionRejected.set(region2);
            region.recycle();
            PointerLocationView.this.invalidate();
        }
    }
}
