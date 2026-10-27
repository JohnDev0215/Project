package com.amazon.mp3.library.refinement.filter.database;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.filter.Filter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DbFilters.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022(\u0012\u0004\u0012\u0002H\u0001\u0012\u0012\u0012\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00060\u0003B%\u0012\u001e\u0010\u0007\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\"\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\bJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016R$\u0010\t\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X\u0096\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/library/refinement/filter/database/DbFilters;", "R", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/mp3/library/refinement/filter/Filter;", "", "Lcom/amazon/mp3/library/refinement/filter/database/DbFilter;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "filters", "([Lcom/amazon/mp3/library/refinement/filter/database/DbFilter;)V", "value", "getValue", "()[Lcom/amazon/mp3/library/refinement/filter/database/DbFilter;", "[Lcom/amazon/mp3/library/refinement/filter/database/DbFilter;", "apply", "dataSource", "remove", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DbFilters<R extends Refinable> implements Filter<R, DbFilter<R>[], DatabaseSource<R>> {
    private final DbFilter<R>[] value;

    public DbFilters(DbFilter<R>... filters) {
        Intrinsics.checkNotNullParameter(filters, "filters");
        this.value = filters;
    }

    @Override // com.amazon.mp3.library.refinement.RefinementType
    public DatabaseSource<R> apply(DatabaseSource<R> dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        DbFilter<R>[] value = getValue();
        int length = value.length;
        int i = 0;
        while (i < length) {
            DbFilter<R> dbFilter = value[i];
            i++;
            dbFilter.apply((DatabaseSource) dataSource);
        }
        return dataSource;
    }

    @Override // com.amazon.mp3.library.refinement.RefinementType
    public DatabaseSource<R> remove(DatabaseSource<R> dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        DbFilter<R>[] value = getValue();
        int length = value.length;
        int i = 0;
        while (i < length) {
            DbFilter<R> dbFilter = value[i];
            i++;
            dbFilter.remove((DatabaseSource) dataSource);
        }
        return dataSource;
    }

    public DbFilter<R>[] getValue() {
        return this.value;
    }
}
