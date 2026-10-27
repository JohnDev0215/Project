package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ProgramMetadata implements Comparable<ProgramMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.ProgramMetadata");
    private String mediaContentId;
    private String programId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ProgramMetadata programMetadata) {
        if (programMetadata == null) {
            return -1;
        }
        if (programMetadata == this) {
            return 0;
        }
        String programId = getProgramId();
        String programId2 = programMetadata.getProgramId();
        if (programId != programId2) {
            if (programId == null) {
                return -1;
            }
            if (programId2 == null) {
                return 1;
            }
            int iCompareTo = programId.compareTo(programId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String mediaContentId = getMediaContentId();
        String mediaContentId2 = programMetadata.getMediaContentId();
        if (mediaContentId != mediaContentId2) {
            if (mediaContentId == null) {
                return -1;
            }
            if (mediaContentId2 == null) {
                return 1;
            }
            int iCompareTo2 = mediaContentId.compareTo(mediaContentId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProgramMetadata)) {
            return false;
        }
        ProgramMetadata programMetadata = (ProgramMetadata) obj;
        return internalEqualityCheck(getProgramId(), programMetadata.getProgramId()) && internalEqualityCheck(getMediaContentId(), programMetadata.getMediaContentId());
    }

    public String getMediaContentId() {
        return this.mediaContentId;
    }

    public String getProgramId() {
        return this.programId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getProgramId(), getMediaContentId());
    }

    public void setMediaContentId(String str) {
        this.mediaContentId = str;
    }

    public void setProgramId(String str) {
        this.programId = str;
    }
}
