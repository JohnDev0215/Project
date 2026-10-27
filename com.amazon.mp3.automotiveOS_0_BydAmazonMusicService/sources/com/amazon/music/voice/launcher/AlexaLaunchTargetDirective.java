package com.amazon.music.voice.launcher;

import com.amazon.alexa.voice.core.processor.superbowl.ContentProvider;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective;
import com.amazon.alexa.voice.core.processor.superbowl.directives.DirectiveBaseFactory;
import com.amazon.music.platform.aom.launcher.CatalogType;
import com.amazon.music.platform.aom.launcher.IdentifierType;
import com.amazon.music.platform.aom.launcher.LaunchConfig;
import com.amazon.music.platform.aom.launcher.LaunchTarget;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: AlexaLaunchTargetDirective.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\b\u0018\u0000 \u001a2\u00020\u0001:\u0003\u001b\u001a\u001cB\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlDirective;", "", "toString", "", "hashCode", "", "other", "", "equals", "Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective$Builder;", "builder", "Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective$Builder;", "getBuilder", "()Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective$Builder;", "token", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "Lcom/amazon/music/platform/aom/launcher/LaunchTarget;", "target", "Lcom/amazon/music/platform/aom/launcher/LaunchTarget;", "getTarget", "()Lcom/amazon/music/platform/aom/launcher/LaunchTarget;", "<init>", "(Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective$Builder;)V", "Companion", "Builder", "Factory", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AlexaLaunchTargetDirective extends SuperbowlDirective {
    private final Builder builder;
    private final LaunchTarget target;
    private final String token;

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AlexaLaunchTargetDirective) && Intrinsics.areEqual(this.builder, ((AlexaLaunchTargetDirective) other).builder);
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective
    public int hashCode() {
        return this.builder.hashCode();
    }

    public String toString() {
        return "AlexaLaunchTargetDirective(builder=" + this.builder + ')';
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlexaLaunchTargetDirective(Builder builder) {
        super(builder);
        Intrinsics.checkNotNullParameter(builder, "builder");
        this.builder = builder;
        this.token = builder.getToken();
        this.target = builder.getTarget();
    }

    public final String getToken() {
        return this.token;
    }

    public final LaunchTarget getTarget() {
        return this.target;
    }

    /* JADX INFO: compiled from: AlexaLaunchTargetDirective.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0002H\u0016J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective$Builder;", "Lcom/amazon/alexa/voice/core/processor/superbowl/SuperbowlDirective$Builder;", "Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective;", "()V", "target", "Lcom/amazon/music/platform/aom/launcher/LaunchTarget;", "getTarget", "()Lcom/amazon/music/platform/aom/launcher/LaunchTarget;", "setTarget", "(Lcom/amazon/music/platform/aom/launcher/LaunchTarget;)V", "token", "", "getToken", "()Ljava/lang/String;", "setToken", "(Ljava/lang/String;)V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder extends SuperbowlDirective.Builder<AlexaLaunchTargetDirective> {
        public LaunchTarget target;
        public String token;

        public Builder() {
            super("Alexa.Launcher", "LaunchTarget");
        }

        public final String getToken() {
            String str = this.token;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("token");
            return null;
        }

        public final void setToken(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.token = str;
        }

        public final LaunchTarget getTarget() {
            LaunchTarget launchTarget = this.target;
            if (launchTarget != null) {
                return launchTarget;
            }
            Intrinsics.throwUninitializedPropertyAccessException("target");
            return null;
        }

        public final void setTarget(LaunchTarget launchTarget) {
            Intrinsics.checkNotNullParameter(launchTarget, "<set-?>");
            this.target = launchTarget;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlDirective.Builder
        public AlexaLaunchTargetDirective build() {
            return new AlexaLaunchTargetDirective(this);
        }

        public final Builder token(String token) {
            Intrinsics.checkNotNullParameter(token, "token");
            setToken(token);
            return this;
        }

        public final Builder target(LaunchTarget target) {
            Intrinsics.checkNotNullParameter(target, "target");
            setTarget(target);
            return this;
        }
    }

    /* JADX INFO: compiled from: AlexaLaunchTargetDirective.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\r"}, d2 = {"Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective$Factory;", "Lcom/amazon/alexa/voice/core/processor/superbowl/directives/DirectiveBaseFactory;", "Lcom/amazon/music/voice/launcher/AlexaLaunchTargetDirective;", "()V", "create", "json", "Lorg/json/JSONObject;", "p1", "Lcom/amazon/alexa/voice/core/processor/superbowl/ContentProvider;", "target", "Lcom/amazon/music/platform/aom/launcher/LaunchTarget;", "token", "", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Factory extends DirectiveBaseFactory<AlexaLaunchTargetDirective> {
        @Override // com.amazon.alexa.voice.core.processor.superbowl.DirectiveFactory.Factory
        public AlexaLaunchTargetDirective create(JSONObject json, ContentProvider p1) {
            SuperbowlDirective superbowlDirectiveBuild = new Builder().token(token(json)).target(target(json)).dialogRequestId(getDialogRequestId(json)).messageId(getMessageId(json)).build();
            Intrinsics.checkNotNullExpressionValue(superbowlDirectiveBuild, "Builder()\n              …                 .build()");
            return (AlexaLaunchTargetDirective) superbowlDirectiveBuild;
        }

        private final String token(JSONObject json) throws Exception {
            JSONObject jSONObject;
            String string = null;
            if (json != null && (jSONObject = json.getJSONObject("payload")) != null) {
                string = jSONObject.getString("token");
            }
            if (string != null) {
                return string;
            }
            throw new Exception("Unable to parse field token");
        }

        private final LaunchTarget target(JSONObject json) throws Exception {
            JSONObject jSONObject;
            JSONObject jSONObject2 = (json == null || (jSONObject = json.getJSONObject("payload")) == null) ? null : jSONObject.getJSONObject("target");
            if (jSONObject2 == null) {
                throw new Exception("Unable to parse field target");
            }
            String string = jSONObject2.getString("catalogType");
            if (string == null) {
                throw new Exception("Unable to parse field target.catalogType");
            }
            CatalogType catalogTypeValueOf = CatalogType.valueOf(string);
            String string2 = jSONObject2.getString("identifier");
            if (string2 == null) {
                throw new Exception("Unable to parse field target.identifier");
            }
            String string3 = jSONObject2.getString("identifierType");
            if (string3 == null) {
                throw new Exception("Unable to parse field target.identifierType");
            }
            IdentifierType identifierTypeValueOf = IdentifierType.valueOf(string3);
            String string4 = jSONObject2.getString("name");
            if (string4 == null) {
                throw new Exception("Unable to parse field target.name");
            }
            String string5 = jSONObject2.getString("token");
            if (string5 == null) {
                throw new Exception("Unable to parse field target.token");
            }
            String string6 = jSONObject2.has("catalogId") ? jSONObject2.getString("catalogId") : null;
            JSONObject jSONObject3 = jSONObject2.has("launchConfig") ? jSONObject2.getJSONObject("launchConfig") : null;
            return new LaunchTarget(catalogTypeValueOf, string2, identifierTypeValueOf, string6, string4, string5, jSONObject3 != null ? new LaunchConfig(jSONObject3 == null ? false : jSONObject3.getBoolean("isMandatoryToLaunchTarget")) : null);
        }
    }
}
