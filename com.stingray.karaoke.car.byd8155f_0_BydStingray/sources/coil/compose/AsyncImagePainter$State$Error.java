package coil.compose;

import A3.f;
import A7.m;
import K3.e;
import kotlin.Metadata;
import r0.AbstractC2268a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u00002\u00020\u0001R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"coil/compose/AsyncImagePainter$State$Error", "LA3/f;", "Lr0/a;", "painter", "Lr0/a;", "a", "()Lr0/a;", "coil-compose-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AsyncImagePainter$State$Error extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f12644a;
    private final AbstractC2268a painter;

    public AsyncImagePainter$State$Error(AbstractC2268a abstractC2268a, e eVar) {
        this.painter = abstractC2268a;
        this.f12644a = eVar;
    }

    public static AsyncImagePainter$State$Error b(AsyncImagePainter$State$Error asyncImagePainter$State$Error, AbstractC2268a abstractC2268a) {
        e eVar = asyncImagePainter$State$Error.f12644a;
        asyncImagePainter$State$Error.getClass();
        return new AsyncImagePainter$State$Error(abstractC2268a, eVar);
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
        if (!(obj instanceof AsyncImagePainter$State$Error)) {
            return false;
        }
        AsyncImagePainter$State$Error asyncImagePainter$State$Error = (AsyncImagePainter$State$Error) obj;
        return m.a(this.painter, asyncImagePainter$State$Error.painter) && m.a(this.f12644a, asyncImagePainter$State$Error.f12644a);
    }

    public final int hashCode() {
        AbstractC2268a abstractC2268a = this.painter;
        return this.f12644a.hashCode() + ((abstractC2268a == null ? 0 : abstractC2268a.hashCode()) * 31);
    }

    public final String toString() {
        return "Error(painter=" + this.painter + ", result=" + this.f12644a + ')';
    }
}
