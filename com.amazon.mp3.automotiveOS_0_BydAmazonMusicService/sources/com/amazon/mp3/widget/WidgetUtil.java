package com.amazon.mp3.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.AccountStateCheckActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.store.util.ImageCache;
import com.amazon.mp3.util.AlbumArtworkUtil;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.voice.VoiceManagerSingleton;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetUtil {
    private static ImageCache sPrimeImageCache;

    public static boolean isAlexaWidgetActivity() {
        return VoiceManagerSingleton.isInitialized() && (VoiceManagerSingleton.getInstance().getActivity() instanceof AlexaWidgetContainerActivity);
    }

    public static boolean isAlexaWidgetOrAccountStateCheckActivity(final Activity activity) {
        return (activity instanceof AlexaWidgetContainerActivity) || (activity instanceof AccountStateCheckActivity);
    }

    @WorkerThread
    public static Bitmap getAlbumArtByAsin(final Context context, final MediaItem mediaItem) {
        Drawable albumArtworkByAsin = new AlbumArtworkUtil(context).getAlbumArtworkByAsin(mediaItem, getPixelSizeArtwork());
        if (albumArtworkByAsin == null) {
            return null;
        }
        return BitmapUtil.convertDrawableToBitmap(albumArtworkByAsin);
    }

    @WorkerThread
    public static Bitmap getArtworkForLibraryMediaItem(final Context context, final MediaItem mediaItem) {
        Drawable albumArtworkForLibraryMediaItem = new AlbumArtworkUtil(context).getAlbumArtworkForLibraryMediaItem(mediaItem, getPixelSizeArtwork());
        if (albumArtworkForLibraryMediaItem == null) {
            return null;
        }
        return BitmapUtil.convertDrawableToBitmap(albumArtworkForLibraryMediaItem);
    }

    @WorkerThread
    public static Bitmap getArtworkForPrimePlaylistMediaItem(final Context context, final MediaItem mediaItem) {
        Drawable albumArtworkForPrimePlaylistMediaItem = new AlbumArtworkUtil(context).getAlbumArtworkForPrimePlaylistMediaItem(mediaItem, getPixelSizeArtwork());
        if (albumArtworkForPrimePlaylistMediaItem == null) {
            return null;
        }
        return BitmapUtil.convertDrawableToBitmap(albumArtworkForPrimePlaylistMediaItem);
    }

    @WorkerThread
    public static Bitmap getArtworkForPrimeBrowseMediaItem(final MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        Uri uriCreateImageUri = mediaItem.createImageUri(getPixelSizeArtwork(), getPixelSizeArtwork(), new MediaItem.ImageType[0]);
        String string = uriCreateImageUri != null ? uriCreateImageUri.toString() : "";
        if (string == null) {
            return null;
        }
        return getAlbumArtFromCacheWithAlbumUrl(string);
    }

    @WorkerThread
    public static Bitmap getAlbumArtFromCacheWithAlbumUrl(final String albumUrl) {
        Drawable drawableCacheOnCurrentThread = albumUrl != null ? getPrimeImageCache(getPixelSizeArtwork()).cacheOnCurrentThread(albumUrl) : null;
        if (drawableCacheOnCurrentThread == null) {
            return null;
        }
        return BitmapUtil.convertDrawableToBitmap(drawableCacheOnCurrentThread);
    }

    public static Bitmap getCircleBitmap(final Bitmap input) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(input.getWidth(), input.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, input.getWidth(), input.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawCircle(input.getWidth() / 2, input.getHeight() / 2, input.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(input, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    public static int getPixelSizeArtwork() {
        return AmazonApplication.getContext().getResources().getDimensionPixelSize(R.dimen.album_art_width);
    }

    private static ImageCache getPrimeImageCache(int artworkSize) {
        if (sPrimeImageCache == null) {
            sPrimeImageCache = new ImageCache(AmazonApplication.getContext(), artworkSize, getPixelSizeArtwork(), Bitmap.Config.RGB_565);
        }
        return sPrimeImageCache;
    }
}
