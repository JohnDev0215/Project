package com.amazon.mp3.library.refinement.query;

import android.net.Uri;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.library.refinement.query.arg.Arg;
import com.amazon.mp3.library.refinement.sort.SortOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DatabaseQuery.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003BQ\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006\u0012\u001a\b\u0002\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u0012\u0012\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u0012¢\u0006\u0004\b$\u0010%J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0017\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0013\u0010\f\u001a\u00020\u000b2\b\u0010\n\u001a\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\u000e\u001a\u00020\rH\u0016Jd\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00062\u001a\b\u0002\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u00122\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u0012HÆ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001f\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u001e\u001a\u0004\b\u001f\u0010\bR)\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\u00128\u0006¢\u0006\f\n\u0004\b\u0015\u0010 \u001a\u0004\b!\u0010\"R#\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u00128\u0006¢\u0006\f\n\u0004\b\u0017\u0010 \u001a\u0004\b#\u0010\"¨\u0006&"}, d2 = {"Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;", "Lcom/amazon/mp3/library/refinement/Refinable;", "R", "", "", "selectionString", "", "selectionArgs", "()[Ljava/lang/String;", "sortOrderString", "other", "", "equals", "", "hashCode", "Landroid/net/Uri;", "uri", "projection", "", "", "Lcom/amazon/mp3/library/refinement/query/arg/Arg;", "selection", "Lcom/amazon/mp3/library/refinement/sort/SortOrder;", "sortColumn", "copy", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)Lcom/amazon/mp3/library/refinement/query/DatabaseQuery;", "toString", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "[Ljava/lang/String;", "getProjection", "Ljava/util/Map;", "getSelection", "()Ljava/util/Map;", "getSortColumn", "<init>", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)V", "MusicExperienceCore_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class DatabaseQuery<R extends Refinable> {
    private final String[] projection;
    private final Map<String, Set<Arg>> selection;
    private final Map<String, SortOrder> sortColumn;
    private final Uri uri;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DatabaseQuery copy$default(DatabaseQuery databaseQuery, Uri uri, String[] strArr, Map map, Map map2, int i, Object obj) {
        if ((i & 1) != 0) {
            uri = databaseQuery.uri;
        }
        if ((i & 2) != 0) {
            strArr = databaseQuery.projection;
        }
        if ((i & 4) != 0) {
            map = databaseQuery.selection;
        }
        if ((i & 8) != 0) {
            map2 = databaseQuery.sortColumn;
        }
        return databaseQuery.copy(uri, strArr, map, map2);
    }

    public final DatabaseQuery<R> copy(Uri uri, String[] projection, Map<String, ? extends Set<? extends Arg>> selection, Map<String, ? extends SortOrder> sortColumn) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(sortColumn, "sortColumn");
        return new DatabaseQuery<>(uri, projection, selection, sortColumn);
    }

    public String toString() {
        return "DatabaseQuery(uri=" + this.uri + ", projection=" + Arrays.toString(this.projection) + ", selection=" + this.selection + ", sortColumn=" + this.sortColumn + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DatabaseQuery(Uri uri, String[] strArr, Map<String, ? extends Set<? extends Arg>> selection, Map<String, ? extends SortOrder> sortColumn) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(selection, "selection");
        Intrinsics.checkNotNullParameter(sortColumn, "sortColumn");
        this.uri = uri;
        this.projection = strArr;
        this.selection = selection;
        this.sortColumn = sortColumn;
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final String[] getProjection() {
        return this.projection;
    }

    public /* synthetic */ DatabaseQuery(Uri uri, String[] strArr, Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(uri, strArr, (i & 4) != 0 ? new LinkedHashMap() : map, (i & 8) != 0 ? MapsKt__MapsKt.emptyMap() : map2);
    }

    public final Map<String, Set<Arg>> getSelection() {
        return this.selection;
    }

    public final Map<String, SortOrder> getSortColumn() {
        return this.sortColumn;
    }

    public final String selectionString() {
        if (this.selection.isEmpty()) {
            return null;
        }
        return CollectionsKt___CollectionsKt.joinToString$default(this.selection.entrySet(), " AND ", null, null, 0, null, new Function1<Map.Entry<? extends String, ? extends Set<? extends Arg>>, CharSequence>() { // from class: com.amazon.mp3.library.refinement.query.DatabaseQuery.selectionString.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Map.Entry<? extends String, ? extends Set<? extends Arg>> entry) {
                return invoke2((Map.Entry<String, ? extends Set<? extends Arg>>) entry);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final CharSequence invoke2(final Map.Entry<String, ? extends Set<? extends Arg>> entry) {
                Intrinsics.checkNotNullParameter(entry, "entry");
                return CollectionsKt___CollectionsKt.joinToString$default(entry.getValue(), " AND ", null, null, 0, null, new Function1<Arg, CharSequence>() { // from class: com.amazon.mp3.library.refinement.query.DatabaseQuery.selectionString.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final CharSequence invoke(Arg it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return entry.getKey() + ' ' + it.getSelectionString();
                    }
                }, 30, null);
            }
        }, 30, null);
    }

    public final String[] selectionArgs() {
        if (this.selection.isEmpty()) {
            return null;
        }
        List listFlatten = CollectionsKt__IterablesKt.flatten(this.selection.values());
        ArrayList arrayList = new ArrayList();
        Iterator it = listFlatten.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((Arg) it.next()).getSelectionArgs());
        }
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    public final String sortOrderString() {
        if (this.sortColumn.isEmpty()) {
            return null;
        }
        Map<String, SortOrder> map = this.sortColumn;
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry<String, SortOrder> entry : map.entrySet()) {
            arrayList.add(entry.getKey() + ' ' + entry.getValue().getValue());
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, ", ", null, null, 0, null, null, 62, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(DatabaseQuery.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            DatabaseQuery databaseQuery = (DatabaseQuery) other;
            if (!Intrinsics.areEqual(this.uri, databaseQuery.uri)) {
                return false;
            }
            String[] strArr = this.projection;
            if (strArr != null) {
                String[] strArr2 = databaseQuery.projection;
                if (strArr2 == null || !Arrays.equals(strArr, strArr2)) {
                    return false;
                }
            } else if (databaseQuery.projection != null) {
                return false;
            }
            return Intrinsics.areEqual(this.selection, databaseQuery.selection) && Intrinsics.areEqual(this.sortColumn, databaseQuery.sortColumn);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.library.refinement.query.DatabaseQuery<*>");
    }

    public int hashCode() {
        int iHashCode = this.uri.hashCode() * 31;
        String[] strArr = this.projection;
        return ((((iHashCode + (strArr == null ? 0 : Arrays.hashCode(strArr))) * 31) + this.selection.hashCode()) * 31) + this.sortColumn.hashCode();
    }
}
