package com.amazon.mp3.library.refinement.datasource;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.query.DatabaseQuery;
import com.amazon.mp3.library.refinement.query.arg.ListArg;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: DatabaseSourcePaged.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003BK\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n0\u0010\u0012\u0006\u0010$\u001a\u00020#\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\u0018\u001a\u00020\u000b¢\u0006\u0004\b%\u0010&J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0002J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\b\u0010\u000f\u001a\u00020\u0007H\u0016J.\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u001e\u0010\u0012\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00110\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\u0006\u0010\u0015\u001a\u00020\u0004R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017R(\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00118\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0018\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!¨\u0006'"}, d2 = {"Lcom/amazon/mp3/library/refinement/datasource/DatabaseSourcePaged;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "", "lookupIds", "updateQueryIds", "", "pageIndex", "pageSize", "", "", "getQueryIds", "getAllIds", SearchIntents.EXTRA_QUERY, "count", "Lkotlin/Function1;", "Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;", "block", "updateQuery", "resetQuery", "close", "secondarySort", "Ljava/lang/String;", "idColumnName", "pagedQuery", "Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;", "getPagedQuery$MusicExperienceCore_release", "()Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;", "setPagedQuery$MusicExperienceCore_release", "(Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;)V", "Landroid/database/Cursor;", "currentCursor", "Landroid/database/Cursor;", "converter", "Landroid/content/Context;", "context", "<init>", "(Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;Lkotlin/jvm/functions/Function1;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public class DatabaseSourcePaged<R extends Refinable> extends DatabaseSource<R> {
    private Cursor currentCursor;
    private final String idColumnName;
    private DatabaseQuery<R> pagedQuery;
    private final String secondarySort;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DatabaseSourcePaged(DatabaseQuery<R> query, Function1<? super Cursor, ? extends List<? extends R>> converter, Context context, String str, String idColumnName) {
        super(query, converter, context);
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(converter, "converter");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(idColumnName, "idColumnName");
        this.secondarySort = str;
        this.idColumnName = idColumnName;
        this.pagedQuery = DatabaseQuery.copy$default(getOriginalQuery(), null, null, null, null, 15, null);
        synchronized (this) {
            setCurrentQuery(DatabaseQuery.copy$default(getOriginalQuery(), null, null, null, null, 15, null));
            lookupIds();
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.mp3.library.refinement.datasource.DatabaseSource
    public synchronized List<R> query() {
        List<R> listInternalQuery$MusicExperienceCore_release;
        updateQueryIds();
        if (count() == 0) {
            listInternalQuery$MusicExperienceCore_release = CollectionsKt__CollectionsKt.emptyList();
        } else {
            listInternalQuery$MusicExperienceCore_release = internalQuery$MusicExperienceCore_release(getCurrentQuery(), this.secondarySort);
        }
        return listInternalQuery$MusicExperienceCore_release;
    }

    public final synchronized List<R> query(int pageIndex, int pageSize) {
        if (pageSize <= 0) {
            return query();
        }
        DatabaseQuery<R> databaseQueryCopy$default = DatabaseQuery.copy$default(getOriginalQuery(), null, null, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(this.idColumnName, SetsKt__SetsJVMKt.setOf(new ListArg(getQueryIds(pageIndex, pageSize), null, 2, null)))), getCurrentQuery().getSortColumn(), 3, null);
        this.pagedQuery = databaseQueryCopy$default;
        return internalQuery$MusicExperienceCore_release(databaseQueryCopy$default, this.secondarySort);
    }

    @Override // com.amazon.mp3.library.refinement.datasource.DatabaseSource, com.amazon.mp3.library.refinement.datasource.RefinableDataSource
    public synchronized int count() {
        Cursor cursor;
        cursor = this.currentCursor;
        return cursor == null ? 0 : cursor.getCount();
    }

    @Override // com.amazon.mp3.library.refinement.datasource.DatabaseSource
    public synchronized DatabaseSourcePaged<R> updateQuery(Function1<? super DatabaseQuery<R>, DatabaseQuery<R>> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        setCurrentQuery(block.invoke(getCurrentQuery()));
        lookupIds();
        return this;
    }

    @Override // com.amazon.mp3.library.refinement.datasource.DatabaseSource
    public synchronized void resetQuery() {
        setCurrentQuery(DatabaseQuery.copy$default(getOriginalQuery(), null, null, null, null, 15, null));
        lookupIds();
    }

    private final synchronized void lookupIds() {
        ContentResolver contentResolver;
        Cursor cursor = this.currentCursor;
        if (cursor != null) {
            cursor.close();
        }
        Cursor cursorQuery = null;
        this.currentCursor = null;
        if (StringsKt__StringsJVMKt.isBlank(this.idColumnName)) {
            return;
        }
        Context context = getContextRef().get();
        if (context != null && (contentResolver = context.getContentResolver()) != null) {
            cursorQuery = contentResolver.query(getCurrentQuery().getUri(), getCurrentQuery().getProjection(), getCurrentQuery().selectionString(), getCurrentQuery().selectionArgs(), sortOrderString(this.secondarySort));
        }
        this.currentCursor = cursorQuery;
    }

    private final void updateQueryIds() {
        if (count() != 0) {
            this.pagedQuery = DatabaseQuery.copy$default(getOriginalQuery(), null, null, MapsKt__MapsJVMKt.mapOf(TuplesKt.to(this.idColumnName, SetsKt__SetsKt.linkedSetOf(new ListArg(getAllIds(), null, 2, null)))), null, 11, null);
        } else {
            DatabaseQuery.copy$default(getCurrentQuery(), null, null, null, null, 15, null);
            lookupIds();
        }
    }

    private final List<String> getQueryIds(int pageIndex, int pageSize) {
        IntRange intRangeUntil;
        int first;
        int last;
        List<String> mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) new ArrayList());
        Cursor cursor = this.currentCursor;
        if (cursor != null && (first = (intRangeUntil = RangesKt___RangesKt.until(0, pageSize)).getFirst()) <= (last = intRangeUntil.getLast())) {
            while (true) {
                int i = first + 1;
                if (cursor.moveToPosition((pageIndex * pageSize) + first)) {
                    mutableList.add(String.valueOf(cursor.getLong(cursor.getColumnIndex(this.idColumnName))));
                }
                if (first == last) {
                    break;
                }
                first = i;
            }
        }
        return mutableList;
    }

    private final List<String> getAllIds() {
        return getQueryIds(0, count());
    }

    public final void close() {
        Cursor cursor = this.currentCursor;
        if (cursor != null) {
            cursor.close();
        }
        this.currentCursor = null;
    }
}
