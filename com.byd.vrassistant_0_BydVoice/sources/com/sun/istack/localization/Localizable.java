package com.sun.istack.localization;

import java.util.Locale;
import java.util.ResourceBundle;

/* JADX INFO: loaded from: classes3.dex */
public interface Localizable {
    public static final String NOT_LOCALIZABLE = "\u0000";

    Object[] getArguments();

    String getKey();

    ResourceBundle getResourceBundle(Locale locale);

    String getResourceBundleName();
}
