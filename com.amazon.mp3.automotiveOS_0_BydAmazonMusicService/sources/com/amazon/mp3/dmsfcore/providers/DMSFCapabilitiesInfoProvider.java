package com.amazon.mp3.dmsfcore.providers;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.skyfire.core.network.CapabilitiesInfo;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMSFCapabilitiesInfoProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFCapabilitiesInfoProvider;", "Lcom/amazon/music/skyfire/core/network/CapabilitiesInfo;", "applicationContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "featureFlags", "", "", "DMSFFeatureFlag", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFCapabilitiesInfoProvider implements CapabilitiesInfo {
    private final Context applicationContext;

    public DMSFCapabilitiesInfoProvider(Context applicationContext) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.applicationContext = applicationContext;
    }

    /* JADX INFO: compiled from: DMSFCapabilitiesInfoProvider.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFCapabilitiesInfoProvider$DMSFFeatureFlag;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "ARTIST_REFINEMENTS", "ENTITY_PLAYLISTS", "CHILD_USER", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private enum DMSFFeatureFlag {
        ARTIST_REFINEMENTS("includeArtistRefinements"),
        ENTITY_PLAYLISTS("entityPlaylistSupported"),
        CHILD_USER("isChildUser");

        private final String value;

        DMSFFeatureFlag(String str) {
            this.value = str;
        }

        public final String getValue() {
            return this.value;
        }
    }

    @Override // com.amazon.music.skyfire.core.network.CapabilitiesInfo
    public List<String> featureFlags() {
        ArrayList arrayList = new ArrayList();
        if (AmazonApplication.getCapabilities().isBauhausLibraryArtistDPEnabled()) {
            arrayList.add(DMSFFeatureFlag.ARTIST_REFINEMENTS.getValue());
        }
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            arrayList.add(DMSFFeatureFlag.ENTITY_PLAYLISTS.getValue());
        }
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        if (childUserUtil.isChildUser(this.applicationContext)) {
            arrayList.add(DMSFFeatureFlag.CHILD_USER.getValue());
        }
        if (childUserUtil.isChildUser(this.applicationContext)) {
            arrayList.add(DMSFFeatureFlag.CHILD_USER.getValue());
        }
        return CollectionsKt___CollectionsKt.toList(arrayList);
    }
}
