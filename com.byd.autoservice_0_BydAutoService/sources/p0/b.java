package p0;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: compiled from: FocusStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: compiled from: FocusStrategy.java */
    public interface a<T> {
        void a(T t3, Rect rect);
    }

    /* JADX INFO: renamed from: p0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FocusStrategy.java */
    public interface InterfaceC0048b<T, V> {
        int a(T t3);

        V b(T t3, int i3);
    }

    /* JADX INFO: compiled from: FocusStrategy.java */
    public static class c<T> implements Comparator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f4483a = new Rect();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Rect f4484b = new Rect();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final boolean f4485c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a<T> f4486d;

        public c(boolean z3, a<T> aVar) {
            this.f4485c = z3;
            this.f4486d = aVar;
        }

        @Override // java.util.Comparator
        public int compare(T t3, T t4) {
            Rect rect = this.f4483a;
            Rect rect2 = this.f4484b;
            this.f4486d.a(t3, rect);
            this.f4486d.a(t4, rect2);
            int i3 = rect.top;
            int i4 = rect2.top;
            if (i3 < i4) {
                return -1;
            }
            if (i3 > i4) {
                return 1;
            }
            int i5 = rect.left;
            int i6 = rect2.left;
            if (i5 < i6) {
                return this.f4485c ? 1 : -1;
            }
            if (i5 > i6) {
                return this.f4485c ? -1 : 1;
            }
            int i7 = rect.bottom;
            int i8 = rect2.bottom;
            if (i7 < i8) {
                return -1;
            }
            if (i7 > i8) {
                return 1;
            }
            int i9 = rect.right;
            int i10 = rect2.right;
            if (i9 < i10) {
                return this.f4485c ? 1 : -1;
            }
            if (i9 > i10) {
                return this.f4485c ? -1 : 1;
            }
            return 0;
        }
    }

    public static boolean a(int i3, Rect rect, Rect rect2, Rect rect3) {
        boolean zB = b(i3, rect, rect2);
        if (b(i3, rect, rect3) || !zB) {
            return false;
        }
        return !j(i3, rect, rect3) || i3 == 17 || i3 == 66 || k(i3, rect, rect2) < m(i3, rect, rect3);
    }

    public static boolean b(int i3, Rect rect, Rect rect2) {
        if (i3 != 17) {
            if (i3 != 33) {
                if (i3 != 66) {
                    if (i3 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static <L, T> T c(L l3, InterfaceC0048b<L, T> interfaceC0048b, a<T> aVar, T t3, Rect rect, int i3) {
        Rect rect2 = new Rect(rect);
        if (i3 == 17) {
            rect2.offset(rect.width() + 1, 0);
        } else if (i3 == 33) {
            rect2.offset(0, rect.height() + 1);
        } else if (i3 == 66) {
            rect2.offset(-(rect.width() + 1), 0);
        } else {
            if (i3 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect2.offset(0, -(rect.height() + 1));
        }
        T t4 = null;
        int iA = interfaceC0048b.a(l3);
        Rect rect3 = new Rect();
        for (int i4 = 0; i4 < iA; i4++) {
            T tB = interfaceC0048b.b(l3, i4);
            if (tB != t3) {
                aVar.a(tB, rect3);
                if (h(i3, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    t4 = tB;
                }
            }
        }
        return t4;
    }

    public static <L, T> T d(L l3, InterfaceC0048b<L, T> interfaceC0048b, a<T> aVar, T t3, int i3, boolean z3, boolean z4) {
        int iA = interfaceC0048b.a(l3);
        ArrayList arrayList = new ArrayList(iA);
        for (int i4 = 0; i4 < iA; i4++) {
            arrayList.add(interfaceC0048b.b(l3, i4));
        }
        Collections.sort(arrayList, new c(z3, aVar));
        if (i3 == 1) {
            return (T) f(t3, arrayList, z4);
        }
        if (i3 == 2) {
            return (T) e(t3, arrayList, z4);
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    }

    public static <T> T e(T t3, ArrayList<T> arrayList, boolean z3) {
        int size = arrayList.size();
        int iLastIndexOf = (t3 == null ? -1 : arrayList.lastIndexOf(t3)) + 1;
        if (iLastIndexOf < size) {
            return arrayList.get(iLastIndexOf);
        }
        if (!z3 || size <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    public static <T> T f(T t3, ArrayList<T> arrayList, boolean z3) {
        int size = arrayList.size();
        int iIndexOf = (t3 == null ? size : arrayList.indexOf(t3)) - 1;
        if (iIndexOf >= 0) {
            return arrayList.get(iIndexOf);
        }
        if (!z3 || size <= 0) {
            return null;
        }
        return arrayList.get(size - 1);
    }

    public static int g(int i3, int i4) {
        return (i3 * 13 * i3) + (i4 * i4);
    }

    public static boolean h(int i3, Rect rect, Rect rect2, Rect rect3) {
        if (!i(rect, rect2, i3)) {
            return false;
        }
        if (i(rect, rect3, i3) && !a(i3, rect, rect2, rect3)) {
            return !a(i3, rect, rect3, rect2) && g(k(i3, rect, rect2), o(i3, rect, rect2)) < g(k(i3, rect, rect3), o(i3, rect, rect3));
        }
        return true;
    }

    public static boolean i(Rect rect, Rect rect2, int i3) {
        if (i3 == 17) {
            int i4 = rect.right;
            int i5 = rect2.right;
            return (i4 > i5 || rect.left >= i5) && rect.left > rect2.left;
        }
        if (i3 == 33) {
            int i6 = rect.bottom;
            int i7 = rect2.bottom;
            return (i6 > i7 || rect.top >= i7) && rect.top > rect2.top;
        }
        if (i3 == 66) {
            int i8 = rect.left;
            int i9 = rect2.left;
            return (i8 < i9 || rect.right <= i9) && rect.right < rect2.right;
        }
        if (i3 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i10 = rect.top;
        int i11 = rect2.top;
        return (i10 < i11 || rect.bottom <= i11) && rect.bottom < rect2.bottom;
    }

    public static boolean j(int i3, Rect rect, Rect rect2) {
        if (i3 == 17) {
            return rect.left >= rect2.right;
        }
        if (i3 == 33) {
            return rect.top >= rect2.bottom;
        }
        if (i3 == 66) {
            return rect.right <= rect2.left;
        }
        if (i3 == 130) {
            return rect.bottom <= rect2.top;
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
    }

    public static int k(int i3, Rect rect, Rect rect2) {
        return Math.max(0, l(i3, rect, rect2));
    }

    public static int l(int i3, Rect rect, Rect rect2) {
        int i4;
        int i5;
        if (i3 == 17) {
            i4 = rect.left;
            i5 = rect2.right;
        } else if (i3 == 33) {
            i4 = rect.top;
            i5 = rect2.bottom;
        } else if (i3 == 66) {
            i4 = rect2.left;
            i5 = rect.right;
        } else {
            if (i3 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i4 = rect2.top;
            i5 = rect.bottom;
        }
        return i4 - i5;
    }

    public static int m(int i3, Rect rect, Rect rect2) {
        return Math.max(1, n(i3, rect, rect2));
    }

    public static int n(int i3, Rect rect, Rect rect2) {
        int i4;
        int i5;
        if (i3 == 17) {
            i4 = rect.left;
            i5 = rect2.left;
        } else if (i3 == 33) {
            i4 = rect.top;
            i5 = rect2.top;
        } else if (i3 == 66) {
            i4 = rect2.right;
            i5 = rect.right;
        } else {
            if (i3 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i4 = rect2.bottom;
            i5 = rect.bottom;
        }
        return i4 - i5;
    }

    public static int o(int i3, Rect rect, Rect rect2) {
        if (i3 != 17) {
            if (i3 != 33) {
                if (i3 != 66) {
                    if (i3 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs((rect.left + (rect.width() / 2)) - (rect2.left + (rect2.width() / 2)));
        }
        return Math.abs((rect.top + (rect.height() / 2)) - (rect2.top + (rect2.height() / 2)));
    }
}
