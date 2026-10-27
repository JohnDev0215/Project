package com.amazon.video.sdk.avod.playbackclient.subtitle.views;

import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.avod.playbackclient.subtitle.SubtitleEventReporter;
import com.amazon.avod.playbackclient.subtitle.internal.CompositeSubtitleElement;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleAttribute;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.internal.attributes.Dimension;
import com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController;
import com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderer;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPreset;
import com.amazon.avod.util.DLog;
import com.amazon.video.player.R$dimen;
import com.amazon.video.player.R$drawable;
import com.amazon.video.player.R$id;
import com.amazon.video.sdk.avod.widget.FitInFrameLayout;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class DefaultSubtitleTextController extends SubtitleTextController {
    private final int mBasePaddingHorizontal;
    private final int mBasePaddingVertical;
    private final int mBaseTextSize;
    private CompositeSubtitleElement mCurrentSubtitle;
    private final EdgeRenderer mEdgeRenderer;
    private final SubtitleViewFormatter mFormatter;
    private final int mMenuShowingPadding;
    private final ViewGroup mRootView;
    private boolean mSubtitleMenuShowing;
    private TextView mSubtitleTextView;
    private FitInFrameLayout mTextContainer;

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void applyLanguageCode(@Nonnull String str) {
    }

    public DefaultSubtitleTextController(@Nonnull ViewGroup viewGroup) {
        this(viewGroup, new EdgeRenderer());
    }

    DefaultSubtitleTextController(@Nonnull ViewGroup viewGroup, @Nonnull EdgeRenderer edgeRenderer) {
        this.mCurrentSubtitle = CompositeSubtitleElement.NO_CAPTION;
        this.mSubtitleMenuShowing = false;
        this.mRootView = (ViewGroup) Preconditions.checkNotNull(viewGroup, "rootView");
        this.mEdgeRenderer = (EdgeRenderer) Preconditions.checkNotNull(edgeRenderer, "edgeRenderer");
        initializeSubtitleViews();
        Resources resources = viewGroup.getResources();
        this.mBasePaddingVertical = resources.getDimensionPixelSize(R$dimen.avod_subtitle_full_screen_bottom_padding);
        this.mBasePaddingHorizontal = resources.getDimensionPixelSize(R$dimen.avod_subtitle_full_screen_horizontal_padding);
        this.mFormatter = new SubtitleViewFormatter(this.mSubtitleTextView, new Point(), SubtitleConfig.getInstance());
        this.mMenuShowingPadding = resources.getDimensionPixelSize(R$dimen.avod_subtitle_with_menu_bottom_padding);
        this.mBaseTextSize = resources.getDimensionPixelSize(R$dimen.avod_languages_base_text_size);
        hideSubtitle();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void initialize(SubtitleEventReporter subtitleEventReporter) {
        updatePosition();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void setSubtitle(@Nonnull CompositeSubtitleElement compositeSubtitleElement) {
        this.mCurrentSubtitle = (CompositeSubtitleElement) Preconditions.checkNotNull(compositeSubtitleElement, MediaTrack.ROLE_SUBTITLE);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void showSubtitle() {
        if (this.mCurrentSubtitle.getText().isEmpty()) {
            hideSubtitle();
        } else {
            this.mFormatter.formatView(this.mCurrentSubtitle);
            this.mSubtitleTextView.setVisibility(0);
        }
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void hideSubtitle() {
        this.mSubtitleTextView.setVisibility(8);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void applyRenderPreset(@Nonnull SubtitleRenderPreset subtitleRenderPreset) {
        Preconditions.checkNotNull(subtitleRenderPreset, "Cannot set null subtitle preset");
        DLog.devf("Applying subtitle preset %s", subtitleRenderPreset);
        applyPresetToView(subtitleRenderPreset, this.mSubtitleTextView);
    }

    private void applyPresetToView(@Nonnull SubtitleRenderPreset subtitleRenderPreset, @Nonnull TextView textView) {
        textView.setTextSize(this.mBaseTextSize * subtitleRenderPreset.getTextFontScale());
        textView.setTextColor(subtitleRenderPreset.getTextArgbColor());
        textView.setTypeface(subtitleRenderPreset.getTextTypeface());
        textView.setTransformationMethod(subtitleRenderPreset.getTextTransformationMethod());
        GradientDrawable gradientDrawable = (GradientDrawable) textView.getBackground();
        gradientDrawable.mutate();
        gradientDrawable.setColor(subtitleRenderPreset.getBackgroundArgbColor());
        gradientDrawable.setStroke((int) textView.getResources().getDimension(R$dimen.avod_spacing_micro), subtitleRenderPreset.getWindowArgbColor());
        this.mEdgeRenderer.applyEdge(textView, subtitleRenderPreset.getTextEdgeType());
        textView.setText(textView.getText(), TextView.BufferType.SPANNABLE);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void reset() {
        updatePosition();
        hideSubtitle();
    }

    private void updatePosition() {
        int i = this.mBasePaddingVertical;
        if (this.mSubtitleMenuShowing) {
            i += this.mMenuShowingPadding;
        }
        DLog.devf("SubtitleText: updated bottom padding to %s", Integer.valueOf(i));
        FitInFrameLayout fitInFrameLayout = this.mTextContainer;
        int i2 = this.mBasePaddingHorizontal;
        fitInFrameLayout.setPadding(i2, 0, i2, i);
    }

    private void initializeSubtitleViews() {
        try {
            FitInFrameLayout fitInFrameLayout = (FitInFrameLayout) this.mRootView.findViewById(R$id.SubtitleContainer);
            this.mTextContainer = fitInFrameLayout;
            if (fitInFrameLayout != null) {
                this.mSubtitleTextView = (TextView) fitInFrameLayout.findViewById(R$id.SubtitleView);
            }
        } catch (NoSuchFieldError unused) {
            DLog.warnf("DefaultSubtitleTextController failed to find subtitle container, will attempt to create one.");
        }
        if (this.mTextContainer == null) {
            FitInFrameLayout fitInFrameLayout2 = new FitInFrameLayout(this.mRootView.getContext());
            fitInFrameLayout2.setId(R$id.SubtitleContainer);
            fitInFrameLayout2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            this.mTextContainer = fitInFrameLayout2;
            this.mRootView.addView(fitInFrameLayout2);
        }
        if (this.mSubtitleTextView == null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            int dimensionPixelSize = this.mRootView.getResources().getDimensionPixelSize(R$dimen.avod_spacing_xxsmall);
            TextView textView = new TextView(this.mRootView.getContext());
            this.mSubtitleTextView = textView;
            textView.setId(R$id.SubtitleView);
            this.mSubtitleTextView.setBackgroundResource(R$drawable.window_with_border);
            this.mSubtitleTextView.setLayoutParams(layoutParams);
            this.mSubtitleTextView.setGravity(81);
            this.mSubtitleTextView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
            this.mTextContainer.addView(this.mSubtitleTextView);
        }
    }

    class SubtitleViewFormatter {
        private final SubtitleConfig mConfig;
        private final Point mDisplaySize;
        private final TextView mView;

        public SubtitleViewFormatter(@Nonnull TextView textView, @Nonnull Point point, @Nonnull SubtitleConfig subtitleConfig) {
            this.mView = (TextView) Preconditions.checkNotNull(textView, "view");
            this.mDisplaySize = (Point) Preconditions.checkNotNull(point, "displaySize");
            this.mConfig = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
            initDisplaySize();
        }

        private void initDisplaySize() {
            ((WindowManager) DefaultSubtitleTextController.this.mRootView.getContext().getSystemService("window")).getDefaultDisplay().getSize(this.mDisplaySize);
            DLog.logf("SubtitleText: display initialized to %s", this.mDisplaySize);
        }

        public void formatView(@Nonnull CompositeSubtitleElement compositeSubtitleElement) {
            FrameLayout.LayoutParams layoutParamsNewWrapParams;
            Preconditions.checkNotNull(compositeSubtitleElement, "element");
            if (this.mView.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                layoutParamsNewWrapParams = (FrameLayout.LayoutParams) this.mView.getLayoutParams();
            } else {
                layoutParamsNewWrapParams = newWrapParams();
            }
            int gravity = this.mConfig.isDynamicPositioningEnabled() ? compositeSubtitleElement.getGravity() : 49;
            Dimension origin = this.mConfig.isDynamicPositioningEnabled() ? compositeSubtitleElement.getOrigin() : SubtitleAttribute.Defaults.ORIGIN;
            Dimension extent = this.mConfig.isDynamicPositioningEnabled() ? compositeSubtitleElement.getExtent() : SubtitleAttribute.Defaults.EXTENT;
            layoutParamsNewWrapParams.gravity = gravity;
            layoutParamsNewWrapParams.leftMargin = applyPct(this.mDisplaySize.x, origin.getX());
            layoutParamsNewWrapParams.topMargin = applyPct(this.mDisplaySize.y, origin.getY());
            layoutParamsNewWrapParams.rightMargin = applyPct(this.mDisplaySize.x, (100.0d - origin.getX()) - extent.getX());
            layoutParamsNewWrapParams.bottomMargin = applyPct(this.mDisplaySize.y, (100.0d - origin.getY()) - extent.getY());
            this.mView.setText(compositeSubtitleElement.getText());
            this.mView.setGravity(compositeSubtitleElement.getGravity());
            this.mView.setLayoutParams(layoutParamsNewWrapParams);
        }

        private int applyPct(int i, double d) {
            return Math.round(Math.round(((double) i) * d) / 100.0f);
        }

        private FrameLayout.LayoutParams newWrapParams() {
            return new FrameLayout.LayoutParams(-2, -2);
        }
    }
}
