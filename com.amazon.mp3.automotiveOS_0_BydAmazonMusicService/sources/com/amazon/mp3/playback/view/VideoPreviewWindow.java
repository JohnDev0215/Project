package com.amazon.mp3.playback.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.google.common.base.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPreviewWindow.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010$\u001a\u00020%J\u000e\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\u0011J\u0016\u0010(\u001a\u00020%2\u0006\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020\nJ\u0006\u0010+\u001a\u00020%J\u0006\u0010,\u001a\u00020%J\u0006\u0010-\u001a\u00020%J\u000e\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u000200J\u0010\u00101\u001a\u00020%2\u0006\u0010/\u001a\u000200H\u0002J\b\u00102\u001a\u00020%H\u0002J\u0010\u00103\u001a\u00020%2\b\u00104\u001a\u0004\u0018\u000105J\u0018\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020\n2\u0006\u00108\u001a\u00020\nH\u0002J\u0018\u00109\u001a\u00020%2\u0006\u00107\u001a\u00020\n2\u0006\u00108\u001a\u00020\nH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/amazon/mp3/playback/view/VideoPreviewWindow;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "isVideoChanged", "", "mHeightOffset", "", "mHorizontalOffset", "mImageHeight", "mImageWidth", "mMaxAdjustedPositionX", "mMinAdjustedPositionX", "mParentView", "Landroid/view/View;", "mParentViewLocationHolder", "", "mSmallScreenHorizontalOffset", "mSmallScreenImageHeight", "mSmallScreenImageWidth", "mTimeTextView", "Landroid/widget/TextView;", "mVerticalOffset", "mVideoPreviewImageView", "Landroid/widget/ImageView;", "mWideScreenHorizontalOffset", "mWideScreenImageHeight", "mWideScreenImageWidth", "mWidthOffset", "mWindowHeight", "mWindowWidth", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "hide", "", "initialize", "userControlsRootView", "move", "xPos", "yPos", "resetVideoPreviewWindow", "show", "updateDimensions", "updateImage", "drawable", "Landroid/graphics/drawable/Drawable;", "updateImageAndWindowDimensions", "updateParentGlobalBounds", "updateText", "timeText", "", "updateWindowDimensions", "windowWidth", "windowHeight", "updateWindowOffsets", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VideoPreviewWindow extends FrameLayout {
    private boolean isVideoChanged;
    private int mHeightOffset;
    private int mHorizontalOffset;
    private int mImageHeight;
    private int mImageWidth;
    private int mMaxAdjustedPositionX;
    private int mMinAdjustedPositionX;
    private View mParentView;
    private final int[] mParentViewLocationHolder;
    private int mSmallScreenHorizontalOffset;
    private int mSmallScreenImageHeight;
    private int mSmallScreenImageWidth;
    private TextView mTimeTextView;
    private final int mVerticalOffset;
    private final ImageView mVideoPreviewImageView;
    private int mWideScreenHorizontalOffset;
    private int mWideScreenImageHeight;
    private int mWideScreenImageWidth;
    private int mWidthOffset;
    private int mWindowHeight;
    private int mWindowWidth;
    private StyleSheet styleSheet;

    /* JADX INFO: compiled from: VideoPreviewWindow.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr[ScreenSizeKey.SMALL.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoPreviewWindow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mParentViewLocationHolder = new int[2];
        this.isVideoChanged = true;
        View.inflate(context, R.layout.videopreview_window_layout, this);
        View viewFindViewById = findViewById(R.id.VideoPreviewImageView);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        this.mVideoPreviewImageView = (ImageView) viewFindViewById;
        Resources resources = context.getResources();
        View viewFindViewById2 = findViewById(R.id.videoPreviewTimeText);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.videoPreviewTimeText)");
        this.mTimeTextView = (TextView) viewFindViewById2;
        this.mVerticalOffset = resources.getDimensionPixelSize(R.dimen.videopreview_vertical_offset);
        this.mSmallScreenHorizontalOffset = resources.getDimensionPixelSize(R.dimen.videopreview_horizontal_offset);
        this.mWideScreenHorizontalOffset = resources.getDimensionPixelSize(R.dimen.videopreview_wide_screen_horizontal_offset);
        this.mSmallScreenImageWidth = resources.getDimensionPixelOffset(R.dimen.videopreview_image_width);
        this.mSmallScreenImageHeight = resources.getDimensionPixelOffset(R.dimen.videopreview_image_height);
        this.mWideScreenImageWidth = resources.getDimensionPixelOffset(R.dimen.videopreview_wide_screen_image_width);
        this.mWideScreenImageHeight = resources.getDimensionPixelOffset(R.dimen.videopreview_wide_screen_image_height);
    }

    public final void initialize(View userControlsRootView) {
        Intrinsics.checkNotNullParameter(userControlsRootView, "userControlsRootView");
        this.mParentView = (View) Preconditions.checkNotNull(userControlsRootView, "userControlsRootView", new Object[0]);
        MutableLiveData<StyleSheet> styleSheet = StyleSheetProvider.getStyleSheet();
        Context context = getContext();
        if (context == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        }
        styleSheet.observe((FragmentActivity) context, new Observer() { // from class: com.amazon.mp3.playback.view.VideoPreviewWindow$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPreviewWindow.m1524initialize$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
        resetVideoPreviewWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initialize$lambda-0, reason: not valid java name */
    public static final void m1524initialize$lambda0(VideoPreviewWindow this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this$0.styleSheet = styleSheet;
    }

    public final void resetVideoPreviewWindow() {
        updateParentGlobalBounds();
        updateWindowOffsets(this.mImageWidth, this.mImageHeight);
        this.mVideoPreviewImageView.setImageResource(0);
        TextView textView = this.mTimeTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTimeTextView");
            textView = null;
        }
        textView.setVisibility(8);
        this.isVideoChanged = true;
    }

    private final void updateParentGlobalBounds() {
        View view = this.mParentView;
        if (view == null) {
            return;
        }
        view.getLocationOnScreen(this.mParentViewLocationHolder);
    }

    public final void show() {
        setVisibility(0);
    }

    public final void hide() {
        setVisibility(8);
    }

    public final void updateDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Context context = getContext();
        if (context == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
        }
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        StyleSheet styleSheet = this.styleSheet;
        ScreenSizeKey screenSizeKey = styleSheet == null ? null : styleSheet.getScreenSizeKey(i);
        int i2 = screenSizeKey == null ? -1 : WhenMappings.$EnumSwitchMapping$0[screenSizeKey.ordinal()];
        if (i2 == 1) {
            this.mImageWidth = this.mSmallScreenImageWidth;
            this.mImageHeight = this.mSmallScreenImageHeight;
            this.mHorizontalOffset = this.mSmallScreenHorizontalOffset;
        } else if (i2 == 2) {
            this.mImageWidth = this.mWideScreenImageWidth;
            this.mImageHeight = this.mWideScreenImageHeight;
            this.mHorizontalOffset = this.mSmallScreenHorizontalOffset;
        } else {
            this.mImageWidth = this.mWideScreenImageWidth;
            this.mImageHeight = this.mWideScreenImageHeight;
            this.mHorizontalOffset = this.mWideScreenHorizontalOffset;
        }
    }

    public final void updateImage(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        updateDimensions();
        updateImageAndWindowDimensions(drawable);
        updateWindowOffsets(this.mWindowWidth, this.mWindowHeight);
        this.mVideoPreviewImageView.setImageDrawable(drawable);
        TextView textView = this.mTimeTextView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTimeTextView");
            textView = null;
        }
        if (textView.getVisibility() == 8) {
            TextView textView3 = this.mTimeTextView;
            if (textView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTimeTextView");
            } else {
                textView2 = textView3;
            }
            textView2.setVisibility(0);
        }
    }

    public final void updateText(String timeText) {
        TextView textView = this.mTimeTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTimeTextView");
            textView = null;
        }
        textView.setText(timeText);
    }

    private final void updateImageAndWindowDimensions(Drawable drawable) {
        ViewGroup.LayoutParams layoutParams = this.mVideoPreviewImageView.getLayoutParams();
        layoutParams.width = this.mImageWidth;
        layoutParams.height = this.mImageHeight;
        this.mVideoPreviewImageView.setLayoutParams(layoutParams);
        int i = this.mImageWidth;
        int i2 = this.mImageHeight;
        TextView textView = this.mTimeTextView;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTimeTextView");
            textView = null;
        }
        int height = i2 + textView.getHeight();
        TextView textView2 = this.mTimeTextView;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mTimeTextView");
            textView2 = null;
        }
        ViewGroup.LayoutParams layoutParams2 = textView2.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams2 instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams2 : null;
        updateWindowDimensions(i, height + (marginLayoutParams != null ? marginLayoutParams.topMargin : 0));
        this.isVideoChanged = false;
    }

    private final void updateWindowDimensions(int windowWidth, int windowHeight) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        this.mWindowWidth = windowWidth;
        this.mWindowHeight = windowHeight;
        layoutParams.width = windowWidth;
        layoutParams.height = windowHeight;
        setLayoutParams(layoutParams);
    }

    private final void updateWindowOffsets(int windowWidth, int windowHeight) {
        updateParentGlobalBounds();
        int[] iArr = this.mParentViewLocationHolder;
        this.mWidthOffset = (windowWidth / 2) + iArr[0];
        this.mHeightOffset = windowHeight + this.mVerticalOffset + iArr[1];
        this.mMinAdjustedPositionX = this.mHorizontalOffset;
        View view = this.mParentView;
        Intrinsics.checkNotNull(view);
        this.mMaxAdjustedPositionX = (view.getWidth() - windowWidth) - this.mHorizontalOffset;
    }

    public final void move(int xPos, int yPos) {
        int i;
        int i2 = xPos - this.mWidthOffset;
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            int i3 = this.mImageHeight;
            TextView textView = this.mTimeTextView;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTimeTextView");
                textView = null;
            }
            int height = i3 + textView.getHeight();
            TextView textView2 = this.mTimeTextView;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mTimeTextView");
                textView2 = null;
            }
            ViewGroup.LayoutParams layoutParams = textView2.getLayoutParams();
            ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
            yPos -= height + (marginLayoutParams != null ? marginLayoutParams.topMargin : 0);
            i = this.mVerticalOffset;
        } else {
            i = this.mHeightOffset;
        }
        int i4 = yPos - i;
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(getLayoutParams());
        int i5 = this.mMaxAdjustedPositionX;
        if (i2 > i5) {
            layoutParams2.setMargins(i5, i4, 0, 0);
        } else {
            int i6 = this.mMinAdjustedPositionX;
            if (i2 < i6) {
                layoutParams2.setMargins(i6, i4, 0, 0);
            } else {
                layoutParams2.setMargins(i2, i4, 0, 0);
            }
        }
        setLayoutParams(layoutParams2);
    }
}
