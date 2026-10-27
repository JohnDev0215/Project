package com.byd.bean;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class ImageEditItem {
    private boolean canTintDrawable;
    private int disableColor;
    private boolean enable;
    private int icon;
    private String name;
    private int nameRes;
    private int selectColor;
    private int selectIcon;
    private Drawable selectorDrawable;
    private int tintColor;

    public ImageEditItem(int i) {
        this.canTintDrawable = true;
        this.tintColor = -1;
        this.enable = true;
        this.icon = i;
    }

    public ImageEditItem(int i, int i2) {
        this.canTintDrawable = true;
        this.tintColor = -1;
        this.enable = true;
        this.icon = i;
        this.selectIcon = i2;
    }

    public ImageEditItem(int i, int i2, boolean z) {
        this.canTintDrawable = true;
        this.tintColor = -1;
        this.enable = true;
        this.icon = i;
        this.selectIcon = i2;
        this.canTintDrawable = z;
    }

    public ImageEditItem(int i, String str) {
        this.canTintDrawable = true;
        this.tintColor = -1;
        this.enable = true;
        this.icon = i;
        this.name = str;
    }

    public ImageEditItem(Drawable drawable) {
        this.canTintDrawable = true;
        this.tintColor = -1;
        this.enable = true;
        this.selectorDrawable = drawable;
    }

    public int getDisableColor() {
        return this.disableColor;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getName() {
        return this.name;
    }

    public int getNameRes() {
        return this.nameRes;
    }

    public int getSelectColor() {
        return this.selectColor;
    }

    public int getSelectIcon() {
        return this.selectIcon;
    }

    public Drawable getSelectorDrawable() {
        return this.selectorDrawable;
    }

    public int getTintColor() {
        return this.tintColor;
    }

    public boolean isCanTintDrawable() {
        return this.canTintDrawable;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public ImageEditItem setCanTintDrawable(boolean z) {
        this.canTintDrawable = z;
        return this;
    }

    public ImageEditItem setDisableColor(int i) {
        this.disableColor = i;
        return this;
    }

    public ImageEditItem setEnable(boolean z) {
        this.enable = z;
        return this;
    }

    public ImageEditItem setIcon(int i) {
        this.icon = i;
        return this;
    }

    public ImageEditItem setName(String str) {
        this.name = str;
        return this;
    }

    public ImageEditItem setNameRes(int i) {
        this.nameRes = i;
        return this;
    }

    public ImageEditItem setSelectColor(int i) {
        this.selectColor = i;
        return this;
    }

    public ImageEditItem setSelectIcon(int i) {
        this.selectIcon = i;
        return this;
    }

    public ImageEditItem setSelectorDrawable(Drawable drawable) {
        this.selectorDrawable = drawable;
        return this;
    }

    public ImageEditItem setTintColor(int i) {
        this.tintColor = i;
        return this;
    }
}
