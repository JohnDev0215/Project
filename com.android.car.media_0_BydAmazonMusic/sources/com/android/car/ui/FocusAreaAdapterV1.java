package com.android.car.ui;

import android.view.View;
import android.widget.LinearLayout;
import com.android.car.ui.rotary.FocusArea;
import java.util.Objects;
import spi.y3;
import spi.z3;

/* JADX INFO: loaded from: classes2.dex */
public class FocusAreaAdapterV1 implements z3 {
    private final FocusArea mFocusArea;

    public FocusAreaAdapterV1(FocusArea focusArea) {
        this.mFocusArea = focusArea;
    }

    public LinearLayout getView() {
        return this.mFocusArea;
    }

    public void setBoundsOffset(int i, int i2, int i3, int i4) {
        this.mFocusArea.a(i, i2, i3, i4);
    }

    public void setDefaultFocus(View view) {
        this.mFocusArea.setDefaultFocus(view);
    }

    public void setHighlightPadding(int i, int i2, int i3, int i4) {
        this.mFocusArea.b(i, i2, i3, i4);
    }

    public void setNudgeShortcut(int i, View view) {
        y3 y3Var = this.mFocusArea.a;
        Objects.requireNonNull(y3Var);
        if (!y3.D.contains(Integer.valueOf(i))) {
            throw new IllegalArgumentException("direction must be FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, or FOCUS_RIGHT.");
        }
        y3Var.f();
        if (view == null) {
            y3Var.t.remove(i);
        } else {
            y3Var.t.put(i, view);
        }
    }

    public void setWrapAround(boolean z) {
        this.mFocusArea.setWrapAround(z);
    }
}
