package com.amazon.mp3.platform.providers.library;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.platform.providers.Coroutines;
import com.amazon.mp3.prime.cta.CtaAlbumRequester;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.music.platform.data.entity.EntityItemCollection;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: AlbumInteractor.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u001b\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000bJ\u001b\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000bR!\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/platform/providers/library/AlbumInteractor;", "Lcom/amazon/mp3/platform/providers/library/BaseInteractor;", "Lcom/amazon/music/platform/data/entity/EntityItemCollection;", "", "albumAsin", "", "addAlbumToLibrary", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "identifier", "addToLibrary", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isInLibrary", "removeFromLibrary", "Lkotlinx/coroutines/Deferred;", "Lcom/amazon/mp3/prime/cta/CtaAlbumRequester;", "albumRequester$delegate", "Lkotlin/Lazy;", "getAlbumRequester", "()Lkotlinx/coroutines/Deferred;", "albumRequester", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/service/job/JobQueue;", "jobQueue", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/service/job/JobQueue;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlbumInteractor extends BaseInteractor<EntityItemCollection> {

    /* JADX INFO: renamed from: albumRequester$delegate, reason: from kotlin metadata */
    private final Lazy albumRequester;

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$1, reason: invalid class name */
    /* JADX INFO: compiled from: AlbumInteractor.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.AlbumInteractor", f = "AlbumInteractor.kt", i = {0, 0, 1, 1}, l = {65, 84}, m = "addAlbumToLibrary", n = {"this", "albumAsin", "this_$iv", "default$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AlbumInteractor.this.addAlbumToLibrary(null, this);
        }
    }

    public /* synthetic */ AlbumInteractor(Context context, JobQueue jobQueue, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new JobQueue(context) : jobQueue);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumInteractor(final Context context, JobQueue jobQueue) {
        super(context, jobQueue);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(jobQueue, "jobQueue");
        this.albumRequester = LazyKt__LazyJVMKt.lazy(new Function0<Deferred<? extends CtaAlbumRequester>>() { // from class: com.amazon.mp3.platform.providers.library.AlbumInteractor$albumRequester$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.AlbumInteractor$albumRequester$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: AlbumInteractor.kt */
            @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/prime/cta/CtaAlbumRequester;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
            @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.AlbumInteractor$albumRequester$2$1", f = "AlbumInteractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CtaAlbumRequester>, Object> {
                final /* synthetic */ Context $context;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(Context context, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.$context = context;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.$context, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
                public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super CtaAlbumRequester> continuation) {
                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return new CtaAlbumRequester(this.$context, false);
                }
            }

            @Override // kotlin.jvm.functions.Function0
            public final Deferred<? extends CtaAlbumRequester> invoke() {
                return BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(context, null), 3, null);
            }
        });
    }

    private final Deferred<CtaAlbumRequester> getAlbumRequester() {
        return (Deferred) this.albumRequester.getValue();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.AlbumInteractor$addToLibrary$2, reason: invalid class name */
    /* JADX INFO: compiled from: AlbumInteractor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.AlbumInteractor$addToLibrary$2", f = "AlbumInteractor.kt", i = {}, l = {35}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ EntityItemIdentifier $identifier;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(EntityItemIdentifier entityItemIdentifier, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$identifier = entityItemIdentifier;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AlbumInteractor.this.new AnonymousClass2(this.$identifier, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AlbumInteractor albumInteractor = AlbumInteractor.this;
                String identifier = this.$identifier.getId().getIdentifier();
                this.label = 1;
                obj = albumInteractor.addAlbumToLibrary(identifier, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AlbumInteractor albumInteractor2 = AlbumInteractor.this;
            if (((Boolean) obj).booleanValue()) {
                albumInteractor2.notifyLibraryChanged();
            }
            return obj;
        }
    }

    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    public Object addToLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Coroutines.INSTANCE.getIoContext(), new AnonymousClass2(entityItemIdentifier, null), continuation);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.AlbumInteractor$isInLibrary$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AlbumInteractor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.AlbumInteractor$isInLibrary$2", f = "AlbumInteractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03842 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ EntityItemIdentifier $identifier;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03842(EntityItemIdentifier entityItemIdentifier, Continuation<? super C03842> continuation) {
            super(2, continuation);
            this.$identifier = entityItemIdentifier;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AlbumInteractor.this.new C03842(this.$identifier, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C03842) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(new QueryAlbumByAsin(AlbumInteractor.this.getContext()).findCollectionUriByAsin(this.$identifier.getId().getIdentifier()) != null);
        }
    }

    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    public Object isInLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Coroutines.INSTANCE.getIoContext(), new C03842(entityItemIdentifier, null), continuation);
    }

    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    public Object removeFromLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Coroutines.INSTANCE.getIoContext(), new C03852(entityItemIdentifier, null), continuation);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.AlbumInteractor$removeFromLibrary$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AlbumInteractor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.AlbumInteractor$removeFromLibrary$2", f = "AlbumInteractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03852 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ EntityItemIdentifier $identifier;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03852(EntityItemIdentifier entityItemIdentifier, Continuation<? super C03852> continuation) {
            super(2, continuation);
            this.$identifier = entityItemIdentifier;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AlbumInteractor.this.new C03852(this.$identifier, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C03852) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                Uri uriFindCollectionUriByAsin = new QueryAlbumByAsin(AlbumInteractor.this.getContext()).findCollectionUriByAsin(this.$identifier.getId().getIdentifier());
                if (uriFindCollectionUriByAsin != null) {
                    AlbumInteractor albumInteractor = AlbumInteractor.this;
                    albumInteractor.getContext().getContentResolver().delete(uriFindCollectionUriByAsin, null, null);
                    albumInteractor.notifyLibraryChanged();
                    return Boxing.boxBoolean(true);
                }
                return Boxing.boxBoolean(false);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(2:4|(1:6)(1:7))(0)|8|(1:(1:(5:12|35|13|31|32)(2:17|18))(1:19))(2:20|(1:22)(1:23))|24|33|25|(1:27)|31|32) */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x007d, code lost:
    
        r12 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x007e, code lost:
    
        r0 = r4;
        r11 = r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object addAlbumToLibrary(java.lang.String r11, kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.amazon.mp3.platform.providers.library.AlbumInteractor.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r12
            com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$1 r0 = (com.amazon.mp3.platform.providers.library.AlbumInteractor.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$1 r0 = new com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4a
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            java.lang.Object r11 = r0.L$1
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            java.lang.Object r0 = r0.L$0
            com.amazon.mp3.platform.providers.Coroutines r0 = (com.amazon.mp3.platform.providers.Coroutines) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L34
            goto L9f
        L34:
            r12 = move-exception
            goto L80
        L36:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3e:
            java.lang.Object r11 = r0.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r2 = r0.L$0
            com.amazon.mp3.platform.providers.library.AlbumInteractor r2 = (com.amazon.mp3.platform.providers.library.AlbumInteractor) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L5f
        L4a:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.Deferred r12 = r10.getAlbumRequester()
            r0.L$0 = r10
            r0.L$1 = r11
            r0.label = r4
            java.lang.Object r12 = r12.await(r0)
            if (r12 != r1) goto L5e
            return r1
        L5e:
            r2 = r10
        L5f:
            com.amazon.mp3.prime.cta.CtaAlbumRequester r12 = (com.amazon.mp3.prime.cta.CtaAlbumRequester) r12
            com.amazon.mp3.platform.providers.Coroutines r4 = com.amazon.mp3.platform.providers.Coroutines.INSTANCE
            r5 = 0
            java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            r6 = 15000(0x3a98, double:7.411E-320)
            com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$lambda-3$$inlined$suspendCoroutineWithTimeout$default$1 r8 = new com.amazon.mp3.platform.providers.library.AlbumInteractor$addAlbumToLibrary$lambda-3$$inlined$suspendCoroutineWithTimeout$default$1     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L7d
            r9 = 0
            r8.<init>(r9, r12, r11, r2)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L7d
            r0.L$0 = r4     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L7d
            r0.L$1 = r5     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L7d
            r0.label = r3     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L7d
            java.lang.Object r12 = kotlinx.coroutines.TimeoutKt.withTimeout(r6, r8, r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L7d
            if (r12 != r1) goto L9f
            return r1
        L7d:
            r12 = move-exception
            r0 = r4
            r11 = r5
        L80:
            java.lang.String r0 = r0.getTAG()
            java.lang.StackTraceElement[] r1 = r12.getStackTrace()
            java.lang.String r12 = "e.stackTrace"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r12)
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 62
            r9 = 0
            java.lang.String r2 = "\n"
            java.lang.String r12 = kotlin.collections.ArraysKt.joinToString$default(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            com.amazon.mp3.util.Log.error(r0, r12)
            r12 = r11
        L9f:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r11 = r12.booleanValue()
            java.lang.Boolean r11 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.library.AlbumInteractor.addAlbumToLibrary(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
