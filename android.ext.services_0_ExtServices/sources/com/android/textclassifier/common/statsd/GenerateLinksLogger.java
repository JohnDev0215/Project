package com.android.textclassifier.common.statsd;

import android.util.StatsEvent;
import android.util.StatsLog;
import android.view.textclassifier.TextLinks;
import androidx.collection.ArrayMap;
import com.android.textclassifier.common.base.TcLog;
import com.android.textclassifier.common.logging.ResultIdUtils;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public final class GenerateLinksLogger {
    private final Random random;
    private final Supplier<String> randomUuidSupplier;
    private final int sampleRate;

    public GenerateLinksLogger(int i) {
        this(i, new Supplier() { // from class: com.android.textclassifier.common.statsd.-$$Lambda$GenerateLinksLogger$Pc3PlDwg4TEgsFqlhikIPj1R5GA
            @Override // java.util.function.Supplier
            public final Object get() {
                return UUID.randomUUID().toString();
            }
        });
    }

    GenerateLinksLogger(int i, Supplier<String> supplier) {
        this.sampleRate = i;
        this.random = new Random();
        Preconditions.checkNotNull(supplier);
        this.randomUuidSupplier = supplier;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void logGenerateLinks(CharSequence charSequence, TextLinks textLinks, String str, long j, Optional<ResultIdUtils.ModelInfo> optional, Optional<ResultIdUtils.ModelInfo> optional2) {
        String entity;
        Preconditions.checkNotNull(charSequence);
        Preconditions.checkNotNull(textLinks);
        Preconditions.checkNotNull(str);
        if (shouldLog()) {
            LinkifyStats linkifyStats = new LinkifyStats();
            ArrayMap arrayMap = new ArrayMap();
            for (TextLinks.TextLink textLink : textLinks.getLinks()) {
                if (textLink.getEntityCount() != 0 && (entity = textLink.getEntity(0)) != null && !"other".equals(entity) && !"".equals(entity)) {
                    linkifyStats.countLink(textLink);
                    ((LinkifyStats) arrayMap.computeIfAbsent(entity, new Function() { // from class: com.android.textclassifier.common.statsd.-$$Lambda$GenerateLinksLogger$Ppb_G2fUFe9AHi7fb5Jb5NKe9nU
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return GenerateLinksLogger.lambda$logGenerateLinks$1((String) obj);
                        }
                    })).countLink(textLink);
                }
            }
            String str2 = this.randomUuidSupplier.get();
            writeStats(str2, str, null, linkifyStats, charSequence, j, optional, optional2);
            UnmodifiableIterator it = ImmutableList.sortedCopyOf(arrayMap.keySet()).iterator();
            while (it.hasNext()) {
                String str3 = (String) it.next();
                writeStats(str2, str, str3, (LinkifyStats) arrayMap.get(str3), charSequence, j, optional, optional2);
            }
        }
    }

    static /* synthetic */ LinkifyStats lambda$logGenerateLinks$1(String str) {
        return new LinkifyStats();
    }

    private boolean shouldLog() {
        int i = this.sampleRate;
        return i <= 1 || this.random.nextInt(i) == 0;
    }

    private static void writeStats(String str, String str2, String str3, LinkifyStats linkifyStats, CharSequence charSequence, long j, Optional<ResultIdUtils.ModelInfo> optional, Optional<ResultIdUtils.ModelInfo> optional2) {
        String str4 = (String) optional.transform(new com.google.common.base.Function() { // from class: com.android.textclassifier.common.statsd.-$$Lambda$oDz1qPSnjjDY1RvRhoswzP9Xa8s
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return ((ResultIdUtils.ModelInfo) obj).toModelName();
            }
        }).or("");
        String str5 = (String) optional2.transform(new com.google.common.base.Function() { // from class: com.android.textclassifier.common.statsd.-$$Lambda$oDz1qPSnjjDY1RvRhoswzP9Xa8s
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return ((ResultIdUtils.ModelInfo) obj).toModelName();
            }
        }).or("");
        StatsLog.write(StatsEvent.newBuilder().setAtomId(220).writeString(str).writeInt(21).writeString(str4).writeInt(0).writeInt(0).writeString(str3).writeInt(linkifyStats.numLinks).writeInt(linkifyStats.numLinksTextLength).writeInt(charSequence.length()).writeLong(j).writeString(str2).writeString(str5).usePooledBuffer().build());
        if (TcLog.ENABLE_FULL_LOGGING) {
            TcLog.v("GenerateLinksLogger", String.format(Locale.US, "%s:%s %d links (%d/%d chars) %dms %s annotator=%s langid=%s", str, str3, Integer.valueOf(linkifyStats.numLinks), Integer.valueOf(linkifyStats.numLinksTextLength), Integer.valueOf(charSequence.length()), Long.valueOf(j), str2, str4, str5));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class LinkifyStats {
        int numLinks;
        int numLinksTextLength;

        private LinkifyStats() {
        }

        void countLink(TextLinks.TextLink textLink) {
            this.numLinks++;
            this.numLinksTextLength += textLink.getEnd() - textLink.getStart();
        }
    }
}
