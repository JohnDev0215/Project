package a.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.loostone.libtuning.component.service.PmKaraokeAppService;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends Binder implements IInterface {
    public a() {
        attachInterface(this, "com.loostone.karaoke.voice.IPmKaraokeAppService");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("com.loostone.karaoke.voice.IPmKaraokeAppService");
            ((PmKaraokeAppService.OooO0O0) this).OooO00o(parcel.readString());
            parcel2.writeNoException();
            return true;
        }
        if (i == 2) {
            parcel.enforceInterface("com.loostone.karaoke.voice.IPmKaraokeAppService");
            ((PmKaraokeAppService.OooO0O0) this).OooO0O0();
            parcel2.writeNoException();
            return true;
        }
        if (i == 3) {
            parcel.enforceInterface("com.loostone.karaoke.voice.IPmKaraokeAppService");
            ((PmKaraokeAppService.OooO0O0) this).OooO00o();
            parcel2.writeNoException();
            return true;
        }
        if (i != 4) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.loostone.karaoke.voice.IPmKaraokeAppService");
            return true;
        }
        parcel.enforceInterface("com.loostone.karaoke.voice.IPmKaraokeAppService");
        ((PmKaraokeAppService.OooO0O0) this).OooO0OO();
        parcel2.writeNoException();
        return true;
    }
}
