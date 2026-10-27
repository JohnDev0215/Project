package com.amazon.music.views.library.views;

import android.view.View;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;

/* JADX INFO: compiled from: AddButtonPresenter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&R\u0014\u0010\t\u001a\u00020\u00068&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\r\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u000f\u001a\u00020\n8&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/views/library/views/AddButtonPresenter;", "", "", "isLiked", "", "restyleLikeIcons", "Landroid/view/View;", "getRowButtonContainerView", "()Landroid/view/View;", "rowButtonContainerView", "Lcom/amazon/ui/foundations/views/BaseButton;", "getRowButtonView", "()Lcom/amazon/ui/foundations/views/BaseButton;", "rowButtonView", "getRowButtonClickedImageView", "rowButtonClickedImageView", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public interface AddButtonPresenter {
    BaseButton getRowButtonClickedImageView();

    View getRowButtonContainerView();

    BaseButton getRowButtonView();

    void restyleLikeIcons(boolean isLiked);
}
