package com.amazon.mp3.platform.providers.library;

import android.content.Context;
import android.os.Bundle;
import com.amazon.mp3.library.job.AddPrimeTrackJob;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.CtaAlbumRequester;
import com.amazon.mp3.prime.data.AsinSubstitutionException;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.music.browse.BrowseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$lambda-3$$inlined$suspendCoroutineWithTimeout$default$1, reason: invalid class name */
/* JADX INFO: compiled from: Coroutines.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$lambda-3$$inlined$suspendCoroutineWithTimeout$default$1", f = "AlbumInteractor.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
public final class AlbumInteractor$addAlbumToLibrary$lambda3$$inlined$suspendCoroutineWithTimeout$default$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ String $albumAsin$inlined;
    final /* synthetic */ CtaAlbumRequester $requester$inlined;
    int label;
    final /* synthetic */ AlbumInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumInteractor$addAlbumToLibrary$lambda3$$inlined$suspendCoroutineWithTimeout$default$1(Continuation continuation, CtaAlbumRequester ctaAlbumRequester, String str, AlbumInteractor albumInteractor) {
        super(2, continuation);
        this.$requester$inlined = ctaAlbumRequester;
        this.$albumAsin$inlined = str;
        this.this$0 = albumInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AlbumInteractor$addAlbumToLibrary$lambda3$$inlined$suspendCoroutineWithTimeout$default$1(continuation, this.$requester$inlined, this.$albumAsin$inlined, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((AlbumInteractor$addAlbumToLibrary$lambda3$$inlined$suspendCoroutineWithTimeout$default$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws ServiceException, BrowseException, AbstractHttpClient.HttpClientException, AsinSubstitutionException {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            List<PrimeTrack> listRequestTracksForAsin = this.$requester$inlined.requestTracksForAsin(this.$albumAsin$inlined);
            Intrinsics.checkNotNullExpressionValue(listRequestTracksForAsin, "requester.requestTracksForAsin(albumAsin)");
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : listRequestTracksForAsin) {
                PrimeTrack primeTrack = (PrimeTrack) obj2;
                boolean z = false;
                if (primeTrack != null) {
                    String asin = primeTrack.getAsin();
                    if (!(asin == null || StringsKt__StringsJVMKt.isBlank(asin))) {
                        ContentOwnershipStatus ownershipStatus = primeTrack.getOwnershipStatus();
                        if ((ownershipStatus == null || ownershipStatus.isInLibrary()) ? false : true) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    arrayList.add(obj2);
                }
            }
            final ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((PrimeTrack) it.next()).getAsin());
            }
            JobQueue jobQueue = this.this$0.getJobQueue();
            final Context context = this.this$0.getContext();
            final AlbumInteractor albumInteractor = this.this$0;
            jobQueue.addJob(new AddPrimeTrackJob(arrayList2, cancellableContinuationImpl, albumInteractor, context) { // from class: com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$2$1$1
                final /* synthetic */ List<String> $asinsToAdd;
                final /* synthetic */ Continuation<Boolean> $continuation;
                final /* synthetic */ AlbumInteractor this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(context, arrayList2);
                    this.$asinsToAdd = arrayList2;
                    this.$continuation = cancellableContinuationImpl;
                    this.this$0 = albumInteractor;
                }

                @Override // com.amazon.mp3.service.job.Job
                protected void onFinished(int resultCode, Bundle resultBundle) {
                    super.onFinished(resultCode, resultBundle);
                    this.$continuation.resumeWith(this.this$0.m1379getJobResultIoAF18A(resultCode));
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
