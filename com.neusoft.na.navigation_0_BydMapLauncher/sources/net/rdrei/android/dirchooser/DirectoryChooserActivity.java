package net.rdrei.android.dirchooser;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import net.rdrei.android.dirchooser.DirectoryChooserFragment;

/* JADX INFO: loaded from: classes3.dex */
public class DirectoryChooserActivity extends Activity implements DirectoryChooserFragment.OnFragmentInteractionListener {
    public static final String EXTRA_INITIAL_DIRECTORY = "initial_directory";
    public static final String EXTRA_NEW_DIR_NAME = "directory_name";
    public static final int RESULT_CODE_DIR_SELECTED = 1;
    public static final String RESULT_SELECTED_DIR = "selected_dir";

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setupActionBar();
        setContentView(R.layout.directory_chooser_activity);
        String stringExtra = getIntent().getStringExtra(EXTRA_NEW_DIR_NAME);
        String stringExtra2 = getIntent().getStringExtra(EXTRA_INITIAL_DIRECTORY);
        if (stringExtra == null) {
            throw new IllegalArgumentException("You must provide EXTRA_NEW_DIR_NAME when starting the DirectoryChooserActivity.");
        }
        if (bundle == null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().add(R.id.main, DirectoryChooserFragment.newInstance(stringExtra, stringExtra2)).commit();
        }
    }

    void setupActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            setResult(0);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // net.rdrei.android.dirchooser.DirectoryChooserFragment.OnFragmentInteractionListener
    public void onSelectDirectory(String str) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_SELECTED_DIR, str);
        setResult(1, intent);
        finish();
    }

    @Override // net.rdrei.android.dirchooser.DirectoryChooserFragment.OnFragmentInteractionListener
    public void onCancelChooser() {
        setResult(0);
        finish();
    }
}
