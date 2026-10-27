package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class AudioReference extends AudioSourceBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.AudioReference");
    private String identifier;
    private String source;
    private String token;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.AudioSourceBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AudioSourceBase audioSourceBase) {
        if (audioSourceBase == null) {
            return -1;
        }
        if (audioSourceBase == this) {
            return 0;
        }
        if (!(audioSourceBase instanceof AudioReference)) {
            return 1;
        }
        AudioReference audioReference = (AudioReference) audioSourceBase;
        String source = getSource();
        String source2 = audioReference.getSource();
        if (source != source2) {
            if (source == null) {
                return -1;
            }
            if (source2 == null) {
                return 1;
            }
            int iCompareTo = source.compareTo(source2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String token = getToken();
        String token2 = audioReference.getToken();
        if (token != token2) {
            if (token == null) {
                return -1;
            }
            if (token2 == null) {
                return 1;
            }
            int iCompareTo2 = token.compareTo(token2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String identifier = getIdentifier();
        String identifier2 = audioReference.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo3 = identifier.compareTo(identifier2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(audioSourceBase);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AudioSourceBase
    public boolean equals(Object obj) {
        if (!(obj instanceof AudioReference)) {
            return false;
        }
        AudioReference audioReference = (AudioReference) obj;
        return super.equals(obj) && internalEqualityCheck(getSource(), audioReference.getSource()) && internalEqualityCheck(getToken(), audioReference.getToken()) && internalEqualityCheck(getIdentifier(), audioReference.getIdentifier());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getSource() {
        return this.source;
    }

    public String getToken() {
        return this.token;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AudioSourceBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSource(), getToken(), getIdentifier());
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public void setToken(String str) {
        this.token = str;
    }
}
