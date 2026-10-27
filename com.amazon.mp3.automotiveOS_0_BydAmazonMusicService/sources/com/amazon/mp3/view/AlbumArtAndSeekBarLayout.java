package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.views.BaseImage;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class AlbumArtAndSeekBarLayout extends FrameLayout {
    private static final String TAG = AlbumArtAndSeekBarLayout.class.getSimpleName();
    public BaseImage mAlbumArtImageView;
    private float mArtworkScalingFactor;
    private float mBaseImageScalingFactor;
    private BauhausSeekBarWidget mSeekBar;
    StyleSheet mStyleSheet;
    BaseImage mSwapArt;
    private Subscription styleSheetSubscription;

    public AlbumArtAndSeekBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mArtworkScalingFactor = 1.0f;
        this.mBaseImageScalingFactor = 1.0f;
        init(context);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, ((int) (measuredWidth * this.mArtworkScalingFactor)) + (this.mSeekBar.getMeasuredHeight() / 2));
    }

    public void init(Context context) {
        this.styleSheetSubscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.view.AlbumArtAndSeekBarLayout$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$init$0((StyleSheet) obj);
            }
        });
        View.inflate(context, R.layout.library_now_playing_album_art_with_seek_bar, this);
        BauhausSeekBarWidget bauhausSeekBarWidget = (BauhausSeekBarWidget) findViewById(R.id.PlayerCoverViewSeekBar);
        this.mSeekBar = bauhausSeekBarWidget;
        bauhausSeekBarWidget.setLayerType(1, null);
        BaseImage baseImage = (BaseImage) findViewById(R.id.PlayerCoverViewAlbumArt);
        this.mAlbumArtImageView = baseImage;
        baseImage.setScalingFactor(this.mBaseImageScalingFactor);
        this.mAlbumArtImageView.applyCornerRadius((int) context.getResources().getDimension(R.dimen.spacer_4));
        BaseImage baseImage2 = (BaseImage) findViewById(R.id.swap_art);
        this.mSwapArt = baseImage2;
        baseImage2.setScalingFactor(this.mBaseImageScalingFactor);
        this.mSwapArt.applyCornerRadius((int) context.getResources().getDimension(R.dimen.spacer_4));
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            this.mSeekBar.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(StyleSheet styleSheet) {
        this.mStyleSheet = styleSheet;
        BaseImage baseImage = this.mAlbumArtImageView;
        if (baseImage != null && styleSheet != null) {
            baseImage.setImageStyle(styleSheet.getImageStyle(ImageStyleKey.DEFAULT));
        }
        BaseImage baseImage2 = this.mSwapArt;
        if (baseImage2 == null || styleSheet == null) {
            return;
        }
        baseImage2.setImageStyle(styleSheet.getImageStyle(ImageStyleKey.DEFAULT));
    }

    public void setPortraitArtworkScalingFactor(float factor) {
        this.mArtworkScalingFactor = factor;
        float f = 1.0f / factor;
        this.mBaseImageScalingFactor = f;
        this.mAlbumArtImageView.setScalingFactor(f);
        this.mSwapArt.setScalingFactor(this.mBaseImageScalingFactor);
        this.mAlbumArtImageView.requestLayout();
        this.mSwapArt.requestLayout();
    }

    public Drawable createDrawableOverlayWithBlurBackground(BitmapDrawable image) {
        float fFloatValue = this.mStyleSheet.getBlurStyle(BlurStyleKey.BLUR_1).floatValue();
        int height = getHeight() - (this.mSeekBar.getHeight() / 2);
        int width = getWidth();
        if (image == null || height <= 0) {
            return null;
        }
        Bitmap bitmap = image.getBitmap();
        Bitmap bitmapCopy = Bitmap.createScaledBitmap(bitmap.copy(bitmap.getConfig(), true), width, height, false).copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmapCopy);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, height, height, false);
        Bitmap bitmapCreateScaledBitmap2 = Bitmap.createScaledBitmap(BitmapEffectUtils.INSTANCE.getBlurBitmap(getContext(), bitmapCopy, fFloatValue), width, height, false);
        int width2 = (canvas.getWidth() - bitmapCreateScaledBitmap.getWidth()) / 2;
        int height2 = (canvas.getHeight() - bitmapCreateScaledBitmap.getHeight()) / 2;
        canvas.drawBitmap(bitmapCreateScaledBitmap2, 0.0f, 0.0f, (Paint) null);
        canvas.drawBitmap(bitmapCreateScaledBitmap, width2, height2, (Paint) null);
        return new BitmapDrawable(getResources(), bitmapCopy);
    }

    public void setAlbumArtDrawable(Drawable drawable) {
        this.mAlbumArtImageView.setImage(drawable);
        this.mAlbumArtImageView.requestLayout();
    }

    public int getDefaultHeight() {
        int width = ((FrameLayout) getParent()).getWidth();
        int i = ((FrameLayout.LayoutParams) getLayoutParams()).leftMargin;
        int i2 = ((FrameLayout.LayoutParams) getLayoutParams()).rightMargin;
        BauhausSeekBarWidget bauhausSeekBarWidget = this.mSeekBar;
        int measuredHeight = 0;
        if (bauhausSeekBarWidget != null) {
            bauhausSeekBarWidget.measure(0, 0);
            measuredHeight = this.mSeekBar.getMeasuredHeight();
        }
        return ((((int) (width * 1.0f)) - i) - i2) + (measuredHeight / 2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.styleSheetSubscription.unsubscribe();
    }
}
