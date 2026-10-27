package org.xwalk.core.extension;

/* JADX INFO: loaded from: classes4.dex */
public class BindingObject {
    private String TAG = "BindingObject";
    protected MessageHandler mHandler = new MessageHandler();
    protected ExtensionInstanceHelper mInstanceHelper;
    protected String mObjectId;

    public void onDestroy() {
    }

    public void onJsBound() {
    }

    public void onJsDestroyed() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public Object handleMessage(MessageInfo messageInfo) {
        return this.mHandler.handleMessage(messageInfo);
    }

    public void initBindingInfo(String str, ExtensionInstanceHelper extensionInstanceHelper) {
        this.mObjectId = str;
        this.mInstanceHelper = extensionInstanceHelper;
    }
}
