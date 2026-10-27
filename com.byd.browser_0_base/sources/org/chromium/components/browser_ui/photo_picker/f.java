package org.chromium.components.browser_ui.photo_picker;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* JADX INFO: compiled from: IDecoderServiceCallback.java */
/* JADX INFO: loaded from: classes2.dex */
public interface f extends IInterface {
    void n(Bundle bundle);

    /* JADX INFO: compiled from: IDecoderServiceCallback.java */
    public static abstract class a extends Binder implements f {

        /* JADX INFO: renamed from: org.chromium.components.browser_ui.photo_picker.f$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IDecoderServiceCallback.java */
        public static class C0123a implements f {
            public final IBinder e;

            public C0123a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // org.chromium.components.browser_ui.photo_picker.f
            public final void n(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.e.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
            }
            if (i == 1598968902) {
                parcel2.writeString("org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            ((c) this).n((Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
