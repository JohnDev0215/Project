package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class PodcastCategory implements Comparable<PodcastCategory> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.PodcastCategory");
    private String id;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PodcastCategory podcastCategory) {
        if (podcastCategory == null) {
            return -1;
        }
        if (podcastCategory == this) {
            return 0;
        }
        String title = getTitle();
        String title2 = podcastCategory.getTitle();
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
        String id = getId();
        String id2 = podcastCategory.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PodcastCategory)) {
            return false;
        }
        PodcastCategory podcastCategory = (PodcastCategory) obj;
        return internalEqualityCheck(getTitle(), podcastCategory.getTitle()) && internalEqualityCheck(getId(), podcastCategory.getId());
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTitle(), getId());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
