package defpackage;

import android.graphics.PointF;
import android.util.Property;
import java.util.Objects;

/* JADX INFO: renamed from: z6, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1858z6 extends Property {
    public C1858z6(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        return null;
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        F6 f6 = (F6) obj;
        PointF pointF = (PointF) obj2;
        Objects.requireNonNull(f6);
        f6.c = Math.round(pointF.x);
        int iRound = Math.round(pointF.y);
        f6.d = iRound;
        int i = f6.g + 1;
        f6.g = i;
        if (f6.f == i) {
            AbstractC1419r7.c(f6.e, f6.a, f6.b, f6.c, iRound);
            f6.f = 0;
            f6.g = 0;
        }
    }
}
