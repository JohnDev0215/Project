package com.amazon.music.platform.data;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WeblabTreatment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/platform/data/WeblabTreatment;", "", "treatment", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getTreatment", "()Ljava/lang/String;", PlatformWeblabs.C, PlatformWeblabs.T1, PlatformWeblabs.T2, PlatformWeblabs.T3, PlatformWeblabs.T4, PlatformWeblabs.T5, "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum WeblabTreatment {
    C(PlatformWeblabs.C),
    T1(PlatformWeblabs.T1),
    T2(PlatformWeblabs.T2),
    T3(PlatformWeblabs.T3),
    T4(PlatformWeblabs.T4),
    T5(PlatformWeblabs.T5);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String treatment;

    WeblabTreatment(String str) {
        this.treatment = str;
        PlatformProviderUtilKt.freeze(this);
    }

    public final String getTreatment() {
        return this.treatment;
    }

    /* JADX INFO: compiled from: WeblabTreatment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/data/WeblabTreatment$Companion;", "", "()V", "from", "Lcom/amazon/music/platform/data/WeblabTreatment;", "value", "", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WeblabTreatment from(String value) {
            WeblabTreatment weblabTreatment;
            Intrinsics.checkNotNullParameter(value, "value");
            WeblabTreatment[] weblabTreatmentArrValues = WeblabTreatment.values();
            int length = weblabTreatmentArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    weblabTreatment = null;
                    break;
                }
                weblabTreatment = weblabTreatmentArrValues[i];
                String treatment = weblabTreatment.getTreatment();
                String upperCase = value.toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                if (Intrinsics.areEqual(treatment, upperCase)) {
                    break;
                }
                i++;
            }
            return weblabTreatment == null ? WeblabTreatment.C : weblabTreatment;
        }
    }
}
