package c.a.e;

import com.bydux.api.common.tools.py.BydPyHelper;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* JADX INFO: compiled from: PinYinUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static String a(String str) {
        String[] contactPinyin = BydPyHelper.getContactPinyin(str);
        String str2 = "";
        if (contactPinyin != null) {
            for (int i = 0; i < contactPinyin.length; i++) {
                if (contactPinyin[i] != null && !contactPinyin[i].equals("null")) {
                    str2 = str2 + contactPinyin[i];
                }
            }
        }
        return str2;
    }

    public static String b(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        char[] charArray = str.toCharArray();
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] > 128) {
                try {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], hanyuPinyinOutputFormat);
                    if (hanyuPinyinStringArray != null && hanyuPinyinStringArray.length > 0) {
                        stringBuffer.append(hanyuPinyinStringArray[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e2) {
                    e2.printStackTrace();
                }
            } else {
                stringBuffer.append(charArray[i]);
            }
        }
        return stringBuffer.toString().replaceAll("\\W", "").trim();
    }

    public static String c(String str) {
        if (str.length() <= 0) {
            return "Z#";
        }
        String upperCase = str.substring(0, 1).toUpperCase();
        return upperCase.matches("[A-Z]") ? upperCase : "Z#";
    }
}
