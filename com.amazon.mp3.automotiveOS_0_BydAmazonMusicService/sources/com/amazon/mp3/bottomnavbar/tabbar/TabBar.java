package com.amazon.mp3.bottomnavbar.tabbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ViewExtensions;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.podcast.views.ToolTipView;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.Validate;

/* JADX INFO: compiled from: TabBar.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 [2\u00020\u0001:\u0001[B\u001b\b\u0016\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\b\u0010X\u001a\u0004\u0018\u00010W¢\u0006\u0004\bY\u0010ZJ\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u0006\u001a\u00020\u0004J\u001e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tJ\u0016\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\tJ\u0016\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0010J\u0015\u0010\u0014\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0016\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0012J\u0006\u0010\u0018\u001a\u00020\u0004J\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0012J\u0010\u0010 \u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u001eH\u0004J\u0006\u0010!\u001a\u00020\u0004J\u0006\u0010\"\u001a\u00020\u0004J\u0010\u0010#\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010$\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002R*\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0%8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0018\u0010-\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u001c\u00100\u001a\b\u0012\u0004\u0012\u00020/0%8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b0\u00101R\u001c\u00103\u001a\b\u0012\u0004\u0012\u0002020%8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b3\u00104R*\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001050%8\u0004@\u0004X\u0084.¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0016\u0010=\u001a\u00020<8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010?\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010@R\u0018\u0010A\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u0010@R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010BR\u001b\u0010G\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010FR\u001b\u0010J\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bH\u0010D\u001a\u0004\bI\u0010FR\u001b\u0010M\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bK\u0010D\u001a\u0004\bL\u0010FR\u001b\u0010P\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u0010D\u001a\u0004\bO\u0010FR\u001b\u0010S\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010D\u001a\u0004\bR\u0010FR\u001b\u0010V\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bT\u0010D\u001a\u0004\bU\u0010F¨\u0006\\"}, d2 = {"Lcom/amazon/mp3/bottomnavbar/tabbar/TabBar;", "Landroid/widget/RelativeLayout;", "Lcom/amazon/mp3/bottomnavbar/tabbar/Tab;", "tab", "", "selectTab", "selectPreviousTab", "Lcom/amazon/mp3/bottomnavbar/tabbar/TabListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "index", "setupTabButton", "", "toolTip", "tabIndex", "showToolTip", "Landroid/view/View;", "view", "", "kotlin.jvm.PlatformType", "getToolTipDismissState", "()Ljava/lang/Boolean;", Constants.ENABLE_DISABLE, "setTabEnabled", "setupToolTipView", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "setStyle", "largeScreen", "updateView", "Landroid/content/Context;", "context", "init", "disableAlexaTab", "disablePersistentUpsellTab", "recordOnClickMetric", "setStyleAtIndex", "", "Landroid/widget/LinearLayout;", "buttons", "[Landroid/widget/LinearLayout;", "getButtons", "()[Landroid/widget/LinearLayout;", "setButtons", "([Landroid/widget/LinearLayout;)V", "activeButton", "Landroid/widget/LinearLayout;", "Landroid/widget/ImageView;", "buttonImages", "[Landroid/widget/ImageView;", "Landroid/widget/TextView;", "buttonTexts", "[Landroid/widget/TextView;", "Landroid/widget/Space;", "paddings", "[Landroid/widget/Space;", "getPaddings", "()[Landroid/widget/Space;", "setPaddings", "([Landroid/widget/Space;)V", "Lcom/amazon/podcast/views/ToolTipView;", "toolTipView", "Lcom/amazon/podcast/views/ToolTipView;", "currentTab", "Lcom/amazon/mp3/bottomnavbar/tabbar/Tab;", "previousTab", "Lcom/amazon/music/views/library/styles/StyleSheet;", "microSpace$delegate", "Lkotlin/Lazy;", "getMicroSpace", "()I", "microSpace", "miniSpace$delegate", "getMiniSpace", "miniSpace", "smallSpace$delegate", "getSmallSpace", "smallSpace", "mediumSpace$delegate", "getMediumSpace", "mediumSpace", "selectedColor$delegate", "getSelectedColor", "selectedColor", "unselectedColor$delegate", "getUnselectedColor", "unselectedColor", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class TabBar extends RelativeLayout {
    private static final String TAG = TabBar.class.getSimpleName();
    private LinearLayout activeButton;
    private ImageView[] buttonImages;
    private TextView[] buttonTexts;
    protected LinearLayout[] buttons;
    private Tab currentTab;

    /* JADX INFO: renamed from: mediumSpace$delegate, reason: from kotlin metadata */
    private final Lazy mediumSpace;

    /* JADX INFO: renamed from: microSpace$delegate, reason: from kotlin metadata */
    private final Lazy microSpace;

    /* JADX INFO: renamed from: miniSpace$delegate, reason: from kotlin metadata */
    private final Lazy miniSpace;
    protected Space[] paddings;
    private Tab previousTab;

    /* JADX INFO: renamed from: selectedColor$delegate, reason: from kotlin metadata */
    private final Lazy selectedColor;

    /* JADX INFO: renamed from: smallSpace$delegate, reason: from kotlin metadata */
    private final Lazy smallSpace;
    private StyleSheet styleSheet;
    private ToolTipView toolTipView;

    /* JADX INFO: renamed from: unselectedColor$delegate, reason: from kotlin metadata */
    private final Lazy unselectedColor;

    protected final LinearLayout[] getButtons() {
        LinearLayout[] linearLayoutArr = this.buttons;
        if (linearLayoutArr != null) {
            return linearLayoutArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("buttons");
        return null;
    }

    protected final void setButtons(LinearLayout[] linearLayoutArr) {
        Intrinsics.checkNotNullParameter(linearLayoutArr, "<set-?>");
        this.buttons = linearLayoutArr;
    }

    protected final Space[] getPaddings() {
        Space[] spaceArr = this.paddings;
        if (spaceArr != null) {
            return spaceArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("paddings");
        return null;
    }

    protected final void setPaddings(Space[] spaceArr) {
        Intrinsics.checkNotNullParameter(spaceArr, "<set-?>");
        this.paddings = spaceArr;
    }

    private final int getMicroSpace() {
        return ((Number) this.microSpace.getValue()).intValue();
    }

    private final int getMiniSpace() {
        return ((Number) this.miniSpace.getValue()).intValue();
    }

    private final int getSmallSpace() {
        return ((Number) this.smallSpace.getValue()).intValue();
    }

    private final int getMediumSpace() {
        return ((Number) this.mediumSpace.getValue()).intValue();
    }

    private final int getSelectedColor() {
        return ((Number) this.selectedColor.getValue()).intValue();
    }

    private final int getUnselectedColor() {
        return ((Number) this.unselectedColor.getValue()).intValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.microSpace = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar$microSpace$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Integer spacerInPixels;
                StyleSheet styleSheet = this.this$0.styleSheet;
                int iIntValue = 0;
                if (styleSheet != null && (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MICRO)) != null) {
                    iIntValue = spacerInPixels.intValue();
                }
                return Integer.valueOf(iIntValue);
            }
        });
        this.miniSpace = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar$miniSpace$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Integer spacerInPixels;
                StyleSheet styleSheet = this.this$0.styleSheet;
                int iIntValue = 0;
                if (styleSheet != null && (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MINI)) != null) {
                    iIntValue = spacerInPixels.intValue();
                }
                return Integer.valueOf(iIntValue);
            }
        });
        this.smallSpace = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar$smallSpace$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Integer spacerInPixels;
                StyleSheet styleSheet = this.this$0.styleSheet;
                int iIntValue = 0;
                if (styleSheet != null && (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.SMALL)) != null) {
                    iIntValue = spacerInPixels.intValue();
                }
                return Integer.valueOf(iIntValue);
            }
        });
        this.mediumSpace = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar$mediumSpace$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Integer spacerInPixels;
                StyleSheet styleSheet = this.this$0.styleSheet;
                int iIntValue = 0;
                if (styleSheet != null && (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM)) != null) {
                    iIntValue = spacerInPixels.intValue();
                }
                return Integer.valueOf(iIntValue);
            }
        });
        this.selectedColor = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar$selectedColor$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                StyleSheet styleSheet = this.this$0.styleSheet;
                Integer color = styleSheet == null ? null : styleSheet.getColor(ColorKey.COLOR_ACCENT);
                return Integer.valueOf(color == null ? this.this$0.getResources().getColor(R.color.cyan_accent) : color.intValue());
            }
        });
        this.unselectedColor = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar$unselectedColor$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                StyleSheet styleSheet = this.this$0.styleSheet;
                Integer color = styleSheet == null ? null : styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_4);
                return Integer.valueOf(color == null ? this.this$0.getResources().getColor(R.color.primary_glass_4) : color.intValue());
            }
        });
        init(context);
    }

    public final void selectTab(Tab tab) {
        LinearLayout linearLayout = this.activeButton;
        if (linearLayout != null) {
            linearLayout.setSelected(false);
            if (this.currentTab != null) {
                Context context = getContext();
                Resources resources = getResources();
                Tab tab2 = this.currentTab;
                Intrinsics.checkNotNull(tab2);
                linearLayout.setContentDescription(context.getString(R.string.content_description_button_suffix, resources.getText(tab2.getTabTextResource())));
            }
        }
        if (tab == null || tab.getTabPosition() < 0 || tab.getTabPosition() >= getButtons().length) {
            this.currentTab = null;
            return;
        }
        this.previousTab = this.currentTab;
        this.currentTab = tab;
        LinearLayout linearLayout2 = getButtons()[tab.getTabPosition()];
        this.activeButton = linearLayout2;
        if (linearLayout2 != null) {
            linearLayout2.setSelected(true);
        }
        LinearLayout linearLayout3 = this.activeButton;
        if (linearLayout3 == null) {
            return;
        }
        linearLayout3.setContentDescription(getContext().getString(R.string.content_description_button_selected_suffix, getResources().getText(tab.getTabTextResource())));
    }

    public final void selectPreviousTab() {
        selectTab(this.previousTab);
        this.previousTab = null;
    }

    public final void setupTabButton(final Tab tab, final TabListener listener, int index) {
        Intrinsics.checkNotNullParameter(tab, "tab");
        Intrinsics.checkNotNullParameter(listener, "listener");
        Validate.notNull(tab);
        Validate.notNull(listener);
        if (index < 0 || index >= getButtons().length || getButtons()[index] == null) {
            Log.debug(TAG, "index parameter is not valid");
            return;
        }
        tab.setTabPosition(index);
        LinearLayout linearLayout = getButtons()[index];
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        ImageView[] imageViewArr = this.buttonImages;
        TextView[] textViewArr = null;
        if (imageViewArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonImages");
            imageViewArr = null;
        }
        imageViewArr[index].setImageResource(tab.getTabImageResource());
        TextView[] textViewArr2 = this.buttonTexts;
        if (textViewArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonTexts");
        } else {
            textViewArr = textViewArr2;
        }
        textViewArr[index].setText(getResources().getText(tab.getTabTextResource()));
        setStyleAtIndex(index);
        LinearLayout linearLayout2 = getButtons()[index];
        if (linearLayout2 != null) {
            linearLayout2.setContentDescription(getContext().getString(R.string.content_description_button_suffix, getResources().getText(tab.getTabTextResource())));
        }
        LinearLayout linearLayout3 = getButtons()[index];
        if (linearLayout3 == null) {
            return;
        }
        linearLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TabBar.m429setupTabButton$lambda0(listener, this, tab, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupTabButton$lambda-0, reason: not valid java name */
    public static final void m429setupTabButton$lambda0(TabListener listener, TabBar this$0, Tab tab, View view) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tab, "$tab");
        if (listener.onTabSelected()) {
            this$0.selectTab(tab);
        }
        this$0.recordOnClickMetric(tab);
    }

    public final void showToolTip(String toolTip, int tabIndex) {
        Intrinsics.checkNotNullParameter(toolTip, "toolTip");
        ToolTipView toolTipView = this.toolTipView;
        ToolTipView toolTipView2 = null;
        if (toolTipView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolTipView");
            toolTipView = null;
        }
        toolTipView.dismissToolTip();
        ToolTipView toolTipView3 = this.toolTipView;
        if (toolTipView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolTipView");
        } else {
            toolTipView2 = toolTipView3;
        }
        toolTipView2.bind(toolTip);
        final LinearLayout linearLayout = getButtons()[tabIndex];
        if (linearLayout == null) {
            return;
        }
        ViewExtensions.waitForLayout(this, new Function0<Unit>() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar$showToolTip$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ToolTipView toolTipView4 = this.this$0.toolTipView;
                if (toolTipView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toolTipView");
                    toolTipView4 = null;
                }
                toolTipView4.showToolTipForView(linearLayout);
            }
        });
    }

    public final void showToolTip(String toolTip, final View view) {
        Intrinsics.checkNotNullParameter(toolTip, "toolTip");
        Intrinsics.checkNotNullParameter(view, "view");
        ToolTipView toolTipView = this.toolTipView;
        ToolTipView toolTipView2 = null;
        if (toolTipView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolTipView");
            toolTipView = null;
        }
        toolTipView.dismissToolTip();
        ToolTipView toolTipView3 = this.toolTipView;
        if (toolTipView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolTipView");
        } else {
            toolTipView2 = toolTipView3;
        }
        toolTipView2.bind(toolTip);
        ViewExtensions.waitForLayout(this, new Function0<Unit>() { // from class: com.amazon.mp3.bottomnavbar.tabbar.TabBar.showToolTip.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ToolTipView toolTipView4 = TabBar.this.toolTipView;
                if (toolTipView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("toolTipView");
                    toolTipView4 = null;
                }
                toolTipView4.showToolTipForView(view);
            }
        });
    }

    public final Boolean getToolTipDismissState() {
        ToolTipView toolTipView = this.toolTipView;
        if (toolTipView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolTipView");
            toolTipView = null;
        }
        return toolTipView.isDismissed();
    }

    public final void setTabEnabled(Tab tab, boolean isEnabled) {
        LinearLayout linearLayout;
        Intrinsics.checkNotNullParameter(tab, "tab");
        int tabPosition = tab.getTabPosition();
        if (tabPosition < 0 || tabPosition >= getButtons().length || (linearLayout = getButtons()[tab.getTabPosition()]) == null) {
            return;
        }
        linearLayout.setVisibility(isEnabled ? 0 : 8);
    }

    public final void setupToolTipView() {
        LinearLayout linearLayout = (LinearLayout) ArraysKt___ArraysKt.getOrNull(getButtons(), Tab.STORE.getTabPosition());
        boolean z = false;
        if (!(linearLayout != null && linearLayout.getVisibility() == 0)) {
            LinearLayout linearLayout2 = (LinearLayout) ArraysKt___ArraysKt.getOrNull(getButtons(), Tab.ALEXA.getTabPosition());
            if (!(linearLayout2 != null && linearLayout2.getVisibility() == 0)) {
                LinearLayout linearLayout3 = (LinearLayout) ArraysKt___ArraysKt.getOrNull(getButtons(), Tab.UPGRADE.getTabPosition());
                if (!(linearLayout3 != null && linearLayout3.getVisibility() == 0)) {
                    z = true;
                }
            }
        }
        this.toolTipView = new ToolTipView(getContext(), ToolTipView.ToolTipOrientation.TOP, true, z);
    }

    public final void setStyle(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        TextView[] textViewArr = this.buttonTexts;
        if (textViewArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonTexts");
            textViewArr = null;
        }
        int length = textViewArr.length;
        int i = 0;
        while (i < length) {
            TextView textView = textViewArr[i];
            i++;
            FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.INDEX);
            if (fontStyle != null) {
                FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
            }
            textView.setTextColor(new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[]{android.R.attr.state_enabled}}, new int[]{getSelectedColor(), getUnselectedColor()}));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateView(boolean largeScreen) {
        LinearLayout[] buttons = getButtons();
        int length = buttons.length;
        int i = 0;
        while (i < length) {
            LinearLayout linearLayout = buttons[i];
            i++;
            if (linearLayout != null) {
                linearLayout.setOrientation(!largeScreen ? 1 : 0);
                linearLayout.setPadding(0, largeScreen ? 0 : getMicroSpace(), 0, 0);
            }
        }
        boolean z = true;
        int length2 = getButtons().length - 1;
        ImageView[] imageViewArr = null;
        if (length2 >= 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                LinearLayout linearLayout2 = getButtons()[i2];
                if ((linearLayout2 != null && linearLayout2.getVisibility() == 8) == false) {
                    TextView[] textViewArr = this.buttonTexts;
                    if (textViewArr == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("buttonTexts");
                        textViewArr = null;
                    }
                    int measuredWidth = textViewArr[i2].getMeasuredWidth();
                    LinearLayout linearLayout3 = getButtons()[i2];
                    if (measuredWidth > (linearLayout3 == null ? 0 : linearLayout3.getMeasuredWidth()) - getMiniSpace()) {
                        break;
                    }
                }
                if (i2 == length2) {
                    break;
                } else {
                    i2 = i3;
                }
            }
            z = false;
        } else {
            z = false;
        }
        TextView[] textViewArr2 = this.buttonTexts;
        if (textViewArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonTexts");
            textViewArr2 = null;
        }
        int length3 = textViewArr2.length;
        int i4 = 0;
        while (i4 < length3) {
            TextView textView = textViewArr2[i4];
            i4++;
            int miniSpace = largeScreen ? getMiniSpace() : 0;
            int microSpace = largeScreen ? 0 : getMicroSpace();
            textView.setPadding(miniSpace, microSpace, 0, microSpace);
            if (z) {
                textView.setTextSize(2, 10.0f);
            }
        }
        ImageView[] imageViewArr2 = this.buttonImages;
        if (imageViewArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonImages");
        } else {
            imageViewArr = imageViewArr2;
        }
        int length4 = imageViewArr.length;
        int i5 = 0;
        while (i5 < length4) {
            ImageView imageView = imageViewArr[i5];
            i5++;
            int mediumSpace = largeScreen ? 0 : getMediumSpace();
            imageView.setPadding(mediumSpace, largeScreen ? getSmallSpace() : getMicroSpace(), mediumSpace, largeScreen ? getSmallSpace() : 0);
        }
    }

    protected final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.LayoutInflater");
        }
        ((LayoutInflater) systemService).inflate(R.layout.tab_bar, (ViewGroup) this, true);
        LinearLayout[] linearLayoutArr = new LinearLayout[5];
        View viewFindViewById = findViewById(R.id.tab_bar_tab_one);
        if (viewFindViewById == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        linearLayoutArr[0] = (LinearLayout) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.tab_bar_tab_two);
        if (viewFindViewById2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        linearLayoutArr[1] = (LinearLayout) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.tab_bar_tab_three);
        if (viewFindViewById3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        linearLayoutArr[2] = (LinearLayout) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.tab_bar_tab_four);
        if (viewFindViewById4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        linearLayoutArr[3] = (LinearLayout) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.tab_bar_tab_five);
        if (viewFindViewById5 != null) {
            linearLayoutArr[4] = (LinearLayout) viewFindViewById5;
            setButtons(linearLayoutArr);
            ImageView[] imageViewArr = new ImageView[5];
            View viewFindViewById6 = findViewById(R.id.tab_bar_image_one);
            if (viewFindViewById6 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
            }
            imageViewArr[0] = (ImageView) viewFindViewById6;
            View viewFindViewById7 = findViewById(R.id.tab_bar_image_two);
            if (viewFindViewById7 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
            }
            imageViewArr[1] = (ImageView) viewFindViewById7;
            View viewFindViewById8 = findViewById(R.id.tab_bar_image_three);
            if (viewFindViewById8 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
            }
            imageViewArr[2] = (ImageView) viewFindViewById8;
            View viewFindViewById9 = findViewById(R.id.tab_bar_image_four);
            if (viewFindViewById9 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
            }
            imageViewArr[3] = (ImageView) viewFindViewById9;
            View viewFindViewById10 = findViewById(R.id.tab_bar_image_five);
            if (viewFindViewById10 != null) {
                imageViewArr[4] = (ImageView) viewFindViewById10;
                this.buttonImages = imageViewArr;
                TextView[] textViewArr = new TextView[5];
                View viewFindViewById11 = findViewById(R.id.tab_bar_text_one);
                if (viewFindViewById11 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                }
                textViewArr[0] = (TextView) viewFindViewById11;
                View viewFindViewById12 = findViewById(R.id.tab_bar_text_two);
                if (viewFindViewById12 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                }
                textViewArr[1] = (TextView) viewFindViewById12;
                View viewFindViewById13 = findViewById(R.id.tab_bar_text_three);
                if (viewFindViewById13 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                }
                textViewArr[2] = (TextView) viewFindViewById13;
                View viewFindViewById14 = findViewById(R.id.tab_bar_text_four);
                if (viewFindViewById14 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                }
                textViewArr[3] = (TextView) viewFindViewById14;
                View viewFindViewById15 = findViewById(R.id.tab_bar_text_five);
                if (viewFindViewById15 != null) {
                    textViewArr[4] = (TextView) viewFindViewById15;
                    this.buttonTexts = textViewArr;
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
    }

    private final void recordOnClickMetric(Tab tab) {
        UserInteractionAppEvent.builder(tab.getMetricsKey()).withInteractionType(InteractionType.TAP).publish();
    }

    private final void setStyleAtIndex(int index) {
        FontStyle fontStyle;
        StyleSheet styleSheet = this.styleSheet;
        TextView[] textViewArr = null;
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.INDEX)) != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            TextView[] textViewArr2 = this.buttonTexts;
            if (textViewArr2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("buttonTexts");
                textViewArr2 = null;
            }
            fontUtil.applyFontStyle(textViewArr2[index], fontStyle);
        }
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[]{android.R.attr.state_enabled}}, new int[]{getSelectedColor(), getUnselectedColor()});
        TextView[] textViewArr3 = this.buttonTexts;
        if (textViewArr3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttonTexts");
        } else {
            textViewArr = textViewArr3;
        }
        textViewArr[index].setTextColor(colorStateList);
    }

    public final void disableAlexaTab() {
        LinearLayout linearLayout;
        if (getButtons()[getButtons().length - 2] == null || (linearLayout = getButtons()[getButtons().length - 2]) == null) {
            return;
        }
        linearLayout.setVisibility(8);
    }

    public final void disablePersistentUpsellTab() {
        LinearLayout linearLayout;
        if (getButtons()[getButtons().length - 1] != null) {
            TextView[] textViewArr = this.buttonTexts;
            if (textViewArr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("buttonTexts");
                textViewArr = null;
            }
            if (!Intrinsics.areEqual(textViewArr[getButtons().length - 1].getText(), getResources().getText(Tab.UPGRADE.getTabTextResource())) || (linearLayout = getButtons()[getButtons().length - 1]) == null) {
                return;
            }
            linearLayout.setVisibility(8);
        }
    }
}
