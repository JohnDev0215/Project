package com.android.car.developeroptions.applications;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.app.procstats.ProcessState;
import com.android.internal.app.procstats.ProcessStats;
import com.android.internal.app.procstats.ServiceState;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class ProcStatsEntry implements Parcelable {
    public static final Parcelable.Creator<ProcStatsEntry> CREATOR = new Parcelable.Creator<ProcStatsEntry>() { // from class: com.android.car.developeroptions.applications.ProcStatsEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProcStatsEntry createFromParcel(Parcel parcel) {
            return new ProcStatsEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ProcStatsEntry[] newArray(int i) {
            return new ProcStatsEntry[i];
        }
    };
    private static boolean DEBUG = false;
    final long mAvgBgMem;
    final long mAvgRunMem;
    String mBestTargetPackage;
    final long mBgDuration;
    final double mBgWeight;
    public CharSequence mLabel;
    final long mMaxBgMem;
    final long mMaxRunMem;
    final String mName;
    final String mPackage;
    final long mRunDuration;
    final double mRunWeight;
    final int mUid;
    final ArrayList<String> mPackages = new ArrayList<>();
    ArrayMap<String, ArrayList<Service>> mServices = new ArrayMap<>(1);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ProcStatsEntry(ProcessState processState, String str, ProcessStats.ProcessDataCollection processDataCollection, ProcessStats.ProcessDataCollection processDataCollection2, boolean z) {
        processState.computeProcessData(processDataCollection, 0L);
        processState.computeProcessData(processDataCollection2, 0L);
        this.mPackage = processState.getPackage();
        this.mUid = processState.getUid();
        this.mName = processState.getName();
        this.mPackages.add(str);
        this.mBgDuration = processDataCollection.totalTime;
        this.mAvgBgMem = z ? processDataCollection.avgUss : processDataCollection.avgPss;
        this.mMaxBgMem = z ? processDataCollection.maxUss : processDataCollection.maxPss;
        this.mBgWeight = this.mAvgBgMem * this.mBgDuration;
        this.mRunDuration = processDataCollection2.totalTime;
        this.mAvgRunMem = z ? processDataCollection2.avgUss : processDataCollection2.avgPss;
        this.mMaxRunMem = z ? processDataCollection2.maxUss : processDataCollection2.maxPss;
        this.mRunWeight = this.mAvgRunMem * this.mRunDuration;
        if (DEBUG) {
            Log.d("ProcStatsEntry", "New proc entry " + processState.getName() + ": dur=" + this.mBgDuration + " avgpss=" + this.mAvgBgMem + " weight=" + this.mBgWeight);
        }
    }

    public ProcStatsEntry(String str, int i, String str2, long j, long j2, long j3) {
        this.mPackage = str;
        this.mUid = i;
        this.mName = str2;
        this.mRunDuration = j;
        this.mBgDuration = j;
        this.mMaxRunMem = j2;
        this.mAvgRunMem = j2;
        this.mMaxBgMem = j2;
        this.mAvgBgMem = j2;
        double d = j3 * j2;
        this.mRunWeight = d;
        this.mBgWeight = d;
        if (DEBUG) {
            Log.d("ProcStatsEntry", "New proc entry " + str2 + ": dur=" + this.mBgDuration + " avgpss=" + this.mAvgBgMem + " weight=" + this.mBgWeight);
        }
    }

    public ProcStatsEntry(Parcel parcel) {
        this.mPackage = parcel.readString();
        this.mUid = parcel.readInt();
        this.mName = parcel.readString();
        parcel.readStringList(this.mPackages);
        this.mBgDuration = parcel.readLong();
        this.mAvgBgMem = parcel.readLong();
        this.mMaxBgMem = parcel.readLong();
        this.mBgWeight = parcel.readDouble();
        this.mRunDuration = parcel.readLong();
        this.mAvgRunMem = parcel.readLong();
        this.mMaxRunMem = parcel.readLong();
        this.mRunWeight = parcel.readDouble();
        this.mBestTargetPackage = parcel.readString();
        int i = parcel.readInt();
        if (i > 0) {
            this.mServices.ensureCapacity(i);
            for (int i2 = 0; i2 < i; i2++) {
                String string = parcel.readString();
                ArrayList arrayList = new ArrayList();
                parcel.readTypedList(arrayList, Service.CREATOR);
                this.mServices.append(string, arrayList);
            }
        }
    }

    public void addPackage(String str) {
        this.mPackages.add(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0388  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0412 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void evaluateTargetPackage(android.content.pm.PackageManager r24, com.android.internal.app.procstats.ProcessStats r25, com.android.internal.app.procstats.ProcessStats.ProcessDataCollection r26, com.android.internal.app.procstats.ProcessStats.ProcessDataCollection r27, java.util.Comparator<com.android.car.developeroptions.applications.ProcStatsEntry> r28, boolean r29) {
        /*
            Method dump skipped, instruction units count: 1099
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.applications.ProcStatsEntry.evaluateTargetPackage(android.content.pm.PackageManager, com.android.internal.app.procstats.ProcessStats, com.android.internal.app.procstats.ProcessStats$ProcessDataCollection, com.android.internal.app.procstats.ProcessStats$ProcessDataCollection, java.util.Comparator, boolean):void");
    }

    public void addService(ServiceState serviceState) {
        ArrayList<Service> arrayList = this.mServices.get(serviceState.getPackage());
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.mServices.put(serviceState.getPackage(), arrayList);
        }
        arrayList.add(new Service(serviceState));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mPackage);
        parcel.writeInt(this.mUid);
        parcel.writeString(this.mName);
        parcel.writeStringList(this.mPackages);
        parcel.writeLong(this.mBgDuration);
        parcel.writeLong(this.mAvgBgMem);
        parcel.writeLong(this.mMaxBgMem);
        parcel.writeDouble(this.mBgWeight);
        parcel.writeLong(this.mRunDuration);
        parcel.writeLong(this.mAvgRunMem);
        parcel.writeLong(this.mMaxRunMem);
        parcel.writeDouble(this.mRunWeight);
        parcel.writeString(this.mBestTargetPackage);
        int size = this.mServices.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeString(this.mServices.keyAt(i2));
            parcel.writeTypedList(this.mServices.valueAt(i2));
        }
    }

    public int getUid() {
        return this.mUid;
    }

    public static final class Service implements Parcelable {
        public static final Parcelable.Creator<Service> CREATOR = new Parcelable.Creator<Service>() { // from class: com.android.car.developeroptions.applications.ProcStatsEntry.Service.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Service createFromParcel(Parcel parcel) {
                return new Service(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Service[] newArray(int i) {
                return new Service[i];
            }
        };
        final long mDuration;
        final String mName;
        final String mPackage;
        final String mProcess;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Service(ServiceState serviceState) {
            this.mPackage = serviceState.getPackage();
            this.mName = serviceState.getName();
            this.mProcess = serviceState.getProcessName();
            this.mDuration = serviceState.dumpTime((PrintWriter) null, (String) null, 0, -1, 0L, 0L);
        }

        public Service(Parcel parcel) {
            this.mPackage = parcel.readString();
            this.mName = parcel.readString();
            this.mProcess = parcel.readString();
            this.mDuration = parcel.readLong();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mPackage);
            parcel.writeString(this.mName);
            parcel.writeString(this.mProcess);
            parcel.writeLong(this.mDuration);
        }
    }
}
