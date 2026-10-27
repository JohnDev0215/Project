package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.media.MediaRoute2Info;
import android.media.RouteDiscoveryPreference;
import android.net.Uri;
import android.os.Bundle;
import androidx.mediarouter.media.MediaRouteDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"NewApi"})
class MediaRouter2Utils {
    public static MediaRouteDescriptor toMediaRouteDescriptor(MediaRoute2Info mediaRoute2Info) {
        if (mediaRoute2Info == null) {
            return null;
        }
        MediaRouteDescriptor.Builder builder = new MediaRouteDescriptor.Builder(mediaRoute2Info.getId(), mediaRoute2Info.getName().toString());
        builder.setConnectionState(mediaRoute2Info.getConnectionState());
        builder.setVolumeHandling(mediaRoute2Info.getVolumeHandling());
        builder.setVolumeMax(mediaRoute2Info.getVolumeMax());
        builder.setVolume(mediaRoute2Info.getVolume());
        builder.setExtras(mediaRoute2Info.getExtras());
        builder.setEnabled(true);
        builder.setCanDisconnect(false);
        CharSequence description = mediaRoute2Info.getDescription();
        if (description != null) {
            builder.setDescription(description.toString());
        }
        Uri iconUri = mediaRoute2Info.getIconUri();
        if (iconUri != null) {
            builder.setIconUri(iconUri);
        }
        Bundle extras = mediaRoute2Info.getExtras();
        if (extras == null || !extras.containsKey("androidx.mediarouter.media.KEY_EXTRAS") || !extras.containsKey("androidx.mediarouter.media.KEY_DEVICE_TYPE") || !extras.containsKey("androidx.mediarouter.media.KEY_CONTROL_FILTERS")) {
            return null;
        }
        builder.setExtras(extras.getBundle("androidx.mediarouter.media.KEY_EXTRAS"));
        builder.setDeviceType(extras.getInt("androidx.mediarouter.media.KEY_DEVICE_TYPE", 0));
        ArrayList parcelableArrayList = extras.getParcelableArrayList("androidx.mediarouter.media.KEY_CONTROL_FILTERS");
        if (parcelableArrayList != null) {
            builder.addControlFilters(parcelableArrayList);
        }
        return builder.build();
    }

    static RouteDiscoveryPreference toDiscoveryPreference(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        if (mediaRouteDiscoveryRequest == null || !mediaRouteDiscoveryRequest.isValid()) {
            return new RouteDiscoveryPreference.Builder(new ArrayList(), false).build();
        }
        return new RouteDiscoveryPreference.Builder((List) mediaRouteDiscoveryRequest.getSelector().getControlCategories().stream().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$zMyvfVxKhaSv8GFN-7x4sfyRIzM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toRouteFeature((String) obj);
            }
        }).collect(Collectors.toList()), mediaRouteDiscoveryRequest.isActiveScan()).build();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String toRouteFeature(java.lang.String r4) {
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouter2Utils.toRouteFeature(java.lang.String):java.lang.String");
    }
}
