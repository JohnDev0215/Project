package com.amazon.mp3.search.suggestion;

import android.database.MatrixCursor;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SearchSuggestionProvider {
    public static final String[] COLUMNS = {"_id", "suggest_text_1"};

    public static MatrixCursor getSuggestionsCursor(final List<String> suggestions) {
        if (suggestions == null) {
            return null;
        }
        MatrixCursor matrixCursor = new MatrixCursor(COLUMNS, suggestions.size());
        for (int i = 0; i < suggestions.size(); i++) {
            matrixCursor.addRow(new String[]{Integer.toString(i), suggestions.get(i)});
        }
        return matrixCursor;
    }
}
