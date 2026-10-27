package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackContainer extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.PlaybackContainer");
    private Entity container;
    private List<Entity> entities;
    private List<Image> images;

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
        if (!(entity instanceof PlaybackContainer)) {
            return 1;
        }
        PlaybackContainer playbackContainer = (PlaybackContainer) entity;
        List<Entity> entities = getEntities();
        List<Entity> entities2 = playbackContainer.getEntities();
        if (entities != entities2) {
            if (entities == null) {
                return -1;
            }
            if (entities2 == null) {
                return 1;
            }
            if (entities instanceof Comparable) {
                int iCompareTo = ((Comparable) entities).compareTo(entities2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!entities.equals(entities2)) {
                int iHashCode = entities.hashCode();
                int iHashCode2 = entities2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Image> images = getImages();
        List<Image> images2 = playbackContainer.getImages();
        if (images != images2) {
            if (images == null) {
                return -1;
            }
            if (images2 == null) {
                return 1;
            }
            if (images instanceof Comparable) {
                int iCompareTo2 = ((Comparable) images).compareTo(images2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!images.equals(images2)) {
                int iHashCode3 = images.hashCode();
                int iHashCode4 = images2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Entity container = getContainer();
        Entity container2 = playbackContainer.getContainer();
        if (container != container2) {
            if (container == null) {
                return -1;
            }
            if (container2 == null) {
                return 1;
            }
            int iCompareTo3 = container.compareTo(container2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof PlaybackContainer)) {
            return false;
        }
        PlaybackContainer playbackContainer = (PlaybackContainer) obj;
        return super.equals(obj) && internalEqualityCheck(getEntities(), playbackContainer.getEntities()) && internalEqualityCheck(getImages(), playbackContainer.getImages()) && internalEqualityCheck(getContainer(), playbackContainer.getContainer());
    }

    public Entity getContainer() {
        return this.container;
    }

    public List<Entity> getEntities() {
        return this.entities;
    }

    public List<Image> getImages() {
        return this.images;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getEntities(), getImages(), getContainer());
    }

    public void setContainer(Entity entity) {
        this.container = entity;
    }

    public void setEntities(List<Entity> list) {
        this.entities = list;
    }

    public void setImages(List<Image> list) {
        this.images = list;
    }
}
