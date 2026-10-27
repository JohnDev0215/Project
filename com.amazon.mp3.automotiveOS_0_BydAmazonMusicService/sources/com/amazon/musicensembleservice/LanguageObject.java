package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageObject implements Comparable<LanguageObject> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.LanguageObject");
    private String languageName;
    private String languagesOfPerformanceFilter;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated LanguageObject languageObject) {
        if (languageObject == null) {
            return -1;
        }
        if (languageObject == this) {
            return 0;
        }
        String languagesOfPerformanceFilter = getLanguagesOfPerformanceFilter();
        String languagesOfPerformanceFilter2 = languageObject.getLanguagesOfPerformanceFilter();
        if (languagesOfPerformanceFilter != languagesOfPerformanceFilter2) {
            if (languagesOfPerformanceFilter == null) {
                return -1;
            }
            if (languagesOfPerformanceFilter2 == null) {
                return 1;
            }
            int iCompareTo = languagesOfPerformanceFilter.compareTo(languagesOfPerformanceFilter2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String languageName = getLanguageName();
        String languageName2 = languageObject.getLanguageName();
        if (languageName != languageName2) {
            if (languageName == null) {
                return -1;
            }
            if (languageName2 == null) {
                return 1;
            }
            int iCompareTo2 = languageName.compareTo(languageName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LanguageObject)) {
            return false;
        }
        LanguageObject languageObject = (LanguageObject) obj;
        return internalEqualityCheck(getLanguagesOfPerformanceFilter(), languageObject.getLanguagesOfPerformanceFilter()) && internalEqualityCheck(getLanguageName(), languageObject.getLanguageName());
    }

    public String getLanguageName() {
        return this.languageName;
    }

    public String getLanguagesOfPerformanceFilter() {
        return this.languagesOfPerformanceFilter;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getLanguagesOfPerformanceFilter(), getLanguageName());
    }

    public void setLanguageName(String str) {
        this.languageName = str;
    }

    public void setLanguagesOfPerformanceFilter(String str) {
        this.languagesOfPerformanceFilter = str;
    }
}
