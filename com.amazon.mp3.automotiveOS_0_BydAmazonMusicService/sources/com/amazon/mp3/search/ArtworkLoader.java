package com.amazon.mp3.search;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.search.model.PodcastEpisode;
import com.amazon.mp3.search.model.PodcastShow;
import com.amazon.mp3.util.ImageProcessor;
import com.amazon.mp3.util.ImageUrlModifier;
import com.amazon.music.find.model.search.SearchItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/* JADX INFO: loaded from: classes4.dex */
public class ArtworkLoader {
    private final boolean mAllowScaling;
    private final int mArtworkSize;
    private final Context mContext;
    private final ImageProcessor mImageProcessor;
    private final Drawable mPlaceHolder;

    private ArtworkLoader(Context context, ImageProcessor imageProcessor, @DrawableRes int placeHolderResId, int artworkSize, boolean allowScaling) {
        this.mContext = context;
        this.mImageProcessor = imageProcessor;
        this.mPlaceHolder = context.getResources().getDrawable(placeHolderResId);
        this.mArtworkSize = artworkSize;
        this.mAllowScaling = allowScaling;
    }

    public void fetchInto(final SearchItem searchItem, @NonNull final ImageView targetView) {
        if (searchItem == null) {
            return;
        }
        int iMax = this.mArtworkSize;
        if (iMax <= 0) {
            iMax = Math.max(targetView.getMeasuredWidth(), targetView.getMeasuredHeight());
        }
        if (TextUtils.isEmpty(searchItem.getArtworkUri())) {
            if (searchItem.getArtworkId() != null) {
                loadFromLibrary(searchItem, targetView, iMax);
                return;
            }
            return;
        }
        fetchFromUrl(searchItem, targetView, iMax);
    }

    private void loadFromLibrary(SearchItem searchItem, ImageView targetView, int artworkSize) {
        CacheManager cacheManager = CacheManager.getInstance();
        ImageLoaderFactory.ItemType artworkType = SearchItemUtils.getArtworkType(searchItem);
        if (artworkSize <= 0) {
            artworkSize = CacheManager.MAX_UNSCALED_SIZE;
        }
        Drawable drawable = cacheManager.get(artworkType, "cirrus", artworkSize, Long.toString(searchItem.getArtworkId().longValue()));
        ImageProcessor imageProcessor = this.mImageProcessor;
        if (imageProcessor != null) {
            imageProcessor.process((BitmapDrawable) drawable, targetView);
        } else {
            targetView.setImageDrawable(drawable);
        }
    }

    private void fetchFromUrl(SearchItem searchItem, ImageView targetView, int artworkSize) {
        boolean z;
        boolean z2;
        String artworkUri = searchItem.getArtworkUri();
        boolean z3 = false;
        if ((searchItem instanceof PodcastShow) || (searchItem instanceof PodcastEpisode)) {
            z = false;
            z2 = true;
        } else {
            z2 = false;
            z = true;
        }
        if (this.mAllowScaling && artworkSize > 0 && !ImageUrlModifier.containsModifiers(artworkUri) && z) {
            artworkUri = ImageUrlModifier.with(artworkUri).upscaleToLongest(artworkSize).toUrl();
            z3 = true;
        }
        RequestCreator requestCreatorPlaceholder = Picasso.get().load(artworkUri).placeholder(this.mPlaceHolder);
        if (!z3 && !z2) {
            requestCreatorPlaceholder.fit().centerInside();
        } else if (z2) {
            requestCreatorPlaceholder.fit().centerCrop();
        }
        ImageProcessor imageProcessor = this.mImageProcessor;
        if (imageProcessor != null) {
            requestCreatorPlaceholder.transform(imageProcessor);
        }
        requestCreatorPlaceholder.into(targetView);
    }

    public static class Builder {
        private boolean mAllowScaling;
        private int mArtworkSize;
        private final Context mContext;
        private ImageProcessor mImageProcessor;

        @DrawableRes
        private int mPlaceholderResId = R.drawable.placeholder_sm;

        public Builder(@NonNull Context context) {
            this.mContext = context;
        }

        public Builder setImageProcessor(@NonNull ImageProcessor imageProcessor) {
            this.mImageProcessor = imageProcessor;
            return this;
        }

        public Builder setPlaceholderResId(@DrawableRes int placeholderResId) {
            this.mPlaceholderResId = placeholderResId;
            return this;
        }

        public Builder setArtworkSize(int artworkSize) {
            this.mArtworkSize = artworkSize;
            return this;
        }

        public Builder setAllowServerToScale(boolean allowScaling) {
            this.mAllowScaling = allowScaling;
            return this;
        }

        public ArtworkLoader build() {
            return new ArtworkLoader(this.mContext, this.mImageProcessor, this.mPlaceholderResId, this.mArtworkSize, this.mAllowScaling);
        }
    }
}
