package androidx.preference;

import android.R;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // androidx.preference.Preference
    public boolean E() {
        return false;
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        TextView textView;
        super.M(fVar);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            fVar.f1092a.setAccessibilityHeading(true);
            return;
        }
        if (i < 21) {
            TypedValue typedValue = new TypedValue();
            if (j().getTheme().resolveAttribute(androidx.appcompat.R$attr.colorAccent, typedValue, true) && (textView = (TextView) fVar.M(R.id.title)) != null) {
                if (textView.getCurrentTextColor() != androidx.core.content.a.b(j(), R$color.preference_fallback_accent_color)) {
                    return;
                }
                textView.setTextColor(typedValue.data);
            }
        }
    }

    @Override // androidx.preference.Preference
    public boolean z0() {
        return !super.E();
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, androidx.core.content.res.f.a(context, R$attr.preferenceCategoryStyle, R.attr.preferenceCategoryStyle));
    }
}
