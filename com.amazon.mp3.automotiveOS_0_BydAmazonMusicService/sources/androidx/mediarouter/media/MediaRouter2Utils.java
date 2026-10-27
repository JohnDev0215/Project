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
import androidx.mediarouter.media.MediaRouteDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(api = 30)
@SuppressLint({"NewApi"})
class MediaRouter2Utils {
    static final String FEATURE_EMPTY = "android.media.route.feature.EMPTY";
    static final String FEATURE_REMOTE_GROUP_PLAYBACK = "android.media.route.feature.REMOTE_GROUP_PLAYBACK";
    static final String KEY_CONTROL_FILTERS = "androidx.mediarouter.media.KEY_CONTROL_FILTERS";
    static final String KEY_DEVICE_TYPE = "androidx.mediarouter.media.KEY_DEVICE_TYPE";
    static final String KEY_EXTRAS = "androidx.mediarouter.media.KEY_EXTRAS";
    static final String KEY_GROUP_ROUTE = "androidx.mediarouter.media.KEY_GROUP_ROUTE";
    static final String KEY_MESSENGER = "androidx.mediarouter.media.KEY_MESSENGER";
    static final String KEY_ORIGINAL_ROUTE_ID = "androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID";
    static final String KEY_PLAYBACK_TYPE = "androidx.mediarouter.media.KEY_PLAYBACK_TYPE";
    static final String KEY_SESSION_NAME = "androidx.mediarouter.media.KEY_SESSION_NAME";

    private MediaRouter2Utils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00b5  */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.media.MediaRoute2Info$Builder] */
    @androidx.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.media.MediaRoute2Info toFwkMediaRoute2Info(@androidx.annotation.Nullable androidx.mediarouter.media.MediaRouteDescriptor r4) {
        /*
            if (r4 != 0) goto L4
            r4 = 0
            return r4
        L4:
            android.media.MediaRoute2Info$Builder r0 = new android.media.MediaRoute2Info$Builder
            java.lang.String r1 = r4.getId()
            java.lang.String r2 = r4.getName()
            r0.<init>(r1, r2)
            java.lang.String r1 = r4.getDescription()
            android.media.MediaRoute2Info$Builder r0 = r0.setDescription(r1)
            int r1 = r4.getConnectionState()
            android.media.MediaRoute2Info$Builder r0 = r0.setConnectionState(r1)
            int r1 = r4.getVolumeHandling()
            android.media.MediaRoute2Info$Builder r0 = r0.setVolumeHandling(r1)
            int r1 = r4.getVolume()
            android.media.MediaRoute2Info$Builder r0 = r0.setVolume(r1)
            int r1 = r4.getVolumeMax()
            android.media.MediaRoute2Info$Builder r0 = r0.setVolumeMax(r1)
            java.util.List r1 = r4.getControlFilters()
            java.util.Collection r1 = toFeatures(r1)
            android.media.MediaRoute2Info$Builder r0 = r0.addFeatures(r1)
            android.net.Uri r1 = r4.getIconUri()
            android.media.MediaRoute2Info$Builder r0 = r0.setIconUri(r1)
            int r1 = r4.getDeviceType()
            r2 = 1
            if (r1 == r2) goto L58
            r2 = 2
            if (r1 == r2) goto L5d
            goto L62
        L58:
            java.lang.String r1 = "android.media.route.feature.REMOTE_VIDEO_PLAYBACK"
            r0.addFeature(r1)
        L5d:
            java.lang.String r1 = "android.media.route.feature.REMOTE_AUDIO_PLAYBACK"
            r0.addFeature(r1)
        L62:
            java.util.List r1 = r4.getGroupMemberIds()
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L71
            java.lang.String r1 = "android.media.route.feature.REMOTE_GROUP_PLAYBACK"
            r0.addFeature(r1)
        L71:
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            android.os.Bundle r2 = r4.getExtras()
            java.lang.String r3 = "androidx.mediarouter.media.KEY_EXTRAS"
            r1.putBundle(r3, r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            java.util.List r3 = r4.getControlFilters()
            r2.<init>(r3)
            java.lang.String r3 = "androidx.mediarouter.media.KEY_CONTROL_FILTERS"
            r1.putParcelableArrayList(r3, r2)
            int r2 = r4.getDeviceType()
            java.lang.String r3 = "androidx.mediarouter.media.KEY_DEVICE_TYPE"
            r1.putInt(r3, r2)
            int r2 = r4.getPlaybackType()
            java.lang.String r3 = "androidx.mediarouter.media.KEY_PLAYBACK_TYPE"
            r1.putInt(r3, r2)
            java.lang.String r2 = r4.getId()
            java.lang.String r3 = "androidx.mediarouter.media.KEY_ORIGINAL_ROUTE_ID"
            r1.putString(r3, r2)
            r0.setExtras(r1)
            java.util.List r4 = r4.getControlFilters()
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto Lba
            java.lang.String r4 = "android.media.route.feature.EMPTY"
            r0.addFeature(r4)
        Lba:
            android.media.MediaRoute2Info r4 = r0.build()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouter2Utils.toFwkMediaRoute2Info(androidx.mediarouter.media.MediaRouteDescriptor):android.media.MediaRoute2Info");
    }

    @Nullable
    public static MediaRouteDescriptor toMediaRouteDescriptor(@Nullable MediaRoute2Info mediaRoute2Info) {
        if (mediaRoute2Info == null) {
            return null;
        }
        MediaRouteDescriptor.Builder canDisconnect = new MediaRouteDescriptor.Builder(mediaRoute2Info.getId(), mediaRoute2Info.getName().toString()).setConnectionState(mediaRoute2Info.getConnectionState()).setVolumeHandling(mediaRoute2Info.getVolumeHandling()).setVolumeMax(mediaRoute2Info.getVolumeMax()).setVolume(mediaRoute2Info.getVolume()).setExtras(mediaRoute2Info.getExtras()).setEnabled(true).setCanDisconnect(false);
        CharSequence description = mediaRoute2Info.getDescription();
        if (description != null) {
            canDisconnect.setDescription(description.toString());
        }
        Uri iconUri = mediaRoute2Info.getIconUri();
        if (iconUri != null) {
            canDisconnect.setIconUri(iconUri);
        }
        Bundle extras = mediaRoute2Info.getExtras();
        if (extras == null || !extras.containsKey(KEY_EXTRAS) || !extras.containsKey(KEY_DEVICE_TYPE) || !extras.containsKey(KEY_CONTROL_FILTERS)) {
            return null;
        }
        canDisconnect.setExtras(extras.getBundle(KEY_EXTRAS));
        canDisconnect.setDeviceType(extras.getInt(KEY_DEVICE_TYPE, 0));
        canDisconnect.setPlaybackType(extras.getInt(KEY_PLAYBACK_TYPE, 1));
        ArrayList parcelableArrayList = extras.getParcelableArrayList(KEY_CONTROL_FILTERS);
        if (parcelableArrayList != null) {
            canDisconnect.addControlFilters(parcelableArrayList);
        }
        return canDisconnect.build();
    }

    static Collection<String> toFeatures(List<IntentFilter> list) {
        HashSet hashSet = new HashSet();
        for (IntentFilter intentFilter : list) {
            int iCountCategories = intentFilter.countCategories();
            for (int i = 0; i < iCountCategories; i++) {
                hashSet.add(toRouteFeature(intentFilter.getCategory(i)));
            }
        }
        return hashSet;
    }

    @NonNull
    static List<IntentFilter> toControlFilters(@Nullable Collection<String> collection) {
        if (collection == null) {
            return new ArrayList();
        }
        return (List) collection.stream().distinct().map(new Function() { // from class: androidx.mediarouter.media.MediaRouter2Utils$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.lambda$toControlFilters$0((String) obj);
            }
        }).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ IntentFilter lambda$toControlFilters$0(String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addCategory(toControlCategory(str));
        return intentFilter;
    }

    @NonNull
    static List<String> getRouteIds(@Nullable List<MediaRoute2Info> list) {
        if (list == null) {
            return new ArrayList();
        }
        return (List) list.stream().filter(new MediaRoute2ProviderServiceAdapter$$ExternalSyntheticLambda6()).map(new Function() { // from class: androidx.mediarouter.media.MediaRouter2Utils$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((MediaRoute2Info) obj).getId();
            }
        }).collect(Collectors.toList());
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [android.media.RouteDiscoveryPreference$Builder] */
    /* JADX WARN: Type inference failed for: r2v1, types: [android.media.RouteDiscoveryPreference$Builder] */
    @NonNull
    static RouteDiscoveryPreference toDiscoveryPreference(@Nullable MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        if (mediaRouteDiscoveryRequest == null || !mediaRouteDiscoveryRequest.isValid()) {
            return new Object(new ArrayList(), false) { // from class: android.media.RouteDiscoveryPreference.Builder
                static {
                    throw new NoClassDefFoundError();
                }

                @android.annotation.NonNull
                public native /* synthetic */ RouteDiscoveryPreference build();
            }.build();
        }
        return new Object((List) mediaRouteDiscoveryRequest.getSelector().getControlCategories().stream().map(new Function() { // from class: androidx.mediarouter.media.MediaRouter2Utils$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MediaRouter2Utils.toRouteFeature((String) obj);
            }
        }).collect(Collectors.toList()), mediaRouteDiscoveryRequest.isActiveScan()) { // from class: android.media.RouteDiscoveryPreference.Builder
            static {
                throw new NoClassDefFoundError();
            }

            @android.annotation.NonNull
            public native /* synthetic */ RouteDiscoveryPreference build();
        }.build();
    }

    static String toRouteFeature(String str) {
        str.hashCode();
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

    static String toControlCategory(String str) {
        str.hashCode();
        switch (str) {
            case "android.media.route.feature.REMOTE_PLAYBACK":
                return MediaControlIntent.CATEGORY_REMOTE_PLAYBACK;
            case "android.media.route.feature.LIVE_AUDIO":
                return MediaControlIntent.CATEGORY_LIVE_AUDIO;
            case "android.media.route.feature.LIVE_VIDEO":
                return MediaControlIntent.CATEGORY_LIVE_VIDEO;
            default:
                return str;
        }
    }
}
