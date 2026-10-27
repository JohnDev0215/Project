package io.objectbox.tree;

/* JADX INFO: loaded from: classes5.dex */
public class LeafNode {
    public final long branchId;
    public double floatingValue;
    public final long id;
    public long integerValue;
    public final long metaId;
    public Object objectValue;
    public short valueType;

    public LeafNode(long j, long j2, long j3, long j4, double d, Object obj, short s) {
        this.id = j;
        this.branchId = j2;
        this.metaId = j3;
        this.integerValue = j4;
        this.floatingValue = d;
        this.objectValue = obj;
        this.valueType = s;
    }
}
