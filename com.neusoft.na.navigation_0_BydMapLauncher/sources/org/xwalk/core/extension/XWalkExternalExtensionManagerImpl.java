package org.xwalk.core.extension;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.xwalk.core.XWalkExternalExtensionManager;
import org.xwalk.core.XWalkNativeExtensionLoader;
import org.xwalk.core.XWalkView;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkExternalExtensionManagerImpl extends XWalkExternalExtensionManager implements XWalkExtensionContextClient {
    private static final String TAG = "XWalkExternalExtensionManagerImpl";
    private final Context mContext;
    private final HashMap<String, XWalkExternalExtensionBridge> mExtensions;
    private boolean mLoadExternalExtensions;
    private final XWalkNativeExtensionLoader mNativeExtensionLoader;
    private final XWalkView mXWalkView;

    public XWalkExternalExtensionManagerImpl(XWalkView xWalkView) {
        super(xWalkView);
        this.mExtensions = new HashMap<>();
        this.mXWalkView = xWalkView;
        if (getBridge() == null) {
            Log.e(TAG, "Cannot load external extensions due to old version of runtime library");
            this.mContext = null;
            this.mLoadExternalExtensions = false;
            this.mNativeExtensionLoader = null;
            return;
        }
        this.mContext = getViewContext();
        this.mLoadExternalExtensions = true;
        this.mNativeExtensionLoader = new XWalkNativeExtensionLoader();
        loadNativeExtensions();
    }

    @Override // org.xwalk.core.extension.XWalkExtensionContextClient
    public void registerExtension(XWalkExternalExtension xWalkExternalExtension) {
        if (this.mExtensions.get(xWalkExternalExtension.getExtensionName()) != null) {
            Log.e(TAG, xWalkExternalExtension.getExtensionName() + "is already registered!");
        } else {
            this.mExtensions.put(xWalkExternalExtension.getExtensionName(), XWalkExternalExtensionBridgeFactory.createInstance(xWalkExternalExtension));
        }
    }

    @Override // org.xwalk.core.extension.XWalkExtensionContextClient
    public void unregisterExtension(String str) {
        XWalkExternalExtensionBridge xWalkExternalExtensionBridge = this.mExtensions.get(str);
        if (xWalkExternalExtensionBridge != null) {
            this.mExtensions.remove(str);
            xWalkExternalExtensionBridge.onDestroy();
        }
    }

    @Override // org.xwalk.core.extension.XWalkExtensionContextClient
    public Context getContext() {
        return this.mContext;
    }

    @Override // org.xwalk.core.extension.XWalkExtensionContextClient
    public Activity getActivity() {
        Context context = this.mContext;
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    @Override // org.xwalk.core.extension.XWalkExtensionContextClient
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        throw new ActivityNotFoundException("This method is no longer supported");
    }

    @Override // org.xwalk.core.XWalkExternalExtensionManager
    public void loadExtension(String str) {
        String fileContent;
        if (this.mLoadExternalExtensions) {
            int length = str.length() - 1;
            if (str.charAt(length) == File.separatorChar) {
                str = str.substring(0, length);
            }
            String str2 = str + File.separator + str.substring(str.lastIndexOf(File.separatorChar) + 1) + ".json";
            try {
                try {
                    JSONObject jSONObject = new JSONObject(getFileContent(this.mContext, str2, false));
                    String string = jSONObject.getString("name");
                    String string2 = jSONObject.getString("class");
                    String strOptString = jSONObject.optString("jsapi");
                    if (strOptString != null && strOptString.length() != 0) {
                        strOptString = str + File.separator + strOptString;
                    }
                    if (strOptString == null || strOptString.length() == 0) {
                        fileContent = null;
                    } else {
                        try {
                            fileContent = getFileContent(this.mContext, strOptString, false);
                        } catch (IOException unused) {
                            Log.w(TAG, "Failed to read the file " + strOptString);
                            return;
                        }
                    }
                    if (string == null || string2 == null) {
                        return;
                    }
                    Log.i(TAG, "createExternalExtension: name: " + string + " className: " + string2);
                    createExternalExtension(string, string2, fileContent, this);
                } catch (JSONException unused2) {
                    Log.w(TAG, "Failed to parse json file: " + str2);
                }
            } catch (IOException unused3) {
                Log.w(TAG, "Failed to read json file: " + str2);
            }
        }
    }

    @Override // org.xwalk.core.extension.XWalkExtensionContextClient
    public void postMessage(XWalkExternalExtension xWalkExternalExtension, int i, String str) {
        XWalkExternalExtensionBridge xWalkExternalExtensionBridge = this.mExtensions.get(xWalkExternalExtension.getExtensionName());
        if (xWalkExternalExtensionBridge != null) {
            xWalkExternalExtensionBridge.postMessage(i, str);
        }
    }

    @Override // org.xwalk.core.extension.XWalkExtensionContextClient
    public void postBinaryMessage(XWalkExternalExtension xWalkExternalExtension, int i, byte[] bArr) {
        XWalkExternalExtensionBridge xWalkExternalExtensionBridge = this.mExtensions.get(xWalkExternalExtension.getExtensionName());
        if (xWalkExternalExtensionBridge != null) {
            xWalkExternalExtensionBridge.postBinaryMessage(i, bArr);
        }
    }

    @Override // org.xwalk.core.extension.XWalkExtensionContextClient
    public void broadcastMessage(XWalkExternalExtension xWalkExternalExtension, String str) {
        XWalkExternalExtensionBridge xWalkExternalExtensionBridge = this.mExtensions.get(xWalkExternalExtension.getExtensionName());
        if (xWalkExternalExtensionBridge != null) {
            xWalkExternalExtensionBridge.broadcastMessage(str);
        }
    }

    @Override // org.xwalk.core.XWalkExternalExtensionManager
    public void onStart() {
        Iterator<XWalkExternalExtensionBridge> it = this.mExtensions.values().iterator();
        while (it.hasNext()) {
            it.next().onStart();
        }
    }

    @Override // org.xwalk.core.XWalkExternalExtensionManager
    public void onResume() {
        Iterator<XWalkExternalExtensionBridge> it = this.mExtensions.values().iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // org.xwalk.core.XWalkExternalExtensionManager
    public void onPause() {
        Iterator<XWalkExternalExtensionBridge> it = this.mExtensions.values().iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    @Override // org.xwalk.core.XWalkExternalExtensionManager
    public void onStop() {
        Iterator<XWalkExternalExtensionBridge> it = this.mExtensions.values().iterator();
        while (it.hasNext()) {
            it.next().onStop();
        }
    }

    @Override // org.xwalk.core.XWalkExternalExtensionManager
    public void onDestroy() {
        Iterator<XWalkExternalExtensionBridge> it = this.mExtensions.values().iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
        this.mExtensions.clear();
    }

    @Override // org.xwalk.core.XWalkExternalExtensionManager
    public void onNewIntent(Intent intent) {
        Iterator<XWalkExternalExtensionBridge> it = this.mExtensions.values().iterator();
        while (it.hasNext()) {
            it.next().onNewIntent(intent);
        }
    }

    public void setAllowExternalExtensions(boolean z) {
        this.mLoadExternalExtensions = z;
    }

    private String getFileContent(Context context, String str, boolean z) throws IOException {
        InputStream inputStreamOpenRawResource = null;
        if (z) {
            try {
                Resources resources = context.getResources();
                String str2 = new File(str).getName().split("\\.")[0];
                int identifier = resources.getIdentifier(str2, "raw", context.getPackageName());
                if (identifier > 0) {
                    try {
                        inputStreamOpenRawResource = resources.openRawResource(identifier);
                    } catch (Resources.NotFoundException unused) {
                        Log.w(TAG, "Inputstream failed to open for R.raw." + str2 + ", try to find it in assets");
                    }
                }
            } catch (Throwable th) {
                if (inputStreamOpenRawResource != null) {
                    inputStreamOpenRawResource.close();
                }
                throw th;
            }
        }
        if (inputStreamOpenRawResource == null) {
            inputStreamOpenRawResource = context.getAssets().open(str);
        }
        byte[] bArr = new byte[inputStreamOpenRawResource.available()];
        inputStreamOpenRawResource.read(bArr);
        String str3 = new String(bArr);
        if (inputStreamOpenRawResource != null) {
            inputStreamOpenRawResource.close();
        }
        return str3;
    }

    private void createExternalExtension(String str, String str2, String str3, XWalkExtensionContextClient xWalkExtensionContextClient) {
        try {
            xWalkExtensionContextClient.getContext().getClassLoader().loadClass(str2).getConstructor(String.class, String.class, XWalkExtensionContextClient.class).newInstance(str, str3, this);
        } catch (ClassNotFoundException e) {
            handleException(e);
        } catch (IllegalAccessException e2) {
            handleException(e2);
        } catch (InstantiationException e3) {
            handleException(e3);
        } catch (NoSuchMethodException e4) {
            handleException(e4);
        } catch (InvocationTargetException e5) {
            handleException(e5);
        }
    }

    private void loadNativeExtensions() {
        String str;
        try {
            str = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), 0).nativeLibraryDir;
        } catch (PackageManager.NameNotFoundException unused) {
            str = null;
        }
        if (str == null || !new File(str).isDirectory()) {
            return;
        }
        this.mNativeExtensionLoader.registerNativeExtensionsInPath(str);
    }

    private static void handleException(Exception exc) {
        Log.e(TAG, "Error in calling methods of external extensions. " + exc.toString());
        exc.printStackTrace();
    }
}
