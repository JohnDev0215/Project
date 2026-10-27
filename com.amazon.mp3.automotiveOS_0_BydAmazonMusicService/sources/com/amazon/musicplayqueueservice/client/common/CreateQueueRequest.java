package com.amazon.musicplayqueueservice.client.common;

import com.amazon.musicplayqueueservice.client.common.casting.StartAt;
import com.amazon.musicplayqueueservice.client.common.casting.Track;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class CreateQueueRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.CreateQueueRequest");
    private String identifier;
    private String identifierType;
    private List<Track> identifiers;
    private boolean isShuffled;
    private Map<String, Map<String, String>> metricsContexts;
    private String playbackPreference;
    private StartAt startAt;
    private List<String> supportedFeatures;
    private String targetId;
    private String timezoneOffset;

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
        if (!(abstractClientRequest instanceof CreateQueueRequest)) {
            return 1;
        }
        CreateQueueRequest createQueueRequest = (CreateQueueRequest) abstractClientRequest;
        List<Track> identifiers = getIdentifiers();
        List<Track> identifiers2 = createQueueRequest.getIdentifiers();
        if (identifiers != identifiers2) {
            if (identifiers == null) {
                return -1;
            }
            if (identifiers2 == null) {
                return 1;
            }
            if (identifiers instanceof Comparable) {
                int iCompareTo = ((Comparable) identifiers).compareTo(identifiers2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!identifiers.equals(identifiers2)) {
                int iHashCode = identifiers.hashCode();
                int iHashCode2 = identifiers2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String targetId = getTargetId();
        String targetId2 = createQueueRequest.getTargetId();
        if (targetId != targetId2) {
            if (targetId == null) {
                return -1;
            }
            if (targetId2 == null) {
                return 1;
            }
            int iCompareTo2 = targetId.compareTo(targetId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String playbackPreference = getPlaybackPreference();
        String playbackPreference2 = createQueueRequest.getPlaybackPreference();
        if (playbackPreference != playbackPreference2) {
            if (playbackPreference == null) {
                return -1;
            }
            if (playbackPreference2 == null) {
                return 1;
            }
            int iCompareTo3 = playbackPreference.compareTo(playbackPreference2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String timezoneOffset = getTimezoneOffset();
        String timezoneOffset2 = createQueueRequest.getTimezoneOffset();
        if (timezoneOffset != timezoneOffset2) {
            if (timezoneOffset == null) {
                return -1;
            }
            if (timezoneOffset2 == null) {
                return 1;
            }
            int iCompareTo4 = timezoneOffset.compareTo(timezoneOffset2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        if (!isIsShuffled() && createQueueRequest.isIsShuffled()) {
            return -1;
        }
        if (isIsShuffled() && !createQueueRequest.isIsShuffled()) {
            return 1;
        }
        StartAt startAt = getStartAt();
        StartAt startAt2 = createQueueRequest.getStartAt();
        if (startAt != startAt2) {
            if (startAt == null) {
                return -1;
            }
            if (startAt2 == null) {
                return 1;
            }
            int iCompareTo5 = startAt.compareTo(startAt2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String identifierType = getIdentifierType();
        String identifierType2 = createQueueRequest.getIdentifierType();
        if (identifierType != identifierType2) {
            if (identifierType == null) {
                return -1;
            }
            if (identifierType2 == null) {
                return 1;
            }
            int iCompareTo6 = identifierType.compareTo(identifierType2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Map<String, Map<String, String>> metricsContexts = getMetricsContexts();
        Map<String, Map<String, String>> metricsContexts2 = createQueueRequest.getMetricsContexts();
        if (metricsContexts != metricsContexts2) {
            if (metricsContexts == null) {
                return -1;
            }
            if (metricsContexts2 == null) {
                return 1;
            }
            if (metricsContexts instanceof Comparable) {
                int iCompareTo7 = ((Comparable) metricsContexts).compareTo(metricsContexts2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!metricsContexts.equals(metricsContexts2)) {
                int iHashCode3 = metricsContexts.hashCode();
                int iHashCode4 = metricsContexts2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String identifier = getIdentifier();
        String identifier2 = createQueueRequest.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo8 = identifier.compareTo(identifier2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<String> supportedFeatures = getSupportedFeatures();
        List<String> supportedFeatures2 = createQueueRequest.getSupportedFeatures();
        if (supportedFeatures != supportedFeatures2) {
            if (supportedFeatures == null) {
                return -1;
            }
            if (supportedFeatures2 == null) {
                return 1;
            }
            if (supportedFeatures instanceof Comparable) {
                int iCompareTo9 = ((Comparable) supportedFeatures).compareTo(supportedFeatures2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!supportedFeatures.equals(supportedFeatures2)) {
                int iHashCode5 = supportedFeatures.hashCode();
                int iHashCode6 = supportedFeatures2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof CreateQueueRequest)) {
            return false;
        }
        CreateQueueRequest createQueueRequest = (CreateQueueRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getIdentifiers(), createQueueRequest.getIdentifiers()) && internalEqualityCheck(getTargetId(), createQueueRequest.getTargetId()) && internalEqualityCheck(getPlaybackPreference(), createQueueRequest.getPlaybackPreference()) && internalEqualityCheck(getTimezoneOffset(), createQueueRequest.getTimezoneOffset()) && internalEqualityCheck(Boolean.valueOf(isIsShuffled()), Boolean.valueOf(createQueueRequest.isIsShuffled())) && internalEqualityCheck(getStartAt(), createQueueRequest.getStartAt()) && internalEqualityCheck(getIdentifierType(), createQueueRequest.getIdentifierType()) && internalEqualityCheck(getMetricsContexts(), createQueueRequest.getMetricsContexts()) && internalEqualityCheck(getIdentifier(), createQueueRequest.getIdentifier()) && internalEqualityCheck(getSupportedFeatures(), createQueueRequest.getSupportedFeatures());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getIdentifierType() {
        return this.identifierType;
    }

    public List<Track> getIdentifiers() {
        return this.identifiers;
    }

    public Map<String, Map<String, String>> getMetricsContexts() {
        return this.metricsContexts;
    }

    public String getPlaybackPreference() {
        return this.playbackPreference;
    }

    public StartAt getStartAt() {
        return this.startAt;
    }

    public List<String> getSupportedFeatures() {
        return this.supportedFeatures;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public String getTimezoneOffset() {
        return this.timezoneOffset;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getIdentifiers(), getTargetId(), getPlaybackPreference(), getTimezoneOffset(), Boolean.valueOf(isIsShuffled()), getStartAt(), getIdentifierType(), getMetricsContexts(), getIdentifier(), getSupportedFeatures());
    }

    public boolean isIsShuffled() {
        return this.isShuffled;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setIdentifierType(String str) {
        this.identifierType = str;
    }

    public void setIdentifiers(List<Track> list) {
        this.identifiers = list;
    }

    public void setMetricsContexts(Map<String, Map<String, String>> map) {
        this.metricsContexts = map;
    }

    public void setPlaybackPreference(String str) {
        this.playbackPreference = str;
    }

    public void setStartAt(StartAt startAt) {
        this.startAt = startAt;
    }

    public void setSupportedFeatures(List<String> list) {
        this.supportedFeatures = list;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }
}
