package com.amazon.mp3.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.amazon.mp3.library.cache.image.loader.BoxBlur;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class BitmapUtil {
    public static Bitmap convertDrawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getMinimumWidth(), drawable.getMinimumHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    public static Drawable convertBitmapToDrawable(Resources res, Bitmap bitmap) {
        return new BitmapDrawable(res, bitmap);
    }

    public static Bitmap generateBoxBlurredBitmapForDrawable(Drawable drawable, int range, int repetitions) {
        return BoxBlur.generateBoxBlurredBitmap(convertDrawableToBitmap(drawable), range, repetitions);
    }

    public static Bitmap cropBitmapAsCircle(Bitmap source) {
        if (source.getWidth() != source.getHeight()) {
            source = cropSquare(source);
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(source, tileMode, tileMode));
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(bitmapCreateBitmap).drawCircle(source.getWidth() / 2.0f, source.getHeight() / 2.0f, source.getHeight() / 2.0f, paint);
        return bitmapCreateBitmap;
    }

    public static Bitmap cropSquare(final Bitmap source) {
        int iMin = Math.min(source.getWidth(), source.getHeight());
        return Bitmap.createBitmap(source, source.getWidth() > source.getHeight() ? (source.getWidth() - iMin) / 2 : 0, 0, iMin, iMin);
    }

    public static Bitmap cropRoundedCornerRect(Bitmap source) {
        if (source.getWidth() != source.getHeight()) {
            source = cropSquare(source);
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        paint.setShader(new BitmapShader(source, tileMode, tileMode));
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        RectF rectF = new RectF(0.0f, 0.0f, source.getWidth(), source.getHeight());
        if (Math.min(source.getHeight(), source.getWidth()) >= 100) {
            canvas.drawRoundRect(rectF, 8.0f, 8.0f, paint);
        } else {
            canvas.drawRoundRect(rectF, 4.0f, 4.0f, paint);
        }
        return bitmapCreateBitmap;
    }

    public static BitmapDrawable cropDrawableAsRoundedCornerRect(Context context, BitmapDrawable drawable) {
        return new BitmapDrawable(context.getResources(), cropRoundedCornerRect(drawable.getBitmap()));
    }

    public static BitmapDrawable cropDrawableAsCircle(Context context, BitmapDrawable drawable) {
        return new BitmapDrawable(context.getResources(), cropBitmapAsCircle(drawable.getBitmap()));
    }

    public static Subscription cropDrawableAsCircle(Context context, BitmapDrawable drawable, ImageView... imageViews) {
        return new CropDrawableAsCircleTask(context, drawable.getBitmap(), imageViews).execute();
    }

    public static Subscription cropDrawableAsRoundedCornerRect(Context context, BitmapDrawable drawable, ImageView... imageViews) {
        return new CropDrawableAsRoundedCornerRectTask(context, drawable.getBitmap(), imageViews).execute();
    }

    public static Subscription cropDrawableAsRoundedCornerRect(Context context, Bitmap bitmap, ImageView... imageViews) {
        return new CropDrawableAsRoundedCornerRectTask(context, bitmap, imageViews).execute();
    }

    public static RequestCreator getPicassoRequestCreator(final String url) {
        return Picasso.get().load(transformUrl(url));
    }

    private static String transformUrl(final String url) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        return url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CropDrawableAsCircleTask {
        private Bitmap bitmap;
        private Context context;
        private ImageView[] imageViews;

        public CropDrawableAsCircleTask(final Context context, final Bitmap bitmap, final ImageView... imageViews) {
            this.imageViews = imageViews;
            this.bitmap = bitmap;
            this.context = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BitmapDrawable fetchDrawable() {
            return new BitmapDrawable(this.context.getResources(), BitmapUtil.cropBitmapAsCircle(this.bitmap));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDrawableFetched(final BitmapDrawable bitmapDrawable) {
            for (ImageView imageView : this.imageViews) {
                imageView.setImageDrawable(bitmapDrawable);
            }
        }

        public Subscription execute() {
            return Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.util.BitmapUtil$CropDrawableAsCircleTask$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.fetchDrawable();
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.util.BitmapUtil$CropDrawableAsCircleTask$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.onDrawableFetched((BitmapDrawable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class CropDrawableAsRoundedCornerRectTask {
        private Bitmap bitmap;
        private Context context;
        private ImageView[] imageViews;

        public CropDrawableAsRoundedCornerRectTask(final Context context, final Bitmap bitmap, final ImageView... imageViews) {
            this.imageViews = imageViews;
            this.bitmap = bitmap;
            this.context = context;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BitmapDrawable fetchDrawable() {
            return new BitmapDrawable(this.context.getResources(), BitmapUtil.cropRoundedCornerRect(this.bitmap));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDrawableFetched(final BitmapDrawable bitmapDrawable) {
            for (ImageView imageView : this.imageViews) {
                imageView.setImageDrawable(bitmapDrawable);
            }
        }

        public Subscription execute() {
            return Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.util.BitmapUtil$CropDrawableAsRoundedCornerRectTask$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.f$0.fetchDrawable();
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.util.BitmapUtil$CropDrawableAsRoundedCornerRectTask$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.onDrawableFetched((BitmapDrawable) obj);
                }
            });
        }
    }

    public static class BlurImageTransformation implements Transformation {
        private Context mContext;
        private float mRadius;

        @Override // com.squareup.picasso.Transformation
        public String key() {
            return "BlurTransformation";
        }

        public BlurImageTransformation(final Context context, final float radius) {
            this.mContext = context;
            this.mRadius = radius;
        }

        @Override // com.squareup.picasso.Transformation
        public Bitmap transform(Bitmap source) {
            Bitmap blurBitmap = BitmapEffectUtils.INSTANCE.getBlurBitmap(this.mContext, source, this.mRadius);
            source.recycle();
            return blurBitmap;
        }
    }
}
