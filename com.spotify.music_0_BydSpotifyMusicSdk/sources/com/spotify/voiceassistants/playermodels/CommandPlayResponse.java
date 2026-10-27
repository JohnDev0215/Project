package com.spotify.voiceassistants.playermodels;

import com.spotify.player.model.Context;
import com.spotify.player.model.PlayOrigin;
import com.spotify.player.model.command.options.PreparePlayOptions;
import p.c71;
import p.hf2;
import p.v03;
import p.z61;

/* JADX INFO: loaded from: classes.dex */
@c71(generateAdapter = false)
public final class CommandPlayResponse {
    private final Context context;
    private final String endpoint;
    private final String error;
    private final PreparePlayOptions playOptions;
    private final PlayOrigin playOrigin;
    private final String requestId;

    public CommandPlayResponse(@z61(name = "req_id") String str, @z61(name = "error") String str2, @z61(name = "endpoint") String str3, @z61(name = "context") Context context, @z61(name = "options") PreparePlayOptions preparePlayOptions, @z61(name = "play_origin") PlayOrigin playOrigin) {
        v03.j(str3, "endpoint");
        this.requestId = str;
        this.error = str2;
        this.endpoint = str3;
        this.context = context;
        this.playOptions = preparePlayOptions;
        this.playOrigin = playOrigin;
    }

    public static /* synthetic */ CommandPlayResponse copy$default(CommandPlayResponse commandPlayResponse, String str, String str2, String str3, Context context, PreparePlayOptions preparePlayOptions, PlayOrigin playOrigin, int i, Object obj) {
        if ((i & 1) != 0) {
            str = commandPlayResponse.requestId;
        }
        if ((i & 2) != 0) {
            str2 = commandPlayResponse.error;
        }
        String str4 = str2;
        if ((i & 4) != 0) {
            str3 = commandPlayResponse.endpoint;
        }
        String str5 = str3;
        if ((i & 8) != 0) {
            context = commandPlayResponse.context;
        }
        Context context2 = context;
        if ((i & 16) != 0) {
            preparePlayOptions = commandPlayResponse.playOptions;
        }
        PreparePlayOptions preparePlayOptions2 = preparePlayOptions;
        if ((i & 32) != 0) {
            playOrigin = commandPlayResponse.playOrigin;
        }
        return commandPlayResponse.copy(str, str4, str5, context2, preparePlayOptions2, playOrigin);
    }

    public final String component1() {
        return this.requestId;
    }

    public final String component2() {
        return this.error;
    }

    public final String component3() {
        return this.endpoint;
    }

    public final Context component4() {
        return this.context;
    }

    public final PreparePlayOptions component5() {
        return this.playOptions;
    }

    public final PlayOrigin component6() {
        return this.playOrigin;
    }

    public final CommandPlayResponse copy(@z61(name = "req_id") String str, @z61(name = "error") String str2, @z61(name = "endpoint") String str3, @z61(name = "context") Context context, @z61(name = "options") PreparePlayOptions preparePlayOptions, @z61(name = "play_origin") PlayOrigin playOrigin) {
        v03.j(str3, "endpoint");
        return new CommandPlayResponse(str, str2, str3, context, preparePlayOptions, playOrigin);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandPlayResponse)) {
            return false;
        }
        CommandPlayResponse commandPlayResponse = (CommandPlayResponse) obj;
        return v03.b(this.requestId, commandPlayResponse.requestId) && v03.b(this.error, commandPlayResponse.error) && v03.b(this.endpoint, commandPlayResponse.endpoint) && v03.b(this.context, commandPlayResponse.context) && v03.b(this.playOptions, commandPlayResponse.playOptions) && v03.b(this.playOrigin, commandPlayResponse.playOrigin);
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    public final String getError() {
        return this.error;
    }

    public final PreparePlayOptions getPlayOptions() {
        return this.playOptions;
    }

    public final PlayOrigin getPlayOrigin() {
        return this.playOrigin;
    }

    public final String getRequestId() {
        return this.requestId;
    }

    public int hashCode() {
        String str = this.requestId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.error;
        int iB = hf2.b(this.endpoint, (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31, 31);
        Context context = this.context;
        int iHashCode2 = (iB + (context == null ? 0 : context.hashCode())) * 31;
        PreparePlayOptions preparePlayOptions = this.playOptions;
        int iHashCode3 = (iHashCode2 + (preparePlayOptions == null ? 0 : preparePlayOptions.hashCode())) * 31;
        PlayOrigin playOrigin = this.playOrigin;
        return iHashCode3 + (playOrigin != null ? playOrigin.hashCode() : 0);
    }

    public String toString() {
        return "CommandPlayResponse(requestId=" + this.requestId + ", error=" + this.error + ", endpoint=" + this.endpoint + ", context=" + this.context + ", playOptions=" + this.playOptions + ", playOrigin=" + this.playOrigin + ')';
    }
}
