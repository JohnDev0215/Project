package com.amazon.music.views.library.metadata;

import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B=\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R*\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\u0006R\u0017\u0010\u0010\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0004\u001a\u0004\b\u0011\u0010\u0006R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0004\u001a\u0004\b\u0013\u0010\u0006R\"\u0010\u0015\u001a\u00020\u00148\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/views/library/metadata/CustomerProfileMetadata;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "", "tags", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "profileId", "getProfileId", "profileName", "getProfileName", "profileStatus", "getProfileStatus", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class CustomerProfileMetadata implements ContentMetadata {
    private final String profileId;
    private final String profileName;
    private final String profileStatus;
    private List<String> tags;
    private Uri uri;
    private final String viewId;

    public CustomerProfileMetadata(String str, List<String> list, String profileId, String profileName, String profileStatus) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(profileName, "profileName");
        Intrinsics.checkNotNullParameter(profileStatus, "profileStatus");
        this.viewId = str;
        this.tags = list;
        this.profileId = profileId;
        this.profileName = profileName;
        this.profileStatus = profileStatus;
        Uri uri = Uri.parse("");
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"\")");
        this.uri = uri;
    }

    public /* synthetic */ CustomerProfileMetadata(String str, List list, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : list, str2, str3, str4);
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public String getViewId() {
        return this.viewId;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public List<String> getTags() {
        return this.tags;
    }

    public final String getProfileId() {
        return this.profileId;
    }

    @Override // com.amazon.music.views.library.metadata.ContentMetadata
    public Uri getUri() {
        return this.uri;
    }
}
