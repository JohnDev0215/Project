package p;

import com.google.firebase.encoders.proto.Protobuf$IntEncoding;

/* JADX INFO: loaded from: classes.dex */
public final class vc implements sy1 {
    public final int a;
    public final Protobuf$IntEncoding b;

    public vc(int i, Protobuf$IntEncoding protobuf$IntEncoding) {
        this.a = i;
        this.b = protobuf$IntEncoding;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return sy1.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof sy1)) {
            return false;
        }
        sy1 sy1Var = (sy1) obj;
        return this.a == ((vc) sy1Var).a && this.b.equals(((vc) sy1Var).b);
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (14552422 ^ this.a) + (this.b.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.a + "intEncoding=" + this.b + ')';
    }
}
