package com.amazon.music.views.library.views;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FollowTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0016R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/views/library/views/FollowTileView;", "Lcom/amazon/music/views/library/views/VerticalTileView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "followButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getFollowButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "followButton$delegate", "Lkotlin/Lazy;", "smallButtonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "initViewStyling", "", "setFollowButtonClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setIsFollowed", "isFollowed", "", "showFollowButton", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FollowTileView extends VerticalTileView {

    /* JADX INFO: renamed from: followButton$delegate, reason: from kotlin metadata */
    private final Lazy followButton;
    private BaseButton.StyleBuilder smallButtonBuilder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FollowTileView(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.followButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.FollowTileView$followButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.follow_button);
            }
        });
        initViewStyling();
    }

    private final BaseButton getFollowButton() {
        Object value = this.followButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-followButton>(...)");
        return (BaseButton) value;
    }

    private final void initViewStyling() {
        this.smallButtonBuilder = getStyleSheet().getButtonBuilder(ButtonSizeKey.SMALL, ButtonStyleKey.FOLLOW_OUTLINE);
    }

    public final void showFollowButton(boolean showFollowButton) {
        if (showFollowButton) {
            BaseButton.StyleBuilder styleBuilder = this.smallButtonBuilder;
            if (styleBuilder != null) {
                String string = getResources().getString(R$string.dmusic_follow_text);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.dmusic_follow_text)");
                styleBuilder.withText(string);
                String string2 = getResources().getString(R$string.dmusic_following_text);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…ng.dmusic_following_text)");
                styleBuilder.withActiveText(string2);
                styleBuilder.applyStyle(getFollowButton());
            }
            LayoutParamUtils.INSTANCE.setLayoutMargins(getFollowButton(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : 0, (240 & 32) != 0 ? null : getSmallSpace(), (240 & 64) != 0 ? null : 0, (240 & 128) != 0 ? null : null);
        }
        getFollowButton().setVisibility(showFollowButton ? 0 : 8);
    }

    public final void setIsFollowed(boolean isFollowed) {
        String string;
        if (isFollowed) {
            string = getResources().getString(R$string.dmusic_following_text);
        } else {
            string = getResources().getString(R$string.dmusic_follow_text);
        }
        Intrinsics.checkNotNullExpressionValue(string, "if (isFollowed) resource…tring.dmusic_follow_text)");
        getFollowButton().setActive(isFollowed);
        getFollowButton().setContentDescription(string);
    }

    public final void setFollowButtonClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getFollowButton().setOnClickListener(onClickListener);
    }
}
