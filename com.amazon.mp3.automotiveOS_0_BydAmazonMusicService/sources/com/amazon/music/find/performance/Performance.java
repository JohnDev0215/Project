package com.amazon.music.find.performance;

import android.os.SystemClock;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: Performance.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 72\u00020\u0001:\u0003678B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH\u0002J\u0012\u0010\u001d\u001a\u00020\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0017J\u0012\u0010\u001f\u001a\u00020\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0017J\u0012\u0010 \u001a\u00020\u001b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0017J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u0017H\u0002J\u0010\u0010%\u001a\u00020\"2\u0006\u0010\u001c\u001a\u00020\rH\u0002J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0'J \u0010(\u001a\b\u0012\u0004\u0012\u00020\r0'2\u0006\u0010\u001e\u001a\u00020\u00172\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*J\u0014\u0010+\u001a\b\u0012\u0004\u0012\u00020\r0'2\u0006\u0010)\u001a\u00020*J\u0010\u0010,\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001e\u001a\u00020\u0017J\u0010\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u0010/\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u00100\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u0017J\u000e\u00101\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u0017J&\u00102\u001a\u00020.2\u0006\u0010\u001e\u001a\u00020\u00172\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0017J\b\u00105\u001a\u00020\u0006H\u0002R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\bR\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u00069"}, d2 = {"Lcom/amazon/music/find/performance/Performance;", "", "timestampSupplier", "Lcom/amazon/music/find/performance/TimestampSupplier;", "(Lcom/amazon/music/find/performance/TimestampSupplier;)V", "duration", "", "getDuration", "()J", "durationMilliseconds", "getDurationMilliseconds", "mEntries", "", "Lcom/amazon/music/find/performance/PerformanceEntry;", "mEntriesComparator", "Lcom/amazon/music/find/performance/Performance$PerformanceEntryComparator;", "mEntriesLock", "mInitEntryMark", "Lcom/amazon/music/find/performance/PerformanceMark;", "mTimestampSupplier", "startTime", "getStartTime", "units", "", "getUnits", "()Ljava/lang/String;", "addSorted", "", "entry", "clear", "name", "clearMarks", "clearMeasures", "equalsIgnoreCase", "", "valueL", "valueR", "exists", "getEntries", "", "getEntriesByName", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/find/performance/EntryType;", "getEntriesByType", "getMarkByName", "getMeasureByName", "Lcom/amazon/music/find/performance/PerformanceMeasure;", "hasMark", "hasMeasure", "mark", "measure", "startMark", "endMark", "now", "Builder", "Companion", "PerformanceEntryComparator", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Performance {
    private static final String PERFORMANCE_TRACKING_START = "PERFORMANCE_TRACKING_START";
    private final List<PerformanceEntry> mEntries;
    private final PerformanceEntryComparator mEntriesComparator;
    private final Object mEntriesLock;
    private final PerformanceMark mInitEntryMark;
    private final TimestampSupplier mTimestampSupplier;

    public /* synthetic */ Performance(TimestampSupplier timestampSupplier, DefaultConstructorMarker defaultConstructorMarker) {
        this(timestampSupplier);
    }

    private Performance(TimestampSupplier timestampSupplier) {
        this.mEntriesLock = new Object();
        this.mEntries = new ArrayList();
        this.mEntriesComparator = new PerformanceEntryComparator();
        this.mTimestampSupplier = timestampSupplier;
        this.mInitEntryMark = new PerformanceMark(PERFORMANCE_TRACKING_START, now());
    }

    public final long getStartTime() {
        return this.mInitEntryMark.getStartTime();
    }

    public final long getDuration() {
        return now() - this.mInitEntryMark.getStartTime();
    }

    public final String getUnits() {
        return this.mTimestampSupplier.getUnits();
    }

    public final long getDurationMilliseconds() {
        return this.mTimestampSupplier.toMilliseconds(now() - this.mInitEntryMark.getStartTime());
    }

    public static /* synthetic */ void clearMarks$default(Performance performance, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        performance.clearMarks(str);
    }

    public final void clearMarks(final String name) {
        synchronized (this.mEntriesLock) {
            if (name != null) {
                CollectionsKt__MutableCollectionsKt.removeAll((List) this.mEntries, (Function1) new Function1<PerformanceEntry, Boolean>() { // from class: com.amazon.music.find.performance.Performance$clearMarks$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(PerformanceEntry it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(it.getEntryType() == EntryType.MARK && this.this$0.equalsIgnoreCase(it.getName(), name));
                    }
                });
            } else {
                CollectionsKt__MutableCollectionsKt.removeAll((List) this.mEntries, (Function1) new Function1<PerformanceEntry, Boolean>() { // from class: com.amazon.music.find.performance.Performance$clearMarks$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(PerformanceEntry it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(it.getEntryType() == EntryType.MARK);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void clearMeasures$default(Performance performance, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        performance.clearMeasures(str);
    }

    public final void clearMeasures(final String name) {
        synchronized (this.mEntriesLock) {
            if (name != null) {
                CollectionsKt__MutableCollectionsKt.removeAll((List) this.mEntries, (Function1) new Function1<PerformanceEntry, Boolean>() { // from class: com.amazon.music.find.performance.Performance$clearMeasures$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(PerformanceEntry it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(it.getEntryType() == EntryType.MEASURE && this.this$0.equalsIgnoreCase(it.getName(), name));
                    }
                });
            } else {
                CollectionsKt__MutableCollectionsKt.removeAll((List) this.mEntries, (Function1) new Function1<PerformanceEntry, Boolean>() { // from class: com.amazon.music.find.performance.Performance$clearMeasures$1$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(PerformanceEntry it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(it.getEntryType() == EntryType.MEASURE);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void clear$default(Performance performance, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        performance.clear(str);
    }

    public final void clear(final String name) {
        synchronized (this.mEntriesLock) {
            if (name != null) {
                CollectionsKt__MutableCollectionsKt.removeAll((List) this.mEntries, (Function1) new Function1<PerformanceEntry, Boolean>() { // from class: com.amazon.music.find.performance.Performance$clear$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Boolean invoke(PerformanceEntry it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Boolean.valueOf(this.this$0.equalsIgnoreCase(it.getName(), name));
                    }
                });
            } else {
                this.mEntries.clear();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    public final List<PerformanceEntry> getEntries() {
        List<PerformanceEntry> list;
        synchronized (this.mEntriesLock) {
            list = CollectionsKt___CollectionsKt.toList(this.mEntries);
        }
        return list;
    }

    public static /* synthetic */ List getEntriesByName$default(Performance performance, String str, EntryType entryType, int i, Object obj) {
        if ((i & 2) != 0) {
            entryType = null;
        }
        return performance.getEntriesByName(str, entryType);
    }

    public final List<PerformanceEntry> getEntriesByName(String name, EntryType type) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(name, "name");
        synchronized (this.mEntriesLock) {
            if (type != null) {
                List<PerformanceEntry> list = this.mEntries;
                arrayList = new ArrayList();
                for (Object obj : list) {
                    PerformanceEntry performanceEntry = (PerformanceEntry) obj;
                    if (performanceEntry.getEntryType() == type && equalsIgnoreCase(performanceEntry.getName(), name)) {
                        arrayList.add(obj);
                    }
                }
            } else {
                List<PerformanceEntry> list2 = this.mEntries;
                arrayList = new ArrayList();
                for (Object obj2 : list2) {
                    if (equalsIgnoreCase(((PerformanceEntry) obj2).getName(), name)) {
                        arrayList.add(obj2);
                    }
                }
            }
        }
        return arrayList;
    }

    public final List<PerformanceEntry> getEntriesByType(EntryType type) {
        ArrayList arrayList;
        Intrinsics.checkNotNullParameter(type, "type");
        synchronized (this.mEntriesLock) {
            List<PerformanceEntry> list = this.mEntries;
            arrayList = new ArrayList();
            for (Object obj : list) {
                if (((PerformanceEntry) obj).getEntryType() == type) {
                    arrayList.add(obj);
                }
            }
        }
        return arrayList;
    }

    public final PerformanceMark mark(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        PerformanceMark performanceMark = new PerformanceMark(name, now());
        synchronized (this.mEntriesLock) {
            addSorted(performanceMark);
            Unit unit = Unit.INSTANCE;
        }
        return performanceMark;
    }

    public static /* synthetic */ PerformanceMeasure measure$default(Performance performance, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        return performance.measure(str, str2, str3);
    }

    public final PerformanceMeasure measure(String name, String startMark, String endMark) {
        long startTime;
        PerformanceMeasure performanceMeasure;
        Object next;
        Intrinsics.checkNotNullParameter(name, "name");
        synchronized (this.mEntriesLock) {
            long jNow = now();
            long startTime2 = this.mInitEntryMark.getStartTime();
            Object obj = null;
            if (startMark != null) {
                Iterator<T> it = this.mEntries.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    PerformanceEntry performanceEntry = (PerformanceEntry) next;
                    if (performanceEntry.getEntryType() == EntryType.MARK && equalsIgnoreCase(performanceEntry.getName(), startMark)) {
                        break;
                    }
                }
                PerformanceEntry performanceEntry2 = (PerformanceEntry) next;
                if (performanceEntry2 == null) {
                    throw new PerformanceException("Mark " + ((Object) startMark) + " was not found");
                }
                startTime2 = performanceEntry2.getStartTime();
            }
            if (endMark != null) {
                Iterator<T> it2 = this.mEntries.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Object next2 = it2.next();
                    PerformanceEntry performanceEntry3 = (PerformanceEntry) next2;
                    if (performanceEntry3.getEntryType() == EntryType.MARK && equalsIgnoreCase(performanceEntry3.getName(), endMark)) {
                        obj = next2;
                        break;
                    }
                }
                PerformanceEntry performanceEntry4 = (PerformanceEntry) obj;
                if (performanceEntry4 == null) {
                    throw new PerformanceException("Mark " + ((Object) endMark) + " was not found");
                }
                startTime = performanceEntry4.getStartTime();
            } else {
                startTime = jNow;
            }
            performanceMeasure = new PerformanceMeasure(name, jNow, startTime - startTime2);
            addSorted(performanceMeasure);
        }
        return performanceMeasure;
    }

    public final PerformanceMark getMarkByName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<PerformanceEntry> entriesByName = getEntriesByName(name, EntryType.MARK);
        if (entriesByName.size() == 1) {
            return (PerformanceMark) entriesByName.get(0);
        }
        return null;
    }

    public final PerformanceMeasure getMeasureByName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<PerformanceEntry> entriesByName = getEntriesByName(name, EntryType.MEASURE);
        if (entriesByName.size() == 1) {
            return (PerformanceMeasure) entriesByName.get(0);
        }
        return null;
    }

    public final boolean hasMark(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getMarkByName(name) != null;
    }

    public final boolean hasMeasure(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getMeasureByName(name) != null;
    }

    private final long now() {
        return this.mTimestampSupplier.now();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean equalsIgnoreCase(String valueL, String valueR) {
        return StringsKt__StringsJVMKt.equals(valueL, valueR, true);
    }

    private final boolean exists(PerformanceEntry entry) {
        Object next;
        Iterator<T> it = this.mEntries.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            PerformanceEntry performanceEntry = (PerformanceEntry) next;
            if (equalsIgnoreCase(performanceEntry.getName(), entry.getName()) && performanceEntry.getEntryType() == entry.getEntryType()) {
                break;
            }
        }
        return next != null;
    }

    private final void addSorted(PerformanceEntry entry) throws PerformanceException {
        if (exists(entry)) {
            throw new PerformanceException("Entry with name " + entry.getName() + " already exists");
        }
        int iBinarySearch$default = CollectionsKt__CollectionsKt.binarySearch$default(this.mEntries, entry, this.mEntriesComparator, 0, 0, 12, null);
        if (iBinarySearch$default < 0) {
            iBinarySearch$default = (iBinarySearch$default + 1) * (-1);
        }
        this.mEntries.add(iBinarySearch$default, entry);
    }

    /* JADX INFO: compiled from: Performance.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/music/find/performance/Performance$PerformanceEntryComparator;", "Ljava/util/Comparator;", "Lcom/amazon/music/find/performance/PerformanceEntry;", "Lkotlin/Comparator;", "()V", "compare", "", "o1", "o2", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class PerformanceEntryComparator implements Comparator<PerformanceEntry> {
        @Override // java.util.Comparator
        public int compare(PerformanceEntry o1, PerformanceEntry o2) {
            if (o1 == null || o2 == null) {
                return 0;
            }
            return Intrinsics.compare(o1.getStartTime(), o2.getStartTime());
        }
    }

    /* JADX INFO: compiled from: Performance.kt */
    @Metadata(d1 = {"\u0000!\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/music/find/performance/Performance$Builder;", "", "()V", "mDefaultTimestampSupplier", "com/amazon/music/find/performance/Performance$Builder$mDefaultTimestampSupplier$1", "Lcom/amazon/music/find/performance/Performance$Builder$mDefaultTimestampSupplier$1;", "mTimestampSupplier", "Lcom/amazon/music/find/performance/TimestampSupplier;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/music/find/performance/Performance;", "withTimestampSupplier", "timestampSupplier", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private final Performance$Builder$mDefaultTimestampSupplier$1 mDefaultTimestampSupplier = new TimestampSupplier() { // from class: com.amazon.music.find.performance.Performance$Builder$mDefaultTimestampSupplier$1
            @Override // com.amazon.music.find.performance.TimestampSupplier
            public String getUnits() {
                return "milliseconds";
            }

            @Override // com.amazon.music.find.performance.TimestampSupplier
            public long toMilliseconds(long interval) {
                return interval;
            }

            @Override // com.amazon.music.find.performance.TimestampSupplier
            public long now() {
                return SystemClock.elapsedRealtime();
            }
        };
        private TimestampSupplier mTimestampSupplier;

        public final Builder withTimestampSupplier(TimestampSupplier timestampSupplier) {
            Intrinsics.checkNotNullParameter(timestampSupplier, "timestampSupplier");
            this.mTimestampSupplier = timestampSupplier;
            return this;
        }

        public final Performance build() {
            TimestampSupplier timestampSupplier = this.mTimestampSupplier;
            if (timestampSupplier == null) {
                timestampSupplier = this.mDefaultTimestampSupplier;
            }
            return new Performance(timestampSupplier, null);
        }
    }
}
