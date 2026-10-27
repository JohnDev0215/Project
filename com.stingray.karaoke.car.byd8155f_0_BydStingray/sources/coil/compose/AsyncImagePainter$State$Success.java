package coil.compose;

import A3.f;
import A7.m;
import K3.p;
import kotlin.Metadata;
import r0.AbstractC2268a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"coil/compose/AsyncImagePainter$State$Success", "LA3/f;", "Lr0/a;", "painter", "Lr0/a;", "a", "()Lr0/a;", "coil-compose-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AsyncImagePainter$State$Success extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f12645a;
    private final AbstractC2268a painter;

    public AsyncImagePainter$State$Success(AbstractC2268a abstractC2268a, p pVar) {
        this.painter = abstractC2268a;
        this.f12645a = pVar;
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
        if (!(obj instanceof AsyncImagePainter$State$Success)) {
            return false;
        }
        AsyncImagePainter$State$Success asyncImagePainter$State$Success = (AsyncImagePainter$State$Success) obj;
        return m.a(this.painter, asyncImagePainter$State$Success.painter) && m.a(this.f12645a, asyncImagePainter$State$Success.f12645a);
    }

    public final int hashCode() {
        return this.f12645a.hashCode() + (this.painter.hashCode() * 31);
    }

    public final String toString() {
        return "Success(painter=" + this.painter + ", result=" + this.f12645a + ')';
    }
}
