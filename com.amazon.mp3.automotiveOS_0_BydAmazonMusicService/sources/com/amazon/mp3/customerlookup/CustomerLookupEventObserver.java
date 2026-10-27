package com.amazon.mp3.customerlookup;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.actionbar.ActionBarProvider;
import com.amazon.mp3.actionbar.view.ActionBarView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.connect.customerlookup.CustomerLookupEvent;
import com.amazon.music.connect.customerlookup.CustomerLookupFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerLookupEventObserver.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/customerlookup/CustomerLookupEventObserver;", "Landroidx/lifecycle/Observer;", "Lcom/amazon/music/connect/customerlookup/CustomerLookupEvent;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "pageId", "", "(Landroidx/fragment/app/FragmentActivity;I)V", "TAG", "", "logger", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "lookupNavigator", "Lcom/amazon/mp3/customerlookup/CustomerLookupNavigation;", "title", "getActionBarProvider", "Lcom/amazon/mp3/actionbar/ActionBarProvider;", "onChanged", "", NotificationCompat.CATEGORY_EVENT, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CustomerLookupEventObserver implements Observer<CustomerLookupEvent> {
    private String TAG;
    private final FragmentActivity activity;
    private final Logger logger;
    private final CustomerLookupNavigation lookupNavigator;
    private final int pageId;
    private final String title;

    /* JADX INFO: compiled from: CustomerLookupEventObserver.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CustomerLookupEvent.FragmentAction.values().length];
            iArr[CustomerLookupEvent.FragmentAction.PROFILE_NAVIGATION.ordinal()] = 1;
            iArr[CustomerLookupEvent.FragmentAction.FRAGMENT_RESUMED.ordinal()] = 2;
            iArr[CustomerLookupEvent.FragmentAction.FRAGMENT_DESTROYED.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CustomerLookupEventObserver(FragmentActivity activity, int i) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.pageId = i;
        String simpleName = Reflection.getOrCreateKotlinClass(CustomerLookupEventObserver.class).getSimpleName();
        this.TAG = simpleName;
        this.logger = LoggerFactory.getLogger(simpleName);
        this.lookupNavigator = new CustomerLookupNavigation(activity);
        String string = activity.getResources().getString(R.string.dmusic_customer_lookup);
        Intrinsics.checkNotNullExpressionValue(string, "activity.resources.getSt…g.dmusic_customer_lookup)");
        this.title = string;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(CustomerLookupEvent event) {
        ActionBarProvider actionBarProvider;
        if (event != null && event.getProcessed()) {
            return;
        }
        if (event != null) {
            event.setProcessed(true);
        }
        CustomerLookupEvent.FragmentAction fragmentAction = event == null ? null : event.getFragmentAction();
        int i = fragmentAction == null ? -1 : WhenMappings.$EnumSwitchMapping$0[fragmentAction.ordinal()];
        if (i == 1) {
            this.lookupNavigator.loadCustomerProfile(event.getProfileUrl());
            return;
        }
        if (i != 2) {
            if (i == 3 && (actionBarProvider = getActionBarProvider()) != null) {
                actionBarProvider.removeHeaderView();
                return;
            }
            return;
        }
        ActionBarProvider actionBarProvider2 = getActionBarProvider();
        ActionBarView actionBarView = new ActionBarView(this.activity, this.title, false, false);
        if (actionBarProvider2 != null) {
            actionBarProvider2.setHeaderView(actionBarView);
        }
        if (actionBarProvider2 == null) {
            return;
        }
        actionBarProvider2.requestHomeButtonAsBack();
    }

    private final ActionBarProvider getActionBarProvider() {
        Fragment fragmentFindFragmentById = this.activity.getSupportFragmentManager().findFragmentById(this.pageId);
        if ((this.activity instanceof ActionBarProvider) && (fragmentFindFragmentById instanceof CustomerLookupFragment) && fragmentFindFragmentById.isVisible()) {
            return (ActionBarProvider) this.activity;
        }
        return null;
    }
}
