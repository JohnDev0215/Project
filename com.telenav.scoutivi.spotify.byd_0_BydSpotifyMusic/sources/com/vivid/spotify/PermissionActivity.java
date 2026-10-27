package com.vivid.spotify;

import android.app.Dialog;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class PermissionActivity extends com.vivid.spotify.common.base.a {
    private static final String E = "com.vivid.spotify.PermissionActivity";
    private androidx.activity.result.b<String[]> B;
    private com.vivid.spotify.common.base.l C;
    private PermissionViewModel D;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X(Map map) {
        if (this.C != null && !map.isEmpty()) {
            this.C.a(map);
        }
        if (map != null) {
            String str = E;
            h3.i.b(str).b("permission result:" + map, new Object[0]);
            Log.i(str, "permission result:" + map);
        } else {
            String str2 = E;
            h3.i.b(str2).b("result null", new Object[0]);
            Log.i(str2, "result null");
        }
        com.vivid.spotify.common.base.l lVar = this.C;
        if (lVar == null || !lVar.e()) {
            finish();
        } else {
            this.D.hasBeenRefused.r(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y(Dialog dialog, View view) {
        com.vivid.spotify.common.base.l lVar = this.C;
        if (lVar != null) {
            lVar.b(this);
        }
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z(DialogInterface dialogInterface) {
        moveTaskToBack(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a0(Dialog dialog, View view) {
        moveTaskToBack(true);
        dialog.dismiss();
    }

    public void W() {
        com.vivid.spotify.common.base.l lVar = this.C;
        if (lVar != null) {
            Set<String> setC = lVar.c(this);
            if (setC == null || setC.isEmpty()) {
                String str = E;
                h3.i.b(str).b("checkPermission : null", new Object[0]);
                Log.i(str, "checkPermission : null");
                finish();
                return;
            }
            this.B.a((String[]) setC.toArray(new String[setC.size()]));
            String str2 = E;
            h3.i.b(str2).b("checkPermission : " + setC, new Object[0]);
            Log.i(str2, "checkPermission : " + setC);
        }
    }

    public void b0(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(v.dialog);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 17;
        dialog.getWindow().setBackgroundDrawableResource(q.colorTransparent);
        dialog.getWindow().setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(false);
        dialog.findViewById(t.btn_sure).setOnClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f5315e.Y(dialog, view);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.vivid.spotify.l
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f5312e.Z(dialogInterface);
            }
        });
        dialog.findViewById(t.btn_cancel).setOnClickListener(new View.OnClickListener() { // from class: com.vivid.spotify.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f5313e.a0(dialog, view);
            }
        });
        dialog.show();
    }

    @Override // androidx.fragment.app.d, androidx.activity.ComponentActivity, w.f, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(v.act_permission);
        this.D = (PermissionViewModel) androidx.lifecycle.c0.e(this).a(PermissionViewModel.class);
        String str = E;
        h3.i.b(str).b("onCreate", new Object[0]);
        Log.i(str, "onCreate");
        this.B = s(new d.b(), new androidx.activity.result.a() { // from class: com.vivid.spotify.o
            @Override // androidx.activity.result.a
            public final void a(Object obj) {
                this.f5356a.X((Map) obj);
            }
        });
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        String str = E;
        h3.i.b(str).b("onDestroy", new Object[0]);
        Log.i(str, "onDestroy");
        this.B.c();
        this.C = null;
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onPause() {
        super.onPause();
        String str = E;
        h3.i.b(str).b("onPause", new Object[0]);
        Log.i(str, "onPause");
    }

    @Override // androidx.fragment.app.d, android.app.Activity
    protected void onResume() {
        super.onResume();
        String str = E;
        h3.i.b(str).b("onResume", new Object[0]);
        Log.i(str, "onResume");
        ComponentCallbacks2 application = getApplication();
        if (application instanceof com.vivid.spotify.common.base.l) {
            com.vivid.spotify.common.base.l lVar = (com.vivid.spotify.common.base.l) application;
            if (lVar.e()) {
                this.C = lVar;
                if (this.D.hasBeenRefused.q()) {
                    b0(this);
                    return;
                } else {
                    W();
                    return;
                }
            }
        }
        finish();
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity
    protected void onStart() {
        super.onStart();
        String str = E;
        h3.i.b(str).b("onStart", new Object[0]);
        Log.i(str, "onStart");
    }

    @Override // androidx.appcompat.app.c, androidx.fragment.app.d, android.app.Activity
    protected void onStop() {
        super.onStop();
        String str = E;
        h3.i.b(str).b("onStop", new Object[0]);
        Log.i(str, "onStop");
    }
}
