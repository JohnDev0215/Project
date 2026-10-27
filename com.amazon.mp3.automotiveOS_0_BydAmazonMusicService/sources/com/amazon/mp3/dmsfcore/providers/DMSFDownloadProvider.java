package com.amazon.mp3.dmsfcore.providers;

import android.content.Context;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.views.library.views.HorizontalTileView;
import com.amazon.music.views.library.views.LibraryAddableView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DMSFDownloadProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u001a\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFDownloadProvider;", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "()V", "downloadTrack", "", "context", "Landroid/content/Context;", "view", "Lcom/amazon/music/views/library/views/LibraryAddableView;", "asin", "", "shouldShowDownloadButton", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFDownloadProvider implements DownloadProvider {
    @Override // com.amazon.music.skyfire.ui.providers.DownloadProvider
    public void downloadTrack(Context context, LibraryAddableView view, String asin) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(asin, "asin");
        new AddAndDownloadTrackTask(context, asin, true, true, (OnItemFinishedAddingListener) null).execute(new Void[0]);
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.DOWNLOAD_TRACK_BUTTON).withInteractionType(InteractionType.TAP).withEntityId(asin).withPageType(PageType.DETAIL_ARTIST).withEntityIdType(EntityIdType.ASIN).withEventTime(System.currentTimeMillis()).build());
        if (view instanceof HorizontalTileView) {
            ((HorizontalTileView) view).clearLeftIcon();
        }
    }

    @Override // com.amazon.music.skyfire.ui.providers.DownloadProvider
    public boolean shouldShowDownloadButton(Context context, String asin) {
        Intrinsics.checkNotNullParameter(context, "context");
        CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory = new CirrusSourceLibraryItemFactory(context);
        if (asin == null) {
            return false;
        }
        MusicTrack trackForAsin = cirrusSourceLibraryItemFactory.getTrackForAsin(asin);
        if (trackForAsin == null) {
            return true;
        }
        int downloadState = trackForAsin.getDownloadState();
        return (downloadState == -2 || downloadState == 1 || downloadState == 0 || downloadState == 4) ? false : true;
    }
}
