package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class LiveSportsImageElement implements Comparable<LiveSportsImageElement> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LiveSportsImageElement");
    private String title;
    private String url;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated LiveSportsImageElement liveSportsImageElement) {
        if (liveSportsImageElement == null) {
            return -1;
        }
        if (liveSportsImageElement == this) {
            return 0;
        }
        String title = getTitle();
        String title2 = liveSportsImageElement.getTitle();
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
        String url = getUrl();
        String url2 = liveSportsImageElement.getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo2 = url.compareTo(url2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LiveSportsImageElement)) {
            return false;
        }
        LiveSportsImageElement liveSportsImageElement = (LiveSportsImageElement) obj;
        return internalEqualityCheck(getTitle(), liveSportsImageElement.getTitle()) && internalEqualityCheck(getUrl(), liveSportsImageElement.getUrl());
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTitle(), getUrl());
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
