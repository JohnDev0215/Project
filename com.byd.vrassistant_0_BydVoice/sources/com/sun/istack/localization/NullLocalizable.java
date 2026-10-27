package com.sun.istack.localization;

import java.util.Locale;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
public final class NullLocalizable implements Localizable {
    private final String msg;

    @Override // com.sun.istack.localization.Localizable
    public String getKey() {
        return "\u0000";
    }

    @Override // com.sun.istack.localization.Localizable
    public ResourceBundle getResourceBundle(Locale locale) {
        return null;
    }

    @Override // com.sun.istack.localization.Localizable
    public String getResourceBundleName() {
        return "";
    }

    public NullLocalizable(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        this.msg = str;
    }

    @Override // com.sun.istack.localization.Localizable
    public Object[] getArguments() {
        return new Object[]{this.msg};
    }
}
