package com.amazon.mp3.library.refinement.filter.database;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.filter.Filter;
import com.amazon.mp3.library.refinement.query.DatabaseQuery;
import com.amazon.mp3.library.refinement.query.arg.Arg;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.collections.SetsKt___SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DbFilter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00050\u0003B\u0015\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0002\u0010\tJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\b\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/library/refinement/filter/database/DbFilter;", "R", "Lcom/amazon/mp3/library/refinement/Refinable;", "Lcom/amazon/mp3/library/refinement/filter/Filter;", "Lcom/amazon/mp3/library/refinement/query/arg/Arg;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "columnName", "", "value", "(Ljava/lang/String;Lcom/amazon/mp3/library/refinement/query/arg/Arg;)V", "getColumnName", "()Ljava/lang/String;", "getValue", "()Lcom/amazon/mp3/library/refinement/query/arg/Arg;", "apply", "dataSource", "remove", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DbFilter<R extends Refinable> implements Filter<R, Arg, DatabaseSource<R>> {
    private final String columnName;
    private final Arg value;

    public DbFilter(String columnName, Arg value) {
        Intrinsics.checkNotNullParameter(columnName, "columnName");
        Intrinsics.checkNotNullParameter(value, "value");
        this.columnName = columnName;
        this.value = value;
    }

    public final String getColumnName() {
        return this.columnName;
    }

    public Arg getValue() {
        return this.value;
    }

    @Override // com.amazon.mp3.library.refinement.RefinementType
    public DatabaseSource<R> apply(DatabaseSource<R> dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        return dataSource.updateQuery(new Function1<DatabaseQuery<R>, DatabaseQuery<R>>(this) { // from class: com.amazon.mp3.library.refinement.filter.database.DbFilter.apply.1
            final /* synthetic */ DbFilter<R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final DatabaseQuery<R> invoke(DatabaseQuery<R> oldQuery) {
                Intrinsics.checkNotNullParameter(oldQuery, "oldQuery");
                Map<String, Set<Arg>> selection = oldQuery.getSelection();
                String columnName = this.this$0.getColumnName();
                Set<Arg> set = oldQuery.getSelection().get(this.this$0.getColumnName());
                Set setPlus = set == null ? null : SetsKt___SetsKt.plus((Set<? extends Arg>) ((Set<? extends Object>) set), this.this$0.getValue());
                if (setPlus == null) {
                    setPlus = SetsKt__SetsKt.linkedSetOf(this.this$0.getValue());
                }
                return DatabaseQuery.copy$default(oldQuery, null, null, MapsKt__MapsKt.plus(selection, TuplesKt.to(columnName, setPlus)), null, 11, null);
            }
        });
    }

    @Override // com.amazon.mp3.library.refinement.RefinementType
    public DatabaseSource<R> remove(DatabaseSource<R> dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        return dataSource.updateQuery(new Function1<DatabaseQuery<R>, DatabaseQuery<R>>(this) { // from class: com.amazon.mp3.library.refinement.filter.database.DbFilter.remove.1
            final /* synthetic */ DbFilter<R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final DatabaseQuery<R> invoke(DatabaseQuery<R> oldQuery) {
                Intrinsics.checkNotNullParameter(oldQuery, "oldQuery");
                Set<Arg> set = oldQuery.getSelection().get(this.this$0.getColumnName());
                if (set == null) {
                    return oldQuery;
                }
                Set setMinus = SetsKt___SetsKt.minus((Set<? extends Arg>) ((Set<? extends Object>) set), this.this$0.getValue());
                if (setMinus.isEmpty()) {
                    return DatabaseQuery.copy$default(oldQuery, null, null, MapsKt__MapsKt.minus((Map<? extends String, ? extends V>) ((Map<? extends Object, ? extends V>) oldQuery.getSelection()), this.this$0.getColumnName()), null, 11, null);
                }
                return DatabaseQuery.copy$default(oldQuery, null, null, MapsKt__MapsKt.plus(oldQuery.getSelection(), TuplesKt.to(this.this$0.getColumnName(), setMinus)), null, 11, null);
            }
        });
    }
}
