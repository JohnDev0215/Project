package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.media.MediaRoute2Info;
import android.media.RouteDiscoveryPreference;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.mediarouter.media.p;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(api = 30)
@SuppressLint({"NewApi"})
class v {
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String a(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 94496206(0x5a1e5ce, float:1.5224784E-35)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L2a
            r1 = 1328964233(0x4f366289, float:3.059911E9)
            if (r0 == r1) goto L20
            r1 = 1348000558(0x5058db2e, float:1.4552971E10)
            if (r0 == r1) goto L16
            goto L34
        L16:
            java.lang.String r0 = "android.media.route.feature.LIVE_VIDEO"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = r3
            goto L35
        L20:
            java.lang.String r0 = "android.media.route.feature.LIVE_AUDIO"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = 0
            goto L35
        L2a:
            java.lang.String r0 = "android.media.route.feature.REMOTE_PLAYBACK"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = r2
            goto L35
        L34:
            r0 = -1
        L35:
            if (r0 == 0) goto L42
            if (r0 == r3) goto L3f
            if (r0 == r2) goto L3c
            return r4
        L3c:
            java.lang.String r4 = "android.media.intent.category.REMOTE_PLAYBACK"
            return r4
        L3f:
            java.lang.String r4 = "android.media.intent.category.LIVE_VIDEO"
            return r4
        L42:
            java.lang.String r4 = "android.media.intent.category.LIVE_AUDIO"
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.v.a(java.lang.String):java.lang.String");
    }

    @NonNull
    static RouteDiscoveryPreference b(@Nullable q qVar) {
        if (qVar == null || !qVar.f()) {
            return new RouteDiscoveryPreference.Builder(new ArrayList(), false).build();
        }
        return new RouteDiscoveryPreference.Builder((List) qVar.d().e().stream().map(new Function() { // from class: androidx.mediarouter.media.m
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return v.f((String) obj);
            }
        }).collect(Collectors.toList()), qVar.e()).build();
    }

    static Collection<String> c(List<IntentFilter> list) {
        HashSet hashSet = new HashSet();
        for (IntentFilter intentFilter : list) {
            int iCountCategories = intentFilter.countCategories();
            for (int i = 0; i < iCountCategories; i++) {
                hashSet.add(f(intentFilter.getCategory(i)));
            }
        }
        return hashSet;
    }

    @Nullable
    public static MediaRoute2Info d(@Nullable p pVar) {
        if (pVar == null) {
            return null;
        }
        MediaRoute2Info.Builder iconUri = new MediaRoute2Info.Builder(pVar.m(), pVar.p()).setDescription(pVar.h()).setConnectionState(pVar.f()).setVolumeHandling(pVar.v()).setVolume(pVar.u()).setVolumeMax(pVar.w()).addFeatures(c(pVar.g())).setIconUri(pVar.l());
        Bundle bundle = new Bundle();
        bundle.putBundle("androidx.mediarouter.media.KEY_EXTRAS", pVar.j());
        bundle.putParcelableArrayList("androidx.mediarouter.media.KEY_CONTROL_FILTERS", new ArrayList<>(pVar.g()));
        bundle.putInt("androidx.mediarouter.media.KEY_DEVICE_TYPE", pVar.i());
        iconUri.setExtras(bundle);
        if (pVar.g().isEmpty()) {
            iconUri.addFeature("android.media.route.feature.EMPTY");
        }
        return iconUri.build();
    }

    @Nullable
    public static p e(@Nullable MediaRoute2Info mediaRoute2Info) {
        if (mediaRoute2Info == null) {
            return null;
        }
        p.a aVar = new p.a(mediaRoute2Info.getId(), mediaRoute2Info.getName().toString());
        aVar.e(mediaRoute2Info.getConnectionState());
        aVar.q(mediaRoute2Info.getVolumeHandling());
        aVar.r(mediaRoute2Info.getVolumeMax());
        aVar.p(mediaRoute2Info.getVolume());
        aVar.i(mediaRoute2Info.getExtras());
        aVar.h(true);
        aVar.d(false);
        CharSequence description = mediaRoute2Info.getDescription();
        if (description != null) {
            aVar.f(description.toString());
        }
        Uri iconUri = mediaRoute2Info.getIconUri();
        if (iconUri != null) {
            aVar.j(iconUri);
        }
        Bundle extras = mediaRoute2Info.getExtras();
        if (extras == null || !extras.containsKey("androidx.mediarouter.media.KEY_EXTRAS") || !extras.containsKey("androidx.mediarouter.media.KEY_DEVICE_TYPE") || !extras.containsKey("androidx.mediarouter.media.KEY_CONTROL_FILTERS")) {
            return null;
        }
        aVar.i(extras.getBundle("androidx.mediarouter.media.KEY_EXTRAS"));
        aVar.g(extras.getInt("androidx.mediarouter.media.KEY_DEVICE_TYPE", 0));
        ArrayList parcelableArrayList = extras.getParcelableArrayList("androidx.mediarouter.media.KEY_CONTROL_FILTERS");
        if (parcelableArrayList != null) {
            aVar.b(parcelableArrayList);
        }
        return aVar.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String f(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = -2065577523(0xffffffff84e1c9cd, float:-5.308257E-36)
            r2 = 2
            r3 = 1
            if (r0 == r1) goto L2a
            r1 = 956939050(0x3909bb2a, float:1.3135062E-4)
            if (r0 == r1) goto L20
            r1 = 975975375(0x3a2c33cf, float:6.5689994E-4)
            if (r0 == r1) goto L16
            goto L34
        L16:
            java.lang.String r0 = "android.media.intent.category.LIVE_VIDEO"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = r3
            goto L35
        L20:
            java.lang.String r0 = "android.media.intent.category.LIVE_AUDIO"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = 0
            goto L35
        L2a:
            java.lang.String r0 = "android.media.intent.category.REMOTE_PLAYBACK"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L34
            r0 = r2
            goto L35
        L34:
            r0 = -1
        L35:
            if (r0 == 0) goto L42
            if (r0 == r3) goto L3f
            if (r0 == r2) goto L3c
            return r4
        L3c:
            java.lang.String r4 = "android.media.route.feature.REMOTE_PLAYBACK"
            return r4
        L3f:
            java.lang.String r4 = "android.media.route.feature.LIVE_VIDEO"
            return r4
        L42:
            java.lang.String r4 = "android.media.route.feature.LIVE_AUDIO"
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.v.f(java.lang.String):java.lang.String");
    }
}
