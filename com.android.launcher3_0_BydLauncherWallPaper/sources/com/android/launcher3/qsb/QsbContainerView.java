package com.android.launcher3.qsb;

import android.app.SearchManager;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.launcher3.AppWidgetResizeFrame;
import com.android.launcher3.InvariantDeviceProfile;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.graphics.FragmentWithPreview;
import com.android.launcher3.qsb.QsbContainerView;

/* JADX INFO: loaded from: classes4.dex */
public class QsbContainerView extends FrameLayout {

    public interface WidgetViewFactory {
        QsbWidgetHostView newView(Context context);
    }

    public QsbContainerView(Context context) {
        super(context);
    }

    public QsbContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QsbContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.view.View
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(0, 0, 0, 0);
    }

    protected void setPaddingUnchecked(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
    }

    public static class QsbFragment extends FragmentWithPreview {
        public static final int QSB_WIDGET_HOST_ID = 1026;
        private static final int REQUEST_BIND_QSB = 1;
        protected String mKeyWidgetId = "qsb_widget_id";
        private int mOrientation;
        private QsbWidgetHostView mQsb;
        private QsbWidgetHost mQsbWidgetHost;
        private AppWidgetProviderInfo mWidgetInfo;
        private FrameLayout mWrapper;

        public boolean isQsbEnabled() {
            return false;
        }

        @Override // com.android.launcher3.graphics.FragmentWithPreview
        public void onInit(Bundle savedInstanceState) {
            this.mQsbWidgetHost = createHost();
            this.mOrientation = getContext().getResources().getConfiguration().orientation;
        }

        protected QsbWidgetHost createHost() {
            return new QsbWidgetHost(getContext(), 1026, new WidgetViewFactory() { // from class: com.android.launcher3.qsb.-$$Lambda$QsbContainerView$QsbFragment$NntLzMG-_V9JH7e31aiJhm30VSM
                @Override // com.android.launcher3.qsb.QsbContainerView.WidgetViewFactory
                public final QsbWidgetHostView newView(Context context) {
                    return QsbContainerView.QsbFragment.lambda$createHost$0(context);
                }
            });
        }

        static /* synthetic */ QsbWidgetHostView lambda$createHost$0(Context context) {
            return new QsbWidgetHostView(context);
        }

        @Override // android.app.Fragment
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            this.mWrapper = new FrameLayout(getContext());
            if (isQsbEnabled()) {
                FrameLayout frameLayout = this.mWrapper;
                frameLayout.addView(createQsb(frameLayout));
            }
            return this.mWrapper;
        }

        private View createQsb(ViewGroup container) {
            AppWidgetProviderInfo searchWidgetProvider = getSearchWidgetProvider();
            this.mWidgetInfo = searchWidgetProvider;
            boolean z = false;
            if (searchWidgetProvider == null) {
                return getDefaultView(container, false);
            }
            Bundle bundleCreateBindOptions = createBindOptions();
            Context context = getContext();
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            int i = -1;
            int i2 = Utilities.getPrefs(context).getInt(this.mKeyWidgetId, -1);
            AppWidgetProviderInfo appWidgetInfo = appWidgetManager.getAppWidgetInfo(i2);
            if (appWidgetInfo != null && appWidgetInfo.provider.equals(this.mWidgetInfo.provider)) {
                z = true;
            }
            if (!z && !isInPreviewMode()) {
                if (i2 > -1) {
                    this.mQsbWidgetHost.deleteHost();
                }
                int iAllocateAppWidgetId = this.mQsbWidgetHost.allocateAppWidgetId();
                boolean zBindAppWidgetIdIfAllowed = appWidgetManager.bindAppWidgetIdIfAllowed(iAllocateAppWidgetId, this.mWidgetInfo.getProfile(), this.mWidgetInfo.provider, bundleCreateBindOptions);
                if (zBindAppWidgetIdIfAllowed) {
                    i = iAllocateAppWidgetId;
                } else {
                    this.mQsbWidgetHost.deleteAppWidgetId(iAllocateAppWidgetId);
                }
                if (i2 != i) {
                    saveWidgetId(i);
                }
                z = zBindAppWidgetIdIfAllowed;
                i2 = i;
            }
            if (z) {
                QsbWidgetHostView qsbWidgetHostView = (QsbWidgetHostView) this.mQsbWidgetHost.createView(context, i2, this.mWidgetInfo);
                this.mQsb = qsbWidgetHostView;
                qsbWidgetHostView.setId(R.id.qsb_widget);
                if (!isInPreviewMode()) {
                    if (!QsbContainerView.containsAll(AppWidgetManager.getInstance(context).getAppWidgetOptions(i2), bundleCreateBindOptions)) {
                        this.mQsb.updateAppWidgetOptions(bundleCreateBindOptions);
                    }
                    this.mQsbWidgetHost.startListening();
                }
                return this.mQsb;
            }
            return getDefaultView(container, true);
        }

        private void saveWidgetId(int widgetId) {
            Utilities.getPrefs(getContext()).edit().putInt(this.mKeyWidgetId, widgetId).apply();
        }

        @Override // android.app.Fragment
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == 1) {
                if (resultCode == -1) {
                    saveWidgetId(data.getIntExtra("appWidgetId", -1));
                    rebindFragment();
                } else {
                    this.mQsbWidgetHost.deleteHost();
                }
            }
        }

        @Override // android.app.Fragment
        public void onResume() {
            super.onResume();
            QsbWidgetHostView qsbWidgetHostView = this.mQsb;
            if (qsbWidgetHostView == null || !qsbWidgetHostView.isReinflateRequired(this.mOrientation)) {
                return;
            }
            rebindFragment();
        }

        @Override // android.app.Fragment
        public void onDestroy() {
            this.mQsbWidgetHost.stopListening();
            super.onDestroy();
        }

        private void rebindFragment() {
            if (!isQsbEnabled() || this.mWrapper == null || getContext() == null) {
                return;
            }
            this.mWrapper.removeAllViews();
            FrameLayout frameLayout = this.mWrapper;
            frameLayout.addView(createQsb(frameLayout));
        }

        protected Bundle createBindOptions() {
            InvariantDeviceProfile idp = LauncherAppState.getIDP(getContext());
            Bundle bundle = new Bundle();
            Rect widgetSizeRanges = AppWidgetResizeFrame.getWidgetSizeRanges(getContext(), idp.numColumns, 1, null);
            bundle.putInt(AppWidgetManager.OPTION_APPWIDGET_MIN_WIDTH, widgetSizeRanges.left);
            bundle.putInt(AppWidgetManager.OPTION_APPWIDGET_MIN_HEIGHT, widgetSizeRanges.top);
            bundle.putInt(AppWidgetManager.OPTION_APPWIDGET_MAX_WIDTH, widgetSizeRanges.right);
            bundle.putInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT, widgetSizeRanges.bottom);
            return bundle;
        }

        protected View getDefaultView(ViewGroup container, boolean showSetupIcon) {
            View defaultView = QsbWidgetHostView.getDefaultView(container);
            if (showSetupIcon) {
                View viewFindViewById = defaultView.findViewById(R.id.btn_qsb_setup);
                viewFindViewById.setVisibility(0);
                viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.qsb.-$$Lambda$QsbContainerView$QsbFragment$tCu8e_YXwH7rMx5tIKqz-KG-OM0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$getDefaultView$1$QsbContainerView$QsbFragment(view);
                    }
                });
            }
            return defaultView;
        }

        public /* synthetic */ void lambda$getDefaultView$1$QsbContainerView$QsbFragment(View view) {
            startActivityForResult(new Intent(AppWidgetManager.ACTION_APPWIDGET_BIND).putExtra("appWidgetId", this.mQsbWidgetHost.allocateAppWidgetId()).putExtra("appWidgetProvider", this.mWidgetInfo.provider), 1);
        }

        protected AppWidgetProviderInfo getSearchWidgetProvider() {
            ComponentName globalSearchActivity = ((SearchManager) getContext().getSystemService("search")).getGlobalSearchActivity();
            AppWidgetProviderInfo appWidgetProviderInfo = null;
            if (globalSearchActivity == null) {
                return null;
            }
            String packageName = globalSearchActivity.getPackageName();
            for (AppWidgetProviderInfo appWidgetProviderInfo2 : AppWidgetManager.getInstance(getContext()).getInstalledProviders()) {
                if (appWidgetProviderInfo2.provider.getPackageName().equals(packageName) && appWidgetProviderInfo2.configure == null) {
                    if ((appWidgetProviderInfo2.widgetCategory & 4) != 0) {
                        return appWidgetProviderInfo2;
                    }
                    if (appWidgetProviderInfo == null) {
                        appWidgetProviderInfo = appWidgetProviderInfo2;
                    }
                }
            }
            return appWidgetProviderInfo;
        }
    }

    public static class QsbWidgetHost extends AppWidgetHost {
        private final WidgetViewFactory mViewFactory;

        public QsbWidgetHost(Context context, int hostId, WidgetViewFactory viewFactory) {
            super(context, hostId);
            this.mViewFactory = viewFactory;
        }

        @Override // android.appwidget.AppWidgetHost
        protected AppWidgetHostView onCreateView(Context context, int appWidgetId, AppWidgetProviderInfo appWidget) {
            return this.mViewFactory.newView(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean containsAll(Bundle original, Bundle updates) {
        for (String str : updates.keySet()) {
            Object obj = updates.get(str);
            Object obj2 = original.get(str);
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }
}
