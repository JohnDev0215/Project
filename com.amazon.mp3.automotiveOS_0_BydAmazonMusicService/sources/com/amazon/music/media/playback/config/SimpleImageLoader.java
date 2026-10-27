package com.amazon.music.media.playback.config;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/* JADX INFO: loaded from: classes4.dex */
public class SimpleImageLoader implements ItemLoader<Uri, Bitmap> {
    @Override // com.amazon.music.media.playback.config.ItemLoader
    @Nullable
    public Bitmap getIfCached(@NonNull Uri uri) {
        return null;
    }

    @Override // com.amazon.music.media.playback.config.ItemLoader
    @NonNull
    public Bitmap load(@NonNull Uri uri) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(uri.toString()).openConnection()));
        httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
    }
}
