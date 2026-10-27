package com.amazon.mp3.card;

/* JADX INFO: loaded from: classes3.dex */
public enum CardLayoutType {
    LINEAR,
    GRID,
    PHOTOS;

    public static CardLayoutType fromValue(int i) {
        if (i == 0) {
            return LINEAR;
        }
        if (i == 1) {
            return GRID;
        }
        if (i == 2) {
            return PHOTOS;
        }
        return LINEAR;
    }
}
