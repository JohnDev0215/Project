package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.query.DatabaseQuery;
import java.util.List;
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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryPlaylistsDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "Lcom/amazon/mp3/library/item/Playlist;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.LibraryPlaylistsDao$getDataSourceForUri$1$1", f = "LibraryPlaylistsDao.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LibraryPlaylistsDao$getDataSourceForUri$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DatabaseSource<Playlist>>, Object> {
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ LibraryPlaylistsDao this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryPlaylistsDao$getDataSourceForUri$1$1(Uri uri, LibraryPlaylistsDao libraryPlaylistsDao, Continuation<? super LibraryPlaylistsDao$getDataSourceForUri$1$1> continuation) {
        super(2, continuation);
        this.$uri = uri;
        this.this$0 = libraryPlaylistsDao;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryPlaylistsDao$getDataSourceForUri$1$1(this.$uri, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super DatabaseSource<Playlist>> continuation) {
        return ((LibraryPlaylistsDao$getDataSourceForUri$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        final String source = MediaProvider.getSource(this.$uri);
        Uri uri = this.$uri;
        String[] playlistsListProjection = DBQueryUtil.INSTANCE.getPlaylistsListProjection();
        LibraryPlaylistsDao libraryPlaylistsDao = this.this$0;
        DatabaseQuery databaseQuery = new DatabaseQuery(uri, playlistsListProjection, null, libraryPlaylistsDao.getDefaultSortColumn(libraryPlaylistsDao.pageType), 4, null);
        final LibraryPlaylistsDao libraryPlaylistsDao2 = this.this$0;
        return new DatabaseSource(databaseQuery, new Function1<Cursor, List<? extends Playlist>>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryPlaylistsDao$getDataSourceForUri$1$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<Playlist> invoke(Cursor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                DBQueryUtil dBQueryUtil = DBQueryUtil.INSTANCE;
                Boolean boolValueOf = Boolean.valueOf(libraryPlaylistsDao2.getUseOnlineSource());
                String source2 = source;
                Intrinsics.checkNotNullExpressionValue(source2, "source");
                return dBQueryUtil.convertCursorToPlaylists(it, boolValueOf, source2);
            }
        }, this.this$0.context);
    }
}
