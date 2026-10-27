package com.android.launcher3.icons;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class FixedScaleDrawable extends DrawableWrapper {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f1297c;
    private float d;

    public FixedScaleDrawable() {
        super(new ColorDrawable());
        this.f1297c = 0.46669f;
        this.d = 0.46669f;
    }

    public void a(float f) {
        float intrinsicHeight = getIntrinsicHeight();
        float intrinsicWidth = getIntrinsicWidth();
        float f2 = f * 0.46669f;
        this.f1297c = f2;
        this.d = f2;
        if (intrinsicHeight > intrinsicWidth && intrinsicWidth > 0.0f) {
            this.f1297c = f2 * (intrinsicWidth / intrinsicHeight);
        } else {
            if (intrinsicWidth <= intrinsicHeight || intrinsicHeight <= 0.0f) {
                return;
            }
            this.d *= intrinsicHeight / intrinsicWidth;
        }
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int iSave = canvas.save();
        canvas.scale(this.f1297c, this.d, getBounds().exactCenterX(), getBounds().exactCenterY());
        super.draw(canvas);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
    }

    @Override // android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
    }
}
