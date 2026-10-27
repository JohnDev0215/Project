package com.amazon.mp3.download;

import android.app.Activity;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.downloads.Item;
import com.amazon.music.media.playback.util.AndroidUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import rx.functions.Action1;

/* JADX INFO: compiled from: MusicDownloaderKt.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
final class MusicDownloaderKt$showQueueInvalidTrackRedownloadDialog$1$dialog$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MusicTrack $track;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MusicDownloaderKt$showQueueInvalidTrackRedownloadDialog$1$dialog$1(MusicTrack musicTrack) {
        super(0);
        this.$track = musicTrack;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invoke$lambda-0, reason: not valid java name */
    public static final void m938invoke$lambda0(Item item) {
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        boolean z = false;
        if (currentActivity != null && ActivityKt.isAvailable(currentActivity)) {
            z = true;
        }
        if (z) {
            MusicDownloaderKt musicDownloaderKt = MusicDownloaderKt.INSTANCE;
            MusicTrack track = this.$track;
            Intrinsics.checkNotNullExpressionValue(track, "track");
            musicDownloaderKt.redownloadTrack(track).subscribe(new Action1() { // from class: com.amazon.mp3.download.MusicDownloaderKt$showQueueInvalidTrackRedownloadDialog$1$dialog$1$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MusicDownloaderKt$showQueueInvalidTrackRedownloadDialog$1$dialog$1.m938invoke$lambda0((Item) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.download.MusicDownloaderKt$showQueueInvalidTrackRedownloadDialog$1$dialog$1$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    MusicDownloaderKt$showQueueInvalidTrackRedownloadDialog$1$dialog$1.m939invoke$lambda1((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invoke$lambda-1, reason: not valid java name */
    public static final void m939invoke$lambda1(Throwable e) {
        MusicDownloaderKt musicDownloaderKt = MusicDownloaderKt.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(e, "e");
        musicDownloaderKt.onRedownloadTrackError(e);
    }
}
