package p;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* JADX INFO: loaded from: classes.dex */
public final class e33 extends k23 {
    public e33(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.assetpacks.protocol.IAssetPackExtractionServiceCallback");
    }

    public final void V(Bundle bundle) {
        Parcel parcelG0 = g0();
        int i = n23.a;
        parcelG0.writeInt(1);
        bundle.writeToParcel(parcelG0, 0);
        h0(parcelG0, 3);
    }
}
