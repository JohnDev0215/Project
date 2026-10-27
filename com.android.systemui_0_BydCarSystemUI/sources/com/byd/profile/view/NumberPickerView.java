package com.byd.profile.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.android.systemui.Constants;
import com.byd.card.bean.Constant;
import com.byd.profile.R;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class NumberPickerView extends View {
    private static final boolean DEFAULT_CURRENT_ITEM_INDEX_EFFECT = false;
    private static final int DEFAULT_DIVIDER_COLOR = -695533;
    private static final int DEFAULT_DIVIDER_HEIGHT = 2;
    private static final int DEFAULT_DIVIDER_MARGIN_HORIZONTAL = 0;
    private static final int DEFAULT_INTERVAL_REVISE_DURATION = 300;
    private static final int DEFAULT_ITEM_PADDING_DP_H = 5;
    private static final int DEFAULT_ITEM_PADDING_DP_V = 2;
    private static final int DEFAULT_MARGIN_END_OF_HINT_DP = 8;
    private static final int DEFAULT_MARGIN_START_OF_HINT_DP = 8;
    private static final int DEFAULT_MAX_SCROLL_BY_INDEX_DURATION = 600;
    private static final int DEFAULT_MIN_SCROLL_BY_INDEX_DURATION = 300;
    private static final boolean DEFAULT_RESPOND_CHANGE_IN_MAIN_THREAD = true;
    private static final boolean DEFAULT_RESPOND_CHANGE_ON_DETACH = false;
    private static final int DEFAULT_SHOWN_COUNT = 3;
    private static final boolean DEFAULT_SHOW_DIVIDER = true;
    private static final int DEFAULT_TEXT_COLOR_NORMAL = -13421773;
    private static final int DEFAULT_TEXT_COLOR_SELECTED = -695533;
    private static final int DEFAULT_TEXT_SIZE_HINT_SP = 14;
    private static final int DEFAULT_TEXT_SIZE_NORMAL_SP = 14;
    private static final int DEFAULT_TEXT_SIZE_SELECTED_SP = 16;
    private static final boolean DEFAULT_WRAP_SELECTOR_WHEEL = true;
    private static final int HANDLER_INTERVAL_REFRESH = 32;
    private static final int HANDLER_WHAT_LISTENER_VALUE_CHANGED = 2;
    private static final int HANDLER_WHAT_REFRESH = 1;
    private static final int HANDLER_WHAT_REQUEST_LAYOUT = 3;
    private static final String TEXT_ELLIPSIZE_END = "end";
    private static final String TEXT_ELLIPSIZE_MIDDLE = "middle";
    private static final String TEXT_ELLIPSIZE_START = "start";
    private float currY;
    private float dividerY0;
    private float dividerY1;
    private float downY;
    private float downYGlobal;
    private String mAlterHint;
    private CharSequence[] mAlterTextArrayWithMeasureHint;
    private CharSequence[] mAlterTextArrayWithoutMeasureHint;
    private int mCurrDrawFirstItemIndex;
    private int mCurrDrawFirstItemY;
    private int mCurrDrawGlobalY;
    private boolean mCurrentItemIndexEffect;
    private String[] mDisplayedValues;
    private int mDividerColor;
    private int mDividerHeight;
    private int mDividerIndex0;
    private int mDividerIndex1;
    private int mDividerMarginL;
    private int mDividerMarginR;
    private String mEmptyItemHint;
    private boolean mFlagMayPress;
    private float mFriction;
    private Handler mHandlerInMainThread;
    private Handler mHandlerInNewThread;
    private HandlerThread mHandlerThread;
    private boolean mHasInit;
    private String mHintText;
    private int mInScrollingPickedNewValue;
    private int mInScrollingPickedOldValue;
    private int mItemHeight;
    private int mItemPaddingHorizontal;
    private int mItemPaddingVertical;
    private int mMarginEndOfHint;
    private int mMarginStartOfHint;
    private int mMaxHeightOfDisplayedValues;
    private int mMaxShowIndex;
    private int mMaxValue;
    private int mMaxWidthOfAlterArrayWithMeasureHint;
    private int mMaxWidthOfAlterArrayWithoutMeasureHint;
    private int mMaxWidthOfDisplayedValues;
    private int mMinShowIndex;
    private int mMinValue;
    private int mMiniVelocityFling;
    private int mNotWrapLimitYBottom;
    private int mNotWrapLimitYTop;
    private OnScrollListener mOnScrollListener;
    private OnValueChangeListener mOnValueChangeListener;
    private OnValueChangeListenerInScrolling mOnValueChangeListenerInScrolling;
    private OnValueChangeListenerRelativeToRaw mOnValueChangeListenerRaw;
    private Paint mPaintDivider;
    private Paint mPaintHint;
    private TextPaint mPaintText;
    private boolean mPendingWrapToLinear;
    private int mPrevPickedIndex;
    private boolean mRespondChangeInMainThread;
    private boolean mRespondChangeOnDetach;
    private int mScaledTouchSlop;
    private int mScrollState;
    private Scroller mScroller;
    private Drawable mSelectedItemBackground;
    private boolean mShowDivider;
    private int mShownCount;
    private int mSpecModeH;
    private int mSpecModeW;
    private int mTextColorHint;
    private int mTextColorNormal;
    private int mTextColorSelected;
    private String mTextEllipsize;
    private int mTextSizeHint;
    private float mTextSizeHintCenterYOffset;
    private int mTextSizeNormal;
    private float mTextSizeNormalCenterYOffset;
    private int mTextSizeSelected;
    private float mTextSizeSelectedCenterYOffset;
    private Map<String, Integer> mTextWidthCache;
    private VelocityTracker mVelocityTracker;
    private float mViewCenterX;
    private int mViewHeight;
    private int mViewWidth;
    private int mWidthOfAlterHint;
    private int mWidthOfHintText;
    private boolean mWrapSelectorWheel;
    private boolean mWrapSelectorWheelCheck;

    public interface OnScrollListener {
        public static final int SCROLL_STATE_FLING = 2;
        public static final int SCROLL_STATE_IDLE = 0;
        public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

        void onScrollStateChange(NumberPickerView view, int scrollState);
    }

    public interface OnValueChangeListener {
        void onValueChange(NumberPickerView picker, int oldVal, int newVal);
    }

    public interface OnValueChangeListenerInScrolling {
        void onValueChangeInScrolling(NumberPickerView picker, int oldVal, int newVal);
    }

    public interface OnValueChangeListenerRelativeToRaw {
        void onValueChangeRelativeToRaw(NumberPickerView picker, int oldPickedIndex, int newPickedIndex, String[] displayedValues);
    }

    private int getEvaluateColor(float fraction, int startColor, int endColor) {
        int i = (startColor & ViewCompat.MEASURED_STATE_MASK) >>> 24;
        int i2 = (startColor & 16711680) >>> 16;
        int i3 = (startColor & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8;
        return ((int) (((startColor & 255) >>> 0) + ((((endColor & 255) >>> 0) - r9) * fraction))) | (((int) (i + (((((-16777216) & endColor) >>> 24) - i) * fraction))) << 24) | (((int) (i2 + ((((16711680 & endColor) >>> 16) - i2) * fraction))) << 16) | (((int) (i3 + ((((65280 & endColor) >>> 8) - i3) * fraction))) << 8);
    }

    private float getEvaluateSize(float fraction, float startSize, float endSize) {
        return startSize + ((endSize - startSize) * fraction);
    }

    public NumberPickerView(Context context) {
        super(context);
        this.mTextColorNormal = DEFAULT_TEXT_COLOR_NORMAL;
        this.mTextColorSelected = -695533;
        this.mTextColorHint = -695533;
        this.mTextSizeNormal = 0;
        this.mTextSizeSelected = 0;
        this.mTextSizeHint = 0;
        this.mWidthOfHintText = 0;
        this.mWidthOfAlterHint = 0;
        this.mMarginStartOfHint = 0;
        this.mMarginEndOfHint = 0;
        this.mItemPaddingVertical = 0;
        this.mItemPaddingHorizontal = 0;
        this.mDividerColor = -695533;
        this.mDividerHeight = 2;
        this.mDividerMarginL = 0;
        this.mDividerMarginR = 0;
        this.mShownCount = 3;
        this.mDividerIndex0 = 0;
        this.mDividerIndex1 = 0;
        this.mMinShowIndex = -1;
        this.mMaxShowIndex = -1;
        this.mMinValue = 0;
        this.mMaxValue = 0;
        this.mMaxWidthOfDisplayedValues = 0;
        this.mMaxHeightOfDisplayedValues = 0;
        this.mMaxWidthOfAlterArrayWithMeasureHint = 0;
        this.mMaxWidthOfAlterArrayWithoutMeasureHint = 0;
        this.mPrevPickedIndex = 0;
        this.mMiniVelocityFling = 150;
        this.mScaledTouchSlop = 8;
        this.mFriction = 1.0f;
        this.mTextSizeNormalCenterYOffset = 0.0f;
        this.mTextSizeSelectedCenterYOffset = 0.0f;
        this.mTextSizeHintCenterYOffset = 0.0f;
        this.mShowDivider = true;
        this.mWrapSelectorWheel = true;
        this.mCurrentItemIndexEffect = false;
        this.mHasInit = false;
        this.mWrapSelectorWheelCheck = true;
        this.mPendingWrapToLinear = false;
        this.mRespondChangeOnDetach = false;
        this.mRespondChangeInMainThread = true;
        this.mSelectedItemBackground = null;
        this.mPaintDivider = new Paint();
        this.mPaintText = new TextPaint();
        this.mPaintHint = new Paint();
        this.mTextWidthCache = new ConcurrentHashMap();
        this.mScrollState = 0;
        this.downYGlobal = 0.0f;
        this.downY = 0.0f;
        this.currY = 0.0f;
        this.mFlagMayPress = false;
        this.mCurrDrawFirstItemIndex = 0;
        this.mCurrDrawFirstItemY = 0;
        this.mCurrDrawGlobalY = 0;
        this.mSpecModeW = 0;
        this.mSpecModeH = 0;
        init(context);
    }

    public NumberPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTextColorNormal = DEFAULT_TEXT_COLOR_NORMAL;
        this.mTextColorSelected = -695533;
        this.mTextColorHint = -695533;
        this.mTextSizeNormal = 0;
        this.mTextSizeSelected = 0;
        this.mTextSizeHint = 0;
        this.mWidthOfHintText = 0;
        this.mWidthOfAlterHint = 0;
        this.mMarginStartOfHint = 0;
        this.mMarginEndOfHint = 0;
        this.mItemPaddingVertical = 0;
        this.mItemPaddingHorizontal = 0;
        this.mDividerColor = -695533;
        this.mDividerHeight = 2;
        this.mDividerMarginL = 0;
        this.mDividerMarginR = 0;
        this.mShownCount = 3;
        this.mDividerIndex0 = 0;
        this.mDividerIndex1 = 0;
        this.mMinShowIndex = -1;
        this.mMaxShowIndex = -1;
        this.mMinValue = 0;
        this.mMaxValue = 0;
        this.mMaxWidthOfDisplayedValues = 0;
        this.mMaxHeightOfDisplayedValues = 0;
        this.mMaxWidthOfAlterArrayWithMeasureHint = 0;
        this.mMaxWidthOfAlterArrayWithoutMeasureHint = 0;
        this.mPrevPickedIndex = 0;
        this.mMiniVelocityFling = 150;
        this.mScaledTouchSlop = 8;
        this.mFriction = 1.0f;
        this.mTextSizeNormalCenterYOffset = 0.0f;
        this.mTextSizeSelectedCenterYOffset = 0.0f;
        this.mTextSizeHintCenterYOffset = 0.0f;
        this.mShowDivider = true;
        this.mWrapSelectorWheel = true;
        this.mCurrentItemIndexEffect = false;
        this.mHasInit = false;
        this.mWrapSelectorWheelCheck = true;
        this.mPendingWrapToLinear = false;
        this.mRespondChangeOnDetach = false;
        this.mRespondChangeInMainThread = true;
        this.mSelectedItemBackground = null;
        this.mPaintDivider = new Paint();
        this.mPaintText = new TextPaint();
        this.mPaintHint = new Paint();
        this.mTextWidthCache = new ConcurrentHashMap();
        this.mScrollState = 0;
        this.downYGlobal = 0.0f;
        this.downY = 0.0f;
        this.currY = 0.0f;
        this.mFlagMayPress = false;
        this.mCurrDrawFirstItemIndex = 0;
        this.mCurrDrawFirstItemY = 0;
        this.mCurrDrawGlobalY = 0;
        this.mSpecModeW = 0;
        this.mSpecModeH = 0;
        initAttr(context, attrs);
        init(context);
    }

    public NumberPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTextColorNormal = DEFAULT_TEXT_COLOR_NORMAL;
        this.mTextColorSelected = -695533;
        this.mTextColorHint = -695533;
        this.mTextSizeNormal = 0;
        this.mTextSizeSelected = 0;
        this.mTextSizeHint = 0;
        this.mWidthOfHintText = 0;
        this.mWidthOfAlterHint = 0;
        this.mMarginStartOfHint = 0;
        this.mMarginEndOfHint = 0;
        this.mItemPaddingVertical = 0;
        this.mItemPaddingHorizontal = 0;
        this.mDividerColor = -695533;
        this.mDividerHeight = 2;
        this.mDividerMarginL = 0;
        this.mDividerMarginR = 0;
        this.mShownCount = 3;
        this.mDividerIndex0 = 0;
        this.mDividerIndex1 = 0;
        this.mMinShowIndex = -1;
        this.mMaxShowIndex = -1;
        this.mMinValue = 0;
        this.mMaxValue = 0;
        this.mMaxWidthOfDisplayedValues = 0;
        this.mMaxHeightOfDisplayedValues = 0;
        this.mMaxWidthOfAlterArrayWithMeasureHint = 0;
        this.mMaxWidthOfAlterArrayWithoutMeasureHint = 0;
        this.mPrevPickedIndex = 0;
        this.mMiniVelocityFling = 150;
        this.mScaledTouchSlop = 8;
        this.mFriction = 1.0f;
        this.mTextSizeNormalCenterYOffset = 0.0f;
        this.mTextSizeSelectedCenterYOffset = 0.0f;
        this.mTextSizeHintCenterYOffset = 0.0f;
        this.mShowDivider = true;
        this.mWrapSelectorWheel = true;
        this.mCurrentItemIndexEffect = false;
        this.mHasInit = false;
        this.mWrapSelectorWheelCheck = true;
        this.mPendingWrapToLinear = false;
        this.mRespondChangeOnDetach = false;
        this.mRespondChangeInMainThread = true;
        this.mSelectedItemBackground = null;
        this.mPaintDivider = new Paint();
        this.mPaintText = new TextPaint();
        this.mPaintHint = new Paint();
        this.mTextWidthCache = new ConcurrentHashMap();
        this.mScrollState = 0;
        this.downYGlobal = 0.0f;
        this.downY = 0.0f;
        this.currY = 0.0f;
        this.mFlagMayPress = false;
        this.mCurrDrawFirstItemIndex = 0;
        this.mCurrDrawFirstItemY = 0;
        this.mCurrDrawGlobalY = 0;
        this.mSpecModeW = 0;
        this.mSpecModeH = 0;
        initAttr(context, attrs);
        init(context);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.NumberPickerView);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R.styleable.NumberPickerView_npv_ShownCount) {
                this.mShownCount = typedArrayObtainStyledAttributes.getInt(index, 3);
            } else if (index == R.styleable.NumberPickerView_npv_DividerColor) {
                this.mDividerColor = typedArrayObtainStyledAttributes.getColor(index, -695533);
            } else if (index == R.styleable.NumberPickerView_npv_DividerHeight) {
                this.mDividerHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 2);
            } else if (index == R.styleable.NumberPickerView_npv_DividerMarginLeft) {
                this.mDividerMarginL = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.NumberPickerView_npv_DividerMarginRight) {
                this.mDividerMarginR = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.NumberPickerView_npv_TextArray) {
                this.mDisplayedValues = convertCharSequenceArrayToStringArray(typedArrayObtainStyledAttributes.getTextArray(index));
            } else if (index == R.styleable.NumberPickerView_npv_TextColorNormal) {
                this.mTextColorNormal = typedArrayObtainStyledAttributes.getColor(index, DEFAULT_TEXT_COLOR_NORMAL);
            } else if (index == R.styleable.NumberPickerView_npv_TextColorSelected) {
                this.mTextColorSelected = typedArrayObtainStyledAttributes.getColor(index, -695533);
            } else if (index == R.styleable.NumberPickerView_npv_TextColorHint) {
                this.mTextColorHint = typedArrayObtainStyledAttributes.getColor(index, -695533);
            } else if (index == R.styleable.NumberPickerView_npv_TextSizeNormal) {
                this.mTextSizeNormal = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, sp2px(context, 14.0f));
            } else if (index == R.styleable.NumberPickerView_npv_TextSizeSelected) {
                this.mTextSizeSelected = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, sp2px(context, 16.0f));
            } else if (index == R.styleable.NumberPickerView_npv_TextSizeHint) {
                this.mTextSizeHint = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, sp2px(context, 14.0f));
            } else if (index == R.styleable.NumberPickerView_npv_MinValue) {
                this.mMinShowIndex = typedArrayObtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.NumberPickerView_npv_MaxValue) {
                this.mMaxShowIndex = typedArrayObtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.NumberPickerView_npv_WrapSelectorWheel) {
                this.mWrapSelectorWheel = typedArrayObtainStyledAttributes.getBoolean(index, true);
            } else if (index == R.styleable.NumberPickerView_npv_ShowDivider) {
                this.mShowDivider = typedArrayObtainStyledAttributes.getBoolean(index, true);
            } else if (index == R.styleable.NumberPickerView_npv_HintText) {
                this.mHintText = typedArrayObtainStyledAttributes.getString(index);
            } else if (index == R.styleable.NumberPickerView_npv_AlternativeHint) {
                this.mAlterHint = typedArrayObtainStyledAttributes.getString(index);
            } else if (index == R.styleable.NumberPickerView_npv_EmptyItemHint) {
                this.mEmptyItemHint = typedArrayObtainStyledAttributes.getString(index);
            } else if (index == R.styleable.NumberPickerView_npv_MarginStartOfHint) {
                this.mMarginStartOfHint = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dp2px(context, 8.0f));
            } else if (index == R.styleable.NumberPickerView_npv_MarginEndOfHint) {
                this.mMarginEndOfHint = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dp2px(context, 8.0f));
            } else if (index == R.styleable.NumberPickerView_npv_ItemPaddingVertical) {
                this.mItemPaddingVertical = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dp2px(context, 2.0f));
            } else if (index == R.styleable.NumberPickerView_npv_ItemPaddingHorizontal) {
                this.mItemPaddingHorizontal = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, dp2px(context, 5.0f));
            } else if (index == R.styleable.NumberPickerView_npv_AlternativeTextArrayWithMeasureHint) {
                this.mAlterTextArrayWithMeasureHint = typedArrayObtainStyledAttributes.getTextArray(index);
            } else if (index == R.styleable.NumberPickerView_npv_AlternativeTextArrayWithoutMeasureHint) {
                this.mAlterTextArrayWithoutMeasureHint = typedArrayObtainStyledAttributes.getTextArray(index);
            } else if (index == R.styleable.NumberPickerView_npv_RespondChangeOnDetached) {
                this.mRespondChangeOnDetach = typedArrayObtainStyledAttributes.getBoolean(index, false);
            } else if (index == R.styleable.NumberPickerView_npv_RespondChangeInMainThread) {
                this.mRespondChangeInMainThread = typedArrayObtainStyledAttributes.getBoolean(index, true);
            } else if (index == R.styleable.NumberPickerView_npv_TextEllipsize) {
                this.mTextEllipsize = typedArrayObtainStyledAttributes.getString(index);
            } else if (index == R.styleable.NumberPickerView_npv_SelectedItemBackground) {
                this.mSelectedItemBackground = typedArrayObtainStyledAttributes.getDrawable(index);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void init(Context context) {
        this.mScroller = new Scroller(context);
        this.mMiniVelocityFling = ViewConfiguration.get(getContext()).getScaledMinimumFlingVelocity();
        this.mScaledTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        if (this.mTextSizeNormal == 0) {
            this.mTextSizeNormal = sp2px(context, 14.0f);
        }
        if (this.mTextSizeSelected == 0) {
            this.mTextSizeSelected = sp2px(context, 16.0f);
        }
        if (this.mTextSizeHint == 0) {
            this.mTextSizeHint = sp2px(context, 14.0f);
        }
        if (this.mMarginStartOfHint == 0) {
            this.mMarginStartOfHint = dp2px(context, 8.0f);
        }
        if (this.mMarginEndOfHint == 0) {
            this.mMarginEndOfHint = dp2px(context, 8.0f);
        }
        this.mPaintDivider.setColor(this.mDividerColor);
        this.mPaintDivider.setAntiAlias(true);
        this.mPaintDivider.setStyle(Paint.Style.STROKE);
        this.mPaintDivider.setStrokeWidth(this.mDividerHeight);
        this.mPaintText.setColor(this.mTextColorNormal);
        this.mPaintText.setAntiAlias(true);
        this.mPaintText.setTextAlign(Paint.Align.CENTER);
        this.mPaintHint.setColor(this.mTextColorHint);
        this.mPaintHint.setAntiAlias(true);
        this.mPaintHint.setTextAlign(Paint.Align.CENTER);
        this.mPaintHint.setTextSize(this.mTextSizeHint);
        int i = this.mShownCount;
        if (i % 2 == 0) {
            this.mShownCount = i + 1;
        }
        if (this.mMinShowIndex == -1 || this.mMaxShowIndex == -1) {
            updateValueForInit();
        }
        initHandler();
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread("HandlerThread-For-Refreshing");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandlerInNewThread = new Handler(this.mHandlerThread.getLooper()) { // from class: com.byd.profile.view.NumberPickerView.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                int willPickIndexByGlobalY;
                int i;
                int i2 = msg.what;
                if (i2 != 1) {
                    if (i2 != 2) {
                        return;
                    }
                    NumberPickerView.this.respondPickedValueChanged(msg.arg1, msg.arg2, msg.obj);
                    return;
                }
                int i3 = 0;
                if (!NumberPickerView.this.mScroller.isFinished()) {
                    if (NumberPickerView.this.mScrollState == 0) {
                        NumberPickerView.this.onScrollStateChange(1);
                    }
                    NumberPickerView.this.mHandlerInNewThread.sendMessageDelayed(NumberPickerView.this.getMsg(1, 0, 0, msg.obj), 32L);
                    return;
                }
                if (NumberPickerView.this.mCurrDrawFirstItemY != 0) {
                    if (NumberPickerView.this.mScrollState == 0) {
                        NumberPickerView.this.onScrollStateChange(1);
                    }
                    if (NumberPickerView.this.mCurrDrawFirstItemY < (-NumberPickerView.this.mItemHeight) / 2) {
                        i = (int) (((NumberPickerView.this.mItemHeight + NumberPickerView.this.mCurrDrawFirstItemY) * 300.0f) / NumberPickerView.this.mItemHeight);
                        NumberPickerView.this.mScroller.startScroll(0, NumberPickerView.this.mCurrDrawGlobalY, 0, NumberPickerView.this.mCurrDrawFirstItemY + NumberPickerView.this.mItemHeight, i * 3);
                        NumberPickerView numberPickerView = NumberPickerView.this;
                        willPickIndexByGlobalY = numberPickerView.getWillPickIndexByGlobalY(numberPickerView.mCurrDrawGlobalY + NumberPickerView.this.mItemHeight + NumberPickerView.this.mCurrDrawFirstItemY);
                    } else {
                        i = (int) (((-NumberPickerView.this.mCurrDrawFirstItemY) * 300.0f) / NumberPickerView.this.mItemHeight);
                        NumberPickerView.this.mScroller.startScroll(0, NumberPickerView.this.mCurrDrawGlobalY, 0, NumberPickerView.this.mCurrDrawFirstItemY, i * 3);
                        NumberPickerView numberPickerView2 = NumberPickerView.this;
                        willPickIndexByGlobalY = numberPickerView2.getWillPickIndexByGlobalY(numberPickerView2.mCurrDrawGlobalY + NumberPickerView.this.mCurrDrawFirstItemY);
                    }
                    i3 = i;
                    NumberPickerView.this.postInvalidate();
                } else {
                    NumberPickerView.this.onScrollStateChange(0);
                    NumberPickerView numberPickerView3 = NumberPickerView.this;
                    willPickIndexByGlobalY = numberPickerView3.getWillPickIndexByGlobalY(numberPickerView3.mCurrDrawGlobalY);
                }
                NumberPickerView numberPickerView4 = NumberPickerView.this;
                Message msg2 = numberPickerView4.getMsg(2, numberPickerView4.mPrevPickedIndex, willPickIndexByGlobalY, msg.obj);
                if (NumberPickerView.this.mRespondChangeInMainThread) {
                    NumberPickerView.this.mHandlerInMainThread.sendMessageDelayed(msg2, i3 * 2);
                } else {
                    NumberPickerView.this.mHandlerInNewThread.sendMessageDelayed(msg2, i3 * 2);
                }
            }
        };
        this.mHandlerInMainThread = new Handler() { // from class: com.byd.profile.view.NumberPickerView.2
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int i = msg.what;
                if (i == 2) {
                    NumberPickerView.this.respondPickedValueChanged(msg.arg1, msg.arg2, msg.obj);
                } else {
                    if (i != 3) {
                        return;
                    }
                    NumberPickerView.this.requestLayout();
                }
            }
        };
    }

    private void respondPickedValueChangedInScrolling(int oldVal, int newVal) {
        this.mOnValueChangeListenerInScrolling.onValueChangeInScrolling(this, oldVal, newVal);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        updateMaxWHOfDisplayedValues(false);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onSizeChanged(int r1, int r2, int r3, int r4) {
        /*
            r0 = this;
            super.onSizeChanged(r1, r2, r3, r4)
            r0.mViewWidth = r1
            r0.mViewHeight = r2
            int r3 = r0.mShownCount
            int r2 = r2 / r3
            r0.mItemHeight = r2
            int r2 = r0.getPaddingLeft()
            int r1 = r1 + r2
            int r2 = r0.getPaddingRight()
            int r1 = r1 - r2
            float r1 = (float) r1
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            r0.mViewCenterX = r1
            int r1 = r0.getOneRecycleSize()
            r2 = 0
            r3 = 1
            if (r1 <= r3) goto L3d
            boolean r1 = r0.mHasInit
            if (r1 == 0) goto L30
            int r1 = r0.getValue()
            int r4 = r0.mMinValue
            int r1 = r1 - r4
            goto L3e
        L30:
            boolean r1 = r0.mCurrentItemIndexEffect
            if (r1 == 0) goto L3d
            int r1 = r0.mCurrDrawFirstItemIndex
            int r4 = r0.mShownCount
            int r4 = r4 - r3
            int r4 = r4 / 2
            int r1 = r1 + r4
            goto L3e
        L3d:
            r1 = r2
        L3e:
            boolean r4 = r0.mWrapSelectorWheel
            if (r4 == 0) goto L47
            boolean r4 = r0.mWrapSelectorWheelCheck
            if (r4 == 0) goto L47
            r2 = r3
        L47:
            r0.correctPositionByDefaultValue(r1, r2)
            r0.updateFontAttr()
            r0.updateNotWrapYLimit()
            r0.updateDividerAttr()
            r0.mHasInit = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.profile.view.NumberPickerView.onSizeChanged(int, int, int, int):void");
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread == null || !handlerThread.isAlive()) {
            initHandler();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandlerThread.quit();
        if (this.mItemHeight == 0) {
            return;
        }
        if (!this.mScroller.isFinished()) {
            this.mScroller.abortAnimation();
            this.mCurrDrawGlobalY = this.mScroller.getCurrY();
            calculateFirstItemParameterByGlobalY();
            int i = this.mCurrDrawFirstItemY;
            if (i != 0) {
                int i2 = this.mItemHeight;
                if (i < (-i2) / 2) {
                    this.mCurrDrawGlobalY = this.mCurrDrawGlobalY + i2 + i;
                } else {
                    this.mCurrDrawGlobalY += i;
                }
                calculateFirstItemParameterByGlobalY();
            }
            onScrollStateChange(0);
        }
        int willPickIndexByGlobalY = getWillPickIndexByGlobalY(this.mCurrDrawGlobalY);
        int i3 = this.mPrevPickedIndex;
        if (willPickIndexByGlobalY != i3 && this.mRespondChangeOnDetach) {
            try {
                OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
                if (onValueChangeListener != null) {
                    int i4 = this.mMinValue;
                    onValueChangeListener.onValueChange(this, i3 + i4, i4 + willPickIndexByGlobalY);
                }
                OnValueChangeListenerRelativeToRaw onValueChangeListenerRelativeToRaw = this.mOnValueChangeListenerRaw;
                if (onValueChangeListenerRelativeToRaw != null) {
                    onValueChangeListenerRelativeToRaw.onValueChangeRelativeToRaw(this, this.mPrevPickedIndex, willPickIndexByGlobalY, this.mDisplayedValues);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.mPrevPickedIndex = willPickIndexByGlobalY;
    }

    public int getOneRecycleSize() {
        return (this.mMaxShowIndex - this.mMinShowIndex) + 1;
    }

    public int getRawContentSize() {
        String[] strArr = this.mDisplayedValues;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    public void setDisplayedValuesAndPickedIndex(String[] newDisplayedValues, int pickedIndex, boolean needRefresh) {
        stopScrolling();
        if (newDisplayedValues == null) {
            throw new IllegalArgumentException("newDisplayedValues should not be null.");
        }
        if (pickedIndex < 0) {
            throw new IllegalArgumentException("pickedIndex should not be negative, now pickedIndex is " + pickedIndex);
        }
        updateContent(newDisplayedValues);
        updateMaxWHOfDisplayedValues(true);
        updateNotWrapYLimit();
        updateValue();
        this.mPrevPickedIndex = this.mMinShowIndex + pickedIndex;
        correctPositionByDefaultValue(pickedIndex, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
        if (needRefresh) {
            this.mHandlerInNewThread.sendMessageDelayed(getMsg(1), 0L);
            postInvalidate();
        }
    }

    public void setDisplayedValues(String[] newDisplayedValues, boolean needRefresh) {
        setDisplayedValuesAndPickedIndex(newDisplayedValues, 0, needRefresh);
    }

    public void setDisplayedValues(String[] newDisplayedValues) {
        stopRefreshing();
        stopScrolling();
        if (newDisplayedValues == null) {
            throw new IllegalArgumentException("newDisplayedValues should not be null.");
        }
        if ((this.mMaxValue - this.mMinValue) + 1 > newDisplayedValues.length) {
            throw new IllegalArgumentException("mMaxValue - mMinValue + 1 should not be greater than mDisplayedValues.length, now ((mMaxValue - mMinValue + 1) is " + ((this.mMaxValue - this.mMinValue) + 1) + " newDisplayedValues.length is " + newDisplayedValues.length + ", you need to set MaxValue and MinValue before setDisplayedValues(String[])");
        }
        updateContent(newDisplayedValues);
        updateMaxWHOfDisplayedValues(true);
        this.mPrevPickedIndex = this.mMinShowIndex + 0;
        correctPositionByDefaultValue(0, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
        postInvalidate();
        this.mHandlerInMainThread.sendEmptyMessage(3);
    }

    public void setDisplayedValues(int[] newDisplayedValues) {
        String[] strArr = new String[newDisplayedValues.length];
        for (int i = 0; i < newDisplayedValues.length; i++) {
            strArr[i] = String.valueOf(newDisplayedValues[i]);
        }
        setDisplayedValues(strArr);
    }

    public String[] getDisplayedValues() {
        return this.mDisplayedValues;
    }

    public void setWrapSelectorWheel(boolean wrapSelectorWheel) {
        if (this.mWrapSelectorWheel != wrapSelectorWheel) {
            if (!wrapSelectorWheel) {
                if (this.mScrollState == 0) {
                    internalSetWrapToLinear();
                    return;
                } else {
                    this.mPendingWrapToLinear = true;
                    return;
                }
            }
            this.mWrapSelectorWheel = wrapSelectorWheel;
            updateWrapStateByContent();
            postInvalidate();
        }
    }

    public void smoothScrollToValue(int toValue) {
        smoothScrollToValue(getValue(), toValue, true);
    }

    public void smoothScrollToValue(int toValue, boolean needRespond) {
        smoothScrollToValue(getValue(), toValue, needRespond);
    }

    public void smoothScrollToValue(int fromValue, int toValue) {
        smoothScrollToValue(fromValue, toValue, true);
    }

    public void smoothScrollToValue(int fromValue, int toValue, boolean needRespond) {
        int i;
        int iRefineValueByLimit = refineValueByLimit(fromValue, this.mMinValue, this.mMaxValue, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
        int iRefineValueByLimit2 = refineValueByLimit(toValue, this.mMinValue, this.mMaxValue, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
        if (this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck) {
            i = iRefineValueByLimit2 - iRefineValueByLimit;
            int oneRecycleSize = getOneRecycleSize() / 2;
            if (i < (-oneRecycleSize) || oneRecycleSize < i) {
                int oneRecycleSize2 = getOneRecycleSize();
                i = i > 0 ? i - oneRecycleSize2 : i + oneRecycleSize2;
            }
        } else {
            i = iRefineValueByLimit2 - iRefineValueByLimit;
        }
        setValue(iRefineValueByLimit);
        if (iRefineValueByLimit == iRefineValueByLimit2) {
            return;
        }
        scrollByIndexSmoothly(i, needRespond);
    }

    public void refreshByNewDisplayedValues(String[] display) {
        int minValue = getMinValue();
        int maxValue = (getMaxValue() - minValue) + 1;
        int length = display.length - 1;
        if ((length - minValue) + 1 > maxValue) {
            setDisplayedValues(display);
            setMaxValue(length);
        } else {
            setMaxValue(length);
            setDisplayedValues(display);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void respondPickedValueChanged(int oldVal, int newVal, Object respondChange) {
        onScrollStateChange(0);
        if (oldVal != newVal && (respondChange == null || !(respondChange instanceof Boolean) || ((Boolean) respondChange).booleanValue())) {
            OnValueChangeListener onValueChangeListener = this.mOnValueChangeListener;
            if (onValueChangeListener != null) {
                int i = this.mMinValue;
                onValueChangeListener.onValueChange(this, oldVal + i, i + newVal);
            }
            OnValueChangeListenerRelativeToRaw onValueChangeListenerRelativeToRaw = this.mOnValueChangeListenerRaw;
            if (onValueChangeListenerRelativeToRaw != null) {
                onValueChangeListenerRelativeToRaw.onValueChangeRelativeToRaw(this, oldVal, newVal, this.mDisplayedValues);
            }
        }
        this.mPrevPickedIndex = newVal;
        if (this.mPendingWrapToLinear) {
            this.mPendingWrapToLinear = false;
            internalSetWrapToLinear();
        }
    }

    private void scrollByIndexSmoothly(int deltaIndex) {
        scrollByIndexSmoothly(deltaIndex, true);
    }

    private void scrollByIndexSmoothly(int deltaIndex, boolean needRespond) {
        int pickedIndexRelativeToRaw;
        int pickedIndexRelativeToRaw2;
        int i;
        int i2;
        if ((!this.mWrapSelectorWheel || !this.mWrapSelectorWheelCheck) && ((pickedIndexRelativeToRaw2 = (pickedIndexRelativeToRaw = getPickedIndexRelativeToRaw()) + deltaIndex) > (i = this.mMaxShowIndex) || pickedIndexRelativeToRaw2 < (i = this.mMinShowIndex))) {
            deltaIndex = i - pickedIndexRelativeToRaw;
        }
        int i3 = this.mCurrDrawFirstItemY;
        int i4 = this.mItemHeight;
        if (i3 < (-i4) / 2) {
            int i5 = i4 + i3;
            int i6 = (int) (((i3 + i4) * 300.0f) / i4);
            i2 = deltaIndex < 0 ? (-i6) - (deltaIndex * 300) : i6 + (deltaIndex * 300);
            i3 = i5;
        } else {
            int i7 = (int) (((-i3) * 300.0f) / i4);
            i2 = deltaIndex < 0 ? i7 - (deltaIndex * 300) : i7 + (deltaIndex * 300);
        }
        int i8 = i3 + (deltaIndex * i4);
        if (i2 < 300) {
            i2 = 300;
        }
        if (i2 > 600) {
            i2 = 600;
        }
        this.mScroller.startScroll(0, this.mCurrDrawGlobalY, 0, i8, i2);
        if (needRespond) {
            this.mHandlerInNewThread.sendMessageDelayed(getMsg(1), i2 / 4);
        } else {
            this.mHandlerInNewThread.sendMessageDelayed(getMsg(1, 0, 0, new Boolean(needRespond)), i2 / 4);
        }
        postInvalidate();
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public void setMinValue(int minValue) {
        this.mMinValue = minValue;
        this.mMinShowIndex = 0;
        updateNotWrapYLimit();
    }

    public void setMaxValue(int maxValue) {
        String[] strArr = this.mDisplayedValues;
        if (strArr == null) {
            throw new IllegalArgumentException("mDisplayedValues should not be null");
        }
        int i = this.mMinValue;
        if ((maxValue - i) + 1 > strArr.length) {
            throw new IllegalArgumentException("(maxValue - mMinValue + 1) should not be greater than mDisplayedValues.length now  (maxValue - mMinValue + 1) is " + ((maxValue - this.mMinValue) + 1) + " and mDisplayedValues.length is " + this.mDisplayedValues.length);
        }
        this.mMaxValue = maxValue;
        int i2 = this.mMinShowIndex;
        int i3 = (maxValue - i) + i2;
        this.mMaxShowIndex = i3;
        setMinAndMaxShowIndex(i2, i3);
        updateNotWrapYLimit();
    }

    public void setValue(int value) {
        int i = this.mMinValue;
        if (value < i) {
            throw new IllegalArgumentException("should not set a value less than mMinValue, value is " + value);
        }
        if (value > this.mMaxValue) {
            throw new IllegalArgumentException("should not set a value greater than mMaxValue, value is " + value);
        }
        setPickedIndexRelativeToRaw(value - i);
    }

    public int getValue() {
        return getPickedIndexRelativeToRaw() + this.mMinValue;
    }

    public String getContentByCurrValue() {
        return this.mDisplayedValues[getValue() - this.mMinValue];
    }

    public boolean getWrapSelectorWheel() {
        return this.mWrapSelectorWheel;
    }

    public boolean getWrapSelectorWheelAbsolutely() {
        return this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck;
    }

    public void setHintText(String hintText) {
        if (isStringEqual(this.mHintText, hintText)) {
            return;
        }
        this.mHintText = hintText;
        this.mTextSizeHintCenterYOffset = getTextCenterYOffset(this.mPaintHint.getFontMetrics());
        this.mWidthOfHintText = getTextWidth(this.mHintText, this.mPaintHint);
        this.mHandlerInMainThread.sendEmptyMessage(3);
    }

    public void setPickedIndexRelativeToMin(int pickedIndexToMin) {
        if (pickedIndexToMin < 0 || pickedIndexToMin >= getOneRecycleSize()) {
            return;
        }
        this.mPrevPickedIndex = this.mMinShowIndex + pickedIndexToMin;
        correctPositionByDefaultValue(pickedIndexToMin, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
        postInvalidate();
    }

    public void setNormalTextColor(int normalTextColor) {
        if (this.mTextColorNormal == normalTextColor) {
            return;
        }
        this.mTextColorNormal = normalTextColor;
        postInvalidate();
    }

    public void setSelectedTextColor(int selectedTextColor) {
        if (this.mTextColorSelected == selectedTextColor) {
            return;
        }
        this.mTextColorSelected = selectedTextColor;
        postInvalidate();
    }

    public void setHintTextColor(int hintTextColor) {
        if (this.mTextColorHint == hintTextColor) {
            return;
        }
        this.mTextColorHint = hintTextColor;
        this.mPaintHint.setColor(hintTextColor);
        postInvalidate();
    }

    public void setDividerColor(int dividerColor) {
        if (this.mDividerColor == dividerColor) {
            return;
        }
        this.mDividerColor = dividerColor;
        this.mPaintDivider.setColor(dividerColor);
        postInvalidate();
    }

    public void setPickedIndexRelativeToRaw(int pickedIndexToRaw) {
        int i = this.mMinShowIndex;
        if (i <= -1 || i > pickedIndexToRaw || pickedIndexToRaw > this.mMaxShowIndex) {
            return;
        }
        this.mPrevPickedIndex = pickedIndexToRaw;
        correctPositionByDefaultValue(pickedIndexToRaw - i, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
        postInvalidate();
    }

    public int getPickedIndexRelativeToRaw() {
        int i = this.mCurrDrawFirstItemY;
        if (i != 0) {
            int i2 = this.mItemHeight;
            if (i < (-i2) / 2) {
                return getWillPickIndexByGlobalY(this.mCurrDrawGlobalY + i2 + i);
            }
            return getWillPickIndexByGlobalY(this.mCurrDrawGlobalY + i);
        }
        return getWillPickIndexByGlobalY(this.mCurrDrawGlobalY);
    }

    public void setMinAndMaxShowIndex(int minShowIndex, int maxShowIndex) {
        setMinAndMaxShowIndex(minShowIndex, maxShowIndex, true);
    }

    public void setMinAndMaxShowIndex(int minShowIndex, int maxShowIndex, boolean needRefresh) {
        if (minShowIndex > maxShowIndex) {
            throw new IllegalArgumentException("minShowIndex should be less than maxShowIndex, minShowIndex is " + minShowIndex + ", maxShowIndex is " + maxShowIndex + Constants.CHAR_DOT);
        }
        String[] strArr = this.mDisplayedValues;
        if (strArr == null) {
            throw new IllegalArgumentException("mDisplayedValues should not be null, you need to set mDisplayedValues first.");
        }
        if (minShowIndex < 0) {
            throw new IllegalArgumentException("minShowIndex should not be less than 0, now minShowIndex is " + minShowIndex);
        }
        if (minShowIndex > strArr.length - 1) {
            throw new IllegalArgumentException("minShowIndex should not be greater than (mDisplayedValues.length - 1), now (mDisplayedValues.length - 1) is " + (this.mDisplayedValues.length - 1) + " minShowIndex is " + minShowIndex);
        }
        if (maxShowIndex < 0) {
            throw new IllegalArgumentException("maxShowIndex should not be less than 0, now maxShowIndex is " + maxShowIndex);
        }
        if (maxShowIndex > strArr.length - 1) {
            throw new IllegalArgumentException("maxShowIndex should not be greater than (mDisplayedValues.length - 1), now (mDisplayedValues.length - 1) is " + (this.mDisplayedValues.length - 1) + " maxShowIndex is " + maxShowIndex);
        }
        this.mMinShowIndex = minShowIndex;
        this.mMaxShowIndex = maxShowIndex;
        if (needRefresh) {
            this.mPrevPickedIndex = minShowIndex + 0;
            correctPositionByDefaultValue(0, this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
            postInvalidate();
        }
    }

    public void setFriction(float friction) {
        if (friction <= 0.0f) {
            throw new IllegalArgumentException("you should set a a positive float friction, now friction is " + friction);
        }
        ViewConfiguration.get(getContext());
        this.mFriction = ViewConfiguration.getScrollFriction() / friction;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScrollStateChange(int scrollState) {
        if (this.mScrollState == scrollState) {
            return;
        }
        this.mScrollState = scrollState;
        OnScrollListener onScrollListener = this.mOnScrollListener;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChange(this, scrollState);
        }
    }

    public void setOnScrollListener(OnScrollListener listener) {
        this.mOnScrollListener = listener;
    }

    public void setOnValueChangedListener(OnValueChangeListener listener) {
        this.mOnValueChangeListener = listener;
    }

    public void setOnValueChangedListenerRelativeToRaw(OnValueChangeListenerRelativeToRaw listener) {
        this.mOnValueChangeListenerRaw = listener;
    }

    public void setOnValueChangeListenerInScrolling(OnValueChangeListenerInScrolling listener) {
        this.mOnValueChangeListenerInScrolling = listener;
    }

    public void setContentTextTypeface(Typeface typeface) {
        this.mPaintText.setTypeface(typeface);
    }

    public void setHintTextTypeface(Typeface typeface) {
        this.mPaintHint.setTypeface(typeface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getWillPickIndexByGlobalY(int globalY) {
        int i = this.mItemHeight;
        boolean z = false;
        if (i == 0) {
            return 0;
        }
        int i2 = (globalY / i) + (this.mShownCount / 2);
        int oneRecycleSize = getOneRecycleSize();
        if (this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck) {
            z = true;
        }
        int indexByRawIndex = getIndexByRawIndex(i2, oneRecycleSize, z);
        if (indexByRawIndex >= 0 && indexByRawIndex < getOneRecycleSize()) {
            return indexByRawIndex + this.mMinShowIndex;
        }
        throw new IllegalArgumentException("getWillPickIndexByGlobalY illegal index : " + indexByRawIndex + " getOneRecycleSize() : " + getOneRecycleSize() + " mWrapSelectorWheel : " + this.mWrapSelectorWheel);
    }

    private int getIndexByRawIndex(int index, int size, boolean wrap) {
        if (size <= 0) {
            return 0;
        }
        if (!wrap) {
            return index;
        }
        int i = index % size;
        return i < 0 ? i + size : i;
    }

    private void internalSetWrapToLinear() {
        correctPositionByDefaultValue(getPickedIndexRelativeToRaw() - this.mMinShowIndex, false);
        this.mWrapSelectorWheel = false;
        postInvalidate();
    }

    private void updateDividerAttr() {
        int i = this.mShownCount / 2;
        this.mDividerIndex0 = i;
        this.mDividerIndex1 = i + 1;
        int i2 = this.mViewHeight;
        this.dividerY0 = (i * i2) / r0;
        this.dividerY1 = (r2 * i2) / r0;
        if (this.mDividerMarginL < 0) {
            this.mDividerMarginL = 0;
        }
        if (this.mDividerMarginR < 0) {
            this.mDividerMarginR = 0;
        }
        if (this.mDividerMarginL + this.mDividerMarginR != 0 && getPaddingLeft() + this.mDividerMarginL >= (this.mViewWidth - getPaddingRight()) - this.mDividerMarginR) {
            int paddingLeft = getPaddingLeft() + this.mDividerMarginL + getPaddingRight();
            int i3 = this.mDividerMarginR;
            int i4 = (paddingLeft + i3) - this.mViewWidth;
            int i5 = this.mDividerMarginL;
            float f = i4;
            this.mDividerMarginL = (int) (i5 - ((i5 * f) / (i5 + i3)));
            this.mDividerMarginR = (int) (i3 - ((f * i3) / (r2 + i3)));
        }
    }

    private void updateFontAttr() {
        int i = this.mTextSizeNormal;
        int i2 = this.mItemHeight;
        if (i > i2) {
            this.mTextSizeNormal = i2;
        }
        if (this.mTextSizeSelected > i2) {
            this.mTextSizeSelected = i2;
        }
        Paint paint = this.mPaintHint;
        if (paint == null) {
            throw new IllegalArgumentException("mPaintHint should not be null.");
        }
        paint.setTextSize(this.mTextSizeHint);
        this.mTextSizeHintCenterYOffset = getTextCenterYOffset(this.mPaintHint.getFontMetrics());
        this.mWidthOfHintText = getTextWidth(this.mHintText, this.mPaintHint);
        TextPaint textPaint = this.mPaintText;
        if (textPaint == null) {
            throw new IllegalArgumentException("mPaintText should not be null.");
        }
        textPaint.setTextSize(this.mTextSizeSelected);
        this.mTextSizeSelectedCenterYOffset = getTextCenterYOffset(this.mPaintText.getFontMetrics());
        this.mPaintText.setTextSize(this.mTextSizeNormal);
        this.mTextSizeNormalCenterYOffset = getTextCenterYOffset(this.mPaintText.getFontMetrics());
    }

    private void updateNotWrapYLimit() {
        this.mNotWrapLimitYTop = 0;
        this.mNotWrapLimitYBottom = (-this.mShownCount) * this.mItemHeight;
        if (this.mDisplayedValues != null) {
            int oneRecycleSize = getOneRecycleSize();
            int i = this.mShownCount;
            int i2 = this.mItemHeight;
            this.mNotWrapLimitYTop = ((oneRecycleSize - (i / 2)) - 1) * i2;
            this.mNotWrapLimitYBottom = (-(i / 2)) * i2;
        }
    }

    private int limitY(int currDrawGlobalYPreferred) {
        if (this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck) {
            return currDrawGlobalYPreferred;
        }
        int i = this.mNotWrapLimitYBottom;
        return (currDrawGlobalYPreferred >= i && currDrawGlobalYPreferred <= (i = this.mNotWrapLimitYTop)) ? currDrawGlobalYPreferred : i;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0063  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.profile.view.NumberPickerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void click(MotionEvent event) {
        float y = event.getY();
        for (int i = 0; i < this.mShownCount; i++) {
            int i2 = this.mItemHeight;
            if (i2 * i <= y && y < i2 * (i + 1)) {
                clickItem(i);
                return;
            }
        }
    }

    private void clickItem(int showCountIndex) {
        int i;
        if (showCountIndex < 0 || showCountIndex >= (i = this.mShownCount)) {
            return;
        }
        scrollByIndexSmoothly(showCountIndex - (i / 2));
    }

    private float getTextCenterYOffset(Paint.FontMetrics fontMetrics) {
        if (fontMetrics == null) {
            return 0.0f;
        }
        return Math.abs(fontMetrics.top + fontMetrics.bottom) / 2.0f;
    }

    private void correctPositionByDefaultValue(int defaultPickedIndex, boolean wrap) {
        int i = defaultPickedIndex - ((this.mShownCount - 1) / 2);
        this.mCurrDrawFirstItemIndex = i;
        int indexByRawIndex = getIndexByRawIndex(i, getOneRecycleSize(), wrap);
        this.mCurrDrawFirstItemIndex = indexByRawIndex;
        int i2 = this.mItemHeight;
        if (i2 == 0) {
            this.mCurrentItemIndexEffect = true;
            return;
        }
        this.mCurrDrawGlobalY = i2 * indexByRawIndex;
        int i3 = indexByRawIndex + (this.mShownCount / 2);
        this.mInScrollingPickedOldValue = i3;
        int oneRecycleSize = i3 % getOneRecycleSize();
        this.mInScrollingPickedOldValue = oneRecycleSize;
        if (oneRecycleSize < 0) {
            this.mInScrollingPickedOldValue = oneRecycleSize + getOneRecycleSize();
        }
        this.mInScrollingPickedNewValue = this.mInScrollingPickedOldValue;
        calculateFirstItemParameterByGlobalY();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mItemHeight != 0 && this.mScroller.computeScrollOffset()) {
            this.mCurrDrawGlobalY = this.mScroller.getCurrY();
            calculateFirstItemParameterByGlobalY();
            postInvalidate();
        }
    }

    private void calculateFirstItemParameterByGlobalY() {
        int iFloor = (int) Math.floor(this.mCurrDrawGlobalY / this.mItemHeight);
        this.mCurrDrawFirstItemIndex = iFloor;
        int i = this.mCurrDrawGlobalY;
        int i2 = this.mItemHeight;
        int i3 = -(i - (iFloor * i2));
        this.mCurrDrawFirstItemY = i3;
        if (this.mOnValueChangeListenerInScrolling != null) {
            if ((-i3) > i2 / 2) {
                this.mInScrollingPickedNewValue = iFloor + 1 + (this.mShownCount / 2);
            } else {
                this.mInScrollingPickedNewValue = iFloor + (this.mShownCount / 2);
            }
            int oneRecycleSize = this.mInScrollingPickedNewValue % getOneRecycleSize();
            this.mInScrollingPickedNewValue = oneRecycleSize;
            if (oneRecycleSize < 0) {
                this.mInScrollingPickedNewValue = oneRecycleSize + getOneRecycleSize();
            }
            int i4 = this.mInScrollingPickedOldValue;
            int i5 = this.mInScrollingPickedNewValue;
            if (i4 != i5) {
                int i6 = this.mMinValue;
                respondPickedValueChangedInScrolling(i4 + i6, i5 + i6);
            }
            this.mInScrollingPickedOldValue = this.mInScrollingPickedNewValue;
        }
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void updateMaxWHOfDisplayedValues(boolean needRequestLayout) {
        updateMaxWidthOfDisplayedValues();
        updateMaxHeightOfDisplayedValues();
        if (needRequestLayout) {
            if (this.mSpecModeW == Integer.MIN_VALUE || this.mSpecModeH == Integer.MIN_VALUE) {
                this.mHandlerInMainThread.sendEmptyMessage(3);
            }
        }
    }

    private int measureWidth(int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        this.mSpecModeW = mode;
        int size = View.MeasureSpec.getSize(measureSpec);
        if (mode == 1073741824) {
            return size;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight() + Math.max(this.mMaxWidthOfAlterArrayWithMeasureHint, Math.max(this.mMaxWidthOfDisplayedValues, this.mMaxWidthOfAlterArrayWithoutMeasureHint) + (((Math.max(this.mWidthOfHintText, this.mWidthOfAlterHint) != 0 ? this.mMarginStartOfHint : 0) + Math.max(this.mWidthOfHintText, this.mWidthOfAlterHint) + (Math.max(this.mWidthOfHintText, this.mWidthOfAlterHint) == 0 ? 0 : this.mMarginEndOfHint) + (this.mItemPaddingHorizontal * 2)) * 2));
        return mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
    }

    private int measureHeight(int measureSpec) {
        int mode = View.MeasureSpec.getMode(measureSpec);
        this.mSpecModeH = mode;
        int size = View.MeasureSpec.getSize(measureSpec);
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = getPaddingTop() + getPaddingBottom() + (this.mShownCount * (this.mMaxHeightOfDisplayedValues + (this.mItemPaddingVertical * 2)));
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSelectedItemBackground(canvas);
        drawContent(canvas);
        drawLine(canvas);
        drawHint(canvas);
    }

    private void drawSelectedItemBackground(Canvas canvas) {
        if (this.mSelectedItemBackground != null) {
            canvas.save();
            this.mSelectedItemBackground.setBounds(getPaddingLeft() + this.mDividerMarginL, (int) this.dividerY0, (this.mViewWidth - getPaddingRight()) - this.mDividerMarginR, (int) this.dividerY1);
            this.mSelectedItemBackground.draw(canvas);
            canvas.restore();
        }
    }

    private void drawContent(Canvas canvas) {
        int evaluateColor;
        float evaluateSize;
        float evaluateSize2;
        int i = 0;
        while (true) {
            if (i >= this.mShownCount + 1) {
                return;
            }
            float f = this.mCurrDrawFirstItemY + (this.mItemHeight * i);
            int indexByRawIndex = getIndexByRawIndex(this.mCurrDrawFirstItemIndex + i, getOneRecycleSize(), this.mWrapSelectorWheel && this.mWrapSelectorWheelCheck);
            if (i == this.mShownCount / 2) {
                float f2 = (this.mCurrDrawFirstItemY + r4) / this.mItemHeight;
                evaluateColor = getEvaluateColor(f2, this.mTextColorNormal, this.mTextColorSelected);
                evaluateSize = getEvaluateSize(f2, this.mTextSizeNormal, this.mTextSizeSelected);
                evaluateSize2 = getEvaluateSize(f2, this.mTextSizeNormalCenterYOffset, this.mTextSizeSelectedCenterYOffset);
            } else {
                evaluateColor = this.mTextColorNormal;
                evaluateSize = this.mTextSizeNormal;
                evaluateSize2 = this.mTextSizeNormalCenterYOffset;
            }
            this.mPaintText.setColor(evaluateColor);
            this.mPaintText.setTextSize(evaluateSize);
            if (indexByRawIndex >= 0 && indexByRawIndex < getOneRecycleSize()) {
                CharSequence charSequenceEllipsize = this.mDisplayedValues[indexByRawIndex + this.mMinShowIndex];
                if (this.mTextEllipsize != null) {
                    charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.mPaintText, getWidth() - (this.mItemPaddingHorizontal * 2), getEllipsizeType());
                }
                canvas.drawText(charSequenceEllipsize.toString(), this.mViewCenterX, f + (this.mItemHeight / 2) + evaluateSize2, this.mPaintText);
            } else if (!TextUtils.isEmpty(this.mEmptyItemHint)) {
                canvas.drawText(this.mEmptyItemHint, this.mViewCenterX, f + (this.mItemHeight / 2) + evaluateSize2, this.mPaintText);
            }
            i++;
        }
    }

    private TextUtils.TruncateAt getEllipsizeType() {
        String str = this.mTextEllipsize;
        str.hashCode();
        switch (str) {
            case "middle":
                return TextUtils.TruncateAt.MIDDLE;
            case "end":
                return TextUtils.TruncateAt.END;
            case "start":
                return TextUtils.TruncateAt.START;
            default:
                throw new IllegalArgumentException("Illegal text ellipsize type.");
        }
    }

    private void drawLine(Canvas canvas) {
        if (this.mShowDivider) {
            canvas.drawLine(getPaddingLeft() + this.mDividerMarginL, this.dividerY0, (this.mViewWidth - getPaddingRight()) - this.mDividerMarginR, this.dividerY0, this.mPaintDivider);
            canvas.drawLine(getPaddingLeft() + this.mDividerMarginL, this.dividerY1, (this.mViewWidth - getPaddingRight()) - this.mDividerMarginR, this.dividerY1, this.mPaintDivider);
        }
    }

    private void drawHint(Canvas canvas) {
        if (TextUtils.isEmpty(this.mHintText)) {
            return;
        }
        canvas.drawText(this.mHintText, this.mViewCenterX + ((this.mMaxWidthOfDisplayedValues + this.mWidthOfHintText) / 2) + this.mMarginStartOfHint, ((this.dividerY0 + this.dividerY1) / 2.0f) + this.mTextSizeHintCenterYOffset, this.mPaintHint);
    }

    private void updateMaxWidthOfDisplayedValues() {
        float textSize = this.mPaintText.getTextSize();
        this.mPaintText.setTextSize(this.mTextSizeSelected);
        this.mMaxWidthOfDisplayedValues = getMaxWidthOfTextArray(this.mDisplayedValues, this.mPaintText);
        this.mMaxWidthOfAlterArrayWithMeasureHint = getMaxWidthOfTextArray(this.mAlterTextArrayWithMeasureHint, this.mPaintText);
        this.mMaxWidthOfAlterArrayWithoutMeasureHint = getMaxWidthOfTextArray(this.mAlterTextArrayWithoutMeasureHint, this.mPaintText);
        this.mPaintText.setTextSize(this.mTextSizeHint);
        this.mWidthOfAlterHint = getTextWidth(this.mAlterHint, this.mPaintText);
        this.mPaintText.setTextSize(textSize);
    }

    private int getMaxWidthOfTextArray(CharSequence[] array, Paint paint) {
        if (array == null) {
            return 0;
        }
        int iMax = 0;
        for (CharSequence charSequence : array) {
            if (charSequence != null) {
                iMax = Math.max(getTextWidth(charSequence, paint), iMax);
            }
        }
        return iMax;
    }

    private int getTextWidth(CharSequence text, Paint paint) {
        Integer num;
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        String string = text.toString();
        if (this.mTextWidthCache.containsKey(string) && (num = this.mTextWidthCache.get(string)) != null) {
            return num.intValue();
        }
        int iMeasureText = (int) (paint.measureText(string) + 0.5f);
        this.mTextWidthCache.put(string, Integer.valueOf(iMeasureText));
        return iMeasureText;
    }

    private void updateMaxHeightOfDisplayedValues() {
        float textSize = this.mPaintText.getTextSize();
        this.mPaintText.setTextSize(this.mTextSizeSelected);
        this.mMaxHeightOfDisplayedValues = (int) (((double) (this.mPaintText.getFontMetrics().bottom - this.mPaintText.getFontMetrics().top)) + 0.5d);
        this.mPaintText.setTextSize(textSize);
    }

    private void updateContentAndIndex(String[] newDisplayedValues) {
        this.mMinShowIndex = 0;
        this.mMaxShowIndex = newDisplayedValues.length - 1;
        this.mDisplayedValues = newDisplayedValues;
        updateWrapStateByContent();
    }

    private void updateContent(String[] newDisplayedValues) {
        this.mDisplayedValues = newDisplayedValues;
        updateWrapStateByContent();
    }

    private void updateValue() {
        inflateDisplayedValuesIfNull();
        updateWrapStateByContent();
        this.mMinShowIndex = 0;
        this.mMaxShowIndex = this.mDisplayedValues.length - 1;
    }

    private void updateValueForInit() {
        inflateDisplayedValuesIfNull();
        updateWrapStateByContent();
        if (this.mMinShowIndex == -1) {
            this.mMinShowIndex = 0;
        }
        if (this.mMaxShowIndex == -1) {
            this.mMaxShowIndex = this.mDisplayedValues.length - 1;
        }
        setMinAndMaxShowIndex(this.mMinShowIndex, this.mMaxShowIndex, false);
    }

    private void inflateDisplayedValuesIfNull() {
        if (this.mDisplayedValues == null) {
            this.mDisplayedValues = new String[]{Constant.CarType.CAR_TYPE_FUEL};
        }
    }

    private void updateWrapStateByContent() {
        this.mWrapSelectorWheelCheck = this.mDisplayedValues.length > this.mShownCount;
    }

    private int refineValueByLimit(int value, int minValue, int maxValue, boolean wrap) {
        if (!wrap) {
            return value > maxValue ? maxValue : value < minValue ? minValue : value;
        }
        if (value > maxValue) {
            return (((value - maxValue) % getOneRecycleSize()) + minValue) - 1;
        }
        return value < minValue ? ((value - minValue) % getOneRecycleSize()) + maxValue + 1 : value;
    }

    private void stopRefreshing() {
        Handler handler = this.mHandlerInNewThread;
        if (handler != null) {
            handler.removeMessages(1);
        }
    }

    public void stopScrolling() {
        Scroller scroller = this.mScroller;
        if (scroller == null || scroller.isFinished()) {
            return;
        }
        Scroller scroller2 = this.mScroller;
        scroller2.startScroll(0, scroller2.getCurrY(), 0, 0, 1);
        this.mScroller.abortAnimation();
        postInvalidate();
    }

    public void stopScrollingAndCorrectPosition() {
        stopScrolling();
        Handler handler = this.mHandlerInNewThread;
        if (handler != null) {
            handler.sendMessageDelayed(getMsg(1), 0L);
        }
    }

    private Message getMsg(int what) {
        return getMsg(what, 0, 0, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Message getMsg(int what, int arg1, int arg2, Object obj) {
        Message messageObtain = Message.obtain();
        messageObtain.what = what;
        messageObtain.arg1 = arg1;
        messageObtain.arg2 = arg2;
        messageObtain.obj = obj;
        return messageObtain;
    }

    private boolean isStringEqual(String a, String b) {
        if (a == null) {
            return b == null;
        }
        return a.equals(b);
    }

    private int sp2px(Context context, float spValue) {
        return (int) ((spValue * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    private int dp2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    private String[] convertCharSequenceArrayToStringArray(CharSequence[] charSequences) {
        if (charSequences == null) {
            return null;
        }
        String[] strArr = new String[charSequences.length];
        for (int i = 0; i < charSequences.length; i++) {
            strArr[i] = charSequences[i].toString();
        }
        return strArr;
    }
}
