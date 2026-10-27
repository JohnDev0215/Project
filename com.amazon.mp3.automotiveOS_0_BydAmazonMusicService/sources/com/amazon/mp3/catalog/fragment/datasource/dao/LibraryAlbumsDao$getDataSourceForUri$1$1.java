package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.query.DatabaseQuery;
import com.amazon.mp3.library.refinement.query.arg.SingleArg;
import com.amazon.mp3.library.refinement.query.arg.SingleArgType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryAlbumsDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "Lcom/amazon/mp3/library/item/Album;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.LibraryAlbumsDao$getDataSourceForUri$1$1", f = "LibraryAlbumsDao.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LibraryAlbumsDao$getDataSourceForUri$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DatabaseSource<Album>>, Object> {
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ LibraryAlbumsDao this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryAlbumsDao$getDataSourceForUri$1$1(LibraryAlbumsDao libraryAlbumsDao, Uri uri, Continuation<? super LibraryAlbumsDao$getDataSourceForUri$1$1> continuation) {
        super(2, continuation);
        this.this$0 = libraryAlbumsDao;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryAlbumsDao$getDataSourceForUri$1$1(this.this$0, this.$uri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super DatabaseSource<Album>> continuation) {
        return ((LibraryAlbumsDao$getDataSourceForUri$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Pair pair = TuplesKt.to("ownership_status", SetsKt__SetsJVMKt.setOf(new SingleArg("300", SingleArgType.LESS_THAN)));
        Pair pair2 = TuplesKt.to("playlist_track_status", SetsKt__SetsJVMKt.setOf(new SingleArg(String.valueOf(MusicTrack.PlaylistTrackStatus.NOT_CATALOG_TRACK.getValue()), SingleArgType.EQUALS)));
        String[] albumListProjection = DBQueryUtil.INSTANCE.getAlbumListProjection();
        LibraryAlbumsDao libraryAlbumsDao = this.this$0;
        return new DatabaseSource(new DatabaseQuery(this.$uri, albumListProjection, MapsKt__MapsKt.mapOf(pair, pair2), libraryAlbumsDao.getDefaultSortColumn(libraryAlbumsDao.pageType)), new Function1<Cursor, List<? extends Album>>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryAlbumsDao$getDataSourceForUri$1$1.1
            @Override // kotlin.jvm.functions.Function1
            public final List<Album> invoke(Cursor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return DBQueryUtil.INSTANCE.convertCursorToAlbums(it);
            }
        }, this.this$0.context);
    }
}
