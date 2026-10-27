package d.a.a;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/* JADX INFO: loaded from: classes.dex */
class c {
    private static String a(String str) {
        String lowerCase = str.toLowerCase();
        if (!lowerCase.matches("[a-z]*[1-5]?")) {
            return lowerCase;
        }
        if (!lowerCase.matches("[a-z]*[1-5]")) {
            return lowerCase.replaceAll("v", "ü");
        }
        int numericValue = Character.getNumericValue(lowerCase.charAt(lowerCase.length() - 1));
        char cCharAt = 'a';
        int iIndexOf = lowerCase.indexOf(97);
        int iIndexOf2 = lowerCase.indexOf(101);
        int iIndexOf3 = lowerCase.indexOf("ou");
        if (-1 == iIndexOf) {
            if (-1 == iIndexOf2) {
                if (-1 == iIndexOf3) {
                    iIndexOf = lowerCase.length() - 1;
                    while (true) {
                        if (iIndexOf < 0) {
                            cCharAt = '$';
                            iIndexOf = -1;
                            break;
                        }
                        if (String.valueOf(lowerCase.charAt(iIndexOf)).matches("[aeiouv]")) {
                            cCharAt = lowerCase.charAt(iIndexOf);
                            break;
                        }
                        iIndexOf--;
                    }
                } else {
                    cCharAt = "ou".charAt(0);
                    iIndexOf = iIndexOf3;
                }
            } else {
                cCharAt = 'e';
                iIndexOf = iIndexOf2;
            }
        }
        if ('$' == cCharAt || -1 == iIndexOf) {
            return lowerCase;
        }
        char cCharAt2 = "āáăàaēéĕèeīíĭìiōóŏòoūúŭùuǖǘǚǜü".charAt(("aeiouv".indexOf(cCharAt) * 5) + (numericValue - 1));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(lowerCase.substring(0, iIndexOf).replaceAll("v", "ü"));
        stringBuffer.append(cCharAt2);
        stringBuffer.append(lowerCase.substring(iIndexOf + 1, lowerCase.length() - 1).replaceAll("v", "ü"));
        return stringBuffer.toString();
    }

    static String b(String str, d.a.a.f.b bVar) throws BadHanyuPinyinOutputFormatCombination {
        d.a.a.f.c cVar = d.a.a.f.c.f2641c;
        if (cVar == bVar.b() && (d.a.a.f.d.f2644b == bVar.c() || d.a.a.f.d.f2643a == bVar.c())) {
            throw new BadHanyuPinyinOutputFormatCombination("tone marks cannot be added to v or u:");
        }
        if (d.a.a.f.c.f2640b == bVar.b()) {
            str = str.replaceAll("[1-5]", "");
        } else if (cVar == bVar.b()) {
            str = a(str.replaceAll("u:", "v"));
        }
        if (d.a.a.f.d.f2644b == bVar.c()) {
            str = str.replaceAll("u:", "v");
        } else if (d.a.a.f.d.f2645c == bVar.c()) {
            str = str.replaceAll("u:", "ü");
        }
        return d.a.a.f.a.f2633a == bVar.a() ? str.toUpperCase() : str;
    }
}
