package com.amazon.music.views.library.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StarRatingView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010%\u001a\u0004\u0018\u00010$¢\u0006\u0004\b&\u0010'J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002R\u0016\u0010\t\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR$\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0006@BX\u0086.¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R.\u0010\u0017\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR.\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u001d8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006("}, d2 = {"Lcom/amazon/music/views/library/views/StarRatingView;", "Landroid/widget/LinearLayout;", "Landroid/content/Context;", "context", "", "init", "updateView", "updateContentDescription", "Ljava/text/NumberFormat;", "numberFormat", "Ljava/text/NumberFormat;", "", "Landroid/widget/ImageView;", "iconList", "Ljava/util/List;", "Landroid/widget/TextView;", "<set-?>", "countView", "Landroid/widget/TextView;", "getCountView", "()Landroid/widget/TextView;", "", "value", "rating", "Ljava/lang/Float;", "getRating", "()Ljava/lang/Float;", "setRating", "(Ljava/lang/Float;)V", "", "ratingCount", "Ljava/lang/Integer;", "getRatingCount", "()Ljava/lang/Integer;", "setRatingCount", "(Ljava/lang/Integer;)V", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class StarRatingView extends LinearLayout {
    private TextView countView;
    private final List<ImageView> iconList;
    private NumberFormat numberFormat;
    private Float rating;
    private Integer ratingCount;

    public final TextView getCountView() {
        TextView textView = this.countView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("countView");
        return null;
    }

    public final Float getRating() {
        return this.rating;
    }

    public final void setRating(Float f) {
        this.rating = f != null ? Float.valueOf(Math.min(Math.max(0.0f, f.floatValue()), 5.0f)) : null;
        updateView();
        updateContentDescription();
    }

    public final Integer getRatingCount() {
        return this.ratingCount;
    }

    public final void setRatingCount(Integer num) {
        this.ratingCount = num != null ? Integer.valueOf(Math.max(0, num.intValue())) : null;
        updateView();
        updateContentDescription();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StarRatingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        NumberFormat numberFormat = NumberFormat.getInstance();
        Intrinsics.checkNotNullExpressionValue(numberFormat, "getInstance()");
        this.numberFormat = numberFormat;
        this.iconList = new ArrayList();
        init(context);
    }

    private final void init(Context context) {
        View.inflate(context, R$layout.dmmviewlibrary_star_rating_view, this);
        List<ImageView> list = this.iconList;
        View viewFindViewById = findViewById(R$id.star_one);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.star_one)");
        list.add((ImageView) viewFindViewById);
        List<ImageView> list2 = this.iconList;
        View viewFindViewById2 = findViewById(R$id.star_two);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.star_two)");
        list2.add((ImageView) viewFindViewById2);
        List<ImageView> list3 = this.iconList;
        View viewFindViewById3 = findViewById(R$id.star_three);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.star_three)");
        list3.add((ImageView) viewFindViewById3);
        List<ImageView> list4 = this.iconList;
        View viewFindViewById4 = findViewById(R$id.star_four);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(R.id.star_four)");
        list4.add((ImageView) viewFindViewById4);
        List<ImageView> list5 = this.iconList;
        View viewFindViewById5 = findViewById(R$id.star_five);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(R.id.star_five)");
        list5.add((ImageView) viewFindViewById5);
        View viewFindViewById6 = findViewById(R$id.count);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(R.id.count)");
        this.countView = (TextView) viewFindViewById6;
    }

    private final void updateView() {
        int i;
        Float f = this.rating;
        if (f == null) {
            Iterator<T> it = this.iconList.iterator();
            while (it.hasNext()) {
                ((ImageView) it.next()).setVisibility(8);
            }
        } else {
            int i2 = 0;
            for (Object obj : this.iconList) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                ImageView imageView = (ImageView) obj;
                double d = i2;
                if (f.floatValue() >= 0.75d + d) {
                    i = R$drawable.ic_star_on;
                } else {
                    i = ((double) f.floatValue()) >= d + 0.25d ? R$drawable.ic_star_half : R$drawable.ic_star_off;
                }
                imageView.setImageResource(i);
                imageView.setVisibility(0);
                i2 = i3;
            }
        }
        Integer num = this.ratingCount;
        if (num == null) {
            getCountView().setVisibility(8);
        } else {
            getCountView().setText(this.numberFormat.format(num));
            getCountView().setVisibility(0);
        }
    }

    private final void updateContentDescription() {
        setContentDescription(getContext().getString(R$string.explore_star_rating, this.rating, this.ratingCount));
    }
}
