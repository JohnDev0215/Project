package com.amazon.music.explore.picasso;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public class VideoRequestHandler extends RequestHandler {
    public String VIDEO_EXTENSION = ".mp4";

    @Override // com.squareup.picasso.RequestHandler
    public boolean canHandleRequest(Request request) {
        String path = request.uri.getPath();
        return path != null && path.endsWith(this.VIDEO_EXTENSION);
    }

    @Override // com.squareup.picasso.RequestHandler
    public RequestHandler.Result load(Request request, int i) throws Throwable {
        String string = request.uri.toString();
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            try {
                MediaMetadataRetriever mediaMetadataRetriever2 = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever2.setDataSource(string, new HashMap());
                    Bitmap frameAtTime = mediaMetadataRetriever2.getFrameAtTime(0L);
                    mediaMetadataRetriever2.release();
                    return new RequestHandler.Result(frameAtTime, Picasso.LoadedFrom.NETWORK);
                } catch (Exception e) {
                    e = e;
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    throw new IOException(e);
                } catch (Throwable th) {
                    th = th;
                    mediaMetadataRetriever = mediaMetadataRetriever2;
                    if (mediaMetadataRetriever != null) {
                        mediaMetadataRetriever.release();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }
}
