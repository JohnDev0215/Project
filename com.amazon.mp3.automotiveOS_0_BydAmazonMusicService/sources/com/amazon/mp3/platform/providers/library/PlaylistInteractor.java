package com.amazon.mp3.platform.providers.library;

import android.content.Context;
import android.database.Cursor;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper;
import com.amazon.mp3.platform.providers.Coroutines;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.service.job.JobQueue;
import com.amazon.music.platform.data.entity.EntityItemCollection;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.EntityItemSingle;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: PlaylistInteractor.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0014B#\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u001b\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\t\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\bJ\u001b\u0010\n\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\n\u0010\bR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/platform/providers/library/PlaylistInteractor;", "Lcom/amazon/mp3/platform/providers/library/BaseInteractor;", "Lcom/amazon/music/platform/data/entity/EntityItemCollection;", "Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;", "identifier", "getUdoPlaylist", "", "addToLibrary", "(Lcom/amazon/music/platform/data/entity/EntityItemIdentifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isInLibrary", "removeFromLibrary", "Lcom/amazon/mp3/playlist/db/PrimePlaylistDatabaseManager;", "primePlaylistDatabaseManager", "Lcom/amazon/mp3/playlist/db/PrimePlaylistDatabaseManager;", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/service/job/JobQueue;", "jobQueue", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/service/job/JobQueue;Lcom/amazon/mp3/playlist/db/PrimePlaylistDatabaseManager;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistInteractor extends BaseInteractor<EntityItemCollection> {
    private static final String TAG = PlaylistInteractor.class.getSimpleName();
    private final PrimePlaylistDatabaseManager primePlaylistDatabaseManager;

    public /* synthetic */ PlaylistInteractor(Context context, JobQueue jobQueue, PrimePlaylistDatabaseManager primePlaylistDatabaseManager, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new JobQueue(context) : jobQueue, (i & 4) != 0 ? new PrimePlaylistDatabaseManager() : primePlaylistDatabaseManager);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistInteractor(Context context, JobQueue jobQueue, PrimePlaylistDatabaseManager primePlaylistDatabaseManager) {
        super(context, jobQueue);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(jobQueue, "jobQueue");
        Intrinsics.checkNotNullParameter(primePlaylistDatabaseManager, "primePlaylistDatabaseManager");
        this.primePlaylistDatabaseManager = primePlaylistDatabaseManager;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.PlaylistInteractor$addToLibrary$2, reason: invalid class name */
    /* JADX INFO: compiled from: PlaylistInteractor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.PlaylistInteractor$addToLibrary$2", f = "PlaylistInteractor.kt", i = {}, l = {42}, m = "invokeSuspend", n = {}, s = {})
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
            return PlaylistInteractor.this.new AnonymousClass2(this.$identifier, continuation);
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
                PlaylistEntityItemHelper playlistEntityItemHelper = new PlaylistEntityItemHelper(PlaylistInteractor.this.getContext());
                EntityItemIdentifier entityItemIdentifier = this.$identifier;
                JobQueue jobQueue = PlaylistInteractor.this.getJobQueue();
                this.label = 1;
                obj = playlistEntityItemHelper.m1359import(entityItemIdentifier, true, jobQueue, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (obj != null) {
                return Boxing.boxBoolean(true);
            }
            return Boxing.boxBoolean(false);
        }
    }

    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    public Object addToLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Coroutines.INSTANCE.getIoContext(), new AnonymousClass2(entityItemIdentifier, null), continuation);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.PlaylistInteractor$isInLibrary$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistInteractor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.PlaylistInteractor$isInLibrary$2", f = "PlaylistInteractor.kt", i = {}, l = {88}, m = "invokeSuspend", n = {}, s = {})
    static final class C03872 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ EntityItemIdentifier $identifier;
        int label;
        final /* synthetic */ PlaylistInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03872(EntityItemIdentifier entityItemIdentifier, PlaylistInteractor playlistInteractor, Continuation<? super C03872> continuation) {
            super(2, continuation);
            this.$identifier = entityItemIdentifier;
            this.this$0 = playlistInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03872(this.$identifier, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C03872) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x0064, code lost:
        
            if (r6.equals("PLAYLIST_ID") == false) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x006d, code lost:
        
            if (r6.equals("ASIN") == false) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0087, code lost:
        
            r6 = new com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper(r5.this$0.getContext());
            r1 = r5.$identifier;
            r4 = r5.this$0.getJobQueue();
            r5.label = 1;
            r6 = r6.m1359import(r1, false, r4, r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00a0, code lost:
        
            if (r6 != r0) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00a2, code lost:
        
            return r0;
         */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00ae  */
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
                if (r1 == 0) goto L19
                if (r1 != r3) goto L11
                kotlin.ResultKt.throwOnFailure(r6)
                goto La3
            L11:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L19:
                kotlin.ResultKt.throwOnFailure(r6)
                com.amazon.music.platform.data.entity.EntityItemIdentifier r6 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityId r6 = r6.getId()
                java.lang.String r6 = r6.getIdentifierType()
                int r1 = r6.hashCode()
                r4 = 2018519(0x1eccd7, float:2.828548E-39)
                if (r1 == r4) goto L67
                r4 = 93508264(0x592d2a8, float:1.3807157E-35)
                if (r1 == r4) goto L5e
                r0 = 785431604(0x2ed0bc34, float:9.4921765E-11)
                if (r1 == r0) goto L3a
                goto L6f
            L3a:
                java.lang.String r0 = "USER_PLAYLIST_ID"
                boolean r6 = r6.equals(r0)
                if (r6 != 0) goto L43
                goto L6f
            L43:
                com.amazon.mp3.platform.providers.library.PlaylistInteractor r6 = r5.this$0
                com.amazon.music.platform.data.entity.EntityItemIdentifier r0 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityItemCollection r6 = com.amazon.mp3.platform.providers.library.PlaylistInteractor.access$getUdoPlaylist(r6, r0)
                java.util.List r6 = r6.getItems()
                if (r6 == 0) goto L5a
                boolean r6 = r6.isEmpty()
                if (r6 == 0) goto L58
                goto L5a
            L58:
                r6 = r2
                goto L5b
            L5a:
                r6 = r3
            L5b:
                if (r6 != 0) goto Laf
                goto Lae
            L5e:
                java.lang.String r1 = "PLAYLIST_ID"
                boolean r6 = r6.equals(r1)
                if (r6 != 0) goto L87
                goto L6f
            L67:
                java.lang.String r1 = "ASIN"
                boolean r6 = r6.equals(r1)
                if (r6 != 0) goto L87
            L6f:
                java.lang.String r6 = com.amazon.mp3.platform.providers.library.PlaylistInteractor.access$getTAG$cp()
                com.amazon.music.platform.data.entity.EntityItemIdentifier r0 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityId r0 = r0.getId()
                java.lang.String r0 = r0.getIdentifierType()
                java.lang.String r1 = "isInLibrary: unsupported type "
                java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r0)
                com.amazon.mp3.util.Log.error(r6, r0)
                goto Laf
            L87:
                com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper r6 = new com.amazon.mp3.platform.mediaitem.PlaylistEntityItemHelper
                com.amazon.mp3.platform.providers.library.PlaylistInteractor r1 = r5.this$0
                android.content.Context r1 = r1.getContext()
                r6.<init>(r1)
                com.amazon.music.platform.data.entity.EntityItemIdentifier r1 = r5.$identifier
                com.amazon.mp3.platform.providers.library.PlaylistInteractor r4 = r5.this$0
                com.amazon.mp3.service.job.JobQueue r4 = r4.getJobQueue()
                r5.label = r3
                java.lang.Object r6 = r6.m1359import(r1, r2, r4, r5)
                if (r6 != r0) goto La3
                return r0
            La3:
                com.amazon.mp3.playlist.CatalogPlaylist r6 = (com.amazon.mp3.playlist.CatalogPlaylist) r6
                if (r6 != 0) goto La8
                goto Laf
            La8:
                boolean r6 = r6.isInLibrary()
                if (r6 != r3) goto Laf
            Lae:
                r2 = r3
            Laf:
                java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.library.PlaylistInteractor.C03872.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    public Object isInLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Coroutines.INSTANCE.getIoContext(), new C03872(entityItemIdentifier, this, null), continuation);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.platform.providers.library.PlaylistInteractor$removeFromLibrary$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlaylistInteractor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.platform.providers.library.PlaylistInteractor$removeFromLibrary$2", f = "PlaylistInteractor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03882 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ EntityItemIdentifier $identifier;
        int label;
        final /* synthetic */ PlaylistInteractor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03882(EntityItemIdentifier entityItemIdentifier, PlaylistInteractor playlistInteractor, Continuation<? super C03882> continuation) {
            super(2, continuation);
            this.$identifier = entityItemIdentifier;
            this.this$0 = playlistInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03882(this.$identifier, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C03882) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0081  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r5.label
                if (r0 != 0) goto Lb0
                kotlin.ResultKt.throwOnFailure(r6)
                com.amazon.music.platform.data.entity.EntityItemIdentifier r6 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityId r6 = r6.getId()
                java.lang.String r6 = r6.getIdentifierType()
                int r0 = r6.hashCode()
                r1 = 2018519(0x1eccd7, float:2.828548E-39)
                r2 = 1
                r3 = 0
                if (r0 == r1) goto L5f
                r1 = 93508264(0x592d2a8, float:1.3807157E-35)
                if (r0 == r1) goto L56
                r1 = 785431604(0x2ed0bc34, float:9.4921765E-11)
                if (r0 == r1) goto L2a
                goto L67
            L2a:
                java.lang.String r0 = "USER_PLAYLIST_ID"
                boolean r6 = r6.equals(r0)
                if (r6 != 0) goto L33
                goto L67
            L33:
                com.amazon.mp3.platform.providers.library.PlaylistInteractor r6 = r5.this$0
                android.content.Context r6 = r6.getContext()
                android.content.ContentResolver r6 = r6.getContentResolver()
                com.amazon.music.platform.data.entity.EntityItemIdentifier r0 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityId r0 = r0.getId()
                java.lang.String r0 = r0.getIdentifier()
                long r0 = java.lang.Long.parseLong(r0)
                java.lang.String r3 = "cirrus-local"
                android.net.Uri r0 = com.amazon.mp3.library.provider.MediaProvider.UdoPlaylists.getContentUri(r3, r0)
                r1 = 0
                r6.delete(r0, r1, r1)
                goto Lab
            L56:
                java.lang.String r0 = "PLAYLIST_ID"
                boolean r6 = r6.equals(r0)
                if (r6 != 0) goto L81
                goto L67
            L5f:
                java.lang.String r0 = "ASIN"
                boolean r6 = r6.equals(r0)
                if (r6 != 0) goto L81
            L67:
                java.lang.String r6 = com.amazon.mp3.platform.providers.library.PlaylistInteractor.access$getTAG$cp()
                com.amazon.music.platform.data.entity.EntityItemIdentifier r0 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityId r0 = r0.getId()
                java.lang.String r0 = r0.getIdentifierType()
                java.lang.String r1 = "removeFromLibrary: unsupported type "
                java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r1, r0)
                com.amazon.mp3.util.Log.error(r6, r0)
                r2 = r3
                goto Lab
            L81:
                com.amazon.mp3.platform.providers.library.PlaylistInteractor r6 = r5.this$0
                com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager r6 = com.amazon.mp3.platform.providers.library.PlaylistInteractor.access$getPrimePlaylistDatabaseManager$p(r6)
                com.amazon.music.platform.data.entity.EntityItemIdentifier r0 = r5.$identifier
                com.amazon.music.platform.data.entity.EntityId r0 = r0.getId()
                java.lang.String r0 = r0.getIdentifier()
                com.amazon.mp3.playlist.CatalogPlaylist r6 = r6.getPlaylist(r0, r3)
                com.amazon.mp3.platform.providers.library.PlaylistInteractor r0 = r5.this$0
                com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager r0 = com.amazon.mp3.platform.providers.library.PlaylistInteractor.access$getPrimePlaylistDatabaseManager$p(r0)
                java.lang.String r6 = r6.getId()
                java.lang.String r1 = "playlist.id"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)
                long r3 = java.lang.Long.parseLong(r6)
                r0.delete(r3)
            Lab:
                java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                return r6
            Lb0:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.platform.providers.library.PlaylistInteractor.C03882.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // com.amazon.music.platform.data.library.LibraryUseCase
    public Object removeFromLibrary(EntityItemIdentifier entityItemIdentifier, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Coroutines.INSTANCE.getIoContext(), new C03882(entityItemIdentifier, this, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EntityItemCollection getUdoPlaylist(EntityItemIdentifier identifier) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = getContext().getContentResolver().query(MediaProvider.UdoPlaylists.Tracks.getContentUri("cirrus-local", Long.parseLong(identifier.getId().getIdentifier())), AmazonApplication.getLibraryItemFactory().getTrackProjection(), null, null, null);
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                try {
                    MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursorQuery);
                    if (track != null) {
                        arrayList.add(BaseInteractor.INSTANCE.toEntityItemSingle(track));
                    }
                } finally {
                }
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorQuery, null);
        }
        return new EntityItemCollection(arrayList, identifier) { // from class: com.amazon.mp3.platform.providers.library.PlaylistInteractor.getUdoPlaylist.2
            final /* synthetic */ EntityItemIdentifier $identifier;
            final /* synthetic */ List<EntityItemSingle> $tracks;
            private final EntityItemIdentifier entityItemIdentifier;
            private final List<EntityItemSingle> items;

            {
                this.$tracks = arrayList;
                this.$identifier = identifier;
                this.items = arrayList;
                this.entityItemIdentifier = identifier;
            }

            @Override // com.amazon.music.platform.data.entity.EntityItemCollection
            public List<EntityItemSingle> getItems() {
                return this.items;
            }

            @Override // com.amazon.music.platform.data.entity.EntityItem
            public EntityItemIdentifier getEntityItemIdentifier() {
                return this.entityItemIdentifier;
            }
        };
    }
}
