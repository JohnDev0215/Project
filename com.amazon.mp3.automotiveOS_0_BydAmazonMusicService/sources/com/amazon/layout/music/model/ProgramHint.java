package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class ProgramHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.ProgramHint");
    private Boolean isBundesligaFree;
    private Boolean isBundesligaPrime;
    private Boolean isBundesligaUnlimited;
    private String programId;
    private String programType;
    private String streamStatus;
    private String streamType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Hint, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        if (hint == null) {
            return -1;
        }
        if (hint == this) {
            return 0;
        }
        if (!(hint instanceof ProgramHint)) {
            return 1;
        }
        ProgramHint programHint = (ProgramHint) hint;
        Boolean boolIsIsBundesligaFree = isIsBundesligaFree();
        Boolean boolIsIsBundesligaFree2 = programHint.isIsBundesligaFree();
        if (boolIsIsBundesligaFree != boolIsIsBundesligaFree2) {
            if (boolIsIsBundesligaFree == null) {
                return -1;
            }
            if (boolIsIsBundesligaFree2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsBundesligaFree.compareTo(boolIsIsBundesligaFree2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIsBundesligaPrime = isIsBundesligaPrime();
        Boolean boolIsIsBundesligaPrime2 = programHint.isIsBundesligaPrime();
        if (boolIsIsBundesligaPrime != boolIsIsBundesligaPrime2) {
            if (boolIsIsBundesligaPrime == null) {
                return -1;
            }
            if (boolIsIsBundesligaPrime2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIsBundesligaPrime.compareTo(boolIsIsBundesligaPrime2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String programType = getProgramType();
        String programType2 = programHint.getProgramType();
        if (programType != programType2) {
            if (programType == null) {
                return -1;
            }
            if (programType2 == null) {
                return 1;
            }
            int iCompareTo3 = programType.compareTo(programType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String streamStatus = getStreamStatus();
        String streamStatus2 = programHint.getStreamStatus();
        if (streamStatus != streamStatus2) {
            if (streamStatus == null) {
                return -1;
            }
            if (streamStatus2 == null) {
                return 1;
            }
            int iCompareTo4 = streamStatus.compareTo(streamStatus2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsBundesligaUnlimited = isIsBundesligaUnlimited();
        Boolean boolIsIsBundesligaUnlimited2 = programHint.isIsBundesligaUnlimited();
        if (boolIsIsBundesligaUnlimited != boolIsIsBundesligaUnlimited2) {
            if (boolIsIsBundesligaUnlimited == null) {
                return -1;
            }
            if (boolIsIsBundesligaUnlimited2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsBundesligaUnlimited.compareTo(boolIsIsBundesligaUnlimited2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String programId = getProgramId();
        String programId2 = programHint.getProgramId();
        if (programId != programId2) {
            if (programId == null) {
                return -1;
            }
            if (programId2 == null) {
                return 1;
            }
            int iCompareTo6 = programId.compareTo(programId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String streamType = getStreamType();
        String streamType2 = programHint.getStreamType();
        if (streamType != streamType2) {
            if (streamType == null) {
                return -1;
            }
            if (streamType2 == null) {
                return 1;
            }
            int iCompareTo7 = streamType.compareTo(streamType2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (!(obj instanceof ProgramHint)) {
            return false;
        }
        ProgramHint programHint = (ProgramHint) obj;
        return super.equals(obj) && internalEqualityCheck(isIsBundesligaFree(), programHint.isIsBundesligaFree()) && internalEqualityCheck(isIsBundesligaPrime(), programHint.isIsBundesligaPrime()) && internalEqualityCheck(getProgramType(), programHint.getProgramType()) && internalEqualityCheck(getStreamStatus(), programHint.getStreamStatus()) && internalEqualityCheck(isIsBundesligaUnlimited(), programHint.isIsBundesligaUnlimited()) && internalEqualityCheck(getProgramId(), programHint.getProgramId()) && internalEqualityCheck(getStreamType(), programHint.getStreamType());
    }

    public String getProgramId() {
        return this.programId;
    }

    public String getProgramType() {
        return this.programType;
    }

    public String getStreamStatus() {
        return this.streamStatus;
    }

    public String getStreamType() {
        return this.streamType;
    }

    @Override // com.amazon.layout.music.model.Hint
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isIsBundesligaFree(), isIsBundesligaPrime(), getProgramType(), getStreamStatus(), isIsBundesligaUnlimited(), getProgramId(), getStreamType());
    }

    public Boolean isIsBundesligaFree() {
        return this.isBundesligaFree;
    }

    public Boolean isIsBundesligaPrime() {
        return this.isBundesligaPrime;
    }

    public Boolean isIsBundesligaUnlimited() {
        return this.isBundesligaUnlimited;
    }

    public void setIsBundesligaFree(Boolean bool) {
        this.isBundesligaFree = bool;
    }

    public void setIsBundesligaPrime(Boolean bool) {
        this.isBundesligaPrime = bool;
    }

    public void setIsBundesligaUnlimited(Boolean bool) {
        this.isBundesligaUnlimited = bool;
    }

    public void setProgramId(String str) {
        this.programId = str;
    }

    public void setProgramType(String str) {
        this.programType = str;
    }

    public void setStreamStatus(String str) {
        this.streamStatus = str;
    }

    public void setStreamType(String str) {
        this.streamType = str;
    }
}
