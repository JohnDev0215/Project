package com.byd.viewsup;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import com.byd.viewsup.adapters.AbsListViewOverScrollDecorAdapter;
import com.byd.viewsup.adapters.HorizontalScrollViewOverScrollDecorAdapter;
import com.byd.viewsup.adapters.ICustomerOverScrollDecoratorAdapter;
import com.byd.viewsup.adapters.IOverScrollDecoratorAdapter;
import com.byd.viewsup.adapters.NestedFrameLayoutScrollDecorAdapter;
import com.byd.viewsup.adapters.ScrollViewOverScrollDecorAdapter;
import com.byd.viewsup.adapters.StaticOverScrollDecorAdapter;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes5.dex */
public class BydScrollerSupportedFrameLayout extends FrameLayout implements IOverScrollUpdateListener, IOverScrollStateListener, OverScrollUpdateListener {
    public static final String TAG = "BydScrollerSupported";
    private int bydOrientation;
    private float elasticCoefficient;
    private Drawable mBottomFadingDrawable;
    private String mBottomText;
    private int mBottomTextColor;
    private float mBottomTextOffSet;
    private View mChildView;
    private Context mContext;
    private int mDefaultTouchSlop;
    private float mDelX;
    private float mDelY;
    public float mEndDisappear;
    private Drawable mFadingDrawable;
    private float mFadingLengthBottom;
    private float mFadingLengthTop;
    private IOverScrollDecor mIOverScrollDecor;
    private IOverScrollStateListener mIOverScrollStateListener;
    private IOverScrollUpdateListener mIOverScrollUpdateListener;
    private boolean mIsCanDrawText;
    private boolean mIsCanRebound;
    public boolean mIsShowFading;
    private float mLineHeight;
    public float mLineLength;
    public long mMaxDuration;
    private float mOffSet;
    private final Rect mOriginalRect;
    private float mOriginalX;
    private float mOriginalY;
    private boolean mShowBtmText;
    public float mStartDisappear;
    private final Paint mTextPaint;
    private Rect mTextRect;
    private Drawable mTopFadingDrawable;
    public float maxDistance;
    private boolean pureScroll;
    private final Rect rect;
    private float scaledTouchSlop;
    private static boolean isExistTab = isExistTab();
    private static final Interpolator mPathInterpolator = new PathInterpolator(0.2f, 0.2f, 0.1f, 1.0f);
    public static AdapterGenerator[] mAdapterGenerator = null;

    public BydScrollerSupportedFrameLayout(Context context) {
        super(context);
        this.mOriginalRect = new Rect();
        this.mIsCanRebound = true;
        this.mIsCanDrawText = false;
        this.mOffSet = 0.0f;
        this.mFadingLengthTop = -1.0f;
        this.mFadingLengthBottom = -1.0f;
        this.mTextPaint = new Paint(1);
        this.rect = new Rect();
        this.pureScroll = false;
        this.elasticCoefficient = 1.0f;
        this.bydOrientation = -1;
        this.scaledTouchSlop = -1.0f;
        this.mContext = null;
        this.mOriginalX = -1.0f;
        this.mOriginalY = -1.0f;
        this.mDelX = -1.0f;
        this.mDelY = -1.0f;
        this.mContext = context;
    }

    public BydScrollerSupportedFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOriginalRect = new Rect();
        this.mIsCanRebound = true;
        this.mIsCanDrawText = false;
        this.mOffSet = 0.0f;
        this.mFadingLengthTop = -1.0f;
        this.mFadingLengthBottom = -1.0f;
        this.mTextPaint = new Paint(1);
        this.rect = new Rect();
        this.pureScroll = false;
        this.elasticCoefficient = 1.0f;
        this.bydOrientation = -1;
        this.scaledTouchSlop = -1.0f;
        this.mContext = null;
        this.mOriginalX = -1.0f;
        this.mOriginalY = -1.0f;
        this.mDelX = -1.0f;
        this.mDelY = -1.0f;
        this.mContext = context;
        init(context, attributeSet);
    }

    public float getScaledTouchSlop() {
        return this.scaledTouchSlop;
    }

    public void setScaledTouchSlop(float f) {
        this.scaledTouchSlop = f;
    }

    public void setElasticCoefficient(float f) {
        if (this.elasticCoefficient != f) {
            if (f < 0.4f) {
                f = 0.4f;
            } else if (f > 2.0f) {
                f = 2.0f;
            }
            this.elasticCoefficient = f;
        }
    }

    private static boolean isExistTab() {
        try {
            Thread.currentThread().getContextClassLoader().loadClass("com.google.android.material.tabs.TabLayout");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean isBydOrientation() {
        return this.bydOrientation != -1;
    }

    private boolean isOnePageTabLayout() {
        if (this.bydOrientation == 0 && isExistTab) {
            View view = this.mChildView;
            if ((view instanceof TabLayout) && !isTabScrollable((TabLayout) view)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTabScrollable(TabLayout tabLayout) {
        return tabLayout.getTabMode() == 0;
    }

    private boolean isBydHorizontal() {
        return this.bydOrientation == 0;
    }

    private boolean isBydVerticalOrientation() {
        return this.bydOrientation == 1;
    }

    private void init(Context context, AttributeSet attributeSet) {
        obtainAttr(context, attributeSet);
        setWillNotDraw(false);
        initGraph();
        setVerticalFadingEdgeEnabled(true);
        this.mDefaultTouchSlop = getDefaultTouchSlop(context);
        Log.d(TAG, "default touch slop: " + this.mDefaultTouchSlop);
    }

    private int getDefaultTouchSlop(Context context) {
        return ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public BydScrollerSupportedFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mOriginalRect = new Rect();
        this.mIsCanRebound = true;
        this.mIsCanDrawText = false;
        this.mOffSet = 0.0f;
        this.mFadingLengthTop = -1.0f;
        this.mFadingLengthBottom = -1.0f;
        this.mTextPaint = new Paint(1);
        this.rect = new Rect();
        this.pureScroll = false;
        this.elasticCoefficient = 1.0f;
        this.bydOrientation = -1;
        this.scaledTouchSlop = -1.0f;
        this.mContext = null;
        this.mOriginalX = -1.0f;
        this.mOriginalY = -1.0f;
        this.mDelX = -1.0f;
        this.mDelY = -1.0f;
        init(context, attributeSet);
    }

    public void setBydOrientation(int i) {
        this.bydOrientation = i;
        this.pureScroll = i != -1;
        if (i != -1) {
            setMaxDuration(200L);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isOnePageTabLayout()) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mOriginalX = motionEvent.getX();
                this.mOriginalY = motionEvent.getY();
            } else if (action == 2) {
                this.mDelX = motionEvent.getX() - this.mOriginalX;
                this.mDelY = motionEvent.getY() - this.mOriginalY;
                if (isNeedIntercept(isBydHorizontal(), this.mDelX, this.mDelY)) {
                    Log.d(TAG, "onInterceptTouchEvent");
                    return true;
                }
                this.mOriginalX = motionEvent.getX();
                this.mOriginalY = motionEvent.getY();
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    private boolean isNeedIntercept(boolean z, float f, float f2) {
        float fMax = Math.max(this.mDefaultTouchSlop, getScaledTouchSlop());
        return z ? Math.abs(f) > fMax && Math.abs(f) > Math.abs(f2) : Math.abs(f2) > fMax && Math.abs(f2) > Math.abs(f);
    }

    public void setBottomFadingDrawable(int i) {
        setBottomFadingDrawable(this.mContext.getDrawable(i));
    }

    public void setBottomFadingDrawable(Drawable drawable) {
        this.mBottomFadingDrawable = drawable;
        invalidate();
    }

    public void setTopFadingDrawable(int i) {
        setBottomFadingDrawable(this.mContext.getDrawable(i));
    }

    public void setTopFadingDrawable(Drawable drawable) {
        this.mTopFadingDrawable = drawable;
        invalidate();
    }

    public void setFadingDrawable(int i) {
        setFadingDrawable(this.mContext.getDrawable(i));
    }

    public void setFadingDrawable(Drawable drawable) {
        if (drawable == null) {
            this.mFadingDrawable = this.mContext.getDrawable(R.drawable.shape_gradient);
        } else {
            this.mFadingDrawable = drawable;
        }
        invalidate();
    }

    public void setShowFading(boolean z) {
        this.mIsShowFading = z;
        invalidate();
    }

    public void setFadingLengthTop(float f) {
        this.mFadingLengthTop = f;
        invalidate();
    }

    public void setFadingLengthBottom(float f) {
        this.mFadingLengthBottom = f;
        invalidate();
    }

    private void setInnerCanDrawText(boolean z) {
        if (this.mIsCanDrawText != z) {
            this.mIsCanDrawText = z;
            invalidate();
        }
    }

    public void setBottomTextOffSet(float f) {
        this.mBottomTextOffSet = f;
        invalidate();
    }

    public void setCanRebound(boolean z) {
        this.mIsCanRebound = z;
        IOverScrollDecor iOverScrollDecor = this.mIOverScrollDecor;
        if (iOverScrollDecor != null) {
            iOverScrollDecor.setCanRebound(z);
        }
        invalidate();
    }

    private void obtainAttr(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BydScrollersupportedFrameLayout);
        setCanRebound(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BydScrollersupportedFrameLayout_isCanRebound, true));
        setBottomText(typedArrayObtainStyledAttributes.getText(R.styleable.BydScrollersupportedFrameLayout_bottomText));
        setBottomTextColor(typedArrayObtainStyledAttributes.getColor(R.styleable.BydScrollersupportedFrameLayout_bottomTextColor, Color.parseColor("#121214")));
        setBottomTextSize(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_bottomTextSize, sp2px(19.0f)));
        setMaxDistance(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_maxDistance, dp2px(155.0f)));
        setStartDisappear(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_startDisappear, dp2px(30.0f)));
        setEndDisappear(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_endDisappear, dp2px(110.0f)));
        setLineLength(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_btmLineLength, dp2px(60.0f)));
        setLineHeight(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_btmLineHeight, dp2px(1.5f)));
        setMaxDuration(typedArrayObtainStyledAttributes.getInt(R.styleable.BydScrollersupportedFrameLayout_maxDuration, 400));
        setShowBtmText(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BydScrollersupportedFrameLayout_showBtmText, true));
        setFadingLengthBottom(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_fadingLengthBottom, dp2px(32.0f)));
        setFadingLengthTop(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_fadingLengthTop, dp2px(32.0f)));
        setFadingDrawable(typedArrayObtainStyledAttributes.getDrawable(R.styleable.BydScrollersupportedFrameLayout_fadingDrawable));
        setTopFadingDrawable(typedArrayObtainStyledAttributes.getDrawable(R.styleable.BydScrollersupportedFrameLayout_topFadingDrawable));
        setBottomFadingDrawable(typedArrayObtainStyledAttributes.getDrawable(R.styleable.BydScrollersupportedFrameLayout_bottomFadingDrawable));
        setShowFading(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BydScrollersupportedFrameLayout_isShowFading, false));
        setBottomTextOffSet(typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_bottomTextOffset, 60.0f));
        setBydOrientation(typedArrayObtainStyledAttributes.getInt(R.styleable.BydScrollersupportedFrameLayout_bydOrientation, -1));
        setElasticCoefficient(typedArrayObtainStyledAttributes.getFloat(R.styleable.BydScrollersupportedFrameLayout_elasticCoefficient, 1.0f));
        this.scaledTouchSlop = typedArrayObtainStyledAttributes.getDimension(R.styleable.BydScrollersupportedFrameLayout_scaledTouchSlop, -1.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public void setLineHeight(float f) {
        this.mLineHeight = f;
        invalidate();
    }

    public void setShowBtmText(boolean z) {
        this.mShowBtmText = z;
        invalidate();
    }

    public void setLineLength(float f) {
        this.mLineLength = f;
    }

    public void setEndDisappear(float f) {
        this.mEndDisappear = f;
        invalidate();
    }

    public void setStartDisappear(float f) {
        this.mStartDisappear = f;
        invalidate();
    }

    public void setBottomTextSize(float f) {
        this.mTextPaint.setTextSize(f);
        invalidate();
    }

    public void setBottomTextColor(int i) {
        this.mBottomTextColor = i;
        this.mTextPaint.setColor(i);
        invalidate();
    }

    public void setMaxDuration(long j) {
        this.mMaxDuration = j;
        IOverScrollDecor iOverScrollDecor = this.mIOverScrollDecor;
        if (iOverScrollDecor != null) {
            iOverScrollDecor.setMaxDuration(j);
        }
        invalidate();
    }

    public void setMaxDistance(float f) {
        this.maxDistance = f;
        IOverScrollDecor iOverScrollDecor = this.mIOverScrollDecor;
        if (iOverScrollDecor != null) {
            iOverScrollDecor.setMaxDistance(f);
        }
        invalidate();
    }

    public void setBottomText(CharSequence charSequence) {
        setBottomText(charSequence, getResources().getString(R.string.defaultBtmStr));
    }

    public void setBottomText(CharSequence charSequence, String str) {
        if (!TextUtils.isEmpty(charSequence)) {
            this.mBottomText = charSequence.toString();
        } else {
            this.mBottomText = str;
        }
        invalidate();
    }

    public static void addAdapterGenerator(AdapterGenerator... adapterGeneratorArr) {
        mAdapterGenerator = adapterGeneratorArr;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        intRebound();
    }

    public IOverScrollDecoratorAdapter buildDecoratorAdapter(View view) {
        ICustomerOverScrollDecoratorAdapter iCustomerOverScrollDecoratorAdapterGenerate;
        IOverScrollDecoratorAdapter pureScrollDecoratorAdapter;
        if (view == null) {
            return null;
        }
        if (view instanceof ScrollView) {
            if (isBydHorizontal()) {
                throw new RuntimeException("ScrollView 不支持水平方向");
            }
            return new ScrollViewOverScrollDecorAdapter((ScrollView) view, isBydVerticalOrientation());
        }
        if (isBydOrientation() && (pureScrollDecoratorAdapter = getPureScrollDecoratorAdapter(view)) != null) {
            return pureScrollDecoratorAdapter;
        }
        AdapterGenerator[] adapterGeneratorArr = mAdapterGenerator;
        if (adapterGeneratorArr != null) {
            for (AdapterGenerator adapterGenerator : adapterGeneratorArr) {
                if (adapterGenerator != null && (iCustomerOverScrollDecoratorAdapterGenerate = adapterGenerator.generate()) != null && iCustomerOverScrollDecoratorAdapterGenerate.setView(view)) {
                    return iCustomerOverScrollDecoratorAdapterGenerate;
                }
            }
        }
        return null;
    }

    private IOverScrollDecoratorAdapter getPureScrollDecoratorAdapter(View view) {
        if (isOnePageTabLayout()) {
            return new NestedFrameLayoutScrollDecorAdapter(this, true);
        }
        if ((view instanceof HorizontalScrollView) && isBydHorizontal()) {
            return new HorizontalScrollViewOverScrollDecorAdapter((HorizontalScrollView) view);
        }
        if (view instanceof AbsListView) {
            return new AbsListViewOverScrollDecorAdapter((AbsListView) view);
        }
        return new StaticOverScrollDecorAdapter(view, isBydHorizontal());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void intRebound() {
        if (getChildCount() != 1) {
            throw new IllegalArgumentException("Only one child view is allowed to exist in BydReboundFrameLayout");
        }
        View childAt = getChildAt(0);
        this.mChildView = childAt;
        if (childAt instanceof BydScrollerTouchChildView) {
            BydScrollerTouchChildView bydScrollerTouchChildView = (BydScrollerTouchChildView) childAt;
            int i = this.bydOrientation;
            if (i != -1) {
                bydScrollerTouchChildView.setHorizontal(i == 0);
            }
        }
        IOverScrollDecoratorAdapter iOverScrollDecoratorAdapterBuildDecoratorAdapter = buildDecoratorAdapter(this.mChildView);
        if (iOverScrollDecoratorAdapterBuildDecoratorAdapter != null) {
            IOverScrollDecor upOverScroll = BydOverScrollHelper.setUpOverScroll(iOverScrollDecoratorAdapterBuildDecoratorAdapter, this.maxDistance, this.mMaxDuration, this.elasticCoefficient);
            this.mIOverScrollDecor = upOverScroll;
            upOverScroll.setCanRebound(this.mIsCanRebound);
            this.mIOverScrollDecor.setOverScrollUpdateListener(this);
            this.mIOverScrollDecor.setOverScrollStateListener(this);
            if (isBydOrientation()) {
                setScrollDecorInterpolator(mPathInterpolator);
            }
        }
    }

    public void setScrollDecorInterpolator(Interpolator interpolator) {
        IOverScrollDecor iOverScrollDecor = this.mIOverScrollDecor;
        if (iOverScrollDecor != null) {
            iOverScrollDecor.setCustomInterpolator(interpolator);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.mOriginalRect.set(this.mChildView.getLeft(), this.mChildView.getTop(), this.mChildView.getRight(), this.mChildView.getBottom());
    }

    void initGraph() {
        this.mTextPaint.setColor(this.mBottomTextColor);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setStrokeWidth(this.mLineHeight);
        this.mTextPaint.setTypeface(Typeface.create("met-ee3a", 0));
    }

    public void setTypeface(Typeface typeface) {
        this.mTextPaint.setTypeface(typeface);
        invalidate();
    }

    private Rect getTextRect(String str, Paint paint) {
        paint.getTextBounds(str, 0, str.length(), this.rect);
        return this.rect;
    }

    private int calculateTextColor(float f) {
        float f2 = this.mStartDisappear;
        if (f < f2) {
            return 0;
        }
        float f3 = this.mEndDisappear;
        if (f > f3) {
            return 255;
        }
        return (int) (((f - f2) * 255.0f) / (f3 - f2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mIsCanDrawText && this.mShowBtmText && this.mIsCanRebound && !this.pureScroll) {
            drawText(canvas, this.mOriginalRect.centerX(), this.mBottomText);
        }
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        if (this.mIsShowFading) {
            if (canDrawBottomFading()) {
                Drawable drawable = this.mBottomFadingDrawable;
                if (drawable == null) {
                    drawable = this.mFadingDrawable;
                }
                drawable.setBounds(0, (int) (this.mOriginalRect.bottom - this.mFadingLengthBottom), getWidth(), this.mOriginalRect.bottom);
                drawable.draw(canvas);
            }
            if (canDrawTopFading()) {
                int i = this.mOriginalRect.top;
                int width = getWidth();
                int i2 = (int) (i + this.mFadingLengthTop);
                Drawable drawable2 = this.mTopFadingDrawable;
                if (drawable2 == null) {
                    drawable2 = this.mFadingDrawable;
                }
                drawable2.setBounds(0, i, width, i2);
                canvas.save();
                if (this.mTopFadingDrawable == null) {
                    canvas.scale(1.0f, -1.0f, (width + 0) >> 1, (i + i2) >> 1);
                }
                drawable2.draw(canvas);
                canvas.restore();
            }
        }
    }

    private boolean canDrawTopFading() {
        IOverScrollDecor iOverScrollDecor = this.mIOverScrollDecor;
        if (iOverScrollDecor == null || this.mFadingLengthTop <= 0.0f || iOverScrollDecor.getView() == null) {
            return false;
        }
        if (isBydOrientation()) {
            return this.mIOverScrollDecor.isInAbsoluteStart();
        }
        return !this.mIOverScrollDecor.getView().canScrollVertically(-1);
    }

    private boolean canDrawBottomFading() {
        IOverScrollDecor iOverScrollDecor = this.mIOverScrollDecor;
        if (iOverScrollDecor == null || this.mFadingLengthTop <= 0.0f || iOverScrollDecor.getView() == null) {
            return false;
        }
        return this.mIOverScrollDecor.isInAbsoluteEnd();
    }

    private void drawLine(Canvas canvas, int i, float f) {
        float f2 = f + (this.mLineHeight * 0.2f);
        this.mTextRect = getTextRect(this.mBottomText, this.mTextPaint);
        float f3 = ((r0.right - this.mTextRect.left) * 1.2f) / 2.0f;
        float f4 = i;
        float f5 = f4 - f3;
        float f6 = f4 + f3;
        float f7 = this.mLineLength;
        canvas.drawLine(f5 - f7, f2, f5, f2, this.mTextPaint);
        canvas.drawLine(f6, f2, f7 + f6, f2, this.mTextPaint);
    }

    private void drawText(Canvas canvas, int i, String str) {
        this.mTextPaint.setAlpha(calculateTextColor(Math.abs(this.mOffSet)));
        Paint.FontMetrics fontMetrics = this.mTextPaint.getFontMetrics();
        float f = fontMetrics.top;
        float f2 = fontMetrics.bottom;
        float f3 = this.mOriginalRect.bottom;
        if (this.mBottomTextOffSet <= 1.0f) {
            f3 -= (f2 - f) * 0.2f;
        }
        float f4 = f3 - this.mBottomTextOffSet;
        canvas.drawText(str, i, f4, this.mTextPaint);
        drawLine(canvas, this.mOriginalRect.centerX(), f4 + (fontMetrics.ascent / 2.0f));
    }

    public static float sp2px(float f) {
        return TypedValue.applyDimension(2, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int px2sp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static float dp2px(float f) {
        return TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    @Override // com.byd.viewsup.IOverScrollUpdateListener
    public void onOverScrollUpdate(IOverScrollDecor iOverScrollDecor, int i, float f) {
        IOverScrollUpdateListener iOverScrollUpdateListener = this.mIOverScrollUpdateListener;
        if (iOverScrollUpdateListener != null) {
            iOverScrollUpdateListener.onOverScrollUpdate(iOverScrollDecor, i, f);
        }
        this.mOffSet = f;
        postInvalidate();
    }

    @Override // com.byd.viewsup.IOverScrollStateListener
    public void onOverScrollStateChange(IOverScrollDecor iOverScrollDecor, int i, int i2) {
        if (i2 != 1) {
            if (i2 == 2) {
                setInnerCanDrawText(true);
            } else if (i2 == 3) {
                setInnerCanDrawText(false);
            }
        }
        if (i != i2) {
            invalidate();
        }
        IOverScrollStateListener iOverScrollStateListener = this.mIOverScrollStateListener;
        if (iOverScrollStateListener != null) {
            iOverScrollStateListener.onOverScrollStateChange(iOverScrollDecor, i, i2);
        }
    }

    @Override // com.byd.viewsup.OverScrollUpdateListener
    public void setOverScrollStateListener(IOverScrollStateListener iOverScrollStateListener) {
        this.mIOverScrollStateListener = iOverScrollStateListener;
    }

    @Override // com.byd.viewsup.OverScrollUpdateListener
    public void setOverScrollUpdateListener(IOverScrollUpdateListener iOverScrollUpdateListener) {
        this.mIOverScrollUpdateListener = iOverScrollUpdateListener;
    }
}
