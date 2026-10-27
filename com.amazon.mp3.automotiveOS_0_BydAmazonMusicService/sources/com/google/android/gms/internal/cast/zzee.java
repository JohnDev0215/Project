package com.google.android.gms.internal.cast;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzee extends zzea implements List, RandomAccess {
    private static final zzev zza = new zzec(zzel.zza, 0);

    zzee() {
    }

    public static zzeb zzi() {
        return new zzeb(4);
    }

    static zzee zzj(Object[] objArr) {
        return zzk(objArr, objArr.length);
    }

    static zzee zzk(Object[] objArr, int i) {
        return i == 0 ? zzel.zza : new zzel(objArr, i);
    }

    public static zzee zzl(Collection collection) {
        if (!(collection instanceof zzea)) {
            Object[] array = collection.toArray();
            int length = array.length;
            zzek.zzb(array, length);
            return zzk(array, length);
        }
        zzee zzeeVarZzd = ((zzea) collection).zzd();
        if (!zzeeVarZzd.zzf()) {
            return zzeeVarZzd;
        }
        Object[] array2 = zzeeVarZzd.toArray();
        return zzk(array2, array2.length);
    }

    public static zzee zzm(Object obj) {
        Object[] objArr = {obj};
        zzek.zzb(objArr, 1);
        return zzk(objArr, 1);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int i = 0; i < size; i++) {
                        if (zzdm.zza(get(i), list.get(i))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!it2.hasNext() || !zzdm.zza(it.next(), it2.next())) {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int iHashCode = 1;
        for (int i = 0; i < size; i++) {
            iHashCode = (iHashCode * 31) + get(i).hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.cast.zzea, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.cast.zzea
    int zza(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i2] = get(i2);
        }
        return size;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    @Deprecated
    public final zzee zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.cast.zzea
    /* JADX INFO: renamed from: zze */
    public final zzeu iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: zzh, reason: merged with bridge method [inline-methods] */
    public zzee subList(int i, int i2) {
        zzdr.zzd(i, i2, size());
        int i3 = i2 - i;
        return i3 == size() ? this : i3 == 0 ? zzel.zza : new zzed(this, i, i3);
    }

    @Override // java.util.List
    /* JADX INFO: renamed from: zzn, reason: merged with bridge method [inline-methods] */
    public final zzev listIterator(int i) {
        zzdr.zzb(i, size(), "index");
        return isEmpty() ? zza : new zzec(this, i);
    }
}
