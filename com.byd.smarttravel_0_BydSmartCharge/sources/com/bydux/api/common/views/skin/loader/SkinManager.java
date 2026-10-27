package com.bydux.api.common.views.skin.loader;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.provider.Settings;
import android.text.TextUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.application.BydApplication;
import com.bydux.api.common.views.skin.entity.SkinAttr;
import com.bydux.api.common.views.skin.listener.ILoaderListener;
import com.bydux.api.common.views.skin.listener.ISkinLoader;
import com.bydux.api.common.views.skin.listener.ISkinUpdate;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SkinManager implements ISkinLoader {
    public static final String ACTION_BYDUI_SKIN_CHANGED = "action.change.day.night.mode";
    public static final boolean DEBUG = true;
    public static final String FIELD_BYDUI_SKIN_PATH = "bydux_skin_path";
    public static final int FIELD_DAY_MODE = 1;
    public static final String FIELD_DAY_NIGHT_MODE = "day_night_mode";
    public static final int FIELD_NIGHT_MODE = 2;
    private static SkinManager mSelf;
    private static Object mSyncLock = new Object();
    private AssetManager assetManager;
    private Context mContext;
    private Resources mResources;
    private SkinContextWrapper mSkinContextWrapper;
    private boolean mSkinEnable;
    private List<ISkinUpdate> mSkinObservers;
    private final String TAG = getClass().getSimpleName();
    private SkinReceiver mSkinReceiver = null;
    private ILoaderListener mLoaderListener = null;
    private String mSkinPackageName = "";
    private String mSkinPath = "";

    public class SkinReceiver extends BroadcastReceiver {
        private SkinReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null || !action.equals(SkinManager.ACTION_BYDUI_SKIN_CHANGED)) {
                return;
            }
            String stringExtra = intent.getStringExtra(SkinManager.FIELD_BYDUI_SKIN_PATH);
            int intExtra = intent.getIntExtra(SkinManager.FIELD_DAY_NIGHT_MODE, 1);
            BydLogHelper.logI(SkinManager.this.TAG, "onReceive", "skinPath=" + stringExtra);
            if (intExtra == 2) {
                SkinManager.this.mSkinPath = stringExtra;
            } else {
                SkinManager.this.mSkinPath = "";
            }
            SkinManager.this.reload();
        }
    }

    private SkinManager() {
    }

    public static SkinManager getInstance() {
        if (mSelf == null) {
            synchronized (mSyncLock) {
                if (mSelf == null) {
                    mSelf = new SkinManager();
                }
            }
        }
        return mSelf;
    }

    private void load(String str, final ILoaderListener iLoaderListener) {
        new AsyncTask<String, Void, Resources>() { // from class: com.bydux.api.common.views.skin.loader.SkinManager.1
            @Override // android.os.AsyncTask
            public void onPreExecute() {
                ILoaderListener iLoaderListener2 = iLoaderListener;
                if (iLoaderListener2 != null) {
                    iLoaderListener2.onChangeSkinStart();
                }
            }

            @Override // android.os.AsyncTask
            @SuppressLint({"StaticFieldLeak"})
            public Resources doInBackground(String... strArr) {
                try {
                    if (strArr.length == 1) {
                        String str2 = strArr[0];
                        if (TextUtils.isEmpty(str2)) {
                            BydLogHelper.logI(SkinManager.this.TAG, "doInBackground", "skinPath is null");
                            return null;
                        }
                        File file = new File(str2);
                        if (!file.exists()) {
                            BydLogHelper.logI(SkinManager.this.TAG, "doInBackground", "file not exist");
                            return null;
                        }
                        BydLogHelper.logI(SkinManager.this.TAG, "doInBackground", "skinPath is null？ // " + str2 + ", file.exists() / " + file.exists());
                        SkinManager.this.mSkinPackageName = SkinManager.this.mContext.getPackageManager().getPackageArchiveInfo(str2, 1).packageName;
                        AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
                        Method method = assetManager.getClass().getMethod("addAssetPath", String.class);
                        method.setAccessible(true);
                        method.invoke(assetManager, str2);
                        Resources resources = SkinManager.this.mContext.getResources();
                        Resources resources2 = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
                        BydLogHelper.logI(SkinManager.this.TAG, "skin pkgName=" + SkinManager.this.mSkinPackageName);
                        return resources2;
                    }
                } catch (Exception unused) {
                }
                return null;
            }

            @Override // android.os.AsyncTask
            public void onPostExecute(Resources resources) {
                SkinManager.this.notifyResoureState(resources, iLoaderListener);
                SkinManager.this.notifySkinUpdate();
            }
        }.execute(str);
    }

    private void registerSkinReceiver() {
        if (this.mSkinReceiver == null) {
            this.mSkinReceiver = new SkinReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ACTION_BYDUI_SKIN_CHANGED);
            this.mContext.registerReceiver(this.mSkinReceiver, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reload() {
        BydLogHelper.logI(this.TAG, "reload", "skin path=" + this.mSkinPath);
        load(this.mSkinPath, this.mLoaderListener);
    }

    private void unregisterSkinReceiver() {
        SkinReceiver skinReceiver = this.mSkinReceiver;
        if (skinReceiver == null) {
            return;
        }
        this.mContext.unregisterReceiver(skinReceiver);
        this.mSkinReceiver = null;
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinLoader
    public void attach(ISkinUpdate iSkinUpdate) {
        if (this.mSkinObservers == null) {
            this.mSkinObservers = new ArrayList();
        }
        if (this.mSkinObservers.contains(iSkinUpdate)) {
            return;
        }
        this.mSkinObservers.add(iSkinUpdate);
    }

    public ColorStateList convertToColorStateList(int i) {
        String resourceEntryName = this.mContext.getResources().getResourceEntryName(i);
        if (this.mResources == null) {
            this.mResources = this.mContext.getResources();
        }
        try {
            if (isDefaultSkin()) {
                return this.mContext.getResources().getColorStateList(i);
            }
            int identifier = this.mResources.getIdentifier(resourceEntryName, SkinAttr.RES_TYPE_NAME_COLOR, this.mSkinPackageName);
            return identifier == 0 ? this.mContext.getResources().getColorStateList(i) : this.mResources.getColorStateList(identifier);
        } catch (Resources.NotFoundException unused) {
            return new ColorStateList((int[][]) Array.newInstance((Class<?>) int.class, 1, 1), new int[]{this.mContext.getResources().getColor(i)});
        }
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinLoader
    public void detach(ISkinUpdate iSkinUpdate) {
        List<ISkinUpdate> list = this.mSkinObservers;
        if (list != null && list.contains(iSkinUpdate)) {
            this.mSkinObservers.remove(iSkinUpdate);
        }
    }

    public int getColor(int i) {
        Context context = this.mContext;
        if (context == null) {
            return 0;
        }
        if (this.mResources == null) {
            this.mResources = context.getResources();
        }
        if (isDefaultSkin()) {
            return this.mContext.getResources().getColor(i);
        }
        int identifier = this.mResources.getIdentifier(this.mContext.getResources().getResourceEntryName(i), SkinAttr.RES_TYPE_NAME_COLOR, this.mSkinPackageName);
        return identifier == 0 ? this.mContext.getResources().getColor(i) : this.mResources.getColor(identifier);
    }

    public Context getContext(Context context) {
        if (!getInstance().getSkinEnable()) {
            return context;
        }
        if (this.mSkinContextWrapper == null) {
            this.mSkinContextWrapper = new SkinContextWrapper(context);
        }
        return this.mSkinContextWrapper;
    }

    public Drawable getDrawable(int i) {
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        if (this.mResources == null) {
            this.mResources = context.getResources();
        }
        if (isDefaultSkin()) {
            return this.mContext.getResources().getDrawable(i, null);
        }
        int identifier = this.mResources.getIdentifier(this.mContext.getResources().getResourceEntryName(i), "drawable", this.mSkinPackageName);
        return identifier == 0 ? this.mContext.getResources().getDrawable(i, null) : this.mResources.getDrawable(identifier, null);
    }

    public Drawable getMipmapDrawable(int i) {
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        if (this.mResources == null) {
            this.mResources = context.getResources();
        }
        if (isDefaultSkin()) {
            return this.mContext.getResources().getDrawable(i, null);
        }
        int identifier = this.mResources.getIdentifier(this.mContext.getResources().getResourceEntryName(i), SkinAttr.RES_TYPE_NAME_MIPMAP, this.mSkinPackageName);
        return identifier == 0 ? this.mContext.getResources().getDrawable(i, null) : this.mResources.getDrawable(identifier, null);
    }

    public Resources getResources() {
        if (this.mResources == null) {
            this.mResources = this.mContext.getResources();
        }
        return this.mResources;
    }

    public boolean getSkinEnable() {
        return this.mSkinEnable;
    }

    public String getSkinPackageName() {
        return this.mSkinPackageName;
    }

    public String getSkinPath() {
        return this.mSkinPath;
    }

    public void init(Context context, ILoaderListener iLoaderListener, boolean z) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mLoaderListener = iLoaderListener;
        this.mSkinEnable = z;
        this.mSkinPath = Settings.System.getString(applicationContext.getContentResolver(), FIELD_BYDUI_SKIN_PATH);
        reload();
        BydLogHelper.logI(this.TAG, "init", "mSkinPath = " + this.mSkinPath);
        registerSkinReceiver();
    }

    public boolean isDefaultSkin() {
        return TextUtils.isEmpty(this.mSkinPath);
    }

    public void notifyResoureState(Resources resources, ILoaderListener iLoaderListener) {
        if (resources == null) {
            BydLogHelper.logI(this.TAG, "changeResource", "result == null");
        }
        this.mResources = resources;
        if (resources != null) {
            BydLogHelper.logI(this.TAG, "changeResource", "success");
            if (iLoaderListener != null) {
                iLoaderListener.onChangeSkinSuccess();
                return;
            }
            return;
        }
        Context context = this.mContext;
        if (context != null) {
            this.mResources = context.getResources();
        }
        if (TextUtils.isEmpty(this.mSkinPath)) {
            BydLogHelper.logI(this.TAG, "changeResource", "default");
            if (iLoaderListener != null) {
                iLoaderListener.onChangeSkinSuccess();
                return;
            }
            return;
        }
        BydLogHelper.logI(this.TAG, "changeResource", "fail");
        if (iLoaderListener != null) {
            iLoaderListener.onChangeSkinFail();
        }
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinLoader
    public void notifySkinUpdate() {
        List<ISkinUpdate> list = this.mSkinObservers;
        if (list == null) {
            return;
        }
        Iterator<ISkinUpdate> it = list.iterator();
        while (it.hasNext()) {
            it.next().onSkinUpdate();
        }
    }

    public void release() {
        if (this.mSkinEnable) {
            this.mContext = null;
            this.mLoaderListener = null;
        }
        unregisterSkinReceiver();
    }

    public void setDefaultSkin() {
        if (this.mSkinEnable) {
            setSkinPath("");
        }
    }

    public void setSkinPath(String str) {
        BydLogHelper.logI(this.TAG, "setSkinPath", "skinPath = " + str);
        this.mSkinPath = str;
        reload();
    }

    public Resources updateResource(Configuration configuration) {
        if (this.mResources == null && BydApplication.getContext() != null) {
            this.mResources = BydApplication.getContext().getResources();
        }
        Context context = this.mContext;
        if (context != null) {
            this.mResources.updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        }
        return this.mResources;
    }

    public Drawable getDrawable(String str) {
        int identifier;
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        if (this.mResources == null) {
            this.mResources = context.getResources();
        }
        if (isDefaultSkin()) {
            identifier = this.mResources.getIdentifier(str, "drawable", this.mContext.getPackageName());
        } else {
            identifier = this.mResources.getIdentifier(str, "drawable", this.mSkinPackageName);
        }
        if (identifier == 0) {
            return null;
        }
        return this.mResources.getDrawable(identifier, null);
    }
}
