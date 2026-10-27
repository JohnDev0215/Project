package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CustomerProfileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0019\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u00132\u0006\u0010&\u001a\u00020'H\u0002J=\u0010(\u001a\u00020$2\u0006\u0010%\u001a\u00020)2\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\b2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0002¢\u0006\u0002\u0010.J)\u0010/\u001a\u00020$2\u0006\u0010%\u001a\u00020)2\b\u00100\u001a\u0004\u0018\u00010\b2\b\u00101\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\u00102J\u001a\u00103\u001a\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u000bJ\u0006\u00108\u001a\u00020\rJ\b\u00109\u001a\u0004\u0018\u00010:J\n\u0010;\u001a\u0004\u0018\u00010<H\u0002J\u0017\u0010=\u001a\u0004\u0018\u00010\b2\u0006\u0010>\u001a\u00020)H\u0002¢\u0006\u0002\u0010?J\u0017\u0010@\u001a\u0004\u0018\u00010\b2\u0006\u0010>\u001a\u00020)H\u0002¢\u0006\u0002\u0010?J\r\u0010A\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010BJ\u0017\u0010C\u001a\u0004\u0018\u00010\b2\u0006\u0010>\u001a\u00020)H\u0002¢\u0006\u0002\u0010?J\u0017\u0010D\u001a\u0004\u0018\u00010\b2\u0006\u0010>\u001a\u00020)H\u0002¢\u0006\u0002\u0010?J\u0006\u0010E\u001a\u00020$J\u0006\u0010F\u001a\u00020$J\u001a\u0010G\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002J\b\u0010H\u001a\u00020$H\u0002J\b\u0010I\u001a\u00020$H\u0002J\b\u0010J\u001a\u00020$H\u0002J\u000e\u0010K\u001a\u00020$2\u0006\u0010L\u001a\u00020MJ\u000e\u0010N\u001a\u00020$2\u0006\u0010L\u001a\u00020MJ\u0018\u0010O\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u0001062\u0006\u00107\u001a\u00020\u000bJ\u0015\u0010P\u001a\u00020$2\b\u0010Q\u001a\u0004\u0018\u00010R¢\u0006\u0002\u0010SJ\u000e\u0010T\u001a\u00020$2\u0006\u0010L\u001a\u00020MJ\u0015\u0010U\u001a\u00020$2\b\u0010Q\u001a\u0004\u0018\u00010R¢\u0006\u0002\u0010SJ\u000e\u0010V\u001a\u00020$2\u0006\u0010L\u001a\u00020MJ \u0010W\u001a\u00020$2\u0006\u0010>\u001a\u00020)2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\bH\u0002J\u000e\u0010X\u001a\u00020$2\u0006\u00107\u001a\u00020\u000bJ\u000e\u0010Y\u001a\u00020$2\u0006\u00107\u001a\u00020\u000bJ\u000e\u0010Z\u001a\u00020$2\u0006\u0010L\u001a\u00020MJ\u000e\u0010[\u001a\u00020$2\u0006\u0010L\u001a\u00020MJ\u0018\u0010\\\u001a\u00020$2\b\u00105\u001a\u0004\u0018\u0001062\u0006\u0010]\u001a\u00020^J\u000e\u0010_\u001a\u00020$2\u0006\u0010L\u001a\u00020MJ\u000e\u0010`\u001a\u00020$2\u0006\u0010a\u001a\u00020^J\u000e\u0010b\u001a\u00020$2\u0006\u0010a\u001a\u00020^J\u000e\u0010c\u001a\u00020$2\u0006\u0010a\u001a\u00020\bJ\u000e\u0010d\u001a\u00020$2\u0006\u0010a\u001a\u00020^J\u000e\u0010e\u001a\u00020$2\u0006\u0010a\u001a\u00020^R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Lcom/amazon/music/views/library/views/CustomerProfileView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DEFAULT_PADDING", "", "DEFUALT_MARGINS", "DOT", "", "backgroundImage", "Landroid/widget/ImageView;", "backgroundImageContainer", "Landroid/widget/FrameLayout;", "buttonSection", "Landroid/widget/LinearLayout;", "dotSeparator", "Landroid/widget/TextView;", "editIcon", "followIconButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "followerCount", "followerText", "followingCount", "followingText", "overflowIcon", "profileHandle", "profileMainDetails", "profileName", "setupButton", "Landroid/widget/Button;", "shareButton", "statsSection", "applyFontStyle", "", "textView", "styleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "applyMargin", "Landroid/view/View;", "top", "left", "width", "height", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;II)V", "applyPadding", "leftPadding", "rightPadding", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;)V", "configureStyleForBaseButton", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "resId", "Landroid/graphics/drawable/Drawable;", "text", "getBackgroundImageContainer", "getButtonSize", "Lcom/amazon/ui/foundations/styles/ButtonSize;", "getButtonStyle", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "getLeftMargin", "view", "(Landroid/view/View;)Ljava/lang/Integer;", "getLeftPadding", "getRefineCornerRadius", "()Ljava/lang/Integer;", "getRightPadding", "getTopMargin", "hideProfileHandle", "hideProfileStatsLayout", "init", "initMargins", "initTextForTextViews", "initTextStyling", "setEditIconClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setFollowIconClickListener", "setFollowImage", "setFollowerCount", "count", "", "(Ljava/lang/Long;)V", "setFollowersLayoutClickListener", "setFollowingCount", "setFollowingLayoutClickListener", "setMarginsForView", "setProfileHandle", "setProfileName", "setSetUpOverflowClickListener", "setSetUpProfileClickListener", "setShareAttributes", "isSelfView", "", "setShareIconClickListener", "showEditIcon", "show", "showFollowIcon", "showOverflowMenu", "showSetUpButton", "showShareIcon", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerProfileView extends RelativeLayout {
    private final int DEFAULT_PADDING;
    private final int DEFUALT_MARGINS;
    private final String DOT;
    private ImageView backgroundImage;
    private FrameLayout backgroundImageContainer;
    private LinearLayout buttonSection;
    private TextView dotSeparator;
    private ImageView editIcon;
    private BaseButton followIconButton;
    private TextView followerCount;
    private TextView followerText;
    private TextView followingCount;
    private TextView followingText;
    private ImageView overflowIcon;
    private TextView profileHandle;
    private LinearLayout profileMainDetails;
    private TextView profileName;
    private Button setupButton;
    private BaseButton shareButton;
    private LinearLayout statsSection;
    private StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public CustomerProfileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.DOT = "·";
        init(context, styleSheet);
        View viewFindViewById = findViewById(R$id.profile_followers_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.profile_followers_text)");
        this.followerText = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R$id.profile_following_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.profile_following_text)");
        this.followingText = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R$id.profile_following_count);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.profile_following_count)");
        this.followingCount = (TextView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R$id.profile_followers_count);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(R.id.profile_followers_count)");
        this.followerCount = (TextView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R$id.profile_name);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(R.id.profile_name)");
        this.profileName = (TextView) viewFindViewById5;
        View viewFindViewById6 = findViewById(R$id.profile_handle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(R.id.profile_handle)");
        this.profileHandle = (TextView) viewFindViewById6;
        View viewFindViewById7 = findViewById(R$id.setupButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(R.id.setupButton)");
        this.setupButton = (Button) viewFindViewById7;
        View viewFindViewById8 = findViewById(R$id.editIcon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(R.id.editIcon)");
        this.editIcon = (ImageView) viewFindViewById8;
        View viewFindViewById9 = findViewById(R$id.profile_stats);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "findViewById(R.id.profile_stats)");
        this.statsSection = (LinearLayout) viewFindViewById9;
        View viewFindViewById10 = findViewById(R$id.profile_details_section);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "findViewById(R.id.profile_details_section)");
        this.backgroundImageContainer = (FrameLayout) viewFindViewById10;
        View viewFindViewById11 = findViewById(R$id.shareButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "findViewById(R.id.shareButton)");
        this.shareButton = (BaseButton) viewFindViewById11;
        View viewFindViewById12 = findViewById(R$id.followIconButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById12, "findViewById(R.id.followIconButton)");
        this.followIconButton = (BaseButton) viewFindViewById12;
        View viewFindViewById13 = findViewById(R$id.counts_dot_separator);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById13, "findViewById(R.id.counts_dot_separator)");
        this.dotSeparator = (TextView) viewFindViewById13;
        View viewFindViewById14 = findViewById(R$id.profile_main_details);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById14, "findViewById(R.id.profile_main_details)");
        this.profileMainDetails = (LinearLayout) viewFindViewById14;
        View viewFindViewById15 = findViewById(R$id.profile_buttons);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById15, "findViewById(R.id.profile_buttons)");
        this.buttonSection = (LinearLayout) viewFindViewById15;
        View viewFindViewById16 = findViewById(R$id.overflowButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById16, "findViewById(R.id.overflowButton)");
        this.overflowIcon = (ImageView) viewFindViewById16;
        View viewFindViewById17 = findViewById(R$id.profile_background_image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById17, "findViewById(R.id.profile_background_image)");
        this.backgroundImage = (ImageView) viewFindViewById17;
        initTextForTextViews();
        initTextStyling();
        initMargins();
    }

    private final void init(Context context, StyleSheet styleSheet) {
        View.inflate(context, R$layout.dmmviewlibrary_customer_profile_layout, this);
        this.styleSheet = styleSheet;
    }

    /* JADX INFO: renamed from: getBackgroundImageContainer, reason: from getter */
    public final ImageView getBackgroundImage() {
        return this.backgroundImage;
    }

    public final void setEditIconClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.editIcon.setOnClickListener(onClickListener);
    }

    public final void setFollowIconClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.followIconButton.setOnClickListener(onClickListener);
    }

    public final void setShareIconClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.shareButton.setOnClickListener(onClickListener);
    }

    public final void setFollowingLayoutClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.followingText.setOnClickListener(onClickListener);
    }

    public final void setFollowersLayoutClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.followerText.setOnClickListener(onClickListener);
    }

    public final void showSetUpButton(boolean show) {
        if (show) {
            return;
        }
        this.setupButton.setVisibility(8);
    }

    public final void setSetUpProfileClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.setupButton.setOnClickListener(onClickListener);
    }

    public final void showShareIcon(boolean show) {
        this.shareButton.setVisibility(show ? 0 : 8);
    }

    public final void showFollowIcon(boolean show) {
        this.followIconButton.setVisibility(show ? 0 : 8);
    }

    public final void setFollowImage(Drawable resId, String text) {
        BaseButton.StyleBuilder styleBuilderConfigureStyleForBaseButton;
        Intrinsics.checkNotNullParameter(text, "text");
        if (resId == null || (styleBuilderConfigureStyleForBaseButton = configureStyleForBaseButton(resId, text)) == null) {
            return;
        }
        styleBuilderConfigureStyleForBaseButton.applyStyle(this.followIconButton);
    }

    public final void showEditIcon(boolean show) {
        this.editIcon.setVisibility(show ? 0 : 8);
    }

    public final void setProfileName(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.profileName.setVisibility(0);
        this.profileName.setText(text);
    }

    public final void setProfileHandle(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (text.length() > 0) {
            this.profileHandle.setVisibility(0);
            this.profileHandle.setText(Intrinsics.stringPlus("@", text));
        }
    }

    public final void hideProfileStatsLayout() {
        this.statsSection.setVisibility(8);
    }

    public final void hideProfileHandle() {
        this.profileHandle.setVisibility(8);
    }

    public final void setFollowingCount(Long count) {
        String string;
        this.followingCount.setVisibility(0);
        TextView textView = this.followingCount;
        String str = "--";
        if (count != null && (string = count.toString()) != null) {
            str = string;
        }
        textView.setText(str);
    }

    public final void setFollowerCount(Long count) {
        String string;
        this.followerCount.setVisibility(0);
        TextView textView = this.followerCount;
        String str = "--";
        if (count != null && (string = count.toString()) != null) {
            str = string;
        }
        textView.setText(str);
    }

    public final void setShareAttributes(Drawable resId, boolean isSelfView) {
        StyleSheet styleSheet;
        BaseButton.StyleBuilder iconBuilder;
        if (resId == null || (styleSheet = this.styleSheet) == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilderWithIcon = null;
        if (isSelfView) {
            String string = getContext().getString(R$string.dmusic_profile_share);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.dmusic_profile_share)");
            styleBuilderWithIcon = configureStyleForBaseButton(resId, string);
        } else if (styleSheet != null && (iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY)) != null) {
            styleBuilderWithIcon = iconBuilder.withIcon(resId);
        }
        if (styleBuilderWithIcon == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(this.shareButton);
    }

    private final ButtonStyle getButtonStyle() {
        ButtonStyle buttonStyle;
        Integer color;
        StyleSheet styleSheet;
        ButtonStyle buttonStyle2;
        StyleSheet styleSheet2 = this.styleSheet;
        StatefulStyle statefulStyle = (styleSheet2 == null || (buttonStyle = styleSheet2.getButtonStyle(ButtonStyleKey.GLASS_OUTLINE)) == null) ? null : buttonStyle.getStatefulStyle();
        int dimension = (int) getResources().getDimension(R$dimen.spacer_1);
        StyleSheet styleSheet3 = this.styleSheet;
        int iIntValue = -1;
        if (styleSheet3 != null && (color = styleSheet3.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_4)) != null) {
            iIntValue = color.intValue();
        }
        ColorStateList colorStateList$default = ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, iIntValue, null, null, null, 14, null);
        if (statefulStyle == null || (styleSheet = this.styleSheet) == null || (buttonStyle2 = styleSheet.getButtonStyle(ButtonStyleKey.GLASS_OUTLINE)) == null) {
            return null;
        }
        return ButtonStyle.copy$default(buttonStyle2, statefulStyle, null, colorStateList$default, Integer.valueOf(dimension), 2, null);
    }

    public final ButtonSize getButtonSize() {
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getButtonSize(ButtonSizeKey.MEDIUM_WITH_ICON);
    }

    public final Integer getRefineCornerRadius() {
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getCornerSize(CornerSizeKey.ROUND);
    }

    public final BaseButton.StyleBuilder configureStyleForBaseButton(Drawable resId, String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        ButtonStyle buttonStyle = getButtonStyle();
        ButtonSize buttonSize = getButtonSize();
        Integer refineCornerRadius = getRefineCornerRadius();
        if (buttonSize == null || buttonStyle == null || refineCornerRadius == null || resId == null) {
            return null;
        }
        return new BaseButton.StyleBuilder().withSize(buttonSize).withStyle(buttonStyle).withCornerRadius(refineCornerRadius.intValue()).withIcon(resId).withText(text);
    }

    private final void initTextStyling() {
        applyFontStyle(this.profileName, FontStyleKey.HEADLINE_3);
        applyFontStyle(this.profileHandle, FontStyleKey.PRIMARY_GLASS);
        TextView textView = this.followerCount;
        FontStyleKey fontStyleKey = FontStyleKey.TERTIARY_BOLD;
        applyFontStyle(textView, fontStyleKey);
        TextView textView2 = this.followerText;
        FontStyleKey fontStyleKey2 = FontStyleKey.TERTIARY;
        applyFontStyle(textView2, fontStyleKey2);
        applyFontStyle(this.followingCount, fontStyleKey);
        applyFontStyle(this.followingText, fontStyleKey2);
        applyFontStyle(this.dotSeparator, FontStyleKey.SECONDARY_GLASS_BOLD);
    }

    private final void initTextForTextViews() {
        this.followerText.setText(getContext().getString(R$string.dmusic_profile_followers));
        this.followingText.setText(getContext().getString(R$string.dmusic_profile_following));
        this.dotSeparator.setText(this.DOT);
    }

    private final void applyFontStyle(TextView textView, FontStyleKey styleKey) {
        StyleSheet styleSheet = this.styleSheet;
        FontStyle fontStyle = styleSheet == null ? null : styleSheet.getFontStyle(styleKey);
        if (textView == null || fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
    }

    private final void initMargins() {
        setMarginsForView(this.profileMainDetails, -2, -2);
        setMarginsForView(this.profileHandle, -2, -2);
        setMarginsForView(this.statsSection, -1, -2);
        setMarginsForView(this.buttonSection, -1, -2);
        setMarginsForView(this.followerText, -1, -2);
        setMarginsForView(this.followingCount, -1, -2);
    }

    private final void setMarginsForView(View view, int width, int height) {
        applyMargin(view, getTopMargin(view), getLeftMargin(view), width, height);
        applyPadding(view, getLeftPadding(view), getRightPadding(view));
    }

    private final Integer getTopMargin(View view) {
        Integer spacerInPixels;
        if (Intrinsics.areEqual(view, this.profileMainDetails)) {
            StyleSheet styleSheet = this.styleSheet;
            if (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.LARGE)) == null) {
                return null;
            }
            return Integer.valueOf(spacerInPixels.intValue() * 5);
        }
        if (Intrinsics.areEqual(view, this.profileHandle)) {
            StyleSheet styleSheet2 = this.styleSheet;
            if (styleSheet2 == null) {
                return null;
            }
            return styleSheet2.getSpacerInPixels(SpacerKey.MICRO);
        }
        if (Intrinsics.areEqual(view, this.statsSection)) {
            StyleSheet styleSheet3 = this.styleSheet;
            if (styleSheet3 == null) {
                return null;
            }
            return styleSheet3.getSpacerInPixels(SpacerKey.MEDIUM);
        }
        if (!Intrinsics.areEqual(view, this.buttonSection)) {
            return Integer.valueOf(this.DEFUALT_MARGINS);
        }
        StyleSheet styleSheet4 = this.styleSheet;
        if (styleSheet4 == null) {
            return null;
        }
        return styleSheet4.getSpacerInPixels(SpacerKey.MEDIUM);
    }

    private final Integer getLeftMargin(View view) {
        if (!(Intrinsics.areEqual(view, this.profileMainDetails) ? true : Intrinsics.areEqual(view, this.statsSection) ? true : Intrinsics.areEqual(view, this.buttonSection))) {
            return Integer.valueOf(this.DEFUALT_MARGINS);
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
    }

    private final Integer getLeftPadding(View view) {
        if (!(Intrinsics.areEqual(view, this.followerText) ? true : Intrinsics.areEqual(view, this.followingCount))) {
            return Integer.valueOf(this.DEFAULT_PADDING);
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.MICRO);
    }

    private final Integer getRightPadding(View view) {
        if (!(Intrinsics.areEqual(view, this.followerText) ? true : Intrinsics.areEqual(view, this.followingCount))) {
            return Integer.valueOf(this.DEFAULT_PADDING);
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.MICRO);
    }

    private final void applyMargin(View textView, Integer top, Integer left, int width, int height) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    private final void applyPadding(View textView, Integer leftPadding, Integer rightPadding) {
        textView.setPadding(leftPadding == null ? 0 : leftPadding.intValue(), 0, rightPadding == null ? 0 : rightPadding.intValue(), 0);
    }

    public final void showOverflowMenu(int show) {
        this.overflowIcon.setVisibility(show);
    }

    public final void setSetUpOverflowClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.overflowIcon.setOnClickListener(onClickListener);
    }
}
