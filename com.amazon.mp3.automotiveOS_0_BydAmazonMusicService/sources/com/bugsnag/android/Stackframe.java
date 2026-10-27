package com.bugsnag.android;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.internal.JsonHelper;
import java.io.IOException;
import java.util.Map;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Stackframe.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0004\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001BW\b\u0011\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\u0018\b\u0002\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u001e\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\bA\u0010BB\u0011\b\u0016\u0012\u0006\u0010D\u001a\u00020C¢\u0006\u0004\bA\u0010EB\u001f\b\u0010\u0012\u0014\u0010G\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010F0\u001e¢\u0006\u0004\bA\u0010$J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R$\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\b\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR$\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR2\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010\u0012\u001a\u0004\b&\u0010\u0014\"\u0004\b'\u0010\u0016R$\u0010)\u001a\u0004\u0018\u00010(8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R$\u0010/\u001a\u0004\u0018\u00010(8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u0010*\u001a\u0004\b0\u0010,\"\u0004\b1\u0010.R$\u00102\u001a\u0004\u0018\u00010(8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u0010*\u001a\u0004\b3\u0010,\"\u0004\b4\u0010.R$\u00105\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u0010\b\u001a\u0004\b6\u0010\n\"\u0004\b7\u0010\fR$\u00108\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b8\u0010\u0019\u001a\u0004\b8\u0010\u001b\"\u0004\b9\u0010\u001dR$\u0010;\u001a\u0004\u0018\u00010:8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@¨\u0006H"}, d2 = {"Lcom/bugsnag/android/Stackframe;", "Lcom/bugsnag/android/JsonStream$Streamable;", "Lcom/bugsnag/android/JsonStream;", "writer", "", "toStream", "", "method", "Ljava/lang/String;", "getMethod", "()Ljava/lang/String;", "setMethod", "(Ljava/lang/String;)V", "file", "getFile", "setFile", "", "lineNumber", "Ljava/lang/Number;", "getLineNumber", "()Ljava/lang/Number;", "setLineNumber", "(Ljava/lang/Number;)V", "", "inProject", "Ljava/lang/Boolean;", "getInProject", "()Ljava/lang/Boolean;", "setInProject", "(Ljava/lang/Boolean;)V", "", "code", "Ljava/util/Map;", "getCode", "()Ljava/util/Map;", "setCode", "(Ljava/util/Map;)V", "columnNumber", "getColumnNumber", "setColumnNumber", "", "frameAddress", "Ljava/lang/Long;", "getFrameAddress", "()Ljava/lang/Long;", "setFrameAddress", "(Ljava/lang/Long;)V", "symbolAddress", "getSymbolAddress", "setSymbolAddress", "loadAddress", "getLoadAddress", "setLoadAddress", "codeIdentifier", "getCodeIdentifier", "setCodeIdentifier", "isPC", "setPC", "Lcom/bugsnag/android/ErrorType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/bugsnag/android/ErrorType;", "getType", "()Lcom/bugsnag/android/ErrorType;", "setType", "(Lcom/bugsnag/android/ErrorType;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Number;Ljava/lang/Boolean;Ljava/util/Map;Ljava/lang/Number;)V", "Lcom/bugsnag/android/NativeStackframe;", "nativeFrame", "(Lcom/bugsnag/android/NativeStackframe;)V", "", "json", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class Stackframe implements JsonStream.Streamable {
    private Map<String, String> code;
    private String codeIdentifier;
    private Number columnNumber;
    private String file;
    private Long frameAddress;
    private Boolean inProject;
    private Boolean isPC;
    private Number lineNumber;
    private Long loadAddress;
    private String method;
    private Long symbolAddress;
    private ErrorType type;

    public final ErrorType getType() {
        return this.type;
    }

    public final void setType(ErrorType errorType) {
        this.type = errorType;
    }

    public /* synthetic */ Stackframe(String str, String str2, Number number, Boolean bool, Map map, Number number2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, number, bool, (i & 16) != 0 ? null : map, (i & 32) != 0 ? null : number2);
    }

    @JvmOverloads
    public Stackframe(String str, String str2, Number number, Boolean bool, Map<String, String> map, Number number2) {
        this.method = str;
        this.file = str2;
        this.lineNumber = number;
        this.inProject = bool;
        this.code = map;
        this.columnNumber = number2;
    }

    public Stackframe(NativeStackframe nativeStackframe) {
        this(nativeStackframe.getMethod(), nativeStackframe.getFile(), nativeStackframe.getLineNumber(), null, null, null, 32, null);
        this.frameAddress = nativeStackframe.getFrameAddress();
        this.symbolAddress = nativeStackframe.getSymbolAddress();
        this.loadAddress = nativeStackframe.getLoadAddress();
        this.codeIdentifier = nativeStackframe.getCodeIdentifier();
        this.isPC = nativeStackframe.getIsPC();
        this.type = nativeStackframe.getType();
    }

    public Stackframe(Map<String, ? extends Object> map) {
        Object obj = map.get("method");
        this.method = (String) (obj instanceof String ? obj : null);
        Object obj2 = map.get("file");
        this.file = (String) (obj2 instanceof String ? obj2 : null);
        JsonHelper jsonHelper = JsonHelper.INSTANCE;
        this.lineNumber = jsonHelper.jsonToLong(map.get("lineNumber"));
        Object obj3 = map.get("inProject");
        this.inProject = (Boolean) (obj3 instanceof Boolean ? obj3 : null);
        Object obj4 = map.get("columnNumber");
        this.columnNumber = (Number) (obj4 instanceof Number ? obj4 : null);
        this.frameAddress = jsonHelper.jsonToLong(map.get("frameAddress"));
        this.symbolAddress = jsonHelper.jsonToLong(map.get("symbolAddress"));
        this.loadAddress = jsonHelper.jsonToLong(map.get("loadAddress"));
        Object obj5 = map.get("codeIdentifier");
        this.codeIdentifier = (String) (obj5 instanceof String ? obj5 : null);
        Object obj6 = map.get("isPC");
        this.isPC = (Boolean) (obj6 instanceof Boolean ? obj6 : null);
        Object obj7 = map.get("code");
        this.code = (Map) (obj7 instanceof Map ? obj7 : null);
        Object obj8 = map.get(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        String str = (String) (obj8 instanceof String ? obj8 : null);
        this.type = str != null ? ErrorType.INSTANCE.fromDescriptor(str) : null;
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(JsonStream writer) throws IOException {
        writer.beginObject();
        writer.name("method").value(this.method);
        writer.name("file").value(this.file);
        writer.name("lineNumber").value(this.lineNumber);
        Boolean bool = this.inProject;
        if (bool != null) {
            writer.name("inProject").value(bool.booleanValue());
        }
        writer.name("columnNumber").value(this.columnNumber);
        Long l = this.frameAddress;
        if (l != null) {
            l.longValue();
            writer.name("frameAddress").value(JsonHelper.INSTANCE.ulongToHex(this.frameAddress));
        }
        Long l2 = this.symbolAddress;
        if (l2 != null) {
            l2.longValue();
            writer.name("symbolAddress").value(JsonHelper.INSTANCE.ulongToHex(this.symbolAddress));
        }
        Long l3 = this.loadAddress;
        if (l3 != null) {
            l3.longValue();
            writer.name("loadAddress").value(JsonHelper.INSTANCE.ulongToHex(this.loadAddress));
        }
        String str = this.codeIdentifier;
        if (str != null) {
            writer.name("codeIdentifier").value(str);
        }
        Boolean bool2 = this.isPC;
        if (bool2 != null) {
            writer.name("isPC").value(bool2.booleanValue());
        }
        ErrorType errorType = this.type;
        if (errorType != null) {
            writer.name(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE).value(errorType.getDesc());
        }
        Map<String, String> map = this.code;
        if (map != null) {
            writer.name("code");
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.beginObject();
                writer.name(entry.getKey());
                writer.value(entry.getValue());
                writer.endObject();
            }
        }
        writer.endObject();
    }
}
