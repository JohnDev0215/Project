package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.refinement.datasource.DatabaseSourcePaged;
import com.amazon.mp3.library.refinement.query.DatabaseQuery;
import com.amazon.mp3.library.refinement.query.arg.SingleArg;
import com.amazon.mp3.library.refinement.query.arg.SingleArgType;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryTracksDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSourcePaged;", "Lcom/amazon/mp3/library/item/MusicTrack;", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao$getDataSourceForUri$1$1", f = "LibraryTracksDao.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class LibraryTracksDao$getDataSourceForUri$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DatabaseSourcePaged<MusicTrack>>, Object> {
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ LibraryTracksDao this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryTracksDao$getDataSourceForUri$1$1(LibraryTracksDao libraryTracksDao, Uri uri, Continuation<? super LibraryTracksDao$getDataSourceForUri$1$1> continuation) {
        super(2, continuation);
        this.this$0 = libraryTracksDao;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryTracksDao$getDataSourceForUri$1$1(this.this$0, this.$uri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super DatabaseSourcePaged<MusicTrack>> continuation) {
        return ((LibraryTracksDao$getDataSourceForUri$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Pair pair = TuplesKt.to("ownership_status", SetsKt__SetsJVMKt.setOf(new SingleArg("300", SingleArgType.LESS_THAN)));
        String[] trackProjection = DBQueryUtil.INSTANCE.getTrackProjection();
        LibraryTracksDao libraryTracksDao = this.this$0;
        Map<String, SortOrder> defaultSortColumn = libraryTracksDao.getDefaultSortColumn(libraryTracksDao.pageType);
        return new DatabaseSourcePaged(new DatabaseQuery(this.$uri, trackProjection, MapsKt__MapsJVMKt.mapOf(pair), defaultSortColumn), new Function1<Cursor, List<? extends MusicTrack>>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryTracksDao$getDataSourceForUri$1$1.1
            @Override // kotlin.jvm.functions.Function1
            public final List<MusicTrack> invoke(Cursor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return DBQueryUtil.INSTANCE.convertTracksCursorToTracks(it);
            }
        }, this.this$0.context, "sort_title ASC", "_id");
    }
}
