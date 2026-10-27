package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class SeeMoreContainer extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.SeeMoreContainer");
    private String next;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Block block) {
        if (block == null) {
            return -1;
        }
        if (block == this) {
            return 0;
        }
        if (!(block instanceof SeeMoreContainer)) {
            return 1;
        }
        SeeMoreContainer seeMoreContainer = (SeeMoreContainer) block;
        String next = getNext();
        String next2 = seeMoreContainer.getNext();
        if (next != next2) {
            if (next == null) {
                return -1;
            }
            if (next2 == null) {
                return 1;
            }
            int iCompareTo = next.compareTo(next2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = seeMoreContainer.getTitle();
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
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof SeeMoreContainer)) {
            return false;
        }
        SeeMoreContainer seeMoreContainer = (SeeMoreContainer) obj;
        return super.equals(obj) && internalEqualityCheck(getNext(), seeMoreContainer.getNext()) && internalEqualityCheck(getTitle(), seeMoreContainer.getTitle());
    }

    public String getNext() {
        return this.next;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getNext(), getTitle());
    }

    public void setNext(String str) {
        this.next = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
