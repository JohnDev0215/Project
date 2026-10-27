package com.amazon.music.views.library.providers;

import android.view.View;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;

/* JADX INFO: compiled from: MetadataContextMenuProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "", "openContextMenu", "", "view", "Landroid/view/View;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "interaction", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "shouldBindContextMenuOnFreeTier", "", "shouldDisplayContextMenu", "updateStateProvider", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface MetadataContextMenuProvider {
    void openContextMenu(View view, ContentMetadata metadata, InteractionType interaction);

    boolean shouldBindContextMenuOnFreeTier();

    boolean shouldDisplayContextMenu(ContentMetadata metadata);

    void updateStateProvider(ContentMetadata metadata);
}
