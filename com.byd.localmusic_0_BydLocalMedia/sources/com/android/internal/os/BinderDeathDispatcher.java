package com.android.internal.os;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.ArraySet;
import java.io.PrintWriter;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class BinderDeathDispatcher<T extends IInterface> {
    private static final String TAG = "BinderDeathDispatcher";
    private final Object mLock = new Object();
    private final ArrayMap<IBinder, BinderDeathDispatcher<T>.RecipientsInfo> mTargets = new ArrayMap<>();

    class RecipientsInfo implements IBinder.DeathRecipient {
        ArraySet<IBinder.DeathRecipient> mRecipients;
        final IBinder mTarget;

        private RecipientsInfo(IBinder iBinder) {
            this.mRecipients = new ArraySet<>();
            this.mTarget = iBinder;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            ArraySet<IBinder.DeathRecipient> arraySet;
            synchronized (BinderDeathDispatcher.this.mLock) {
                arraySet = this.mRecipients;
                this.mRecipients = null;
                BinderDeathDispatcher.this.mTargets.remove(this.mTarget);
            }
            if (arraySet == null) {
                return;
            }
            int size = arraySet.size();
            for (int i = 0; i < size; i++) {
                arraySet.valueAt(i).binderDied();
            }
        }
    }

    public int linkToDeath(T t, IBinder.DeathRecipient deathRecipient) {
        int size;
        IBinder iBinderAsBinder = t.asBinder();
        synchronized (this.mLock) {
            BinderDeathDispatcher<T>.RecipientsInfo recipientsInfo = this.mTargets.get(iBinderAsBinder);
            if (recipientsInfo == null) {
                recipientsInfo = new RecipientsInfo(iBinderAsBinder);
                try {
                    iBinderAsBinder.linkToDeath(recipientsInfo, 0);
                    this.mTargets.put(iBinderAsBinder, recipientsInfo);
                } catch (RemoteException unused) {
                    return -1;
                }
            }
            recipientsInfo.mRecipients.add(deathRecipient);
            size = recipientsInfo.mRecipients.size();
        }
        return size;
    }

    public void unlinkToDeath(T t, IBinder.DeathRecipient deathRecipient) {
        IBinder iBinderAsBinder = t.asBinder();
        synchronized (this.mLock) {
            BinderDeathDispatcher<T>.RecipientsInfo recipientsInfo = this.mTargets.get(iBinderAsBinder);
            if (recipientsInfo == null) {
                return;
            }
            if (recipientsInfo.mRecipients.remove(deathRecipient) && recipientsInfo.mRecipients.size() == 0) {
                recipientsInfo.mTarget.unlinkToDeath(recipientsInfo, 0);
                this.mTargets.remove(recipientsInfo.mTarget);
            }
        }
    }

    public void dump(PrintWriter printWriter, String str) {
        synchronized (this.mLock) {
            printWriter.print(str);
            printWriter.print("# of watched binders: ");
            printWriter.println(this.mTargets.size());
            printWriter.print(str);
            printWriter.print("# of death recipients: ");
            int size = 0;
            Iterator<BinderDeathDispatcher<T>.RecipientsInfo> it = this.mTargets.values().iterator();
            while (it.hasNext()) {
                size += it.next().mRecipients.size();
            }
            printWriter.println(size);
        }
    }

    public ArrayMap<IBinder, BinderDeathDispatcher<T>.RecipientsInfo> getTargetsForTest() {
        return this.mTargets;
    }
}
