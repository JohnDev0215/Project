package m0;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: compiled from: FocusStrategy.java */
/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: compiled from: FocusStrategy.java */
    public interface a<T> {
        void a(T t6, Rect rect);
    }

    /* JADX INFO: renamed from: m0.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FocusStrategy.java */
    public interface InterfaceC0121b<T, V> {
        int a(T t6);

        V b(T t6, int i7);
    }

    /* JADX INFO: compiled from: FocusStrategy.java */
    private static class c<T> implements Comparator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Rect f8039a = new Rect();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Rect f8040b = new Rect();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f8041c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final a<T> f8042d;

        c(boolean z6, a<T> aVar) {
            this.f8041c = z6;
            this.f8042d = aVar;
        }

        @Override // java.util.Comparator
        public int compare(T t6, T t7) {
            Rect rect = this.f8039a;
            Rect rect2 = this.f8040b;
            this.f8042d.a(t6, rect);
            this.f8042d.a(t7, rect2);
            int i7 = rect.top;
            int i8 = rect2.top;
            if (i7 < i8) {
                return -1;
            }
            if (i7 > i8) {
                return 1;
            }
            int i9 = rect.left;
            int i10 = rect2.left;
            if (i9 < i10) {
                return this.f8041c ? 1 : -1;
            }
            if (i9 > i10) {
                return this.f8041c ? -1 : 1;
            }
            int i11 = rect.bottom;
            int i12 = rect2.bottom;
            if (i11 < i12) {
                return -1;
            }
            if (i11 > i12) {
                return 1;
            }
            int i13 = rect.right;
            int i14 = rect2.right;
            if (i13 < i14) {
                return this.f8041c ? 1 : -1;
            }
            if (i13 > i14) {
                return this.f8041c ? -1 : 1;
            }
            return 0;
        }
    }

    private static boolean a(int i7, Rect rect, Rect rect2, Rect rect3) {
        boolean zB = b(i7, rect, rect2);
        if (b(i7, rect, rect3) || !zB) {
            return false;
        }
        return !j(i7, rect, rect3) || i7 == 17 || i7 == 66 || k(i7, rect, rect2) < m(i7, rect, rect3);
    }

    private static boolean b(int i7, Rect rect, Rect rect2) {
        if (i7 != 17) {
            if (i7 != 33) {
                if (i7 != 66) {
                    if (i7 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static <L, T> T c(L l7, InterfaceC0121b<L, T> interfaceC0121b, a<T> aVar, T t6, Rect rect, int i7) {
        Rect rect2 = new Rect(rect);
        if (i7 == 17) {
            rect2.offset(rect.width() + 1, 0);
        } else if (i7 == 33) {
            rect2.offset(0, rect.height() + 1);
        } else if (i7 == 66) {
            rect2.offset(-(rect.width() + 1), 0);
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect2.offset(0, -(rect.height() + 1));
        }
        T t7 = null;
        int iA = interfaceC0121b.a(l7);
        Rect rect3 = new Rect();
        for (int i8 = 0; i8 < iA; i8++) {
            T tB = interfaceC0121b.b(l7, i8);
            if (tB != t6) {
                aVar.a(tB, rect3);
                if (h(i7, rect, rect3, rect2)) {
                    rect2.set(rect3);
                    t7 = tB;
                }
            }
        }
        return t7;
    }

    public static <L, T> T d(L l7, InterfaceC0121b<L, T> interfaceC0121b, a<T> aVar, T t6, int i7, boolean z6, boolean z7) {
        int iA = interfaceC0121b.a(l7);
        ArrayList arrayList = new ArrayList(iA);
        for (int i8 = 0; i8 < iA; i8++) {
            arrayList.add(interfaceC0121b.b(l7, i8));
        }
        Collections.sort(arrayList, new c(z6, aVar));
        if (i7 == 1) {
            return (T) f(t6, arrayList, z7);
        }
        if (i7 == 2) {
            return (T) e(t6, arrayList, z7);
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD}.");
    }

    private static <T> T e(T t6, ArrayList<T> arrayList, boolean z6) {
        int size = arrayList.size();
        int iLastIndexOf = (t6 == null ? -1 : arrayList.lastIndexOf(t6)) + 1;
        if (iLastIndexOf < size) {
            return arrayList.get(iLastIndexOf);
        }
        if (!z6 || size <= 0) {
            return null;
        }
        return arrayList.get(0);
    }

    private static <T> T f(T t6, ArrayList<T> arrayList, boolean z6) {
        int size = arrayList.size();
        int iIndexOf = (t6 == null ? size : arrayList.indexOf(t6)) - 1;
        if (iIndexOf >= 0) {
            return arrayList.get(iIndexOf);
        }
        if (!z6 || size <= 0) {
            return null;
        }
        return arrayList.get(size - 1);
    }

    private static int g(int i7, int i8) {
        return (i7 * 13 * i7) + (i8 * i8);
    }

    private static boolean h(int i7, Rect rect, Rect rect2, Rect rect3) {
        if (!i(rect, rect2, i7)) {
            return false;
        }
        if (i(rect, rect3, i7) && !a(i7, rect, rect2, rect3)) {
            return !a(i7, rect, rect3, rect2) && g(k(i7, rect, rect2), o(i7, rect, rect2)) < g(k(i7, rect, rect3), o(i7, rect, rect3));
        }
        return true;
    }

    private static boolean i(Rect rect, Rect rect2, int i7) {
        if (i7 == 17) {
            int i8 = rect.right;
            int i9 = rect2.right;
            return (i8 > i9 || rect.left >= i9) && rect.left > rect2.left;
        }
        if (i7 == 33) {
            int i10 = rect.bottom;
            int i11 = rect2.bottom;
            return (i10 > i11 || rect.top >= i11) && rect.top > rect2.top;
        }
        if (i7 == 66) {
            int i12 = rect.left;
            int i13 = rect2.left;
            return (i12 < i13 || rect.right <= i13) && rect.right < rect2.right;
        }
        if (i7 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i14 = rect.top;
        int i15 = rect2.top;
        return (i14 < i15 || rect.bottom <= i15) && rect.bottom < rect2.bottom;
    }

    private static boolean j(int i7, Rect rect, Rect rect2) {
        if (i7 == 17) {
            return rect.left >= rect2.right;
        }
        if (i7 == 33) {
            return rect.top >= rect2.bottom;
        }
        if (i7 == 66) {
            return rect.right <= rect2.left;
        }
        if (i7 == 130) {
            return rect.bottom <= rect2.top;
        }
        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
    }

    private static int k(int i7, Rect rect, Rect rect2) {
        return Math.max(0, l(i7, rect, rect2));
    }

    private static int l(int i7, Rect rect, Rect rect2) {
        int i8;
        int i9;
        if (i7 == 17) {
            i8 = rect.left;
            i9 = rect2.right;
        } else if (i7 == 33) {
            i8 = rect.top;
            i9 = rect2.bottom;
        } else if (i7 == 66) {
            i8 = rect2.left;
            i9 = rect.right;
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i8 = rect2.top;
            i9 = rect.bottom;
        }
        return i8 - i9;
    }

    private static int m(int i7, Rect rect, Rect rect2) {
        return Math.max(1, n(i7, rect, rect2));
    }

    private static int n(int i7, Rect rect, Rect rect2) {
        int i8;
        int i9;
        if (i7 == 17) {
            i8 = rect.left;
            i9 = rect2.left;
        } else if (i7 == 33) {
            i8 = rect.top;
            i9 = rect2.top;
        } else if (i7 == 66) {
            i8 = rect2.right;
            i9 = rect.right;
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i8 = rect2.bottom;
            i9 = rect.bottom;
        }
        return i8 - i9;
    }

    private static int o(int i7, Rect rect, Rect rect2) {
        if (i7 != 17) {
            if (i7 != 33) {
                if (i7 != 66) {
                    if (i7 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs((rect.left + (rect.width() / 2)) - (rect2.left + (rect2.width() / 2)));
        }
        return Math.abs((rect.top + (rect.height() / 2)) - (rect2.top + (rect2.height() / 2)));
    }
}
