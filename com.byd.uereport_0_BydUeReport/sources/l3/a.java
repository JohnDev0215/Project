package l3;

import android.content.Context;
import android.hardware.bydauto.digital.BYDAutoDigitalKeyDevice;
import android.hardware.bydauto.digital.IDigitalKeyLisenter;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;
import k6.d;

/* JADX INFO: compiled from: DigitalKeyHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f6267a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f6268b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoDigitalKeyDevice f6269c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BYDAutoTestDevice f6270d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public m3.b f6271e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6272f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public AbsBYDAutoTestListener f6273g = new C0082a();

    /* JADX INFO: renamed from: l3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DigitalKeyHandler.java */
    public class C0082a extends AbsBYDAutoTestListener {

        /* JADX INFO: renamed from: l3.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DigitalKeyHandler.java */
        public class C0083a extends p6.a<List<n3.a>> {
            public C0083a() {
            }
        }

        public C0082a() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onCloudServiceConnectStateChanged(int i8) {
            super.onCloudServiceConnectStateChanged(i8);
            BydLogHelper.logD("onCloudServiceConnectStateChanged:" + i8);
            if (i8 != 1) {
                a.this.f6272f = false;
                return;
            }
            a.this.f6272f = true;
            String strB = o3.a.b(a.this.f6268b, "pre_remove_key", "");
            boolean z7 = o3.a.a(a.this.f6268b, "pre_is_new_card", 0) == 1;
            BydLogHelper.logD("isNewCard:" + z7);
            if (!TextUtils.isEmpty(strB)) {
                d dVar = new d();
                List list = (List) dVar.j(strB, new C0083a().f());
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(list);
                for (int i9 = 0; i9 < list.size(); i9++) {
                    n3.a aVar = (n3.a) list.get(i9);
                    BydLogHelper.logD("delete key info :" + aVar.toString());
                    String str = aVar.f6517b;
                    int i10 = aVar.f6516a;
                    if (!z7) {
                        arrayList.remove(aVar);
                        BYDAutoDigitalKeyDevice.getInstance(a.this.f6268b).deleteNfcKeyByKeyNo(1, str);
                    } else if (i10 == 1) {
                        arrayList.remove(aVar);
                        BYDAutoDigitalKeyDevice.getInstance(a.this.f6268b).deleteNfcKeyByKeyNo(1, str);
                    }
                }
                o3.a.d(a.this.f6268b, "pre_remove_key", dVar.r(arrayList));
            }
            o3.a.c(a.this.f6268b, "pre_is_new_card", 0);
        }
    }

    /* JADX INFO: compiled from: DigitalKeyHandler.java */
    public class b extends p6.a<List<n3.a>> {
        public b() {
        }
    }

    public a(Context context) {
        this.f6268b = context.getApplicationContext();
        e();
    }

    public static a c(Context context) {
        if (f6267a == null) {
            synchronized (a.class) {
                if (f6267a == null) {
                    f6267a = new a(context);
                }
            }
        }
        return f6267a;
    }

    public void d(String str) {
        d dVar = new d();
        n3.a aVar = (n3.a) dVar.i(str, n3.a.class);
        ArrayList arrayList = new ArrayList();
        String strB = o3.a.b(this.f6268b, "pre_remove_key", "");
        if (!TextUtils.isEmpty(strB)) {
            arrayList.addAll((List) dVar.j(strB, new b().f()));
        }
        n3.a aVar2 = new n3.a();
        if (aVar.a()) {
            aVar2.f6516a = 1;
        }
        aVar2.f6517b = aVar.f6517b;
        arrayList.add(aVar2);
        o3.a.d(this.f6268b, "pre_remove_key", dVar.r(arrayList));
    }

    public final void e() {
        this.f6269c = BYDAutoDigitalKeyDevice.getInstance(this.f6268b);
        this.f6270d = BYDAutoTestDevice.getInstance(this.f6268b);
    }

    public boolean f() {
        return this.f6272f;
    }

    public void g() {
        m3.b bVar = new m3.b(this.f6268b);
        this.f6271e = bVar;
        this.f6269c.registerListener((IDigitalKeyLisenter) bVar);
        this.f6270d.registerListener(this.f6273g);
    }

    public void h() {
        this.f6269c.unregisterListener((IDigitalKeyLisenter) this.f6271e);
        this.f6270d.unregisterListener(this.f6273g);
    }
}
