package ts.car.service.carplay;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayAppListener {
    default void onCarPlayDisplayStatusChanged(boolean isShow) {
    }

    default void onEnhancedSiriParametersChanged(int voiceActivationMode, String voiceModelLanguage) {
    }

    default void onSuggestUi(int url) {
    }

    default void onAuxInUpdateState(int state) {
    }

    default void onAltRequestSetup() {
    }

    default void onRequestViewArea(int index) {
    }
}
