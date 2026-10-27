package androidx.appcompat.widget;

import android.content.Context;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.view.ActionProvider;

/* JADX INFO: loaded from: classes.dex */
public class ShareActionProvider extends ActionProvider {
    public final int d;
    public final Context e;
    public final String f;
    public OnShareTargetSelectedListener g;
    public ActivityChooserModel.OnChooseActivityListener h;

    public interface OnShareTargetSelectedListener {
    }

    public class ShareActivityChooserModelPolicy implements ActivityChooserModel.OnChooseActivityListener {
        public ShareActivityChooserModelPolicy(ShareActionProvider shareActionProvider) {
        }
    }

    public class ShareMenuItemOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        public ShareMenuItemOnMenuItemClickListener() {
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            ShareActionProvider shareActionProvider = ShareActionProvider.this;
            ActivityChooserModel activityChooserModelB = ActivityChooserModel.b(shareActionProvider.e, shareActionProvider.f);
            menuItem.getItemId();
            synchronized (activityChooserModelB.a) {
            }
            return true;
        }
    }

    public ShareActionProvider(Context context) {
        super(context);
        this.d = 4;
        new ShareMenuItemOnMenuItemClickListener();
        this.f = "share_history.xml";
        this.e = context;
    }

    @Override // androidx.core.view.ActionProvider
    public final View c() {
        Context context = this.e;
        ActivityChooserView activityChooserView = new ActivityChooserView(context);
        if (!activityChooserView.isInEditMode()) {
            activityChooserView.setActivityChooserModel(ActivityChooserModel.b(context, this.f));
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.actionModeShareDrawable, typedValue, true);
        activityChooserView.setExpandActivityOverflowButtonDrawable(AppCompatResources.a(context, typedValue.resourceId));
        activityChooserView.setProvider(this);
        activityChooserView.setDefaultActionButtonContentDescription(R.string.abc_shareactionprovider_share_with_application);
        activityChooserView.setExpandActivityOverflowButtonContentDescription(R.string.abc_shareactionprovider_share_with);
        return activityChooserView;
    }

    @Override // androidx.core.view.ActionProvider
    public final void f(SubMenuBuilder subMenuBuilder) {
        subMenuBuilder.clear();
        Context context = this.e;
        ActivityChooserModel activityChooserModelB = ActivityChooserModel.b(context, this.f);
        context.getPackageManager();
        int iD = activityChooserModelB.d();
        int iMin = Math.min(iD, this.d);
        if (iMin > 0) {
            activityChooserModelB.c(0);
            throw null;
        }
        if (iMin < iD) {
            subMenuBuilder.addSubMenu(0, iMin, iMin, context.getString(R.string.abc_activity_chooser_view_see_all));
            if (iD <= 0) {
                return;
            }
            activityChooserModelB.c(0);
            throw null;
        }
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener onShareTargetSelectedListener) {
        this.g = onShareTargetSelectedListener;
        if (onShareTargetSelectedListener == null) {
            return;
        }
        if (this.h == null) {
            this.h = new ShareActivityChooserModelPolicy(this);
        }
        ActivityChooserModel.b(this.e, this.f).setOnChooseActivityListener(this.h);
    }
}
