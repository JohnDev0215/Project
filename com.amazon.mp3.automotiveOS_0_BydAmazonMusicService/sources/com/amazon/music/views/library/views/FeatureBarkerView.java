package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$styleable;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeatureBarkerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010h\u001a\u00020g\u0012\u0006\u0010'\u001a\u00020&\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+\u0012\b\b\u0002\u00100\u001a\u00020\u0004\u0012\b\b\u0002\u00104\u001a\u00020\u0004¢\u0006\u0004\bi\u0010jJ\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\r\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\b\u0010\u000e\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u000f\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u0011\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\b\u0010\u0012\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u0013\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\b\u0010\u0014\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0015J\u0006\u0010\u0019\u001a\u00020\u0018J\u0006\u0010\u001b\u001a\u00020\u001aJ\b\u0010\u001c\u001a\u00020\u0007H\u0002J\u001a\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002JG\u0010$\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001d2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b$\u0010%R\u0017\u0010'\u001a\u00020&8\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0019\u0010,\u001a\u0004\u0018\u00010+8\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u0017\u00100\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0017\u00104\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b4\u00101\u001a\u0004\b5\u00103R\u001b\u0010:\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u001b\u0010>\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u00107\u001a\u0004\b<\u0010=R\u001b\u0010A\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b?\u00107\u001a\u0004\b@\u0010=R\u001b\u0010D\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bB\u00107\u001a\u0004\bC\u0010=R\u001b\u0010G\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bE\u00107\u001a\u0004\bF\u0010=R\u001b\u0010K\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bH\u00107\u001a\u0004\bI\u0010JR\u001b\u0010P\u001a\u00020L8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bM\u00107\u001a\u0004\bN\u0010OR\u001b\u0010U\u001a\u00020Q8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bR\u00107\u001a\u0004\bS\u0010TR\u0018\u0010W\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u001b\u0010]\u001a\u00020Y8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bZ\u00107\u001a\u0004\b[\u0010\\R\u0016\u0010^\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u0016\u0010b\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010aR\u0016\u0010c\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010aR\u0016\u0010d\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010aR\u0016\u0010e\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010aR\u0016\u0010f\u001a\u0004\u0018\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bf\u0010a¨\u0006k"}, d2 = {"Lcom/amazon/music/views/library/views/FeatureBarkerView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", "is16x9", "", "width", "height", "", "initViewMargins", Constants.ENABLE_DISABLE, "setEnabled", "", "text", "setLabelText", "getLabelText", "setMainText", "getMainText", "setSubText", "getSubText", "setDetailText", "getDetailText", "Landroid/view/View$OnClickListener;", "onClickListener", "setArtSquareOnClickListener", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "getImageArtView", "Lcom/amazon/ui/foundations/views/BaseImage;", "getBackgroundImageView", "initViewStyling", "Landroid/widget/TextView;", "textView", "setOrHideText", "left", "top", "right", "bottom", "setTextMargins", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/util/AttributeSet;", "attrs", "Landroid/util/AttributeSet;", "getAttrs", "()Landroid/util/AttributeSet;", "defStyleAttr", "I", "getDefStyleAttr", "()I", "defStyleRes", "getDefStyleRes", "barkerBackgroundImageView$delegate", "Lkotlin/Lazy;", "getBarkerBackgroundImageView", "()Lcom/amazon/ui/foundations/views/BaseImage;", "barkerBackgroundImageView", "featuredLabelTextView$delegate", "getFeaturedLabelTextView", "()Landroid/widget/TextView;", "featuredLabelTextView", "featuredMainTextView$delegate", "getFeaturedMainTextView", "featuredMainTextView", "featuredSubTextView$delegate", "getFeaturedSubTextView", "featuredSubTextView", "featuredDetailTextView$delegate", "getFeaturedDetailTextView", "featuredDetailTextView", "featuredImageView$delegate", "getFeaturedImageView", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "featuredImageView", "Lcom/amazon/ui/foundations/views/BaseButton;", "explicitTag$delegate", "getExplicitTag", "()Lcom/amazon/ui/foundations/views/BaseButton;", "explicitTag", "Landroid/widget/LinearLayout;", "textContainer$delegate", "getTextContainer", "()Landroid/widget/LinearLayout;", "textContainer", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "explicitTagBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "Landroid/view/View;", "disabledMask$delegate", "getDisabledMask", "()Landroid/view/View;", "disabledMask", "mIsEnabled", "Z", "nanoSpace", "Ljava/lang/Integer;", "microSpace", "miniSpace", "smallSpace", "baseSpace", "largeSpace", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/util/AttributeSet;II)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class FeatureBarkerView extends ConstraintLayout {
    private final AttributeSet attrs;

    /* JADX INFO: renamed from: barkerBackgroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy barkerBackgroundImageView;
    private final Integer baseSpace;
    private final int defStyleAttr;
    private final int defStyleRes;

    /* JADX INFO: renamed from: disabledMask$delegate, reason: from kotlin metadata */
    private final Lazy disabledMask;

    /* JADX INFO: renamed from: explicitTag$delegate, reason: from kotlin metadata */
    private final Lazy explicitTag;
    private BaseButton.StyleBuilder explicitTagBuilder;

    /* JADX INFO: renamed from: featuredDetailTextView$delegate, reason: from kotlin metadata */
    private final Lazy featuredDetailTextView;

    /* JADX INFO: renamed from: featuredImageView$delegate, reason: from kotlin metadata */
    private final Lazy featuredImageView;

    /* JADX INFO: renamed from: featuredLabelTextView$delegate, reason: from kotlin metadata */
    private final Lazy featuredLabelTextView;

    /* JADX INFO: renamed from: featuredMainTextView$delegate, reason: from kotlin metadata */
    private final Lazy featuredMainTextView;

    /* JADX INFO: renamed from: featuredSubTextView$delegate, reason: from kotlin metadata */
    private final Lazy featuredSubTextView;
    private final Integer largeSpace;
    private boolean mIsEnabled;
    private final Integer microSpace;
    private final Integer miniSpace;
    private final Integer nanoSpace;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: textContainer$delegate, reason: from kotlin metadata */
    private final Lazy textContainer;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m2400_init_$lambda0(View view) {
    }

    public /* synthetic */ FeatureBarkerView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i3 & 4) != 0 ? null : attributeSet, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? 0 : i2);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final AttributeSet getAttrs() {
        return this.attrs;
    }

    public final int getDefStyleAttr() {
        return this.defStyleAttr;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeatureBarkerView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.attrs = attributeSet;
        this.defStyleAttr = i;
        this.defStyleRes = i2;
        this.barkerBackgroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<BaseImage>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$barkerBackgroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseImage invoke() {
                return (BaseImage) this.this$0.findViewById(R$id.background_image);
            }
        });
        this.featuredLabelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$featuredLabelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label);
            }
        });
        this.featuredMainTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$featuredMainTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.featuredSubTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$featuredSubTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.subtitle);
            }
        });
        this.featuredDetailTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$featuredDetailTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.recommendation_text);
            }
        });
        this.featuredImageView = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$featuredImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                return (ArtworkFrameView) this.this$0.findViewById(R$id.artwork_frame_view);
            }
        });
        this.explicitTag = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$explicitTag$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.explict_tag);
            }
        });
        this.textContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$textContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.text_container);
            }
        });
        this.disabledMask = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$disabledMask$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R$id.disabled_mask);
            }
        });
        this.mIsEnabled = true;
        this.nanoSpace = styleSheet.getSpacerInPixels(SpacerKey.NANO);
        this.microSpace = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        this.miniSpace = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.baseSpace = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        this.largeSpace = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        View.inflate(context, R$layout.dmmviewlibrary_feature_barker, this);
        if (!isInEditMode()) {
            getDisabledMask().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.FeatureBarkerView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FeatureBarkerView.m2400_init_$lambda0(view);
                }
            });
        }
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.FeatureBarker, i, i2);
            Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.theme.obtainStyl…efStyleAttr, defStyleRes)");
            try {
                setMainText(typedArrayObtainStyledAttributes.getString(R$styleable.FeatureBarker_barker_main_text));
                setSubText(typedArrayObtainStyledAttributes.getString(R$styleable.FeatureBarker_barker_sub_text));
                setEnabled(typedArrayObtainStyledAttributes.getBoolean(R$styleable.FeatureBarker_barker_enabled, true));
            } finally {
                typedArrayObtainStyledAttributes.recycle();
            }
        }
        initViewStyling();
    }

    public final int getDefStyleRes() {
        return this.defStyleRes;
    }

    private final BaseImage getBarkerBackgroundImageView() {
        Object value = this.barkerBackgroundImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-barkerBackgroundImageView>(...)");
        return (BaseImage) value;
    }

    private final TextView getFeaturedLabelTextView() {
        Object value = this.featuredLabelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-featuredLabelTextView>(...)");
        return (TextView) value;
    }

    private final TextView getFeaturedMainTextView() {
        Object value = this.featuredMainTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-featuredMainTextView>(...)");
        return (TextView) value;
    }

    private final TextView getFeaturedSubTextView() {
        Object value = this.featuredSubTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-featuredSubTextView>(...)");
        return (TextView) value;
    }

    private final TextView getFeaturedDetailTextView() {
        Object value = this.featuredDetailTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-featuredDetailTextView>(...)");
        return (TextView) value;
    }

    private final ArtworkFrameView getFeaturedImageView() {
        Object value = this.featuredImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-featuredImageView>(...)");
        return (ArtworkFrameView) value;
    }

    private final BaseButton getExplicitTag() {
        Object value = this.explicitTag.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-explicitTag>(...)");
        return (BaseButton) value;
    }

    private final LinearLayout getTextContainer() {
        Object value = this.textContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textContainer>(...)");
        return (LinearLayout) value;
    }

    private final View getDisabledMask() {
        Object value = this.disabledMask.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-disabledMask>(...)");
        return (View) value;
    }

    public final void initViewMargins(boolean is16x9, int width, int height) {
        int i;
        int i2;
        int dimension = (int) getResources().getDimension(R$dimen.artwork_dimen_featured);
        if (is16x9) {
            int i3 = height / 2;
            i = ((i3 - dimension) / 2) + i3;
        } else {
            i = (height - dimension) / 2;
        }
        if (is16x9) {
            i2 = ((height / 2) - dimension) / 2;
        } else {
            i2 = (height - dimension) / 2;
        }
        Integer num = is16x9 ? this.smallSpace : this.largeSpace;
        int i4 = is16x9 ? 0 : width / 2;
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        layoutParamUtils.setLayoutMargins(getTextContainer(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : this.smallSpace, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : Integer.valueOf(i4), (240 & 128) != 0 ? null : 0);
        layoutParamUtils.setLayoutMargins(getFeaturedImageView(), dimension, dimension, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : num, (240 & 32) != 0 ? null : Integer.valueOf(i), (240 & 64) != 0 ? null : 0, (240 & 128) != 0 ? null : Integer.valueOf(i2));
        setTextMargins(getFeaturedLabelTextView(), 0, 0, 0, 0);
        setTextMargins(getFeaturedMainTextView(), 0, 0, this.baseSpace, this.nanoSpace);
        setTextMargins(getFeaturedSubTextView(), 0, 0, 0, this.nanoSpace);
        setTextMargins(getFeaturedDetailTextView(), 0, 0, 0, 0);
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getFeaturedLabelTextView(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getFeaturedMainTextView(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle3 != null) {
            FontUtil.INSTANCE.applyFontStyle(getFeaturedSubTextView(), fontStyle3);
        }
        FontStyle fontStyle4 = this.styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle4 != null) {
            FontUtil.INSTANCE.applyFontStyle(getFeaturedDetailTextView(), fontStyle4);
        }
        this.explicitTagBuilder = this.styleSheet.getTagBuilder(TagSizeKey.SMALL, TagStyleKey.GLASS_PRIMARY);
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        this.mIsEnabled = isEnabled;
        if (!isEnabled) {
            getDisabledMask().setVisibility(0);
        } else {
            getDisabledMask().setVisibility(8);
        }
    }

    public final void setLabelText(CharSequence text) {
        setOrHideText(getFeaturedLabelTextView(), text);
    }

    public final CharSequence getLabelText() {
        return getFeaturedLabelTextView().getText();
    }

    public final void setMainText(CharSequence text) {
        setOrHideText(getFeaturedMainTextView(), text);
    }

    public final CharSequence getMainText() {
        return getFeaturedMainTextView().getText();
    }

    public final void setSubText(CharSequence text) {
        setOrHideText(getFeaturedSubTextView(), text);
    }

    public final CharSequence getSubText() {
        return getFeaturedSubTextView().getText();
    }

    public final void setDetailText(CharSequence text) {
        setOrHideText(getFeaturedDetailTextView(), text);
    }

    public final CharSequence getDetailText() {
        return getFeaturedDetailTextView().getText();
    }

    private final void setOrHideText(TextView textView, CharSequence text) {
        if (text == null) {
            textView.setVisibility(4);
        } else {
            textView.setText(text);
            textView.setVisibility(0);
        }
    }

    public final void setArtSquareOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getFeaturedImageView().setOnClickListener(onClickListener);
    }

    public final ArtworkFrameView getImageArtView() {
        return getFeaturedImageView();
    }

    public final BaseImage getBackgroundImageView() {
        return getBarkerBackgroundImageView();
    }

    private final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
