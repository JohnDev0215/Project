package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.liveevent.LineupEndpointType;
import com.amazon.music.alps.liveevent.viewmodel.VerticalIconLinkItemViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerticalIconLinkItemConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/VerticalIconLinkItemConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Endpoint;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class VerticalIconLinkItemConverter implements SingleBaseModelConverter<Lineup.Endpoint> {
    private final Context context;

    /* JADX INFO: compiled from: VerticalIconLinkItemConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LineupEndpointType.values().length];
            iArr[LineupEndpointType.PRIMEVIDEO.ordinal()] = 1;
            iArr[LineupEndpointType.TWITCH_URL.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public VerticalIconLinkItemConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Lineup.Endpoint data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int i = WhenMappings.$EnumSwitchMapping$0[LineupEndpointType.INSTANCE.from(data.getType()).ordinal()];
        if (i == 1) {
            return new VerticalIconLinkItemViewModel(null, ActionType.GO_PRIME_VIDEO, "https://m.media-amazon.com/images/G/01/LiveEvent/livestream_prime_video.png", this.context.getString(R$string.prime_video), data.getUrl(), 1, null);
        }
        if (i != 2) {
            return null;
        }
        return new VerticalIconLinkItemViewModel(null, ActionType.GO_LIVE_STREAM, "https://m.media-amazon.com/images/G/01/LiveEvent/livestream_amazon_music.png", this.context.getString(R$string.amazon_music), data.getUrl(), 1, null);
    }
}
