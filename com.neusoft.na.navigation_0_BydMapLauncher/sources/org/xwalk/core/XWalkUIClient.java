package org.xwalk.core;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkUIClient {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod enumJavascriptMessageTypeClassValueOfMethod = new ReflectMethod();
    private ReflectMethod enumConsoleMessageTypeClassValueOfMethod = new ReflectMethod();
    private ReflectMethod enumInitiateByClassValueOfMethod = new ReflectMethod();
    private ReflectMethod enumLoadStatusClassValueOfMethod = new ReflectMethod();
    private ReflectMethod onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod = new ReflectMethod((Class<?>) null, "onCreateWindowRequested", (Class<?>[]) new Class[0]);
    private ReflectMethod onIconAvailableXWalkViewInternalStringMessageMethod = new ReflectMethod((Class<?>) null, "onIconAvailable", (Class<?>[]) new Class[0]);
    private ReflectMethod onReceivedIconXWalkViewInternalStringBitmapMethod = new ReflectMethod((Class<?>) null, "onReceivedIcon", (Class<?>[]) new Class[0]);
    private ReflectMethod onRequestFocusXWalkViewInternalMethod = new ReflectMethod((Class<?>) null, "onRequestFocus", (Class<?>[]) new Class[0]);
    private ReflectMethod onJavascriptCloseWindowXWalkViewInternalMethod = new ReflectMethod((Class<?>) null, "onJavascriptCloseWindow", (Class<?>[]) new Class[0]);
    private ReflectMethod onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod = new ReflectMethod((Class<?>) null, "onJavascriptModalDialog", (Class<?>[]) new Class[0]);
    private ReflectMethod onFullscreenToggledXWalkViewInternalbooleanMethod = new ReflectMethod((Class<?>) null, "onFullscreenToggled", (Class<?>[]) new Class[0]);
    private ReflectMethod openFileChooserXWalkViewInternalValueCallbackStringStringMethod = new ReflectMethod((Class<?>) null, "openFileChooser", (Class<?>[]) new Class[0]);
    private ReflectMethod onScaleChangedXWalkViewInternalfloatfloatMethod = new ReflectMethod((Class<?>) null, "onScaleChanged", (Class<?>[]) new Class[0]);
    private ReflectMethod shouldOverrideKeyEventXWalkViewInternalKeyEventMethod = new ReflectMethod((Class<?>) null, "shouldOverrideKeyEvent", (Class<?>[]) new Class[0]);
    private ReflectMethod onUnhandledKeyEventXWalkViewInternalKeyEventMethod = new ReflectMethod((Class<?>) null, "onUnhandledKeyEvent", (Class<?>[]) new Class[0]);
    private ReflectMethod onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod = new ReflectMethod((Class<?>) null, "onConsoleMessage", (Class<?>[]) new Class[0]);
    private ReflectMethod onReceivedTitleXWalkViewInternalStringMethod = new ReflectMethod((Class<?>) null, "onReceivedTitle", (Class<?>[]) new Class[0]);
    private ReflectMethod onPageLoadStartedXWalkViewInternalStringMethod = new ReflectMethod((Class<?>) null, "onPageLoadStarted", (Class<?>[]) new Class[0]);
    private ReflectMethod onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod = new ReflectMethod((Class<?>) null, "onPageLoadStopped", (Class<?>[]) new Class[0]);
    private ReflectMethod onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod = new ReflectMethod((Class<?>) null, "onJsAlert", (Class<?>[]) new Class[0]);
    private ReflectMethod onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod = new ReflectMethod((Class<?>) null, "onJsConfirm", (Class<?>[]) new Class[0]);
    private ReflectMethod onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod = new ReflectMethod((Class<?>) null, "onJsPrompt", (Class<?>[]) new Class[0]);
    private ReflectMethod onShowCustomViewViewCustomViewCallbackInternalMethod = new ReflectMethod((Class<?>) null, "onShowCustomView", (Class<?>[]) new Class[0]);
    private ReflectMethod onShowCustomViewViewintCustomViewCallbackInternalMethod = new ReflectMethod((Class<?>) null, "onShowCustomView", (Class<?>[]) new Class[0]);
    private ReflectMethod onHideCustomViewMethod = new ReflectMethod((Class<?>) null, "onHideCustomView", (Class<?>[]) new Class[0]);

    public enum ConsoleMessageType {
        DEBUG,
        ERROR,
        LOG,
        INFO,
        WARNING
    }

    public enum InitiateBy {
        BY_USER_GESTURE,
        BY_JAVASCRIPT
    }

    public enum JavascriptMessageType {
        JAVASCRIPT_ALERT,
        JAVASCRIPT_CONFIRM,
        JAVASCRIPT_PROMPT,
        JAVASCRIPT_BEFOREUNLOAD
    }

    public enum LoadStatus {
        FINISHED,
        FAILED,
        CANCELLED
    }

    private Object ConvertJavascriptMessageType(JavascriptMessageType javascriptMessageType) {
        return this.enumJavascriptMessageTypeClassValueOfMethod.invoke(javascriptMessageType.toString());
    }

    private Object ConvertConsoleMessageType(ConsoleMessageType consoleMessageType) {
        return this.enumConsoleMessageTypeClassValueOfMethod.invoke(consoleMessageType.toString());
    }

    private Object ConvertInitiateBy(InitiateBy initiateBy) {
        return this.enumInitiateByClassValueOfMethod.invoke(initiateBy.toString());
    }

    private Object ConvertLoadStatus(LoadStatus loadStatus) {
        return this.enumLoadStatusClassValueOfMethod.invoke(loadStatus.toString());
    }

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkUIClient(XWalkView xWalkView) {
        ArrayList<Object> arrayList = new ArrayList<>();
        this.constructorTypes = arrayList;
        arrayList.add("XWalkViewBridge");
        ArrayList<Object> arrayList2 = new ArrayList<>();
        this.constructorParams = arrayList2;
        arrayList2.add(xWalkView);
        reflectionInit();
    }

    public boolean onCreateWindowRequested(XWalkView xWalkView, InitiateBy initiateBy, ValueCallback<XWalkView> valueCallback) {
        try {
            return ((Boolean) this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod.invoke(xWalkView.getBridge(), ConvertInitiateBy(initiateBy), valueCallback)).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void onIconAvailable(XWalkView xWalkView, String str, Message message) {
        try {
            this.onIconAvailableXWalkViewInternalStringMessageMethod.invoke(xWalkView.getBridge(), str, message);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onReceivedIcon(XWalkView xWalkView, String str, Bitmap bitmap) {
        try {
            this.onReceivedIconXWalkViewInternalStringBitmapMethod.invoke(xWalkView.getBridge(), str, bitmap);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onRequestFocus(XWalkView xWalkView) {
        try {
            this.onRequestFocusXWalkViewInternalMethod.invoke(xWalkView.getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onJavascriptCloseWindow(XWalkView xWalkView) {
        try {
            this.onJavascriptCloseWindowXWalkViewInternalMethod.invoke(xWalkView.getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean onJavascriptModalDialog(XWalkView xWalkView, JavascriptMessageType javascriptMessageType, String str, String str2, String str3, XWalkJavascriptResult xWalkJavascriptResult) {
        try {
            return ((Boolean) this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod.invoke(xWalkView.getBridge(), ConvertJavascriptMessageType(javascriptMessageType), str, str2, str3, ((XWalkJavascriptResultHandler) xWalkJavascriptResult).getBridge())).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void onFullscreenToggled(XWalkView xWalkView, boolean z) {
        try {
            this.onFullscreenToggledXWalkViewInternalbooleanMethod.invoke(xWalkView.getBridge(), Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void openFileChooser(XWalkView xWalkView, ValueCallback<Uri> valueCallback, String str, String str2) {
        try {
            this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod.invoke(xWalkView.getBridge(), valueCallback, str, str2);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onScaleChanged(XWalkView xWalkView, float f, float f2) {
        try {
            this.onScaleChangedXWalkViewInternalfloatfloatMethod.invoke(xWalkView.getBridge(), Float.valueOf(f), Float.valueOf(f2));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean shouldOverrideKeyEvent(XWalkView xWalkView, KeyEvent keyEvent) {
        try {
            return ((Boolean) this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod.invoke(xWalkView.getBridge(), keyEvent)).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void onUnhandledKeyEvent(XWalkView xWalkView, KeyEvent keyEvent) {
        try {
            this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod.invoke(xWalkView.getBridge(), keyEvent);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean onConsoleMessage(XWalkView xWalkView, String str, int i, String str2, ConsoleMessageType consoleMessageType) {
        try {
            return ((Boolean) this.onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod.invoke(xWalkView.getBridge(), str, Integer.valueOf(i), str2, ConvertConsoleMessageType(consoleMessageType))).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void onReceivedTitle(XWalkView xWalkView, String str) {
        try {
            this.onReceivedTitleXWalkViewInternalStringMethod.invoke(xWalkView.getBridge(), str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onPageLoadStarted(XWalkView xWalkView, String str) {
        try {
            this.onPageLoadStartedXWalkViewInternalStringMethod.invoke(xWalkView.getBridge(), str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onPageLoadStopped(XWalkView xWalkView, String str, LoadStatus loadStatus) {
        try {
            this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod.invoke(xWalkView.getBridge(), str, ConvertLoadStatus(loadStatus));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean onJsAlert(XWalkView xWalkView, String str, String str2, XWalkJavascriptResult xWalkJavascriptResult) {
        try {
            return ((Boolean) this.onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod.invoke(xWalkView.getBridge(), str, str2, ((XWalkJavascriptResultHandler) xWalkJavascriptResult).getBridge())).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean onJsConfirm(XWalkView xWalkView, String str, String str2, XWalkJavascriptResult xWalkJavascriptResult) {
        try {
            return ((Boolean) this.onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod.invoke(xWalkView.getBridge(), str, str2, ((XWalkJavascriptResultHandler) xWalkJavascriptResult).getBridge())).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public boolean onJsPrompt(XWalkView xWalkView, String str, String str2, String str3, XWalkJavascriptResult xWalkJavascriptResult) {
        try {
            return ((Boolean) this.onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod.invoke(xWalkView.getBridge(), str, str2, str3, ((XWalkJavascriptResultHandler) xWalkJavascriptResult).getBridge())).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
        try {
            this.onShowCustomViewViewCustomViewCallbackInternalMethod.invoke(view, ((CustomViewCallbackHandler) customViewCallback).getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
        try {
            this.onShowCustomViewViewintCustomViewCallbackInternalMethod.invoke(view, Integer.valueOf(i), ((CustomViewCallbackHandler) customViewCallback).getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onHideCustomView() {
        try {
            this.onHideCustomViewMethod.invoke(new Object[0]);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    void reflectionInit() {
        XWalkCoreWrapper.initEmbeddedMode();
        XWalkCoreWrapper xWalkCoreWrapper = XWalkCoreWrapper.getInstance();
        this.coreWrapper = xWalkCoreWrapper;
        if (xWalkCoreWrapper == null) {
            XWalkCoreWrapper.reserveReflectObject(this);
            return;
        }
        int size = this.constructorTypes.size();
        Class[] clsArr = new Class[size + 1];
        for (int i = 0; i < size; i++) {
            Object obj = this.constructorTypes.get(i);
            if (obj instanceof String) {
                clsArr[i] = this.coreWrapper.getBridgeClass((String) obj);
                ArrayList<Object> arrayList = this.constructorParams;
                arrayList.set(i, this.coreWrapper.getBridgeObject(arrayList.get(i)));
            } else if (obj instanceof Class) {
                clsArr[i] = (Class) obj;
            }
        }
        clsArr[size] = Object.class;
        this.constructorParams.add(this);
        try {
            this.bridge = new ReflectConstructor(this.coreWrapper.getBridgeClass("XWalkUIClientBridge"), clsArr).newInstance(this.constructorParams.toArray());
            ReflectMethod reflectMethod = this.postWrapperMethod;
            if (reflectMethod != null) {
                reflectMethod.invoke(new Object[0]);
            }
            this.enumJavascriptMessageTypeClassValueOfMethod.init(null, this.coreWrapper.getBridgeClass("XWalkUIClientInternal$JavascriptMessageTypeInternal"), "valueOf", String.class);
            this.enumConsoleMessageTypeClassValueOfMethod.init(null, this.coreWrapper.getBridgeClass("XWalkUIClientInternal$ConsoleMessageType"), "valueOf", String.class);
            this.enumInitiateByClassValueOfMethod.init(null, this.coreWrapper.getBridgeClass("XWalkUIClientInternal$InitiateByInternal"), "valueOf", String.class);
            this.enumLoadStatusClassValueOfMethod.init(null, this.coreWrapper.getBridgeClass("XWalkUIClientInternal$LoadStatusInternal"), "valueOf", String.class);
            this.onCreateWindowRequestedXWalkViewInternalInitiateByInternalValueCallbackMethod.init(this.bridge, null, "onCreateWindowRequestedSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), this.coreWrapper.getBridgeClass("XWalkUIClientInternal$InitiateByInternal"), ValueCallback.class);
            this.onIconAvailableXWalkViewInternalStringMessageMethod.init(this.bridge, null, "onIconAvailableSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class, Message.class);
            this.onReceivedIconXWalkViewInternalStringBitmapMethod.init(this.bridge, null, "onReceivedIconSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class, Bitmap.class);
            this.onRequestFocusXWalkViewInternalMethod.init(this.bridge, null, "onRequestFocusSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"));
            this.onJavascriptCloseWindowXWalkViewInternalMethod.init(this.bridge, null, "onJavascriptCloseWindowSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"));
            this.onJavascriptModalDialogXWalkViewInternalJavascriptMessageTypeInternalStringStringStringXWalkJavascriptResultInternalMethod.init(this.bridge, null, "onJavascriptModalDialogSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), this.coreWrapper.getBridgeClass("XWalkUIClientInternal$JavascriptMessageTypeInternal"), String.class, String.class, String.class, this.coreWrapper.getBridgeClass("XWalkJavascriptResultHandlerBridge"));
            this.onFullscreenToggledXWalkViewInternalbooleanMethod.init(this.bridge, null, "onFullscreenToggledSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), Boolean.TYPE);
            this.openFileChooserXWalkViewInternalValueCallbackStringStringMethod.init(this.bridge, null, "openFileChooserSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), ValueCallback.class, String.class, String.class);
            this.onScaleChangedXWalkViewInternalfloatfloatMethod.init(this.bridge, null, "onScaleChangedSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), Float.TYPE, Float.TYPE);
            this.shouldOverrideKeyEventXWalkViewInternalKeyEventMethod.init(this.bridge, null, "shouldOverrideKeyEventSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), KeyEvent.class);
            this.onUnhandledKeyEventXWalkViewInternalKeyEventMethod.init(this.bridge, null, "onUnhandledKeyEventSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), KeyEvent.class);
            this.onConsoleMessageXWalkViewInternalStringintStringConsoleMessageTypeMethod.init(this.bridge, null, "onConsoleMessageSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class, Integer.TYPE, String.class, this.coreWrapper.getBridgeClass("XWalkUIClientInternal$ConsoleMessageType"));
            this.onReceivedTitleXWalkViewInternalStringMethod.init(this.bridge, null, "onReceivedTitleSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class);
            this.onPageLoadStartedXWalkViewInternalStringMethod.init(this.bridge, null, "onPageLoadStartedSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class);
            this.onPageLoadStoppedXWalkViewInternalStringLoadStatusInternalMethod.init(this.bridge, null, "onPageLoadStoppedSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class, this.coreWrapper.getBridgeClass("XWalkUIClientInternal$LoadStatusInternal"));
            this.onJsAlertXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod.init(this.bridge, null, "onJsAlertSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class, String.class, this.coreWrapper.getBridgeClass("XWalkJavascriptResultHandlerBridge"));
            this.onJsConfirmXWalkViewInternalStringStringXWalkJavascriptResultInternalMethod.init(this.bridge, null, "onJsConfirmSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class, String.class, this.coreWrapper.getBridgeClass("XWalkJavascriptResultHandlerBridge"));
            this.onJsPromptXWalkViewInternalStringStringStringXWalkJavascriptResultInternalMethod.init(this.bridge, null, "onJsPromptSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class, String.class, String.class, this.coreWrapper.getBridgeClass("XWalkJavascriptResultHandlerBridge"));
            this.onShowCustomViewViewCustomViewCallbackInternalMethod.init(this.bridge, null, "onShowCustomViewSuper", View.class, this.coreWrapper.getBridgeClass("CustomViewCallbackHandlerBridge"));
            this.onShowCustomViewViewintCustomViewCallbackInternalMethod.init(this.bridge, null, "onShowCustomViewSuper", View.class, Integer.TYPE, this.coreWrapper.getBridgeClass("CustomViewCallbackHandlerBridge"));
            this.onHideCustomViewMethod.init(this.bridge, null, "onHideCustomViewSuper", new Class[0]);
        } catch (UnsupportedOperationException unused) {
        }
    }
}
