package com.byd.common.pinyin;

import java.util.Comparator;

/* JADX INFO: loaded from: classes5.dex */
public class PinyinComparator implements Comparator<PinyinItem> {
    @Override // java.util.Comparator
    public int compare(PinyinItem pinyinItem, PinyinItem pinyinItem2) {
        String pinyin = pinyinItem.getPinyin();
        String pinyin2 = pinyinItem2.getPinyin();
        if (pinyin.equals(pinyin2)) {
            return compareDisplayName(pinyinItem.getDisplayName(), pinyinItem2.getDisplayName());
        }
        if ("#".equals(pinyin)) {
            return 1;
        }
        if ("#".equals(pinyin2)) {
            return -1;
        }
        return pinyin.compareTo(pinyin2);
    }

    private int compareDisplayName(String str, String str2) {
        if (str == null && str2 == null) {
            return 0;
        }
        if (str == null && str2 != null) {
            return -1;
        }
        if (str == null || str2 != null) {
            return str.compareTo(str2);
        }
        return 1;
    }
}
