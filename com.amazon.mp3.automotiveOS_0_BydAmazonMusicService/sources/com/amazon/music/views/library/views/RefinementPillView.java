package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.compose.runtime.ComposerKt;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.enums.RefinementListType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementPillView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\"\u0010\u001b\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!J\u0010\u0010\"\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0016\u0010#\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0012\u0010$\u001a\u00020\u00172\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\u001a\u0010'\u001a\u00020\u00172\b\u0010(\u001a\u0004\u0018\u00010)2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010(\u001a\u00020)H\u0002R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/amazon/music/views/library/views/RefinementPillView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "button", "Lcom/amazon/ui/foundations/views/BaseButton;", "getButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "button$delegate", "Lkotlin/Lazy;", "refinementListType", "Lcom/amazon/music/views/library/models/enums/RefinementListType;", "getRefinementListType", "()Lcom/amazon/music/views/library/models/enums/RefinementListType;", "setRefinementListType", "(Lcom/amazon/music/views/library/models/enums/RefinementListType;)V", "getFocusedActiveStyle", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "getFocusedStyle", "initViewStyling", "", "setId", "id", "", "setView", "content", "Lcom/amazon/music/views/library/models/FilterModel;", "isFocused", "", "onClickListener", "Landroid/view/View$OnClickListener;", "updateButtonStyle", "updateViewFilterModel", "updateViewForFilter", "filterItemModel", "Lcom/amazon/music/views/library/models/FilterItemModel;", "updateViewForFilterCollection", "filterCollectionModel", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "updateViewForSortingFilterCollection", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementPillView extends FrameLayout {

    /* JADX INFO: renamed from: button$delegate, reason: from kotlin metadata */
    private final Lazy button;
    private RefinementListType refinementListType;
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: RefinementPillView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RefinementListType.values().length];
            iArr[RefinementListType.FILTER.ordinal()] = 1;
            iArr[RefinementListType.NAVIGATION.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefinementPillView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.button = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.RefinementPillView$button$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.filter_button);
            }
        });
        this.refinementListType = RefinementListType.FILTER;
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_refinement_pill_item, this);
        initViewStyling();
    }

    public final BaseButton getButton() {
        Object value = this.button.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-button>(...)");
        return (BaseButton) value;
    }

    public final RefinementListType getRefinementListType() {
        return this.refinementListType;
    }

    public final void setRefinementListType(RefinementListType refinementListType) {
        Intrinsics.checkNotNullParameter(refinementListType, "<set-?>");
        this.refinementListType = refinementListType;
    }

    @Override // android.view.View
    public void setId(int id) {
        super.setId(id);
        getButton().setId(id);
    }

    public final void setView(FilterModel content, boolean isFocused, final View.OnClickListener onClickListener) {
        getButton().setIcon(null);
        if (content != null && content.isCollection()) {
            updateViewForFilterCollection(content.getCollectionModel(), isFocused);
        } else {
            if ((content == null || content.isCollection()) ? false : true) {
                updateViewForFilter(content.getItemModel());
            }
        }
        if (onClickListener != null) {
            getButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.RefinementPillView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    onClickListener.onClick(view);
                }
            });
        }
    }

    public final void updateViewFilterModel(FilterModel content, boolean isFocused) {
        Intrinsics.checkNotNullParameter(content, "content");
        if (content.isCollection()) {
            updateViewForFilterCollection(content.getCollectionModel(), isFocused);
        } else {
            updateViewForFilter(content.getItemModel());
        }
    }

    private final void updateViewForFilterCollection(FilterCollectionModel filterCollectionModel, boolean isFocused) {
        Object obj;
        Object next;
        if (filterCollectionModel == null) {
            return;
        }
        updateButtonStyle(isFocused);
        Iterator<T> it = filterCollectionModel.getFilters().iterator();
        while (true) {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            FilterItemModel filterItemModel = (FilterItemModel) next;
            if (filterItemModel != null && filterItemModel.getSelected()) {
                break;
            }
        }
        FilterItemModel filterItemModel2 = (FilterItemModel) next;
        Iterator<T> it2 = filterCollectionModel.getFilters().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next2 = it2.next();
            FilterItemModel filterItemModel3 = (FilterItemModel) next2;
            if (filterItemModel3 != null && filterItemModel3.getIsDefaultFilter()) {
                obj = next2;
                break;
            }
        }
        boolean z = (filterItemModel2 == null || Intrinsics.areEqual(filterItemModel2, (FilterItemModel) obj)) ? false : true;
        getButton().setActive(z);
        getButton().setSelected(z);
        setSelected(z);
        getButton().setText(filterCollectionModel.getTitle());
        if (filterCollectionModel.getIsSortCollection()) {
            updateViewForSortingFilterCollection(filterCollectionModel);
            return;
        }
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_chevron_caretdown);
        if (drawable == null) {
            return;
        }
        getButton().setIconPositionRight();
        getButton().setIcon(drawable);
    }

    private final void updateViewForSortingFilterCollection(FilterCollectionModel filterCollectionModel) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_chevron_caretdown);
        Drawable drawable2 = AppCompatResources.getDrawable(getContext(), R$drawable.ic_other_sort1);
        if (drawable2 == null || drawable == null) {
            return;
        }
        getButton().setIcon(drawable2);
        getButton().setIcon(drawable);
        getButton().getButton().setCompoundDrawables(drawable2, null, drawable, null);
    }

    private final void updateViewForFilter(FilterItemModel filterItemModel) {
        if (filterItemModel == null) {
            return;
        }
        updateButtonStyle(false);
        getButton().setText(filterItemModel.getTitle());
        getButton().setActive(filterItemModel.getSelected());
        getButton().setSelected(filterItemModel.getSelected());
        setSelected(filterItemModel.getSelected());
    }

    private final void initViewStyling() {
        updateButtonStyle(false);
    }

    private final void updateButtonStyle(boolean isFocused) {
        ButtonSize buttonSize;
        Integer cornerSize;
        ButtonStyle buttonStyle;
        Drawable drawable;
        ButtonStyle buttonStyle2;
        int i = WhenMappings.$EnumSwitchMapping$0[this.refinementListType.ordinal()];
        if (i == 1) {
            buttonSize = this.styleSheet.getButtonSize(ButtonSizeKey.MEDIUM_WITH_ICON);
            cornerSize = this.styleSheet.getCornerSize(CornerSizeKey.MEDIUM);
            buttonStyle = this.styleSheet.getButtonStyle(ButtonStyleKey.FOLLOW_OUTLINE);
            drawable = null;
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ButtonSize buttonSize2 = this.styleSheet.getButtonSize(ButtonSizeKey.MEDIUM_WITH_ICON);
            buttonSize = buttonSize2 != null ? buttonSize2.copy((4026 & 1) != 0 ? buttonSize2.height : null, (4026 & 2) != 0 ? buttonSize2.width : null, (4026 & 4) != 0 ? buttonSize2.paddingHorizontal : 0, (4026 & 8) != 0 ? buttonSize2.paddingVertical : null, (4026 & 16) != 0 ? buttonSize2.minWidth : null, (4026 & 32) != 0 ? buttonSize2.cornerRadius : null, (4026 & 64) != 0 ? buttonSize2.textSize : null, (4026 & 128) != 0 ? buttonSize2.textTypeface : null, (4026 & 256) != 0 ? buttonSize2.textAllCaps : null, (4026 & 512) != 0 ? buttonSize2.iconSize : null, (4026 & 1024) != 0 ? buttonSize2.iconPaddingLeft : null, (4026 & 2048) != 0 ? buttonSize2.iconPaddingRight : null) : null;
            cornerSize = this.styleSheet.getCornerSize(CornerSizeKey.ROUND);
            buttonStyle = this.styleSheet.getButtonStyle(ButtonStyleKey.GLASS);
            drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_cancel);
        }
        if (isFocused) {
            buttonStyle = getFocusedStyle(this.styleSheet);
        }
        if (isFocused) {
            buttonStyle2 = getFocusedActiveStyle(this.styleSheet);
        } else {
            buttonStyle2 = this.styleSheet.getButtonStyle(ButtonStyleKey.SOLID);
        }
        if (buttonStyle == null || buttonStyle2 == null || buttonSize == null || cornerSize == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilderWithCornerRadius = new BaseButton.StyleBuilder().withSize(buttonSize).withStyle(buttonStyle).withActiveStyle(buttonStyle2).withCornerRadius(cornerSize.intValue());
        if (drawable != null) {
            styleBuilderWithCornerRadius.withActiveIcon(drawable);
            styleBuilderWithCornerRadius.withIconInOneStateOnly();
        }
        styleBuilderWithCornerRadius.applyStyle(getButton());
    }

    private final ButtonStyle getFocusedStyle(StyleSheet styleSheet) {
        ButtonStyle buttonStyle = styleSheet.getButtonStyle(ButtonStyleKey.FOLLOW_OUTLINE);
        if (buttonStyle == null) {
            return null;
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        return new ButtonStyle(new StatefulStyle(buttonStyle.getStatefulStyle().getFocusedColor(), buttonStyle.getStatefulStyle().getPressedColor(), null, null, buttonStyle.getStatefulStyle().getFocusedBackgroundColor(), buttonStyle.getStatefulStyle().getPressedBackgroundColor(), null, null, ComposerKt.providerMapsKey, null), null, ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, color == null ? -1 : color.intValue(), null, null, null, 14, null), buttonStyle.getInnerBorderSize(), 2, null);
    }

    private final ButtonStyle getFocusedActiveStyle(StyleSheet styleSheet) {
        int iIntValue;
        ButtonStyle buttonStyle = styleSheet.getButtonStyle(ButtonStyleKey.SOLID);
        if (buttonStyle == null) {
            return null;
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_ACCENT);
        if (color != null) {
            iIntValue = color.intValue();
        } else {
            iIntValue = getResources().getColor(R$color.blue_accent, null);
        }
        return new ButtonStyle(new StatefulStyle(buttonStyle.getStatefulStyle().getColor(), buttonStyle.getStatefulStyle().getPressedColor(), null, null, buttonStyle.getStatefulStyle().getFocusedBackgroundColor(), buttonStyle.getStatefulStyle().getPressedBackgroundColor(), null, null, ComposerKt.providerMapsKey, null), null, ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, iIntValue, null, null, null, 14, null), buttonStyle.getInnerBorderSize(), 2, null);
    }
}
