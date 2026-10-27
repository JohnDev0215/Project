package com.bugsnag.android;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.bugsnag.android.internal.ImmutableConfig;
import java.io.IOException;

/* JADX INFO: compiled from: AppWithState.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0004\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u007f\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010 \u001a\u0004\u0018\u00010\u001b\u0012\b\u0010!\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\"\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010#\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b$\u0010%Bk\b\u0010\u0012\u0006\u0010'\u001a\u00020&\u0012\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\u001f\u001a\u0004\u0018\u00010\u001b\u0012\b\u0010 \u001a\u0004\u0018\u00010\u001b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b$\u0010(J\u0017\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0010¢\u0006\u0004\b\u0005\u0010\u0006R$\u0010\t\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u0014\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018¨\u0006)"}, d2 = {"Lcom/bugsnag/android/AppWithState;", "Lcom/bugsnag/android/App;", "Lcom/bugsnag/android/JsonStream;", "writer", "", "serialiseFields$bugsnag_android_core_release", "(Lcom/bugsnag/android/JsonStream;)V", "serialiseFields", "", "duration", "Ljava/lang/Number;", "getDuration", "()Ljava/lang/Number;", "setDuration", "(Ljava/lang/Number;)V", "durationInForeground", "getDurationInForeground", "setDurationInForeground", "", "inForeground", "Ljava/lang/Boolean;", "getInForeground", "()Ljava/lang/Boolean;", "setInForeground", "(Ljava/lang/Boolean;)V", "isLaunching", "setLaunching", "", "binaryArch", "id", "releaseStage", "version", "codeBundleId", "buildUuid", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "versionCode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "Lcom/bugsnag/android/internal/ImmutableConfig;", "config", "(Lcom/bugsnag/android/internal/ImmutableConfig;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class AppWithState extends App {
    private Number duration;
    private Number durationInForeground;
    private Boolean inForeground;
    private Boolean isLaunching;

    public final Number getDuration() {
        return this.duration;
    }

    public final Number getDurationInForeground() {
        return this.durationInForeground;
    }

    public final Boolean getInForeground() {
        return this.inForeground;
    }

    /* JADX INFO: renamed from: isLaunching, reason: from getter */
    public final Boolean getIsLaunching() {
        return this.isLaunching;
    }

    public AppWithState(String str, String str2, String str3, String str4, String str5, String str6, String str7, Number number, Number number2, Number number3, Boolean bool, Boolean bool2) {
        super(str, str2, str3, str4, str5, str6, str7, number);
        this.duration = number2;
        this.durationInForeground = number3;
        this.inForeground = bool;
        this.isLaunching = bool2;
    }

    public AppWithState(ImmutableConfig immutableConfig, String str, String str2, String str3, String str4, String str5, Number number, Number number2, Boolean bool, Boolean bool2) {
        this(str, str2, str3, str4, str5, immutableConfig.getBuildUuid(), immutableConfig.getAppType(), immutableConfig.getVersionCode(), number, number2, bool, bool2);
    }

    @Override // com.bugsnag.android.App
    public void serialiseFields$bugsnag_android_core_release(JsonStream writer) throws IOException {
        super.serialiseFields$bugsnag_android_core_release(writer);
        writer.name("duration").value(this.duration);
        writer.name("durationInForeground").value(this.durationInForeground);
        writer.name("inForeground").value(this.inForeground);
        writer.name("isLaunching").value(this.isLaunching);
    }
}
