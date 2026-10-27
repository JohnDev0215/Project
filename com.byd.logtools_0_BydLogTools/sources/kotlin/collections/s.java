package kotlin.collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import okhttp3.HttpUrl;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Collections.kt */
/* JADX INFO: loaded from: classes.dex */
public class s extends r {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Sequences.kt */
    public static final class a<T> implements kotlin.p.d<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Iterable f5097a;

        public a(Iterable iterable) {
            this.f5097a = iterable;
        }

        @Override // kotlin.p.d
        public Iterator<T> iterator() {
            return this.f5097a.iterator();
        }
    }

    public static final <T> String A(Iterable<? extends T> iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.b.l<? super T, ? extends CharSequence> lVar) {
        kotlin.jvm.internal.i.c(iterable, "$this$joinToString");
        kotlin.jvm.internal.i.c(charSequence, "separator");
        kotlin.jvm.internal.i.c(charSequence2, "prefix");
        kotlin.jvm.internal.i.c(charSequence3, "postfix");
        kotlin.jvm.internal.i.c(charSequence4, "truncated");
        String string = ((StringBuilder) y(iterable, new StringBuilder(), charSequence, charSequence2, charSequence3, i, charSequence4, lVar)).toString();
        kotlin.jvm.internal.i.b(string, "joinTo(StringBuilder(), …ed, transform).toString()");
        return string;
    }

    public static /* synthetic */ String B(Iterable iterable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.b.l lVar, int i2, Object obj) {
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
        return A(iterable, charSequence, charSequence6, charSequence5, i4, charSequence7, lVar);
    }

    public static <T> List<T> C(Collection<? extends T> collection, Iterable<? extends T> iterable) {
        kotlin.jvm.internal.i.c(collection, "$this$plus");
        kotlin.jvm.internal.i.c(iterable, "elements");
        if (!(iterable instanceof Collection)) {
            ArrayList arrayList = new ArrayList(collection);
            p.r(arrayList, iterable);
            return arrayList;
        }
        Collection collection2 = (Collection) iterable;
        ArrayList arrayList2 = new ArrayList(collection.size() + collection2.size());
        arrayList2.addAll(collection);
        arrayList2.addAll(collection2);
        return arrayList2;
    }

    public static <T> List<T> D(Collection<? extends T> collection, T t) {
        kotlin.jvm.internal.i.c(collection, "$this$plus");
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(t);
        return arrayList;
    }

    public static <T> T E(Iterable<? extends T> iterable) {
        kotlin.jvm.internal.i.c(iterable, "$this$single");
        if (iterable instanceof List) {
            return (T) F((List) iterable);
        }
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        T next = it.next();
        if (it.hasNext()) {
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        return next;
    }

    public static final <T> T F(List<? extends T> list) {
        kotlin.jvm.internal.i.c(list, "$this$single");
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
        if (size == 1) {
            return list.get(0);
        }
        throw new IllegalArgumentException("List has more than one element.");
    }

    public static final <T, C extends Collection<? super T>> C G(Iterable<? extends T> iterable, C c2) {
        kotlin.jvm.internal.i.c(iterable, "$this$toCollection");
        kotlin.jvm.internal.i.c(c2, "destination");
        Iterator<? extends T> it = iterable.iterator();
        while (it.hasNext()) {
            c2.add(it.next());
        }
        return c2;
    }

    public static <T> List<T> H(Iterable<? extends T> iterable) {
        kotlin.jvm.internal.i.c(iterable, "$this$toList");
        if (!(iterable instanceof Collection)) {
            return k.l(I(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return k.f();
        }
        if (size != 1) {
            return J(collection);
        }
        return j.b(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static final <T> List<T> I(Iterable<? extends T> iterable) {
        kotlin.jvm.internal.i.c(iterable, "$this$toMutableList");
        return iterable instanceof Collection ? J((Collection) iterable) : (List) G(iterable, new ArrayList());
    }

    public static <T> List<T> J(Collection<? extends T> collection) {
        kotlin.jvm.internal.i.c(collection, "$this$toMutableList");
        return new ArrayList(collection);
    }

    public static <T> Set<T> K(Iterable<? extends T> iterable) {
        kotlin.jvm.internal.i.c(iterable, "$this$toSet");
        if (!(iterable instanceof Collection)) {
            return c0.c((Set) G(iterable, new LinkedHashSet()));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return c0.b();
        }
        if (size != 1) {
            return (Set) G(iterable, new LinkedHashSet(x.a(collection.size())));
        }
        return b0.a(iterable instanceof List ? ((List) iterable).get(0) : iterable.iterator().next());
    }

    public static <T> kotlin.p.d<T> v(Iterable<? extends T> iterable) {
        kotlin.jvm.internal.i.c(iterable, "$this$asSequence");
        return new a(iterable);
    }

    public static <T> T w(List<? extends T> list) {
        kotlin.jvm.internal.i.c(list, "$this$first");
        if (list.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return list.get(0);
    }

    public static <T> T x(List<? extends T> list) {
        kotlin.jvm.internal.i.c(list, "$this$firstOrNull");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static final <T, A extends Appendable> A y(Iterable<? extends T> iterable, A a2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.b.l<? super T, ? extends CharSequence> lVar) throws IOException {
        kotlin.jvm.internal.i.c(iterable, "$this$joinTo");
        kotlin.jvm.internal.i.c(a2, "buffer");
        kotlin.jvm.internal.i.c(charSequence, "separator");
        kotlin.jvm.internal.i.c(charSequence2, "prefix");
        kotlin.jvm.internal.i.c(charSequence3, "postfix");
        kotlin.jvm.internal.i.c(charSequence4, "truncated");
        a2.append(charSequence2);
        int i2 = 0;
        for (T t : iterable) {
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

    public static /* synthetic */ Appendable z(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, CharSequence charSequence4, kotlin.jvm.b.l lVar, int i2, Object obj) {
        CharSequence charSequence5 = (i2 & 2) != 0 ? ", " : charSequence;
        int i3 = i2 & 4;
        CharSequence charSequence6 = HttpUrl.FRAGMENT_ENCODE_SET;
        CharSequence charSequence7 = i3 != 0 ? HttpUrl.FRAGMENT_ENCODE_SET : charSequence2;
        if ((i2 & 8) == 0) {
            charSequence6 = charSequence3;
        }
        return y(iterable, appendable, charSequence5, charSequence7, charSequence6, (i2 & 16) != 0 ? -1 : i, (i2 & 32) != 0 ? "..." : charSequence4, (i2 & 64) != 0 ? null : lVar);
    }
}
