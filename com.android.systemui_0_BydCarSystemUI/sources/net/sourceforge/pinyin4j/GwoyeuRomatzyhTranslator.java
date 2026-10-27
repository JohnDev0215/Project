package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.Element;
import com.hp.hpl.sparta.ParseException;

/* JADX INFO: loaded from: classes2.dex */
class GwoyeuRomatzyhTranslator {
    private static String[] tones = {"_I", "_II", "_III", "_IV", "_V"};

    GwoyeuRomatzyhTranslator() {
    }

    static String convertHanyuPinyinToGwoyeuRomatzyh(String str) {
        String strExtractPinyinString = TextHelper.extractPinyinString(str);
        String strExtractToneNumber = TextHelper.extractToneNumber(str);
        try {
            Element elementXpathSelectElement = GwoyeuRomatzyhResource.getInstance().getPinyinToGwoyeuMappingDoc().xpathSelectElement(new StringBuffer().append("//").append(PinyinRomanizationType.HANYU_PINYIN.getTagName()).append("[text()='").append(strExtractPinyinString).append("']").toString());
            if (elementXpathSelectElement != null) {
                return elementXpathSelectElement.xpathSelectString(new StringBuffer().append("../").append(PinyinRomanizationType.GWOYEU_ROMATZYH.getTagName()).append(tones[Integer.parseInt(strExtractToneNumber) - 1]).append("/text()").toString());
            }
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
