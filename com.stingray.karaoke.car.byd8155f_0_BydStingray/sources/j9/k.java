package j9;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class k extends f9.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f17532e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ m f17533f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f17534g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(String str, m mVar, int i7, List list) {
        super(str, true);
        this.f17533f = mVar;
        this.f17534g = i7;
    }

    @Override // f9.a
    public final long a() {
        switch (this.f17532e) {
            case 0:
                this.f17533f.f17556v.getClass();
                try {
                    this.f17533f.f17545H.o(this.f17534g, 9);
                    synchronized (this.f17533f) {
                        this.f17533f.f17547J.remove(Integer.valueOf(this.f17534g));
                    }
                    return -1L;
                } catch (IOException unused) {
                    return -1L;
                }
            default:
                this.f17533f.f17556v.getClass();
                try {
                    this.f17533f.f17545H.o(this.f17534g, 9);
                    synchronized (this.f17533f) {
                        this.f17533f.f17547J.remove(Integer.valueOf(this.f17534g));
                    }
                    return -1L;
                } catch (IOException unused2) {
                    return -1L;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(String str, m mVar, int i7, List list, boolean z6) {
        super(str, true);
        this.f17533f = mVar;
        this.f17534g = i7;
    }
}
