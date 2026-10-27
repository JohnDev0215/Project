package com.amazon.mp3.alps.adapters;

import android.content.Context;
import com.amazon.music.alps.providers.SharingProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ALPSSharingProviderAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/alps/adapters/ALPSSharingProviderAdapter;", "Lcom/amazon/music/alps/providers/SharingProvider;", "sharingProvider", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "(Lcom/amazon/music/skyfire/ui/providers/SharingProvider;)V", "share", "", "context", "Landroid/content/Context;", "content", "Lcom/amazon/music/alps/providers/SharingProvider$ShareContent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ALPSSharingProviderAdapter implements SharingProvider {
    private final com.amazon.music.skyfire.ui.providers.SharingProvider sharingProvider;

    public ALPSSharingProviderAdapter(com.amazon.music.skyfire.ui.providers.SharingProvider sharingProvider) {
        Intrinsics.checkNotNullParameter(sharingProvider, "sharingProvider");
        this.sharingProvider = sharingProvider;
    }

    @Override // com.amazon.music.alps.providers.SharingProvider
    public void share(Context context, SharingProvider.ShareContent content) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(content, "content");
        this.sharingProvider.share(context, MapsKt__MapsKt.mapOf(TuplesKt.to(Contexts.OpenShareLink.contentType, String.valueOf(content.getContentType())), TuplesKt.to(Contexts.OpenShareLink.refMarker, content.getRefMarker()), TuplesKt.to("MESSAGE", content.getMessage()), TuplesKt.to(Contexts.OpenShareLink.imageAspectRatio, String.valueOf(content.getImageAspectRatio())), TuplesKt.to(Contexts.OpenShareLink.backgroundImage, content.getBackgroundImage()), TuplesKt.to(Contexts.OpenShareLink.image, content.getImage()), TuplesKt.to(Contexts.OpenShareLink.entityType, String.valueOf(content.getEntityType())), TuplesKt.to(Contexts.OpenShareLink.entityIdType, String.valueOf(content.getEntityIdType())), TuplesKt.to(Contexts.OpenShareLink.entityId, content.getEntityId()), TuplesKt.to(Contexts.OpenShareLink.subject, content.getSubject()), TuplesKt.to(Contexts.OpenShareLink.subtitle, content.getSubtitle()), TuplesKt.to("TITLE", content.getTitle()), TuplesKt.to("URL", content.getUrl())));
    }
}
