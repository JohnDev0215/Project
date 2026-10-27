package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
public class UiConfigData {
    public AapClusterUiConfig mAapClusterUiConfig480p = new AapClusterUiConfig();
    public AapClusterUiConfig mAapClusterUiConfig720p = new AapClusterUiConfig();
    public AapClusterUiConfig mAapClusterUiConfig1080p = new AapClusterUiConfig();

    public class AapClusterUiConfig {
        public int mAapContentInsetsBottom;
        public int mAapContentInsetsLeft;
        public int mAapContentInsetsRight;
        public int mAapContentInsetsTop;
        public int mAapMarginBottom;
        public int mAapMarginLeft;
        public int mAapMarginRight;
        public int mAapMarginTop;
        public int mAapStableContentInsetsBottom;
        public int mAapStableContentInsetsLeft;
        public int mAapStableContentInsetsRight;
        public int mAapStableContentInsetsTop;

        public AapClusterUiConfig() {
        }
    }

    public String toString() {
        return "mAapClusterUiConfig480p:" + this.mAapClusterUiConfig480p + " mAapClusterUiConfig720p:" + this.mAapClusterUiConfig720p + " mAapClusterUiConfig1080p:" + this.mAapClusterUiConfig1080p;
    }
}
