package B5;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class A implements v, f7.c {
    public final /* synthetic */ StringBuilder l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f642m;

    public A(StringBuilder sb, boolean z6) {
        this.l = sb;
        this.f642m = z6;
    }

    @Override // B5.v
    public boolean a(InputStream inputStream, int i7) {
        boolean z6 = this.f642m;
        StringBuilder sb = this.l;
        if (z6) {
            this.f642m = false;
        } else {
            sb.append(", ");
        }
        sb.append(i7);
        return true;
    }

    @Override // f7.c
    public void b(Object obj) {
        StringBuilder sb = this.l;
        sb.append((String) obj);
        if (this.f642m) {
            return;
        }
        sb.append((char) 30);
    }

    public A(StringBuilder sb) {
        this.l = sb;
        this.f642m = true;
    }
}
