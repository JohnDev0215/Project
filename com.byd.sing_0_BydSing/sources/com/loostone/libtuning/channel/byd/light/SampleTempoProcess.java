package com.loostone.libtuning.channel.byd.light;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO0O0;
import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO;
import com.loostone.libtuning.inf.extern.IAudioTempoProcess;
import com.loostone.libtuning.inf.extern.ITempoCallBack;

/* JADX INFO: loaded from: classes.dex */
public class SampleTempoProcess implements IAudioTempoProcess {
    private static final String TAG = "SampleTempo";
    private int brightness;
    private ITempoCallBack callBack;
    private boolean colorChange;
    private int colorIndex;
    private int[] colors;
    private boolean shineFlag;
    private int sleepTime = 50;
    private final OooO0O0 command = new OooO0O0.a().g(OooO0O0.OooO.DEFAULT).a(71).j(4).f(OooO0O0.OooO0o.READ).e(OooO0O0.OooO0OO.NONE).i();

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public void deinit() {
        OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0.f531a.d(new OooO0O0.a().g(OooO0O0.OooO.DEFAULT).a(71).j(1).f(OooO0O0.OooO0o.WRITE).e(OooO0O0.OooO0OO.NONE).h("3").i());
        this.shineFlag = false;
        if (this.callBack != null) {
            this.callBack = null;
        }
        OooO.f199a.j0().e(Boolean.FALSE);
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public int getBrightness() {
        int[] iArrB = OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0.f531a.b(this.command);
        if (iArrB != null && iArrB.length == 2) {
            this.brightness = iArrB[0];
            this.colorChange = iArrB[1] == 1;
        }
        return ColorDefine.BRIGHTNESS_GROUP5[this.brightness];
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public int getColor() {
        int[] iArr = this.colors;
        if (iArr == null) {
            return 0;
        }
        if (this.colorIndex >= iArr.length) {
            this.colorIndex = 0;
        }
        return iArr[this.colorIndex];
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public boolean getColorChangeOrNot() {
        if (!this.colorChange) {
            return false;
        }
        this.colorChange = false;
        int i = this.colorIndex + 1;
        this.colorIndex = i;
        if (i >= this.colors.length) {
            this.colorIndex = 0;
        }
        return true;
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public void init() {
        b.f41a.b(TAG, "BRIGHTNESS_GROUP5");
        OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0.f531a.d(new OooO0O0.a().g(OooO0O0.OooO.DEFAULT).a(71).j(1).f(OooO0O0.OooO0o.WRITE).e(OooO0O0.OooO0OO.NONE).h("1").i());
        OooO.f199a.j0().e(Boolean.TRUE);
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public void initSong() {
        OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0.f531a.d(new OooO0O0.a().g(OooO0O0.OooO.DEFAULT).a(71).j(1).f(OooO0O0.OooO0o.WRITE).e(OooO0O0.OooO0OO.NONE).h("2").i());
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public void setColors(int[] iArr) {
        this.colors = iArr;
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public void setListener(final ITempoCallBack iTempoCallBack) {
        this.callBack = iTempoCallBack;
        if (this.shineFlag) {
            return;
        }
        this.shineFlag = true;
        new Thread(new Runnable() { // from class: com.loostone.libtuning.channel.byd.light.SampleTempoProcess.1
            @Override // java.lang.Runnable
            public void run() {
                while (SampleTempoProcess.this.shineFlag) {
                    ITempoCallBack iTempoCallBack2 = iTempoCallBack;
                    if (iTempoCallBack2 != null) {
                        iTempoCallBack2.setBrightness(SampleTempoProcess.this.getBrightness());
                        iTempoCallBack.setColor(SampleTempoProcess.this.getColor());
                        if (SampleTempoProcess.this.getColorChangeOrNot()) {
                            iTempoCallBack.colorChange();
                        }
                    }
                    try {
                        Thread.sleep(SampleTempoProcess.this.sleepTime);
                    } catch (InterruptedException e) {
                        SampleTempoProcess.this.shineFlag = false;
                        e.printStackTrace();
                    }
                }
                SampleTempoProcess.this.shineFlag = false;
            }
        }).start();
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public void setMode(int i, int i2) {
        int i3 = 0;
        if (i == 2) {
            this.colors = new int[ColorDefine.COLOR_GROUP2.length];
            while (true) {
                int[][] iArr = ColorDefine.COLOR_GROUP2;
                if (i3 >= iArr.length) {
                    return;
                }
                this.colors[i3] = ColorUtil.getColor(iArr[i3]);
                i3++;
            }
        } else if (i == 3) {
            this.colors = new int[ColorDefine.COLOR_GROUP3.length];
            while (true) {
                int[][] iArr2 = ColorDefine.COLOR_GROUP3;
                if (i3 >= iArr2.length) {
                    return;
                }
                this.colors[i3] = ColorUtil.getColor(iArr2[i3]);
                i3++;
            }
        } else {
            if (i != 4) {
                this.colors = new int[]{ColorUtil.getColor(ColorDefine.COLOR_GROUP1[i2 % 31])};
                return;
            }
            this.colors = new int[ColorDefine.COLOR_GROUP4.length];
            while (true) {
                int[][] iArr3 = ColorDefine.COLOR_GROUP4;
                if (i3 >= iArr3.length) {
                    return;
                }
                this.colors[i3] = ColorUtil.getColor(iArr3[i3]);
                i3++;
            }
        }
    }

    @Override // com.loostone.libtuning.inf.extern.IAudioTempoProcess
    public void setSleepTime(int i) {
        this.sleepTime = Math.max(i, 0);
    }
}
