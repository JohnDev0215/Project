package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class CatalogPlaylistTrackEntry extends AbstractPlaylistTrackEntry {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.CatalogPlaylistTrackEntry");
    private String curatedBy;
    private Date lastModifiedDate;
    private CatalogTrackMetadata metadata;
    private CatalogTrackId trackId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.AbstractPlaylistTrackEntry, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractPlaylistTrackEntry abstractPlaylistTrackEntry) {
        if (abstractPlaylistTrackEntry == null) {
            return -1;
        }
        if (abstractPlaylistTrackEntry == this) {
            return 0;
        }
        if (!(abstractPlaylistTrackEntry instanceof CatalogPlaylistTrackEntry)) {
            return 1;
        }
        CatalogPlaylistTrackEntry catalogPlaylistTrackEntry = (CatalogPlaylistTrackEntry) abstractPlaylistTrackEntry;
        Date lastModifiedDate = getLastModifiedDate();
        Date lastModifiedDate2 = catalogPlaylistTrackEntry.getLastModifiedDate();
        if (lastModifiedDate != lastModifiedDate2) {
            if (lastModifiedDate == null) {
                return -1;
            }
            if (lastModifiedDate2 == null) {
                return 1;
            }
            int iCompareTo = lastModifiedDate.compareTo(lastModifiedDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        CatalogTrackId trackId = getTrackId();
        CatalogTrackId trackId2 = catalogPlaylistTrackEntry.getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo2 = trackId.compareTo(trackId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        CatalogTrackMetadata metadata = getMetadata();
        CatalogTrackMetadata metadata2 = catalogPlaylistTrackEntry.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo3 = metadata.compareTo(metadata2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = catalogPlaylistTrackEntry.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo4 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(abstractPlaylistTrackEntry);
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistTrackEntry
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogPlaylistTrackEntry)) {
            return false;
        }
        CatalogPlaylistTrackEntry catalogPlaylistTrackEntry = (CatalogPlaylistTrackEntry) obj;
        return super.equals(obj) && internalEqualityCheck(getLastModifiedDate(), catalogPlaylistTrackEntry.getLastModifiedDate()) && internalEqualityCheck(getTrackId(), catalogPlaylistTrackEntry.getTrackId()) && internalEqualityCheck(getMetadata(), catalogPlaylistTrackEntry.getMetadata()) && internalEqualityCheck(getCuratedBy(), catalogPlaylistTrackEntry.getCuratedBy());
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public CatalogTrackMetadata getMetadata() {
        return this.metadata;
    }

    public CatalogTrackId getTrackId() {
        return this.trackId;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistTrackEntry
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLastModifiedDate(), getTrackId(), getMetadata(), getCuratedBy());
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setMetadata(CatalogTrackMetadata catalogTrackMetadata) {
        this.metadata = catalogTrackMetadata;
    }

    public void setTrackId(CatalogTrackId catalogTrackId) {
        this.trackId = catalogTrackId;
    }
}
