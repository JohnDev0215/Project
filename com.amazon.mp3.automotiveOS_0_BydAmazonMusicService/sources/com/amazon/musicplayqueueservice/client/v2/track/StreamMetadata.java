package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.musicplayqueueservice.client.common.ParentalControls;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class StreamMetadata extends QueuedEntityMetadataBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.StreamMetadata");
    private String identifier;
    private String identifierType;
    private String name;
    private ParentalControls parentalControls;
    private String programId;
    private String serviceTier;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated QueuedEntityMetadataBase queuedEntityMetadataBase) {
        if (queuedEntityMetadataBase == null) {
            return -1;
        }
        if (queuedEntityMetadataBase == this) {
            return 0;
        }
        if (!(queuedEntityMetadataBase instanceof StreamMetadata)) {
            return 1;
        }
        StreamMetadata streamMetadata = (StreamMetadata) queuedEntityMetadataBase;
        String name = getName();
        String name2 = streamMetadata.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo = name.compareTo(name2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String identifier = getIdentifier();
        String identifier2 = streamMetadata.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo2 = identifier.compareTo(identifier2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String identifierType = getIdentifierType();
        String identifierType2 = streamMetadata.getIdentifierType();
        if (identifierType != identifierType2) {
            if (identifierType == null) {
                return -1;
            }
            if (identifierType2 == null) {
                return 1;
            }
            int iCompareTo3 = identifierType.compareTo(identifierType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = streamMetadata.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo4 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String serviceTier = getServiceTier();
        String serviceTier2 = streamMetadata.getServiceTier();
        if (serviceTier != serviceTier2) {
            if (serviceTier == null) {
                return -1;
            }
            if (serviceTier2 == null) {
                return 1;
            }
            int iCompareTo5 = serviceTier.compareTo(serviceTier2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String programId = getProgramId();
        String programId2 = streamMetadata.getProgramId();
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
        return super.compareTo(queuedEntityMetadataBase);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase
    public boolean equals(Object obj) {
        if (!(obj instanceof StreamMetadata)) {
            return false;
        }
        StreamMetadata streamMetadata = (StreamMetadata) obj;
        return super.equals(obj) && internalEqualityCheck(getName(), streamMetadata.getName()) && internalEqualityCheck(getIdentifier(), streamMetadata.getIdentifier()) && internalEqualityCheck(getIdentifierType(), streamMetadata.getIdentifierType()) && internalEqualityCheck(getParentalControls(), streamMetadata.getParentalControls()) && internalEqualityCheck(getServiceTier(), streamMetadata.getServiceTier()) && internalEqualityCheck(getProgramId(), streamMetadata.getProgramId());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getIdentifierType() {
        return this.identifierType;
    }

    public String getName() {
        return this.name;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getProgramId() {
        return this.programId;
    }

    public String getServiceTier() {
        return this.serviceTier;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getName(), getIdentifier(), getIdentifierType(), getParentalControls(), getServiceTier(), getProgramId());
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setIdentifierType(String str) {
        this.identifierType = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setProgramId(String str) {
        this.programId = str;
    }

    public void setServiceTier(String str) {
        this.serviceTier = str;
    }
}
