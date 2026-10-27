package kotlin.p;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.b.l;
import okhttp3.HttpUrl;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public class j extends i {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Iterables.kt */
    public static final class a<T> implements Iterable<T>, kotlin.jvm.internal.n.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f5151b;

        public a(d dVar) {
            this.f5151b = dVar;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.f5151b.iterator();
        }
    }

    public static <T> Iterable<T> b(d<? extends T> dVar) {
        kotlin.jvm.internal.i.c(dVar, "$this$asIterable");
        return new a(dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> d<T> c(d<? extends T> dVar, int i) {
        kotlin.jvm.internal.i.c(dVar, "$this$drop");
        if (i >= 0) {
            return i == 0 ? dVar : dVar instanceof b ? ((b) dVar).a(i) : new kotlin.p.a(dVar, i);
        }
        throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
    }

    public static final <T, A extends Appendable> A d(d<? extends T> dVar, A a2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, l<? super T, ? extends CharSequence> lVar) throws IOException {
        kotlin.jvm.internal.i.c(dVar, "$this$joinTo");
        kotlin.jvm.internal.i.c(a2, "buffer");
        kotlin.jvm.internal.i.c(charSequence, "separator");
        kotlin.jvm.internal.i.c(charSequence2, "prefix");
        kotlin.jvm.internal.i.c(charSequence3, "postfix");
        kotlin.jvm.internal.i.c(charSequence4, "truncated");
        a2.append(charSequence2);
        int i2 = 0;
        for (T t : dVar) {
            i2++;
            if (i2 > 1) {
                a2.append(charSequence);
            }
            if (i >= 0 && i2 > i) {
                break;
            }
            kotlin.text.l.a(a2, t, lVar);
        }
        if (i >= 0 && i2 > i) {
            a2.append(charSequence4);
        }
        a2.append(charSequence3);
        return a2;
    }

    public static final <T> String e(d<? extends T> dVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, l<? super T, ? extends CharSequence> lVar) {
        kotlin.jvm.internal.i.c(dVar, "$this$joinToString");
        kotlin.jvm.internal.i.c(charSequence, "separator");
        kotlin.jvm.internal.i.c(charSequence2, "prefix");
        kotlin.jvm.internal.i.c(charSequence3, "postfix");
        kotlin.jvm.internal.i.c(charSequence4, "truncated");
        String string = ((StringBuilder) d(dVar, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, lVar)).toString();
        kotlin.jvm.internal.i.b(string, "joinTo(StringBuilder(), …ed, transform).toString()");
        return string;
    }

    public static /* synthetic */ String f(d dVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, l lVar, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            charSequence = ", ";
        }
        int i3 = i2 & 2;
        CharSequence charSequence5 = HttpUrl.FRAGMENT_ENCODE_SET;
        CharSequence charSequence6 = i3 != 0 ? HttpUrl.FRAGMENT_ENCODE_SET : charSequence2;
        if ((i2 & 4) == 0) {
            charSequence5 = charSequence3;
        }
        if ((i2 & 8) != 0) {
            i = -1;
        }
        int i4 = i;
        if ((i2 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i2 & 32) != 0) {
            lVar = null;
        }
        return e(dVar, charSequence, charSequence6, charSequence5, i4, charSequence7, lVar);
    }

    public static <T, R> d<R> g(d<? extends T> dVar, l<? super T, ? extends R> lVar) {
        kotlin.jvm.internal.i.c(dVar, "$this$map");
        kotlin.jvm.internal.i.c(lVar, "transform");
        return new k(dVar, lVar);
    }

    public static final <T, C extends Collection<? super T>> C h(d<? extends T> dVar, C c2) {
        kotlin.jvm.internal.i.c(dVar, "$this$toCollection");
        kotlin.jvm.internal.i.c(c2, "destination");
        Iterator<? extends T> it = dVar.iterator();
        while (it.hasNext()) {
            c2.add(it.next());
        }
        return c2;
    }

    public static <T> List<T> i(d<? extends T> dVar) {
        kotlin.jvm.internal.i.c(dVar, "$this$toList");
        return kotlin.collections.k.l(j(dVar));
    }

    public static final <T> List<T> j(d<? extends T> dVar) {
        kotlin.jvm.internal.i.c(dVar, "$this$toMutableList");
        return (List) h(dVar, new ArrayList());
    }
}
