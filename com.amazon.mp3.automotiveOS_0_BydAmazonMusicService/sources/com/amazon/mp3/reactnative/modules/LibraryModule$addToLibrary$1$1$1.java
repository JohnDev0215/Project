package com.amazon.mp3.reactnative.modules;

import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.library.LibraryUseCase;
import com.facebook.react.bridge.Promise;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryModule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.reactnative.modules.LibraryModule$addToLibrary$1$1$1", f = "LibraryModule.kt", i = {}, l = {50, 53}, m = "invokeSuspend", n = {}, s = {})
final class LibraryModule$addToLibrary$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ EntityItemIdentifier $identifier;
    final /* synthetic */ LibraryUseCase<EntityItem> $interactor;
    final /* synthetic */ Promise $promise;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    LibraryModule$addToLibrary$1$1$1(LibraryUseCase<? extends EntityItem> libraryUseCase, EntityItemIdentifier entityItemIdentifier, Promise promise, Continuation<? super LibraryModule$addToLibrary$1$1$1> continuation) {
        super(2, continuation);
        this.$interactor = libraryUseCase;
        this.$identifier = entityItemIdentifier;
        this.$promise = promise;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryModule$addToLibrary$1$1$1(this.$interactor, this.$identifier, this.$promise, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryModule$addToLibrary$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Promise promise;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            LibraryUseCase<EntityItem> libraryUseCase = this.$interactor;
            EntityItemIdentifier entityItemIdentifier = this.$identifier;
            this.label = 1;
            obj = libraryUseCase.isInLibrary(entityItemIdentifier, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    promise = (Promise) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    promise.resolve(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj).booleanValue()) {
            this.$promise.resolve(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        }
        Promise promise2 = this.$promise;
        LibraryUseCase<EntityItem> libraryUseCase2 = this.$interactor;
        EntityItemIdentifier entityItemIdentifier2 = this.$identifier;
        this.L$0 = promise2;
        this.label = 2;
        Object objAddToLibrary = libraryUseCase2.addToLibrary(entityItemIdentifier2, this);
        if (objAddToLibrary == coroutine_suspended) {
            return coroutine_suspended;
        }
        promise = promise2;
        obj = objAddToLibrary;
        promise.resolve(obj);
        return Unit.INSTANCE;
    }
}
