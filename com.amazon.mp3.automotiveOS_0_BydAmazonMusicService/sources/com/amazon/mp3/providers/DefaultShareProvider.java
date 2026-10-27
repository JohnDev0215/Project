package com.amazon.mp3.providers;

import android.content.Context;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.share.ArtistShareProvider;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.providers.ShareProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultShareProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/providers/DefaultShareProvider;", "Lcom/amazon/music/views/library/providers/ShareProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "share", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultShareProvider implements ShareProvider {
    private final Context context;

    public DefaultShareProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.providers.ShareProvider
    public void share(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        String musicDomain = EndpointsProvider.get().getMusicDomain();
        if (metadata instanceof ArtistMetadata) {
            ArtistMetadata artistMetadata = (ArtistMetadata) metadata;
            new ArtistShareProvider(this.context, musicDomain, artistMetadata.getAsin(), artistMetadata.getTitle(), artistMetadata.getImageUrl()).startShare();
            UIEventHandler.INSTANCE.emitUiClickMetric(ActionType.SHARE, metadata);
        }
    }
}
