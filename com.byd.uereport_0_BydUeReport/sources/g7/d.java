package g7;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* JADX INFO: loaded from: classes2.dex */
public class d {
    public static String[] a(char c8, h7.b bVar) throws BadHanyuPinyinOutputFormatCombination {
        String[] strArrB = b(c8);
        if (strArrB == null) {
            return null;
        }
        for (int i8 = 0; i8 < strArrB.length; i8++) {
            strArrB[i8] = c.b(strArrB[i8], bVar);
        }
        return strArrB;
    }

    public static String[] b(char c8) {
        return b.c().b(c8);
    }

    public static String[] c(char c8, h7.b bVar) throws BadHanyuPinyinOutputFormatCombination {
        return a(c8, bVar);
    }
}
