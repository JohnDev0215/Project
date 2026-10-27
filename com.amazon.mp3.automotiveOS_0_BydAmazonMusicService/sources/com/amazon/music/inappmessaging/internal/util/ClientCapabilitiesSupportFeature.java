package com.amazon.music.inappmessaging.internal.util;

import com.amazon.music.featuregating.rules.BooleanConfigurationRule;
import com.amazon.music.platform.featuregate.Feature;
import com.amazon.music.platform.featuregate.Rule;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ClientCapabilitiesSupportFeature.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/inappmessaging/internal/util/ClientCapabilitiesSupportFeature;", "", "Lcom/amazon/music/platform/featuregate/Feature;", "(Ljava/lang/String;I)V", "empty_control_support", "DMMNotificationUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum ClientCapabilitiesSupportFeature implements Feature {
    empty_control_support { // from class: com.amazon.music.inappmessaging.internal.util.ClientCapabilitiesSupportFeature.empty_control_support
        @Override // com.amazon.music.inappmessaging.internal.util.ClientCapabilitiesSupportFeature, com.amazon.music.platform.featuregate.Feature
        public Rule getRule() {
            return new BooleanConfigurationRule("is_empty_control_supported");
        }
    };

    /* synthetic */ ClientCapabilitiesSupportFeature(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public abstract /* synthetic */ Rule getRule();
}
