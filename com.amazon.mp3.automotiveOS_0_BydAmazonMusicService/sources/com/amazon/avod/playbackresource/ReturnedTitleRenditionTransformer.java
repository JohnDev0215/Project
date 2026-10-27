package com.amazon.avod.playbackresource;

import com.amazon.atvplaybackresource.AuditPing;
import com.amazon.atvplaybackresource.ResponseTitleRendition;
import com.amazon.avod.content.urlvending.ReturnedTitleRendition;
import com.amazon.avod.util.DLog;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ReturnedTitleRenditionTransformer {
    private final TimeShiftPolicyTransformer mTimeShiftPolicyTransformer;

    ReturnedTitleRenditionTransformer() {
        this(new TimeShiftPolicyTransformer());
    }

    ReturnedTitleRenditionTransformer(@Nonnull TimeShiftPolicyTransformer timeShiftPolicyTransformer) {
        this.mTimeShiftPolicyTransformer = (TimeShiftPolicyTransformer) Preconditions.checkNotNull(timeShiftPolicyTransformer, "timeShiftPolicyTransformer");
    }

    @Nonnull
    public ReturnedTitleRendition transform(@Nonnull Optional<ResponseTitleRendition> optional) {
        ImmutableList immutableListBuild;
        if (optional.isPresent()) {
            ResponseTitleRendition responseTitleRendition = optional.get();
            String value = responseTitleRendition.audioQuality.isPresent() ? responseTitleRendition.audioQuality.get().getValue() : "";
            String strOr = responseTitleRendition.contentId.or("");
            String value2 = responseTitleRendition.videoQuality.isPresent() ? responseTitleRendition.videoQuality.get().getValue() : "";
            String value3 = responseTitleRendition.videoMaterialType.isPresent() ? responseTitleRendition.videoMaterialType.get().getValue() : "";
            String strOr2 = responseTitleRendition.titleId.or("");
            if (responseTitleRendition.auditPings.isPresent()) {
                immutableListBuild = FluentIterable.from(responseTitleRendition.auditPings.get()).transform(new WireTypeToAuditPing()).filter(Predicates.notNull()).toList();
            } else {
                immutableListBuild = ImmutableList.builder().build();
            }
            return new ReturnedTitleRendition(value, strOr, value2, value3, strOr2, immutableListBuild, this.mTimeShiftPolicyTransformer.transform(responseTitleRendition.timeShift));
        }
        DLog.warnf("ReturnedTitleRendition is absent in PRS response");
        return new ReturnedTitleRendition(null, null, null, null, null, null, null);
    }

    private static class WireTypeToAuditPing implements Function<AuditPing, com.amazon.avod.content.urlvending.AuditPing> {
        private WireTypeToAuditPing() {
        }

        @Override // com.google.common.base.Function
        @Nullable
        public com.amazon.avod.content.urlvending.AuditPing apply(@Nonnull AuditPing auditPing) {
            Preconditions.checkNotNull(auditPing, "item from ImmutableList should not be null");
            return new com.amazon.avod.content.urlvending.AuditPing(auditPing.adReportingAgency.or(""), auditPing.url.or(""));
        }
    }
}
