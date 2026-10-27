package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BrushRecentSearchConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00042\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushRecentSearchConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushRecentSearchConverter implements BaseContainerModelConverter<List<? extends String>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: BrushRecentSearchConverter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushRecentSearchConverter$Companion;", "", "()V", "RECENT_SEARCHES_TAG", "", "containsRecentSearchesTag", "", "tags", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean containsRecentSearchesTag(List<String> tags) {
            Intrinsics.checkNotNullParameter(tags, "tags");
            Iterator<String> it = tags.iterator();
            while (it.hasNext()) {
                if (StringsKt__StringsKt.contains$default((CharSequence) it.next(), (CharSequence) "recent-searches", false, 2, (Object) null)) {
                    return true;
                }
            }
            return false;
        }
    }
}
