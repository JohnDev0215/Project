package android.app.job;

import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.notification.ZenModeConfig;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IJobScheduler extends IInterface {

    public static class Default implements IJobScheduler {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.job.IJobScheduler
        public void cancel(int i) throws RemoteException {
        }

        @Override // android.app.job.IJobScheduler
        public void cancelAll() throws RemoteException {
        }

        @Override // android.app.job.IJobScheduler
        public int enqueue(JobInfo jobInfo, JobWorkItem jobWorkItem) throws RemoteException {
            return 0;
        }

        @Override // android.app.job.IJobScheduler
        public ParceledListSlice getAllJobSnapshots() throws RemoteException {
            return null;
        }

        @Override // android.app.job.IJobScheduler
        public ParceledListSlice getAllPendingJobs() throws RemoteException {
            return null;
        }

        @Override // android.app.job.IJobScheduler
        public JobInfo getPendingJob(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.job.IJobScheduler
        public List<JobInfo> getStartedJobs() throws RemoteException {
            return null;
        }

        @Override // android.app.job.IJobScheduler
        public int schedule(JobInfo jobInfo) throws RemoteException {
            return 0;
        }

        @Override // android.app.job.IJobScheduler
        public int scheduleAsPackage(JobInfo jobInfo, String str, int i, String str2) throws RemoteException {
            return 0;
        }
    }

    void cancel(int i) throws RemoteException;

    void cancelAll() throws RemoteException;

    int enqueue(JobInfo jobInfo, JobWorkItem jobWorkItem) throws RemoteException;

    ParceledListSlice getAllJobSnapshots() throws RemoteException;

    ParceledListSlice getAllPendingJobs() throws RemoteException;

    JobInfo getPendingJob(int i) throws RemoteException;

    List<JobInfo> getStartedJobs() throws RemoteException;

    int schedule(JobInfo jobInfo) throws RemoteException;

    int scheduleAsPackage(JobInfo jobInfo, String str, int i, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IJobScheduler {
        private static final String DESCRIPTOR = "android.app.job.IJobScheduler";
        static final int TRANSACTION_cancel = 4;
        static final int TRANSACTION_cancelAll = 5;
        static final int TRANSACTION_enqueue = 2;
        static final int TRANSACTION_getAllJobSnapshots = 9;
        static final int TRANSACTION_getAllPendingJobs = 6;
        static final int TRANSACTION_getPendingJob = 7;
        static final int TRANSACTION_getStartedJobs = 8;
        static final int TRANSACTION_schedule = 1;
        static final int TRANSACTION_scheduleAsPackage = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return ZenModeConfig.SCHEDULE_PATH;
                case 2:
                    return "enqueue";
                case 3:
                    return "scheduleAsPackage";
                case 4:
                    return "cancel";
                case 5:
                    return "cancelAll";
                case 6:
                    return "getAllPendingJobs";
                case 7:
                    return "getPendingJob";
                case 8:
                    return "getStartedJobs";
                case 9:
                    return "getAllJobSnapshots";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IJobScheduler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IJobScheduler)) {
                return (IJobScheduler) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iSchedule = schedule(parcel.readInt() != 0 ? JobInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iSchedule);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iEnqueue = enqueue(parcel.readInt() != 0 ? JobInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? JobWorkItem.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iEnqueue);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iScheduleAsPackage = scheduleAsPackage(parcel.readInt() != 0 ? JobInfo.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iScheduleAsPackage);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancel(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    cancelAll();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice allPendingJobs = getAllPendingJobs();
                    parcel2.writeNoException();
                    if (allPendingJobs != null) {
                        parcel2.writeInt(1);
                        allPendingJobs.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    JobInfo pendingJob = getPendingJob(parcel.readInt());
                    parcel2.writeNoException();
                    if (pendingJob != null) {
                        parcel2.writeInt(1);
                        pendingJob.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<JobInfo> startedJobs = getStartedJobs();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(startedJobs);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParceledListSlice allJobSnapshots = getAllJobSnapshots();
                    parcel2.writeNoException();
                    if (allJobSnapshots != null) {
                        parcel2.writeInt(1);
                        allJobSnapshots.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IJobScheduler {
            public static IJobScheduler sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.app.job.IJobScheduler
            public int schedule(JobInfo jobInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (jobInfo != null) {
                        parcelObtain.writeInt(1);
                        jobInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().schedule(jobInfo);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.job.IJobScheduler
            public int enqueue(JobInfo jobInfo, JobWorkItem jobWorkItem) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (jobInfo != null) {
                        parcelObtain.writeInt(1);
                        jobInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (jobWorkItem != null) {
                        parcelObtain.writeInt(1);
                        jobWorkItem.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enqueue(jobInfo, jobWorkItem);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.job.IJobScheduler
            public int scheduleAsPackage(JobInfo jobInfo, String str, int i, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (jobInfo != null) {
                        parcelObtain.writeInt(1);
                        jobInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().scheduleAsPackage(jobInfo, str, i, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.job.IJobScheduler
            public void cancel(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancel(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.job.IJobScheduler
            public void cancelAll() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancelAll();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.job.IJobScheduler
            public ParceledListSlice getAllPendingJobs() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllPendingJobs();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.job.IJobScheduler
            public JobInfo getPendingJob(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPendingJob(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? JobInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.job.IJobScheduler
            public List<JobInfo> getStartedJobs() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStartedJobs();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(JobInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.job.IJobScheduler
            public ParceledListSlice getAllJobSnapshots() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllJobSnapshots();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParceledListSlice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IJobScheduler iJobScheduler) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iJobScheduler == null) {
                return false;
            }
            Proxy.sDefaultImpl = iJobScheduler;
            return true;
        }

        public static IJobScheduler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
