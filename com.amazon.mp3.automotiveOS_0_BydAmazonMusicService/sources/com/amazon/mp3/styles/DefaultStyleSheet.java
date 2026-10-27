package com.amazon.mp3.styles;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Range;
import android.util.TypedValue;
import android.view.WindowManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.views.library.styles.LinkStyleKey;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.DividerStyleKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.styles.keys.GridStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.ImageSizeKey;
import com.amazon.music.views.library.styles.keys.ImageStyleKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.styles.keys.IndicatorStyleKey;
import com.amazon.music.views.library.styles.keys.PopularityWidgetStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.DividerStyle;
import com.amazon.ui.foundations.styles.FontSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.styles.GridStyle;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.styles.ImageStyle;
import com.amazon.ui.foundations.styles.LinkStyle;
import com.amazon.ui.foundations.styles.OutlineStyle;
import com.amazon.ui.foundations.styles.PopularityBarStyle;
import com.amazon.ui.foundations.styles.PopularityWidgetStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.ColorParser;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.visualon.OSMPUtils.voOSType;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: DefaultStyleSheet.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B\u000f\u0012\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b)\u0010*J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0005H\u0002J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0005H\u0002J\b\u0010\u000b\u001a\u00020\u0002H\u0014J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0017\u0010\u0014\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0019\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0011H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\fH\u0016J\u001f\u0010\u001f\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u001c\u0010%\u001a\n $*\u0004\u0018\u00010#0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&¨\u0006,"}, d2 = {"Lcom/amazon/mp3/styles/DefaultStyleSheet;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "", "initStyles", "", "Lcom/amazon/music/views/library/styles/keys/ScreenSizeKey;", "getNeededScreenSizes", "targetScreenSizeKey", "getNeededScreenSizesForTargetScreenSize", "screenSizeKey", "initScreenSizeDependentValues", "initializeStyles", "", "size", "Lcom/amazon/ui/foundations/styles/ImageSize;", "getImageSize", "getImageCornerSize", "Lcom/amazon/music/views/library/styles/keys/ImageSizeKey;", "getImageSizeKey$DigitalMusicAndroid3P_automotiveProdRelease", "(I)Lcom/amazon/music/views/library/styles/keys/ImageSizeKey;", "getImageSizeKey", "imageSizeKey", "", "inImageSizeRange$DigitalMusicAndroid3P_automotiveProdRelease", "(ILcom/amazon/music/views/library/styles/keys/ImageSizeKey;)Z", "inImageSizeRange", "Lcom/amazon/ui/foundations/styles/GridSize;", "getGridSize", "getScreenSizeKey", "inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease", "(ILcom/amazon/music/views/library/styles/keys/ScreenSizeKey;)Z", "inScreenSizeRange", "Landroid/view/WindowManager;", "windowManager", "Landroid/view/WindowManager;", "Landroid/content/res/Resources;", "kotlin.jvm.PlatformType", "resources", "Landroid/content/res/Resources;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DefaultStyleSheet extends StyleSheet {
    private static final String TAG = DefaultStyleSheet.class.getSimpleName();
    private final Resources resources;
    private final WindowManager windowManager;

    /* JADX INFO: compiled from: DefaultStyleSheet.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr[ScreenSizeKey.SMALL.ordinal()] = 2;
            iArr[ScreenSizeKey.MEDIUM.ordinal()] = 3;
            iArr[ScreenSizeKey.LARGE.ordinal()] = 4;
            iArr[ScreenSizeKey.XLARGE.ordinal()] = 5;
            iArr[ScreenSizeKey.XLARGE2.ordinal()] = 6;
            iArr[ScreenSizeKey.XLARGE3.ordinal()] = 7;
            iArr[ScreenSizeKey.XLARGE4.ordinal()] = 8;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultStyleSheet(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("window");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        this.windowManager = (WindowManager) systemService;
        this.resources = context.getResources();
        initializeStyles();
    }

    /* JADX INFO: renamed from: com.amazon.mp3.styles.DefaultStyleSheet$initializeStyles$1, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultStyleSheet.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.styles.DefaultStyleSheet$initializeStyles$1", f = "DefaultStyleSheet.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultStyleSheet.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DefaultStyleSheet.this.initStyles();
            DefaultStyleSheet.this.onInitCompleted();
            return Unit.INSTANCE;
        }
    }

    protected void initializeStyles() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // com.amazon.music.views.library.styles.StyleSheet
    public ImageSize getImageSize(int size) {
        if (size <= 0) {
            return null;
        }
        return new ImageSize(size, Integer.valueOf(getImageCornerSize(size)));
    }

    @Override // com.amazon.music.views.library.styles.StyleSheet
    public int getImageCornerSize(int size) {
        String imageCornerKey;
        Integer cornerSize;
        if (size <= 0 || (imageCornerKey = getImageCornerKey(getImageSizeKey$DigitalMusicAndroid3P_automotiveProdRelease(size))) == null || (cornerSize = getCornerSize(imageCornerKey)) == null) {
            return 0;
        }
        return cornerSize.intValue();
    }

    public final ImageSizeKey getImageSizeKey$DigitalMusicAndroid3P_automotiveProdRelease(int size) {
        ImageSizeKey imageSizeKey = ImageSizeKey.LARGE;
        if (inImageSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, imageSizeKey)) {
            return imageSizeKey;
        }
        ImageSizeKey imageSizeKey2 = ImageSizeKey.MEDIUM;
        if (inImageSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, imageSizeKey2)) {
            return imageSizeKey2;
        }
        ImageSizeKey imageSizeKey3 = ImageSizeKey.SMALL;
        return inImageSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, imageSizeKey3) ? imageSizeKey3 : ImageSizeKey.TINY;
    }

    public final boolean inImageSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(int size, ImageSizeKey imageSizeKey) {
        Intrinsics.checkNotNullParameter(imageSizeKey, "imageSizeKey");
        Range<Integer> imageSizeRange = getImageSizeRange(imageSizeKey);
        if (imageSizeRange == null) {
            return false;
        }
        return imageSizeRange.contains(Integer.valueOf(size));
    }

    @Override // com.amazon.music.views.library.styles.StyleSheet
    public GridSize getGridSize() {
        return getGridSize(getWidthScreenSizeKey());
    }

    @Override // com.amazon.music.views.library.styles.StyleSheet
    public ScreenSizeKey getScreenSizeKey(int size) {
        ScreenSizeKey screenSizeKey = ScreenSizeKey.XLARGE4;
        if (inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, screenSizeKey)) {
            return screenSizeKey;
        }
        ScreenSizeKey screenSizeKey2 = ScreenSizeKey.XLARGE3;
        if (inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, screenSizeKey2)) {
            return screenSizeKey2;
        }
        ScreenSizeKey screenSizeKey3 = ScreenSizeKey.XLARGE2;
        if (inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, screenSizeKey3)) {
            return screenSizeKey3;
        }
        ScreenSizeKey screenSizeKey4 = ScreenSizeKey.XLARGE;
        if (inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, screenSizeKey4)) {
            return screenSizeKey4;
        }
        ScreenSizeKey screenSizeKey5 = ScreenSizeKey.LARGE;
        if (inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, screenSizeKey5)) {
            return screenSizeKey5;
        }
        ScreenSizeKey screenSizeKey6 = ScreenSizeKey.MEDIUM;
        if (inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, screenSizeKey6)) {
            return screenSizeKey6;
        }
        ScreenSizeKey screenSizeKey7 = ScreenSizeKey.SMALL;
        return inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(size, screenSizeKey7) ? screenSizeKey7 : ScreenSizeKey.XSMALL;
    }

    public final boolean inScreenSizeRange$DigitalMusicAndroid3P_automotiveProdRelease(int size, ScreenSizeKey screenSizeKey) {
        Intrinsics.checkNotNullParameter(screenSizeKey, "screenSizeKey");
        Range<Integer> screenSizeRange = getScreenSizeRange(screenSizeKey);
        if (screenSizeRange == null) {
            return false;
        }
        return screenSizeRange.contains(Integer.valueOf(size));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.lang.Integer, kotlin.jvm.internal.DefaultConstructorMarker] */
    /* JADX WARN: Type inference failed for: r4v82 */
    /* JADX WARN: Type inference failed for: r4v9 */
    public final void initStyles() {
        int color;
        int color2;
        IconSizeKey iconSizeKey;
        int i;
        IconSizeKey iconSizeKey2;
        int i2;
        Integer combinationColor;
        Integer num;
        int i3;
        Integer combinationColor2;
        boolean z;
        ?? r4;
        FontSize fontSize;
        FontSize fontSize2;
        FontSize fontSize3;
        Typeface typeface;
        int i4;
        FontStyle fontStyle;
        FontStyle fontStyle2;
        FontStyle fontStyle3;
        FontStyle fontStyle4;
        String str;
        FontStyle fontStyle5;
        FontStyle fontStyle6;
        ButtonSize buttonSize;
        FontSize fontSize4;
        FontSize fontSize5;
        FontSize fontSize6;
        FontSize fontSize7;
        FontSize fontSize8;
        FontSize fontSize9;
        Typeface typeFaceSharpGrotesk20SemiBD;
        FontSize fontSize10;
        Typeface typeface2;
        FontStyle fontStyle7;
        FontStyle fontStyle8;
        FontStyle fontStyle9;
        String str2;
        char c;
        boolean zIsSonicRushEnabled = AmazonApplication.getCapabilities().isSonicRushEnabled();
        boolean zIsCarModeV2Enabled = AmazonApplication.getCapabilities().isCarModeV2Enabled();
        int color3 = this.resources.getColor(R.color.white);
        int color4 = this.resources.getColor(R.color.black);
        int color5 = this.resources.getColor(R.color.playback_container_header);
        if (!zIsSonicRushEnabled) {
            color = this.resources.getColor(R.color.blue_accent);
        } else if (zIsSonicRushEnabled) {
            color = this.resources.getColor(R.color.cyan_accent);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        int i5 = color;
        if (!zIsSonicRushEnabled) {
            color2 = this.resources.getColor(R.color.dark_gray_blue_background);
        } else if (zIsSonicRushEnabled) {
            color2 = this.resources.getColor(R.color.cyan_charcoal);
        } else {
            throw new NoWhenBranchMatchedException();
        }
        int color6 = this.resources.getColor(R.color.tier_hd);
        int color7 = this.resources.getColor(R.color.bauhaus_accent_secondary_glass_2);
        int color8 = this.resources.getColor(R.color.bauhaus_accent_secondary_glass_4);
        ColorKey colorKey = ColorKey.COLOR_PRIMARY;
        addColor(colorKey, color3);
        ColorKey colorKey2 = ColorKey.COLOR_SECONDARY;
        addColor(colorKey2, color4);
        ColorKey colorKey3 = ColorKey.COLOR_ACCENT;
        addColor(colorKey3, i5);
        ColorKey colorKey4 = ColorKey.COLOR_BASE;
        addColor(colorKey4, color2);
        addColor(ColorKey.COLOR_ERROR, this.resources.getColor(R.color.red));
        ColorKey colorKey5 = ColorKey.COLOR_TIER_HD;
        addColor(colorKey5, color6);
        addColor(ColorKey.COLOR_TIER_UNLIMITED, this.resources.getColor(R.color.unlimited_blue));
        addColor(ColorKey.COLOR_TIER_PRIME, this.resources.getColor(R.color.prime_blue));
        addColor(ColorKey.COLOR_PRIMARY_GLASS_2_BASE_FALLBACK, this.resources.getColor(R.color.primary_glass_2_base_fallback));
        if (zIsSonicRushEnabled) {
            addColor(ColorKey.COLOR_BRAND_COACHELLA, R.color.cyan_accent);
        } else {
            addColor(ColorKey.COLOR_BRAND_COACHELLA, R.color.blue_accent);
        }
        addColor(ColorKey.COLOR_BRAND_GRAMMYS, this.resources.getColor(R.color.brand_pink));
        addColor(ColorKey.COLOR_BRAND_MTV, this.resources.getColor(R.color.brand_yellow));
        ColorKey colorKey6 = ColorKey.COLOR_BRAND_OUTSIDE_LANDS;
        addColor(colorKey6, this.resources.getColor(R.color.red));
        if (zIsCarModeV2Enabled) {
            addColor(ColorKey.COLOR_CAR_MODE_EQUALIZER, i5);
        } else {
            addColor(ColorKey.COLOR_CAR_MODE_EQUALIZER, color3);
        }
        TypedValue typedValue = new TypedValue();
        this.resources.getValue(R.dimen.alpha_5_percent, typedValue, true);
        AlphaKey alphaKey = AlphaKey.GLASS_1;
        addAlphaPercent(alphaKey, typedValue.getFloat());
        this.resources.getValue(R.dimen.alpha_15_percent, typedValue, true);
        AlphaKey alphaKey2 = AlphaKey.GLASS_2;
        addAlphaPercent(alphaKey2, typedValue.getFloat());
        int i6 = color2;
        this.resources.getValue(R.dimen.alpha_30_percent, typedValue, true);
        AlphaKey alphaKey3 = AlphaKey.GLASS_3;
        addAlphaPercent(alphaKey3, typedValue.getFloat());
        this.resources.getValue(R.dimen.alpha_60_percent, typedValue, true);
        AlphaKey alphaKey4 = AlphaKey.GLASS_4;
        addAlphaPercent(alphaKey4, typedValue.getFloat());
        this.resources.getValue(R.dimen.alpha_75_percent, typedValue, true);
        AlphaKey alphaKey5 = AlphaKey.GLASS_5;
        addAlphaPercent(alphaKey5, typedValue.getFloat());
        this.resources.getValue(R.dimen.alpha_90_percent, typedValue, true);
        AlphaKey alphaKey6 = AlphaKey.GLASS_6;
        addAlphaPercent(alphaKey6, typedValue.getFloat());
        Float alpha = getAlpha(alphaKey3);
        Float alpha2 = getAlpha(alphaKey4);
        addColor(colorKey, alphaKey);
        addColor(colorKey, alphaKey2);
        addColor(colorKey, alphaKey3);
        addColor(colorKey, alphaKey4);
        addColor(colorKey, alphaKey5);
        addColor(colorKey, alphaKey6);
        Integer color9 = getColor(colorKey, alphaKey);
        Integer color10 = getColor(colorKey, alphaKey2);
        Integer color11 = getColor(colorKey, alphaKey3);
        Integer color12 = getColor(colorKey, alphaKey4);
        addColor(colorKey2, alphaKey);
        addColor(colorKey2, alphaKey2);
        addColor(colorKey2, alphaKey3);
        addColor(colorKey2, alphaKey4);
        addColor(colorKey2, alphaKey5);
        addColor(colorKey2, alphaKey6);
        getColor(colorKey2, alphaKey);
        Integer color13 = getColor(colorKey2, alphaKey2);
        Integer color14 = getColor(colorKey2, alphaKey3);
        Integer color15 = getColor(colorKey2, alphaKey4);
        addColor(colorKey3, alphaKey);
        addColor(colorKey3, alphaKey2);
        addColor(colorKey3, alphaKey3);
        addColor(colorKey3, alphaKey4);
        addColor(colorKey3, alphaKey5);
        addColor(colorKey3, alphaKey6);
        Integer color16 = getColor(colorKey3, alphaKey);
        Integer color17 = getColor(colorKey3, alphaKey2);
        Integer color18 = getColor(colorKey3, alphaKey3);
        getColor(colorKey3, alphaKey4);
        getColor(colorKey3, alphaKey5);
        int color19 = this.resources.getColor(R.color.refresh_accent_glass_3);
        addColor(colorKey4, alphaKey);
        addColor(colorKey4, alphaKey2);
        addColor(colorKey4, alphaKey3);
        addColor(colorKey4, alphaKey4);
        addColor(colorKey4, alphaKey5);
        addColor(colorKey4, alphaKey6);
        Integer color20 = getColor(colorKey4, alphaKey);
        Integer color21 = getColor(colorKey4, alphaKey3);
        Integer color22 = getColor(colorKey4, alphaKey4);
        Integer color23 = getColor(colorKey4, alphaKey5);
        addColor(colorKey5, alphaKey);
        addColor(colorKey5, alphaKey2);
        addColor(colorKey5, alphaKey3);
        addColor(colorKey5, alphaKey4);
        addColor(colorKey5, alphaKey5);
        addColor(colorKey5, alphaKey6);
        Integer color24 = getColor(colorKey5, alphaKey2);
        getColor(colorKey5, alphaKey4);
        Integer combinationColor3 = getCombinationColor(colorKey6, colorKey2, alphaKey2);
        ColorKey colorKey7 = ColorKey.COLOR_LIVE;
        Intrinsics.checkNotNull(combinationColor3);
        addColor(colorKey7, combinationColor3.intValue());
        addSpacerInPixels(SpacerKey.NANO, this.resources.getDimension(R.dimen.spacer_2));
        addSpacerInPixels(SpacerKey.MICRO, this.resources.getDimension(R.dimen.spacer_4));
        addSpacerInPixels(SpacerKey.MINI, this.resources.getDimension(R.dimen.spacer_8));
        addSpacerInPixels(SpacerKey.SMALL, this.resources.getDimension(R.dimen.spacer_12));
        addSpacerInPixels(SpacerKey.BASE, this.resources.getDimension(R.dimen.spacer_16));
        addSpacerInPixels(SpacerKey.MEDIUM, this.resources.getDimension(R.dimen.spacer_20));
        addSpacerInPixels(SpacerKey.LARGE, this.resources.getDimension(R.dimen.spacer_24));
        addSpacerInPixels(SpacerKey.HUGE, this.resources.getDimension(R.dimen.spacer_32));
        addSpacerInPixels(SpacerKey.GIANT, this.resources.getDimension(R.dimen.spacer_36));
        addSpacerInPixels(SpacerKey.EPIC, this.resources.getDimension(R.dimen.spacer_48));
        ButtonSize buttonSize2 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_24), null, this.resources.getDimension(R.dimen.spacer_12), 2, null);
        ButtonSize buttonSize3 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_32), null, this.resources.getDimension(R.dimen.spacer_16), 2, null);
        ButtonSize buttonSize4 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_40), null, this.resources.getDimension(R.dimen.spacer_20), 2, null);
        ButtonSize buttonSize5 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_48), null, this.resources.getDimension(R.dimen.spacer_24), 2, null);
        ButtonSize buttonSize6 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_64), null, this.resources.getDimension(R.dimen.spacer_32), 2, null);
        ButtonSize buttonSize7 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_80), null, this.resources.getDimension(R.dimen.spacer_40), 2, null);
        ButtonSize buttonSize8 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_96), null, this.resources.getDimension(R.dimen.spacer_48), 2, null);
        IconSizeKey iconSizeKey3 = IconSizeKey.MICRO;
        addIconSize(iconSizeKey3, buttonSize2);
        IconSizeKey iconSizeKey4 = IconSizeKey.TINY;
        addIconSize(iconSizeKey4, buttonSize3);
        IconSizeKey iconSizeKey5 = IconSizeKey.SMALL;
        addIconSize(iconSizeKey5, buttonSize4);
        IconSizeKey iconSizeKey6 = IconSizeKey.MEDIUM;
        addIconSize(iconSizeKey6, buttonSize5);
        IconSizeKey iconSizeKey7 = IconSizeKey.LARGE;
        addIconSize(iconSizeKey7, buttonSize6);
        IconSizeKey iconSizeKey8 = IconSizeKey.HUGE;
        addIconSize(iconSizeKey8, buttonSize7);
        IconSizeKey iconSizeKey9 = IconSizeKey.EPIC;
        addIconSize(iconSizeKey9, buttonSize8);
        if (alpha == null) {
            iconSizeKey = iconSizeKey6;
            i = color4;
            combinationColor = null;
            iconSizeKey2 = iconSizeKey3;
            i2 = i5;
        } else {
            iconSizeKey = iconSizeKey6;
            i = color4;
            iconSizeKey2 = iconSizeKey3;
            i2 = i5;
            combinationColor = getCombinationColor(i2, i, alpha.floatValue());
        }
        Integer combinationColor4 = alpha2 == null ? null : getCombinationColor(i2, i, alpha2.floatValue());
        if (alpha2 == null) {
            num = combinationColor3;
            i3 = color3;
            combinationColor2 = null;
        } else {
            num = combinationColor3;
            i3 = color3;
            combinationColor2 = getCombinationColor(i2, i3, alpha2.floatValue());
        }
        Integer num2 = combinationColor;
        Integer num3 = combinationColor2;
        StatefulStyle statefulStyle = new StatefulStyle(Integer.valueOf(i3), num2, num3, color11, null, null, null, null, 240, null);
        IconStyleKey iconStyleKey = IconStyleKey.PRIMARY;
        ColorUtil colorUtil = ColorUtil.INSTANCE;
        addIconStyle(iconStyleKey, new ButtonStyle(statefulStyle, null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i2), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        StatefulStyle statefulStyle2 = new StatefulStyle(Integer.valueOf(i2), num2, num3, combinationColor4, null, null, null, null, 240, null);
        IconStyleKey iconStyleKey2 = IconStyleKey.ACCENT;
        addIconStyle(iconStyleKey2, new ButtonStyle(statefulStyle2, null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i2), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        StatefulStyle statefulStyle3 = new StatefulStyle(Integer.valueOf(i3), Integer.valueOf(i3), Integer.valueOf(i3), color11, color10, color9, color11, color9);
        IconStyleKey iconStyleKey3 = IconStyleKey.GLASS;
        addIconStyle(iconStyleKey3, new ButtonStyle(statefulStyle3, null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i3), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        ButtonStyle buttonStyle = new ButtonStyle(new StatefulStyle(Integer.valueOf(i3), Integer.valueOf(i3), Integer.valueOf(i3), color11, color22, color20, color21, color20), null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i6), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null);
        IconStyleKey iconStyleKey4 = IconStyleKey.BASE_GLASS;
        addIconStyle(iconStyleKey4, buttonStyle);
        Integer num4 = combinationColor2;
        StatefulStyle statefulStyle4 = new StatefulStyle(Integer.valueOf(i), Integer.valueOf(i), null, Integer.valueOf(i), Integer.valueOf(i2), combinationColor, num4, combinationColor4, 4, null);
        IconStyleKey iconStyleKey5 = IconStyleKey.SOLID;
        addIconStyle(iconStyleKey5, new ButtonStyle(statefulStyle4, null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i2), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        StatefulStyle statefulStyle5 = new StatefulStyle(Integer.valueOf(i2), Integer.valueOf(i2), Integer.valueOf(i2), color18, color17, color16, color18, color16);
        IconStyleKey iconStyleKey6 = IconStyleKey.ACCENT_GLASS;
        addIconStyle(iconStyleKey6, new ButtonStyle(statefulStyle5, null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i2), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        addIconStyle(IconStyleKey.ACCENT_NORMAL, new ButtonStyle(new StatefulStyle(Integer.valueOf(i2), Integer.valueOf(i2), Integer.valueOf(i2), color18, color10, color9, color11, color9), null, colorUtil.getColorStateList(0, 0, 0, 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        StatefulStyle statefulStyle6 = new StatefulStyle(Integer.valueOf(i), Integer.valueOf(i), Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i3), color9, color11, color9);
        IconStyleKey iconStyleKey7 = IconStyleKey.SECONDARY_GLASS;
        addIconStyle(iconStyleKey7, new ButtonStyle(statefulStyle6, null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        StatefulStyle statefulStyle7 = new StatefulStyle(Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i), color18, null, combinationColor, num4, null, voOSType.VOOSMP_PID_ANALYTICS_AGENT, null);
        IconStyleKey iconStyleKey8 = IconStyleKey.SONIC_RUSH;
        int i7 = i2;
        IconSizeKey iconSizeKey10 = iconSizeKey2;
        Integer num5 = combinationColor;
        int i8 = i3;
        int i9 = i;
        IconSizeKey iconSizeKey11 = iconSizeKey;
        addIconStyle(iconStyleKey8, new ButtonStyle(statefulStyle7, null, ColorUtil.getColorStateList$default(colorUtil, i2, null, Integer.valueOf(i6), combinationColor, 2, null), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        StatefulStyle statefulStyle8 = new StatefulStyle(Integer.valueOf(i7), null, null, color18, color23, null, null, null, 230, null);
        IconStyleKey iconStyleKey9 = IconStyleKey.SONIC_RUSH_OVERLAY;
        addIconStyle(iconStyleKey9, new ButtonStyle(statefulStyle8, null, ColorUtil.getColorStateList$default(colorUtil, i7, null, Integer.valueOf(i6), num5, 2, null), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null));
        addBaseIconBuilder(iconSizeKey4, iconStyleKey8);
        addBaseIconBuilder(iconSizeKey10, iconStyleKey8);
        addBaseIconBuilder(iconSizeKey4, iconStyleKey9);
        addBaseIconBuilder(iconSizeKey10, iconStyleKey9);
        addBaseIconBuilder(iconSizeKey10, iconStyleKey5);
        addBaseIconBuilder(iconSizeKey4, iconStyleKey);
        addBaseIconBuilder(iconSizeKey4, iconStyleKey2);
        addBaseIconBuilder(iconSizeKey4, iconStyleKey3);
        addBaseIconBuilder(iconSizeKey4, iconStyleKey5);
        addBaseIconBuilder(iconSizeKey4, iconStyleKey6);
        addBaseIconBuilder(iconSizeKey4, iconStyleKey7);
        addBaseIconBuilder(iconSizeKey5, iconStyleKey);
        addBaseIconBuilder(iconSizeKey5, iconStyleKey2);
        addBaseIconBuilder(iconSizeKey5, iconStyleKey3);
        addBaseIconBuilder(iconSizeKey5, iconStyleKey5);
        addBaseIconBuilder(iconSizeKey5, iconStyleKey6);
        addBaseIconBuilder(iconSizeKey5, iconStyleKey7);
        addBaseIconBuilder(iconSizeKey11, iconStyleKey);
        addBaseIconBuilder(iconSizeKey11, iconStyleKey2);
        addBaseIconBuilder(iconSizeKey11, iconStyleKey3);
        addBaseIconBuilder(iconSizeKey11, iconStyleKey5);
        addBaseIconBuilder(iconSizeKey11, iconStyleKey6);
        addBaseIconBuilder(iconSizeKey11, iconStyleKey7);
        addBaseIconBuilder(iconSizeKey11, iconStyleKey4);
        addBaseIconBuilder(iconSizeKey7, iconStyleKey);
        addBaseIconBuilder(iconSizeKey7, iconStyleKey2);
        addBaseIconBuilder(iconSizeKey7, iconStyleKey3);
        addBaseIconBuilder(iconSizeKey7, iconStyleKey5);
        addBaseIconBuilder(iconSizeKey7, iconStyleKey6);
        addBaseIconBuilder(iconSizeKey7, iconStyleKey7);
        addBaseIconBuilder(iconSizeKey8, iconStyleKey);
        addBaseIconBuilder(iconSizeKey8, iconStyleKey2);
        addBaseIconBuilder(iconSizeKey8, iconStyleKey3);
        addBaseIconBuilder(iconSizeKey8, iconStyleKey5);
        addBaseIconBuilder(iconSizeKey8, iconStyleKey6);
        addBaseIconBuilder(iconSizeKey8, iconStyleKey7);
        addBaseIconBuilder(iconSizeKey9, iconStyleKey);
        addBaseIconBuilder(iconSizeKey9, iconStyleKey2);
        addBaseIconBuilder(iconSizeKey9, iconStyleKey3);
        addBaseIconBuilder(iconSizeKey9, iconStyleKey5);
        addBaseIconBuilder(iconSizeKey9, iconStyleKey6);
        addBaseIconBuilder(iconSizeKey9, iconStyleKey7);
        if (zIsSonicRushEnabled) {
            z = zIsSonicRushEnabled;
            r4 = 0;
            if (z) {
                fontSize = new FontSize((int) this.resources.getDimension(R.dimen.text_size_38), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_48)));
            } else {
                throw new NoWhenBranchMatchedException();
            }
        } else {
            r4 = 0;
            fontSize = new FontSize((int) this.resources.getDimension(R.dimen.text_size_50), null, 2, null);
            z = zIsSonicRushEnabled;
        }
        if (!z) {
            fontSize2 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_50), r4, 2, r4);
        } else if (z) {
            fontSize2 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_38), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_48)));
        } else {
            throw new NoWhenBranchMatchedException();
        }
        if (!z) {
            fontSize3 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_40), r4, 2, r4);
        } else if (z) {
            fontSize3 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_32), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_40)));
        } else {
            throw new NoWhenBranchMatchedException();
        }
        FontSize fontSize11 = fontSize3;
        FontSize fontSize12 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_28), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_32)));
        FontSize fontSize13 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_18), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_24)));
        FontSize fontSize14 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_15), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_20)));
        FontSize fontSize15 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_13), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_16)));
        FontSize fontSize16 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_12), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_16)));
        FontSize fontSize17 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_11), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_12)));
        FontSize fontSize18 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_24), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_26)));
        FontSize fontSize19 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_18), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_28)));
        FontSize fontSize20 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_22), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_32)));
        FontSize fontSize21 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_20), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_24)));
        FontSize fontSize22 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_18), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_24)));
        FontSize fontSize23 = new FontSize((int) this.resources.getDimension(R.dimen.text_size_14), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_18)));
        addFontSize(FontSizeKey.HEADLINE_1, fontSize);
        addFontSize(FontSizeKey.HEADLINE_2, fontSize11);
        addFontSize(FontSizeKey.HEADLINE_3, fontSize12);
        addFontSize(FontSizeKey.HEADLINE_4, fontSize13);
        addFontSize(FontSizeKey.BODY_1, fontSize14);
        addFontSize(FontSizeKey.BODY_2, fontSize15);
        addFontSize(FontSizeKey.BODY_3, fontSize16);
        addFontSize(FontSizeKey.BODY_4, fontSize17);
        Typeface typeFaceSharpGrotesk20BD = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_sharp_grotesk_bold));
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_sharp_grotesk_semi_bold));
        Typeface typeFaceSharpGrotesk16BD = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_sharp_grotesk_bold_16));
        Typeface typefaceEmberDisplayCD = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_display_condensed));
        Typeface typefaceEmberBD = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_bold));
        Typeface typefaceCreateFromAsset2 = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_regular));
        Typeface typefaceEmberRGItalic = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_amazon_ember_regular_italic));
        Typeface typeFaceSharpGroteskItalic = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_sharp_grotesk_italic));
        Typeface typefaceCreateFromAsset3 = Typeface.createFromAsset(this.resources.getAssets(), this.resources.getString(R.string.font_sharp_grotesk_medium));
        if (z) {
            typeface = typefaceCreateFromAsset3;
            i4 = i8;
            if (!z) {
                throw new NoWhenBranchMatchedException();
            }
            Intrinsics.checkNotNullExpressionValue(typeFaceSharpGrotesk20BD, "typeFaceSharpGrotesk20BD");
            fontStyle = new FontStyle(fontSize, typeFaceSharpGrotesk20BD, i4, Boolean.FALSE);
        } else {
            Intrinsics.checkNotNull(typefaceEmberDisplayCD);
            typeface = typefaceCreateFromAsset3;
            i4 = i8;
            fontStyle = new FontStyle(fontSize, typefaceEmberDisplayCD, i4, Boolean.TRUE);
        }
        FontStyle fontStyle10 = fontStyle;
        if (!z) {
            Intrinsics.checkNotNullExpressionValue(typefaceEmberDisplayCD, "typefaceEmberDisplayCD");
            fontStyle2 = new FontStyle(fontSize, typefaceEmberDisplayCD, i4, Boolean.TRUE);
        } else {
            if (!z) {
                throw new NoWhenBranchMatchedException();
            }
            Intrinsics.checkNotNullExpressionValue(typeFaceSharpGrotesk16BD, "typeFaceSharpGrotesk16BD");
            fontStyle2 = new FontStyle(fontSize2, typeFaceSharpGrotesk16BD, i4, Boolean.FALSE);
        }
        if (!z) {
            Intrinsics.checkNotNullExpressionValue(typefaceEmberDisplayCD, "typefaceEmberDisplayCD");
            fontStyle3 = new FontStyle(fontSize11, typefaceEmberDisplayCD, i4, Boolean.TRUE);
        } else {
            if (!z) {
                throw new NoWhenBranchMatchedException();
            }
            Intrinsics.checkNotNullExpressionValue(typeFaceSharpGrotesk20BD, "typeFaceSharpGrotesk20BD");
            fontStyle3 = new FontStyle(fontSize11, typeFaceSharpGrotesk20BD, i4, Boolean.FALSE);
        }
        FontStyle fontStyle11 = fontStyle3;
        if (z) {
            fontStyle4 = fontStyle2;
            str = "typefaceEmberBD";
            fontStyle5 = fontStyle11;
            fontStyle6 = fontStyle10;
            buttonSize = buttonSize3;
            fontSize4 = fontSize20;
            fontSize5 = fontSize18;
            fontSize6 = fontSize21;
            fontSize7 = fontSize23;
            fontSize8 = fontSize15;
            fontSize9 = fontSize14;
            typeFaceSharpGrotesk20SemiBD = typefaceCreateFromAsset;
            fontSize10 = fontSize11;
            typeface2 = typefaceEmberBD;
            if (!z) {
                throw new NoWhenBranchMatchedException();
            }
            Intrinsics.checkNotNullExpressionValue(typeFaceSharpGrotesk20SemiBD, "typeFaceSharpGrotesk20SemiBD");
            fontStyle7 = new FontStyle(fontSize12, typeFaceSharpGrotesk20SemiBD, i4, null, 8, null);
        } else {
            Intrinsics.checkNotNullExpressionValue(typefaceEmberBD, "typefaceEmberBD");
            buttonSize = buttonSize3;
            fontSize6 = fontSize21;
            str = "typefaceEmberBD";
            fontSize5 = fontSize18;
            fontSize7 = fontSize23;
            fontStyle5 = fontStyle11;
            fontSize8 = fontSize15;
            fontStyle6 = fontStyle10;
            fontSize9 = fontSize14;
            fontStyle4 = fontStyle2;
            fontSize4 = fontSize20;
            typeFaceSharpGrotesk20SemiBD = typefaceCreateFromAsset;
            fontSize10 = fontSize11;
            typeface2 = typefaceEmberBD;
            fontStyle7 = new FontStyle(fontSize12, typefaceEmberBD, i4, null, 8, null);
        }
        FontStyle fontStyle12 = fontStyle7;
        if (z) {
            fontStyle8 = fontStyle12;
            String str3 = str;
            if (!z) {
                throw new NoWhenBranchMatchedException();
            }
            Intrinsics.checkNotNullExpressionValue(typeFaceSharpGrotesk20SemiBD, "typeFaceSharpGrotesk20SemiBD");
            str2 = str3;
            fontStyle9 = new FontStyle(fontSize13, typeFaceSharpGrotesk20SemiBD, i4, null, 8, null);
        } else {
            String str4 = str;
            Intrinsics.checkNotNullExpressionValue(typeface2, str4);
            str2 = str4;
            fontStyle8 = fontStyle12;
            fontStyle9 = new FontStyle(fontSize13, typeface2, i4, null, 8, null);
        }
        Intrinsics.checkNotNull(typefaceCreateFromAsset2);
        FontSize fontSize24 = fontSize9;
        int i10 = i4;
        FontStyle fontStyle13 = new FontStyle(fontSize24, typefaceCreateFromAsset2, i10, null, 8, null);
        Intrinsics.checkNotNullExpressionValue(typeface2, str2);
        FontStyle fontStyle14 = new FontStyle(fontSize24, typeface2, i10, null, 8, null);
        Intrinsics.checkNotNull(color12);
        FontStyle fontStyle15 = new FontStyle(fontSize9, typefaceCreateFromAsset2, color12.intValue(), null, 8, null);
        FontStyle fontStyle16 = new FontStyle(fontSize8, typefaceCreateFromAsset2, i10, null, 8, null);
        FontStyle fontStyle17 = new FontStyle(fontSize8, typefaceCreateFromAsset2, color12.intValue(), null, 8, null);
        FontStyle fontStyle18 = new FontStyle(fontSize16, typefaceCreateFromAsset2, color12.intValue(), null, 8, null);
        FontStyle fontStyle19 = new FontStyle(fontSize8, typeface2, color12.intValue(), null, 8, null);
        Boolean bool = Boolean.TRUE;
        FontStyle fontStyle20 = new FontStyle(fontSize16, typeface2, i7, bool);
        FontStyle fontStyle21 = new FontStyle(fontSize16, typeface2, i4, null, 8, null);
        FontStyle fontStyle22 = new FontStyle(fontSize16, typeface2, i9, null, 8, null);
        FontStyle fontStyle23 = new FontStyle(fontSize17, typeface2, color19, bool);
        FontStyle fontStyle24 = new FontStyle(fontSize17, typeface2, i7, bool);
        FontStyle fontStyle25 = new FontStyle(fontSize17, typeface2, color12.intValue(), bool);
        FontStyle fontStyle26 = new FontStyle(fontSize16, typefaceCreateFromAsset2, color12.intValue(), bool);
        FontStyle fontStyle27 = new FontStyle(fontSize17, typeface2, i4, bool);
        FontStyle fontStyle28 = new FontStyle(fontSize17, typeface2, i9, bool);
        FontStyle fontStyle29 = new FontStyle(fontSize8, typeface2, i4, bool);
        FontStyle fontStyle30 = new FontStyle(fontSize4, typeface2, i4, null, 8, null);
        FontStyle fontStyle31 = new FontStyle(fontSize6, typeface2, i9, null, 8, null);
        FontStyle fontStyle32 = new FontStyle(fontSize22, typefaceCreateFromAsset2, i9, null, 8, null);
        Intrinsics.checkNotNullExpressionValue(typeFaceSharpGroteskItalic, "typeFaceSharpGroteskItalic");
        Boolean bool2 = Boolean.FALSE;
        FontStyle fontStyle33 = new FontStyle(fontSize12, typeFaceSharpGroteskItalic, i4, bool2);
        FontSize fontSize25 = fontSize7;
        FontStyle fontStyle34 = new FontStyle(fontSize25, typefaceCreateFromAsset2, i4, bool2);
        Intrinsics.checkNotNullExpressionValue(typeFaceSharpGrotesk20SemiBD, "typeFaceSharpGrotesk20SemiBD");
        FontStyle fontStyle35 = new FontStyle(fontSize12, typeFaceSharpGrotesk20SemiBD, i4, bool2);
        Typeface typeface3 = typeFaceSharpGrotesk20SemiBD;
        int i11 = i4;
        FontStyle fontStyle36 = new FontStyle(fontSize4, typeface3, i11, null, 8, null);
        FontStyle fontStyle37 = new FontStyle(fontSize22, typeface3, i11, null, 8, null);
        FontSize fontSize26 = fontSize6;
        FontStyle fontStyle38 = new FontStyle(fontSize26, typeface3, i11, null, 8, null);
        FontStyle fontStyle39 = new FontStyle(fontSize26, typefaceCreateFromAsset2, i11, null, 8, null);
        FontStyle fontStyle40 = new FontStyle(fontSize25, typefaceCreateFromAsset2, color12.intValue(), null, 8, null);
        FontStyle fontStyle41 = new FontStyle(fontSize22, typefaceCreateFromAsset2, color12.intValue(), null, 8, null);
        FontStyle fontStyle42 = new FontStyle(fontSize5, typeFaceSharpGrotesk20SemiBD, i11, null, 8, null);
        FontStyle fontStyle43 = new FontStyle(fontSize12, typeface2, i7, bool);
        FontStyle fontStyle44 = new FontStyle(fontSize12, typeface2, i4, bool);
        Typeface typeface4 = typeFaceSharpGrotesk20SemiBD;
        FontStyle fontStyle45 = new FontStyle(fontSize10, typeFaceSharpGrotesk20SemiBD, i6, null, 8, null);
        FontStyle fontStyle46 = new FontStyle(fontSize22, typefaceCreateFromAsset2, i6, null, 8, null);
        Typeface typeface5 = typeface2;
        FontStyle fontStyle47 = new FontStyle(fontSize22, typeface2, i6, null, 8, null);
        Typeface typeFaceSharpGroteskMedium = typeface;
        Intrinsics.checkNotNullExpressionValue(typeFaceSharpGroteskMedium, "typeFaceSharpGroteskMedium");
        FontStyle fontStyle48 = new FontStyle(fontSize12, typeFaceSharpGroteskMedium, i4, null, 8, null);
        Intrinsics.checkNotNullExpressionValue(typefaceEmberRGItalic, "typefaceEmberRGItalic");
        int i12 = i4;
        FontStyle fontStyle49 = new FontStyle(fontSize19, typefaceEmberRGItalic, i6, null, 8, null);
        FontStyle fontStyle50 = new FontStyle(fontSize4, typefaceCreateFromAsset2, color12.intValue(), null, 8, null);
        addFontStyle(FontStyleKey.HEADLINE_1, fontStyle6);
        addFontStyle(FontStyleKey.HEADLINE_1_NARROW, fontStyle4);
        addFontStyle(FontStyleKey.HEADLINE_2, fontStyle5);
        addFontStyle(FontStyleKey.HEADLINE_3, fontStyle8);
        addFontStyle(FontStyleKey.HEADLINE_4, fontStyle9);
        addFontStyle(FontStyleKey.PRIMARY, fontStyle13);
        addFontStyle(FontStyleKey.PRIMARY_BOLD, fontStyle14);
        addFontStyle(FontStyleKey.PRIMARY_GLASS, fontStyle15);
        addFontStyle(FontStyleKey.PRIMARY_2, fontStyle16);
        addFontStyle(FontStyleKey.SECONDARY, fontStyle17);
        addFontStyle(FontStyleKey.SECONDARY_GLASS_BOLD, fontStyle19);
        addFontStyle(FontStyleKey.TERTIARY, fontStyle18);
        addFontStyle(FontStyleKey.ACCENT_TERTIARY, fontStyle20);
        addFontStyle(FontStyleKey.TERTIARY_BOLD, fontStyle21);
        addFontStyle(FontStyleKey.SECONDARY_TERTIARY, fontStyle22);
        addFontStyle(FontStyleKey.LABEL, fontStyle24);
        addFontStyle(FontStyleKey.INDEX, fontStyle25);
        addFontStyle(FontStyleKey.DP_TERTIARY, fontStyle26);
        addFontStyle(FontStyleKey.INDEX_PRIMARY, fontStyle27);
        addFontStyle(FontStyleKey.INDEX_SECONDARY, fontStyle28);
        addFontStyle(FontStyleKey.TABS, fontStyle29);
        addFontStyle(FontStyleKey.PRIMARY_HUGE, fontStyle30);
        addFontStyle(FontStyleKey.SECONDARY_HUGE, fontStyle31);
        addFontStyle(FontStyleKey.SECONDARY_BIG, fontStyle32);
        addFontStyle(FontStyleKey.LYRICS_XRAY, fontStyle33);
        addFontStyle(FontStyleKey.DYNAMIC_MESSAGING_MODAL_BODY, fontStyle34);
        addFontStyle(FontStyleKey.DYNAMIC_MESSAGING_MODAL_HEADLINE, fontStyle35);
        addFontStyle(FontStyleKey.PRIMARY_T1_HUGE_SHARPGROTESK, fontStyle36);
        addFontStyle(FontStyleKey.PRIMARY_T2_BIG_SHARPGROTESK, fontStyle37);
        addFontStyle(FontStyleKey.PRIMARY_T1_BIG_SHARPGROTESK, fontStyle38);
        addFontStyle(FontStyleKey.PRIMARY_T1_BIG_EMBER_RG, fontStyle39);
        addFontStyle(FontStyleKey.PRIMARY_GLASS_T3_BIG_EMBER_RG, fontStyle40);
        addFontStyle(FontStyleKey.PRIMARY_GLASS_T2_BIG_EMBER_RG, fontStyle41);
        addFontStyle(FontStyleKey.ACCENT_HEADLINE_3_EMBER_BD, fontStyle43);
        addFontStyle(FontStyleKey.PRIMARY_HEADLINE_3_EMBER_BD, fontStyle44);
        addFontStyle(FontStyleKey.PRIMARY_HEADLINE_4_BIG_SHARPGROTESK, fontStyle42);
        addFontStyle(FontStyleKey.PRIMARY_HEADLINE_3_MEDIUM_SHARPGROTESK, fontStyle48);
        addFontStyle(FontStyleKey.BASE_HEADLINE_2, fontStyle45);
        addFontStyle(FontStyleKey.BASE_HEADLINE_4_TALL_EMBER_RG_IT, fontStyle49);
        addFontStyle(FontStyleKey.BASE_T2_BIG_EMBER_RG, fontStyle46);
        addFontStyle(FontStyleKey.BASE_T2_BIG_EMBER_BD, fontStyle47);
        addFontStyle(FontStyleKey.PRIMARY_GLASS_T1_HUGE_EMBER_RG, fontStyle50);
        addFontStyle(FontStyleKey.DISABLED_LABEL, fontStyle23);
        ButtonSize buttonSize9 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_28), null, this.resources.getDimension(R.dimen.spacer_16), this.resources.getDimension(R.dimen.text_size_12), typeface5, bool, null, null, null, 450, null);
        ButtonSize buttonSize10 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_28), null, this.resources.getDimension(R.dimen.spacer_16), this.resources.getDimension(R.dimen.text_size_12), typeface5, bool2, null, null, null, 450, null);
        ButtonSize buttonSize11 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_36), null, this.resources.getDimension(R.dimen.spacer_16), this.resources.getDimension(R.dimen.text_size_13), typefaceCreateFromAsset2, null, null, null, null, 482, null);
        ButtonSize buttonSize12 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_36), null, this.resources.getDimension(R.dimen.spacer_16), this.resources.getDimension(R.dimen.text_size_13), typefaceCreateFromAsset2, null, Float.valueOf(this.resources.getDimension(R.dimen.spacer_16)), Float.valueOf(this.resources.getDimension(R.dimen.spacer_12)), Float.valueOf(this.resources.getDimension(R.dimen.spacer_8)), 34, null);
        ButtonSize buttonSize13 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_48), null, this.resources.getDimension(R.dimen.spacer_24), this.resources.getDimension(R.dimen.text_size_15), typefaceCreateFromAsset2, null, null, null, null, 482, null);
        ButtonSize buttonSize14 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_48), null, this.resources.getDimension(R.dimen.spacer_24), this.resources.getDimension(R.dimen.text_size_15), typefaceCreateFromAsset2, null, Float.valueOf(this.resources.getDimension(R.dimen.spacer_24)), Float.valueOf(this.resources.getDimension(R.dimen.spacer_16)), Float.valueOf(this.resources.getDimension(R.dimen.spacer_12)), 34, null);
        ButtonSize buttonSize15 = new ButtonSize(this.resources.getDimension(R.dimen.spacing_60dp), null, this.resources.getDimension(R.dimen.spacer_24), this.resources.getDimension(R.dimen.text_size_18), typeface4, null, null, null, null, 482, null);
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.SMALL;
        addButtonSize(buttonSizeKey, buttonSize9);
        ButtonSizeKey buttonSizeKey2 = ButtonSizeKey.SMALL_NOT_ALL_CAPS;
        addButtonSize(buttonSizeKey2, buttonSize10);
        ButtonSizeKey buttonSizeKey3 = ButtonSizeKey.MEDIUM;
        addButtonSize(buttonSizeKey3, buttonSize11);
        ButtonSizeKey buttonSizeKey4 = ButtonSizeKey.MEDIUM_WITH_ICON;
        addButtonSize(buttonSizeKey4, buttonSize12);
        ButtonSizeKey buttonSizeKey5 = ButtonSizeKey.LARGE;
        addButtonSize(buttonSizeKey5, buttonSize13);
        ButtonSizeKey buttonSizeKey6 = ButtonSizeKey.LARGE_WITH_ICON;
        addButtonSize(buttonSizeKey6, buttonSize14);
        ButtonSizeKey buttonSizeKey7 = ButtonSizeKey.EPIC;
        addButtonSize(buttonSizeKey7, buttonSize15);
        StatefulStyle statefulStyle9 = new StatefulStyle(Integer.valueOf(i7), Integer.valueOf(i9), Integer.valueOf(i9), color18, 0, num5, combinationColor2, 0);
        StatefulStyle statefulStyle10 = new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), Integer.valueOf(i12), Integer.valueOf(i12), 0, color10, color10, 0);
        StatefulStyle statefulStyle11 = new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), Integer.valueOf(i12), color12, 0, color10, color10, 0);
        ButtonStyle buttonStyle2 = new ButtonStyle(statefulStyle3, null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i12), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null);
        ButtonStyle buttonStyle3 = new ButtonStyle(statefulStyle4, null, colorUtil.getColorStateList(0, 0, Integer.valueOf(i7), 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null);
        ButtonStyle buttonStyle4 = new ButtonStyle(statefulStyle9, null, colorUtil.getColorStateList(i7, 0, Integer.valueOf(i7), num5), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null);
        ButtonStyle buttonStyle5 = new ButtonStyle(statefulStyle10, null, colorUtil.getColorStateList(i12, 0, Integer.valueOf(i12), color10), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null);
        Intrinsics.checkNotNull(color11);
        ButtonStyle buttonStyle6 = new ButtonStyle(statefulStyle10, null, colorUtil.getColorStateList(color11.intValue(), 0, Integer.valueOf(i12), color10), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null);
        ButtonStyle buttonStyle7 = new ButtonStyle(statefulStyle11, null, colorUtil.getColorStateList(0, 0, 0, 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null);
        ButtonStyle buttonStyle8 = new ButtonStyle(statefulStyle9, null, colorUtil.getColorStateList(0, 0, 0, 0), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), 2, null);
        ButtonStyleKey buttonStyleKey = ButtonStyleKey.GLASS;
        addButtonStyle(buttonStyleKey, buttonStyle2);
        ButtonStyleKey buttonStyleKey2 = ButtonStyleKey.SOLID;
        addButtonStyle(buttonStyleKey2, buttonStyle3);
        ButtonStyleKey buttonStyleKey3 = ButtonStyleKey.OUTLINE;
        addButtonStyle(buttonStyleKey3, buttonStyle4);
        ButtonStyleKey buttonStyleKey4 = ButtonStyleKey.FOLLOW_OUTLINE;
        addButtonStyle(buttonStyleKey4, buttonStyle5);
        ButtonStyleKey buttonStyleKey5 = ButtonStyleKey.GLASS_OUTLINE;
        addButtonStyle(buttonStyleKey5, buttonStyle6);
        ButtonStyleKey buttonStyleKey6 = ButtonStyleKey.BORDERLESS;
        addButtonStyle(buttonStyleKey6, buttonStyle7);
        ButtonStyleKey buttonStyleKey7 = ButtonStyleKey.BORDERLESS_OUTLINE;
        addButtonStyle(buttonStyleKey7, buttonStyle8);
        ButtonStyleKey buttonStyleKey8 = ButtonStyleKey.BASE_GLASS;
        addButtonStyle(buttonStyleKey8, buttonStyle);
        addBaseButtonBuilder(buttonSizeKey, buttonStyleKey);
        addBaseButtonBuilder(buttonSizeKey, buttonStyleKey5);
        addBaseButtonBuilder(buttonSizeKey, buttonStyleKey2);
        addBaseButtonBuilder(buttonSizeKey, buttonStyleKey3);
        addBaseButtonBuilder(buttonSizeKey, buttonStyleKey4);
        addBaseButtonBuilder(buttonSizeKey, buttonStyleKey6);
        addBaseButtonBuilder(buttonSizeKey, buttonStyleKey7);
        addBaseButtonBuilder(buttonSizeKey2, buttonStyleKey);
        addBaseButtonBuilder(buttonSizeKey3, buttonStyleKey);
        addBaseButtonBuilder(buttonSizeKey3, buttonStyleKey2);
        addBaseButtonBuilder(buttonSizeKey3, buttonStyleKey3);
        addBaseButtonBuilder(buttonSizeKey3, buttonStyleKey5);
        addBaseButtonBuilder(buttonSizeKey3, buttonStyleKey6);
        addBaseButtonBuilder(buttonSizeKey3, buttonStyleKey7);
        addBaseButtonBuilder(buttonSizeKey4, buttonStyleKey);
        addBaseButtonBuilder(buttonSizeKey4, buttonStyleKey2);
        addBaseButtonBuilder(buttonSizeKey4, buttonStyleKey3);
        addBaseButtonBuilder(buttonSizeKey4, buttonStyleKey6);
        addBaseButtonBuilder(buttonSizeKey4, buttonStyleKey5);
        addBaseButtonBuilder(buttonSizeKey4, buttonStyleKey7);
        addBaseButtonBuilder(buttonSizeKey5, buttonStyleKey);
        addBaseButtonBuilder(buttonSizeKey5, buttonStyleKey2);
        addBaseButtonBuilder(buttonSizeKey5, buttonStyleKey3);
        addBaseButtonBuilder(buttonSizeKey5, buttonStyleKey6);
        addBaseButtonBuilder(buttonSizeKey6, buttonStyleKey);
        addBaseButtonBuilder(buttonSizeKey6, buttonStyleKey2);
        addBaseButtonBuilder(buttonSizeKey6, buttonStyleKey3);
        addBaseButtonBuilder(buttonSizeKey6, buttonStyleKey6);
        addBaseButtonBuilder(buttonSizeKey6, buttonStyleKey7);
        addBaseButtonBuilder(buttonSizeKey7, buttonStyleKey2);
        addBaseButtonBuilder(buttonSizeKey7, buttonStyleKey8);
        addCornerSize(CornerSizeKey.TINY, this.resources.getDimension(R.dimen.spacer_2));
        CornerSizeKey cornerSizeKey = CornerSizeKey.SMALL;
        addCornerSize(cornerSizeKey, this.resources.getDimension(R.dimen.spacer_4));
        CornerSizeKey cornerSizeKey2 = CornerSizeKey.MEDIUM;
        addCornerSize(cornerSizeKey2, this.resources.getDimension(R.dimen.spacer_8));
        CornerSizeKey cornerSizeKey3 = CornerSizeKey.LARGE;
        addCornerSize(cornerSizeKey3, this.resources.getDimension(R.dimen.spacer_16));
        addCornerSize(CornerSizeKey.ROUND, this.resources.getDimension(R.dimen.spacing_50));
        int dimension = (int) this.resources.getDimension(R.dimen.size_75);
        int dimension2 = (int) this.resources.getDimension(R.dimen.size_100);
        int dimension3 = (int) this.resources.getDimension(R.dimen.size_175);
        ImageSizeKey imageSizeKey = ImageSizeKey.TINY;
        addImageSizeRange(imageSizeKey, new Range<>(0, Integer.valueOf(dimension - 1)));
        ImageSizeKey imageSizeKey2 = ImageSizeKey.SMALL;
        addImageSizeRange(imageSizeKey2, new Range<>(Integer.valueOf(dimension), Integer.valueOf(dimension2 - 1)));
        ImageSizeKey imageSizeKey3 = ImageSizeKey.MEDIUM;
        addImageSizeRange(imageSizeKey3, new Range<>(Integer.valueOf(dimension2), Integer.valueOf(dimension3 - 1)));
        ImageSizeKey imageSizeKey4 = ImageSizeKey.LARGE;
        addImageSizeRange(imageSizeKey4, new Range<>(Integer.valueOf(dimension3), Integer.MAX_VALUE));
        addImageCornerKey(imageSizeKey, cornerSizeKey);
        addImageCornerKey(imageSizeKey2, cornerSizeKey2);
        addImageCornerKey(imageSizeKey3, cornerSizeKey2);
        addImageCornerKey(imageSizeKey4, cornerSizeKey3);
        addImageStyle(ImageStyleKey.DEFAULT, new ImageStyle(Integer.valueOf(i9), color10 == null ? null : new OutlineStyle(color10.intValue(), this.resources.getDimension(R.dimen.spacer_2)), null, 4, null));
        Intrinsics.checkNotNull(color14);
        GradientStyle gradientStyle = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{color14.intValue(), i9}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 1.0f}), false);
        GradientStyle gradientStyle2 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{i9, i6}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 1.0f}), false);
        GradientStyle gradientStyle3 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{i6, i9}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 1.0f}), false);
        GradientStyle gradientStyle4 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{i12, i12, 0}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 0.9f, 1.0f}), true);
        GradientStyle gradientStyle5 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{0, color5}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 1.0f}), true);
        if (alpha == null || alpha2 == null) {
            c = 1;
        } else {
            float fFloatValue = (alpha.floatValue() + 1.0f) / 2;
            ColorParser colorParser = ColorParser.INSTANCE;
            c = 1;
            addGradientStyle(GradientStyleKey.FEATURE_BARKER, new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{colorParser.parseColor(colorParser.parseCombinationColor(i9, alpha.floatValue(), i9, alpha2.floatValue()), 0.0f), colorParser.parseColor(colorParser.parseCombinationColor(i9, fFloatValue, i9, alpha2.floatValue()), 0.9f), colorParser.parseColor(colorParser.parseCombinationColor(i9, 1.0f, i9, alpha2.floatValue()), 1.0f)}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 0.5f, 1.0f}), true));
        }
        int[] iArr = new int[2];
        iArr[0] = i12;
        iArr[c] = 0;
        GradientStyle gradientStyle6 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(iArr), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 1.0f}), false);
        int[] iArr2 = new int[3];
        iArr2[0] = i6;
        iArr2[c] = color8;
        iArr2[2] = 0;
        GradientStyle gradientStyle7 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(iArr2), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 0.5f, 1.0f}), false);
        int[] iArr3 = new int[3];
        iArr3[0] = i6;
        iArr3[c] = color7;
        iArr3[2] = 0;
        GradientStyle gradientStyle8 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(iArr3), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 0.15f, 1.0f}), false);
        GradientStyle gradientStyle9 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{i6, color7, 0}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 0.5f, 1.0f}), false);
        GradientStyle gradientStyle10 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{i6, color7, 0}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 0.0f, 1.0f}), false);
        Intrinsics.checkNotNull(color15);
        GradientStyle gradientStyle11 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{i9, color15.intValue(), color15.intValue()}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 0.65f, 1.0f}), false);
        GradientStyle gradientStyle12 = new GradientStyle(ArraysKt___ArraysJvmKt.asList(new int[]{i9, color8, 0}), ArraysKt___ArraysJvmKt.asList(new float[]{0.0f, 0.5f, 1.0f}), false);
        addGradientStyle(GradientStyleKey.GRADIENT_1, gradientStyle);
        addGradientStyle(GradientStyleKey.GRADIENT_2, gradientStyle2);
        addGradientStyle(GradientStyleKey.GRADIENT_3, gradientStyle3);
        addGradientStyle(GradientStyleKey.TEXT_TRUNCATION, gradientStyle4);
        addGradientStyle(GradientStyleKey.TRANSPARENT_GRADIENT, gradientStyle6);
        addGradientStyle(GradientStyleKey.FEATURED_PLAY_BACKGROUND, gradientStyle7);
        addGradientStyle(GradientStyleKey.FEATURED_PLAY_VIDEO_VERTICAL, gradientStyle10);
        addGradientStyle(GradientStyleKey.FEATURED_PLAY_HERO_TOP, gradientStyle8);
        addGradientStyle(GradientStyleKey.FEATURED_PLAY_HERO_BOTTOM, gradientStyle9);
        addGradientStyle(GradientStyleKey.FEATURED_PLAY_HERO_BACKGROUND_TOP, gradientStyle11);
        addGradientStyle(GradientStyleKey.FEATURED_PLAY_HERO_BACKGROUND_BOTTOM, gradientStyle12);
        addGradientStyle(GradientStyleKey.PLAYBACK_CONTAINER_GRADIENT, gradientStyle5);
        this.resources.getValue(R.dimen.blur_1, typedValue, true);
        addBlurStyle(BlurStyleKey.BLUR_1, typedValue.getFloat());
        this.resources.getValue(R.dimen.blur_2, typedValue, true);
        addBlurStyle(BlurStyleKey.BLUR_2, typedValue.getFloat());
        ButtonSize buttonSize16 = new ButtonSize(null, null, (int) this.resources.getDimension(R.dimen.spacer_2), Integer.valueOf(-((int) this.resources.getDimension(R.dimen.spacer_1))), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_12)), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), Integer.valueOf(fontStyle25.getFontSize().getTextSize()), fontStyle25.getTypeface(), fontStyle25.getAllCaps(), null, null, null, 3587, null);
        ButtonSize buttonSize17 = new ButtonSize(null, null, (int) this.resources.getDimension(R.dimen.spacer_4), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_1)), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_16)), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), Integer.valueOf(fontStyle25.getFontSize().getTextSize()), fontStyle25.getTypeface(), fontStyle25.getAllCaps(), null, null, null, 3587, null);
        ButtonSize buttonSize18 = new ButtonSize(null, null, (int) this.resources.getDimension(R.dimen.spacer_2), Integer.valueOf(-((int) this.resources.getDimension(R.dimen.spacer_1))), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_12)), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), Integer.valueOf(fontStyle25.getFontSize().getTextSize()), fontStyle25.getTypeface(), fontStyle25.getAllCaps(), buttonSize2.getIconSize(), null, null, 3075, null);
        ButtonSize buttonSize19 = new ButtonSize(null, null, (int) this.resources.getDimension(R.dimen.spacer_4), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_1)), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_16)), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_2)), Integer.valueOf(fontStyle25.getFontSize().getTextSize()), fontStyle25.getTypeface(), fontStyle25.getAllCaps(), buttonSize2.getIconSize(), null, null, 3075, null);
        TagSizeKey tagSizeKey = TagSizeKey.SMALL;
        addTagSize(tagSizeKey, buttonSize16);
        TagSizeKey tagSizeKey2 = TagSizeKey.MEDIUM;
        addTagSize(tagSizeKey2, buttonSize17);
        TagSizeKey tagSizeKey3 = TagSizeKey.SMALL_WITH_ICON;
        addTagSize(tagSizeKey3, buttonSize18);
        TagSizeKey tagSizeKey4 = TagSizeKey.MEDIUM_WITH_ICON;
        addTagSize(tagSizeKey4, buttonSize19);
        ButtonStyle buttonStyle9 = new ButtonStyle(new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), null, Integer.valueOf(i12), color10, color10, null, color10, 68, null), null, null, null, 14, null);
        ButtonStyle buttonStyle10 = new ButtonStyle(new StatefulStyle(color12, color12, null, color10, color10, color10, null, color9, 68, null), null, null, null, 14, null);
        ButtonStyle buttonStyle11 = new ButtonStyle(new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), null, Integer.valueOf(i12), Integer.valueOf(color7), Integer.valueOf(color7), null, Integer.valueOf(color7), 68, null), null, null, null, 14, null);
        ButtonStyle buttonStyle12 = new ButtonStyle(new StatefulStyle(color12, color12, null, color12, color10, color10, null, color10, 68, null), new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), null, Integer.valueOf(i12), null, null, null, null, 244, null), null, null, 12, null);
        ButtonStyle buttonStyle13 = new ButtonStyle(new StatefulStyle(Integer.valueOf(color6), Integer.valueOf(color6), null, Integer.valueOf(color6), color24, color24, null, color24, 68, null), null, null, null, 14, null);
        ButtonStyle buttonStyle14 = new ButtonStyle(new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), null, Integer.valueOf(i12), null, null, null, null, 244, null), null, ColorUtil.getColorStateList$default(colorUtil, i12, null, null, null, 14, null), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_1)), 2, null);
        ButtonStyle buttonStyle15 = new ButtonStyle(new StatefulStyle(Integer.valueOf(color6), Integer.valueOf(color6), null, Integer.valueOf(color6), null, null, null, null, 244, null), null, ColorUtil.getColorStateList$default(colorUtil, color6, null, null, null, 14, null), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_1)), 2, null);
        ButtonStyle buttonStyle16 = new ButtonStyle(new StatefulStyle(Integer.valueOf(color6), Integer.valueOf(color6), null, Integer.valueOf(color6), null, null, null, null, 244, null), new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), null, Integer.valueOf(i12), null, null, null, null, 244, null), ColorUtil.getColorStateList$default(colorUtil, color6, null, null, null, 14, null), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_1)));
        ButtonStyle buttonStyle17 = new ButtonStyle(new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), null, Integer.valueOf(i12), num, num, null, num, 68, null), null, null, null, 14, null);
        ButtonStyle buttonStyle18 = new ButtonStyle(new StatefulStyle(Integer.valueOf(i12), Integer.valueOf(i12), null, Integer.valueOf(i12), color15, color15, null, color15, 68, null), null, null, null, 14, null);
        TagStyleKey tagStyleKey = TagStyleKey.SOLID_PRIMARY;
        addTagStyle(tagStyleKey, buttonStyle9);
        TagStyleKey tagStyleKey2 = TagStyleKey.GLASS_PRIMARY;
        addTagStyle(tagStyleKey2, buttonStyle10);
        TagStyleKey tagStyleKey3 = TagStyleKey.GLASS_SECONDARY;
        addTagStyle(tagStyleKey3, buttonStyle11);
        TagStyleKey tagStyleKey4 = TagStyleKey.GLASS_PRIMARY_ALERT;
        addTagStyle(tagStyleKey4, buttonStyle12);
        TagStyleKey tagStyleKey5 = TagStyleKey.GLASS_TIER_HD;
        addTagStyle(tagStyleKey5, buttonStyle13);
        TagStyleKey tagStyleKey6 = TagStyleKey.OUTLINE_PRIMARY;
        addTagStyle(tagStyleKey6, buttonStyle14);
        TagStyleKey tagStyleKey7 = TagStyleKey.OUTLINE_TIER_HD;
        addTagStyle(tagStyleKey7, buttonStyle15);
        TagStyleKey tagStyleKey8 = TagStyleKey.OUTLINE_TIER_HD_ALERT;
        addTagStyle(tagStyleKey8, buttonStyle16);
        TagStyleKey tagStyleKey9 = TagStyleKey.LIVE;
        addTagStyle(tagStyleKey9, buttonStyle17);
        TagStyleKey tagStyleKey10 = TagStyleKey.LIVE_VIEWER;
        addTagStyle(tagStyleKey10, buttonStyle18);
        addTagBuilder(tagSizeKey, tagStyleKey);
        addTagBuilder(tagSizeKey, tagStyleKey2);
        addTagBuilder(tagSizeKey, tagStyleKey5);
        addTagBuilder(tagSizeKey, tagStyleKey6);
        addTagBuilder(tagSizeKey, tagStyleKey7);
        addTagBuilder(tagSizeKey2, tagStyleKey);
        addTagBuilder(tagSizeKey2, tagStyleKey2);
        addTagBuilder(tagSizeKey2, tagStyleKey5);
        addTagBuilder(tagSizeKey2, tagStyleKey6);
        addTagBuilder(tagSizeKey2, tagStyleKey7);
        addTagBuilder(tagSizeKey2, tagStyleKey3);
        addTagBuilder(tagSizeKey3, tagStyleKey);
        addTagBuilder(tagSizeKey3, tagStyleKey2);
        addTagBuilder(tagSizeKey3, tagStyleKey5);
        addTagBuilder(tagSizeKey3, tagStyleKey6);
        addTagBuilder(tagSizeKey3, tagStyleKey7);
        addTagBuilder(tagSizeKey3, tagStyleKey4);
        addTagBuilder(tagSizeKey3, tagStyleKey8);
        addTagBuilder(tagSizeKey4, tagStyleKey);
        addTagBuilder(tagSizeKey4, tagStyleKey2);
        addTagBuilder(tagSizeKey4, tagStyleKey5);
        addTagBuilder(tagSizeKey4, tagStyleKey6);
        addTagBuilder(tagSizeKey4, tagStyleKey7);
        addTagBuilder(tagSizeKey4, tagStyleKey4);
        addTagBuilder(tagSizeKey4, tagStyleKey8);
        addTagBuilder(tagSizeKey2, tagStyleKey9);
        addTagBuilder(tagSizeKey2, tagStyleKey10);
        PopularityWidgetStyle popularityWidgetStyle = new PopularityWidgetStyle(10, new PopularityBarStyle(this.resources.getDimension(R.dimen.spacer_2), this.resources.getDimension(R.dimen.spacer_6), null, 4, null), null, color11.intValue(), i7, 4, null);
        PopularityWidgetStyleKey popularityWidgetStyleKey = PopularityWidgetStyleKey.DEFAULT;
        addPopularityWidgetStyle(popularityWidgetStyleKey, popularityWidgetStyle);
        addPopularityWidgetBuilder(popularityWidgetStyleKey);
        if (color10 != null) {
            DividerStyle dividerStyle = new DividerStyle(this.resources.getDimension(R.dimen.spacer_1), color10.intValue(), null, 4, null);
            DividerStyleKey dividerStyleKey = DividerStyleKey.DEFAULT;
            addDividerStyle(dividerStyleKey, dividerStyle);
            addDividerBuilder(dividerStyleKey);
        }
        ButtonSize buttonSize20 = new ButtonSize(this.resources.getDimension(R.dimen.spacer_16), null, this.resources.getDimension(R.dimen.spacer_12), 2, null);
        IndicatorSizeKey indicatorSizeKey = IndicatorSizeKey.TINY;
        addIndicatorSize(indicatorSizeKey, buttonSize20);
        IndicatorSizeKey indicatorSizeKey2 = IndicatorSizeKey.SMALL;
        addIndicatorSize(indicatorSizeKey2, buttonSize2);
        IndicatorSizeKey indicatorSizeKey3 = IndicatorSizeKey.MEDIUM;
        addIndicatorSize(indicatorSizeKey3, buttonSize);
        IndicatorSizeKey indicatorSizeKey4 = IndicatorSizeKey.LARGE;
        addIndicatorSize(indicatorSizeKey4, buttonSize4);
        ButtonStyle buttonStyle19 = new ButtonStyle(new StatefulStyle(Integer.valueOf(i12), null, null, null, color15, null, null, color13, 110, null), null, ColorUtil.getColorStateList$default(colorUtil, i12, null, null, null, 14, null), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacer_1)), 2, null);
        IndicatorStyleKey indicatorStyleKey = IndicatorStyleKey.PLAYBACK;
        addIndicatorStyle(indicatorStyleKey, buttonStyle19);
        ButtonStyle buttonStyle20 = new ButtonStyle(new StatefulStyle(Integer.valueOf(i9), null, null, null, Integer.valueOf(i7), null, null, color18, 110, null), null, null, null, 14, null);
        IndicatorStyleKey indicatorStyleKey2 = IndicatorStyleKey.DOWNLOAD;
        addIndicatorStyle(indicatorStyleKey2, buttonStyle20);
        addIndicatorBuilder(indicatorSizeKey2, indicatorStyleKey);
        addIndicatorBuilder(indicatorSizeKey3, indicatorStyleKey);
        addIndicatorBuilder(indicatorSizeKey4, indicatorStyleKey);
        addIndicatorBuilder(indicatorSizeKey, indicatorStyleKey2);
        LinkStyle linkStyle = new LinkStyle(new StatefulStyle(Integer.valueOf(i12), num5, null, null, null, null, null, color11, 124, null), null, 2, null);
        LinkStyle linkStyle2 = new LinkStyle(new StatefulStyle(Integer.valueOf(i7), num5, null, null, null, null, null, combinationColor4, 124, null), bool);
        LinkStyleKey linkStyleKey = LinkStyleKey.STANDARD;
        addLinkStyle(linkStyleKey, linkStyle);
        LinkStyleKey linkStyleKey2 = LinkStyleKey.ACCENT;
        addLinkStyle(linkStyleKey2, linkStyle2);
        addLinkBuilder(linkStyleKey);
        addLinkBuilder(linkStyleKey2);
        int dimension4 = (int) this.resources.getDimension(R.dimen.screen_size_360);
        int dimension5 = (int) this.resources.getDimension(R.dimen.screen_size_480);
        int dimension6 = (int) this.resources.getDimension(R.dimen.screen_size_640);
        int dimension7 = (int) this.resources.getDimension(R.dimen.screen_size_840);
        int dimension8 = (int) this.resources.getDimension(R.dimen.screen_size_1024);
        int dimension9 = (int) this.resources.getDimension(R.dimen.screen_size_1280);
        int dimension10 = (int) this.resources.getDimension(R.dimen.screen_size_1440);
        ScreenSizeKey screenSizeKey = ScreenSizeKey.XSMALL;
        addScreenSizeRange(screenSizeKey, new Range<>(0, Integer.valueOf(dimension4 - 1)));
        ScreenSizeKey screenSizeKey2 = ScreenSizeKey.SMALL;
        addScreenSizeRange(screenSizeKey2, new Range<>(Integer.valueOf(dimension4), Integer.valueOf(dimension5 - 1)));
        ScreenSizeKey screenSizeKey3 = ScreenSizeKey.MEDIUM;
        addScreenSizeRange(screenSizeKey3, new Range<>(Integer.valueOf(dimension5), Integer.valueOf(dimension6 - 1)));
        ScreenSizeKey screenSizeKey4 = ScreenSizeKey.LARGE;
        addScreenSizeRange(screenSizeKey4, new Range<>(Integer.valueOf(dimension6), Integer.valueOf(dimension7 - 1)));
        ScreenSizeKey screenSizeKey5 = ScreenSizeKey.XLARGE;
        addScreenSizeRange(screenSizeKey5, new Range<>(Integer.valueOf(dimension7), Integer.valueOf(dimension8 - 1)));
        ScreenSizeKey screenSizeKey6 = ScreenSizeKey.XLARGE2;
        addScreenSizeRange(screenSizeKey6, new Range<>(Integer.valueOf(dimension8), Integer.valueOf(dimension9 - 1)));
        ScreenSizeKey screenSizeKey7 = ScreenSizeKey.XLARGE3;
        addScreenSizeRange(screenSizeKey7, new Range<>(Integer.valueOf(dimension9), Integer.valueOf(dimension10 - 1)));
        ScreenSizeKey screenSizeKey8 = ScreenSizeKey.XLARGE4;
        addScreenSizeRange(screenSizeKey8, new Range<>(Integer.valueOf(dimension10), Integer.MAX_VALUE));
        Integer spacerInPixels = getSpacerInPixels(SpacerKey.HUGE);
        Intrinsics.checkNotNull(spacerInPixels);
        int iIntValue = spacerInPixels.intValue();
        int i13 = iIntValue * 3;
        addFeaturedPlayTopMargin(screenSizeKey, i13);
        addFeaturedPlayTopMargin(screenSizeKey2, iIntValue * 4);
        addFeaturedPlayTopMargin(screenSizeKey3, iIntValue * 5);
        addFeaturedPlayTopMargin(screenSizeKey4, iIntValue * 6);
        int i14 = iIntValue * 7;
        addFeaturedPlayTopMargin(screenSizeKey5, i14);
        addFeaturedPlayTopMargin(screenSizeKey6, iIntValue * 8);
        int i15 = iIntValue * 9;
        addFeaturedPlayTopMargin(screenSizeKey7, i15);
        addFeaturedPlayTopMargin(screenSizeKey8, iIntValue * 10);
        addFeaturedPlayLeftAlignedTopMargin(screenSizeKey, i14);
        addFeaturedPlayLeftAlignedTopMargin(screenSizeKey2, i15);
        addFeaturedPlayLeftAlignedTopMargin(screenSizeKey3, iIntValue * 12);
        addFeaturedPlayLeftAlignedTopMargin(screenSizeKey4, i13);
        Iterator<ScreenSizeKey> it = getNeededScreenSizes().iterator();
        while (it.hasNext()) {
            initScreenSizeDependentValues(it.next());
        }
        addFontStyle(FontStyleKey.PRIMARY_TITLE, new FontStyle(new FontSize((int) this.resources.getDimension(R.dimen.text_size_24), Integer.valueOf((int) this.resources.getDimension(R.dimen.spacing_32))), typeface4, i12, Boolean.FALSE));
    }

    private final Set<ScreenSizeKey> getNeededScreenSizes() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = CollectionsKt__CollectionsKt.listOf((Object[]) new ScreenSizeKey[]{getFullWidthScreenSizeKey(), getFullHeightScreenSizeKey()}).iterator();
        while (it.hasNext()) {
            linkedHashSet.addAll(getNeededScreenSizesForTargetScreenSize((ScreenSizeKey) it.next()));
        }
        return linkedHashSet;
    }

    private final Set<ScreenSizeKey> getNeededScreenSizesForTargetScreenSize(ScreenSizeKey targetScreenSizeKey) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (ScreenSizeKey screenSizeKey : CollectionsKt__CollectionsKt.listOf((Object[]) new ScreenSizeKey[]{ScreenSizeKey.XSMALL, ScreenSizeKey.SMALL, ScreenSizeKey.MEDIUM, ScreenSizeKey.LARGE, ScreenSizeKey.XLARGE, ScreenSizeKey.XLARGE2, ScreenSizeKey.XLARGE3, ScreenSizeKey.XLARGE4})) {
            linkedHashSet.add(screenSizeKey);
            if (targetScreenSizeKey == screenSizeKey) {
                break;
            }
        }
        return linkedHashSet;
    }

    private final void initScreenSizeDependentValues(ScreenSizeKey screenSizeKey) {
        int dimension = (int) this.resources.getDimension(R.dimen.spacer_24);
        int dimension2 = (int) this.resources.getDimension(R.dimen.spacer_12);
        Integer spacerInPixels = getSpacerInPixels(SpacerKey.GIANT);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        switch (WhenMappings.$EnumSwitchMapping$0[screenSizeKey.ordinal()]) {
            case 1:
                int i = iIntValue;
                GridSize gridSize = new GridSize(this.resources.getDimension(R.dimen.spacer_4), this.resources.getDimension(R.dimen.spacer_16));
                ScreenSizeKey screenSizeKey2 = ScreenSizeKey.XSMALL;
                addGridSize(screenSizeKey2, gridSize);
                int dimension3 = ((int) this.resources.getDimension(R.dimen.spacer_16)) * 2;
                int dimension4 = ((int) this.resources.getDimension(R.dimen.spacer_16)) * 10;
                addGridStyle(screenSizeKey2, GridStyleKey.VERTICAL_ITEM_GRID, new GridStyle(gridSize, dimension, null, 2, 2, 1, Integer.valueOf(dimension3), 4, null));
                addGridStyle(screenSizeKey2, GridStyleKey.LARGE_VERTICAL_ITEM_GRID, new GridStyle(gridSize, dimension, null, 1, 1, 1, Integer.valueOf(dimension4), 4, null));
                addGridStyle(screenSizeKey2, GridStyleKey.VERTICAL_16X9_ITEM_GRID, new GridStyle(gridSize, dimension, null, 2, 1, 1, Integer.valueOf(((int) this.resources.getDimension(R.dimen.spacer_16)) * 6), 4, null));
                addGridStyle(screenSizeKey2, GridStyleKey.HORIZONTAL_ITEM_GRID, new GridStyle(gridSize, 0, null, 3, 1, 3, Integer.valueOf(dimension3), 4, null));
                addGridStyle(screenSizeKey2, GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, new GridStyle(gridSize, 0, null, 3, 1, 3, Integer.valueOf(dimension3), 4, null));
                addGridStyle(screenSizeKey2, GridStyleKey.FEATURED_ITEM_GRID, new GridStyle(gridSize, 0, null, 1, 1, 1, Integer.valueOf(dimension3), 4, null));
                addGridStyle(screenSizeKey2, GridStyleKey.LIST_ITEM_GRID, new GridStyle(gridSize, 0, null, 4, 1, 1, Integer.valueOf(dimension3), 4, null));
                Integer spacerInPixels2 = getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
                int i2 = iIntValue2;
                addGridStyle(screenSizeKey2, GridStyleKey.BUTTON_NAVIGATOR_GRID, new GridStyle(gridSize, i2, Integer.valueOf(iIntValue2), Integer.MAX_VALUE, 1, null, null, 96, null));
                addGridStyle(screenSizeKey2, GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, new GridStyle(gridSize, i2, Integer.valueOf(iIntValue2), 4, 2, 1, Integer.valueOf(dimension3)));
                addGridStyle(screenSizeKey2, GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, new GridStyle(gridSize, dimension, null, 2, 2, 1, Integer.valueOf(i * 3), 4, null));
                addGridStyle(screenSizeKey2, GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID, new GridStyle(gridSize, 0, null, 1, 2, 1, null, 68, null));
                addGridStyle(screenSizeKey2, GridStyleKey.DESCRIPTIVE_LISTING_GRID, new GridStyle(gridSize, 0, null, 3, 1, null, null, 100, null));
                addGridStyle(screenSizeKey2, GridStyleKey.CAR_MODE_GRID, new GridStyle(gridSize, dimension2, null, 2, 2, null, null, 100, null));
                break;
            case 2:
                int i3 = iIntValue;
                GridSize gridSize2 = new GridSize(this.resources.getDimension(R.dimen.spacer_4), this.resources.getDimension(R.dimen.spacer_16));
                ScreenSizeKey screenSizeKey3 = ScreenSizeKey.SMALL;
                addGridSize(screenSizeKey3, gridSize2);
                int dimension5 = ((int) this.resources.getDimension(R.dimen.spacer_16)) * 2;
                int dimension6 = ((int) this.resources.getDimension(R.dimen.spacer_16)) * 10;
                addGridStyle(screenSizeKey3, GridStyleKey.VERTICAL_ITEM_GRID, new GridStyle(gridSize2, dimension, null, 2, 2, 1, Integer.valueOf(dimension5), 4, null));
                addGridStyle(screenSizeKey3, GridStyleKey.VERTICAL_16X9_ITEM_GRID, new GridStyle(gridSize2, dimension, null, 2, 1, 1, Integer.valueOf(((int) this.resources.getDimension(R.dimen.spacer_16)) * 6), 4, null));
                addGridStyle(screenSizeKey3, GridStyleKey.LARGE_VERTICAL_ITEM_GRID, new GridStyle(gridSize2, dimension, null, 1, 1, 1, Integer.valueOf(dimension6), 4, null));
                addGridStyle(screenSizeKey3, GridStyleKey.HORIZONTAL_ITEM_GRID, new GridStyle(gridSize2, 0, null, 3, 1, 3, Integer.valueOf(dimension5), 4, null));
                addGridStyle(screenSizeKey3, GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, new GridStyle(gridSize2, 0, null, 3, 1, 3, Integer.valueOf(dimension5), 4, null));
                addGridStyle(screenSizeKey3, GridStyleKey.FEATURED_ITEM_GRID, new GridStyle(gridSize2, 0, null, 1, 1, 1, Integer.valueOf(dimension5), 4, null));
                addGridStyle(screenSizeKey3, GridStyleKey.LIST_ITEM_GRID, new GridStyle(gridSize2, 0, null, 4, 1, 1, Integer.valueOf(dimension5), 4, null));
                Integer spacerInPixels3 = getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue3 = spacerInPixels3 == null ? 0 : spacerInPixels3.intValue();
                addGridStyle(screenSizeKey3, GridStyleKey.BUTTON_NAVIGATOR_GRID, new GridStyle(gridSize2, iIntValue3, Integer.valueOf(iIntValue3), Integer.MAX_VALUE, 1, null, null, 96, null));
                addGridStyle(screenSizeKey3, GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, new GridStyle(gridSize2, iIntValue3, Integer.valueOf(iIntValue3), 4, 2, 1, Integer.valueOf(dimension5)));
                addGridStyle(screenSizeKey3, GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, new GridStyle(gridSize2, dimension, null, 2, 2, 1, Integer.valueOf(i3 * 3), 4, null));
                addGridStyle(screenSizeKey3, GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID, new GridStyle(gridSize2, 0, null, 1, 3, 1, null, 68, null));
                addGridStyle(screenSizeKey3, GridStyleKey.DESCRIPTIVE_LISTING_GRID, new GridStyle(gridSize2, 0, null, 3, 1, null, null, 100, null));
                addGridStyle(screenSizeKey3, GridStyleKey.CAR_MODE_GRID, new GridStyle(gridSize2, dimension2, null, 2, 2, null, null, 100, null));
                break;
            case 3:
                int i4 = iIntValue;
                GridSize gridSize3 = new GridSize(this.resources.getDimension(R.dimen.spacer_12), this.resources.getDimension(R.dimen.spacer_24));
                ScreenSizeKey screenSizeKey4 = ScreenSizeKey.MEDIUM;
                addGridSize(screenSizeKey4, gridSize3);
                int dimension7 = ((int) this.resources.getDimension(R.dimen.spacer_24)) * 2;
                int dimension8 = ((int) this.resources.getDimension(R.dimen.spacer_24)) * 6;
                addGridStyle(screenSizeKey4, GridStyleKey.VERTICAL_ITEM_GRID, new GridStyle(gridSize3, dimension, null, 2, 3, 1, Integer.valueOf(dimension7), 4, null));
                addGridStyle(screenSizeKey4, GridStyleKey.VERTICAL_16X9_ITEM_GRID, new GridStyle(gridSize3, dimension, null, 2, 1, 1, Integer.valueOf(((int) this.resources.getDimension(R.dimen.spacer_24)) * 6), 4, null));
                addGridStyle(screenSizeKey4, GridStyleKey.HORIZONTAL_ITEM_GRID, new GridStyle(gridSize3, 0, null, 3, 1, 3, Integer.valueOf(dimension7), 4, null));
                addGridStyle(screenSizeKey4, GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, new GridStyle(gridSize3, 0, null, 3, 1, 3, Integer.valueOf(dimension7), 4, null));
                addGridStyle(screenSizeKey4, GridStyleKey.FEATURED_ITEM_GRID, new GridStyle(gridSize3, 0, null, 1, 1, 1, Integer.valueOf(dimension7), 4, null));
                addGridStyle(screenSizeKey4, GridStyleKey.LIST_ITEM_GRID, new GridStyle(gridSize3, 0, null, 4, 1, 1, Integer.valueOf(dimension7), 4, null));
                Integer spacerInPixels4 = getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue4 = spacerInPixels4 == null ? 0 : spacerInPixels4.intValue();
                int i5 = iIntValue4;
                addGridStyle(screenSizeKey4, GridStyleKey.BUTTON_NAVIGATOR_GRID, new GridStyle(gridSize3, i5, Integer.valueOf(iIntValue4), Integer.MAX_VALUE, 1, null, null, 96, null));
                addGridStyle(screenSizeKey4, GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, new GridStyle(gridSize3, i5, Integer.valueOf(iIntValue4), 4, 2, 1, Integer.valueOf(dimension7)));
                addGridStyle(screenSizeKey4, GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, new GridStyle(gridSize3, dimension, null, 2, 3, 1, Integer.valueOf(i4 * 3), 4, null));
                addGridStyle(screenSizeKey4, GridStyleKey.LARGE_VERTICAL_ITEM_GRID, new GridStyle(gridSize3, dimension, null, 1, 2, 1, Integer.valueOf(dimension8), 4, null));
                addGridStyle(screenSizeKey4, GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID, new GridStyle(gridSize3, 0, null, 1, 3, 1, null, 68, null));
                addGridStyle(screenSizeKey4, GridStyleKey.DESCRIPTIVE_LISTING_GRID, new GridStyle(gridSize3, 0, null, 3, 1, null, null, 100, null));
                addGridStyle(screenSizeKey4, GridStyleKey.CAR_MODE_GRID, new GridStyle(gridSize3, dimension2, null, 2, 2, null, null, 100, null));
                break;
            case 4:
                int i6 = iIntValue;
                GridSize gridSize4 = new GridSize(this.resources.getDimension(R.dimen.spacer_12), this.resources.getDimension(R.dimen.spacer_24));
                ScreenSizeKey screenSizeKey5 = ScreenSizeKey.LARGE;
                addGridSize(screenSizeKey5, gridSize4);
                int dimension9 = ((int) this.resources.getDimension(R.dimen.spacer_24)) * 2;
                int dimension10 = ((int) this.resources.getDimension(R.dimen.spacer_24)) * 6;
                addGridStyle(screenSizeKey5, GridStyleKey.VERTICAL_ITEM_GRID, new GridStyle(gridSize4, dimension, null, 2, 4, 1, Integer.valueOf(dimension9), 4, null));
                addGridStyle(screenSizeKey5, GridStyleKey.LARGE_VERTICAL_ITEM_GRID, new GridStyle(gridSize4, dimension, null, 1, 3, 1, Integer.valueOf(dimension10), 4, null));
                addGridStyle(screenSizeKey5, GridStyleKey.VERTICAL_16X9_ITEM_GRID, new GridStyle(gridSize4, dimension, null, 2, 2, 1, Integer.valueOf(dimension9), 4, null));
                addGridStyle(screenSizeKey5, GridStyleKey.HORIZONTAL_ITEM_GRID, new GridStyle(gridSize4, 0, null, 3, 2, 3, Integer.valueOf(dimension9), 4, null));
                addGridStyle(screenSizeKey5, GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, new GridStyle(gridSize4, 0, null, 3, 1, 3, Integer.valueOf(dimension9), 4, null));
                addGridStyle(screenSizeKey5, GridStyleKey.FEATURED_ITEM_GRID, new GridStyle(gridSize4, 0, null, 1, 2, 1, Integer.valueOf(dimension9), 4, null));
                Integer spacerInPixels5 = getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue5 = spacerInPixels5 == null ? 0 : spacerInPixels5.intValue();
                addGridStyle(screenSizeKey5, GridStyleKey.LIST_ITEM_GRID, new GridStyle(gridSize4, 0, Integer.valueOf(iIntValue5), 2, 2, 1, Integer.valueOf(dimension9)));
                int i7 = iIntValue5;
                addGridStyle(screenSizeKey5, GridStyleKey.BUTTON_NAVIGATOR_GRID, new GridStyle(gridSize4, i7, Integer.valueOf(iIntValue5), Integer.MAX_VALUE, 2, null, null, 96, null));
                addGridStyle(screenSizeKey5, GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, new GridStyle(gridSize4, i7, Integer.valueOf(iIntValue5), 4, 2, 1, Integer.valueOf(dimension9)));
                addGridStyle(screenSizeKey5, GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, new GridStyle(gridSize4, dimension, null, 2, 4, 1, Integer.valueOf(i6 * 3), 4, null));
                addGridStyle(screenSizeKey5, GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID, new GridStyle(gridSize4, 0, null, 1, 2, 1, null, 68, null));
                addGridStyle(screenSizeKey5, GridStyleKey.DESCRIPTIVE_LISTING_GRID, new GridStyle(gridSize4, 0, null, 3, 1, null, null, 100, null));
                addGridStyle(screenSizeKey5, GridStyleKey.CAR_MODE_GRID, new GridStyle(gridSize4, dimension2, null, 2, 2, null, null, 100, null));
                break;
            case 5:
                int i8 = iIntValue;
                GridSize gridSize5 = new GridSize(this.resources.getDimension(R.dimen.spacer_12), this.resources.getDimension(R.dimen.spacer_24));
                ScreenSizeKey screenSizeKey6 = ScreenSizeKey.XLARGE;
                addGridSize(screenSizeKey6, gridSize5);
                int dimension11 = ((int) this.resources.getDimension(R.dimen.spacer_24)) * 2;
                int dimension12 = ((int) this.resources.getDimension(R.dimen.spacer_24)) * 6;
                addGridStyle(screenSizeKey6, GridStyleKey.VERTICAL_ITEM_GRID, new GridStyle(gridSize5, dimension, null, 2, 4, 1, Integer.valueOf(dimension11), 4, null));
                addGridStyle(screenSizeKey6, GridStyleKey.VERTICAL_16X9_ITEM_GRID, new GridStyle(gridSize5, dimension, null, 2, 2, 1, Integer.valueOf(dimension11), 4, null));
                addGridStyle(screenSizeKey6, GridStyleKey.HORIZONTAL_ITEM_GRID, new GridStyle(gridSize5, 0, null, 3, 2, 3, Integer.valueOf(dimension11), 4, null));
                addGridStyle(screenSizeKey6, GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, new GridStyle(gridSize5, 0, null, 3, 1, 3, Integer.valueOf(dimension11), 4, null));
                addGridStyle(screenSizeKey6, GridStyleKey.FEATURED_ITEM_GRID, new GridStyle(gridSize5, 0, null, 1, 3, 1, Integer.valueOf(dimension11), 4, null));
                Integer spacerInPixels6 = getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue6 = spacerInPixels6 == null ? 0 : spacerInPixels6.intValue();
                addGridStyle(screenSizeKey6, GridStyleKey.LIST_ITEM_GRID, new GridStyle(gridSize5, 0, Integer.valueOf(iIntValue6), 2, 2, 1, Integer.valueOf(dimension11)));
                int i9 = iIntValue6;
                addGridStyle(screenSizeKey6, GridStyleKey.BUTTON_NAVIGATOR_GRID, new GridStyle(gridSize5, i9, Integer.valueOf(iIntValue6), Integer.MAX_VALUE, 2, null, null, 96, null));
                addGridStyle(screenSizeKey6, GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, new GridStyle(gridSize5, i9, Integer.valueOf(iIntValue6), 4, 3, 1, Integer.valueOf(dimension11)));
                addGridStyle(screenSizeKey6, GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, new GridStyle(gridSize5, dimension, null, 2, 5, 1, Integer.valueOf(i8 * 3), 4, null));
                addGridStyle(screenSizeKey6, GridStyleKey.LARGE_VERTICAL_ITEM_GRID, new GridStyle(gridSize5, dimension, null, 1, 3, 1, Integer.valueOf(dimension12), 4, null));
                addGridStyle(screenSizeKey6, GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID, new GridStyle(gridSize5, 0, null, 1, 2, 1, null, 68, null));
                addGridStyle(screenSizeKey6, GridStyleKey.DESCRIPTIVE_LISTING_GRID, new GridStyle(gridSize5, 0, null, 3, 1, null, null, 100, null));
                addGridStyle(screenSizeKey6, GridStyleKey.CAR_MODE_GRID, new GridStyle(gridSize5, dimension2, null, 2, 2, null, null, 100, null));
                break;
            case 6:
                int i10 = iIntValue;
                GridSize gridSize6 = new GridSize(this.resources.getDimension(R.dimen.spacer_12), this.resources.getDimension(R.dimen.spacer_24));
                ScreenSizeKey screenSizeKey7 = ScreenSizeKey.XLARGE2;
                addGridSize(screenSizeKey7, gridSize6);
                int dimension13 = ((int) this.resources.getDimension(R.dimen.spacer_24)) * 2;
                int dimension14 = ((int) this.resources.getDimension(R.dimen.spacer_24)) * 6;
                addGridStyle(screenSizeKey7, GridStyleKey.VERTICAL_ITEM_GRID, new GridStyle(gridSize6, dimension, null, 2, 5, 1, Integer.valueOf(dimension13), 4, null));
                addGridStyle(screenSizeKey7, GridStyleKey.VERTICAL_16X9_ITEM_GRID, new GridStyle(gridSize6, dimension, null, 2, 2, 1, Integer.valueOf(dimension13), 4, null));
                addGridStyle(screenSizeKey7, GridStyleKey.HORIZONTAL_ITEM_GRID, new GridStyle(gridSize6, 0, null, 3, 3, 3, Integer.valueOf(dimension13), 4, null));
                addGridStyle(screenSizeKey7, GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, new GridStyle(gridSize6, 0, null, 3, 1, 3, Integer.valueOf(dimension13), 4, null));
                addGridStyle(screenSizeKey7, GridStyleKey.FEATURED_ITEM_GRID, new GridStyle(gridSize6, 0, null, 1, 3, 1, Integer.valueOf(dimension13), 4, null));
                Integer spacerInPixels7 = getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue7 = spacerInPixels7 == null ? 0 : spacerInPixels7.intValue();
                addGridStyle(screenSizeKey7, GridStyleKey.LIST_ITEM_GRID, new GridStyle(gridSize6, 0, Integer.valueOf(iIntValue7), 2, 2, 1, Integer.valueOf(dimension13)));
                int i11 = iIntValue7;
                addGridStyle(screenSizeKey7, GridStyleKey.BUTTON_NAVIGATOR_GRID, new GridStyle(gridSize6, i11, Integer.valueOf(iIntValue7), Integer.MAX_VALUE, 2, null, null, 96, null));
                addGridStyle(screenSizeKey7, GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, new GridStyle(gridSize6, i11, Integer.valueOf(iIntValue7), 4, 3, 1, Integer.valueOf(dimension13)));
                addGridStyle(screenSizeKey7, GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, new GridStyle(gridSize6, dimension, null, 2, 6, 1, Integer.valueOf(i10 * 3), 4, null));
                addGridStyle(screenSizeKey7, GridStyleKey.LARGE_VERTICAL_ITEM_GRID, new GridStyle(gridSize6, dimension, null, 1, 4, 1, Integer.valueOf(dimension14), 4, null));
                addGridStyle(screenSizeKey7, GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID, new GridStyle(gridSize6, 0, null, 1, 2, 1, null, 68, null));
                addGridStyle(screenSizeKey7, GridStyleKey.DESCRIPTIVE_LISTING_GRID, new GridStyle(gridSize6, 0, null, 3, 1, null, null, 100, null));
                addGridStyle(screenSizeKey7, GridStyleKey.CAR_MODE_GRID, new GridStyle(gridSize6, dimension2, null, 2, 2, null, null, 100, null));
                break;
            case 7:
                GridSize gridSize7 = new GridSize(this.resources.getDimension(R.dimen.spacer_20), this.resources.getDimension(R.dimen.spacer_32));
                ScreenSizeKey screenSizeKey8 = ScreenSizeKey.XLARGE3;
                addGridSize(screenSizeKey8, gridSize7);
                int dimension15 = ((int) this.resources.getDimension(R.dimen.spacer_32)) * 2;
                int dimension16 = ((int) this.resources.getDimension(R.dimen.spacer_32)) * 5;
                int i12 = iIntValue;
                addGridStyle(screenSizeKey8, GridStyleKey.VERTICAL_ITEM_GRID, new GridStyle(gridSize7, dimension, null, 2, 6, 1, Integer.valueOf(dimension15), 4, null));
                addGridStyle(screenSizeKey8, GridStyleKey.VERTICAL_16X9_ITEM_GRID, new GridStyle(gridSize7, dimension, null, 2, 3, 1, Integer.valueOf(dimension15), 4, null));
                addGridStyle(screenSizeKey8, GridStyleKey.HORIZONTAL_ITEM_GRID, new GridStyle(gridSize7, 0, null, 3, 3, 3, Integer.valueOf(dimension15), 4, null));
                addGridStyle(screenSizeKey8, GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, new GridStyle(gridSize7, 0, null, 3, 1, 3, Integer.valueOf(dimension15), 4, null));
                addGridStyle(screenSizeKey8, GridStyleKey.FEATURED_ITEM_GRID, new GridStyle(gridSize7, 0, null, 1, 4, 1, Integer.valueOf(dimension15), 4, null));
                Integer spacerInPixels8 = getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue8 = spacerInPixels8 == null ? 0 : spacerInPixels8.intValue();
                addGridStyle(screenSizeKey8, GridStyleKey.LIST_ITEM_GRID, new GridStyle(gridSize7, 0, Integer.valueOf(iIntValue8), 1, 4, 1, Integer.valueOf(dimension15)));
                int i13 = iIntValue8;
                addGridStyle(screenSizeKey8, GridStyleKey.BUTTON_NAVIGATOR_GRID, new GridStyle(gridSize7, i13, Integer.valueOf(iIntValue8), Integer.MAX_VALUE, 4, null, null, 96, null));
                addGridStyle(screenSizeKey8, GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, new GridStyle(gridSize7, i13, Integer.valueOf(iIntValue8), 4, 4, 1, Integer.valueOf(dimension15)));
                addGridStyle(screenSizeKey8, GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, new GridStyle(gridSize7, dimension, null, 2, 7, 1, Integer.valueOf(i12 * 3), 4, null));
                addGridStyle(screenSizeKey8, GridStyleKey.LARGE_VERTICAL_ITEM_GRID, new GridStyle(gridSize7, dimension, null, 1, 5, 1, Integer.valueOf(dimension16), 4, null));
                addGridStyle(screenSizeKey8, GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID, new GridStyle(gridSize7, 0, null, 1, 2, 1, null, 68, null));
                addGridStyle(screenSizeKey8, GridStyleKey.DESCRIPTIVE_LISTING_GRID, new GridStyle(gridSize7, 0, null, 3, 1, null, null, 100, null));
                addGridStyle(screenSizeKey8, GridStyleKey.CAR_MODE_GRID, new GridStyle(gridSize7, dimension2, null, 2, 2, null, null, 100, null));
                break;
            case 8:
                GridSize gridSize8 = new GridSize(this.resources.getDimension(R.dimen.spacer_20), this.resources.getDimension(R.dimen.spacer_32));
                ScreenSizeKey screenSizeKey9 = ScreenSizeKey.XLARGE4;
                addGridSize(screenSizeKey9, gridSize8);
                int dimension17 = ((int) this.resources.getDimension(R.dimen.spacer_32)) * 2;
                int dimension18 = ((int) this.resources.getDimension(R.dimen.spacer_32)) * 5;
                addGridStyle(screenSizeKey9, GridStyleKey.VERTICAL_ITEM_GRID, new GridStyle(gridSize8, dimension, null, 2, 7, 1, Integer.valueOf(dimension17), 4, null));
                addGridStyle(screenSizeKey9, GridStyleKey.HORIZONTAL_ITEM_GRID, new GridStyle(gridSize8, 0, null, 3, 3, 3, Integer.valueOf(dimension17), 4, null));
                addGridStyle(screenSizeKey9, GridStyleKey.HORIZONTAL_ITEM_SECTION_NESTED_GRID, new GridStyle(gridSize8, 0, null, 3, 1, 3, Integer.valueOf(dimension17), 4, null));
                addGridStyle(screenSizeKey9, GridStyleKey.FEATURED_ITEM_GRID, new GridStyle(gridSize8, 0, null, 1, 4, 1, Integer.valueOf(dimension17), 4, null));
                Integer spacerInPixels9 = getSpacerInPixels(SpacerKey.SMALL);
                int iIntValue9 = spacerInPixels9 == null ? 0 : spacerInPixels9.intValue();
                addGridStyle(screenSizeKey9, GridStyleKey.LIST_ITEM_GRID, new GridStyle(gridSize8, 0, Integer.valueOf(iIntValue9), 1, 4, 1, Integer.valueOf(dimension17)));
                int i14 = iIntValue9;
                addGridStyle(screenSizeKey9, GridStyleKey.BUTTON_NAVIGATOR_GRID, new GridStyle(gridSize8, i14, Integer.valueOf(iIntValue9), Integer.MAX_VALUE, 4, null, null, 96, null));
                addGridStyle(screenSizeKey9, GridStyleKey.THUMBNAIL_NAVIGATOR_GRID, new GridStyle(gridSize8, i14, Integer.valueOf(iIntValue9), 4, 4, 1, Integer.valueOf(dimension17)));
                addGridStyle(screenSizeKey9, GridStyleKey.COMPACT_VERTICAL_ITEM_GRID, new GridStyle(gridSize8, dimension, null, 2, 7, 1, Integer.valueOf(iIntValue * 3), 4, null));
                addGridStyle(screenSizeKey9, GridStyleKey.DESCRIPTIVE_SHOVELER_FOCUSED_TILE_GRID, new GridStyle(gridSize8, 0, null, 1, 2, 1, null, 68, null));
                addGridStyle(screenSizeKey9, GridStyleKey.LARGE_VERTICAL_ITEM_GRID, new GridStyle(gridSize8, dimension, null, 1, 6, 1, Integer.valueOf(dimension18), 4, null));
                addGridStyle(screenSizeKey9, GridStyleKey.DESCRIPTIVE_LISTING_GRID, new GridStyle(gridSize8, 0, null, 3, 1, null, null, 100, null));
                addGridStyle(screenSizeKey9, GridStyleKey.CAR_MODE_GRID, new GridStyle(gridSize8, dimension2, null, 2, 2, null, null, 100, null));
                break;
        }
    }
}
