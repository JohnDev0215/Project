package i5;

import h5.g0;
import h5.h0;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: MapBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
final class i implements Externalizable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a f7041g = new a(null);
    private static final long serialVersionUID = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Map<?, ?> f7042e;

    /* JADX INFO: compiled from: MapBuilder.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    public i(Map<?, ?> map) {
        l.e(map, "map");
        this.f7042e = map;
    }

    private final Object readResolve() {
        return this.f7042e;
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput input) throws InvalidObjectException {
        l.e(input, "input");
        byte b7 = input.readByte();
        if (b7 != 0) {
            throw new InvalidObjectException("Unsupported flags value: " + ((int) b7));
        }
        int i7 = input.readInt();
        if (i7 < 0) {
            throw new InvalidObjectException("Illegal size value: " + i7 + '.');
        }
        Map mapB = g0.b(i7);
        for (int i8 = 0; i8 < i7; i8++) {
            mapB.put(input.readObject(), input.readObject());
        }
        this.f7042e = g0.a(mapB);
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput output) throws IOException {
        l.e(output, "output");
        output.writeByte(0);
        output.writeInt(this.f7042e.size());
        for (Map.Entry<?, ?> entry : this.f7042e.entrySet()) {
            output.writeObject(entry.getKey());
            output.writeObject(entry.getValue());
        }
    }

    public i() {
        this(h0.f());
    }
}
