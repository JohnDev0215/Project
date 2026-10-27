package defpackage;

import android.net.Uri;
import android.webkit.ValueCallback;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: Ib, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0145Ib implements ValueCallback {
    public boolean a;
    public final /* synthetic */ Callback b;

    public C0145Ib(C0252Ob c0252Ob, Callback callback) {
        this.b = callback;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.webkit.ValueCallback
    public void onReceiveValue(Object obj) {
        Uri uri = (Uri) obj;
        if (this.a) {
            throw new IllegalStateException("showFileChooser result was already called");
        }
        this.a = true;
        this.b.a(uri == null ? null : new String[]{uri.toString()});
    }
}
