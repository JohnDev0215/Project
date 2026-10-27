package com.ts.carplay.app.service.manager;

import android.content.res.Configuration;
import ts.car.service.carplay.data.CallStateInfo;
import ts.car.service.carplay.data.NowPlayingInfo;
import ts.car.service.carplay.data.RouteGuidanceInfo;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayRemoteManagerListener {
    default void onServiceStateChanged(boolean connected) {
    }

    default void onCarPlaySessionStateChanged(int status) {
    }

    default void onAppStatesChanged(int appState, boolean enabled, int speechMode) {
    }

    default void onAppStatesChanged(int appState, int entity, int speechMode) {
    }

    default void onNowPlayingUpdate(NowPlayingInfo info) {
    }

    default void onRouteGuidanceUpdate(RouteGuidanceInfo info) {
    }

    default void onCallStateUpdate(CallStateInfo info) {
    }

    default void onCarPlayMediaFocusState(boolean isCarPlayMedia) {
    }

    default void onEnhancedSiriParametersChanged(int voiceActivationMode, String voiceModelLanguage) {
    }

    default void onCarPlayDisplayStatusChanged(boolean isShow) {
    }

    default void onSuggestUi(int url) {
    }

    default void onAuxInUpdateState(int state) {
    }

    default void onAltRequestSetup() {
    }

    default void onRequestViewArea(int index) {
    }

    default void onEcallStatusChanged(int status) {
    }

    default void onConfigurationChanged(Configuration newConfig) {
    }
}
