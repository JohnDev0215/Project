package com.ecarx.xui.adaptapi.policy;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class StoragePolicy implements IStoragePolicy {
    public static final String TAG = "StoragePolicy";
    private Context mContext;

    public static final class VolumeType {
        public static int VOLUME_GKUI_PRIVATE_COMMON = 5;
        public static int VOLUME_GKUI_PRIVATE_MAP = 4;
        public static int VOLUME_GKUI_PRIVATE_VR_RES = 3;
        public static int VOLUME_USB_FLASH_DISK_1 = 1;
        public static int VOLUME_USB_FLASH_DISK_2 = 2;
    }

    public StoragePolicy(Context context) {
        this.mContext = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002e A[PHI: r2
  0x002e: PHI (r2v6 java.lang.String) = (r2v5 java.lang.String), (r2v5 java.lang.String), (r2v7 java.lang.String), (r2v7 java.lang.String) binds: [B:21:0x0036, B:23:0x0039, B:14:0x0029, B:16:0x002c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x003c  */
    @Override // com.ecarx.xui.adaptapi.policy.IStoragePolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getVolumeName(int r5) {
        /*
            r4 = this;
            com.ecarx.xui.adaptapi.policy.ContextProxy r0 = com.ecarx.xui.adaptapi.policy.ContextProxy.getInstance()
            r0.getProject()
            r0 = 107(0x6b, float:1.5E-43)
            int r0 = com.neusoft.optimus.utility.offlineconfig.OfflineConfig.getE01OfflineConfig(r0)
            r1 = 274(0x112, float:3.84E-43)
            int r1 = com.neusoft.optimus.utility.offlineconfig.OfflineConfig.getE01OfflineConfig(r1)
            int r2 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_GKUI_PRIVATE_COMMON
            if (r5 != r2) goto L18
            goto L3c
        L18:
            int r2 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_GKUI_PRIVATE_MAP
            if (r5 != r2) goto L1d
            goto L3c
        L1d:
            int r2 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_GKUI_PRIVATE_VR_RES
            if (r5 != r2) goto L22
            goto L3c
        L22:
            int r2 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_USB_FLASH_DISK_1
            r3 = 0
            if (r5 != r2) goto L30
            java.lang.String r2 = "external_udisk1"
            if (r0 != 0) goto L2e
            r0 = 1
            if (r1 != r0) goto L3e
        L2e:
            r3 = r2
            goto L3e
        L30:
            int r2 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_USB_FLASH_DISK_2
            if (r5 != r2) goto L3c
            java.lang.String r2 = "external_udisk2"
            if (r0 != 0) goto L2e
            r0 = 2
            if (r1 != r0) goto L3e
            goto L2e
        L3c:
            java.lang.String r3 = ""
        L3e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getVolumeName volumeType = "
            r0.<init>(r1)
            java.lang.StringBuilder r5 = r0.append(r5)
            java.lang.String r0 = ", result = "
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.StringBuilder r5 = r5.append(r3)
            java.lang.String r5 = r5.toString()
            java.lang.String r0 = "StoragePolicy"
            android.util.Log.d(r0, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.policy.StoragePolicy.getVolumeName(int):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005e  */
    @Override // com.ecarx.xui.adaptapi.policy.IStoragePolicy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getVolumeFullPath(int r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getVolumeFullPath volumeType = "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r7)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "StoragePolicy"
            android.util.Log.d(r1, r0)
            com.ecarx.xui.adaptapi.policy.ContextProxy r0 = com.ecarx.xui.adaptapi.policy.ContextProxy.getInstance()
            r0.getProject()
            r0 = 107(0x6b, float:1.5E-43)
            int r0 = com.neusoft.optimus.utility.offlineconfig.OfflineConfig.getE01OfflineConfig(r0)
            r2 = 274(0x112, float:3.84E-43)
            int r2 = com.neusoft.optimus.utility.offlineconfig.OfflineConfig.getE01OfflineConfig(r2)
            int r3 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_GKUI_PRIVATE_COMMON
            java.lang.String r4 = ""
            if (r7 != r3) goto L43
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.StringBuilder r7 = r7.append(r4)
            java.lang.String r4 = r7.toString()
            goto L6b
        L43:
            int r3 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_GKUI_PRIVATE_MAP
            if (r7 != r3) goto L4a
            java.lang.String r4 = "/resources"
            goto L6b
        L4a:
            int r3 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_GKUI_PRIVATE_VR_RES
            if (r7 != r3) goto L51
            java.lang.String r4 = "/ivres"
            goto L6b
        L51:
            int r3 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_USB_FLASH_DISK_1
            r5 = 0
            if (r7 != r3) goto L60
            java.lang.String r4 = "/storage/usbotg/usbotg-sda1"
            if (r0 != 0) goto L6b
            r7 = 1
            if (r2 != r7) goto L5e
            goto L6b
        L5e:
            r4 = r5
            goto L6b
        L60:
            int r3 = com.ecarx.xui.adaptapi.policy.StoragePolicy.VolumeType.VOLUME_USB_FLASH_DISK_2
            if (r7 != r3) goto L6b
            java.lang.String r4 = "/storage/usbotg/usbotg-sda2"
            if (r0 != 0) goto L6b
            r7 = 2
            if (r2 != r7) goto L5e
        L6b:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r0 = "getVolumeFullPath result = "
            r7.<init>(r0)
            java.lang.StringBuilder r7 = r7.append(r4)
            java.lang.String r7 = r7.toString()
            android.util.Log.d(r1, r7)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecarx.xui.adaptapi.policy.StoragePolicy.getVolumeFullPath(int):java.lang.String");
    }
}
