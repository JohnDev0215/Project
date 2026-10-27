package a.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.loostone.libtuning.component.service.PmKaraokeVoiceService;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends Binder implements IInterface {
    public b() {
        attachInterface(this, "com.loostone.karaoke.voice.IPmKaraokeVoiceService");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("com.loostone.karaoke.voice.IPmKaraokeVoiceService");
            byte[] bArrCreateByteArray = parcel.createByteArray();
            int iOooO00o = ((PmKaraokeVoiceService.OooO0O0) this).OooO00o(bArrCreateByteArray, parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeInt(iOooO00o);
            parcel2.writeByteArray(bArrCreateByteArray);
            return true;
        }
        if (i == 2) {
            parcel.enforceInterface("com.loostone.karaoke.voice.IPmKaraokeVoiceService");
            int iOooO00o2 = ((PmKaraokeVoiceService.OooO0O0) this).OooO00o(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(iOooO00o2);
            return true;
        }
        if (i != 3) {
            if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel2.writeString("com.loostone.karaoke.voice.IPmKaraokeVoiceService");
            return true;
        }
        parcel.enforceInterface("com.loostone.karaoke.voice.IPmKaraokeVoiceService");
        int iOooO00o3 = ((PmKaraokeVoiceService.OooO0O0) this).OooO00o();
        parcel2.writeNoException();
        parcel2.writeInt(iOooO00o3);
        return true;
    }
}
