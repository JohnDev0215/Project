package com.byd.secretcode.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DrawBoardView extends View {
    public ArrayList<a> b;
    public ArrayList<a> c;
    public int d;
    public int e;
    public float f;
    public float g;
    public Path h;

    public class a {
        public Paint a;
        public Path b;

        public a(Paint paint, Path path) {
            this.a = paint;
            this.b = path;
        }
    }

    public DrawBoardView(Context context) {
        super(context);
        this.d = -65536;
        this.e = 5;
        this.f = 0.0f;
        this.g = 0.0f;
    }

    public final void a() {
        this.b = new ArrayList<>();
        this.c = new ArrayList<>();
        setBackgroundColor(-1);
    }

    public void b() {
        this.b.clear();
        invalidate();
    }

    public int getLineColor() {
        return this.d;
    }

    public int getLineSize() {
        return this.e;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        for (a aVar : this.b) {
            canvas.drawPath(aVar.b, aVar.a);
            Paint paint = new Paint(1);
            paint.setColor(-16777216);
            paint.setStrokeWidth(1.0f);
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(24.0f);
            canvas.drawText("position: X : " + this.f + "/ Y : " + this.g, 100.0f, 130.0f, paint);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            b();
            Paint paint = new Paint(1);
            paint.setColor(this.d);
            paint.setStrokeWidth(this.e);
            paint.setStyle(Paint.Style.STROKE);
            Path path = new Path();
            this.h = path;
            path.moveTo(motionEvent.getX(), motionEvent.getY());
            a aVar = new a(paint, this.h);
            this.b.add(aVar);
            this.c.add(aVar);
            this.f = motionEvent.getX();
            this.g = motionEvent.getY();
        } else if (action == 1) {
            this.f = motionEvent.getX();
            this.g = motionEvent.getY();
        } else if (action == 2) {
            this.h.lineTo(motionEvent.getX(), motionEvent.getY());
            this.f = motionEvent.getX();
            this.g = motionEvent.getY();
        }
        invalidate();
        return true;
    }

    public void setLineColor(int i) {
        this.d = i;
    }

    public void setLineSize(int i) {
        this.e = i;
    }

    public DrawBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = -65536;
        this.e = 5;
        this.f = 0.0f;
        this.g = 0.0f;
        a();
    }
}
