package android.service.quickaccesswallet;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
class QuickAccessWalletServiceInfo {
    private static final String TAG = "QAWalletSInfo";
    private static final String TAG_WALLET_SERVICE = "quickaccesswallet-service";
    private final ServiceInfo mServiceInfo;
    private final ServiceMetadata mServiceMetadata;

    private QuickAccessWalletServiceInfo(ServiceInfo serviceInfo, ServiceMetadata serviceMetadata) {
        this.mServiceInfo = serviceInfo;
        this.mServiceMetadata = serviceMetadata;
    }

    static QuickAccessWalletServiceInfo tryCreate(Context context) {
        ServiceInfo walletServiceInfo;
        ComponentName defaultPaymentApp = getDefaultPaymentApp(context);
        if (defaultPaymentApp == null || (walletServiceInfo = getWalletServiceInfo(context, defaultPaymentApp.getPackageName())) == null) {
            return null;
        }
        if (Manifest.permission.BIND_QUICK_ACCESS_WALLET_SERVICE.equals(walletServiceInfo.permission)) {
            return new QuickAccessWalletServiceInfo(walletServiceInfo, parseServiceMetadata(context, walletServiceInfo));
        }
        Log.w(TAG, String.format("%s.%s does not require permission %s", walletServiceInfo.packageName, walletServiceInfo.name, Manifest.permission.BIND_QUICK_ACCESS_WALLET_SERVICE));
        return null;
    }

    private static ComponentName getDefaultPaymentApp(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.NFC_PAYMENT_DEFAULT_COMPONENT);
        if (string == null) {
            return null;
        }
        return ComponentName.unflattenFromString(string);
    }

    private static ServiceInfo getWalletServiceInfo(Context context, String str) {
        Intent intent = new Intent(QuickAccessWalletService.SERVICE_INTERFACE);
        intent.setPackage(str);
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 65536);
        if (listQueryIntentServices.isEmpty()) {
            return null;
        }
        return listQueryIntentServices.get(0).serviceInfo;
    }

    private static class ServiceMetadata {
        private final String mSettingsActivity;
        private final CharSequence mShortcutLongLabel;
        private final CharSequence mShortcutShortLabel;
        private final String mTargetActivity;

        /* JADX INFO: Access modifiers changed from: private */
        public static ServiceMetadata empty() {
            return new ServiceMetadata(null, null, null, null);
        }

        private ServiceMetadata(String str, String str2, CharSequence charSequence, CharSequence charSequence2) {
            this.mTargetActivity = str;
            this.mSettingsActivity = str2;
            this.mShortcutShortLabel = charSequence;
            this.mShortcutLongLabel = charSequence2;
        }
    }

    private static ServiceMetadata parseServiceMetadata(Context context, ServiceInfo serviceInfo) {
        Resources resourcesForApplication;
        PackageManager packageManager = context.getPackageManager();
        XmlResourceParser xmlResourceParserLoadXmlMetaData = serviceInfo.loadXmlMetaData(packageManager, QuickAccessWalletService.SERVICE_META_DATA);
        if (xmlResourceParserLoadXmlMetaData == null) {
            return ServiceMetadata.empty();
        }
        try {
            resourcesForApplication = packageManager.getResourcesForApplication(serviceInfo.applicationInfo);
            for (int next = 0; next != 1 && next != 2; next = xmlResourceParserLoadXmlMetaData.next()) {
            }
        } catch (PackageManager.NameNotFoundException | IOException | XmlPullParserException e) {
            Log.e(TAG, "Error parsing quickaccesswallet service meta-data", e);
        }
        if (TAG_WALLET_SERVICE.equals(xmlResourceParserLoadXmlMetaData.getName())) {
            TypedArray typedArrayObtainAttributes = null;
            try {
                typedArrayObtainAttributes = resourcesForApplication.obtainAttributes(Xml.asAttributeSet(xmlResourceParserLoadXmlMetaData), R.styleable.QuickAccessWalletService);
                return new ServiceMetadata(typedArrayObtainAttributes.getString(0), typedArrayObtainAttributes.getString(1), typedArrayObtainAttributes.getText(2), typedArrayObtainAttributes.getText(3));
            } finally {
                if (typedArrayObtainAttributes != null) {
                    typedArrayObtainAttributes.recycle();
                }
            }
        }
        Log.e(TAG, "Meta-data does not start with quickaccesswallet-service tag");
        return ServiceMetadata.empty();
    }

    ComponentName getComponentName() {
        return this.mServiceInfo.getComponentName();
    }

    String getWalletActivity() {
        return this.mServiceMetadata.mTargetActivity;
    }

    String getSettingsActivity() {
        return this.mServiceMetadata.mSettingsActivity;
    }

    Drawable getWalletLogo(Context context) {
        Drawable drawableLoadLogo = this.mServiceInfo.loadLogo(context.getPackageManager());
        return drawableLoadLogo != null ? drawableLoadLogo : this.mServiceInfo.loadIcon(context.getPackageManager());
    }

    CharSequence getShortcutShortLabel(Context context) {
        if (!TextUtils.isEmpty(this.mServiceMetadata.mShortcutShortLabel)) {
            return this.mServiceMetadata.mShortcutShortLabel;
        }
        return this.mServiceInfo.loadLabel(context.getPackageManager());
    }

    CharSequence getShortcutLongLabel(Context context) {
        if (!TextUtils.isEmpty(this.mServiceMetadata.mShortcutLongLabel)) {
            return this.mServiceMetadata.mShortcutLongLabel;
        }
        return this.mServiceInfo.loadLabel(context.getPackageManager());
    }

    CharSequence getServiceLabel(Context context) {
        return this.mServiceInfo.loadLabel(context.getPackageManager());
    }
}
