package com.byd.dilink.compat;

import android.util.Log;
import androidx.annotation.Keep;
import com.byd.spi.register.RegisterService_1db9ff3288b50a5dd615e4c9a76e7edf;
import com.byd.spi.register.RegisterService_2caf140845aad6272d5415e8e22f3b35;
import com.byd.spi.register.RegisterService_39fbee12c1e482b80acad9fa7cc47506;
import com.byd.spi.register.RegisterService_4656417fe63e6686596b6c28925d2144;
import com.byd.spi.register.RegisterService_47d82600a8d4045600ae13f1710304e6;
import com.byd.spi.register.RegisterService_5a29efc71b124ba9a29f9de492a4dfc4;
import com.byd.spi.register.RegisterService_6426cb87a34aafceea42f6d24592e47f;
import com.byd.spi.register.RegisterService_782d736035e9697bb4fa4b4e6f095329;
import com.byd.spi.register.RegisterService_8ffce627f7fe69c0dc709db88c22b484;
import com.byd.spi.register.RegisterService_d8637966874ac63b278475bd3a74d169;
import com.byd.spi.register.RegisterService_ec1091ae1c74271e7f6095b4e7dc658f;
import com.byd.spi.register.RegisterService_fa11b5ae53bf7a2a4cfcfb9a6b228b39;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class DiLinkSpiRegisterEntry {
    public static void registerAll() {
        Log.d("DiLinkSpiRegisterEntry", "start register All spi impl!");
        try {
            RegisterService_ec1091ae1c74271e7f6095b4e7dc658f.register();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [1]!");
        try {
            RegisterService_6426cb87a34aafceea42f6d24592e47f.register();
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [2]!");
        try {
            RegisterService_8ffce627f7fe69c0dc709db88c22b484.register();
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [3]!");
        try {
            RegisterService_2caf140845aad6272d5415e8e22f3b35.register();
        } catch (Throwable th4) {
            th4.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [4]!");
        try {
            RegisterService_fa11b5ae53bf7a2a4cfcfb9a6b228b39.register();
        } catch (Throwable th5) {
            th5.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [5]!");
        try {
            RegisterService_1db9ff3288b50a5dd615e4c9a76e7edf.register();
        } catch (Throwable th6) {
            th6.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [6]!");
        try {
            RegisterService_39fbee12c1e482b80acad9fa7cc47506.register();
        } catch (Throwable th7) {
            th7.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [7]!");
        try {
            RegisterService_782d736035e9697bb4fa4b4e6f095329.register();
        } catch (Throwable th8) {
            th8.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [8]!");
        try {
            RegisterService_d8637966874ac63b278475bd3a74d169.register();
        } catch (Throwable th9) {
            th9.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [9]!");
        try {
            RegisterService_5a29efc71b124ba9a29f9de492a4dfc4.register();
        } catch (Throwable th10) {
            th10.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [10]!");
        try {
            RegisterService_4656417fe63e6686596b6c28925d2144.register();
        } catch (Throwable th11) {
            th11.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [11]!");
        try {
            RegisterService_47d82600a8d4045600ae13f1710304e6.register();
        } catch (Throwable th12) {
            th12.printStackTrace();
        }
        Log.d("DiLinkSpiRegisterEntry", "finish register spi impl [12]!");
    }
}
