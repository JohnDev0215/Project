package com.ecarx.xui.adaptapi.dvr;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Log;
import com.ecarx.xui.adaptapi.dvr.IOperation;
import com.ecarx.xui.adaptapi.dvr.ota.IOta;
import com.ecarx.xui.adaptapi.wpc.specific.WPCModel;
import com.neusoft.alfus.RpcManager;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DvrImp extends Dvr {
    private static final int RPC_DATA_LEN = 25;
    public static final int cGetDTCLost = 1913;
    private IOperation iOperation;
    private IOperation.IOperationCallback iOperationCallback;
    private RpcReceiver rpcReceiver;
    public Boolean sPepsAccOn;

    @Override // com.ecarx.xui.adaptapi.dvr.Dvr
    public IOta getOta() {
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.dvr.Dvr
    public ISettings getSettings() {
        return null;
    }

    public DvrImp(Context context) {
        super(context);
        this.sPepsAccOn = false;
        this.iOperation = new IOperation() { // from class: com.ecarx.xui.adaptapi.dvr.DvrImp.1
            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean backToHome() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean browseFirstPage() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean browseLastPage() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean browseNextPage() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean browsePage(int i) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean browsePreviousPage() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean capture() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean changeMode(int i) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean deleteAllFiles() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean deleteAllFilesWithType(int i) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean deleteFiles(List<IDvrFile> list) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean exitPlay() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean factoryReset() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean formatSdcard() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public int getPageCount() {
                return 0;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public int getPlayingStatus() {
                return 0;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public int getSdcardStatus() {
                return 0;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean isCameraOnline() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean lockFile(IDvrFile iDvrFile) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean moveVideosToEmergencyVideoPartition(List<IDvrVideoFile> list) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean naviDown() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean naviLeft() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean naviRight() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean naviUp() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean pause() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean play() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean play(IDvrFile iDvrFile) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean playNext() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean playPrevious() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean replay() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean rotatePhoto(int i) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean rotatePhoto(IDvrPhotoFile iDvrPhotoFile, int i) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean selectAllFiles() {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean selectFile(IDvrFile iDvrFile) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public boolean unlockFile(IDvrFile iDvrFile) {
                return false;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public int getCurrentMode() {
                Log.d("DvrImp", "checkCanData");
                if (DvrImp.this.isDVRLostCanData().booleanValue()) {
                    return 8192;
                }
                Log.d("DvrImp", "getDVRSystemData");
                byte[] bArr = new byte[25];
                bArr[0] = 0;
                bArr[1] = 1;
                bArr[2] = 20;
                byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
                if (message == null) {
                    Log.d("DvrImp", "data nll");
                    return 8192;
                }
                byte b = message[8];
                if (b == 0) {
                    Log.d("DvrImp", "initializing");
                    return 1;
                }
                if (b == 1) {
                    Log.d("DvrImp", "generalrecord");
                    return 2;
                }
                if (b == 2) {
                    Log.d("DvrImp", "pauserecord");
                    return 4;
                }
                if (b == 3) {
                    Log.d("DvrImp", "emergencyrecordbyuser");
                    return 32;
                }
                if (b == 4) {
                    Log.d("DvrImp", "emergencyrecordbycrash");
                    return 32;
                }
                if (b == 5) {
                    Log.d("DvrImp", "riskrecord");
                    return 32;
                }
                if (b == 6) {
                    Log.d("DvrImp", "generalbrowse");
                    return SystemProperties.get("ro.product.device").contains("KC") ? 4 : 0;
                }
                if (b == 7) {
                    Log.d("DvrImp", "emergencybrowse");
                    return SystemProperties.get("ro.product.device").contains("KC") ? 4 : 0;
                }
                if (b == 8) {
                    Log.d("DvrImp", "riskbrowse");
                    return SystemProperties.get("ro.product.device").contains("KC") ? 4 : 0;
                }
                if (b == 9) {
                    Log.d("DvrImp", "photobrowse");
                    return SystemProperties.get("ro.product.device").contains("KC") ? 4 : 0;
                }
                if (b == 10) {
                    Log.d("DvrImp", "playvideo");
                    return SystemProperties.get("ro.product.device").contains("KC") ? 4 : 0;
                }
                if (b == 11) {
                    Log.d("DvrImp", "pausevideo");
                    return SystemProperties.get("ro.product.device").contains("KC") ? 4 : 0;
                }
                if (b == 12) {
                    Log.d("DvrImp", "updatemode");
                    return 4;
                }
                if (b == 15) {
                    Log.d("DvrImp", "error");
                }
                return 8192;
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public void setCallback(IOperation.IOperationCallback iOperationCallback) {
                Log.d("DvrImp", "setcallback");
                if (DvrImp.this.isDVRLostCanData().booleanValue()) {
                    Log.d("DvrImp", "can lost");
                    iOperationCallback.onMode(8192);
                }
                if (DvrImp.this.rpcReceiver == null) {
                    DvrImp.this.rpcReceiver = new RpcReceiver(iOperationCallback);
                }
                DvrImp.this.rpcReceiver.init();
                Log.d("DvrImp", "initReceiver");
            }

            @Override // com.ecarx.xui.adaptapi.dvr.IOperation
            public void unsetCallback(IOperation.IOperationCallback iOperationCallback) {
                DvrImp.this.rpcReceiver.exit();
                DvrImp.this.rpcReceiver = null;
            }
        };
    }

    @Override // com.ecarx.xui.adaptapi.dvr.Dvr
    public IOperation getOperation() {
        return this.iOperation;
    }

    public Boolean getAccState() {
        byte b;
        Log.d("DvrImp", "getAccState");
        byte[] bArr = new byte[25];
        bArr[0] = 0;
        bArr[1] = 2;
        bArr[2] = WPCModel.cSignalId0x27;
        byte[] message = RpcManager.getInstance().getMessage(1881, bArr);
        if (message == null) {
            Log.d("DvrImp", "Peps data is null");
            this.sPepsAccOn = false;
        } else {
            Log.d("DvrImp", "PEPS valid = " + ((int) message[14]) + "   ACC = " + ((int) message[13]));
            if (message[14] == 2 && ((b = message[13]) == 1 || b == 2 || b == 4)) {
                Log.d("DvrImp", "PEPS valid is ACC ON");
                this.sPepsAccOn = true;
            } else {
                this.sPepsAccOn = false;
            }
        }
        return this.sPepsAccOn;
    }

    public Boolean isDVRLostCanData() {
        byte[] bArr = new byte[25];
        bArr[0] = 25;
        bArr[1] = 2;
        byte[] message = RpcManager.getInstance().getMessage(1913, bArr);
        Log.d("DvrImp", "GET-DVRDataLost = " + Arrays.toString(message));
        if (message != null && (message[0] & 1) == 1) {
            Log.d("DvrImp", "DVR lost CAN data");
            return true;
        }
        return false;
    }
}
