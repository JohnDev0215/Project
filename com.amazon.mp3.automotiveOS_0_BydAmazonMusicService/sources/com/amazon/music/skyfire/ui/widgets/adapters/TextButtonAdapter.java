package com.amazon.music.skyfire.ui.widgets.adapters;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextButtonAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fJ\u0006\u0010\u0010\u001a\u00020\rJ\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\u0018\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0018\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0018\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u0018\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u001a\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/TextButtonAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/skyfire/ui/widgets/adapters/TextButtonAdapter$TextButtonViewHolder;", "bindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/TextButtonElement;", "selectedItemPositions", "", "", "addItems", "", "newItems", "", "clear", "dispatchMethods", "viewHolder", "position", "getItemCount", "onBindViewHolder", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "selectItem", "setButtonStyle", "updateButtonStyle", "buttonStyle", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "TextButtonViewHolder", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TextButtonAdapter extends RecyclerView.Adapter<TextButtonViewHolder> {
    private final ElementBindingProvider bindingProvider;
    private List<TextButtonElement> items;
    private Set<Integer> selectedItemPositions;

    public TextButtonAdapter(ElementBindingProvider bindingProvider) {
        Intrinsics.checkNotNullParameter(bindingProvider, "bindingProvider");
        this.bindingProvider = bindingProvider;
        this.items = new ArrayList();
        this.selectedItemPositions = new LinkedHashSet();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public TextButtonViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View viewCreateView = this.bindingProvider.createView(this.items.get(position), viewGroup);
        if (viewCreateView != null) {
            return new TextButtonViewHolder((BaseButton) viewCreateView);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.ui.foundations.views.BaseButton");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final TextButtonViewHolder viewHolder, final int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        this.bindingProvider.bind(this.items.get(position), viewHolder.getView());
        setButtonStyle(viewHolder, position);
        viewHolder.getView().getButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.adapters.TextButtonAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TextButtonAdapter.m2212onBindViewHolder$lambda0(this.f$0, viewHolder, position, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m2212onBindViewHolder$lambda0(TextButtonAdapter this$0, TextButtonViewHolder viewHolder, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(viewHolder, "$viewHolder");
        this$0.selectItem(viewHolder, i);
        this$0.dispatchMethods(viewHolder, i);
    }

    private final void selectItem(TextButtonViewHolder viewHolder, int position) {
        ButtonStyle buttonStyle;
        if (!this.selectedItemPositions.contains(Integer.valueOf(position))) {
            this.selectedItemPositions.add(Integer.valueOf(position));
            buttonStyle = this.bindingProvider.getStyleSheet().getButtonStyle(ButtonStyleKey.OUTLINE);
        } else {
            this.selectedItemPositions.remove(Integer.valueOf(position));
            buttonStyle = this.bindingProvider.getStyleSheet().getButtonStyle(ButtonStyleKey.GLASS);
        }
        updateButtonStyle(viewHolder, buttonStyle);
    }

    private final void updateButtonStyle(TextButtonViewHolder viewHolder, ButtonStyle buttonStyle) {
        if (buttonStyle == null) {
            return;
        }
        MaterialButton button = viewHolder.getView().getButton();
        button.setStrokeColor(buttonStyle.getInnerBorderColor());
        Integer innerBorderSize = buttonStyle.getInnerBorderSize();
        button.setStrokeWidth(innerBorderSize == null ? 0 : innerBorderSize.intValue());
        button.setTextColor(buttonStyle.getStatefulStyle().getColorStateList());
        button.setBackgroundTintList(buttonStyle.getStatefulStyle().getBackgroundColorStateList());
    }

    private final void dispatchMethods(TextButtonViewHolder viewHolder, int position) {
        if (this.items.get(position).shouldToggle()) {
            DispatcherContext dispatcherContext = this.bindingProvider.getDispatcherContext();
            if (dispatcherContext == null) {
                return;
            }
            List<Method> listOnItemSelected = this.items.get(position).onItemSelected();
            Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "items[position].onItemSelected()");
            SkyFireKt.dispatch(dispatcherContext, listOnItemSelected, ViewKt.getOrCreateUniqueTag(viewHolder.getView()));
            return;
        }
        DispatcherContext dispatcherContext2 = this.bindingProvider.getDispatcherContext();
        if (dispatcherContext2 == null) {
            return;
        }
        List<Method> listOnItemSelected2 = this.items.get(position).onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected2, "items[position].onItemSelected()");
        SkyFireKt.dispatch$default(dispatcherContext2, listOnItemSelected2, (String) null, 2, (Object) null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public final void addItems(List<? extends TextButtonElement> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        this.items.addAll(newItems);
    }

    public final void clear() {
        this.items.clear();
    }

    private final void setButtonStyle(TextButtonViewHolder viewHolder, int position) {
        MaterialButton button = viewHolder.getView().getButton();
        button.setSingleLine(true);
        button.setMinHeight((int) viewHolder.getView().getContext().getResources().getDimension(R.dimen.multi_selector_tab_text_button_height));
        if (this.selectedItemPositions.contains(Integer.valueOf(position))) {
            updateButtonStyle(viewHolder, this.bindingProvider.getStyleSheet().getButtonStyle(ButtonStyleKey.OUTLINE));
        }
    }

    /* JADX INFO: compiled from: TextButtonAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/TextButtonAdapter$TextButtonViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Lcom/amazon/ui/foundations/views/BaseButton;", "(Lcom/amazon/ui/foundations/views/BaseButton;)V", "getView", "()Lcom/amazon/ui/foundations/views/BaseButton;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class TextButtonViewHolder extends RecyclerView.ViewHolder {
        private final BaseButton view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TextButtonViewHolder(BaseButton view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final BaseButton getView() {
            return this.view;
        }
    }
}
