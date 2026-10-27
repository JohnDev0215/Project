package Y4;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f10705a;

    public a(float f10) {
        this.f10705a = f10;
    }

    @Override // Y4.c
    public final float a(RectF rectF) {
        return this.f10705a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f10705a == ((a) obj).f10705a;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f10705a)});
    }
}
