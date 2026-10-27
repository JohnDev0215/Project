package net.sourceforge.pinyin4j;

import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes.dex */
class ResourceHelper {
    static /* synthetic */ Class class$net$sourceforge$pinyin4j$ResourceHelper;

    ResourceHelper() {
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    static BufferedInputStream getResourceInputStream(String str) {
        Class clsClass$ = class$net$sourceforge$pinyin4j$ResourceHelper;
        if (clsClass$ == null) {
            clsClass$ = class$("net.sourceforge.pinyin4j.ResourceHelper");
            class$net$sourceforge$pinyin4j$ResourceHelper = clsClass$;
        }
        return new BufferedInputStream(clsClass$.getResourceAsStream(str));
    }
}
