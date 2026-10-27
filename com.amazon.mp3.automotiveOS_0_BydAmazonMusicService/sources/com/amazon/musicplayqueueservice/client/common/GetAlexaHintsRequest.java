package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GetAlexaHintsRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.GetAlexaHintsRequest");
    private String hintType;
    private String language;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractClientRequest abstractClientRequest) {
        if (abstractClientRequest == null) {
            return -1;
        }
        if (abstractClientRequest == this) {
            return 0;
        }
        if (!(abstractClientRequest instanceof GetAlexaHintsRequest)) {
            return 1;
        }
        GetAlexaHintsRequest getAlexaHintsRequest = (GetAlexaHintsRequest) abstractClientRequest;
        String language = getLanguage();
        String language2 = getAlexaHintsRequest.getLanguage();
        if (language != language2) {
            if (language == null) {
                return -1;
            }
            if (language2 == null) {
                return 1;
            }
            int iCompareTo = language.compareTo(language2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String hintType = getHintType();
        String hintType2 = getAlexaHintsRequest.getHintType();
        if (hintType != hintType2) {
            if (hintType == null) {
                return -1;
            }
            if (hintType2 == null) {
                return 1;
            }
            int iCompareTo2 = hintType.compareTo(hintType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof GetAlexaHintsRequest)) {
            return false;
        }
        GetAlexaHintsRequest getAlexaHintsRequest = (GetAlexaHintsRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getLanguage(), getAlexaHintsRequest.getLanguage()) && internalEqualityCheck(getHintType(), getAlexaHintsRequest.getHintType());
    }

    public String getHintType() {
        return this.hintType;
    }

    public String getLanguage() {
        return this.language;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLanguage(), getHintType());
    }
}
