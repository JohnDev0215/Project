package com.amazon.music.voice.launcher;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaLauncherPlugin.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/voice/launcher/LauncherTokens;", "", "", "toString", "", "hashCode", "other", "", "equals", "Landroid/net/Uri;", "uri", "Landroid/net/Uri;", "getUri", "()Landroid/net/Uri;", "directiveToken", "Ljava/lang/String;", "getDirectiveToken", "()Ljava/lang/String;", "eventToken", "getEventToken", "<init>", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;)V", "DMMVoice_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LauncherTokens {
    private final String directiveToken;
    private final String eventToken;
    private final Uri uri;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LauncherTokens)) {
            return false;
        }
        LauncherTokens launcherTokens = (LauncherTokens) other;
        return Intrinsics.areEqual(this.uri, launcherTokens.uri) && Intrinsics.areEqual(this.directiveToken, launcherTokens.directiveToken) && Intrinsics.areEqual(this.eventToken, launcherTokens.eventToken);
    }

    public int hashCode() {
        return (((this.uri.hashCode() * 31) + this.directiveToken.hashCode()) * 31) + this.eventToken.hashCode();
    }

    public String toString() {
        return "LauncherTokens(uri=" + this.uri + ", directiveToken=" + this.directiveToken + ", eventToken=" + this.eventToken + ')';
    }

    public LauncherTokens(Uri uri, String directiveToken, String eventToken) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(directiveToken, "directiveToken");
        Intrinsics.checkNotNullParameter(eventToken, "eventToken");
        this.uri = uri;
        this.directiveToken = directiveToken;
        this.eventToken = eventToken;
    }
}
