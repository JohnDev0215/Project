package com.amazon.musicplayqueueservice.client.v2.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SimplePlayableEntityIdentifier extends AbstractPlayableEntityIdentifier {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.common.SimplePlayableEntityIdentifier");
    private String identifier;
    private String identifierType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier, com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractIdentifier abstractIdentifier) {
        if (abstractIdentifier == null) {
            return -1;
        }
        if (abstractIdentifier == this) {
            return 0;
        }
        if (!(abstractIdentifier instanceof SimplePlayableEntityIdentifier)) {
            return 1;
        }
        SimplePlayableEntityIdentifier simplePlayableEntityIdentifier = (SimplePlayableEntityIdentifier) abstractIdentifier;
        String identifier = getIdentifier();
        String identifier2 = simplePlayableEntityIdentifier.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo = identifier.compareTo(identifier2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String identifierType = getIdentifierType();
        String identifierType2 = simplePlayableEntityIdentifier.getIdentifierType();
        if (identifierType != identifierType2) {
            if (identifierType == null) {
                return -1;
            }
            if (identifierType2 == null) {
                return 1;
            }
            int iCompareTo2 = identifierType.compareTo(identifierType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(abstractIdentifier);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier, com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier
    public boolean equals(Object obj) {
        if (!(obj instanceof SimplePlayableEntityIdentifier)) {
            return false;
        }
        SimplePlayableEntityIdentifier simplePlayableEntityIdentifier = (SimplePlayableEntityIdentifier) obj;
        return super.equals(obj) && internalEqualityCheck(getIdentifier(), simplePlayableEntityIdentifier.getIdentifier()) && internalEqualityCheck(getIdentifierType(), simplePlayableEntityIdentifier.getIdentifierType());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getIdentifierType() {
        return this.identifierType;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier, com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getIdentifier(), getIdentifierType());
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setIdentifierType(String str) {
        this.identifierType = str;
    }
}
