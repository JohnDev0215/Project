package com.amplifyframework.logging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.util.Environment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class LoggingCategory extends Category<LoggingPlugin<?>> implements LoggingCategoryBehavior {
    private final LoggingPlugin<?> defaultPlugin;

    public LoggingCategory() {
        this(Environment.isJUnitTest() ? new JavaLoggingPlugin() : new AndroidLoggingPlugin());
    }

    @VisibleForTesting
    LoggingCategory(LoggingPlugin<?> loggingPlugin) {
        this.defaultPlugin = loggingPlugin;
    }

    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.LOGGING;
    }

    @Override // com.amplifyframework.logging.LoggingCategoryBehavior
    @NonNull
    public Logger forNamespace(@Nullable String str) {
        HashSet hashSet = new HashSet(getPlugins());
        hashSet.add(this.defaultPlugin);
        ArrayList arrayList = new ArrayList();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            arrayList.add(((LoggingPlugin) it.next()).forNamespace(str));
        }
        return new BroadcastLogger(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amplifyframework.core.category.Category
    @NonNull
    public LoggingPlugin<?> getSelectedPlugin() throws IllegalStateException {
        throw new UnsupportedOperationException("Getting the selected logging plugin is not supported.");
    }
}
