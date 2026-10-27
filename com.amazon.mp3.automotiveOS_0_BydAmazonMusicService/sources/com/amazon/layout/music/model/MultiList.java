package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class MultiList extends BlockContainer {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.MultiList");
    private SingleList list1;
    private SingleList list2;

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
        if (!(block instanceof MultiList)) {
            return 1;
        }
        MultiList multiList = (MultiList) block;
        SingleList list2 = getList2();
        SingleList list22 = multiList.getList2();
        if (list2 != list22) {
            if (list2 == null) {
                return -1;
            }
            if (list22 == null) {
                return 1;
            }
            int iCompareTo = list2.compareTo(list22);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        SingleList list1 = getList1();
        SingleList list12 = multiList.getList1();
        if (list1 != list12) {
            if (list1 == null) {
                return -1;
            }
            if (list12 == null) {
                return 1;
            }
            int iCompareTo2 = list1.compareTo(list12);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(block);
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public boolean equals(Object obj) {
        if (!(obj instanceof MultiList)) {
            return false;
        }
        MultiList multiList = (MultiList) obj;
        return super.equals(obj) && internalEqualityCheck(getList2(), multiList.getList2()) && internalEqualityCheck(getList1(), multiList.getList1());
    }

    public SingleList getList1() {
        return this.list1;
    }

    public SingleList getList2() {
        return this.list2;
    }

    @Override // com.amazon.layout.music.model.BlockContainer, com.amazon.layout.music.model.Block
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getList2(), getList1());
    }

    public void setList1(SingleList singleList) {
        this.list1 = singleList;
    }

    public void setList2(SingleList singleList) {
        this.list2 = singleList;
    }
}
