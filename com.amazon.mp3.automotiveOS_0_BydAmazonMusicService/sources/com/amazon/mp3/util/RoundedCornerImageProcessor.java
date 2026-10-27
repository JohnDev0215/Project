package com.amazon.mp3.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes4.dex */
public class RoundedCornerImageProcessor implements ImageProcessor {
    private final Context mContext;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "roundedCornerTransformation";
    }

    public RoundedCornerImageProcessor(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.mp3.util.ImageProcessor
    public void process(final BitmapDrawable drawable, final ImageView... imageViews) {
        this.subscription.add(BitmapUtil.cropDrawableAsRoundedCornerRect(this.mContext, drawable, imageViews));
    }

    public void process(final Bitmap drawable, final ImageView... imageViews) {
        this.subscription.add(BitmapUtil.cropDrawableAsRoundedCornerRect(this.mContext, drawable, imageViews));
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(final Bitmap source) {
        Bitmap bitmapCropRoundedCornerRect = BitmapUtil.cropRoundedCornerRect(source);
        source.recycle();
        return bitmapCropRoundedCornerRect;
    }
}
