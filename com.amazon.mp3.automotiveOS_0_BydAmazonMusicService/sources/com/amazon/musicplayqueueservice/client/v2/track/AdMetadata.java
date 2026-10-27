package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AdMetadata extends QueuedEntityMetadataBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.AdMetadata");
    private String advertiserName;
    private List<AdAudioDetails> audioDetailOptions;
    private String productName;
    private List<AdVisualDetails> visualDetailOptions;

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
        if (!(queuedEntityMetadataBase instanceof AdMetadata)) {
            return 1;
        }
        AdMetadata adMetadata = (AdMetadata) queuedEntityMetadataBase;
        String productName = getProductName();
        String productName2 = adMetadata.getProductName();
        if (productName != productName2) {
            if (productName == null) {
                return -1;
            }
            if (productName2 == null) {
                return 1;
            }
            int iCompareTo = productName.compareTo(productName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String advertiserName = getAdvertiserName();
        String advertiserName2 = adMetadata.getAdvertiserName();
        if (advertiserName != advertiserName2) {
            if (advertiserName == null) {
                return -1;
            }
            if (advertiserName2 == null) {
                return 1;
            }
            int iCompareTo2 = advertiserName.compareTo(advertiserName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<AdAudioDetails> audioDetailOptions = getAudioDetailOptions();
        List<AdAudioDetails> audioDetailOptions2 = adMetadata.getAudioDetailOptions();
        if (audioDetailOptions != audioDetailOptions2) {
            if (audioDetailOptions == null) {
                return -1;
            }
            if (audioDetailOptions2 == null) {
                return 1;
            }
            if (audioDetailOptions instanceof Comparable) {
                int iCompareTo3 = ((Comparable) audioDetailOptions).compareTo(audioDetailOptions2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!audioDetailOptions.equals(audioDetailOptions2)) {
                int iHashCode = audioDetailOptions.hashCode();
                int iHashCode2 = audioDetailOptions2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<AdVisualDetails> visualDetailOptions = getVisualDetailOptions();
        List<AdVisualDetails> visualDetailOptions2 = adMetadata.getVisualDetailOptions();
        if (visualDetailOptions != visualDetailOptions2) {
            if (visualDetailOptions == null) {
                return -1;
            }
            if (visualDetailOptions2 == null) {
                return 1;
            }
            if (visualDetailOptions instanceof Comparable) {
                int iCompareTo4 = ((Comparable) visualDetailOptions).compareTo(visualDetailOptions2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!visualDetailOptions.equals(visualDetailOptions2)) {
                int iHashCode3 = visualDetailOptions.hashCode();
                int iHashCode4 = visualDetailOptions2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(queuedEntityMetadataBase);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase
    public boolean equals(Object obj) {
        if (!(obj instanceof AdMetadata)) {
            return false;
        }
        AdMetadata adMetadata = (AdMetadata) obj;
        return super.equals(obj) && internalEqualityCheck(getProductName(), adMetadata.getProductName()) && internalEqualityCheck(getAdvertiserName(), adMetadata.getAdvertiserName()) && internalEqualityCheck(getAudioDetailOptions(), adMetadata.getAudioDetailOptions()) && internalEqualityCheck(getVisualDetailOptions(), adMetadata.getVisualDetailOptions());
    }

    public String getAdvertiserName() {
        return this.advertiserName;
    }

    public List<AdAudioDetails> getAudioDetailOptions() {
        return this.audioDetailOptions;
    }

    public String getProductName() {
        return this.productName;
    }

    public List<AdVisualDetails> getVisualDetailOptions() {
        return this.visualDetailOptions;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getProductName(), getAdvertiserName(), getAudioDetailOptions(), getVisualDetailOptions());
    }

    public void setAdvertiserName(String str) {
        this.advertiserName = str;
    }

    public void setAudioDetailOptions(List<AdAudioDetails> list) {
        this.audioDetailOptions = list;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setVisualDetailOptions(List<AdVisualDetails> list) {
        this.visualDetailOptions = list;
    }
}
