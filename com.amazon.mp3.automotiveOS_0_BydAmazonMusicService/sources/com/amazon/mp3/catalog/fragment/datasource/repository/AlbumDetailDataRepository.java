package com.amazon.mp3.catalog.fragment.datasource.repository;

import com.amazon.mp3.catalog.fragment.datasource.dao.LibraryAlbumDetailDao;
import com.amazon.mp3.library.item.AbstractItem;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumDetailDataRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\rJ\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/AlbumDetailDataRepository;", "Lcom/amazon/mp3/catalog/fragment/datasource/repository/PageDataRepository;", "Lio/reactivex/Single;", "Lcom/amazon/mp3/library/item/AbstractItem;", "fetchItem", "", "isItemOwned", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryAlbumDetailDao;", "dao", "Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryAlbumDetailDao;", "getDao", "()Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryAlbumDetailDao;", "<init>", "(Lcom/amazon/mp3/catalog/fragment/datasource/dao/LibraryAlbumDetailDao;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlbumDetailDataRepository extends PageDataRepository {
    private final LibraryAlbumDetailDao dao;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumDetailDataRepository(LibraryAlbumDetailDao dao) {
        super(dao);
        Intrinsics.checkNotNullParameter(dao, "dao");
        this.dao = dao;
    }

    public final Single<AbstractItem> fetchItem() {
        return this.dao.fetchItem();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: isItemOwned$lambda-0, reason: not valid java name */
    public static final Boolean m570isItemOwned$lambda0(AbstractItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Boolean.valueOf(it.isAllOwned());
    }

    public final Single<Boolean> isItemOwned() {
        Single map = fetchItem().map(new Function() { // from class: com.amazon.mp3.catalog.fragment.datasource.repository.AlbumDetailDataRepository$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AlbumDetailDataRepository.m570isItemOwned$lambda0((AbstractItem) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "fetchItem().map { it.isAllOwned }");
        return map;
    }
}
