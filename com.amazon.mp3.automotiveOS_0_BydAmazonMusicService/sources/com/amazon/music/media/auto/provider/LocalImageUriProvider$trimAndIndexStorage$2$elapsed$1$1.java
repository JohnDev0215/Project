package com.amazon.music.media.auto.provider;

import com.amazon.music.media.auto.util.AutoLog;
import java.io.File;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__FileTreeWalkKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt___SequencesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LocalImageUriProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.music.media.auto.provider.LocalImageUriProvider$trimAndIndexStorage$2$elapsed$1$1", f = "LocalImageUriProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LocalImageUriProvider$trimAndIndexStorage$2$elapsed$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ LocalImageUriProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LocalImageUriProvider$trimAndIndexStorage$2$elapsed$1$1(LocalImageUriProvider localImageUriProvider, Continuation<? super LocalImageUriProvider$trimAndIndexStorage$2$elapsed$1$1> continuation) {
        super(2, continuation);
        this.this$0 = localImageUriProvider;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LocalImageUriProvider$trimAndIndexStorage$2$elapsed$1$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LocalImageUriProvider$trimAndIndexStorage$2$elapsed$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Ref.IntRef intRef = new Ref.IntRef();
        try {
            Sequence<File> sequenceSortedWith = SequencesKt___SequencesKt.sortedWith(SequencesKt___SequencesKt.filter(FilesKt__FileTreeWalkKt.walk$default(this.this$0.getCacheDir(), null, 1, null), new Function1<File, Boolean>() { // from class: com.amazon.music.media.auto.provider.LocalImageUriProvider$trimAndIndexStorage$2$elapsed$1$1.1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(File it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    return Boolean.valueOf(it.isFile() && it.canRead());
                }
            }), new Comparator() { // from class: com.amazon.music.media.auto.provider.LocalImageUriProvider$trimAndIndexStorage$2$elapsed$1$1$invokeSuspend$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((File) t2).lastModified()), Long.valueOf(((File) t).lastModified()));
                }
            });
            LocalImageUriProvider localImageUriProvider = this.this$0;
            for (File file : sequenceSortedWith) {
                if (intRef.element < 200 && file.length() > 0) {
                    AutoLog autoLog = AutoLog.INSTANCE;
                    String TAG = LocalImageUriProvider.TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
                    autoLog.debug(TAG, "Adding cached image to index: " + ((Object) file.getName()) + ", size = " + file.length() + ", last modified = " + file.lastModified());
                    localImageUriProvider.cachedImages.put(FilesKt__UtilsKt.getNameWithoutExtension(file), LocalImageUriProviderKt.getContentProviderUri(file));
                    intRef.element = intRef.element + 1;
                } else {
                    AutoLog autoLog2 = AutoLog.INSTANCE;
                    String TAG2 = LocalImageUriProvider.TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    autoLog2.debug(TAG2, "Deleting cached image: " + ((Object) file.getName()) + ", size = " + file.length() + ", last modified = " + file.lastModified());
                    file.delete();
                }
            }
        } catch (Exception e) {
            AutoLog autoLog3 = AutoLog.INSTANCE;
            String TAG3 = LocalImageUriProvider.TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            autoLog3.error(TAG3, "Parsing image cache failed", e);
        }
        return Unit.INSTANCE;
    }
}
