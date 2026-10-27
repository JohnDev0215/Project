package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Element;
import com.hp.hpl.sparta.ParseException;

/* JADX INFO: loaded from: classes3.dex */
class PinyinRomanizationTranslator {
    PinyinRomanizationTranslator() {
    }

    static String convertRomanizationSystem(String str, PinyinRomanizationType pinyinRomanizationType, PinyinRomanizationType pinyinRomanizationType2) {
        String strExtractPinyinString = TextHelper.extractPinyinString(str);
        String strExtractToneNumber = TextHelper.extractToneNumber(str);
        try {
            Element elementXpathSelectElement = PinyinRomanizationResource.getInstance().getPinyinMappingDoc().xpathSelectElement(new StringBuffer("//").append(pinyinRomanizationType.getTagName()).append("[text()='").append(strExtractPinyinString).append("']").toString());
            if (elementXpathSelectElement == null) {
                return null;
            }
            return new StringBuffer().append(elementXpathSelectElement.xpathSelectString(new StringBuffer("../").append(pinyinRomanizationType2.getTagName()).append("/text()").toString())).append(strExtractToneNumber).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
