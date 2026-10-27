package com.amazon.music.views.library.providers;

import android.view.View;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.views.ScopedView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: ContentEnabilityProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u001b\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0002H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0002H\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "", "shouldEnableContent", "isContentAccessAvailable", "", "handleContentAccessUnavailable", "Landroid/view/View;", "view", "Lcom/amazon/music/views/library/models/base/BaseViewContentModel;", "viewModel", "setContentEnabled", "isAvailable", "updateViewEnability", "contentMetadata", "isContentAvailable", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/views/library/providers/AvailabilityState;", "getAvailabilityState", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface ContentEnabilityProvider {
    AvailabilityState getAvailabilityState(ContentMetadata metadata);

    void handleContentAccessUnavailable(ContentMetadata metadata);

    boolean isContentAccessAvailable(ContentMetadata metadata);

    Object isContentAvailable(ContentMetadata contentMetadata, Continuation<? super Boolean> continuation);

    void setContentEnabled(View view, BaseViewContentModel viewModel);

    boolean shouldEnableContent(ContentMetadata metadata);

    void updateViewEnability(View view, boolean isAvailable);

    /* JADX INFO: compiled from: ContentEnabilityProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public static final class DefaultImpls {
        /* JADX WARN: Multi-variable type inference failed */
        public static void setContentEnabled(ContentEnabilityProvider contentEnabilityProvider, View view, BaseViewContentModel viewModel) {
            CoroutineScope limitedScope;
            Intrinsics.checkNotNullParameter(contentEnabilityProvider, "this");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(viewModel, "viewModel");
            ContentMetadata metadata = viewModel.getMetadata();
            boolean zShouldEnableContent = contentEnabilityProvider.shouldEnableContent(metadata);
            view.setEnabled(zShouldEnableContent);
            if (zShouldEnableContent) {
                ScopedView scopedView = view instanceof ScopedView ? (ScopedView) view : null;
                if (scopedView == null || (limitedScope = scopedView.getLimitedScope()) == null) {
                    return;
                }
                BuildersKt__Builders_commonKt.launch$default(limitedScope, null, null, contentEnabilityProvider.new AnonymousClass1(metadata, view, null), 3, null);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static void updateViewEnability(ContentEnabilityProvider contentEnabilityProvider, View view, boolean z) {
            Intrinsics.checkNotNullParameter(contentEnabilityProvider, "this");
            Intrinsics.checkNotNullParameter(view, "view");
            boolean z2 = view instanceof NestedContentView;
            View contentView = view;
            if (z2) {
                contentView = ((NestedContentView) view).getContentView();
            }
            contentView.setLayerType(z ? 0 : 2, null);
            contentView.setAlpha(z ? 1.0f : 0.3f);
        }

        public static Object isContentAvailable(ContentEnabilityProvider contentEnabilityProvider, ContentMetadata contentMetadata, Continuation<? super Boolean> continuation) {
            return Boxing.boxBoolean(contentEnabilityProvider.shouldEnableContent(contentMetadata) && contentEnabilityProvider.isContentAccessAvailable(contentMetadata));
        }

        public static AvailabilityState getAvailabilityState(ContentEnabilityProvider contentEnabilityProvider, ContentMetadata metadata) {
            Intrinsics.checkNotNullParameter(contentEnabilityProvider, "this");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            if (!contentEnabilityProvider.shouldEnableContent(metadata)) {
                return AvailabilityState.DISABLED;
            }
            if (contentEnabilityProvider.isContentAccessAvailable(metadata)) {
                return AvailabilityState.AVAILABLE;
            }
            return AvailabilityState.UNAVAILABLE;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.providers.ContentEnabilityProvider$setContentEnabled$1, reason: invalid class name */
    /* JADX INFO: compiled from: ContentEnabilityProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.views.library.providers.ContentEnabilityProvider$setContentEnabled$1", f = "ContentEnabilityProvider.kt", i = {}, l = {49}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ContentMetadata $contentMetadata;
        final /* synthetic */ View $view;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ContentMetadata contentMetadata, View view, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$contentMetadata = contentMetadata;
            this.$view = view;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ContentEnabilityProvider.this.new AnonymousClass1(this.$contentMetadata, this.$view, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                boolean zIsContentAccessAvailable = ContentEnabilityProvider.this.isContentAccessAvailable(this.$contentMetadata);
                MainCoroutineDispatcher main2 = Dispatchers.getMain();
                C01201 c01201 = new C01201(ContentEnabilityProvider.this, this.$view, zIsContentAccessAvailable, null);
                this.label = 1;
                if (BuildersKt.withContext(main2, c01201, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.music.views.library.providers.ContentEnabilityProvider$setContentEnabled$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ContentEnabilityProvider.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.music.views.library.providers.ContentEnabilityProvider$setContentEnabled$1$1", f = "ContentEnabilityProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ boolean $isAvailable;
            final /* synthetic */ View $view;
            int label;
            final /* synthetic */ ContentEnabilityProvider this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01201(ContentEnabilityProvider contentEnabilityProvider, View view, boolean z, Continuation<? super C01201> continuation) {
                super(2, continuation);
                this.this$0 = contentEnabilityProvider;
                this.$view = view;
                this.$isAvailable = z;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01201(this.this$0, this.$view, this.$isAvailable, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.updateViewEnability(this.$view, this.$isAvailable);
                return Unit.INSTANCE;
            }
        }
    }
}
