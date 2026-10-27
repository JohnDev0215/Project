package com.byd.common.pinyin;

/* JADX INFO: loaded from: classes5.dex */
public interface PinyinItem {
    public static final String UNKNOWN = "#";

    default String getDisplayName() {
        return "";
    }

    String getPinyin();
}
