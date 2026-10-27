package android.gsi;

import android.gsi.IProgressCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IImageService extends IInterface {
    public static final int CREATE_IMAGE_DEFAULT = 0;
    public static final int CREATE_IMAGE_READONLY = 1;
    public static final int CREATE_IMAGE_ZERO_FILL = 2;
    public static final int IMAGE_ERROR = 1;
    public static final int IMAGE_OK = 0;

    public static class Default implements IImageService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.gsi.IImageService
        public boolean backingImageExists(String str) throws RemoteException {
            return false;
        }

        @Override // android.gsi.IImageService
        public void createBackingImage(String str, long j, int i, IProgressCallback iProgressCallback) throws RemoteException {
        }

        @Override // android.gsi.IImageService
        public void deleteBackingImage(String str) throws RemoteException {
        }

        @Override // android.gsi.IImageService
        public List<String> getAllBackingImages() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IImageService
        public int getAvbPublicKey(String str, AvbPublicKey avbPublicKey) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IImageService
        public String getMappedImageDevice(String str) throws RemoteException {
            return null;
        }

        @Override // android.gsi.IImageService
        public boolean isImageMapped(String str) throws RemoteException {
            return false;
        }

        @Override // android.gsi.IImageService
        public void mapImageDevice(String str, int i, MappedImage mappedImage) throws RemoteException {
        }

        @Override // android.gsi.IImageService
        public void removeAllImages() throws RemoteException {
        }

        @Override // android.gsi.IImageService
        public void removeDisabledImages() throws RemoteException {
        }

        @Override // android.gsi.IImageService
        public void unmapImageDevice(String str) throws RemoteException {
        }

        @Override // android.gsi.IImageService
        public void zeroFillNewImage(String str, long j) throws RemoteException {
        }
    }

    boolean backingImageExists(String str) throws RemoteException;

    void createBackingImage(String str, long j, int i, IProgressCallback iProgressCallback) throws RemoteException;

    void deleteBackingImage(String str) throws RemoteException;

    List<String> getAllBackingImages() throws RemoteException;

    int getAvbPublicKey(String str, AvbPublicKey avbPublicKey) throws RemoteException;

    String getMappedImageDevice(String str) throws RemoteException;

    boolean isImageMapped(String str) throws RemoteException;

    void mapImageDevice(String str, int i, MappedImage mappedImage) throws RemoteException;

    void removeAllImages() throws RemoteException;

    void removeDisabledImages() throws RemoteException;

    void unmapImageDevice(String str) throws RemoteException;

    void zeroFillNewImage(String str, long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IImageService {
        private static final String DESCRIPTOR = "android.gsi.IImageService";
        static final int TRANSACTION_backingImageExists = 5;
        static final int TRANSACTION_createBackingImage = 1;
        static final int TRANSACTION_deleteBackingImage = 2;
        static final int TRANSACTION_getAllBackingImages = 8;
        static final int TRANSACTION_getAvbPublicKey = 7;
        static final int TRANSACTION_getMappedImageDevice = 12;
        static final int TRANSACTION_isImageMapped = 6;
        static final int TRANSACTION_mapImageDevice = 3;
        static final int TRANSACTION_removeAllImages = 10;
        static final int TRANSACTION_removeDisabledImages = 11;
        static final int TRANSACTION_unmapImageDevice = 4;
        static final int TRANSACTION_zeroFillNewImage = 9;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "createBackingImage";
                case 2:
                    return "deleteBackingImage";
                case 3:
                    return "mapImageDevice";
                case 4:
                    return "unmapImageDevice";
                case 5:
                    return "backingImageExists";
                case 6:
                    return "isImageMapped";
                case 7:
                    return "getAvbPublicKey";
                case 8:
                    return "getAllBackingImages";
                case 9:
                    return "zeroFillNewImage";
                case 10:
                    return "removeAllImages";
                case 11:
                    return "removeDisabledImages";
                case 12:
                    return "getMappedImageDevice";
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

        public static IImageService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IImageService)) {
                return (IImageService) iInterfaceQueryLocalInterface;
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
                    createBackingImage(parcel.readString(), parcel.readLong(), parcel.readInt(), IProgressCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteBackingImage(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string = parcel.readString();
                    int i3 = parcel.readInt();
                    MappedImage mappedImage = new MappedImage();
                    mapImageDevice(string, i3, mappedImage);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    mappedImage.writeToParcel(parcel2, 1);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    unmapImageDevice(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zBackingImageExists = backingImageExists(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zBackingImageExists ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsImageMapped = isImageMapped(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsImageMapped ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String string2 = parcel.readString();
                    AvbPublicKey avbPublicKey = new AvbPublicKey();
                    int avbPublicKey2 = getAvbPublicKey(string2, avbPublicKey);
                    parcel2.writeNoException();
                    parcel2.writeInt(avbPublicKey2);
                    parcel2.writeInt(1);
                    avbPublicKey.writeToParcel(parcel2, 1);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> allBackingImages = getAllBackingImages();
                    parcel2.writeNoException();
                    parcel2.writeStringList(allBackingImages);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    zeroFillNewImage(parcel.readString(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAllImages();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeDisabledImages();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    String mappedImageDevice = getMappedImageDevice(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(mappedImageDevice);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IImageService {
            public static IImageService sDefaultImpl;
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

            @Override // android.gsi.IImageService
            public void createBackingImage(String str, long j, int i, IProgressCallback iProgressCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iProgressCallback != null ? iProgressCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createBackingImage(str, j, i, iProgressCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public void deleteBackingImage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteBackingImage(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public void mapImageDevice(String str, int i, MappedImage mappedImage) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().mapImageDevice(str, i, mappedImage);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        mappedImage.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public void unmapImageDevice(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unmapImageDevice(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public boolean backingImageExists(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().backingImageExists(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public boolean isImageMapped(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isImageMapped(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public int getAvbPublicKey(String str, AvbPublicKey avbPublicKey) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvbPublicKey(str, avbPublicKey);
                    }
                    parcelObtain2.readException();
                    int i = parcelObtain2.readInt();
                    if (parcelObtain2.readInt() != 0) {
                        avbPublicKey.readFromParcel(parcelObtain2);
                    }
                    return i;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public List<String> getAllBackingImages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllBackingImages();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public void zeroFillNewImage(String str, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().zeroFillNewImage(str, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public void removeAllImages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAllImages();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public void removeDisabledImages() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeDisabledImages();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IImageService
            public String getMappedImageDevice(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMappedImageDevice(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IImageService iImageService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iImageService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iImageService;
            return true;
        }

        public static IImageService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
