package com.amazon.music.media.auto.provider;

import android.net.Uri;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.Playback;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PlaybackLocalImageUriProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/music/media/auto/provider/PlaybackLocalImageUriProvider;", "Lcom/amazon/music/media/playback/MediaItem$SimpleImageUriProvider;", "Lcom/amazon/music/media/playback/MediaItem$ImageInfo;", "info", "Landroid/net/Uri;", "getImageUri", "createImageUri", "Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;", "uriProvider", "Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;", "<init>", "(Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;)V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class PlaybackLocalImageUriProvider extends MediaItem.SimpleImageUriProvider {
    private final LocalImageUriProvider uriProvider;

    public PlaybackLocalImageUriProvider(LocalImageUriProvider uriProvider) {
        Intrinsics.checkNotNullParameter(uriProvider, "uriProvider");
        this.uriProvider = uriProvider;
    }

    @Override // com.amazon.music.media.playback.MediaItem.SimpleImageUriProvider, com.amazon.music.media.playback.MediaItem.ImageUriProvider
    public Uri getImageUri(MediaItem.ImageInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        MediaItem.ImageInfo imageInfo = new MediaItem.ImageInfo(info.getMediaItem(), info.getImageType(), 640, 640);
        Uri imageUri = super.getImageUri(imageInfo);
        if (imageUri == null) {
            imageUri = Playback.getInstance().getPlaybackConfig().getMediaItemImageUriProvider().getImageUri(imageInfo);
        }
        if (imageUri == null) {
            return null;
        }
        return this.uriProvider.getLocalImageUri(imageUri);
    }

    @Override // com.amazon.music.media.playback.MediaItem.SimpleImageUriProvider, com.amazon.music.media.playback.MediaItem.ImageUriProvider
    public Uri createImageUri(MediaItem.ImageInfo info) {
        Intrinsics.checkNotNullParameter(info, "info");
        MediaItem.ImageInfo imageInfo = new MediaItem.ImageInfo(info.getMediaItem(), info.getImageType(), 640, 640);
        Uri uriCreateImageUri = super.createImageUri(imageInfo);
        if (uriCreateImageUri == null) {
            uriCreateImageUri = Playback.getInstance().getPlaybackConfig().getMediaItemImageUriProvider().createImageUri(imageInfo);
        }
        if (uriCreateImageUri == null) {
            return null;
        }
        Uri localImageUri = this.uriProvider.getLocalImageUri(uriCreateImageUri);
        return localImageUri == null ? (Uri) BuildersKt.runBlocking(this.uriProvider.getCoroutineScope().getCoroutineContext(), new AnonymousClass1(uriCreateImageUri, null)) : localImageUri;
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.provider.PlaybackLocalImageUriProvider$createImageUri$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlaybackLocalImageUriProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Landroid/net/Uri;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.provider.PlaybackLocalImageUriProvider$createImageUri$1", f = "PlaybackLocalImageUriProvider.kt", i = {}, l = {44}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Uri>, Object> {
        final /* synthetic */ Uri $remoteImageUrl;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Uri uri, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$remoteImageUrl = uri;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlaybackLocalImageUriProvider.this.new AnonymousClass1(this.$remoteImageUrl, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Uri> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LocalImageUriProvider localImageUriProvider = PlaybackLocalImageUriProvider.this.uriProvider;
                Uri uri = this.$remoteImageUrl;
                this.label = 1;
                obj = localImageUriProvider.createLocalImageUri(uri, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }
}
