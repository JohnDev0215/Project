package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class StreamProvider extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.StreamProvider");
    private List<Image> wordmarkImages;

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
        if (!(entity instanceof StreamProvider)) {
            return 1;
        }
        List<Image> wordmarkImages = getWordmarkImages();
        List<Image> wordmarkImages2 = ((StreamProvider) entity).getWordmarkImages();
        if (wordmarkImages != wordmarkImages2) {
            if (wordmarkImages == null) {
                return -1;
            }
            if (wordmarkImages2 == null) {
                return 1;
            }
            if (wordmarkImages instanceof Comparable) {
                int iCompareTo = ((Comparable) wordmarkImages).compareTo(wordmarkImages2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!wordmarkImages.equals(wordmarkImages2)) {
                int iHashCode = wordmarkImages.hashCode();
                int iHashCode2 = wordmarkImages2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (obj instanceof StreamProvider) {
            return super.equals(obj) && internalEqualityCheck(getWordmarkImages(), ((StreamProvider) obj).getWordmarkImages());
        }
        return false;
    }

    public List<Image> getWordmarkImages() {
        return this.wordmarkImages;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getWordmarkImages());
    }

    public void setWordmarkImages(List<Image> list) {
        this.wordmarkImages = list;
    }
}
