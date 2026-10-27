package com.amazon.mp3.voice.hints;

import com.amazon.music.platform.aom.hints.AlexaContextualHintsProvider;
import com.amazon.music.platform.aom.hints.AlexaHintsRepository;
import com.amazon.music.platform.aom.hints.provider.GUIContextProvider;
import com.amazon.music.voice.ui.AlexaHintsViewModel;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: AlexaHintsViewModelImpl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\"\u0010\u0012\u001a\u00020\u00102\u0018\u0010\u0013\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0004\u0012\u00020\u00100\u0014H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/voice/hints/AlexaHintsViewModelImpl;", "Lcom/amazon/music/voice/ui/AlexaHintsViewModel;", "alexaHintsRepository", "Lcom/amazon/music/platform/aom/hints/AlexaHintsRepository;", "guiContextProvider", "Lcom/amazon/music/platform/aom/hints/provider/GUIContextProvider;", "(Lcom/amazon/music/platform/aom/hints/AlexaHintsRepository;Lcom/amazon/music/platform/aom/hints/provider/GUIContextProvider;)V", "alexaContextualHintsProvider", "Lcom/amazon/music/platform/aom/hints/AlexaContextualHintsProvider;", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineJob", "Lkotlinx/coroutines/CompletableJob;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "cancelHintsFetch", "", "clearHints", "fetchHints", "onHintsFetched", "Lkotlin/Function1;", "", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaHintsViewModelImpl implements AlexaHintsViewModel {
    private static final String TAG = AlexaHintsViewModel.class.getSimpleName();
    private final AlexaContextualHintsProvider alexaContextualHintsProvider;
    private final AlexaHintsRepository alexaHintsRepository;
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CompletableJob coroutineJob;
    private final CoroutineScope coroutineScope;
    private final GUIContextProvider guiContextProvider;

    public AlexaHintsViewModelImpl(AlexaHintsRepository alexaHintsRepository, GUIContextProvider guiContextProvider) {
        Intrinsics.checkNotNullParameter(alexaHintsRepository, "alexaHintsRepository");
        Intrinsics.checkNotNullParameter(guiContextProvider, "guiContextProvider");
        this.alexaHintsRepository = alexaHintsRepository;
        this.guiContextProvider = guiContextProvider;
        this.alexaContextualHintsProvider = new AlexaContextualHintsProvider(alexaHintsRepository);
        CompletableJob completableJobSupervisorJob$default = SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null);
        this.coroutineJob = completableJobSupervisorJob$default;
        AlexaHintsViewModelImpl$special$$inlined$CoroutineExceptionHandler$1 alexaHintsViewModelImpl$special$$inlined$CoroutineExceptionHandler$1 = new AlexaHintsViewModelImpl$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE);
        this.coroutineExceptionHandler = alexaHintsViewModelImpl$special$$inlined$CoroutineExceptionHandler$1;
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(completableJobSupervisorJob$default).plus(alexaHintsViewModelImpl$special$$inlined$CoroutineExceptionHandler$1));
    }

    /* JADX INFO: renamed from: com.amazon.mp3.voice.hints.AlexaHintsViewModelImpl$fetchHints$1, reason: invalid class name */
    /* JADX INFO: compiled from: AlexaHintsViewModelImpl.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.voice.hints.AlexaHintsViewModelImpl$fetchHints$1", f = "AlexaHintsViewModelImpl.kt", i = {}, l = {46, 47}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<List<String>, Unit> $onHintsFetched;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super List<String>, Unit> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$onHintsFetched = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AlexaHintsViewModelImpl.this.new AnonymousClass1(this.$onHintsFetched, continuation);
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
                AlexaContextualHintsProvider alexaContextualHintsProvider = AlexaHintsViewModelImpl.this.alexaContextualHintsProvider;
                GUIContextProvider gUIContextProvider = AlexaHintsViewModelImpl.this.guiContextProvider;
                this.label = 1;
                obj = AlexaContextualHintsProvider.getContextualHints$default(alexaContextualHintsProvider, gUIContextProvider, 0, this, 2, null);
                if (obj == coroutine_suspended) {
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
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            C01051 c01051 = new C01051(this.$onHintsFetched, (List) obj, null);
            this.label = 2;
            if (BuildersKt.withContext(main2, c01051, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.mp3.voice.hints.AlexaHintsViewModelImpl$fetchHints$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AlexaHintsViewModelImpl.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.mp3.voice.hints.AlexaHintsViewModelImpl$fetchHints$1$1", f = "AlexaHintsViewModelImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ List<String> $contextualHints;
            final /* synthetic */ Function1<List<String>, Unit> $onHintsFetched;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C01051(Function1<? super List<String>, Unit> function1, List<String> list, Continuation<? super C01051> continuation) {
                super(2, continuation);
                this.$onHintsFetched = function1;
                this.$contextualHints = list;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01051(this.$onHintsFetched, this.$contextualHints, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.$onHintsFetched.invoke(this.$contextualHints);
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.music.voice.ui.AlexaHintsViewModel
    public void fetchHints(Function1<? super List<String>, Unit> onHintsFetched) {
        Intrinsics.checkNotNullParameter(onHintsFetched, "onHintsFetched");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(onHintsFetched, null), 3, null);
    }

    @Override // com.amazon.music.voice.ui.AlexaHintsViewModel
    public void cancelHintsFetch() {
        CoroutineScopeKt.cancel(this.coroutineScope, new CancellationException());
    }

    @Override // com.amazon.music.voice.ui.AlexaHintsViewModel
    public void clearHints() {
        this.alexaHintsRepository.resetAlexaHintsCache();
    }
}
