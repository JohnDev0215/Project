package com.byd.mvp.ui.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import b.b.m.c.a;
import com.byd.mvp.ui.jsbridge.BridgeWebView;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class BydBridgeWebView extends BridgeWebView {
    public BydBridgeWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static void k() {
        Method declaredMethod;
        int i = Build.VERSION.SDK_INT;
        try {
            Class<?> cls = Class.forName("android.webkit.WebViewFactory");
            Field declaredField = cls.getDeclaredField("sProviderInstance");
            declaredField.setAccessible(true);
            Object objInvoke = declaredField.get(null);
            if (objInvoke != null) {
                a.d("BydBridgeWebView", "sProviderInstance isn't null");
                return;
            }
            if (i > 22) {
                declaredMethod = cls.getDeclaredMethod("getProviderClass", new Class[0]);
            } else {
                if (i != 22) {
                    a.d("BydBridgeWebView", "Don't need to Hook WebView");
                    return;
                }
                declaredMethod = cls.getDeclaredMethod("getFactoryClass", new Class[0]);
            }
            declaredMethod.setAccessible(true);
            Class cls2 = (Class) declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls3 = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            if (i < 26) {
                Constructor constructor = cls2.getConstructor(cls3);
                if (constructor != null) {
                    constructor.setAccessible(true);
                    objInvoke = constructor.newInstance(declaredConstructor.newInstance(new Object[0]));
                }
            } else {
                Field declaredField2 = cls.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
                declaredField2.setAccessible(true);
                String str = (String) declaredField2.get(null);
                if (str == null) {
                    str = "create";
                }
                Method method = cls2.getMethod(str, cls3);
                if (method != null) {
                    objInvoke = method.invoke(null, declaredConstructor.newInstance(new Object[0]));
                }
            }
            if (objInvoke == null) {
                a.d("BydBridgeWebView", "Hook failed!");
            } else {
                declaredField.set("sProviderInstance", objInvoke);
                a.d("BydBridgeWebView", "Hook success!");
            }
        } catch (Throwable th) {
            a.c("BydBridgeWebView", th + "");
        }
    }

    @Override // com.byd.mvp.ui.jsbridge.BridgeWebView
    public void i() {
        loadUrl("javascript:window.native_obj.getSourceCode(document.getElementsByTagName('html')[0].innerHTML)");
    }

    public BydBridgeWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BydBridgeWebView(Context context) {
        super(context);
    }
}
