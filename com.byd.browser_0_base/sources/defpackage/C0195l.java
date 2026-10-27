package defpackage;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: l, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AbsoluteCornerSize.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0195l implements l00 {
    public final float a;

    public C0195l(float f) {
        this.a = f;
    }

    @Override // defpackage.l00
    public final float a(RectF rectF) {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof C0195l) && this.a == ((C0195l) obj).a;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.a)});
    }
}
