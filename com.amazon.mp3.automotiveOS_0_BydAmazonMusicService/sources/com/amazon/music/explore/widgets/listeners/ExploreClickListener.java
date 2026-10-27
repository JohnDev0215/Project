package com.amazon.music.explore.widgets.listeners;

import CoreInterface.v1_0.Method;
import android.view.View;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreClickListener implements View.OnClickListener {
    private final MethodsDispatcher methodsDispatcher;
    private final List<Method> onItemSelected;
    private final String ownerId;

    public ExploreClickListener(String str, MethodsDispatcher methodsDispatcher, List<Method> list) {
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
        this.onItemSelected = list;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.methodsDispatcher.dispatchMethods(this.ownerId, this.onItemSelected);
    }
}
