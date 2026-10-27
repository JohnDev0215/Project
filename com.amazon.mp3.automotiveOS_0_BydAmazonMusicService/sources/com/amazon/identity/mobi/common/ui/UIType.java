package com.amazon.identity.mobi.common.ui;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
public enum UIType {
    TIV_APPROVAL(1, 1),
    ATB_CONSENT_APPROVAL(0, 1);

    private final int mPriority;
    private final int mUiPendingQueueCapacity;

    public static class UITypeComparator implements Comparator<UIType>, Serializable {
        @Override // java.util.Comparator
        public int compare(UIType uIType, UIType uIType2) {
            if (uIType.getPriority() < uIType2.getPriority()) {
                return -1;
            }
            return uIType.getPriority() > uIType2.getPriority() ? 1 : 0;
        }
    }

    UIType(int i, int i2) {
        this.mPriority = i;
        this.mUiPendingQueueCapacity = i2;
    }

    int getPriority() {
        return this.mPriority;
    }

    int getUiPendingQueueCapacity() {
        return this.mUiPendingQueueCapacity;
    }
}
