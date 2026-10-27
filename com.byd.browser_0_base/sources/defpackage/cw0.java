package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: IIdentityService.java */
/* JADX INFO: loaded from: classes3.dex */
public interface cw0 extends IInterface {

    /* JADX INFO: compiled from: IIdentityService.java */
    public static abstract class a extends Binder implements cw0 {
        public static final /* synthetic */ int e = 0;

        /* JADX INFO: renamed from: cw0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IIdentityService.java */
        public static class C0055a implements cw0 {
            public final IBinder e;

            public C0055a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // defpackage.cw0
            public final String a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.webapk.lib.common.identity_service.IIdentityService");
                    this.e.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }
        }
    }

    String a();
}
