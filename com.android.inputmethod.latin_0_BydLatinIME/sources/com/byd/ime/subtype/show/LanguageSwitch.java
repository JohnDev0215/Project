package com.byd.ime.subtype.show;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Switch;
import com.android.inputmethod.latin.R;
import kotlin.Metadata;

/* JADX INFO: compiled from: LanguageSwitch.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u001b\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e¨\u0006\u0010"}, d2 = {"Lcom/byd/ime/subtype/show/LanguageSwitch;", "Landroid/widget/Switch;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "setChecked", "", "checked", "", "fromUser", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class LanguageSwitch extends Switch {
    public LanguageSwitch(Context context) {
        this(context, null, 0);
    }

    public LanguageSwitch(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LanguageSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public final void setChecked(boolean checked, boolean fromUser) {
        Drawable drawable;
        super.setChecked(checked);
        if (checked) {
            drawable = getResources().getDrawable(R.drawable.check_state_check, null);
        } else {
            drawable = getResources().getDrawable(R.drawable.check_state_normal, null);
        }
        setBackground(drawable);
    }
}
