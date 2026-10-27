package com.amazon.mp3.auto.carmode;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.navigation.ContextAwareDeeplinkManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: CarModeAlexaActionsHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001JD\u0010\u0002\u001a\u00020\u00032:\u0010\u0004\u001a6\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005H&JL\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2:\u0010\u0004\u001a6\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005H&JD\u0010\u000f\u001a\u00020\u00032:\u0010\u0004\u001a6\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005H&JD\u0010\u0010\u001a\u00020\u00032:\u0010\u0004\u001a6\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeAlexaActionsHandler;", "", "handleExitCarMode", "", "onComplete", "Lkotlin/Function2;", "Lcom/amazon/mp3/navigation/ContextAwareDeeplinkManager$Companion$Status;", "Lkotlin/ParameterName;", "name", NotificationCompat.CATEGORY_STATUS, "", "message", "handlePresetPlayback", "presetNumber", "", "handlePresetsPlaybackNext", "handlePresetsPlaybackPrevious", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface CarModeAlexaActionsHandler {
    void handleExitCarMode(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete);

    void handlePresetPlayback(int presetNumber, Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete);

    void handlePresetsPlaybackNext(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete);

    void handlePresetsPlaybackPrevious(Function2<? super ContextAwareDeeplinkManager.Companion.Status, ? super String, Unit> onComplete);
}
