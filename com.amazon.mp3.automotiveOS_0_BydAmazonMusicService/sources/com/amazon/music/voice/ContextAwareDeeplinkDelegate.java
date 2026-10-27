package com.amazon.music.voice;

import android.net.Uri;
import com.amazon.music.voice.launcher.LaunchTargetError;
import com.amazon.music.voice.launcher.LauncherTokens;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: ContextAwareDeeplinkDelegate.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001JR\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u000728\u0010\b\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00030\tH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/voice/ContextAwareDeeplinkDelegate;", "", "handle", "", "uri", "Landroid/net/Uri;", "launcherTokens", "Lcom/amazon/music/voice/launcher/LauncherTokens;", "onComplete", "Lkotlin/Function2;", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "Lkotlin/ParameterName;", "name", "launchTargetError", "DMMVoice_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ContextAwareDeeplinkDelegate {
    void handle(Uri uri, LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete);
}
