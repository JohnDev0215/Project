package com.amazon.identity.auth.device.api;

import androidx.annotation.NonNull;
import com.amazon.fireos.sdk.annotations.FireOsSdk;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ActorInfo {

    @FireOsSdk
    public static final String ACTOR_TYPE_ADULT = "PERSON.ADULT";

    @FireOsSdk
    public static final String ACTOR_TYPE_CHILD = "PERSON.CHILD";

    @FireOsSdk
    public static final String ACTOR_TYPE_TEEN = "PERSON.TEEN";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f195a;
    public final String b;
    public final String c;
    public final String d;

    @FireOsSdk
    public ActorInfo(@NonNull String str, @NonNull String str2, @NonNull String str3, String str4) {
        this.f195a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    @FireOsSdk
    public String getAccountDirectedId() {
        return this.b;
    }

    @FireOsSdk
    public String getActorDirectedId() {
        return this.c;
    }

    @FireOsSdk
    public String getProgram() {
        return this.f195a;
    }

    @FireOsSdk
    public String getSuggestedActorType() {
        return this.d;
    }
}
