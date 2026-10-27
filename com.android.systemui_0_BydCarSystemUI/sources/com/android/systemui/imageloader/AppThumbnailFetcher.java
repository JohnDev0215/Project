package com.android.systemui.imageloader;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.recents.LegacyRecentsImpl;
import com.android.systemui.shared.recents.model.ThumbnailData;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class AppThumbnailFetcher implements DataFetcher<Bitmap> {
    private final AppThumbnail appThumbnail;

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cancel() {
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void cleanup() {
    }

    public AppThumbnailFetcher(AppThumbnail appThumbnail) {
        this.appThumbnail = appThumbnail;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public void loadData(Priority priority, DataFetcher.DataCallback<? super Bitmap> callback) {
        Bitmap bitmapChangeThumbnailBitmap = changeThumbnailBitmap(LegacyRecentsImpl.getTaskLoader().getAndUpdateThumbnail(this.appThumbnail.getTaskKey(), true, true));
        if (bitmapChangeThumbnailBitmap != null) {
            callback.onDataReady(bitmapChangeThumbnailBitmap);
        } else {
            BydLogHelper.logD("without imageData!");
            callback.onLoadFailed(new Exception("without imageData!"));
        }
    }

    private Bitmap changeThumbnailBitmap(ThumbnailData thumbnailData) {
        if (thumbnailData == null || thumbnailData.thumbnail == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        Rect rect = thumbnailData.insets;
        matrix.setTranslate(-rect.top, -rect.left);
        int i = SystemUIApplication.getInstance().getResources().getConfiguration().orientation;
        int i2 = thumbnailData.orientation;
        if (i != i2) {
            if (i2 == 1) {
                matrix.setRotate(-90.0f);
            } else {
                matrix.setRotate(90.0f);
            }
        }
        Bitmap bitmap = thumbnailData.thumbnail;
        int width = (bitmap.getWidth() - rect.left) - rect.right;
        int height = thumbnailData.thumbnail.getHeight();
        if (thumbnailData.reducedResolution) {
            return Bitmap.createBitmap(bitmap, rect.left, rect.top / 2, width, (height - (rect.top / 2)) - (rect.bottom / 2), matrix, false);
        }
        return Bitmap.createBitmap(bitmap, rect.left, rect.top, width, (height - rect.top) - rect.bottom, matrix, false);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public Class<Bitmap> getDataClass() {
        return Bitmap.class;
    }

    @Override // com.bumptech.glide.load.data.DataFetcher
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}
