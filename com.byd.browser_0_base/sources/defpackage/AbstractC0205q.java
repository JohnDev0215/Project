package defpackage;

import java.util.AbstractList;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/webapk8.dex
  classes.dex
 */
/* JADX INFO: renamed from: q, reason: case insensitive filesystem */
/* JADX INFO: compiled from: AbstractMutableList.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0205q<E> extends AbstractList<E> implements List<E> {
    @Override // java.util.AbstractList, java.util.List
    public final E remove(int i) {
        j8 j8Var = (j8) this;
        int i2 = j8Var.g;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
        }
        if (i == z7.w(j8Var)) {
            if (j8Var.isEmpty()) {
                throw new NoSuchElementException("ArrayDeque is empty.");
            }
            int iD = j8Var.d(z7.w(j8Var) + j8Var.e);
            Object[] objArr = j8Var.f;
            E e = (E) objArr[iD];
            objArr[iD] = null;
            j8Var.g--;
            return e;
        }
        if (i == 0) {
            if (j8Var.isEmpty()) {
                throw new NoSuchElementException("ArrayDeque is empty.");
            }
            Object[] objArr2 = j8Var.f;
            int i3 = j8Var.e;
            E e2 = (E) objArr2[i3];
            objArr2[i3] = null;
            j8Var.e = j8Var.c(i3);
            j8Var.g--;
            return e2;
        }
        int iD2 = j8Var.d(j8Var.e + i);
        Object[] objArr3 = j8Var.f;
        E e3 = (E) objArr3[iD2];
        if (i < (j8Var.g >> 1)) {
            int i4 = j8Var.e;
            if (iD2 >= i4) {
                r8.q0(i4 + 1, i4, iD2, objArr3, objArr3);
            } else {
                r8.q0(1, 0, iD2, objArr3, objArr3);
                Object[] objArr4 = j8Var.f;
                objArr4[0] = objArr4[objArr4.length - 1];
                int i5 = j8Var.e;
                r8.q0(i5 + 1, i5, objArr4.length - 1, objArr4, objArr4);
            }
            Object[] objArr5 = j8Var.f;
            int i6 = j8Var.e;
            objArr5[i6] = null;
            j8Var.e = j8Var.c(i6);
        } else {
            int iD3 = j8Var.d(z7.w(j8Var) + j8Var.e);
            if (iD2 <= iD3) {
                Object[] objArr6 = j8Var.f;
                r8.q0(iD2, iD2 + 1, iD3 + 1, objArr6, objArr6);
            } else {
                Object[] objArr7 = j8Var.f;
                r8.q0(iD2, iD2 + 1, objArr7.length, objArr7, objArr7);
                Object[] objArr8 = j8Var.f;
                objArr8[objArr8.length - 1] = objArr8[0];
                r8.q0(0, 1, iD3 + 1, objArr8, objArr8);
            }
            j8Var.f[iD3] = null;
        }
        j8Var.g--;
        return e3;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return ((j8) this).g;
    }
}
