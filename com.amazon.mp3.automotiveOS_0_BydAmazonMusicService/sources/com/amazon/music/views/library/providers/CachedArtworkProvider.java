package com.amazon.music.views.library.providers;

import android.widget.ImageView;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;

/* JADX INFO: compiled from: CachedArtworkProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "", "loadCachedArtwork", "", "imageView", "Landroid/widget/ImageView;", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "imageLoaderCallback", "Lcom/amazon/music/imageloader/ImageLoader$ImageLoaderCallback;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface CachedArtworkProvider {
    void loadCachedArtwork(ImageView imageView, ContentMetadata metadata);

    void loadCachedArtwork(ImageLoader.ImageLoaderCallback imageLoaderCallback, ImageView imageView, ContentMetadata metadata);
}
