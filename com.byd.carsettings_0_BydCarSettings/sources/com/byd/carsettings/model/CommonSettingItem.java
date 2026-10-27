package com.byd.carsettings.model;

import com.byd.mvp.model.BydData;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class CommonSettingItem extends BydData implements Comparable<CommonSettingItem> {
    public static final int TYPE_AL_AREA = 8;
    public static final int TYPE_RADIO_BUTTON_2 = 3;
    public static final int TYPE_RADIO_BUTTON_3 = 4;
    public static final int TYPE_SEEKBAR_CUSTOM_0 = 1;
    public static final int TYPE_SEEKBAR_CUSTOM_1 = 2;
    public static final int TYPE_SEEKBAR_RADIO_GROUP = 5;
    public static final int TYPE_SINGLE_SEEKBAR = 0;
    public static final int TYPE_SWITCH_1 = 6;
    public static final int TYPE_SWITCH_2 = 7;
    private int index;
    private int index1;
    private CommonItemId originalId;
    private String[] rbtnTextArr;
    private String title;
    private String title1;
    private int orderId = 0;
    private int viewType = 0;
    private int max = 0;
    private int min = 0;

    public CommonSettingItem() {
    }

    public int getIndex() {
        return this.index;
    }

    public int getIndex1() {
        return this.index1;
    }

    public int getMax() {
        return this.max;
    }

    public int getMin() {
        return this.min;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public CommonItemId getOriginalId() {
        return this.originalId;
    }

    public String[] getRbtnTextArr() {
        return this.rbtnTextArr;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitle1() {
        return this.title1;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setIndex1(int i) {
        this.index1 = i;
    }

    public void setMax(int i) {
        this.max = i;
    }

    public void setMin(int i) {
        this.min = i;
    }

    public void setOrderId(int i) {
        this.orderId = i;
    }

    public void setRbtnTextArr(String[] strArr) {
        this.rbtnTextArr = strArr;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitle1(String str) {
        this.title1 = str;
    }

    public void setViewType(int i) {
        this.viewType = i;
    }

    public String toString() {
        return "CommonSettingItem{orderId=" + this.orderId + ", viewType=" + this.viewType + ", title='" + this.title + "', max=" + this.max + ", min=" + this.min + ", rbtnTextArr=" + Arrays.toString(this.rbtnTextArr) + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(CommonSettingItem commonSettingItem) {
        if (commonSettingItem.getOrderId() > getOrderId()) {
            return -1;
        }
        return commonSettingItem.getOrderId() < getOrderId() ? 1 : 0;
    }

    public CommonSettingItem(CommonItemId commonItemId) {
        this.originalId = commonItemId;
    }
}
