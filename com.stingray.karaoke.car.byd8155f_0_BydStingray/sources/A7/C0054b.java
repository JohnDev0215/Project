package A7;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.NoSuchElementException;
import n7.AbstractC1989d;
import u.C2459G;

/* JADX INFO: renamed from: A7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0054b implements Iterator, B7.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f453m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f454n;

    public /* synthetic */ C0054b(Object obj, int i7) {
        this.l = i7;
        this.f454n = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.l) {
            case 0:
                if (this.f453m < ((Object[]) this.f454n).length) {
                }
                break;
            case 1:
                if (this.f453m < ((ViewGroup) this.f454n).getChildCount()) {
                }
                break;
            case 2:
                if (this.f453m < ((AbstractC1989d) this.f454n).d()) {
                }
                break;
            default:
                if (this.f453m < ((C2459G) this.f454n).f()) {
                }
                break;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.l) {
            case 0:
                try {
                    Object[] objArr = (Object[]) this.f454n;
                    int i7 = this.f453m;
                    this.f453m = i7 + 1;
                    return objArr[i7];
                } catch (ArrayIndexOutOfBoundsException e10) {
                    this.f453m--;
                    throw new NoSuchElementException(e10.getMessage());
                }
            case 1:
                int i10 = this.f453m;
                this.f453m = i10 + 1;
                View childAt = ((ViewGroup) this.f454n).getChildAt(i10);
                if (childAt != null) {
                    return childAt;
                }
                throw new IndexOutOfBoundsException();
            case 2:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i11 = this.f453m;
                this.f453m = i11 + 1;
                return ((AbstractC1989d) this.f454n).get(i11);
            default:
                int i12 = this.f453m;
                this.f453m = i12 + 1;
                return ((C2459G) this.f454n).g(i12);
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.l) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                int i7 = this.f453m - 1;
                this.f453m = i7;
                ((ViewGroup) this.f454n).removeViewAt(i7);
                return;
            case 2:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public C0054b(Object[] objArr) {
        this.l = 0;
        m.f(objArr, "array");
        this.f454n = objArr;
    }
}
