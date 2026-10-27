package com.amazon.dcp.sso;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes2.dex */
public interface IRequestAuthenticationMethod extends IInterface {

    /* JADX INFO: compiled from: DCP */
    public static abstract class Stub extends Binder implements IRequestAuthenticationMethod {

        /* JADX INFO: compiled from: DCP */
        public static class Proxy implements IRequestAuthenticationMethod {
            public static IRequestAuthenticationMethod sDefaultImpl;
            private IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public String getAdpToken(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdpToken(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public String getAdpTokenByDirectedId(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdpTokenByDirectedId(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public ReturnValueOrError getAuthenticationParametersForRequest(String str, String str2, String str3, Uri uri, String str4, Map map, byte[] bArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str4);
                    parcelObtain.writeMap(map);
                    parcelObtain.writeByteArray(bArr);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        ReturnValueOrError authenticationParametersForRequest = Stub.getDefaultImpl().getAuthenticationParametersForRequest(str, str2, str3, uri, str4, map, bArr);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return authenticationParametersForRequest;
                    }
                    parcelObtain2.readException();
                    ReturnValueOrError returnValueOrErrorCreateFromParcel = parcelObtain2.readInt() != 0 ? ReturnValueOrError.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return returnValueOrErrorCreateFromParcel;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public ReturnValueOrError getAuthenticationParametersForRequestByDirectedId(String str, String str2, Uri uri, String str3, Map map, byte[] bArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (uri != null) {
                        parcelObtain.writeInt(1);
                        uri.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str3);
                    parcelObtain.writeMap(map);
                    parcelObtain.writeByteArray(bArr);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        ReturnValueOrError authenticationParametersForRequestByDirectedId = Stub.getDefaultImpl().getAuthenticationParametersForRequestByDirectedId(str, str2, uri, str3, map, bArr);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return authenticationParametersForRequestByDirectedId;
                    }
                    parcelObtain2.readException();
                    ReturnValueOrError returnValueOrErrorCreateFromParcel = parcelObtain2.readInt() != 0 ? ReturnValueOrError.CREATOR.createFromParcel(parcelObtain2) : null;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return returnValueOrErrorCreateFromParcel;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public ReturnValueOrError signCorpus(String str, String str2, String str3, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().signCorpus(str, str2, str3, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ReturnValueOrError.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.amazon.dcp.sso.IRequestAuthenticationMethod
            public ReturnValueOrError signCorpusByDirectedId(String str, String str2, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().signCorpusByDirectedId(str, str2, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ReturnValueOrError.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.amazon.dcp.sso.IRequestAuthenticationMethod");
        }

        public static IRequestAuthenticationMethod asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.amazon.dcp.sso.IRequestAuthenticationMethod");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IRequestAuthenticationMethod)) ? new Proxy(iBinder) : (IRequestAuthenticationMethod) iInterfaceQueryLocalInterface;
        }

        public static IRequestAuthenticationMethod getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    ReturnValueOrError authenticationParametersForRequest = getAuthenticationParametersForRequest(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readHashMap(getClass().getClassLoader()), parcel.createByteArray());
                    parcel2.writeNoException();
                    if (authenticationParametersForRequest != null) {
                        parcel2.writeInt(1);
                        authenticationParametersForRequest.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    ReturnValueOrError returnValueOrErrorSignCorpus = signCorpus(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    if (returnValueOrErrorSignCorpus != null) {
                        parcel2.writeInt(1);
                        returnValueOrErrorSignCorpus.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    String adpToken = getAdpToken(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(adpToken);
                    return true;
                case 4:
                    parcel.enforceInterface("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    ReturnValueOrError authenticationParametersForRequestByDirectedId = getAuthenticationParametersForRequestByDirectedId(parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readHashMap(getClass().getClassLoader()), parcel.createByteArray());
                    parcel2.writeNoException();
                    if (authenticationParametersForRequestByDirectedId != null) {
                        parcel2.writeInt(1);
                        authenticationParametersForRequestByDirectedId.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    ReturnValueOrError returnValueOrErrorSignCorpusByDirectedId = signCorpusByDirectedId(parcel.readString(), parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    if (returnValueOrErrorSignCorpusByDirectedId != null) {
                        parcel2.writeInt(1);
                        returnValueOrErrorSignCorpusByDirectedId.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface("com.amazon.dcp.sso.IRequestAuthenticationMethod");
                    String adpTokenByDirectedId = getAdpTokenByDirectedId(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(adpTokenByDirectedId);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String getAdpToken(String str, String str2) throws RemoteException;

    String getAdpTokenByDirectedId(String str) throws RemoteException;

    ReturnValueOrError getAuthenticationParametersForRequest(String str, String str2, String str3, Uri uri, String str4, Map map, byte[] bArr) throws RemoteException;

    ReturnValueOrError getAuthenticationParametersForRequestByDirectedId(String str, String str2, Uri uri, String str3, Map map, byte[] bArr) throws RemoteException;

    ReturnValueOrError signCorpus(String str, String str2, String str3, byte[] bArr) throws RemoteException;

    ReturnValueOrError signCorpusByDirectedId(String str, String str2, byte[] bArr) throws RemoteException;
}
