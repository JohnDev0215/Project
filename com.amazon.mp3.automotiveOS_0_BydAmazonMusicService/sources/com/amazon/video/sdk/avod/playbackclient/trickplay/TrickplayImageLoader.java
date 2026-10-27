package com.amazon.video.sdk.avod.playbackclient.trickplay;

import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import com.amazon.avod.content.image.TrickplayIndex;
import com.amazon.avod.threading.ATVAndroidAsyncTask;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class TrickplayImageLoader {
    private ImageLoaderTask mImageLoaderTask;
    private ImageLoaderListener mTrickplayImageLoaderListener;
    private TrickplayIndex mTrickplayIndex;

    public interface ImageLoaderListener {
        void onImageAvailable(@Nonnull Drawable drawable);
    }

    public TrickplayImageLoader(@Nonnull TrickplayIndex trickplayIndex, @Nullable ImageLoaderListener imageLoaderListener) {
        this.mTrickplayIndex = (TrickplayIndex) Preconditions.checkNotNull(trickplayIndex, "Must register a valid TrickplayIndex.");
        this.mTrickplayImageLoaderListener = imageLoaderListener;
    }

    public void loadImageAsync(long j) {
        ImageLoaderTask imageLoaderTask = new ImageLoaderTask();
        this.mImageLoaderTask = imageLoaderTask;
        imageLoaderTask.execute(Long.valueOf(j));
    }

    private class ImageLoaderTask extends ATVAndroidAsyncTask<Long, Void, Drawable> {
        private ImageLoaderTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.amazon.avod.threading.ATVAndroidAsyncTask
        @Nullable
        public Drawable doInBackground(Long... lArr) {
            return Drawable.createFromPath(TrickplayImageLoader.this.mTrickplayIndex.getImageFileForTimecode(lArr[0].longValue()));
        }

        @Override // com.amazon.avod.threading.ATVAndroidAsyncTask
        public void onPostExecute(@Nullable Drawable drawable) {
            if (TrickplayImageLoader.this.mTrickplayImageLoaderListener == null || drawable == null) {
                return;
            }
            TrickplayImageLoader.this.mTrickplayImageLoaderListener.onImageAvailable(drawable);
        }
    }
}
