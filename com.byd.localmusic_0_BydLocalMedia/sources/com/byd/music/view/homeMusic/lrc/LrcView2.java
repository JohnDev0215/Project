package com.byd.music.view.homeMusic.lrc;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Looper;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;
import com.byd.music.R;
import com.byd.music.utils.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LrcView2 extends View {
    private static final long ADJUST_DURATION = 100;
    private static final String TAG = "LrcView";
    private static final long TIMELINE_KEEP_TIME = 4000;
    private float height;
    private Runnable hideTimelineRunnable;
    private boolean isFling;
    private boolean isShowTimeline;
    private boolean isTouching;
    private long mAnimationDuration;
    private ValueAnimator mAnimator;
    private int mCurrentLine;
    private int mCurrentTextColor;
    private float mCurrentTextSize;
    private String mDefaultLabel;
    private float mDividerHeight;
    private int mDrawableWidth;
    private Object mFlag;
    private GestureDetector mGestureDetector;
    private List<LrcEntry> mLrcEntryList;
    private float mLrcPadding;
    private TextPaint mLrcPaint;
    private int mNormalTextColor;
    private float mNormalTextSize;
    private float mOffset;
    private OnPlayClickListener mOnPlayClickListener;
    private OnTapListener mOnTapListener;
    private Drawable mPlayDrawable;
    private Scroller mScroller;
    private boolean mShowTopLrc;
    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener;
    private boolean mSingleLine;
    private int mTextGravity;
    private Paint.FontMetrics mTimeFontMetrics;
    private TextPaint mTimePaint;
    private int mTimeTextColor;
    private int mTimeTextWidth;
    private int mTimelineColor;
    private int mTimelineTextColor;
    private float mUpperCenter;
    private float width;

    public interface OnPlayClickListener {
        boolean onPlayClick(LrcView2 lrcView2, long j);
    }

    public interface OnTapListener {
        void onTap(LrcView2 lrcView2, float f, float f2);
    }

    static /* synthetic */ float access$1016(LrcView2 lrcView2, float f) {
        float f2 = lrcView2.mOffset + f;
        lrcView2.mOffset = f2;
        return f2;
    }

    public LrcView2(Context context) {
        this(context, null);
    }

    public LrcView2(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LrcView2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLrcEntryList = new ArrayList();
        this.mLrcPaint = new TextPaint();
        this.mTimePaint = new TextPaint();
        this.mSimpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.5
            private boolean isTouchForStopFling = false;

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                if (LrcView2.this.hasLrc()) {
                    LrcView2.this.isTouching = true;
                    LrcView2 lrcView2 = LrcView2.this;
                    lrcView2.removeCallbacks(lrcView2.hideTimelineRunnable);
                    if (LrcView2.this.isFling) {
                        this.isTouchForStopFling = true;
                        LrcView2.this.mScroller.forceFinished(true);
                    } else {
                        this.isTouchForStopFling = false;
                    }
                    return (LrcView2.this.mOnPlayClickListener == null && LrcView2.this.mOnTapListener == null) ? false : true;
                }
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (LrcView2.this.hasLrc() && LrcView2.this.mOnPlayClickListener != null) {
                    if (!LrcView2.this.isShowTimeline) {
                        LrcView2.this.isShowTimeline = true;
                    } else {
                        LrcView2.access$1016(LrcView2.this, -f2);
                        LrcView2 lrcView2 = LrcView2.this;
                        lrcView2.mOffset = Math.min(lrcView2.mOffset, LrcView2.this.getOffset(0));
                        LrcView2 lrcView22 = LrcView2.this;
                        float f3 = lrcView22.mOffset;
                        LrcView2 lrcView23 = LrcView2.this;
                        lrcView22.mOffset = Math.max(f3, lrcView23.getOffset(lrcView23.mLrcEntryList.size() - 1));
                    }
                    LrcView2.this.invalidate();
                    return true;
                }
                return super.onScroll(motionEvent, motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (LrcView2.this.hasLrc() && LrcView2.this.mOnPlayClickListener != null) {
                    if (LrcView2.this.isShowTimeline) {
                        LrcView2.this.isFling = true;
                        LrcView2 lrcView2 = LrcView2.this;
                        lrcView2.removeCallbacks(lrcView2.hideTimelineRunnable);
                        LrcView2 lrcView22 = LrcView2.this;
                        LrcView2.this.mScroller.fling(0, (int) LrcView2.this.mOffset, 0, (int) f2, 0, 0, (int) lrcView22.getOffset(lrcView22.mLrcEntryList.size() - 1), (int) LrcView2.this.getOffset(0));
                        return true;
                    }
                    return super.onFling(motionEvent, motionEvent2, f, f2);
                }
                return super.onFling(motionEvent, motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (LrcView2.this.hasLrc()) {
                    if (LrcView2.this.mOnPlayClickListener == null || !LrcView2.this.isShowTimeline || !LrcView2.this.mPlayDrawable.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                        if (LrcView2.this.mOnTapListener != null && !this.isTouchForStopFling) {
                            LrcView2.this.mOnTapListener.onTap(LrcView2.this, motionEvent.getX(), motionEvent.getY());
                        }
                    } else {
                        int centerLine = LrcView2.this.getCenterLine();
                        long time = ((LrcEntry) LrcView2.this.mLrcEntryList.get(centerLine)).getTime();
                        if (LrcView2.this.mOnPlayClickListener != null && LrcView2.this.mOnPlayClickListener.onPlayClick(LrcView2.this, time)) {
                            LrcView2.this.isShowTimeline = false;
                            LrcView2 lrcView2 = LrcView2.this;
                            lrcView2.removeCallbacks(lrcView2.hideTimelineRunnable);
                            LrcView2.this.mCurrentLine = centerLine;
                            LrcView2.this.invalidate();
                            return true;
                        }
                    }
                    return super.onSingleTapConfirmed(motionEvent);
                }
                return super.onSingleTapConfirmed(motionEvent);
            }
        };
        this.hideTimelineRunnable = new Runnable() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.6
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.d(LrcView2.TAG, "hideTimelineRunnable run");
                if (LrcView2.this.hasLrc() && LrcView2.this.isShowTimeline) {
                    LrcView2.this.isShowTimeline = false;
                    LrcView2 lrcView2 = LrcView2.this;
                    lrcView2.smoothScrollTo(lrcView2.mCurrentLine);
                }
            }
        };
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.LrcView);
        this.mCurrentTextSize = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcTextSize, getResources().getDimension(R.dimen.lrc_text_size));
        float dimension = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcNormalTextSize, getResources().getDimension(R.dimen.lrc_text_size));
        this.mNormalTextSize = dimension;
        if (dimension == 0.0f) {
            this.mNormalTextSize = this.mCurrentTextSize;
        }
        this.mDividerHeight = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcDividerHeight, getResources().getDimension(R.dimen.lrc_divider_height));
        this.mUpperCenter = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcUpperCenter, 0.0f);
        int integer = getResources().getInteger(R.integer.lrc_animation_duration);
        long j = typedArrayObtainStyledAttributes.getInt(R.styleable.LrcView_lrcAnimationDuration, integer);
        this.mAnimationDuration = j;
        if (j < 0) {
            j = integer;
        }
        this.mAnimationDuration = j;
        this.mNormalTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcNormalTextColor, getResources().getColor(R.color.lrc_normal_text));
        this.mCurrentTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcCurrentTextColor, getResources().getColor(R.color.lrc_current_text));
        this.mTimelineTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcTimelineTextColor, getResources().getColor(R.color.lrc_timeline_text));
        this.mSingleLine = typedArrayObtainStyledAttributes.getBoolean(R.styleable.LrcView_LrcSingleLine, false);
        this.mShowTopLrc = typedArrayObtainStyledAttributes.getBoolean(R.styleable.LrcView_lrcShowTopLrc, true);
        this.mDefaultLabel = typedArrayObtainStyledAttributes.getString(R.styleable.LrcView_lrcLabel);
        this.mLrcPadding = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcPadding, 0.0f);
        this.mTimelineColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcTimelineColor, getResources().getColor(R.color.lrc_timeline_color));
        float dimension2 = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcTimelineHeight, getResources().getDimension(R.dimen.lrc_timeline_height));
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.LrcView_lrcPlayDrawable);
        this.mPlayDrawable = drawable;
        if (drawable == null) {
            drawable = getResources().getDrawable(R.drawable.lrc_play);
        }
        this.mPlayDrawable = drawable;
        this.mTimeTextColor = typedArrayObtainStyledAttributes.getColor(R.styleable.LrcView_lrcTimeTextColor, getResources().getColor(R.color.lrc_timeline_color));
        float dimension3 = typedArrayObtainStyledAttributes.getDimension(R.styleable.LrcView_lrcTimeTextSize, getResources().getDimension(R.dimen.lrc_time_text_size));
        this.mTextGravity = typedArrayObtainStyledAttributes.getInteger(R.styleable.LrcView_lrcTextGravity, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.mDrawableWidth = (int) getResources().getDimension(R.dimen.lrc_drawable_width);
        this.mTimeTextWidth = (int) getResources().getDimension(R.dimen.lrc_time_width);
        this.mLrcPaint.setAntiAlias(true);
        this.mLrcPaint.setTextSize(this.mCurrentTextSize);
        if (this.mSingleLine) {
            this.mLrcPaint.setTextAlign(Paint.Align.LEFT);
        } else {
            this.mLrcPaint.setTextAlign(this.mShowTopLrc ? Paint.Align.LEFT : Paint.Align.CENTER);
        }
        this.mTimePaint.setAntiAlias(true);
        this.mTimePaint.setTextSize(dimension3);
        this.mTimePaint.setTextAlign(Paint.Align.CENTER);
        this.mTimePaint.setStrokeWidth(dimension2);
        this.mTimePaint.setStrokeCap(Paint.Cap.ROUND);
        this.mTimeFontMetrics = this.mTimePaint.getFontMetrics();
        GestureDetector gestureDetector = new GestureDetector(getContext(), this.mSimpleOnGestureListener);
        this.mGestureDetector = gestureDetector;
        gestureDetector.setIsLongpressEnabled(false);
        this.mScroller = new Scroller(getContext());
    }

    public void setNormalColor(int i) {
        this.mNormalTextColor = i;
        postInvalidate();
    }

    public void setNormalTextSize(float f) {
        this.mNormalTextSize = f;
    }

    public void setCurrentTextSize(float f) {
        this.mCurrentTextSize = f;
    }

    public void setCurrentColor(int i) {
        this.mCurrentTextColor = i;
        postInvalidate();
    }

    public void setTimelineTextColor(int i) {
        this.mTimelineTextColor = i;
        postInvalidate();
    }

    public void setTimelineColor(int i) {
        this.mTimelineColor = i;
        postInvalidate();
    }

    public void setTimeTextColor(int i) {
        this.mTimeTextColor = i;
        postInvalidate();
    }

    public void setDraggable(boolean z, OnPlayClickListener onPlayClickListener) {
        if (!z) {
            this.mOnPlayClickListener = null;
        } else {
            if (onPlayClickListener == null) {
                throw new IllegalArgumentException("if draggable == true, onPlayClickListener must not be null");
            }
            this.mOnPlayClickListener = onPlayClickListener;
        }
    }

    @Deprecated
    public void setOnPlayClickListener(OnPlayClickListener onPlayClickListener) {
        this.mOnPlayClickListener = onPlayClickListener;
    }

    public void setOnTapListener(OnTapListener onTapListener) {
        this.mOnTapListener = onTapListener;
    }

    public void setLabel(final String str) {
        runOnUi(new Runnable() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.1
            @Override // java.lang.Runnable
            public void run() {
                LrcView2.this.mDefaultLabel = str;
                LrcView2.this.invalidate();
            }
        });
    }

    public void loadLrc(File file) {
        loadLrc(file, (File) null);
    }

    public void loadLrc(final File file, final File file2) {
        runOnUi(new Runnable() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.2
            @Override // java.lang.Runnable
            public void run() {
                LrcView2.this.reset();
                StringBuilder sb = new StringBuilder("file://");
                sb.append(file.getPath());
                if (file2 != null) {
                    sb.append("#");
                    sb.append(file2.getPath());
                }
                final String string = sb.toString();
                LrcView2.this.setFlag(string);
                new AsyncTask<File, Integer, List<LrcEntry>>() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.2.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public List<LrcEntry> doInBackground(File... fileArr) {
                        return LrcUtils.parseLrc(fileArr);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(List<LrcEntry> list) {
                        if (LrcView2.this.getFlag() == string) {
                            LrcView2.this.onLrcLoaded(list);
                            LrcView2.this.setFlag(null);
                        }
                    }
                }.execute(file, file2);
            }
        });
    }

    public void loadLrc(String str) {
        loadLrc(str, (String) null);
    }

    public void loadLrc(final String str, final String str2) {
        runOnUi(new Runnable() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.3
            @Override // java.lang.Runnable
            public void run() {
                LrcView2.this.reset();
                StringBuilder sb = new StringBuilder("file://");
                sb.append(str);
                if (str2 != null) {
                    sb.append("#");
                    sb.append(str2);
                }
                final String string = sb.toString();
                LrcView2.this.setFlag(string);
                new AsyncTask<String, Integer, List<LrcEntry>>() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.3.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public List<LrcEntry> doInBackground(String... strArr) {
                        return LrcUtils.parseLrc(strArr);
                    }

                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // android.os.AsyncTask
                    public void onPostExecute(List<LrcEntry> list) {
                        if (LrcView2.this.getFlag() == string) {
                            LrcView2.this.onLrcLoaded(list);
                            LrcView2.this.setFlag(null);
                        }
                    }
                }.execute(str, str2);
            }
        });
    }

    public void loadLrcByUrl(String str) {
        loadLrcByUrl(str, "utf-8");
    }

    public void loadLrcByUrl(String str, String str2) {
        final String str3 = "url://" + str;
        setFlag(str3);
        new AsyncTask<String, Integer, String>() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(String... strArr) {
                return LrcUtils.getContentFromNetwork(strArr[0], strArr[1]);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str4) {
                if (LrcView2.this.getFlag() == str3) {
                    LrcView2.this.loadLrc(str4);
                }
            }
        }.execute(str, str2);
    }

    public boolean hasLrc() {
        return !this.mLrcEntryList.isEmpty();
    }

    public void updateTime(final long j) {
        runOnUi(new Runnable() { // from class: com.byd.music.view.homeMusic.lrc.-$$Lambda$LrcView2$4m4lfy_Zc4jgBxnxqWMbMoRGi8Q
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateTime$0$LrcView2(j);
            }
        });
    }

    public /* synthetic */ void lambda$updateTime$0$LrcView2(long j) {
        int iFindShowLine;
        if (hasLrc() && (iFindShowLine = findShowLine(j)) != this.mCurrentLine) {
            this.mCurrentLine = iFindShowLine;
            if (!this.isShowTimeline) {
                smoothScrollTo(iFindShowLine);
            } else {
                invalidate();
            }
        }
    }

    @Deprecated
    public void onDrag(long j) {
        updateTime(j);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            initPlayDrawable();
            initEntryList();
            if (hasLrc()) {
                smoothScrollTo(this.mCurrentLine, 0L);
            }
        }
        this.width = (getWidth() - getPaddingStart()) - getPaddingEnd();
        this.height = (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerLine = getCenterLine();
        float height = 0.0f;
        if (!this.mShowTopLrc) {
            try {
                float height2 = this.height / 2.0f;
                this.mLrcPaint.setTextSize(this.mCurrentTextSize);
                this.mLrcPaint.setColor(this.mCurrentTextColor);
                if (this.mSingleLine) {
                    canvas.drawText(this.mLrcEntryList.get(centerLine).getText(), 0.0f, (this.height / 3.0f) * 2.0f, this.mLrcPaint);
                } else {
                    canvas.drawText(this.mLrcEntryList.get(centerLine).getText(), this.width / 2.0f, this.height / 2.0f, this.mLrcPaint);
                }
                if (this.mSingleLine) {
                    return;
                }
                this.mLrcPaint.setTextSize(this.mNormalTextSize);
                this.mLrcPaint.setColor(this.mNormalTextColor);
                while (centerLine < this.mLrcEntryList.size() && height2 <= this.height) {
                    height2 = height2 + this.mLrcEntryList.get(centerLine).getHeight() + this.mDividerHeight;
                    canvas.drawText(this.mLrcEntryList.get(centerLine).getText(), this.width / 2.0f, height2, this.mLrcPaint);
                    centerLine++;
                }
                return;
            } catch (Exception unused) {
                if (this.mSingleLine) {
                    return;
                }
                canvas.drawText("...木有歌词文件，赶紧去下载...", this.width / 2.0f, this.height / 2.0f, this.mLrcPaint);
                return;
            }
        }
        int height3 = getHeight() / 2;
        if (!hasLrc()) {
            this.mLrcPaint.setColor(this.mCurrentTextColor);
            drawText(canvas, new StaticLayout(this.mDefaultLabel, this.mLrcPaint, (int) getLrcWidth(), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false), height3);
            return;
        }
        if (this.isShowTimeline) {
            this.mPlayDrawable.draw(canvas);
            this.mTimePaint.setColor(this.mTimelineColor);
            float f = height3;
            canvas.drawLine(this.mTimeTextWidth, f, getWidth() - this.mTimeTextWidth, f, this.mTimePaint);
            this.mTimePaint.setColor(this.mTimeTextColor);
            canvas.drawText(LrcUtils.formatTime(this.mLrcEntryList.get(centerLine).getTime()), getWidth() - (this.mTimeTextWidth / 2), f - ((this.mTimeFontMetrics.descent + this.mTimeFontMetrics.ascent) / 2.0f), this.mTimePaint);
        }
        canvas.translate(0.0f, this.mOffset);
        for (int i = 0; i < this.mLrcEntryList.size(); i++) {
            if (i > 0) {
                height += ((this.mLrcEntryList.get(i - 1).getHeight() + this.mLrcEntryList.get(i).getHeight()) >> 1) + this.mDividerHeight;
            }
            if (i == this.mCurrentLine) {
                this.mLrcPaint.setTextSize(this.mCurrentTextSize);
                this.mLrcPaint.setColor(this.mCurrentTextColor);
            } else if (this.isShowTimeline && i == centerLine) {
                this.mLrcPaint.setColor(this.mTimelineTextColor);
            } else {
                this.mLrcPaint.setTextSize(this.mNormalTextSize);
                this.mLrcPaint.setColor(this.mNormalTextColor);
            }
            drawText(canvas, this.mLrcEntryList.get(i).getStaticLayout(), height);
        }
    }

    private void drawText(Canvas canvas, StaticLayout staticLayout, float f) {
        canvas.save();
        canvas.translate(this.mLrcPadding, f - (staticLayout.getHeight() >> 1));
        staticLayout.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.isTouching = false;
            if (hasLrc() && this.isShowTimeline) {
                adjustCenter();
                postDelayed(this.hideTimelineRunnable, TIMELINE_KEEP_TIME);
            }
        }
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            this.mOffset = this.mScroller.getCurrY();
            invalidate();
        }
        if (this.isFling && this.mScroller.isFinished()) {
            LogUtil.d(TAG, "fling finish");
            this.isFling = false;
            if (!hasLrc() || this.isTouching) {
                return;
            }
            adjustCenter();
            postDelayed(this.hideTimelineRunnable, TIMELINE_KEEP_TIME);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        removeCallbacks(this.hideTimelineRunnable);
        super.onDetachedFromWindow();
    }

    public void onLrcLoaded(List<LrcEntry> list) {
        this.mLrcEntryList.clear();
        if (list != null && !list.isEmpty()) {
            this.mLrcEntryList.addAll(list);
        }
        Collections.sort(this.mLrcEntryList);
        initEntryList();
        invalidate();
    }

    private void initPlayDrawable() {
        int i = (this.mTimeTextWidth - this.mDrawableWidth) / 2;
        int height = getHeight() / 2;
        int i2 = this.mDrawableWidth;
        int i3 = height - (i2 / 2);
        this.mPlayDrawable.setBounds(i, i3, i + i2, i2 + i3);
    }

    private void initEntryList() {
        if (!hasLrc() || getWidth() == 0) {
            return;
        }
        Iterator<LrcEntry> it = this.mLrcEntryList.iterator();
        while (it.hasNext()) {
            it.next().init(this.mLrcPaint, (int) getLrcWidth(), this.mTextGravity);
        }
        this.mOffset = getHeight() / 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reset() {
        endAnimation();
        this.mScroller.forceFinished(true);
        this.isShowTimeline = false;
        this.isTouching = false;
        this.isFling = false;
        removeCallbacks(this.hideTimelineRunnable);
        this.mLrcEntryList.clear();
        this.mOffset = 0.0f;
        this.mCurrentLine = 0;
        invalidate();
    }

    private void adjustCenter() {
        smoothScrollTo(getCenterLine(), ADJUST_DURATION);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void smoothScrollTo(int i) {
        smoothScrollTo(i, this.mAnimationDuration);
    }

    private void smoothScrollTo(int i, long j) {
        float offset = getOffset(i);
        endAnimation();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.mOffset, offset);
        this.mAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(j);
        this.mAnimator.setInterpolator(new LinearInterpolator());
        this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.byd.music.view.homeMusic.lrc.LrcView2.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                LrcView2.this.mOffset = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LrcView2.this.invalidate();
            }
        });
        LrcUtils.resetDurationScale();
        this.mAnimator.start();
    }

    private void endAnimation() {
        ValueAnimator valueAnimator = this.mAnimator;
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return;
        }
        this.mAnimator.end();
    }

    private int findShowLine(long j) {
        int size = this.mLrcEntryList.size();
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            if (j < this.mLrcEntryList.get(i2).getTime()) {
                size = i2 - 1;
            } else {
                i = i2 + 1;
                if (i >= this.mLrcEntryList.size() || j < this.mLrcEntryList.get(i).getTime()) {
                    return i2;
                }
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getCenterLine() {
        float fAbs = Float.MAX_VALUE;
        int i = 0;
        for (int i2 = 0; i2 < this.mLrcEntryList.size(); i2++) {
            if (Math.abs(this.mOffset - getOffset(i2)) < fAbs) {
                fAbs = Math.abs(this.mOffset - getOffset(i2));
                i = i2;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getOffset(int i) {
        if (this.mLrcEntryList.get(i).getOffset() == Float.MIN_VALUE) {
            float height = getHeight() / 2;
            for (int i2 = 1; i2 <= i; i2++) {
                height -= ((this.mLrcEntryList.get(i2 - 1).getHeight() + this.mLrcEntryList.get(i2).getHeight()) >> 1) + this.mDividerHeight;
            }
            this.mLrcEntryList.get(i).setOffset(height);
        }
        return this.mLrcEntryList.get(i).getOffset();
    }

    private float getLrcWidth() {
        return getWidth() - (this.mLrcPadding * 2.0f);
    }

    private void runOnUi(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object getFlag() {
        return this.mFlag;
    }

    public void setFlag(Object obj) {
        this.mFlag = obj;
    }
}
