package com.amazon.music.views.library.metadata;

import android.net.Uri;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContentMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u001c\u0010\u000b\u001a\u00020\u00068&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\f8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "", "getViewId", "()Ljava/lang/String;", "viewId", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "setUri", "(Landroid/net/Uri;)V", "uri", "", "getTags", "()Ljava/util/List;", "tags", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface ContentMetadata {
    List<String> getTags();

    Uri getUri();

    String getViewId();
}
