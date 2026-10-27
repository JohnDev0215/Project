package com.android.car.developeroptions.slices;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.android.car.developeroptions.overlay.FeatureFactory;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class SlicesIndexer implements Runnable {
    private Context mContext;
    private SlicesDatabaseHelper mHelper;

    public SlicesIndexer(Context context) {
        this.mContext = context;
        this.mHelper = SlicesDatabaseHelper.getInstance(context);
    }

    @Override // java.lang.Runnable
    public void run() {
        indexSliceData();
    }

    protected void indexSliceData() {
        if (this.mHelper.isSliceDataIndexed()) {
            Log.d("SlicesIndexer", "Slices already indexed - returning.");
            return;
        }
        SQLiteDatabase writableDatabase = this.mHelper.getWritableDatabase();
        long jCurrentTimeMillis = System.currentTimeMillis();
        writableDatabase.beginTransaction();
        try {
            this.mHelper.reconstruct(writableDatabase);
            insertSliceData(writableDatabase, getSliceData());
            this.mHelper.setIndexedState();
            Log.d("SlicesIndexer", "Indexing slices database took: " + (System.currentTimeMillis() - jCurrentTimeMillis));
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    List<SliceData> getSliceData() {
        return FeatureFactory.getFactory(this.mContext).getSlicesFeatureProvider().getSliceDataConverter(this.mContext).getSliceData();
    }

    void insertSliceData(SQLiteDatabase sQLiteDatabase, List<SliceData> list) {
        for (SliceData sliceData : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("key", sliceData.getKey());
            contentValues.put("title", sliceData.getTitle());
            contentValues.put("summary", sliceData.getSummary());
            contentValues.put("screentitle", sliceData.getScreenTitle().toString());
            contentValues.put("keywords", sliceData.getKeywords());
            contentValues.put("icon", Integer.valueOf(sliceData.getIconResource()));
            contentValues.put("fragment", sliceData.getFragmentClassName());
            contentValues.put("controller", sliceData.getPreferenceController());
            contentValues.put("platform_slice", Boolean.valueOf(sliceData.isPlatformDefined()));
            contentValues.put("slice_type", Integer.valueOf(sliceData.getSliceType()));
            contentValues.put("unavailable_slice_subtitle", sliceData.getUnavailableSliceSubtitle());
            sQLiteDatabase.replaceOrThrow("slices_index", null, contentValues);
        }
    }
}
