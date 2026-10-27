package com.android.providers.telephony;

import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class SqlQueryChecker {
    static void checkToken(String str) {
        if ("select".equals(str.toLowerCase())) {
            throw new IllegalArgumentException("SELECT token not allowed in query");
        }
    }

    static void checkQueryParametersForSubqueries(String[] strArr, String str, String str2) {
        if (strArr != null) {
            for (String str3 : strArr) {
                SQLiteTokenizer.tokenize(str3, 0, new Consumer() { // from class: com.android.providers.telephony.-$$Lambda$0AiRL9suPf7o1_c8HvQdRaQo1MA
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SqlQueryChecker.checkToken((String) obj);
                    }
                });
            }
        }
        SQLiteTokenizer.tokenize(str, 0, new Consumer() { // from class: com.android.providers.telephony.-$$Lambda$0AiRL9suPf7o1_c8HvQdRaQo1MA
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SqlQueryChecker.checkToken((String) obj);
            }
        });
        SQLiteTokenizer.tokenize(str2, 0, new Consumer() { // from class: com.android.providers.telephony.-$$Lambda$0AiRL9suPf7o1_c8HvQdRaQo1MA
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SqlQueryChecker.checkToken((String) obj);
            }
        });
    }
}
