package com.amazon.mp3.widget;

import android.content.Context;
import android.graphics.Bitmap;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.library.provider.source.nowplaying.AlexaMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.LibraryMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.music.media.playback.MediaItem;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PlayerWidgetUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.widget.PlayerWidgetUtils$initArtwork$albumArtwork$1", f = "PlayerWidgetUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class PlayerWidgetUtils$initArtwork$albumArtwork$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MediaItem $currentMediaItem;
    final /* synthetic */ ActionValidatedPlaybackController $playbackController;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlayerWidgetUtils$initArtwork$albumArtwork$1(MediaItem mediaItem, Context context, ActionValidatedPlaybackController actionValidatedPlaybackController, Continuation<? super PlayerWidgetUtils$initArtwork$albumArtwork$1> continuation) {
        super(2, continuation);
        this.$currentMediaItem = mediaItem;
        this.$context = context;
        this.$playbackController = actionValidatedPlaybackController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerWidgetUtils$initArtwork$albumArtwork$1(this.$currentMediaItem, this.$context, this.$playbackController, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((PlayerWidgetUtils$initArtwork$albumArtwork$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaItem mediaItem = this.$currentMediaItem;
            if (mediaItem instanceof AlexaMediaItem) {
                return WidgetUtil.getAlbumArtByAsin(this.$context, mediaItem);
            }
            if (this.$playbackController.canRateCurrentItem()) {
                return WidgetUtil.getArtworkForPrimeBrowseMediaItem(this.$currentMediaItem);
            }
            if (MediaItemHelper.getAlbumId(this.$currentMediaItem) != 3 && MediaItemHelper.getAlbumId(this.$currentMediaItem) != LibraryMediaItem.NO_ALBUM_ID) {
                return WidgetUtil.getArtworkForLibraryMediaItem(this.$context, this.$currentMediaItem);
            }
            MediaItem mediaItem2 = this.$currentMediaItem;
            if (mediaItem2 instanceof LibraryMediaItem) {
                if (TrackType.CATALOG_PLAYLIST_TRACK == ((LibraryMediaItem) mediaItem2).getTrackType()) {
                    return WidgetUtil.getArtworkForPrimePlaylistMediaItem(this.$context, this.$currentMediaItem);
                }
                return WidgetUtil.getArtworkForPrimeBrowseMediaItem(this.$currentMediaItem);
            }
            return WidgetUtil.getArtworkForPrimeBrowseMediaItem(mediaItem2);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
