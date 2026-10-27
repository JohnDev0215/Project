package com.amazon.music.picassoimageloader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import com.amazon.music.imageloader.ImageLoader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class PicassoImageLoader implements ImageLoader {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) PicassoImageLoader.class);
    private final Context context;
    private final Picasso picasso;
    private Object tag;
    private final Set<Target> targets;

    /* JADX INFO: renamed from: com.amazon.music.picassoimageloader.PicassoImageLoader$1, reason: invalid class name */
    class AnonymousClass1 implements Transformation {
    }

    /* synthetic */ PicassoImageLoader(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    private PicassoImageLoader(Builder builder) {
        this.targets = new HashSet();
        this.context = builder.context;
        this.picasso = builder.picasso;
        this.tag = builder.tag;
    }

    public void loadImage(String str, View view) {
        if (view instanceof ImageView) {
            loadIntoImageView(str, (ImageView) view, null);
        } else {
            loadIntoView(str, view, null);
        }
    }

    @Override // com.amazon.music.imageloader.ImageLoader
    public void loadImage(String str, View view, int i) {
        Drawable drawable = this.context.getResources().getDrawable(i);
        if (view instanceof ImageView) {
            loadIntoImageView(str, (ImageView) view, drawable);
        } else {
            loadIntoView(str, view, drawable);
        }
    }

    @Override // com.amazon.music.imageloader.ImageLoader
    public void loadImage(String str, View view, int i, ImageLoader.TransformationType transformationType) {
        loadImage(str, view, i, transformationType, 0);
    }

    public void loadImage(String str, View view, int i, ImageLoader.TransformationType transformationType, int i2) {
        loadImage(str, view, i, transformationType, i2, Boolean.FALSE);
    }

    @Override // com.amazon.music.imageloader.ImageLoader
    public void loadImage(String str, View view, int i, ImageLoader.TransformationType transformationType, int i2, Boolean bool) {
        Drawable drawable = this.context.getResources().getDrawable(i);
        Transformation transformationFromTransformationType = getTransformationFromTransformationType(transformationType);
        if (view instanceof ImageView) {
            loadIntoImageView(str, (ImageView) view, drawable, transformationFromTransformationType, i2);
        } else {
            loadIntoView(str, view, drawable, transformationFromTransformationType, i2, bool);
        }
    }

    @Override // com.amazon.music.imageloader.ImageLoader
    public void loadImage(String str, ImageLoader.ImageLoaderCallback imageLoaderCallback) {
        loadImage(str, imageLoaderCallback, (Drawable) null, (Transformation) null);
    }

    @Override // com.amazon.music.imageloader.ImageLoader
    public void loadImage(String str, ImageLoader.ImageLoaderCallback imageLoaderCallback, ImageLoader.TransformationType transformationType) {
        loadImage(str, imageLoaderCallback, (Drawable) null, getTransformationFromTransformationType(transformationType));
    }

    @Override // com.amazon.music.imageloader.ImageLoader
    public void loadImage(String str, ImageLoader.ImageLoaderCallback imageLoaderCallback, final com.amazon.music.imageloader.transformation.Transformation transformation) {
        loadImage(str, imageLoaderCallback, (Drawable) null, new Transformation() { // from class: com.amazon.music.picassoimageloader.PicassoImageLoader.2
            @Override // com.squareup.picasso.Transformation
            public Bitmap transform(Bitmap bitmap) {
                return transformation.transform(bitmap);
            }

            @Override // com.squareup.picasso.Transformation
            public String key() {
                return transformation.key();
            }
        });
    }

    /* JADX INFO: renamed from: com.amazon.music.picassoimageloader.PicassoImageLoader$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$imageloader$ImageLoader$TransformationType;

        static {
            int[] iArr = new int[ImageLoader.TransformationType.values().length];
            $SwitchMap$com$amazon$music$imageloader$ImageLoader$TransformationType = iArr;
            try {
                iArr[ImageLoader.TransformationType.ImageOverlay.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$imageloader$ImageLoader$TransformationType[ImageLoader.TransformationType.Circle.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$imageloader$ImageLoader$TransformationType[ImageLoader.TransformationType.Blur.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$imageloader$ImageLoader$TransformationType[ImageLoader.TransformationType.Blur2.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$imageloader$ImageLoader$TransformationType[ImageLoader.TransformationType.RoundCorner.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$imageloader$ImageLoader$TransformationType[ImageLoader.TransformationType.None.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private Transformation getTransformationFromTransformationType(ImageLoader.TransformationType transformationType) {
        if (transformationType == null) {
            return null;
        }
        int i = AnonymousClass7.$SwitchMap$com$amazon$music$imageloader$ImageLoader$TransformationType[transformationType.ordinal()];
        if (i == 1) {
            return new ImageOverlayTransformation(this.context, Resources.getSystem().getDisplayMetrics().heightPixels);
        }
        if (i == 2) {
            return new CircleTransformation();
        }
        if (i == 3) {
            return new ImageBlurTransformation();
        }
        if (i != 4) {
            if (i != 5) {
                return null;
            }
            return new ImageRoundCornerTransformation();
        }
        TypedValue typedValue = new TypedValue();
        this.context.getResources().getValue(R$dimen.blur_2, typedValue, true);
        return new ImageBlurTransformation((int) typedValue.getFloat());
    }

    private void loadImage(final String str, final ImageLoader.ImageLoaderCallback imageLoaderCallback, Drawable drawable, Transformation transformation) {
        Target target = new Target() { // from class: com.amazon.music.picassoimageloader.PicassoImageLoader.4
            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                imageLoaderCallback.onBitmapLoaded(bitmap);
                PicassoImageLoader.this.targets.remove(this);
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception exc, Drawable drawable2) {
                PicassoImageLoader.LOG.warn("Failed to load image from " + str, (Throwable) exc);
                PicassoImageLoader.this.targets.remove(this);
            }

            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable drawable2) {
                imageLoaderCallback.onPrepareLoad(drawable2);
            }
        };
        this.targets.add(target);
        setupRequestCreator(str, drawable, transformation).into(target);
    }

    private RequestCreator setupRequestCreator(String str, Drawable drawable, Transformation transformation) {
        return setupRequestCreator(str, drawable, transformation, 0);
    }

    private RequestCreator setupRequestCreator(String str, Drawable drawable, Transformation transformation, int i) {
        RequestCreator requestCreatorLoad = this.picasso.load(str);
        if (drawable != null) {
            requestCreatorLoad.placeholder(drawable);
        }
        if (transformation != null) {
            requestCreatorLoad.transform(transformation);
        }
        Object obj = this.tag;
        if (obj != null) {
            requestCreatorLoad.tag(obj);
        }
        if (i > 0) {
            requestCreatorLoad.rotate(i);
        }
        return requestCreatorLoad;
    }

    private void loadIntoImageView(String str, ImageView imageView, Drawable drawable) {
        loadIntoImageView(str, imageView, drawable, null, 0);
    }

    private void loadIntoImageView(String str, ImageView imageView, Drawable drawable, Transformation transformation, int i) {
        RequestCreator requestCreator = setupRequestCreator(str, drawable, transformation, i);
        if (imageView.getWidth() > 0 && imageView.getHeight() > 0) {
            requestCreator.resize(imageView.getWidth(), imageView.getHeight()).centerCrop(17).into(imageView);
        } else {
            requestCreator.fit().into(imageView);
        }
    }

    private void loadIntoView(String str, View view, Drawable drawable) {
        loadIntoView(str, view, drawable, null, 0);
    }

    private void loadIntoView(String str, View view, Drawable drawable, Transformation transformation, int i) {
        loadIntoView(str, view, drawable, transformation, i, Boolean.FALSE);
    }

    private void loadIntoView(final String str, final View view, Drawable drawable, Transformation transformation, int i, final Boolean bool) {
        Target target = new Target() { // from class: com.amazon.music.picassoimageloader.PicassoImageLoader.6
            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                view.post(new Runnable() { // from class: com.amazon.music.picassoimageloader.PicassoImageLoader.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AnonymousClass6 anonymousClass6 = AnonymousClass6.this;
                        PicassoImageLoader.this.setBitmapToView(bitmap, view, bool);
                    }
                });
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception exc, Drawable drawable2) {
                PicassoImageLoader.LOG.warn("Failed to load image from " + str, (Throwable) exc);
                PicassoImageLoader.this.setBackground(view, drawable2);
            }

            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable drawable2) {
                PicassoImageLoader.this.setBackground(view, drawable2);
            }
        };
        view.setTag(target);
        setupRequestCreator(str, drawable, transformation, i).into(target);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBitmapToView(Bitmap bitmap, View view, Boolean bool) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, view.getWidth(), view.getHeight(), false);
        if (bool.booleanValue()) {
            int iMax = Math.max(view.getWidth(), view.getHeight());
            bitmapCreateScaledBitmap = Bitmap.createBitmap(Bitmap.createScaledBitmap(bitmap, iMax, iMax, false), 0, 0, view.getWidth(), view.getHeight());
        }
        setBackground(view, new BitmapDrawable(this.context.getResources(), bitmapCreateScaledBitmap));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static Builder builder(Context context, Picasso picasso) {
        return new Builder(context, picasso, null);
    }

    public static final class Builder {
        private final Context context;
        private final Picasso picasso;
        private Object tag;

        /* synthetic */ Builder(Context context, Picasso picasso, AnonymousClass1 anonymousClass1) {
            this(context, picasso);
        }

        private Builder(Context context, Picasso picasso) {
            this.context = context.getApplicationContext();
            this.picasso = picasso;
        }

        public Builder withTag(Object obj) {
            this.tag = obj;
            return this;
        }

        public PicassoImageLoader build() {
            return new PicassoImageLoader(this, null);
        }
    }
}
