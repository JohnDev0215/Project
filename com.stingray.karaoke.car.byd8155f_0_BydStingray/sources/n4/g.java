package n4;

import android.graphics.ImageDecoder;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class g implements e4.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1957c f19941b;

    public g(int i7) {
        this.f19940a = i7;
        switch (i7) {
            case 1:
                this.f19941b = new C1957c();
                break;
            default:
                this.f19941b = new C1957c();
                break;
        }
    }

    @Override // e4.j
    public final g4.x a(Object obj, int i7, int i10, e4.h hVar) {
        switch (this.f19940a) {
            case 0:
                return this.f19941b.c(ImageDecoder.createSource((ByteBuffer) obj), i7, i10, hVar);
            default:
                return this.f19941b.c(ImageDecoder.createSource(A4.c.b((InputStream) obj)), i7, i10, hVar);
        }
    }

    @Override // e4.j
    public final /* bridge */ /* synthetic */ boolean b(Object obj, e4.h hVar) {
        switch (this.f19940a) {
            case 0:
                break;
            default:
                break;
        }
        return true;
    }
}
