package com.android.car.ui.toolbar;

import android.widget.ProgressBar;

/* JADX INFO: loaded from: classes2.dex */
class ProgressBarControllerImpl implements ProgressBarController {
    private final ProgressBar mProgressBar;

    ProgressBarControllerImpl(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public void setVisible(boolean visible) {
        this.mProgressBar.setVisibility(visible ? 0 : 8);
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public boolean isVisible() {
        return this.mProgressBar.getVisibility() == 0;
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public void setIndeterminate(boolean indeterminate) {
        this.mProgressBar.setIndeterminate(indeterminate);
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public boolean isIndeterminate() {
        return this.mProgressBar.isIndeterminate();
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public void setMax(int max) {
        this.mProgressBar.setMax(max);
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public int getMax() {
        return this.mProgressBar.getMax();
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public void setMin(int min) {
        this.mProgressBar.setMin(min);
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public int getMin() {
        return this.mProgressBar.getMin();
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public void setProgress(int progress) {
        this.mProgressBar.setProgress(progress);
    }

    @Override // com.android.car.ui.toolbar.ProgressBarController
    public int getProgress() {
        return this.mProgressBar.getProgress();
    }
}
