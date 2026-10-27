package com.neusoft.na.nodejs;

/* JADX INFO: loaded from: classes2.dex */
public class JsContext {
    private final NodeInstance mInstance;

    protected JsContext(NodeInstance nodeInstance) {
        this.mInstance = nodeInstance;
    }

    public NodeInstance getInstance() {
        return this.mInstance;
    }

    public Object exec(String str) {
        return this.mInstance.exec(str);
    }

    public Object call(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str).append("(");
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            Json.append(sb, objArr[i]);
        }
        sb.append(");");
        return this.mInstance.exec(sb.toString());
    }

    public void set(String str, JsCallback jsCallback) {
        this.mInstance.set(str, jsCallback);
    }
}
