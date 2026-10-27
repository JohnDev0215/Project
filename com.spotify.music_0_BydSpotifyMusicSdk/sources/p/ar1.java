package p;

import java.util.RandomAccess;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class ar1 extends p1 implements RandomAccess {
    public final ByteString[] c;
    public final int[] e;

    public ar1(ByteString[] byteStringArr, int[] iArr) {
        this.c = byteStringArr;
        this.e = iArr;
    }

    @Override // kotlin.collections.a
    public final int a() {
        return this.c.length;
    }

    @Override // kotlin.collections.a, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return super.contains((ByteString) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.c[i];
    }

    @Override // p.p1, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return super.indexOf((ByteString) obj);
        }
        return -1;
    }

    @Override // p.p1, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return super.lastIndexOf((ByteString) obj);
        }
        return -1;
    }
}
