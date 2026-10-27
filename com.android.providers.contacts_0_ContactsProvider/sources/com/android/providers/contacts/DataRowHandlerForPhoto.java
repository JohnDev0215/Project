package com.android.providers.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.android.providers.contacts.aggregation.AbstractContactAggregator;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class DataRowHandlerForPhoto extends DataRowHandler {
    private final int mMaxDisplayPhotoDim;
    private final int mMaxThumbnailPhotoDim;
    private final PhotoStore mPhotoStore;

    public DataRowHandlerForPhoto(Context context, ContactsDatabaseHelper contactsDatabaseHelper, AbstractContactAggregator abstractContactAggregator, PhotoStore photoStore, int i, int i2) {
        super(context, contactsDatabaseHelper, abstractContactAggregator, "vnd.android.cursor.item/photo");
        this.mPhotoStore = photoStore;
        this.mMaxDisplayPhotoDim = i;
        this.mMaxThumbnailPhotoDim = i2;
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public long insert(SQLiteDatabase sQLiteDatabase, TransactionContext transactionContext, long j, ContentValues contentValues) {
        if (contentValues.containsKey("skip_processing")) {
            contentValues.remove("skip_processing");
        } else if (!preProcessPhoto(contentValues)) {
            return 0L;
        }
        long jInsert = super.insert(sQLiteDatabase, transactionContext, j, contentValues);
        if (!transactionContext.isNewRawContact(j)) {
            this.mContactAggregator.updatePhotoId(sQLiteDatabase, j);
        }
        return jInsert;
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public boolean update(SQLiteDatabase sQLiteDatabase, TransactionContext transactionContext, ContentValues contentValues, Cursor cursor, boolean z, boolean z2) {
        long j = cursor.getLong(1);
        if (contentValues.containsKey("skip_processing")) {
            contentValues.remove("skip_processing");
        } else if (!preProcessPhoto(contentValues)) {
            return false;
        }
        if (!super.update(sQLiteDatabase, transactionContext, contentValues, cursor, z, z2)) {
            return false;
        }
        this.mContactAggregator.updatePhotoId(sQLiteDatabase, j);
        return true;
    }

    private boolean preProcessPhoto(ContentValues contentValues) {
        if (!contentValues.containsKey("data15")) {
            return true;
        }
        if (hasNonNullPhoto(contentValues)) {
            return processPhoto(contentValues);
        }
        contentValues.putNull("data15");
        contentValues.putNull("data14");
        return true;
    }

    private boolean hasNonNullPhoto(ContentValues contentValues) {
        byte[] asByteArray = contentValues.getAsByteArray("data15");
        return asByteArray != null && asByteArray.length > 0;
    }

    @Override // com.android.providers.contacts.DataRowHandler
    public int delete(SQLiteDatabase sQLiteDatabase, TransactionContext transactionContext, Cursor cursor) {
        long j = cursor.getLong(2);
        int iDelete = super.delete(sQLiteDatabase, transactionContext, cursor);
        this.mContactAggregator.updatePhotoId(sQLiteDatabase, j);
        return iDelete;
    }

    private boolean processPhoto(ContentValues contentValues) {
        byte[] asByteArray = contentValues.getAsByteArray("data15");
        if (asByteArray == null) {
            return false;
        }
        try {
            PhotoProcessor photoProcessor = new PhotoProcessor(asByteArray, this.mMaxDisplayPhotoDim, this.mMaxThumbnailPhotoDim);
            long jInsert = this.mPhotoStore.insert(photoProcessor);
            if (jInsert != 0) {
                contentValues.put("data14", Long.valueOf(jInsert));
            } else {
                contentValues.putNull("data14");
            }
            contentValues.put("data15", photoProcessor.getThumbnailPhotoBytes());
            return true;
        } catch (IOException e) {
            Log.e("DataRowHandlerForPhoto", "Could not process photo for insert or update", e);
            return false;
        }
    }
}
