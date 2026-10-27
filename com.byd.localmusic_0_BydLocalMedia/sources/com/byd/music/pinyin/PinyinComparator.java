package com.byd.music.pinyin;

import com.byd.music.utils.LogUtil;
import java.util.Comparator;

/* JADX INFO: loaded from: classes5.dex */
public class PinyinComparator implements Comparator<PinyinItem> {
    @Override // java.util.Comparator
    public int compare(PinyinItem pinyinItem, PinyinItem pinyinItem2) {
        int i;
        int iDigitEndIndexStartingWithPrime;
        int iDigitEndIndexStartingWithPrime2;
        String itemPinyin = pinyinItem.getItemPinyin();
        String itemPinyin2 = pinyinItem2.getItemPinyin();
        if (itemPinyin == null || itemPinyin2 == null) {
            return -1;
        }
        int i2 = 0;
        if (itemPinyin.equals(itemPinyin2)) {
            return 0;
        }
        if (itemPinyin.startsWith("#") && itemPinyin2.startsWith("#")) {
            if (itemPinyin.length() <= 1 || (iDigitEndIndexStartingWithPrime2 = digitEndIndexStartingWithPrime(itemPinyin)) <= 1) {
                i = 0;
            } else {
                try {
                    i = Integer.parseInt(itemPinyin.substring(1, iDigitEndIndexStartingWithPrime2));
                } catch (NumberFormatException e) {
                    LogUtil.d("PinyinComparator", "The num is too large to convert to int, covert as MAX.");
                    e.printStackTrace();
                    i = Integer.MAX_VALUE;
                }
            }
            if (itemPinyin2.length() > 1 && (iDigitEndIndexStartingWithPrime = digitEndIndexStartingWithPrime(itemPinyin2)) > 1) {
                try {
                    i2 = Integer.parseInt(itemPinyin2.substring(1, iDigitEndIndexStartingWithPrime));
                } catch (NumberFormatException e2) {
                    LogUtil.d("PinyinComparator", "The num is too large to convert to int, covert as MAX.");
                    e2.printStackTrace();
                    i2 = Integer.MAX_VALUE;
                }
            }
            return i - i2;
        }
        if (itemPinyin.startsWith("#")) {
            return 1;
        }
        if (itemPinyin2.startsWith("#")) {
            return -1;
        }
        return itemPinyin.compareTo(itemPinyin2);
    }

    private int digitEndIndexStartingWithPrime(String str) {
        int iCodePointAt;
        for (int i = 0; i < str.length(); i++) {
            if (i != 0 && ((iCodePointAt = str.codePointAt(i)) < 48 || iCodePointAt > 57)) {
                return i;
            }
        }
        return 0;
    }
}
