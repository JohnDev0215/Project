package io.objectbox.tree;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class Branch {
    private final long id;
    private final Tree tree;

    private native long nativeGetBranchId(long j, long j2, String[] strArr);

    private native LeafNode nativeGetLeaf(long j, long j2, String[] strArr);

    Branch(Tree tree, long j) {
        this.tree = tree;
        this.id = j;
    }

    public Tree getTree() {
        return this.tree;
    }

    public long getId() {
        return this.id;
    }

    @Nullable
    public Branch branch(String[] strArr) {
        checkPath(strArr);
        long jNativeGetBranchId = nativeGetBranchId(this.tree.getHandle(), this.id, strArr);
        if (jNativeGetBranchId == 0) {
            return null;
        }
        return new Branch(this.tree, jNativeGetBranchId);
    }

    @Nullable
    public Branch branch(String str) {
        checkNameOrPath(str);
        return branch(str.split(this.tree.getPathSeparatorRegex()));
    }

    @Nullable
    public Branch branchChild(String str) {
        return branch(new String[]{str});
    }

    @Nullable
    public Leaf leaf(String[] strArr) {
        checkPath(strArr);
        LeafNode leafNodeNativeGetLeaf = nativeGetLeaf(this.tree.getHandle(), this.id, strArr);
        if (leafNodeNativeGetLeaf == null) {
            return null;
        }
        return new Leaf(leafNodeNativeGetLeaf);
    }

    @Nullable
    public Leaf leaf(String str) {
        checkNameOrPath(str);
        return leaf(str.split(this.tree.getPathSeparatorRegex()));
    }

    @Nullable
    public Leaf leafChild(String str) {
        checkNameOrPath(str);
        return leaf(new String[]{str});
    }

    private void checkNameOrPath(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("name/path must not be null or empty");
        }
    }

    private void checkPath(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("path must not be null or empty");
        }
    }
}
