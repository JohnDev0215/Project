package androidx.autofill.inline.common;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class BundledStyle {
    protected final Bundle mBundle;

    protected abstract String getStyleKey();

    protected BundledStyle(Bundle bundle) {
        this.mBundle = bundle;
    }

    public boolean isValid() {
        Bundle bundle = this.mBundle;
        return bundle != null && bundle.getBoolean(getStyleKey(), false);
    }
}
