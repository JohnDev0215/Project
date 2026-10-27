package org.chromium.components.browser_ui.settings;

import android.R;
import android.content.Context;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceGroup;
import defpackage.bz;
import defpackage.n32;
import org.chromium.ui.drawable.StateListDrawableBuilder;
import org.chromium.ui.widget.CheckableImageView;

/* JADX INFO: loaded from: classes2.dex */
public class ExpandablePreferenceGroup extends PreferenceGroup {
    public boolean b0;
    public AnimatedStateListDrawable c0;

    public ExpandablePreferenceGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R$attr.preferenceStyle);
        this.b0 = true;
        this.K = R$layout.checkable_image_view_widget;
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        AnimatedStateListDrawable animatedStateListDrawable = this.c0;
        Context context = this.e;
        if (animatedStateListDrawable == null) {
            StateListDrawableBuilder stateListDrawableBuilder = new StateListDrawableBuilder(context);
            StateListDrawableBuilder.a aVarA = stateListDrawableBuilder.a(R$drawable.ic_expand_down, R.attr.state_checked);
            StateListDrawableBuilder.a aVarA2 = stateListDrawableBuilder.a(R$drawable.ic_expand_up, new int[0]);
            stateListDrawableBuilder.b(aVarA, aVarA2, R$drawable.ic_expand_down_to_up_animation);
            stateListDrawableBuilder.b(aVarA2, aVarA, R$drawable.ic_expand_up_to_down_animation);
            AnimatedStateListDrawable animatedStateListDrawableC = stateListDrawableBuilder.c();
            animatedStateListDrawableC.setTintList(bz.b(context, R$color.default_icon_color_tint_list));
            this.c0 = animatedStateListDrawableC;
        }
        CheckableImageView checkableImageView = (CheckableImageView) n32Var.u(R$id.checkable_image_view);
        checkableImageView.setImageDrawable(this.c0);
        checkableImageView.setChecked(this.b0);
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.l);
        sb.append(context.getResources().getString(this.b0 ? R$string.accessibility_expanded_group : R$string.accessibility_collapsed_group));
        String string = sb.toString();
        View view = n32Var.e;
        view.setContentDescription(string);
        if (view.isAccessibilityFocused()) {
            view.sendAccessibilityEvent(4);
        }
    }
}
