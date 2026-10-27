package net.sourceforge.pinyin4j;

import com.hp.hpl.sparta.ParseException;
import com.hp.hpl.sparta.d;

/* JADX INFO: loaded from: classes.dex */
class GwoyeuRomatzyhTranslator {
    private static String[] tones = {"_I", "_II", "_III", "_IV", "_V"};

    GwoyeuRomatzyhTranslator() {
    }

    static String convertHanyuPinyinToGwoyeuRomatzyh(String str) {
        String strExtractPinyinString = TextHelper.extractPinyinString(str);
        String strExtractToneNumber = TextHelper.extractToneNumber(str);
        try {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("//");
            stringBuffer.append(PinyinRomanizationType.HANYU_PINYIN.getTagName());
            stringBuffer.append("[text()='");
            stringBuffer.append(strExtractPinyinString);
            stringBuffer.append("']");
            d dVarT = GwoyeuRomatzyhResource.getInstance().getPinyinToGwoyeuMappingDoc().t(stringBuffer.toString());
            if (dVarT == null) {
                return null;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("../");
            stringBuffer2.append(PinyinRomanizationType.GWOYEU_ROMATZYH.getTagName());
            stringBuffer2.append(tones[Integer.parseInt(strExtractToneNumber) - 1]);
            stringBuffer2.append("/text()");
            return dVarT.A(stringBuffer2.toString());
        } catch (ParseException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
