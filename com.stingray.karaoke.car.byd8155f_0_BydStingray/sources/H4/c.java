package H4;

import android.graphics.Path;

/* JADX INFO: loaded from: classes.dex */
public final class c implements e, u, m, r, t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c f2797b = new c(0);
    public static final c c = new c(1);
    public static final c d = new c(2);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final c f2798e = new c(3);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2799a;

    public /* synthetic */ c(int i7) {
        this.f2799a = i7;
    }

    @Override // H4.u
    public final Path a(float f10, F4.c cVar) {
        switch (this.f2799a) {
            case 0:
                Path path = new Path();
                path.addRect(0.0f, 0.0f, f10, f10, Path.Direction.CW);
                return path;
            case 1:
                Path path2 = new Path();
                float f11 = f10 / 7.0f;
                Path.Direction direction = Path.Direction.CW;
                path2.addRect(0.0f, 0.0f, f10, f11, direction);
                path2.addRect(0.0f, 0.0f, f11, f10, direction);
                float f12 = f10 - f11;
                path2.addRect(f12, 0.0f, f10, f10, direction);
                path2.addRect(0.0f, f12, f10, f10, direction);
                return path2;
            case 2:
                Path path3 = new Path();
                path3.addRect(0.0f, 0.0f, f10, f10, Path.Direction.CW);
                return path3;
            default:
                Path path4 = new Path();
                path4.addRect(0.0f, 0.0f, f10, f10, Path.Direction.CW);
                return path4;
        }
    }
}
