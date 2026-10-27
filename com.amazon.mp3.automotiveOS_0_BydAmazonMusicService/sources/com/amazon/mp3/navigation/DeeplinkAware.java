package com.amazon.mp3.navigation;

import android.net.Uri;
import com.amazon.music.voice.launcher.LaunchTargetError;
import com.amazon.music.voice.launcher.LauncherTokens;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: DeeplinkAware.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u001a\u0010\b\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\n\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\tH&¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/navigation/DeeplinkAware;", "", "handle", "", "uri", "Landroid/net/Uri;", "launcherTokens", "Lcom/amazon/music/voice/launcher/LauncherTokens;", "onComplete", "Lkotlin/Function2;", "Lcom/amazon/music/voice/launcher/LaunchTargetError;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface DeeplinkAware {
    void handle(Uri uri, LauncherTokens launcherTokens, Function2<? super LaunchTargetError, ? super LauncherTokens, Unit> onComplete);
}
