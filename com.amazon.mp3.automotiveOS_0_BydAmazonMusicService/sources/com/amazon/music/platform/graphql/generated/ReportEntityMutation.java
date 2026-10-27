package com.amazon.music.platform.graphql.generated;

import com.amazon.music.platform.graphql.generated.ReportEntityMutation;
import com.amazon.music.platform.graphql.generated.adapter.ReportEntityMutation_VariablesAdapter;
import com.amazon.music.platform.graphql.generated.type.ReportedAssetArg;
import com.apollographql.apollo3.api.Adapter;
import com.apollographql.apollo3.api.Adapters;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Optional;
import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonWriter;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReportEntityMutation.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003()*B1\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u001b\u001a\u00020\u0003\u0012\u0006\u0010\u001f\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030!¢\u0006\u0004\b&\u0010'J\b\u0010\u0004\u001a\u00020\u0003H\u0016J\b\u0010\u0005\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u001c\u001a\u0004\b \u0010\u001eR\u001f\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%¨\u0006+"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ReportEntityMutation;", "Lcom/apollographql/apollo3/api/Mutation;", "Lcom/amazon/music/platform/graphql/generated/ReportEntityMutation$Data;", "", "id", "document", "name", "Lcom/apollographql/apollo3/api/json/JsonWriter;", "writer", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "", "serializeVariables", "Lcom/apollographql/apollo3/api/Adapter;", "adapter", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/type/ReportedAssetArg;", "reportedAsset", "Lcom/amazon/music/platform/graphql/generated/type/ReportedAssetArg;", "getReportedAsset", "()Lcom/amazon/music/platform/graphql/generated/type/ReportedAssetArg;", "reportedOrigin", "Ljava/lang/String;", "getReportedOrigin", "()Ljava/lang/String;", "reportedType", "getReportedType", "Lcom/apollographql/apollo3/api/Optional;", "addedDetails", "Lcom/apollographql/apollo3/api/Optional;", "getAddedDetails", "()Lcom/apollographql/apollo3/api/Optional;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/type/ReportedAssetArg;Ljava/lang/String;Ljava/lang/String;Lcom/apollographql/apollo3/api/Optional;)V", "Companion", "Data", "ReportEntity", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class ReportEntityMutation implements Mutation<Data> {
    private final Optional<String> addedDetails;
    private final ReportedAssetArg reportedAsset;
    private final String reportedOrigin;
    private final String reportedType;

    @Override // com.apollographql.apollo3.api.Operation
    public String document() {
        return "mutation ReportEntity($reportedAsset: ReportedAssetArg!, $reportedOrigin: String!, $reportedType: String!, $addedDetails: String) { reportEntity(reportedAsset: $reportedAsset, reportedOrigin: $reportedOrigin, reportedType: $reportedType, addedDetails: $addedDetails) { reportStatus details } }";
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReportEntityMutation)) {
            return false;
        }
        ReportEntityMutation reportEntityMutation = (ReportEntityMutation) other;
        return Intrinsics.areEqual(this.reportedAsset, reportEntityMutation.reportedAsset) && Intrinsics.areEqual(this.reportedOrigin, reportEntityMutation.reportedOrigin) && Intrinsics.areEqual(this.reportedType, reportEntityMutation.reportedType) && Intrinsics.areEqual(this.addedDetails, reportEntityMutation.addedDetails);
    }

    public int hashCode() {
        return (((((this.reportedAsset.hashCode() * 31) + this.reportedOrigin.hashCode()) * 31) + this.reportedType.hashCode()) * 31) + this.addedDetails.hashCode();
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String id() {
        return "67040ab74fcc0d4014926046e4fc2a0dea1d5a2b9c95b09a2613eee667ca8684";
    }

    @Override // com.apollographql.apollo3.api.Operation
    public String name() {
        return "ReportEntity";
    }

    public String toString() {
        return "ReportEntityMutation(reportedAsset=" + this.reportedAsset + ", reportedOrigin=" + this.reportedOrigin + ", reportedType=" + this.reportedType + ", addedDetails=" + this.addedDetails + ')';
    }

    public ReportEntityMutation(ReportedAssetArg reportedAsset, String reportedOrigin, String reportedType, Optional<String> addedDetails) {
        Intrinsics.checkNotNullParameter(reportedAsset, "reportedAsset");
        Intrinsics.checkNotNullParameter(reportedOrigin, "reportedOrigin");
        Intrinsics.checkNotNullParameter(reportedType, "reportedType");
        Intrinsics.checkNotNullParameter(addedDetails, "addedDetails");
        this.reportedAsset = reportedAsset;
        this.reportedOrigin = reportedOrigin;
        this.reportedType = reportedType;
        this.addedDetails = addedDetails;
    }

    public final ReportedAssetArg getReportedAsset() {
        return this.reportedAsset;
    }

    public final String getReportedOrigin() {
        return this.reportedOrigin;
    }

    public final String getReportedType() {
        return this.reportedType;
    }

    public final Optional<String> getAddedDetails() {
        return this.addedDetails;
    }

    @Override // com.apollographql.apollo3.api.Operation, com.apollographql.apollo3.api.Executable
    public void serializeVariables(JsonWriter writer, CustomScalarAdapters customScalarAdapters) throws IOException {
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
        ReportEntityMutation_VariablesAdapter.INSTANCE.toJson(writer, customScalarAdapters, this);
    }

    @Override // com.apollographql.apollo3.api.Operation
    public Adapter<Data> adapter() {
        return Adapters.m2611obj$default(new Adapter<Data>() { // from class: com.amazon.music.platform.graphql.generated.adapter.ReportEntityMutation_ResponseAdapter$Data
            private static final List<String> RESPONSE_NAMES = CollectionsKt__CollectionsJVMKt.listOf("reportEntity");

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public ReportEntityMutation.Data fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                ReportEntityMutation.ReportEntity reportEntity = null;
                while (reader.selectName(RESPONSE_NAMES) == 0) {
                    reportEntity = (ReportEntityMutation.ReportEntity) Adapters.m2609nullable(Adapters.m2611obj$default(ReportEntityMutation_ResponseAdapter$ReportEntity.INSTANCE, false, 1, null)).fromJson(reader, customScalarAdapters);
                }
                return new ReportEntityMutation.Data(reportEntity);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, ReportEntityMutation.Data value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.name("reportEntity");
                Adapters.m2609nullable(Adapters.m2611obj$default(ReportEntityMutation_ResponseAdapter$ReportEntity.INSTANCE, false, 1, null)).toJson(writer, customScalarAdapters, value.getReportEntity());
            }
        }, false, 1, null);
    }

    /* JADX INFO: compiled from: ReportEntityMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ReportEntityMutation$Data;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/platform/graphql/generated/ReportEntityMutation$ReportEntity;", "reportEntity", "Lcom/amazon/music/platform/graphql/generated/ReportEntityMutation$ReportEntity;", "getReportEntity", "()Lcom/amazon/music/platform/graphql/generated/ReportEntityMutation$ReportEntity;", "<init>", "(Lcom/amazon/music/platform/graphql/generated/ReportEntityMutation$ReportEntity;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class Data implements Operation.Data {
        private final ReportEntity reportEntity;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Data) && Intrinsics.areEqual(this.reportEntity, ((Data) other).reportEntity);
        }

        public int hashCode() {
            ReportEntity reportEntity = this.reportEntity;
            if (reportEntity == null) {
                return 0;
            }
            return reportEntity.hashCode();
        }

        public String toString() {
            return "Data(reportEntity=" + this.reportEntity + ')';
        }

        public Data(ReportEntity reportEntity) {
            this.reportEntity = reportEntity;
        }

        public final ReportEntity getReportEntity() {
            return this.reportEntity;
        }
    }

    /* JADX INFO: compiled from: ReportEntityMutation.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/platform/graphql/generated/ReportEntityMutation$ReportEntity;", "", "", "toString", "", "hashCode", "other", "", "equals", "reportStatus", "Ljava/lang/Boolean;", "getReportStatus", "()Ljava/lang/Boolean;", "details", "Ljava/lang/String;", "getDetails", "()Ljava/lang/String;", "<init>", "(Ljava/lang/Boolean;Ljava/lang/String;)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
    public static final /* data */ class ReportEntity {
        private final String details;
        private final Boolean reportStatus;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ReportEntity)) {
                return false;
            }
            ReportEntity reportEntity = (ReportEntity) other;
            return Intrinsics.areEqual(this.reportStatus, reportEntity.reportStatus) && Intrinsics.areEqual(this.details, reportEntity.details);
        }

        public int hashCode() {
            Boolean bool = this.reportStatus;
            int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
            String str = this.details;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "ReportEntity(reportStatus=" + this.reportStatus + ", details=" + this.details + ')';
        }

        public ReportEntity(Boolean bool, String str) {
            this.reportStatus = bool;
            this.details = str;
        }

        public final Boolean getReportStatus() {
            return this.reportStatus;
        }

        public final String getDetails() {
            return this.details;
        }
    }
}
