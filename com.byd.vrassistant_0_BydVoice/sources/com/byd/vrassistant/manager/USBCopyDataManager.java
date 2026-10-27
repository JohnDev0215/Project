package com.byd.vrassistant.manager;

import android.content.Context;
import android.hardware.bydauto.pki.PkiManager;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import cerence.ark.assistant.framework.data.Language;
import com.blankj.utilcode.util.SPUtils;
import com.byd.assistant.HPatch;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.bean.VoiceLanuageDp;
import com.byd.vrassistant.eventbus.DataRecordEvent;
import com.byd.vrassistant.manager.USBCopyDataManager;
import com.byd.vrassistant.utils.DataUtils;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SevenZipUtil;
import com.byd.vrassistant.utils.SevenZipUtil2;
import com.byd.vrassistant.utils.UsbUtils;
import com.byd.vrassistant.view.CopyDataProgressBar;
import com.byd.vrassistant.view.ViewManager;
import com.byd.vrdownload.model.LanguageUpdate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class USBCopyDataManager {
    private static final String TAG = "NEU_USBCopyDataManager";
    public static boolean has_usa_copyed = false;
    private static volatile USBCopyDataManager mInstance;
    private Context context = BydVoiceApplication.getContext();
    ThreadPoolExecutor USBCopyDataExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() - 1, 30, 5000, TimeUnit.SECONDS, new LinkedBlockingQueue());
    boolean has_usb_copyed = false;
    public boolean has_ota_copyed = false;
    boolean has_accent_copyed = false;
    boolean need2Record = false;
    private Map<String, Runnable> verifyAccentMap = new HashMap();

    public static USBCopyDataManager get() {
        if (mInstance == null) {
            synchronized (USBCopyDataManager.class) {
                if (mInstance == null) {
                    mInstance = new USBCopyDataManager();
                }
            }
        }
        return mInstance;
    }

    private USBCopyDataManager() {
    }

    public void init() {
        this.USBCopyDataExecutor.prestartAllCoreThreads();
    }

    public void uninit() {
        this.USBCopyDataExecutor.shutdown();
    }

    public class USBCopyDataTaskEx implements Runnable {
        public String dataPath;
        public String name;
        public DataUtils.USBCopyDataProgress usbCopyDataProgress = null;

        public USBCopyDataTaskEx(String str, String str2) {
            this.dataPath = str;
            this.name = str2;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            String str;
            StringBuilder sb;
            String str2;
            String str3;
            File file;
            RLog.e(USBCopyDataManager.TAG, "start copy usb data path :" + this.dataPath);
            int i = 2;
            int i2 = 1;
            try {
                try {
                    try {
                        DataUtils.USBCopyDataProgress uSBCopyDataProgress = this.usbCopyDataProgress;
                        if (uSBCopyDataProgress != null) {
                            uSBCopyDataProgress.onFileCopyStarted("start copy usb data path :" + this.dataPath, this.name);
                        }
                        str2 = this.dataPath;
                        str3 = DataUtils.getPlatformDataPackPathEx() + "/";
                        file = new File(str2 + "/" + this.name + ".7z");
                    } catch (Exception e) {
                        DataUtils.USBCopyDataProgress uSBCopyDataProgress2 = this.usbCopyDataProgress;
                        if (uSBCopyDataProgress2 != null) {
                            uSBCopyDataProgress2.onToast(" copy usb data error ", this.name, 2);
                        }
                        e.printStackTrace();
                        DataUtils.USBCopyDataProgress uSBCopyDataProgress3 = this.usbCopyDataProgress;
                        if (uSBCopyDataProgress3 != null) {
                            uSBCopyDataProgress3.onFileCopyFinished(1, this.name);
                        }
                        str = USBCopyDataManager.TAG;
                        sb = new StringBuilder("end copy usb data path :");
                    }
                    if (!file.exists()) {
                        DataUtils.USBCopyDataProgress uSBCopyDataProgress4 = this.usbCopyDataProgress;
                        if (uSBCopyDataProgress4 != null) {
                            uSBCopyDataProgress4.onToast("usb 7z data not exist", this.name, 2);
                            this.usbCopyDataProgress.onGetMaxFileSize(1);
                            try {
                                this.usbCopyDataProgress.onFileCopyFinished(2, this.name);
                            } catch (Throwable th) {
                                th = th;
                                DataUtils.USBCopyDataProgress uSBCopyDataProgress5 = this.usbCopyDataProgress;
                                if (uSBCopyDataProgress5 != null) {
                                    uSBCopyDataProgress5.onFileCopyFinished(i, this.name);
                                }
                                RLog.e(USBCopyDataManager.TAG, "end copy usb data path :" + this.dataPath);
                                throw th;
                            }
                        } else {
                            i = 0;
                        }
                        DataUtils.USBCopyDataProgress uSBCopyDataProgress6 = this.usbCopyDataProgress;
                        if (uSBCopyDataProgress6 != null) {
                            uSBCopyDataProgress6.onFileCopyFinished(i, this.name);
                        }
                        RLog.e(USBCopyDataManager.TAG, "end copy usb data path :" + this.dataPath);
                        return;
                    }
                    int compressFileCount = SevenZipUtil.getCompressFileCount(file);
                    DataUtils.USBCopyDataProgress uSBCopyDataProgress7 = this.usbCopyDataProgress;
                    if (uSBCopyDataProgress7 != null) {
                        uSBCopyDataProgress7.onGetMaxFileSize(compressFileCount);
                        RLog.e(USBCopyDataManager.TAG, this.name + " onGetMaxFileSize :" + compressFileCount);
                    }
                    RLog.e(USBCopyDataManager.TAG, "start verify usb data path :" + str2);
                    if (USBCopyDataManager.this.verifyDataPackaCert(str2, this.name, this.usbCopyDataProgress)) {
                        RLog.e(USBCopyDataManager.TAG, "verify usb data true:" + str2);
                        RLog.e(USBCopyDataManager.TAG, "start uncompress usb data to " + str3);
                        SevenZipUtil.unCompressEx(new File(str2 + "/" + this.name + ".7z"), new File(str3), this.usbCopyDataProgress);
                        VoiceAssistantManager.get().recordDataPackaProcessEx(DataRecordEvent.USB_COPY.name(), str3 + this.name);
                        i2 = 0;
                    } else {
                        RLog.e(USBCopyDataManager.TAG, "verify usb data error :" + this.dataPath);
                    }
                    DataUtils.USBCopyDataProgress uSBCopyDataProgress8 = this.usbCopyDataProgress;
                    if (uSBCopyDataProgress8 != null) {
                        uSBCopyDataProgress8.onFileCopyFinished(i2, this.name);
                    }
                    str = USBCopyDataManager.TAG;
                    sb = new StringBuilder("end copy usb data path :");
                    RLog.e(str, sb.append(this.dataPath).toString());
                } catch (Throwable th2) {
                    th = th2;
                    i = 0;
                }
            } catch (Throwable th3) {
                th = th3;
                i = 1;
            }
        }
    }

    public class USBCopyDataTask implements Runnable {
        public String dataPath;
        public String name;
        public DataUtils.USBCopyDataProgress usbCopyDataProgress = null;

        public USBCopyDataTask(String str, String str2) {
            this.dataPath = str;
            this.name = str2;
        }

        /* JADX WARN: Removed duplicated region for block: B:129:0x03b7  */
        /* JADX WARN: Removed duplicated region for block: B:132:0x03bc  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x03ef  */
        /* JADX WARN: Removed duplicated region for block: B:160:0x0410  */
        /* JADX WARN: Removed duplicated region for block: B:174:0x0232 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 1068
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.manager.USBCopyDataManager.USBCopyDataTask.run():void");
        }

        /* JADX INFO: renamed from: lambda$run$0$com-byd-vrassistant-manager-USBCopyDataManager$USBCopyDataTask, reason: not valid java name */
        /* synthetic */ void m163xd4234a2f(String str, String str2) {
            DataUtils.USBCopyDataProgress uSBCopyDataProgress = this.usbCopyDataProgress;
            if (uSBCopyDataProgress != null) {
                uSBCopyDataProgress.onFileCopyIncreased(str, this.name);
            }
        }
    }

    public class OTACopyTask implements Runnable {
        public DataUtils.OTADataProgress otaDataProgress = null;
        private VoiceLanuageDp voiceLanuageDp;

        public OTACopyTask(VoiceLanuageDp voiceLanuageDp) {
            this.voiceLanuageDp = voiceLanuageDp;
        }

        private void processPorVoiceAccentCopy() {
            if (this.voiceLanuageDp.getName().contains("por-BRA")) {
                RLog.e(USBCopyDataManager.TAG, "processPorVoiceAccentCopy  :" + this.voiceLanuageDp.toString());
                String str = DataUtils.getPlatformDataPackPathEx() + "/" + this.voiceLanuageDp.getName() + "/";
                String str2 = DataUtils.getPlatformDataPackPathEx() + "/data_por-PRT/";
                RLog.e(USBCopyDataManager.TAG, "processPorVoiceAccentCopy src_data_path:" + str + ",dst_data_path:" + str2);
                VoiceAssistantManager.get().copyDataPack2Accent(str, str2, "por-BRA", Language.POR_PRT);
            }
        }

        private void processSpaVoiceAccentCopy() {
            if (this.voiceLanuageDp.getName().contains("spa-XLA")) {
                RLog.e(USBCopyDataManager.TAG, "processPorVoiceAccentCopy  :" + this.voiceLanuageDp.toString());
                String str = DataUtils.getPlatformDataPackPathEx() + "/" + this.voiceLanuageDp.getName() + "/";
                String str2 = DataUtils.getPlatformDataPackPathEx() + "/data_spa-ESP/";
                RLog.e(USBCopyDataManager.TAG, "processPorVoiceAccentCopy src_data_path:" + str + ",dst_data_path:" + str2);
                VoiceAssistantManager.get().copyDataPack2Accent(str, str2, "spa-XLA", Language.SPA_ESP);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            String str;
            StringBuilder sb;
            RLog.e(USBCopyDataManager.TAG, "start process ota data  :" + this.voiceLanuageDp.toString());
            try {
                try {
                    if (this.otaDataProgress != null) {
                        USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$OTACopyTask$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m159xd8d58cfd();
                            }
                        });
                    }
                    int type = this.voiceLanuageDp.getType();
                    if (type == 2) {
                        File file = new File(DataUtils.getPlatformDataPackPathEx() + "/" + this.voiceLanuageDp.getName() + "/");
                        if (file.exists() && DataUtils.deleteDir(file)) {
                            RLog.e(USBCopyDataManager.TAG, "deleteDir ok");
                            VoiceAssistantManager.get().recordDataPackaVersionProcess(DataRecordEvent.OTA_DEL.name(), this.voiceLanuageDp.getPath(), this.voiceLanuageDp.getVersion());
                        }
                    } else if (type == 3 || type == 4 || type == 5 || type == 6) {
                        String path = this.voiceLanuageDp.getPath();
                        String str2 = DataUtils.getPlatformDataPackPathEx() + "/" + this.voiceLanuageDp.getName();
                        File file2 = new File(str2);
                        File file3 = new File(path);
                        RLog.e(USBCopyDataManager.TAG, "SevenZipUtil start unCompress : src_data_path = " + path + ",dst_data_path = " + str2);
                        if (type == 3 || type == 4) {
                            if (file2.exists() && DataUtils.deleteDir(file2)) {
                                RLog.e(USBCopyDataManager.TAG, "deleteDir ok");
                            }
                            SevenZipUtil2.unCompress(file3, file2);
                            VoiceAssistantManager.get().recordDataPackaVersionProcess(DataRecordEvent.OTA_INSTALL.name(), this.voiceLanuageDp.getPath(), this.voiceLanuageDp.getVersion());
                        } else if (type == 5 || type == 6) {
                            String str3 = str2 + "-patch";
                            try {
                                int iPatchDir = HPatch.patchDir(str2, path, str3);
                                RLog.i(USBCopyDataManager.TAG, "patch = " + iPatchDir);
                                if (iPatchDir == 0) {
                                    RLog.e(USBCopyDataManager.TAG, "patch ok ");
                                    VoiceAssistantManager.get().recordDataPackaVersionProcess(DataRecordEvent.OTA_PATCH.name(), this.voiceLanuageDp.getPath(), this.voiceLanuageDp.getVersion());
                                    if (file2.exists() && DataUtils.deleteDir(file2)) {
                                        RLog.e(USBCopyDataManager.TAG, "deleteDir ok");
                                    }
                                    new File(str3).renameTo(file2);
                                } else {
                                    RLog.e(USBCopyDataManager.TAG, "patch error ");
                                    if (this.otaDataProgress != null) {
                                        if (this.voiceLanuageDp.getState() == -1) {
                                            this.voiceLanuageDp.setState(1);
                                        }
                                        USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$OTACopyTask$$ExternalSyntheticLambda1
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                this.f$0.m160xded9585c();
                                            }
                                        });
                                    }
                                }
                            } catch (Exception e) {
                                RLog.i(USBCopyDataManager.TAG, "error = " + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                        RLog.e(USBCopyDataManager.TAG, this.voiceLanuageDp.getName() + ": dst_data_version = " + VoiceAssistantManager.get().getDataPackVersion(str2));
                        if (file3.delete()) {
                            RLog.e(USBCopyDataManager.TAG, path + " : deleted!");
                        }
                    }
                    if (this.otaDataProgress != null) {
                        if (this.voiceLanuageDp.getState() == -1) {
                            this.voiceLanuageDp.setState(0);
                        }
                        USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$OTACopyTask$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m162xeae0ef1a();
                            }
                        });
                    }
                    str = USBCopyDataManager.TAG;
                    sb = new StringBuilder("end process ota data :");
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (this.otaDataProgress != null) {
                        if (this.voiceLanuageDp.getState() == -1) {
                            this.voiceLanuageDp.setState(1);
                        }
                        USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$OTACopyTask$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m161xe4dd23bb(e2);
                            }
                        });
                    }
                    if (this.otaDataProgress != null) {
                        if (this.voiceLanuageDp.getState() == -1) {
                            this.voiceLanuageDp.setState(0);
                        }
                        USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$OTACopyTask$$ExternalSyntheticLambda2
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.m162xeae0ef1a();
                            }
                        });
                    }
                    str = USBCopyDataManager.TAG;
                    sb = new StringBuilder("end process ota data :");
                }
                RLog.e(str, sb.append(this.voiceLanuageDp.toString()).toString());
            } catch (Throwable th) {
                if (this.otaDataProgress != null) {
                    if (this.voiceLanuageDp.getState() == -1) {
                        this.voiceLanuageDp.setState(0);
                    }
                    USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$OTACopyTask$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.m162xeae0ef1a();
                        }
                    });
                }
                RLog.e(USBCopyDataManager.TAG, "end process ota data :" + this.voiceLanuageDp.toString());
                throw th;
            }
        }

        /* JADX INFO: renamed from: lambda$run$0$com-byd-vrassistant-manager-USBCopyDataManager$OTACopyTask, reason: not valid java name */
        /* synthetic */ void m159xd8d58cfd() {
            this.otaDataProgress.onOtaDataStarted(this.voiceLanuageDp);
        }

        /* JADX INFO: renamed from: lambda$run$1$com-byd-vrassistant-manager-USBCopyDataManager$OTACopyTask, reason: not valid java name */
        /* synthetic */ void m160xded9585c() {
            this.otaDataProgress.onOtaDataErr(this.voiceLanuageDp, "patch");
        }

        /* JADX INFO: renamed from: lambda$run$2$com-byd-vrassistant-manager-USBCopyDataManager$OTACopyTask, reason: not valid java name */
        /* synthetic */ void m161xe4dd23bb(Exception exc) {
            this.otaDataProgress.onOtaDataErr(this.voiceLanuageDp, exc.getMessage());
        }

        /* JADX INFO: renamed from: lambda$run$3$com-byd-vrassistant-manager-USBCopyDataManager$OTACopyTask, reason: not valid java name */
        /* synthetic */ void m162xeae0ef1a() {
            this.otaDataProgress.onOtaDataFinished(this.voiceLanuageDp);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof OTACopyTask) {
                return this.voiceLanuageDp.equals(((OTACopyTask) obj).voiceLanuageDp);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.voiceLanuageDp);
        }
    }

    public class AccentCopyTask implements Runnable {
        String dst;
        String dst_suffix;
        String src;
        String src_suffix;

        public String getSrc() {
            return this.src;
        }

        public String getDst() {
            return this.dst;
        }

        public AccentCopyTask(String str, String str2, String str3, String str4) {
            this.src = str;
            this.dst = str2;
            this.dst_suffix = str4;
            this.src_suffix = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            RLog.e(USBCopyDataManager.TAG, "start process accent data  :" + this.dst_suffix);
            VoiceAssistantManager.get().copyDataPack2Accent(this.src + "/", this.dst, this.src_suffix, this.dst_suffix);
            USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$AccentCopyTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m158xc81b5cfd();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$run$0$com-byd-vrassistant-manager-USBCopyDataManager$AccentCopyTask, reason: not valid java name */
        /* synthetic */ void m158xc81b5cfd() {
            try {
                try {
                    VoiceAssistantManager.get().initLanguageDatas();
                    VoiceAssistantManager.get().destroyVoiceAssistant();
                    VoiceAssistantManager.get().initVoiceAssistant();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } finally {
                USBCopyDataManager.this.has_accent_copyed = false;
            }
        }
    }

    public class AccentVerifyTask implements Runnable {
        String dst;
        String dst_suffix;
        String src;
        String src_suffix;

        public String getSrc() {
            return this.src;
        }

        public String getDst() {
            return this.dst;
        }

        public AccentVerifyTask(String str, String str2, String str3, String str4) {
            this.src = str;
            this.dst = str2;
            this.dst_suffix = str4;
            this.src_suffix = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            RLog.e(USBCopyDataManager.TAG, "start process AccentVerifyTask");
            File file = new File(this.src + "/");
            File file2 = new File(this.dst);
            if (!file.exists()) {
                RLog.e(USBCopyDataManager.TAG, this.src + " not exists");
                return;
            }
            if (!file2.exists()) {
                RLog.e(USBCopyDataManager.TAG, this.dst + " not exists");
                return;
            }
            HashMap<String, String> mapListAllFileMD5Sum = DataUtils.listAllFileMD5Sum(this.src, null, 0, "");
            HashMap<String, String> mapListAllFileMD5Sum2 = DataUtils.listAllFileMD5Sum(this.dst, null, 0, "");
            String str = file.getAbsolutePath() + "/version/version.json";
            String str2 = file.getAbsolutePath() + "/asr_" + this.src_suffix + "/json/cloud_services.json";
            RLog.e(USBCopyDataManager.TAG, "version_file :" + str + " src_cloud_file :" + str2);
            mapListAllFileMD5Sum.remove(str);
            mapListAllFileMD5Sum.remove(str2);
            if (mapListAllFileMD5Sum2.values().containsAll(mapListAllFileMD5Sum.values())) {
                RLog.e(USBCopyDataManager.TAG, this.dst + " md5sum is allright need not copy");
                DataUtils.copy(this.src + "/version/", this.dst + "/version/");
                String file2String = DataUtils.readFile2String(this.dst + "/version/version.json");
                if (file2String != null && !file2String.isEmpty()) {
                    RLog.e(USBCopyDataManager.TAG, "version.json:" + file2String);
                    try {
                        JSONObject jSONObject = new JSONObject(file2String);
                        if (jSONObject.has(LanguageUpdate.LANGUAGE)) {
                            RLog.e(USBCopyDataManager.TAG, "language :" + jSONObject.getString(LanguageUpdate.LANGUAGE));
                            jSONObject.put(LanguageUpdate.LANGUAGE, "data_" + this.dst_suffix);
                            DataUtils.stringToFile(this.dst + "/version/version.json", jSONObject.toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    DataUtils.copyFileFromAssets(USBCopyDataManager.this.context, this.dst_suffix + "/cloud_services.json", (this.dst + "/asr_" + this.src_suffix + "/json/") + "/cloud_services.json");
                }
            } else {
                RLog.e(USBCopyDataManager.TAG, this.dst + " md5sum is error need to copy");
                USBCopyDataManager.this.startAccentTask(this.src, this.dst, this.src_suffix, this.dst_suffix);
            }
            RLog.e(USBCopyDataManager.TAG, "end process AccentVerifyTask");
            USBCopyDataManager.this.verifyAccentMap.remove(this.dst);
        }
    }

    public class USACopyTask implements Runnable {
        public USACopyTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            RLog.e(USBCopyDataManager.TAG, "start process USA data Copy task");
            final String str = DataUtils.getPlatformUSADataPackPath() + "/";
            final String str2 = DataUtils.getPlatformDataPackPathEx() + "/data_eng-USA/";
            File file = new File(str);
            File file2 = new File(str2);
            RLog.e(USBCopyDataManager.TAG, str + " copy to " + str2);
            if (!file.exists()) {
                RLog.e(USBCopyDataManager.TAG, str + " not exists");
                USBCopyDataManager.has_usa_copyed = false;
                return;
            }
            if (!file2.exists()) {
                DataUtils.copy(str, str2);
            } else {
                if (DataUtils.listAllFileMD5Sum(str2, null, 0, "").values().containsAll(DataUtils.listAllFileMD5Sum(str, null, 0, "").values())) {
                    RLog.e(USBCopyDataManager.TAG, str + " md5sum is allright need not copy");
                } else {
                    USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$USACopyTask$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            USBCopyDataManager.USACopyTask.lambda$run$0(str, str2);
                        }
                    });
                }
            }
            USBCopyDataManager.has_usa_copyed = false;
            RLog.e(USBCopyDataManager.TAG, "end process USA data Copy task");
        }

        static /* synthetic */ void lambda$run$0(String str, String str2) {
            VoiceAssistantManager.get().destroyVoiceAssistant();
            RLog.e(USBCopyDataManager.TAG, str + " md5sum is error need to copy");
            if (!DataUtils.deleteDir(new File(str2))) {
                RLog.e(USBCopyDataManager.TAG, "delete dst " + str2 + " err");
            }
            DataUtils.copy(str, str2);
            try {
                VoiceAssistantManager.get().initLanguageDatas();
                VoiceAssistantManager.get().initVoiceAssistant();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public final void runOnUiThreadEx(Runnable runnable) {
        new Handler(this.context.getMainLooper()).post(runnable);
    }

    public final void runOnUiThreadDelayEx(Runnable runnable, int i) {
        new Handler(this.context.getMainLooper()).postDelayed(runnable, i);
    }

    public void endUSBCopyData() {
        RLog.e(TAG, "USBCopyDataExecutor count:" + this.USBCopyDataExecutor.getActiveCount());
        ViewManager.getUsbInstallManager().closeHardBar();
        if (this.has_usb_copyed) {
            try {
                try {
                    VoiceAssistantManager.get().initLanguageDatas();
                    VoiceAssistantManager.get().initVoiceAssistant();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } finally {
                this.has_usb_copyed = false;
            }
        }
    }

    private static String printOpensslFormatCertFileContent(Certificate certificate) throws IOException, CertificateEncodingException {
        PemObject pemObject = new PemObject("CERTIFICATE", certificate.getEncoded());
        StringWriter stringWriter = new StringWriter();
        PemWriter pemWriter = new PemWriter(stringWriter);
        pemWriter.writeObject(pemObject);
        pemWriter.close();
        stringWriter.close();
        return stringWriter.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean verifyDataPackaCert(String str, String str2, DataUtils.USBCopyDataProgress uSBCopyDataProgress) {
        String str3 = str + "/signature.txt";
        String str4 = str + "/signer.crt";
        if (!new File(str3).exists() || !new File(str4).exists()) {
            if (uSBCopyDataProgress != null) {
                uSBCopyDataProgress.onToast("The certificate is missing，upgrade cannot be performed ", str2, 2);
            }
            return false;
        }
        String file2String = DataUtils.readFile2String(str3);
        String str5 = TAG;
        RLog.i(str5, "signatureStr : " + file2String);
        PkiManager pkiManager = PkiManager.getInstance(this.context);
        try {
            try {
                PublicKey publicKeyLoadPublicKey = DataUtils.loadPublicKey(str4);
                if (uSBCopyDataProgress != null) {
                    uSBCopyDataProgress.onGetFileMD5Sum(str4, 2, str2);
                }
                boolean zVerifyCertHSM = pkiManager.verifyCertHSM(printOpensslFormatCertFileContent(DataUtils.loadCertificate(str4)));
                RLog.i(str5, "verResult : " + zVerifyCertHSM);
                if (!zVerifyCertHSM) {
                    if (uSBCopyDataProgress != null) {
                        uSBCopyDataProgress.onToast("The certificate verification fails，upgrade cannot be performed ", str2, 2);
                    }
                    return false;
                }
                if (uSBCopyDataProgress != null) {
                    uSBCopyDataProgress.onGetFileMD5Sum(str + "/" + str2 + ".7z", 2, str2);
                }
                String hash = DataUtils.getHash(new File(str + "/" + str2 + ".7z"));
                RLog.i(str5, "fileHash : " + hash);
                boolean zBooleanValue = DataUtils.checkSign(hash, file2String, publicKeyLoadPublicKey).booleanValue();
                RLog.i(str5, "sigResult : " + zBooleanValue);
                if (!zBooleanValue && uSBCopyDataProgress != null) {
                    uSBCopyDataProgress.onToast("The data package is damaged, upgrade cannot be performed ", str2, 2);
                }
                return zBooleanValue;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public void startUSBCopyDataEx() {
        String str = TAG;
        RLog.e(str, "USBCopyDataExecutor pool:" + this.USBCopyDataExecutor.getCorePoolSize());
        RLog.e(str, "USBCopyDataExecutor count:" + this.USBCopyDataExecutor.getActiveCount());
        if (this.has_accent_copyed) {
            RLog.e(str, "accent has process data usb can't copy");
            return;
        }
        if (this.has_ota_copyed) {
            RLog.e(str, "ota has process data usb can't copy");
            return;
        }
        if (UsbUtils.isExistUSB(this.context)) {
            RLog.e(str, "isExistUSB");
            Arrays.stream(UsbUtils.getStoragePath(this.context)).forEach(new Consumer() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m152x6f965165((String) obj);
                }
            });
        }
        RLog.e(str, "USBCopyDataExecutor execute");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x025a  */
    /* JADX INFO: renamed from: lambda$startUSBCopyDataEx$3$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    /* synthetic */ void m152x6f965165(java.lang.String r29) {
        /*
            Method dump skipped, instruction units count: 878
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.manager.USBCopyDataManager.m152x6f965165(java.lang.String):void");
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.manager.USBCopyDataManager$1, reason: invalid class name */
    class AnonymousClass1 implements DataUtils.USBCopyDataProgress {
        final /* synthetic */ AtomicInteger val$avalibel_language;
        final /* synthetic */ List val$copyList;
        final /* synthetic */ AtomicInteger val$copy_file_size;
        final /* synthetic */ HashMap val$failed_file_size;
        final /* synthetic */ HashMap val$finished_file_size;
        final /* synthetic */ AtomicInteger val$start_copy_count;
        final /* synthetic */ AtomicInteger val$total_file_size;

        AnonymousClass1(List list, HashMap map, HashMap map2, AtomicInteger atomicInteger, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3, AtomicInteger atomicInteger4) {
            this.val$copyList = list;
            this.val$finished_file_size = map;
            this.val$failed_file_size = map2;
            this.val$start_copy_count = atomicInteger;
            this.val$avalibel_language = atomicInteger2;
            this.val$total_file_size = atomicInteger3;
            this.val$copy_file_size = atomicInteger4;
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public void onFileCopyStarted(String str, String str2) {
            this.val$copyList.add(str2);
            USBCopyDataManager uSBCopyDataManager = USBCopyDataManager.this;
            final List list = this.val$copyList;
            uSBCopyDataManager.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$1$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass1.lambda$onFileCopyStarted$0(list);
                }
            });
        }

        static /* synthetic */ void lambda$onFileCopyStarted$0(List list) {
            RLog.e(USBCopyDataManager.TAG, "onFileCopyStarted");
            ViewManager.getUsbInstallManager().showHardBar();
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                pb.tx_state2.setText("start read data " + String.join(" ", list));
                pb.pb_copy.setProgress(0);
                pb.tx_state.setText("You are ready to start copying USB data");
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public void onFileCopyFinished(int i, String str) {
            this.val$finished_file_size.put(str, Integer.valueOf(i));
            RLog.e(USBCopyDataManager.TAG, "finished_file_size : " + this.val$finished_file_size.size());
            if (i == 1 || i == 2) {
                this.val$failed_file_size.put(str, Integer.valueOf(i));
                RLog.e(USBCopyDataManager.TAG, "failed_file_size : " + this.val$failed_file_size.size());
            }
            USBCopyDataManager uSBCopyDataManager = USBCopyDataManager.this;
            final HashMap map = this.val$finished_file_size;
            final HashMap map2 = this.val$failed_file_size;
            final AtomicInteger atomicInteger = this.val$start_copy_count;
            final AtomicInteger atomicInteger2 = this.val$avalibel_language;
            final AtomicInteger atomicInteger3 = this.val$total_file_size;
            uSBCopyDataManager.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$1$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m153x30c63665(map, map2, atomicInteger, atomicInteger2, atomicInteger3);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onFileCopyFinished$2$com-byd-vrassistant-manager-USBCopyDataManager$1, reason: not valid java name */
        /* synthetic */ void m153x30c63665(HashMap map, HashMap map2, AtomicInteger atomicInteger, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                String strJoin = String.join(" ", map.keySet());
                String strJoin2 = String.join(" ", map2.keySet());
                ProgressBar progressBar = pb.pb_copy;
                if (atomicInteger.get() >= atomicInteger2.get()) {
                    if (progressBar.getProgress() >= atomicInteger3.get() || map.size() >= atomicInteger2.get()) {
                        RLog.e(USBCopyDataManager.TAG, "total_file_size : " + atomicInteger3.get());
                        RLog.e(USBCopyDataManager.TAG, "pb.getProgress() : " + progressBar.getProgress());
                        pb.tx_state.setText("USB data copy is complete");
                        if (map2.size() > 0) {
                            pb.tx_title.setText(strJoin2 + " wrong");
                            Toast.makeText(USBCopyDataManager.this.context, "Data verification failed", 0).show();
                        } else {
                            pb.tx_title.setText("");
                        }
                        pb.tx_state2.setText(strJoin + " complete");
                        final AtomicInteger atomicInteger4 = new AtomicInteger();
                        map.forEach(new BiConsumer() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$1$$ExternalSyntheticLambda4
                            @Override // java.util.function.BiConsumer
                            public final void accept(Object obj, Object obj2) {
                                USBCopyDataManager.AnonymousClass1.lambda$onFileCopyFinished$1(atomicInteger4, (String) obj, (Integer) obj2);
                            }
                        });
                        pb.bt_cancel.setText("OK");
                        pb.bt_cancel.setVisibility(0);
                    }
                }
            }
        }

        static /* synthetic */ void lambda$onFileCopyFinished$1(AtomicInteger atomicInteger, String str, Integer num) {
            if (num.intValue() == 0) {
                atomicInteger.getAndIncrement();
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public void onToast(final String str, final String str2, final int i) {
            USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$1$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m154x1e5003f8(i, str2, str);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onToast$3$com-byd-vrassistant-manager-USBCopyDataManager$1, reason: not valid java name */
        /* synthetic */ void m154x1e5003f8(int i, String str, String str2) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                if (i == 0) {
                    pb.tx_state2.setText(str + str2);
                } else if (i == 1) {
                    pb.tx_title.setText(str + str2);
                } else if (i == 2) {
                    Toast.makeText(USBCopyDataManager.this.context, str + str2, 1).show();
                }
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public boolean onFileCopyBackUp(final String str, final String str2) {
            USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass1.lambda$onFileCopyBackUp$4(str, str2);
                }
            });
            return true;
        }

        static /* synthetic */ void lambda$onFileCopyBackUp$4(String str, String str2) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                pb.tx_title.setText(str);
                pb.tx_state.setText("Backing up current data " + str2);
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public boolean onFileCopyIncreased(final String str, final String str2) {
            USBCopyDataManager uSBCopyDataManager = USBCopyDataManager.this;
            final AtomicInteger atomicInteger = this.val$copy_file_size;
            final AtomicInteger atomicInteger2 = this.val$start_copy_count;
            final AtomicInteger atomicInteger3 = this.val$avalibel_language;
            uSBCopyDataManager.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$1$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass1.lambda$onFileCopyIncreased$5(str, atomicInteger, atomicInteger2, atomicInteger3, str2);
                }
            });
            return true;
        }

        static /* synthetic */ void lambda$onFileCopyIncreased$5(String str, AtomicInteger atomicInteger, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3, String str2) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                pb.tx_title.setText(str);
                RLog.e(USBCopyDataManager.TAG, "onFileCopyIncreased : " + str);
                atomicInteger.addAndGet(1);
                if (atomicInteger2.get() >= atomicInteger3.get()) {
                    pb.tx_state.setText("Copying USB data " + str2);
                    if (pb.pb_copy.getProgress() == 0) {
                        pb.pb_copy.setProgress(atomicInteger.get());
                    } else {
                        pb.pb_copy.incrementProgressBy(1);
                    }
                }
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public void onGetMaxFileSize(final int i) {
            USBCopyDataManager uSBCopyDataManager = USBCopyDataManager.this;
            final AtomicInteger atomicInteger = this.val$total_file_size;
            final AtomicInteger atomicInteger2 = this.val$start_copy_count;
            final AtomicInteger atomicInteger3 = this.val$avalibel_language;
            uSBCopyDataManager.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$1$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass1.lambda$onGetMaxFileSize$6(atomicInteger, i, atomicInteger2, atomicInteger3);
                }
            });
        }

        static /* synthetic */ void lambda$onGetMaxFileSize$6(AtomicInteger atomicInteger, int i, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3) {
            atomicInteger.addAndGet(i);
            atomicInteger2.addAndGet(1);
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                pb.tx_state.setText("The system starts scanning USB data");
                if (atomicInteger2.get() >= atomicInteger3.get()) {
                    pb.pb_copy.setMax(atomicInteger.get());
                }
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public boolean onGetFileMD5Sum(final String str, final int i, String str2) {
            USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass1.lambda$onGetFileMD5Sum$7(i, str);
                }
            });
            return true;
        }

        static /* synthetic */ void lambda$onGetFileMD5Sum$7(int i, String str) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                if (i == 0) {
                    pb.tx_state.setText("Generating file verification code");
                } else if (i == 1) {
                    pb.tx_state.setText("checking file verification code");
                } else if (i == 2) {
                    pb.tx_state.setText("verification usb file");
                }
                pb.tx_title.setText(str);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$startUSBCopyDataEx$1$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /* synthetic */ void m150xfc010da7(CopyDataProgressBar copyDataProgressBar, List list, View view) {
        copyDataProgressBar.group_start.setVisibility(0);
        copyDataProgressBar.bt_cancel.setVisibility(8);
        copyDataProgressBar.bt_start.setVisibility(8);
        list.forEach(new Consumer() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m149xc2366bc8((USBCopyDataManager.USBCopyDataTaskEx) obj);
            }
        });
        if (this.has_usb_copyed) {
            return;
        }
        VoiceAssistantManager.get().destroyVoiceAssistant();
        this.has_usb_copyed = true;
    }

    /* JADX INFO: renamed from: lambda$startUSBCopyDataEx$0$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /* synthetic */ void m149xc2366bc8(USBCopyDataTaskEx uSBCopyDataTaskEx) {
        this.USBCopyDataExecutor.submit(uSBCopyDataTaskEx);
    }

    /* JADX INFO: renamed from: lambda$startUSBCopyDataEx$2$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /* synthetic */ void m151x35cbaf86(View view) {
        endUSBCopyData();
    }

    public void startUSBCopyData() {
        String str = TAG;
        RLog.e(str, "USBCopyDataExecutor pool:" + this.USBCopyDataExecutor.getCorePoolSize());
        RLog.e(str, "USBCopyDataExecutor count:" + this.USBCopyDataExecutor.getActiveCount());
        if (this.has_accent_copyed) {
            RLog.e(str, "accent has process data usb can't copy");
            return;
        }
        if (this.has_ota_copyed) {
            RLog.e(str, "ota has process data usb can't copy");
            return;
        }
        if (UsbUtils.isExistUSB(this.context)) {
            RLog.e(str, "isExistUSB");
            Arrays.stream(UsbUtils.getStoragePath(this.context)).forEach(new Consumer() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$$ExternalSyntheticLambda5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m148xd05c65d4((String) obj);
                }
            });
        }
        RLog.e(str, "USBCopyDataExecutor execute");
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x028b  */
    /* JADX INFO: renamed from: lambda$startUSBCopyData$7$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    /* synthetic */ void m148xd05c65d4(java.lang.String r31) {
        /*
            Method dump skipped, instruction units count: 959
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.manager.USBCopyDataManager.m148xd05c65d4(java.lang.String):void");
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.manager.USBCopyDataManager$2, reason: invalid class name */
    class AnonymousClass2 implements DataUtils.USBCopyDataProgress {
        final /* synthetic */ AtomicInteger val$avalibel_language;
        final /* synthetic */ List val$copyList;
        final /* synthetic */ AtomicInteger val$copy_file_size;
        final /* synthetic */ HashMap val$failed_file_size;
        final /* synthetic */ HashMap val$finished_file_size;
        final /* synthetic */ AtomicInteger val$start_copy_count;
        final /* synthetic */ AtomicInteger val$total_file_size;

        AnonymousClass2(List list, HashMap map, HashMap map2, AtomicInteger atomicInteger, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3, AtomicInteger atomicInteger4) {
            this.val$copyList = list;
            this.val$finished_file_size = map;
            this.val$failed_file_size = map2;
            this.val$start_copy_count = atomicInteger;
            this.val$avalibel_language = atomicInteger2;
            this.val$total_file_size = atomicInteger3;
            this.val$copy_file_size = atomicInteger4;
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public void onFileCopyStarted(String str, String str2) {
            this.val$copyList.add(str2);
            USBCopyDataManager uSBCopyDataManager = USBCopyDataManager.this;
            final List list = this.val$copyList;
            uSBCopyDataManager.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$2$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass2.lambda$onFileCopyStarted$0(list);
                }
            });
        }

        static /* synthetic */ void lambda$onFileCopyStarted$0(List list) {
            RLog.e(USBCopyDataManager.TAG, "onFileCopyStarted");
            ViewManager.getUsbInstallManager().showHardBar();
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                pb.tx_state2.setText("start read data " + String.join(" ", list));
                pb.pb_copy.setProgress(0);
                pb.tx_state.setText("You are ready to start copying USB data");
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public void onFileCopyFinished(int i, String str) {
            this.val$finished_file_size.put(str, Integer.valueOf(i));
            RLog.e(USBCopyDataManager.TAG, "finished_file_size : " + this.val$finished_file_size.size());
            if (i == 1 || i == 2) {
                this.val$failed_file_size.put(str, Integer.valueOf(i));
                RLog.e(USBCopyDataManager.TAG, "failed_file_size : " + this.val$failed_file_size.size());
            }
            USBCopyDataManager uSBCopyDataManager = USBCopyDataManager.this;
            final HashMap map = this.val$finished_file_size;
            final HashMap map2 = this.val$failed_file_size;
            final AtomicInteger atomicInteger = this.val$start_copy_count;
            final AtomicInteger atomicInteger2 = this.val$avalibel_language;
            final AtomicInteger atomicInteger3 = this.val$total_file_size;
            uSBCopyDataManager.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m155x30c63666(map, map2, atomicInteger, atomicInteger2, atomicInteger3);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onFileCopyFinished$2$com-byd-vrassistant-manager-USBCopyDataManager$2, reason: not valid java name */
        /* synthetic */ void m155x30c63666(HashMap map, HashMap map2, AtomicInteger atomicInteger, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                String strJoin = String.join(" ", map.keySet());
                String strJoin2 = String.join(" ", map2.keySet());
                ProgressBar progressBar = pb.pb_copy;
                if (atomicInteger.get() >= atomicInteger2.get()) {
                    if (progressBar.getProgress() >= atomicInteger3.get() || map.size() >= atomicInteger2.get()) {
                        RLog.e(USBCopyDataManager.TAG, "total_file_size : " + atomicInteger3.get());
                        RLog.e(USBCopyDataManager.TAG, "pb.getProgress() : " + progressBar.getProgress());
                        pb.tx_state.setText("USB data copy is complete");
                        if (map2.size() > 0) {
                            pb.tx_title.setText(strJoin2 + " wrong");
                            Toast.makeText(USBCopyDataManager.this.context, "Data verification failed", 0).show();
                        } else {
                            pb.tx_title.setText("");
                        }
                        pb.tx_state2.setText(strJoin + " complete");
                        final AtomicInteger atomicInteger4 = new AtomicInteger();
                        map.forEach(new BiConsumer() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$2$$ExternalSyntheticLambda7
                            @Override // java.util.function.BiConsumer
                            public final void accept(Object obj, Object obj2) {
                                USBCopyDataManager.AnonymousClass2.lambda$onFileCopyFinished$1(atomicInteger4, (String) obj, (Integer) obj2);
                            }
                        });
                        pb.bt_cancel.setText("OK");
                        pb.bt_cancel.setVisibility(0);
                    }
                }
            }
        }

        static /* synthetic */ void lambda$onFileCopyFinished$1(AtomicInteger atomicInteger, String str, Integer num) {
            if (num.intValue() == 0) {
                atomicInteger.getAndIncrement();
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public void onToast(final String str, final String str2, final int i) {
            USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$2$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m156x1e5003f9(i, str2, str);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onToast$3$com-byd-vrassistant-manager-USBCopyDataManager$2, reason: not valid java name */
        /* synthetic */ void m156x1e5003f9(int i, String str, String str2) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                if (i == 0) {
                    pb.tx_state2.setText(str + str2);
                } else if (i == 1) {
                    pb.tx_title.setText(str + str2);
                } else if (i == 2) {
                    Toast.makeText(USBCopyDataManager.this.context, str + str2, 0).show();
                }
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public boolean onFileCopyBackUp(final String str, final String str2) {
            USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$2$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass2.lambda$onFileCopyBackUp$4(str, str2);
                }
            });
            return true;
        }

        static /* synthetic */ void lambda$onFileCopyBackUp$4(String str, String str2) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                pb.tx_title.setText(str);
                pb.tx_state.setText("Backing up current data " + str2);
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public boolean onFileCopyIncreased(final String str, final String str2) {
            USBCopyDataManager uSBCopyDataManager = USBCopyDataManager.this;
            final AtomicInteger atomicInteger = this.val$copy_file_size;
            final AtomicInteger atomicInteger2 = this.val$start_copy_count;
            final AtomicInteger atomicInteger3 = this.val$avalibel_language;
            uSBCopyDataManager.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$2$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass2.lambda$onFileCopyIncreased$5(str, atomicInteger, atomicInteger2, atomicInteger3, str2);
                }
            });
            return true;
        }

        static /* synthetic */ void lambda$onFileCopyIncreased$5(String str, AtomicInteger atomicInteger, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3, String str2) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                pb.tx_title.setText(str);
                atomicInteger.addAndGet(1);
                if (atomicInteger2.get() >= atomicInteger3.get()) {
                    pb.tx_state.setText("Copying USB data " + str2);
                    if (pb.pb_copy.getProgress() == 0) {
                        pb.pb_copy.setProgress(atomicInteger.get());
                    } else {
                        pb.pb_copy.incrementProgressBy(1);
                    }
                }
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public void onGetMaxFileSize(final int i) {
            USBCopyDataManager uSBCopyDataManager = USBCopyDataManager.this;
            final AtomicInteger atomicInteger = this.val$total_file_size;
            final AtomicInteger atomicInteger2 = this.val$start_copy_count;
            final AtomicInteger atomicInteger3 = this.val$avalibel_language;
            uSBCopyDataManager.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$2$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass2.lambda$onGetMaxFileSize$6(atomicInteger, i, atomicInteger2, atomicInteger3);
                }
            });
        }

        static /* synthetic */ void lambda$onGetMaxFileSize$6(AtomicInteger atomicInteger, int i, AtomicInteger atomicInteger2, AtomicInteger atomicInteger3) {
            atomicInteger.addAndGet(i);
            atomicInteger2.addAndGet(1);
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                pb.tx_state.setText("The system starts scanning USB data");
                if (atomicInteger2.get() >= atomicInteger3.get()) {
                    pb.pb_copy.setMax(atomicInteger.get());
                }
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.USBCopyDataProgress
        public boolean onGetFileMD5Sum(final String str, final int i, String str2) {
            USBCopyDataManager.this.runOnUiThreadEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$2$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    USBCopyDataManager.AnonymousClass2.lambda$onGetFileMD5Sum$7(i, str);
                }
            });
            return true;
        }

        static /* synthetic */ void lambda$onGetFileMD5Sum$7(int i, String str) {
            CopyDataProgressBar pb = ViewManager.getUsbInstallManager().getPb();
            if (pb != null) {
                if (i == 0) {
                    pb.tx_state.setText("Generating file verification code");
                } else if (i == 1) {
                    pb.tx_state.setText("checking file verification code");
                }
                pb.tx_title.setText(str);
            }
        }
    }

    /* JADX INFO: renamed from: lambda$startUSBCopyData$5$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /* synthetic */ void m146x5cc72216(CopyDataProgressBar copyDataProgressBar, List list, View view) {
        copyDataProgressBar.group_start.setVisibility(0);
        copyDataProgressBar.bt_cancel.setVisibility(8);
        copyDataProgressBar.bt_start.setVisibility(8);
        list.forEach(new Consumer() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$$ExternalSyntheticLambda10
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.m145x22fc8037((USBCopyDataManager.USBCopyDataTask) obj);
            }
        });
        if (this.has_usb_copyed) {
            return;
        }
        VoiceAssistantManager.get().destroyVoiceAssistant();
        this.has_usb_copyed = true;
    }

    /* JADX INFO: renamed from: lambda$startUSBCopyData$4$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /* synthetic */ void m145x22fc8037(USBCopyDataTask uSBCopyDataTask) {
        this.USBCopyDataExecutor.submit(uSBCopyDataTask);
    }

    /* JADX INFO: renamed from: lambda$startUSBCopyData$6$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /* synthetic */ void m147x9691c3f5(View view) {
        endUSBCopyData();
    }

    public void startOTATask(List<VoiceLanuageDp> list) {
        String str = TAG;
        RLog.e(str, "ota_tasks count:" + list.size());
        RLog.e(str, "USBCopyDataExecutor pool:" + this.USBCopyDataExecutor.getCorePoolSize());
        RLog.e(str, "USBCopyDataExecutor count:" + this.USBCopyDataExecutor.getActiveCount());
        if (this.has_accent_copyed) {
            RLog.e(str, "accent has process data can't process ota");
        } else if (this.has_usb_copyed) {
            RLog.e(str, "usb has copy data can't process ota");
        } else {
            this.has_ota_copyed = true;
            list.forEach(new Consumer() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m144x7cf68d95((VoiceLanuageDp) obj);
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$startOTATask$8$com-byd-vrassistant-manager-USBCopyDataManager, reason: not valid java name */
    /* synthetic */ void m144x7cf68d95(VoiceLanuageDp voiceLanuageDp) {
        if (!VoiceAssistantManager.get().getVoiceLanuageInstallList().contains(voiceLanuageDp)) {
            String str = TAG;
            RLog.e(str, "ota task not exist : " + voiceLanuageDp.toString());
            OTACopyTask oTACopyTask = new OTACopyTask(voiceLanuageDp);
            oTACopyTask.otaDataProgress = new AnonymousClass3();
            if (VoiceAssistantManager.get().getVoiceLanuageInstallList().size() > 0 && voiceLanuageDp.getType() == 1) {
                RLog.e(str, "ota installing can't switch : " + voiceLanuageDp.toString());
                Settings.System.putInt(this.context.getContentResolver(), "KEY_LANGUAG_INSTALLING", 0);
                Settings.System.putInt(this.context.getContentResolver(), "KEY_LANGUAG_INSTALLING", 1);
                return;
            } else {
                updateInstallingDPList(voiceLanuageDp);
                this.USBCopyDataExecutor.submit(oTACopyTask);
                return;
            }
        }
        RLog.e(TAG, "ota task exist : " + voiceLanuageDp.toString());
    }

    /* JADX INFO: renamed from: com.byd.vrassistant.manager.USBCopyDataManager$3, reason: invalid class name */
    class AnonymousClass3 implements DataUtils.OTADataProgress {
        AnonymousClass3() {
        }

        @Override // com.byd.vrassistant.utils.DataUtils.OTADataProgress
        public void onOtaDataStarted(VoiceLanuageDp voiceLanuageDp) {
            RLog.e(USBCopyDataManager.TAG, "onOtaDataStarted : " + voiceLanuageDp.toString());
            switch (voiceLanuageDp.getType()) {
                case 1:
                    VoiceAssistantManager.get().setIsVoiceAssistantIniting(false);
                    VoiceAssistantManager.get().setIsVoiceAssistantStarting(false);
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    USBCopyDataManager.this.updateInstallingDPList(voiceLanuageDp);
                    RLog.e(USBCopyDataManager.TAG, "installing ota task : " + voiceLanuageDp.toString());
                    break;
            }
        }

        @Override // com.byd.vrassistant.utils.DataUtils.OTADataProgress
        public void onOtaDataFinished(final VoiceLanuageDp voiceLanuageDp) {
            RLog.e(USBCopyDataManager.TAG, "onOtaDataFinished : " + voiceLanuageDp.toString());
            USBCopyDataManager.this.updateLanuageDPList(voiceLanuageDp);
            switch (voiceLanuageDp.getType()) {
                case 1:
                case 4:
                case 6:
                    if (voiceLanuageDp.getType() == 4 || voiceLanuageDp.getType() == 6) {
                        SPUtils.getInstance().put(Constant.OTA_KEY_CUR_LANGUAGE, DataUtils.getPlatformDataPackPathEx() + "/" + voiceLanuageDp.getName(), true);
                        VoiceAssistantManager.get().addInstallEnableItem(DataUtils.getPlatformDataPackPathEx() + "/" + voiceLanuageDp.getName());
                    } else if (voiceLanuageDp.getType() == 1) {
                        SPUtils.getInstance().put(Constant.OTA_KEY_CUR_LANGUAGE, voiceLanuageDp.getPath(), true);
                    }
                    break;
                case 2:
                case 3:
                case 5:
                    if (voiceLanuageDp.getType() == 2) {
                        String string = SPUtils.getInstance().getString(Constant.OTA_KEY_CUR_LANGUAGE, "");
                        if (!string.isEmpty() && string.contains(voiceLanuageDp.getName())) {
                            SPUtils.getInstance().put(Constant.OTA_KEY_CUR_LANGUAGE, "", true);
                        }
                    }
                    break;
            }
            USBCopyDataManager.this.runOnUiThreadDelayEx(new Runnable() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m157x43cc91ac(voiceLanuageDp);
                }
            }, 700);
        }

        /* JADX INFO: renamed from: lambda$onOtaDataFinished$0$com-byd-vrassistant-manager-USBCopyDataManager$3, reason: not valid java name */
        /* synthetic */ void m157x43cc91ac(VoiceLanuageDp voiceLanuageDp) {
            USBCopyDataManager.this.endOTATask(voiceLanuageDp);
        }

        @Override // com.byd.vrassistant.utils.DataUtils.OTADataProgress
        public void onOtaDataErr(VoiceLanuageDp voiceLanuageDp, String str) {
            USBCopyDataManager.this.updateLanuageDPList(voiceLanuageDp);
            RLog.e(USBCopyDataManager.TAG, "onOtaDataErr : " + voiceLanuageDp.toString() + ",msg : " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateLanuageDPList(VoiceLanuageDp voiceLanuageDp) {
        List<VoiceLanuageDp> voiceLanuageDpList = VoiceAssistantManager.get().getVoiceLanuageDpList();
        RLog.e(TAG, "updateLanuageDPList : " + voiceLanuageDp.toString());
        for (VoiceLanuageDp voiceLanuageDp2 : voiceLanuageDpList) {
            if (voiceLanuageDp2.getName().equals(voiceLanuageDp.getName())) {
                voiceLanuageDp2.copy(voiceLanuageDp);
                String str = TAG;
                RLog.e(str, "updateLanuageDPList : " + voiceLanuageDp.getName() + ", cpoy");
                VoiceAssistantManager.get();
                String json = VoiceAssistantManager.gson.toJson(voiceLanuageDpList);
                RLog.e(str, "voiceLanuageDpJsons:" + json);
                Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_DPS", json);
                return;
            }
        }
        int type = voiceLanuageDp.getType();
        if (type == 3 || type == 4 || type == 5 || type == 6) {
            voiceLanuageDp.setPath(DataUtils.getPlatformDataPackPathEx() + "/" + voiceLanuageDp.getName());
        }
        String str2 = TAG;
        RLog.e(str2, "updateLanuageDPList : " + voiceLanuageDp.getName() + ", add");
        voiceLanuageDpList.add(voiceLanuageDp);
        VoiceAssistantManager.get();
        String json2 = VoiceAssistantManager.gson.toJson(voiceLanuageDpList);
        RLog.e(str2, "voiceLanuageDpJsons:" + json2);
        Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_DPS", json2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInstallingDPList(VoiceLanuageDp voiceLanuageDp) {
        List<VoiceLanuageDp> voiceLanuageInstallList = VoiceAssistantManager.get().getVoiceLanuageInstallList();
        if (!voiceLanuageInstallList.contains(voiceLanuageDp)) {
            if (voiceLanuageDp.getType() >= 2 && !this.need2Record) {
                this.need2Record = true;
                RLog.e(TAG, "need2Record: " + this.need2Record);
            }
            voiceLanuageInstallList.add(voiceLanuageDp);
            RLog.e(TAG, "voiceInstallDpList add : " + voiceLanuageDp.toString());
        }
        String str = TAG;
        RLog.e(str, "voiceInstallDpList : " + voiceLanuageDp.toString());
        VoiceAssistantManager.get();
        String json = VoiceAssistantManager.gson.toJson(voiceLanuageInstallList);
        RLog.e(str, "voiceLanuageDpJsons:" + json);
        Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_INSTALLDPS", json);
    }

    public void endOTATask(VoiceLanuageDp voiceLanuageDp) {
        String str = TAG;
        RLog.e(str, "endOTATask:" + voiceLanuageDp.getName());
        if (has_usa_copyed && this.USBCopyDataExecutor.getActiveCount() > 1) {
            RLog.e(str, "endOTATask has_usa_copyed:" + has_usa_copyed + ",USBCopyDataExecutor.getActiveCount():" + this.USBCopyDataExecutor.getActiveCount());
            return;
        }
        this.has_ota_copyed = false;
        VoiceAssistantManager.get().getVoiceLanuageInstallList().clear();
        Settings.System.putString(this.context.getContentResolver(), "KEY_LANGUAG_INSTALLDPS", "");
        try {
            if (VoiceAssistantManager.get().getFailCountDown() >= 2) {
                VoiceAssistantManager.get().setFailCountDown(1);
            }
            initLanguageList();
            if (voiceLanuageDp.getType() == 1 || voiceLanuageDp.getType() == 4 || voiceLanuageDp.getType() == 6) {
                RLog.e(str, "endOTATask destroyVoiceAssistant");
                VoiceAssistantManager.get().destroyVoiceAssistant();
                RLog.e(str, "endOTATask initVoiceAssistant");
                VoiceAssistantManager.get().initVoiceAssistant();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initLanguageList() {
        Optional<VoiceLanuageDp> optionalFindAny = VoiceAssistantManager.get().getVoiceLanuageDpList().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$$ExternalSyntheticLambda8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return USBCopyDataManager.lambda$initLanguageList$9((VoiceLanuageDp) obj);
            }
        }).findAny();
        final VoiceLanuageDp voiceLanuageDp = optionalFindAny.isPresent() ? optionalFindAny.get() : null;
        VoiceAssistantManager.get().initLanguageDatas();
        if (voiceLanuageDp != null) {
            VoiceAssistantManager.get().getVoiceLanuageDpList().stream().forEach(new Consumer() { // from class: com.byd.vrassistant.manager.USBCopyDataManager$$ExternalSyntheticLambda9
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    USBCopyDataManager.lambda$initLanguageList$10(voiceLanuageDp, (VoiceLanuageDp) obj);
                }
            });
        }
    }

    static /* synthetic */ boolean lambda$initLanguageList$9(VoiceLanuageDp voiceLanuageDp) {
        return voiceLanuageDp.getUsed() == 1;
    }

    static /* synthetic */ void lambda$initLanguageList$10(VoiceLanuageDp voiceLanuageDp, VoiceLanuageDp voiceLanuageDp2) {
        if (voiceLanuageDp2.getName().equals(voiceLanuageDp.getName())) {
            voiceLanuageDp2.setUsed(1);
        }
    }

    public void startAccentTask(String str, String str2, String str3, String str4) {
        String str5 = TAG;
        RLog.e(str5, "startAccentTask src_suffix:" + str3 + ",dst_suffix:" + str4);
        if (this.has_usb_copyed) {
            RLog.e(str5, "usb has copy data can't process ota");
            return;
        }
        if (this.has_ota_copyed) {
            RLog.e(str5, "ota has process data usb can't copy");
            return;
        }
        RLog.e(str5, "ac hascopyed " + this.has_accent_copyed);
        RLog.e(str5, "ac getActiveCount " + this.USBCopyDataExecutor.getActiveCount());
        RLog.e(str5, "submit src_suffix:" + str3 + ",dst_suffix:" + str4);
        this.has_accent_copyed = true;
        this.USBCopyDataExecutor.submit(new AccentCopyTask(str, str2, str3, str4));
    }

    public void startAccentVerifyTask(String str, String str2, String str3, String str4) {
        String str5 = TAG;
        RLog.e(str5, "startAccentVerifyTask src:" + str + ",dst:" + str2);
        RLog.e(str5, "USBCopyDataExecutor.getActiveCount " + this.USBCopyDataExecutor.getActiveCount());
        AccentVerifyTask accentVerifyTask = new AccentVerifyTask(str, str2, str3, str4);
        if (this.verifyAccentMap.containsKey(str2)) {
            return;
        }
        this.verifyAccentMap.put(str2, accentVerifyTask);
        new Thread(accentVerifyTask).start();
    }

    public void startCopyUSATask() {
        String str = TAG;
        RLog.e(str, "startCopyUSATask ");
        if (has_usa_copyed) {
            RLog.e(str, "startCopyUSATask is started");
        } else {
            has_usa_copyed = true;
            this.USBCopyDataExecutor.submit(new USACopyTask());
        }
    }
}
