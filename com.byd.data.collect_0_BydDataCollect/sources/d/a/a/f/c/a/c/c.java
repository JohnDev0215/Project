package d.a.a.f.c.a.c;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.TextUtils;
import com.bydux.api.common.views.application.BydApplication;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: SkinManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Object f4662a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static c f4663b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Context f4665d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private List<d.a.a.f.c.a.b.b> f4668g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Resources f4670i;
    private boolean k;
    private d.a.a.f.c.a.c.a l;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4664c = c.class.getSimpleName();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f4666e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private d.a.a.f.c.a.b.a f4667f = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f4669h = "";
    private String j = "";

    /* JADX INFO: compiled from: SkinManager.java */
    class a extends AsyncTask<String, Void, Resources> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d.a.a.f.c.a.b.a f4671a;

        a(d.a.a.f.c.a.b.a aVar) {
            this.f4671a = aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        @SuppressLint({"StaticFieldLeak"})
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Resources doInBackground(String... strArr) {
            try {
                if (strArr.length == 1) {
                    String str = strArr[0];
                    if (TextUtils.isEmpty(str)) {
                        d.a.a.f.b.b.a.i(c.this.f4664c, "doInBackground", "skinPath is null");
                        return null;
                    }
                    File file = new File(str);
                    if (!file.exists()) {
                        d.a.a.f.b.b.a.i(c.this.f4664c, "doInBackground", "file not exist");
                        return null;
                    }
                    d.a.a.f.b.b.a.i(c.this.f4664c, "doInBackground", "skinPath is null？ // " + str + ", file.exists() / " + file.exists());
                    c.this.f4669h = c.this.f4665d.getPackageManager().getPackageArchiveInfo(str, 1).packageName;
                    AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
                    Method method = assetManager.getClass().getMethod("addAssetPath", String.class);
                    method.setAccessible(true);
                    method.invoke(assetManager, str);
                    Resources resources = c.this.f4665d.getResources();
                    Resources resources2 = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
                    d.a.a.f.b.b.a.h(c.this.f4664c, "skin pkgName=" + c.this.f4669h);
                    return resources2;
                }
            } catch (Exception unused) {
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Resources resources) {
            c.this.v(resources, this.f4671a);
            c.this.w();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            d.a.a.f.c.a.b.a aVar = this.f4671a;
            if (aVar != null) {
                aVar.c();
            }
        }
    }

    /* JADX INFO: compiled from: SkinManager.java */
    private class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null || !action.equals("action.change.day.night.mode")) {
                return;
            }
            String stringExtra = intent.getStringExtra("bydux_skin_path");
            int intExtra = intent.getIntExtra("day_night_mode", 1);
            d.a.a.f.b.b.a.i(c.this.f4664c, "onReceive", "skinPath=" + stringExtra);
            if (intExtra == 2) {
                c.this.j = stringExtra;
            } else {
                c.this.j = "";
            }
            c.this.z();
        }

        /* synthetic */ b(c cVar, a aVar) {
            this();
        }
    }

    private c() {
    }

    private void A() {
        b bVar = this.f4666e;
        if (bVar == null) {
            return;
        }
        this.f4665d.unregisterReceiver(bVar);
        this.f4666e = null;
    }

    public static c n() {
        if (f4663b == null) {
            synchronized (f4662a) {
                if (f4663b == null) {
                    f4663b = new c();
                }
            }
        }
        return f4663b;
    }

    private void u(String str, d.a.a.f.c.a.b.a aVar) {
        new a(aVar).execute(str);
    }

    private void x() {
        if (this.f4666e == null) {
            this.f4666e = new b(this, null);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("action.change.day.night.mode");
            this.f4665d.registerReceiver(this.f4666e, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        d.a.a.f.b.b.a.i(this.f4664c, "reload", "skin path=" + this.j);
        u(this.j, this.f4667f);
    }

    public Resources B(Configuration configuration) {
        if (this.f4670i == null && BydApplication.d() != null) {
            this.f4670i = BydApplication.d().getResources();
        }
        Context context = this.f4665d;
        if (context != null) {
            this.f4670i.updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        }
        return this.f4670i;
    }

    public void g(d.a.a.f.c.a.b.b bVar) {
        if (this.f4668g == null) {
            this.f4668g = new ArrayList();
        }
        if (this.f4668g.contains(bVar)) {
            return;
        }
        this.f4668g.add(bVar);
    }

    public ColorStateList h(int i2) {
        String resourceEntryName = this.f4665d.getResources().getResourceEntryName(i2);
        if (this.f4670i == null) {
            this.f4670i = this.f4665d.getResources();
        }
        try {
            if (t()) {
                return this.f4665d.getResources().getColorStateList(i2);
            }
            int identifier = this.f4670i.getIdentifier(resourceEntryName, "color", this.f4669h);
            return identifier == 0 ? this.f4665d.getResources().getColorStateList(i2) : this.f4670i.getColorStateList(identifier);
        } catch (Resources.NotFoundException unused) {
            return new ColorStateList((int[][]) Array.newInstance((Class<?>) int.class, 1, 1), new int[]{this.f4665d.getResources().getColor(i2)});
        }
    }

    public void i(d.a.a.f.c.a.b.b bVar) {
        List<d.a.a.f.c.a.b.b> list = this.f4668g;
        if (list != null && list.contains(bVar)) {
            this.f4668g.remove(bVar);
        }
    }

    public int j(int i2) {
        Context context = this.f4665d;
        if (context == null) {
            return 0;
        }
        if (this.f4670i == null) {
            this.f4670i = context.getResources();
        }
        if (t()) {
            return this.f4665d.getResources().getColor(i2);
        }
        int identifier = this.f4670i.getIdentifier(this.f4665d.getResources().getResourceEntryName(i2), "color", this.f4669h);
        int color = identifier == 0 ? this.f4665d.getResources().getColor(i2) : this.f4670i.getColor(identifier);
        d.a.a.f.b.b.a.i(this.f4664c, "getColor", "trueResId = " + identifier);
        return color;
    }

    public Context k(Context context) {
        if (!n().q()) {
            return context;
        }
        if (this.l == null) {
            this.l = new d.a.a.f.c.a.c.a(context);
        }
        return this.l;
    }

    public Drawable l(int i2) {
        Context context = this.f4665d;
        if (context == null) {
            return null;
        }
        if (this.f4670i == null) {
            this.f4670i = context.getResources();
        }
        if (t()) {
            return this.f4665d.getResources().getDrawable(i2, null);
        }
        int identifier = this.f4670i.getIdentifier(this.f4665d.getResources().getResourceEntryName(i2), "drawable", this.f4669h);
        Drawable drawable = identifier == 0 ? this.f4665d.getResources().getDrawable(i2, null) : this.f4670i.getDrawable(identifier, null);
        d.a.a.f.b.b.a.i(this.f4664c, "getDrawable", "trueResId = " + identifier);
        return drawable;
    }

    public Drawable m(String str) {
        Context context = this.f4665d;
        if (context == null) {
            return null;
        }
        if (this.f4670i == null) {
            this.f4670i = context.getResources();
        }
        int identifier = t() ? this.f4670i.getIdentifier(str, "drawable", this.f4665d.getPackageName()) : this.f4670i.getIdentifier(str, "drawable", this.f4669h);
        Drawable drawable = identifier != 0 ? this.f4670i.getDrawable(identifier, null) : null;
        d.a.a.f.b.b.a.i(this.f4664c, "getDrawable", "trueResId = " + identifier);
        return drawable;
    }

    public Drawable o(int i2) {
        Context context = this.f4665d;
        if (context == null) {
            return null;
        }
        if (this.f4670i == null) {
            this.f4670i = context.getResources();
        }
        if (t()) {
            return this.f4665d.getResources().getDrawable(i2, null);
        }
        int identifier = this.f4670i.getIdentifier(this.f4665d.getResources().getResourceEntryName(i2), "mipmap", this.f4669h);
        Drawable drawable = identifier == 0 ? this.f4665d.getResources().getDrawable(i2, null) : this.f4670i.getDrawable(identifier, null);
        d.a.a.f.b.b.a.i(this.f4664c, "getDrawable", "trueResId = " + identifier);
        return drawable;
    }

    public Resources p() {
        if (this.f4670i == null) {
            this.f4670i = this.f4665d.getResources();
        }
        return this.f4670i;
    }

    public boolean q() {
        return this.k;
    }

    public String r() {
        return this.j;
    }

    public void s(Context context, d.a.a.f.c.a.b.a aVar, boolean z) {
        Context applicationContext = context.getApplicationContext();
        this.f4665d = applicationContext;
        this.f4667f = aVar;
        this.k = z;
        this.j = Settings.System.getString(applicationContext.getContentResolver(), "bydux_skin_path");
        z();
        d.a.a.f.b.b.a.i(this.f4664c, "init", "mSkinPath = " + this.j);
        x();
    }

    public boolean t() {
        return TextUtils.isEmpty(this.j);
    }

    public void v(Resources resources, d.a.a.f.c.a.b.a aVar) {
        if (resources == null) {
            d.a.a.f.b.b.a.i(this.f4664c, "changeResource", "result == null");
        }
        this.f4670i = resources;
        if (resources != null) {
            d.a.a.f.b.b.a.i(this.f4664c, "changeResource", "success");
            if (aVar != null) {
                aVar.b();
                return;
            }
            return;
        }
        Context context = this.f4665d;
        if (context != null) {
            this.f4670i = context.getResources();
        }
        if (TextUtils.isEmpty(this.j)) {
            d.a.a.f.b.b.a.i(this.f4664c, "changeResource", "default");
            if (aVar != null) {
                aVar.b();
                return;
            }
            return;
        }
        d.a.a.f.b.b.a.i(this.f4664c, "changeResource", "fail");
        if (aVar != null) {
            aVar.a();
        }
    }

    public void w() {
        List<d.a.a.f.c.a.b.b> list = this.f4668g;
        if (list == null) {
            return;
        }
        Iterator<d.a.a.f.c.a.b.b> it = list.iterator();
        while (it.hasNext()) {
            it.next().o();
        }
    }

    public void y() {
        if (this.k) {
            this.f4665d = null;
            this.f4667f = null;
        }
        A();
    }
}
