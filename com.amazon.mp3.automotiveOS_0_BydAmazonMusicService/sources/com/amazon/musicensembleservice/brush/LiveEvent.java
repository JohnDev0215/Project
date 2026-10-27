package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.MusicEntity;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class LiveEvent extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.LiveEvent");
    private String id;
    private Image image;
    private String title;

    public static class Builder extends MusicEntity.Builder {
    }

    public static Builder builder() {
        return new Builder();
    }

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
        if (!(entity instanceof LiveEvent)) {
            return 1;
        }
        LiveEvent liveEvent = (LiveEvent) entity;
        String title = getTitle();
        String title2 = liveEvent.getTitle();
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
        Image image = getImage();
        Image image2 = liveEvent.getImage();
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
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof LiveEvent)) {
            return false;
        }
        LiveEvent liveEvent = (LiveEvent) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), liveEvent.getTitle()) && internalEqualityCheck(getImage(), liveEvent.getImage()) && internalEqualityCheck(getId(), liveEvent.getId());
    }

    public String getId() {
        return this.id;
    }

    public Image getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getImage(), getId());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
