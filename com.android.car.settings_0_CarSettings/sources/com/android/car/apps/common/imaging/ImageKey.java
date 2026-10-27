package com.android.car.apps.common.imaging;

import android.net.Uri;
import android.util.Size;
import androidx.core.util.Preconditions;
import com.android.car.apps.common.UriUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
class ImageKey {
    public final Uri mImageUri;
    public final Size mMaxImageSize;

    ImageKey(Uri imageUri, Size maxImageSize) {
        Preconditions.checkArgument(!UriUtils.isEmpty(imageUri), "Empty uri!");
        this.mImageUri = imageUri;
        this.mMaxImageSize = maxImageSize;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImageKey imageKey = (ImageKey) o;
        if (this.mImageUri.equals(imageKey.mImageUri) && this.mMaxImageSize.equals(imageKey.mMaxImageSize)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mImageUri, this.mMaxImageSize);
    }

    public String toString() {
        return "ImageKey{mImageUri=" + this.mImageUri + ", mMaxImageSize=" + this.mMaxImageSize + '}';
    }
}
