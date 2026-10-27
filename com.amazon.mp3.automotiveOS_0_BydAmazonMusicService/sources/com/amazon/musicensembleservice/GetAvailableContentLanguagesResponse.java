package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GetAvailableContentLanguagesResponse implements Comparable<GetAvailableContentLanguagesResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.GetAvailableContentLanguagesResponse");
    private Map<String, Map<String, String>> contentLanguageMap;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetAvailableContentLanguagesResponse getAvailableContentLanguagesResponse) {
        Map<String, Map<String, String>> contentLanguageMap;
        Map<String, Map<String, String>> contentLanguageMap2;
        if (getAvailableContentLanguagesResponse == null) {
            return -1;
        }
        if (getAvailableContentLanguagesResponse != this && (contentLanguageMap = getContentLanguageMap()) != (contentLanguageMap2 = getAvailableContentLanguagesResponse.getContentLanguageMap())) {
            if (contentLanguageMap == null) {
                return -1;
            }
            if (contentLanguageMap2 == null) {
                return 1;
            }
            if (contentLanguageMap instanceof Comparable) {
                int iCompareTo = ((Comparable) contentLanguageMap).compareTo(contentLanguageMap2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!contentLanguageMap.equals(contentLanguageMap2)) {
                int iHashCode = contentLanguageMap.hashCode();
                int iHashCode2 = contentLanguageMap2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GetAvailableContentLanguagesResponse) {
            return internalEqualityCheck(getContentLanguageMap(), ((GetAvailableContentLanguagesResponse) obj).getContentLanguageMap());
        }
        return false;
    }

    public Map<String, Map<String, String>> getContentLanguageMap() {
        return this.contentLanguageMap;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContentLanguageMap());
    }
}
