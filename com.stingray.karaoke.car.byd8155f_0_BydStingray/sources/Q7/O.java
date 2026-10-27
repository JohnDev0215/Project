package Q7;

import F8.AbstractC0287w;
import z8.InterfaceC2796d;

/* JADX INFO: loaded from: classes.dex */
public final class O implements InterfaceC2796d, N {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final O f7180m = new O(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final O f7181n = new O(1);
    public final /* synthetic */ int l;

    public /* synthetic */ O(int i7) {
        this.l = i7;
    }

    @Override // z8.InterfaceC2796d
    public AbstractC0287w b() {
        switch (this.l) {
            case 2:
                throw new IllegalStateException("This method should not be called");
            case 3:
                throw new IllegalStateException("This method should not be called");
            default:
                throw new IllegalStateException("This method should not be called");
        }
    }

    public String toString() {
        switch (this.l) {
            case 6:
                return "NO_SOURCE";
            default:
                return super.toString();
        }
    }
}
