package com.amazon.mp3.library.refinement.sort;

import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.datasource.DatabaseSource;
import com.amazon.mp3.library.refinement.query.DatabaseQuery;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DbSort.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003B\u001b\b\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\r\u001a\u00020\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/library/refinement/sort/DbSort;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/sort/Sort;", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "dataSource", "apply", "", "columnName", "Ljava/lang/String;", "getColumnName", "()Ljava/lang/String;", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "sortOrder", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "getSortOrder", "()Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "<init>", "(Ljava/lang/String;Lcom/amazon/mp3/library/refinement/sort/SortOrder;)V", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public final class DbSort<R extends Refinable> implements Sort<R, DatabaseSource<R>> {
    private final String columnName;
    private final SortOrder sortOrder;

    @JvmOverloads
    public DbSort(String columnName, SortOrder sortOrder) {
        Intrinsics.checkNotNullParameter(columnName, "columnName");
        Intrinsics.checkNotNullParameter(sortOrder, "sortOrder");
        this.columnName = columnName;
        this.sortOrder = sortOrder;
    }

    public final String getColumnName() {
        return this.columnName;
    }

    public /* synthetic */ DbSort(String str, SortOrder sortOrder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? SortOrder.ASCENDING : sortOrder);
    }

    public SortOrder getSortOrder() {
        return this.sortOrder;
    }

    @Override // com.amazon.mp3.library.refinement.RefinementType
    public DatabaseSource<R> apply(DatabaseSource<R> dataSource) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        dataSource.updateQuery(new Function1<DatabaseQuery<R>, DatabaseQuery<R>>(this) { // from class: com.amazon.mp3.library.refinement.sort.DbSort$apply$1$1
            final /* synthetic */ DbSort<R> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function1
            public final DatabaseQuery<R> invoke(DatabaseQuery<R> data) {
                Intrinsics.checkNotNullParameter(data, "data");
                return DatabaseQuery.copy$default(data, null, null, null, MapsKt__MapsKt.plus(data.getSortColumn(), TuplesKt.to(this.this$0.getColumnName(), this.this$0.getSortOrder())), 7, null);
            }
        });
        return dataSource;
    }
}
