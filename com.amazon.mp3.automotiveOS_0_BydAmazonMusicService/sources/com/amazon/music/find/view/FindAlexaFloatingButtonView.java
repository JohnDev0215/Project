package com.amazon.music.find.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.find.R;
import kotlin.Metadata;

/* JADX INFO: compiled from: FindAlexaFloatingButtonView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/view/FindAlexaFloatingButtonView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindAlexaFloatingButtonView extends ConstraintLayout {
    public FindAlexaFloatingButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(12);
        layoutParams.addRule(21);
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.find_alexa_floating_button_view, (ViewGroup) this, true);
        if (viewInflate == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout");
        }
        ((ConstraintLayout) viewInflate).setLayoutParams(layoutParams);
    }
}
