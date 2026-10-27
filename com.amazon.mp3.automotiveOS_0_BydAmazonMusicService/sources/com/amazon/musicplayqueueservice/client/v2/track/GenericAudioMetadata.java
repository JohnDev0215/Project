package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.musicplayqueueservice.client.common.ParentalControls;
import com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GenericAudioMetadata extends QueuedEntityMetadataBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.GenericAudioMetadata");
    private AbstractPlayableEntityIdentifier identifier;
    private ImageMetadataBase imageMetadata;
    private ParentalControls parentalControls;
    private String rating;
    private String serviceTier;
    private AudioSourceBase source;
    private String subTitle;
    private String title;

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
        if (!(queuedEntityMetadataBase instanceof GenericAudioMetadata)) {
            return 1;
        }
        GenericAudioMetadata genericAudioMetadata = (GenericAudioMetadata) queuedEntityMetadataBase;
        String subTitle = getSubTitle();
        String subTitle2 = genericAudioMetadata.getSubTitle();
        if (subTitle != subTitle2) {
            if (subTitle == null) {
                return -1;
            }
            if (subTitle2 == null) {
                return 1;
            }
            int iCompareTo = subTitle.compareTo(subTitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = genericAudioMetadata.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo2 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        AbstractPlayableEntityIdentifier identifier = getIdentifier();
        AbstractPlayableEntityIdentifier identifier2 = genericAudioMetadata.getIdentifier();
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
        String serviceTier = getServiceTier();
        String serviceTier2 = genericAudioMetadata.getServiceTier();
        if (serviceTier != serviceTier2) {
            if (serviceTier == null) {
                return -1;
            }
            if (serviceTier2 == null) {
                return 1;
            }
            int iCompareTo4 = serviceTier.compareTo(serviceTier2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        ImageMetadataBase imageMetadata = getImageMetadata();
        ImageMetadataBase imageMetadata2 = genericAudioMetadata.getImageMetadata();
        if (imageMetadata != imageMetadata2) {
            if (imageMetadata == null) {
                return -1;
            }
            if (imageMetadata2 == null) {
                return 1;
            }
            int iCompareTo5 = imageMetadata.compareTo(imageMetadata2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String rating = getRating();
        String rating2 = genericAudioMetadata.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo6 = rating.compareTo(rating2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String title = getTitle();
        String title2 = genericAudioMetadata.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo7 = title.compareTo(title2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        AudioSourceBase source = getSource();
        AudioSourceBase source2 = genericAudioMetadata.getSource();
        if (source != source2) {
            if (source == null) {
                return -1;
            }
            if (source2 == null) {
                return 1;
            }
            int iCompareTo8 = source.compareTo(source2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(queuedEntityMetadataBase);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase
    public boolean equals(Object obj) {
        if (!(obj instanceof GenericAudioMetadata)) {
            return false;
        }
        GenericAudioMetadata genericAudioMetadata = (GenericAudioMetadata) obj;
        return super.equals(obj) && internalEqualityCheck(getSubTitle(), genericAudioMetadata.getSubTitle()) && internalEqualityCheck(getParentalControls(), genericAudioMetadata.getParentalControls()) && internalEqualityCheck(getIdentifier(), genericAudioMetadata.getIdentifier()) && internalEqualityCheck(getServiceTier(), genericAudioMetadata.getServiceTier()) && internalEqualityCheck(getImageMetadata(), genericAudioMetadata.getImageMetadata()) && internalEqualityCheck(getRating(), genericAudioMetadata.getRating()) && internalEqualityCheck(getTitle(), genericAudioMetadata.getTitle()) && internalEqualityCheck(getSource(), genericAudioMetadata.getSource());
    }

    public AbstractPlayableEntityIdentifier getIdentifier() {
        return this.identifier;
    }

    public ImageMetadataBase getImageMetadata() {
        return this.imageMetadata;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getRating() {
        return this.rating;
    }

    public String getServiceTier() {
        return this.serviceTier;
    }

    public AudioSourceBase getSource() {
        return this.source;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSubTitle(), getParentalControls(), getIdentifier(), getServiceTier(), getImageMetadata(), getRating(), getTitle(), getSource());
    }

    public void setIdentifier(AbstractPlayableEntityIdentifier abstractPlayableEntityIdentifier) {
        this.identifier = abstractPlayableEntityIdentifier;
    }

    public void setImageMetadata(ImageMetadataBase imageMetadataBase) {
        this.imageMetadata = imageMetadataBase;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public void setServiceTier(String str) {
        this.serviceTier = str;
    }

    public void setSource(AudioSourceBase audioSourceBase) {
        this.source = audioSourceBase;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
