package android.service.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.UserHandle;
import android.service.notification.IStatusBarNotificationHolder;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface INotificationListener extends IInterface {

    public static class Default implements INotificationListener {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.notification.INotificationListener
        public void onActionClicked(String str, Notification.Action action, int i) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onAllowedAdjustmentsChanged() throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onInterruptionFilterChanged(int i) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onListenerConnected(NotificationRankingUpdate notificationRankingUpdate) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onListenerHintsChanged(int i) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationChannelGroupModification(String str, UserHandle userHandle, NotificationChannelGroup notificationChannelGroup, int i) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationChannelModification(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationDirectReply(String str) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationEnqueuedWithChannel(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationChannel notificationChannel) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationExpansionChanged(String str, boolean z, boolean z2) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationPosted(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationRankingUpdate notificationRankingUpdate) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationRankingUpdate(NotificationRankingUpdate notificationRankingUpdate) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationRemoved(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationRankingUpdate notificationRankingUpdate, NotificationStats notificationStats, int i) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder iStatusBarNotificationHolder, String str) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationVisibilityChanged(String str, boolean z) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onNotificationsSeen(List<String> list) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onPanelHidden() throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onPanelRevealed(int i) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onStatusBarIconsBehaviorChanged(boolean z) throws RemoteException {
        }

        @Override // android.service.notification.INotificationListener
        public void onSuggestedReplySent(String str, CharSequence charSequence, int i) throws RemoteException {
        }
    }

    void onActionClicked(String str, Notification.Action action, int i) throws RemoteException;

    void onAllowedAdjustmentsChanged() throws RemoteException;

    void onInterruptionFilterChanged(int i) throws RemoteException;

    void onListenerConnected(NotificationRankingUpdate notificationRankingUpdate) throws RemoteException;

    void onListenerHintsChanged(int i) throws RemoteException;

    void onNotificationChannelGroupModification(String str, UserHandle userHandle, NotificationChannelGroup notificationChannelGroup, int i) throws RemoteException;

    void onNotificationChannelModification(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) throws RemoteException;

    void onNotificationDirectReply(String str) throws RemoteException;

    void onNotificationEnqueuedWithChannel(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationChannel notificationChannel) throws RemoteException;

    void onNotificationExpansionChanged(String str, boolean z, boolean z2) throws RemoteException;

    void onNotificationPosted(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationRankingUpdate notificationRankingUpdate) throws RemoteException;

    void onNotificationRankingUpdate(NotificationRankingUpdate notificationRankingUpdate) throws RemoteException;

    void onNotificationRemoved(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationRankingUpdate notificationRankingUpdate, NotificationStats notificationStats, int i) throws RemoteException;

    void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder iStatusBarNotificationHolder, String str) throws RemoteException;

    void onNotificationVisibilityChanged(String str, boolean z) throws RemoteException;

    void onNotificationsSeen(List<String> list) throws RemoteException;

    void onPanelHidden() throws RemoteException;

    void onPanelRevealed(int i) throws RemoteException;

    void onStatusBarIconsBehaviorChanged(boolean z) throws RemoteException;

    void onSuggestedReplySent(String str, CharSequence charSequence, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements INotificationListener {
        private static final String DESCRIPTOR = "android.service.notification.INotificationListener";
        static final int TRANSACTION_onActionClicked = 19;
        static final int TRANSACTION_onAllowedAdjustmentsChanged = 20;
        static final int TRANSACTION_onInterruptionFilterChanged = 7;
        static final int TRANSACTION_onListenerConnected = 1;
        static final int TRANSACTION_onListenerHintsChanged = 6;
        static final int TRANSACTION_onNotificationChannelGroupModification = 9;
        static final int TRANSACTION_onNotificationChannelModification = 8;
        static final int TRANSACTION_onNotificationDirectReply = 17;
        static final int TRANSACTION_onNotificationEnqueuedWithChannel = 10;
        static final int TRANSACTION_onNotificationExpansionChanged = 16;
        static final int TRANSACTION_onNotificationPosted = 2;
        static final int TRANSACTION_onNotificationRankingUpdate = 5;
        static final int TRANSACTION_onNotificationRemoved = 4;
        static final int TRANSACTION_onNotificationSnoozedUntilContext = 11;
        static final int TRANSACTION_onNotificationVisibilityChanged = 15;
        static final int TRANSACTION_onNotificationsSeen = 12;
        static final int TRANSACTION_onPanelHidden = 14;
        static final int TRANSACTION_onPanelRevealed = 13;
        static final int TRANSACTION_onStatusBarIconsBehaviorChanged = 3;
        static final int TRANSACTION_onSuggestedReplySent = 18;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onListenerConnected";
                case 2:
                    return "onNotificationPosted";
                case 3:
                    return "onStatusBarIconsBehaviorChanged";
                case 4:
                    return "onNotificationRemoved";
                case 5:
                    return "onNotificationRankingUpdate";
                case 6:
                    return "onListenerHintsChanged";
                case 7:
                    return "onInterruptionFilterChanged";
                case 8:
                    return "onNotificationChannelModification";
                case 9:
                    return "onNotificationChannelGroupModification";
                case 10:
                    return "onNotificationEnqueuedWithChannel";
                case 11:
                    return "onNotificationSnoozedUntilContext";
                case 12:
                    return "onNotificationsSeen";
                case 13:
                    return "onPanelRevealed";
                case 14:
                    return "onPanelHidden";
                case 15:
                    return "onNotificationVisibilityChanged";
                case 16:
                    return "onNotificationExpansionChanged";
                case 17:
                    return "onNotificationDirectReply";
                case 18:
                    return "onSuggestedReplySent";
                case 19:
                    return "onActionClicked";
                case 20:
                    return "onAllowedAdjustmentsChanged";
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

        public static INotificationListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INotificationListener)) {
                return (INotificationListener) iInterfaceQueryLocalInterface;
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
                    onListenerConnected(parcel.readInt() != 0 ? NotificationRankingUpdate.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationPosted(IStatusBarNotificationHolder.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? NotificationRankingUpdate.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStatusBarIconsBehaviorChanged(parcel.readInt() != 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationRemoved(IStatusBarNotificationHolder.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? NotificationRankingUpdate.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NotificationStats.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationRankingUpdate(parcel.readInt() != 0 ? NotificationRankingUpdate.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onListenerHintsChanged(parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onInterruptionFilterChanged(parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationChannelModification(parcel.readString(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NotificationChannel.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationChannelGroupModification(parcel.readString(), parcel.readInt() != 0 ? UserHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? NotificationChannelGroup.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationEnqueuedWithChannel(IStatusBarNotificationHolder.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? NotificationChannel.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationSnoozedUntilContext(IStatusBarNotificationHolder.Stub.asInterface(parcel.readStrongBinder()), parcel.readString());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationsSeen(parcel.createStringArrayList());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPanelRevealed(parcel.readInt());
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPanelHidden();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationVisibilityChanged(parcel.readString(), parcel.readInt() != 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationExpansionChanged(parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationDirectReply(parcel.readString());
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSuggestedReplySent(parcel.readString(), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    onActionClicked(parcel.readString(), parcel.readInt() != 0 ? Notification.Action.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAllowedAdjustmentsChanged();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements INotificationListener {
            public static INotificationListener sDefaultImpl;
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

            @Override // android.service.notification.INotificationListener
            public void onListenerConnected(NotificationRankingUpdate notificationRankingUpdate) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notificationRankingUpdate != null) {
                        parcelObtain.writeInt(1);
                        notificationRankingUpdate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onListenerConnected(notificationRankingUpdate);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationPosted(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationRankingUpdate notificationRankingUpdate) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iStatusBarNotificationHolder != null ? iStatusBarNotificationHolder.asBinder() : null);
                    if (notificationRankingUpdate != null) {
                        parcelObtain.writeInt(1);
                        notificationRankingUpdate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationPosted(iStatusBarNotificationHolder, notificationRankingUpdate);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onStatusBarIconsBehaviorChanged(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStatusBarIconsBehaviorChanged(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationRemoved(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationRankingUpdate notificationRankingUpdate, NotificationStats notificationStats, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iStatusBarNotificationHolder != null ? iStatusBarNotificationHolder.asBinder() : null);
                    if (notificationRankingUpdate != null) {
                        parcelObtain.writeInt(1);
                        notificationRankingUpdate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (notificationStats != null) {
                        parcelObtain.writeInt(1);
                        notificationStats.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationRemoved(iStatusBarNotificationHolder, notificationRankingUpdate, notificationStats, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationRankingUpdate(NotificationRankingUpdate notificationRankingUpdate) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (notificationRankingUpdate != null) {
                        parcelObtain.writeInt(1);
                        notificationRankingUpdate.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationRankingUpdate(notificationRankingUpdate);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onListenerHintsChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onListenerHintsChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onInterruptionFilterChanged(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInterruptionFilterChanged(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationChannelModification(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (notificationChannel != null) {
                        parcelObtain.writeInt(1);
                        notificationChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationChannelModification(str, userHandle, notificationChannel, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationChannelGroupModification(String str, UserHandle userHandle, NotificationChannelGroup notificationChannelGroup, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (userHandle != null) {
                        parcelObtain.writeInt(1);
                        userHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (notificationChannelGroup != null) {
                        parcelObtain.writeInt(1);
                        notificationChannelGroup.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationChannelGroupModification(str, userHandle, notificationChannelGroup, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationEnqueuedWithChannel(IStatusBarNotificationHolder iStatusBarNotificationHolder, NotificationChannel notificationChannel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iStatusBarNotificationHolder != null ? iStatusBarNotificationHolder.asBinder() : null);
                    if (notificationChannel != null) {
                        parcelObtain.writeInt(1);
                        notificationChannel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationEnqueuedWithChannel(iStatusBarNotificationHolder, notificationChannel);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationSnoozedUntilContext(IStatusBarNotificationHolder iStatusBarNotificationHolder, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iStatusBarNotificationHolder != null ? iStatusBarNotificationHolder.asBinder() : null);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationSnoozedUntilContext(iStatusBarNotificationHolder, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationsSeen(List<String> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringList(list);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationsSeen(list);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onPanelRevealed(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPanelRevealed(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onPanelHidden() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPanelHidden();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationVisibilityChanged(String str, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(15, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationVisibilityChanged(str, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationExpansionChanged(String str, boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (this.mRemote.transact(16, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationExpansionChanged(str, z, z2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onNotificationDirectReply(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(17, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationDirectReply(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onSuggestedReplySent(String str, CharSequence charSequence, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(18, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSuggestedReplySent(str, charSequence, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onActionClicked(String str, Notification.Action action, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (action != null) {
                        parcelObtain.writeInt(1);
                        action.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(19, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onActionClicked(str, action, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.notification.INotificationListener
            public void onAllowedAdjustmentsChanged() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(20, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAllowedAdjustmentsChanged();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INotificationListener iNotificationListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNotificationListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNotificationListener;
            return true;
        }

        public static INotificationListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
