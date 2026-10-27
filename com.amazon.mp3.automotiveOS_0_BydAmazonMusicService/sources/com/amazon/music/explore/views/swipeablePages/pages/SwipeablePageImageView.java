package com.amazon.music.explore.views.swipeablePages.pages;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeablePageImageView extends ImageView {
    private Path clipPath;
    private SwipeablePagesStyleSheet styleSheet;

    public SwipeablePageImageView(Context context) {
        super(context);
        init();
    }

    public SwipeablePageImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SwipeablePageImageView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public SwipeablePageImageView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    private void init() {
        this.clipPath = new Path();
    }

    public void setStyleSheet(SwipeablePagesStyleSheet swipeablePagesStyleSheet) {
        this.styleSheet = swipeablePagesStyleSheet;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setClipPath();
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (!this.clipPath.isEmpty() && canvas != null) {
            canvas.clipPath(this.clipPath);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.clipPath.isEmpty() || canvas == null) {
            super.onDraw(canvas);
            return;
        }
        canvas.save();
        canvas.clipPath(this.clipPath);
        super.onDraw(canvas);
        canvas.restore();
    }

    private void setClipPath() {
        float measuredWidth = getMeasuredWidth();
        float measuredHeight = getMeasuredHeight();
        int peekLeftHeight = this.styleSheet.getPeekLeftHeight();
        int peekRightHeight = this.styleSheet.getPeekRightHeight();
        this.clipPath.reset();
        this.clipPath.moveTo(0.0f, 0.0f);
        this.clipPath.lineTo(measuredWidth, peekLeftHeight - peekRightHeight);
        this.clipPath.lineTo(measuredWidth, measuredHeight);
        this.clipPath.lineTo(0.0f, measuredHeight);
        this.clipPath.close();
    }
}
