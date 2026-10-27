package org.chromium.components.browser_ui.site_settings;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.DialogPreference;
import defpackage.n32;

/* JADX INFO: loaded from: classes2.dex */
public class ClearWebsiteStorage extends DialogPreference {
    public Context Y;
    public String Z;
    public boolean a0;
    public boolean b0;

    public ClearWebsiteStorage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.X = R$layout.clear_data_dialog;
        this.Y = context;
    }

    @Override // androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        this.T = this.Y.getString(!this.b0 ? this.a0 ? R$string.webstorage_clear_data_dialog_message_single_with_app : R$string.webstorage_clear_data_dialog_message_single : this.a0 ? R$string.webstorage_clear_data_dialog_message_group_with_app : R$string.webstorage_clear_data_dialog_message_group, this.Z);
    }

    public ClearWebsiteStorage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.X = R$layout.clear_data_dialog;
        this.Y = context;
    }
}
