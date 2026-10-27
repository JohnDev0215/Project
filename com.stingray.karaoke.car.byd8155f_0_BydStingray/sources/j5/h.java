package J5;

import A7.m;
import B5.C0083g;
import B5.RunnableC0080d;
import K5.t;
import S8.AbstractC0727y;
import S8.E;
import android.content.Context;
import com.stingray.companion.CompanionManager;
import com.stingray.kca.model.LoginInfo;
import com.stingray.shared.logs.segment.MemberStatus;
import com.stingray.shared.logs.segment.UserType;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.UUID;
import k6.C1699g;
import r6.C2302c;
import timber.log.Timber;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f3100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1699g f3101b;
    public final A6.e c;
    public final t d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3102e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f3103f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public LoginInfo f3104g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final X8.c f3105h;

    public h(Context context, c cVar, C1699g c1699g, A6.e eVar, t tVar) {
        m.f(context, "appContext");
        m.f(cVar, "KCAService");
        m.f(c1699g, "deviceManager");
        m.f(eVar, "segmentLogger");
        m.f(tVar, "dataStoreManager");
        this.f3100a = cVar;
        this.f3101b = c1699g;
        this.c = eVar;
        this.d = tVar;
        this.f3102e = "en";
        Z8.e eVar2 = E.f8084a;
        this.f3105h = AbstractC0727y.a(Z8.d.f11010n);
    }

    public final synchronized LoginInfo a() {
        return this.f3104g;
    }

    public final synchronized boolean b() {
        if (this.f3104g != null) {
            return true;
        }
        try {
            this.f3102e = Locale.getDefault().toLanguageTag();
            LoginInfo loginInfoB = C2302c.b((C2302c) this.f3100a.c.getValue(), this.f3101b.a());
            Integer num = loginInfoB.f13164b;
            this.f3103f = (System.currentTimeMillis() + ((long) (num != null ? num.intValue() : 0))) - ((long) 60000);
            this.f3104g = loginInfoB;
            c();
            return true;
        } catch (Exception e10) {
            B9.a aVar = Timber.f22577a;
            aVar.e("LoginManager");
            aVar.a(e10);
            return false;
        }
    }

    public final void c() {
        if (this.f3104g == null) {
            return;
        }
        AbstractC0727y.w(this.f3105h, null, null, new g(this, new j5.m().e(this.f3104g), null), 3);
        LoginInfo loginInfo = this.f3104g;
        m.c(loginInfo);
        LinkedHashMap linkedHashMap = A6.d.f439a;
        String str = this.f3102e;
        CompanionManager.Companion.getClass();
        String str2 = (String) CompanionManager.companionSessionId.getValue();
        UserType userType = UserType.GUEST;
        MemberStatus memberStatus = MemberStatus.SUBSCRIBED;
        m.f(str, "language");
        m.f(str2, "companionSessionId");
        m.f(userType, "userType");
        m.f(memberStatus, "memberStatus");
        String str3 = loginInfo.d;
        if (str3 != null && str3.length() > 0) {
            A6.d.f439a.put("ip_country", str3);
        }
        String str4 = loginInfo.f13165e;
        if (str4 != null && str4.length() > 0) {
            A6.d.f439a.put("ip_region", str4);
        }
        String str5 = loginInfo.f13166f;
        if (str5 != null && str5.length() > 0) {
            A6.d.f439a.put("ip_city", str5);
        }
        LinkedHashMap linkedHashMap2 = A6.d.f439a;
        linkedHashMap2.put("app_language", str);
        linkedHashMap2.put("lobby_id", str2);
        if (A6.d.f440b.length() == 0) {
            A6.d.f440b = UUID.randomUUID().toString();
        }
        linkedHashMap2.put("session_id", A6.d.f440b);
        linkedHashMap2.put("navigation_uid", String.valueOf(C6.b.f1011e));
        linkedHashMap2.put("passenger_status", "NOT_AVAILABLE");
        linkedHashMap2.put("user_type", userType.name());
        linkedHashMap2.put("karaoke_member_status", memberStatus.name());
        String strA = this.f3101b.a();
        A6.e eVar = this.c;
        eVar.getClass();
        m.f(strA, "userId");
        if (eVar.c == null) {
            eVar.c = C0083g.h(eVar.f441a);
        }
        C0083g c0083g = eVar.c;
        m.c(c0083g);
        if (D5.g.g(strA)) {
            throw new IllegalArgumentException("Either userId or some traits must be provided.");
        }
        c0083g.f709u.submit(new RunnableC0080d(c0083g, strA, new Date()));
    }

    public final synchronized void d(String str, boolean z6) {
        if (z6) {
            this.f3104g = null;
        } else {
            try {
                LoginInfo loginInfo = this.f3104g;
                if (m.a(str, loginInfo != null ? loginInfo.f13163a : null)) {
                    this.f3104g = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
