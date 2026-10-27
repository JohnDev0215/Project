package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class LanguageOfPerformance implements Comparable<LanguageOfPerformance> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LanguageOfPerformance");
    private String languageOfPerformance;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated LanguageOfPerformance languageOfPerformance) {
        String languageOfPerformance2;
        String languageOfPerformance3;
        if (languageOfPerformance == null) {
            return -1;
        }
        if (languageOfPerformance != this && (languageOfPerformance2 = getLanguageOfPerformance()) != (languageOfPerformance3 = languageOfPerformance.getLanguageOfPerformance())) {
            if (languageOfPerformance2 == null) {
                return -1;
            }
            if (languageOfPerformance3 == null) {
                return 1;
            }
            int iCompareTo = languageOfPerformance2.compareTo(languageOfPerformance3);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof LanguageOfPerformance) {
            return internalEqualityCheck(getLanguageOfPerformance(), ((LanguageOfPerformance) obj).getLanguageOfPerformance());
        }
        return false;
    }

    public String getLanguageOfPerformance() {
        return this.languageOfPerformance;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getLanguageOfPerformance());
    }

    public void setLanguageOfPerformance(String str) {
        this.languageOfPerformance = str;
    }
}
