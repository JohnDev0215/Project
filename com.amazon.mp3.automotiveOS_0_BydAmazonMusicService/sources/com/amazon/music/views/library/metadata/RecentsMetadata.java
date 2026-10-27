package com.amazon.music.views.library.metadata;

import android.net.Uri;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.ContentAccessType;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.converter.BrushV2Converter;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0099\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010$\u001a\u00020#\u0012\f\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)¢\u0006\u0004\b/\u00100R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\"\u0010\b\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000e8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0004\u001a\u0004\b\u0016\u0010\u0006R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0004\u001a\u0004\b\u0018\u0010\u0006R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u0006R\u0019\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0004\u001a\u0004\b\u001c\u0010\u0006R\u0019\u0010\u001d\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0004\u001a\u0004\b\u001e\u0010\u0006R\u0019\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u0004\u001a\u0004\b \u0010\u0006R\u0019\u0010!\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b!\u0010\u0004\u001a\u0004\b\"\u0010\u0006R\"\u0010$\u001a\u00020#8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b$\u0010&\"\u0004\b'\u0010(R\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.¨\u00061"}, d2 = {"Lcom/amazon/music/views/library/metadata/RecentsMetadata;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "title", "getTitle", "imageUrl", "getImageUrl", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "getTrackAsin", "collectionAsin", "getCollectionAsin", "collectionId", "getCollectionId", "trackLuid", "getTrackLuid", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "getType", "", BrushV2Converter.KEY_IS_EXPLICIT, "Z", "()Z", "setExplicit", "(Z)V", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "Ljava/util/Set;", "getSupportedTiers", "()Ljava/util/Set;", "<init>", "(Ljava/lang/String;Landroid/net/Uri;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/Set;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class RecentsMetadata implements ContentMetadata {
    private final String collectionAsin;
    private final String collectionId;
    private final String imageUrl;
    private boolean isExplicit;
    private final Set<ContentAccessType> supportedTiers;
    private List<String> tags;
    private final String title;
    private final String trackAsin;
    private final String trackLuid;
    private final String type;
    private Uri uri;
    private final String viewId;

    /* JADX WARN: Multi-variable type inference failed */
    public RecentsMetadata(String str, Uri uri, List<String> list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, Set<? extends ContentAccessType> supportedTiers) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(supportedTiers, "supportedTiers");
        this.viewId = str;
        this.uri = uri;
        this.tags = list;
        this.title = str2;
        this.imageUrl = str3;
        this.trackAsin = str4;
        this.collectionAsin = str5;
        this.collectionId = str6;
        this.trackLuid = str7;
        this.type = str8;
        this.isExplicit = z;
        this.supportedTiers = supportedTiers;
    }

    public /* synthetic */ RecentsMetadata(String str, Uri uri, List list, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, uri, (i & 4) != 0 ? null : list, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : str6, (i & 256) != 0 ? null : str7, (i & 512) != 0 ? null : str8, (i & 1024) != 0 ? false : z, set);
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

    public final String getTitle() {
        return this.title;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final String getTrackAsin() {
        return this.trackAsin;
    }

    public final String getCollectionAsin() {
        return this.collectionAsin;
    }

    public final String getCollectionId() {
        return this.collectionId;
    }

    public final String getTrackLuid() {
        return this.trackLuid;
    }

    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: isExplicit, reason: from getter */
    public final boolean getIsExplicit() {
        return this.isExplicit;
    }

    public final Set<ContentAccessType> getSupportedTiers() {
        return this.supportedTiers;
    }
}
