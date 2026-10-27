package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class Album implements Comparable<Album> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.Album");
    private String asin;
    private String image;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Album album) {
        if (album == null) {
            return -1;
        }
        if (album == this) {
            return 0;
        }
        String image = getImage();
        String image2 = album.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo = image.compareTo(image2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String asin = getAsin();
        String asin2 = album.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo2 = asin.compareTo(asin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String title = getTitle();
        String title2 = album.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo3 = title.compareTo(title2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album album = (Album) obj;
        return internalEqualityCheck(getImage(), album.getImage()) && internalEqualityCheck(getAsin(), album.getAsin()) && internalEqualityCheck(getTitle(), album.getTitle());
    }

    public String getAsin() {
        return this.asin;
    }

    public String getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getImage(), getAsin(), getTitle());
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
