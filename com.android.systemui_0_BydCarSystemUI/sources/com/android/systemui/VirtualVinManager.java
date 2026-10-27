package com.android.systemui;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Handler;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.byd.profile.utils.BydLogHelper;
import com.google.common.primitives.UnsignedBytes;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class VirtualVinManager {
    public static final String BYD_TAG = "BYD";
    public static final String SALE_VALUE = "tFjx4#Gyn!5ZbKC6u3lh3Izu%P5i25w%";
    private static volatile VirtualVinManager singleton;
    private Context mContext;
    private BYDAutoBodyworkDevice mDevice;
    private String realVin;
    private String virtualVinStr = "tes0000000000000000";

    public static VirtualVinManager getInstance(Context context) {
        if (singleton == null) {
            synchronized (VirtualVinManager.class) {
                if (singleton == null) {
                    singleton = new VirtualVinManager(context.getApplicationContext());
                }
            }
        }
        return singleton;
    }

    private VirtualVinManager(Context context) {
        this.mContext = context;
        this.mDevice = BYDAutoBodyworkDevice.getInstance(context);
    }

    public void setVirtualVinSystemProperties() {
        new Handler().postDelayed(new Runnable() { // from class: com.android.systemui.VirtualVinManager.1
            @Override // java.lang.Runnable
            public void run() {
                VirtualVinManager virtualVinManager = VirtualVinManager.this;
                virtualVinManager.realVin = virtualVinManager.mDevice.getRealAutoVIN();
                if (TextUtils.isEmpty(VirtualVinManager.this.realVin)) {
                    BydLogHelper.e("getRealAutoVIN==null,return!!!");
                    SystemProperties.set(BYDAutoSysPropertiesDevice.CAR_VIN, VirtualVinManager.this.virtualVinStr);
                    return;
                }
                VirtualVinManager virtualVinManager2 = VirtualVinManager.this;
                String strCreateMD5 = virtualVinManager2.createMD5(virtualVinManager2.realVin.concat(VirtualVinManager.SALE_VALUE));
                if (strCreateMD5 == null) {
                    BydLogHelper.e("createMD5==null,return!!!");
                    SystemProperties.set(BYDAutoSysPropertiesDevice.CAR_VIN, VirtualVinManager.this.virtualVinStr);
                    return;
                }
                VirtualVinManager.this.virtualVinStr = VirtualVinManager.BYD_TAG.concat(strCreateMD5.substring(8, 24));
                BydLogHelper.i("virtualVinStr:" + VirtualVinManager.this.virtualVinStr);
                SystemProperties.set(BYDAutoSysPropertiesDevice.CAR_VIN, VirtualVinManager.this.virtualVinStr);
            }
        }, 5000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String createMD5(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(text.getBytes(StandardCharsets.UTF_8));
            return bytesToHexStr(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String bytesToHexStr(byte[] buf) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : buf) {
            String hexString = Integer.toHexString(b & UnsignedBytes.MAX_VALUE);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }
}
