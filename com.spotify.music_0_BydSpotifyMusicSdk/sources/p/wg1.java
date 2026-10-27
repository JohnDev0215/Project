package p;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.media.MediaBrowserService;
import android.support.v4.media.session.MediaSessionCompat$Token;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.eventsender.FragmentsContainer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class wg1 {
    public final Object a;

    public /* synthetic */ wg1(Object obj) {
        this.a = obj;
    }

    public IBinder a() {
        return ((Messenger) this.a).getBinder();
    }

    public ai0 b(fi0 fi0Var) {
        FragmentsContainer fragmentsContainerG = FragmentsContainer.g();
        try {
            fragmentsContainerG = FragmentsContainer.j(fi0Var.f);
        } catch (InvalidProtocolBufferException e) {
            ((u11) ((wc1) this.a)).y("Error in parsing contexts.", e);
        }
        long j = fi0Var.a;
        String str = fi0Var.c;
        byte[] bArr = fi0Var.d;
        ByteString byteString = ByteString.c;
        return new ai0(j, str, ByteString.c(bArr, 0, bArr.length), fi0Var.e, com.google.common.collect.a.a(fragmentsContainerG.h()).e(new lj(6)));
    }

    public void c(String str, MediaSessionCompat$Token mediaSessionCompat$Token, Bundle bundle) throws RemoteException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("extra_service_version", 2);
        Bundle bundle2 = new Bundle();
        bundle2.putString("data_media_item_id", str);
        bundle2.putParcelable("data_media_session_token", mediaSessionCompat$Token);
        bundle2.putBundle("data_root_hints", bundle);
        g(1, bundle2);
    }

    public void d(String str, List list, Bundle bundle, Bundle bundle2) throws RemoteException {
        Bundle bundle3 = new Bundle();
        bundle3.putString("data_media_item_id", str);
        bundle3.putBundle("data_options", bundle);
        bundle3.putBundle("data_notify_children_changed_options", bundle2);
        if (list != null) {
            bundle3.putParcelableArrayList("data_media_item_list", list instanceof ArrayList ? (ArrayList) list : new ArrayList<>(list));
        }
        g(3, bundle3);
    }

    public void e(int i, String str) {
        ((ww1) this.a).t().edit().putInt(str, i).apply();
    }

    public void f(String str, String str2) {
        ((ww1) this.a).t().edit().putString(str, str2).apply();
    }

    public void g(int i, Bundle bundle) throws RemoteException {
        Message messageObtain = Message.obtain();
        messageObtain.what = i;
        messageObtain.arg1 = 2;
        messageObtain.setData(bundle);
        ((Messenger) this.a).send(messageObtain);
    }

    public void h(Object obj) {
        boolean z = obj instanceof List;
        ArrayList arrayList = null;
        Object obj2 = this.a;
        if (!z) {
            if (!(obj instanceof Parcel)) {
                ((MediaBrowserService.Result) obj2).sendResult(null);
                return;
            }
            Parcel parcel = (Parcel) obj;
            parcel.setDataPosition(0);
            ((MediaBrowserService.Result) obj2).sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
            parcel.recycle();
            return;
        }
        MediaBrowserService.Result result = (MediaBrowserService.Result) obj2;
        List<Parcel> list = (List) obj;
        if (list != null) {
            arrayList = new ArrayList(list.size());
            for (Parcel parcel2 : list) {
                parcel2.setDataPosition(0);
                arrayList.add((MediaBrowser.MediaItem) MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel2));
                parcel2.recycle();
            }
        }
        result.sendResult(arrayList);
    }

    public wg1() {
        Set setSingleton = Collections.singleton("RawCoreStream");
        v03.i(setSingleton, "singleton(element)");
        this.a = setSingleton;
    }
}
