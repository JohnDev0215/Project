package ts.car.service.carlife;

/* JADX INFO: loaded from: classes.dex */
public interface ICarLifeServiceListener {
    default void onVideoFocusChanged(int requestType) {
    }

    default void onProgressChanged(int progress) {
    }

    default void onMediaFocusStateChanged(boolean isCarLifeMedia) {
    }

    default void onViewResolutionChanged(int width, int height, int frameRate) {
    }
}
