package com.amazon.musicensembleservice.stations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GetStationSectionsResponse implements Comparable<GetStationSectionsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.stations.GetStationSectionsResponse");
    private Map<String, Category> categories;
    private List<Section> sections;
    private Map<String, Station> stations;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetStationSectionsResponse getStationSectionsResponse) {
        if (getStationSectionsResponse == null) {
            return -1;
        }
        if (getStationSectionsResponse == this) {
            return 0;
        }
        Map<String, Station> stations = getStations();
        Map<String, Station> stations2 = getStationSectionsResponse.getStations();
        if (stations != stations2) {
            if (stations == null) {
                return -1;
            }
            if (stations2 == null) {
                return 1;
            }
            if (stations instanceof Comparable) {
                int iCompareTo = ((Comparable) stations).compareTo(stations2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!stations.equals(stations2)) {
                int iHashCode = stations.hashCode();
                int iHashCode2 = stations2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Section> sections = getSections();
        List<Section> sections2 = getStationSectionsResponse.getSections();
        if (sections != sections2) {
            if (sections == null) {
                return -1;
            }
            if (sections2 == null) {
                return 1;
            }
            if (sections instanceof Comparable) {
                int iCompareTo2 = ((Comparable) sections).compareTo(sections2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!sections.equals(sections2)) {
                int iHashCode3 = sections.hashCode();
                int iHashCode4 = sections2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Map<String, Category> categories = getCategories();
        Map<String, Category> categories2 = getStationSectionsResponse.getCategories();
        if (categories != categories2) {
            if (categories == null) {
                return -1;
            }
            if (categories2 == null) {
                return 1;
            }
            if (categories instanceof Comparable) {
                int iCompareTo3 = ((Comparable) categories).compareTo(categories2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!categories.equals(categories2)) {
                int iHashCode5 = categories.hashCode();
                int iHashCode6 = categories2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetStationSectionsResponse)) {
            return false;
        }
        GetStationSectionsResponse getStationSectionsResponse = (GetStationSectionsResponse) obj;
        return internalEqualityCheck(getStations(), getStationSectionsResponse.getStations()) && internalEqualityCheck(getSections(), getStationSectionsResponse.getSections()) && internalEqualityCheck(getCategories(), getStationSectionsResponse.getCategories());
    }

    public Map<String, Category> getCategories() {
        return this.categories;
    }

    public List<Section> getSections() {
        return this.sections;
    }

    public Map<String, Station> getStations() {
        return this.stations;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getStations(), getSections(), getCategories());
    }

    public void setCategories(Map<String, Category> map) {
        this.categories = map;
    }

    public void setSections(List<Section> list) {
        this.sections = list;
    }

    public void setStations(Map<String, Station> map) {
        this.stations = map;
    }
}
