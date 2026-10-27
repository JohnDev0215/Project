package com.android.car.ui.toolbar;

/* JADX INFO: loaded from: classes7.dex */
public interface ProgressBarController {
    int getMax();

    int getMin();

    int getProgress();

    boolean isIndeterminate();

    boolean isVisible();

    void setIndeterminate(boolean z);

    void setMax(int i);

    void setMin(int i);

    void setProgress(int i);

    void setVisible(boolean z);
}
