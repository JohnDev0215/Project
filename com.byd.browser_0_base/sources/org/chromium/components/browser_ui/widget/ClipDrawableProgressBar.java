package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import defpackage.ii2;

/* JADX INFO: loaded from: classes2.dex */
public class ClipDrawableProgressBar extends ImageView {
    public final ColorDrawable e;
    public int f;
    public float g;
    public int h;

    public static class a {
        public final Rect a = new Rect();
        public final Rect b = new Rect();
        public int c;
        public int d;
    }

    public interface b {
    }

    public ClipDrawableProgressBar(Context context, int i) {
        super(context);
        this.h = getVisibility();
        int iB = ii2.b(getContext());
        this.f = getContext().getColor(R$color.progress_bar_bg_color_list);
        ColorDrawable colorDrawable = new ColorDrawable(iB);
        this.e = colorDrawable;
        setImageDrawable(new ClipDrawable(colorDrawable, 8388611, 1));
        setBackgroundColor(this.f);
        setLayoutParams(new ViewGroup.LayoutParams(-1, i));
    }

    public int getForegroundColor() {
        return this.e.getColor();
    }

    public float getProgress() {
        return this.g;
    }

    @Override // android.view.View
    public final boolean onSetAlpha(int i) {
        int visibility = getVisibility();
        int i2 = this.h;
        if (getAlpha() == 0.0f && this.h == 0) {
            i2 = 4;
        }
        if (visibility != i2) {
            super.setVisibility(i2);
        }
        return super.onSetAlpha(i);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        if (i == 0) {
            setBackground(null);
        } else {
            super.setBackgroundColor(i);
        }
        this.f = i;
    }

    public void setForegroundColor(int i) {
        this.e.setColor(i);
    }

    public void setProgress(float f) {
        if (this.g == f) {
            return;
        }
        this.g = f;
        getDrawable().setLevel(Math.round(f * 10000.0f));
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        this.h = i;
        int visibility = getVisibility();
        int i2 = this.h;
        if (getAlpha() == 0.0f && this.h == 0) {
            i2 = 4;
        }
        if (visibility != i2) {
            super.setVisibility(i2);
        }
    }

    public void setProgressBarObserver(b bVar) {
    }
}
