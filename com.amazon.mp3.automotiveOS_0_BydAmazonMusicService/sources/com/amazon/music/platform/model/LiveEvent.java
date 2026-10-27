package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public class LiveEvent extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LiveEvent");
    private Art artOriginal;
    private String id;
    private Date startDate;
    private String title;

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
        if (!(document instanceof LiveEvent)) {
            return 1;
        }
        LiveEvent liveEvent = (LiveEvent) document;
        Date startDate = getStartDate();
        Date startDate2 = liveEvent.getStartDate();
        if (startDate != startDate2) {
            if (startDate == null) {
                return -1;
            }
            if (startDate2 == null) {
                return 1;
            }
            int iCompareTo = startDate.compareTo(startDate2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = liveEvent.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo2 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String id = getId();
        String id2 = liveEvent.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo3 = id.compareTo(id2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String title = getTitle();
        String title2 = liveEvent.getTitle();
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
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof LiveEvent)) {
            return false;
        }
        LiveEvent liveEvent = (LiveEvent) obj;
        return super.equals(obj) && internalEqualityCheck(getStartDate(), liveEvent.getStartDate()) && internalEqualityCheck(getArtOriginal(), liveEvent.getArtOriginal()) && internalEqualityCheck(getId(), liveEvent.getId()) && internalEqualityCheck(getTitle(), liveEvent.getTitle());
    }

    public Art getArtOriginal() {
        return this.artOriginal;
    }

    public String getId() {
        return this.id;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getStartDate(), getArtOriginal(), getId(), getTitle());
    }

    public void setArtOriginal(Art art) {
        this.artOriginal = art;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
