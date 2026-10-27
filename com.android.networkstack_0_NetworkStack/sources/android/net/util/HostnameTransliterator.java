package android.net.util;

import android.icu.text.Transliterator;
import android.text.TextUtils;
import android.util.Log;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class HostnameTransliterator {
    private final Transliterator mTransliterator;

    public HostnameTransliterator() {
        HashSet hashSet = new HashSet(Collections.list(Transliterator.getAvailableIDs()));
        StringBuilder sb = new StringBuilder();
        if (hashSet.contains("Any-ASCII")) {
            sb.append(":: Any-ASCII; ");
        } else if (hashSet.contains("Any-Latin") && hashSet.contains("Latin-ASCII")) {
            sb.append(":: Any-Latin; :: Latin-ASCII; ");
        } else {
            Log.e("HostnameTransliterator", "ICU Transliterator doesn't include supported ID");
            this.mTransliterator = null;
            return;
        }
        this.mTransliterator = Transliterator.createFromRules("", sb.toString(), 0);
    }

    public HostnameTransliterator(Transliterator transliterator) {
        this.mTransliterator = transliterator;
    }

    private String maybeRemoveRedundantSymbols(String str) {
        String strReplaceAll = str.replaceAll("[^a-zA-Z0-9-]", "-").replaceAll("-+", "-");
        if (strReplaceAll.startsWith("-")) {
            strReplaceAll = strReplaceAll.replaceFirst("-", "");
        }
        return strReplaceAll.endsWith("-") ? strReplaceAll.substring(0, strReplaceAll.length() - 1) : strReplaceAll;
    }

    public String transliterate(String str) {
        if (str == null) {
            return null;
        }
        Transliterator transliterator = this.mTransliterator;
        if (transliterator == null) {
            if (!str.matches("\\p{ASCII}*")) {
                return null;
            }
            String strMaybeRemoveRedundantSymbols = maybeRemoveRedundantSymbols(str);
            if (TextUtils.isEmpty(strMaybeRemoveRedundantSymbols)) {
                return null;
            }
            return strMaybeRemoveRedundantSymbols.length() > 63 ? strMaybeRemoveRedundantSymbols.substring(0, 63) : strMaybeRemoveRedundantSymbols;
        }
        String strMaybeRemoveRedundantSymbols2 = maybeRemoveRedundantSymbols(transliterator.transliterate(str));
        if (TextUtils.isEmpty(strMaybeRemoveRedundantSymbols2)) {
            return null;
        }
        return strMaybeRemoveRedundantSymbols2.length() > 63 ? strMaybeRemoveRedundantSymbols2.substring(0, 63) : strMaybeRemoveRedundantSymbols2;
    }
}
