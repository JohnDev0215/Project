package com.byd.digitalkey.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.bydauto.digital.tsimpl.DigitalKeyBean;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import b.b.a.j.b.a;
import b.b.j.d;
import com.byd.base.view.recyclerview.BaseListRecyclerAdapter;
import com.byd.base.view.recyclerview.RVGridItemDecoration;
import com.byd.digitalkey.R$color;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.byd.digitalkey.R$string;
import com.byd.digitalkey.activity.DigitalKeyActivateNewActivity;
import com.byd.digitalkey.activity.DigitalKeyActivity;
import com.byd.digitalkey.activity.UserAgreementActivity;
import com.byd.digitalkey.activity.UserHelpActivity;
import com.byd.digitalkey.adapter.BydDigitalKeyItem;
import com.byd.digitalkey.adapter.BydDigitalKeyList;
import com.byd.digitalkey.adapter.DigitalKeyAdapter;
import com.bydux.api.common.views.window.BydFragment;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class DigitalKeyFragment extends BydFragment implements b.b.a.j.d.b, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f3542b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public DigitalKeyAdapter f3543c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SwipeRefreshLayout f3544d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b.b.j.c f3545e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f3546f;
    public TextView g;
    public TextView h;
    public TextView i;
    public View j;
    public ImageView k;
    public List<BydDigitalKeyItem> l;
    public BydDigitalKeyItem n;
    public b.b.a.j.b.a o;
    public b.b.a.j.b.a p;
    public b.b.a.j.b.a q;
    public b.b.a.j.b.a r;
    public b.b.a.j.b.a s;
    public b.b.a.j.b.a t;
    public boolean u;
    public b.b.j.d v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3541a = getClass().getSimpleName();
    public volatile int m = -1;
    public final ServiceConnection w = new f();
    public final b.b.j.a x = new g();
    public final b.b.j.b y = new i();

    public class a extends ClickableSpan {
        public a() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(@NonNull View view) {
            DigitalKeyFragment digitalKeyFragment = DigitalKeyFragment.this;
            digitalKeyFragment.startActivity(new Intent(digitalKeyFragment.getContext(), (Class<?>) UserHelpActivity.class));
        }
    }

    public class b implements a.g {
        public b() {
        }

        @Override // b.b.a.j.b.a.g
        public void a(View view) {
            if (DigitalKeyFragment.this.f3545e.g() != 1) {
                ToastUtils.show(DigitalKeyFragment.this.getContext(), DigitalKeyFragment.this.getString(R$string.nfc_yun_cloud_net_error));
            } else if (DigitalKeyFragment.this.f3545e.q(DigitalKeyFragment.this.getActivity())) {
                DigitalKeyFragment.this.W(2);
            } else {
                DigitalKeyFragment.this.l0();
            }
        }
    }

    public class c implements a.g {
        public c() {
        }

        @Override // b.b.a.j.b.a.g
        public void a(View view) {
            if (DigitalKeyFragment.this.f3545e.q(DigitalKeyFragment.this.getActivity())) {
                DigitalKeyFragment.this.W(3);
            } else {
                DigitalKeyFragment.this.l0();
            }
        }
    }

    public class d implements Runnable {

        public class a implements a.g {
            public a() {
            }

            @Override // b.b.a.j.b.a.g
            public void a(View view) {
                if (DigitalKeyFragment.this.m == 1) {
                    DigitalKeyFragment.this.m0();
                } else if (DigitalKeyFragment.this.m == 2) {
                    DigitalKeyFragment.this.W(2);
                } else if (DigitalKeyFragment.this.m == 3) {
                    DigitalKeyFragment.this.W(3);
                }
            }
        }

        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DigitalKeyFragment.this.p == null) {
                DigitalKeyFragment digitalKeyFragment = DigitalKeyFragment.this;
                digitalKeyFragment.p = new b.b.a.j.b.a(digitalKeyFragment.getActivity());
            }
            DigitalKeyFragment.this.p.setTitle(R$string.nfc_tip);
            DigitalKeyFragment.this.p.t(R$string.nfc_key_not_in_car);
            DigitalKeyFragment.this.p.o(R$string.nfc_sure_retry);
            DigitalKeyFragment.this.p.h().setTag(2);
            if (DigitalKeyFragment.this.p.isShowing()) {
                b.b.a.f.f.b(DigitalKeyFragment.this.f3541a, "mKeyNotInCarDialog isShowing", new Object[0]);
            } else {
                DigitalKeyFragment.this.p.dismiss();
                DigitalKeyFragment.this.p.show();
            }
            DigitalKeyFragment digitalKeyFragment2 = DigitalKeyFragment.this;
            digitalKeyFragment2.t = digitalKeyFragment2.p;
            DigitalKeyFragment.this.p.q(new a());
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DigitalKeyFragment.this.j.getVisibility() == 0) {
                return;
            }
            DigitalKeyFragment.this.j.setVisibility(0);
            RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setDuration(1500L);
            rotateAnimation.setRepeatCount(-1);
            DigitalKeyFragment.this.k.startAnimation(rotateAnimation);
            DigitalKeyFragment.this.f3546f.setEnabled(false);
        }
    }

    public class f implements ServiceConnection {
        public f() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            DigitalKeyFragment.this.u = true;
            DigitalKeyFragment.this.v = d.a.d0(iBinder);
            b.b.a.f.f.b(DigitalKeyFragment.this.f3541a, "onServiceConnected %s:", DigitalKeyFragment.this.v.toString());
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DigitalKeyFragment.this.u = false;
            b.b.a.f.f.b(DigitalKeyFragment.this.f3541a, "onServiceDisconnected", new Object[0]);
        }
    }

    public class g extends b.b.j.a {
        public g() {
        }

        @Override // b.b.j.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
        public void onKeyFindingResults(int i, int i2) {
            super.onKeyFindingResults(i, i2);
            if (!DigitalKeyFragment.this.isResumed()) {
                b.b.a.f.f.b(DigitalKeyFragment.this.f3541a, "not on resume,return!!", new Object[0]);
                return;
            }
            if (i == 0) {
                return;
            }
            if (i2 == 0) {
                if (DigitalKeyFragment.this.m == 2) {
                    b.b.j.e.b(250002);
                } else if (DigitalKeyFragment.this.m == 3) {
                    b.b.j.e.b(250003);
                }
            } else if (i2 == 1) {
                b.b.j.e.b(250001);
            } else {
                b.b.a.f.f.b(DigitalKeyFragment.this.f3541a, "onKeyFindingResults not removeMessages", new Object[0]);
            }
            DigitalKeyFragment.this.b0(i);
        }

        @Override // b.b.j.a, android.hardware.bydauto.digital.IDigitalKeyLisenter
        public void onPadOfflineOperationResp(int i) {
            super.onPadOfflineOperationResp(i);
            b.b.j.e.b(25009);
            if (i == 0) {
                b.b.a.f.f.b(DigitalKeyFragment.this.f3541a, "result == 0 || keyId == null", new Object[0]);
                return;
            }
            if (i == 1) {
                if (DigitalKeyFragment.this.n != null) {
                    ToastUtils.show(DigitalKeyFragment.this.getActivity(), DigitalKeyFragment.this.getString(R$string.nfc_key_delete_ok));
                    DigitalKeyFragment digitalKeyFragment = DigitalKeyFragment.this;
                    digitalKeyFragment.e0(i, Arrays.toString(digitalKeyFragment.n.getKeyIdArray()));
                }
                DigitalKeyFragment.this.h0();
                return;
            }
            if (i == 2) {
                if (DigitalKeyFragment.this.n != null) {
                    ToastUtils.show(DigitalKeyFragment.this.getActivity(), DigitalKeyFragment.this.getString(R$string.nfc_key_delete_fail));
                }
                DigitalKeyFragment.this.h0();
            }
        }
    }

    public class h implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3556a;

        public h(int i) {
            this.f3556a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((BydDigitalKeyItem) DigitalKeyFragment.this.l.get(this.f3556a)).setKeyState((byte) 19);
            DigitalKeyFragment.this.f3543c.notifyDataSetChanged();
        }
    }

    public class i extends b.b.j.b {

        public class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f3559a;

            public a(int i) {
                this.f3559a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.f3559a != 1) {
                    DigitalKeyFragment.this.j0(3);
                    ToastUtils.show(DigitalKeyFragment.this.getActivity(), DigitalKeyFragment.this.getString(R$string.nfc_key_delete_fail));
                } else if (DigitalKeyFragment.this.getActivity() != null) {
                    ToastUtils.show(DigitalKeyFragment.this.getActivity(), DigitalKeyFragment.this.getString(R$string.nfc_key_delete_ok));
                    DigitalKeyFragment digitalKeyFragment = DigitalKeyFragment.this;
                    digitalKeyFragment.startActivity(new Intent(digitalKeyFragment.getActivity(), (Class<?>) DigitalKeyActivity.class));
                }
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ int f3561a;

            public b(int i) {
                this.f3561a = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (this.f3561a == 1) {
                    ToastUtils.show(DigitalKeyFragment.this.getActivity(), DigitalKeyFragment.this.getString(R$string.nfc_key_delete_ok));
                    DigitalKeyFragment.this.Y();
                } else {
                    DigitalKeyFragment.this.j0(2);
                    ToastUtils.show(DigitalKeyFragment.this.getActivity(), DigitalKeyFragment.this.getString(R$string.nfc_key_delete_fail));
                }
            }
        }

        public i() {
        }

        @Override // b.b.j.b, android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
        public void onDeleteNfcKeyByKeyNoResult(String str, int i) {
            super.onDeleteNfcKeyByKeyNoResult(str, i);
            System.out.println("云端反馈删除单把钥匙的结果,keyNo：" + str + ",result:" + i);
            b.b.j.e.b(25006);
            DigitalKeyFragment.this.h0();
            if (!DigitalKeyFragment.this.isResumed()) {
                b.b.a.f.f.b(DigitalKeyFragment.this.f3541a, "not on resume,return!!", new Object[0]);
                return;
            }
            if (DigitalKeyFragment.this.n == null || DigitalKeyFragment.this.n.getKeyNo() == null || DigitalKeyFragment.this.n.getKeyNo().equals(str)) {
                if (DigitalKeyFragment.this.getActivity() != null) {
                    DigitalKeyFragment.this.getActivity().runOnUiThread(new a(i));
                    return;
                }
                return;
            }
            b.b.a.f.f.a(DigitalKeyFragment.this.f3541a, "当前操作的钥匙编号：" + DigitalKeyFragment.this.n.getKeyNo() + ",云端返回的钥匙编号：" + str, new Object[0]);
        }

        @Override // b.b.j.b, android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
        public void onDeleteOwnIphoneKeyResult(String str, int i) {
            super.onDeleteOwnIphoneKeyResult(str, i);
            System.out.println("云端反馈更换车主钥匙结果,keyNo：" + str + ",result:" + i);
            b.b.j.e.b(25005);
            DigitalKeyFragment.this.h0();
            if (!DigitalKeyFragment.this.isResumed()) {
                b.b.a.f.f.b(DigitalKeyFragment.this.f3541a, "not on resume,return!!", new Object[0]);
                return;
            }
            if (DigitalKeyFragment.this.n == null || DigitalKeyFragment.this.n.getKeyNo() == null || DigitalKeyFragment.this.n.getKeyNo().equals(str)) {
                if (DigitalKeyFragment.this.getActivity() != null) {
                    DigitalKeyFragment.this.getActivity().runOnUiThread(new b(i));
                    return;
                }
                return;
            }
            b.b.a.f.f.a(DigitalKeyFragment.this.f3541a, "当前操作的钥匙编号：" + DigitalKeyFragment.this.n.getKeyNo() + ",云端返回的钥匙编号：" + str, new Object[0]);
        }

        @Override // b.b.j.b, android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
        public void onKeyListPageRefresh() {
            super.onKeyListPageRefresh();
            DigitalKeyFragment.this.f3545e.t();
        }

        @Override // b.b.j.b, android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
        public void onKeyListResponse(int i, List<DigitalKeyBean> list) {
            super.onKeyListResponse(i, list);
            DigitalKeyFragment.this.c0(list);
        }
    }

    public class j implements Runnable {
        public j() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DigitalKeyFragment.this.getActivity() != null) {
                DigitalKeyFragment.this.getActivity().finish();
                DigitalKeyFragment digitalKeyFragment = DigitalKeyFragment.this;
                digitalKeyFragment.startActivity(new Intent(digitalKeyFragment.getActivity(), (Class<?>) DigitalKeyActivateNewActivity.class));
            }
        }
    }

    public class k extends GridLayoutManager.SpanSizeLookup {
        public k() {
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            return i == 0 ? 2 : 1;
        }
    }

    public class l implements a.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f3565a;

        public l(int i) {
            this.f3565a = i;
        }

        @Override // b.b.a.j.b.a.g
        public void a(View view) {
            DigitalKeyFragment.this.W(this.f3565a);
        }
    }

    public class m implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ List f3567a;

        public m(List list) {
            this.f3567a = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            DigitalKeyFragment.this.f3544d.setRefreshing(false);
            if (DigitalKeyFragment.this.f3543c != null) {
                DigitalKeyFragment.this.f3543c.e();
                DigitalKeyFragment.this.f3543c.a(this.f3567a);
            }
            String strB = b.b.a.h.j.b(System.currentTimeMillis());
            b.b.a.h.i.b(DigitalKeyFragment.this.getActivity(), "key_update_time", strB);
            DigitalKeyFragment digitalKeyFragment = DigitalKeyFragment.this;
            String string = digitalKeyFragment.getString(R$string.nfc_key_total, Integer.valueOf(digitalKeyFragment.l.size()));
            if (DigitalKeyFragment.this.l.size() == 1 && string.contains("(s)")) {
                string = string.replace("(s)", "");
            }
            DigitalKeyFragment.this.i.setText(string);
            DigitalKeyFragment.this.h.setText(DigitalKeyFragment.this.getString(R$string.nfc_update_time, strB));
            DigitalKeyFragment.this.f0();
        }
    }

    public class n implements Runnable {
        public n() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DigitalKeyFragment.this.k.clearAnimation();
            DigitalKeyFragment.this.j.setVisibility(8);
            DigitalKeyFragment.this.f3546f.setEnabled(true);
        }
    }

    public void U() {
        Intent intent = new Intent();
        intent.setAction("com.byd.digitalkey.action");
        intent.setPackage("com.byd.uereport");
        if (getContext() != null) {
            getContext().bindService(intent, this.w, 1);
        }
    }

    public final void V() {
        if (this.q == null) {
            this.q = new b.b.a.j.b.a(getContext());
        }
        this.q.setTitle(R$string.nfc_tip);
        this.q.t(R$string.nfc_change_phone_message);
        this.q.o(R$string.base_common_dialog_btn_ok);
        this.q.h().setTag(6);
        this.q.q(new b());
        if (!this.q.isShowing()) {
            this.q.show();
        }
        this.t = this.q;
    }

    public final void W(int i2) {
        this.m = i2;
        this.f3545e.x(i2);
        n0();
    }

    public final void X() {
        if (this.r == null) {
            this.r = new b.b.a.j.b.a(getContext());
        }
        this.r.setTitle(R$string.nfc_tip);
        this.r.t(R$string.digital_key_remove_msg);
        this.r.o(R$string.base_common_dialog_btn_ok);
        this.r.h().setTag(7);
        this.r.q(new c());
        if (!this.r.isShowing()) {
            this.r.show();
        }
        this.t = this.r;
    }

    public final void Y() {
        this.f3545e.z(1);
        b.b.a.f.h.a().g(new j(), 1500L);
    }

    public final void Z() {
        if (getContext() == null) {
            return;
        }
        String string = getContext().getString(R$string.nfc_no_che_zhu_key_text);
        String string2 = getContext().getString(R$string.nfc_use_tips);
        int iIndexOf = string.indexOf(string2);
        int length = string2.length() + iIndexOf;
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getContext().getColor(R$color.nfc_apple_support_adapter_color)), iIndexOf, length, 33);
            spannableStringBuilder.setSpan(new a(), iIndexOf, length, 33);
            spannableStringBuilder.setSpan(new UnderlineSpan() { // from class: com.byd.digitalkey.fragment.DigitalKeyFragment.11
                @Override // android.text.style.UnderlineSpan, android.text.style.CharacterStyle
                public void updateDrawState(@NonNull TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(false);
                }
            }, iIndexOf, length, 33);
            this.g.setText(spannableStringBuilder);
            this.g.setMovementMethod(LinkMovementMethod.getInstance());
            this.g.setHighlightColor(0);
        } catch (Exception unused) {
            this.g.setText(string);
        }
    }

    public String a0() {
        return this.f3541a;
    }

    public final void b0(int i2) {
        if (1 != i2) {
            if (2 == i2 || 3 == i2) {
                k0();
                h0();
                return;
            }
            return;
        }
        if (this.m == 1) {
            if (getActivity() != null) {
                startActivity(new Intent(getActivity(), (Class<?>) DigitalKeyActivateNewActivity.class));
            }
            h0();
            return;
        }
        if (this.m == 2) {
            BydDigitalKeyItem bydDigitalKeyItem = this.n;
            if (bydDigitalKeyItem == null || TextUtils.isEmpty(bydDigitalKeyItem.getKeyNo())) {
                b.b.a.f.f.a(this.f3541a, "mKeyItem is null or keyNo is empty", new Object[0]);
                return;
            } else {
                this.f3545e.d(this.n.getKeyNo());
                n0();
                return;
            }
        }
        if (this.m == 3) {
            BydDigitalKeyItem bydDigitalKeyItem2 = this.n;
            if (bydDigitalKeyItem2 == null || TextUtils.isEmpty(bydDigitalKeyItem2.getKeyNo()) || TextUtils.isEmpty(Arrays.toString(this.n.getKeyIdArray()))) {
                b.b.a.f.f.a(this.f3541a, "mKeyItem is null or keyNo or keyId is empty", new Object[0]);
                return;
            }
            if (this.f3545e.g() != 1) {
                d0();
            } else {
                this.f3545e.b(this.n.getKeyNo());
            }
            n0();
        }
    }

    public final void c0(List<DigitalKeyBean> list) {
        b.b.j.e.b(25008);
        if (getActivity() != null) {
            if (list == null || list.size() == 0) {
                b.b.a.h.i.b(getActivity(), "nfc_key_list_cache", "");
                return;
            }
            List<BydDigitalKeyItem> listB = ((DigitalKeyActivity) getActivity()).B(list);
            this.l = listB;
            getActivity().runOnUiThread(new m(listB));
        }
    }

    public final void d0() {
        byte keyType = this.n.getKeyType();
        this.f3545e.s(keyType == 9 || keyType == 10, this.n.getKeyIdArray());
    }

    public final void e0(int i2, String str) {
        if (i2 == 2) {
            return;
        }
        for (int i3 = 0; i3 < this.l.size(); i3++) {
            BydDigitalKeyItem bydDigitalKeyItem = this.l.get(i3);
            String string = Arrays.toString(bydDigitalKeyItem.getKeyIdArray());
            b.b.a.f.f.b(this.f3541a, "updateKeyListCache id:%s,keyId:%s", string, str);
            if (str.equals(string)) {
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new h(i3));
                    b.b.a.h.i.b(getActivity(), "nfc_key_list_cache", b.a.a.a.toJSONString(this.l));
                }
                if (!this.u || this.v == null) {
                    return;
                }
                try {
                    String jSONString = b.a.a.a.toJSONString(bydDigitalKeyItem);
                    this.v.F(jSONString);
                    b.b.a.f.f.b(this.f3541a, "setRemovedKeyInfo:%s", jSONString);
                    return;
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
        }
    }

    public final void f0() {
        boolean z;
        List<BydDigitalKeyItem> list = this.l;
        if (list != null) {
            Iterator<BydDigitalKeyItem> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().hasCarOwnerKey()) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        if (z) {
            this.g.setVisibility(0);
            this.f3546f.setVisibility(8);
        } else {
            this.f3546f.setVisibility(0);
            this.g.setVisibility(8);
        }
    }

    public final View g0() {
        View viewInflate = View.inflate(getContext(), R$layout.head_kaitong_digital_key, null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.tv_nfc_kaitong);
        this.f3546f = textView;
        textView.setOnClickListener(this);
        this.g = (TextView) viewInflate.findViewById(R$id.tv_has_che_zhu_help);
        this.h = (TextView) viewInflate.findViewById(R$id.tv_update_time);
        this.i = (TextView) viewInflate.findViewById(R$id.tv_total_key);
        String strA = b.b.a.h.i.a(viewInflate.getContext(), "key_update_time");
        if (!TextUtils.isEmpty(strA)) {
            this.h.setText(getString(R$string.nfc_update_time, strA));
        }
        List<BydDigitalKeyItem> list = this.l;
        if (list != null) {
            String string = getString(R$string.nfc_key_total, Integer.valueOf(list.size()));
            if (this.l.size() == 1 && string.contains("(s)")) {
                string = string.replace("(s)", "");
            }
            this.i.setText(string);
        }
        f0();
        Z();
        return viewInflate;
    }

    public final void h0() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new n());
        }
    }

    public void i0(List<BydDigitalKeyItem> list) {
        this.l = list;
    }

    public final void j0(int i2) {
        if (this.o == null) {
            this.o = new b.b.a.j.b.a(getContext());
        }
        this.o.setTitle(R$string.nfc_tip);
        this.o.t(R$string.nfc_key_delete_fail);
        this.o.o(R$string.nfc_sure_retry);
        this.o.h().setTag(5);
        this.o.q(new l(i2));
        if (this.o.isShowing()) {
            b.b.a.f.f.b(this.f3541a, "deleteFailDialog isShowing", new Object[0]);
        } else {
            this.o.show();
        }
        this.t = this.o;
    }

    public final void k0() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new d());
        }
    }

    public final void l0() {
        if (this.s == null) {
            this.s = new b.b.a.j.b.a(getActivity());
        }
        this.s.setTitle(R$string.nfc_tip);
        this.s.t(R$string.nfc_safe_diviver_tips);
        this.s.l();
        this.s.o(R$string.digital_key_kown);
        this.s.h().setTag(1);
        if (this.s.isShowing()) {
            b.b.a.f.f.b(this.f3541a, "mNotSafeGearDialog isShowing", new Object[0]);
        } else {
            this.s.show();
        }
        this.t = this.s;
    }

    public final void m0() {
        this.m = 1;
        this.f3545e.z(1);
        n0();
    }

    public final void n0() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new e());
        }
    }

    public void o0() {
        if (this.f3545e.q(getActivity())) {
            m0();
        } else {
            l0();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            int i2 = bundle.getInt("dialog_restore_bundle_key", 0);
            this.m = bundle.getInt("current_op", -1);
            b.b.a.f.f.b(this.f3541a, "onActivityCreated dialog：%d:", Integer.valueOf(i2));
            if (i2 == 1) {
                l0();
                return;
            }
            if (i2 == 2) {
                k0();
                return;
            }
            if (i2 == 5) {
                j0(this.m);
            } else if (i2 == 6) {
                V();
            } else if (i2 == 7) {
                X();
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R$id.tv_nfc_kaitong) {
            Intent intent = new Intent(getActivity(), (Class<?>) UserAgreementActivity.class);
            intent.putExtra("from_source_page", 2);
            getActivity().startActivityForResult(intent, 1);
        }
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        RVGridItemDecoration rVGridItemDecoration;
        View viewInflate = layoutInflater.inflate(R$layout.fragment_digital_key, (ViewGroup) null);
        this.f3542b = (RecyclerView) viewInflate.findViewById(R$id.rv_digital_key);
        this.f3544d = (SwipeRefreshLayout) viewInflate.findViewById(R$id.srl);
        this.j = viewInflate.findViewById(R$id.loading_ll);
        this.k = (ImageView) viewInflate.findViewById(R$id.iv_loading);
        this.f3544d.setOnRefreshListener(this);
        DigitalKeyAdapter digitalKeyAdapter = new DigitalKeyAdapter();
        this.f3543c = digitalKeyAdapter;
        this.f3542b.setAdapter(digitalKeyAdapter);
        ((SimpleItemAnimator) this.f3542b.getItemAnimator()).setSupportsChangeAnimations(false);
        this.f3543c.setItemChildClickListener(this);
        int i2 = getResources().getConfiguration().orientation;
        int iA = b.b.a.h.c.a(getContext(), 16.0f);
        if (bundle != null) {
            this.l = ((BydDigitalKeyList) bundle.getSerializable("nfc_key_list")).keyItemList;
            b.b.a.f.f.b(this.f3541a, "onCreateView-nfc_key_list:" + this.l, new Object[0]);
        }
        if (i2 == 2) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            this.f3542b.setLayoutManager(gridLayoutManager);
            rVGridItemDecoration = new RVGridItemDecoration(0, iA, iA, 0);
            gridLayoutManager.setSpanSizeLookup(new k());
        } else {
            this.f3542b.setLayoutManager(new LinearLayoutManager(getContext()));
            rVGridItemDecoration = new RVGridItemDecoration(0, iA, 0, 0);
        }
        this.f3542b.addItemDecoration(rVGridItemDecoration);
        this.f3545e = b.b.j.c.i(getActivity());
        this.f3543c.b(g0());
        this.f3543c.a(this.l);
        EventBus.getDefault().register(this);
        this.f3545e.v(this.x);
        this.f3545e.w(this.y);
        U();
        h0();
        return viewInflate;
    }

    @Override // com.bydux.api.common.views.window.BydFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f3545e.C(this.x);
        this.f3545e.D(this.y);
        EventBus.getDefault().unregister(this);
        b.b.a.j.b.a aVar = this.t;
        if (aVar != null && aVar.isShowing()) {
            this.t.dismiss();
        }
        if (getContext() != null) {
            getContext().unbindService(this.w);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(b.b.j.f fVar) {
        if (!isResumed()) {
            b.b.a.f.f.b(this.f3541a, "not on resume,return!!", new Object[0]);
        }
        int iA = fVar.a();
        if (iA == 25005) {
            j0(2);
            ToastUtils.show(getActivity(), getString(R$string.nfc_key_delete_fail));
            h0();
            b.b.a.f.f.b(this.f3541a, "更换车主手机删钥匙20s超时", new Object[0]);
            return;
        }
        if (iA == 25006) {
            j0(3);
            ToastUtils.show(getActivity(), getString(R$string.nfc_key_delete_fail));
            h0();
            b.b.a.f.f.b(this.f3541a, "删单把钥匙20s超时", new Object[0]);
            return;
        }
        if (iA == 25008) {
            this.f3544d.setRefreshing(false);
            b.b.a.f.f.b(this.f3541a, "刷新钥匙列表10s超时", new Object[0]);
            return;
        }
        if (iA == 25009) {
            int iL = this.f3545e.l();
            if (iL == 1) {
                ToastUtils.show(getActivity(), getString(R$string.nfc_key_delete_ok));
                BydDigitalKeyItem bydDigitalKeyItem = this.n;
                if (bydDigitalKeyItem != null) {
                    e0(iL, Arrays.toString(bydDigitalKeyItem.getKeyIdArray()));
                }
            } else if (iL == 2) {
                ToastUtils.show(getActivity(), getString(R$string.nfc_key_delete_fail));
            }
            h0();
            b.b.a.f.f.b(this.f3541a, "离线删钥匙10s超时", new Object[0]);
            return;
        }
        switch (iA) {
            case 250001:
                this.m = 1;
                int iJ = this.f3545e.j();
                b0(iJ);
                b.b.a.f.f.b(this.f3541a, "开卡寻钥匙6s超时", new Object[0]);
                if (iJ == 0) {
                    k0();
                    h0();
                }
                break;
            case 250002:
                this.m = 2;
                int iN = this.f3545e.n();
                b0(iN);
                b.b.a.f.f.b(this.f3541a, "更换车主手机寻钥匙6s超时", new Object[0]);
                if (iN == 0) {
                    k0();
                    h0();
                }
                break;
            case 250003:
                this.m = 3;
                int iN2 = this.f3545e.n();
                b0(iN2);
                b.b.a.f.f.b(this.f3541a, "删单把钥匙寻钥匙6s超时", new Object[0]);
                if (iN2 == 0) {
                    k0();
                    h0();
                }
                break;
        }
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
    public void onRefresh() {
        if (this.f3545e.g() == 1) {
            this.f3545e.t();
        } else {
            ToastUtils.show(getContext(), getString(R$string.nfc_yun_cloud_net_error));
            this.f3544d.setRefreshing(false);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        BydDigitalKeyList bydDigitalKeyList = new BydDigitalKeyList();
        bydDigitalKeyList.keyItemList = this.l;
        bundle.putSerializable("nfc_key_list", bydDigitalKeyList);
        bundle.putInt("current_op", this.m);
        b.b.a.f.f.b(this.f3541a, "onSaveInstanceState-nfc_key_list:" + this.l, new Object[0]);
        b.b.a.j.b.a aVar = this.t;
        if (aVar == null || !aVar.isShowing()) {
            return;
        }
        this.t.dismiss();
        bundle.putInt("dialog_restore_bundle_key", ((Integer) this.t.h().getTag()).intValue());
    }

    @Override // b.b.a.j.d.b
    public void r(BaseListRecyclerAdapter baseListRecyclerAdapter, View view, int i2) {
        BydDigitalKeyItem bydDigitalKeyItem;
        if (baseListRecyclerAdapter == null || view == null || (bydDigitalKeyItem = (BydDigitalKeyItem) baseListRecyclerAdapter.getItem(i2)) == null || getContext() == null) {
            return;
        }
        this.n = bydDigitalKeyItem;
        int id = view.getId();
        if (id == R$id.iv_key_trash) {
            X();
        } else if (id == R$id.key_change_tv) {
            V();
        }
    }
}
