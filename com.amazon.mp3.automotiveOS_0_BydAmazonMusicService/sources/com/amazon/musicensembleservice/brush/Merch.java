package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class Merch extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Merch");
    private String artistAsin;
    private String asin;
    private Image image;
    private Boolean primeEligible;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof Merch)) {
            return 1;
        }
        Merch merch = (Merch) entity;
        String asin = getAsin();
        String asin2 = merch.getAsin();
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
        Image image = getImage();
        Image image2 = merch.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo2 = image.compareTo(image2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = merch.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo3 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsPrimeEligible = isPrimeEligible();
        Boolean boolIsPrimeEligible2 = merch.isPrimeEligible();
        if (boolIsPrimeEligible != boolIsPrimeEligible2) {
            if (boolIsPrimeEligible == null) {
                return -1;
            }
            if (boolIsPrimeEligible2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsPrimeEligible.compareTo(boolIsPrimeEligible2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof Merch)) {
            return false;
        }
        Merch merch = (Merch) obj;
        return super.equals(obj) && internalEqualityCheck(getAsin(), merch.getAsin()) && internalEqualityCheck(getImage(), merch.getImage()) && internalEqualityCheck(getArtistAsin(), merch.getArtistAsin()) && internalEqualityCheck(isPrimeEligible(), merch.isPrimeEligible());
    }

    public String getArtistAsin() {
        return this.artistAsin;
    }

    public String getAsin() {
        return this.asin;
    }

    public Image getImage() {
        return this.image;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAsin(), getImage(), getArtistAsin(), isPrimeEligible());
    }

    public Boolean isPrimeEligible() {
        return this.primeEligible;
    }

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPrimeEligible(Boolean bool) {
        this.primeEligible = bool;
    }
}
