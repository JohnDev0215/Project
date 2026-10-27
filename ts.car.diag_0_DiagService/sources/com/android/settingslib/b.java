package com.android.settingslib;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.f;
import com.android.settingslib.a;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f1338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f1339b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a.C0045a f1340c;
    private String d;
    private boolean e;

    public b(Context context, Preference preference, AttributeSet attributeSet) {
        CharSequence text;
        this.d = null;
        boolean z = false;
        this.e = false;
        this.f1338a = context;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RestrictedPreference);
            TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(R$styleable.RestrictedPreference_userRestriction);
            if (typedValuePeekValue == null || typedValuePeekValue.type != 3) {
                text = null;
            } else {
                int i = typedValuePeekValue.resourceId;
                text = i != 0 ? context.getText(i) : typedValuePeekValue.string;
            }
            String string = text == null ? null : text.toString();
            this.d = string;
            if (RestrictedLockUtilsInternal.d(this.f1338a, string, UserHandle.myUserId())) {
                this.d = null;
                return;
            }
            TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(R$styleable.RestrictedPreference_useAdminDisabledSummary);
            if (typedValuePeekValue2 != null) {
                if (typedValuePeekValue2.type == 18 && typedValuePeekValue2.data != 0) {
                    z = true;
                }
                this.e = z;
            }
        }
    }

    public boolean a() {
        return this.f1339b;
    }

    public void b(f fVar) {
        TextView textView;
        if (this.f1339b) {
            fVar.f1092a.setEnabled(true);
        }
        if (!this.e || (textView = (TextView) fVar.M(R.id.summary)) == null) {
            return;
        }
        CharSequence text = textView.getContext().getText(R$string.disabled_by_admin_summary_text);
        if (this.f1339b) {
            textView.setText(text);
        } else if (TextUtils.equals(text, textView.getText())) {
            textView.setText((CharSequence) null);
        }
    }

    public boolean c() {
        if (!this.f1339b) {
            return false;
        }
        a.c(this.f1338a, this.f1340c);
        return true;
    }

    public void d(boolean z) {
        this.e = z;
    }
}
