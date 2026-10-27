package com.android.car.activityresolver;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import com.android.internal.app.ResolverActivity;
import com.android.internal.app.ResolverViewPager;

/* JADX INFO: loaded from: classes2.dex */
public final class CarResolverActivity extends ResolverActivity implements ViewTreeObserver.OnGlobalLayoutListener {
    private ResolverViewPager mProfilePager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme.Translucent.NoTitleBar);
        setSafeForwardingMode(false);
        ResolverViewPager resolverViewPagerFindViewById = findViewById(android.R.id.month_view);
        this.mProfilePager = resolverViewPagerFindViewById;
        if (resolverViewPagerFindViewById != null && resolverViewPagerFindViewById.getViewTreeObserver() != null) {
            this.mProfilePager.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }
        finish();
    }

    protected void onDestroy() {
        ResolverViewPager resolverViewPager = this.mProfilePager;
        if (resolverViewPager != null && resolverViewPager.getViewTreeObserver() != null) {
            this.mProfilePager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
        super.onDestroy();
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        final ListView listView = (ListView) findViewById(android.R.id.notification_custom_view_index_tag);
        if (listView != null) {
            listView.setItemsCanFocus(true);
            for (int i = 0; i < listView.getChildCount(); i++) {
                View element = listView.getChildAt(i);
                element.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.activityresolver.-$$Lambda$CarResolverActivity$AOM9TRoth1gwhdS_-b31gpAZzmc
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        CarResolverActivity.lambda$onGlobalLayout$0(listView, view);
                    }
                });
            }
        }
    }

    static /* synthetic */ void lambda$onGlobalLayout$0(ListView listView, View view) {
        int position = listView.getPositionForView(view);
        long id = listView.getItemIdAtPosition(position);
        listView.performItemClick(view, position, id);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
