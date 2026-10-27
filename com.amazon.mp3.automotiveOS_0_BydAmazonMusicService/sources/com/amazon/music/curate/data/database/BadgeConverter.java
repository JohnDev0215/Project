package com.amazon.music.curate.data.database;

import androidx.room.TypeConverter;
import com.amazon.music.curate.model.Badge;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BadgeConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0016\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0007¨\u0006\r"}, d2 = {"Lcom/amazon/music/curate/data/database/BadgeConverter;", "", "()V", "toBadge", "Lcom/amazon/music/curate/model/Badge;", "ordinal", "", "toBadges", "", "string", "", "toString", "badges", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BadgeConverter {
    @TypeConverter
    public final List<Badge> toBadges(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        if (string.length() == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        List listSplit$default = StringsKt__StringsKt.split$default((CharSequence) string, new String[]{","}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listSplit$default, 10));
        Iterator it = listSplit$default.iterator();
        while (it.hasNext()) {
            arrayList.add(toBadge(Integer.parseInt((String) it.next())));
        }
        return arrayList;
    }

    private final Badge toBadge(int ordinal) {
        if (ordinal == 0) {
            return Badge.HD;
        }
        if (ordinal == 1) {
            return Badge.ULTRA_HD;
        }
        if (ordinal == 2) {
            return Badge.LYRICS;
        }
        throw new IllegalArgumentException("Invalid");
    }

    @TypeConverter
    public final String toString(List<? extends Badge> badges) {
        Intrinsics.checkNotNullParameter(badges, "badges");
        String str = "";
        if (badges.isEmpty()) {
            return "";
        }
        Iterator<T> it = badges.iterator();
        while (it.hasNext()) {
            str = str + ((Badge) it.next()).ordinal() + ',';
        }
        String strSubstring = str.substring(0, str.length() - 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
