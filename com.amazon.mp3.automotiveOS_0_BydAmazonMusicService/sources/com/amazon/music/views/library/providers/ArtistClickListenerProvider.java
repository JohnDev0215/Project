package com.amazon.music.views.library.providers;

import android.content.Context;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtistDetailHeaderModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ArtistClickListenerProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\tH&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J(\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J(\u0010\u0013\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;", "", "onFollowClick", "", "metadata", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "isFollowing", "", "callback", "Lkotlin/Function1;", "model", "Lcom/amazon/music/views/library/models/ArtistDetailHeaderModel;", "onPlayActionIconClick", "context", "Landroid/content/Context;", "metadataList", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "onShareClick", "onShuffleActionIconClick", "onStationActionIconClick", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ArtistClickListenerProvider {
    void onFollowClick(ArtistMetadata metadata, boolean isFollowing, Function1<? super Boolean, Unit> callback);

    void onFollowClick(ArtistDetailHeaderModel model);

    void onPlayActionIconClick(Context context, ArtistMetadata metadata, List<? extends ContentMetadata> metadataList);

    void onShareClick(ArtistMetadata metadata);

    void onShuffleActionIconClick(Context context, ArtistMetadata metadata, List<? extends ContentMetadata> metadataList);

    void onStationActionIconClick(Context context, ArtistMetadata metadata);
}
