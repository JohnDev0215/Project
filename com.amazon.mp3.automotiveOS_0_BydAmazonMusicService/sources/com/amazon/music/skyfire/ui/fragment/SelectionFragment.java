package com.amazon.music.skyfire.ui.fragment;

import CoreInterface.v1_0.Method;
import Touch.SelectionTemplateInterface.v1_0.SelectionTemplate;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.SelectionElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.skyfire.Mapper;
import com.amazon.music.skyfire.ui.utils.ViewFinder;
import com.amazon.music.skyfire.ui.utils.ViewFinderKt;
import com.amazon.music.skyfire.ui.widgets.views.SelectionView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import dagger.android.support.AndroidSupportInjection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectionFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 92\u00020\u0001:\u00019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020+H\u0002J\b\u0010-\u001a\u00020+H\u0002J\u0012\u0010.\u001a\u00020+2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J&\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u00192\b\u0010/\u001a\u0004\u0018\u000100H\u0016J\b\u00106\u001a\u00020+H\u0016J\b\u00107\u001a\u00020+H\u0016J\u000e\u00108\u001a\u00020+2\u0006\u0010\u0014\u001a\u00020\u0015R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u00108\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u00020\u00128\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00020\u00198\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u00020\u001f8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001b\u0010$\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b&\u0010'¨\u0006:"}, d2 = {"Lcom/amazon/music/skyfire/ui/fragment/SelectionFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "dispatcherContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "getDispatcherContext", "()Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "setDispatcherContext", "(Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;)V", "elementBindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "getElementBindingProvider", "()Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "setElementBindingProvider", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", "header", "Landroid/widget/TextView;", "primaryButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "secondaryButton", "selectedIndex", "", "selectionChanged", "", "selectionItems", "Landroid/view/ViewGroup;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheetProvider", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "getStyleSheetProvider", "()Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "setStyleSheetProvider", "(Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;)V", "template", "LTouch/SelectionTemplateInterface/v1_0/SelectionTemplate;", "getTemplate", "()LTouch/SelectionTemplateInterface/v1_0/SelectionTemplate;", "template$delegate", "Lkotlin/Lazy;", "bind", "", "bindButtons", "finalizeSelection", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "onDestroy", "onStart", "resetSelection", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SelectionFragment extends BottomSheetDialogFragment {
    public static final String ARG_TEMPLATE = "com.amazon.music.skyfire.ui.Template";
    private DispatcherContext dispatcherContext;
    public ElementBindingProvider elementBindingProvider;

    @ViewFinder.AView
    private TextView header;

    @ViewFinder.AView
    private BaseButton primaryButton;

    @ViewFinder.AView
    private BaseButton secondaryButton;
    private boolean selectionChanged;

    @ViewFinder.AView
    private ViewGroup selectionItems;
    public StyleSheetProvider styleSheetProvider;
    private int selectedIndex = -1;

    /* JADX INFO: renamed from: template$delegate, reason: from kotlin metadata */
    private final Lazy template = LazyKt__LazyJVMKt.lazy(new Function0<SelectionTemplate>() { // from class: com.amazon.music.skyfire.ui.fragment.SelectionFragment$template$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SelectionTemplate invoke() throws JsonProcessingException {
            Mapper mapper = Mapper.INSTANCE;
            Bundle arguments = this.this$0.getArguments();
            String string = arguments == null ? null : arguments.getString(SelectionFragment.ARG_TEMPLATE);
            Intrinsics.checkNotNull(string);
            Intrinsics.checkNotNullExpressionValue(string, "arguments?.getString(ARG_TEMPLATE)!!");
            Object value = mapper.getInstance().readValue(string, (Class<Object>) SelectionTemplate.class);
            Intrinsics.checkNotNull(value);
            return (SelectionTemplate) value;
        }
    });

    public final StyleSheetProvider getStyleSheetProvider() {
        StyleSheetProvider styleSheetProvider = this.styleSheetProvider;
        if (styleSheetProvider != null) {
            return styleSheetProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("styleSheetProvider");
        return null;
    }

    public final void setStyleSheetProvider(StyleSheetProvider styleSheetProvider) {
        Intrinsics.checkNotNullParameter(styleSheetProvider, "<set-?>");
        this.styleSheetProvider = styleSheetProvider;
    }

    public final ElementBindingProvider getElementBindingProvider() {
        ElementBindingProvider elementBindingProvider = this.elementBindingProvider;
        if (elementBindingProvider != null) {
            return elementBindingProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("elementBindingProvider");
        return null;
    }

    public final void setElementBindingProvider(ElementBindingProvider elementBindingProvider) {
        Intrinsics.checkNotNullParameter(elementBindingProvider, "<set-?>");
        this.elementBindingProvider = elementBindingProvider;
    }

    public final DispatcherContext getDispatcherContext() {
        return this.dispatcherContext;
    }

    public final void setDispatcherContext(DispatcherContext dispatcherContext) {
        this.dispatcherContext = dispatcherContext;
    }

    private final StyleSheet getStyleSheet() {
        StyleSheet value = getStyleSheetProvider().getStyleSheet().getValue();
        Intrinsics.checkNotNull(value);
        Intrinsics.checkNotNullExpressionValue(value, "styleSheetProvider.styleSheet.value!!");
        return value;
    }

    private final SelectionTemplate getTemplate() {
        return (SelectionTemplate) this.template.getValue();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        getElementBindingProvider().setContext(requireContext());
        getElementBindingProvider().setDispatcherContext(this.dispatcherContext);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.fragment_selection, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater\n               …ection, container, false)");
        return ViewFinderKt.findViews(viewInflate, this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        getElementBindingProvider().setContext(null);
        getElementBindingProvider().setDispatcherContext(null);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        bind();
    }

    private final void bind() {
        TextView textView = this.header;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("header");
            textView = null;
        }
        ViewKt.applyFontStyle(textView, getStyleSheet(), FontStyleKey.HEADLINE_4);
        TextView textView2 = this.header;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("header");
            textView2 = null;
        }
        textView2.setText(getTemplate().primaryHeader());
        bindButtons();
        ViewGroup viewGroup = this.selectionItems;
        if (viewGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectionItems");
            viewGroup = null;
        }
        viewGroup.removeAllViews();
        List<SelectionElement> listItems = getTemplate().items();
        Intrinsics.checkNotNullExpressionValue(listItems, "template.items()");
        int i = 0;
        final int i2 = 0;
        for (Object obj : listItems) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            final SelectionElement e = (SelectionElement) obj;
            ElementBindingProvider elementBindingProvider = getElementBindingProvider();
            Intrinsics.checkNotNullExpressionValue(e, "e");
            ViewGroup viewGroup2 = this.selectionItems;
            if (viewGroup2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectionItems");
                viewGroup2 = null;
            }
            View viewAttachView = elementBindingProvider.attachView(e, viewGroup2);
            SelectionView selectionView = viewAttachView instanceof SelectionView ? (SelectionView) viewAttachView : null;
            if (selectionView != null) {
                List<SelectionElement> listItems2 = getTemplate().items();
                Intrinsics.checkNotNullExpressionValue(listItems2, "template.items()");
                selectionView.setDividerVisible(i2 != CollectionsKt__CollectionsKt.getLastIndex(listItems2));
            }
            if (selectionView != null) {
                selectionView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.fragment.SelectionFragment$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SelectionFragment.m2197bind$lambda1$lambda0(this.f$0, e, i2, view);
                    }
                });
            }
            i2 = i3;
        }
        List<SelectionElement> listItems3 = getTemplate().items();
        Intrinsics.checkNotNullExpressionValue(listItems3, "template.items()");
        Iterator<SelectionElement> it = listItems3.iterator();
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            } else if (it.next().isSelected()) {
                break;
            } else {
                i++;
            }
        }
        resetSelection(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bind$lambda-1$lambda-0, reason: not valid java name */
    public static final void m2197bind$lambda1$lambda0(SelectionFragment this$0, SelectionElement selectionElement, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DispatcherContext dispatcherContext = this$0.dispatcherContext;
        if (dispatcherContext != null) {
            List<Method> listOnItemSelected = selectionElement.onItemSelected();
            Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "e.onItemSelected()");
            SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
        }
        this$0.selectionChanged = true;
        this$0.resetSelection(i);
    }

    public final void resetSelection(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        ViewGroup viewGroup = this.selectionItems;
        if (viewGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectionItems");
            viewGroup = null;
        }
        int i = 0;
        for (View view : ViewGroupKt.getChildren(viewGroup)) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            view.setActivated(i == selectedIndex);
            i = i2;
        }
    }

    private final void bindButtons() {
        StyleSheet value = getStyleSheetProvider().getStyleSheet().getValue();
        Intrinsics.checkNotNull(value);
        Intrinsics.checkNotNullExpressionValue(value, "styleSheetProvider.styleSheet.value!!");
        StyleSheet styleSheet = value;
        ButtonElement buttonElementPrimaryButton = getTemplate().primaryButton();
        if (buttonElementPrimaryButton == null) {
            throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.TextButtonElement");
        }
        TextButtonElement textButtonElement = (TextButtonElement) buttonElementPrimaryButton;
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.MEDIUM;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            String strText = textButtonElement.text();
            Intrinsics.checkNotNullExpressionValue(strText, "primaryElement.text()");
            BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(strText);
            if (styleBuilderWithText != null) {
                BaseButton baseButton = this.primaryButton;
                if (baseButton == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("primaryButton");
                    baseButton = null;
                }
                styleBuilderWithText.applyStyle(baseButton);
            }
        }
        BaseButton baseButton2 = this.primaryButton;
        if (baseButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("primaryButton");
            baseButton2 = null;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.fragment.SelectionFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectionFragment.m2198bindButtons$lambda4(this.f$0, view);
            }
        });
        DispatcherContext dispatcherContext = this.dispatcherContext;
        if (dispatcherContext != null) {
            List<Method> listOnViewed = textButtonElement.onViewed();
            Intrinsics.checkNotNullExpressionValue(listOnViewed, "primaryElement.onViewed()");
            SkyFireKt.dispatch$default(dispatcherContext, listOnViewed, (String) null, 2, (Object) null);
        }
        ButtonElement buttonElementSecondaryButton = getTemplate().secondaryButton();
        if (buttonElementSecondaryButton == null) {
            throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.TextButtonElement");
        }
        final TextButtonElement textButtonElement2 = (TextButtonElement) buttonElementSecondaryButton;
        BaseButton.StyleBuilder buttonBuilder2 = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.BORDERLESS);
        if (buttonBuilder2 != null) {
            String strText2 = textButtonElement2.text();
            Intrinsics.checkNotNullExpressionValue(strText2, "secondaryElement.text()");
            BaseButton.StyleBuilder styleBuilderWithText2 = buttonBuilder2.withText(strText2);
            if (styleBuilderWithText2 != null) {
                BaseButton baseButton3 = this.secondaryButton;
                if (baseButton3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("secondaryButton");
                    baseButton3 = null;
                }
                styleBuilderWithText2.applyStyle(baseButton3);
            }
        }
        BaseButton baseButton4 = this.secondaryButton;
        if (baseButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("secondaryButton");
            baseButton4 = null;
        }
        baseButton4.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.fragment.SelectionFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectionFragment.m2199bindButtons$lambda5(this.f$0, textButtonElement2, view);
            }
        });
        DispatcherContext dispatcherContext2 = this.dispatcherContext;
        if (dispatcherContext2 == null) {
            return;
        }
        List<Method> listOnViewed2 = textButtonElement2.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed2, "secondaryElement.onViewed()");
        SkyFireKt.dispatch$default(dispatcherContext2, listOnViewed2, (String) null, 2, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindButtons$lambda-4, reason: not valid java name */
    public static final void m2198bindButtons$lambda4(SelectionFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finalizeSelection();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindButtons$lambda-5, reason: not valid java name */
    public static final void m2199bindButtons$lambda5(SelectionFragment this$0, TextButtonElement secondaryElement, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(secondaryElement, "$secondaryElement");
        DispatcherContext dispatcherContext = this$0.dispatcherContext;
        if (dispatcherContext != null) {
            List<Method> listOnItemSelected = secondaryElement.onItemSelected();
            Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "secondaryElement.onItemSelected()");
            SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
        }
        this$0.dismiss();
    }

    private final void finalizeSelection() {
        Object next;
        DispatcherContext dispatcherContext = this.dispatcherContext;
        if (dispatcherContext == null) {
            return;
        }
        if (!this.selectionChanged) {
            List<SelectionElement> listItems = getTemplate().items();
            Intrinsics.checkNotNullExpressionValue(listItems, "template.items()");
            Iterator<T> it = listItems.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (((SelectionElement) next).isSelected()) {
                        break;
                    }
                }
            }
            SelectionElement selectionElement = (SelectionElement) next;
            if (selectionElement == null) {
                return;
            }
            List<Method> listOnItemSelected = selectionElement.onItemSelected();
            Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "selection.onItemSelected()");
            SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
        }
        List<Method> listOnItemSelected2 = getTemplate().primaryButton().onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected2, "template.primaryButton().onItemSelected()");
        SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected2, (String) null, 2, (Object) null);
        dismiss();
    }
}
