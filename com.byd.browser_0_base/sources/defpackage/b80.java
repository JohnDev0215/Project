package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import androidx.fragment.app.b;
import defpackage.fh1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.BooleanSupplier;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfileBridge;
import org.chromium.chrome.browser.browserservices.permissiondelegation.InstalledWebappGeolocationBridge;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sharing.SharingServiceProxy;
import org.chromium.components.browser_ui.styles.R$color;
import org.chromium.components.content_relationship_verification.OriginVerifier;
import org.chromium.content.browser.AppWebMessagePort;
import org.chromium.content_public.browser.MessagePayload;
import org.chromium.content_public.browser.MessagePort;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b80 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;

    public /* synthetic */ b80(Object obj, int i, Object obj2, Object obj3) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // java.lang.Runnable
    public final void run() {
        int i = 4;
        int i2 = this.e;
        int i3 = 2;
        int i4 = 0;
        Object obj = this.h;
        Object obj2 = this.g;
        Object obj3 = this.f;
        switch (i2) {
            case 0:
                b bVar = (b) obj3;
                b.a aVar = (b.a) obj;
                i21.e(bVar, "this$0");
                i21.e(aVar, "$animationInfo");
                bVar.a.endViewTransition((View) obj2);
                aVar.a();
                break;
            case 1:
                aq1 aq1Var = (aq1) obj3;
                Callback callback = (Callback) obj;
                if (aq1Var.f == obj2 && aq1Var.g.b((Callback<E>) callback)) {
                    callback.onResult(aq1Var.f);
                    break;
                }
                break;
            case 2:
                InstalledWebappGeolocationBridge.a aVar2 = (InstalledWebappGeolocationBridge.a) obj3;
                String str = (String) obj2;
                Bundle bundle = (Bundle) obj;
                aVar2.getClass();
                boolean zEquals = TextUtils.equals(str, "onNewLocationAvailable");
                InstalledWebappGeolocationBridge installedWebappGeolocationBridge = InstalledWebappGeolocationBridge.this;
                if (!zEquals) {
                    if (TextUtils.equals(str, "onNewLocationError")) {
                        String string = bundle != null ? bundle.getString("message", "") : "";
                        long j = installedWebappGeolocationBridge.a;
                        if (j != 0) {
                            N.M243l30e(j, string);
                            break;
                        }
                    }
                } else if (bundle == null) {
                    installedWebappGeolocationBridge.getClass();
                    break;
                } else if (installedWebappGeolocationBridge.a != 0) {
                    N.M5uE1cdn(installedWebappGeolocationBridge.a, bundle.getDouble("latitude"), bundle.getDouble("longitude"), bundle.getLong("timeStamp") / 1000.0d, bundle.containsKey("altitude"), bundle.getDouble("altitude"), bundle.containsKey("accuracy"), bundle.getDouble("accuracy"), bundle.containsKey("bearing"), bundle.getDouble("bearing"), bundle.containsKey("speed"), bundle.getDouble("speed"));
                    break;
                }
                break;
            case 3:
                ((a.e) obj3).g.f((OriginVerifier.a) obj2, (ft1) obj);
                break;
            case 4:
                c4 c4Var = (c4) obj3;
                qa qaVar = (qa) obj2;
                Callback callback2 = (Callback) obj;
                c4Var.m = true;
                PersonalDataManager personalDataManagerC = PersonalDataManager.c();
                personalDataManagerC.getClass();
                Object obj4 = ThreadUtils.a;
                N.MCBooW5W(personalDataManagerC.a);
                PersonalDataManager.AutofillProfile autofillProfile = c4Var.p;
                String string2 = c4Var.j.n.toString();
                autofillProfile.getClass();
                autofillProfile.n = new PersonalDataManager.b(string2, 4);
                autofillProfile.o = new PersonalDataManager.b(c4Var.k.n.toString(), 4);
                autofillProfile.r = c4Var.i.a;
                HashSet hashSet = new HashSet();
                while (true) {
                    int size = c4Var.l.size();
                    HashMap map = c4Var.d;
                    if (i4 >= size) {
                        for (Map.Entry entry : map.entrySet()) {
                            if (!hashSet.contains(entry.getKey())) {
                                c4.e(autofillProfile, ((Integer) entry.getKey()).intValue(), "");
                            }
                        }
                        if (c4Var.f) {
                            PersonalDataManager personalDataManagerC2 = PersonalDataManager.c();
                            PersonalDataManager.AutofillProfile autofillProfile2 = c4Var.p;
                            personalDataManagerC2.getClass();
                            Object obj5 = ThreadUtils.a;
                            autofillProfile.a = N.McRRW$S3(personalDataManagerC2.a, personalDataManagerC2, autofillProfile2);
                        }
                        if (autofillProfile.getGUID().isEmpty()) {
                            autofillProfile.a = UUID.randomUUID().toString();
                        }
                        autofillProfile.c = true;
                        qaVar.k(c4Var.p);
                        callback2.onResult(qaVar);
                    } else {
                        AutofillProfileBridge.a aVar3 = (AutofillProfileBridge.a) c4Var.l.get(i4);
                        hashSet.add(Integer.valueOf(aVar3.a));
                        int i5 = aVar3.a;
                        if (i5 != 0) {
                            c4.e(autofillProfile, i5, ((xf0) map.get(Integer.valueOf(i5))).n);
                        }
                        i4++;
                    }
                    break;
                }
                break;
            case 5:
                String str2 = (String) obj3;
                String str3 = (String) obj2;
                String str4 = (String) obj;
                if (SharingServiceProxy.a == null) {
                    if (SharingServiceProxy.b == 0) {
                        N.MI$va2Pq(Profile.d());
                    }
                    SharingServiceProxy.a = new SharingServiceProxy();
                }
                sl0 sl0Var = new sl0(str4, i3, str3, str2);
                long j2 = SharingServiceProxy.b;
                if (j2 == 0) {
                    sl0Var.onResult(5);
                } else {
                    N.ML9GlI7W(j2, str2, str3, sl0Var);
                }
                break;
            case 6:
                w93 w93Var = (w93) obj2;
                Runnable runnable = (Runnable) obj;
                int i6 = R$drawable.ic_suggestion_magnifier;
                Context context = ((ka3) obj3).a;
                Resources resources = context.getResources();
                ThreadLocal<TypedValue> threadLocal = rb2.a;
                w93Var.e = resources.getDrawable(i6, null);
                String str5 = yp.a;
                w93Var.f = bz.b(context, R$color.default_icon_color_secondary_tint_list);
                w93Var.c = 3;
                if (runnable != null) {
                    runnable.run();
                }
                break;
            case 7:
                hg1 hg1Var = (hg1) obj3;
                fh1.a aVar4 = (fh1.a) obj2;
                Runnable runnable2 = (Runnable) obj;
                if (hg1Var.a != null) {
                    Object[] objArr = new Object[3];
                    objArr[0] = Integer.valueOf(((org.chromium.components.messages.b) aVar4.d).a());
                    objArr[1] = aVar4.c;
                    BooleanSupplier booleanSupplier = (BooleanSupplier) ((org.chromium.components.messages.b) aVar4.d).d.j(ng1.y);
                    objArr[2] = Boolean.valueOf(booleanSupplier != null ? booleanSupplier.getAsBoolean() : true);
                    fa1.m("MessageQueueManager", "MessageStateHandler#shouldShow for message with ID %s and key %s in MessageQueueManager#updateCurrentDisplayedMessage returned %s.", objArr);
                    hg1Var.h.d(new tf3(hg1Var, i, ((org.chromium.components.messages.b) hg1Var.a.d).c(0, 1), runnable2));
                    hg1Var.c = hg1Var.a;
                    break;
                }
                break;
            case 8:
                MessagePayload messagePayload = (MessagePayload) obj2;
                MessagePort[] messagePortArr = (MessagePort[]) obj;
                long j3 = ((AppWebMessagePort) obj3).a;
                if (j3 != 0) {
                    N.MnazrhT9(j3, messagePayload, messagePortArr);
                    break;
                }
                break;
            default:
                tp3 tp3Var = (tp3) obj3;
                Callable callable = (Callable) obj2;
                Callback callback3 = (Callback) obj;
                tp3Var.getClass();
                Boolean bool = Boolean.FALSE;
                try {
                    bool = (Boolean) callable.call();
                } catch (Exception unused) {
                }
                PostTask.c(tp3Var.c, new w70(tp3Var, 7, callback3, bool));
                break;
        }
    }
}
