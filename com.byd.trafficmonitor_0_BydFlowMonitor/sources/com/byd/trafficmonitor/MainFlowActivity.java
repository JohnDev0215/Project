package com.byd.trafficmonitor;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import c.a.d.n.a;
import c.a.d.n.b;
import c.a.d.s.j;
import com.bydux.api.R;
import com.bydux.api.common.views.window.BydActivity;
import com.bydux.api.common.views.window.BydFragment;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MainFlowActivity extends BydActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public LinearLayout f4391b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LinearLayout f4392c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageView f4393d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageView f4394e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4395f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b f4396g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public a f4397h;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(View view) {
        finish();
    }

    public void a() {
        if (this.f4392c.isSelected()) {
            return;
        }
        a aVar = this.f4397h;
        h(aVar, aVar.c());
        g(false);
    }

    public void b() {
        if (this.f4391b.isSelected()) {
            return;
        }
        b bVar = this.f4396g;
        h(bVar, bVar.f());
        g(true);
    }

    public void c() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(67108864);
        window.setStatusBarColor(0);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getDecorView().setSystemUiVisibility(1792);
    }

    public final void d() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: c.a.d.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3558b.f(view);
            }
        });
        boolean zEquals = j.a(this, "has_voice", "0").equals("1");
        this.f4395f = zEquals;
        if (!zEquals) {
            findViewById(R.id.ll_left_menu).setVisibility(8);
            b bVarO = b.o();
            this.f4396g = bVarO;
            h(bVarO, bVarO.f());
            return;
        }
        this.f4391b = (LinearLayout) findViewById(R.id.service_5g_ll);
        this.f4392c = (LinearLayout) findViewById(R.id.service_icall_ll);
        this.f4393d = (ImageView) findViewById(R.id.iv_5g);
        this.f4394e = (ImageView) findViewById(R.id.iv_icall);
        this.f4391b.setOnClickListener(this);
        this.f4392c.setOnClickListener(this);
        this.f4396g = b.o();
        this.f4397h = a.g();
        b();
    }

    public final void g(boolean z) {
        this.f4391b.setSelected(z);
        this.f4393d.setSelected(z);
        this.f4392c.setSelected(!z);
        this.f4394e.setSelected(!z);
    }

    public void h(BydFragment bydFragment, String str) {
        List<Fragment> listD = c.a.a.g.b.d(getSupportFragmentManager());
        Fragment fragmentC = c.a.a.g.b.c(getSupportFragmentManager(), str);
        if (fragmentC != null) {
            listD.remove(fragmentC);
            c.a.a.g.b.j(fragmentC, listD);
        } else {
            Iterator<Fragment> it = listD.iterator();
            while (it.hasNext()) {
                c.a.a.g.b.e(it.next());
            }
            c.a.a.g.b.a(getSupportFragmentManager(), bydFragment, R.id.flow_fl_container, str);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.service_5g_ll) {
            b();
        } else if (view.getId() == R.id.service_icall_ll) {
            a();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
        setContentView(R.layout.activity_flow_main);
        d();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.action_buy_for_friend && itemId != R.id.action_buy_for_friend_sub) {
            return super.onOptionsItemSelected(menuItem);
        }
        Log.i("MainFlowActivity", " onOptionsItemSelected is click");
        return true;
    }
}
