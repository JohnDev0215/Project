package com.loostone.libtuning.channel.byd.upgrade.util;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Build;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.q;
import com.blankj.utilcode.util.w;
import com.google.gson.Gson;
import com.loostone.libtuning.channel.byd.old.IDongleUpgrade;
import com.loostone.libtuning.channel.byd.old.PuremicTuning;
import com.loostone.libtuning.channel.byd.upgrade.DongleUpgradeInfo;
import com.loostone.libtuning.channel.byd.upgrade.UpdateHelper;
import com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware;
import com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.lingala.zip4j.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class DongleUpdateUtil {
    private static final String DEFAULT_DEVICE_INFO = "dgl";
    private static final String DEFAULT_DEVICE_NAME = "接收器";
    private static final String DONGLE_FIRMWARE = "dongle_firmware.bin";
    private static final String DONGLE_FIRMWARE_MORE_DIR = "DongleFirmwareMore";
    private static final String DONGLE_FIRMWARE_MORE_JSON = "dongle_firmware_more.json";
    private static final String DONGLE_FIRMWARE_PATH = "DongleFirmware";
    private static final String DONGLE_FIRMWARE_ZIP = "loostone_dongle_firmware.zip";
    private static final String DONGLE_INFO = "dongle_info.json";
    private static final String MIC_FIRMWARE_MORE_DIR = "MicFirmwareMore";
    private static final String MIC_FIRMWARE_MORE_JSON = "mic_firmware_more.json";
    private static final String TAG = "DongleUpdateUtil";
    private static volatile DongleUpdateUtil mInstance;
    private AlertDialog.Builder dialogBuilder;
    private DongleUpgradeInfo dongleUpgradeInfo;
    private IDongleUpgrade iDongleListener;
    private IUpgradeAndReset.CheckNewVersionCallback mCheckNewVersionCallback;
    private OooO00o mUsbDevice;
    private Boolean initFlagConsumed = null;
    private String mDglSN = BuildConfig.FLAVOR;
    private boolean isUnzipping = false;
    private boolean realUpdating = false;
    private Boolean isUpgradeSuccess = null;
    private Integer mErrorCode = null;
    private final UpdateHelper helper = new UpdateHelper();
    private final IUpdateFirmware.Callback mCallback = new IUpdateFirmware.Callback() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil.2
        @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware.Callback
        public void error(int i) {
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            bVar.b(DongleUpdateUtil.TAG, "errorCode:" + i);
            if (!DongleUpdateUtil.this.realUpdating) {
                bVar.g(DongleUpdateUtil.TAG, "return, during non-upgrade phase!");
                return;
            }
            MicUpdateUtil.getInstance().stopUpdate();
            DongleUpdateUtil.this.mErrorCode = Integer.valueOf(i);
            if (DongleUpdateUtil.this.iDongleListener != null) {
                DongleUpdateUtil.this.iDongleListener.onDongleUpdateStatus(5, "升级出错了，错误代码:" + i);
            }
            MicUpdateUtil.getInstance().clearUpgradeList();
            DongleUpdateUtil.this.clearUpgradeList();
            DongleUpdateUtil.this.isUpgradeSuccess = Boolean.FALSE;
            DongleUpdateUtil dongleUpdateUtil = DongleUpdateUtil.this;
            dongleUpdateUtil.mDglSN = dongleUpdateUtil.getDglSn();
        }

        @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware.Callback
        public void finish() {
            OooO00o.OooO00o.OooO0O0.b.f41a.b(DongleUpdateUtil.TAG, "finish");
            if (DongleUpdateUtil.this.iDongleListener != null) {
                DongleUpdateUtil.this.iDongleListener.onDongleUpdateStatus(4, "升级完成");
            }
            if (DongleUpdateUtil.this.mUpgradeFromUDisk) {
                ToastUtils.u("The dongle firmware upgrade was successful.");
            }
            DongleUpdateUtil.this.isUpgradeSuccess = Boolean.TRUE;
            DongleUpdateUtil dongleUpdateUtil = DongleUpdateUtil.this;
            dongleUpdateUtil.mDglSN = dongleUpdateUtil.getDglSn();
        }

        @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpdateFirmware.Callback
        public void process(int i) {
            OooO00o.OooO00o.OooO0O0.b.f41a.b(DongleUpdateUtil.TAG, "process:process = " + i);
            if (DongleUpdateUtil.this.iDongleListener != null) {
                DongleUpdateUtil.this.iDongleListener.onDongleUpdateStatus(6, i + BuildConfig.FLAVOR);
            }
        }
    };
    private boolean mUpgradeFromUDisk = false;
    private String mVersionName = BuildConfig.FLAVOR;
    private final ArrayList<Map<String, Object>> mUpgradeList = new ArrayList<>();
    private AlertDialog alertDialog = null;
    private final Map<Integer, byte[]> infoBytesList = new HashMap();

    private DongleUpdateUtil() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canUpdate(String str) {
        if (TextUtils.isEmpty(str)) {
            if (this.realUpdating) {
                str = "正在升级中...";
            } else {
                OooO00o oooO00o = this.mUsbDevice;
                if (oooO00o == null || oooO00o.w() == null) {
                    str = "未检测到USB设备";
                } else if (TextUtils.isEmpty(this.mUsbDevice.t())) {
                    str = "未成功识别USB设备型号";
                } else if (this.isUnzipping) {
                    str = "正在解压中...";
                }
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

    private void checkDglBuildIn() {
        if (this.realUpdating) {
            OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "checkDglBuildIn, realUpdating is true");
        } else {
            ThreadUtils.h(new w.d<String>(null) { // from class: com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil.1
                @Override // com.blankj.utilcode.util.ThreadUtils.e
                public String doInBackground() throws Throwable {
                    String str;
                    String str2;
                    for (int i = 0; i < 2; i++) {
                        if (i == 0) {
                            str = DongleUpdateUtil.DONGLE_FIRMWARE_MORE_JSON;
                            str2 = DongleUpdateUtil.DONGLE_FIRMWARE_MORE_DIR;
                        } else {
                            str = DongleUpdateUtil.MIC_FIRMWARE_MORE_JSON;
                            str2 = DongleUpdateUtil.MIC_FIRMWARE_MORE_DIR;
                        }
                        DongleUpdateUtil.this.parseUpgradeFiles(str, str2);
                    }
                    return "升级文件已就绪";
                }
            });
        }
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
        if (this.mUsbDevice == null || this.realUpdating || (arrayListQueryDeviceVersionCode = this.helper.queryDeviceVersionCode()) == null || arrayListQueryDeviceVersionCode.size() <= 1) {
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

    public static DongleUpdateUtil getInstance() {
        if (mInstance == null) {
            synchronized (DongleUpdateUtil.class) {
                if (mInstance == null) {
                    mInstance = new DongleUpdateUtil();
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
    public /* synthetic */ void a(long j, File file, DialogInterface dialogInterface, int i) {
        update(j, file.getAbsolutePath());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$checkUpgrade$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(File file) throws Throwable {
        boolean z;
        StringBuilder sb = new StringBuilder();
        if (canUpdate(BuildConfig.FLAVOR)) {
            if (file == null) {
                canUpdate("升级文件为null");
                return;
            }
            String firmwareDirs = getFirmwareDirs();
            boolean zC = true;
            try {
                this.isUnzipping = true;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(firmwareDirs);
                String str = File.separator;
                sb2.append(str);
                sb2.append(file.getName());
                final File file2 = new File(sb2.toString());
                if (file2.exists() && file2.isFile()) {
                    if (TextUtils.equals(getMiniFileMD5(file2), getMiniFileMD5(file))) {
                        com.blankj.utilcode.util.i.i(firmwareDirs, new FileFilter() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.e
                            @Override // java.io.FileFilter
                            public final boolean accept(File file3) {
                                return DongleUpdateUtil.lambda$checkUpgrade$1(file2, file3);
                            }
                        });
                        z = false;
                    } else {
                        com.blankj.utilcode.util.i.f(firmwareDirs);
                        z = true;
                    }
                    Thread.sleep(10L);
                } else {
                    z = true;
                }
                if (z) {
                    zC = com.blankj.utilcode.util.h.c(file2, new FileInputStream(file));
                    Thread.sleep(30L);
                }
                if (!zC) {
                    this.isUnzipping = false;
                    canUpdate("复制文件失败");
                    return;
                }
                ZipFile zipFile = new ZipFile(file2);
                if (!zipFile.isValidZipFile()) {
                    this.isUnzipping = false;
                    canUpdate("不是有效的压缩文件");
                    return;
                }
                if (!zipFile.isEncrypted()) {
                    this.isUnzipping = false;
                    canUpdate("文件密钥无效");
                    return;
                }
                unzip(zipFile, firmwareDirs, "LoostoneDgl2022");
                Thread.sleep(100L);
                this.isUnzipping = false;
                String str2 = firmwareDirs + str + DONGLE_INFO;
                File file3 = new File(str2);
                if (!file3.exists() || !file3.isFile()) {
                    canUpdate("配置文件异常：不存在或非文件");
                    return;
                }
                try {
                    DongleUpgradeInfo dongleUpgradeInfo = (DongleUpgradeInfo) new Gson().fromJson(OooO00o.OooO00o.OooO0O0.OooO0o0.b.c(str2), DongleUpgradeInfo.class);
                    this.dongleUpgradeInfo = dongleUpgradeInfo;
                    if (dongleUpgradeInfo != null) {
                        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "dongleUpgradeInfo = " + this.dongleUpgradeInfo);
                        try {
                            final long j = Long.parseLong(this.dongleUpgradeInfo.getVersion(), 16);
                            final File file4 = new File(firmwareDirs + str + DONGLE_FIRMWARE);
                            if (!file4.exists() || !file4.isFile()) {
                                canUpdate("固件文件出现异常：不存在或非文件");
                                return;
                            }
                            OooO00o oooO00o = this.mUsbDevice;
                            if (oooO00o == null || oooO00o.t() == null || !this.mUsbDevice.t().toUpperCase().contains(this.dongleUpgradeInfo.getDongleName()) || !this.dongleUpgradeInfo.getFileMd5().equalsIgnoreCase(getMiniFileMD5(file4)) || j <= getVersionCode()) {
                                canUpdate("升级信息校验出现异常");
                                return;
                            }
                            if (this.realUpdating) {
                                canUpdate("正在升级过程中");
                                return;
                            }
                            this.mVersionName = getVersionName();
                            String strConvertVersion = convertVersion(this.dongleUpgradeInfo.versionCode);
                            sb.append("接收器存在新版本：");
                            sb.append(strConvertVersion);
                            getDialogBuilder("检测到接收器新固件", "新版本：" + strConvertVersion + "\n当前版本：" + this.mVersionName).setPositiveButton("升级", new DialogInterface.OnClickListener() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.d
                                @Override // android.content.DialogInterface.OnClickListener
                                public final void onClick(DialogInterface dialogInterface, int i) {
                                    this.f3867b.a(j, file4, dialogInterface, i);
                                }
                            }).setNegativeButton("取消", (DialogInterface.OnClickListener) null);
                            HashMap map = new HashMap();
                            map.put("version", Long.valueOf(j));
                            map.put("filepath", file4.getAbsolutePath());
                            this.mUpgradeList.add(map);
                        } catch (Exception e) {
                            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "parse dongle versionCode error :: " + e);
                            canUpdate("解析固件版本信息出现异常");
                            return;
                        }
                    }
                    notifyUpgrade(sb.toString());
                } catch (Exception unused) {
                    canUpdate("配置文件解析异常：fromJson error");
                }
            } catch (Exception e2) {
                this.isUnzipping = false;
                canUpdate("处理升级包文件出现错误 :: " + e2);
                e2.printStackTrace();
            }
        }
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
            update(Long.parseLong(this.dongleUpgradeInfo.getVersion(), 16), getFirmwareDirs() + File.separator + DONGLE_FIRMWARE);
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "parse dongle versionCode error :: " + e);
        }
    }

    private void notifyUpgrade(final String str) {
        if (this.mCheckNewVersionCallback != null) {
            ThreadUtils.q(new Runnable() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3862b.c(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseUpgradeFiles(String str, String str2) throws Throwable {
        boolean z;
        try {
            InputStream inputStreamOpen = OooO00o.OooO00o.OooO0O0.a.f39a.a().getAssets().open(str);
            if (inputStreamOpen != null) {
                String miniFileMD5 = getMiniFileMD5(inputStreamOpen);
                String firmwareDirs = getFirmwareDirs(str2);
                String str3 = firmwareDirs + File.separator + str;
                File file = new File(str3);
                boolean zA = true;
                if (!file.exists() || !file.isFile()) {
                    z = true;
                } else if (TextUtils.equals(getMiniFileMD5(file), miniFileMD5)) {
                    z = false;
                } else {
                    file.delete();
                    z = true;
                }
                if (z) {
                    zA = q.a(str, str3);
                    Thread.sleep(30L);
                }
                if (zA) {
                    unzip(new ZipFile(file), firmwareDirs, BuildConfig.FLAVOR);
                    Thread.sleep(100L);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                str = "恭喜，接收器升级成功。\n从版本：" + this.mVersionName + "成功升级到版本：" + versionName;
            } else {
                if (this.mErrorCode != null) {
                    str = "接收器升级出现未知异常，错误代码：" + this.mErrorCode + "，升级失败了。请重试\n";
                } else {
                    str = "接收器升级出现未知异常，升级失败了。请重试\n";
                }
                this.mErrorCode = null;
            }
            if (this.isUpgradeSuccess.booleanValue()) {
                getDialogBuilder("升级完成", str).setPositiveButton("完成", (DialogInterface.OnClickListener) null).setNegativeButton(BuildConfig.FLAVOR, (DialogInterface.OnClickListener) null);
            } else {
                getDialogBuilder("升级完成", str).setPositiveButton("重试", new DialogInterface.OnClickListener() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.b
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        this.f3864b.d(dialogInterface, i);
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
        ThreadUtils.h(new w.d<String>(null) { // from class: com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil.4
            @Override // com.blankj.utilcode.util.ThreadUtils.e
            public String doInBackground() {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(DongleUpdateUtil.TAG, "update-doInBackground-start");
                if (DongleUpdateUtil.this.mUsbDevice == null) {
                    DongleUpdateUtil.this.canUpdate("开始升级前设备被移除");
                    return "开始升级前设备被移除";
                }
                try {
                    ((AudioManager) OooO00o.OooO00o.OooO0O0.a.f39a.a().getSystemService("audio")).setParameters("Karaoke_enable=false");
                } catch (Exception e) {
                    OooO00o.OooO00o.OooO0O0.b.f41a.d(DongleUpdateUtil.TAG, "update, Karaoke_enable=false, error :: " + e);
                    e.printStackTrace();
                }
                OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
                bVar.b(DongleUpdateUtil.TAG, "update-doInBackground-middle");
                DongleUpdateUtil.this.realUpdating = true;
                DongleUpdateUtil.this.helper.init(DongleUpdateUtil.this.mUsbDevice.w());
                DongleUpdateUtil.this.helper.queryHidVersionCode();
                DongleUpdateUtil.this.helper.queryDeviceStatus();
                DongleUpdateUtil.this.helper.queryDeviceVersionCode();
                DongleUpdateUtil.this.helper.requestUpdate(j, str);
                DongleUpdateUtil.this.helper.updateProcess();
                if (DongleUpdateUtil.this.mUpgradeFromUDisk) {
                    DongleUpdateUtil.this.rebootAndRelease();
                }
                DongleUpdateUtil.this.iDongleListener = null;
                bVar.b(DongleUpdateUtil.TAG, "update-doInBackground-end");
                return "接收器升级完成";
            }
        });
    }

    public void checkUpgrade(IUpgradeAndReset.CheckNewVersionCallback checkNewVersionCallback) {
        if (this.realUpdating) {
            OooO00o.OooO00o.OooO0O0.b.f41a.g(TAG, "checkUpgrade, realUpdating, break!!!");
            return;
        }
        this.mUpgradeList.clear();
        this.mCheckNewVersionCallback = checkNewVersionCallback;
        OooO00o oooO00o = this.mUsbDevice;
        if (oooO00o == null) {
            notifyUpgrade(BuildConfig.FLAVOR);
            return;
        }
        String strT = oooO00o.t();
        StringBuilder sb = new StringBuilder();
        sb.append(getFirmwareDirs(DONGLE_FIRMWARE_MORE_DIR));
        String str = File.separator;
        sb.append(str);
        sb.append(strT);
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
            long j = Long.parseLong(strReplaceFirst.substring(i2, i2 + 2), 16);
            if (j > 9 || sb.length() <= 0) {
                sb.append(j);
                sb.append(".");
            } else {
                sb.append("0");
                sb.append(j);
                sb.append(".");
            }
        }
        if (sb.length() > 1) {
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        }
        String string = sb.toString();
        return string.startsWith("0") ? string.substring(1) : string;
    }

    public String getDglSn() {
        OooO00o oooO00o = this.mUsbDevice;
        if (oooO00o == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            return oooO00o.a();
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "getDglSn error :: " + e);
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public String getFirmwareDirs() {
        return getFirmwareDirs(DONGLE_FIRMWARE_PATH);
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
            strE = OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o.f18a.e(file.getAbsolutePath());
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

    public boolean isRealUpdating() {
        return this.realUpdating;
    }

    public String queryMicInfo(int i) {
        try {
            queryMicInfoAll(false);
            byte[] bArr = this.infoBytesList.get(Integer.valueOf(i));
            if (i == 6 || i == 7) {
                return convertVersion(bArr);
            }
            switch (i) {
                case 10:
                case 11:
                    return convertByte2Str(bArr);
                case 12:
                case 13:
                case 14:
                case 15:
                    return convertByte2Char(bArr);
                default:
                    OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "queryMicInfo " + i + ", key invalid!");
                    return BuildConfig.FLAVOR;
            }
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "queryMicInfo " + i + " error :: " + e);
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public Map<Integer, byte[]> queryMicInfoAll(boolean z) {
        if (this.infoBytesList.size() >= 15 && !z) {
            return this.infoBytesList;
        }
        if (this.mUsbDevice == null || this.realUpdating) {
            return null;
        }
        try {
            this.infoBytesList.clear();
            ArrayList<byte[]> arrayListQueryDeviceInfoAll = this.helper.queryDeviceInfoAll();
            if (arrayListQueryDeviceInfoAll == null || arrayListQueryDeviceInfoAll.size() < 1) {
                return null;
            }
            HashMap map = new HashMap();
            byte[] bArr = arrayListQueryDeviceInfoAll.get(0);
            int i = 3;
            while (i < bArr.length - 1) {
                byte b2 = bArr[i];
                int i2 = i + 1;
                int i3 = bArr[i2];
                byte[] bArr2 = new byte[i3];
                map.put(Integer.valueOf(b2), Boolean.TRUE);
                for (int i4 = 0; i4 < i3; i4++) {
                    i2++;
                    bArr2[i4] = bArr[i2];
                    if (Boolean.TRUE.equals(map.get(Integer.valueOf(b2))) && bArr2[i4] != 0) {
                        map.put(Integer.valueOf(b2), Boolean.FALSE);
                    }
                }
                this.infoBytesList.put(Integer.valueOf(b2), bArr2);
                i = i2 + 1;
            }
            if (map.containsValue(Boolean.TRUE)) {
                this.helper.clearCachedData(IUpdateFirmware.MapData.DEVICE_INFO_ALL);
            }
            return this.infoBytesList;
        } catch (Exception e) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d(TAG, "queryMicInfoAll error :: " + e);
            e.printStackTrace();
            return null;
        }
    }

    public void rebootAndRelease() {
        rebootAndRelease(true);
    }

    public void release() {
        this.mUpgradeFromUDisk = false;
        this.realUpdating = false;
        this.mUpgradeList.clear();
        this.helper.release();
        this.mUsbDevice = null;
    }

    public void setInitFlag() {
        this.initFlagConsumed = Boolean.FALSE;
    }

    public void setUpgradeFromUDisk() {
        this.mUpgradeFromUDisk = true;
    }

    public void setUsbDevice(OooO00o oooO00o, boolean z) {
        if (oooO00o == null) {
            this.mUsbDevice = null;
            return;
        }
        OooO00o.OooO00o.OooO0O0.b.f41a.e(TAG, "setUsbDevice & init, isAdd = " + z);
        if (z) {
            OooO00o oooO00o2 = this.mUsbDevice;
            if (oooO00o2 == null || oooO00o2 != oooO00o) {
                this.helper.init(oooO00o.w());
                this.helper.setCallback(this.mCallback);
                this.mUsbDevice = oooO00o;
            }
            if (TextUtils.equals(getDglSn(), this.mDglSN)) {
                showUpgradeAfter();
            }
            this.mUpgradeFromUDisk = false;
            this.mDglSN = BuildConfig.FLAVOR;
            Boolean bool = this.initFlagConsumed;
            if (bool != null && !bool.booleanValue()) {
                this.initFlagConsumed = Boolean.TRUE;
                checkDglBuildIn();
            }
        } else {
            OooO00o oooO00o3 = this.mUsbDevice;
            if (oooO00o3 != null && oooO00o3 == oooO00o) {
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

    public void rebootAndRelease(final boolean z) {
        OooO00o.OooO00o.OooO0O0.b.f41a.b(TAG, "reboot and release, isUpdateSucceed = " + z);
        ThreadUtils.h(new w.d<String>(null) { // from class: com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil.5
            @Override // com.blankj.utilcode.util.ThreadUtils.e
            public String doInBackground() {
                if (DongleUpdateUtil.this.realUpdating && z) {
                    DongleUpdateUtil.this.helper.queryCheckResult();
                    DongleUpdateUtil.this.helper.startUpdate();
                    DongleUpdateUtil.this.helper.queryUpdateResult();
                    DongleUpdateUtil.this.helper.release();
                }
                DongleUpdateUtil.this.realUpdating = false;
                DongleUpdateUtil.this.iDongleListener = null;
                if (z) {
                    return "queryCheckResult & queryUpdateResult";
                }
                DongleUpdateUtil.this.helper.release();
                PuremicTuning.getInstance().rebootDongle();
                return "queryCheckResult & queryUpdateResult";
            }
        });
    }

    public void startUpdate(File file, IDongleUpgrade iDongleUpgrade) {
        this.iDongleListener = iDongleUpgrade;
        checkUpgrade(file, new IUpgradeAndReset.CheckNewVersionCallback() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil.3
            @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset.CheckNewVersionCallback
            public void onNewVersionAvailable(String str) {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(DongleUpdateUtil.TAG, "startUpdate, onNewVersionAvailable");
                if (DongleUpdateUtil.this.mUpgradeFromUDisk) {
                    DongleUpdateUtil.this.showDialog();
                }
            }

            @Override // com.loostone.libtuning.channel.byd.upgrade.inf.IUpgradeAndReset.CheckNewVersionCallback
            public void onUpToDate() {
                OooO00o.OooO00o.OooO0O0.b.f41a.b(DongleUpdateUtil.TAG, "startUpdate, onUpToDate");
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
            java.lang.String r1 = "DongleUpdateUtil"
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
        throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil.getMiniFileMD5(java.io.InputStream):java.lang.String");
    }

    public byte[] queryMicInfo(int i, int i2) {
        ArrayList<byte[]> arrayListQueryDeviceInfo;
        if (this.mUsbDevice == null || this.realUpdating || (arrayListQueryDeviceInfo = this.helper.queryDeviceInfo(i, i2)) == null || arrayListQueryDeviceInfo.size() < 1) {
            return null;
        }
        return arrayListQueryDeviceInfo.get(0);
    }

    public void checkUpgrade(final File file, IUpgradeAndReset.CheckNewVersionCallback checkNewVersionCallback) {
        if (this.realUpdating) {
            OooO00o.OooO00o.OooO0O0.b.f41a.g(TAG, "checkUpgrade, realUpdating, break!!!");
            return;
        }
        this.mUpgradeList.clear();
        this.mCheckNewVersionCallback = checkNewVersionCallback;
        new Thread(new Runnable() { // from class: com.loostone.libtuning.channel.byd.upgrade.util.c
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f3865b.b(file);
            }
        }).start();
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
