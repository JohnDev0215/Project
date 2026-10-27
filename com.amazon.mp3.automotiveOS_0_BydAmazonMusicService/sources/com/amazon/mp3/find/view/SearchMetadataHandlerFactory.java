package com.amazon.mp3.find.view;

import com.amazon.music.find.viewmodels.ContentMetadataHandlerFactory;
import com.amazon.music.find.viewmodels.SearchAction;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchMetadataHandlerFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J.\u0010\u0006\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/find/view/SearchMetadataHandlerFactory;", "Lcom/amazon/music/find/viewmodels/ContentMetadataHandlerFactory;", "handlers", "", "Lcom/amazon/mp3/find/view/ContentMetadataActionHandler;", "(Ljava/util/List;)V", "handle", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "action", "Lcom/amazon/music/find/viewmodels/SearchAction;", "interactionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "contentMetadataList", "positionToStart", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchMetadataHandlerFactory implements ContentMetadataHandlerFactory {
    private final List<ContentMetadataActionHandler> handlers;

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public SearchMetadataHandlerFactory(List<? extends ContentMetadataActionHandler> handlers) {
        Intrinsics.checkNotNullParameter(handlers, "handlers");
        this.handlers = handlers;
    }

    @Override // com.amazon.music.find.viewmodels.ContentMetadataHandlerFactory
    public void handle(ContentMetadata contentMetadata, SearchAction action, InteractionType interactionType) {
        Object next;
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        Iterator<T> it = this.handlers.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((ContentMetadataActionHandler) next).canHandle(action)) {
                    break;
                }
            }
        }
        ContentMetadataActionHandler contentMetadataActionHandler = (ContentMetadataActionHandler) next;
        if (contentMetadataActionHandler == null) {
            return;
        }
        contentMetadataActionHandler.handle(contentMetadata, interactionType);
    }

    @Override // com.amazon.music.find.viewmodels.ContentMetadataHandlerFactory
    public void handle(List<? extends ContentMetadata> contentMetadataList, int positionToStart, SearchAction action, InteractionType interactionType) {
        Object next;
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        Iterator<T> it = this.handlers.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((ContentMetadataActionHandler) next).canHandle(action)) {
                    break;
                }
            }
        }
        ContentMetadataActionHandler contentMetadataActionHandler = (ContentMetadataActionHandler) next;
        if (contentMetadataActionHandler == null) {
            return;
        }
        contentMetadataActionHandler.handle(contentMetadataList, positionToStart, interactionType);
    }
}
