package com.amazon.music.views.library.providers;

import com.amazon.music.views.library.models.CastingListItemHorizontalTileModel;
import com.amazon.music.views.library.views.CastingListItemHorizontalTileView;
import kotlin.Metadata;

/* JADX INFO: compiled from: CastingTileClickListenerProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/views/library/providers/CastingTileListenerProvider;", "", "clickListener", "", "view", "Lcom/amazon/music/views/library/views/CastingListItemHorizontalTileView;", "model", "Lcom/amazon/music/views/library/models/CastingListItemHorizontalTileModel;", "disconnectListener", "isCasting", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface CastingTileListenerProvider {
    void clickListener(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model);

    void disconnectListener(CastingListItemHorizontalTileView view, CastingListItemHorizontalTileModel model);

    boolean isCasting(CastingListItemHorizontalTileModel model);
}
