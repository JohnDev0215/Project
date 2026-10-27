package com.android.systemui.stackdivider.view.help;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.systemui.R;

/* JADX INFO: loaded from: classes.dex */
public class RoundImageView extends ImageView {
    float radius;

    public RoundImageView(Context context) {
        super(context);
        this.radius = 20.0f;
        this.radius = context.getResources().getDimensionPixelSize(R.dimen.dp_12);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.radius = 20.0f;
        this.radius = context.getResources().getDimensionPixelSize(R.dimen.dp_12);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.radius = 20.0f;
        this.radius = context.getResources().getDimensionPixelSize(R.dimen.dp_12);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        float f = this.radius;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
