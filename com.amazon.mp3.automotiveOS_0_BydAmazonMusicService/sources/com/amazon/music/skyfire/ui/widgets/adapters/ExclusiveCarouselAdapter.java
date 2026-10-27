package com.amazon.music.skyfire.ui.widgets.adapters;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.R;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.transformations.GradientTransformation;
import com.amazon.music.skyfire.ui.transformations.OverlayTransformation;
import com.amazon.music.skyfire.ui.widgets.adapters.ExclusiveCarouselAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExclusiveCarouselAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u0014\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/ExclusiveCarouselAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/skyfire/ui/widgets/adapters/ExclusiveCarouselAdapter$ViewHolder;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "dispatchContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "(Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;)V", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/ExclusiveCarouselSlideElement;", "getItemCount", "", "onBindViewHolder", "", "viewHolder", "position", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "resetItems", "newItems", "", "ViewHolder", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExclusiveCarouselAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final DispatcherContext dispatchContext;
    private final List<ExclusiveCarouselSlideElement> items;
    private final StyleSheet styleSheet;

    public ExclusiveCarouselAdapter(StyleSheet styleSheet, DispatcherContext dispatchContext) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(dispatchContext, "dispatchContext");
        this.styleSheet = styleSheet;
        this.dispatchContext = dispatchContext;
        this.items = new ArrayList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        ViewHolder viewHolder = new ViewHolder(ViewGroupKt.inflate$default(viewGroup, R.layout.exclusive_carousel_slide_view, false, 2, null));
        viewHolder.initStyle(this.styleSheet);
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        viewHolder.bind(this.items.get(position), this.dispatchContext);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public final void resetItems(List<? extends ExclusiveCarouselSlideElement> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        this.items.clear();
        CollectionsKt__MutableCollectionsKt.addAll(this.items, newItems);
        notifyDataSetChanged();
    }

    /* JADX INFO: compiled from: ExclusiveCarouselAdapter.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0013\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010¨\u0006\u001e"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/ExclusiveCarouselAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "parent", "Landroid/view/View;", "(Landroid/view/View;)V", "backdrop", "Landroid/widget/ImageView;", "getBackdrop", "()Landroid/widget/ImageView;", "callToActionButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getCallToActionButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", MediaTrack.ROLE_DESCRIPTION, "Landroid/widget/TextView;", "getDescription", "()Landroid/widget/TextView;", "headline", "getHeadline", ContextMappingConstants.LABEL, "getLabel", "bind", "", "element", "LTouch/WidgetsInterface/v1_0/ExclusiveCarouselSlideElement;", "dispatcherContext", "Lcom/amazon/music/skyfire/ui/skyfire/DispatcherContext;", "initStyle", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView backdrop;
        private final BaseButton callToActionButton;
        private final TextView description;
        private final TextView headline;
        private final TextView label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View parent) {
            super(parent);
            Intrinsics.checkNotNullParameter(parent, "parent");
            View viewFindViewById = parent.findViewById(R.id.backdrop);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "parent.findViewById(R.id.backdrop)");
            this.backdrop = (ImageView) viewFindViewById;
            View viewFindViewById2 = parent.findViewById(R.id.label);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "parent.findViewById(R.id.label)");
            this.label = (TextView) viewFindViewById2;
            View viewFindViewById3 = parent.findViewById(R.id.headline);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "parent.findViewById(R.id.headline)");
            this.headline = (TextView) viewFindViewById3;
            View viewFindViewById4 = parent.findViewById(R.id.description);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "parent.findViewById(R.id.description)");
            this.description = (TextView) viewFindViewById4;
            View viewFindViewById5 = parent.findViewById(R.id.call_to_action_button);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "parent.findViewById(R.id.call_to_action_button)");
            this.callToActionButton = (BaseButton) viewFindViewById5;
        }

        public final ImageView getBackdrop() {
            return this.backdrop;
        }

        public final TextView getLabel() {
            return this.label;
        }

        public final TextView getHeadline() {
            return this.headline;
        }

        public final TextView getDescription() {
            return this.description;
        }

        public final BaseButton getCallToActionButton() {
            return this.callToActionButton;
        }

        public final void initStyle(StyleSheet styleSheet) {
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            ViewKt.applyFontStyle(this.label, styleSheet, FontStyleKey.LABEL);
            ViewKt.applyFontStyle(this.headline, styleSheet, FontStyleKey.HEADLINE_2);
            ViewKt.applyFontStyle(this.description, styleSheet, FontStyleKey.SECONDARY);
            BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.GLASS);
            Intrinsics.checkNotNull(buttonBuilder);
            buttonBuilder.applyStyle(this.callToActionButton);
        }

        public final void bind(ExclusiveCarouselSlideElement element, final DispatcherContext dispatcherContext) {
            String strText;
            Intrinsics.checkNotNullParameter(element, "element");
            Intrinsics.checkNotNullParameter(dispatcherContext, "dispatcherContext");
            Context context = this.itemView.getContext();
            String strImage = element.image();
            if (strImage != null) {
                Picasso.get().load(strImage).fit().centerCrop().transform(new GradientTransformation(ContextCompat.getColor(context, R.color.secondary_glass_3), ContextCompat.getColor(context, R.color.secondary), 0)).transform(new OverlayTransformation(ContextCompat.getColor(this.itemView.getContext(), R.color.secondary_glass_1))).into(getBackdrop());
            }
            this.backdrop.setContentDescription(element.imageDescription());
            this.label.setText(element.label());
            this.headline.setText(element.primaryText());
            this.description.setText(element.secondaryText());
            ButtonElement buttonElementCallToActionButton = element.callToActionButton();
            final TextButtonElement textButtonElement = buttonElementCallToActionButton instanceof TextButtonElement ? (TextButtonElement) buttonElementCallToActionButton : null;
            BaseButton baseButton = this.callToActionButton;
            String str = "";
            if (textButtonElement != null && (strText = textButtonElement.text()) != null) {
                str = strText;
            }
            baseButton.setText(str);
            com.amazon.ui.foundations.ktx.ViewKt.collapseIf(baseButton, textButtonElement == null);
            baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.skyfire.ui.widgets.adapters.ExclusiveCarouselAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ExclusiveCarouselAdapter.ViewHolder.m2211bind$lambda2$lambda1(dispatcherContext, textButtonElement, view);
                }
            });
            List<Method> listOnViewed = textButtonElement == null ? null : textButtonElement.onViewed();
            if (listOnViewed == null) {
                listOnViewed = CollectionsKt__CollectionsKt.emptyList();
            }
            SkyFireKt.dispatch$default(dispatcherContext, listOnViewed, (String) null, 2, (Object) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: bind$lambda-2$lambda-1, reason: not valid java name */
        public static final void m2211bind$lambda2$lambda1(DispatcherContext dispatcherContext, TextButtonElement textButtonElement, View view) {
            Intrinsics.checkNotNullParameter(dispatcherContext, "$dispatcherContext");
            List<Method> listOnItemSelected = textButtonElement == null ? null : textButtonElement.onItemSelected();
            if (listOnItemSelected == null) {
                listOnItemSelected = CollectionsKt__CollectionsKt.emptyList();
            }
            SkyFireKt.dispatch$default(dispatcherContext, listOnItemSelected, (String) null, 2, (Object) null);
        }
    }
}
