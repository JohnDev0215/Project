package com.amazon.music.find.api.util;

import com.amazon.music.search.SpellCorrections;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: SpellCorrectionsUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/find/api/util/SpellCorrectionsUtil;", "", "()V", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SpellCorrectionsUtil {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: SpellCorrectionsUtil.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/find/api/util/SpellCorrectionsUtil$Companion;", "", "()V", "getExecutedTerm", "", SearchIntents.EXTRA_QUERY, "spellCorrections", "Lcom/amazon/music/search/SpellCorrections;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getExecutedTerm(String query, SpellCorrections spellCorrections) {
            List<String> autoCorrectionText;
            Intrinsics.checkNotNullParameter(query, "query");
            if (spellCorrections != null && (autoCorrectionText = spellCorrections.getAutoCorrectionText()) != null) {
                ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(autoCorrectionText, 10));
                for (String correctionTerm : autoCorrectionText) {
                    if (!StringsKt__StringsJVMKt.equals(correctionTerm, query, true)) {
                        Intrinsics.checkNotNullExpressionValue(correctionTerm, "correctionTerm");
                        return correctionTerm;
                    }
                    arrayList.add(Unit.INSTANCE);
                }
            }
            return query;
        }
    }
}
