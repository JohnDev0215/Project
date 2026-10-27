package com.sun.istack.localization;

import java.util.Locale;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
public class LocalizableMessageFactory {
    private final String _bundlename;
    private final ResourceBundleSupplier _rbSupplier;

    public interface ResourceBundleSupplier {
        ResourceBundle getResourceBundle(Locale locale);
    }

    @Deprecated
    public LocalizableMessageFactory(String str) {
        this._bundlename = str;
        this._rbSupplier = null;
    }

    public LocalizableMessageFactory(String str, ResourceBundleSupplier resourceBundleSupplier) {
        this._bundlename = str;
        this._rbSupplier = resourceBundleSupplier;
    }

    public Localizable getMessage(String str, Object... objArr) {
        return new LocalizableMessage(this._bundlename, this._rbSupplier, str, objArr);
    }
}
