package androidx.mediarouter.media;

import android.media.MediaRoute2Info;
import android.media.RouteDiscoveryPreference;
import android.net.Uri;
import android.os.Bundle;
import androidx.mediarouter.media.d;
import defpackage.rd1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MediaRouter2Utils.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static ArrayList a(List list) {
        if (list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            MediaRoute2Info mediaRoute2Info = (MediaRoute2Info) it.next();
            if (mediaRoute2Info != null) {
                arrayList.add(mediaRoute2Info.getId());
            }
        }
        return arrayList;
    }

    public static RouteDiscoveryPreference b(rd1 rd1Var) {
        if (!rd1Var.c()) {
            return new RouteDiscoveryPreference.Builder(new ArrayList(), false).build();
        }
        boolean zB = rd1Var.b();
        ArrayList arrayList = new ArrayList();
        rd1Var.a();
        Iterator it = rd1Var.b.b().iterator();
        while (it.hasNext()) {
            arrayList.add(e((String) it.next()));
        }
        return new RouteDiscoveryPreference.Builder(arrayList, zB).build();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.media.MediaRoute2Info c(androidx.mediarouter.media.d r9) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.m.c(androidx.mediarouter.media.d):android.media.MediaRoute2Info");
    }

    public static d d(MediaRoute2Info mediaRoute2Info) {
        if (mediaRoute2Info == null) {
            return null;
        }
        d.a aVar = new d.a(mediaRoute2Info.getId(), mediaRoute2Info.getName().toString());
        int connectionState = mediaRoute2Info.getConnectionState();
        Bundle bundle = aVar.a;
        bundle.putInt("connectionState", connectionState);
        bundle.putInt("volumeHandling", mediaRoute2Info.getVolumeHandling());
        bundle.putInt("volumeMax", mediaRoute2Info.getVolumeMax());
        bundle.putInt("volume", mediaRoute2Info.getVolume());
        Bundle extras = mediaRoute2Info.getExtras();
        if (extras == null) {
            bundle.putBundle("extras", null);
        } else {
            bundle.putBundle("extras", new Bundle(extras));
        }
        bundle.putBoolean("enabled", true);
        bundle.putBoolean("canDisconnect", false);
        CharSequence description = mediaRoute2Info.getDescription();
        if (description != null) {
            bundle.putString("status", description.toString());
        }
        Uri iconUri = mediaRoute2Info.getIconUri();
        if (iconUri != null) {
            bundle.putString("iconUri", iconUri.toString());
        }
        Bundle extras2 = mediaRoute2Info.getExtras();
        if (extras2 == null || !extras2.containsKey("androidx.mediarouter.media.KEY_EXTRAS") || !extras2.containsKey("androidx.mediarouter.media.KEY_DEVICE_TYPE") || !extras2.containsKey("androidx.mediarouter.media.KEY_CONTROL_FILTERS")) {
            return null;
        }
        Bundle bundle2 = extras2.getBundle("androidx.mediarouter.media.KEY_EXTRAS");
        if (bundle2 == null) {
            bundle.putBundle("extras", null);
        } else {
            bundle.putBundle("extras", new Bundle(bundle2));
        }
        bundle.putInt("deviceType", extras2.getInt("androidx.mediarouter.media.KEY_DEVICE_TYPE", 0));
        bundle.putInt("playbackType", extras2.getInt("androidx.mediarouter.media.KEY_PLAYBACK_TYPE", 1));
        ArrayList parcelableArrayList = extras2.getParcelableArrayList("androidx.mediarouter.media.KEY_CONTROL_FILTERS");
        if (parcelableArrayList != null) {
            aVar.a(parcelableArrayList);
        }
        return aVar.b();
    }

    public static String e(String str) {
        str.getClass();
        switch (str) {
            case "android.media.intent.category.REMOTE_PLAYBACK":
                return "android.media.route.feature.REMOTE_PLAYBACK";
            case "android.media.intent.category.LIVE_AUDIO":
                return "android.media.route.feature.LIVE_AUDIO";
            case "android.media.intent.category.LIVE_VIDEO":
                return "android.media.route.feature.LIVE_VIDEO";
            default:
                return str;
        }
    }
}
