package com.facebook.react;

import com.BV.LinearGradient.LinearGradientPackage;
import com.airbnb.android.react.lottie.LottiePackage;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.horcrux.svg.SvgPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativecommunity.netinfo.NetInfoPackage;
import com.shopify.reactnative.flash_list.ReactNativeFlashListPackage;
import com.shopify.reactnativeperformance.ReactNativePerformancePackage;
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import java.util.ArrayList;
import java.util.Arrays;
import org.reactnative.maskedview.RNCMaskedViewPackage;

/* JADX INFO: loaded from: classes6.dex */
public class PackageList {
    private ReactNativeHost reactNativeHost;

    public PackageList(ReactNativeHost reactNativeHost) {
        this(reactNativeHost, null);
    }

    public PackageList(ReactNativeHost reactNativeHost, MainPackageConfig config) {
        this.reactNativeHost = reactNativeHost;
    }

    public ArrayList<ReactPackage> getPackages() {
        return new ArrayList<>(Arrays.asList(new MainReactPackage(null), new RNCMaskedViewPackage(), new ReactNativeFlashListPackage(), new ReactNativePerformancePackage(), new AsyncStoragePackage(), new NetInfoPackage(), new SvgPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new LottiePackage(), new LinearGradientPackage(), new RNGestureHandlerPackage()));
    }
}
