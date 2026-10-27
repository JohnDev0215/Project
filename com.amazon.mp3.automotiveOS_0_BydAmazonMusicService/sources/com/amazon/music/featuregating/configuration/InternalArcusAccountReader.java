package com.amazon.music.featuregating.configuration;

import com.amazon.music.featuregating.configuration.ArcusAccountReader;
import com.amazon.music.platform.PlatformProviderUtilKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObjectBuilder;
import kotlinx.serialization.json.JsonTransformingSerializer;

/* JADX INFO: compiled from: InternalArcusAccountReader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003\u000b\f\rB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003J\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader;", "", "configData", "", "(Ljava/lang/String;)V", "arcusAccounts", "Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader$InternalArcusAccounts;", "getConfigId", "title", "getEnvironments", "", "InternalArcusAccount", "InternalArcusAccounts", "InternalArcusAccountsSerializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InternalArcusAccountReader {
    private final InternalArcusAccounts arcusAccounts;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: InternalArcusAccountReader.kt */
    @Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0083\b\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001c\u001bB/\b\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u000b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0010\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader$InternalArcusAccount;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "configId", "getConfigId", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "<init>", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    static final /* data */ class InternalArcusAccount {
        private final String configId;
        private final String title;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InternalArcusAccount)) {
                return false;
            }
            InternalArcusAccount internalArcusAccount = (InternalArcusAccount) other;
            return Intrinsics.areEqual(this.title, internalArcusAccount.title) && Intrinsics.areEqual(this.configId, internalArcusAccount.configId);
        }

        public int hashCode() {
            return (this.title.hashCode() * 31) + this.configId.hashCode();
        }

        public String toString() {
            return "InternalArcusAccount(title=" + this.title + ", configId=" + this.configId + ')';
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ InternalArcusAccount(int i, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
            if (3 != (i & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i, 3, InternalArcusAccountReader$InternalArcusAccount$$serializer.INSTANCE.getDescriptor());
            }
            this.title = str;
            this.configId = str2;
            PlatformProviderUtilKt.freeze(this);
        }

        @JvmStatic
        public static final void write$Self(InternalArcusAccount self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            output.encodeStringElement(serialDesc, 0, self.title);
            output.encodeStringElement(serialDesc, 1, self.configId);
        }

        public final String getConfigId() {
            return this.configId;
        }

        public final String getTitle() {
            return this.title;
        }
    }

    public InternalArcusAccountReader(String configData) throws ArcusAccountReader.ArcusAccountNotFound {
        Intrinsics.checkNotNullParameter(configData, "configData");
        try {
            this.arcusAccounts = (InternalArcusAccounts) Json.INSTANCE.decodeFromString(new InternalArcusAccountsSerializer(), configData);
        } catch (Exception unused) {
            throw new ArcusAccountReader.ArcusAccountNotFound();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: InternalArcusAccountReader.kt */
    @Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0083\b\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001c\u001bB+\b\u0017\u0012\u0006\u0010\u0016\u001a\u00020\u000b\u0012\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005HÇ\u0001J\t\u0010\n\u001a\u00020\tHÖ\u0001J\t\u0010\f\u001a\u00020\u000bHÖ\u0001J\u0013\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u001d\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader$InternalArcusAccounts;", "", "self", "Lkotlinx/serialization/encoding/CompositeEncoder;", "output", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialDesc", "", "write$Self", "", "toString", "", "hashCode", "other", "", "equals", "", "Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader$InternalArcusAccount;", "accounts", "Ljava/util/List;", "getAccounts", "()Ljava/util/List;", "seen1", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "serializationConstructorMarker", "<init>", "(ILjava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "Companion", "$serializer", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
    @Serializable
    static final /* data */ class InternalArcusAccounts {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final List<InternalArcusAccount> accounts;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof InternalArcusAccounts) && Intrinsics.areEqual(this.accounts, ((InternalArcusAccounts) other).accounts);
        }

        public int hashCode() {
            return this.accounts.hashCode();
        }

        public String toString() {
            return "InternalArcusAccounts(accounts=" + this.accounts + ')';
        }

        /* JADX INFO: compiled from: InternalArcusAccountReader.kt */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader$InternalArcusAccounts$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader$InternalArcusAccounts;", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final KSerializer<InternalArcusAccounts> serializer() {
                return InternalArcusAccountReader$InternalArcusAccounts$$serializer.INSTANCE;
            }
        }

        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ InternalArcusAccounts(int i, List list, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (i & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i, 1, InternalArcusAccountReader$InternalArcusAccounts$$serializer.INSTANCE.getDescriptor());
            }
            this.accounts = list;
        }

        @JvmStatic
        public static final void write$Self(InternalArcusAccounts self, CompositeEncoder output, SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            output.encodeSerializableElement(serialDesc, 0, new ArrayListSerializer(InternalArcusAccountReader$InternalArcusAccount$$serializer.INSTANCE), self.accounts);
        }

        public final List<InternalArcusAccount> getAccounts() {
            return this.accounts;
        }
    }

    /* JADX INFO: compiled from: InternalArcusAccountReader.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0014¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader$InternalArcusAccountsSerializer;", "Lkotlinx/serialization/json/JsonTransformingSerializer;", "Lcom/amazon/music/featuregating/configuration/InternalArcusAccountReader$InternalArcusAccounts;", "()V", "transformDeserialize", "Lkotlinx/serialization/json/JsonElement;", "element", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    private static final class InternalArcusAccountsSerializer extends JsonTransformingSerializer<InternalArcusAccounts> {
        public InternalArcusAccountsSerializer() {
            super(InternalArcusAccounts.INSTANCE.serializer());
        }

        @Override // kotlinx.serialization.json.JsonTransformingSerializer
        protected JsonElement transformDeserialize(JsonElement element) {
            Intrinsics.checkNotNullParameter(element, "element");
            if (!(element instanceof JsonArray)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
            jsonObjectBuilder.put("accounts", element);
            return jsonObjectBuilder.build();
        }
    }

    public final List<String> getEnvironments() {
        List<InternalArcusAccount> accounts = this.arcusAccounts.getAccounts();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(accounts, 10));
        Iterator<T> it = accounts.iterator();
        while (it.hasNext()) {
            arrayList.add(((InternalArcusAccount) it.next()).getTitle());
        }
        return arrayList;
    }

    public final String getConfigId(String title) throws ArcusAccountReader.ArcusAccountNotFound {
        Object next;
        String configId;
        Intrinsics.checkNotNullParameter(title, "title");
        Iterator<T> it = this.arcusAccounts.getAccounts().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(((InternalArcusAccount) next).getTitle(), title)) {
                break;
            }
        }
        InternalArcusAccount internalArcusAccount = (InternalArcusAccount) next;
        if (internalArcusAccount == null || (configId = internalArcusAccount.getConfigId()) == null) {
            throw new ArcusAccountReader.ArcusAccountNotFound();
        }
        return configId;
    }
}
