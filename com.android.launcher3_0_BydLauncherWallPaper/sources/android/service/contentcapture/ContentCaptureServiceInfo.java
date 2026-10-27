package android.service.contentcapture;

import android.Manifest;
import android.app.AppGlobals;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.RemoteException;
import android.util.Log;
import android.util.Slog;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import java.io.PrintWriter;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
public final class ContentCaptureServiceInfo {
    private static final String TAG = "ContentCaptureServiceInfo";
    private static final String XML_TAG_SERVICE = "content-capture-service";
    private final ServiceInfo mServiceInfo;
    private final String mSettingsActivity;

    private static ServiceInfo getServiceInfoOrThrow(ComponentName componentName, boolean z, int i) throws PackageManager.NameNotFoundException {
        ServiceInfo serviceInfo = null;
        try {
            serviceInfo = AppGlobals.getPackageManager().getServiceInfo(componentName, !z ? 1048704 : 128, i);
        } catch (RemoteException unused) {
        }
        if (serviceInfo == null) {
            throw new PackageManager.NameNotFoundException("Could not get serviceInfo for " + (z ? " (temp)" : "(default system)") + " " + componentName.flattenToShortString());
        }
        return serviceInfo;
    }

    public ContentCaptureServiceInfo(Context context, ComponentName componentName, boolean z, int i) throws PackageManager.NameNotFoundException {
        this(context, getServiceInfoOrThrow(componentName, z, i));
    }

    private ContentCaptureServiceInfo(Context context, ServiceInfo serviceInfo) {
        TypedArray typedArrayObtainAttributes;
        if (!Manifest.permission.BIND_CONTENT_CAPTURE_SERVICE.equals(serviceInfo.permission)) {
            Slog.w(TAG, "ContentCaptureService from '" + serviceInfo.packageName + "' does not require permission " + Manifest.permission.BIND_CONTENT_CAPTURE_SERVICE);
            throw new SecurityException("Service does not require permission android.permission.BIND_CONTENT_CAPTURE_SERVICE");
        }
        this.mServiceInfo = serviceInfo;
        XmlResourceParser xmlResourceParserLoadXmlMetaData = serviceInfo.loadXmlMetaData(context.getPackageManager(), ContentCaptureService.SERVICE_META_DATA);
        String string = null;
        if (xmlResourceParserLoadXmlMetaData == null) {
            this.mSettingsActivity = null;
            return;
        }
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(serviceInfo.applicationInfo);
            for (int next = 0; next != 1 && next != 2; next = xmlResourceParserLoadXmlMetaData.next()) {
            }
            if (XML_TAG_SERVICE.equals(xmlResourceParserLoadXmlMetaData.getName())) {
                try {
                    typedArrayObtainAttributes = resourcesForApplication.obtainAttributes(Xml.asAttributeSet(xmlResourceParserLoadXmlMetaData), R.styleable.ContentCaptureService);
                } catch (Throwable th) {
                    th = th;
                    typedArrayObtainAttributes = null;
                }
                try {
                    string = typedArrayObtainAttributes.getString(0);
                    if (typedArrayObtainAttributes != null) {
                        typedArrayObtainAttributes.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (typedArrayObtainAttributes != null) {
                        typedArrayObtainAttributes.recycle();
                    }
                    throw th;
                }
            } else {
                Log.e(TAG, "Meta-data does not start with content-capture-service tag");
            }
        } catch (PackageManager.NameNotFoundException | IOException | XmlPullParserException e) {
            Log.e(TAG, "Error parsing auto fill service meta-data", e);
        }
        this.mSettingsActivity = string;
    }

    public ServiceInfo getServiceInfo() {
        return this.mServiceInfo;
    }

    public String getSettingsActivity() {
        return this.mSettingsActivity;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[").append(this.mServiceInfo);
        sb.append(", settings:").append(this.mSettingsActivity);
        return sb.toString();
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.print("Component: ");
        printWriter.println(getServiceInfo().getComponentName());
        printWriter.print(str);
        printWriter.print("Settings: ");
        printWriter.println(this.mSettingsActivity);
    }
}
