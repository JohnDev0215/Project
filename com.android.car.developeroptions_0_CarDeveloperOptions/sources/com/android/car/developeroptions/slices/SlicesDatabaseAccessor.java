package com.android.car.developeroptions.slices;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.slices.SliceData;

/* JADX INFO: loaded from: classes.dex */
public class SlicesDatabaseAccessor {
    public static final String[] SELECT_COLUMNS_ALL = {"key", "title", "summary", "screentitle", "keywords", "icon", "fragment", "controller", "platform_slice", "slice_type", "unavailable_slice_subtitle"};
    private final Context mContext;
    private final SlicesDatabaseHelper mHelper;

    public SlicesDatabaseAccessor(Context context) {
        this.mContext = context;
        this.mHelper = SlicesDatabaseHelper.getInstance(context);
    }

    public SliceData getSliceDataFromKey(String str) {
        Cursor indexedSliceData = getIndexedSliceData(str);
        try {
            SliceData sliceDataBuildSliceData = buildSliceData(indexedSliceData, null, false);
            if (indexedSliceData != null) {
                indexedSliceData.close();
            }
            return sliceDataBuildSliceData;
        } catch (Throwable th) {
            if (indexedSliceData != null) {
                try {
                    indexedSliceData.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private Cursor getIndexedSliceData(String str) {
        verifyIndexing();
        Cursor cursorQuery = this.mHelper.getReadableDatabase().query("slices_index", SELECT_COLUMNS_ALL, buildKeyMatchWhereClause(), new String[]{str}, null, null, null);
        int count = cursorQuery.getCount();
        if (count == 0) {
            throw new IllegalStateException("Invalid Slices key from path: " + str);
        }
        if (count > 1) {
            throw new IllegalStateException("Should not match more than 1 slice with path: " + str);
        }
        cursorQuery.moveToFirst();
        return cursorQuery;
    }

    private String buildKeyMatchWhereClause() {
        return "key = ?";
    }

    private SliceData buildSliceData(Cursor cursor, Uri uri, boolean z) {
        String string = cursor.getString(cursor.getColumnIndex("key"));
        String string2 = cursor.getString(cursor.getColumnIndex("title"));
        String string3 = cursor.getString(cursor.getColumnIndex("summary"));
        String string4 = cursor.getString(cursor.getColumnIndex("screentitle"));
        String string5 = cursor.getString(cursor.getColumnIndex("keywords"));
        int i = cursor.getInt(cursor.getColumnIndex("icon"));
        String string6 = cursor.getString(cursor.getColumnIndex("fragment"));
        String string7 = cursor.getString(cursor.getColumnIndex("controller"));
        boolean z2 = cursor.getInt(cursor.getColumnIndex("platform_slice")) == 1;
        int i2 = cursor.getInt(cursor.getColumnIndex("slice_type"));
        String string8 = cursor.getString(cursor.getColumnIndex("unavailable_slice_subtitle"));
        int i3 = z ? 0 : i2;
        SliceData.Builder builder = new SliceData.Builder();
        builder.setKey(string);
        builder.setTitle(string2);
        builder.setSummary(string3);
        builder.setScreenTitle(string4);
        builder.setKeywords(string5);
        builder.setIcon(i);
        builder.setFragmentName(string6);
        builder.setPreferenceControllerClassName(string7);
        builder.setUri(uri);
        builder.setPlatformDefined(z2);
        builder.setSliceType(i3);
        builder.setUnavailableSliceSubtitle(string8);
        return builder.build();
    }

    private void verifyIndexing() {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            FeatureFactory.getFactory(this.mContext).getSlicesFeatureProvider().indexSliceData(this.mContext);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}
