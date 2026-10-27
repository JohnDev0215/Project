package com.amazon.music.views.library.metadata;

import android.net.Uri;
import com.amazon.music.ContentAccessType;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StationMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0012\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0011\u0018\u00002\u00020\u0001B·\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e\u0012\u0006\u0010\u0015\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!\u0012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\"0(\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010#¢\u0006\u0004\b7\u00108R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\b\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0004\u001a\u0004\b\u0016\u0010\u0006R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0018\u0010\u0006R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u0006R\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0004\u001a\u0004\b\u001e\u0010\u0006R\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u0004\u001a\u0004\b \u0010\u0006R#\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!8\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R(\u0010)\u001a\b\u0012\u0004\u0012\u00020\"0(8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0019\u0010/\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b/\u0010\u0004\u001a\u0004\b0\u0010\u0006R\u0019\u00101\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b1\u0010\u0004\u001a\u0004\b2\u0010\u0006R\u0019\u00103\u001a\u0004\u0018\u00010#8\u0006¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106¨\u00069"}, d2 = {"Lcom/amazon/music/views/library/metadata/StationMetadata;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "key", "getKey", "title", "getTitle", "callToActionTitle", "getCallToActionTitle", "playingStateTitle", "getPlayingStateTitle", "pausedStateTitle", "getPausedStateTitle", "imageUrl", "getImageUrl", "", "Lcom/amazon/music/ContentAccessType;", "", "explicitMap", "Ljava/util/Map;", "getExplicitMap", "()Ljava/util/Map;", "", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "setSupportedTiers", "(Ljava/util/Set;)V", "token", "getToken", "queueId", "getQueueId", "preferKeyOverToken", "Ljava/lang/Boolean;", "getPreferKeyOverToken", "()Ljava/lang/Boolean;", "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class StationMetadata implements ContentMetadata {
    private final String callToActionTitle;
    private final Map<ContentAccessType, Boolean> explicitMap;
    private final String imageUrl;
    private final String key;
    private final String pausedStateTitle;
    private final String playingStateTitle;
    private final Boolean preferKeyOverToken;
    private final String queueId;
    private Set<? extends ContentAccessType> supportedTiers;
    private List<String> tags;
    private final String title;
    private final String token;
    private Uri uri;
    private final String viewId;

    public StationMetadata(String str, Uri uri, List<String> list, String key, String str2, String str3, String str4, String str5, String str6, Map<ContentAccessType, Boolean> explicitMap, Set<? extends ContentAccessType> supportedTiers, String str7, String str8, Boolean bool) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(explicitMap, "explicitMap");
        Intrinsics.checkNotNullParameter(supportedTiers, "supportedTiers");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.key = key;
        this.title = str2;
        this.callToActionTitle = str3;
        this.playingStateTitle = str4;
        this.pausedStateTitle = str5;
        this.imageUrl = str6;
        this.explicitMap = explicitMap;
        this.supportedTiers = supportedTiers;
        this.token = str7;
        this.queueId = str8;
        this.preferKeyOverToken = bool;
    }

    public /* synthetic */ StationMetadata(String str, Uri uri, List list, String str2, String str3, String str4, String str5, String str6, String str7, Map map, Set set, String str8, String str9, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, uri, (i & 4) != 0 ? null : list, str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, map, set, (i & 2048) != 0 ? null : str8, (i & 4096) != 0 ? null : str9, (i & 8192) != 0 ? null : bool);
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public String getViewId() {
        return this.viewId;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public Uri getUri() {
        return this.uri;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public List<String> getTags() {
        return this.tags;
    }

    public final String getKey() {
        return this.key;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getCallToActionTitle() {
        return this.callToActionTitle;
    }

    public final String getPlayingStateTitle() {
        return this.playingStateTitle;
    }

    public final String getPausedStateTitle() {
        return this.pausedStateTitle;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final Map<ContentAccessType, Boolean> getExplicitMap() {
        return this.explicitMap;
    }

    public final Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }

    public final String getToken() {
        return this.token;
    }

    public final String getQueueId() {
        return this.queueId;
    }

    public final Boolean getPreferKeyOverToken() {
        return this.preferKeyOverToken;
    }
}
