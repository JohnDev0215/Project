package com.android.internal.os;

import android.app.LoadedApk;
import android.app.ZygotePreload;
import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.net.LocalSocket;
import android.util.Log;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
class AppZygoteInit {
    public static final String TAG = "AppZygoteInit";
    private static ZygoteServer sServer;

    AppZygoteInit() {
    }

    private static class AppZygoteServer extends ZygoteServer {
        private AppZygoteServer() {
        }

        @Override // com.android.internal.os.ZygoteServer
        protected ZygoteConnection createNewConnection(LocalSocket localSocket, String str) throws IOException {
            return new AppZygoteConnection(localSocket, str);
        }
    }

    private static class AppZygoteConnection extends ZygoteConnection {
        @Override // com.android.internal.os.ZygoteConnection
        protected boolean canPreloadApp() {
            return true;
        }

        @Override // com.android.internal.os.ZygoteConnection
        protected boolean isPreloadComplete() {
            return true;
        }

        @Override // com.android.internal.os.ZygoteConnection
        protected void preload() {
        }

        AppZygoteConnection(LocalSocket localSocket, String str) throws IOException {
            super(localSocket, str);
        }

        @Override // com.android.internal.os.ZygoteConnection
        protected void handlePreloadApp(ApplicationInfo applicationInfo) {
            Log.i(AppZygoteInit.TAG, "Beginning application preload for " + applicationInfo.packageName);
            ClassLoader classLoader = new LoadedApk(null, applicationInfo, null, null, false, true, false).getClassLoader();
            Zygote.allowAppFilesAcrossFork(applicationInfo);
            int i = 1;
            if (applicationInfo.zygotePreloadName != null) {
                try {
                    ComponentName componentNameCreateRelative = ComponentName.createRelative(applicationInfo.packageName, applicationInfo.zygotePreloadName);
                    Class<?> cls = Class.forName(componentNameCreateRelative.getClassName(), true, classLoader);
                    if (!ZygotePreload.class.isAssignableFrom(cls)) {
                        Log.e(AppZygoteInit.TAG, componentNameCreateRelative.getClassName() + " does not implement " + ZygotePreload.class.getName());
                    } else {
                        ((ZygotePreload) cls.getConstructor(new Class[0]).newInstance(new Object[0])).doPreload(applicationInfo);
                    }
                } catch (ReflectiveOperationException e) {
                    Log.e(AppZygoteInit.TAG, "AppZygote application preload failed for " + applicationInfo.zygotePreloadName, e);
                }
            } else {
                Log.i(AppZygoteInit.TAG, "No zygotePreloadName attribute specified.");
            }
            try {
                DataOutputStream socketOutputStream = getSocketOutputStream();
                if (classLoader == null) {
                    i = 0;
                }
                socketOutputStream.writeInt(i);
                Log.i(AppZygoteInit.TAG, "Application preload done");
            } catch (IOException e2) {
                throw new IllegalStateException("Error writing to command socket", e2);
            }
        }
    }

    public static void main(String[] strArr) {
        ChildZygoteInit.runZygoteServer(new AppZygoteServer(), strArr);
    }
}
