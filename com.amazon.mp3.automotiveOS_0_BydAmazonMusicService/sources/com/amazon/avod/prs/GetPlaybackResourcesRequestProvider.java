package com.amazon.avod.prs;

import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.atvplaybackdevice.types.Resource;
import com.amazon.atvplaybackdevice.types.ResourceUsage;
import com.amazon.atvplaybackdevice.types.VideoMaterialType;
import com.amazon.avod.playbackresource.PlaybackResourcesWrapper;
import com.amazon.bolthttp.Request;
import com.google.common.base.Joiner;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface GetPlaybackResourcesRequestProvider {
    public static final Joiner JOINER = Joiner.on(",").skipNulls();

    @Nonnull
    Request<PlaybackResourcesWrapper> buildRequest(@Nonnull String str, @Nonnull VideoMaterialType videoMaterialType, @Nonnull ConsumptionType consumptionType, @Nonnull ResourceUsage resourceUsage, @Nonnull Set<Resource> set, @Nonnull AdditionalParams additionalParams, @Nonnull Map<String, String> map);
}
