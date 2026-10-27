package com.ecarx.xui.adaptapi.device.log;

import com.ecarx.xui.adaptapi.device.log.DeviceLog;
import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceLogImpl implements DeviceLog {
    FileToZip fileToZip;
    private DeviceLog.IRequestCallback logCallback;
    Thread thread;
    private String cTag = "DeviceLogImpl";
    String sourceFilePath = "/sdcard/KC2Log";
    String zipFilePath = "/sdcard";
    String fileName = "uploadlog";
    Runnable runnable = new Runnable() { // from class: com.ecarx.xui.adaptapi.device.log.DeviceLogImpl.1
        @Override // java.lang.Runnable
        public void run() {
            DeviceLogImpl.this.fileToZip = new FileToZip();
            LogTool.d(DeviceLogImpl.this.cTag, "zip start");
            FileToZip fileToZip = DeviceLogImpl.this.fileToZip;
            if (FileToZip.fileToZip(DeviceLogImpl.this.sourceFilePath, DeviceLogImpl.this.zipFilePath, DeviceLogImpl.this.fileName)) {
                LogTool.d(DeviceLogImpl.this.cTag, "zip end");
                String[] strArr = {DeviceLogImpl.this.zipFilePath + "/" + DeviceLogImpl.this.fileName + ".zip"};
                if (DeviceLogImpl.this.logCallback != null) {
                    DeviceLogImpl.this.logCallback.onLogUploadFilesReady(strArr);
                }
            } else {
                LogTool.d(DeviceLogImpl.this.cTag, "zip error");
            }
            DeviceLogImpl.this.thread = null;
        }
    };

    @Override // com.ecarx.xui.adaptapi.device.log.DeviceLog
    public String[] getLogRootFolders() {
        return new String[0];
    }

    @Override // com.ecarx.xui.adaptapi.device.log.DeviceLog
    public void setLogcatLogLevel(int[] iArr) {
    }

    @Override // com.ecarx.xui.adaptapi.device.log.DeviceLog
    public void requestLogUpload(DeviceLog.IRequestCallback iRequestCallback) {
        LogTool.d(this.cTag, "requestLogUpload thread=" + this.thread);
        this.logCallback = iRequestCallback;
        if (this.thread == null) {
            Thread thread = new Thread(this.runnable);
            this.thread = thread;
            thread.start();
            return;
        }
        LogTool.d(this.cTag, "system is ziping now!!!! please wait");
    }

    @Override // com.ecarx.xui.adaptapi.device.log.DeviceLog
    public void requestLogUpload(int[] iArr, DeviceLog.IRequestCallback iRequestCallback) {
        requestLogUpload(iRequestCallback);
    }

    @Override // com.ecarx.xui.adaptapi.device.log.DeviceLog
    public void requestLogUpload(int[] iArr, long j, DeviceLog.IRequestCallback iRequestCallback) {
        requestLogUpload(iRequestCallback);
    }

    @Override // com.ecarx.xui.adaptapi.device.log.DeviceLog
    public void releaseRequestCallback() {
        this.logCallback = null;
    }

    @Override // com.ecarx.xui.adaptapi.device.log.DeviceLog
    public String[] getLogRootFolders(int i) {
        return new String[]{this.zipFilePath + "/" + this.fileName + ".zip"};
    }
}
