package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class Justification implements Comparable<Justification> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.Justification");
    private String artist;
    private String asin;
    private String cause;
    private String reason;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Justification justification) {
        if (justification == null) {
            return -1;
        }
        if (justification == this) {
            return 0;
        }
        String reason = getReason();
        String reason2 = justification.getReason();
        if (reason != reason2) {
            if (reason == null) {
                return -1;
            }
            if (reason2 == null) {
                return 1;
            }
            int iCompareTo = reason.compareTo(reason2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String artist = getArtist();
        String artist2 = justification.getArtist();
        if (artist != artist2) {
            if (artist == null) {
                return -1;
            }
            if (artist2 == null) {
                return 1;
            }
            int iCompareTo2 = artist.compareTo(artist2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String cause = getCause();
        String cause2 = justification.getCause();
        if (cause != cause2) {
            if (cause == null) {
                return -1;
            }
            if (cause2 == null) {
                return 1;
            }
            int iCompareTo3 = cause.compareTo(cause2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String title = getTitle();
        String title2 = justification.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo4 = title.compareTo(title2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String asin = getAsin();
        String asin2 = justification.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo5 = asin.compareTo(asin2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Justification)) {
            return false;
        }
        Justification justification = (Justification) obj;
        return internalEqualityCheck(getReason(), justification.getReason()) && internalEqualityCheck(getArtist(), justification.getArtist()) && internalEqualityCheck(getCause(), justification.getCause()) && internalEqualityCheck(getTitle(), justification.getTitle()) && internalEqualityCheck(getAsin(), justification.getAsin());
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getCause() {
        return this.cause;
    }

    public String getReason() {
        return this.reason;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getReason(), getArtist(), getCause(), getTitle(), getAsin());
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setCause(String str) {
        this.cause = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
