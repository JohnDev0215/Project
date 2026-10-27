package com.amazon.mp3.library.cache.image;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.task.AbstractMetadata;

/* JADX INFO: loaded from: classes3.dex */
public class ImageMetadata extends AbstractMetadata {
    private int mFlags = 0;
    private String mId;
    private Drawable mImage;
    private String mKey;
    private int mSize;
    private String mSource;
    private ImageLoaderFactory.ItemType mType;
    private String mUri;

    public ImageMetadata(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        this.mId = id;
        this.mType = type;
        this.mSize = size;
        this.mKey = makeKey(type, source, size, id);
        this.mSource = source;
    }

    public static String makeKey(ImageLoaderFactory.ItemType type, String source, int size, String id) {
        StringBuilder sb = new StringBuilder();
        sb.append(type.toString());
        sb.append('+');
        if (!TextUtils.isEmpty(source)) {
            sb.append(source);
            sb.append('+');
        }
        sb.append(size);
        sb.append('+');
        sb.append(id);
        return sb.toString();
    }

    @Override // com.amazon.mp3.task.AbstractMetadata
    public String getKey() {
        return this.mKey;
    }

    public String getId() {
        return this.mId;
    }

    public ImageLoaderFactory.ItemType getType() {
        return this.mType;
    }

    public int getSize() {
        return this.mSize;
    }

    public String getSource() {
        return this.mSource;
    }

    public Drawable getImage() {
        return this.mImage;
    }

    public void setImage(Drawable image) {
        this.mImage = image;
    }

    public void setUri(String uri) {
        this.mUri = uri;
    }

    public String getUri() {
        return this.mUri;
    }

    public void addFlags(int newFlags) {
        this.mFlags = newFlags | this.mFlags;
    }
}
