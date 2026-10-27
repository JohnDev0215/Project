package com.amazon.digitalmusicxp.inputs;

import com.amazon.digitalmusicxp.ExpectedFunctionsKt;
import com.amazon.digitalmusicxp.enums.SeedContextTypeEnum;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SeedContextInput.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/digitalmusicxp/inputs/SeedContextInput;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/digitalmusicxp/enums/SeedContextTypeEnum;", "contextType", "Lcom/amazon/digitalmusicxp/enums/SeedContextTypeEnum;", "getContextType", "()Lcom/amazon/digitalmusicxp/enums/SeedContextTypeEnum;", "contextValue", "Ljava/lang/String;", "getContextValue", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/digitalmusicxp/enums/SeedContextTypeEnum;Ljava/lang/String;)V", "DigitalMusicCloudQueueXP_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class SeedContextInput {
    private final SeedContextTypeEnum contextType;
    private final String contextValue;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SeedContextInput)) {
            return false;
        }
        SeedContextInput seedContextInput = (SeedContextInput) other;
        return this.contextType == seedContextInput.contextType && Intrinsics.areEqual(this.contextValue, seedContextInput.contextValue);
    }

    public int hashCode() {
        SeedContextTypeEnum seedContextTypeEnum = this.contextType;
        return ((seedContextTypeEnum == null ? 0 : seedContextTypeEnum.hashCode()) * 31) + this.contextValue.hashCode();
    }

    public String toString() {
        return "SeedContextInput(contextType=" + this.contextType + ", contextValue=" + this.contextValue + ')';
    }

    public SeedContextInput(SeedContextTypeEnum seedContextTypeEnum, String contextValue) {
        Intrinsics.checkNotNullParameter(contextValue, "contextValue");
        this.contextType = seedContextTypeEnum;
        this.contextValue = contextValue;
        ExpectedFunctionsKt.freeze(this);
    }

    public final SeedContextTypeEnum getContextType() {
        return this.contextType;
    }

    public final String getContextValue() {
        return this.contextValue;
    }
}
