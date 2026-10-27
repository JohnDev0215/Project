package p;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class z23 extends l23 implements a33 {
    @Override // p.l23
    public final boolean g0(int i, Parcel parcel) {
        switch (i) {
            case 2:
                int i2 = parcel.readInt();
                b03 b03Var = (b03) this;
                b03Var.c.d.c(b03Var.b);
                com.google.android.play.core.assetpacks.a.g.e("onStartDownload(%d)", Integer.valueOf(i2));
                return true;
            case 3:
                int i3 = parcel.readInt();
                b03 b03Var2 = (b03) this;
                b03Var2.c.d.c(b03Var2.b);
                com.google.android.play.core.assetpacks.a.g.e("onCancelDownload(%d)", Integer.valueOf(i3));
                return true;
            case 4:
                int i4 = parcel.readInt();
                b03 b03Var3 = (b03) this;
                b03Var3.c.d.c(b03Var3.b);
                com.google.android.play.core.assetpacks.a.g.e("onGetSession(%d)", Integer.valueOf(i4));
                return true;
            case 5:
                M(parcel.createTypedArrayList(Bundle.CREATOR));
                return true;
            case 6:
                Parcelable.Creator creator = Bundle.CREATOR;
                Bundle bundle = (Bundle) n23.a(parcel, creator);
                b03 b03Var4 = (b03) this;
                b03Var4.c.d.c(b03Var4.b);
                com.google.android.play.core.assetpacks.a.g.e("onNotifyChunkTransferred(%s, %s, %d, session=%d)", bundle.getString("module_name"), bundle.getString("slice_id"), Integer.valueOf(bundle.getInt("chunk_number")), Integer.valueOf(bundle.getInt("session_id")));
                return true;
            case 7:
                V((Bundle) n23.a(parcel, Bundle.CREATOR));
                return true;
            case 8:
                Parcelable.Creator creator2 = Bundle.CREATOR;
                Bundle bundle2 = (Bundle) n23.a(parcel, creator2);
                b03 b03Var5 = (b03) this;
                b03Var5.c.d.c(b03Var5.b);
                com.google.android.play.core.assetpacks.a.g.e("onNotifyModuleCompleted(%s, sessionId=%d)", bundle2.getString("module_name"), Integer.valueOf(bundle2.getInt("session_id")));
                return true;
            case 9:
            default:
                return false;
            case 10:
                Parcelable.Creator creator3 = Bundle.CREATOR;
                Bundle bundle3 = (Bundle) n23.a(parcel, creator3);
                b03 b03Var6 = (b03) this;
                b03Var6.c.d.c(b03Var6.b);
                com.google.android.play.core.assetpacks.a.g.e("onNotifySessionFailed(%d)", Integer.valueOf(bundle3.getInt("session_id")));
                return true;
            case 11:
                Parcelable.Creator creator4 = Bundle.CREATOR;
                Q((Bundle) n23.a(parcel, creator4), (Bundle) n23.a(parcel, creator4));
                return true;
            case 12:
                Parcelable.Creator creator5 = Bundle.CREATOR;
                c((Bundle) n23.a(parcel, creator5), (Bundle) n23.a(parcel, creator5));
                return true;
            case 13:
                Parcelable.Creator creator6 = Bundle.CREATOR;
                b03 b03Var7 = (b03) this;
                b03Var7.c.d.c(b03Var7.b);
                com.google.android.play.core.assetpacks.a.g.e("onRequestDownloadInfo()", new Object[0]);
                return true;
            case 14:
                Parcelable.Creator creator7 = Bundle.CREATOR;
                b03 b03Var8 = (b03) this;
                b03Var8.c.d.c(b03Var8.b);
                com.google.android.play.core.assetpacks.a.g.e("onRemoveModule()", new Object[0]);
                return true;
            case 15:
                b03 b03Var9 = (b03) this;
                b03Var9.c.d.c(b03Var9.b);
                com.google.android.play.core.assetpacks.a.g.e("onCancelDownloads()", new Object[0]);
                return true;
        }
    }
}
