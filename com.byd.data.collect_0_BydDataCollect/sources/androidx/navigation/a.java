package androidx.navigation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.navigation.q;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: ActivityNavigator.java */
/* JADX INFO: loaded from: classes.dex */
@q.b("activity")
public class a extends q<C0023a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Activity f1644b;

    /* JADX INFO: renamed from: androidx.navigation.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityNavigator.java */
    public static class C0023a extends i {
        private Intent k;
        private String l;

        public C0023a(q<? extends C0023a> qVar) {
            super(qVar);
        }

        public final C0023a A(String str) {
            if (this.k == null) {
                this.k = new Intent();
            }
            this.k.setPackage(str);
            return this;
        }

        @Override // androidx.navigation.i
        public void m(Context context, AttributeSet attributeSet) {
            super.m(context, attributeSet);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, t.ActivityNavigator);
            String string = typedArrayObtainAttributes.getString(t.ActivityNavigator_targetPackage);
            if (string != null) {
                string = string.replace("${applicationId}", context.getPackageName());
            }
            A(string);
            String string2 = typedArrayObtainAttributes.getString(t.ActivityNavigator_android_name);
            if (string2 != null) {
                if (string2.charAt(0) == '.') {
                    string2 = context.getPackageName() + string2;
                }
                x(new ComponentName(context, string2));
            }
            w(typedArrayObtainAttributes.getString(t.ActivityNavigator_action));
            String string3 = typedArrayObtainAttributes.getString(t.ActivityNavigator_data);
            if (string3 != null) {
                y(Uri.parse(string3));
            }
            z(typedArrayObtainAttributes.getString(t.ActivityNavigator_dataPattern));
            typedArrayObtainAttributes.recycle();
        }

        @Override // androidx.navigation.i
        boolean r() {
            return false;
        }

        public final String s() {
            Intent intent = this.k;
            if (intent == null) {
                return null;
            }
            return intent.getAction();
        }

        public final ComponentName t() {
            Intent intent = this.k;
            if (intent == null) {
                return null;
            }
            return intent.getComponent();
        }

        @Override // androidx.navigation.i
        public String toString() {
            ComponentName componentNameT = t();
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            if (componentNameT != null) {
                sb.append(" class=");
                sb.append(componentNameT.getClassName());
            } else {
                String strS = s();
                if (strS != null) {
                    sb.append(" action=");
                    sb.append(strS);
                }
            }
            return sb.toString();
        }

        public final String u() {
            return this.l;
        }

        public final Intent v() {
            return this.k;
        }

        public final C0023a w(String str) {
            if (this.k == null) {
                this.k = new Intent();
            }
            this.k.setAction(str);
            return this;
        }

        public final C0023a x(ComponentName componentName) {
            if (this.k == null) {
                this.k = new Intent();
            }
            this.k.setComponent(componentName);
            return this;
        }

        public final C0023a y(Uri uri) {
            if (this.k == null) {
                this.k = new Intent();
            }
            this.k.setData(uri);
            return this;
        }

        public final C0023a z(String str) {
            this.l = str;
            return this;
        }
    }

    /* JADX INFO: compiled from: ActivityNavigator.java */
    public static final class b implements q.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f1645a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final androidx.core.app.b f1646b;

        public androidx.core.app.b a() {
            return this.f1646b;
        }

        public int b() {
            return this.f1645a;
        }
    }

    public a(Context context) {
        this.f1643a = context;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                this.f1644b = (Activity) context;
                return;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
    }

    @Override // androidx.navigation.q
    public boolean e() {
        Activity activity = this.f1644b;
        if (activity == null) {
            return false;
        }
        activity.finish();
        return true;
    }

    @Override // androidx.navigation.q
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public C0023a a() {
        return new C0023a(this);
    }

    final Context g() {
        return this.f1643a;
    }

    @Override // androidx.navigation.q
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public i b(C0023a c0023a, Bundle bundle, n nVar, q.a aVar) {
        Intent intent;
        int intExtra;
        if (c0023a.v() == null) {
            throw new IllegalStateException("Destination " + c0023a.i() + " does not have an Intent set.");
        }
        Intent intent2 = new Intent(c0023a.v());
        if (bundle != null) {
            intent2.putExtras(bundle);
            String strU = c0023a.u();
            if (!TextUtils.isEmpty(strU)) {
                StringBuffer stringBuffer = new StringBuffer();
                Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(strU);
                while (matcher.find()) {
                    String strGroup = matcher.group(1);
                    if (!bundle.containsKey(strGroup)) {
                        throw new IllegalArgumentException("Could not find " + strGroup + " in " + bundle + " to fill data pattern " + strU);
                    }
                    matcher.appendReplacement(stringBuffer, "");
                    stringBuffer.append(Uri.encode(bundle.get(strGroup).toString()));
                }
                matcher.appendTail(stringBuffer);
                intent2.setData(Uri.parse(stringBuffer.toString()));
            }
        }
        boolean z = aVar instanceof b;
        if (z) {
            intent2.addFlags(((b) aVar).b());
        }
        if (!(this.f1643a instanceof Activity)) {
            intent2.addFlags(268435456);
        }
        if (nVar != null && nVar.g()) {
            intent2.addFlags(536870912);
        }
        Activity activity = this.f1644b;
        if (activity != null && (intent = activity.getIntent()) != null && (intExtra = intent.getIntExtra("android-support-navigation:ActivityNavigator:current", 0)) != 0) {
            intent2.putExtra("android-support-navigation:ActivityNavigator:source", intExtra);
        }
        intent2.putExtra("android-support-navigation:ActivityNavigator:current", c0023a.i());
        Resources resources = g().getResources();
        if (nVar != null) {
            int iC = nVar.c();
            int iD = nVar.d();
            if ((iC <= 0 || !resources.getResourceTypeName(iC).equals("animator")) && (iD <= 0 || !resources.getResourceTypeName(iD).equals("animator"))) {
                intent2.putExtra("android-support-navigation:ActivityNavigator:popEnterAnim", iC);
                intent2.putExtra("android-support-navigation:ActivityNavigator:popExitAnim", iD);
            } else {
                Log.w("ActivityNavigator", "Activity destinations do not support Animator resource. Ignoring popEnter resource " + resources.getResourceName(iC) + " and popExit resource " + resources.getResourceName(iD) + "when launching " + c0023a);
            }
        }
        if (z && ((b) aVar).a() != null) {
            throw null;
        }
        this.f1643a.startActivity(intent2);
        if (nVar != null && this.f1644b != null) {
            int iA = nVar.a();
            int iB = nVar.b();
            if ((iA > 0 && resources.getResourceTypeName(iA).equals("animator")) || (iB > 0 && resources.getResourceTypeName(iB).equals("animator"))) {
                Log.w("ActivityNavigator", "Activity destinations do not support Animator resource. Ignoring enter resource " + resources.getResourceName(iA) + " and exit resource " + resources.getResourceName(iB) + "when launching " + c0023a);
            } else if (iA >= 0 || iB >= 0) {
                this.f1644b.overridePendingTransition(Math.max(iA, 0), Math.max(iB, 0));
            }
        }
        return null;
    }
}
