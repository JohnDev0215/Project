package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.ParseException;
import com.hp.hpl.sparta.d;

/* JADX INFO: loaded from: classes.dex */
class PinyinRomanizationTranslator {
    PinyinRomanizationTranslator() {
    }

    static String convertRomanizationSystem(String str, PinyinRomanizationType pinyinRomanizationType, PinyinRomanizationType pinyinRomanizationType2) {
        String strExtractPinyinString = TextHelper.extractPinyinString(str);
        String strExtractToneNumber = TextHelper.extractToneNumber(str);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("//");
            stringBuffer.append(pinyinRomanizationType.getTagName());
            stringBuffer.append("[text()='");
            stringBuffer.append(strExtractPinyinString);
            stringBuffer.append("']");
            d dVarT = PinyinRomanizationResource.getInstance().getPinyinMappingDoc().t(stringBuffer.toString());
            if (dVarT == null) {
                return null;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("../");
            stringBuffer2.append(pinyinRomanizationType2.getTagName());
            stringBuffer2.append("/text()");
            String strA = dVarT.A(stringBuffer2.toString());
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(strA);
            stringBuffer3.append(strExtractToneNumber);
            return stringBuffer3.toString();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
