package com.amazon.mp3.prime;

import androidx.recyclerview.widget.ItemTouchHelper;

/* JADX INFO: loaded from: classes4.dex */
public enum ContentOwnershipStatus {
    UNKNOWN(-1),
    OWNED(150),
    ADDED(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION),
    NOT_IN_LIBRARY(350);

    public static final int IN_LIB_OR_NOT_BOUNDARY = 300;
    public static final int OWN_OR_NOT_BOUNDARY = 200;
    private int mValue;

    ContentOwnershipStatus(int value) {
        this.mValue = value;
    }

    public int getValue() {
        return this.mValue;
    }

    public static ContentOwnershipStatus fromValue(int value) {
        for (ContentOwnershipStatus contentOwnershipStatus : values()) {
            if (contentOwnershipStatus.mValue == value) {
                return contentOwnershipStatus;
            }
        }
        return UNKNOWN;
    }

    public boolean isInLibrary() {
        return UNKNOWN == this || 300 > this.mValue;
    }

    public boolean isOwned() {
        return UNKNOWN == this || 200 > this.mValue;
    }
}
