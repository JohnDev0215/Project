package com.amazon.music.views.library.views;

import android.content.Context;
import android.view.View;
import com.amazon.music.views.library.R$string;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryAddableView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020*H\u0004J\u0010\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020\rH\u0016J\b\u0010.\u001a\u00020*H\u0016J\u0006\u0010/\u001a\u00020*J\u000e\u00100\u001a\u00020*2\u0006\u00101\u001a\u000202J\u0010\u00103\u001a\u00020*2\u0006\u00104\u001a\u00020\rH\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR\u001a\u0010\u0011\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\u000bR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001c\u0010 \u001a\u0004\u0018\u00010\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\u001c\u0010#\u001a\u0004\u0018\u00010\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001c\u0010&\u001a\u0004\u0018\u00010\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0017\"\u0004\b(\u0010\u0019¨\u00065"}, d2 = {"Lcom/amazon/music/views/library/views/LibraryAddableView;", "Lcom/amazon/music/views/library/views/ScopedConstraintLayout;", "Lcom/amazon/music/views/library/views/AddButtonPresenter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "buttonContentDesc", "", "getButtonContentDesc", "()Ljava/lang/String;", "setButtonContentDesc", "(Ljava/lang/String;)V", "hasAppliedStyling", "", "likeButtonLikedDesc", "getLikeButtonLikedDesc", "setLikeButtonLikedDesc", "likeButtonNeutralDesc", "getLikeButtonNeutralDesc", "setLikeButtonNeutralDesc", "likesButtonClickedIconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getLikesButtonClickedIconBuilder", "()Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "setLikesButtonClickedIconBuilder", "(Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;)V", "likesButtonIconBuilder", "getLikesButtonIconBuilder", "setLikesButtonIconBuilder", "playIconBuilder", "getPlayIconBuilder", "setPlayIconBuilder", "playIconBuilderTiny", "getPlayIconBuilderTiny", "setPlayIconBuilderTiny", "rowButtonClickedIconBuilder", "getRowButtonClickedIconBuilder", "setRowButtonClickedIconBuilder", "rowButtonIconBuilder", "getRowButtonIconBuilder", "setRowButtonIconBuilder", "disableRowButtonIcon", "", "initRowButtonStyling", "restyleLikeIcons", "isLiked", "restylePlayIcon", "restyleRowButtonIcon", "setRowButtonOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "showRowButton", "show", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class LibraryAddableView extends ScopedConstraintLayout implements AddButtonPresenter {
    private String buttonContentDesc;
    private boolean hasAppliedStyling;
    private String likeButtonLikedDesc;
    private String likeButtonNeutralDesc;
    private BaseButton.StyleBuilder likesButtonClickedIconBuilder;
    private BaseButton.StyleBuilder likesButtonIconBuilder;
    private BaseButton.StyleBuilder playIconBuilder;
    private BaseButton.StyleBuilder playIconBuilderTiny;
    private BaseButton.StyleBuilder rowButtonClickedIconBuilder;
    private BaseButton.StyleBuilder rowButtonIconBuilder;

    public abstract /* synthetic */ BaseButton getRowButtonClickedImageView();

    public abstract /* synthetic */ View getRowButtonContainerView();

    public abstract /* synthetic */ BaseButton getRowButtonView();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LibraryAddableView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getResources().getString(R$string.like_state_liked);
        Intrinsics.checkNotNullExpressionValue(string, "context.getResources().g….string.like_state_liked)");
        this.likeButtonLikedDesc = string;
        String string2 = context.getResources().getString(R$string.like_state_neutral);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getResources().g…tring.like_state_neutral)");
        this.likeButtonNeutralDesc = string2;
        this.buttonContentDesc = "";
    }

    protected final BaseButton.StyleBuilder getRowButtonIconBuilder() {
        return this.rowButtonIconBuilder;
    }

    protected final void setRowButtonIconBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.rowButtonIconBuilder = styleBuilder;
    }

    protected final BaseButton.StyleBuilder getRowButtonClickedIconBuilder() {
        return this.rowButtonClickedIconBuilder;
    }

    protected final void setRowButtonClickedIconBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.rowButtonClickedIconBuilder = styleBuilder;
    }

    protected final BaseButton.StyleBuilder getPlayIconBuilder() {
        return this.playIconBuilder;
    }

    protected final void setPlayIconBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.playIconBuilder = styleBuilder;
    }

    protected final BaseButton.StyleBuilder getPlayIconBuilderTiny() {
        return this.playIconBuilderTiny;
    }

    protected final void setPlayIconBuilderTiny(BaseButton.StyleBuilder styleBuilder) {
        this.playIconBuilderTiny = styleBuilder;
    }

    protected final BaseButton.StyleBuilder getLikesButtonIconBuilder() {
        return this.likesButtonIconBuilder;
    }

    protected final void setLikesButtonIconBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.likesButtonIconBuilder = styleBuilder;
    }

    protected final BaseButton.StyleBuilder getLikesButtonClickedIconBuilder() {
        return this.likesButtonClickedIconBuilder;
    }

    protected final void setLikesButtonClickedIconBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.likesButtonClickedIconBuilder = styleBuilder;
    }

    protected final String getLikeButtonLikedDesc() {
        return this.likeButtonLikedDesc;
    }

    protected final void setLikeButtonLikedDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.likeButtonLikedDesc = str;
    }

    protected final String getLikeButtonNeutralDesc() {
        return this.likeButtonNeutralDesc;
    }

    protected final void setLikeButtonNeutralDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.likeButtonNeutralDesc = str;
    }

    protected final String getButtonContentDesc() {
        return this.buttonContentDesc;
    }

    protected final void setButtonContentDesc(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.buttonContentDesc = str;
    }

    public void showRowButton(boolean show) {
        getRowButtonView().setVisibility(show ? 0 : 4);
        getRowButtonContainerView().setVisibility(show ? 0 : 4);
    }

    @Override // com.amazon.music.views.library.views.AddButtonPresenter
    public void restyleLikeIcons(boolean isLiked) {
        if (isLiked) {
            this.rowButtonIconBuilder = this.likesButtonClickedIconBuilder;
            this.rowButtonClickedIconBuilder = this.likesButtonIconBuilder;
            this.buttonContentDesc = this.likeButtonLikedDesc;
        } else {
            this.rowButtonIconBuilder = this.likesButtonIconBuilder;
            this.rowButtonClickedIconBuilder = this.likesButtonClickedIconBuilder;
            this.buttonContentDesc = this.likeButtonNeutralDesc;
        }
        this.hasAppliedStyling = false;
        initRowButtonStyling();
    }

    public void restylePlayIcon() {
        this.rowButtonIconBuilder = this.playIconBuilderTiny;
        this.hasAppliedStyling = false;
        initRowButtonStyling();
    }

    public final void setRowButtonOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getRowButtonView().setOnClickListener(onClickListener);
    }

    public void disableRowButtonIcon() {
        getRowButtonView().setVisibility(8);
        getRowButtonContainerView().setVisibility(8);
    }

    protected final void initRowButtonStyling() {
        if (this.hasAppliedStyling) {
            return;
        }
        BaseButton.StyleBuilder styleBuilder = this.rowButtonIconBuilder;
        if (styleBuilder != null) {
            styleBuilder.applyStyle(getRowButtonView());
        }
        BaseButton.StyleBuilder styleBuilder2 = this.rowButtonClickedIconBuilder;
        if (styleBuilder2 != null) {
            styleBuilder2.applyStyle(getRowButtonClickedImageView());
        }
        if (!(this.buttonContentDesc.length() == 0)) {
            getRowButtonView().setContentDescription(this.buttonContentDesc);
        }
        this.hasAppliedStyling = true;
    }

    public final void restyleRowButtonIcon() {
        this.hasAppliedStyling = false;
        initRowButtonStyling();
    }
}
