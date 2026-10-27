package com.amazon.music.share.menu;

import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.image.ImageUrlRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: SharingFragmentV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.music.share.menu.SharingFragmentV2$bindThumbnail$2$1", f = "SharingFragmentV2.kt", i = {}, l = {245, 247}, m = "invokeSuspend", n = {}, s = {})
final class SharingFragmentV2$bindThumbnail$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ShareableContent $content;
    final /* synthetic */ Ref.ObjectRef<String> $imageUrl;
    final /* synthetic */ SharingFragmentV2 $this_run;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SharingFragmentV2$bindThumbnail$2$1(Ref.ObjectRef<String> objectRef, SharingFragmentV2 sharingFragmentV2, ShareableContent shareableContent, Continuation<? super SharingFragmentV2$bindThumbnail$2$1> continuation) {
        super(2, continuation);
        this.$imageUrl = objectRef;
        this.$this_run = sharingFragmentV2;
        this.$content = shareableContent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SharingFragmentV2$bindThumbnail$2$1(this.$imageUrl, this.$this_run, this.$content, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SharingFragmentV2$bindThumbnail$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref.ObjectRef<String> objectRef;
        T t;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            objectRef = this.$imageUrl;
            ImageUrlRepository imageUrlRepository = this.$this_run.getImageUrlRepository();
            String entityId = this.$content.getEntityId();
            EntityItemType platformEntityItemType = SharingUtil.INSTANCE.toPlatformEntityItemType(this.$content.getEntityType());
            this.L$0 = objectRef;
            this.label = 1;
            Object imageUrl = imageUrlRepository.getImageUrl(entityId, platformEntityItemType, this);
            t = imageUrl;
            if (imageUrl == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
            t = obj;
        }
        objectRef.element = t;
        this.$content.setImage(this.$imageUrl.element);
        MainCoroutineDispatcher main2 = Dispatchers.getMain();
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_run, this.$content, this.$imageUrl, null);
        this.L$0 = null;
        this.label = 2;
        if (BuildersKt.withContext(main2, anonymousClass1, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragmentV2$bindThumbnail$2$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: SharingFragmentV2.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragmentV2$bindThumbnail$2$1$1", f = "SharingFragmentV2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ShareableContent $content;
        final /* synthetic */ Ref.ObjectRef<String> $imageUrl;
        final /* synthetic */ SharingFragmentV2 $this_run;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SharingFragmentV2 sharingFragmentV2, ShareableContent shareableContent, Ref.ObjectRef<String> objectRef, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_run = sharingFragmentV2;
            this.$content = shareableContent;
            this.$imageUrl = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$this_run, this.$content, this.$imageUrl, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$this_run.loadThumbnailAndRebind(this.$content, this.$imageUrl.element);
            return Unit.INSTANCE;
        }
    }
}
