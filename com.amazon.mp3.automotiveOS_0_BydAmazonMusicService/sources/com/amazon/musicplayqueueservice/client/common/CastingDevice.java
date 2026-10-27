package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CastingDevice implements Comparable<CastingDevice> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.CastingDevice");
    private String category;
    private String imageUrl;
    private boolean isPlaying;
    private String name;
    private String targetId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CastingDevice castingDevice) {
        if (castingDevice == null) {
            return -1;
        }
        if (castingDevice == this) {
            return 0;
        }
        String imageUrl = getImageUrl();
        String imageUrl2 = castingDevice.getImageUrl();
        if (imageUrl != imageUrl2) {
            if (imageUrl == null) {
                return -1;
            }
            if (imageUrl2 == null) {
                return 1;
            }
            int iCompareTo = imageUrl.compareTo(imageUrl2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String category = getCategory();
        String category2 = castingDevice.getCategory();
        if (category != category2) {
            if (category == null) {
                return -1;
            }
            if (category2 == null) {
                return 1;
            }
            int iCompareTo2 = category.compareTo(category2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (!isIsPlaying() && castingDevice.isIsPlaying()) {
            return -1;
        }
        if (isIsPlaying() && !castingDevice.isIsPlaying()) {
            return 1;
        }
        String targetId = getTargetId();
        String targetId2 = castingDevice.getTargetId();
        if (targetId != targetId2) {
            if (targetId == null) {
                return -1;
            }
            if (targetId2 == null) {
                return 1;
            }
            int iCompareTo3 = targetId.compareTo(targetId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String name = getName();
        String name2 = castingDevice.getName();
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CastingDevice)) {
            return false;
        }
        CastingDevice castingDevice = (CastingDevice) obj;
        return internalEqualityCheck(getImageUrl(), castingDevice.getImageUrl()) && internalEqualityCheck(getCategory(), castingDevice.getCategory()) && internalEqualityCheck(Boolean.valueOf(isIsPlaying()), Boolean.valueOf(castingDevice.isIsPlaying())) && internalEqualityCheck(getTargetId(), castingDevice.getTargetId()) && internalEqualityCheck(getName(), castingDevice.getName());
    }

    public String getCategory() {
        return this.category;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getName() {
        return this.name;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getImageUrl(), getCategory(), Boolean.valueOf(isIsPlaying()), getTargetId(), getName());
    }

    public boolean isIsPlaying() {
        return this.isPlaying;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public void setIsPlaying(boolean z) {
        this.isPlaying = z;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }
}
