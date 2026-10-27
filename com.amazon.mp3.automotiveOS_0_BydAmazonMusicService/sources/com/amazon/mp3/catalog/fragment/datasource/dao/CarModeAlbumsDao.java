package com.amazon.mp3.catalog.fragment.datasource.dao;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.auto.storage.PresetEntityType;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.catalog.fragment.datasource.DBQueryUtil;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.query.DatabaseQuery;
import com.amazon.mp3.library.refinement.query.arg.SingleArg;
import com.amazon.mp3.library.refinement.query.arg.SingleArgType;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeAlbumsDao.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/dao/CarModeAlbumsDao;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "queryAlbums", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "uri", "Landroid/net/Uri;", "limit", "", "(Landroid/net/Uri;Ljava/lang/Integer;)Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeAlbumsDao {
    private final Context context;

    public CarModeAlbumsDao(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final DatabaseSource<PresetIdentifier> queryAlbums(Uri uri, final Integer limit) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new DatabaseSource<>(new DatabaseQuery(uri, DBQueryUtil.INSTANCE.getAlbumListProjection(), MapsKt__MapsKt.mapOf(TuplesKt.to("ownership_status", SetsKt__SetsJVMKt.setOf(new SingleArg("300", SingleArgType.LESS_THAN))), TuplesKt.to("album_asin", SetsKt__SetsJVMKt.setOf(new SingleArg("NULL", SingleArgType.NOT_EQUALS)))), null, 8, null), new Function1<Cursor, List<? extends PresetIdentifier>>() { // from class: com.amazon.mp3.catalog.fragment.datasource.dao.CarModeAlbumsDao.queryAlbums.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final List<PresetIdentifier> invoke(Cursor it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return PresetIdentifierHelper.INSTANCE.convertCursorToPresetIdentifiers(it, limit, "album_asin", PresetEntityType.Album);
            }
        }, this.context);
    }
}
