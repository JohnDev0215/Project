package com.byd.digitalkey.activity;

import android.content.Intent;
import android.hardware.bydauto.digital.tsimpl.DigitalKeyBean;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import b.b.a.f.f;
import b.b.a.h.g;
import b.b.a.h.i;
import b.b.j.b;
import b.b.j.c;
import com.byd.base.view.BaseTitleActivity;
import com.byd.digitalkey.R$drawable;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.byd.digitalkey.R$string;
import com.byd.digitalkey.adapter.BydDigitalKeyItem;
import com.byd.digitalkey.fragment.DigitalKeyFragment;
import com.byd.digitalkey.fragment.DigitalKeyResultEmptyFragment;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DigitalKeyActivity extends BaseTitleActivity {
    public static final String i = DigitalKeyActivity.class.getSimpleName();
    public DigitalKeyResultEmptyFragment j;
    public DigitalKeyFragment k;
    public c l;
    public final b m = new a();

    public class a extends b.b.j.b {

        /* JADX INFO: renamed from: com.byd.digitalkey.activity.DigitalKeyActivity$a$a, reason: collision with other inner class name */
        public class RunnableC0096a implements Runnable {
            public RunnableC0096a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (DigitalKeyActivity.this.j == null) {
                    DigitalKeyActivity.this.j = DigitalKeyResultEmptyFragment.N();
                }
                g.l(DigitalKeyActivity.this.getSupportFragmentManager(), DigitalKeyActivity.this.j, R$id.digital_key_frame, DigitalKeyActivity.this.j.J());
                if (DigitalKeyActivity.this.k != null) {
                    g.j(DigitalKeyActivity.this.k);
                }
            }
        }

        public class b implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ List f3522a;

            public b(List list) {
                this.f3522a = list;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (DigitalKeyActivity.this.k == null) {
                    DigitalKeyActivity.this.k = new DigitalKeyFragment();
                }
                DigitalKeyActivity.this.k.i0(this.f3522a);
                g.l(DigitalKeyActivity.this.getSupportFragmentManager(), DigitalKeyActivity.this.k, R$id.digital_key_frame, DigitalKeyActivity.this.k.a0());
                if (DigitalKeyActivity.this.j != null) {
                    g.j(DigitalKeyActivity.this.j);
                }
            }
        }

        public a() {
        }

        @Override // b.b.j.b, android.hardware.bydauto.digital.tsimpl.IDigitalKeyTboxListener
        public void onKeyListResponse(int i, List<DigitalKeyBean> list) {
            super.onKeyListResponse(i, list);
            if (list == null || list.size() == 0) {
                i.b(DigitalKeyActivity.this, "nfc_key_list_cache", "");
                DigitalKeyActivity.this.runOnUiThread(new RunnableC0096a());
            } else {
                DigitalKeyActivity.this.runOnUiThread(new b(DigitalKeyActivity.this.B(list)));
            }
        }
    }

    public final void A() {
        r(0, 0, 8);
        u(0, 0, 8);
        q(R$drawable.icon_dark_public_help);
        t(R$drawable.icon_dark_navigation_setting);
    }

    public List<BydDigitalKeyItem> B(List<DigitalKeyBean> list) {
        ArrayList arrayList = new ArrayList();
        for (DigitalKeyBean digitalKeyBean : list) {
            BydDigitalKeyItem bydDigitalKeyItem = new BydDigitalKeyItem();
            bydDigitalKeyItem.setUserPro(digitalKeyBean.getUserPro());
            bydDigitalKeyItem.setUserPhone(digitalKeyBean.getUserPhone());
            bydDigitalKeyItem.setKeyType(digitalKeyBean.getKeyType());
            bydDigitalKeyItem.setKeyNo(digitalKeyBean.getKeyNo());
            bydDigitalKeyItem.setKeyId(digitalKeyBean.getKeyId());
            bydDigitalKeyItem.setKeyIdArray(digitalKeyBean.getKeyIdArray());
            bydDigitalKeyItem.setKeyState(digitalKeyBean.getKeyState());
            bydDigitalKeyItem.setKeyNameStr(digitalKeyBean.getKeyNameStr());
            bydDigitalKeyItem.setExpireTime(digitalKeyBean.getExpireTime());
            bydDigitalKeyItem.setDeviceModelStr(digitalKeyBean.getDeviceModelStr());
            arrayList.add(bydDigitalKeyItem);
        }
        f.b(i, "trans2BydDigitalKeyItem:" + arrayList, new Object[0]);
        z(arrayList);
        return arrayList;
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public int f() {
        BYDAutoStudy.getInstance(this).getDigtalKeySupport();
        return R$string.nfc_key_mode_nfc;
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public void n() {
        startActivity(new Intent(this, (Class<?>) UserHelpActivity.class));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        DigitalKeyFragment digitalKeyFragment;
        DigitalKeyResultEmptyFragment digitalKeyResultEmptyFragment;
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1) {
            if (i3 == 2 && (digitalKeyResultEmptyFragment = this.j) != null) {
                digitalKeyResultEmptyFragment.U();
            } else {
                if (i3 != 3 || (digitalKeyFragment = this.k) == null) {
                    return;
                }
                digitalKeyFragment.o0();
            }
        }
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.activity_digital_key);
        A();
        f.b(i, "onCreate:" + this, new Object[0]);
        c cVarI = c.i(this);
        this.l = cVarI;
        cVarI.t();
        this.l.w(this.m);
        String strA = i.a(this, "nfc_key_list_cache");
        if (TextUtils.isEmpty(strA)) {
            this.j = DigitalKeyResultEmptyFragment.N();
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            DigitalKeyResultEmptyFragment digitalKeyResultEmptyFragment = this.j;
            g.a(supportFragmentManager, digitalKeyResultEmptyFragment, R$id.digital_key_frame, digitalKeyResultEmptyFragment.J());
            return;
        }
        List<BydDigitalKeyItem> array = b.a.a.a.parseArray(strA, BydDigitalKeyItem.class);
        if (this.k == null) {
            this.k = new DigitalKeyFragment();
        }
        this.k.i0(array);
        FragmentManager supportFragmentManager2 = getSupportFragmentManager();
        DigitalKeyFragment digitalKeyFragment = this.k;
        g.a(supportFragmentManager2, digitalKeyFragment, R$id.digital_key_frame, digitalKeyFragment.a0());
    }

    @Override // com.byd.base.view.BaseTitleActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.l.D(this.m);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.l.t();
        f.b(i, "onNewIntent:" + this, new Object[0]);
    }

    @Override // com.byd.base.view.BaseTitleActivity
    public void p() {
        startActivity(new Intent(this, (Class<?>) DigitalKeySettingActivity.class));
    }

    public final void z(List<BydDigitalKeyItem> list) {
        i.b(this, "nfc_key_list_cache", b.a.a.a.toJSONString(list));
    }
}
