package androidx.media;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    c f838a;

    public b(@NonNull String str, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f838a = new d(str, i, i2);
        } else {
            this.f838a = new e(str, i, i2);
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b) {
            return this.f838a.equals(((b) obj).f838a);
        }
        return false;
    }

    public int hashCode() {
        return this.f838a.hashCode();
    }
}
