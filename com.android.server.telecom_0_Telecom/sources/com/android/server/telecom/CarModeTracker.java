package com.android.server.telecom;

import android.telecom.Log;
import android.util.LocalLog;
import com.android.internal.util.IndentingPrintWriter;
import com.android.server.telecom.CarModeTracker;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class CarModeTracker {
    private PriorityQueue<CarModeApp> mCarModeApps = new PriorityQueue<>(2, new CarModeAppComparator());
    private final LocalLog mCarModeChangeLog = new LocalLog(20);

    /* JADX INFO: Access modifiers changed from: private */
    class CarModeApp {
        private String mPackageName;
        private int mPriority;

        public CarModeApp(CarModeTracker carModeTracker, int i, String str) {
            this.mPriority = i;
            Objects.requireNonNull(str);
            this.mPackageName = str;
        }

        public int getPriority() {
            return this.mPriority;
        }

        public String getPackageName() {
            return this.mPackageName;
        }
    }

    private class CarModeAppComparator implements Comparator<CarModeApp> {
        private CarModeAppComparator(CarModeTracker carModeTracker) {
        }

        @Override // java.util.Comparator
        public int compare(CarModeApp carModeApp, CarModeApp carModeApp2) {
            return Integer.compare(carModeApp2.getPriority(), carModeApp.getPriority());
        }
    }

    static /* synthetic */ boolean lambda$handleEnterCarMode$0(int i, CarModeApp carModeApp) {
        return carModeApp.getPriority() == i;
    }

    public void handleEnterCarMode(final int i, final String str) {
        if (this.mCarModeApps.stream().anyMatch(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CarModeTracker$W9QHpSg9D_k3P6p2EOYNNkSM9No
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CarModeTracker.lambda$handleEnterCarMode$0(i, (CarModeTracker.CarModeApp) obj);
            }
        })) {
            Log.w(this, "handleEnterCarMode: already in car mode at priority %d (apps: %s)", new Object[]{Integer.valueOf(i), getCarModePriorityString()});
            return;
        }
        if (this.mCarModeApps.stream().anyMatch(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CarModeTracker$KygxGt_m9ggSIbHxNiq2f4SbRo4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CarModeTracker.CarModeApp) obj).getPackageName().equals(str);
            }
        })) {
            Log.w(this, "handleEnterCarMode: %s is already in car mode (apps: %s)", new Object[]{str, getCarModePriorityString()});
            return;
        }
        Log.i(this, "handleEnterCarMode: packageName=%s, priority=%d", new Object[]{str, Integer.valueOf(i)});
        this.mCarModeChangeLog.log("enterCarMode: packageName=" + str + ", priority=" + i);
        this.mCarModeApps.add(new CarModeApp(this, i, str));
    }

    static /* synthetic */ boolean lambda$handleExitCarMode$2(int i, CarModeApp carModeApp) {
        return carModeApp.getPriority() == i;
    }

    public void handleExitCarMode(final int i, final String str) {
        if (!this.mCarModeApps.stream().anyMatch(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CarModeTracker$-Q9L_BKHf82FpeTRlXCDPsrtrJo
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CarModeTracker.lambda$handleExitCarMode$2(i, (CarModeTracker.CarModeApp) obj);
            }
        })) {
            Log.w(this, "handleExitCarMode: not in car mode at priority %d (apps=%s)", new Object[]{Integer.valueOf(i), getCarModePriorityString()});
            return;
        }
        if (i != 0 && !this.mCarModeApps.stream().anyMatch(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CarModeTracker$85m0jmx0y6KjmENXKXJY38OopKw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CarModeTracker.lambda$handleExitCarMode$3(str, i, (CarModeTracker.CarModeApp) obj);
            }
        })) {
            Log.w(this, "handleExitCarMode: %s didn't enter car mode at priority %d (apps=%s)", new Object[]{str, Integer.valueOf(i), getCarModePriorityString()});
            return;
        }
        Log.i(this, "handleExitCarMode: packageName=%s, priority=%d", new Object[]{str, Integer.valueOf(i)});
        this.mCarModeChangeLog.log("exitCarMode: packageName=" + str + ", priority=" + i);
        this.mCarModeApps.removeIf(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CarModeTracker$62YfdAXkQLR0Hs7Wicavio18710
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CarModeTracker.lambda$handleExitCarMode$4(i, (CarModeTracker.CarModeApp) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$handleExitCarMode$3(String str, int i, CarModeApp carModeApp) {
        return carModeApp.getPackageName().equals(str) && carModeApp.getPriority() == i;
    }

    static /* synthetic */ boolean lambda$handleExitCarMode$4(int i, CarModeApp carModeApp) {
        return carModeApp.getPriority() == i;
    }

    public List<String> getCarModeApps() {
        return (List) this.mCarModeApps.stream().sorted(this.mCarModeApps.comparator()).map(new Function() { // from class: com.android.server.telecom.-$$Lambda$CarModeTracker$k_nRBmVMWIsprvZ2nATaxdmFy8o
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CarModeTracker.CarModeApp) obj).getPackageName();
            }
        }).collect(Collectors.toList());
    }

    private String getCarModePriorityString() {
        return (String) this.mCarModeApps.stream().sorted(this.mCarModeApps.comparator()).map(new Function() { // from class: com.android.server.telecom.-$$Lambda$CarModeTracker$BYOvYTnvAKsHir2Mu32jah_QDck
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CarModeTracker.lambda$getCarModePriorityString$6((CarModeTracker.CarModeApp) obj);
            }
        }).collect(Collectors.joining(", "));
    }

    static /* synthetic */ String lambda$getCarModePriorityString$6(CarModeApp carModeApp) {
        return "[" + carModeApp.getPriority() + ", " + carModeApp.getPackageName() + "]";
    }

    public String getCurrentCarModePackage() {
        CarModeApp carModeAppPeek = this.mCarModeApps.peek();
        if (carModeAppPeek == null) {
            return null;
        }
        return carModeAppPeek.getPackageName();
    }

    public boolean isInCarMode() {
        return !this.mCarModeApps.isEmpty();
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println("CarModeTracker:");
        indentingPrintWriter.increaseIndent();
        indentingPrintWriter.println("Current car mode apps:");
        indentingPrintWriter.increaseIndent();
        for (CarModeApp carModeApp : this.mCarModeApps) {
            indentingPrintWriter.print("[");
            indentingPrintWriter.print(carModeApp.getPriority());
            indentingPrintWriter.print("] ");
            indentingPrintWriter.println(carModeApp.getPackageName());
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("Car mode history:");
        indentingPrintWriter.increaseIndent();
        this.mCarModeChangeLog.dump(indentingPrintWriter);
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.decreaseIndent();
    }
}
