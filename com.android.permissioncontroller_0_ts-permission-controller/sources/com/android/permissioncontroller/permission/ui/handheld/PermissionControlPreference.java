package com.android.permissioncontroller.permission.ui.handheld;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.ui.LocationProviderInterceptDialog;
import com.android.permissioncontroller.permission.utils.KotlinUtilsKt;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class PermissionControlPreference extends Preference {
    private String mCaller;
    private final Context mContext;
    private boolean mEllipsizeEnd;
    private String mGranted;
    private boolean mHasNavGraph;
    private String mPackageName;
    private String mPermGroupName;
    private long mSessionId;
    private List<Integer> mSummaryIcons;
    private List<Integer> mTitleIcons;
    private boolean mUseSmallerIcon;
    private UserHandle mUser;
    private Drawable mWidgetIcon;

    public PermissionControlPreference(Context context, AppPermissionGroup group, String caller) {
        this(context, group, caller, 0L);
    }

    public PermissionControlPreference(Context context, AppPermissionGroup group, String caller, long sessionId) {
        this(context, group.getApp().packageName, group.getName(), group.getUser(), caller, sessionId, null, false);
    }

    public PermissionControlPreference(Context context, String packageName, String permGroupName, UserHandle user, String caller, long sessionId, String granted, boolean hasNavGraph) {
        super(context);
        this.mContext = context;
        this.mWidgetIcon = null;
        this.mUseSmallerIcon = false;
        this.mEllipsizeEnd = false;
        this.mTitleIcons = null;
        this.mSummaryIcons = null;
        this.mPackageName = packageName;
        this.mCaller = caller;
        this.mPermGroupName = permGroupName;
        this.mSessionId = sessionId;
        this.mUser = user;
        this.mGranted = granted;
        this.mHasNavGraph = hasNavGraph;
    }

    public void useSmallerIcon() {
        this.mUseSmallerIcon = true;
    }

    public void setEllipsizeEnd() {
        this.mEllipsizeEnd = true;
    }

    public void setGroupSummary(AppPermissionGroup group) {
        AppPermissionGroup backgroundGroup;
        if (group.hasPermissionWithBackgroundMode() && group.areRuntimePermissionsGranted() && ((backgroundGroup = group.getBackgroundPermissions()) == null || !backgroundGroup.areRuntimePermissionsGranted())) {
            setSummary(R.string.permission_subtitle_only_in_foreground);
        } else {
            setSummary("");
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(final PreferenceViewHolder holder) {
        if (this.mUseSmallerIcon) {
            ImageView icon = (ImageView) holder.findViewById(android.R.id.icon);
            icon.setMaxWidth(this.mContext.getResources().getDimensionPixelSize(R.dimen.secondary_app_icon_size));
            icon.setMaxHeight(this.mContext.getResources().getDimensionPixelSize(R.dimen.secondary_app_icon_size));
        }
        super.onBindViewHolder(holder);
        if (this.mWidgetIcon != null) {
            View widgetFrame = holder.findViewById(android.R.id.widget_frame);
            ((ImageView) widgetFrame.findViewById(R.id.icon)).setImageDrawable(this.mWidgetIcon);
        }
        if (this.mEllipsizeEnd) {
            TextView title = (TextView) holder.findViewById(android.R.id.title);
            title.setMaxLines(1);
            title.setEllipsize(TextUtils.TruncateAt.END);
        }
        setIcons(holder, this.mSummaryIcons, R.id.summary_widget_frame);
        setIcons(holder, this.mTitleIcons, R.id.title_widget_frame);
        setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionControlPreference$w7Xk9szNdvHquUCKAzSia9QGn-w
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$onBindViewHolder$0$PermissionControlPreference(holder, preference);
            }
        });
    }

    public /* synthetic */ boolean lambda$onBindViewHolder$0$PermissionControlPreference(PreferenceViewHolder holder, Preference pref) {
        if (LocationUtils.isLocationGroupAndProvider(this.mContext, this.mPermGroupName, this.mPackageName)) {
            Intent intent = new Intent(this.mContext, (Class<?>) LocationProviderInterceptDialog.class);
            intent.putExtra("android.intent.extra.PACKAGE_NAME", this.mPackageName);
            this.mContext.startActivityAsUser(intent, this.mUser);
            return true;
        }
        if (LocationUtils.isLocationGroupAndControllerExtraPackage(this.mContext, this.mPermGroupName, this.mPackageName)) {
            LocationUtils.startLocationControllerExtraPackageSettings(this.mContext, this.mUser);
            return true;
        }
        if (this.mHasNavGraph) {
            Bundle args = new Bundle();
            args.putString("android.intent.extra.PACKAGE_NAME", this.mPackageName);
            args.putString("android.intent.extra.PERMISSION_GROUP_NAME", this.mPermGroupName);
            args.putParcelable("android.intent.extra.USER", this.mUser);
            args.putString("com.android.permissioncontroller.extra.CALLER_NAME", this.mCaller);
            args.putLong(Constants.EXTRA_SESSION_ID, this.mSessionId);
            args.putString("grant_category", this.mGranted);
            KotlinUtilsKt.navigateSafe(Navigation.findNavController(holder.itemView), R.id.perm_groups_to_app, args);
            return true;
        }
        Intent intent2 = new Intent("android.intent.action.MANAGE_APP_PERMISSION");
        intent2.putExtra("android.intent.extra.PACKAGE_NAME", this.mPackageName);
        intent2.putExtra("android.intent.extra.PERMISSION_GROUP_NAME", this.mPermGroupName);
        intent2.putExtra("android.intent.extra.USER", this.mUser);
        intent2.putExtra("com.android.permissioncontroller.extra.CALLER_NAME", this.mCaller);
        intent2.putExtra(Constants.EXTRA_SESSION_ID, this.mSessionId);
        this.mContext.startActivity(intent2);
        return true;
    }

    private void setIcons(PreferenceViewHolder holder, List<Integer> icons, int frameId) {
        ViewGroup frame = (ViewGroup) holder.findViewById(frameId);
        if (icons != null && !icons.isEmpty()) {
            frame.setVisibility(0);
            frame.removeAllViews();
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(LayoutInflater.class);
            int numIcons = icons.size();
            for (int i = 0; i < numIcons; i++) {
                ViewGroup group = (ViewGroup) inflater.inflate(R.layout.title_summary_image_view, (ViewGroup) null);
                ImageView imageView = (ImageView) group.requireViewById(R.id.icon);
                imageView.setImageResource(icons.get(i).intValue());
                frame.addView(group);
            }
            return;
        }
        if (frame != null) {
            frame.setVisibility(8);
        }
    }
}
