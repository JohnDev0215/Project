package t0;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: compiled from: FocusStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: compiled from: FocusStrategy.java */
    public interface a<T> {
        void a(T t7, Rect rect);
    }

    /* JADX INFO: renamed from: t0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FocusStrategy.java */
    public interface InterfaceC0122b<T, V> {
        V a(T t7, int i8);

        int b(T t7);
    }

    /* JADX INFO: compiled from: FocusStrategy.java */
    public static class c<T> implements Comparator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f7967a = new Rect();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f7968b = new Rect();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f7969c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a<T> f7970d;

        public c(boolean z7, a<T> aVar) {
            this.f7969c = z7;
            this.f7970d = aVar;
        }

        @Override // java.util.Comparator
        public int compare(T t7, T t8) {
            Rect rect = this.f7967a;
            Rect rect2 = this.f7968b;
            this.f7970d.a(t7, rect);
            this.f7970d.a(t8, rect2);
            int i8 = rect.top;
            int i9 = rect2.top;
            if (i8 < i9) {
                return -1;
            }
            if (i8 > i9) {
                return 1;
            }
            int i10 = rect.left;
            int i11 = rect2.left;
            if (i10 < i11) {
                return this.f7969c ? 1 : -1;
            }
            if (i10 > i11) {
                return this.f7969c ? -1 : 1;
            }
            int i12 = rect.bottom;
            int i13 = rect2.bottom;
            if (i12 < i13) {
                return -1;
            }
            if (i12 > i13) {
                return 1;
            }
            int i14 = rect.right;
            int i15 = rect2.right;
            if (i14 < i15) {
                return this.f7969c ? 1 : -1;
            }
            if (i14 > i15) {
                return this.f7969c ? -1 : 1;
            }
            return 0;
        }
    }

    public static boolean a(int i8, Rect rect, Rect rect2, Rect rect3) {
        boolean zB = b(i8, rect, rect2);
        if (b(i8, rect, rect3) || !zB) {
            return false;
        }
        return !j(i8, rect, rect3) || i8 == 17 || i8 == 66 || k(i8, rect, rect2) < m(i8, rect, rect3);
    }

    public static boolean b(int i8, Rect rect, Rect rect2) {
        if (i8 != 17) {
            if (i8 != 33) {
                if (i8 != 66) {
                    if (i8 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static <L, T> T c(L l8, InterfaceC0122b<L, T> interfaceC0122b, a<T> aVar, T t7, Rect rect, int i8) {
        Rect rect2 = new Rect(rect);
        if (i8 == 17) {
            rect2.offset(rect.width() + 1, 0);
        } else if (i8 == 33) {
            rect2.offset(0, rect.height() + 1);
        } else if (i8 == 66) {
            rect2.offset(-(rect.width() + 1), 0);
        } else {
            if (i8 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect2.offset(0, -(rect.height() + 1));
        }
        T t8 = null;
        int iB = interfaceC0122b.b(l8);
        Rect rect3 = new Rect();
        for (int i9 = 0; i9 < iB; i9++) {
            T tA = interfaceC0122b.a(l8, i9);
            if (tA != t7) {
                aVar.a(tA, rect3);
                if (h(i8, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    t8 = tA;
                }
            }
        }
        return t8;
    }

    public static <L, T> T d(L l8, InterfaceC0122b<L, T> interfaceC0122b, a<T> aVar, T t7, int i8, boolean z7, boolean z8) {
        int iB = interfaceC0122b.b(l8);
        ArrayList arrayList = new ArrayList(iB);
        for (int i9 = 0; i9 < iB; i9++) {
            arrayList.add(interfaceC0122b.a(l8, i9));
        }
        Collections.sort(arrayList, new c(z7, aVar));
        if (i8 == 1) {
            return (T) f(t7, arrayList, z8);
        }
        if (i8 == 2) {
            return (T) e(t7, arrayList, z8);
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    }

    public static <T> T e(T t7, ArrayList<T> arrayList, boolean z7) {
        int size = arrayList.size();
        int iLastIndexOf = (t7 == null ? -1 : arrayList.lastIndexOf(t7)) + 1;
        if (iLastIndexOf < size) {
            return arrayList.get(iLastIndexOf);
        }
        if (!z7 || size <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    public static <T> T f(T t7, ArrayList<T> arrayList, boolean z7) {
        int size = arrayList.size();
        int iIndexOf = (t7 == null ? size : arrayList.indexOf(t7)) - 1;
        if (iIndexOf >= 0) {
            return arrayList.get(iIndexOf);
        }
        if (!z7 || size <= 0) {
            return null;
        }
        return arrayList.get(size - 1);
    }

    public static int g(int i8, int i9) {
        return (i8 * 13 * i8) + (i9 * i9);
    }

    public static boolean h(int i8, Rect rect, Rect rect2, Rect rect3) {
        if (!i(rect, rect2, i8)) {
            return false;
        }
        if (i(rect, rect3, i8) && !a(i8, rect, rect2, rect3)) {
            return !a(i8, rect, rect3, rect2) && g(k(i8, rect, rect2), o(i8, rect, rect2)) < g(k(i8, rect, rect3), o(i8, rect, rect3));
        }
        return true;
    }

    public static boolean i(Rect rect, Rect rect2, int i8) {
        if (i8 == 17) {
            int i9 = rect.right;
            int i10 = rect2.right;
            return (i9 > i10 || rect.left >= i10) && rect.left > rect2.left;
        }
        if (i8 == 33) {
            int i11 = rect.bottom;
            int i12 = rect2.bottom;
            return (i11 > i12 || rect.top >= i12) && rect.top > rect2.top;
        }
        if (i8 == 66) {
            int i13 = rect.left;
            int i14 = rect2.left;
            return (i13 < i14 || rect.right <= i14) && rect.right < rect2.right;
        }
        if (i8 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i15 = rect.top;
        int i16 = rect2.top;
        return (i15 < i16 || rect.bottom <= i16) && rect.bottom < rect2.bottom;
    }

    public static boolean j(int i8, Rect rect, Rect rect2) {
        if (i8 == 17) {
            return rect.left >= rect2.right;
        }
        if (i8 == 33) {
            return rect.top >= rect2.bottom;
        }
        if (i8 == 66) {
            return rect.right <= rect2.left;
        }
        if (i8 == 130) {
            return rect.bottom <= rect2.top;
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
    }

    public static int k(int i8, Rect rect, Rect rect2) {
        return Math.max(0, l(i8, rect, rect2));
    }

    public static int l(int i8, Rect rect, Rect rect2) {
        int i9;
        int i10;
        if (i8 == 17) {
            i9 = rect.left;
            i10 = rect2.right;
        } else if (i8 == 33) {
            i9 = rect.top;
            i10 = rect2.bottom;
        } else if (i8 == 66) {
            i9 = rect2.left;
            i10 = rect.right;
        } else {
            if (i8 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i9 = rect2.top;
            i10 = rect.bottom;
        }
        return i9 - i10;
    }

    public static int m(int i8, Rect rect, Rect rect2) {
        return Math.max(1, n(i8, rect, rect2));
    }

    public static int n(int i8, Rect rect, Rect rect2) {
        int i9;
        int i10;
        if (i8 == 17) {
            i9 = rect.left;
            i10 = rect2.left;
        } else if (i8 == 33) {
            i9 = rect.top;
            i10 = rect2.top;
        } else if (i8 == 66) {
            i9 = rect2.right;
            i10 = rect.right;
        } else {
            if (i8 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i9 = rect2.bottom;
            i10 = rect.bottom;
        }
        return i9 - i10;
    }

    public static int o(int i8, Rect rect, Rect rect2) {
        if (i8 != 17) {
            if (i8 != 33) {
                if (i8 != 66) {
                    if (i8 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs((rect.left + (rect.width() / 2)) - (rect2.left + (rect2.width() / 2)));
        }
        return Math.abs((rect.top + (rect.height() / 2)) - (rect2.top + (rect2.height() / 2)));
    }
}
