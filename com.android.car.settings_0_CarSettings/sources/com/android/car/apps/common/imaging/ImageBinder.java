package com.android.car.apps.common.imaging;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Size;
import androidx.core.util.Preconditions;
import com.android.car.apps.common.R;
import com.android.car.apps.common.UriUtils;
import com.android.car.apps.common.imaging.ImageBinder.ImageRef;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes5.dex */
public class ImageBinder<T extends ImageRef> {
    private final Consumer<Drawable> mClient;
    private ImageKey mCurrentKey;
    private T mCurrentRef;
    private BiConsumer<ImageKey, Drawable> mFetchReceiver;
    private Drawable mLoadingDrawable;
    private final Size mMaxImageSize;
    private final PlaceholderType mPlaceholderType;

    public enum PlaceholderType {
        NONE,
        FOREGROUND,
        BACKGROUND
    }

    public interface ImageRef {
        boolean equals(Context context, Object obj);

        Uri getImageURI();

        Drawable getPlaceholder(Context context, PlaceholderType placeholderType);

        default Drawable getImage(Context context) {
            return null;
        }
    }

    public ImageBinder(PlaceholderType type, Size maxImageSize, Consumer<Drawable> consumer) {
        this.mPlaceholderType = (PlaceholderType) Preconditions.checkNotNull(type, "Need a type");
        this.mMaxImageSize = (Size) Preconditions.checkNotNull(maxImageSize, "Need a size");
        this.mClient = (Consumer) Preconditions.checkNotNull(consumer, "Cannot bind a null consumer");
    }

    protected ImageBinder(PlaceholderType type, Size maxImageSize) {
        this.mPlaceholderType = (PlaceholderType) Preconditions.checkNotNull(type, "Need a type");
        this.mMaxImageSize = (Size) Preconditions.checkNotNull(maxImageSize, "Need a size");
        this.mClient = null;
    }

    protected void setDrawable(Drawable drawable) {
        Consumer<Drawable> consumer = this.mClient;
        if (consumer != null) {
            consumer.accept(drawable);
        }
    }

    public void setImage(final Context context, T newRef) {
        if (isSameImage(context, newRef)) {
            return;
        }
        prepareForNewBinding(context);
        this.mCurrentRef = newRef;
        if (newRef == null) {
            setDrawable(null);
            return;
        }
        Drawable image = newRef.getImage(context);
        if (image != null) {
            setDrawable(image);
            return;
        }
        this.mFetchReceiver = new BiConsumer() { // from class: com.android.car.apps.common.imaging.-$$Lambda$ImageBinder$QkmNHfSwNsyPg0mofnvRbDmwAAI
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.f$0.lambda$setImage$0$ImageBinder(context, (ImageKey) obj, (Drawable) obj2);
            }
        };
        if (UriUtils.isEmpty(this.mCurrentRef.getImageURI())) {
            this.mCurrentKey = null;
            this.mFetchReceiver.accept(null, null);
        } else {
            this.mCurrentKey = new ImageKey(this.mCurrentRef.getImageURI(), this.mMaxImageSize);
            getImageFetcher(context).getImage(context, this.mCurrentKey, this.mFetchReceiver);
        }
    }

    public /* synthetic */ void lambda$setImage$0$ImageBinder(Context context, ImageKey key, Drawable drawable) {
        Drawable displayed;
        if (Objects.equals(this.mCurrentKey, key)) {
            if (drawable == null && this.mPlaceholderType != PlaceholderType.NONE) {
                displayed = this.mCurrentRef.getPlaceholder(context, this.mPlaceholderType);
            } else {
                displayed = drawable;
            }
            setDrawable(displayed);
            onRequestFinished();
        }
    }

    private boolean isSameImage(Context context, T newRef) {
        if (this.mCurrentRef == null && newRef == null) {
            return true;
        }
        T t = this.mCurrentRef;
        if (t != null && newRef != null) {
            return t.equals(context, newRef);
        }
        return false;
    }

    private LocalImageFetcher getImageFetcher(Context context) {
        return LocalImageFetcher.getInstance(context);
    }

    protected void prepareForNewBinding(Context context) {
        if (this.mCurrentKey != null) {
            getImageFetcher(context).cancelRequest(this.mCurrentKey, this.mFetchReceiver);
            onRequestFinished();
        }
        setDrawable(this.mPlaceholderType != PlaceholderType.NONE ? getLoadingDrawable(context) : null);
    }

    private void onRequestFinished() {
        this.mCurrentKey = null;
        this.mFetchReceiver = null;
    }

    private Drawable getLoadingDrawable(Context context) {
        if (this.mLoadingDrawable == null) {
            int color = context.getColor(R.color.loading_image_placeholder_color);
            this.mLoadingDrawable = new ColorDrawable(color);
        }
        return this.mLoadingDrawable;
    }
}
