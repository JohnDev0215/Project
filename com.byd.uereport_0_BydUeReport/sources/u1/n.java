package u1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: BaseAnimatableValue.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class n<V, O> implements m<V, O> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<b2.a<V>> f8143a;

    public n(V v7) {
        this(Collections.singletonList(new b2.a(v7)));
    }

    @Override // u1.m
    public List<b2.a<V>> b() {
        return this.f8143a;
    }

    @Override // u1.m
    public boolean c() {
        return this.f8143a.isEmpty() || (this.f8143a.size() == 1 && this.f8143a.get(0).h());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f8143a.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.f8143a.toArray()));
        }
        return sb.toString();
    }

    public n(List<b2.a<V>> list) {
        this.f8143a = list;
    }
}
