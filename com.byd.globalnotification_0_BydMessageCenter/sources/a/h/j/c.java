package a.h.j;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class c<F, S> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F f801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final S f802b;

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return Objects.equals(cVar.f801a, null) && Objects.equals(cVar.f802b, null);
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "Pair{null null}";
    }
}
