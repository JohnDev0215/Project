package com.byd.music;

import android.app.ListActivity;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public class MusicPicker extends ListActivity implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Uri f3906b;

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if ("android.intent.action.GET_CONTENT".equals(getIntent().getAction())) {
            this.f3906b = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        } else {
            this.f3906b = getIntent().getData();
        }
        Log.w("MusicPicker", "Doesn't handle for data URI given to PICK action");
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override // android.app.ListActivity
    protected void onListItemClick(ListView listView, View view, int i, long j) {
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onRestart() {
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
    }
}
