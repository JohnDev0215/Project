package x5;

import g5.r;
import java.util.Iterator;
import r5.p;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: SequenceBuilder.kt */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Sequences.kt */
    public static final class a<T> implements d<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ p f9862a;

        public a(p pVar) {
            this.f9862a = pVar;
        }

        @Override // x5.d
        public Iterator<T> iterator() {
            return h.a(this.f9862a);
        }
    }

    public static final <T> Iterator<T> a(p<? super f<? super T>, ? super k5.d<? super r>, ? extends Object> block) {
        kotlin.jvm.internal.l.e(block, "block");
        e eVar = new e();
        eVar.g(l5.c.b(block, eVar, eVar));
        return eVar;
    }

    public static <T> d<T> b(p<? super f<? super T>, ? super k5.d<? super r>, ? extends Object> block) {
        kotlin.jvm.internal.l.e(block, "block");
        return new a(block);
    }
}
