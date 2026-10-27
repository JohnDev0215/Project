package com.google.gson.internal;

import android.Manifest;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Set;
import p.p00;
import p.ta1;

/* JADX INFO: loaded from: classes.dex */
public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final p00 c = new p00(2);
    Comparator<? super K> comparator;
    private b entrySet;
    final ta1 header;
    private c keySet;
    int modCount;
    ta1 root;
    int size;

    public LinkedTreeMap() {
        p00 p00Var = c;
        this.size = 0;
        this.modCount = 0;
        this.header = new ta1();
        this.comparator = p00Var;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization is unsupported");
    }

    private Object writeReplace() {
        return new LinkedHashMap(this);
    }

    public final ta1 a(Object obj, boolean z) {
        int iCompareTo;
        ta1 ta1Var;
        Comparator<? super K> comparator = this.comparator;
        ta1 ta1Var2 = this.root;
        p00 p00Var = c;
        if (ta1Var2 != null) {
            Comparable comparable = comparator == p00Var ? (Comparable) obj : null;
            while (true) {
                Manifest manifest = (Object) ta1Var2.n;
                iCompareTo = comparable != null ? comparable.compareTo(manifest) : comparator.compare(obj, manifest);
                if (iCompareTo == 0) {
                    return ta1Var2;
                }
                ta1 ta1Var3 = iCompareTo < 0 ? ta1Var2.e : ta1Var2.k;
                if (ta1Var3 == null) {
                    break;
                }
                ta1Var2 = ta1Var3;
            }
        } else {
            iCompareTo = 0;
        }
        if (!z) {
            return null;
        }
        ta1 ta1Var4 = this.header;
        if (ta1Var2 != null) {
            ta1Var = new ta1(ta1Var2, obj, ta1Var4, ta1Var4.m);
            if (iCompareTo < 0) {
                ta1Var2.e = ta1Var;
            } else {
                ta1Var2.k = ta1Var;
            }
            b(ta1Var2, true);
        } else {
            if (comparator == p00Var && !(obj instanceof Comparable)) {
                throw new ClassCastException(obj.getClass().getName().concat(" is not Comparable"));
            }
            ta1Var = new ta1(ta1Var2, obj, ta1Var4, ta1Var4.m);
            this.root = ta1Var;
        }
        this.size++;
        this.modCount++;
        return ta1Var;
    }

    public final void b(ta1 ta1Var, boolean z) {
        while (ta1Var != null) {
            ta1 ta1Var2 = ta1Var.e;
            ta1 ta1Var3 = ta1Var.k;
            int i = ta1Var2 != null ? ta1Var2.f188p : 0;
            int i2 = ta1Var3 != null ? ta1Var3.f188p : 0;
            int i3 = i - i2;
            if (i3 == -2) {
                ta1 ta1Var4 = ta1Var3.e;
                ta1 ta1Var5 = ta1Var3.k;
                int i4 = (ta1Var4 != null ? ta1Var4.f188p : 0) - (ta1Var5 != null ? ta1Var5.f188p : 0);
                if (i4 == -1 || (i4 == 0 && !z)) {
                    e(ta1Var);
                } else {
                    f(ta1Var3);
                    e(ta1Var);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 2) {
                ta1 ta1Var6 = ta1Var2.e;
                ta1 ta1Var7 = ta1Var2.k;
                int i5 = (ta1Var6 != null ? ta1Var6.f188p : 0) - (ta1Var7 != null ? ta1Var7.f188p : 0);
                if (i5 == 1 || (i5 == 0 && !z)) {
                    f(ta1Var);
                } else {
                    e(ta1Var2);
                    f(ta1Var);
                }
                if (z) {
                    return;
                }
            } else if (i3 == 0) {
                ta1Var.f188p = i + 1;
                if (z) {
                    return;
                }
            } else {
                ta1Var.f188p = Math.max(i, i2) + 1;
                if (!z) {
                    return;
                }
            }
            ta1Var = ta1Var.c;
        }
    }

    public final void c(ta1 ta1Var, boolean z) {
        ta1 ta1Var2;
        ta1 ta1Var3;
        int i;
        if (z) {
            ta1 ta1Var4 = ta1Var.m;
            ta1Var4.l = ta1Var.l;
            ta1Var.l.m = ta1Var4;
        }
        ta1 ta1Var5 = ta1Var.e;
        ta1 ta1Var6 = ta1Var.k;
        ta1 ta1Var7 = ta1Var.c;
        int i2 = 0;
        if (ta1Var5 == null || ta1Var6 == null) {
            if (ta1Var5 != null) {
                d(ta1Var, ta1Var5);
                ta1Var.e = null;
            } else if (ta1Var6 != null) {
                d(ta1Var, ta1Var6);
                ta1Var.k = null;
            } else {
                d(ta1Var, null);
            }
            b(ta1Var7, false);
            this.size--;
            this.modCount++;
            return;
        }
        if (ta1Var5.f188p > ta1Var6.f188p) {
            ta1 ta1Var8 = ta1Var5.k;
            while (true) {
                ta1 ta1Var9 = ta1Var8;
                ta1Var3 = ta1Var5;
                ta1Var5 = ta1Var9;
                if (ta1Var5 == null) {
                    break;
                } else {
                    ta1Var8 = ta1Var5.k;
                }
            }
        } else {
            ta1 ta1Var10 = ta1Var6.e;
            while (true) {
                ta1Var2 = ta1Var6;
                ta1Var6 = ta1Var10;
                if (ta1Var6 == null) {
                    break;
                } else {
                    ta1Var10 = ta1Var6.e;
                }
            }
            ta1Var3 = ta1Var2;
        }
        c(ta1Var3, false);
        ta1 ta1Var11 = ta1Var.e;
        if (ta1Var11 != null) {
            i = ta1Var11.f188p;
            ta1Var3.e = ta1Var11;
            ta1Var11.c = ta1Var3;
            ta1Var.e = null;
        } else {
            i = 0;
        }
        ta1 ta1Var12 = ta1Var.k;
        if (ta1Var12 != null) {
            i2 = ta1Var12.f188p;
            ta1Var3.k = ta1Var12;
            ta1Var12.c = ta1Var3;
            ta1Var.k = null;
        }
        ta1Var3.f188p = Math.max(i, i2) + 1;
        d(ta1Var, ta1Var3);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        ta1 ta1Var = this.header;
        ta1Var.m = ta1Var;
        ta1Var.l = ta1Var;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        ta1 ta1VarA = null;
        if (obj != null) {
            try {
                ta1VarA = a(obj, false);
            } catch (ClassCastException unused) {
            }
        }
        return ta1VarA != null;
    }

    public final void d(ta1 ta1Var, ta1 ta1Var2) {
        ta1 ta1Var3 = ta1Var.c;
        ta1Var.c = null;
        if (ta1Var2 != null) {
            ta1Var2.c = ta1Var3;
        }
        if (ta1Var3 == null) {
            this.root = ta1Var2;
        } else if (ta1Var3.e == ta1Var) {
            ta1Var3.e = ta1Var2;
        } else {
            ta1Var3.k = ta1Var2;
        }
    }

    public final void e(ta1 ta1Var) {
        ta1 ta1Var2 = ta1Var.e;
        ta1 ta1Var3 = ta1Var.k;
        ta1 ta1Var4 = ta1Var3.e;
        ta1 ta1Var5 = ta1Var3.k;
        ta1Var.k = ta1Var4;
        if (ta1Var4 != null) {
            ta1Var4.c = ta1Var;
        }
        d(ta1Var, ta1Var3);
        ta1Var3.e = ta1Var;
        ta1Var.c = ta1Var3;
        int iMax = Math.max(ta1Var2 != null ? ta1Var2.f188p : 0, ta1Var4 != null ? ta1Var4.f188p : 0) + 1;
        ta1Var.f188p = iMax;
        ta1Var3.f188p = Math.max(iMax, ta1Var5 != null ? ta1Var5.f188p : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        b bVar = this.entrySet;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b(this);
        this.entrySet = bVar2;
        return bVar2;
    }

    public final void f(ta1 ta1Var) {
        ta1 ta1Var2 = ta1Var.e;
        ta1 ta1Var3 = ta1Var.k;
        ta1 ta1Var4 = ta1Var2.e;
        ta1 ta1Var5 = ta1Var2.k;
        ta1Var.e = ta1Var5;
        if (ta1Var5 != null) {
            ta1Var5.c = ta1Var;
        }
        d(ta1Var, ta1Var2);
        ta1Var2.k = ta1Var;
        ta1Var.c = ta1Var2;
        int iMax = Math.max(ta1Var3 != null ? ta1Var3.f188p : 0, ta1Var5 != null ? ta1Var5.f188p : 0) + 1;
        ta1Var.f188p = iMax;
        ta1Var2.f188p = Math.max(iMax, ta1Var4 != null ? ta1Var4.f188p : 0) + 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        ta1 ta1VarA;
        if (obj != null) {
            try {
                ta1VarA = a(obj, false);
            } catch (ClassCastException unused) {
                ta1VarA = null;
            }
        } else {
            ta1VarA = null;
        }
        if (ta1VarA != null) {
            return ta1VarA.o;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        c cVar = this.keySet;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(this);
        this.keySet = cVar2;
        return cVar2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("key == null");
        }
        ta1 ta1VarA = a(obj, true);
        Object obj3 = ta1VarA.o;
        ta1VarA.o = obj2;
        return obj3;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        ta1 ta1VarA;
        if (obj != null) {
            try {
                ta1VarA = a(obj, false);
            } catch (ClassCastException unused) {
                ta1VarA = null;
            }
        } else {
            ta1VarA = null;
        }
        if (ta1VarA != null) {
            c(ta1VarA, true);
        }
        if (ta1VarA != null) {
            return ta1VarA.o;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.size;
    }
}
