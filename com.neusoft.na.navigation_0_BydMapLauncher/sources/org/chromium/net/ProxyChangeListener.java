package org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.base.annotations.NativeClassQualifiedName;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("net")
public class ProxyChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "ProxyChangeListener";
    private static boolean sEnabled = true;
    private Context mContext;
    private Delegate mDelegate;
    private long mNativePtr;
    private ProxyReceiver mProxyReceiver;

    public interface Delegate {
        void proxySettingsChanged();
    }

    @NativeClassQualifiedName("ProxyConfigServiceAndroid::JNIDelegate")
    private native void nativeProxySettingsChanged(long j);

    @NativeClassQualifiedName("ProxyConfigServiceAndroid::JNIDelegate")
    private native void nativeProxySettingsChangedTo(long j, String str, int i, String str2, String[] strArr);

    private static class ProxyConfig {
        public final String[] mExclusionList;
        public final String mHost;
        public final String mPacUrl;
        public final int mPort;

        public ProxyConfig(String str, int i, String str2, String[] strArr) {
            this.mHost = str;
            this.mPort = i;
            this.mPacUrl = str2;
            this.mExclusionList = strArr;
        }
    }

    private ProxyChangeListener(Context context) {
        this.mContext = context;
    }

    public static void setEnabled(boolean z) {
        sEnabled = z;
    }

    public void setDelegateForTesting(Delegate delegate) {
        this.mDelegate = delegate;
    }

    public static ProxyChangeListener create(Context context) {
        return new ProxyChangeListener(context);
    }

    public static String getProperty(String str) {
        return System.getProperty(str);
    }

    public void start(long j) {
        this.mNativePtr = j;
        registerReceiver();
    }

    public void stop() {
        this.mNativePtr = 0L;
        unregisterReceiver();
    }

    private class ProxyReceiver extends BroadcastReceiver {
        private ProxyReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PROXY_CHANGE")) {
                ProxyChangeListener.this.proxySettingsChanged(extractNewProxy(intent));
            }
        }

        private ProxyConfig extractNewProxy(Intent intent) {
            try {
                Object obj = intent.getExtras().get("android.intent.extra.PROXY_INFO");
                if (obj == null) {
                    return null;
                }
                Class<?> cls = Class.forName("android.net.ProxyInfo");
                Method declaredMethod = cls.getDeclaredMethod("getHost", new Class[0]);
                Method declaredMethod2 = cls.getDeclaredMethod("getPort", new Class[0]);
                Method declaredMethod3 = cls.getDeclaredMethod("getExclusionList", new Class[0]);
                String str = (String) declaredMethod.invoke(obj, new Object[0]);
                int iIntValue = ((Integer) declaredMethod2.invoke(obj, new Object[0])).intValue();
                String[] strArr = (String[]) declaredMethod3.invoke(obj, new Object[0]);
                Uri uri = (Uri) cls.getDeclaredMethod("getPacFileUrl", new Class[0]).invoke(obj, new Object[0]);
                if (!Uri.EMPTY.equals(uri)) {
                    return new ProxyConfig(str, iIntValue, uri.toString(), strArr);
                }
                return new ProxyConfig(str, iIntValue, null, strArr);
            } catch (ClassNotFoundException e) {
                Log.e(ProxyChangeListener.TAG, "Using no proxy configuration due to exception:" + e);
                return null;
            } catch (IllegalAccessException e2) {
                Log.e(ProxyChangeListener.TAG, "Using no proxy configuration due to exception:" + e2);
                return null;
            } catch (NoSuchMethodException e3) {
                Log.e(ProxyChangeListener.TAG, "Using no proxy configuration due to exception:" + e3);
                return null;
            } catch (NullPointerException e4) {
                Log.e(ProxyChangeListener.TAG, "Using no proxy configuration due to exception:" + e4);
                return null;
            } catch (InvocationTargetException e5) {
                Log.e(ProxyChangeListener.TAG, "Using no proxy configuration due to exception:" + e5);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void proxySettingsChanged(ProxyConfig proxyConfig) {
        if (sEnabled) {
            Delegate delegate = this.mDelegate;
            if (delegate != null) {
                delegate.proxySettingsChanged();
            }
            long j = this.mNativePtr;
            if (j == 0) {
                return;
            }
            if (proxyConfig != null) {
                nativeProxySettingsChangedTo(j, proxyConfig.mHost, proxyConfig.mPort, proxyConfig.mPacUrl, proxyConfig.mExclusionList);
            } else {
                nativeProxySettingsChanged(j);
            }
        }
    }

    private void registerReceiver() {
        if (this.mProxyReceiver != null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PROXY_CHANGE");
        this.mProxyReceiver = new ProxyReceiver();
        this.mContext.getApplicationContext().registerReceiver(this.mProxyReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        ProxyReceiver proxyReceiver = this.mProxyReceiver;
        if (proxyReceiver == null) {
            return;
        }
        this.mContext.unregisterReceiver(proxyReceiver);
        this.mProxyReceiver = null;
    }
}
