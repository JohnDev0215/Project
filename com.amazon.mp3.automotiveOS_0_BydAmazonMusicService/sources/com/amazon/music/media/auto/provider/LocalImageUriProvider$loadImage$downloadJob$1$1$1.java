package com.amazon.music.media.auto.provider;

import android.net.Uri;
import com.amazon.music.media.auto.util.AutoLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LocalImageUriProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.music.media.auto.provider.LocalImageUriProvider$loadImage$downloadJob$1$1$1", f = "LocalImageUriProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LocalImageUriProvider$loadImage$downloadJob$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $key;
    final /* synthetic */ Uri $remoteImageUrl;
    int label;
    final /* synthetic */ LocalImageUriProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LocalImageUriProvider$loadImage$downloadJob$1$1$1(LocalImageUriProvider localImageUriProvider, String str, Uri uri, Continuation<? super LocalImageUriProvider$loadImage$downloadJob$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = localImageUriProvider;
        this.$key = str;
        this.$remoteImageUrl = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LocalImageUriProvider$loadImage$downloadJob$1$1$1(this.this$0, this.$key, this.$remoteImageUrl, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LocalImageUriProvider$loadImage$downloadJob$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            File fileForKey = this.this$0.getFileForKey(this.$key);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileForKey);
                LocalImageUriProvider localImageUriProvider = this.this$0;
                Uri uri = this.$remoteImageUrl;
                try {
                    if (!localImageUriProvider.downloadUsingMSA(uri, fileOutputStream)) {
                        localImageUriProvider.downloadUsingPicasso(uri, fileOutputStream);
                    }
                    CloseableKt.closeFinally(fileOutputStream, null);
                    if (fileForKey.canRead() && fileForKey.length() > 0) {
                        ReentrantReadWriteLock reentrantReadWriteLock = this.this$0.lock;
                        LocalImageUriProvider localImageUriProvider2 = this.this$0;
                        String str = this.$key;
                        ReentrantReadWriteLock.ReadLock lock = reentrantReadWriteLock.readLock();
                        int i = 0;
                        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                        int i2 = 0;
                        while (i2 < readHoldCount) {
                            i2++;
                            lock.unlock();
                        }
                        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
                        writeLock.lock();
                        try {
                            localImageUriProvider2.cachedImages.put(str, LocalImageUriProviderKt.getContentProviderUri(fileForKey));
                            Unit unit = Unit.INSTANCE;
                        } finally {
                            while (i < readHoldCount) {
                                i++;
                                lock.lock();
                            }
                            writeLock.unlock();
                        }
                    } else {
                        fileForKey.delete();
                    }
                    return Unit.INSTANCE;
                } finally {
                }
            } catch (IOException e) {
                AutoLog autoLog = AutoLog.INSTANCE;
                String TAG = LocalImageUriProvider.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
                autoLog.error(TAG, Intrinsics.stringPlus("Failed to download and save image file ", fileForKey.getName()), e);
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
