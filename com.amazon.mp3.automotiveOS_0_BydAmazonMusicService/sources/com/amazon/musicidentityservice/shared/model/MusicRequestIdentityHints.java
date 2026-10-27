package com.amazon.musicidentityservice.shared.model;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MusicRequestIdentityHints implements Comparable<MusicRequestIdentityHints> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicidentityservice.shared.model.MusicRequestIdentityHints");
    private String activeCid;
    private String deviceId;
    private String deviceType;
    private Map<String, Boolean> identityFlags;
    private Map<String, String> identityMetadata;
    private String linkedMusicCid;
    private Long linkedMusicCidConfidenceScore;
    private String speakerCid;
    private Long speakerCidConfidenceScore;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MusicRequestIdentityHints musicRequestIdentityHints) {
        if (musicRequestIdentityHints == null) {
            return -1;
        }
        if (musicRequestIdentityHints == this) {
            return 0;
        }
        String linkedMusicCid = getLinkedMusicCid();
        String linkedMusicCid2 = musicRequestIdentityHints.getLinkedMusicCid();
        if (linkedMusicCid != linkedMusicCid2) {
            if (linkedMusicCid == null) {
                return -1;
            }
            if (linkedMusicCid2 == null) {
                return 1;
            }
            int iCompareTo = linkedMusicCid.compareTo(linkedMusicCid2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, String> identityMetadata = getIdentityMetadata();
        Map<String, String> identityMetadata2 = musicRequestIdentityHints.getIdentityMetadata();
        if (identityMetadata != identityMetadata2) {
            if (identityMetadata == null) {
                return -1;
            }
            if (identityMetadata2 == null) {
                return 1;
            }
            if (identityMetadata instanceof Comparable) {
                int iCompareTo2 = ((Comparable) identityMetadata).compareTo(identityMetadata2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!identityMetadata.equals(identityMetadata2)) {
                int iHashCode = identityMetadata.hashCode();
                int iHashCode2 = identityMetadata2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Long linkedMusicCidConfidenceScore = getLinkedMusicCidConfidenceScore();
        Long linkedMusicCidConfidenceScore2 = musicRequestIdentityHints.getLinkedMusicCidConfidenceScore();
        if (linkedMusicCidConfidenceScore != linkedMusicCidConfidenceScore2) {
            if (linkedMusicCidConfidenceScore == null) {
                return -1;
            }
            if (linkedMusicCidConfidenceScore2 == null) {
                return 1;
            }
            int iCompareTo3 = linkedMusicCidConfidenceScore.compareTo(linkedMusicCidConfidenceScore2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String deviceId = getDeviceId();
        String deviceId2 = musicRequestIdentityHints.getDeviceId();
        if (deviceId != deviceId2) {
            if (deviceId == null) {
                return -1;
            }
            if (deviceId2 == null) {
                return 1;
            }
            int iCompareTo4 = deviceId.compareTo(deviceId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String deviceType = getDeviceType();
        String deviceType2 = musicRequestIdentityHints.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo5 = deviceType.compareTo(deviceType2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String speakerCid = getSpeakerCid();
        String speakerCid2 = musicRequestIdentityHints.getSpeakerCid();
        if (speakerCid != speakerCid2) {
            if (speakerCid == null) {
                return -1;
            }
            if (speakerCid2 == null) {
                return 1;
            }
            int iCompareTo6 = speakerCid.compareTo(speakerCid2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Map<String, Boolean> identityFlags = getIdentityFlags();
        Map<String, Boolean> identityFlags2 = musicRequestIdentityHints.getIdentityFlags();
        if (identityFlags != identityFlags2) {
            if (identityFlags == null) {
                return -1;
            }
            if (identityFlags2 == null) {
                return 1;
            }
            if (identityFlags instanceof Comparable) {
                int iCompareTo7 = ((Comparable) identityFlags).compareTo(identityFlags2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!identityFlags.equals(identityFlags2)) {
                int iHashCode3 = identityFlags.hashCode();
                int iHashCode4 = identityFlags2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Long speakerCidConfidenceScore = getSpeakerCidConfidenceScore();
        Long speakerCidConfidenceScore2 = musicRequestIdentityHints.getSpeakerCidConfidenceScore();
        if (speakerCidConfidenceScore != speakerCidConfidenceScore2) {
            if (speakerCidConfidenceScore == null) {
                return -1;
            }
            if (speakerCidConfidenceScore2 == null) {
                return 1;
            }
            int iCompareTo8 = speakerCidConfidenceScore.compareTo(speakerCidConfidenceScore2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String activeCid = getActiveCid();
        String activeCid2 = musicRequestIdentityHints.getActiveCid();
        if (activeCid != activeCid2) {
            if (activeCid == null) {
                return -1;
            }
            if (activeCid2 == null) {
                return 1;
            }
            int iCompareTo9 = activeCid.compareTo(activeCid2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MusicRequestIdentityHints)) {
            return false;
        }
        MusicRequestIdentityHints musicRequestIdentityHints = (MusicRequestIdentityHints) obj;
        return internalEqualityCheck(getLinkedMusicCid(), musicRequestIdentityHints.getLinkedMusicCid()) && internalEqualityCheck(getIdentityMetadata(), musicRequestIdentityHints.getIdentityMetadata()) && internalEqualityCheck(getLinkedMusicCidConfidenceScore(), musicRequestIdentityHints.getLinkedMusicCidConfidenceScore()) && internalEqualityCheck(getDeviceId(), musicRequestIdentityHints.getDeviceId()) && internalEqualityCheck(getDeviceType(), musicRequestIdentityHints.getDeviceType()) && internalEqualityCheck(getSpeakerCid(), musicRequestIdentityHints.getSpeakerCid()) && internalEqualityCheck(getIdentityFlags(), musicRequestIdentityHints.getIdentityFlags()) && internalEqualityCheck(getSpeakerCidConfidenceScore(), musicRequestIdentityHints.getSpeakerCidConfidenceScore()) && internalEqualityCheck(getActiveCid(), musicRequestIdentityHints.getActiveCid());
    }

    public String getActiveCid() {
        return this.activeCid;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public Map<String, Boolean> getIdentityFlags() {
        return this.identityFlags;
    }

    public Map<String, String> getIdentityMetadata() {
        return this.identityMetadata;
    }

    public String getLinkedMusicCid() {
        return this.linkedMusicCid;
    }

    public Long getLinkedMusicCidConfidenceScore() {
        return this.linkedMusicCidConfidenceScore;
    }

    public String getSpeakerCid() {
        return this.speakerCid;
    }

    public Long getSpeakerCidConfidenceScore() {
        return this.speakerCidConfidenceScore;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getLinkedMusicCid(), getIdentityMetadata(), getLinkedMusicCidConfidenceScore(), getDeviceId(), getDeviceType(), getSpeakerCid(), getIdentityFlags(), getSpeakerCidConfidenceScore(), getActiveCid());
    }

    public void setActiveCid(String str) {
        this.activeCid = str;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setIdentityFlags(Map<String, Boolean> map) {
        this.identityFlags = map;
    }

    public void setIdentityMetadata(Map<String, String> map) {
        this.identityMetadata = map;
    }

    public void setLinkedMusicCid(String str) {
        this.linkedMusicCid = str;
    }

    public void setLinkedMusicCidConfidenceScore(Long l) {
        this.linkedMusicCidConfidenceScore = l;
    }

    public void setSpeakerCid(String str) {
        this.speakerCid = str;
    }

    public void setSpeakerCidConfidenceScore(Long l) {
        this.speakerCidConfidenceScore = l;
    }
}
