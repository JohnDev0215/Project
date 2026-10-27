package com.google.android.exoplayer2.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.util.Formatter;
import java.util.Locale;

/* JADX INFO: loaded from: classes6.dex */
public class DefaultTimeBar extends View implements TimeBar {
    private int adGroupCount;
    private long[] adGroupTimesMs;
    private final Paint adMarkerPaint;
    private final int adMarkerWidth;
    private final int barHeight;
    private final Rect bufferedBar;
    private final Paint bufferedPaint;
    private long bufferedPosition;
    private long duration;
    private final int fineScrubYThreshold;
    private final StringBuilder formatBuilder;
    private final Formatter formatter;
    private int keyCountIncrement;
    private long keyTimeIncrement;
    private int lastCoarseScrubXPosition;
    private TimeBar.OnScrubListener listener;
    private int[] locationOnScreen;
    private boolean[] playedAdGroups;
    private final Paint playedAdMarkerPaint;
    private final Paint playedPaint;
    private long position;
    private final Rect progressBar;
    private long scrubPosition;
    private final Rect scrubberBar;
    private final int scrubberDisabledSize;
    private final int scrubberDraggedSize;
    private final int scrubberEnabledSize;
    private final int scrubberPadding;
    private final Paint scrubberPaint;
    private boolean scrubbing;
    private final Rect seekBounds;
    private final Runnable stopScrubbingRunnable;
    private Point touchPosition;
    private final int touchTargetHeight;
    private final Paint unplayedPaint;

    private static int getDefaultBufferedColor(int i) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (-872415232);
    }

    private static int getDefaultPlayedAdMarkerColor(int i) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | 855638016;
    }

    private static int getDefaultScrubberColor(int i) {
        return i | ViewCompat.MEASURED_STATE_MASK;
    }

    private static int getDefaultUnplayedColor(int i) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | 855638016;
    }

    public DefaultTimeBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.seekBounds = new Rect();
        this.progressBar = new Rect();
        this.bufferedBar = new Rect();
        this.scrubberBar = new Rect();
        Paint paint = new Paint();
        this.playedPaint = paint;
        Paint paint2 = new Paint();
        this.bufferedPaint = paint2;
        Paint paint3 = new Paint();
        this.unplayedPaint = paint3;
        Paint paint4 = new Paint();
        this.adMarkerPaint = paint4;
        Paint paint5 = new Paint();
        this.playedAdMarkerPaint = paint5;
        Paint paint6 = new Paint();
        this.scrubberPaint = paint6;
        paint6.setAntiAlias(true);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.fineScrubYThreshold = dpToPx(displayMetrics, -50);
        int iDpToPx = dpToPx(displayMetrics, 4);
        int iDpToPx2 = dpToPx(displayMetrics, 26);
        int iDpToPx3 = dpToPx(displayMetrics, 4);
        int iDpToPx4 = dpToPx(displayMetrics, 12);
        int iDpToPx5 = dpToPx(displayMetrics, 0);
        int iDpToPx6 = dpToPx(displayMetrics, 16);
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.DefaultTimeBar, 0, 0);
            try {
                this.barHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.DefaultTimeBar_bar_height, iDpToPx);
                this.touchTargetHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.DefaultTimeBar_touch_target_height, iDpToPx2);
                this.adMarkerWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.DefaultTimeBar_ad_marker_width, iDpToPx3);
                this.scrubberEnabledSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.DefaultTimeBar_scrubber_enabled_size, iDpToPx4);
                this.scrubberDisabledSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.DefaultTimeBar_scrubber_disabled_size, iDpToPx5);
                this.scrubberDraggedSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.DefaultTimeBar_scrubber_dragged_size, iDpToPx6);
                int i = typedArrayObtainStyledAttributes.getInt(R$styleable.DefaultTimeBar_played_color, -1);
                int i2 = typedArrayObtainStyledAttributes.getInt(R$styleable.DefaultTimeBar_scrubber_color, getDefaultScrubberColor(i));
                int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.DefaultTimeBar_buffered_color, getDefaultBufferedColor(i));
                int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.DefaultTimeBar_unplayed_color, getDefaultUnplayedColor(i));
                int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.DefaultTimeBar_ad_marker_color, -1291845888);
                int i6 = typedArrayObtainStyledAttributes.getInt(R$styleable.DefaultTimeBar_played_ad_marker_color, getDefaultPlayedAdMarkerColor(i5));
                paint.setColor(i);
                paint6.setColor(i2);
                paint2.setColor(i3);
                paint3.setColor(i4);
                paint4.setColor(i5);
                paint5.setColor(i6);
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        } else {
            this.barHeight = iDpToPx;
            this.touchTargetHeight = iDpToPx2;
            this.adMarkerWidth = iDpToPx3;
            this.scrubberEnabledSize = iDpToPx4;
            this.scrubberDisabledSize = iDpToPx5;
            this.scrubberDraggedSize = iDpToPx6;
            paint.setColor(-1);
            paint6.setColor(getDefaultScrubberColor(-1));
            paint2.setColor(getDefaultBufferedColor(-1));
            paint3.setColor(getDefaultUnplayedColor(-1));
            paint4.setColor(-1291845888);
        }
        StringBuilder sb = new StringBuilder();
        this.formatBuilder = sb;
        this.formatter = new Formatter(sb, Locale.getDefault());
        this.stopScrubbingRunnable = new Runnable() { // from class: com.google.android.exoplayer2.ui.DefaultTimeBar.1
            @Override // java.lang.Runnable
            public void run() {
                DefaultTimeBar.this.stopScrubbing(false);
            }
        };
        this.scrubberPadding = (Math.max(this.scrubberDisabledSize, Math.max(this.scrubberEnabledSize, this.scrubberDraggedSize)) + 1) / 2;
        this.duration = -9223372036854775807L;
        this.keyTimeIncrement = -9223372036854775807L;
        this.keyCountIncrement = 20;
        setFocusable(true);
        if (Util.SDK_INT >= 16) {
            maybeSetImportantForAccessibilityV16();
        }
    }

    public void setListener(TimeBar.OnScrubListener onScrubListener) {
        this.listener = onScrubListener;
    }

    public void setKeyTimeIncrement(long j) {
        Assertions.checkArgument(j > 0);
        this.keyCountIncrement = -1;
        this.keyTimeIncrement = j;
    }

    @Override // com.google.android.exoplayer2.ui.TimeBar
    public void setKeyCountIncrement(int i) {
        Assertions.checkArgument(i > 0);
        this.keyCountIncrement = i;
        this.keyTimeIncrement = -9223372036854775807L;
    }

    public void setPosition(long j) {
        this.position = j;
        setContentDescription(getProgressText());
        update();
    }

    public void setBufferedPosition(long j) {
        this.bufferedPosition = j;
        update();
    }

    public void setDuration(long j) {
        this.duration = j;
        if (this.scrubbing && j == -9223372036854775807L) {
            stopScrubbing(true);
        }
        update();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (!this.scrubbing || z) {
            return;
        }
        stopScrubbing(true);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.save();
        drawTimeBar(canvas);
        drawPlayhead(canvas);
        canvas.restore();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            boolean r0 = r7.isEnabled()
            r1 = 0
            if (r0 == 0) goto L7f
            long r2 = r7.duration
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 > 0) goto L11
            goto L7f
        L11:
            android.graphics.Point r0 = r7.resolveRelativeTouchPosition(r8)
            int r2 = r0.x
            int r0 = r0.y
            int r3 = r8.getAction()
            r4 = 1
            if (r3 == 0) goto L64
            r5 = 3
            if (r3 == r4) goto L55
            r6 = 2
            if (r3 == r6) goto L29
            if (r3 == r5) goto L55
            goto L7f
        L29:
            boolean r8 = r7.scrubbing
            if (r8 == 0) goto L7f
            int r8 = r7.fineScrubYThreshold
            if (r0 >= r8) goto L3b
            int r8 = r7.lastCoarseScrubXPosition
            int r2 = r2 - r8
            int r2 = r2 / r5
            int r8 = r8 + r2
            float r8 = (float) r8
            r7.positionScrubber(r8)
            goto L41
        L3b:
            r7.lastCoarseScrubXPosition = r2
            float r8 = (float) r2
            r7.positionScrubber(r8)
        L41:
            long r0 = r7.getScrubberPosition()
            r7.scrubPosition = r0
            com.google.android.exoplayer2.ui.TimeBar$OnScrubListener r8 = r7.listener
            if (r8 == 0) goto L4e
            r8.onScrubMove(r7, r0)
        L4e:
            r7.update()
            r7.invalidate()
            return r4
        L55:
            boolean r0 = r7.scrubbing
            if (r0 == 0) goto L7f
            int r8 = r8.getAction()
            if (r8 != r5) goto L60
            r1 = r4
        L60:
            r7.stopScrubbing(r1)
            return r4
        L64:
            float r8 = (float) r2
            float r0 = (float) r0
            boolean r0 = r7.isInSeekBar(r8, r0)
            if (r0 == 0) goto L7f
            r7.startScrubbing()
            r7.positionScrubber(r8)
            long r0 = r7.getScrubberPosition()
            r7.scrubPosition = r0
            r7.update()
            r7.invalidate()
            return r4
        L7f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DefaultTimeBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onKeyDown(int r5, android.view.KeyEvent r6) {
        /*
            r4 = this;
            boolean r0 = r4.isEnabled()
            if (r0 == 0) goto L36
            long r0 = r4.getPositionIncrement()
            r2 = 66
            r3 = 1
            if (r5 == r2) goto L27
            switch(r5) {
                case 21: goto L13;
                case 22: goto L14;
                case 23: goto L27;
                default: goto L12;
            }
        L12:
            goto L36
        L13:
            long r0 = -r0
        L14:
            boolean r0 = r4.scrubIncrementally(r0)
            if (r0 == 0) goto L36
            java.lang.Runnable r5 = r4.stopScrubbingRunnable
            r4.removeCallbacks(r5)
            java.lang.Runnable r5 = r4.stopScrubbingRunnable
            r0 = 1000(0x3e8, double:4.94E-321)
            r4.postDelayed(r5, r0)
            return r3
        L27:
            boolean r0 = r4.scrubbing
            if (r0 == 0) goto L36
            java.lang.Runnable r5 = r4.stopScrubbingRunnable
            r4.removeCallbacks(r5)
            java.lang.Runnable r5 = r4.stopScrubbingRunnable
            r5.run()
            return r3
        L36:
            boolean r5 = super.onKeyDown(r5, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.ui.DefaultTimeBar.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 0) {
            size = this.touchTargetHeight;
        } else if (mode != 1073741824) {
            size = Math.min(this.touchTargetHeight, size);
        }
        setMeasuredDimension(View.MeasureSpec.getSize(i), size);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = ((i4 - i2) - this.touchTargetHeight) / 2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i3 - i) - getPaddingRight();
        int i6 = this.touchTargetHeight;
        int i7 = ((i6 - this.barHeight) / 2) + i5;
        this.seekBounds.set(paddingLeft, i5, paddingRight, i6 + i5);
        Rect rect = this.progressBar;
        Rect rect2 = this.seekBounds;
        int i8 = rect2.left;
        int i9 = this.scrubberPadding;
        rect.set(i8 + i9, i7, rect2.right - i9, this.barHeight + i7);
        update();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    @TargetApi(14)
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (accessibilityEvent.getEventType() == 4) {
            accessibilityEvent.getText().add(getProgressText());
        }
        accessibilityEvent.setClassName(DefaultTimeBar.class.getName());
    }

    @Override // android.view.View
    @TargetApi(21)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(DefaultTimeBar.class.getCanonicalName());
        accessibilityNodeInfo.setContentDescription(getProgressText());
        if (this.duration <= 0) {
            return;
        }
        int i = Util.SDK_INT;
        if (i >= 21) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
        } else if (i >= 16) {
            accessibilityNodeInfo.addAction(4096);
            accessibilityNodeInfo.addAction(8192);
        }
    }

    @Override // android.view.View
    @TargetApi(16)
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        if (this.duration <= 0) {
            return false;
        }
        if (i == 8192) {
            if (scrubIncrementally(-getPositionIncrement())) {
                stopScrubbing(false);
            }
        } else {
            if (i != 4096) {
                return false;
            }
            if (scrubIncrementally(getPositionIncrement())) {
                stopScrubbing(false);
            }
        }
        sendAccessibilityEvent(4);
        return true;
    }

    @TargetApi(16)
    private void maybeSetImportantForAccessibilityV16() {
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    private void startScrubbing() {
        this.scrubbing = true;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        TimeBar.OnScrubListener onScrubListener = this.listener;
        if (onScrubListener != null) {
            onScrubListener.onScrubStart(this, getScrubberPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopScrubbing(boolean z) {
        this.scrubbing = false;
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        invalidate();
        TimeBar.OnScrubListener onScrubListener = this.listener;
        if (onScrubListener != null) {
            onScrubListener.onScrubStop(this, getScrubberPosition(), z);
        }
    }

    private void update() {
        this.bufferedBar.set(this.progressBar);
        this.scrubberBar.set(this.progressBar);
        long j = this.scrubbing ? this.scrubPosition : this.position;
        if (this.duration > 0) {
            int iWidth = (int) ((((long) this.progressBar.width()) * this.bufferedPosition) / this.duration);
            Rect rect = this.bufferedBar;
            Rect rect2 = this.progressBar;
            rect.right = Math.min(rect2.left + iWidth, rect2.right);
            int iWidth2 = (int) ((((long) this.progressBar.width()) * j) / this.duration);
            Rect rect3 = this.scrubberBar;
            Rect rect4 = this.progressBar;
            rect3.right = Math.min(rect4.left + iWidth2, rect4.right);
        } else {
            Rect rect5 = this.bufferedBar;
            int i = this.progressBar.left;
            rect5.right = i;
            this.scrubberBar.right = i;
        }
        invalidate(this.seekBounds);
    }

    private void positionScrubber(float f) {
        Rect rect = this.scrubberBar;
        Rect rect2 = this.progressBar;
        rect.right = Util.constrainValue((int) f, rect2.left, rect2.right);
    }

    private Point resolveRelativeTouchPosition(MotionEvent motionEvent) {
        if (this.locationOnScreen == null) {
            this.locationOnScreen = new int[2];
            this.touchPosition = new Point();
        }
        getLocationOnScreen(this.locationOnScreen);
        this.touchPosition.set(((int) motionEvent.getRawX()) - this.locationOnScreen[0], ((int) motionEvent.getRawY()) - this.locationOnScreen[1]);
        return this.touchPosition;
    }

    private long getScrubberPosition() {
        if (this.progressBar.width() <= 0 || this.duration == -9223372036854775807L) {
            return 0L;
        }
        return (((long) this.scrubberBar.width()) * this.duration) / ((long) this.progressBar.width());
    }

    private boolean isInSeekBar(float f, float f2) {
        return this.seekBounds.contains((int) f, (int) f2);
    }

    private void drawTimeBar(Canvas canvas) {
        int iHeight = this.progressBar.height();
        int iCenterY = this.progressBar.centerY() - (iHeight / 2);
        int i = iHeight + iCenterY;
        if (this.duration <= 0) {
            Rect rect = this.progressBar;
            canvas.drawRect(rect.left, iCenterY, rect.right, i, this.unplayedPaint);
            return;
        }
        Rect rect2 = this.bufferedBar;
        int i2 = rect2.left;
        int i3 = rect2.right;
        int iMax = Math.max(Math.max(this.progressBar.left, i3), this.scrubberBar.right);
        int i4 = this.progressBar.right;
        if (iMax < i4) {
            canvas.drawRect(iMax, iCenterY, i4, i, this.unplayedPaint);
        }
        int iMax2 = Math.max(i2, this.scrubberBar.right);
        if (i3 > iMax2) {
            canvas.drawRect(iMax2, iCenterY, i3, i, this.bufferedPaint);
        }
        if (this.scrubberBar.width() > 0) {
            Rect rect3 = this.scrubberBar;
            canvas.drawRect(rect3.left, iCenterY, rect3.right, i, this.playedPaint);
        }
        int i5 = this.adMarkerWidth / 2;
        for (int i6 = 0; i6 < this.adGroupCount; i6++) {
            int iWidth = ((int) ((((long) this.progressBar.width()) * Util.constrainValue(this.adGroupTimesMs[i6], 0L, this.duration)) / this.duration)) - i5;
            Rect rect4 = this.progressBar;
            canvas.drawRect(rect4.left + Math.min(rect4.width() - this.adMarkerWidth, Math.max(0, iWidth)), iCenterY, r7 + this.adMarkerWidth, i, this.playedAdGroups[i6] ? this.playedAdMarkerPaint : this.adMarkerPaint);
        }
    }

    private void drawPlayhead(Canvas canvas) {
        int i;
        if (this.duration <= 0) {
            return;
        }
        if (this.scrubbing || isFocused()) {
            i = this.scrubberDraggedSize;
        } else {
            i = isEnabled() ? this.scrubberEnabledSize : this.scrubberDisabledSize;
        }
        Rect rect = this.scrubberBar;
        canvas.drawCircle(Util.constrainValue(rect.right, rect.left, this.progressBar.right), this.scrubberBar.centerY(), i / 2, this.scrubberPaint);
    }

    private String getProgressText() {
        return Util.getStringForTime(this.formatBuilder, this.formatter, this.position);
    }

    private long getPositionIncrement() {
        long j = this.keyTimeIncrement;
        if (j != -9223372036854775807L) {
            return j;
        }
        long j2 = this.duration;
        if (j2 == -9223372036854775807L) {
            return 0L;
        }
        return j2 / ((long) this.keyCountIncrement);
    }

    private boolean scrubIncrementally(long j) {
        if (this.duration <= 0) {
            return false;
        }
        long scrubberPosition = getScrubberPosition();
        long jConstrainValue = Util.constrainValue(scrubberPosition + j, 0L, this.duration);
        this.scrubPosition = jConstrainValue;
        if (jConstrainValue == scrubberPosition) {
            return false;
        }
        if (!this.scrubbing) {
            startScrubbing();
        }
        TimeBar.OnScrubListener onScrubListener = this.listener;
        if (onScrubListener != null) {
            onScrubListener.onScrubMove(this, this.scrubPosition);
        }
        update();
        return true;
    }

    private static int dpToPx(DisplayMetrics displayMetrics, int i) {
        return (int) ((i * displayMetrics.density) + 0.5f);
    }
}
