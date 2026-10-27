package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;

/* JADX INFO: loaded from: classes3.dex */
public class EmptyFilterAdapter extends CursorAdapter {
    private MatrixCursor mCursor;
    private View mView;

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
    }

    public EmptyFilterAdapter(final Context context, final String filter, int errorStringId) {
        this(context, filter, errorStringId, true);
    }

    public EmptyFilterAdapter(final Context context, final String filter, boolean showStoreLink) {
        this(context, filter, R.string.dmusic_library_empty_filter_no_music_found, showStoreLink);
    }

    public EmptyFilterAdapter(final Context context, final String filter, int errorStringId, boolean showStoreLink) {
        super(context, null);
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id"});
        this.mCursor = matrixCursor;
        matrixCursor.addRow(new Object[]{-1L});
        String string = context.getString(R.string.dmusic_library_empty_filter_shop_store, filter);
        View viewInflate = View.inflate(context, R.layout.library_list_activity_no_results, null);
        this.mView = viewInflate;
        ((TextView) viewInflate.findViewById(R.id.EmptySearchNoResultsError)).setText(errorStringId);
        TextView textView = (TextView) this.mView.findViewById(R.id.EmptySearchShopStore);
        textView.setVisibility(showStoreLink ? 0 : 8);
        if (showStoreLink) {
            if (AmazonApplication.getCapabilities().isStoreSupported()) {
                textView.setText(string);
                this.mView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.adapter.EmptyFilterAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View v) {
                        Bundle bundleBundleForSearchString = HTMLStoreActivityFactory.bundleForSearchString(filter, 0);
                        Context context2 = context;
                        context2.startActivity(HTMLStoreActivityFactory.intentForSearch(context2, bundleBundleForSearchString));
                        MetricsLogger.clickedOnStoreLink(StoreLinkType.ANY);
                        UserInteractionAppEvent.builder("searchMoreStore").publish();
                    }
                });
            } else {
                textView.setVisibility(8);
            }
        }
        changeCursor(this.mCursor);
    }

    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return this.mView;
    }
}
