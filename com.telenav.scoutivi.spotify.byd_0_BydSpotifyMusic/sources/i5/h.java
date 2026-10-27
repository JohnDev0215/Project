package i5;

import h5.k0;
import h5.o;
import h5.p;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: ListBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h implements Externalizable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a f7038h = new a(null);
    private static final long serialVersionUID = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Collection<?> f7039e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f7040g;

    /* JADX INFO: compiled from: ListBuilder.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public h(Collection<?> collection, int i7) {
        l.e(collection, "collection");
        this.f7039e = collection;
        this.f7040g = i7;
    }

    private final Object readResolve() {
        return this.f7039e;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput input) throws InvalidObjectException {
        Collection<?> collectionA;
        l.e(input, "input");
        byte b7 = input.readByte();
        int i7 = b7 & 1;
        if ((b7 & (-2)) != 0) {
            throw new InvalidObjectException("Unsupported flags value: " + ((int) b7) + '.');
        }
        int i8 = input.readInt();
        if (i8 < 0) {
            throw new InvalidObjectException("Illegal size value: " + i8 + '.');
        }
        int i9 = 0;
        if (i7 == 0) {
            List listC = o.c(i8);
            while (i9 < i8) {
                listC.add(input.readObject());
                i9++;
            }
            collectionA = o.a(listC);
        } else {
            if (i7 != 1) {
                throw new InvalidObjectException("Unsupported collection type tag: " + i7 + '.');
            }
            Set setB = k0.b(i8);
            while (i9 < i8) {
                setB.add(input.readObject());
                i9++;
            }
            collectionA = k0.a(setB);
        }
        this.f7039e = collectionA;
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput output) throws IOException {
        l.e(output, "output");
        output.writeByte(this.f7040g);
        output.writeInt(this.f7039e.size());
        Iterator<?> it = this.f7039e.iterator();
        while (it.hasNext()) {
            output.writeObject(it.next());
        }
    }

    public h() {
        this(p.h(), 0);
    }
}
