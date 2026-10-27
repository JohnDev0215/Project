package com.amazon.mp3.card;

/* JADX INFO: loaded from: classes3.dex */
public class CardStyle {
    final int marginBottom;
    final int paddingLeft;
    final int paddingRight;
    final int width = -1;
    final int height = -2;

    public int getHeight() {
        return -2;
    }

    public int getWidth() {
        return -1;
    }

    public CardStyle(final int margin, final int padding) {
        this.marginBottom = margin;
        this.paddingLeft = padding;
        this.paddingRight = padding;
    }

    public int getMarginBottom() {
        return this.marginBottom;
    }

    public int getPaddingLeft() {
        return this.paddingLeft;
    }

    public int getPaddingRight() {
        return this.paddingRight;
    }
}
