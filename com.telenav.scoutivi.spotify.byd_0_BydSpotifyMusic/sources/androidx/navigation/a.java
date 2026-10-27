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
import androidx.navigation.r;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: ActivityNavigator.java */
/* JADX INFO: loaded from: classes.dex */
@r.b("activity")
public class a extends r<C0024a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f2629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Activity f2630b;

    /* JADX INFO: renamed from: androidx.navigation.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityNavigator.java */
    public static class C0024a extends j {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private Intent f2631o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private String f2632p;

        public C0024a(r<? extends C0024a> rVar) {
            super(rVar);
        }

        public final C0024a A(ComponentName componentName) {
            if (this.f2631o == null) {
                this.f2631o = new Intent();
            }
            this.f2631o.setComponent(componentName);
            return this;
        }

        public final C0024a B(Uri uri) {
            if (this.f2631o == null) {
                this.f2631o = new Intent();
            }
            this.f2631o.setData(uri);
            return this;
        }

        public final C0024a C(String str) {
            this.f2632p = str;
            return this;
        }

        public final C0024a D(String str) {
            if (this.f2631o == null) {
                this.f2631o = new Intent();
            }
            this.f2631o.setPackage(str);
            return this;
        }

        @Override // androidx.navigation.j
        public void p(Context context, AttributeSet attributeSet) {
            super.p(context, attributeSet);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, u.ActivityNavigator);
            String string = typedArrayObtainAttributes.getString(u.ActivityNavigator_targetPackage);
            if (string != null) {
                string = string.replace("${applicationId}", context.getPackageName());
            }
            D(string);
            String string2 = typedArrayObtainAttributes.getString(u.ActivityNavigator_android_name);
            if (string2 != null) {
                if (string2.charAt(0) == '.') {
                    string2 = context.getPackageName() + string2;
                }
                A(new ComponentName(context, string2));
            }
            z(typedArrayObtainAttributes.getString(u.ActivityNavigator_action));
            String string3 = typedArrayObtainAttributes.getString(u.ActivityNavigator_data);
            if (string3 != null) {
                B(Uri.parse(string3));
            }
            C(typedArrayObtainAttributes.getString(u.ActivityNavigator_dataPattern));
            typedArrayObtainAttributes.recycle();
        }

        @Override // androidx.navigation.j
        public String toString() {
            ComponentName componentNameW = w();
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            if (componentNameW != null) {
                sb.append(" class=");
                sb.append(componentNameW.getClassName());
            } else {
                String strV = v();
                if (strV != null) {
                    sb.append(" action=");
                    sb.append(strV);
                }
            }
            return sb.toString();
        }

        @Override // androidx.navigation.j
        boolean u() {
            return false;
        }

        public final String v() {
            Intent intent = this.f2631o;
            if (intent == null) {
                return null;
            }
            return intent.getAction();
        }

        public final ComponentName w() {
            Intent intent = this.f2631o;
            if (intent == null) {
                return null;
            }
            return intent.getComponent();
        }

        public final String x() {
            return this.f2632p;
        }

        public final Intent y() {
            return this.f2631o;
        }

        public final C0024a z(String str) {
            if (this.f2631o == null) {
                this.f2631o = new Intent();
            }
            this.f2631o.setAction(str);
            return this;
        }
    }

    /* JADX INFO: compiled from: ActivityNavigator.java */
    public static final class b implements r.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f2633a;

        public w.c a() {
            return null;
        }

        public int b() {
            return this.f2633a;
        }
    }

    public a(Context context) {
        this.f2629a = context;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                this.f2630b = (Activity) context;
                return;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
    }

    @Override // androidx.navigation.r
    public boolean e() {
        Activity activity = this.f2630b;
        if (activity == null) {
            return false;
        }
        activity.finish();
        return true;
    }

    @Override // androidx.navigation.r
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public C0024a a() {
        return new C0024a(this);
    }

    final Context g() {
        return this.f2629a;
    }

    @Override // androidx.navigation.r
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public j b(C0024a c0024a, Bundle bundle, o oVar, r.a aVar) {
        Intent intent;
        int intExtra;
        if (c0024a.y() == null) {
            throw new IllegalStateException("Destination " + c0024a.k() + " does not have an Intent set.");
        }
        Intent intent2 = new Intent(c0024a.y());
        if (bundle != null) {
            intent2.putExtras(bundle);
            String strX = c0024a.x();
            if (!TextUtils.isEmpty(strX)) {
                StringBuffer stringBuffer = new StringBuffer();
                Matcher matcher = Pattern.compile("\\{(.+?)\\}").matcher(strX);
                while (matcher.find()) {
                    String strGroup = matcher.group(1);
                    if (!bundle.containsKey(strGroup)) {
                        throw new IllegalArgumentException("Could not find " + strGroup + " in " + bundle + " to fill data pattern " + strX);
                    }
                    matcher.appendReplacement(stringBuffer, "");
                    stringBuffer.append(Uri.encode(bundle.get(strGroup).toString()));
                }
                matcher.appendTail(stringBuffer);
                intent2.setData(Uri.parse(stringBuffer.toString()));
            }
        }
        boolean z6 = aVar instanceof b;
        if (z6) {
            intent2.addFlags(((b) aVar).b());
        }
        if (!(this.f2629a instanceof Activity)) {
            intent2.addFlags(268435456);
        }
        if (oVar != null && oVar.g()) {
            intent2.addFlags(536870912);
        }
        Activity activity = this.f2630b;
        if (activity != null && (intent = activity.getIntent()) != null && (intExtra = intent.getIntExtra("android-support-navigation:ActivityNavigator:current", 0)) != 0) {
            intent2.putExtra("android-support-navigation:ActivityNavigator:source", intExtra);
        }
        intent2.putExtra("android-support-navigation:ActivityNavigator:current", c0024a.k());
        Resources resources = g().getResources();
        if (oVar != null) {
            int iC = oVar.c();
            int iD = oVar.d();
            if ((iC <= 0 || !resources.getResourceTypeName(iC).equals("animator")) && (iD <= 0 || !resources.getResourceTypeName(iD).equals("animator"))) {
                intent2.putExtra("android-support-navigation:ActivityNavigator:popEnterAnim", iC);
                intent2.putExtra("android-support-navigation:ActivityNavigator:popExitAnim", iD);
            } else {
                Log.w("ActivityNavigator", "Activity destinations do not support Animator resource. Ignoring popEnter resource " + resources.getResourceName(iC) + " and popExit resource " + resources.getResourceName(iD) + "when launching " + c0024a);
            }
        }
        if (z6) {
            ((b) aVar).a();
            this.f2629a.startActivity(intent2);
        } else {
            this.f2629a.startActivity(intent2);
        }
        if (oVar == null || this.f2630b == null) {
            return null;
        }
        int iA = oVar.a();
        int iB = oVar.b();
        if ((iA <= 0 || !resources.getResourceTypeName(iA).equals("animator")) && (iB <= 0 || !resources.getResourceTypeName(iB).equals("animator"))) {
            if (iA < 0 && iB < 0) {
                return null;
            }
            this.f2630b.overridePendingTransition(Math.max(iA, 0), Math.max(iB, 0));
            return null;
        }
        Log.w("ActivityNavigator", "Activity destinations do not support Animator resource. Ignoring enter resource " + resources.getResourceName(iA) + " and exit resource " + resources.getResourceName(iB) + "when launching " + c0024a);
        return null;
    }
}
