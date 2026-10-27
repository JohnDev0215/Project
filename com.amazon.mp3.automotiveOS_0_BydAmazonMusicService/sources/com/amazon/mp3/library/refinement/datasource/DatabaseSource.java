package com.amazon.mp3.library.refinement.datasource;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.query.DatabaseQuery;
import com.google.android.gms.actions.SearchIntents;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: DatabaseSource.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003B7\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\t\u0012\u0006\u0010)\u001a\u00020\u001e¢\u0006\u0004\b*\u0010+J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J.\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u001e\u0010\n\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\tH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J/\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0010¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0004R,\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\t8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR(\u0010 \u001a\u0010\u0012\f\u0012\n \u001f*\u0004\u0018\u00010\u001e0\u001e0\u001d8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R6\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0006@DX\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010\u001a\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010(¨\u0006,"}, d2 = {"Lcom/amazon/mp3/library/refinement/datasource/DatabaseSource;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "Lcom/amazon/mp3/library/refinement/datasource/RefinableDataSource;", "Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;", "", SearchIntents.EXTRA_QUERY, "", "count", "Lkotlin/Function1;", "block", "updateQuery", "", "resetQuery", "", "secondarySort", "internalQuery$MusicExperienceCore_release", "(Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;Ljava/lang/String;)Ljava/util/List;", "internalQuery", "sortOrderString", "Landroid/database/Cursor;", "converter", "Lkotlin/jvm/functions/Function1;", "getConverter", "()Lkotlin/jvm/functions/Function1;", "originalQuery", "Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;", "getOriginalQuery", "()Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;", "Ljava/lang/ref/WeakReference;", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "contextRef", "Ljava/lang/ref/WeakReference;", "getContextRef", "()Ljava/lang/ref/WeakReference;", "<set-?>", "currentQuery", "getCurrentQuery", "setCurrentQuery", "(Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;)V", "context", "<init>", "(Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;Lkotlin/jvm/functions/Function1;Landroid/content/Context;)V", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public class DatabaseSource<R extends Refinable> implements RefinableDataSource<R, DatabaseQuery<R>> {
    private final WeakReference<Context> contextRef;
    private final Function1<Cursor, List<R>> converter;
    private DatabaseQuery<R> currentQuery;
    private final DatabaseQuery<R> originalQuery;

    /* JADX WARN: Multi-variable type inference failed */
    public DatabaseSource(DatabaseQuery<R> query, Function1<? super Cursor, ? extends List<? extends R>> converter, Context context) {
        Intrinsics.checkNotNullParameter(query, "query");
        Intrinsics.checkNotNullParameter(converter, "converter");
        Intrinsics.checkNotNullParameter(context, "context");
        this.converter = converter;
        this.originalQuery = query;
        this.contextRef = new WeakReference<>(context);
        this.currentQuery = DatabaseQuery.copy$default(query, null, null, null, null, 15, null);
    }

    protected final Function1<Cursor, List<R>> getConverter() {
        return this.converter;
    }

    protected final DatabaseQuery<R> getOriginalQuery() {
        return this.originalQuery;
    }

    protected final WeakReference<Context> getContextRef() {
        return this.contextRef;
    }

    public final DatabaseQuery<R> getCurrentQuery() {
        return this.currentQuery;
    }

    protected final void setCurrentQuery(DatabaseQuery<R> databaseQuery) {
        Intrinsics.checkNotNullParameter(databaseQuery, "<set-?>");
        this.currentQuery = databaseQuery;
    }

    public List<R> query() {
        return internalQuery$MusicExperienceCore_release$default(this, this.currentQuery, null, 2, null);
    }

    @Override // com.amazon.mp3.library.refinement.datasource.RefinableDataSource
    public int count() {
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", " Not yet implemented"));
    }

    public DatabaseSource<R> updateQuery(Function1<? super DatabaseQuery<R>, DatabaseQuery<R>> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        setCurrentQuery(block.invoke(getCurrentQuery()));
        return this;
    }

    public void resetQuery() {
        this.currentQuery = DatabaseQuery.copy$default(this.originalQuery, null, null, null, null, 15, null);
    }

    public static /* synthetic */ List internalQuery$MusicExperienceCore_release$default(DatabaseSource databaseSource, DatabaseQuery databaseQuery, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: internalQuery");
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return databaseSource.internalQuery$MusicExperienceCore_release(databaseQuery, str);
    }

    public synchronized List<R> internalQuery$MusicExperienceCore_release(DatabaseQuery<R> query, String secondarySort) {
        List<R> listEmptyList;
        ContentResolver contentResolver;
        Cursor cursorQuery;
        Intrinsics.checkNotNullParameter(query, "query");
        Context context = this.contextRef.get();
        listEmptyList = null;
        if (context != null && (contentResolver = context.getContentResolver()) != null && (cursorQuery = contentResolver.query(query.getUri(), query.getProjection(), query.selectionString(), query.selectionArgs(), sortOrderString(secondarySort))) != null) {
            try {
                List<R> listInvoke = getConverter().invoke(cursorQuery);
                CloseableKt.closeFinally(cursorQuery, null);
                listEmptyList = listInvoke;
            } finally {
            }
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    protected final String sortOrderString(String secondarySort) {
        String strSortOrderString = this.currentQuery.sortOrderString();
        if (secondarySort == null) {
            return strSortOrderString;
        }
        return ((strSortOrderString == null || strSortOrderString.length() == 0) || StringsKt__StringsKt.contains$default((CharSequence) strSortOrderString, (CharSequence) secondarySort, false, 2, (Object) null)) ? strSortOrderString : Intrinsics.stringPlus(strSortOrderString, Intrinsics.stringPlus(", ", secondarySort));
    }
}
