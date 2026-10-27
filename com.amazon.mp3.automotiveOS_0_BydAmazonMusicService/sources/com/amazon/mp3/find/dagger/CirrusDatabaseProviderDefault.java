package com.amazon.mp3.find.dagger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.data.database.AbstractCirrusDatabaseProvider;
import com.amazon.music.find.model.search.SearchContentCatalogStatus;
import com.amazon.music.find.model.search.SearchContentOwnershipStatus;
import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CirrusDatabaseProviderDefault.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rH\u0016J\u001a\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u0006H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0006H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/find/dagger/CirrusDatabaseProviderDefault;", "Lcom/amazon/music/find/data/database/AbstractCirrusDatabaseProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "convertContentCatalogStatusToInt", "", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/music/find/model/search/SearchContentCatalogStatus;", "convertContentOwnershipStatusToInt", "Lcom/amazon/music/find/model/search/SearchContentOwnershipStatus;", "convertLibrarySourceToInt", PageUriUtils.SOURCE_QUERY_PARAM, "Lcom/amazon/music/find/model/search/SearchLibrarySource;", "convertLibrarySourceToMediaSource", "", "convertStringToContentEncoding", "", "Lcom/amazon/music/contentEncoding/ContentEncoding;", ContextMappingConstants.CONTENT_ENCODING, "getContentCatalogStatusPrevCatalogOrNotBoundary", "getReadOnlyDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "getTrueValue", "isOwned", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CirrusDatabaseProviderDefault extends AbstractCirrusDatabaseProvider {
    private final Context context;

    public CirrusDatabaseProviderDefault(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public SQLiteDatabase getReadOnlyDatabase() {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(this.context);
        Intrinsics.checkNotNullExpressionValue(readOnlyDatabase, "getReadOnlyDatabase(context)");
        return readOnlyDatabase;
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public List<ContentEncoding> convertStringToContentEncoding(String contentEncoding) {
        return SearchItemUtils.convertStringToContentEncoding(contentEncoding);
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public int convertLibrarySourceToInt(SearchLibrarySource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return SearchItemUtils.convertLibrarySourceToInt(source);
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public String convertLibrarySourceToMediaSource(SearchLibrarySource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return SearchItemUtils.convertLibrarySourceToMediaSource(source);
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public int convertContentOwnershipStatusToInt(SearchContentOwnershipStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return SearchItemUtils.convertOwnershipStatus(status).getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public int convertContentCatalogStatusToInt(SearchContentCatalogStatus status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return SearchItemUtils.convertContentCatalogStatus(status).getValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public int getTrueValue() {
        return SearchItemUtils.getTrueValue();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public int getContentCatalogStatusPrevCatalogOrNotBoundary() {
        return SearchItemUtils.getContentCatalogStatusPrevCatalogOrNotBoundary();
    }

    @Override // com.amazon.music.find.data.database.CirrusDatabaseProvider
    public boolean isOwned(int status) {
        return ContentOwnershipStatus.fromValue(status).isOwned();
    }
}
