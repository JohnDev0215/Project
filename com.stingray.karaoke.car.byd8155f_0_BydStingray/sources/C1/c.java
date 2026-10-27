package C1;

import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f928a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f929b;
    public List c;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Objects.equals(this.f928a, cVar.f928a) && Objects.equals(this.f929b, cVar.f929b) && Objects.equals(this.c, cVar.c);
    }

    public final int hashCode() {
        return Objects.hash(this.f928a, this.f929b, this.c);
    }
}
