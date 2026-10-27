package com.loostone.libtuning.channel.byd.upgrade.util;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.usb.UsbDevice;
import android.os.Build;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.w;
import com.loostone.libtuning.channel.byd.old.IDongleUpgrade;
import com.loostone.libtuning.channel.byd.upgrade.DongleUpgradeInfo;
import com.loostone.libtuning.channel.byd.upgrade.UpdateHelperMic;
import com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware;
import com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset;
import com.loostone.libtuning.channel.byd.upgrade.util.InfoConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import net.lingala.zip4j.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class MicUpdateUtil {
    private static final String DEFAULT_DEVICE_INFO = "mic";
    private static final String DEFAULT_DEVICE_NAME = "麦克风";
    private static final String DONGLE_FIRMWARE = "mic_firmware.bin";
    private static final String DONGLE_FIRMWARE_MORE_DIR = "MicFirmwareMore";
    private static final String DONGLE_FIRMWARE_PATH = "MicFirmware";
    private static final String DONGLE_FIRMWARE_ZIP = "loostone_mic_firmware.zip";
    private static final String DONGLE_FLASH = "mic_flash.bin";
    private static final String DONGLE_INFO = "mic_info.json";
    private static final String TAG = "MicUpdateUtil";
    private static volatile MicUpdateUtil mInstance;
    private AlertDialog.Builder dialogBuilder;
    private DongleUpgradeInfo dongleUpgradeInfo;
    private IDongleUpgrade iDongleListener;
    private boolean isStopped;
    private IUpgradeAndReset.CheckNewVersionCallback mCheckNewVersionCallback;
    private UsbDevice mUsbDevice;
    private String mDglSN = BuildConfig.FLAVOR;
    private boolean isUnzipping = false;
    private boolean realUpdating = false;
    private Boolean isUpgradeSuccess = null;
    private Integer mErrorCode = null;
    private final UpdateHelperMic helper = new UpdateHelperMic();
    private final IUpdateFirmware.Callback mCallback = new IUpdateFirmware.Callback() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.MicUpdateUtil.1
        @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware.Callback
        public void error(int i) {
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.b(MicUpdateUtil.TAG, "errorCode:" + i);
            if (!MicUpdateUtil.this.realUpdating) {
                bVar.g(MicUpdateUtil.TAG, "return, during non-upgrade phase!");
                return;
            }
            MicUpdateUtil.this.mErrorCode = Integer.valueOf(i);
            if (MicUpdateUtil.this.iDongleListener != null) {
                MicUpdateUtil.this.iDongleListener.onDongleUpdateStatus(5, "升级出错了，错误代码:" + i);
            }
            MicUpdateUtil.this.clearUpgradeList();
            MicUpdateUtil.this.isUpgradeSuccess = Boolean.FALSE;
            MicUpdateUtil micUpdateUtil = MicUpdateUtil.this;
            micUpdateUtil.mDglSN = micUpdateUtil.getMicSn();
        }

        @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware.Callback
        public void finish() {
            OooO00o.OooO00o.OooO0O0.b.f41a.b(MicUpdateUtil.TAG, "finish");
            if (MicUpdateUtil.this.iDongleListener != null) {
                MicUpdateUtil.this.iDongleListener.onDongleUpdateStatus(4, "升级完成");
            }
            if (MicUpdateUtil.this.mUpgradeFromUDisk) {
                ToastUtils.u("The mic firmware upgrade was successful.");
            }
            MicUpdateUtil.this.isUpgradeSuccess = Boolean.TRUE;
            MicUpdateUtil micUpdateUtil = MicUpdateUtil.this;
            micUpdateUtil.mDglSN = micUpdateUtil.getMicSn();
        }

        @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware.Callback
        public void process(int i) {
            OooO00o.OooO00o.OooO0O0.b.f41a.b(MicUpdateUtil.TAG, "process:process = " + i);
            if (MicUpdateUtil.this.iDongleListener != null) {
                MicUpdateUtil.this.iDongleListener.onDongleUpdateStatus(6, i + BuildConfig.FLAVOR);
            }
        }
    };
    private boolean mUpgradeFromUDisk = false;
    private String mVersionName = BuildConfig.FLAVOR;
    private final ArrayList<Map<String, Object>> mUpgradeList = new ArrayList<>();
    private String mDeviceName = BuildConfig.FLAVOR;
    private AlertDialog alertDialog = null;

    private MicUpdateUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canUpdate(String str) {
        UsbDevice usbDevice;
        if (TextUtils.isEmpty(str)) {
            if (this.realUpdating) {
                str = "正在升级中...";
            } else if (TextUtils.isEmpty(this.mDeviceName)) {
                if (this.mUpgradeFromUDisk) {
                    if (TextUtils.isEmpty(this.mDeviceName) && (usbDevice = this.mUsbDevice) != null && !TextUtils.isEmpty(usbDevice.getProductName())) {
                        this.mDeviceName = this.mUsbDevice.getProductName();
                        if (this.mUsbDevice.getProductName().length() > 2) {
                            String str2 = this.mDeviceName;
                            this.mDeviceName = str2.substring(str2.length() - 2);
                        }
                    }
                    OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "canUpdate, mDeviceName = " + this.mDeviceName);
                } else {
                    str = "未成功识别USB设备型号";
                }
            } else if (this.isUnzipping) {
                str = "正在解压中...";
            }
        }
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.g(TAG, "update error or intercepted :: " + str);
        clearUpgradeList();
        notifyUpgrade(BuildConfig.FLAVOR);
        return false;
    }

    private void dismissDialog() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.alertDialog.dismiss();
    }

    private byte[] getDeviceVersionCode() {
        ArrayList<byte[]> arrayListQueryDeviceVersionCode;
        if (this.mUsbDevice == null || this.realUpdating || DongleUpdateUtil.getInstance().isRealUpdating() || (arrayListQueryDeviceVersionCode = this.helper.queryDeviceVersionCode()) == null || arrayListQueryDeviceVersionCode.size() <= 1) {
            return null;
        }
        return arrayListQueryDeviceVersionCode.get(1);
    }

    private AlertDialog.Builder getDialogBuilder(String str, String str2) {
        if (this.dialogBuilder == null) {
            this.dialogBuilder = new AlertDialog.Builder(OooO00o.OooO00o.OooO0O0.a.f39a.a());
        }
        this.dialogBuilder.setTitle(str).setMessage(str2);
        return this.dialogBuilder;
    }

    public static MicUpdateUtil getInstance() {
        if (mInstance == null) {
            synchronized (MicUpdateUtil.class) {
                if (mInstance == null) {
                    mInstance = new MicUpdateUtil();
                }
            }
        }
        return mInstance;
    }

    static /* synthetic */ boolean lambda$checkUpgrade$1(File file, File file2) {
        return !file.equals(file2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$checkUpgrade$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a(long j, String str, DialogInterface dialogInterface, int i) {
        update(j, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(6:(3:118|47|48)|(2:53|(1:55)(8:57|122|58|(2:63|(1:65)(1:67))(1:62)|123|68|78|(2:102|103)(2:88|(7:90|(1:92)|93|(1:95)|96|(1:98)|99)(2:100|101))))(1:52)|56|122|58|(9:60|63|(0)(0)|123|68|78|(1:80)|102|103)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x01ec, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01f4, code lost:
    
        r16 = 2576980377L;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01bd A[Catch: Exception -> 0x01ec, TryCatch #3 {Exception -> 0x01ec, blocks: (B:58:0x0184, B:60:0x01b0, B:62:0x01b6, B:63:0x01bd, B:65:0x01c1), top: B:122:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01c1 A[Catch: Exception -> 0x01ec, TRY_LEAVE, TryCatch #3 {Exception -> 0x01ec, blocks: (B:58:0x0184, B:60:0x01b0, B:62:0x01b6, B:63:0x01bd, B:65:0x01c1), top: B:122:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0223  */
    /* JADX INFO: renamed from: lambda$checkUpgrade$3, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void b(java.io.File r19) {
        /*
            Method dump skipped, instruction units count: 809
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.channel.byd.upgrade.util.MicUpdateUtil.b(java.io.File):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyUpgrade$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mCheckNewVersionCallback.onUpToDate();
        } else {
            this.mCheckNewVersionCallback.onNewVersionAvailable(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$showUpgradeAfter$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(DialogInterface dialogInterface, int i) {
        this.mUpgradeFromUDisk = true;
        try {
            update(Long.parseLong(this.dongleUpgradeInfo.getVersion(), 16), getFirmwareDirs());
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "parse mic versionCode error :: " + e);
        }
    }

    private void notifyUpgrade(final String str) {
        if (this.mCheckNewVersionCallback != null) {
            ThreadUtils.q(new Runnable() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3871b.c(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog() {
        AlertDialog.Builder builder = this.dialogBuilder;
        if (builder != null) {
            AlertDialog alertDialogCreate = builder.create();
            this.alertDialog = alertDialogCreate;
            if (Build.VERSION.SDK_INT >= 26) {
                alertDialogCreate.getWindow().setType(2038);
            } else {
                alertDialogCreate.getWindow().setType(2003);
            }
            this.alertDialog.show();
        }
    }

    private void showUpgradeAfter() {
        String str;
        if (this.mUpgradeFromUDisk && this.isUpgradeSuccess != null) {
            String versionName = getVersionName();
            OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "upgrade finish, versionName = " + versionName);
            if (this.isUpgradeSuccess.booleanValue()) {
                str = "恭喜，麦克风升级成功。\n从版本：" + this.mVersionName + "成功升级到版本：" + versionName;
            } else {
                if (this.mErrorCode != null) {
                    str = "麦克风升级出现未知异常，错误代码：" + this.mErrorCode + "，升级失败了。请重试\n";
                } else {
                    str = "麦克风升级出现未知异常，升级失败了。请重试\n";
                }
                this.mErrorCode = null;
            }
            if (this.isUpgradeSuccess.booleanValue()) {
                getDialogBuilder("升级完成", str).setPositiveButton("完成", (DialogInterface.OnClickListener) null).setNegativeButton(BuildConfig.FLAVOR, (DialogInterface.OnClickListener) null);
            } else {
                getDialogBuilder("升级完成", str).setPositiveButton("重试", new DialogInterface.OnClickListener() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.g
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.f3873b.d(dialogInterface, i);
                    }
                }).setNegativeButton("放弃", (DialogInterface.OnClickListener) null);
            }
            showDialog();
            this.isUpgradeSuccess = null;
        }
    }

    private void unzip(ZipFile zipFile, String str, String str2) {
        if (zipFile.isValidZipFile()) {
            File file = new File(str);
            if (file.isDirectory() && !file.exists()) {
                file.mkdir();
            }
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(str2.toCharArray());
            }
            zipFile.extractAll(str);
        }
    }

    private void update(final long j, final String str) {
        ThreadUtils.h(new w.d<String>(null) { // from class: com.loostone.libtuning.channel.byd.upgrade.util.MicUpdateUtil.3
            @Override // com.blankj.utilcode.util.ThreadUtils.e
            public String doInBackground() {
                OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
                bVar.b(MicUpdateUtil.TAG, "update-doInBackground-start");
                if (MicUpdateUtil.this.mUsbDevice == null) {
                    MicUpdateUtil.this.canUpdate("开始升级前设备被移除");
                    return "开始升级前设备被移除";
                }
                if (MicUpdateUtil.this.isStopped) {
                    bVar.g(MicUpdateUtil.TAG, "update error or intercepted :: stopped");
                    MicUpdateUtil.this.isStopped = false;
                    return "开始升级时意外取消了";
                }
                bVar.b(MicUpdateUtil.TAG, "update-doInBackground-middle");
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                String str2 = File.separator;
                sb.append(str2);
                sb.append(MicUpdateUtil.DONGLE_FLASH);
                String string = sb.toString();
                String str3 = str + str2 + MicUpdateUtil.DONGLE_FIRMWARE;
                MicUpdateUtil.this.realUpdating = true;
                MicUpdateUtil.this.helper.init(MicUpdateUtil.this.mUsbDevice);
                MicUpdateUtil.this.helper.setHeader(InfoConstant.MicUpdateType.UPDATE_FLASH);
                MicUpdateUtil.this.helper.queryHidVersionCode();
                MicUpdateUtil.this.helper.queryDeviceStatus();
                MicUpdateUtil.this.helper.queryDeviceVersionCode();
                MicUpdateUtil.this.helper.requestUpdate(j, string);
                MicUpdateUtil.this.helper.updateProcess();
                MicUpdateUtil.this.helper.setHeader(InfoConstant.MicUpdateType.UPDATE_BIN);
                MicUpdateUtil.this.helper.queryHidVersionCode();
                MicUpdateUtil.this.helper.queryDeviceStatus();
                MicUpdateUtil.this.helper.queryDeviceVersionCode();
                MicUpdateUtil.this.helper.requestUpdate(j, str3);
                MicUpdateUtil.this.helper.updateProcess();
                MicUpdateUtil.this.helper.queryCheckResult();
                MicUpdateUtil.this.helper.startUpdate();
                MicUpdateUtil.this.helper.queryUpdateResult();
                MicUpdateUtil.this.helper.release();
                MicUpdateUtil.this.realUpdating = false;
                MicUpdateUtil.this.iDongleListener = null;
                bVar.b(MicUpdateUtil.TAG, "update-doInBackground-end");
                return "麦克风升级完成";
            }
        });
    }

    public void checkUpgrade(IUpgradeAndReset.CheckNewVersionCallback checkNewVersionCallback) {
        if (this.realUpdating || DongleUpdateUtil.getInstance().isRealUpdating()) {
            OooO00o.OooO00o.OooO0O0.b.f41a.g(TAG, "checkUpgrade, realUpdating, break!!!");
            return;
        }
        this.mUpgradeList.clear();
        this.mCheckNewVersionCallback = checkNewVersionCallback;
        String strQueryMicInfo = DongleUpdateUtil.getInstance().queryMicInfo(14);
        this.mDeviceName = strQueryMicInfo;
        if (TextUtils.isEmpty(strQueryMicInfo)) {
            this.mDeviceName = DongleUpdateUtil.getInstance().queryMicInfo(15);
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "checkUpgrade ,mDeviceName = " + this.mDeviceName);
        if (TextUtils.isEmpty(this.mDeviceName)) {
            notifyUpgrade(BuildConfig.FLAVOR);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getFirmwareDirs(DONGLE_FIRMWARE_MORE_DIR));
        String str = File.separator;
        sb.append(str);
        sb.append("Loostone PureMic ");
        sb.append(this.mDeviceName);
        sb.append(str);
        sb.append(DONGLE_FIRMWARE_ZIP);
        checkUpgrade(new File(sb.toString()), checkNewVersionCallback);
    }

    public void clearUpgradeList() {
        this.mUpgradeList.clear();
    }

    public String convertByte2Char(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append((char) (b2 & 255));
        }
        return sb.toString();
    }

    public String convertByte2Str(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bArr) {
            sb.append(b2 & 255);
        }
        return sb.toString();
    }

    public String convertVersion(String str) {
        String strReplaceFirst = str.replaceFirst("^0*", BuildConfig.FLAVOR);
        StringBuilder sb = new StringBuilder();
        int length = strReplaceFirst.length() % 2;
        int length2 = strReplaceFirst.length() / 2;
        if (length > 0) {
            sb.append(Long.parseLong(strReplaceFirst.substring(0, length), 16));
            sb.append(".");
        }
        for (int i = 0; i < length2; i++) {
            int i2 = (i * 2) + length;
            sb.append(Long.parseLong(strReplaceFirst.substring(i2, i2 + 2), 16));
            sb.append(".");
        }
        if (sb.length() > 1) {
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        }
        String string = sb.toString();
        return string.startsWith("0") ? string.substring(1) : string;
    }

    public String getFirmwareDirs() {
        return getFirmwareDirs(DONGLE_FIRMWARE_PATH);
    }

    public String getMicSn() {
        UsbDevice usbDevice = this.mUsbDevice;
        if (usbDevice == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            return usbDevice.getSerialNumber();
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "getMicSn error :: " + e);
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public String getMiniFileMD5(File file) throws Throwable {
        boolean zIsFile = file.isFile();
        String strE = BuildConfig.FLAVOR;
        if (!zIsFile) {
            return BuildConfig.FLAVOR;
        }
        try {
            strE = getMiniFileMD5(new FileInputStream(file));
            OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "file, md5Value = " + strE);
        } catch (FileNotFoundException e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "getFileMD5 error :: " + e);
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(strE)) {
            strE = OooO00o.f18a.e(file.getAbsolutePath());
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "md5sum, md5Value = " + strE);
        return strE;
    }

    public ArrayList<Map<String, Object>> getUpgradeList() {
        return this.mUpgradeList;
    }

    public long getVersionCode() {
        byte[] deviceVersionCode = getDeviceVersionCode();
        long j = (deviceVersionCode == null || deviceVersionCode.length < 4) ? 2576980377L : (((long) (deviceVersionCode[0] & 255)) << 24) | ((long) ((deviceVersionCode[1] & 255) << 16)) | ((long) ((deviceVersionCode[2] & 255) << 8)) | ((long) (deviceVersionCode[3] & 255));
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "versionCode = " + j);
        return j;
    }

    public String getVersionName() {
        return convertVersion(getDeviceVersionCode());
    }

    public boolean hasRealNewVersion() {
        long j;
        try {
            j = Long.parseLong(this.dongleUpgradeInfo.getVersion(), 16);
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "hasRealNewVersion parse mic versionCode error :: " + e);
            j = 0;
        }
        return j > getVersionCode();
    }

    public boolean isRealUpdating() {
        return this.realUpdating;
    }

    public void release() {
        this.mUpgradeFromUDisk = false;
        this.realUpdating = false;
        this.mUpgradeList.clear();
        this.helper.release();
        this.mUsbDevice = null;
    }

    public void setUpgradeFromUDisk() {
        this.mUpgradeFromUDisk = true;
    }

    public void setUsbDevice(UsbDevice usbDevice, boolean z) {
        if (usbDevice == null) {
            this.mUsbDevice = null;
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setUsbDevice & init, isAdd = " + z);
        if (z) {
            UsbDevice usbDevice2 = this.mUsbDevice;
            if (usbDevice2 == null || usbDevice2 != usbDevice) {
                this.helper.init(usbDevice);
                this.helper.setCallback(this.mCallback);
                this.mUsbDevice = usbDevice;
            }
            if (TextUtils.equals(getMicSn(), this.mDglSN)) {
                showUpgradeAfter();
            }
            this.mUpgradeFromUDisk = false;
            this.mDglSN = BuildConfig.FLAVOR;
        } else {
            if (this.mUsbDevice != null) {
                this.helper.release();
                this.mUsbDevice = null;
            }
            this.realUpdating = false;
        }
        clearUpgradeList();
    }

    public void startUpdate(long j, String str, IDongleUpgrade iDongleUpgrade) {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "startUpdate---versionCode = " + j + ", filePath = " + str);
        this.iDongleListener = iDongleUpgrade;
        update(j, str);
    }

    public void stopUpdate() {
        OooO00o.OooO00o.OooO0O0.b.f41a.g(TAG, "update error or intercepted :: stopUpdate");
        this.isStopped = true;
        this.helper.cancelUpdate();
    }

    public void uDiskRemove() {
        if (!this.realUpdating) {
            this.mUpgradeFromUDisk = false;
        }
        dismissDialog();
    }

    public String getFirmwareDirs(String str) {
        String str2 = OooO00o.OooO00o.OooO0O0.a.f39a.a().getFilesDir().getAbsolutePath() + File.separator + str;
        File file = new File(str2);
        if (!file.exists()) {
            boolean zMkdirs = file.mkdirs();
            OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "mkdirs " + str + ", result = " + zMkdirs);
        }
        return str2;
    }

    public void startUpdate(File file, IDongleUpgrade iDongleUpgrade) {
        this.iDongleListener = iDongleUpgrade;
        checkUpgrade(file, new IUpgradeAndReset.CheckNewVersionCallback() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.MicUpdateUtil.2
            @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset.CheckNewVersionCallback
            public void onNewVersionAvailable(String str) {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(MicUpdateUtil.TAG, "startUpdate, onNewVersionAvailable");
                if (MicUpdateUtil.this.mUpgradeFromUDisk) {
                    MicUpdateUtil.this.showDialog();
                }
            }

            @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset.CheckNewVersionCallback
            public void onUpToDate() {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(MicUpdateUtil.TAG, "startUpdate, onUpToDate");
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getMiniFileMD5(java.io.InputStream r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.security.NoSuchAlgorithmException -> L4f
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.security.NoSuchAlgorithmException -> L4f
            r2.<init>(r6, r1)     // Catch: java.lang.Throwable -> L4b java.io.IOException -> L4d java.security.NoSuchAlgorithmException -> L4f
            r6 = 262144(0x40000, float:3.67342E-40)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
        L10:
            int r0 = r2.read(r6)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            if (r0 > 0) goto L10
            java.security.MessageDigest r6 = r2.getMessageDigest()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            byte[] r6 = r6.digest()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            java.lang.String r6 = com.blankj.utilcode.util.e.b(r6)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            OooO00o.OooO00o.OooO0O0.b r0 = OooO00o.OooO00o.OooO0O0.b.f41a     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            java.lang.String r1 = "MicUpdateUtil"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            r3.<init>()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            java.lang.String r4 = "is, md5Value = "
            r3.append(r4)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            r3.append(r6)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            r0.b(r1, r3)     // Catch: java.lang.Throwable -> L43 java.io.IOException -> L46 java.security.NoSuchAlgorithmException -> L49
            r2.close()     // Catch: java.io.IOException -> L3e
            goto L42
        L3e:
            r0 = move-exception
            r0.printStackTrace()
        L42:
            return r6
        L43:
            r6 = move-exception
            r0 = r2
            goto L60
        L46:
            r6 = move-exception
        L47:
            r0 = r2
            goto L50
        L49:
            r6 = move-exception
            goto L47
        L4b:
            r6 = move-exception
            goto L60
        L4d:
            r6 = move-exception
            goto L50
        L4f:
            r6 = move-exception
        L50:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L4b
            if (r0 == 0) goto L5d
            r0.close()     // Catch: java.io.IOException -> L59
            goto L5d
        L59:
            r6 = move-exception
            r6.printStackTrace()
        L5d:
            java.lang.String r6 = ""
            return r6
        L60:
            if (r0 == 0) goto L6a
            r0.close()     // Catch: java.io.IOException -> L66
            goto L6a
        L66:
            r0 = move-exception
            r0.printStackTrace()
        L6a:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.channel.byd.upgrade.util.MicUpdateUtil.getMiniFileMD5(java.io.InputStream):java.lang.String");
    }

    public void checkUpgrade(final File file, IUpgradeAndReset.CheckNewVersionCallback checkNewVersionCallback) {
        if (!this.realUpdating && !DongleUpdateUtil.getInstance().isRealUpdating()) {
            this.isStopped = false;
            this.mUpgradeList.clear();
            this.mCheckNewVersionCallback = checkNewVersionCallback;
            new Thread(new Runnable() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.j
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3878b.b(file);
                }
            }).start();
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.g(TAG, "checkUpgrade, realUpdating, break!!!");
    }

    public String convertVersion(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            long j = bArr[i] & 255;
            if (j != 0) {
                if (j <= 9 && sb.length() > 0) {
                    sb.append("0");
                    sb.append(j);
                } else {
                    sb.append(j);
                }
                if (i != bArr.length - 1) {
                    sb.append(".");
                }
            }
        }
        return sb.toString();
    }
}
