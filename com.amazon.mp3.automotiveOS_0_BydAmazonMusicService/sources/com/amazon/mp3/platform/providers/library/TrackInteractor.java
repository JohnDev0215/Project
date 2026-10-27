package com.amazon.mp3.platform.providers.library;

import android.content.Context;
import com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper;
import com.amazon.mp3.platform.providers.Coroutines;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemSingle;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: TrackInteractor.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B#\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\u000bJ\u001b\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000bJ\u001b\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/platform/providers/library/TrackInteractor;", "Lcom/amazon/mp3/platform/providers/library/BaseInteractor;", "Lcom/amazon/music/platform/data/entity/EntityItemSingle;", "", "asin", "", "deletePrimeTrackCoroutine", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "identifier", "addToLibraryCoroutine", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addToLibrary", "isInLibrary", "removeFromLibrary", "Lcom/amazon/mp3/platform/mediaitem/TrackEntityItemHelper;", "trackEntityItemHelper", "Lcom/amazon/mp3/platform/mediaitem/TrackEntityItemHelper;", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/service/job/JobQueue;", "jobQueue", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/service/job/JobQueue;Lcom/amazon/mp3/platform/mediaitem/TrackEntityItemHelper;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TrackInteractor extends BaseInteractor<EntityItemSingle> {
    private static final String TAG = TrackInteractor.class.getSimpleName();
    private final TrackEntityItemHelper trackEntityItemHelper;

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibraryCoroutine$1, reason: invalid class name */
    /* JADX INFO: compiled from: TrackInteractor.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.TrackInteractor", f = "TrackInteractor.kt", i = {0, 0}, l = {125}, m = "addToLibraryCoroutine", n = {"this_$iv", "default$iv"}, s = {"L$0", "L$1"})
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
            return TrackInteractor.this.addToLibraryCoroutine(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.TrackInteractor$deletePrimeTrackCoroutine$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TrackInteractor.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.TrackInteractor", f = "TrackInteractor.kt", i = {0, 0}, l = {125}, m = "deletePrimeTrackCoroutine", n = {"this_$iv", "default$iv"}, s = {"L$0", "L$1"})
    static final class C03891 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C03891(Continuation<? super C03891> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TrackInteractor.this.deletePrimeTrackCoroutine(null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.TrackInteractor$isInLibrary$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TrackInteractor.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.TrackInteractor", f = "TrackInteractor.kt", i = {0}, l = {47}, m = "isInLibrary", n = {"identifier"}, s = {"L$0"})
    static final class C03901 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C03901(Continuation<? super C03901> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TrackInteractor.this.isInLibrary(null, this);
        }
    }

    public /* synthetic */ TrackInteractor(Context context, JobQueue jobQueue, TrackEntityItemHelper trackEntityItemHelper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new JobQueue(context) : jobQueue, (i & 4) != 0 ? new TrackEntityItemHelper(context) : trackEntityItemHelper);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrackInteractor(Context context, JobQueue jobQueue, TrackEntityItemHelper trackEntityItemHelper) {
        super(context, jobQueue);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(jobQueue, "jobQueue");
        Intrinsics.checkNotNullParameter(trackEntityItemHelper, "trackEntityItemHelper");
        this.trackEntityItemHelper = trackEntityItemHelper;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibrary$2, reason: invalid class name */
    /* JADX INFO: compiled from: TrackInteractor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibrary$2", f = "TrackInteractor.kt", i = {}, l = {42}, m = "invokeSuspend", n = {}, s = {})
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
            return TrackInteractor.this.new AnonymousClass2(this.$identifier, continuation);
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
                TrackInteractor trackInteractor = TrackInteractor.this;
                EntityItemIdentifier entityItemIdentifier = this.$identifier;
                this.label = 1;
                obj = trackInteractor.addToLibraryCoroutine(entityItemIdentifier, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            TrackInteractor trackInteractor2 = TrackInteractor.this;
            if (((Boolean) obj).booleanValue()) {
                trackInteractor2.notifyLibraryChanged();
            }
            return obj;
        }
    }

    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    public Object addToLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Coroutines.INSTANCE.getIoContext(), new AnonymousClass2(entityItemIdentifier, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object isInLibrary(com.amazon.music.platform.data.entity.EntityItemIdentifier r6, kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.amazon.mp3.platform.providers.library.TrackInteractor.C03901
            if (r0 == 0) goto L13
            r0 = r7
            com.amazon.mp3.platform.providers.library.TrackInteractor$isInLibrary$1 r0 = (com.amazon.mp3.platform.providers.library.TrackInteractor.C03901) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.platform.providers.library.TrackInteractor$isInLibrary$1 r0 = new com.amazon.mp3.platform.providers.library.TrackInteractor$isInLibrary$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L36
            if (r2 != r4) goto L2e
            java.lang.Object r6 = r0.L$0
            com.amazon.music.platform.data.entity.EntityItemIdentifier r6 = (com.amazon.music.platform.data.entity.EntityItemIdentifier) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Exception -> L53
            goto L46
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            kotlin.ResultKt.throwOnFailure(r7)
            com.amazon.mp3.platform.mediaitem.TrackEntityItemHelper r7 = r5.trackEntityItemHelper     // Catch: java.lang.Exception -> L53
            r0.L$0 = r6     // Catch: java.lang.Exception -> L53
            r0.label = r4     // Catch: java.lang.Exception -> L53
            java.lang.Object r7 = r7.m1360import(r6, r0)     // Catch: java.lang.Exception -> L53
            if (r7 != r1) goto L46
            return r1
        L46:
            com.amazon.mp3.library.item.MusicTrack r7 = (com.amazon.mp3.library.item.MusicTrack) r7     // Catch: java.lang.Exception -> L53
            if (r7 != 0) goto L4b
            goto L72
        L4b:
            boolean r6 = r7.isInLibrary()     // Catch: java.lang.Exception -> L53
            if (r6 != r4) goto L72
            r3 = r4
            goto L72
        L53:
            java.lang.String r7 = com.amazon.mp3.platform.providers.library.TrackInteractor.TAG
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "item with id="
            r0.append(r1)
            com.amazon.music.platform.data.entity.EntityId r6 = r6.getId()
            r0.append(r6)
            java.lang.String r6 = " doesn't exist"
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            com.amazon.mp3.util.Log.info(r7, r6)
        L72:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.library.TrackInteractor.isInLibrary(com.amazon.music.platform.data.entity.EntityItemIdentifier, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.TrackInteractor$removeFromLibrary$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TrackInteractor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.TrackInteractor$removeFromLibrary$2", f = "TrackInteractor.kt", i = {0}, l = {58}, m = "invokeSuspend", n = {"identifierId"}, s = {"L$0"})
    static final class C03912 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ EntityItemIdentifier $identifier;
        Object L$0;
        int label;
        final /* synthetic */ TrackInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03912(EntityItemIdentifier entityItemIdentifier, TrackInteractor trackInteractor, Continuation<? super C03912> continuation) {
            super(2, continuation);
            this.$identifier = entityItemIdentifier;
            this.this$0 = trackInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03912(this.$identifier, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C03912) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0076  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L1c
                if (r1 != r3) goto L14
                java.lang.Object r0 = r5.L$0
                java.lang.String r0 = (java.lang.String) r0
                kotlin.ResultKt.throwOnFailure(r6)
                goto L4a
            L14:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1c:
                kotlin.ResultKt.throwOnFailure(r6)
                com.amazon.music.platform.data.entity.EntityItemIdentifier r6 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityId r6 = r6.getId()
                java.lang.String r6 = r6.getIdentifier()
                com.amazon.music.platform.data.entity.EntityItemIdentifier r1 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityId r1 = r1.getId()
                java.lang.String r1 = r1.getIdentifierType()
                java.lang.String r4 = "ASIN"
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r4)
                if (r4 == 0) goto L51
                com.amazon.mp3.platform.providers.library.TrackInteractor r1 = r5.this$0
                r5.L$0 = r6
                r5.label = r3
                java.lang.Object r1 = com.amazon.mp3.platform.providers.library.TrackInteractor.access$deletePrimeTrackCoroutine(r1, r6, r5)
                if (r1 != r0) goto L48
                return r0
            L48:
                r0 = r6
                r6 = r1
            L4a:
                java.lang.Boolean r6 = (java.lang.Boolean) r6
                boolean r6 = r6.booleanValue()
                goto L6a
            L51:
                java.lang.String r0 = "OBJECT_ID"
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
                if (r0 == 0) goto L68
                java.util.List r0 = kotlin.collections.CollectionsKt.listOf(r6)
                java.lang.String r1 = "cirrus"
                int r0 = com.amazon.mp3.library.provider.source.local.DeleteUtil.deleteTracksByLuids(r1, r0)
                if (r0 <= 0) goto L68
                r0 = r6
                r6 = r3
                goto L6a
            L68:
                r0 = r6
                r6 = r2
            L6a:
                if (r6 == 0) goto L76
                com.amazon.mp3.platform.providers.library.TrackInteractor r6 = r5.this$0
                r6.notifyLibraryChanged()
                java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                return r6
            L76:
                java.lang.Exception r6 = new java.lang.Exception
                java.lang.String r1 = "Can't remove from library: "
                java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r0)
                r6.<init>(r0)
                java.lang.String r0 = com.amazon.mp3.platform.providers.library.TrackInteractor.access$getTAG$cp()
                java.lang.String r1 = "removeFromLibrary failed"
                com.amazon.mp3.util.Log.error(r0, r1, r6)
                java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.library.TrackInteractor.C03912.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    public Object removeFromLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Coroutines.INSTANCE.getIoContext(), new C03912(entityItemIdentifier, this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object deletePrimeTrackCoroutine(java.lang.String r11, kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.amazon.mp3.platform.providers.library.TrackInteractor.C03891
            if (r0 == 0) goto L13
            r0 = r12
            com.amazon.mp3.platform.providers.library.TrackInteractor$deletePrimeTrackCoroutine$1 r0 = (com.amazon.mp3.platform.providers.library.TrackInteractor.C03891) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.platform.providers.library.TrackInteractor$deletePrimeTrackCoroutine$1 r0 = new com.amazon.mp3.platform.providers.library.TrackInteractor$deletePrimeTrackCoroutine$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r11 = r0.L$1
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            java.lang.Object r0 = r0.L$0
            com.amazon.mp3.platform.providers.Coroutines r0 = (com.amazon.mp3.platform.providers.Coroutines) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L31
            goto L7d
        L31:
            r12 = move-exception
            goto L5e
        L33:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3b:
            kotlin.ResultKt.throwOnFailure(r12)
            com.amazon.mp3.platform.providers.Coroutines r12 = com.amazon.mp3.platform.providers.Coroutines.INSTANCE
            r2 = 0
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            r4 = 15000(0x3a98, double:7.411E-320)
            com.amazon.mp3.platform.providers.library.TrackInteractor$deletePrimeTrackCoroutine$$inlined$suspendCoroutineWithTimeout$default$1 r6 = new com.amazon.mp3.platform.providers.library.TrackInteractor$deletePrimeTrackCoroutine$$inlined$suspendCoroutineWithTimeout$default$1     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            r7 = 0
            r6.<init>(r7, r10, r11)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            r0.L$0 = r12     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            r0.L$1 = r2     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            r0.label = r3     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            java.lang.Object r12 = kotlinx.coroutines.TimeoutKt.withTimeout(r4, r6, r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            if (r12 != r1) goto L7d
            return r1
        L5a:
            r11 = move-exception
            r0 = r12
            r12 = r11
            r11 = r2
        L5e:
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
        L7d:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.library.TrackInteractor.deletePrimeTrackCoroutine(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object addToLibraryCoroutine(com.amazon.music.platform.data.entity.EntityItemIdentifier r11, kotlin.coroutines.Continuation<? super java.lang.Boolean> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof com.amazon.mp3.platform.providers.library.TrackInteractor.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r12
            com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibraryCoroutine$1 r0 = (com.amazon.mp3.platform.providers.library.TrackInteractor.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibraryCoroutine$1 r0 = new com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibraryCoroutine$1
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L33
            java.lang.Object r11 = r0.L$1
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            java.lang.Object r0 = r0.L$0
            com.amazon.mp3.platform.providers.Coroutines r0 = (com.amazon.mp3.platform.providers.Coroutines) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L31
            goto L7d
        L31:
            r12 = move-exception
            goto L5e
        L33:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3b:
            kotlin.ResultKt.throwOnFailure(r12)
            com.amazon.mp3.platform.providers.Coroutines r12 = com.amazon.mp3.platform.providers.Coroutines.INSTANCE
            r2 = 0
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
            r4 = 15000(0x3a98, double:7.411E-320)
            com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibraryCoroutine$$inlined$suspendCoroutineWithTimeout$default$1 r6 = new com.amazon.mp3.platform.providers.library.TrackInteractor$addToLibraryCoroutine$$inlined$suspendCoroutineWithTimeout$default$1     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            r7 = 0
            r6.<init>(r7, r10, r11)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            r0.L$0 = r12     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            r0.L$1 = r2     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            r0.label = r3     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            java.lang.Object r12 = kotlinx.coroutines.TimeoutKt.withTimeout(r4, r6, r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L5a
            if (r12 != r1) goto L7d
            return r1
        L5a:
            r11 = move-exception
            r0 = r12
            r12 = r11
            r11 = r2
        L5e:
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
        L7d:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.library.TrackInteractor.addToLibraryCoroutine(com.amazon.music.platform.data.entity.EntityItemIdentifier, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
