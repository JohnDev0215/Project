package com.amazon.music.featuregating.configuration;

import com.amazon.music.platform.PlatformProviderUtilKt;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;

/* JADX INFO: compiled from: ArcusAccountReader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\b"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ArcusAccountReader;", "", "()V", "getArcusConfigId", "", "configString", "ArcusAccount", "ArcusAccountNotFound", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class ArcusAccountReader {
    public static final ArcusAccountReader INSTANCE = new ArcusAccountReader();

    /* JADX INFO: compiled from: ArcusAccountReader.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ArcusAccountReader$ArcusAccountNotFound;", "", "()V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class ArcusAccountNotFound extends Throwable {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ArcusAccountReader.kt */
    @Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0083\b\u0018\u0000 \u00192\u00020\u0001:\u0002\u001a\u0019B%\b\u0017\u0012\u0006\u0010\u0014\u001a\u00020\u000b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0010\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ArcusAccountReader$ArcusAccount;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "configId", "Ljava/lang/String;", "getConfigId", "()Ljava/lang/String;", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "<init>", "(ILjava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    static final /* data */ class ArcusAccount {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final String configId;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ArcusAccount) && Intrinsics.areEqual(this.configId, ((ArcusAccount) other).configId);
        }

        public int hashCode() {
            return this.configId.hashCode();
        }

        public String toString() {
            return "ArcusAccount(configId=" + this.configId + ')';
        }

        /* JADX INFO: compiled from: ArcusAccountReader.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/featuregating/configuration/ArcusAccountReader$ArcusAccount$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/music/featuregating/configuration/ArcusAccountReader$ArcusAccount;", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<ArcusAccount> serializer() {
                return ArcusAccountReader$ArcusAccount$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ ArcusAccount(int i, String str, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, ArcusAccountReader$ArcusAccount$$serializer.INSTANCE.getDescriptor());
            }
            this.configId = str;
            PlatformProviderUtilKt.freeze(this);
        }

        @JvmStatic
        public static final void write$Self(ArcusAccount self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            output.encodeStringElement(serialDesc, 0, self.configId);
        }

        public final String getConfigId() {
            return this.configId;
        }
    }

    private ArcusAccountReader() {
    }

    public final String getArcusConfigId(String configString) throws ArcusAccountNotFound {
        Intrinsics.checkNotNullParameter(configString, "configString");
        try {
            return ((ArcusAccount) Json.INSTANCE.decodeFromString(ArcusAccount.INSTANCE.serializer(), configString)).getConfigId();
        } catch (Exception unused) {
            throw new ArcusAccountNotFound();
        }
    }
}
