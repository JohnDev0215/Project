package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.Entity;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class NavigationEntity extends Entity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.NavigationEntity");
    private String contentType;
    private String id;
    private String target;
    private String title;

    public static class Builder extends Entity.Builder {
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
    @Override // com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof NavigationEntity)) {
            return 1;
        }
        NavigationEntity navigationEntity = (NavigationEntity) entity;
        String contentType = getContentType();
        String contentType2 = navigationEntity.getContentType();
        if (contentType != contentType2) {
            if (contentType == null) {
                return -1;
            }
            if (contentType2 == null) {
                return 1;
            }
            int iCompareTo = contentType.compareTo(contentType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = navigationEntity.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String id = getId();
        String id2 = navigationEntity.getId();
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
        String target = getTarget();
        String target2 = navigationEntity.getTarget();
        if (target != target2) {
            if (target == null) {
                return -1;
            }
            if (target2 == null) {
                return 1;
            }
            int iCompareTo4 = target.compareTo(target2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof NavigationEntity)) {
            return false;
        }
        NavigationEntity navigationEntity = (NavigationEntity) obj;
        return super.equals(obj) && internalEqualityCheck(getContentType(), navigationEntity.getContentType()) && internalEqualityCheck(getTitle(), navigationEntity.getTitle()) && internalEqualityCheck(getId(), navigationEntity.getId()) && internalEqualityCheck(getTarget(), navigationEntity.getTarget());
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getId() {
        return this.id;
    }

    public String getTarget() {
        return this.target;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContentType(), getTitle(), getId(), getTarget());
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
