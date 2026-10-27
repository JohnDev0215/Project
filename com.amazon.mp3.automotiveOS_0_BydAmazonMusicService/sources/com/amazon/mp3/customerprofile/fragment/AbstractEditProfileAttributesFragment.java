package com.amazon.mp3.customerprofile.fragment;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.view.BauhausActionBarView;
import com.amazon.mp3.activity.NavigationActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractEditProfileAttributesFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b&\u0018\u0000 R2\u00020\u0001:\u0001RB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0002JI\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000e2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010\u001fJ=\u0010 \u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000e2\b\u0010!\u001a\u0004\u0018\u00010\u001a2\b\u0010\"\u001a\u0004\u0018\u00010\u001a2\b\u0010#\u001a\u0004\u0018\u00010\u001a2\b\u0010$\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020\u0013H\u0002J\b\u0010'\u001a\u00020\u0013H\u0002J\u001c\u0010(\u001a\u00020\u00132\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0004J\u0010\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020/H\u0004J\b\u00100\u001a\u0004\u0018\u00010\u0006J\u0017\u00101\u001a\u0004\u0018\u00010\u001a2\u0006\u00102\u001a\u00020\u000eH\u0002¢\u0006\u0002\u00103J\b\u00104\u001a\u0004\u0018\u00010\nJ\b\u00105\u001a\u0004\u0018\u00010\u0006J\u0017\u00106\u001a\u0004\u0018\u00010\u001a2\u0006\u00102\u001a\u00020\u000eH\u0002¢\u0006\u0002\u00103J\u0017\u00107\u001a\u0004\u0018\u00010\u001a2\u0006\u00102\u001a\u00020\u000eH\u0002¢\u0006\u0002\u00103J\b\u00108\u001a\u00020,H\u0004J\u0017\u00109\u001a\u0004\u0018\u00010\u001a2\u0006\u00102\u001a\u00020\u000eH\u0002¢\u0006\u0002\u00103J\u0017\u0010:\u001a\u0004\u0018\u00010\u001a2\u0006\u00102\u001a\u00020\u000eH\u0002¢\u0006\u0002\u00103J\b\u0010;\u001a\u00020\u0013H\u0004J\b\u0010<\u001a\u00020\u0013H\u0002J\b\u0010=\u001a\u00020\u0013H\u0014J\b\u0010>\u001a\u00020\u0013H\u0002J&\u0010?\u001a\u0004\u0018\u00010\u000e2\u0006\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010C2\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\b\u0010F\u001a\u00020\u0013H\u0016J\b\u0010G\u001a\u00020\u0013H\u0016J\b\u0010H\u001a\u00020\u0013H\u0014J \u0010I\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001aH\u0002J\b\u0010J\u001a\u00020\u0013H\u0014J\u0012\u0010K\u001a\u00020\u00132\b\u0010L\u001a\u0004\u0018\u00010,H\u0004J\u0010\u0010M\u001a\u00020\u00132\u0006\u0010N\u001a\u00020,H\u0004J\b\u0010O\u001a\u00020\u0013H\u0004J\u0010\u0010P\u001a\u00020\u00132\u0006\u0010Q\u001a\u00020/H\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"Lcom/amazon/mp3/customerprofile/fragment/AbstractEditProfileAttributesFragment;", "Landroidx/fragment/app/Fragment;", "()V", "mActionBarView", "Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "mAttributeTitleView", "Landroid/widget/TextView;", "mCancelButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "mEditAttributeView", "Landroid/widget/EditText;", "mEditProfileMessageView", "mErrorMessageText", "mErrorMessageView", "Landroid/view/View;", "mSaveButton", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "addTextChangedListener", "", "applyFontStyle", "textView", "styleKey", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "applyMargin", "top", "", "left", "right", "width", "height", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;II)V", "applyPadding", "leftPadding", "rightPadding", "topPadding", "bottomPadding", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "configureCancelButton", "configureSaveButton", "createTopBar", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "title", "", "enableDisableSaveButton", Constants.ENABLE_DISABLE, "", "getAttributeTitleView", "getBottomPadding", "view", "(Landroid/view/View;)Ljava/lang/Integer;", "getEditAttributeView", "getEditProfileMessageViewText", "getMarginForTextView", "getPaddingForTextView", "getSelectedAttributeValue", "getTopMargin", "getTopPadding", "hideInvalidAttributeErrorMessage", "initMargins", "initTextForTextViews", "initTextStyling", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onStop", "saveProfileAttribute", "setMarginsForView", "setSelectedProfileAttribute", "setTextForEditText", "text", "showInvalidAttributeErrorMessage", "errText", "showTextToast", "updateSaveButtonState", "profileAttributeChanged", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbstractEditProfileAttributesFragment extends Fragment {
    private BauhausActionBarView mActionBarView;
    private TextView mAttributeTitleView;
    private BaseButton mCancelButton;
    private EditText mEditAttributeView;
    private TextView mEditProfileMessageView;
    private TextView mErrorMessageText;
    private View mErrorMessageView;
    private BaseButton mSaveButton;
    private StyleSheet mStyleSheet;

    protected void addTextChangedListener() {
    }

    protected void initTextForTextViews() {
    }

    protected void setSelectedProfileAttribute() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.edit_profile_attribute_layout, container, false);
        this.mAttributeTitleView = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_attribute_text);
        this.mEditAttributeView = (EditText) viewInflate.findViewById(R.id.customer_profile_edit_attribute);
        this.mErrorMessageView = viewInflate.findViewById(R.id.customer_profile_edit_attribute_error_message);
        this.mErrorMessageText = (TextView) viewInflate.findViewById(R.id.customer_profile_edit_attribute_error_text);
        this.mSaveButton = (BaseButton) viewInflate.findViewById(R.id.customer_profile_edit_attribute_save_button);
        this.mCancelButton = (BaseButton) viewInflate.findViewById(R.id.edit_attribute_text_icon);
        this.mEditProfileMessageView = (TextView) viewInflate.findViewById(R.id.customer_profile_attribute_edit_message);
        this.mStyleSheet = StyleSheetProvider.getStyleSheet().getValue();
        initTextForTextViews();
        initTextStyling();
        configureSaveButton();
        configureCancelButton();
        initMargins();
        setSelectedProfileAttribute();
        enableDisableSaveButton(false);
        addTextChangedListener();
        BaseButton baseButton = this.mCancelButton;
        if (baseButton != null) {
            baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AbstractEditProfileAttributesFragment.m788onCreateView$lambda0(this.f$0, view);
                }
            });
        }
        BaseButton baseButton2 = this.mSaveButton;
        if (baseButton2 != null) {
            baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.customerprofile.fragment.AbstractEditProfileAttributesFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AbstractEditProfileAttributesFragment.m789onCreateView$lambda1(this.f$0, view);
                }
            });
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m788onCreateView$lambda0(AbstractEditProfileAttributesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        EditText editText = this$0.mEditAttributeView;
        if (editText != null) {
            editText.setText("");
        }
        this$0.enableDisableSaveButton(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m789onCreateView$lambda1(AbstractEditProfileAttributesFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.saveProfileAttribute();
    }

    protected void saveProfileAttribute() {
        EditText editText = this.mEditAttributeView;
        if (editText == null) {
            return;
        }
        editText.onEditorAction(6);
    }

    /* JADX INFO: renamed from: getAttributeTitleView, reason: from getter */
    public final TextView getMAttributeTitleView() {
        return this.mAttributeTitleView;
    }

    /* JADX INFO: renamed from: getEditProfileMessageViewText, reason: from getter */
    public final TextView getMEditProfileMessageView() {
        return this.mEditProfileMessageView;
    }

    /* JADX INFO: renamed from: getEditAttributeView, reason: from getter */
    public final EditText getMEditAttributeView() {
        return this.mEditAttributeView;
    }

    private final void configureSaveButton() {
        StyleSheet styleSheet;
        BaseButton.StyleBuilder buttonBuilder;
        BaseButton.StyleBuilder styleBuilderWithText;
        BaseButton baseButton = this.mSaveButton;
        if (baseButton == null) {
            return;
        }
        Context context = getContext();
        String string = context == null ? null : context.getString(R.string.dmusic_button_save);
        if (string == null || (styleSheet = this.mStyleSheet) == null || (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID)) == null || (styleBuilderWithText = buttonBuilder.withText(string)) == null) {
            return;
        }
        styleBuilderWithText.applyStyle(baseButton);
    }

    private final void configureCancelButton() {
        BaseButton.StyleBuilder iconBuilder;
        StyleSheet styleSheet = this.mStyleSheet;
        BaseButton.StyleBuilder styleBuilderWithIcon = null;
        if (styleSheet != null && (iconBuilder = styleSheet.getIconBuilder(IconSizeKey.TINY, IconStyleKey.PRIMARY)) != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.cancel_btn);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.cancel_btn)");
            styleBuilderWithIcon = iconBuilder.withIcon(drawable);
        }
        BaseButton baseButton = this.mCancelButton;
        if (baseButton == null || styleBuilderWithIcon == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(baseButton);
    }

    private final void initTextStyling() {
        applyFontStyle(this.mAttributeTitleView, FontStyleKey.INDEX_PRIMARY);
        applyFontStyle(this.mEditProfileMessageView, FontStyleKey.SECONDARY);
        applyFontStyle(this.mEditAttributeView, FontStyleKey.PRIMARY);
    }

    private final void applyFontStyle(TextView textView, FontStyleKey styleKey) {
        StyleSheet styleSheet = this.mStyleSheet;
        FontStyle fontStyle = styleSheet == null ? null : styleSheet.getFontStyle(styleKey);
        if (textView == null || fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
    }

    private final void initMargins() {
        Integer spacerInPixels;
        BaseButton baseButton;
        Integer spacerInPixels2;
        TextView textView = this.mAttributeTitleView;
        if (textView != null) {
            setMarginsForView(textView, -2, -2);
        }
        EditText editText = this.mEditAttributeView;
        if (editText != null) {
            setMarginsForView(editText, -1, -2);
        }
        View view = this.mErrorMessageView;
        if (view != null) {
            setMarginsForView(view, -1, -2);
        }
        TextView textView2 = this.mEditProfileMessageView;
        if (textView2 != null) {
            setMarginsForView(textView2, -2, -2);
        }
        StyleSheet styleSheet = this.mStyleSheet;
        Integer numValueOf = null;
        Integer numValueOf2 = (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.EPIC)) == null) ? null : Integer.valueOf(spacerInPixels.intValue() * 2);
        if (numValueOf2 != null) {
            int iIntValue = numValueOf2.intValue();
            StyleSheet styleSheet2 = this.mStyleSheet;
            if (styleSheet2 != null && (spacerInPixels2 = styleSheet2.getSpacerInPixels(SpacerKey.MEDIUM)) != null) {
                numValueOf = Integer.valueOf(spacerInPixels2.intValue() + iIntValue);
            }
        }
        if (numValueOf == null || (baseButton = this.mSaveButton) == null) {
            return;
        }
        setMarginsForView(baseButton, numValueOf.intValue(), -2);
    }

    private final void setMarginsForView(View view, int width, int height) {
        applyMargin(view, getTopMargin(view), getMarginForTextView(view), getMarginForTextView(view), width, height);
        applyPadding(view, getPaddingForTextView(view), getPaddingForTextView(view), getTopPadding(view), getBottomPadding(view));
    }

    private final void applyMargin(View textView, Integer top, Integer left, Integer right, int width, int height) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : null);
    }

    private final void applyPadding(View textView, Integer leftPadding, Integer rightPadding, Integer topPadding, Integer bottomPadding) {
        textView.setPadding(leftPadding == null ? 0 : leftPadding.intValue(), topPadding == null ? 0 : topPadding.intValue(), rightPadding == null ? 0 : rightPadding.intValue(), bottomPadding != null ? bottomPadding.intValue() : 0);
    }

    private final Integer getTopMargin(View view) {
        Integer spacerInPixels;
        Integer spacerInPixels2;
        if (Intrinsics.areEqual(view, this.mAttributeTitleView)) {
            StyleSheet styleSheet = this.mStyleSheet;
            Integer spacerInPixels3 = styleSheet == null ? null : styleSheet.getSpacerInPixels(SpacerKey.EPIC);
            int dimension = spacerInPixels3 == null ? (int) getResources().getDimension(R.dimen.bauhaus_spacer_epic) : spacerInPixels3.intValue();
            StyleSheet styleSheet2 = this.mStyleSheet;
            if (styleSheet2 == null || (spacerInPixels2 = styleSheet2.getSpacerInPixels(SpacerKey.GIANT)) == null) {
                return null;
            }
            return Integer.valueOf(spacerInPixels2.intValue() + dimension);
        }
        if (Intrinsics.areEqual(view, this.mEditProfileMessageView)) {
            StyleSheet styleSheet3 = this.mStyleSheet;
            Integer spacerInPixels4 = styleSheet3 == null ? null : styleSheet3.getSpacerInPixels(SpacerKey.BASE);
            int dimension2 = spacerInPixels4 == null ? (int) getResources().getDimension(R.dimen.bauhaus_spacer_base) : spacerInPixels4.intValue();
            StyleSheet styleSheet4 = this.mStyleSheet;
            if (styleSheet4 == null || (spacerInPixels = styleSheet4.getSpacerInPixels(SpacerKey.EPIC)) == null) {
                return null;
            }
            return Integer.valueOf(spacerInPixels.intValue() + dimension2);
        }
        if (Intrinsics.areEqual(view, this.mEditAttributeView)) {
            StyleSheet styleSheet5 = this.mStyleSheet;
            if (styleSheet5 == null) {
                return null;
            }
            return styleSheet5.getSpacerInPixels(SpacerKey.LARGE);
        }
        if (!Intrinsics.areEqual(view, this.mSaveButton)) {
            return 0;
        }
        StyleSheet styleSheet6 = this.mStyleSheet;
        if (styleSheet6 == null) {
            return null;
        }
        return styleSheet6.getSpacerInPixels(SpacerKey.MEDIUM);
    }

    private final Integer getMarginForTextView(View view) {
        if (!(Intrinsics.areEqual(view, this.mEditAttributeView) ? true : Intrinsics.areEqual(view, this.mErrorMessageView))) {
            return 0;
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
    }

    private final Integer getPaddingForTextView(View view) {
        if (!(Intrinsics.areEqual(view, this.mAttributeTitleView) ? true : Intrinsics.areEqual(view, this.mEditProfileMessageView))) {
            return 0;
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
    }

    private final Integer getBottomPadding(View view) {
        if (!Intrinsics.areEqual(view, this.mEditAttributeView)) {
            return 0;
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.BASE);
    }

    private final Integer getTopPadding(View view) {
        if (!Intrinsics.areEqual(view, this.mEditAttributeView)) {
            return 0;
        }
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet == null) {
            return null;
        }
        return styleSheet.getSpacerInPixels(SpacerKey.MINI);
    }

    protected final void enableDisableSaveButton(boolean isEnabled) {
        BaseButton baseButton = this.mSaveButton;
        if (baseButton == null) {
            return;
        }
        if (isEnabled) {
            Drawable background = baseButton.getBackground();
            if (background != null) {
                background.setAlpha(255);
            }
        } else {
            Drawable background2 = baseButton.getBackground();
            if (background2 != null) {
                background2.setAlpha(128);
            }
        }
        baseButton.setEnabled(isEnabled);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        BauhausActionBarView bauhausActionBarView = this.mActionBarView;
        if (bauhausActionBarView == null) {
            return;
        }
        bauhausActionBarView.updateActionBarOverlayState(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        BauhausActionBarView bauhausActionBarView = this.mActionBarView;
        if (bauhausActionBarView == null) {
            return;
        }
        bauhausActionBarView.updateActionBarOverlayState(false);
    }

    protected final void createTopBar(FragmentActivity activity, String title) {
        NavigationActivity navigationActivity = activity instanceof NavigationActivity ? (NavigationActivity) activity : null;
        if (navigationActivity == null) {
            return;
        }
        BauhausActionBarView bauhausActionBarView = new BauhausActionBarView(navigationActivity, title);
        this.mActionBarView = bauhausActionBarView;
        navigationActivity.setHeaderView(bauhausActionBarView);
        navigationActivity.requestHomeButtonAsBack();
        BauhausActionBarView bauhausActionBarView2 = this.mActionBarView;
        if (bauhausActionBarView2 == null) {
            return;
        }
        bauhausActionBarView2.updateActionBarOverlayState(true);
    }

    protected final void setTextForEditText(String text) {
        EditText editText = this.mEditAttributeView;
        if (editText == null) {
            return;
        }
        editText.setText(text, TextView.BufferType.EDITABLE);
    }

    protected final String getSelectedAttributeValue() {
        EditText editText = this.mEditAttributeView;
        return String.valueOf(editText == null ? null : editText.getText());
    }

    protected final void updateSaveButtonState(boolean profileAttributeChanged) {
        if (profileAttributeChanged) {
            String selectedAttributeValue = getSelectedAttributeValue();
            if (!(selectedAttributeValue == null || selectedAttributeValue.length() == 0)) {
                enableDisableSaveButton(true);
                return;
            }
        }
        enableDisableSaveButton(false);
    }

    protected final void hideInvalidAttributeErrorMessage() {
        EditText editText = this.mEditAttributeView;
        Drawable background = editText == null ? null : editText.getBackground();
        if (background != null) {
            background.setColorFilter(null);
        }
        View view = this.mErrorMessageView;
        if (view == null) {
            return;
        }
        view.setVisibility(8);
    }

    protected final void showInvalidAttributeErrorMessage(String errText) {
        Drawable background;
        Integer color;
        Intrinsics.checkNotNullParameter(errText, "errText");
        StyleSheet styleSheet = this.mStyleSheet;
        int iIntValue = R.color.error_red;
        if (styleSheet != null && (color = styleSheet.getColor(ColorKey.COLOR_ERROR)) != null) {
            iIntValue = color.intValue();
        }
        EditText editText = this.mEditAttributeView;
        if (editText != null && (background = editText.getBackground()) != null) {
            background.setColorFilter(iIntValue, PorterDuff.Mode.SRC_ATOP);
        }
        TextView textView = this.mErrorMessageText;
        if (textView != null) {
            textView.setText(errText);
        }
        View view = this.mErrorMessageView;
        if (view == null) {
            return;
        }
        view.setVisibility(0);
    }

    protected final void showTextToast() {
        FragmentActivity activity = getActivity();
        Context context = getContext();
        BauhausToastUtils.showTextToast(activity, context == null ? null : context.getString(R.string.dmusic_edit_profile_update_fail_text), 1);
    }
}
