package com.byd.music.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.blankj.utilcode.util.SizeUtils;
import com.byd.music.R;
import com.byd.music.utils.LogUtil;
import defpackage.C$r8$backportedMethods$utility$List$1$ofArray;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SlideBar extends View {
    private final String[] ALPHABETS;
    private int ALPHABETS_LENGTH;
    private final String TAG;
    private final List<String> charList;
    private Rect mContainer;
    private int mCurrentPosition;
    private float mLetterHeight;
    private OnLetterTouchListener mOnLetterTouchListener;
    private Paint mPaint;
    private Rect mSlideBar;
    private Rect mTextBound;

    public interface OnLetterTouchListener {
        void onLetterTouch(String str, float f);

        void onTouchEnd();
    }

    public SlideBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        String[] strArr = {"A", "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "#"};
        this.ALPHABETS = strArr;
        this.ALPHABETS_LENGTH = strArr.length;
        this.TAG = "SlideBar";
        this.charList = C$r8$backportedMethods$utility$List$1$ofArray.of(strArr);
        this.mContainer = new Rect();
        this.mCurrentPosition = -1;
        this.mPaint = new Paint();
        this.mSlideBar = new Rect();
        this.mTextBound = new Rect();
        this.mPaint.setAntiAlias(true);
        this.mPaint.setTextSize(SizeUtils.dp2px(8.0f));
        this.mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        Rect rect = this.mContainer;
        rect.left = 0;
        rect.right = getMeasuredWidth();
        rect.top = 0;
        rect.bottom = getMeasuredHeight();
        this.mSlideBar.left = getPaddingStart();
        this.mSlideBar.top = getPaddingTop();
        this.mSlideBar.right = getMeasuredWidth() - getPaddingEnd();
        this.mSlideBar.bottom = getMeasuredHeight() - getPaddingBottom();
        this.mLetterHeight = (this.mSlideBar.bottom - this.mSlideBar.top) / this.ALPHABETS_LENGTH;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int iSave = canvas.save();
        canvas.translate(0.0f, this.mSlideBar.top);
        for (int i = 0; i < this.ALPHABETS_LENGTH; i++) {
            if (i == this.mCurrentPosition) {
                this.mPaint.setColor(getResources().getColor(R.color.bg_slideBar_pressed, null));
            } else {
                this.mPaint.setColor(getResources().getColor(R.color.colorSlideBarText, null));
            }
            canvas.drawText(this.ALPHABETS[i], this.mSlideBar.centerX(), this.mLetterHeight + (getTextHeight(this.ALPHABETS[i]) / 2.0f), this.mPaint);
            canvas.translate(0.0f, this.mLetterHeight);
        }
        canvas.restoreToCount(iSave);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int touchLetterIndex = getTouchLetterIndex(motionEvent.getY());
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2 && action != 3) {
                    return super.onTouchEvent(motionEvent);
                }
                if (action != 3) {
                    checkCurPosition(touchLetterIndex);
                    return true;
                }
            }
            getParent().requestDisallowInterceptTouchEvent(false);
            invalidate();
            OnLetterTouchListener onLetterTouchListener = this.mOnLetterTouchListener;
            if (onLetterTouchListener != null) {
                onLetterTouchListener.onTouchEnd();
            }
            return true;
        }
        checkCurPosition(touchLetterIndex);
        return true;
    }

    private void checkCurPosition(int i) {
        getParent().requestDisallowInterceptTouchEvent(true);
        if (this.mCurrentPosition != i) {
            this.mCurrentPosition = i;
            invalidate();
            if (this.mOnLetterTouchListener != null) {
                String str = this.ALPHABETS[i];
                float f = this.mSlideBar.top;
                float f2 = this.mLetterHeight;
                this.mOnLetterTouchListener.onLetterTouch(str, f + (i * f2) + (f2 / 2.0f));
                LogUtil.d(getClass().toString(), "Cur Letter is " + str);
            }
        }
    }

    private int getTouchLetterIndex(float f) {
        int i = (int) ((f - this.mSlideBar.top) / this.mLetterHeight);
        if (i >= this.ALPHABETS.length) {
            return r0.length - 1;
        }
        if (i < 0) {
            return 0;
        }
        return i;
    }

    private float getTextHeight(String str) {
        this.mPaint.getTextBounds(str, 0, 1, this.mTextBound);
        return this.mTextBound.bottom - this.mTextBound.top;
    }

    public void setOnLetterTouchListener(OnLetterTouchListener onLetterTouchListener) {
        this.mOnLetterTouchListener = onLetterTouchListener;
    }

    public void setCurrentPosition(String str) {
        getParent().requestDisallowInterceptTouchEvent(true);
        int iIndexOf = this.charList.indexOf(str);
        if (this.mCurrentPosition != iIndexOf) {
            this.mCurrentPosition = iIndexOf;
            invalidate();
        }
    }
}
