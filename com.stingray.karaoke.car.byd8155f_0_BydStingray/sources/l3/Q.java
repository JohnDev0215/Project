package l3;

import android.text.TextUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class Q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f18539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18540b;
    public final int c;

    public Q(String str, int i7, int i10) {
        this.f18539a = str;
        this.f18540b = i7;
        this.c = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Q)) {
            return false;
        }
        Q q4 = (Q) obj;
        int i7 = this.c;
        String str = this.f18539a;
        int i10 = this.f18540b;
        return (i10 < 0 || q4.f18540b < 0) ? TextUtils.equals(str, q4.f18539a) && i7 == q4.c : TextUtils.equals(str, q4.f18539a) && i10 == q4.f18540b && i7 == q4.c;
    }

    public final int hashCode() {
        return Objects.hash(this.f18539a, Integer.valueOf(this.c));
    }
}
