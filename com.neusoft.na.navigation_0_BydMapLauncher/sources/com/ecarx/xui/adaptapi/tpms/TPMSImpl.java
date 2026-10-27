package com.ecarx.xui.adaptapi.tpms;

import android.util.Log;
import com.ecarx.xui.adaptapi.tpms.TPMS;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TPMSImpl extends TPMS {
    public static final String TAG = "TPMS";
    private TPMS.ITireStateMonitor mMonitor;
    private TireState mTireState;
    private RpcManager.OnRpcListener warningCallback = new RpcManager.OnRpcListener() { // from class: com.ecarx.xui.adaptapi.tpms.TPMSImpl.1
        public void onCallback(int i, byte[] bArr) {
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 2 && bArr[2] == 21) {
                Log.d(TPMSImpl.TAG, "_onCallback  bytes: " + Arrays.toString(bArr));
                byte b = bArr[9];
                byte b2 = bArr[10];
                byte b3 = bArr[11];
                byte b4 = bArr[12];
                if (b == 2) {
                    TPMSImpl.this.setTireStateChanged(1, 2);
                } else if (b == 3) {
                    TPMSImpl.this.setTireStateChanged(1, 1);
                } else if (b == 0) {
                    TPMSImpl.this.setTireStateChanged(1, 0);
                }
                if (b2 == 2) {
                    TPMSImpl.this.setTireStateChanged(3, 2);
                } else if (b2 == 3) {
                    TPMSImpl.this.setTireStateChanged(3, 1);
                } else if (b2 == 0) {
                    TPMSImpl.this.setTireStateChanged(3, 0);
                }
                if (b3 == 2) {
                    TPMSImpl.this.setTireStateChanged(2, 2);
                } else if (b3 == 3) {
                    TPMSImpl.this.setTireStateChanged(2, 1);
                } else if (b3 == 0) {
                    TPMSImpl.this.setTireStateChanged(2, 0);
                }
                if (b4 == 2) {
                    TPMSImpl.this.setTireStateChanged(4, 2);
                    return;
                } else if (b4 == 3) {
                    TPMSImpl.this.setTireStateChanged(4, 1);
                    return;
                } else {
                    if (b4 == 0) {
                        TPMSImpl.this.setTireStateChanged(4, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 17) {
                byte b5 = bArr[3];
                if (b5 == 1) {
                    TPMSImpl.this.setTireStateChanged(1, 4);
                    return;
                } else {
                    if (b5 == 0) {
                        TPMSImpl.this.setTireStateChanged(1, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 16) {
                byte b6 = bArr[3];
                if (b6 == 1) {
                    TPMSImpl.this.setTireStateChanged(3, 4);
                    return;
                } else {
                    if (b6 == 0) {
                        TPMSImpl.this.setTireStateChanged(3, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 15) {
                byte b7 = bArr[3];
                if (b7 == 1) {
                    TPMSImpl.this.setTireStateChanged(4, 4);
                    return;
                } else {
                    if (b7 == 0) {
                        TPMSImpl.this.setTireStateChanged(4, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 14) {
                byte b8 = bArr[3];
                if (b8 == 1) {
                    TPMSImpl.this.setTireStateChanged(2, 4);
                    return;
                } else {
                    if (b8 == 0) {
                        TPMSImpl.this.setTireStateChanged(2, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 13) {
                byte b9 = bArr[3];
                if (b9 == 1) {
                    TPMSImpl.this.setTireStateChanged(1, 3);
                    return;
                } else {
                    if (b9 == 0) {
                        TPMSImpl.this.setTireStateChanged(1, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 12) {
                byte b10 = bArr[3];
                if (b10 == 1) {
                    TPMSImpl.this.setTireStateChanged(3, 3);
                    return;
                } else {
                    if (b10 == 0) {
                        TPMSImpl.this.setTireStateChanged(3, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 11) {
                byte b11 = bArr[3];
                if (b11 == 1) {
                    TPMSImpl.this.setTireStateChanged(4, 3);
                    return;
                } else {
                    if (b11 == 0) {
                        TPMSImpl.this.setTireStateChanged(4, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 10) {
                byte b12 = bArr[3];
                if (b12 == 1) {
                    TPMSImpl.this.setTireStateChanged(2, 3);
                    return;
                } else {
                    if (b12 == 0) {
                        TPMSImpl.this.setTireStateChanged(2, 0);
                        return;
                    }
                    return;
                }
            }
            if (i == 1867 && bArr[0] == 0 && bArr[1] == 7 && bArr[2] == 18) {
                byte b13 = bArr[3];
                if (b13 == 2) {
                    TPMSImpl.this.setTireStateChanged(0, 5);
                } else if (b13 == 0) {
                    TPMSImpl.this.setTireStateChanged(0, 0);
                }
            }
        }
    };

    @Override // com.ecarx.xui.adaptapi.tpms.TPMS
    public ITireState getTireState(int i) {
        Log.d("Tire", "getTireState number = " + i);
        TireState tireState = new TireState(i);
        this.mTireState = tireState;
        return tireState;
    }

    @Override // com.ecarx.xui.adaptapi.tpms.TPMS
    public boolean registerTireStateMonitor(TPMS.ITireStateMonitor iTireStateMonitor) {
        if (iTireStateMonitor == null) {
            Log.d(TAG, "IVehicleWarningObserver == null");
            return false;
        }
        this.mMonitor = iTireStateMonitor;
        RpcManager.getInstance().registerListener(122355712, (byte) 1, this.warningCallback);
        Log.d(TAG, "registerTireStateMonitor = " + iTireStateMonitor.toString());
        return true;
    }

    @Override // com.ecarx.xui.adaptapi.tpms.TPMS
    public boolean unregisterTireStateMonitor(TPMS.ITireStateMonitor iTireStateMonitor) {
        if (iTireStateMonitor == null) {
            Log.d(TAG, "unregisterTireStateMonitor == null");
            return false;
        }
        RpcManager.getInstance().unregisterListener(122355712, (byte) 1, this.warningCallback);
        this.mMonitor = null;
        Log.d(TAG, "unregisterTireStateMonitor");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTireStateChanged(int i, int i2) {
        Log.d(TAG, "setTireStateChanged tire = " + i + ", type = " + i2);
        TireState tireState = new TireState(i);
        tireState.setTireWarning(i2);
        TPMS.ITireStateMonitor iTireStateMonitor = this.mMonitor;
        if (iTireStateMonitor != null) {
            iTireStateMonitor.onTireStateChanged(i, tireState);
        }
    }
}
