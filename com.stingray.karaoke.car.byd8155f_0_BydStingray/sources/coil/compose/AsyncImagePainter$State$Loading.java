package coil.compose;

import A3.f;
import A7.m;
import kotlin.Metadata;
import r0.AbstractC2268a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"coil/compose/AsyncImagePainter$State$Loading", "LA3/f;", "Lr0/a;", "painter", "Lr0/a;", "a", "()Lr0/a;", "coil-compose-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AsyncImagePainter$State$Loading extends f {
    private final AbstractC2268a painter;

    public AsyncImagePainter$State$Loading(AbstractC2268a abstractC2268a) {
        this.painter = abstractC2268a;
    }

    @Override // A3.f
    /* JADX INFO: renamed from: a, reason: from getter */
    public final AbstractC2268a getPainter() {
        return this.painter;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof AsyncImagePainter$State$Loading) && m.a(this.painter, ((AsyncImagePainter$State$Loading) obj).painter);
    }

    public final int hashCode() {
        AbstractC2268a abstractC2268a = this.painter;
        if (abstractC2268a == null) {
            return 0;
        }
        return abstractC2268a.hashCode();
    }

    public final String toString() {
        return "Loading(painter=" + this.painter + ')';
    }
}
