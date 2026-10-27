package com.android.car.ui.toolbar;

import androidx.annotation.Nullable;
import spi.h9;
import spi.j9;

/* JADX INFO: loaded from: classes2.dex */
class ProgressBarControllerAdapterV1 implements h9 {

    @Nullable
    private final j9 mOemProgressbar;
    private boolean mVisible = false;
    private boolean mIndeterminate = true;
    private int mMax = 100;
    private int mMin = 0;
    private int mProgress = 0;

    public ProgressBarControllerAdapterV1(@Nullable j9 j9Var) {
        this.mOemProgressbar = j9Var;
    }

    public int getMax() {
        return this.mMax;
    }

    public int getMin() {
        return this.mMin;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public boolean isIndeterminate() {
        return this.mIndeterminate;
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    public void setIndeterminate(boolean z) {
        j9 j9Var = this.mOemProgressbar;
        if (j9Var != null) {
            j9Var.d();
        }
        this.mIndeterminate = z;
    }

    public void setMax(int i) {
        j9 j9Var = this.mOemProgressbar;
        if (j9Var != null) {
            j9Var.c();
        }
        this.mMax = i;
    }

    public void setMin(int i) {
        j9 j9Var = this.mOemProgressbar;
        if (j9Var != null) {
            j9Var.e();
        }
        this.mMin = i;
    }

    public void setProgress(int i) {
        j9 j9Var = this.mOemProgressbar;
        if (j9Var != null) {
            j9Var.a();
        }
        this.mProgress = i;
    }

    public void setVisible(boolean z) {
        j9 j9Var = this.mOemProgressbar;
        if (j9Var != null) {
            j9Var.b();
        }
        this.mVisible = z;
    }
}
