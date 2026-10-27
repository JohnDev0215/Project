package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.models.MultiToggleViewModel;
import com.amazon.music.views.library.models.ToggleButtonModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.MultiToggleView;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: MultiToggleViewBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0003H\u0016J(\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002J\u0010\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0003H\u0002JE\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010$J \u0010%\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u000eH\u0002J\u0018\u0010'\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u000bH\u0002J(\u0010)\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000eH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/music/views/library/binders/MultiToggleViewBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/MultiToggleView;", "Lcom/amazon/music/views/library/models/MultiToggleViewModel;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "buttonBinder", "Lcom/amazon/music/views/library/binders/ToggleButtonBinder;", "onValueChangedListener", "Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/binders/ToggleButtonBinder;Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;)V", "currentActive", "", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createBaseButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/music/views/library/models/ToggleButtonModel;", "index", "lastIndex", "createView", "setButtons", "setMargins", "Landroid/view/View;", "left", "top", "right", "bottom", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setOnClickListener", "baseButton", "setOnValueChangedListener", "toggleOnValueChangeListener", "setupButtonMargins", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MultiToggleViewBinder implements UniversalBinder<MultiToggleView, MultiToggleViewModel> {
    private final ToggleButtonBinder buttonBinder;
    private final Context context;
    private int currentActive;
    private final MultiToggleView.OnValueChangedListener onValueChangedListener;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<MultiToggleViewModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(MultiToggleViewModel.class);
    }

    public MultiToggleViewBinder(Context context, StyleSheet styleSheet, ToggleButtonBinder buttonBinder, MultiToggleView.OnValueChangedListener onValueChangedListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(buttonBinder, "buttonBinder");
        this.context = context;
        this.styleSheet = styleSheet;
        this.buttonBinder = buttonBinder;
        this.onValueChangedListener = onValueChangedListener;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public MultiToggleView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new MultiToggleView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(MultiToggleView view, MultiToggleViewModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        setButtons(view, model);
    }

    private final void setButtons(MultiToggleView view, MultiToggleViewModel model) {
        view.setButtonSize(model.getIconSizeKey(), model.getButtonSizeKey());
        List<? extends BaseButton> listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        MultiToggleView.OnValueChangedListener onValueChangedListener = this.onValueChangedListener;
        if (onValueChangedListener != null) {
            setOnValueChangedListener(view, onValueChangedListener);
        }
        this.currentActive = model.getDefaultActiveButton();
        List<ToggleButtonModel> buttons = model.getButtons();
        if (buttons != null) {
            listEmptyList = new ArrayList<>(CollectionsKt__IterablesKt.collectionSizeOrDefault(buttons, 10));
            int i = 0;
            for (Object obj : buttons) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                listEmptyList.add(createBaseButton(view, (ToggleButtonModel) obj, i, buttons.size() - 1));
                i = i2;
            }
        }
        if (!listEmptyList.isEmpty()) {
            view.addButtons(listEmptyList, model.getLayoutWidth());
        }
    }

    private final void setOnValueChangedListener(MultiToggleView view, MultiToggleView.OnValueChangedListener toggleOnValueChangeListener) {
        view.setOnValueChangedListener(toggleOnValueChangeListener);
    }

    private final void setOnClickListener(final MultiToggleView view, BaseButton baseButton, final int index) {
        this.buttonBinder.setOnClickListener(baseButton, new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.MultiToggleViewBinder$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MultiToggleViewBinder.m2345setOnClickListener$lambda3(this.f$0, index, view, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setOnClickListener$lambda-3, reason: not valid java name */
    public static final void m2345setOnClickListener$lambda3(MultiToggleViewBinder this$0, int i, MultiToggleView view, View view2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        int i2 = this$0.currentActive;
        if (i2 != i) {
            view.playAnimation(i, i2, this$0.buttonBinder);
        }
        this$0.currentActive = i;
    }

    private final BaseButton createBaseButton(MultiToggleView view, ToggleButtonModel model, int index, int lastIndex) {
        BaseButton baseButtonCreateView = this.buttonBinder.createView(this.context);
        this.buttonBinder.bind(baseButtonCreateView, model);
        setOnClickListener(view, baseButtonCreateView, index);
        if (this.currentActive == index) {
            this.buttonBinder.handleStateChange(baseButtonCreateView, true);
        }
        setupButtonMargins(baseButtonCreateView, model, index, lastIndex);
        return baseButtonCreateView;
    }

    private final void setupButtonMargins(BaseButton view, ToggleButtonModel model, int index, int lastIndex) {
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(model.getText() == null ? SpacerKey.MICRO : SpacerKey.NANO);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        view.setPadding(iIntValue, iIntValue, iIntValue, iIntValue);
        if (index == 0) {
            setMargins(view, 0, 0, Integer.valueOf(iIntValue), 0);
        } else if (index == lastIndex) {
            setMargins(view, Integer.valueOf(iIntValue), 0, 0, 0);
        } else {
            setMargins(view, Integer.valueOf(iIntValue), 0, Integer.valueOf(iIntValue), 0);
        }
    }

    private final void setMargins(View view, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
