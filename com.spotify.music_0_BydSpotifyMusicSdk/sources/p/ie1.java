package p;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class ie1 extends ke1 implements Iterator, g81 {
    public final /* synthetic */ int l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ie1(MapBuilder mapBuilder, int i) {
        super(mapBuilder);
        this.l = i;
        if (i == 1) {
            v03.j(mapBuilder, "map");
            super(mapBuilder);
        } else if (i != 2) {
            v03.j(mapBuilder, "map");
        } else {
            v03.j(mapBuilder, "map");
            super(mapBuilder);
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.l;
        MapBuilder mapBuilder = this.c;
        switch (i) {
            case 0:
                if (this.e >= mapBuilder.length) {
                    throw new NoSuchElementException();
                }
                int i2 = this.e;
                this.e = i2 + 1;
                this.k = i2;
                je1 je1Var = new je1(mapBuilder, i2);
                a();
                return je1Var;
            case 1:
                if (this.e >= mapBuilder.length) {
                    throw new NoSuchElementException();
                }
                int i3 = this.e;
                this.e = i3 + 1;
                this.k = i3;
                Object obj = mapBuilder.keysArray[this.k];
                a();
                return obj;
            default:
                if (this.e >= mapBuilder.length) {
                    throw new NoSuchElementException();
                }
                int i4 = this.e;
                this.e = i4 + 1;
                this.k = i4;
                Object[] objArr = mapBuilder.valuesArray;
                v03.h(objArr);
                Object obj2 = objArr[this.k];
                a();
                return obj2;
        }
    }
}
