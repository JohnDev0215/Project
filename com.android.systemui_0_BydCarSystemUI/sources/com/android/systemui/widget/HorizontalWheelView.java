package com.android.systemui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.util.ViewSoundEffectUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalWheelView extends View implements GestureDetector.OnGestureListener {
    public static final float DEFAULT_MARK_RATIO = 0.7f;
    private boolean isNeedPlayWheelSoundEffect;
    private String mAdditionCenterMark;
    private float mBottomSpace;
    private CarController mCarController;
    private int mCenterIndex;
    private final Path mCenterIndicatorPath;
    private float mCenterTextSize;
    private RectF mContentRectF;
    private float mCursorSize;
    private boolean mFling;
    private GestureDetectorCompat mGestureDetectorCompat;
    private int mHeight;
    private int mHiHighlightColor;
    private int mHighlightColor;
    private float mIntervalDis;
    private float mIntervalFactor;
    private List<String> mItems;
    private int mLastSelectedIndex;
    private int mLoHighlightColor;
    private int mMarkCount;
    private float mMarkRatio;
    private int mMarkTextColor;
    private TextPaint mMarkTextPaint;
    private float mMaxOverScrollDistance;
    private int mMaxSelectableIndex;
    private int mMinSelectableIndex;
    private float mNormalTextSize;
    private OnWheelItemSelectedListener mOnWheelItemSelectedListener;
    private OverScroller mScroller;
    private String mTemp;
    private float mTempAccuracyType;
    private final float mTopSpace;
    private int mViewHeight;
    private int mViewScopeSize;
    private int mViewWidth;
    private PaintFlagsDrawFilter paintFlagsDrawFilter;

    public interface OnWheelItemSelectedListener {
        void onShowDialog();

        void onWheelItemChanged(HorizontalWheelView horizontalWheelView, int position);

        void onWheelItemSelected(HorizontalWheelView horizontalWheelView, int position);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(MotionEvent e) {
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(MotionEvent e) {
    }

    public HorizontalWheelView(Context context) {
        super(context);
        this.mCenterIndex = -1;
        this.mIntervalFactor = 1.2f;
        this.mTempAccuracyType = 1.0f;
        this.mMarkRatio = 0.7f;
        this.mCenterIndicatorPath = new Path();
        this.mFling = false;
        this.mTopSpace = 0.0f;
        this.mBottomSpace = 0.0f;
        this.mLastSelectedIndex = -1;
        this.mMinSelectableIndex = Integer.MIN_VALUE;
        this.mMaxSelectableIndex = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.isNeedPlayWheelSoundEffect = false;
        this.mTemp = "";
        init(null);
    }

    public HorizontalWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCenterIndex = -1;
        this.mIntervalFactor = 1.2f;
        this.mTempAccuracyType = 1.0f;
        this.mMarkRatio = 0.7f;
        this.mCenterIndicatorPath = new Path();
        this.mFling = false;
        this.mTopSpace = 0.0f;
        this.mBottomSpace = 0.0f;
        this.mLastSelectedIndex = -1;
        this.mMinSelectableIndex = Integer.MIN_VALUE;
        this.mMaxSelectableIndex = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.isNeedPlayWheelSoundEffect = false;
        this.mTemp = "";
        init(attrs);
    }

    public HorizontalWheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCenterIndex = -1;
        this.mIntervalFactor = 1.2f;
        this.mTempAccuracyType = 1.0f;
        this.mMarkRatio = 0.7f;
        this.mCenterIndicatorPath = new Path();
        this.mFling = false;
        this.mTopSpace = 0.0f;
        this.mBottomSpace = 0.0f;
        this.mLastSelectedIndex = -1;
        this.mMinSelectableIndex = Integer.MIN_VALUE;
        this.mMaxSelectableIndex = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        this.isNeedPlayWheelSoundEffect = false;
        this.mTemp = "";
        init(attrs);
    }

    protected final void init(AttributeSet attrs) {
        this.mCarController = (CarController) Dependency.get(CarController.class);
        float f = getResources().getDisplayMetrics().density;
        this.mHighlightColor = getContext().getColor(R.color.color_hvac_set_temp_pop_high_light_text);
        this.mMarkTextColor = getContext().getColor(R.color.color_hvac_set_temp_pop_normal_text);
        this.mHiHighlightColor = getContext().getColor(R.color.color_temp_hi);
        this.mLoHighlightColor = getContext().getColor(R.color.color_temp_lo);
        this.mCursorSize = getResources().getDimensionPixelSize(R.dimen.dp_18);
        this.mCenterTextSize = getResources().getDimensionPixelSize(R.dimen.dp_60);
        this.mNormalTextSize = getResources().getDimensionPixelSize(R.dimen.dp_32);
        this.mBottomSpace = getResources().getDimensionPixelSize(R.dimen.dp_24);
        this.paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
        TypedArray typedArrayObtainStyledAttributes = attrs == null ? null : getContext().obtainStyledAttributes(attrs, R.styleable.Horizontal_Wheel_View);
        if (typedArrayObtainStyledAttributes != null) {
            this.mHighlightColor = typedArrayObtainStyledAttributes.getColor(3, this.mHighlightColor);
            this.mMarkTextColor = typedArrayObtainStyledAttributes.getColor(7, this.mMarkTextColor);
            this.mIntervalFactor = typedArrayObtainStyledAttributes.getFloat(4, this.mIntervalFactor);
            this.mMarkRatio = typedArrayObtainStyledAttributes.getFloat(6, this.mMarkRatio);
            this.mAdditionCenterMark = typedArrayObtainStyledAttributes.getString(0);
            this.mCenterTextSize = typedArrayObtainStyledAttributes.getDimension(1, this.mCenterTextSize);
            this.mNormalTextSize = typedArrayObtainStyledAttributes.getDimension(8, this.mNormalTextSize);
            this.mCursorSize = typedArrayObtainStyledAttributes.getDimension(2, this.mCursorSize);
        }
        this.mIntervalFactor = Math.max(1.0f, this.mIntervalFactor);
        this.mMarkRatio = Math.min(1.0f, this.mMarkRatio);
        TextPaint textPaint = new TextPaint(1);
        this.mMarkTextPaint = textPaint;
        textPaint.setTextAlign(Paint.Align.CENTER);
        this.mMarkTextPaint.setColor(this.mHighlightColor);
        this.mMarkTextPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "font/HFMoBestSans-R.otf"));
        this.mMarkTextPaint.setTextSize(this.mCenterTextSize);
        calcIntervalDis();
        this.mScroller = new OverScroller(getContext());
        this.mContentRectF = new RectF();
        this.mGestureDetectorCompat = new GestureDetectorCompat(getContext(), this);
        BydLogHelper.logD("init", "selectIndex 2");
    }

    private void calcIntervalDis() {
        int iWidth;
        if (this.mMarkTextPaint == null) {
            return;
        }
        Rect rect = new Rect();
        List<String> list = this.mItems;
        if (list != null && list.size() > 0) {
            iWidth = 0;
            for (String str : this.mItems) {
                this.mMarkTextPaint.getTextBounds(str, 0, str.length(), rect);
                if (rect.width() > iWidth) {
                    iWidth = rect.width();
                }
            }
        } else {
            this.mMarkTextPaint.getTextBounds("888888", 0, 6, rect);
            iWidth = rect.width();
        }
        if (!TextUtils.isEmpty(this.mAdditionCenterMark)) {
            this.mMarkTextPaint.setTextSize(this.mNormalTextSize);
            TextPaint textPaint = this.mMarkTextPaint;
            String str2 = this.mAdditionCenterMark;
            textPaint.getTextBounds(str2, 0, str2.length(), rect);
            iWidth += rect.width();
        }
        this.mIntervalDis = iWidth * this.mIntervalFactor;
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.mViewWidth = measureWidth(widthMeasureSpec);
        int iMeasureHeight = measureHeight(heightMeasureSpec);
        this.mViewHeight = iMeasureHeight;
        setMeasuredDimension(this.mViewWidth, iMeasureHeight);
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? size : getSuggestedMinimumWidth();
    }

    private int measureHeight(int heightMeasure) {
        int mode = View.MeasureSpec.getMode(heightMeasure);
        int size = View.MeasureSpec.getSize(heightMeasure);
        int i = (int) (this.mBottomSpace + 0.0f + this.mCenterTextSize);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? i : Math.max(i, size);
        }
        return Math.min(i, size);
    }

    public void fling(int velocityX, int velocityY) {
        OverScroller overScroller = this.mScroller;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i = (int) ((-this.mMaxOverScrollDistance) + (this.mMinSelectableIndex * this.mIntervalDis));
        float fWidth = this.mContentRectF.width();
        float f = this.mMaxOverScrollDistance;
        overScroller.fling(scrollX, scrollY, velocityX, velocityY, i, (int) ((fWidth - f) - (((this.mMarkCount - 1) - this.mMaxSelectableIndex) * this.mIntervalDis)), 0, 0, (int) f, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    @Override // android.view.View
    protected void onSizeChanged(int wight, int high, int oldWight, int oldHigh) {
        super.onSizeChanged(wight, high, oldWight, oldHigh);
        BydLogHelper.logD("onSizeChanged", "wight : " + wight + " , high : " + high + " , oldWight : " + oldWight + " , oldHigh : " + oldHigh);
        if (wight == oldWight && high == oldHigh) {
            return;
        }
        this.mHeight = high;
        this.mMaxOverScrollDistance = wight / 2.0f;
        this.mContentRectF.set(0.0f, 0.0f, (this.mMarkCount - 1) * this.mIntervalDis, high);
        this.mViewScopeSize = (int) Math.ceil(this.mMaxOverScrollDistance / this.mIntervalDis);
        BydLogHelper.logD("onSizeChanged", "mViewScopeSize : " + this.mViewScopeSize);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(this.paintFlagsDrawFilter);
        this.mCenterIndicatorPath.reset();
        float f = this.mCursorSize;
        float f2 = f / 2.0f;
        float f3 = f / 3.0f;
        this.mCenterIndicatorPath.moveTo((this.mMaxOverScrollDistance - f2) + getScrollX(), 0.0f);
        this.mCenterIndicatorPath.rLineTo(0.0f, f3);
        this.mCenterIndicatorPath.rLineTo(f2, f2);
        this.mCenterIndicatorPath.rLineTo(f2, -f2);
        this.mCenterIndicatorPath.rLineTo(0.0f, -f3);
        this.mCenterIndicatorPath.close();
        BydLogHelper.logD("onDraw", "mCenterIndex : " + this.mCenterIndex + " , mMaxSelectableIndex : " + this.mMaxSelectableIndex);
        int i = this.mCenterIndex;
        int i2 = this.mViewScopeSize;
        int i3 = i - i2;
        int i4 = i + i2 + 1;
        BydLogHelper.logD("onDraw", "end1 : " + i4 + " , mMarkCount + mViewScopeSize * 2 : " + (this.mMarkCount + (this.mViewScopeSize * 2)));
        int iMax = Math.max(i3, (-this.mViewScopeSize) * 2);
        int iMin = Math.min(i4, this.mMarkCount + (this.mViewScopeSize * 2));
        BydLogHelper.logD("onDraw", "end2 : " + iMin);
        int i5 = this.mCenterIndex;
        if (i5 == this.mMaxSelectableIndex) {
            iMin += this.mViewScopeSize;
        } else if (i5 == this.mMinSelectableIndex) {
            iMax -= this.mViewScopeSize;
        }
        float f4 = iMax * this.mIntervalDis;
        BydLogHelper.logD("onDraw", "x : " + f4);
        BydLogHelper.logD("onDraw", "start : " + iMax);
        BydLogHelper.logD("onDraw", "end3 : " + iMin);
        float f5 = f4;
        while (iMax < iMin) {
            BydLogHelper.logD("onDraw", "mMarkCount : " + this.mMarkCount);
            int i6 = this.mMarkCount;
            if (i6 > 0 && iMax >= 0 && iMax < i6) {
                String str = this.mItems.get(iMax);
                if (this.mCenterIndex == iMax) {
                    this.mMarkTextPaint.setColor(this.mHighlightColor);
                    this.mMarkTextPaint.setTextSize(this.mCenterTextSize);
                    BydLogHelper.logD("onDraw", "temp : " + str + " , mTemp : " + this.mTemp + " , isNeedPlayWheelSoundEffect : " + this.isNeedPlayWheelSoundEffect);
                    if (!this.mTemp.equals(str)) {
                        this.mTemp = str;
                        BydLogHelper.logD("onDraw", "isNeedPlayWheelSoundEffect : " + this.isNeedPlayWheelSoundEffect);
                        if (this.isNeedPlayWheelSoundEffect) {
                            BydLogHelper.logD("onDraw", "playWheelSoundEffect");
                            ViewSoundEffectUtil.playWheelSoundEffect(this);
                        }
                    }
                    if (str.equals(getResources().getString(R.string.sys_ui_temp_hi))) {
                        this.mMarkTextPaint.setColor(this.mHiHighlightColor);
                    } else if (str.equals(getResources().getString(R.string.sys_ui_temp_low))) {
                        this.mMarkTextPaint.setColor(this.mLoHighlightColor);
                    } else {
                        this.mMarkTextPaint.setColor(this.mHighlightColor);
                    }
                    if (this.mTempAccuracyType == 2.0f) {
                        drawFloat(str, canvas, this.mMarkTextPaint, this.mCenterTextSize, f5, (this.mHeight - this.mBottomSpace) + 12.0f);
                    } else {
                        canvas.drawText(str, 0, str.length(), f5, (this.mHeight - this.mBottomSpace) + 4.0f, (Paint) this.mMarkTextPaint);
                    }
                } else {
                    this.mMarkTextPaint.setColor(this.mMarkTextColor);
                    this.mMarkTextPaint.setTextSize(this.mNormalTextSize);
                    if (this.mTempAccuracyType == 2.0f) {
                        drawFloat(str, canvas, this.mMarkTextPaint, this.mNormalTextSize, f5, (this.mHeight - this.mBottomSpace) + 4.0f);
                    } else {
                        canvas.drawText(str, 0, str.length(), f5, (this.mHeight - this.mBottomSpace) - 8.0f, (Paint) this.mMarkTextPaint);
                    }
                }
            }
            f5 += this.mIntervalDis;
            iMax++;
        }
    }

    private void drawFloat(String temp, Canvas canvas, Paint paint, float textSize, float x, float baseline) {
        BydLogHelper.logD("drawFloat temp = " + temp);
        if (temp.contains(Constants.CHAR_DOT)) {
            float fMeasureText = paint.measureText(temp, temp.indexOf(Constants.CHAR_DOT), temp.length());
            canvas.drawText(temp, 0, temp.indexOf(Constants.CHAR_DOT), x, baseline, paint);
            paint.setTextSize((textSize / 5.0f) * 3.0f);
            canvas.drawText(temp, temp.indexOf(Constants.CHAR_DOT), temp.length(), x + fMeasureText, baseline, paint);
            return;
        }
        canvas.drawText(temp, 0, temp.length(), x, baseline, paint);
    }

    public void setTempAccuracyType(int tempAccuracyType) {
        this.mTempAccuracyType = tempAccuracyType;
    }

    public void setIntervalFactor(float intervalFactor) {
        this.mIntervalFactor = intervalFactor;
    }

    public void setCenterTextSize(float centerTextSize) {
        this.mCenterTextSize = centerTextSize;
    }

    public void setNormalTextSize(float normalTextSize) {
        this.mNormalTextSize = normalTextSize;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        OnWheelItemSelectedListener onWheelItemSelectedListener;
        BydLogHelper.logD("showCarModelType : " + this.mCarController.getShowCarModelType());
        if (this.mCarController.getShowCarModelType() == 1 && (onWheelItemSelectedListener = this.mOnWheelItemSelectedListener) != null) {
            onWheelItemSelectedListener.onShowDialog();
        }
        List<String> list = this.mItems;
        if (list == null || list.size() == 0 || !isEnabled()) {
            return false;
        }
        boolean zOnTouchEvent = this.mGestureDetectorCompat.onTouchEvent(event);
        if (!this.mFling && 1 == event.getAction()) {
            autoSettle();
            zOnTouchEvent = true;
        }
        return zOnTouchEvent || super.onTouchEvent(event);
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        BydLogHelper.logD("computeScroll");
        if (this.mScroller.computeScrollOffset()) {
            scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
            refreshCenter();
            invalidate();
        } else if (this.mFling) {
            this.mFling = false;
            autoSettle();
        }
    }

    public void setAdditionCenterMark(String additionCenterMark) {
        this.mAdditionCenterMark = additionCenterMark;
        calcIntervalDis();
        invalidate();
    }

    private void autoSettle() {
        int scrollX = getScrollX();
        this.mScroller.startScroll(scrollX, 0, (int) (((this.mCenterIndex * this.mIntervalDis) - scrollX) - this.mMaxOverScrollDistance), 0);
        postInvalidate();
        BydLogHelper.logD("autoSettle mLastSelectedIndex : " + this.mLastSelectedIndex + " , mCenterIndex : " + this.mCenterIndex);
        int i = this.mCenterIndex;
        this.mLastSelectedIndex = i;
        OnWheelItemSelectedListener onWheelItemSelectedListener = this.mOnWheelItemSelectedListener;
        if (onWheelItemSelectedListener != null) {
            onWheelItemSelectedListener.onWheelItemSelected(this, i);
        }
    }

    private int safeCenter(int center) {
        int i = this.mMinSelectableIndex;
        return (center >= i && center <= (i = this.mMaxSelectableIndex)) ? center : i;
    }

    private void refreshCenter(int offsetX) {
        int iSafeCenter = safeCenter(Math.round(((int) (offsetX + this.mMaxOverScrollDistance)) / this.mIntervalDis));
        if (this.mCenterIndex == iSafeCenter) {
            BydLogHelper.logD("refreshCenter return");
        } else {
            this.mCenterIndex = iSafeCenter;
            notifyWheelItemChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCenter() {
        BydLogHelper.logD("refreshCenter");
        refreshCenter(getScrollX());
    }

    public void selectIndex(int index, final boolean isNotifyWheelItemSelected) {
        this.mCenterIndex = safeCenter(index);
        post(new Runnable() { // from class: com.android.systemui.widget.HorizontalWheelView.1
            @Override // java.lang.Runnable
            public void run() {
                HorizontalWheelView.this.scrollTo((int) ((r0.mCenterIndex * HorizontalWheelView.this.mIntervalDis) - HorizontalWheelView.this.mMaxOverScrollDistance), 0);
                HorizontalWheelView.this.invalidate();
                HorizontalWheelView.this.refreshCenter();
                if (isNotifyWheelItemSelected) {
                    HorizontalWheelView.this.notifyWheelItemSelected();
                    HorizontalWheelView.this.notifyWheelItemChanged();
                }
            }
        });
        BydLogHelper.logD("selectIndex", "index : " + index);
        BydLogHelper.logD("selectIndex", "mCenterIndex : " + this.mCenterIndex);
    }

    public int getCenterIndex() {
        return this.mCenterIndex;
    }

    public int getSize() {
        List<String> list = this.mItems;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWheelItemSelected() {
        OnWheelItemSelectedListener onWheelItemSelectedListener = this.mOnWheelItemSelectedListener;
        if (onWheelItemSelectedListener != null) {
            onWheelItemSelectedListener.onWheelItemSelected(this, this.mCenterIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyWheelItemChanged() {
        OnWheelItemSelectedListener onWheelItemSelectedListener = this.mOnWheelItemSelectedListener;
        if (onWheelItemSelectedListener != null) {
            onWheelItemSelectedListener.onWheelItemChanged(this, this.mCenterIndex);
        }
    }

    public void smoothSelectIndex(int index, boolean isNotifyWheelItemSelected) {
        int iSafeCenter = safeCenter(index);
        if (!this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        this.mScroller.startScroll(getScrollX(), 0, (int) ((iSafeCenter - this.mCenterIndex) * this.mIntervalDis), 0);
        invalidate();
        this.mCenterIndex = safeCenter(iSafeCenter);
        if (isNotifyWheelItemSelected) {
            notifyWheelItemSelected();
        }
    }

    public void setItems(List<String> items) {
        List<String> list = this.mItems;
        if (list == null) {
            this.mItems = new ArrayList();
        } else {
            list.clear();
        }
        this.mItems.addAll(items);
        this.mMarkTextPaint.reset();
        this.mMarkTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mMarkTextPaint.setColor(this.mHighlightColor);
        this.mMarkTextPaint.setTextSize(this.mCenterTextSize);
        List<String> list2 = this.mItems;
        int size = list2 == null ? 0 : list2.size();
        this.mMarkCount = size;
        if (size > 0) {
            this.mMinSelectableIndex = Math.max(this.mMinSelectableIndex, 0);
            this.mMaxSelectableIndex = Math.min(this.mMaxSelectableIndex, this.mMarkCount - 1);
        }
        this.mContentRectF.set(0.0f, 0.0f, (this.mMarkCount - 1) * this.mIntervalDis, getMeasuredHeight());
        this.mCenterIndex = Math.min(this.mCenterIndex, this.mMarkCount);
        BydLogHelper.logD("setItems", "mCenterIndex : " + this.mCenterIndex);
        calcIntervalDis();
        invalidate();
    }

    public int getSelectedPosition() {
        return this.mCenterIndex;
    }

    public void setOnWheelItemSelectedListener(OnWheelItemSelectedListener onWheelItemSelectedListener) {
        this.mOnWheelItemSelectedListener = onWheelItemSelectedListener;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(MotionEvent e) {
        if (!this.mScroller.isFinished()) {
            this.mScroller.forceFinished(false);
        }
        this.mFling = false;
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return true;
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(MotionEvent e) {
        playSoundEffect(0);
        refreshCenter((int) ((getScrollX() + e.getX()) - this.mMaxOverScrollDistance));
        autoSettle();
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0022  */
    @Override // android.view.GestureDetector.OnGestureListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onScroll(android.view.MotionEvent r5, android.view.MotionEvent r6, float r7, float r8) {
        /*
            r4 = this;
            int r5 = r4.getScrollX()
            float r5 = (float) r5
            int r6 = r4.mMinSelectableIndex
            float r8 = (float) r6
            float r0 = r4.mIntervalDis
            float r8 = r8 * r0
            float r1 = r4.mMaxOverScrollDistance
            r2 = 1073741824(0x40000000, float:2.0)
            float r2 = r2 * r1
            float r8 = r8 - r2
            int r8 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            r2 = 0
            r3 = 1
            if (r8 >= 0) goto L19
        L17:
            r7 = r2
            goto L53
        L19:
            float r6 = (float) r6
            float r6 = r6 * r0
            float r6 = r6 - r1
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            r8 = 1082130432(0x40800000, float:4.0)
            if (r6 >= 0) goto L24
        L22:
            float r7 = r7 / r8
            goto L53
        L24:
            android.graphics.RectF r6 = r4.mContentRectF
            float r6 = r6.width()
            int r0 = r4.mMarkCount
            int r1 = r4.mMaxSelectableIndex
            int r0 = r0 - r1
            int r0 = r0 - r3
            float r0 = (float) r0
            float r1 = r4.mIntervalDis
            float r0 = r0 * r1
            float r6 = r6 - r0
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r6 <= 0) goto L3a
            goto L17
        L3a:
            android.graphics.RectF r6 = r4.mContentRectF
            float r6 = r6.width()
            int r0 = r4.mMarkCount
            int r1 = r4.mMaxSelectableIndex
            int r0 = r0 - r1
            int r0 = r0 - r3
            float r0 = (float) r0
            float r1 = r4.mIntervalDis
            float r0 = r0 * r1
            float r6 = r6 - r0
            float r0 = r4.mMaxOverScrollDistance
            float r6 = r6 - r0
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 <= 0) goto L53
            goto L22
        L53:
            int r5 = (int) r7
            r6 = 0
            r4.scrollBy(r5, r6)
            java.lang.String r5 = "onScroll"
            com.bydux.api.common.tools.log.BydLogHelper.logD(r5)
            r4.isNeedPlayWheelSoundEffect = r3
            r4.refreshCenter()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.widget.HorizontalWheelView.onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        BydLogHelper.logD("onFling");
        float scrollX = getScrollX();
        if (scrollX < (-this.mMaxOverScrollDistance) + (this.mMinSelectableIndex * this.mIntervalDis) || scrollX > (this.mContentRectF.width() - this.mMaxOverScrollDistance) - (((this.mMarkCount - 1) - this.mMaxSelectableIndex) * this.mIntervalDis)) {
            return false;
        }
        this.mFling = true;
        fling((int) (-velocityX), 0);
        return true;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.index = getSelectedPosition();
        savedState.min = this.mMinSelectableIndex;
        savedState.max = this.mMaxSelectableIndex;
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        BydLogHelper.logD("onRestoreInstanceState", "state : " + state);
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mMinSelectableIndex = savedState.min;
        this.mMaxSelectableIndex = savedState.max;
        selectIndex(savedState.index, false);
        requestLayout();
    }

    public boolean isNeedPlayWheelSoundEffect() {
        return this.isNeedPlayWheelSoundEffect;
    }

    public void setNeedPlayWheelSoundEffect(boolean needPlayWheelSoundEffect) {
        this.isNeedPlayWheelSoundEffect = needPlayWheelSoundEffect;
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.android.systemui.widget.HorizontalWheelView.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int index;
        int max;
        int min;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.index = in.readInt();
            this.min = in.readInt();
            this.max = in.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.index);
            out.writeInt(this.min);
            out.writeInt(this.max);
        }

        public String toString() {
            return "WheelView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " index=" + this.index + " min=" + this.min + " max=" + this.max + "}";
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            this.mHighlightColor = getContext().getColor(R.color.color_hvac_set_temp_pop_high_light_text);
            this.mMarkTextColor = getContext().getColor(R.color.color_hvac_set_temp_pop_normal_text);
        } else {
            this.mHighlightColor = getContext().getColor(R.color.color_navi_temp_set_text_nor);
            this.mMarkTextColor = getContext().getColor(R.color.color_navi_temp_set_text_nor);
        }
        invalidate();
    }
}
