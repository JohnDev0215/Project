package android.service.autofill;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.view.autofill.AutofillValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface IAutofillFieldClassificationService extends IInterface {

    public static class Default implements IAutofillFieldClassificationService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.autofill.IAutofillFieldClassificationService
        public void calculateScores(RemoteCallback remoteCallback, List<AutofillValue> list, String[] strArr, String[] strArr2, String str, Bundle bundle, Map map, Map map2) throws RemoteException {
        }
    }

    void calculateScores(RemoteCallback remoteCallback, List<AutofillValue> list, String[] strArr, String[] strArr2, String str, Bundle bundle, Map map, Map map2) throws RemoteException;

    public static abstract class Stub extends Binder implements IAutofillFieldClassificationService {
        private static final String DESCRIPTOR = "android.service.autofill.IAutofillFieldClassificationService";
        static final int TRANSACTION_calculateScores = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "calculateScores";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAutofillFieldClassificationService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAutofillFieldClassificationService)) {
                return (IAutofillFieldClassificationService) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            RemoteCallback remoteCallbackCreateFromParcel = parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null;
            ArrayList arrayListCreateTypedArrayList = parcel.createTypedArrayList(AutofillValue.CREATOR);
            String[] strArrCreateStringArray = parcel.createStringArray();
            String[] strArrCreateStringArray2 = parcel.createStringArray();
            String string = parcel.readString();
            Bundle bundleCreateFromParcel = parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null;
            ClassLoader classLoader = getClass().getClassLoader();
            calculateScores(remoteCallbackCreateFromParcel, arrayListCreateTypedArrayList, strArrCreateStringArray, strArrCreateStringArray2, string, bundleCreateFromParcel, parcel.readHashMap(classLoader), parcel.readHashMap(classLoader));
            return true;
        }

        private static class Proxy implements IAutofillFieldClassificationService {
            public static IAutofillFieldClassificationService sDefaultImpl;
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

            @Override // android.service.autofill.IAutofillFieldClassificationService
            public void calculateScores(RemoteCallback remoteCallback, List<AutofillValue> list, String[] strArr, String[] strArr2, String str, Bundle bundle, Map map, Map map2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeStringArray(strArr2);
                    parcelObtain.writeString(str);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeMap(map);
                    parcelObtain.writeMap(map2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().calculateScores(remoteCallback, list, strArr, strArr2, str, bundle, map, map2);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }
        }

        public static boolean setDefaultImpl(IAutofillFieldClassificationService iAutofillFieldClassificationService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAutofillFieldClassificationService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAutofillFieldClassificationService;
            return true;
        }

        public static IAutofillFieldClassificationService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
