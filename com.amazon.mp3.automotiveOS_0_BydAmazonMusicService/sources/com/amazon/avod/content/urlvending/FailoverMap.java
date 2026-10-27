package com.amazon.avod.content.urlvending;

import androidx.mediarouter.media.MediaRouter;
import com.amazon.avod.content.urlvending.FailoverRule;
import com.amazon.avod.util.json.JacksonJsonParser;
import com.amazon.avod.util.json.JsonContractException;
import com.amazon.avod.util.json.ListParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class FailoverMap {
    private static final ImmutableList<FailoverRule> DEFAULT_FAILOVER_RULE_LIST;
    private static final FailoverMap DEFAULT_INSTANCE;
    private final List<FailoverRule> mFailoverRuleList;

    static {
        ImmutableList.Builder builder = ImmutableList.builder();
        FailoverType failoverType = FailoverType.CLOSE_PLAYER;
        DownloadType downloadType = DownloadType.ANY;
        ImmutableList.Builder builderAdd = builder.add(new FailoverRule(1, "LivePlaybackOrigin", MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED, MediaRouter.GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED, 1, failoverType, downloadType));
        FailoverType failoverType2 = FailoverType.ORIGIN;
        ImmutableList.Builder builderAdd2 = builderAdd.add(new FailoverRule(2, "anywhere", 591, 591, 1, failoverType2, downloadType));
        DownloadType downloadType2 = DownloadType.FRAGMENT;
        ImmutableList.Builder builderAdd3 = builderAdd2.add(new FailoverRule(3, "LivePlaybackOrigin", 404, 404, 100, failoverType2, downloadType2)).add(new FailoverRule(4, "LivePlaybackOrigin", 400, 599, 3, failoverType2, DownloadType.MANIFEST)).add(new FailoverRule(5, "Midas", 400, 599, 3, FailoverType.MANIFEST_SERVICE, downloadType));
        FailoverType failoverType3 = FailoverType.CDN;
        ImmutableList<FailoverRule> immutableListBuild = builderAdd3.add(new FailoverRule(6, "CDN", 400, 599, 3, failoverType3, downloadType)).add(new FailoverRule(7, "anywhere", -2, -2, 3, failoverType3, downloadType)).add(new FailoverRule(8, "anywhere", 400, 599, 3, failoverType3, downloadType)).add(new FailoverRule(9, "anywhere", MediaError.DetailedErrorCode.SEGMENT_NETWORK, 302, 1, failoverType3, downloadType2)).build();
        DEFAULT_FAILOVER_RULE_LIST = immutableListBuild;
        DEFAULT_INSTANCE = new FailoverMap(immutableListBuild);
    }

    public static FailoverMap getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public FailoverMap(@Nonnull ImmutableList<FailoverRule> immutableList) {
        this.mFailoverRuleList = (List) Preconditions.checkNotNull(immutableList, "failoverRulesList");
    }

    public List<FailoverRule> getFailoverRuleList() {
        return this.mFailoverRuleList;
    }

    public static class Parser implements JacksonJsonParser<FailoverMap> {
        private final ListParser<FailoverRule> mParser = ListParser.newParser(new FailoverRule.Parser());

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public FailoverMap parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            return new FailoverMap(this.mParser.parse(jsonNode));
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public FailoverMap parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            return new FailoverMap(this.mParser.parse(jsonParser));
        }
    }
}
