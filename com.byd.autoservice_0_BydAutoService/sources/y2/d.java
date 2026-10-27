package y2;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static String[] a(char c3, z2.b bVar) throws BadHanyuPinyinOutputFormatCombination {
        String[] strArrB = b(c3);
        if (strArrB == null) {
            return null;
        }
        for (int i3 = 0; i3 < strArrB.length; i3++) {
            strArrB[i3] = c.b(strArrB[i3], bVar);
        }
        return strArrB;
    }

    public static String[] b(char c3) {
        return b.c().b(c3);
    }

    public static String[] c(char c3, z2.b bVar) throws BadHanyuPinyinOutputFormatCombination {
        return a(c3, bVar);
    }
}
