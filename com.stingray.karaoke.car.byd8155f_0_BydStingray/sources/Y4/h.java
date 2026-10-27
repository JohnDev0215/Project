package Y4;

import android.graphics.RectF;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class h implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f10738a;

    public h(float f10) {
        this.f10738a = f10;
    }

    @Override // Y4.c
    public final float a(RectF rectF) {
        return rectF.height() * this.f10738a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof h) && this.f10738a == ((h) obj).f10738a;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.f10738a)});
    }
}
