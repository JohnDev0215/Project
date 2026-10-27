package defpackage;

import org.chromium.components.content_capture.PlatformContentCaptureController;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m30 implements nw2 {
    public final /* synthetic */ int e;

    public /* synthetic */ m30(int i) {
        this.e = i;
    }

    @Override // defpackage.nw2
    public final Object get() {
        switch (this.e) {
            case 0:
                return null;
            case 1:
                Object obj = b62.d;
                return PlatformContentCaptureController.d;
            case 2:
                return Float.valueOf(0.0f);
            default:
                return 0;
        }
    }
}
