package com.amazon.music.featuregating.weblabs;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.data.WeblabTreatment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeblabDescriptor.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/featuregating/weblabs/WeblabDescriptor;", "", "", "toString", "", "hashCode", "other", "", "equals", "name", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "Lcom/amazon/music/platform/data/WeblabTreatment;", "defaultTreatment", "Lcom/amazon/music/platform/data/WeblabTreatment;", "getDefaultTreatment", "()Lcom/amazon/music/platform/data/WeblabTreatment;", "<init>", "(Ljava/lang/String;Lcom/amazon/music/platform/data/WeblabTreatment;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class WeblabDescriptor {
    private final WeblabTreatment defaultTreatment;
    private final String name;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WeblabDescriptor)) {
            return false;
        }
        WeblabDescriptor weblabDescriptor = (WeblabDescriptor) other;
        return Intrinsics.areEqual(this.name, weblabDescriptor.name) && this.defaultTreatment == weblabDescriptor.defaultTreatment;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.defaultTreatment.hashCode();
    }

    public String toString() {
        return "WeblabDescriptor(name=" + this.name + ", defaultTreatment=" + this.defaultTreatment + ')';
    }

    public WeblabDescriptor(String name, WeblabTreatment defaultTreatment) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(defaultTreatment, "defaultTreatment");
        this.name = name;
        this.defaultTreatment = defaultTreatment;
        PlatformProviderUtilKt.freeze(this);
    }

    public /* synthetic */ WeblabDescriptor(String str, WeblabTreatment weblabTreatment, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? WeblabTreatment.C : weblabTreatment);
    }

    public final WeblabTreatment getDefaultTreatment() {
        return this.defaultTreatment;
    }

    public final String getName() {
        return this.name;
    }
}
