package com.byd.trafficmonitor;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c.a.a.h.b.c;
import c.a.d.f;
import c.a.d.o.a;
import com.bydux.api.R;
import com.bydux.api.common.views.window.BydActivity;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BuyForFriendActivity extends BydActivity {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f4373b;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c(View view) {
        finish();
    }

    public final void a() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: c.a.d.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f3557b.c(view);
            }
        });
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 5; i2++) {
            arrayList.add(new a());
        }
        f fVar = new f();
        this.f4373b.setAdapter(fVar);
        this.f4373b.setLayoutManager(new LinearLayoutManager(this));
        this.f4373b.h(new c());
        fVar.w(arrayList);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        getDelegate().E(2);
        super.onAttachedToWindow();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_buy_for_friend);
        this.f4373b = (RecyclerView) findViewById(R.id.rv_car_list);
        a();
    }
}
