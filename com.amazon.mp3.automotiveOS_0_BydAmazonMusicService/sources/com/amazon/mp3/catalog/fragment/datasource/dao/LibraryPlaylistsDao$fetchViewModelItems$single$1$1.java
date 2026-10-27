package com.amazon.mp3.catalog.fragment.datasource.dao;

import com.amazon.music.views.library.models.PageGridViewModel;
import io.reactivex.SingleEmitter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryPlaylistsDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "com.amazon.mp3.catalog.fragment.datasource.dao.LibraryPlaylistsDao$fetchViewModelItems$single$1$1", f = "LibraryPlaylistsDao.kt", i = {}, l = {89}, m = "invokeSuspend", n = {}, s = {})
final class LibraryPlaylistsDao$fetchViewModelItems$single$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SingleEmitter<PageGridViewModel> $it;
    final /* synthetic */ int $pageIndex;
    int label;
    final /* synthetic */ LibraryPlaylistsDao this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryPlaylistsDao$fetchViewModelItems$single$1$1(LibraryPlaylistsDao libraryPlaylistsDao, SingleEmitter<PageGridViewModel> singleEmitter, int i, Continuation<? super LibraryPlaylistsDao$fetchViewModelItems$single$1$1> continuation) {
        super(2, continuation);
        this.this$0 = libraryPlaylistsDao;
        this.$it = singleEmitter;
        this.$pageIndex = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryPlaylistsDao$fetchViewModelItems$single$1$1(this.this$0, this.$it, this.$pageIndex, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryPlaylistsDao$fetchViewModelItems$single$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:93:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b0  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            Method dump skipped, instruction units count: 593
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.catalog.fragment.datasource.dao.LibraryPlaylistsDao$fetchViewModelItems$single$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
