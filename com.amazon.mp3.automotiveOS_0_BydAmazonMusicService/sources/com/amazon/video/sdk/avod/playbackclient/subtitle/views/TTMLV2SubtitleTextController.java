package com.amazon.video.sdk.avod.playbackclient.subtitle.views;

import android.content.res.Resources;
import android.graphics.Point;
import android.text.Html;
import android.text.Spanned;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.avod.core.subtitle.SubtitleFontStyleType;
import com.amazon.avod.playbackclient.subtitle.SubtitleEventReporter;
import com.amazon.avod.playbackclient.subtitle.internal.CompositeSubtitleElement;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleAttribute;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleTagHandler;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleTextElement;
import com.amazon.avod.playbackclient.subtitle.internal.TTMLV2CanvasUtil;
import com.amazon.avod.playbackclient.subtitle.internal.attributes.Dimension;
import com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController;
import com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderer;
import com.amazon.avod.playbackclient.subtitle.views.util.SubtitleRenderPreset;
import com.amazon.avod.util.DLog;
import com.amazon.video.player.R$dimen;
import com.amazon.video.player.R$id;
import com.amazon.video.player.R$string;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.base.Preconditions;
import com.google.common.collect.UnmodifiableIterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
public class TTMLV2SubtitleTextController extends SubtitleTextController {
    private final int mBasePaddingHorizontal;
    private final int mBasePaddingVertical;
    private final int mBaseTextSize;
    private String mCurrentLanguageCode;
    private CompositeSubtitleElement mCurrentSubtitle;
    private final String mDefaultSubtitleSample;
    private final EdgeRenderer mEdgeRenderer;
    private final SubtitleViewFormatter mFormatter;
    private final int mMenuShowingPadding;
    private final ViewGroup mRootView;
    private boolean mSubtitleMenuShowing;
    private final FrameLayout mTextContainer;
    private final TTMLV2TextViewPool mTextViewPool;

    public TTMLV2SubtitleTextController(@Nonnull ViewGroup viewGroup) {
        this(viewGroup, new EdgeRenderer());
    }

    TTMLV2SubtitleTextController(@Nonnull ViewGroup viewGroup, @Nonnull EdgeRenderer edgeRenderer) {
        this(viewGroup, edgeRenderer, new TTMLV2TextViewPool(viewGroup, edgeRenderer));
    }

    TTMLV2SubtitleTextController(@Nonnull ViewGroup viewGroup, @Nonnull EdgeRenderer edgeRenderer, @Nonnull TTMLV2TextViewPool tTMLV2TextViewPool) {
        this.mCurrentSubtitle = CompositeSubtitleElement.NO_CAPTION;
        this.mSubtitleMenuShowing = false;
        ViewGroup viewGroup2 = (ViewGroup) Preconditions.checkNotNull(viewGroup, "rootView");
        this.mRootView = viewGroup2;
        this.mEdgeRenderer = (EdgeRenderer) Preconditions.checkNotNull(edgeRenderer, "edgeRenderer");
        this.mTextViewPool = (TTMLV2TextViewPool) Preconditions.checkNotNull(tTMLV2TextViewPool, "textViewPool");
        this.mTextContainer = (FrameLayout) Preconditions.checkNotNull((FrameLayout) viewGroup2.findViewById(R$id.SubtitleContainer));
        Resources resources = viewGroup.getResources();
        this.mBasePaddingVertical = resources.getDimensionPixelSize(R$dimen.avod_subtitle_full_screen_bottom_padding);
        this.mBasePaddingHorizontal = resources.getDimensionPixelSize(R$dimen.avod_subtitle_full_screen_horizontal_padding);
        this.mFormatter = new SubtitleViewFormatter(new Point(), SubtitleConfig.getInstance());
        this.mMenuShowingPadding = resources.getDimensionPixelSize(R$dimen.avod_subtitle_with_menu_bottom_padding);
        this.mDefaultSubtitleSample = resources.getString(R$string.AV_MOBILE_ANDROID_PLAYER_SUBTITLE_SAMPLE_TEXT);
        this.mBaseTextSize = resources.getDimensionPixelSize(R$dimen.avod_languages_base_text_size);
        hideSubtitle();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void initialize(SubtitleEventReporter subtitleEventReporter) {
        updatePosition();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void setSubtitle(@Nonnull CompositeSubtitleElement compositeSubtitleElement) {
        if (this.mCurrentSubtitle.getText().equals(compositeSubtitleElement.getText())) {
            return;
        }
        this.mCurrentSubtitle = (CompositeSubtitleElement) Preconditions.checkNotNull(compositeSubtitleElement, MediaTrack.ROLE_SUBTITLE);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void showSubtitle() {
        hideSubtitle();
        if (this.mCurrentSubtitle.getSubtitleTextElements().size() == 0) {
            return;
        }
        this.mTextViewPool.clear();
        UnmodifiableIterator<SubtitleTextElement> it = this.mCurrentSubtitle.getSubtitleTextElements().iterator();
        while (it.hasNext()) {
            SubtitleTextElement next = it.next();
            TextView textViewVendTextView = this.mTextViewPool.vendTextView();
            this.mFormatter.formatView(textViewVendTextView, next, next.isVertical());
            textViewVendTextView.setVisibility(0);
        }
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void hideSubtitle() {
        this.mTextViewPool.hideAllTextViews();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void applyRenderPreset(@Nonnull SubtitleRenderPreset subtitleRenderPreset) {
        Preconditions.checkNotNull(subtitleRenderPreset, "Cannot set null subtitle preset");
        DLog.devf("Applying subtitle preset %s", subtitleRenderPreset);
        this.mTextViewPool.applyRenderPreset(subtitleRenderPreset);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void applyLanguageCode(@Nonnull String str) {
        this.mCurrentLanguageCode = ((String) Preconditions.checkNotNull(str, "languageCode")).toLowerCase();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController
    public void reset() {
        updatePosition();
    }

    private void updatePosition() {
        int i = this.mBasePaddingVertical;
        if (this.mSubtitleMenuShowing) {
            i += this.mMenuShowingPadding;
        }
        DLog.devf("SubtitleText: updated bottom padding to %s", Integer.valueOf(i));
        FrameLayout frameLayout = this.mTextContainer;
        int i2 = this.mBasePaddingHorizontal;
        frameLayout.setPadding(i2, 0, i2, i);
    }

    class SubtitleViewFormatter {
        private final SubtitleConfig mConfig;
        private final Point mDisplaySize;

        public SubtitleViewFormatter(@Nonnull Point point, @Nonnull SubtitleConfig subtitleConfig) {
            this.mDisplaySize = (Point) Preconditions.checkNotNull(point, "displaySize");
            this.mConfig = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
            initDisplaySize();
        }

        private void initDisplaySize() {
            ((WindowManager) TTMLV2SubtitleTextController.this.mRootView.getContext().getSystemService("window")).getDefaultDisplay().getSize(this.mDisplaySize);
            DLog.logf("SubtitleText: display initialized to %s", this.mDisplaySize);
        }

        public void formatView(@Nonnull TextView textView, @Nonnull SubtitleTextElement subtitleTextElement, boolean z) {
            FrameLayout.LayoutParams layoutParamsNewWrapParams;
            Preconditions.checkNotNull(subtitleTextElement, "element");
            TTMLV2CanvasUtil tTMLV2CanvasUtil = new TTMLV2CanvasUtil(textView, subtitleTextElement, z);
            tTMLV2CanvasUtil.init();
            if (textView.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                layoutParamsNewWrapParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
            } else {
                layoutParamsNewWrapParams = newWrapParams();
            }
            Dimension extent = this.mConfig.isDynamicPositioningEnabled() ? TTMLV2SubtitleTextController.this.mCurrentSubtitle.getExtent(z) : SubtitleAttribute.Defaults.EXTENT;
            int gravity = this.mConfig.isDynamicPositioningEnabled() ? tTMLV2CanvasUtil.getGravity() : 49;
            Dimension origin = this.mConfig.isDynamicPositioningEnabled() ? TTMLV2SubtitleTextController.this.mCurrentSubtitle.getOrigin(gravity) : SubtitleAttribute.Defaults.ORIGIN;
            Spanned spannedFromHtml = Html.fromHtml(subtitleTextElement.getSubtitleText(), null, new SubtitleTagHandler(subtitleTextElement, tTMLV2CanvasUtil, TTMLV2SubtitleTextController.this.mCurrentLanguageCode));
            layoutParamsNewWrapParams.leftMargin = applyPct(this.mDisplaySize.x, origin.getX());
            layoutParamsNewWrapParams.topMargin = applyPct(this.mDisplaySize.y, origin.getY());
            layoutParamsNewWrapParams.rightMargin = applyPct(this.mDisplaySize.x, (100.0d - origin.getX()) - extent.getX());
            layoutParamsNewWrapParams.bottomMargin = applyPct(this.mDisplaySize.y, (100.0d - origin.getY()) - extent.getY());
            layoutParamsNewWrapParams.gravity = gravity;
            if (subtitleTextElement.getStyleElement() != null) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleFontStyleType[tTMLV2CanvasUtil.getFontStyle().ordinal()];
                if (i == 1 || i == 2) {
                    textView.setTypeface(textView.getTypeface(), 2);
                } else {
                    textView.setTypeface(textView.getTypeface(), 0);
                }
            }
            textView.setWidth(tTMLV2CanvasUtil.getWidth());
            textView.setHeight(tTMLV2CanvasUtil.getHeight());
            textView.setText(spannedFromHtml);
            textView.setLayoutParams(layoutParamsNewWrapParams);
            textView.setVisibility(0);
        }

        private int applyPct(int i, double d) {
            return Math.round(Math.round(((double) i) * d) / 100.0f);
        }

        private FrameLayout.LayoutParams newWrapParams() {
            return new FrameLayout.LayoutParams(-2, -2);
        }
    }

    /* JADX INFO: renamed from: com.amazon.video.sdk.avod.playbackclient.subtitle.views.TTMLV2SubtitleTextController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleFontStyleType;

        static {
            int[] iArr = new int[SubtitleFontStyleType.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleFontStyleType = iArr;
            try {
                iArr[SubtitleFontStyleType.ITALIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleFontStyleType[SubtitleFontStyleType.OBLIQUE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleFontStyleType[SubtitleFontStyleType.NORMAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
