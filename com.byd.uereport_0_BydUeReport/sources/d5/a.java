package d5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.systemsettings.system.BydXCallDevice;
import com.byd.uereport.R;
import g5.e;
import y4.a;

/* JADX INFO: compiled from: XCallWindowManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements View.OnClickListener, View.OnTouchListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile a f4435d;
    public View A;
    public TextView B;
    public TextView C;
    public TextView D;
    public y4.a J;
    public int O;
    public int P;
    public d Q;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public WindowManager f4437f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WindowManager.LayoutParams f4438g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Context f4439h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public View f4440i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public View f4441j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f4442k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4443l;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public AudioManager f4447p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public AudioFocusRequest f4448q;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f4456y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public View f4457z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f4436e = "E_CALL";

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4444m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f4445n = 8;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f4446o = 21;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f4449r = 1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f4450s = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f4451t = 1;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f4452u = 0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f4453v = 18;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f4454w = 0;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f4455x = 1;
    public final int E = 2000;
    public final int F = 2001;
    public final int G = 2002;
    public final int H = 2003;
    public final String I = BydXCallDevice.E_CALL_STATE;
    public Handler K = new Handler(Looper.getMainLooper(), new C0029a());
    public int L = 0;
    public int M = 0;
    public boolean N = false;

    /* JADX INFO: renamed from: d5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: XCallWindowManager.java */
    public class C0029a implements Handler.Callback {
        public C0029a() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 2000:
                    int i8 = 8 - a.this.f4456y;
                    if (a.this.B != null) {
                        a.this.B.setText("(" + i8 + " s)");
                    }
                    if (a.this.f4456y != 8) {
                        a.c(a.this);
                        if (a.this.J != null) {
                            a.this.J.r(i8, a.this.f4444m);
                        }
                        a.this.K.sendEmptyMessageDelayed(2000, 1000L);
                    } else {
                        if (a.this.f4457z != null) {
                            a.this.f4457z.setVisibility(8);
                        }
                        if (a.this.J != null) {
                            a.this.J.r(0, a.this.f4444m);
                        }
                    }
                    return false;
                case 2001:
                    if (a.this.C != null) {
                        TextView textView = a.this.C;
                        a aVar = a.this;
                        textView.setText(aVar.w(aVar.f4443l));
                    }
                    if (a.this.J != null) {
                        a.this.J.r(a.this.f4443l, a.this.f4444m);
                    }
                    a.this.K.sendEmptyMessageDelayed(2001, 1000L);
                    a.p(a.this);
                    return false;
                case 2002:
                    g5.a.b("E_CALL", "MSG_UPDATE_ECALL_VIEW.");
                    if (a.this.f4440i != null) {
                        a.this.u();
                        a.this.N();
                        a.this.J();
                        a.this.f4438g.y = a.this.M;
                        if (g5.c.e() == 1) {
                            a.this.f4438g.x = -a.this.L;
                        } else {
                            a.this.f4438g.x = a.this.L;
                        }
                        g5.a.b("E_CALL", "eCall location x:" + a.this.f4438g.x + ", y:" + a.this.f4438g.y);
                        a.this.f4437f.updateViewLayout(a.this.f4441j, a.this.f4438g);
                    } else if (a.this.f4441j != null) {
                        a.this.f4438g.y = a.this.M;
                        if (g5.c.e() == 1) {
                            a.this.f4438g.x = -a.this.L;
                        } else {
                            a.this.f4438g.x = a.this.L;
                        }
                        g5.a.b("E_CALL", "eCall location x:" + a.this.f4438g.x + ", y:" + a.this.f4438g.y);
                        a.this.f4437f.updateViewLayout(a.this.f4441j, a.this.f4438g);
                    }
                    return false;
                case 2003:
                    g5.a.b("E_CALL", "MSG_SHOW_ECALL_VIEW.");
                    boolean zQ = a.this.J != null ? a.this.J.q() : false;
                    g5.a.b("E_CALL", "avm view mode is pip:" + zQ);
                    if (zQ) {
                        a.this.J();
                        a.this.N();
                        a.this.f4438g.y = a.this.M;
                        if (g5.c.e() == 1) {
                            a.this.f4438g.x = -a.this.L;
                        } else {
                            a.this.f4438g.x = a.this.L;
                        }
                        g5.a.b("E_CALL", "eCall location x:" + a.this.f4438g.x + ", y:" + a.this.f4438g.y);
                        a.this.f4437f.updateViewLayout(a.this.f4441j, a.this.f4438g);
                    } else {
                        a.this.M();
                    }
                    return false;
                default:
                    return false;
            }
        }
    }

    /* JADX INFO: compiled from: XCallWindowManager.java */
    public class b implements a.f {
        public b() {
        }

        @Override // y4.a.f
        public void a() {
            g5.a.b("E_CALL", "onAvmPipState");
            a.this.K.sendEmptyMessage(2002);
        }

        @Override // y4.a.f
        public void b() {
            g5.a.b("E_CALL", "onAvmCancel");
            d dVar = a.this.Q;
            if (dVar != null) {
                dVar.a();
            }
            a.this.t();
        }
    }

    /* JADX INFO: compiled from: XCallWindowManager.java */
    public class c implements AudioManager.OnAudioFocusChangeListener {
        public c() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i8) {
        }
    }

    /* JADX INFO: compiled from: XCallWindowManager.java */
    public interface d {
        void a();
    }

    public a(Context context) {
        this.f4437f = (WindowManager) context.getSystemService("window");
        B();
        this.f4439h = context;
        this.f4447p = (AudioManager) context.getSystemService("audio");
        this.J = y4.a.n(context);
    }

    public static /* synthetic */ int c(a aVar) {
        int i8 = aVar.f4456y;
        aVar.f4456y = i8 + 1;
        return i8;
    }

    public static /* synthetic */ int p(a aVar) {
        int i8 = aVar.f4443l;
        aVar.f4443l = i8 + 1;
        return i8;
    }

    public static a z(Context context) {
        if (f4435d == null) {
            synchronized (a.class) {
                if (f4435d == null) {
                    f4435d = new a(context.getApplicationContext());
                }
            }
        }
        return f4435d;
    }

    public final void A(Context context) {
        g5.a.b("E_CALL", "initAvmECallListener");
        this.J.u(new b());
    }

    public final void B() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f4438g = layoutParams;
        layoutParams.type = 2045;
        layoutParams.format = -3;
        layoutParams.flags = 142608168;
        layoutParams.flags = 142608168 | 16777216;
        layoutParams.windowAnimations = R.style.default_style;
        layoutParams.softInputMode = 16;
    }

    public void C(int i8) {
        g5.a.b("E_CALL", "onConfigurationChanged:" + i8);
        View view = this.f4440i;
        if (view == null && this.f4441j == null) {
            return;
        }
        if (i8 == 0) {
            this.D.setText(this.f4439h.getString(R.string.ecall_title));
            if (this.f4443l == 0) {
                this.C.setText(this.f4439h.getString(R.string.ecall_state_connecting));
                return;
            }
            return;
        }
        if (i8 == 2) {
            this.A.setBackground(null);
            this.A.setBackgroundResource(R.drawable.common_dialog_background);
            this.D.setTextColor(this.f4439h.getColor(R.color.call_name_text_color));
            this.C.setTextColor(this.f4439h.getColor(R.color.call_state_text_color));
            return;
        }
        if (i8 == 1) {
            if (view != null) {
                WindowManager.LayoutParams layoutParams = this.f4438g;
                layoutParams.y = 0;
                layoutParams.x = 0;
                this.f4437f.updateViewLayout(view, layoutParams);
                return;
            }
            if (this.f4441j != null) {
                y4.a aVar = this.J;
                boolean zQ = aVar != null ? aVar.q() : false;
                g5.a.b("E_CALL", "avm view mode is pip:" + zQ);
                if (zQ) {
                    J();
                    this.f4438g.y = this.M;
                    if (g5.c.e() == 1) {
                        this.f4438g.x = -this.L;
                    } else {
                        this.f4438g.x = this.L;
                    }
                } else {
                    WindowManager.LayoutParams layoutParams2 = this.f4438g;
                    layoutParams2.y = -200;
                    layoutParams2.x = 0;
                }
                g5.a.b("E_CALL", "eCall location x:" + this.f4438g.x + ", y:" + this.f4438g.y);
                this.f4437f.updateViewLayout(this.f4441j, this.f4438g);
            }
        }
    }

    public final void D() {
        this.f4440i = null;
        this.f4441j = null;
        this.B = null;
        this.C = null;
        this.f4457z = null;
        this.A = null;
    }

    @SuppressLint({"NewApi"})
    public final AudioFocusRequest E(int i8, AudioManager audioManager) {
        g5.a.b("E_CALL", "request EMERGENCY audio focus .");
        AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setUsage(i8).build();
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(1).setAudioAttributes(audioAttributesBuild).setOnAudioFocusChangeListener(new c()).build();
        g5.a.b("E_CALL", "request EMERGENCY audio usage:" + audioAttributesBuild.getUsage() + ",result:" + audioManager.requestAudioFocus(audioFocusRequestBuild));
        return audioFocusRequestBuild;
    }

    public final void F() {
        g5.a.b("E_CALL", "send eCall dismiss broadcast .");
        this.f4439h.sendBroadcast(new Intent("com.byd.system.ecall.ACTION_DISMISS"));
    }

    public void G() {
        g5.a.b("E_CALL", "send eCall event broadcast ......isShow:" + this.f4454w + ",isFullScreen:" + this.f4455x);
        Intent intent = new Intent("com.byd.system.ecall.ACTION_EVENT_CHANGE");
        intent.putExtra("isShow", this.f4454w);
        intent.putExtra("isFullScreen", this.f4455x);
        this.f4439h.sendBroadcast(intent);
    }

    public final void H() {
        g5.a.b("E_CALL", "send eCall show broadcast .");
        this.f4439h.sendBroadcast(new Intent("com.byd.system.ecall.ACTION_SHOW"));
    }

    public final void I(boolean z7) {
        if (z7) {
            this.N = true;
        } else {
            if (!this.N) {
                g5.a.b("E_CALL", "has not set mute , return");
                return;
            }
            this.N = false;
        }
        g5.a.b("E_CALL", "setAllMute ,args:" + (z7 ? 1 : 0));
        BYDAutoAudioDevice.getInstance(this.f4439h).setAllMute(z7 ? 1 : 0);
        BYDAutoAudioDevice.getInstance(this.f4439h).setCallMuteState(z7 ? 1 : 0);
    }

    public final void J() {
        if (this.f4439h.getResources().getConfiguration().orientation == 1) {
            this.L = 300;
            this.M = 425;
        } else {
            this.L = 725;
            this.M = 75;
        }
    }

    public void K(d dVar) {
        this.Q = dVar;
    }

    public a L(int i8) {
        y4.a aVar;
        this.f4444m = i8;
        s();
        int i9 = this.f4444m;
        if (i9 == 6) {
            this.K.sendEmptyMessage(2001);
        } else if (i9 == 5) {
            O(this.f4439h.getString(R.string.ecall_state_ringing));
        } else if (i9 == 3) {
            O(this.f4439h.getString(R.string.call_retry));
        } else if (i9 == 4) {
            O(this.f4439h.getString(R.string.call_fail_statue));
        } else if (i9 == 1 || i9 == 2) {
            O(this.f4439h.getString(R.string.ecall_state_connecting));
        } else if (i9 == 10) {
            this.K.sendEmptyMessage(2000);
        }
        g5.a.b("E_CALL", "currentState:" + this.f4444m);
        int i10 = this.f4444m;
        if (i10 != 8 && (aVar = this.J) != null) {
            aVar.r(0, i10);
        }
        return f4435d;
    }

    public void M() {
        if (this.f4440i == null) {
            View viewY = y();
            this.f4440i = viewY;
            this.f4437f.addView(viewY, this.f4438g);
            this.f4455x = 1;
            G();
        }
    }

    public void N() {
        if (this.f4441j == null) {
            View viewX = x();
            this.f4441j = viewX;
            this.f4437f.addView(viewX, this.f4438g);
            this.f4455x = 0;
            G();
        }
    }

    public final void O(String str) {
        TextView textView = this.C;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void P() {
        g5.a.b("E_CALL", "show eCall view .");
        if (this.f4440i == null && this.f4441j == null) {
            A(this.f4439h);
            H();
            this.f4454w = 1;
            g5.d.b(this.f4439h);
            Q();
            this.f4448q = E(21, this.f4447p);
            this.K.sendEmptyMessageDelayed(2003, 100L);
            I(true);
        }
    }

    public void Q() {
        Settings.System.putInt(this.f4439h.getContentResolver(), BydXCallDevice.E_CALL_STATE, this.f4454w);
        g5.a.b("E_CALL", "updateECallSettings:" + Settings.System.getInt(this.f4439h.getContentResolver(), BydXCallDevice.E_CALL_STATE, this.f4454w));
    }

    public final void R() {
        int i8 = this.f4444m;
        if (i8 == 10) {
            this.f4457z.setVisibility(0);
            this.B.setText("(" + (8 - this.f4456y) + " s)");
            return;
        }
        if (i8 == 6) {
            this.C.setText(w(this.f4443l));
            return;
        }
        if (i8 == 5) {
            this.C.setText(this.f4439h.getString(R.string.ecall_state_ringing));
        } else if (i8 == 3) {
            this.C.setText(this.f4439h.getString(R.string.call_retry));
        } else if (i8 == 4) {
            this.C.setText(this.f4439h.getString(R.string.call_fail_statue));
        }
    }

    @SuppressLint({"NewApi"})
    public final void a(AudioFocusRequest audioFocusRequest, AudioManager audioManager) {
        if (audioFocusRequest == null) {
            return;
        }
        g5.a.b("E_CALL", "release EMERGENCY audio focus .");
        audioManager.abandonAudioFocusRequest(audioFocusRequest);
        this.f4448q = null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.container /* 2131296435 */:
                if (System.currentTimeMillis() - this.f4442k >= 800) {
                    this.f4442k = System.currentTimeMillis();
                } else if (this.f4441j != null) {
                    v();
                    M();
                }
                break;
            case R.id.fullscreen_container /* 2131296522 */:
            case R.id.iv_scale /* 2131296617 */:
                if (this.f4440i != null) {
                    u();
                    N();
                }
                break;
            case R.id.rl_cancel /* 2131296801 */:
                t();
                d dVar = this.Q;
                if (dVar != null) {
                    dVar.a();
                }
                break;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.O = (int) motionEvent.getRawX();
            this.P = (int) motionEvent.getRawY();
            return false;
        }
        if (action != 2) {
            return false;
        }
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int i8 = rawX - this.O;
        int i9 = rawY - this.P;
        this.O = rawX;
        this.P = rawY;
        int iA = e.a(view);
        if (i8 >= 0) {
            int iE = e.e(this.f4439h);
            int measuredWidth = view.getMeasuredWidth();
            if (iA + measuredWidth + i8 > iE) {
                i8 = (iE - iA) - measuredWidth;
            }
        } else if (iA + i8 < 0) {
            i8 = iA;
        }
        int iB = e.b(view);
        if (i9 >= 0) {
            int iD = e.d(this.f4439h);
            int measuredHeight = view.getMeasuredHeight();
            int iC = e.c(this.f4439h);
            if (iB + measuredHeight + iC + i9 > iD) {
                i9 = ((iD - iB) - measuredHeight) - iC;
            }
        } else if (iB + i9 < 0) {
            i9 = iB;
        }
        WindowManager.LayoutParams layoutParams = this.f4438g;
        layoutParams.x += i8;
        layoutParams.y += i9;
        this.f4437f.updateViewLayout(view, layoutParams);
        return false;
    }

    public final void s() {
        this.f4443l = 0;
        this.K.removeMessages(2001);
    }

    public void t() {
        g5.a.b("E_CALL", "ECall dismiss .");
        this.f4444m = 8;
        this.J.u(null);
        this.J.r(0, this.f4444m);
        u();
        v();
        this.K.removeCallbacksAndMessages(null);
        this.f4443l = 0;
        this.f4444m = -1;
        this.f4456y = 0;
        this.f4454w = 0;
        Q();
        G();
        F();
        a(this.f4448q, this.f4447p);
        I(false);
    }

    public final void u() {
        View view = this.f4440i;
        if (view != null) {
            this.f4437f.removeView(view);
            D();
        }
    }

    public final void v() {
        View view = this.f4441j;
        if (view != null) {
            this.f4437f.removeView(view);
            D();
        }
    }

    public final String w(int i8) {
        int i9 = i8 / 3600;
        int i10 = i8 - (i9 * 3600);
        int i11 = i10 / 60;
        int i12 = i10 - (i11 * 60);
        StringBuffer stringBuffer = new StringBuffer();
        if (i9 > 0 && i9 < 10) {
            stringBuffer.append("0" + i9 + ":");
        } else if (i9 >= 10) {
            stringBuffer.append(i9 + ":");
        }
        if (i11 < 0 || i11 >= 10) {
            stringBuffer.append(i11 + ":");
        } else {
            stringBuffer.append("0" + i11 + ":");
        }
        if (i12 < 0 || i12 >= 10) {
            stringBuffer.append(i12 + "");
        } else {
            stringBuffer.append("0" + i12);
        }
        return stringBuffer.toString();
    }

    public final View x() {
        View viewInflate = LayoutInflater.from(this.f4439h).inflate(R.layout.layout_e_call_scale, (ViewGroup) null);
        this.f4457z = viewInflate.findViewById(R.id.rl_cancel);
        this.A = viewInflate.findViewById(R.id.container);
        this.C = (TextView) viewInflate.findViewById(R.id.tv_call_state);
        this.D = (TextView) viewInflate.findViewById(R.id.tv_call_title);
        this.B = (TextView) viewInflate.findViewById(R.id.tv_countdown);
        this.f4438g.width = (int) this.f4439h.getResources().getDimension(R.dimen.dp_280);
        this.f4438g.height = (int) this.f4439h.getResources().getDimension(R.dimen.dp_254);
        WindowManager.LayoutParams layoutParams = this.f4438g;
        layoutParams.gravity = 1;
        layoutParams.y = -200;
        layoutParams.x = 0;
        R();
        if (8 == this.f4456y) {
            this.f4457z.setVisibility(8);
        }
        this.f4457z.setOnClickListener(this);
        viewInflate.setOnClickListener(this);
        viewInflate.setOnTouchListener(this);
        return viewInflate;
    }

    public final View y() {
        View viewInflate = LayoutInflater.from(this.f4439h).inflate(R.layout.layout_e_call, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_scale);
        this.f4457z = viewInflate.findViewById(R.id.rl_cancel);
        this.A = viewInflate.findViewById(R.id.container);
        this.C = (TextView) viewInflate.findViewById(R.id.tv_call_state);
        this.D = (TextView) viewInflate.findViewById(R.id.tv_call_title);
        this.B = (TextView) viewInflate.findViewById(R.id.tv_countdown);
        WindowManager.LayoutParams layoutParams = this.f4438g;
        layoutParams.width = -1;
        layoutParams.height = -1;
        layoutParams.gravity = 17;
        layoutParams.y = 0;
        layoutParams.x = 0;
        R();
        if (8 == this.f4456y) {
            this.f4457z.setVisibility(8);
        }
        imageView.setOnClickListener(this);
        this.f4457z.setOnClickListener(this);
        this.A.setOnClickListener(this);
        viewInflate.setOnClickListener(this);
        return viewInflate;
    }
}
