package com.amazon.avod.playbackresource;

import com.amazon.atvplaybackresource.TrickplayUrlInfo;
import com.amazon.atvplaybackresource.TrickplayUrls;
import com.amazon.atvplaybackresource.TrickplayUrlsCdnSet;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.UnmodifiableIterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TrickplayUrlTransformer {
    public ImmutableMultimap<String, String> transform(Optional<TrickplayUrls> optional) {
        ImmutableList<TrickplayUrlsCdnSet> immutableListOrNull = optional.isPresent() ? optional.get().trickplayUrlsCdnSets.orNull() : null;
        if (immutableListOrNull == null) {
            return ImmutableMultimap.of();
        }
        ImmutableMultimap.Builder<String, String> builder = ImmutableMultimap.builder();
        UnmodifiableIterator<TrickplayUrlsCdnSet> it = immutableListOrNull.iterator();
        while (it.hasNext()) {
            parseCdnSet(builder, it.next());
        }
        return builder.build();
    }

    private void parseCdnSet(@Nonnull ImmutableMultimap.Builder<String, String> builder, @Nonnull TrickplayUrlsCdnSet trickplayUrlsCdnSet) {
        if (trickplayUrlsCdnSet.trickplayUrlInfoList.isPresent()) {
            UnmodifiableIterator<TrickplayUrlInfo> it = trickplayUrlsCdnSet.trickplayUrlInfoList.get().iterator();
            while (it.hasNext()) {
                parseUrlInfo(builder, it.next());
            }
        }
    }

    private void parseUrlInfo(@Nonnull ImmutableMultimap.Builder<String, String> builder, @Nonnull TrickplayUrlInfo trickplayUrlInfo) {
        if (trickplayUrlInfo.resolution.isPresent() && trickplayUrlInfo.url.isPresent()) {
            builder.put(trickplayUrlInfo.resolution.get(), trickplayUrlInfo.url.get());
        }
    }
}
