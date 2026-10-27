package com.android.systemui.qs;

/* JADX INFO: loaded from: classes.dex */
public class QSEditEvent {
    private String categoryLabel;

    public String getCategoryLabel() {
        return this.categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public QSEditEvent(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }
}
