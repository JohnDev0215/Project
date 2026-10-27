package com.android.car.developeroptions.widget;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.AppInfoBase;
import com.android.car.developeroptions.applications.appinfo.AppInfoDashboardFragment;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.ActionBarShadowController;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class EntityHeaderController {
    private int mAction1;
    private int mAction2;
    private final Activity mActivity;
    private final Context mAppContext;
    private Intent mAppNotifPrefIntent;
    private View.OnClickListener mEditOnClickListener;
    private final Fragment mFragment;
    private boolean mHasAppInfoLink;
    private final View mHeader;
    private Drawable mIcon;
    private String mIconContentDescription;
    private boolean mIsInstantApp;
    private CharSequence mLabel;
    private Lifecycle mLifecycle;
    private final int mMetricsCategory;
    private String mPackageName;
    private RecyclerView mRecyclerView;
    private CharSequence mSecondSummary;
    private CharSequence mSummary;
    private int mUid = -10000;

    public static EntityHeaderController newInstance(Activity activity, Fragment fragment, View view) {
        return new EntityHeaderController(activity, fragment, view);
    }

    private EntityHeaderController(Activity activity, Fragment fragment, View view) {
        this.mActivity = activity;
        Context applicationContext = activity.getApplicationContext();
        this.mAppContext = applicationContext;
        this.mFragment = fragment;
        this.mMetricsCategory = FeatureFactory.getFactory(applicationContext).getMetricsFeatureProvider().getMetricsCategory(fragment);
        if (view != null) {
            this.mHeader = view;
        } else {
            this.mHeader = LayoutInflater.from(fragment.getContext()).inflate(R.layout.settings_entity_header, (ViewGroup) null);
        }
    }

    public EntityHeaderController setRecyclerView(RecyclerView recyclerView, Lifecycle lifecycle) {
        this.mRecyclerView = recyclerView;
        this.mLifecycle = lifecycle;
        return this;
    }

    public EntityHeaderController setIcon(Drawable drawable) {
        if (drawable != null) {
            this.mIcon = drawable.getConstantState().newDrawable(this.mAppContext.getResources());
        }
        return this;
    }

    public EntityHeaderController setIcon(ApplicationsState.AppEntry appEntry) {
        this.mIcon = IconDrawableFactory.newInstance(this.mAppContext).getBadgedIcon(appEntry.info);
        return this;
    }

    public EntityHeaderController setLabel(CharSequence charSequence) {
        this.mLabel = charSequence;
        return this;
    }

    public EntityHeaderController setLabel(ApplicationsState.AppEntry appEntry) {
        this.mLabel = appEntry.label;
        return this;
    }

    public EntityHeaderController setSummary(CharSequence charSequence) {
        this.mSummary = charSequence;
        return this;
    }

    public EntityHeaderController setSummary(PackageInfo packageInfo) {
        if (packageInfo != null) {
            this.mSummary = packageInfo.versionName;
        }
        return this;
    }

    public EntityHeaderController setHasAppInfoLink(boolean z) {
        this.mHasAppInfoLink = z;
        return this;
    }

    public EntityHeaderController setButtonActions(int i, int i2) {
        this.mAction1 = i;
        this.mAction2 = i2;
        return this;
    }

    public EntityHeaderController setPackageName(String str) {
        this.mPackageName = str;
        return this;
    }

    public EntityHeaderController setUid(int i) {
        this.mUid = i;
        return this;
    }

    public EntityHeaderController setIsInstantApp(boolean z) {
        this.mIsInstantApp = z;
        return this;
    }

    public LayoutPreference done(Activity activity, Context context) {
        LayoutPreference layoutPreference = new LayoutPreference(context, done(activity));
        layoutPreference.setOrder(-1000);
        layoutPreference.setSelectable(false);
        layoutPreference.setKey("pref_app_header");
        layoutPreference.setAllowDividerBelow(true);
        return layoutPreference;
    }

    public View done(Activity activity, boolean z) {
        styleActionBar(activity);
        ImageView imageView = (ImageView) this.mHeader.findViewById(R.id.entity_header_icon);
        if (imageView != null) {
            imageView.setImageDrawable(this.mIcon);
            imageView.setContentDescription(this.mIconContentDescription);
        }
        setText(R.id.entity_header_title, this.mLabel);
        setText(R.id.entity_header_summary, this.mSummary);
        setText(R.id.entity_header_second_summary, this.mSecondSummary);
        if (this.mIsInstantApp) {
            setText(R.id.install_type, this.mHeader.getResources().getString(R.string.install_type_instant));
        }
        if (z) {
            bindHeaderButtons();
        }
        return this.mHeader;
    }

    public EntityHeaderController bindHeaderButtons() {
        View viewFindViewById = this.mHeader.findViewById(R.id.entity_header_content);
        ImageButton imageButton = (ImageButton) this.mHeader.findViewById(android.R.id.button1);
        ImageButton imageButton2 = (ImageButton) this.mHeader.findViewById(android.R.id.button2);
        bindAppInfoLink(viewFindViewById);
        bindButton(imageButton, this.mAction1);
        bindButton(imageButton2, this.mAction2);
        return this;
    }

    private void bindAppInfoLink(View view) {
        String str;
        if (this.mHasAppInfoLink) {
            if (view == null || (str = this.mPackageName) == null || str.equals("os") || this.mUid == -10000) {
                Log.w("AppDetailFeature", "Missing ingredients to build app info link, skip");
            } else {
                view.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.widget.EntityHeaderController.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        AppInfoBase.startAppInfoFragment(AppInfoDashboardFragment.class, R.string.application_info_label, EntityHeaderController.this.mPackageName, EntityHeaderController.this.mUid, EntityHeaderController.this.mFragment, 0, EntityHeaderController.this.mMetricsCategory);
                    }
                });
            }
        }
    }

    public EntityHeaderController styleActionBar(Activity activity) {
        Lifecycle lifecycle;
        if (activity == null) {
            Log.w("AppDetailFeature", "No activity, cannot style actionbar.");
            return this;
        }
        ActionBar actionBar = activity.getActionBar();
        if (actionBar == null) {
            Log.w("AppDetailFeature", "No actionbar, cannot style actionbar.");
            return this;
        }
        actionBar.setBackgroundDrawable(new ColorDrawable(Utils.getColorAttrDefaultColor(activity, android.R.attr.colorPrimaryDark)));
        actionBar.setElevation(0.0f);
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && (lifecycle = this.mLifecycle) != null) {
            ActionBarShadowController.attachToView(this.mActivity, lifecycle, recyclerView);
        }
        return this;
    }

    View done(Activity activity) {
        return done(activity, true);
    }

    private void bindButton(ImageButton imageButton, int i) {
        if (imageButton == null) {
            return;
        }
        if (i == 0) {
            imageButton.setVisibility(8);
            return;
        }
        if (i == 1) {
            if (this.mAppNotifPrefIntent == null) {
                imageButton.setVisibility(8);
                return;
            } else {
                imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.widget.-$$Lambda$EntityHeaderController$Mm_2e7Wz7bBIqpq-OOauzHeJU9Y
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$bindButton$0$EntityHeaderController(view);
                    }
                });
                imageButton.setVisibility(0);
                return;
            }
        }
        if (i != 2) {
            return;
        }
        if (this.mEditOnClickListener == null) {
            imageButton.setVisibility(8);
            return;
        }
        imageButton.setImageResource(android.R.drawable.ic_media_route_connecting_dark_01_mtrl);
        imageButton.setVisibility(0);
        imageButton.setOnClickListener(this.mEditOnClickListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$bindButton$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$bindButton$0$EntityHeaderController(View view) {
        FeatureFactory.getFactory(this.mAppContext).getMetricsFeatureProvider().action(0, 1016, this.mMetricsCategory, null, 0);
        this.mFragment.startActivity(this.mAppNotifPrefIntent);
    }

    private void setText(int i, CharSequence charSequence) {
        TextView textView = (TextView) this.mHeader.findViewById(i);
        if (textView != null) {
            textView.setText(charSequence);
            textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        }
    }
}
