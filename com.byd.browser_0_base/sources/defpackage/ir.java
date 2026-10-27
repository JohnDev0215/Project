package defpackage;

import J.N;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.chromium.base.BuildInfo;
import org.chromium.base.Callback;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.share.a;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: ChromeProvidedSharingOptionsProviderBase.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ir {
    public final Activity a;
    public final WindowAndroid b;
    public final nw2<Tab> c;
    public final ji d;
    public final org.chromium.components.browser_ui.share.a e;
    public final boolean f;
    public final ArrayList g;
    public final rq h;
    public final String i;
    public final xe3 j;
    public final Profile k;

    /* JADX INFO: compiled from: ChromeProvidedSharingOptionsProviderBase.java */
    public static class a {
        public final int a;
        public final int b;
        public final String c;
        public final String d;
        public final Callback<View> e;
        public final Collection<Integer> f;
        public final Collection<Integer> g;
        public final Collection<Integer> h;
        public final boolean i;

        public a() {
            throw null;
        }

        public a(int i, int i2, String str, String str2, Callback callback, List list, List list2, List list3, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = str;
            this.d = str2;
            this.e = callback;
            this.f = list;
            this.g = list2;
            this.h = list3;
            this.i = z;
        }
    }

    public ir(Activity activity, WindowAndroid windowAndroid, nw2<Tab> nw2Var, ji jiVar, org.chromium.components.browser_ui.share.a aVar, boolean z, rq rqVar, xe3 xe3Var, String str, Profile profile) {
        a aVarB;
        this.a = activity;
        this.b = windowAndroid;
        this.c = nw2Var;
        this.d = jiVar;
        this.e = aVar;
        this.f = z;
        this.j = xe3Var;
        this.h = rqVar;
        this.i = str;
        this.k = profile;
        ArrayList arrayList = new ArrayList();
        this.g = arrayList;
        if (BuildInfo.a.a.m) {
            arrayList.add(a());
            f();
            e();
            return;
        }
        sk skVar = bq.a;
        int i = 1;
        final int i2 = 0;
        if (N.M09VlOh_("WebNotesStylize")) {
            arrayList.add(new a(R$drawable.webnote, R$string.sharing_webnotes_create_card, activity.getString(R$string.sharing_webnotes_accessibility_description), "SharingHubAndroid.WebnotesStylize", new gp(this, 20, aVar.b), Arrays.asList(3), Arrays.asList(new Integer[0]), Arrays.asList(new Integer[0]), false));
        }
        a aVarC = c();
        if (aVarC != null) {
            arrayList.add(aVarC);
        }
        if (nw2Var.f() && (aVarB = b()) != null) {
            arrayList.add(aVarB);
        }
        arrayList.add(a());
        int i3 = R$drawable.ic_content_copy_black;
        arrayList.add(new a(i3, R$string.sharing_copy_gif, null, "SharingHubAndroid.CopyGifSelected", new gr(this, i), Arrays.asList(5, 7), Arrays.asList(new Integer[0]), Arrays.asList(1, 5, 0), false));
        final int i4 = 1;
        arrayList.add(new a(i3, R$string.sharing_copy_image, null, "SharingHubAndroid.CopyImageSelected", new Callback(this) { // from class: hr
            public final /* synthetic */ ir f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i5 = i4;
                ir irVar = this.f;
                switch (i5) {
                    case 0:
                        Activity activity2 = irVar.a;
                        ClipboardManager clipboardManager = (ClipboardManager) activity2.getSystemService("clipboard");
                        a aVar2 = irVar.e;
                        clipboardManager.setPrimaryClip(ClipData.newPlainText(aVar2.b, aVar2.b()));
                        Toast.c(activity2, R$string.copied, 0).e();
                        break;
                    default:
                        a aVar3 = irVar.e;
                        if (!aVar3.g.isEmpty()) {
                            Clipboard.getInstance().f(aVar3.g.get(0));
                            Toast.c(irVar.a, R$string.image_copied, 0).e();
                        }
                        break;
                }
            }
        }, Arrays.asList(5, 7), Arrays.asList(new Integer[0]), Arrays.asList(2), false));
        arrayList.add(new a(i3, R$string.sharing_copy, null, "SharingHubAndroid.CopySelected", new Callback(this) { // from class: hr
            public final /* synthetic */ ir f;

            {
                this.f = this;
            }

            @Override // org.chromium.base.Callback
            public final void onResult(Object obj) {
                int i5 = i2;
                ir irVar = this.f;
                switch (i5) {
                    case 0:
                        Activity activity2 = irVar.a;
                        ClipboardManager clipboardManager = (ClipboardManager) activity2.getSystemService("clipboard");
                        a aVar2 = irVar.e;
                        clipboardManager.setPrimaryClip(ClipData.newPlainText(aVar2.b, aVar2.b()));
                        Toast.c(activity2, R$string.copied, 0).e();
                        break;
                    default:
                        a aVar3 = irVar.e;
                        if (!aVar3.g.isEmpty()) {
                            Clipboard.getInstance().f(aVar3.g.get(0));
                            Toast.c(irVar.a, R$string.image_copied, 0).e();
                        }
                        break;
                }
            }
        }, Arrays.asList(4), Arrays.asList(new Integer[0]), Arrays.asList(new Integer[0]), false));
        arrayList.add(new a(i3, R$string.sharing_copy_text, null, "SharingHubAndroid.CopyTextSelected", new gr(this, i2), Arrays.asList(2, 3), Arrays.asList(4), Arrays.asList(new Integer[0]), false));
        f();
        e();
        arrayList.add(new a(R$drawable.save_to_device, R$string.sharing_save_image, null, "SharingHubAndroid.SaveImageSelected", new fr(this, 0), Arrays.asList(5, 7), Arrays.asList(new Integer[0]), Arrays.asList(new Integer[0]), false));
    }

    public final a a() {
        return new a(R$drawable.ic_content_copy_black, R$string.sharing_copy_url, null, "SharingHubAndroid.CopyURLSelected", new fr(this, 1), Arrays.asList(0, 1), Arrays.asList(4), Arrays.asList(new Integer[0]), false);
    }

    public abstract a b();

    public abstract a c();

    public final ArrayList d(Set set, int i, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (a aVar : this.g) {
            if (!Collections.disjoint(set, aVar.f) && Collections.disjoint(set, aVar.g) && !aVar.h.contains(Integer.valueOf(i)) && (!z || !aVar.i)) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    public final void e() {
        if (this.f) {
            return;
        }
        this.g.add(new a(R$drawable.qr_code, R$string.qr_code_share_icon_label, null, "SharingHubAndroid.QRCodeSelected", new td2(24, this), Arrays.asList(0, 1, 5), Arrays.asList(new Integer[0]), Arrays.asList(5), false));
    }

    public final void f() {
        if (!n3.W(this.i, this.k).isPresent()) {
            sk skVar = bq.a;
            if (N.M09VlOh_("SendTabToSelfSigninPromo")) {
                return;
            }
        }
        this.g.add(new a(R$drawable.send_tab, R$string.send_tab_to_self_share_activity_title, null, "SharingHubAndroid.SendTabToSelfSelected", new gr(this, 2), Arrays.asList(0, 1, 5), Arrays.asList(new Integer[0]), Arrays.asList(5), false));
    }
}
