package com.amazon.mp3.detailpages.actions;

import android.net.Uri;
import com.amazon.mp3.catalog.fragment.viewmodel.MusicTrackFetcher;
import com.amazon.mp3.library.item.MusicTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistTrackInformationFetcher.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000f\u0010\u0010J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0014R$\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/PlaylistTrackInformationFetcher;", "Lcom/amazon/mp3/detailpages/actions/DefaultTrackInformationFetcher;", "Landroid/net/Uri;", "uri", "Lcom/amazon/mp3/library/item/MusicTrack;", "getMusicTrack", "Lcom/amazon/mp3/catalog/fragment/viewmodel/MusicTrackFetcher;", "playlistTrackFetcher", "Lcom/amazon/mp3/catalog/fragment/viewmodel/MusicTrackFetcher;", "getPlaylistTrackFetcher", "()Lcom/amazon/mp3/catalog/fragment/viewmodel/MusicTrackFetcher;", "setPlaylistTrackFetcher", "(Lcom/amazon/mp3/catalog/fragment/viewmodel/MusicTrackFetcher;)V", "Lcom/amazon/mp3/detailpages/actions/BaseViewModelFetcher;", "modelFetcher", "<init>", "(Lcom/amazon/mp3/detailpages/actions/BaseViewModelFetcher;Lcom/amazon/mp3/catalog/fragment/viewmodel/MusicTrackFetcher;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistTrackInformationFetcher extends DefaultTrackInformationFetcher {
    private MusicTrackFetcher playlistTrackFetcher;

    public final void setPlaylistTrackFetcher(MusicTrackFetcher musicTrackFetcher) {
        this.playlistTrackFetcher = musicTrackFetcher;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistTrackInformationFetcher(BaseViewModelFetcher modelFetcher, MusicTrackFetcher musicTrackFetcher) {
        super(modelFetcher);
        Intrinsics.checkNotNullParameter(modelFetcher, "modelFetcher");
        this.playlistTrackFetcher = musicTrackFetcher;
    }

    @Override // com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher
    protected MusicTrack getMusicTrack(Uri uri) {
        MusicTrackFetcher musicTrackFetcher = this.playlistTrackFetcher;
        MusicTrack musicTrack = musicTrackFetcher == null ? null : musicTrackFetcher.getMusicTrack(uri);
        return musicTrack == null ? super.getMusicTrack(uri) : musicTrack;
    }
}
