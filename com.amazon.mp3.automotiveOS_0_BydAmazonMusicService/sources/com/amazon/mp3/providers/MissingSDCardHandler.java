package com.amazon.mp3.providers;

import android.content.Context;
import com.amazon.mp3.library.dialog.DownloadedFileNotFoundActivityDialog;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.providers.SDCardMissingProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MissingSDCardHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/providers/MissingSDCardHandler;", "Lcom/amazon/music/views/library/providers/SDCardMissingProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "onSDCardMissingIconClicked", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MissingSDCardHandler implements SDCardMissingProvider {
    private final Context context;

    public MissingSDCardHandler(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.providers.SDCardMissingProvider
    public void onSDCardMissingIconClicked(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (metadata instanceof TrackMetadata) {
            AbstractItem item = new CirrusSourceLibraryItemFactory(this.context).getItem(metadata.getUri());
            MusicTrack musicTrack = item instanceof MusicTrack ? (MusicTrack) item : null;
            if (musicTrack == null) {
                return;
            }
            Context context = this.context;
            context.startActivity(DownloadedFileNotFoundActivityDialog.getStartIntent(context, musicTrack));
        }
    }
}
