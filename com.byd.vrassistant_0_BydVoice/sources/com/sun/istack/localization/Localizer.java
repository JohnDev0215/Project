package com.sun.istack.localization;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
public class Localizer {
    private final Locale _locale;
    private final HashMap<String, ResourceBundle> _resourceBundles;

    public Localizer() {
        this(Locale.getDefault());
    }

    public Localizer(Locale locale) {
        this._locale = locale;
        this._resourceBundles = new HashMap<>();
    }

    public Locale getLocale() {
        return this._locale;
    }

    public String localize(Localizable localizable) {
        String string;
        String key = localizable.getKey();
        if (key == "\u0000") {
            return (String) localizable.getArguments()[0];
        }
        String resourceBundleName = localizable.getResourceBundleName();
        try {
            ResourceBundle bundle = this._resourceBundles.get(resourceBundleName);
            if (bundle == null && (bundle = localizable.getResourceBundle(this._locale)) != null) {
                this._resourceBundles.put(resourceBundleName, bundle);
            }
            if (bundle == null) {
                try {
                    bundle = ResourceBundle.getBundle(resourceBundleName, this._locale);
                } catch (MissingResourceException unused) {
                    int iLastIndexOf = resourceBundleName.lastIndexOf(46);
                    if (iLastIndexOf != -1) {
                        try {
                            try {
                                bundle = ResourceBundle.getBundle(resourceBundleName.substring(iLastIndexOf + 1), this._locale);
                            } catch (MissingResourceException unused2) {
                                return getDefaultMessage(localizable);
                            }
                        } catch (MissingResourceException unused3) {
                            bundle = ResourceBundle.getBundle(resourceBundleName, this._locale, Thread.currentThread().getContextClassLoader());
                        }
                    }
                }
                this._resourceBundles.put(resourceBundleName, bundle);
            }
            if (bundle == null) {
                return getDefaultMessage(localizable);
            }
            if (key == null) {
                key = "undefined";
            }
            try {
                string = bundle.getString(key);
            } catch (MissingResourceException unused4) {
                string = bundle.getString("undefined");
            }
            Object[] arguments = localizable.getArguments();
            for (int i = 0; i < arguments.length; i++) {
                Object obj = arguments[i];
                if (obj instanceof Localizable) {
                    arguments[i] = localize((Localizable) obj);
                }
            }
            return MessageFormat.format(string, arguments);
        } catch (MissingResourceException unused5) {
            return getDefaultMessage(localizable);
        }
    }

    private String getDefaultMessage(Localizable localizable) {
        String key = localizable.getKey();
        Object[] arguments = localizable.getArguments();
        StringBuilder sb = new StringBuilder("[failed to localize] ");
        sb.append(key);
        if (arguments != null) {
            sb.append('(');
            for (int i = 0; i < arguments.length; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(String.valueOf(arguments[i]));
            }
            sb.append(')');
        }
        return sb.toString();
    }
}
