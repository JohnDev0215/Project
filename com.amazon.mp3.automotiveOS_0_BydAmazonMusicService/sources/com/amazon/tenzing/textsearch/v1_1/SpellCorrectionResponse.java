package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SpellCorrectionResponse implements Comparable<SpellCorrectionResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SpellCorrectionResponse");
    private String blockRef;
    private String contentInfoInstId;
    private SpellCorrection executed;
    private SpellCorrection offer;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SpellCorrectionResponse spellCorrectionResponse) {
        if (spellCorrectionResponse == null) {
            return -1;
        }
        if (spellCorrectionResponse == this) {
            return 0;
        }
        String blockRef = getBlockRef();
        String blockRef2 = spellCorrectionResponse.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo = blockRef.compareTo(blockRef2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String contentInfoInstId = getContentInfoInstId();
        String contentInfoInstId2 = spellCorrectionResponse.getContentInfoInstId();
        if (contentInfoInstId != contentInfoInstId2) {
            if (contentInfoInstId == null) {
                return -1;
            }
            if (contentInfoInstId2 == null) {
                return 1;
            }
            int iCompareTo2 = contentInfoInstId.compareTo(contentInfoInstId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        SpellCorrection executed = getExecuted();
        SpellCorrection executed2 = spellCorrectionResponse.getExecuted();
        if (executed != executed2) {
            if (executed == null) {
                return -1;
            }
            if (executed2 == null) {
                return 1;
            }
            int iCompareTo3 = executed.compareTo(executed2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        SpellCorrection offer = getOffer();
        SpellCorrection offer2 = spellCorrectionResponse.getOffer();
        if (offer != offer2) {
            if (offer == null) {
                return -1;
            }
            if (offer2 == null) {
                return 1;
            }
            int iCompareTo4 = offer.compareTo(offer2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SpellCorrectionResponse)) {
            return false;
        }
        SpellCorrectionResponse spellCorrectionResponse = (SpellCorrectionResponse) obj;
        return internalEqualityCheck(getBlockRef(), spellCorrectionResponse.getBlockRef()) && internalEqualityCheck(getContentInfoInstId(), spellCorrectionResponse.getContentInfoInstId()) && internalEqualityCheck(getExecuted(), spellCorrectionResponse.getExecuted()) && internalEqualityCheck(getOffer(), spellCorrectionResponse.getOffer());
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public String getContentInfoInstId() {
        return this.contentInfoInstId;
    }

    public SpellCorrection getExecuted() {
        return this.executed;
    }

    public SpellCorrection getOffer() {
        return this.offer;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBlockRef(), getContentInfoInstId(), getExecuted(), getOffer());
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setContentInfoInstId(String str) {
        this.contentInfoInstId = str;
    }

    public void setExecuted(SpellCorrection spellCorrection) {
        this.executed = spellCorrection;
    }

    public void setOffer(SpellCorrection spellCorrection) {
        this.offer = spellCorrection;
    }
}
