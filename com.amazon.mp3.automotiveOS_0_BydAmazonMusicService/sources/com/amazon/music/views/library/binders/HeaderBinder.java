package com.amazon.music.views.library.binders;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.music.views.library.models.ButtonModel;
import com.amazon.music.views.library.models.HeaderModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.HeaderView;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: HeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u0016H\u0002J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0003H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/views/library/binders/HeaderBinder;", "Lcom/amazon/music/views/library/binders/UniversalBinder;", "Lcom/amazon/music/views/library/views/HeaderView;", "Lcom/amazon/music/views/library/models/HeaderModel;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "buttonBinder", "Lcom/amazon/music/views/library/binders/ButtonBinder;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/binders/ButtonBinder;Landroidx/lifecycle/LifecycleOwner;)V", "modelClass", "Lkotlin/reflect/KClass;", "getModelClass", "()Lkotlin/reflect/KClass;", "bind", "", "view", "model", "createBaseButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "Lcom/amazon/music/views/library/models/ButtonModel;", "createView", "observeModelChanges", "setLayout", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HeaderBinder implements UniversalBinder<HeaderView, HeaderModel> {
    private final ButtonBinder buttonBinder;
    private final Context context;
    private final LifecycleOwner lifecycleOwner;
    private final StyleSheet styleSheet;

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<HeaderModel> getModelClass() {
        return Reflection.getOrCreateKotlinClass(HeaderModel.class);
    }

    public HeaderBinder(Context context, StyleSheet styleSheet, ButtonBinder buttonBinder, LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(buttonBinder, "buttonBinder");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        this.context = context;
        this.styleSheet = styleSheet;
        this.buttonBinder = buttonBinder;
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public HeaderView createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new HeaderView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(HeaderView view, HeaderModel model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        setLayout(view, model);
        String labelText = model.getLabelText();
        if (labelText != null) {
            view.setLabel(labelText, model.getLabelFontStyle());
        }
        String headerText = model.getHeaderText();
        if (headerText != null) {
            view.setHeadline(headerText, model.getHeaderFontStyle());
        }
        String primaryText = model.getPrimaryText();
        if (primaryText != null) {
            view.setPrimaryText(primaryText);
        }
        String secondaryText = model.getSecondaryText();
        if (secondaryText != null) {
            view.setSecondaryText(secondaryText);
        }
        String tertiaryText = model.getTertiaryText();
        if (tertiaryText != null) {
            view.setTertiaryText(tertiaryText);
        }
        List<List<ButtonModel>> actionButtonRows = model.getActionButtonRows();
        if (actionButtonRows != null && (!actionButtonRows.isEmpty())) {
            List<ButtonModel> list = actionButtonRows.get(0);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(createBaseButton((ButtonModel) it.next()));
            }
            view.addButtonRow1(arrayList);
            if (actionButtonRows.size() > 1) {
                List<ButtonModel> list2 = actionButtonRows.get(1);
                ArrayList arrayList2 = new ArrayList();
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    BaseButton baseButtonCreateBaseButton = createBaseButton((ButtonModel) it2.next());
                    if (baseButtonCreateBaseButton != null) {
                        arrayList2.add(baseButtonCreateBaseButton);
                    }
                }
                view.addButtonRow2(arrayList2);
            }
        }
    }

    private final void setLayout(HeaderView view, HeaderModel model) {
        GridSize gridSize = this.styleSheet.getGridSize(this.context);
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        GridSize gridSize2 = this.styleSheet.getGridSize(this.context);
        int columnSpace = marginSpace + (gridSize2 == null ? 0 : gridSize2.getColumnSpace());
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
        Integer spacerInPixels3 = this.styleSheet.getSpacerInPixels(SpacerKey.EPIC);
        int iIntValue3 = (spacerInPixels3 == null ? 0 : spacerInPixels3.intValue()) * 2;
        if (model.getImageUrl() == null) {
            iIntValue3 = iIntValue2;
        }
        view.setPadding(columnSpace, iIntValue3, columnSpace, iIntValue2);
        if (model.getLabelText() != null) {
            view.setLabelPadding(0, 0, 0, Integer.valueOf(iIntValue));
        }
        if (model.getHeaderText() != null) {
            view.setHeaderTextPadding(0, 0, 0, Integer.valueOf((model.getPrimaryText() == null && model.getSecondaryText() == null && model.getTertiaryText() == null) ? 0 : iIntValue));
        }
        if (model.getPrimaryText() != null) {
            view.setPrimaryTextPadding(0, 0, 0, Integer.valueOf((model.getSecondaryText() == null && model.getTertiaryText() == null) ? 0 : iIntValue));
        }
        if (model.getSecondaryText() == null) {
            return;
        }
        view.setSecondaryTextPadding(0, 0, 0, Integer.valueOf(model.getTertiaryText() != null ? iIntValue : 0));
    }

    private final BaseButton createBaseButton(ButtonModel model) {
        BaseButton baseButtonCreateView = this.buttonBinder.createView(this.context);
        this.buttonBinder.bind(baseButtonCreateView, model);
        observeModelChanges(model, baseButtonCreateView);
        return baseButtonCreateView;
    }

    private final void observeModelChanges(final ButtonModel model, final BaseButton view) {
        model.getChangedObservable$DMMViewLibrary_release().observe(this.lifecycleOwner, new Observer() { // from class: com.amazon.music.views.library.binders.HeaderBinder$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                HeaderBinder.m2318observeModelChanges$lambda12(this.f$0, view, model, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeModelChanges$lambda-12, reason: not valid java name */
    public static final void m2318observeModelChanges$lambda12(HeaderBinder this$0, BaseButton view, ButtonModel model, Integer payload) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(model, "$model");
        ButtonBinder buttonBinder = this$0.buttonBinder;
        Intrinsics.checkNotNullExpressionValue(payload, "payload");
        buttonBinder.handleStateChange(view, model, (Object) payload);
    }
}
