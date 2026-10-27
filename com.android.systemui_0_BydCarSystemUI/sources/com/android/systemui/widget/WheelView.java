package com.android.systemui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.android.systemui.Constants;
import com.android.systemui.R;
import com.android.systemui.statusbar.wm.TempData;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class WheelView extends View {
    public static final float SPEED = 10.0f;
    private boolean canScroll;
    private List<TempData> items;
    private boolean loop;
    private int mCurrentSelectedIndex;
    private float mFloatTextSize;
    private float mLastDownY;
    private float mMaxTextAlpha;
    private float mMaxTextSize;
    private float mMinTextAlpha;
    private float mMinTextSize;
    private float mMoveLen;
    private Paint mPaint;
    private OnSelectListener mSelectListener;
    private MyTimerTask mTask;
    private float mTextSpaceRatio;
    private int mViewHeight;
    private int mViewWidth;
    private Paint nPaint;
    private int normalTextColor;
    private int selectedTextColor;
    private Timer timer;
    private Handler updateHandler;

    public interface OnSelectListener {
        void onSelect(int index, String text);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performSelect() {
        OnSelectListener onSelectListener = this.mSelectListener;
        if (onSelectListener != null) {
            int i = this.mCurrentSelectedIndex;
            onSelectListener.onSelect(i, this.items.get(i).getKey());
        }
    }

    public WheelView(Context context) {
        this(context, null);
    }

    public WheelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.loop = true;
        this.mMoveLen = 0.0f;
        this.canScroll = true;
        this.updateHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.systemui.widget.WheelView.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (Math.abs(WheelView.this.mMoveLen) < 10.0f) {
                    WheelView.this.mMoveLen = 0.0f;
                    if (WheelView.this.mTask != null) {
                        WheelView.this.mTask.cancel();
                        WheelView.this.mTask = null;
                        WheelView.this.performSelect();
                    }
                } else {
                    WheelView.this.mMoveLen -= (WheelView.this.mMoveLen / Math.abs(WheelView.this.mMoveLen)) * 10.0f;
                }
                WheelView.this.invalidate();
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.wheel_view, defStyleAttr, 0);
        init(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void init(TypedArray a) {
        float fApplyDimension = TypedValue.applyDimension(2, 18.0f, getResources().getDisplayMetrics());
        float fApplyDimension2 = TypedValue.applyDimension(2, 14.0f, getResources().getDisplayMetrics());
        this.mMaxTextSize = a.getDimension(2, fApplyDimension);
        this.mMinTextSize = a.getDimension(4, fApplyDimension2);
        this.mFloatTextSize = a.getDimension(0, fApplyDimension2);
        this.mMaxTextAlpha = a.getInteger(1, 255);
        this.mMinTextAlpha = a.getInteger(3, 120);
        this.mTextSpaceRatio = a.getFloat(7, 1.6f);
        float f = this.mMaxTextAlpha;
        if (f > 255.0f) {
            f = 255.0f;
        }
        this.mMaxTextAlpha = f;
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.mMaxTextAlpha = f;
        float f2 = this.mMinTextAlpha;
        float f3 = f2 <= 255.0f ? f2 : 255.0f;
        this.mMinTextAlpha = f3;
        this.mMinTextAlpha = f3 >= 0.0f ? f3 : 0.0f;
        this.timer = new Timer();
        this.items = new ArrayList();
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getContext().getAssets(), "font/HFMoBestSans-R.otf");
        this.mPaint.setTypeface(typefaceCreateFromAsset);
        Paint paint2 = new Paint(1);
        this.nPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.nPaint.setTextAlign(Paint.Align.CENTER);
        this.nPaint.setTypeface(typefaceCreateFromAsset);
        this.normalTextColor = a.getColor(6, -13421773);
        int color = a.getColor(5, -10898788);
        this.selectedTextColor = color;
        setColors(this.normalTextColor, color);
    }

    public void setOnSelectListener(OnSelectListener listener) {
        this.mSelectListener = listener;
    }

    public void setTextSize(float mMaxTextSize, float mMinTextSize) {
        this.mMaxTextSize = mMaxTextSize;
        this.mMinTextSize = mMinTextSize;
        invalidate();
    }

    public void setTextAlpha(float mMaxTextAlpha, float mMinTextAlpha) {
        this.mMaxTextAlpha = mMaxTextAlpha;
        this.mMinTextAlpha = mMinTextAlpha;
        invalidate();
    }

    public String getItemAtPosition(int position) {
        List<TempData> list = this.items;
        return (list != null && position < list.size()) ? this.items.get(position).getKey() : "";
    }

    public void setItems(List<TempData> items) {
        boolean z = (items == null || items.isEmpty()) ? false : true;
        this.canScroll = z;
        if (z && items.size() > 1) {
            BydLogHelper.logD("setItems update " + items.get(items.size() - 1).getKey() + "---" + items.get(0).getKey());
        }
        this.items = items;
        invalidate();
    }

    public void setTextSpaceRatio(float mTextSpaceRatio) {
        this.mTextSpaceRatio = mTextSpaceRatio;
        invalidate();
    }

    public void setSelected(int selected) {
        this.mCurrentSelectedIndex = selected;
        if (this.loop) {
            int size = (this.items.size() / 2) - this.mCurrentSelectedIndex;
            int i = 0;
            if (size < 0) {
                while (i < (-size)) {
                    moveHeadToTail();
                    this.mCurrentSelectedIndex--;
                    i++;
                }
            } else if (size > 0) {
                while (i < size) {
                    moveTailToHead();
                    this.mCurrentSelectedIndex++;
                    i++;
                }
            }
        }
        invalidate();
        MyTimerTask myTimerTask = this.mTask;
        if (myTimerTask != null) {
            myTimerTask.cancel();
            this.mTask = null;
        }
        MyTimerTask myTimerTask2 = new MyTimerTask(this.updateHandler);
        this.mTask = myTimerTask2;
        this.timer.schedule(myTimerTask2, 0L, 10L);
    }

    public int getCurrentSelectedIndex() {
        BydLogHelper.logD("setTemp mCurrentSelectedIndex:" + this.mCurrentSelectedIndex);
        return this.mCurrentSelectedIndex;
    }

    public TempData getCurrentSelectedData() {
        BydLogHelper.logD("setTemp mCurrentSelectedIndex:" + this.mCurrentSelectedIndex);
        return this.items.get(this.mCurrentSelectedIndex);
    }

    public String getCurrentSelectedItem() {
        List<TempData> list = this.items;
        return (list == null || list.size() == 0) ? "" : this.items.get(this.mCurrentSelectedIndex).getKey();
    }

    private void moveHeadToTail() {
        if (this.loop) {
            TempData tempData = this.items.get(0);
            this.items.remove(0);
            this.items.add(tempData);
        }
    }

    private void moveTailToHead() {
        if (this.loop) {
            TempData tempData = this.items.get(r0.size() - 1);
            this.items.remove(r1.size() - 1);
            this.items.add(0, tempData);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mViewHeight = getMeasuredHeight();
        this.mViewWidth = getMeasuredWidth();
        invalidate();
    }

    public void setColors(int textColor, int selectedTextColor) {
        this.nPaint.setColor(textColor);
        this.mPaint.setColor(selectedTextColor);
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawData(canvas);
    }

    private void drawData(Canvas canvas) {
        List<TempData> list = this.items;
        if (list == null || list.isEmpty()) {
            return;
        }
        float fParabola = parabola(this.mViewHeight / 2.0f, this.mMoveLen);
        float f = this.mMaxTextSize;
        float f2 = this.mMinTextSize;
        float f3 = ((f - f2) * fParabola) + f2;
        this.mPaint.setTextSize(f3);
        Paint paint = this.mPaint;
        float f4 = this.mMaxTextAlpha;
        float f5 = this.mMinTextAlpha;
        paint.setAlpha((int) (((f4 - f5) * fParabola) + f5));
        float f6 = (float) (((double) this.mViewWidth) / 2.0d);
        float f7 = (float) ((((double) this.mViewHeight) / 2.0d) + ((double) this.mMoveLen));
        Paint.FontMetricsInt fontMetricsInt = this.mPaint.getFontMetricsInt();
        float f8 = (float) (((double) f7) - ((((double) fontMetricsInt.bottom) / 2.0d) + (((double) fontMetricsInt.top) / 2.0d)));
        String key = this.items.get(this.mCurrentSelectedIndex).getKey();
        if (key.contains(getContext().getString(R.string.sys_ui_temp_hi))) {
            this.mPaint.setColor(getContext().getColor(R.color.color_temp_hi));
        } else if (key.contains(getContext().getString(R.string.sys_ui_temp_low))) {
            this.mPaint.setColor(getContext().getColor(R.color.color_temp_lo));
        } else {
            this.mPaint.setColor(this.selectedTextColor);
        }
        if (key.contains(Constants.CHAR_DOT)) {
            drawFloat(key, canvas, this.mPaint, f3, this.mFloatTextSize, f8);
        } else {
            canvas.drawText(key, f6, f8, this.mPaint);
        }
        for (int i = 1; this.mCurrentSelectedIndex - i >= 0; i++) {
            drawOtherText(canvas, i, -1);
        }
        for (int i2 = 1; this.mCurrentSelectedIndex + i2 < this.items.size(); i2++) {
            drawOtherText(canvas, i2, 1);
        }
    }

    private void drawOtherText(Canvas canvas, int position, int type) {
        float f = type;
        float f2 = (this.mTextSpaceRatio * this.mMinTextSize * position) + (this.mMoveLen * f);
        float fParabola = parabola(this.mViewHeight / 4.0f, f2);
        float f3 = this.mMaxTextSize;
        float f4 = this.mMinTextSize;
        float f5 = ((f3 - f4) * fParabola) + f4;
        this.nPaint.setTextSize(f5);
        Paint paint = this.nPaint;
        float f6 = this.mMaxTextAlpha;
        float f7 = this.mMinTextAlpha;
        paint.setAlpha((int) (((f6 - f7) * fParabola) + f7));
        float f8 = (float) ((((double) this.mViewHeight) / 2.0d) + ((double) (f * f2)));
        Paint.FontMetricsInt fontMetricsInt = this.nPaint.getFontMetricsInt();
        float f9 = (float) (((double) f8) - ((((double) fontMetricsInt.bottom) / 2.0d) + (((double) fontMetricsInt.top) / 2.0d)));
        String key = this.items.get(this.mCurrentSelectedIndex + (type * position)).getKey();
        if (key.contains(getContext().getString(R.string.sys_ui_temp_hi))) {
            this.nPaint.setColor(getContext().getColor(R.color.color_temp_hi));
        } else if (key.contains(getContext().getString(R.string.sys_ui_temp_low))) {
            this.nPaint.setColor(getContext().getColor(R.color.color_temp_lo));
        } else {
            this.nPaint.setColor(this.normalTextColor);
            if (key.contains(Constants.CHAR_DOT)) {
                drawFloat(key, canvas, this.nPaint, f5, this.mFloatTextSize, f9);
                return;
            }
        }
        canvas.drawText(key, (float) (((double) this.mViewWidth) / 2.0d), f9, this.nPaint);
    }

    private void drawFloat(String content, Canvas canvas, Paint paint, float sizeNormal, float sizeSmall, float baseline) {
        BydLogHelper.logD("drawFloat content = " + content);
        float fMeasureText = paint.measureText(content, 0, content.indexOf(Constants.CHAR_DOT));
        float fMeasureText2 = paint.measureText(content, content.indexOf(Constants.CHAR_DOT), content.length());
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_20);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.dp_50);
        float f = (this.mViewWidth - dimensionPixelSize) - (((int) fMeasureText2) >> 1);
        paint.setTextSize(sizeSmall);
        canvas.drawText(content, content.indexOf(Constants.CHAR_DOT), content.length(), f, baseline, paint);
        paint.setTextSize(sizeNormal);
        canvas.drawText(content, 0, content.indexOf(Constants.CHAR_DOT), (this.mViewWidth - dimensionPixelSize2) - (((int) fMeasureText) >> 1), baseline, paint);
    }

    private float parabola(float zero, float x) {
        float fPow = (float) (1.0d - Math.pow(x / zero, 2.0d));
        if (fPow < 0.0f) {
            return 0.0f;
        }
        return fPow;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            doDown(event);
        } else if (actionMasked == 1) {
            doUp();
        } else if (actionMasked == 2) {
            doMove(event);
        }
        return true;
    }

    private void doDown(MotionEvent event) {
        MyTimerTask myTimerTask = this.mTask;
        if (myTimerTask != null) {
            myTimerTask.cancel();
            this.mTask = null;
        }
        this.mLastDownY = event.getY();
    }

    private void doMove(MotionEvent event) {
        float y = this.mMoveLen + (event.getY() - this.mLastDownY);
        this.mMoveLen = y;
        float f = this.mTextSpaceRatio;
        float f2 = this.mMinTextSize;
        if (y > (f * f2) / 2.0f) {
            boolean z = this.loop;
            if (!z && this.mCurrentSelectedIndex == 0) {
                this.mLastDownY = event.getY();
                invalidate();
                return;
            } else {
                if (!z) {
                    this.mCurrentSelectedIndex--;
                }
                moveTailToHead();
                this.mMoveLen -= this.mTextSpaceRatio * this.mMinTextSize;
            }
        } else if (y < ((-f) * f2) / 2.0f) {
            if (this.mCurrentSelectedIndex == this.items.size() - 1) {
                this.mLastDownY = event.getY();
                invalidate();
                return;
            } else {
                if (!this.loop) {
                    this.mCurrentSelectedIndex++;
                }
                moveHeadToTail();
                this.mMoveLen += this.mTextSpaceRatio * this.mMinTextSize;
            }
        }
        this.mLastDownY = event.getY();
        invalidate();
    }

    private void doUp() {
        if (Math.abs(this.mMoveLen) < 1.0E-4d) {
            this.mMoveLen = 0.0f;
            return;
        }
        MyTimerTask myTimerTask = this.mTask;
        if (myTimerTask != null) {
            myTimerTask.cancel();
            this.mTask = null;
        }
        MyTimerTask myTimerTask2 = new MyTimerTask(this.updateHandler);
        this.mTask = myTimerTask2;
        this.timer.schedule(myTimerTask2, 0L, 10L);
    }

    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Handler handler = this.handler;
            handler.sendMessage(handler.obtainMessage());
        }
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        return this.canScroll && super.dispatchTouchEvent(event);
    }

    public void setIsLoop(boolean isLoop) {
        this.loop = isLoop;
    }

    public int getItemCount() {
        List<TempData> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
