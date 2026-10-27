package com.amazon.podcast.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import androidx.appcompat.widget.AppCompatTextView;
import com.amazon.podcast.Podcast;
import com.amazonaws.util.StringUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PicassoImageGetter implements Html.ImageGetter {
    private static final Logger logger = LoggerFactory.getLogger("FeaturedSingleItemView");
    private final AppCompatTextView textView;

    public PicassoImageGetter(final AppCompatTextView target) {
        this.textView = target;
    }

    @Override // android.text.Html.ImageGetter
    public Drawable getDrawable(final String source) {
        BitmapDrawablePlaceHolder bitmapDrawablePlaceHolder = new BitmapDrawablePlaceHolder();
        Picasso.get().load(source).placeholder(Podcast.getRuntimeStyleSheet().getIcEmptyStateImageDrawableId()).into(bitmapDrawablePlaceHolder);
        return bitmapDrawablePlaceHolder;
    }

    private final class BitmapDrawablePlaceHolder extends BitmapDrawable implements Target {
        protected Drawable drawable;

        @Override // com.squareup.picasso.Target
        public final void onBitmapFailed(final Exception e, final Drawable drawable) {
        }

        @Override // com.squareup.picasso.Target
        public final void onPrepareLoad(final Drawable placeHolderDrawable) {
        }

        private BitmapDrawablePlaceHolder() {
        }

        @Override // android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable
        public final void draw(final Canvas canvas) {
            Drawable drawable = this.drawable;
            if (drawable == null) {
                return;
            }
            drawable.draw(canvas);
        }

        public void setDrawable(final Drawable drawable) {
            this.drawable = drawable;
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            if (PicassoImageGetter.this.textView == null || StringUtils.isBlank(PicassoImageGetter.this.textView.getText())) {
                return;
            }
            PicassoImageGetter.this.textView.setText(PicassoImageGetter.this.textView.getText());
        }

        @Override // com.squareup.picasso.Target
        public final void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom from) {
            PicassoImageGetter.logger.debug("onBitmapLoaded");
            setDrawable(new BitmapDrawable(PicassoImageGetter.this.textView.getResources(), bitmap));
        }
    }
}
