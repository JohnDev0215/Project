package c5;

import B5.u;
import N1.C0420a;
import P8.n;
import java.util.Iterator;
import java.util.NoSuchElementException;
import x.AbstractC2641j;

/* JADX INFO: renamed from: c5.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1030g implements Iterator {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f12399m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final CharSequence f12400n;
    public final C1026c o;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f12402q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C0420a f12403r;
    public int l = 2;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f12401p = 0;

    public C1030g(C0420a c0420a, u uVar, CharSequence charSequence) {
        this.f12403r = c0420a;
        this.o = (C1026c) uVar.f736n;
        this.f12402q = uVar.f735m;
        this.f12400n = charSequence;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        String string;
        int i7 = this.l;
        if (i7 == 4) {
            throw new IllegalStateException();
        }
        int iC = AbstractC2641j.c(i7);
        if (iC == 0) {
            return true;
        }
        if (iC == 2) {
            return false;
        }
        this.l = 4;
        int i10 = this.f12401p;
        while (true) {
            int length = this.f12401p;
            if (length == -1) {
                this.l = 3;
                string = null;
                break;
            }
            C1025b c1025b = (C1025b) this.f12403r.l;
            CharSequence charSequence = this.f12400n;
            int length2 = charSequence.length();
            n.r(length, length2);
            while (true) {
                if (length >= length2) {
                    length = -1;
                    break;
                }
                if (c1025b.a(charSequence.charAt(length))) {
                    break;
                }
                length++;
            }
            if (length == -1) {
                length = charSequence.length();
                this.f12401p = -1;
            } else {
                this.f12401p = length + 1;
            }
            int i11 = this.f12401p;
            if (i11 == i10) {
                int i12 = i11 + 1;
                this.f12401p = i12;
                if (i12 > charSequence.length()) {
                    this.f12401p = -1;
                }
            } else {
                C1026c c1026c = this.o;
                if (i10 < length) {
                    charSequence.charAt(i10);
                    c1026c.getClass();
                }
                if (length > i10) {
                    charSequence.charAt(length - 1);
                    c1026c.getClass();
                }
                int i13 = this.f12402q;
                if (i13 == 1) {
                    length = charSequence.length();
                    this.f12401p = -1;
                    if (length > i10) {
                        charSequence.charAt(length - 1);
                        c1026c.getClass();
                    }
                } else {
                    this.f12402q = i13 - 1;
                }
                string = charSequence.subSequence(i10, length).toString();
            }
        }
        this.f12399m = string;
        if (this.l == 3) {
            return false;
        }
        this.l = 1;
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.l = 2;
        String str = this.f12399m;
        this.f12399m = null;
        return str;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
