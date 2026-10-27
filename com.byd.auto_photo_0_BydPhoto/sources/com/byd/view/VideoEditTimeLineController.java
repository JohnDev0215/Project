package com.byd.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import com.byd.auto_photo.R;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class VideoEditTimeLineController extends ViewGroup {
    private static final int MIN_TIME_DISTANCE = 2000;
    private static final int OFFSET_PROGRESS_SHOW = 80;
    public static final int TYPE_FRAME_CONTENT = 0;
    public static final int TYPE_PROGRESS_CURRENT = 3;
    public static final int TYPE_PROGRESS_END = 2;
    public static final int TYPE_PROGRESS_START = 1;
    private static final ThreadLocal<DecimalFormat> decimalFormat = new ThreadLocal<DecimalFormat>() { // from class: com.byd.view.VideoEditTimeLineController.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public DecimalFormat initialValue() {
            return new DecimalFormat("0.0", DecimalFormatSymbols.getInstance(Locale.CHINESE));
        }
    };
    private ValueAnimator autoScrollAnim;
    private int canvasOffsetX;
    private float clipDurationTextSize;
    private boolean clipEditMode;
    private int colorClipDurationText;
    private int colorDisableMask;
    private int colorProgressLine;
    private int colorScaleLine;
    private int colorSlideThumb;
    private int colorSliderBg;
    private Context context;
    private int dragType;
    private int endProgress;
    private LinearLayout frameContainer;
    private int frameContainerHeight;
    private int frameContainerTopMargin;
    private int frameContainerVerticalBorderSize;
    private int frameContainerWidth;
    private int frameItemWidth;
    private int max;
    private OnTimeLineListener onTimeLineListener;
    private Paint paint;
    private int progress;
    private int progressLineWidth;
    private int scaleLineBottomOffset;
    private int scaleLineHeight;
    private int scaleLineSpacing;
    private int scaleLineTopOffset;
    private int scaleLineWidth;
    private int scaleTextHeight;
    private float scaleTextSize;
    private final Path sliderPath;
    private int sliderTop;
    private int sliderWidth;
    private int startProgress;
    private int startX;
    private Rect textRect;
    private RectF textShowRect;
    private VelocityTracker tracker;
    private int viewShowHeight;
    private int viewShowWidth;
    private int viewTotalWidth;
    private float xVelocity;

    public interface OnTimeLineListener {
        void onDraggingEnd(int i);

        void onDraggingStart(int i);

        void onProgressChanged(int i, int i2, int i3);
    }

    public VideoEditTimeLineController(Context context) {
        this(context, null);
    }

    public VideoEditTimeLineController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoEditTimeLineController(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public VideoEditTimeLineController(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.sliderPath = new Path();
        this.textRect = new Rect();
        this.textShowRect = new RectF();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VideoEditTimeLineController, i, i2);
        this.sliderWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(17, 50);
        this.scaleLineHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, 40);
        this.scaleLineSpacing = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, 20);
        this.scaleTextHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(15, 30);
        this.frameContainerTopMargin = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 10);
        this.colorScaleLine = typedArrayObtainStyledAttributes.getColor(4, -1);
        this.scaleLineWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(14, 2);
        this.progressLineWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, 3);
        this.colorProgressLine = typedArrayObtainStyledAttributes.getColor(3, -1);
        this.scaleTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, 16);
        this.clipDurationTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 13);
        this.frameContainerVerticalBorderSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 15);
        this.colorSliderBg = typedArrayObtainStyledAttributes.getColor(6, -1);
        this.colorSlideThumb = typedArrayObtainStyledAttributes.getColor(5, -10066330);
        this.colorDisableMask = typedArrayObtainStyledAttributes.getColor(2, -1300398723);
        this.colorClipDurationText = typedArrayObtainStyledAttributes.getColor(1, -452984832);
        this.scaleLineTopOffset = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, 10);
        this.scaleLineBottomOffset = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, 2);
        typedArrayObtainStyledAttributes.recycle();
        init(context);
    }

    private int calcDragType(int i, int i2) {
        int progressXOffset = this.canvasOffsetX + getProgressXOffset();
        log("calcDragType posOffset : " + progressXOffset + " x : " + i + " y: " + i2);
        if (i > progressXOffset - 10 && i < progressXOffset + 10 && i2 > 0 && i2 < this.viewShowHeight) {
            return 3;
        }
        if (!this.clipEditMode) {
            return 0;
        }
        int iProgressToXOffset = this.canvasOffsetX + progressToXOffset(this.startProgress);
        int i3 = this.sliderWidth;
        int i4 = iProgressToXOffset + i3;
        int i5 = this.sliderTop;
        int i6 = this.frameContainerHeight + i5 + (this.frameContainerVerticalBorderSize * 2);
        if (i >= iProgressToXOffset && i < i4 && i2 >= i5 && i2 <= i6) {
            return 1;
        }
        int iProgressToXOffset2 = this.canvasOffsetX + i3 + progressToXOffset(this.endProgress);
        return (i < iProgressToXOffset2 || i >= this.sliderWidth + iProgressToXOffset2 || i2 < this.sliderTop || i2 > i6) ? 0 : 2;
    }

    private void checkAutoScroll() {
        float f = this.xVelocity;
        if (f > 0.0f && this.canvasOffsetX >= 0) {
            log("left bound");
            return;
        }
        if (f < 0.0f && this.canvasOffsetX <= (-(this.viewTotalWidth - this.viewShowWidth))) {
            log("right bound");
            return;
        }
        if (Math.abs(f) < 1000.0f) {
            log("too slow");
            return;
        }
        this.autoScrollAnim = ValueAnimator.ofInt((int) (this.xVelocity / 10.0f), 0);
        this.autoScrollAnim.setDuration((long) Math.abs(this.xVelocity / 10.0f));
        this.autoScrollAnim.setInterpolator(new DecelerateInterpolator());
        this.autoScrollAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.byd.view.VideoEditTimeLineController.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                VideoEditTimeLineController.this.log("===>" + iIntValue);
                if (VideoEditTimeLineController.this.moveContainer(iIntValue)) {
                    return;
                }
                VideoEditTimeLineController.this.autoScrollAnim.cancel();
            }
        });
        this.autoScrollAnim.start();
    }

    private void checkProgressOutOfBound() {
        int progressXOffset = getProgressXOffset();
        int i = this.canvasOffsetX;
        int i2 = progressXOffset + i;
        int i3 = this.viewShowWidth;
        if (i2 < i3 - 80) {
            if (i >= 0 || i2 >= 80) {
                return;
            }
            this.canvasOffsetX = Math.min(80 - progressXOffset, 0);
            return;
        }
        int i4 = this.sliderWidth + progressXOffset + 80;
        int i5 = this.viewTotalWidth;
        if (i4 >= i5) {
            this.canvasOffsetX = -(i5 - i3);
        } else {
            this.canvasOffsetX = (i3 - 80) - progressXOffset;
        }
    }

    private void clearVelocity() {
        VelocityTracker velocityTracker = this.tracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.tracker.recycle();
            this.tracker = null;
        }
    }

    private void drawClipDurationTipsTime(Canvas canvas, int i, int i2) {
        String clipDurationTimeText = getClipDurationTimeText();
        int i3 = this.canvasOffsetX;
        int i4 = i3 < 0 ? -i3 : i;
        if (i4 >= i) {
            i = i4;
        }
        this.paint.setTextSize(this.clipDurationTextSize);
        this.paint.getTextBounds(clipDurationTimeText, 0, clipDurationTimeText.length(), this.textRect);
        int iWidth = this.textRect.width();
        int iHeight = this.textRect.height();
        this.textShowRect.set(i + 10, (r8 - iHeight) - 8, r7 + iWidth + 8, (i2 + this.frameContainerHeight) - 10);
        this.paint.setColor(this.colorSliderBg);
        canvas.drawRoundRect(this.textShowRect, 4.0f, 8.0f, this.paint);
        this.paint.setColor(this.colorClipDurationText);
        canvas.drawText(clipDurationTimeText, this.textShowRect.centerX() - this.textRect.centerX(), this.textShowRect.centerY() - this.textRect.centerY(), this.paint);
    }

    private void drawLineText(int i, Rect rect, Canvas canvas, int i2, int i3) {
        String timeInFormat = getTimeInFormat(i / 5);
        this.paint.getTextBounds(timeInFormat, 0, timeInFormat.length(), rect);
        canvas.drawText(timeInFormat, i2 - rect.centerX(), (i3 - rect.centerY()) + rect.height() + 1, this.paint);
    }

    private void drawScalePlate(Canvas canvas) {
        int i = this.sliderWidth;
        int i2 = ((this.max * 5) / 1000) + 10;
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.colorScaleLine);
        this.paint.setTextSize(this.scaleTextSize);
        Rect rect = new Rect();
        this.paint.setStrokeWidth(this.scaleLineWidth);
        drawLineText(0, rect, canvas, i, this.scaleLineHeight);
        float f = i;
        canvas.drawLine(f, 0.0f, f, this.scaleLineHeight, this.paint);
        int i3 = i + this.scaleLineSpacing;
        int i4 = this.scaleLineTopOffset;
        int i5 = this.scaleLineHeight - this.scaleLineBottomOffset;
        int i6 = i3;
        for (int i7 = 1; i7 < i2; i7++) {
            if (i7 % 10 == 0) {
                this.paint.setAlpha(255);
                drawLineText(i7, rect, canvas, i6, i5);
            } else {
                this.paint.setAlpha(178);
            }
            float f2 = i6;
            canvas.drawLine(f2, i4, f2, i5, this.paint);
            i6 += this.scaleLineSpacing;
            this.paint.setAlpha(255);
        }
    }

    private void drawSlider(Canvas canvas) {
        int iProgressToXOffset = progressToXOffset(this.startProgress);
        int i = this.sliderWidth;
        int i2 = iProgressToXOffset + i;
        int i3 = this.sliderTop;
        int i4 = this.frameContainerHeight + i3;
        int i5 = this.frameContainerVerticalBorderSize;
        int i6 = i4 + (i5 * 2);
        int i7 = i3 + i5;
        int i8 = this.frameContainerWidth + i;
        this.paint.setColor(1509949439);
        this.paint.setStyle(Paint.Style.STROKE);
        float f = i7;
        float f2 = i8;
        canvas.drawRect(this.sliderWidth, f, f2, this.frameContainerHeight + i7, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        if (this.startProgress > 0) {
            this.paint.setColor(this.colorDisableMask);
            canvas.drawRect(this.sliderWidth, f, i2, this.frameContainerHeight + i7, this.paint);
        }
        if (this.clipEditMode) {
            this.sliderPath.reset();
            float f3 = i2;
            this.sliderPath.moveTo(f3, this.sliderTop);
            float f4 = iProgressToXOffset + 20;
            this.sliderPath.lineTo(f4, this.sliderTop);
            float f5 = iProgressToXOffset;
            this.sliderPath.quadTo(f5, this.sliderTop, f5, r10 + 20);
            this.sliderPath.lineTo(f5, i6 - 20);
            float f6 = i6;
            this.sliderPath.quadTo(f5, f6, f4, f6);
            this.sliderPath.lineTo(f3, f6);
            float f7 = i6 - this.frameContainerVerticalBorderSize;
            this.sliderPath.lineTo(f3, f7);
            float f8 = i2 + 10;
            this.sliderPath.lineTo(f8, f7);
            this.sliderPath.quadTo(f3, f7, f3, r6 - 10);
            int i9 = this.sliderTop + this.frameContainerVerticalBorderSize;
            this.sliderPath.lineTo(f3, i9 + 10);
            float f9 = i9;
            this.sliderPath.quadTo(f3, f9, f8, f9);
            this.sliderPath.lineTo(f3, f9);
            this.sliderPath.close();
            this.paint.setColor(this.colorSliderBg);
            canvas.drawPath(this.sliderPath, this.paint);
            drawSliderDecoration(canvas, iProgressToXOffset, true);
            drawClipDurationTipsTime(canvas, i2, i7);
        }
        int iProgressToXOffset2 = this.sliderWidth + progressToXOffset(this.endProgress);
        int i10 = this.sliderWidth + iProgressToXOffset2;
        if (this.endProgress < this.max) {
            this.paint.setColor(this.colorDisableMask);
            canvas.drawRect(iProgressToXOffset2, f, f2, i7 + this.frameContainerHeight, this.paint);
        }
        if (this.clipEditMode) {
            this.sliderPath.reset();
            float f10 = iProgressToXOffset2;
            this.sliderPath.moveTo(f10, this.sliderTop);
            float f11 = i10 - 20;
            this.sliderPath.lineTo(f11, this.sliderTop);
            float f12 = i10;
            this.sliderPath.quadTo(f12, this.sliderTop, f12, r6 + 20);
            this.sliderPath.lineTo(f12, i6 - 20);
            float f13 = i6;
            this.sliderPath.quadTo(f12, f13, f11, f13);
            this.sliderPath.lineTo(f10, f13);
            float f14 = i6 - this.frameContainerVerticalBorderSize;
            this.sliderPath.lineTo(f10, f14);
            float f15 = iProgressToXOffset2 - 10;
            this.sliderPath.lineTo(f15, f14);
            this.sliderPath.quadTo(f10, f14, f10, r4 - 10);
            int i11 = this.sliderTop + this.frameContainerVerticalBorderSize;
            this.sliderPath.lineTo(f10, i11 + 10);
            float f16 = i11;
            this.sliderPath.quadTo(f10, f16, f15, f16);
            this.sliderPath.lineTo(f10, f16);
            this.sliderPath.close();
            this.paint.setColor(this.colorSliderBg);
            canvas.drawPath(this.sliderPath, this.paint);
            drawSliderDecoration(canvas, iProgressToXOffset2, false);
        }
    }

    private void drawSliderDecoration(Canvas canvas, int i, boolean z) {
        int i2 = this.frameContainerHeight;
        int i3 = (this.frameContainerVerticalBorderSize * 2) + i2;
        int i4 = this.sliderWidth;
        int i5 = i + i4;
        int i6 = (i2 * 2) / 5;
        int i7 = i4 / 4;
        RectF rectF = new RectF();
        if (z) {
            float f = i + i7;
            rectF.left = f;
            rectF.right = f + 8;
        } else {
            float f2 = i5 - i7;
            rectF.right = f2;
            rectF.left = f2 - 8;
        }
        float f3 = this.sliderTop + ((i3 - i6) / 2.0f);
        rectF.top = f3;
        rectF.bottom = f3 + i6;
        this.paint.setColor(this.colorSlideThumb);
        canvas.drawRoundRect(rectF, 5.0f, 5.0f, this.paint);
    }

    private String getClipDurationTimeText() {
        return decimalFormat.get().format((this.endProgress - this.startProgress) / 1000.0f) + "s";
    }

    private int getProgressXOffset() {
        return this.sliderWidth + progressToXOffset(this.progress);
    }

    private String getTimeInFormat(int i) {
        Object objValueOf;
        Object objValueOf2;
        if (i <= 0) {
            return "00:00";
        }
        int i2 = i / 60;
        int i3 = i % 60;
        StringBuilder sb = new StringBuilder();
        if (i2 < 10) {
            objValueOf = "0" + i2;
        } else {
            objValueOf = Integer.valueOf(i2);
        }
        sb.append(objValueOf);
        sb.append(":");
        if (i3 < 10) {
            objValueOf2 = "0" + i3;
        } else {
            objValueOf2 = Integer.valueOf(i3);
        }
        sb.append(objValueOf2);
        return sb.toString();
    }

    private void init(Context context) {
        this.context = context;
        LinearLayout linearLayout = new LinearLayout(context);
        this.frameContainer = linearLayout;
        linearLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.frameContainer.setOrientation(0);
        addView(this.frameContainer);
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.frameItemWidth = this.scaleLineSpacing * 5;
        this.sliderTop = this.scaleLineHeight + this.scaleTextHeight + this.frameContainerTopMargin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        Log.d("m_tag", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean moveContainer(int i) {
        int i2 = this.canvasOffsetX;
        int i3 = i + i2;
        int i4 = this.viewTotalWidth;
        int i5 = this.viewShowWidth;
        if (i3 < (-(i4 - i5))) {
            i3 = -(i4 - i5);
        }
        if (i3 > 0) {
            i3 = 0;
        }
        if (i3 == i2) {
            return false;
        }
        this.canvasOffsetX = i3;
        invalidate();
        return true;
    }

    private void moveLeftSlider(int i) {
        int iXOffsetToProgress = this.startProgress + xOffsetToProgress(i);
        boolean z = false;
        if (iXOffsetToProgress < 0) {
            iXOffsetToProgress = 0;
        } else {
            int i2 = this.endProgress;
            if (iXOffsetToProgress > i2 - 2000) {
                iXOffsetToProgress = i2 - 2000;
            }
        }
        if (iXOffsetToProgress != this.startProgress) {
            this.startProgress = iXOffsetToProgress;
            if (this.progress < iXOffsetToProgress) {
                this.progress = iXOffsetToProgress;
                z = true;
            }
            OnTimeLineListener onTimeLineListener = this.onTimeLineListener;
            if (onTimeLineListener != null) {
                onTimeLineListener.onProgressChanged(iXOffsetToProgress, 1, z ? 3 : -1);
            }
            invalidate();
        }
    }

    private void moveProgressLine(int i) {
        int iXOffsetToProgress = this.progress + xOffsetToProgress(i);
        int i2 = this.startProgress;
        if (iXOffsetToProgress < i2 || iXOffsetToProgress > (i2 = this.endProgress)) {
            iXOffsetToProgress = i2;
        }
        if (iXOffsetToProgress != this.progress) {
            this.progress = iXOffsetToProgress;
            checkProgressOutOfBound();
            OnTimeLineListener onTimeLineListener = this.onTimeLineListener;
            if (onTimeLineListener != null) {
                onTimeLineListener.onProgressChanged(this.progress, 3, -1);
            }
            invalidate();
        }
    }

    private void moveRightSlider(int i) {
        int iXOffsetToProgress = this.endProgress + xOffsetToProgress(i);
        int i2 = this.max;
        if (iXOffsetToProgress > i2) {
            iXOffsetToProgress = i2;
        } else {
            int i3 = this.startProgress;
            if (iXOffsetToProgress < i3 + 2000) {
                iXOffsetToProgress = i3 + 2000;
            }
        }
        if (iXOffsetToProgress != this.endProgress) {
            this.endProgress = iXOffsetToProgress;
            boolean z = false;
            if (this.progress > iXOffsetToProgress) {
                this.progress = iXOffsetToProgress;
                z = true;
            }
            OnTimeLineListener onTimeLineListener = this.onTimeLineListener;
            if (onTimeLineListener != null) {
                onTimeLineListener.onProgressChanged(iXOffsetToProgress, 2, z ? 3 : -1);
            }
            invalidate();
        }
    }

    private int progressToXOffset(int i) {
        return ((i * 5) * this.scaleLineSpacing) / 1000;
    }

    private int xOffsetToProgress(int i) {
        return (i * 1000) / (this.scaleLineSpacing * 5);
    }

    public void addVideoFrame(Bitmap bitmap) {
        ImageView imageView = new ImageView(this.context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.frameItemWidth, this.frameContainerHeight);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.frameContainer.addView(imageView, layoutParams);
        imageView.setImageBitmap(bitmap);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.max <= 0) {
            super.dispatchDraw(canvas);
            return;
        }
        canvas.translate(this.canvasOffsetX, 0.0f);
        if (this.clipEditMode) {
            this.paint.setColor(this.colorSliderBg);
            this.paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(this.sliderWidth + progressToXOffset(this.startProgress), this.sliderTop, this.sliderWidth + progressToXOffset(this.endProgress), this.sliderTop + this.frameContainerHeight + (this.frameContainerVerticalBorderSize * 2), this.paint);
        }
        super.dispatchDraw(canvas);
        drawScalePlate(canvas);
        drawSlider(canvas);
        int progressXOffset = getProgressXOffset();
        this.paint.setStrokeWidth(this.progressLineWidth);
        this.paint.setColor(this.colorProgressLine);
        float f = progressXOffset;
        canvas.drawLine(f, 0.0f, f, this.viewShowHeight, this.paint);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0053  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = r7.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 1
            if (r0 == 0) goto L80
            if (r0 == r1) goto L53
            r2 = 3
            r3 = 2
            if (r0 == r3) goto L13
            if (r0 == r2) goto L53
            goto Lbd
        L13:
            float r0 = r7.getX()
            int r0 = (int) r0
            int r4 = r6.startX
            int r4 = r0 - r4
            int r5 = r6.dragType
            if (r5 == r1) goto L4d
            if (r5 == r3) goto L49
            if (r5 == r2) goto L45
            int r2 = r6.viewTotalWidth
            int r3 = r6.viewShowWidth
            if (r2 > r3) goto L2b
            return r1
        L2b:
            android.view.VelocityTracker r2 = r6.tracker
            if (r2 == 0) goto L41
            r2.addMovement(r7)
            android.view.VelocityTracker r7 = r6.tracker
            r2 = 1000(0x3e8, float:1.401E-42)
            r7.computeCurrentVelocity(r2)
            android.view.VelocityTracker r7 = r6.tracker
            float r7 = r7.getXVelocity()
            r6.xVelocity = r7
        L41:
            r6.moveContainer(r4)
            goto L50
        L45:
            r6.moveProgressLine(r4)
            goto L50
        L49:
            r6.moveRightSlider(r4)
            goto L50
        L4d:
            r6.moveLeftSlider(r4)
        L50:
            r6.startX = r0
            goto Lbd
        L53:
            com.byd.view.VideoEditTimeLineController$OnTimeLineListener r7 = r6.onTimeLineListener
            if (r7 == 0) goto L5c
            int r0 = r6.dragType
            r7.onDraggingEnd(r0)
        L5c:
            r6.clearVelocity()
            int r7 = r6.dragType
            if (r7 != 0) goto L7c
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "xVelocity : "
            r7.append(r0)
            float r0 = r6.xVelocity
            r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.log(r7)
            r6.checkAutoScroll()
        L7c:
            r7 = 0
            r6.dragType = r7
            goto Lbd
        L80:
            float r0 = r7.getX()
            int r0 = (int) r0
            r6.startX = r0
            float r2 = r7.getY()
            int r2 = (int) r2
            int r0 = r6.calcDragType(r0, r2)
            r6.dragType = r0
            android.animation.ValueAnimator r0 = r6.autoScrollAnim
            if (r0 == 0) goto La1
            boolean r0 = r0.isRunning()
            if (r0 == 0) goto La1
            android.animation.ValueAnimator r0 = r6.autoScrollAnim
            r0.cancel()
        La1:
            int r0 = r6.dragType
            if (r0 != 0) goto Lb4
            android.view.VelocityTracker r0 = r6.tracker
            if (r0 != 0) goto Laf
            android.view.VelocityTracker r0 = android.view.VelocityTracker.obtain()
            r6.tracker = r0
        Laf:
            android.view.VelocityTracker r0 = r6.tracker
            r0.addMovement(r7)
        Lb4:
            com.byd.view.VideoEditTimeLineController$OnTimeLineListener r7 = r6.onTimeLineListener
            if (r7 == 0) goto Lbd
            int r0 = r6.dragType
            r7.onDraggingStart(r0)
        Lbd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.VideoEditTimeLineController.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public int getEndProgress() {
        return this.endProgress;
    }

    public int getFrameContainerTopMargin() {
        return this.frameContainerTopMargin;
    }

    public int getMax() {
        return this.max;
    }

    public int getProgress() {
        return this.progress;
    }

    public int getScaleLineHeight() {
        return this.scaleLineHeight;
    }

    public int getScaleTextHeight() {
        return this.scaleTextHeight;
    }

    public int getSliderWidth() {
        return this.sliderWidth;
    }

    public int getStartProgress() {
        return this.startProgress;
    }

    public boolean isClipEditMode() {
        return this.clipEditMode;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.max <= 0) {
            return;
        }
        int i5 = this.sliderWidth;
        int i6 = this.scaleLineHeight + this.scaleTextHeight + this.frameContainerTopMargin + this.frameContainerVerticalBorderSize;
        this.frameContainer.layout(i5, i6, this.frameContainerWidth + i5, this.frameContainerHeight + i6);
        log("onLayout " + i5 + " " + (i5 + this.frameContainerWidth) + " " + this.viewTotalWidth + " " + (this.viewTotalWidth - this.sliderWidth));
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.max;
        if (i3 <= 0) {
            log("onMeasure max : " + this.max);
            super.onMeasure(i, i2);
            return;
        }
        int i4 = ((i3 * 5) * this.scaleLineSpacing) / 1000;
        this.frameContainerWidth = i4;
        int i5 = i4 + (this.sliderWidth * 2);
        this.viewTotalWidth = i5;
        this.viewTotalWidth = i5 + 100;
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            int i6 = (this.frameItemWidth * 3) / 4;
            this.frameContainerHeight = i6;
            this.viewShowHeight = i6 + this.scaleLineHeight + this.scaleTextHeight + this.frameContainerTopMargin + (this.frameContainerVerticalBorderSize * 2);
        } else {
            int iResolveSize = ViewGroup.resolveSize(0, i2);
            this.viewShowHeight = iResolveSize;
            this.frameContainerHeight = (((iResolveSize - this.scaleLineHeight) - this.scaleTextHeight) - this.frameContainerTopMargin) - (this.frameContainerVerticalBorderSize * 2);
        }
        measureChild(this.frameContainer, View.MeasureSpec.makeMeasureSpec(this.frameContainerWidth, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(this.frameContainerHeight, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
        int iResolveSize2 = ViewGroup.resolveSize(0, i);
        this.viewShowWidth = iResolveSize2;
        setMeasuredDimension(iResolveSize2, this.viewShowHeight);
    }

    public void setClipEditMode(boolean z) {
        if (this.clipEditMode == z) {
            return;
        }
        this.clipEditMode = z;
        invalidate();
    }

    public void setEndProgress(int i) {
        this.endProgress = i;
    }

    public void setFrameContainerTopMargin(int i) {
        this.frameContainerTopMargin = i;
    }

    public void setMax(int i) {
        this.max = i;
        this.startProgress = 0;
        this.endProgress = i;
        requestLayout();
    }

    public void setOnTimeLineListener(OnTimeLineListener onTimeLineListener) {
        this.onTimeLineListener = onTimeLineListener;
    }

    public void setProgress(int i) {
        int i2 = this.max;
        if (i > i2) {
            i = i2;
        }
        if (this.progress == i) {
            return;
        }
        this.progress = i;
        checkProgressOutOfBound();
        invalidate();
    }

    public void setScaleLineHeight(int i) {
        this.scaleLineHeight = i;
    }

    public void setScaleTextHeight(int i) {
        this.scaleTextHeight = i;
    }

    public void setSliderWidth(int i) {
        this.sliderWidth = i;
    }

    public void setStartProgress(int i) {
        this.startProgress = i;
    }

    public void showVideoFrames(List<Bitmap> list) {
        Iterator<Bitmap> it = list.iterator();
        while (it.hasNext()) {
            addVideoFrame(it.next());
        }
    }
}
