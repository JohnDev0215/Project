package com.amazon.mp3.find.view;

import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeSearchMetadataClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J(\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/find/view/CarModeSearchMetadataClickListener;", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "searchMetadataPlayHandler", "Lcom/amazon/mp3/find/view/CarModeSearchMetadataPlayHandler;", "(Lcom/amazon/mp3/find/view/CarModeSearchMetadataPlayHandler;)V", "onContentClicked", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "onContentListClicked", "contentMetadataList", "", "positionToStart", "", "contentListTitle", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeSearchMetadataClickListener implements ContentMetadataOnClickListener {
    private final CarModeSearchMetadataPlayHandler searchMetadataPlayHandler;

    @JvmOverloads
    public CarModeSearchMetadataClickListener(CarModeSearchMetadataPlayHandler searchMetadataPlayHandler) {
        Intrinsics.checkNotNullParameter(searchMetadataPlayHandler, "searchMetadataPlayHandler");
        this.searchMetadataPlayHandler = searchMetadataPlayHandler;
    }

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        this.searchMetadataPlayHandler.handle(contentMetadataList, positionToStart, InteractionType.TAP);
    }

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentClicked(ContentMetadata contentMetadata) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        this.searchMetadataPlayHandler.handle(contentMetadata, InteractionType.TAP);
    }
}
