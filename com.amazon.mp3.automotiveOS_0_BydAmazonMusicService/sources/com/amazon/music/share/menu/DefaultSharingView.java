package com.amazon.music.share.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.music.share.R$dimen;
import com.amazon.music.share.R$id;
import com.amazon.music.share.R$layout;
import com.amazon.music.share.R$string;
import com.amazon.music.share.menu.destinations.ShareDestination;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.UIExtensionsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultSharingView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0001(B7\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b&\u0010'J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J(\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0016\u0010\u0013\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J \u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0018\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001bH\u0002R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0016\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010!R\u0016\u0010\n\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\"R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010#R\u0014\u0010$\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006)"}, d2 = {"Lcom/amazon/music/share/menu/DefaultSharingView;", "Landroid/widget/LinearLayout;", "Landroid/content/Context;", "context", "", "bindSharingItems", "initSharingItemsContainer", "initTitleTextView", "initRootView", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/share/menu/ShareableContent;", "sharableContent", "Lcom/amazon/music/share/menu/DefaultSharingViewModel;", "defaultSharingViewModel", "bindImageItems", "", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "destination", "bindItems", "Landroid/view/LayoutInflater;", "layoutInflater", "", "firstItem", "addShareCard", "Lcom/amazon/music/share/menu/DefaultSharingView$ShareItemViewHolder;", "vh", "", "leftMargin", "setLeftMargin", "Landroidx/lifecycle/LifecycleOwner;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "Lcom/amazon/music/share/menu/ShareableContent;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/share/menu/DefaultSharingViewModel;", "sharingItemsContainer", "Landroid/widget/LinearLayout;", "<init>", "(Landroid/content/Context;Landroidx/lifecycle/LifecycleOwner;Lcom/amazon/music/share/menu/ShareableContent;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/share/menu/DefaultSharingViewModel;)V", "ShareItemViewHolder", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class DefaultSharingView extends LinearLayout {
    private final DefaultSharingViewModel defaultSharingViewModel;
    private final LifecycleOwner lifecycleOwner;
    private final ShareableContent sharableContent;
    private final LinearLayout sharingItemsContainer;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSharingView(Context context, LifecycleOwner lifecycleOwner, ShareableContent shareableContent, StyleSheet styleSheet, DefaultSharingViewModel defaultSharingViewModel) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.lifecycleOwner = lifecycleOwner;
        this.sharableContent = shareableContent;
        this.styleSheet = styleSheet;
        this.defaultSharingViewModel = defaultSharingViewModel;
        initRootView();
        initTitleTextView(context);
        this.sharingItemsContainer = initSharingItemsContainer(context);
        bindSharingItems(context);
    }

    private final void bindSharingItems(Context context) {
        ShareableContent shareableContent;
        if (this.styleSheet == null || (shareableContent = this.sharableContent) == null || this.defaultSharingViewModel == null) {
            return;
        }
        if (shareableContent.getImage() == null) {
            bindItems(this.defaultSharingViewModel.getDestinations(context, this.styleSheet, false));
            bindImageItems(context, this.styleSheet, this.sharableContent, this.defaultSharingViewModel);
        } else {
            bindItems(this.defaultSharingViewModel.getDestinations(context, this.styleSheet, true));
        }
    }

    private final LinearLayout initSharingItemsContainer(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(context);
        horizontalScrollView.addView(linearLayout);
        horizontalScrollView.setHorizontalScrollBarEnabled(false);
        horizontalScrollView.setFillViewport(true);
        addView(horizontalScrollView);
        return linearLayout;
    }

    private final void initTitleTextView(Context context) {
        FontStyle fontStyle;
        Integer shareTextId;
        TextView textView = new TextView(context);
        ShareableContent shareableContent = this.sharableContent;
        String string = null;
        if (shareableContent != null && (shareTextId = shareableContent.getShareTextId()) != null) {
            string = textView.getResources().getString(shareTextId.intValue());
        }
        if (string == null) {
            string = textView.getResources().getString(R$string.dmusic_context_share);
        }
        textView.setText(string);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins((int) textView.getResources().getDimension(R$dimen.spacer_4), (int) textView.getResources().getDimension(R$dimen.spacer_16), 0, (int) textView.getResources().getDimension(R$dimen.default_share_view_text_margin_bottom));
        textView.setLayoutParams(layoutParams);
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4)) != null) {
            UIExtensionsKt.applyFontStyle(textView, fontStyle);
        }
        addView(textView);
    }

    private final void initRootView() {
        setOrientation(1);
    }

    private final void bindImageItems(final Context context, final StyleSheet styleSheet, final ShareableContent sharableContent, final DefaultSharingViewModel defaultSharingViewModel) {
        if (this.lifecycleOwner == null) {
            return;
        }
        defaultSharingViewModel.fetchImageUrl(sharableContent.getEntityId(), sharableContent.getEntityType()).observe(this.lifecycleOwner, new Observer() { // from class: com.amazon.music.share.menu.DefaultSharingView$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DefaultSharingView.m2183bindImageItems$lambda7$lambda6(sharableContent, this, defaultSharingViewModel, context, styleSheet, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindImageItems$lambda-7$lambda-6, reason: not valid java name */
    public static final void m2183bindImageItems$lambda7$lambda6(ShareableContent sharableContent, DefaultSharingView this$0, DefaultSharingViewModel defaultSharingViewModel, Context context, StyleSheet styleSheet, String str) {
        Intrinsics.checkNotNullParameter(sharableContent, "$sharableContent");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(defaultSharingViewModel, "$defaultSharingViewModel");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(styleSheet, "$styleSheet");
        sharableContent.setImage(str);
        this$0.bindItems(defaultSharingViewModel.getImageDestinations(context, styleSheet));
    }

    private final void bindItems(List<? extends ShareDestination> destination) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        ArrayList arrayList = new ArrayList();
        for (Object obj : destination) {
            if (((ShareDestination) obj).isAvailable()) {
                arrayList.add(obj);
            }
        }
        int i = 0;
        for (Object obj2 : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            ShareDestination shareDestination = (ShareDestination) obj2;
            Intrinsics.checkNotNullExpressionValue(layoutInflater, "layoutInflater");
            addShareCard(layoutInflater, shareDestination, i == 0);
            i = i2;
        }
    }

    private final void addShareCard(LayoutInflater layoutInflater, final ShareDestination destination, boolean firstItem) {
        FontStyle fontStyle;
        View viewInflate = layoutInflater.inflate(R$layout.default_sharing_item, (ViewGroup) this.sharingItemsContainer, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "layoutInflater.inflate(R…ingItemsContainer, false)");
        ShareItemViewHolder shareItemViewHolder = new ShareItemViewHolder(viewInflate);
        if (!firstItem) {
            setLeftMargin(shareItemViewHolder, (int) getResources().getDimension(R$dimen.default_share_item_margin));
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.TERTIARY)) != null) {
            UIExtensionsKt.applyFontStyle(shareItemViewHolder.getText(), fontStyle);
        }
        shareItemViewHolder.getText().setTextAlignment(4);
        shareItemViewHolder.getText().setText(destination.getNameResId());
        shareItemViewHolder.getIcon().setImageResource(destination.getIconResId());
        shareItemViewHolder.getView().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.share.menu.DefaultSharingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DefaultSharingView.m2182addShareCard$lambda12$lambda11(this.f$0, destination, view);
            }
        });
        this.sharingItemsContainer.addView(shareItemViewHolder.getView());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addShareCard$lambda-12$lambda-11, reason: not valid java name */
    public static final void m2182addShareCard$lambda12$lambda11(DefaultSharingView this$0, ShareDestination destination, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(destination, "$destination");
        DefaultSharingViewModel defaultSharingViewModel = this$0.defaultSharingViewModel;
        if (defaultSharingViewModel == null) {
            return;
        }
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        defaultSharingViewModel.onItemShared(context, destination, this$0.sharableContent);
    }

    private final void setLeftMargin(ShareItemViewHolder vh, int leftMargin) {
        ViewGroup.LayoutParams layoutParams = vh.getView().getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(leftMargin, 0, 0, 0);
        vh.getView().setLayoutParams(marginLayoutParams);
    }

    /* JADX INFO: compiled from: DefaultSharingView.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/share/menu/DefaultSharingView$ShareItemViewHolder;", "", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "icon", "Landroid/widget/ImageView;", "getIcon", "()Landroid/widget/ImageView;", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "getView", "()Landroid/view/View;", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class ShareItemViewHolder {
        private final ImageView icon;
        private final TextView text;
        private final View view;

        public ShareItemViewHolder(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
            View viewFindViewById = view.findViewById(R$id.icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.icon)");
            this.icon = (ImageView) viewFindViewById;
            View viewFindViewById2 = view.findViewById(R$id.text);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.text)");
            this.text = (TextView) viewFindViewById2;
        }

        public final View getView() {
            return this.view;
        }

        public final ImageView getIcon() {
            return this.icon;
        }

        public final TextView getText() {
            return this.text;
        }
    }
}
