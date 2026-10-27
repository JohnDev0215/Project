package e.a.a;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* JADX INFO: loaded from: classes3.dex */
public class d {
    public static String[] a(char c2, e.a.a.f.b bVar) throws BadHanyuPinyinOutputFormatCombination {
        String[] strArrB = b(c2);
        if (strArrB == null) {
            return null;
        }
        for (int i = 0; i < strArrB.length; i++) {
            strArrB[i] = c.b(strArrB[i], bVar);
        }
        return strArrB;
    }

    public static String[] b(char c2) {
        return b.c().b(c2);
    }

    public static String[] c(char c2, e.a.a.f.b bVar) throws BadHanyuPinyinOutputFormatCombination {
        return a(c2, bVar);
    }
}
