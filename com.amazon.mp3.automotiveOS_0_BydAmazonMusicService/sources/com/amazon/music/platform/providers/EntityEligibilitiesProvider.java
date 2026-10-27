package com.amazon.music.platform.providers;

import com.amazon.music.platform.data.eligibility.Eligibility;
import com.amazon.music.platform.data.entity.EntityItem;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: EntityEligibilitiesProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/platform/providers/EntityEligibilitiesProvider;", "", "getEligibilities", "", "", "Lcom/amazon/music/platform/data/eligibility/Eligibility;", "getEntityItem", "Lcom/amazon/music/platform/data/entity/EntityItem;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface EntityEligibilitiesProvider {
    Map<String, Eligibility> getEligibilities();

    EntityItem getEntityItem();
}
