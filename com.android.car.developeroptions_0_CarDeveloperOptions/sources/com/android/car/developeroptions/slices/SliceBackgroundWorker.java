package com.android.car.developeroptions.slices;

import android.content.Context;
import android.net.Uri;
import android.util.ArrayMap;
import java.io.Closeable;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class SliceBackgroundWorker<E> implements Closeable {
    private static final Map<Uri, SliceBackgroundWorker> LIVE_WORKERS = new ArrayMap();
    private List<E> mCachedResults;
    private final Context mContext;
    private final Uri mUri;

    protected SliceBackgroundWorker(Context context, Uri uri) {
        this.mContext = context;
        this.mUri = uri;
    }

    public static <T extends SliceBackgroundWorker> T getInstance(Uri uri) {
        return (T) LIVE_WORKERS.get(uri);
    }

    protected final void updateResults(List<E> list) {
        boolean zAreListsTheSame = true;
        if (list == null) {
            if (this.mCachedResults == null) {
                zAreListsTheSame = false;
            }
        } else {
            zAreListsTheSame = true ^ areListsTheSame(list, this.mCachedResults);
        }
        if (zAreListsTheSame) {
            this.mCachedResults = list;
            notifySliceChange();
        }
    }

    protected boolean areListsTheSame(List<E> list, List<E> list2) {
        return list.equals(list2);
    }

    protected final void notifySliceChange() {
        this.mContext.getContentResolver().notifyChange(this.mUri, null);
    }
}
