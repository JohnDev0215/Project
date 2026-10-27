package io.reactivex.rxjava3.internal.functions;

import java.util.Comparator;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
final class Functions$NaturalComparator implements Comparator<Object> {
    public static final Functions$NaturalComparator c;
    public static final /* synthetic */ Functions$NaturalComparator[] e;

    static {
        Functions$NaturalComparator functions$NaturalComparator = new Functions$NaturalComparator("INSTANCE", 0);
        c = functions$NaturalComparator;
        e = new Functions$NaturalComparator[]{functions$NaturalComparator};
    }

    public static Functions$NaturalComparator valueOf(String str) {
        return (Functions$NaturalComparator) Enum.valueOf(Functions$NaturalComparator.class, str);
    }

    public static Functions$NaturalComparator[] values() {
        return (Functions$NaturalComparator[]) e.clone();
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ((Comparable) obj).compareTo(obj2);
    }
}
