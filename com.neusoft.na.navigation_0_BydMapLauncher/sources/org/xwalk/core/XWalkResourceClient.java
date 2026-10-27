package org.xwalk.core;

import android.net.http.SslError;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkResourceClient {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ERROR_AUTHENTICATION = -4;
    public static final int ERROR_BAD_URL = -12;
    public static final int ERROR_CONNECT = -6;
    public static final int ERROR_FAILED_SSL_HANDSHAKE = -11;
    public static final int ERROR_FILE = -13;
    public static final int ERROR_FILE_NOT_FOUND = -14;
    public static final int ERROR_HOST_LOOKUP = -2;
    public static final int ERROR_IO = -7;
    public static final int ERROR_OK = 0;
    public static final int ERROR_PROXY_AUTHENTICATION = -5;
    public static final int ERROR_REDIRECT_LOOP = -9;
    public static final int ERROR_TIMEOUT = -8;
    public static final int ERROR_TOO_MANY_REQUESTS = -15;
    public static final int ERROR_UNKNOWN = -1;
    public static final int ERROR_UNSUPPORTED_AUTH_SCHEME = -3;
    public static final int ERROR_UNSUPPORTED_SCHEME = -10;
    private Object bridge;
    private ArrayList<Object> constructorParams;
    private ArrayList<Object> constructorTypes;
    private XWalkCoreWrapper coreWrapper;
    private ReflectMethod postWrapperMethod;
    private ReflectMethod onDocumentLoadedInFrameXWalkViewInternallongMethod = new ReflectMethod((Class<?>) null, "onDocumentLoadedInFrame", (Class<?>[]) new Class[0]);
    private ReflectMethod onLoadStartedXWalkViewInternalStringMethod = new ReflectMethod((Class<?>) null, "onLoadStarted", (Class<?>[]) new Class[0]);
    private ReflectMethod onLoadFinishedXWalkViewInternalStringMethod = new ReflectMethod((Class<?>) null, "onLoadFinished", (Class<?>[]) new Class[0]);
    private ReflectMethod onProgressChangedXWalkViewInternalintMethod = new ReflectMethod((Class<?>) null, "onProgressChanged", (Class<?>[]) new Class[0]);
    private ReflectMethod shouldInterceptLoadRequestXWalkViewInternalStringMethod = new ReflectMethod((Class<?>) null, "shouldInterceptLoadRequest", (Class<?>[]) new Class[0]);
    private ReflectMethod shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod = new ReflectMethod((Class<?>) null, "shouldInterceptLoadRequest", (Class<?>[]) new Class[0]);
    private ReflectMethod onReceivedLoadErrorXWalkViewInternalintStringStringMethod = new ReflectMethod((Class<?>) null, "onReceivedLoadError", (Class<?>[]) new Class[0]);
    private ReflectMethod shouldOverrideUrlLoadingXWalkViewInternalStringMethod = new ReflectMethod((Class<?>) null, "shouldOverrideUrlLoading", (Class<?>[]) new Class[0]);
    private ReflectMethod onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod = new ReflectMethod((Class<?>) null, "onReceivedSslError", (Class<?>[]) new Class[0]);
    private ReflectMethod onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod = new ReflectMethod((Class<?>) null, "onReceivedClientCertRequest", (Class<?>[]) new Class[0]);
    private ReflectMethod onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod = new ReflectMethod((Class<?>) null, "onReceivedResponseHeaders", (Class<?>[]) new Class[0]);
    private ReflectMethod doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod = new ReflectMethod((Class<?>) null, "doUpdateVisitedHistory", (Class<?>[]) new Class[0]);
    private ReflectMethod onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod = new ReflectMethod((Class<?>) null, "onReceivedHttpAuthRequest", (Class<?>[]) new Class[0]);
    private ReflectMethod createXWalkWebResourceResponseStringStringInputStreamMethod = new ReflectMethod((Class<?>) null, "createXWalkWebResourceResponse", (Class<?>[]) new Class[0]);
    private ReflectMethod createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod = new ReflectMethod((Class<?>) null, "createXWalkWebResourceResponse", (Class<?>[]) new Class[0]);

    protected Object getBridge() {
        return this.bridge;
    }

    public XWalkResourceClient(XWalkView xWalkView) {
        ArrayList<Object> arrayList = new ArrayList<>();
        this.constructorTypes = arrayList;
        arrayList.add("XWalkViewBridge");
        ArrayList<Object> arrayList2 = new ArrayList<>();
        this.constructorParams = arrayList2;
        arrayList2.add(xWalkView);
        reflectionInit();
    }

    public void onDocumentLoadedInFrame(XWalkView xWalkView, long j) {
        try {
            this.onDocumentLoadedInFrameXWalkViewInternallongMethod.invoke(xWalkView.getBridge(), Long.valueOf(j));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onLoadStarted(XWalkView xWalkView, String str) {
        try {
            this.onLoadStartedXWalkViewInternalStringMethod.invoke(xWalkView.getBridge(), str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onLoadFinished(XWalkView xWalkView, String str) {
        try {
            this.onLoadFinishedXWalkViewInternalStringMethod.invoke(xWalkView.getBridge(), str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onProgressChanged(XWalkView xWalkView, int i) {
        try {
            this.onProgressChangedXWalkViewInternalintMethod.invoke(xWalkView.getBridge(), Integer.valueOf(i));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public WebResourceResponse shouldInterceptLoadRequest(XWalkView xWalkView, String str) {
        try {
            return (WebResourceResponse) this.shouldInterceptLoadRequestXWalkViewInternalStringMethod.invoke(xWalkView.getBridge(), str);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public XWalkWebResourceResponse shouldInterceptLoadRequest(XWalkView xWalkView, XWalkWebResourceRequest xWalkWebResourceRequest) {
        try {
            return (XWalkWebResourceResponse) this.coreWrapper.getWrapperObject(this.shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod.invoke(xWalkView.getBridge(), ((XWalkWebResourceRequestHandler) xWalkWebResourceRequest).getBridge()));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public void onReceivedLoadError(XWalkView xWalkView, int i, String str, String str2) {
        try {
            this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod.invoke(xWalkView.getBridge(), Integer.valueOf(i), str, str2);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public boolean shouldOverrideUrlLoading(XWalkView xWalkView, String str) {
        try {
            return ((Boolean) this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod.invoke(xWalkView.getBridge(), str)).booleanValue();
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return false;
        }
    }

    public void onReceivedSslError(XWalkView xWalkView, ValueCallback<Boolean> valueCallback, SslError sslError) {
        try {
            this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod.invoke(xWalkView.getBridge(), valueCallback, sslError);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onReceivedClientCertRequest(XWalkView xWalkView, ClientCertRequest clientCertRequest) {
        try {
            this.onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod.invoke(xWalkView.getBridge(), ((ClientCertRequestHandler) clientCertRequest).getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onReceivedResponseHeaders(XWalkView xWalkView, XWalkWebResourceRequest xWalkWebResourceRequest, XWalkWebResourceResponse xWalkWebResourceResponse) {
        try {
            this.onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod.invoke(xWalkView.getBridge(), ((XWalkWebResourceRequestHandler) xWalkWebResourceRequest).getBridge(), xWalkWebResourceResponse.getBridge());
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void doUpdateVisitedHistory(XWalkView xWalkView, String str, boolean z) {
        try {
            this.doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod.invoke(xWalkView.getBridge(), str, Boolean.valueOf(z));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public void onReceivedHttpAuthRequest(XWalkView xWalkView, XWalkHttpAuthHandler xWalkHttpAuthHandler, String str, String str2) {
        try {
            this.onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod.invoke(xWalkView.getBridge(), xWalkHttpAuthHandler.getBridge(), str, str2);
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
        }
    }

    public XWalkWebResourceResponse createXWalkWebResourceResponse(String str, String str2, InputStream inputStream) {
        try {
            return (XWalkWebResourceResponse) this.coreWrapper.getWrapperObject(this.createXWalkWebResourceResponseStringStringInputStreamMethod.invoke(str, str2, inputStream));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
        }
    }

    public XWalkWebResourceResponse createXWalkWebResourceResponse(String str, String str2, InputStream inputStream, int i, String str3, Map<String, String> map) {
        try {
            return (XWalkWebResourceResponse) this.coreWrapper.getWrapperObject(this.createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod.invoke(str, str2, inputStream, Integer.valueOf(i), str3, map));
        } catch (UnsupportedOperationException e) {
            if (this.coreWrapper == null) {
                throw new RuntimeException("Crosswalk's APIs are not ready yet");
            }
            XWalkCoreWrapper.handleRuntimeError(e);
            return null;
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
            this.bridge = new ReflectConstructor(this.coreWrapper.getBridgeClass("XWalkResourceClientBridge"), clsArr).newInstance(this.constructorParams.toArray());
            ReflectMethod reflectMethod = this.postWrapperMethod;
            if (reflectMethod != null) {
                reflectMethod.invoke(new Object[0]);
            }
            this.onDocumentLoadedInFrameXWalkViewInternallongMethod.init(this.bridge, null, "onDocumentLoadedInFrameSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), Long.TYPE);
            this.onLoadStartedXWalkViewInternalStringMethod.init(this.bridge, null, "onLoadStartedSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class);
            this.onLoadFinishedXWalkViewInternalStringMethod.init(this.bridge, null, "onLoadFinishedSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class);
            this.onProgressChangedXWalkViewInternalintMethod.init(this.bridge, null, "onProgressChangedSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), Integer.TYPE);
            this.shouldInterceptLoadRequestXWalkViewInternalStringMethod.init(this.bridge, null, "shouldInterceptLoadRequestSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class);
            this.shouldInterceptLoadRequestXWalkViewInternalXWalkWebResourceRequestInternalMethod.init(this.bridge, null, "shouldInterceptLoadRequestSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), this.coreWrapper.getBridgeClass("XWalkWebResourceRequestHandlerBridge"));
            this.onReceivedLoadErrorXWalkViewInternalintStringStringMethod.init(this.bridge, null, "onReceivedLoadErrorSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), Integer.TYPE, String.class, String.class);
            this.shouldOverrideUrlLoadingXWalkViewInternalStringMethod.init(this.bridge, null, "shouldOverrideUrlLoadingSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class);
            this.onReceivedSslErrorXWalkViewInternalValueCallbackSslErrorMethod.init(this.bridge, null, "onReceivedSslErrorSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), ValueCallback.class, SslError.class);
            this.onReceivedClientCertRequestXWalkViewInternalClientCertRequestInternalMethod.init(this.bridge, null, "onReceivedClientCertRequestSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), this.coreWrapper.getBridgeClass("ClientCertRequestHandlerBridge"));
            this.onReceivedResponseHeadersXWalkViewInternalXWalkWebResourceRequestInternalXWalkWebResourceResponseInternalMethod.init(this.bridge, null, "onReceivedResponseHeadersSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), this.coreWrapper.getBridgeClass("XWalkWebResourceRequestHandlerBridge"), this.coreWrapper.getBridgeClass("XWalkWebResourceResponseBridge"));
            this.doUpdateVisitedHistoryXWalkViewInternalStringbooleanMethod.init(this.bridge, null, "doUpdateVisitedHistorySuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), String.class, Boolean.TYPE);
            this.onReceivedHttpAuthRequestXWalkViewInternalXWalkHttpAuthHandlerInternalStringStringMethod.init(this.bridge, null, "onReceivedHttpAuthRequestSuper", this.coreWrapper.getBridgeClass("XWalkViewBridge"), this.coreWrapper.getBridgeClass("XWalkHttpAuthHandlerBridge"), String.class, String.class);
            this.createXWalkWebResourceResponseStringStringInputStreamMethod.init(this.bridge, null, "createXWalkWebResourceResponseSuper", String.class, String.class, InputStream.class);
            this.createXWalkWebResourceResponseStringStringInputStreamintStringMapMethod.init(this.bridge, null, "createXWalkWebResourceResponseSuper", String.class, String.class, InputStream.class, Integer.TYPE, String.class, Map.class);
        } catch (UnsupportedOperationException unused) {
        }
    }
}
