package com.amazon.music.platform.providers;

import com.amazon.music.platform.data.CustomerMetadata;
import kotlin.Metadata;

/* JADX INFO: compiled from: CustomerMetadataProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0005\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/platform/providers/CustomerMetadataProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "Lcom/amazon/music/platform/data/CustomerMetadata;", "getCustomerMetadata", "()Lcom/amazon/music/platform/data/CustomerMetadata;", "customerMetadata", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface CustomerMetadataProvider extends InterfaceProvider {
    CustomerMetadata getCustomerMetadata();
}
