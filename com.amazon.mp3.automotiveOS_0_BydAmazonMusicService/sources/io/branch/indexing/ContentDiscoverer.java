package io.branch.indexing;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.TextView;
import com.google.android.gms.cast.HlsSegmentFormat;
import io.branch.indexing.ContentDiscoveryManifest;
import io.branch.referral.PrefHelper;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
public class ContentDiscoverer {
    private static ContentDiscoverer thisInstance_;
    private ContentDiscoveryManifest cdManifest_;
    private JSONObject contentEvent_;
    private int discoveryRepeatCnt_;
    private WeakReference<Activity> lastActivityReference_;
    private String referredUrl_;
    private int maxDiscoveryRepeatCnt = 15;
    private ArrayList<String> discoveredViewList_ = new ArrayList<>();
    private Runnable readContentRunnable = new Runnable() { // from class: io.branch.indexing.ContentDiscoverer.1
        @Override // java.lang.Runnable
        public void run() {
            try {
                ContentDiscoverer.access$008(ContentDiscoverer.this);
                if (!ContentDiscoverer.this.cdManifest_.isCDEnabled() || ContentDiscoverer.this.lastActivityReference_ == null || ContentDiscoverer.this.lastActivityReference_.get() == null) {
                    return;
                }
                Activity activity = (Activity) ContentDiscoverer.this.lastActivityReference_.get();
                ContentDiscoverer.this.contentEvent_ = new JSONObject();
                ContentDiscoverer.this.contentEvent_.put(HlsSegmentFormat.TS, System.currentTimeMillis());
                if (!TextUtils.isEmpty(ContentDiscoverer.this.referredUrl_)) {
                    ContentDiscoverer.this.contentEvent_.put("rl", ContentDiscoverer.this.referredUrl_);
                }
                String str = "/" + activity.getClass().getSimpleName();
                ContentDiscoverer.this.contentEvent_.put("v", str);
                ViewGroup viewGroup = (ViewGroup) activity.findViewById(R.id.content);
                if (viewGroup != null) {
                    ContentDiscoveryManifest.CDPathProperties cDPathProperties = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity);
                    boolean z = true;
                    boolean zIsClearTextRequested = cDPathProperties != null && cDPathProperties.isClearTextRequested();
                    JSONArray filteredElements = null;
                    if (cDPathProperties != null) {
                        zIsClearTextRequested = cDPathProperties.isClearTextRequested();
                        JSONObject jSONObject = ContentDiscoverer.this.contentEvent_;
                        if (zIsClearTextRequested) {
                            z = false;
                        }
                        jSONObject.put("h", z);
                        filteredElements = cDPathProperties.getFilteredElements();
                    }
                    JSONArray jSONArray = filteredElements;
                    boolean z2 = zIsClearTextRequested;
                    if (jSONArray == null || jSONArray.length() <= 0) {
                        if (!ContentDiscoverer.this.discoveredViewList_.contains(str)) {
                            JSONArray jSONArray2 = new JSONArray();
                            ContentDiscoverer.this.contentEvent_.put("ck", jSONArray2);
                            ContentDiscoverer.this.discoverContentKeys(viewGroup, jSONArray2, activity.getResources());
                        }
                    } else {
                        JSONArray jSONArray3 = new JSONArray();
                        ContentDiscoverer.this.contentEvent_.put("ck", jSONArray3);
                        JSONArray jSONArray4 = new JSONArray();
                        ContentDiscoverer.this.contentEvent_.put("cd", jSONArray4);
                        ContentDiscoverer.this.discoverContentData(jSONArray, jSONArray4, jSONArray3, activity, z2);
                    }
                    ContentDiscoverer.this.discoveredViewList_.add(str);
                    PrefHelper.getInstance(activity).saveBranchAnalyticsData(ContentDiscoverer.this.contentEvent_);
                    int discoveryRepeatInterval = ContentDiscoverer.this.cdManifest_.getCDPathProperties(activity).getDiscoveryRepeatInterval();
                    ContentDiscoverer contentDiscoverer = ContentDiscoverer.this;
                    contentDiscoverer.maxDiscoveryRepeatCnt = contentDiscoverer.cdManifest_.getCDPathProperties(activity).getMaxDiscoveryRepeatNumber();
                    if (ContentDiscoverer.this.discoveryRepeatCnt_ >= ContentDiscoverer.this.maxDiscoveryRepeatCnt || discoveryRepeatInterval < 500 || jSONArray == null || jSONArray.length() <= 0) {
                        return;
                    }
                    ContentDiscoverer.this.handler_.postDelayed(ContentDiscoverer.this.readContentRunnable, discoveryRepeatInterval);
                }
            } catch (Exception unused) {
            }
        }
    };
    private ViewTreeObserver.OnScrollChangedListener scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: io.branch.indexing.ContentDiscoverer.2
        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public void onScrollChanged() {
            ContentDiscoverer.this.handler_.removeCallbacks(ContentDiscoverer.this.readListRunnable);
            if (ContentDiscoverer.this.maxDiscoveryRepeatCnt > ContentDiscoverer.this.discoveryRepeatCnt_) {
                ContentDiscoverer.this.handler_.postDelayed(ContentDiscoverer.this.readListRunnable, 1500L);
            }
        }
    };
    private Runnable readListRunnable = new Runnable() { // from class: io.branch.indexing.ContentDiscoverer.3
        @Override // java.lang.Runnable
        public void run() {
            ContentDiscoverer.this.readContentRunnable.run();
        }
    };
    private Handler handler_ = new Handler();
    private final HashHelper hashHelper_ = new HashHelper();
    private final Map<String, WeakReference<ViewTreeObserver>> viewTreeObserverMap = new HashMap();

    static /* synthetic */ int access$008(ContentDiscoverer contentDiscoverer) {
        int i = contentDiscoverer.discoveryRepeatCnt_;
        contentDiscoverer.discoveryRepeatCnt_ = i + 1;
        return i;
    }

    public static ContentDiscoverer getInstance() {
        if (thisInstance_ == null) {
            thisInstance_ = new ContentDiscoverer();
        }
        return thisInstance_;
    }

    private ContentDiscoverer() {
    }

    public void discoverContent(Activity activity, String str) {
        ContentDiscoveryManifest contentDiscoveryManifest = ContentDiscoveryManifest.getInstance(activity);
        this.cdManifest_ = contentDiscoveryManifest;
        this.referredUrl_ = str;
        ContentDiscoveryManifest.CDPathProperties cDPathProperties = contentDiscoveryManifest.getCDPathProperties(activity);
        if (cDPathProperties != null) {
            if (cDPathProperties.isSkipContentDiscovery()) {
                return;
            }
            discoverContent(activity);
        } else {
            if (TextUtils.isEmpty(this.referredUrl_)) {
                return;
            }
            discoverContent(activity);
        }
    }

    public void onActivityStopped(Activity activity) {
        WeakReference<Activity> weakReference = this.lastActivityReference_;
        if (weakReference != null && weakReference.get() != null && this.lastActivityReference_.get().getClass().getName().equals(activity.getClass().getName())) {
            this.handler_.removeCallbacks(this.readContentRunnable);
            this.lastActivityReference_ = null;
        }
        updateLastViewTimeStampIfNeeded();
        Iterator<WeakReference<ViewTreeObserver>> it = this.viewTreeObserverMap.values().iterator();
        while (it.hasNext()) {
            ViewTreeObserver viewTreeObserver = it.next().get();
            if (viewTreeObserver != null) {
                viewTreeObserver.removeOnScrollChangedListener(this.scrollChangedListener);
            }
        }
        this.viewTreeObserverMap.clear();
    }

    public void onSessionStarted(Activity activity, String str) {
        this.discoveredViewList_ = new ArrayList<>();
        discoverContent(activity, str);
    }

    private void discoverContent(Activity activity) {
        this.discoveryRepeatCnt_ = 0;
        if (this.discoveredViewList_.size() < this.cdManifest_.getMaxViewHistorySize()) {
            this.handler_.removeCallbacks(this.readContentRunnable);
            this.lastActivityReference_ = new WeakReference<>(activity);
            this.handler_.postDelayed(this.readContentRunnable, 1000L);
        }
    }

    private void updateLastViewTimeStampIfNeeded() {
        try {
            JSONObject jSONObject = this.contentEvent_;
            if (jSONObject != null) {
                jSONObject.put("tc", System.currentTimeMillis());
            }
        } catch (JSONException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void discoverContentKeys(ViewGroup viewGroup, JSONArray jSONArray, Resources resources) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getVisibility() == 0) {
                if ((childAt instanceof AbsListView) || childAt.getClass().getSimpleName().equals("RecyclerView")) {
                    discoverListViewContentKeys((ViewGroup) childAt, resources, jSONArray);
                } else if (childAt instanceof ViewGroup) {
                    discoverContentKeys((ViewGroup) childAt, jSONArray, resources);
                } else if (childAt instanceof TextView) {
                    jSONArray.put(getViewName(childAt, resources));
                }
            }
        }
    }

    private void discoverListViewContentKeys(ViewGroup viewGroup, Resources resources, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        if (viewGroup == null || viewGroup.getChildCount() <= -1) {
            return;
        }
        View childAt = viewGroup.getChildAt(viewGroup.getChildCount() <= 1 ? 0 : 1);
        if (childAt != null) {
            JSONArray jSONArray2 = new JSONArray();
            try {
                jSONObject.put(getViewName(viewGroup, resources), jSONArray2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (childAt instanceof ViewGroup) {
                discoverContentKeys((ViewGroup) childAt, jSONArray2, resources);
            } else if (childAt instanceof TextView) {
                jSONArray2.put(getViewName(childAt, resources));
            }
            if (jSONObject.length() > 0) {
                jSONArray.put("$" + jSONObject);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void discoverContentData(JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, Activity activity, boolean z) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                String string = jSONArray.getString(i);
                if (string.startsWith("$")) {
                    discoverListViewContentData(string, activity, z, jSONArray2, jSONArray3);
                } else {
                    updateElementData(string, activity.findViewById(activity.getResources().getIdentifier(jSONArray.getString(i), "id", activity.getPackageName())), z, jSONArray2, jSONArray3);
                }
            } catch (JSONException unused) {
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void discoverListViewContentData(java.lang.String r17, android.app.Activity r18, boolean r19, org.json.JSONArray r20, org.json.JSONArray r21) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.indexing.ContentDiscoverer.discoverListViewContentData(java.lang.String, android.app.Activity, boolean, org.json.JSONArray, org.json.JSONArray):void");
    }

    private String getViewName(View view, Resources resources) {
        String strValueOf = String.valueOf(view.getId());
        try {
            return resources.getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return strValueOf;
        }
    }

    private String getTextViewValue(View view, boolean z) {
        TextView textView = (TextView) view;
        if (textView.getText() == null) {
            return null;
        }
        String strSubstring = textView.getText().toString().substring(0, Math.min(textView.getText().toString().length(), this.cdManifest_.getMaxTextLen()));
        return z ? strSubstring : this.hashHelper_.hashContent(strSubstring);
    }

    private void updateElementData(String str, View view, boolean z, JSONArray jSONArray, JSONArray jSONArray2) {
        if (view instanceof TextView) {
            jSONArray.put(getTextViewValue(view, z));
            jSONArray2.put(str);
        }
    }

    public JSONObject getContentDiscoverDataForCloseRequest(Context context) {
        JSONObject jSONObject;
        JSONObject branchAnalyticsData = PrefHelper.getInstance(context).getBranchAnalyticsData();
        if (branchAnalyticsData.length() <= 0 || branchAnalyticsData.toString().length() >= this.cdManifest_.getMaxPacketSize()) {
            jSONObject = null;
        } else {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("mv", ContentDiscoveryManifest.getInstance(context).getManifestVersion()).put("e", branchAnalyticsData);
                if (context != null) {
                    jSONObject.put("p", context.getPackageName());
                    jSONObject.put("p", context.getPackageName());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        PrefHelper.getInstance(context).clearBranchAnalyticsData();
        return jSONObject;
    }
}
