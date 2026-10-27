package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementRowItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u0010\u0010\n\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0006R\u001a\u0010\f\u001a\u00020\u000b8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0013\u001a\u00020\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u00108&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/views/library/views/RefinementRowItemView;", "Landroid/widget/LinearLayout;", "", "isSelected", "", "setIsSelected", "", "title", "setTitle", "subTitle", "setSubTitle", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/widget/TextView;", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView", "getSubTitleTextView", "subTitleTextView", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class RefinementRowItemView extends LinearLayout {
    private final StyleSheet styleSheet;

    public abstract TextView getSubTitleTextView();

    public abstract TextView getTitleTextView();

    public abstract void setIsSelected(boolean isSelected);

    protected final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefinementRowItemView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
    }

    public final void setSubTitle(String subTitle) {
        if (TextUtils.isEmpty(subTitle)) {
            getSubTitleTextView().setVisibility(8);
        } else {
            getSubTitleTextView().setText(subTitle);
            getSubTitleTextView().setVisibility(0);
        }
    }
}
