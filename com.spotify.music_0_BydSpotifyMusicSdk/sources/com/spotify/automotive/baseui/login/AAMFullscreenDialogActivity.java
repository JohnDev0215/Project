package com.spotify.automotive.baseui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.spotify.music.R;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.a;
import p.ed1;
import p.s;
import p.t7;
import p.y3;

/* JADX INFO: loaded from: classes.dex */
public final class AAMFullscreenDialogActivity extends t7 {
    public static final /* synthetic */ int H = 0;
    public final a G = new a();

    @Override // p.zw0, androidx.activity.a, p.wv, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("title");
        String stringExtra2 = intent.getStringExtra("content");
        setContentView(R.layout.activity_fullscreen_dialog);
        TextView textView = (TextView) findViewById(R.id.misc_title);
        TextView textView2 = (TextView) findViewById(R.id.misc_desc);
        textView.setText(stringExtra);
        int color = getResources().getColor(R.color.green);
        textView2.setText(stringExtra2);
        textView2.setLinkTextColor(color);
        ((Toolbar) findViewById(R.id.toolbar)).setNavigationOnClickListener(new s(this, 0));
    }

    @Override // p.zw0, android.app.Activity
    public final void onPause() {
        super.onPause();
        this.G.d();
    }

    @Override // p.zw0, android.app.Activity
    public final void onResume() {
        super.onResume();
        new y3(this, 1);
        this.G.c(Observable.empty().subscribe(new com.spotify.automotive.auth.a(8, this), new ed1(9)));
    }
}
