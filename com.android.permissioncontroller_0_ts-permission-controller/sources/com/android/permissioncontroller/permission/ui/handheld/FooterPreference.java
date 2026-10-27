package com.android.permissioncontroller.permission.ui.handheld;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.R;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FooterPreference.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R$\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/android/permissioncontroller/permission/ui/handheld/FooterPreference;", "Landroidx/preference/Preference;", "c", "Landroid/content/Context;", "(Landroid/content/Context;)V", "a", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "attr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "res", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "value", "", "secondSummary", "getSecondSummary", "()Ljava/lang/CharSequence;", "setSecondSummary", "(Ljava/lang/CharSequence;)V", "secondSummaryView", "Landroid/widget/TextView;", "onBindViewHolder", "", "holder", "Landroidx/preference/PreferenceViewHolder;", "vendor__ts__proprietary__CarPackageManager__CarPermissionController__android_common__ts-permission-controller"}, k = 1, mv = {1, 1, 16})
public final class FooterPreference extends Preference {
    private CharSequence secondSummary;
    private TextView secondSummaryView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FooterPreference(Context c) {
        super(c);
        Intrinsics.checkParameterIsNotNull(c, "c");
        setLayoutResource(R.layout.footer_preference);
        this.secondSummary = "";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FooterPreference(Context c, AttributeSet a) {
        super(c, a);
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(a, "a");
        setLayoutResource(R.layout.footer_preference);
        this.secondSummary = "";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FooterPreference(Context c, AttributeSet a, int attr) {
        super(c, a, attr);
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(a, "a");
        setLayoutResource(R.layout.footer_preference);
        this.secondSummary = "";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FooterPreference(Context c, AttributeSet a, int attr, int res) {
        super(c, a, attr, res);
        Intrinsics.checkParameterIsNotNull(c, "c");
        Intrinsics.checkParameterIsNotNull(a, "a");
        setLayoutResource(R.layout.footer_preference);
        this.secondSummary = "";
    }

    public final CharSequence getSecondSummary() {
        return this.secondSummary;
    }

    public final void setSecondSummary(CharSequence value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        TextView textView = this.secondSummaryView;
        if (textView != null) {
            textView.setText(value);
        }
        this.secondSummary = value;
        notifyChanged();
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        View viewFindViewById = holder != null ? holder.findViewById(R.id.summary2) : null;
        if (viewFindViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView textView = (TextView) viewFindViewById;
        this.secondSummaryView = textView;
        if (textView != null) {
            textView.setText(this.secondSummary);
        }
        super.onBindViewHolder(holder);
    }
}
