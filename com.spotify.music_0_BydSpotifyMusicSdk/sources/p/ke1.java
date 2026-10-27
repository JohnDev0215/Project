package p;

import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public abstract class ke1 {
    public final MapBuilder c;
    public int e;
    public int k;

    public ke1(MapBuilder mapBuilder) {
        v03.j(mapBuilder, "map");
        this.c = mapBuilder;
        this.k = -1;
        a();
    }

    public final void a() {
        while (true) {
            int i = this.e;
            MapBuilder mapBuilder = this.c;
            if (i >= mapBuilder.length) {
                return;
            }
            int[] iArr = mapBuilder.presenceArray;
            int i2 = this.e;
            if (iArr[i2] >= 0) {
                return;
            } else {
                this.e = i2 + 1;
            }
        }
    }

    public final boolean hasNext() {
        return this.e < this.c.length;
    }

    public final void remove() {
        if (this.k == -1) {
            throw new IllegalStateException("Call next() before removing element from the iterator.".toString());
        }
        MapBuilder mapBuilder = this.c;
        mapBuilder.h();
        mapBuilder.r(this.k);
        this.k = -1;
    }
}
