package com.amazon.mp3.playback.fragment.contextmenu;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.playback.fragment.contextmenu.NowPlayingContextMenuAdapter;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: NowPlayingContextMenuAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\"B\u001d\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b \u0010!J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0018\u0010\t\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0018\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J\b\u0010\u000e\u001a\u00020\u0007H\u0016J\u0014\u0010\u0012\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fR\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0019\u001a\u00020\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006#"}, d2 = {"Lcom/amazon/mp3/playback/fragment/contextmenu/NowPlayingContextMenuAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/mp3/playback/fragment/contextmenu/NowPlayingContextMenuAdapter$MenuItemViewHolder;", "", "isStage", "Landroid/view/ViewGroup;", "parent", "", "viewType", "onCreateViewHolder", "holder", "position", "", "onBindViewHolder", "getItemCount", "", "Landroid/view/MenuItem;", ContextMappingConstants.ITEMS, "updateMenuEntries", "Lrx/functions/Action1;", "clickAction", "Lrx/functions/Action1;", "getClickAction", "()Lrx/functions/Action1;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "", "menuEntries", "Ljava/util/List;", "<init>", "(Lrx/functions/Action1;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "MenuItemViewHolder", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NowPlayingContextMenuAdapter extends RecyclerView.Adapter<MenuItemViewHolder> {
    private final Action1<MenuItem> clickAction;
    private final List<MenuItem> menuEntries;
    private final StyleSheet styleSheet;

    public NowPlayingContextMenuAdapter(Action1<MenuItem> clickAction, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(clickAction, "clickAction");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.clickAction = clickAction;
        this.styleSheet = styleSheet;
        this.menuEntries = new ArrayList();
    }

    /* JADX INFO: compiled from: NowPlayingContextMenuAdapter.kt */
    @Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010$\u001a\u00020\u001d\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b%\u0010&J\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0017\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u001c\u001a\u00020\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010!\u001a\u00020\u001d8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001f\u0010 R\u0016\u0010\"\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006'"}, d2 = {"Lcom/amazon/mp3/playback/fragment/contextmenu/NowPlayingContextMenuAdapter$MenuItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/MenuItem;", "item", "", "bindTo", "cleanViewHolder", "removeDivider", "Lrx/functions/Action1;", "clickAction", "Lrx/functions/Action1;", "getClickAction", "()Lrx/functions/Action1;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/widget/ImageView;", "icon$delegate", "Lkotlin/Lazy;", "getIcon", "()Landroid/widget/ImageView;", "icon", "Landroid/widget/TextView;", "title$delegate", "getTitle", "()Landroid/widget/TextView;", "title", "Landroid/view/View;", "divider$delegate", "getDivider", "()Landroid/view/View;", "divider", "menuItem", "Landroid/view/MenuItem;", "view", "<init>", "(Landroid/view/View;Lrx/functions/Action1;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class MenuItemViewHolder extends RecyclerView.ViewHolder {
        private final Action1<MenuItem> clickAction;

        /* JADX INFO: renamed from: divider$delegate, reason: from kotlin metadata */
        private final Lazy divider;

        /* JADX INFO: renamed from: icon$delegate, reason: from kotlin metadata */
        private final Lazy icon;
        private MenuItem menuItem;
        private final StyleSheet styleSheet;

        /* JADX INFO: renamed from: title$delegate, reason: from kotlin metadata */
        private final Lazy title;

        public final StyleSheet getStyleSheet() {
            return this.styleSheet;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MenuItemViewHolder(final View view, Action1<MenuItem> clickAction, StyleSheet styleSheet) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(clickAction, "clickAction");
            Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
            this.clickAction = clickAction;
            this.styleSheet = styleSheet;
            this.icon = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.playback.fragment.contextmenu.NowPlayingContextMenuAdapter$MenuItemViewHolder$icon$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ImageView invoke() {
                    return (ImageView) view.findViewById(R.id.section_icon);
                }
            });
            this.title = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.fragment.contextmenu.NowPlayingContextMenuAdapter$MenuItemViewHolder$title$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TextView invoke() {
                    return (TextView) view.findViewById(R.id.section_name);
                }
            });
            this.divider = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.playback.fragment.contextmenu.NowPlayingContextMenuAdapter$MenuItemViewHolder$divider$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final View invoke() {
                    return view.findViewById(R.id.divider);
                }
            });
        }

        private final ImageView getIcon() {
            Object value = this.icon.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-icon>(...)");
            return (ImageView) value;
        }

        private final TextView getTitle() {
            Object value = this.title.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-title>(...)");
            return (TextView) value;
        }

        private final View getDivider() {
            Object value = this.divider.getValue();
            Intrinsics.checkNotNullExpressionValue(value, "<get-divider>(...)");
            return (View) value;
        }

        public final void bindTo(MenuItem item) {
            cleanViewHolder();
            if (item == null) {
                return;
            }
            this.menuItem = item;
            getTitle().setText(item.getTitle());
            if (item.getIcon() != null) {
                getIcon().setImageDrawable(item.getIcon());
                getIcon().setVisibility(0);
            }
            if (item.isEnabled()) {
                Integer color = getStyleSheet().getColor(ColorKey.COLOR_PRIMARY);
                if (color != null) {
                    getTitle().setTextColor(color.intValue());
                }
                View view = (View) getTitle().getParent();
                if (view == null) {
                    return;
                }
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.contextmenu.NowPlayingContextMenuAdapter$MenuItemViewHolder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        NowPlayingContextMenuAdapter.MenuItemViewHolder.m1467bindTo$lambda3$lambda1(this.f$0, view2);
                    }
                });
                return;
            }
            Integer color2 = getStyleSheet().getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3);
            if (color2 == null) {
                return;
            }
            getTitle().setTextColor(color2.intValue());
            getIcon().setEnabled(false);
            getTitle().setEnabled(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: bindTo$lambda-3$lambda-1, reason: not valid java name */
        public static final void m1467bindTo$lambda3$lambda1(MenuItemViewHolder this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Action1<MenuItem> action1 = this$0.clickAction;
            MenuItem menuItem = this$0.menuItem;
            if (menuItem == null) {
                Intrinsics.throwUninitializedPropertyAccessException("menuItem");
                menuItem = null;
            }
            action1.call(menuItem);
        }

        public final void cleanViewHolder() {
            getTitle().setEnabled(true);
            getIcon().setEnabled(true);
            getIcon().setImageDrawable(null);
        }

        public final void removeDivider() {
            getDivider().setVisibility(8);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.context_menu_item, parent, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.section_icon);
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        if (isStage()) {
            ((ImageView) view.findViewById(R.id.caret_right)).setVisibility(0);
            ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = (int) parent.getContext().getResources().getDimension(R.dimen.bauhaus_spacer_small);
        } else {
            ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = (int) parent.getContext().getResources().getDimension(R.dimen.bauhaus_spacer_medium);
        }
        imageView.setLayoutParams(layoutParams2);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new MenuItemViewHolder(view, this.clickAction, this.styleSheet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bindTo(this.menuEntries.get(position));
        if (position == getItemCount() - 1) {
            holder.removeDivider();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.menuEntries.size();
    }

    public final void updateMenuEntries(List<? extends MenuItem> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.menuEntries.clear();
        this.menuEntries.addAll(items);
    }

    private final boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }
}
