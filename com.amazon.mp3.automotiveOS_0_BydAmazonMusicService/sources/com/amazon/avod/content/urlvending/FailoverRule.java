package com.amazon.avod.content.urlvending;

import com.amazon.avod.util.json.JacksonJsonParser;
import com.amazon.avod.util.json.JsonContractException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@SuppressFBWarnings(justification = "this class has a natural ordering that is inconsistent with equals", value = {"EQ_COMPARETO_USE_OBJECT_EQUALS"})
public class FailoverRule implements Comparable<FailoverRule> {
    private final DownloadType mDownloadType;
    private final String mFailoverSource;
    private final FailoverType mFailoverType;
    private final int mRetryCount;
    private final int mRuleId;
    private final int mStatusCodeFrom;
    private final int mStatusCodeTo;

    public static class Parser implements JacksonJsonParser<FailoverRule> {
        final ObjectMapper mObjectMapper = new ObjectMapper();

        @Override // com.amazon.avod.util.json.JacksonJsonNodeParser
        @Nonnull
        public FailoverRule parse(@Nonnull JsonNode jsonNode) throws JsonContractException, IOException {
            return (FailoverRule) this.mObjectMapper.convertValue(jsonNode, FailoverRule.class);
        }

        @Override // com.amazon.avod.util.json.JacksonJsonStreamParser
        @Nonnull
        public FailoverRule parse(@Nonnull JsonParser jsonParser) throws JsonContractException, IOException {
            return (FailoverRule) this.mObjectMapper.readValue(jsonParser, FailoverRule.class);
        }
    }

    public FailoverRule(@JsonProperty("ruleId") int i, @JsonProperty("failoverSource") @Nonnull String str, @JsonProperty("statusCodeFrom") int i2, @JsonProperty("stausCodeTo") int i3, @JsonProperty("retryCount") int i4, @JsonProperty("failoverType") @Nonnull FailoverType failoverType, @JsonProperty("downloadType") @Nonnull DownloadType downloadType) {
        this.mRuleId = i;
        this.mFailoverSource = (String) Preconditions.checkNotNull(str, "failoverSource");
        this.mStatusCodeFrom = i2;
        this.mStatusCodeTo = i3;
        this.mRetryCount = i4;
        this.mFailoverType = (FailoverType) Preconditions.checkNotNull(failoverType, "failoverType");
        this.mDownloadType = (DownloadType) Preconditions.checkNotNull(downloadType, "downloadType");
    }

    public boolean matches(@Nonnull String str, int i, @Nonnull DownloadType downloadType) {
        Preconditions.checkNotNull(str, "failoverSource");
        Preconditions.checkNotNull(downloadType, "downloadType");
        if ((!this.mFailoverSource.equalsIgnoreCase("anywhere") && !this.mFailoverSource.equalsIgnoreCase(str)) || i < this.mStatusCodeFrom || i > this.mStatusCodeTo) {
            return false;
        }
        DownloadType downloadType2 = this.mDownloadType;
        return downloadType2 == DownloadType.ANY || downloadType2 == downloadType;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    @Nonnull
    public FailoverType getFailoverType() {
        return this.mFailoverType;
    }

    public int getRuleId() {
        return this.mRuleId;
    }

    @Override // java.lang.Comparable
    public int compareTo(FailoverRule failoverRule) {
        int i = this.mRuleId;
        int i2 = failoverRule.mRuleId;
        if (i > i2) {
            return 1;
        }
        return i < i2 ? -1 : 0;
    }
}
