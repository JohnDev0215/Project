package com.android.car.apps.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import spi.c1;
import spi.uc;

/* JADX INFO: loaded from: classes.dex */
public class MinimizedControlBar extends ConstraintLayout implements c1 {
    public FrameLayout[] a;
    public final View[] b;
    public View[] c;
    public TextView d;
    public TextView e;
    public ImageView f;

    public MinimizedControlBar(Context context) {
        this(context, null, 0);
    }

    @Override // spi.c1
    public final void a(@Nullable View view, int i) {
        this.b[i] = view;
        e();
    }

    @Override // spi.c1
    public final ImageButton b(Drawable drawable) {
        ImageButton imageButton = (ImageButton) LayoutInflater.from(this.a[0].getContext()).inflate(R$layout.control_bar_button, (ViewGroup) this.a[0], false);
        imageButton.setImageDrawable(drawable);
        return imageButton;
    }

    public final void e() {
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            View view = null;
            View[] viewArr = this.b;
            if (viewArr[i2] != null) {
                view = viewArr[i2];
            } else {
                View[] viewArr2 = this.c;
                if (viewArr2 != null && i < viewArr2.length) {
                    view = viewArr2[i];
                    i++;
                }
            }
            uc.d(view, this.a[c1.c(i2, 3)]);
        }
    }

    @Override // spi.c1
    public int getFocusedViewIndex() {
        int i = 0;
        while (true) {
            FrameLayout[] frameLayoutArr = this.a;
            if (i >= frameLayoutArr.length) {
                return -1;
            }
            FrameLayout frameLayout = frameLayoutArr[i];
            if (frameLayout != null && frameLayout.hasFocus()) {
                return i;
            }
            i++;
        }
    }

    @Override // spi.c1
    public void setFocusAtViewIndex(int i) {
        if (-1 < i) {
            FrameLayout[] frameLayoutArr = this.a;
            if (i >= frameLayoutArr.length || frameLayoutArr[i] == null) {
                return;
            }
            frameLayoutArr[i].requestFocus();
        }
    }

    @Override // spi.c1
    public void setViews(@Nullable View[] viewArr) {
        this.c = viewArr;
        e();
    }

    public MinimizedControlBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MinimizedControlBar(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R$layout.minimized_control_bar);
    }

    public MinimizedControlBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.b = new View[3];
        View.inflate(context, i2, this);
        this.c = new View[3];
        this.d = (TextView) findViewById(R$id.minimized_control_bar_title);
        this.e = (TextView) findViewById(R$id.minimized_control_bar_subtitle);
        this.f = (ImageView) findViewById(R$id.minimized_control_bar_content_tile);
        FrameLayout[] frameLayoutArr = new FrameLayout[3];
        this.a = frameLayoutArr;
        frameLayoutArr[0] = (FrameLayout) findViewById(R$id.minimized_control_bar_left_slot);
        this.a[1] = (FrameLayout) findViewById(R$id.minimized_control_bar_main_slot);
        this.a[2] = (FrameLayout) findViewById(R$id.minimized_control_bar_right_slot);
    }
}
