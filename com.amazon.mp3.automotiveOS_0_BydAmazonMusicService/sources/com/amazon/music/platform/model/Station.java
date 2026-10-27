package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Station extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Station");
    private String stationKey;
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
        if (!(document instanceof Station)) {
            return 1;
        }
        Station station = (Station) document;
        String title = getTitle();
        String title2 = station.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String stationKey = getStationKey();
        String stationKey2 = station.getStationKey();
        if (stationKey != stationKey2) {
            if (stationKey == null) {
                return -1;
            }
            if (stationKey2 == null) {
                return 1;
            }
            int iCompareTo2 = stationKey.compareTo(stationKey2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof Station)) {
            return false;
        }
        Station station = (Station) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), station.getTitle()) && internalEqualityCheck(getStationKey(), station.getStationKey());
    }

    public String getStationKey() {
        return this.stationKey;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getStationKey());
    }

    public void setStationKey(String str) {
        this.stationKey = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
