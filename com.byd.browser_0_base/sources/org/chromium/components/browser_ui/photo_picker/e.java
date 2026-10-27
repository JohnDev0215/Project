package org.chromium.components.browser_ui.photo_picker;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import org.chromium.components.browser_ui.photo_picker.f;

/* JADX INFO: compiled from: IDecoderService.java */
/* JADX INFO: loaded from: classes2.dex */
public interface e extends IInterface {
    void e(Bundle bundle, f fVar);

    /* JADX INFO: compiled from: IDecoderService.java */
    public static abstract class a extends Binder implements e {
        public static final /* synthetic */ int e = 0;

        /* JADX INFO: renamed from: org.chromium.components.browser_ui.photo_picker.e$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IDecoderService.java */
        public static class C0122a implements e {
            public final IBinder e;

            public C0122a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // org.chromium.components.browser_ui.photo_picker.e
            public final void e(Bundle bundle, f fVar) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.components.browser_ui.photo_picker.IDecoderService");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongInterface(fVar);
                    this.e.transact(1, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "org.chromium.components.browser_ui.photo_picker.IDecoderService");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("org.chromium.components.browser_ui.photo_picker.IDecoderService");
            }
            if (i == 1598968902) {
                parcel2.writeString("org.chromium.components.browser_ui.photo_picker.IDecoderService");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            f c0123a = null;
            Bundle bundle = (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
            IBinder strongBinder = parcel.readStrongBinder();
            if (strongBinder != null) {
                IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("org.chromium.components.browser_ui.photo_picker.IDecoderServiceCallback");
                c0123a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof f)) ? new f.a.C0123a(strongBinder) : (f) iInterfaceQueryLocalInterface;
            }
            ((g) this).e(bundle, c0123a);
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
