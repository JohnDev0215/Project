package defpackage;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* JADX INFO: loaded from: classes.dex */
public class tl {
    public static String[] a(char c, wl wlVar) throws BadHanyuPinyinOutputFormatCombination {
        String[] strArrB = b(c);
        if (strArrB == null) {
            return null;
        }
        for (int i = 0; i < strArrB.length; i++) {
            strArrB[i] = sl.b(strArrB[i], wlVar);
        }
        return strArrB;
    }

    public static String[] b(char c) {
        return rl.c().b(c);
    }

    public static String[] c(char c, wl wlVar) throws BadHanyuPinyinOutputFormatCombination {
        return a(c, wlVar);
    }
}
