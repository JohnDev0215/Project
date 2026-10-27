package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.media.MediaRoute2Info;
import android.media.RouteDiscoveryPreference;
import android.net.Uri;
import android.os.Bundle;
import androidx.mediarouter.media.MediaRouteDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
class MediaRouter2Utils {
    static final String FEATURE_EMPTY = "android.media.route.feature.EMPTY";
    static final String KEY_CONTROL_FILTERS = "androidx.mediarouter.media.KEY_CONTROL_FILTERS";
    static final String KEY_DEVICE_TYPE = "androidx.mediarouter.media.KEY_DEVICE_TYPE";
    static final String KEY_EXTRAS = "androidx.mediarouter.media.KEY_EXTRAS";
    static final String KEY_MESSENGER = "androidx.mediarouter.media.KEY_MESSENGER";

    private MediaRouter2Utils() {
    }

    public static MediaRoute2Info toFwkMediaRoute2Info(MediaRouteDescriptor descriptor) {
        if (descriptor == null) {
            return null;
        }
        MediaRoute2Info.Builder builder = new MediaRoute2Info.Builder(descriptor.getId(), descriptor.getName()).setDescription(descriptor.getDescription()).setConnectionState(descriptor.getConnectionState()).setVolumeHandling(descriptor.getVolumeHandling()).setVolume(descriptor.getVolume()).setVolumeMax(descriptor.getVolumeMax()).addFeatures(toFeatures(descriptor.getControlFilters())).setIconUri(descriptor.getIconUri());
        Bundle extras = new Bundle();
        extras.putBundle(KEY_EXTRAS, descriptor.getExtras());
        extras.putParcelableArrayList(KEY_CONTROL_FILTERS, new ArrayList<>(descriptor.getControlFilters()));
        extras.putInt(KEY_DEVICE_TYPE, descriptor.getDeviceType());
        builder.setExtras(extras);
        if (descriptor.getControlFilters().isEmpty()) {
            builder.addFeature(FEATURE_EMPTY);
        }
        return builder.build();
    }

    public static MediaRouteDescriptor toMediaRouteDescriptor(MediaRoute2Info fwkMediaRoute2Info) {
        if (fwkMediaRoute2Info == null) {
            return null;
        }
        MediaRouteDescriptor.Builder builder = new MediaRouteDescriptor.Builder(fwkMediaRoute2Info.getId(), fwkMediaRoute2Info.getName().toString()).setConnectionState(fwkMediaRoute2Info.getConnectionState()).setVolumeHandling(fwkMediaRoute2Info.getVolumeHandling()).setVolumeMax(fwkMediaRoute2Info.getVolumeMax()).setVolume(fwkMediaRoute2Info.getVolume()).setExtras(fwkMediaRoute2Info.getExtras()).setEnabled(true).setCanDisconnect(false);
        CharSequence description = fwkMediaRoute2Info.getDescription();
        if (description != null) {
            builder.setDescription(description.toString());
        }
        Uri iconUri = fwkMediaRoute2Info.getIconUri();
        if (iconUri != null) {
            builder.setIconUri(iconUri);
        }
        Bundle extras = fwkMediaRoute2Info.getExtras();
        if (extras == null || !extras.containsKey(KEY_EXTRAS) || !extras.containsKey(KEY_DEVICE_TYPE) || !extras.containsKey(KEY_CONTROL_FILTERS)) {
            return null;
        }
        builder.setExtras(extras.getBundle(KEY_EXTRAS));
        builder.setDeviceType(extras.getInt(KEY_DEVICE_TYPE, 0));
        List<IntentFilter> controlFilters = extras.getParcelableArrayList(KEY_CONTROL_FILTERS);
        if (controlFilters != null) {
            builder.addControlFilters(controlFilters);
        }
        return builder.build();
    }

    static Collection<String> toFeatures(List<IntentFilter> controlFilters) {
        Set<String> features = new HashSet<>();
        for (IntentFilter filter : controlFilters) {
            int count = filter.countCategories();
            for (int i = 0; i < count; i++) {
                features.add(toRouteFeature(filter.getCategory(i)));
            }
        }
        return features;
    }

    static List<IntentFilter> toControlFilters(Collection<String> features) {
        if (features == null) {
            return new ArrayList();
        }
        return (List) features.stream().distinct().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$MediaRouter2Utils$uCpi47E8rheyDXJ-HinBKf5A5uc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.lambda$toControlFilters$0((String) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ IntentFilter lambda$toControlFilters$0(String f) {
        IntentFilter filter = new IntentFilter();
        filter.addCategory(toControlCategory(f));
        return filter;
    }

    static RouteDiscoveryPreference toDiscoveryPreference(MediaRouteDiscoveryRequest discoveryRequest) {
        if (discoveryRequest == null || !discoveryRequest.isValid()) {
            return new RouteDiscoveryPreference.Builder(new ArrayList(), false).build();
        }
        boolean activeScan = discoveryRequest.isActiveScan();
        List<String> routeFeatures = (List) discoveryRequest.getSelector().getControlCategories().stream().map(new Function() { // from class: androidx.mediarouter.media.-$$Lambda$zMyvfVxKhaSv8GFN-7x4sfyRIzM
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toRouteFeature((String) obj);
            }
        }).collect(Collectors.toList());
        return new RouteDiscoveryPreference.Builder(routeFeatures, activeScan).build();
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
        L15:
            goto L34
        L16:
            java.lang.String r0 = "android.media.intent.category.LIVE_VIDEO"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L15
            r0 = r3
            goto L35
        L20:
            java.lang.String r0 = "android.media.intent.category.LIVE_AUDIO"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L15
            r0 = 0
            goto L35
        L2a:
            java.lang.String r0 = "android.media.intent.category.REMOTE_PLAYBACK"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L15
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
            java.lang.String r0 = "android.media.route.feature.REMOTE_PLAYBACK"
            return r0
        L3f:
            java.lang.String r0 = "android.media.route.feature.LIVE_VIDEO"
            return r0
        L42:
            java.lang.String r0 = "android.media.route.feature.LIVE_AUDIO"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouter2Utils.toRouteFeature(java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String toControlCategory(java.lang.String r4) {
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
        L15:
            goto L34
        L16:
            java.lang.String r0 = "android.media.route.feature.LIVE_VIDEO"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L15
            r0 = r3
            goto L35
        L20:
            java.lang.String r0 = "android.media.route.feature.LIVE_AUDIO"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L15
            r0 = 0
            goto L35
        L2a:
            java.lang.String r0 = "android.media.route.feature.REMOTE_PLAYBACK"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L15
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
            java.lang.String r0 = "android.media.intent.category.REMOTE_PLAYBACK"
            return r0
        L3f:
            java.lang.String r0 = "android.media.intent.category.LIVE_VIDEO"
            return r0
        L42:
            java.lang.String r0 = "android.media.intent.category.LIVE_AUDIO"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouter2Utils.toControlCategory(java.lang.String):java.lang.String");
    }
}
