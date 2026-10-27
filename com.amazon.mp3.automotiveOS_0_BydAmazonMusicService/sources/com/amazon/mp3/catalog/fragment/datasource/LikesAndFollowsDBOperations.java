package com.amazon.mp3.catalog.fragment.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;

/* JADX INFO: compiled from: LikesAndFollowsDBOperations.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001f\u0010 J\u0014\u0010\u0004\u001a\u00020\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007J\u0012\u0010\u0005\u001a\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0002J5\u0010\u0014\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0012H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0006J3\u0010\u0019\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0012¢\u0006\u0004\b\u0019\u0010\u0015J\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00160\u001b2\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u000eR\u0014\u0010\u001d\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006!"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/LikesAndFollowsDBOperations;", "", "", "sortOrder", "followedArtistsQuery", "getLocalizedSortOrderIfEmpty", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "metadata", "Landroid/content/Context;", "context", "", "insertArtistToLocalDB", "asin", "removeSingleArtistFromLocalDB", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "insertAlbumToLocalDB", "removeSingleAlbumFromLocalDB", "whereClause", "", "whereArgs", "removeAlbumsFromLocalDB", "(Landroid/content/Context;Ljava/lang/String;[Ljava/lang/String;)V", "", "isFollow", "updateArtistFollowStatusInLocalDB", "removeArtistsFromLocalDB", "performLike", "Lrx/Observable;", "updateAlbumLikeStatusInLocalDB", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LikesAndFollowsDBOperations {
    public static final LikesAndFollowsDBOperations INSTANCE = new LikesAndFollowsDBOperations();
    private static final String TAG;

    private LikesAndFollowsDBOperations() {
    }

    static {
        String simpleName = LikesAndFollowsDBOperations.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "LikesAndFollowsDBOperations::class.java.simpleName");
        TAG = simpleName;
    }

    @JvmStatic
    public static final String followedArtistsQuery(String sortOrder) {
        return "SELECT * FROM Artists ORDER BY " + INSTANCE.getLocalizedSortOrderIfEmpty(sortOrder);
    }

    private final String getLocalizedSortOrderIfEmpty(String sortOrder) {
        String str = AmazonApplication.getCapabilities().supportsLocalizedCollation() ? " COLLATE LOCALIZED ASC" : " ASC";
        if (!(sortOrder == null || sortOrder.length() == 0)) {
            return sortOrder;
        }
        return "sort_name " + str + " , name " + str;
    }

    public final void updateArtistFollowStatusInLocalDB(boolean isFollow, ArtistMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Context context = AmazonApplication.getContext();
        if (isFollow) {
            Intrinsics.checkNotNullExpressionValue(context, "context");
            insertArtistToLocalDB(metadata, context);
            return;
        }
        String asin = metadata.getAsin();
        if (asin == null) {
            return;
        }
        LikesAndFollowsDBOperations likesAndFollowsDBOperations = INSTANCE;
        Intrinsics.checkNotNullExpressionValue(context, "context");
        likesAndFollowsDBOperations.removeSingleArtistFromLocalDB(asin, context);
    }

    private final void insertArtistToLocalDB(ArtistMetadata metadata, Context context) {
        Long dateFollowed;
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        ContentValues contentValues = new ContentValues();
        String asin = metadata.getAsin();
        if (asin != null) {
            contentValues.put("artist_asin", asin);
        }
        String name = metadata.getName();
        if (name != null) {
            contentValues.put("name", name);
            contentValues.put("sort_name", name);
        }
        contentValues.put("follow_status", (Integer) 1);
        contentValues.put("enabled_notification_events", "");
        if (metadata.getDateFollowed() == null) {
            dateFollowed = Long.valueOf(System.currentTimeMillis());
        } else {
            dateFollowed = metadata.getDateFollowed();
        }
        contentValues.put("date_created", dateFollowed);
        writableDatabase.insert("Artists", null, contentValues);
    }

    private final void removeSingleArtistFromLocalDB(String asin, Context context) {
        removeArtistsFromLocalDB(context, "artist_asin = ?", new String[]{asin});
    }

    public static /* synthetic */ void removeArtistsFromLocalDB$default(LikesAndFollowsDBOperations likesAndFollowsDBOperations, Context context, String str, String[] strArr, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            strArr = null;
        }
        likesAndFollowsDBOperations.removeArtistsFromLocalDB(context, str, strArr);
    }

    public final void removeArtistsFromLocalDB(Context context, String whereClause, String[] whereArgs) {
        Intrinsics.checkNotNullParameter(context, "context");
        CirrusDatabase.getWritableDatabase(context).delete("Artists", whereClause, whereArgs);
    }

    public final Observable<Boolean> updateAlbumLikeStatusInLocalDB(final boolean performLike, final AlbumMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Observable<Boolean> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LikesAndFollowsDBOperations.m534updateAlbumLikeStatusInLocalDB$lambda5(performLike, metadata, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …)\n            }\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateAlbumLikeStatusInLocalDB$lambda-5, reason: not valid java name */
    public static final void m534updateAlbumLikeStatusInLocalDB$lambda5(boolean z, AlbumMetadata metadata, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        try {
            Context context = AmazonApplication.getContext();
            if (z) {
                LikesAndFollowsDBOperations likesAndFollowsDBOperations = INSTANCE;
                Intrinsics.checkNotNullExpressionValue(context, "context");
                likesAndFollowsDBOperations.insertAlbumToLocalDB(metadata, context);
            } else {
                String asin = metadata.getAsin();
                if (asin != null) {
                    LikesAndFollowsDBOperations likesAndFollowsDBOperations2 = INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(context, "context");
                    likesAndFollowsDBOperations2.removeSingleAlbumFromLocalDB(asin, context);
                }
            }
            subscriber.onNext(Boolean.TRUE);
            subscriber.onCompleted();
        } catch (Exception e) {
            subscriber.onError(e);
        }
    }

    private final void insertAlbumToLocalDB(AlbumMetadata metadata, Context context) throws Exception {
        try {
            SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
            ContentValues contentValues = new ContentValues();
            String asin = metadata.getAsin();
            if (asin != null) {
                contentValues.put("album_asin", asin);
            }
            String title = metadata.getTitle();
            if (title != null) {
                contentValues.put("title", title);
                contentValues.put("sort_title", StringUtil.normalizeAlbumName(title));
            }
            String artistAsin = metadata.getArtistAsin();
            if (artistAsin != null) {
                contentValues.put("artist_asin", artistAsin);
            }
            String artistTitle = metadata.getArtistTitle();
            if (artistTitle != null) {
                contentValues.put("artist", artistTitle);
                contentValues.put("sort_artist", StringUtil.normalizeArtistName(artistTitle));
            }
            List<String> contentEncodings = metadata.getContentEncodings();
            if (contentEncodings != null) {
                contentValues.put("content_encoding", CollectionsKt___CollectionsKt.joinToString$default(contentEncodings, ",", null, null, 0, null, null, 62, null));
            }
            contentValues.put("like_status", (Integer) 1);
            contentValues.put("date_created", Long.valueOf(System.currentTimeMillis()));
            writableDatabase.insert("Albums", null, contentValues);
        } catch (Exception e) {
            Log.error(TAG, Intrinsics.stringPlus("Unable to insert Album in Albums database, ", e.getMessage()));
            throw new Exception(e);
        }
    }

    private final void removeSingleAlbumFromLocalDB(String asin, Context context) throws Exception {
        try {
            removeAlbumsFromLocalDB(context, "album_asin = ?", new String[]{asin});
        } catch (Exception e) {
            Log.error(TAG, "Unable to remove Album with asin: " + asin + " from Albums database, " + ((Object) e.getMessage()));
            throw new Exception(e);
        }
    }

    private final void removeAlbumsFromLocalDB(Context context, String whereClause, String[] whereArgs) throws Exception {
        try {
            CirrusDatabase.getWritableDatabase(context).delete("Albums", whereClause, whereArgs);
        } catch (Exception e) {
            Log.error(TAG, Intrinsics.stringPlus("Unable to remove Album from Albums database, ", e.getMessage()));
            throw new Exception(e);
        }
    }
}
