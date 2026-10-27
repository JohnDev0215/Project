package U0;

import A7.m;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Locale f8956a;

    public a(Locale locale) {
        this.f8956a = locale;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return m.a(this.f8956a.toLanguageTag(), ((a) obj).f8956a.toLanguageTag());
    }

    public final int hashCode() {
        return this.f8956a.toLanguageTag().hashCode();
    }

    public final String toString() {
        return this.f8956a.toLanguageTag();
    }
}
