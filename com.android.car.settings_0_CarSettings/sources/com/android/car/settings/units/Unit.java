package com.android.car.settings.units;

/* JADX INFO: loaded from: classes5.dex */
public class Unit {
    private final int mAbbreviationResId;
    private final int mId;
    private final int mNameResId;

    Unit(int unitId, int unitAbbreviationResId, int unitNameResId) {
        this.mId = unitId;
        this.mAbbreviationResId = unitAbbreviationResId;
        this.mNameResId = unitNameResId;
    }

    public int getId() {
        return this.mId;
    }

    public int getAbbreviationResId() {
        return this.mAbbreviationResId;
    }

    public int getNameResId() {
        return this.mNameResId;
    }
}
