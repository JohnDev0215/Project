package h5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Arrays.kt */
/* JADX INFO: loaded from: classes.dex */
public class l extends k {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: compiled from: Iterables.kt */
    public static final class a<T> implements Iterable<T>, s5.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ Object[] f6654e;

        public a(Object[] objArr) {
            this.f6654e = objArr;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return kotlin.jvm.internal.b.a(this.f6654e);
        }
    }

    public static <T> Iterable<T> m(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        return tArr.length == 0 ? p.h() : new a(tArr);
    }

    public static final <T> boolean n(T[] tArr, T t6) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        return s(tArr, t6) >= 0;
    }

    public static final <T> List<T> o(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        return (List) p(tArr, new ArrayList());
    }

    public static final <C extends Collection<? super T>, T> C p(T[] tArr, C destination) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        kotlin.jvm.internal.l.e(destination, "destination");
        for (T t6 : tArr) {
            if (t6 != null) {
                destination.add(t6);
            }
        }
        return destination;
    }

    public static <T> v5.c q(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        return new v5.c(0, r(tArr));
    }

    public static <T> int r(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        return tArr.length - 1;
    }

    public static <T> int s(T[] tArr, T t6) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        int i7 = 0;
        if (t6 == null) {
            int length = tArr.length;
            while (i7 < length) {
                if (tArr[i7] == null) {
                    return i7;
                }
                i7++;
            }
            return -1;
        }
        int length2 = tArr.length;
        while (i7 < length2) {
            if (kotlin.jvm.internal.l.a(t6, tArr[i7])) {
                return i7;
            }
            i7++;
        }
        return -1;
    }

    public static char t(char[] cArr) {
        kotlin.jvm.internal.l.e(cArr, "<this>");
        int length = cArr.length;
        if (length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        if (length == 1) {
            return cArr[0];
        }
        throw new IllegalArgumentException("Array has more than one element.");
    }

    public static <T> T u(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        if (tArr.length == 1) {
            return tArr[0];
        }
        return null;
    }

    public static <T> List<T> v(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        int length = tArr.length;
        return length != 0 ? length != 1 ? w(tArr) : o.d(tArr[0]) : p.h();
    }

    public static <T> List<T> w(T[] tArr) {
        kotlin.jvm.internal.l.e(tArr, "<this>");
        return new ArrayList(p.e(tArr));
    }
}
