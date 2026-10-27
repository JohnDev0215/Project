package io.reactivex.rxjava3.disposables;

import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.internal.util.d;
import java.util.ArrayList;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Disposable, b {
    public d c;
    public volatile boolean e;

    public static void f(d dVar) {
        if (dVar == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Object obj : dVar.d) {
            if (obj instanceof Disposable) {
                try {
                    ((Disposable) obj).dispose();
                } catch (Throwable th) {
                    he1.R(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
            }
        }
        if (arrayList != null) {
            if (arrayList.size() != 1) {
                throw new CompositeException(arrayList);
            }
            throw io.reactivex.rxjava3.internal.util.c.f((Throwable) arrayList.get(0));
        }
    }

    @Override // io.reactivex.rxjava3.disposables.b
    public final boolean a(Disposable disposable) {
        if (!b(disposable)) {
            return false;
        }
        disposable.dispose();
        return true;
    }

    @Override // io.reactivex.rxjava3.disposables.b
    public final boolean b(Disposable disposable) {
        Object obj;
        Objects.requireNonNull(disposable, "disposable is null");
        if (this.e) {
            return false;
        }
        synchronized (this) {
            try {
                if (this.e) {
                    return false;
                }
                d dVar = this.c;
                if (dVar != null) {
                    Object[] objArr = dVar.d;
                    int i = dVar.a;
                    int iHashCode = disposable.hashCode() * (-1640531527);
                    int i2 = (iHashCode ^ (iHashCode >>> 16)) & i;
                    Object obj2 = objArr[i2];
                    if (obj2 != null) {
                        if (obj2.equals(disposable)) {
                            dVar.b(i2, i, objArr);
                        } else {
                            do {
                                i2 = (i2 + 1) & i;
                                obj = objArr[i2];
                                if (obj == null) {
                                }
                            } while (!obj.equals(disposable));
                            dVar.b(i2, i, objArr);
                        }
                        return true;
                    }
                }
                return false;
            } finally {
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.b
    public final boolean c(Disposable disposable) {
        Objects.requireNonNull(disposable, "disposable is null");
        if (!this.e) {
            synchronized (this) {
                try {
                    if (!this.e) {
                        d dVar = this.c;
                        if (dVar == null) {
                            dVar = new d();
                            this.c = dVar;
                        }
                        dVar.a(disposable);
                        return true;
                    }
                } finally {
                }
            }
        }
        disposable.dispose();
        return false;
    }

    public final void d() {
        if (this.e) {
            return;
        }
        synchronized (this) {
            try {
                if (this.e) {
                    return;
                }
                d dVar = this.c;
                this.c = null;
                f(dVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.e) {
            return;
        }
        synchronized (this) {
            try {
                if (this.e) {
                    return;
                }
                this.e = true;
                d dVar = this.c;
                this.c = null;
                f(dVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final int g() {
        if (this.e) {
            return 0;
        }
        synchronized (this) {
            try {
                if (this.e) {
                    return 0;
                }
                d dVar = this.c;
                return dVar != null ? dVar.b : 0;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e;
    }
}
