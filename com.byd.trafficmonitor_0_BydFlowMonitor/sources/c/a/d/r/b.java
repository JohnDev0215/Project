package c.a.d.r;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import com.bydux.api.R;

/* JADX INFO: compiled from: FlowPresent.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements c.a.d.r.c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile c.a.d.r.c.a f3672a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f3673b = new byte[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.d.r.c.b f3674c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Activity f3676e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Handler f3677f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3675d = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3678g = 33;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f3679h = 1;

    public b(Activity activity, c.a.d.r.c.b bVar) {
        f(activity, bVar);
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: c.a.d.r.a
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f3671b.d(message);
            }
        });
        this.f3677f = handler;
        Message messageObtainMessage = handler.obtainMessage(33);
        messageObtainMessage.arg1 = 1;
        this.f3677f.sendMessageDelayed(messageObtainMessage, 3000L);
    }

    public static c.a.d.r.c.a c(Activity activity, c.a.d.r.c.b bVar) {
        if (f3672a == null) {
            synchronized (f3673b) {
                if (f3672a == null) {
                    f3672a = new b(activity, bVar);
                }
            }
        }
        return f3672a;
    }

    @Override // c.a.d.r.c.a
    public void a() {
        Handler handler = this.f3677f;
        if (handler != null && handler.hasMessages(33)) {
            this.f3677f.removeMessages(33);
        }
        this.f3674c = null;
        this.f3676e = null;
    }

    public CharSequence b(String str) {
        Activity activity;
        if (TextUtils.isEmpty(str) || (activity = this.f3676e) == null || activity.isFinishing()) {
            return str;
        }
        String string = this.f3676e.getString(R.string.flow_expire_time);
        String str2 = string + str;
        SpannableString spannableString = new SpannableString(str2);
        if (this.f3675d) {
            spannableString.setSpan(new ForegroundColorSpan(this.f3676e.getColor(R.color.flow_sub_title_color)), 0, string.length(), 17);
            spannableString.setSpan(new ForegroundColorSpan(this.f3676e.getColor(R.color.flow_expire_warn_color)), string.length(), str2.length(), 34);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(this.f3676e.getColor(R.color.flow_invalid_time_color)), 0, str2.length(), 18);
        }
        return spannableString;
    }

    public final boolean d(Message message) {
        if (message.what == 33) {
            boolean z = message.arg1 == 1;
            if (this.f3675d ^ z) {
                this.f3675d = z;
                c.a.d.r.c.b bVar = this.f3674c;
                if (bVar != null) {
                    bVar.a(b(z ? "2022-8-16" : "2022-10-11"));
                }
                Message messageObtainMessage = this.f3677f.obtainMessage(33);
                messageObtainMessage.arg1 = 0;
                this.f3677f.sendMessageDelayed(messageObtainMessage, 5000L);
            }
        }
        return true;
    }

    public void f(Activity activity, c.a.d.r.c.b bVar) {
        this.f3674c = bVar;
        this.f3676e = activity;
    }
}
