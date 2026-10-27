package com.amazon.mp3.catalog.fragment.viewmodel;

import android.net.Uri;
import com.amazon.mp3.library.item.MusicTrack;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlaylistDetailViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/viewmodel/MusicTrackFetcher;", "", "Landroid/net/Uri;", "uri", "Lcom/amazon/mp3/library/item/MusicTrack;", "getMusicTrack", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface MusicTrackFetcher {
    MusicTrack getMusicTrack(Uri uri);
}
