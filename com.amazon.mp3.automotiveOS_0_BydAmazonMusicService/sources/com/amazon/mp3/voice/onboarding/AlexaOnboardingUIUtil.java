package com.amazon.mp3.voice.onboarding;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.ScreenUtil;
import com.amazon.ui.foundations.utils.SpacerParser;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaOnboardingUIUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingUIUtil;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaOnboardingUIUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int IMAGE_SIZE_XSMALL = 130;
    private static final int IMAGE_SIZE_SMALL = CBORConstants.PREFIX_TYPE_OBJECT;
    private static final int IMAGE_SIZE_NORMAL = 235;

    /* JADX INFO: compiled from: AlexaOnboardingUIUtil.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u0011J\u001e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/voice/onboarding/AlexaOnboardingUIUtil$Companion;", "", "()V", "IMAGE_SIZE_NORMAL", "", "getIMAGE_SIZE_NORMAL", "()I", "IMAGE_SIZE_SMALL", "getIMAGE_SIZE_SMALL", "IMAGE_SIZE_XSMALL", "getIMAGE_SIZE_XSMALL", "getCurrentScreenWidth", "context", "Landroid/content/Context;", "getTitleFontSize", "Lcom/amazon/ui/foundations/styles/FontSize;", "resources", "Landroid/content/res/Resources;", "fontSizeKey", "Lcom/amazon/music/views/library/styles/keys/FontSizeKey;", "getTitleFontSizeKey", "setOnboardingImageSize", "", "onboardingImageView", "Landroid/widget/ImageView;", "setOnboardingTitle", "titleTextView", "Landroid/widget/TextView;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: AlexaOnboardingUIUtil.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[FontSizeKey.values().length];
                iArr[FontSizeKey.HEADLINE_1.ordinal()] = 1;
                iArr[FontSizeKey.HEADLINE_2.ordinal()] = 2;
                iArr[FontSizeKey.HEADLINE_3.ordinal()] = 3;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getIMAGE_SIZE_XSMALL() {
            return AlexaOnboardingUIUtil.IMAGE_SIZE_XSMALL;
        }

        public final int getIMAGE_SIZE_SMALL() {
            return AlexaOnboardingUIUtil.IMAGE_SIZE_SMALL;
        }

        public final int getIMAGE_SIZE_NORMAL() {
            return AlexaOnboardingUIUtil.IMAGE_SIZE_NORMAL;
        }

        public final void setOnboardingTitle(TextView titleTextView, Resources resources, StyleSheet styleSheet) {
            String string;
            Intrinsics.checkNotNullParameter(titleTextView, "titleTextView");
            Intrinsics.checkNotNullParameter(resources, "resources");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            SpannableString spannableString = new SpannableString(resources.getString(R.string.control_music_with_your_voice));
            Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
            if (color != null) {
                spannableString.setSpan(new ForegroundColorSpan(color.intValue()), 0, spannableString.length(), 33);
            }
            SpannableString spannableString2 = new SpannableString(resources.getString(R.string.just_ask_alexa));
            Integer color2 = styleSheet.getColor(ColorKey.COLOR_ACCENT);
            if (color2 != null) {
                spannableString2.setSpan(new ForegroundColorSpan(color2.intValue()), 0, spannableString2.length(), 33);
            }
            titleTextView.setText(TextUtils.concat(spannableString, " ", spannableString2));
            Context context = titleTextView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "titleTextView.context");
            FontSize titleFontSize = getTitleFontSize(resources, getTitleFontSizeKey(context, resources));
            AssetManager assets = resources.getAssets();
            if (AmazonApplication.getCapabilities().isSonicRushEnabled()) {
                string = resources.getString(R.string.font_sharp_grotesk_bold);
            } else {
                string = resources.getString(R.string.font_amazon_ember_display_condensed);
            }
            Typeface typefaceCreateFromAsset = Typeface.createFromAsset(assets, string);
            FontUtil.INSTANCE.applyFontSize(titleTextView, titleFontSize);
            titleTextView.setTypeface(typefaceCreateFromAsset);
        }

        public final void setOnboardingImageSize(ImageView onboardingImageView, Resources resources) {
            int image_size_normal;
            Intrinsics.checkNotNullParameter(onboardingImageView, "onboardingImageView");
            Intrinsics.checkNotNullParameter(resources, "resources");
            int dimension = (int) resources.getDimension(R.dimen.screen_size_360);
            int dimension2 = (int) resources.getDimension(R.dimen.screen_size_375);
            Context context = onboardingImageView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "onboardingImageView.context");
            int currentScreenWidth = getCurrentScreenWidth(context);
            if (currentScreenWidth >= 0 && currentScreenWidth <= dimension) {
                image_size_normal = getIMAGE_SIZE_XSMALL();
            } else {
                if (dimension + 1 <= currentScreenWidth && currentScreenWidth <= dimension2) {
                    image_size_normal = getIMAGE_SIZE_SMALL();
                } else {
                    image_size_normal = getIMAGE_SIZE_NORMAL();
                }
            }
            ViewGroup.LayoutParams layoutParams = onboardingImageView.getLayoutParams();
            SpacerParser spacerParser = SpacerParser.INSTANCE;
            layoutParams.width = spacerParser.dpToPixels(resources, image_size_normal);
            onboardingImageView.getLayoutParams().height = spacerParser.dpToPixels(resources, image_size_normal);
            onboardingImageView.requestLayout();
        }

        private final FontSizeKey getTitleFontSizeKey(Context context, Resources resources) {
            int dimension = (int) resources.getDimension(R.dimen.screen_size_360);
            int dimension2 = (int) resources.getDimension(R.dimen.screen_size_480);
            int currentScreenWidth = getCurrentScreenWidth(context);
            if (currentScreenWidth >= 0 && currentScreenWidth <= dimension) {
                return FontSizeKey.HEADLINE_3;
            }
            if (dimension + 1 <= currentScreenWidth && currentScreenWidth < dimension2) {
                return FontSizeKey.HEADLINE_2;
            }
            return FontSizeKey.HEADLINE_1;
        }

        private final FontSize getTitleFontSize(Resources resources, FontSizeKey fontSizeKey) {
            int i = WhenMappings.$EnumSwitchMapping$0[fontSizeKey.ordinal()];
            return new FontSize((int) resources.getDimension(i != 1 ? i != 2 ? R.dimen.alexa_onboarding_title_size_h3 : R.dimen.alexa_onboarding_title_size_h2 : R.dimen.alexa_onboarding_title_size_h1), null, 2, null);
        }

        private final int getCurrentScreenWidth(Context context) {
            Object systemService = context.getSystemService("window");
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
            }
            return ScreenUtil.INSTANCE.getScreenWidthPixels((WindowManager) systemService);
        }
    }
}
