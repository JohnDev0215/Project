package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Artist extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Artist");
    private Art artExtraLarge;
    private Art artFull;
    private Art artLarge;
    private Art artMedium;
    private Art artOriginal;
    private Art artSmall;
    private Art artTiny;
    private String asin;
    private String name;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof Artist)) {
            return 1;
        }
        Artist artist = (Artist) document;
        String asin = getAsin();
        String asin2 = artist.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo = asin.compareTo(asin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Art artLarge = getArtLarge();
        Art artLarge2 = artist.getArtLarge();
        if (artLarge != artLarge2) {
            if (artLarge == null) {
                return -1;
            }
            if (artLarge2 == null) {
                return 1;
            }
            int iCompareTo2 = artLarge.compareTo(artLarge2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Art artSmall = getArtSmall();
        Art artSmall2 = artist.getArtSmall();
        if (artSmall != artSmall2) {
            if (artSmall == null) {
                return -1;
            }
            if (artSmall2 == null) {
                return 1;
            }
            int iCompareTo3 = artSmall.compareTo(artSmall2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String name = getName();
        String name2 = artist.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo4 = name.compareTo(name2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Art artTiny = getArtTiny();
        Art artTiny2 = artist.getArtTiny();
        if (artTiny != artTiny2) {
            if (artTiny == null) {
                return -1;
            }
            if (artTiny2 == null) {
                return 1;
            }
            int iCompareTo5 = artTiny.compareTo(artTiny2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Art artMedium = getArtMedium();
        Art artMedium2 = artist.getArtMedium();
        if (artMedium != artMedium2) {
            if (artMedium == null) {
                return -1;
            }
            if (artMedium2 == null) {
                return 1;
            }
            int iCompareTo6 = artMedium.compareTo(artMedium2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Art artFull = getArtFull();
        Art artFull2 = artist.getArtFull();
        if (artFull != artFull2) {
            if (artFull == null) {
                return -1;
            }
            if (artFull2 == null) {
                return 1;
            }
            int iCompareTo7 = artFull.compareTo(artFull2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Art artExtraLarge = getArtExtraLarge();
        Art artExtraLarge2 = artist.getArtExtraLarge();
        if (artExtraLarge != artExtraLarge2) {
            if (artExtraLarge == null) {
                return -1;
            }
            if (artExtraLarge2 == null) {
                return 1;
            }
            int iCompareTo8 = artExtraLarge.compareTo(artExtraLarge2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = artist.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo9 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof Artist)) {
            return false;
        }
        Artist artist = (Artist) obj;
        return super.equals(obj) && internalEqualityCheck(getAsin(), artist.getAsin()) && internalEqualityCheck(getArtLarge(), artist.getArtLarge()) && internalEqualityCheck(getArtSmall(), artist.getArtSmall()) && internalEqualityCheck(getName(), artist.getName()) && internalEqualityCheck(getArtTiny(), artist.getArtTiny()) && internalEqualityCheck(getArtMedium(), artist.getArtMedium()) && internalEqualityCheck(getArtFull(), artist.getArtFull()) && internalEqualityCheck(getArtExtraLarge(), artist.getArtExtraLarge()) && internalEqualityCheck(getArtOriginal(), artist.getArtOriginal());
    }

    public Art getArtExtraLarge() {
        return this.artExtraLarge;
    }

    public Art getArtFull() {
        return this.artFull;
    }

    public Art getArtLarge() {
        return this.artLarge;
    }

    public Art getArtMedium() {
        return this.artMedium;
    }

    public Art getArtOriginal() {
        return this.artOriginal;
    }

    public Art getArtSmall() {
        return this.artSmall;
    }

    public Art getArtTiny() {
        return this.artTiny;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getName() {
        return this.name;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAsin(), getArtLarge(), getArtSmall(), getName(), getArtTiny(), getArtMedium(), getArtFull(), getArtExtraLarge(), getArtOriginal());
    }

    public void setArtExtraLarge(Art art) {
        this.artExtraLarge = art;
    }

    public void setArtFull(Art art) {
        this.artFull = art;
    }

    public void setArtLarge(Art art) {
        this.artLarge = art;
    }

    public void setArtMedium(Art art) {
        this.artMedium = art;
    }

    public void setArtOriginal(Art art) {
        this.artOriginal = art;
    }

    public void setArtSmall(Art art) {
        this.artSmall = art;
    }

    public void setArtTiny(Art art) {
        this.artTiny = art;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
