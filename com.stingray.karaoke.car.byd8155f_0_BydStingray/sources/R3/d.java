package R3;

import b4.C0995a;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class d implements b {
    public final C0995a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f7387m = -1.0f;

    public d(List list) {
        this.l = (C0995a) list.get(0);
    }

    @Override // R3.b
    public final boolean isEmpty() {
        return false;
    }

    @Override // R3.b
    public final float p() {
        return this.l.a();
    }

    @Override // R3.b
    public final boolean q(float f10) {
        if (this.f7387m == f10) {
            return true;
        }
        this.f7387m = f10;
        return false;
    }

    @Override // R3.b
    public final float r() {
        return this.l.b();
    }

    @Override // R3.b
    public final C0995a t() {
        return this.l;
    }

    @Override // R3.b
    public final boolean x(float f10) {
        return !this.l.c();
    }
}
