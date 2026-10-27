package com.sun.istack.localization;

import com.sun.istack.localization.LocalizableMessageFactory;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
public final class LocalizableMessage implements Localizable {
    private final Object[] _args;
    private final String _bundlename;
    private final String _key;
    private final LocalizableMessageFactory.ResourceBundleSupplier _rbSupplier;

    @Deprecated
    public LocalizableMessage(String str, String str2, Object... objArr) {
        this(str, null, str2, objArr);
    }

    public LocalizableMessage(String str, LocalizableMessageFactory.ResourceBundleSupplier resourceBundleSupplier, String str2, Object... objArr) {
        this._bundlename = str;
        this._rbSupplier = resourceBundleSupplier;
        this._key = str2;
        this._args = objArr == null ? new Object[0] : objArr;
    }

    @Override // com.sun.istack.localization.Localizable
    public String getKey() {
        return this._key;
    }

    @Override // com.sun.istack.localization.Localizable
    public Object[] getArguments() {
        Object[] objArr = this._args;
        return Arrays.copyOf(objArr, objArr.length);
    }

    @Override // com.sun.istack.localization.Localizable
    public String getResourceBundleName() {
        return this._bundlename;
    }

    @Override // com.sun.istack.localization.Localizable
    public ResourceBundle getResourceBundle(Locale locale) {
        LocalizableMessageFactory.ResourceBundleSupplier resourceBundleSupplier = this._rbSupplier;
        if (resourceBundleSupplier == null) {
            return null;
        }
        return resourceBundleSupplier.getResourceBundle(locale);
    }
}
