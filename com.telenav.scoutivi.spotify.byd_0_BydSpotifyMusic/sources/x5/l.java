package x5;

import h5.p;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Sequences.kt */
/* JADX INFO: loaded from: classes.dex */
public class l extends k {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Iterables.kt */
    public static final class a<T> implements Iterable<T>, s5.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ d f9864e;

        public a(d dVar) {
            this.f9864e = dVar;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.f9864e.iterator();
        }
    }

    public static <T> Iterable<T> e(d<? extends T> dVar) {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        return new a(dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> d<T> f(d<? extends T> dVar, int i7) {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        if (i7 >= 0) {
            return i7 == 0 ? dVar : dVar instanceof c ? ((c) dVar).a(i7) : new b(dVar, i7);
        }
        throw new IllegalArgumentException(("Requested element count " + i7 + " is less than zero.").toString());
    }

    public static final <T, A extends Appendable> A g(d<? extends T> dVar, A buffer, CharSequence separator, CharSequence prefix, CharSequence postfix, int i7, CharSequence truncated, r5.l<? super T, ? extends CharSequence> lVar) throws IOException {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        kotlin.jvm.internal.l.e(buffer, "buffer");
        kotlin.jvm.internal.l.e(separator, "separator");
        kotlin.jvm.internal.l.e(prefix, "prefix");
        kotlin.jvm.internal.l.e(postfix, "postfix");
        kotlin.jvm.internal.l.e(truncated, "truncated");
        buffer.append(prefix);
        int i8 = 0;
        for (T t6 : dVar) {
            i8++;
            if (i8 > 1) {
                buffer.append(separator);
            }
            if (i7 >= 0 && i8 > i7) {
                break;
            }
            y5.h.a(buffer, t6, lVar);
        }
        if (i7 >= 0 && i8 > i7) {
            buffer.append(truncated);
        }
        buffer.append(postfix);
        return buffer;
    }

    public static final <T> String h(d<? extends T> dVar, CharSequence separator, CharSequence prefix, CharSequence postfix, int i7, CharSequence truncated, r5.l<? super T, ? extends CharSequence> lVar) {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        kotlin.jvm.internal.l.e(separator, "separator");
        kotlin.jvm.internal.l.e(prefix, "prefix");
        kotlin.jvm.internal.l.e(postfix, "postfix");
        kotlin.jvm.internal.l.e(truncated, "truncated");
        String string = ((StringBuilder) g(dVar, new StringBuilder(), separator, prefix, postfix, i7, truncated, lVar)).toString();
        kotlin.jvm.internal.l.d(string, "joinTo(StringBuilder(), …ed, transform).toString()");
        return string;
    }

    public static /* synthetic */ String i(d dVar, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i7, CharSequence charSequence4, r5.l lVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            charSequence = ", ";
        }
        CharSequence charSequence5 = (i8 & 2) != 0 ? "" : charSequence2;
        CharSequence charSequence6 = (i8 & 4) == 0 ? charSequence3 : "";
        if ((i8 & 8) != 0) {
            i7 = -1;
        }
        int i9 = i7;
        if ((i8 & 16) != 0) {
            charSequence4 = "...";
        }
        CharSequence charSequence7 = charSequence4;
        if ((i8 & 32) != 0) {
            lVar = null;
        }
        return h(dVar, charSequence, charSequence5, charSequence6, i9, charSequence7, lVar);
    }

    public static <T, R> d<R> j(d<? extends T> dVar, r5.l<? super T, ? extends R> transform) {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        kotlin.jvm.internal.l.e(transform, "transform");
        return new m(dVar, transform);
    }

    public static final <T, C extends Collection<? super T>> C k(d<? extends T> dVar, C destination) {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        kotlin.jvm.internal.l.e(destination, "destination");
        Iterator<? extends T> it = dVar.iterator();
        while (it.hasNext()) {
            destination.add(it.next());
        }
        return destination;
    }

    public static <T> List<T> l(d<? extends T> dVar) {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        return p.m(m(dVar));
    }

    public static final <T> List<T> m(d<? extends T> dVar) {
        kotlin.jvm.internal.l.e(dVar, "<this>");
        return (List) k(dVar, new ArrayList());
    }
}
