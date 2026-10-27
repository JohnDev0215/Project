package k4;

import f4.C1257b;
import java.io.File;
import z4.C2768d;

/* JADX INFO: renamed from: k4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1685c implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18184a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f18185b;

    public /* synthetic */ C1685c(Object obj, int i7) {
        this.f18184a = i7;
        this.f18185b = obj;
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, e4.h hVar) {
        switch (this.f18184a) {
            case 0:
                byte[] bArr = (byte[]) obj;
                return new p(new C2768d(bArr), new l(bArr, 1, (z) this.f18185b));
            case 1:
                return new p(new C2768d(obj), new C1257b(1, obj.toString(), (z) this.f18185b));
            default:
                File file = (File) obj;
                return new p(new C2768d(file), new C1257b(2, file, (z) this.f18185b));
        }
    }

    @Override // k4.q
    public final boolean b(Object obj) {
        switch (this.f18184a) {
            case 0:
                return true;
            case 1:
                return obj.toString().startsWith("data:image");
            default:
                return true;
        }
    }
}
