package y0;

/* JADX INFO: renamed from: y0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2723a implements InterfaceC2735m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f24049b;

    public C2723a(int i7) {
        this.f24049b = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!C2723a.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        A7.m.d(obj, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.AndroidPointerIconType");
        return this.f24049b == ((C2723a) obj).f24049b;
    }

    public final int hashCode() {
        return this.f24049b;
    }

    public final String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.j(new StringBuilder("AndroidPointerIcon(type="), this.f24049b, ')');
    }
}
