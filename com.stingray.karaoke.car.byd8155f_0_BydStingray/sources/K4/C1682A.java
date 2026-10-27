package k4;

import java.io.File;
import z4.C2768d;

/* JADX INFO: renamed from: k4.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1682A implements q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C1682A f18174b = new C1682A(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18175a;

    public /* synthetic */ C1682A(int i7) {
        this.f18175a = i7;
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, e4.h hVar) {
        switch (this.f18175a) {
            case 0:
                return new p(new C2768d(obj), new C1686d(obj, 1));
            case 1:
                File file = (File) obj;
                return new p(new C2768d(file), new C1686d(file, 0));
            default:
                return null;
        }
    }

    @Override // k4.q
    public final boolean b(Object obj) {
        switch (this.f18175a) {
            case 0:
                return true;
            case 1:
                return true;
            default:
                return false;
        }
    }
}
