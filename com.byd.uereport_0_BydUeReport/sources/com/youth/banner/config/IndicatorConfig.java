package com.youth.banner.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public class IndicatorConfig {
    private int currentPosition;
    private int indicatorSize;
    private Margins margins;
    private int gravity = 1;
    private int indicatorSpace = BannerConfig.INDICATOR_SPACE;
    private int normalWidth = BannerConfig.INDICATOR_NORMAL_WIDTH;
    private int selectedWidth = BannerConfig.INDICATOR_SELECTED_WIDTH;
    private int normalColor = BannerConfig.INDICATOR_NORMAL_COLOR;
    private int selectedColor = BannerConfig.INDICATOR_SELECTED_COLOR;
    private int radius = BannerConfig.INDICATOR_RADIUS;
    private int height = BannerConfig.INDICATOR_HEIGHT;
    private boolean attachToBanner = true;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Direction {
        public static final int CENTER = 1;
        public static final int LEFT = 0;
        public static final int RIGHT = 2;
    }

    public static class Margins {
        public int bottomMargin;
        public int leftMargin;
        public int rightMargin;
        public int topMargin;

        public Margins() {
            this(BannerConfig.INDICATOR_MARGIN);
        }

        public Margins(int i8) {
            this(i8, i8, i8, i8);
        }

        public Margins(int i8, int i9, int i10, int i11) {
            this.leftMargin = i8;
            this.topMargin = i9;
            this.rightMargin = i10;
            this.bottomMargin = i11;
        }
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    public int getGravity() {
        return this.gravity;
    }

    public int getHeight() {
        return this.height;
    }

    public int getIndicatorSize() {
        return this.indicatorSize;
    }

    public int getIndicatorSpace() {
        return this.indicatorSpace;
    }

    public Margins getMargins() {
        if (this.margins == null) {
            setMargins(new Margins());
        }
        return this.margins;
    }

    public int getNormalColor() {
        return this.normalColor;
    }

    public int getNormalWidth() {
        return this.normalWidth;
    }

    public int getRadius() {
        return this.radius;
    }

    public int getSelectedColor() {
        return this.selectedColor;
    }

    public int getSelectedWidth() {
        return this.selectedWidth;
    }

    public boolean isAttachToBanner() {
        return this.attachToBanner;
    }

    public IndicatorConfig setAttachToBanner(boolean z7) {
        this.attachToBanner = z7;
        return this;
    }

    public IndicatorConfig setCurrentPosition(int i8) {
        this.currentPosition = i8;
        return this;
    }

    public IndicatorConfig setGravity(int i8) {
        this.gravity = i8;
        return this;
    }

    public IndicatorConfig setHeight(int i8) {
        this.height = i8;
        return this;
    }

    public IndicatorConfig setIndicatorSize(int i8) {
        this.indicatorSize = i8;
        return this;
    }

    public IndicatorConfig setIndicatorSpace(int i8) {
        this.indicatorSpace = i8;
        return this;
    }

    public IndicatorConfig setMargins(Margins margins) {
        this.margins = margins;
        return this;
    }

    public IndicatorConfig setNormalColor(int i8) {
        this.normalColor = i8;
        return this;
    }

    public IndicatorConfig setNormalWidth(int i8) {
        this.normalWidth = i8;
        return this;
    }

    public IndicatorConfig setRadius(int i8) {
        this.radius = i8;
        return this;
    }

    public IndicatorConfig setSelectedColor(int i8) {
        this.selectedColor = i8;
        return this;
    }

    public IndicatorConfig setSelectedWidth(int i8) {
        this.selectedWidth = i8;
        return this;
    }
}
