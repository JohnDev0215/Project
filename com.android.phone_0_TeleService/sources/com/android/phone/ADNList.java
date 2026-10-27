package com.android.phone;

import android.app.ListActivity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ADNList extends ListActivity {
    private static final String[] COLUMN_NAMES = {"name", "number", "emails"};
    private static final int[] VIEW_NAMES = {android.R.id.text1, android.R.id.text2};
    protected CursorAdapter mCursorAdapter;
    private TextView mEmptyText;
    protected QueryHandler mQueryHandler;
    protected Cursor mCursor = null;
    protected int mInitialSelection = -1;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(5);
        setContentView(R.layout.adn_list);
        this.mEmptyText = (TextView) findViewById(android.R.id.empty);
        this.mQueryHandler = new QueryHandler(getContentResolver());
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        query();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        Cursor cursor = this.mCursor;
        if (cursor != null) {
            cursor.deactivate();
        }
    }

    protected Uri resolveIntent() {
        Intent intent = getIntent();
        if (intent.getData() == null) {
            intent.setData(Uri.parse("content://icc/adn"));
        }
        return intent.getData();
    }

    private void query() {
        this.mQueryHandler.startQuery(0, null, resolveIntent(), COLUMN_NAMES, null, null, null);
        displayProgress(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reQuery() {
        query();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdapter() {
        CursorAdapter cursorAdapter = this.mCursorAdapter;
        if (cursorAdapter == null) {
            CursorAdapter cursorAdapterNewAdapter = newAdapter();
            this.mCursorAdapter = cursorAdapterNewAdapter;
            setListAdapter(cursorAdapterNewAdapter);
        } else {
            cursorAdapter.changeCursor(this.mCursor);
        }
        int i = this.mInitialSelection;
        if (i < 0 || i >= this.mCursorAdapter.getCount()) {
            return;
        }
        setSelection(this.mInitialSelection);
        getListView().setFocusableInTouchMode(true);
        getListView().requestFocus();
    }

    protected CursorAdapter newAdapter() {
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, this.mCursor, COLUMN_NAMES, VIEW_NAMES);
        simpleCursorAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder(this) { // from class: com.android.phone.ADNList.1
            @Override // android.widget.SimpleCursorAdapter.ViewBinder
            public boolean setViewValue(View view, Cursor cursor, int i) {
                view.setTextAlignment(5);
                if (i != 1) {
                    return false;
                }
                String string = cursor.getString(1);
                if (string != null) {
                    string = BidiFormatter.getInstance().unicodeWrap(string, TextDirectionHeuristics.LTR, true);
                }
                if (view instanceof TextView) {
                    ((TextView) view).setText(string);
                }
                return true;
            }
        });
        return simpleCursorAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayProgress(boolean z) {
        this.mEmptyText.setText(z ? R.string.simContacts_emptyLoading : R.string.simContacts_empty);
        getWindow().setFeatureInt(5, z ? -1 : -2);
    }

    private class QueryHandler extends AsyncQueryHandler {
        public QueryHandler(ContentResolver contentResolver) {
            super(contentResolver);
        }

        @Override // android.content.AsyncQueryHandler
        protected void onQueryComplete(int i, Object obj, Cursor cursor) {
            ADNList aDNList = ADNList.this;
            aDNList.mCursor = cursor;
            aDNList.setAdapter();
            ADNList.this.displayProgress(false);
            ADNList.this.invalidateOptionsMenu();
        }

        @Override // android.content.AsyncQueryHandler
        protected void onInsertComplete(int i, Object obj, Uri uri) {
            ADNList.this.reQuery();
        }

        @Override // android.content.AsyncQueryHandler
        protected void onUpdateComplete(int i, Object obj, int i2) {
            ADNList.this.reQuery();
        }

        @Override // android.content.AsyncQueryHandler
        protected void onDeleteComplete(int i, Object obj, int i2) {
            ADNList.this.reQuery();
        }
    }
}
