package i2;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f15673m;

    public /* synthetic */ d() {
    }

    public static String c(int i7) {
        return "" + ((char) ((i7 >> 24) & 255)) + ((char) ((i7 >> 16) & 255)) + ((char) ((i7 >> 8) & 255)) + ((char) (i7 & 255));
    }

    public void b(int i7) {
        this.f15673m = i7 | this.f15673m;
    }

    public boolean e(int i7) {
        return (this.f15673m & i7) == i7;
    }

    public String toString() {
        switch (this.l) {
            case 0:
                return c(this.f15673m);
            default:
                return super.toString();
        }
    }

    public d(int i7) {
        this.f15673m = i7;
    }
}
