package kotlinx.serialization.json;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: Json.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010<\u001a\u00020;¢\u0006\u0004\b=\u0010>J\u000f\u0010\u0005\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR(\u0010\r\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\r\u0010\b\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\"\u0010\u0012\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010\b\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\fR\"\u0010\u0015\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\b\u001a\u0004\b\u0015\u0010\n\"\u0004\b\u0016\u0010\fR\"\u0010\u0017\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\b\u001a\u0004\b\u0018\u0010\n\"\u0004\b\u0019\u0010\fR\"\u0010\u001a\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\b\u001a\u0004\b\u001b\u0010\n\"\u0004\b\u001c\u0010\fR(\u0010\u001e\u001a\u00020\u001d8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b\u001e\u0010\u001f\u0012\u0004\b$\u0010\u0011\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\"\u0010%\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010\b\u001a\u0004\b&\u0010\n\"\u0004\b'\u0010\fR\"\u0010(\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b(\u0010\b\u001a\u0004\b)\u0010\n\"\u0004\b*\u0010\fR\"\u0010+\u001a\u00020\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010\u001f\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R\"\u0010.\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010\b\u001a\u0004\b/\u0010\n\"\u0004\b0\u0010\fR\"\u00101\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u0010\b\u001a\u0004\b2\u0010\n\"\u0004\b3\u0010\fR\"\u00105\u001a\u0002048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006?"}, d2 = {"Lkotlinx/serialization/json/JsonBuilder;", "", "Lkotlinx/serialization/json/JsonConfiguration;", "build$kotlinx_serialization_json", "()Lkotlinx/serialization/json/JsonConfiguration;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", "encodeDefaults", "Z", "getEncodeDefaults", "()Z", "setEncodeDefaults", "(Z)V", "explicitNulls", "getExplicitNulls", "setExplicitNulls", "getExplicitNulls$annotations", "()V", "ignoreUnknownKeys", "getIgnoreUnknownKeys", "setIgnoreUnknownKeys", "isLenient", "setLenient", "allowStructuredMapKeys", "getAllowStructuredMapKeys", "setAllowStructuredMapKeys", "prettyPrint", "getPrettyPrint", "setPrettyPrint", "", "prettyPrintIndent", "Ljava/lang/String;", "getPrettyPrintIndent", "()Ljava/lang/String;", "setPrettyPrintIndent", "(Ljava/lang/String;)V", "getPrettyPrintIndent$annotations", "coerceInputValues", "getCoerceInputValues", "setCoerceInputValues", "useArrayPolymorphism", "getUseArrayPolymorphism", "setUseArrayPolymorphism", "classDiscriminator", "getClassDiscriminator", "setClassDiscriminator", "allowSpecialFloatingPointValues", "getAllowSpecialFloatingPointValues", "setAllowSpecialFloatingPointValues", "useAlternativeNames", "getUseAlternativeNames", "setUseAlternativeNames", "Lkotlinx/serialization/modules/SerializersModule;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "setSerializersModule", "(Lkotlinx/serialization/modules/SerializersModule;)V", "Lkotlinx/serialization/json/Json;", "json", "<init>", "(Lkotlinx/serialization/json/Json;)V", "kotlinx-serialization-json"}, k = 1, mv = {1, 7, 1})
public final class JsonBuilder {
    private boolean allowSpecialFloatingPointValues;
    private boolean allowStructuredMapKeys;
    private String classDiscriminator;
    private boolean coerceInputValues;
    private boolean encodeDefaults;
    private boolean explicitNulls;
    private boolean ignoreUnknownKeys;
    private boolean isLenient;
    private boolean prettyPrint;
    private String prettyPrintIndent;
    private SerializersModule serializersModule;
    private boolean useAlternativeNames;
    private boolean useArrayPolymorphism;

    public JsonBuilder(Json json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.encodeDefaults = json.getConfiguration().getEncodeDefaults();
        this.explicitNulls = json.getConfiguration().getExplicitNulls();
        this.ignoreUnknownKeys = json.getConfiguration().getIgnoreUnknownKeys();
        this.isLenient = json.getConfiguration().getIsLenient();
        this.allowStructuredMapKeys = json.getConfiguration().getAllowStructuredMapKeys();
        this.prettyPrint = json.getConfiguration().getPrettyPrint();
        this.prettyPrintIndent = json.getConfiguration().getPrettyPrintIndent();
        this.coerceInputValues = json.getConfiguration().getCoerceInputValues();
        this.useArrayPolymorphism = json.getConfiguration().getUseArrayPolymorphism();
        this.classDiscriminator = json.getConfiguration().getClassDiscriminator();
        this.allowSpecialFloatingPointValues = json.getConfiguration().getAllowSpecialFloatingPointValues();
        this.useAlternativeNames = json.getConfiguration().getUseAlternativeNames();
        this.serializersModule = json.getSerializersModule();
    }

    public final void setIgnoreUnknownKeys(boolean z) {
        this.ignoreUnknownKeys = z;
    }

    public final SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public final void setSerializersModule(SerializersModule serializersModule) {
        Intrinsics.checkNotNullParameter(serializersModule, "<set-?>");
        this.serializersModule = serializersModule;
    }

    public final JsonConfiguration build$kotlinx_serialization_json() {
        if (this.useArrayPolymorphism && !Intrinsics.areEqual(this.classDiscriminator, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
            throw new IllegalArgumentException("Class discriminator should not be specified when array polymorphism is specified".toString());
        }
        if (!this.prettyPrint) {
            if (!Intrinsics.areEqual(this.prettyPrintIndent, "    ")) {
                throw new IllegalArgumentException("Indent should not be specified when default printing mode is used".toString());
            }
        } else if (!Intrinsics.areEqual(this.prettyPrintIndent, "    ")) {
            String str = this.prettyPrintIndent;
            boolean z = false;
            int i = 0;
            while (true) {
                boolean z2 = true;
                if (i >= str.length()) {
                    z = true;
                    break;
                }
                char cCharAt = str.charAt(i);
                if (cCharAt != ' ' && cCharAt != '\t' && cCharAt != '\r' && cCharAt != '\n') {
                    z2 = false;
                }
                if (!z2) {
                    break;
                }
                i++;
            }
            if (!z) {
                throw new IllegalArgumentException(("Only whitespace, tab, newline and carriage return are allowed as pretty print symbols. Had " + this.prettyPrintIndent).toString());
            }
        }
        return new JsonConfiguration(this.encodeDefaults, this.ignoreUnknownKeys, this.isLenient, this.allowStructuredMapKeys, this.prettyPrint, this.explicitNulls, this.prettyPrintIndent, this.coerceInputValues, this.useArrayPolymorphism, this.classDiscriminator, this.allowSpecialFloatingPointValues, this.useAlternativeNames);
    }
}
