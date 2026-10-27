package com.amazon.mp3.view.lyrics.sharing.utils;

import android.content.Context;
import android.graphics.Bitmap;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingAssetManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LyricsSharingAssetManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingAssetManager$getTarget$1$onBitmapLoaded$1", f = "LyricsSharingAssetManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LyricsSharingAssetManager$getTarget$1$onBitmapLoaded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Bitmap $bitmap;
    final /* synthetic */ LyricsSharingAssetManager.AnonymousClass1 $target;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LyricsSharingAssetManager$getTarget$1$onBitmapLoaded$1(LyricsSharingAssetManager.AnonymousClass1 anonymousClass1, Bitmap bitmap, Continuation<? super LyricsSharingAssetManager$getTarget$1$onBitmapLoaded$1> continuation) {
        super(2, continuation);
        this.$target = anonymousClass1;
        this.$bitmap = bitmap;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LyricsSharingAssetManager$getTarget$1$onBitmapLoaded$1(this.$target, this.$bitmap, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LyricsSharingAssetManager$getTarget$1$onBitmapLoaded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Bitmap bitmap;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        String str = (String) LyricsSharingAssetManager.targets.getOrDefault(this.$target, null);
        String fileNameFromUrl = str != null ? LyricsSharingAssetManager.INSTANCE.getFileNameFromUrl(str) : null;
        LyricsSharingAssetManager.targets.remove(this.$target);
        if (fileNameFromUrl != null && (bitmap = this.$bitmap) != null) {
            LyricsShareCacheUtil lyricsShareCacheUtil = LyricsShareCacheUtil.INSTANCE;
            Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
            lyricsShareCacheUtil.saveBitmapToFile(applicationContext, fileNameFromUrl, bitmap);
        }
        return Unit.INSTANCE;
    }
}
