package kotlin.text;

import java.util.Iterator;
import kotlin.collections.AbstractCollection;

/* JADX INFO: compiled from: Regex.kt */
/* JADX INFO: loaded from: classes.dex */
public final class MatcherMatchResult$groups$1 extends AbstractCollection<g> implements h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ MatcherMatchResult f5160b;

    MatcherMatchResult$groups$1(MatcherMatchResult matcherMatchResult) {
        this.f5160b = matcherMatchResult;
    }

    public /* bridge */ boolean a(g gVar) {
        return super.contains(gVar);
    }

    public g b(int i) {
        kotlin.o.c cVarI = j.i(this.f5160b.c(), i);
        if (cVarI.g().intValue() < 0) {
            return null;
        }
        String strGroup = this.f5160b.c().group(i);
        kotlin.jvm.internal.i.b(strGroup, "matchResult.group(index)");
        return new g(strGroup, cVarI);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj != null ? obj instanceof g : true) {
            return a((g) obj);
        }
        return false;
    }

    @Override // kotlin.collections.AbstractCollection
    public int getSize() {
        return this.f5160b.c().groupCount() + 1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return false;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<g> iterator() {
        return kotlin.p.j.g(kotlin.collections.s.v(kotlin.collections.k.g(this)), new kotlin.jvm.b.l<Integer, g>() { // from class: kotlin.text.MatcherMatchResult$groups$1$iterator$1
            {
                super(1);
            }

            @Override // kotlin.jvm.b.l
            public /* bridge */ /* synthetic */ g invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final g invoke(int i) {
                return this.this$0.b(i);
            }
        }).iterator();
    }
}
