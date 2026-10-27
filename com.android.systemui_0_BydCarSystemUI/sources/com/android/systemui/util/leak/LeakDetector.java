package com.android.systemui.util.leak;

import android.os.Build;
import com.android.internal.util.IndentingPrintWriter;
import com.android.systemui.Dumpable;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class LeakDetector implements Dumpable {
    public static final boolean ENABLED = Build.IS_DEBUGGABLE;
    private final TrackedCollections mTrackedCollections;
    private final TrackedGarbage mTrackedGarbage;
    private final TrackedObjects mTrackedObjects;

    public LeakDetector(TrackedCollections trackedCollections, TrackedGarbage trackedGarbage, TrackedObjects trackedObjects) {
        this.mTrackedCollections = trackedCollections;
        this.mTrackedGarbage = trackedGarbage;
        this.mTrackedObjects = trackedObjects;
    }

    public <T> void trackInstance(T object) {
        TrackedObjects trackedObjects = this.mTrackedObjects;
        if (trackedObjects != null) {
            trackedObjects.track(object);
        }
    }

    public <T> void trackCollection(Collection<T> collection, String tag) {
        TrackedCollections trackedCollections = this.mTrackedCollections;
        if (trackedCollections != null) {
            trackedCollections.track(collection, tag);
        }
    }

    public void trackGarbage(Object o) {
        TrackedGarbage trackedGarbage = this.mTrackedGarbage;
        if (trackedGarbage != null) {
            trackedGarbage.track(o);
        }
    }

    TrackedGarbage getTrackedGarbage() {
        return this.mTrackedGarbage;
    }

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor df, PrintWriter w, String[] args) {
        PrintWriter indentingPrintWriter = new IndentingPrintWriter(w, "  ");
        indentingPrintWriter.println("SYSUI LEAK DETECTOR");
        indentingPrintWriter.increaseIndent();
        if (this.mTrackedCollections != null && this.mTrackedGarbage != null) {
            indentingPrintWriter.println("TrackedCollections:");
            indentingPrintWriter.increaseIndent();
            this.mTrackedCollections.dump(indentingPrintWriter, new Predicate() { // from class: com.android.systemui.util.leak.-$$Lambda$LeakDetector$WUJMCHFtoUoAr9o87OE8XGfD-Ms
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return LeakDetector.lambda$dump$0((Collection) obj);
                }
            });
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.println();
            indentingPrintWriter.println("TrackedObjects:");
            indentingPrintWriter.increaseIndent();
            this.mTrackedCollections.dump(indentingPrintWriter, new Predicate() { // from class: com.android.systemui.util.leak.-$$Lambda$gcPXHqlRatwGM8tZRz-xI3y_nKk
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TrackedObjects.isTrackedObject((Collection) obj);
                }
            });
            indentingPrintWriter.decreaseIndent();
            indentingPrintWriter.println();
            indentingPrintWriter.print("TrackedGarbage:");
            indentingPrintWriter.increaseIndent();
            this.mTrackedGarbage.dump(indentingPrintWriter);
            indentingPrintWriter.decreaseIndent();
        } else {
            indentingPrintWriter.println("disabled");
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println();
    }

    static /* synthetic */ boolean lambda$dump$0(Collection collection) {
        return !TrackedObjects.isTrackedObject(collection);
    }

    public static LeakDetector create() {
        if (ENABLED) {
            TrackedCollections trackedCollections = new TrackedCollections();
            return new LeakDetector(trackedCollections, new TrackedGarbage(trackedCollections), new TrackedObjects(trackedCollections));
        }
        return new LeakDetector(null, null, null);
    }
}
