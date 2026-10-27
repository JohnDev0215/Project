package G4;

import H4.m;
import android.graphics.Path;

/* JADX INFO: loaded from: classes.dex */
public final class a implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f2666a = new a();

    @Override // H4.u
    public final Path a(float f10, F4.c cVar) {
        Path path = new Path();
        float f11 = f10 / 5.0f;
        Path.Direction direction = Path.Direction.CW;
        path.addRect(0.0f, 0.0f, f10, f11, direction);
        path.addRect(0.0f, 0.0f, f11, f10, direction);
        float f12 = f10 - f11;
        path.addRect(f12, 0.0f, f10, f10, direction);
        path.addRect(0.0f, f12, f10, f10, direction);
        return path;
    }
}
