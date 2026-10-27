package com.byd.music.view.musicPlayingSlideView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.byd.music.R;
import com.byd.music.view.musicPlayingSlideView.IndicatorController;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class MusicPlayingSlideView extends View {
    public static final int DEFAULT_HEIGHT = 15;
    public static final int DEFAULT_WIDTH = 20;
    float four;
    private boolean isPlaying;
    private boolean mHasAnimation;
    int mIndicatorColor;
    IndicatorController mIndicatorController;
    Paint mPaint;
    float one;
    float[] scaleYFloats;
    float three;
    float two;

    public MusicPlayingSlideView(Context context) {
        super(context);
        this.scaleYFloats = new float[4];
        init(null);
    }

    public MusicPlayingSlideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.scaleYFloats = new float[4];
        init(attributeSet);
    }

    public MusicPlayingSlideView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.scaleYFloats = new float[4];
        init(attributeSet);
    }

    public MusicPlayingSlideView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.scaleYFloats = new float[4];
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MusicIndicatorView);
        this.mIndicatorColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MusicIndicatorView_music_indicator_color, getResources().getColor(R.color.text_color_selected));
        this.one = typedArrayObtainStyledAttributes.getFloat(R.styleable.MusicIndicatorView_music_indicator_one, 0.0f);
        this.two = typedArrayObtainStyledAttributes.getFloat(R.styleable.MusicIndicatorView_music_indicator_two, 2.0f);
        this.three = typedArrayObtainStyledAttributes.getFloat(R.styleable.MusicIndicatorView_music_indicator_three, 3.0f);
        this.four = typedArrayObtainStyledAttributes.getFloat(R.styleable.MusicIndicatorView_music_indicator_four, 2.5f);
        typedArrayObtainStyledAttributes.recycle();
        float[] fArr = this.scaleYFloats;
        fArr[0] = this.one;
        fArr[1] = this.two;
        fArr[2] = this.three;
        fArr[3] = this.four;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(this.mIndicatorColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        applyIndicator();
    }

    private void applyIndicator() {
        measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        this.mIndicatorController = new LineScaleWaveIndicator();
        bringToFront();
        this.mIndicatorController.setTarget(this);
        if (this.mHasAnimation) {
            return;
        }
        this.mHasAnimation = true;
        applyAnimation();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(measureDimension(dp2px(20), i), measureDimension(dp2px(15), i2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIndicator(canvas, this.scaleYFloats);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mHasAnimation && this.isPlaying) {
            this.mIndicatorController.setAnimationStatus(IndicatorController.AnimStatus.START);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIndicatorController.setAnimationStatus(IndicatorController.AnimStatus.CANCEL);
    }

    public void setPlaying(boolean z) {
        this.isPlaying = z;
    }

    void drawIndicator(Canvas canvas, float[] fArr) {
        this.mIndicatorController.draw(canvas, this.mPaint, fArr);
    }

    private int measureDimension(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        return mode == 1073741824 ? size : mode == Integer.MIN_VALUE ? Math.min(i, size) : i;
    }

    public void applyAnimation() {
        this.mIndicatorController.initAnimation(this.scaleYFloats);
    }

    public void cancel() {
        this.mIndicatorController.setAnimationStatus(IndicatorController.AnimStatus.CANCEL);
    }

    public void end() {
        this.mIndicatorController.setAnimationStatus(IndicatorController.AnimStatus.END);
    }

    public void start() {
        this.mIndicatorController.setAnimationStatus(IndicatorController.AnimStatus.START);
    }

    private int dp2px(int i) {
        return ((int) getContext().getResources().getDisplayMetrics().density) * i;
    }
}
