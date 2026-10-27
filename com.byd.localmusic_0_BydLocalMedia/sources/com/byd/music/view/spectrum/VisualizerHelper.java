package com.byd.music.view.spectrum;

import android.media.audiofx.Visualizer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class VisualizerHelper {
    private static VisualizerHelper helper;
    private List<OnVisualizerEnergyCallBack> onEnergyCallBacks = new ArrayList();
    private final Visualizer.OnDataCaptureListener dataCaptureListener = new Visualizer.OnDataCaptureListener() { // from class: com.byd.music.view.spectrum.VisualizerHelper.1
        @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
        public void onWaveFormDataCapture(Visualizer visualizer, byte[] bArr, int i) {
            if (AppConstant.isFFT) {
                return;
            }
            VisualizerHelper.this.dispose(bArr);
        }

        @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
        public void onFftDataCapture(Visualizer visualizer, byte[] bArr, int i) {
            if (AppConstant.isFFT) {
                VisualizerHelper.this.dispose(bArr);
            }
        }
    };

    public interface OnVisualizerEnergyCallBack {
        void setWaveData(byte[] bArr, float f);
    }

    public static synchronized VisualizerHelper getInstance() {
        VisualizerHelper visualizerHelper;
        synchronized (VisualizerHelper.class) {
            if (helper == null) {
                helper = new VisualizerHelper();
            }
            visualizerHelper = helper;
        }
        return visualizerHelper;
        return visualizerHelper;
    }

    public Visualizer.OnDataCaptureListener getDataCaptureListener() {
        return this.dataCaptureListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispose(byte[] bArr) {
        byte[] bArr2 = new byte[256];
        float f = 0.0f;
        if (AppConstant.imageValue) {
            byte[] bArr3 = new byte[128];
            for (int i = 0; i < 128; i++) {
                byte bAbs = (byte) Math.abs((int) bArr[128 - i]);
                if (bAbs < 0) {
                    bAbs = 0;
                }
                bArr3[i] = bAbs;
                bArr2[i] = bArr3[i];
                f += bArr2[i];
            }
            for (int i2 = 0; i2 < 128; i2++) {
                bArr2[128 + i2] = bArr3[127 - i2];
                f += bArr2[i2];
            }
        } else {
            for (int i3 = 0; i3 < 256; i3++) {
                byte bAbs2 = (byte) Math.abs((int) bArr[i3]);
                if (bAbs2 < 0) {
                    bAbs2 = 0;
                }
                bArr2[i3] = bAbs2;
                f += bArr2[i3];
            }
        }
        for (int i4 = 0; i4 < this.onEnergyCallBacks.size(); i4++) {
            this.onEnergyCallBacks.get(i4).setWaveData(bArr2, f);
        }
    }

    public void addCallBack(OnVisualizerEnergyCallBack onVisualizerEnergyCallBack) {
        this.onEnergyCallBacks.add(onVisualizerEnergyCallBack);
    }

    public void removeCallBack(OnVisualizerEnergyCallBack onVisualizerEnergyCallBack) {
        this.onEnergyCallBacks.remove(onVisualizerEnergyCallBack);
    }
}
