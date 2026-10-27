package com.amazon.mp3.platform.mediaitem;

import android.os.Bundle;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.playlist.job.GetPrimePlaylistJob;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1", f = "PlaylistEntityItemHelper.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
public final class PlaylistEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CatalogPlaylist>, Object> {
    final /* synthetic */ boolean $addToLibrary$inlined;
    final /* synthetic */ EntityItemIdentifier $identifier$inlined;
    final /* synthetic */ JobQueue $jobQueue$inlined;
    int label;
    final /* synthetic */ PlaylistEntityItemHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1(Continuation continuation, EntityItemIdentifier entityItemIdentifier, PlaylistEntityItemHelper playlistEntityItemHelper, JobQueue jobQueue, boolean z) {
        super(2, continuation);
        this.$identifier$inlined = entityItemIdentifier;
        this.this$0 = playlistEntityItemHelper;
        this.$jobQueue$inlined = jobQueue;
        this.$addToLibrary$inlined = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1(continuation, this.$identifier$inlined, this.this$0, this.$jobQueue$inlined, this.$addToLibrary$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super CatalogPlaylist> continuation) {
        return ((PlaylistEntityItemHelper$import$$inlined$suspendCoroutineWithTimeout$default$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            String identifierType = this.$identifier$inlined.getId().getIdentifierType();
            if (!Intrinsics.areEqual(this.$identifier$inlined.getType(), CollectionType.Playlist.INSTANCE) || (!Intrinsics.areEqual(identifierType, "ASIN") && !Intrinsics.areEqual(identifierType, "PLAYLIST_ID"))) {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m2810constructorimpl(ResultKt.createFailure(new Throwable("EntityItemIdentifier not suitable to fetch playlist"))));
            }
            final String identifier = this.$identifier$inlined.getId().getIdentifier();
            final PrimePlaylistDatabaseManager primePlaylistDatabaseManager = new PrimePlaylistDatabaseManager(this.this$0.getContext().getApplicationContext());
            JobQueue jobQueue = this.$jobQueue$inlined;
            final boolean zAreEqual = Intrinsics.areEqual(identifierType, "PLAYLIST_ID");
            final boolean z = this.$addToLibrary$inlined;
            jobQueue.addJob(new GetPrimePlaylistJob(identifier, z, cancellableContinuationImpl, zAreEqual) { // from class: com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper$import$2$1
                final /* synthetic */ boolean $addToLibrary;
                final /* synthetic */ Continuation<CatalogPlaylist> $continuation;
                final /* synthetic */ String $idValue;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(this.$primePlaylistDatabase, identifier, 0, z, zAreEqual, false);
                    this.$idValue = identifier;
                    this.$addToLibrary = z;
                    this.$continuation = cancellableContinuationImpl;
                }

                @Override // com.amazon.mp3.service.job.Job
                protected void onFinished(int resultCode, Bundle resultBundle) {
                    Unit unit;
                    super.onFinished(resultCode, resultBundle);
                    if (1 == resultCode) {
                        CatalogPlaylist playlist = getPlaylist();
                        if (playlist == null) {
                            unit = null;
                        } else {
                            this.$continuation.resumeWith(Result.m2810constructorimpl(playlist));
                            unit = Unit.INSTANCE;
                        }
                        if (unit == null) {
                            Continuation<CatalogPlaylist> continuation = this.$continuation;
                            Result.Companion companion2 = Result.INSTANCE;
                            continuation.resumeWith(Result.m2810constructorimpl(ResultKt.createFailure(new Throwable("No playlist found"))));
                            return;
                        }
                        return;
                    }
                    Continuation<CatalogPlaylist> continuation2 = this.$continuation;
                    Result.Companion companion3 = Result.INSTANCE;
                    continuation2.resumeWith(Result.m2810constructorimpl(ResultKt.createFailure(new Throwable("Unable to fetch playlist"))));
                }
            });
            obj = cancellableContinuationImpl.getResult();
            if (obj == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
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
