package com.amazon.mp3.detailpages.artist;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.detailpages.actions.DefaultTrackInformationFetcher;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.providers.LibraryTracksPlaybackHandler;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.YieldKt;
import rx.Observable;

/* JADX INFO: compiled from: LibraryArtistDetailViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.detailpages.artist.LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1", f = "LibraryArtistDetailViewModel.kt", i = {1, 1}, l = {121, 125}, m = "invokeSuspend", n = {"trackList", "trackPosition"}, s = {"L$0", "I$0"})
final class LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ContentMetadata $contentMetadata;
    final /* synthetic */ Context $context;
    final /* synthetic */ DefaultTrackInformationFetcher $trackInformationFetcher;
    final /* synthetic */ Uri $uri;
    int I$0;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1(DefaultTrackInformationFetcher defaultTrackInformationFetcher, Context context, ContentMetadata contentMetadata, Uri uri, Continuation<? super LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1> continuation) {
        super(2, continuation);
        this.$trackInformationFetcher = defaultTrackInformationFetcher;
        this.$context = context;
        this.$contentMetadata = contentMetadata;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1(this.$trackInformationFetcher, this.$context, this.$contentMetadata, this.$uri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object tracks;
        int i;
        List list;
        int i2;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            DefaultTrackInformationFetcher defaultTrackInformationFetcher = this.$trackInformationFetcher;
            this.label = 1;
            tracks = defaultTrackInformationFetcher.getTracks(this);
            if (tracks == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i3 != 1) {
                if (i3 == 2) {
                    i2 = this.I$0;
                    List list2 = (List) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    list = list2;
                    LibraryTracksPlaybackHandler libraryTracksPlaybackHandler = LibraryTracksPlaybackHandler.INSTANCE;
                    int size = list.size();
                    Context context = this.$context;
                    ContentMetadata contentMetadata = this.$contentMetadata;
                    final DefaultTrackInformationFetcher defaultTrackInformationFetcher2 = this.$trackInformationFetcher;
                    LibraryTracksPlaybackHandler.initiatePlayback$default(libraryTracksPlaybackHandler, context, contentMetadata, size, list, new Function1<Integer, Observable<MusicTrack>>() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Observable<MusicTrack> invoke(Integer num) {
                            return invoke(num.intValue());
                        }

                        public final Observable<MusicTrack> invoke(int i4) {
                            return defaultTrackInformationFetcher2.getTrack(i4);
                        }
                    }, Boxing.boxInt(i2), false, this.$uri, null, null, null, null, false, 6144, null);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            tracks = obj;
        }
        List list3 = (List) tracks;
        ContentMetadata contentMetadata2 = this.$contentMetadata;
        int i4 = 0;
        Iterator it = list3.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (Intrinsics.areEqual(((MusicTrack) it.next()).getContentUri(), contentMetadata2.getUri())) {
                i = i4;
                break;
            }
            i4++;
        }
        this.L$0 = list3;
        this.I$0 = i;
        this.label = 2;
        if (YieldKt.yield(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        list = list3;
        i2 = i;
        LibraryTracksPlaybackHandler libraryTracksPlaybackHandler2 = LibraryTracksPlaybackHandler.INSTANCE;
        int size2 = list.size();
        Context context2 = this.$context;
        ContentMetadata contentMetadata3 = this.$contentMetadata;
        final DefaultTrackInformationFetcher defaultTrackInformationFetcher22 = this.$trackInformationFetcher;
        LibraryTracksPlaybackHandler.initiatePlayback$default(libraryTracksPlaybackHandler2, context2, contentMetadata3, size2, list, new Function1<Integer, Observable<MusicTrack>>() { // from class: com.amazon.mp3.detailpages.artist.LibraryArtistDetailViewModel$handleTrackItemClickPlayback$1$1$1.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Observable<MusicTrack> invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final Observable<MusicTrack> invoke(int i42) {
                return defaultTrackInformationFetcher22.getTrack(i42);
            }
        }, Boxing.boxInt(i2), false, this.$uri, null, null, null, null, false, 6144, null);
        return Unit.INSTANCE;
    }
}
