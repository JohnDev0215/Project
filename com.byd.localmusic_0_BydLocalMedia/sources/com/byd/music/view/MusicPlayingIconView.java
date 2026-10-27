package com.byd.music.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.byd.music.R;
import com.byd.music.utils.DipUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes5.dex */
public class MusicPlayingIconView extends View {
    private float basePointX;
    private float basePointY;
    private float i;
    public boolean isPlaying;
    private int j;
    private Paint paint;
    private int pointerColor;
    private int pointerNum;
    private float pointerPadding;
    private int pointerSpeed;
    private float pointerWidth;
    private List<Pointer> pointers;
    private final Runnable uiRunnable;

    public MusicPlayingIconView(Context context) {
        this(context, null);
    }

    public MusicPlayingIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MusicPlayingIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.pointerColor = Color.GREEN;
        this.isPlaying = false;
        this.i = 0.0f;
        this.j = 0;
        this.uiRunnable = new Runnable() { // from class: com.byd.music.view.-$$Lambda$MusicPlayingIconView$KPWmnJ1zSaCSGMhb50Upwy6cuYs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.invalidate();
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MusicPlayingIconAttr);
        this.pointerColor = typedArrayObtainStyledAttributes.getColor(R.styleable.MusicPlayingIconAttr_pointer_color, context.getColor(R.color.text_color_selected));
        this.pointerNum = typedArrayObtainStyledAttributes.getInt(R.styleable.MusicPlayingIconAttr_pointer_num, 4);
        this.pointerWidth = DipUtil.dp2px(typedArrayObtainStyledAttributes.getFloat(R.styleable.MusicPlayingIconAttr_pointer_width, 5.0f));
        this.pointerSpeed = typedArrayObtainStyledAttributes.getInt(R.styleable.MusicPlayingIconAttr_pointer_speed, 40);
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setColor(this.pointerColor);
        this.pointers = new ArrayList();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.basePointY = getHeight() - getPaddingBottom();
        Random random = new Random();
        List<Pointer> list = this.pointers;
        if (list != null) {
            list.clear();
        }
        for (int i5 = 0; i5 < this.pointerNum; i5++) {
            this.pointers.add(new Pointer((float) (((double) (random.nextInt(10) + 1)) * 0.1d * ((double) ((getHeight() - getPaddingBottom()) - getPaddingTop())))));
        }
        this.pointerPadding = (((getWidth() - getPaddingLeft()) - getPaddingRight()) - (this.pointerWidth * this.pointerNum)) / (r7 - 1);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.basePointX = getPaddingLeft() + 0.0f;
        for (int i = 0; i < this.pointers.size(); i++) {
            canvas.drawRect(this.basePointX, this.basePointY - this.pointers.get(i).getHeight(), this.basePointX + this.pointerWidth, this.basePointY, this.paint);
            this.basePointX += this.pointerPadding + this.pointerWidth;
        }
        this.pointers.get(this.j).setHeight((this.basePointY - getPaddingTop()) * ((float) Math.abs(Math.sin(this.i + this.j))));
        int i2 = this.j + 1;
        this.j = i2;
        if (i2 == this.pointers.size()) {
            this.j = 0;
        }
        this.i = (float) (((double) this.i) + 0.1d);
        if (this.isPlaying) {
            postDelayed(this.uiRunnable, this.pointerSpeed);
        }
    }

    public void start() {
        if (this.isPlaying) {
            return;
        }
        this.isPlaying = true;
        invalidate();
    }

    public void stop() {
        this.isPlaying = false;
        invalidate();
    }

    public class Pointer {
        private float height;

        public Pointer(float f) {
            this.height = f;
        }

        public float getHeight() {
            return this.height;
        }

        public void setHeight(float f) {
            this.height = f;
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isPlaying = false;
    }
}
