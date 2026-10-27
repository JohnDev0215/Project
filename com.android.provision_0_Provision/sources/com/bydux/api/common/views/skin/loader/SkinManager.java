package com.bydux.api.common.views.skin.loader;

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
import com.bydux.api.common.views.skin.entity.AttrFactory;
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

    private SkinManager() {
    }

    private void registerSkinReceiver() {
        if (this.mSkinReceiver == null) {
            this.mSkinReceiver = new SkinReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ACTION_BYDUI_SKIN_CHANGED);
            this.mContext.registerReceiver(this.mSkinReceiver, intentFilter);
        }
    }

    private void unregisterSkinReceiver() {
        SkinReceiver skinReceiver = this.mSkinReceiver;
        if (skinReceiver == null) {
            return;
        }
        this.mContext.unregisterReceiver(skinReceiver);
        this.mSkinReceiver = null;
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

    public void init(Context context, ILoaderListener listener, boolean skinEnable) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mLoaderListener = listener;
        this.mSkinEnable = skinEnable;
        this.mSkinPath = Settings.System.getString(applicationContext.getContentResolver(), FIELD_BYDUI_SKIN_PATH);
        reload();
        BydLogHelper.logI(this.TAG, "init", "mSkinPath = " + this.mSkinPath);
        registerSkinReceiver();
    }

    public void release() {
        if (this.mSkinEnable) {
            this.mContext = null;
            this.mLoaderListener = null;
        }
        unregisterSkinReceiver();
    }

    public boolean getSkinEnable() {
        return this.mSkinEnable;
    }

    public String getSkinPath() {
        return this.mSkinPath;
    }

    public void setSkinPath(String skinPath) {
        BydLogHelper.logI(this.TAG, "setSkinPath", "skinPath = " + skinPath);
        this.mSkinPath = skinPath;
        reload();
    }

    public void setDefaultSkin() {
        if (this.mSkinEnable) {
            setSkinPath("");
        }
    }

    public boolean isDefaultSkin() {
        return TextUtils.isEmpty(this.mSkinPath);
    }

    public String getSkinPackageName() {
        return this.mSkinPackageName;
    }

    public Resources getResources() {
        if (this.mResources == null) {
            this.mResources = this.mContext.getResources();
        }
        return this.mResources;
    }

    public int getColor(int resId) {
        int color;
        Context context = this.mContext;
        if (context == null) {
            return 0;
        }
        if (this.mResources == null) {
            this.mResources = context.getResources();
        }
        if (isDefaultSkin()) {
            return this.mContext.getResources().getColor(resId);
        }
        int identifier = this.mResources.getIdentifier(this.mContext.getResources().getResourceEntryName(resId), "color", this.mSkinPackageName);
        if (identifier == 0) {
            color = this.mContext.getResources().getColor(resId);
        } else {
            color = this.mResources.getColor(identifier);
        }
        BydLogHelper.logI(this.TAG, "getColor", "trueResId = " + identifier);
        return color;
    }

    public Drawable getDrawable(int resId) {
        Drawable drawable;
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        if (this.mResources == null) {
            this.mResources = context.getResources();
        }
        if (isDefaultSkin()) {
            return this.mContext.getResources().getDrawable(resId, null);
        }
        int identifier = this.mResources.getIdentifier(this.mContext.getResources().getResourceEntryName(resId), AttrFactory.DRAWABLE, this.mSkinPackageName);
        if (identifier == 0) {
            drawable = this.mContext.getResources().getDrawable(resId, null);
        } else {
            drawable = this.mResources.getDrawable(identifier, null);
        }
        BydLogHelper.logI(this.TAG, "getDrawable", "trueResId = " + identifier);
        return drawable;
    }

    public Drawable getDrawable(String resName) {
        int identifier;
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        if (this.mResources == null) {
            this.mResources = context.getResources();
        }
        if (isDefaultSkin()) {
            identifier = this.mResources.getIdentifier(resName, AttrFactory.DRAWABLE, this.mContext.getPackageName());
        } else {
            identifier = this.mResources.getIdentifier(resName, AttrFactory.DRAWABLE, this.mSkinPackageName);
        }
        Drawable drawable = identifier != 0 ? this.mResources.getDrawable(identifier, null) : null;
        BydLogHelper.logI(this.TAG, "getDrawable", "trueResId = " + identifier);
        return drawable;
    }

    public Drawable getMipmapDrawable(int resId) {
        Drawable drawable;
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        if (this.mResources == null) {
            this.mResources = context.getResources();
        }
        if (isDefaultSkin()) {
            return this.mContext.getResources().getDrawable(resId, null);
        }
        int identifier = this.mResources.getIdentifier(this.mContext.getResources().getResourceEntryName(resId), "mipmap", this.mSkinPackageName);
        if (identifier == 0) {
            drawable = this.mContext.getResources().getDrawable(resId, null);
        } else {
            drawable = this.mResources.getDrawable(identifier, null);
        }
        BydLogHelper.logI(this.TAG, "getDrawable", "trueResId = " + identifier);
        return drawable;
    }

    public ColorStateList convertToColorStateList(int resId) {
        String resourceEntryName = this.mContext.getResources().getResourceEntryName(resId);
        if (this.mResources == null) {
            this.mResources = this.mContext.getResources();
        }
        try {
            if (isDefaultSkin()) {
                return this.mContext.getResources().getColorStateList(resId);
            }
            int identifier = this.mResources.getIdentifier(resourceEntryName, "color", this.mSkinPackageName);
            if (identifier == 0) {
                return this.mContext.getResources().getColorStateList(resId);
            }
            return this.mResources.getColorStateList(identifier);
        } catch (Resources.NotFoundException unused) {
            return new ColorStateList((int[][]) Array.newInstance((Class<?>) int.class, 1, 1), new int[]{this.mContext.getResources().getColor(resId)});
        }
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinLoader
    public void attach(ISkinUpdate observer) {
        if (this.mSkinObservers == null) {
            this.mSkinObservers = new ArrayList();
        }
        if (this.mSkinObservers.contains(observer)) {
            return;
        }
        this.mSkinObservers.add(observer);
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinLoader
    public void detach(ISkinUpdate observer) {
        List<ISkinUpdate> list = this.mSkinObservers;
        if (list != null && list.contains(observer)) {
            this.mSkinObservers.remove(observer);
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

    private class SkinReceiver extends BroadcastReceiver {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void reload() {
        BydLogHelper.logI(this.TAG, "reload", "skin path=" + this.mSkinPath);
        load(this.mSkinPath, this.mLoaderListener);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.bydux.api.common.views.skin.loader.SkinManager$1] */
    private void load(String skinPath, final ILoaderListener listener) {
        new AsyncTask<String, Void, Resources>() { // from class: com.bydux.api.common.views.skin.loader.SkinManager.1
            @Override // android.os.AsyncTask
            protected void onPreExecute() {
                ILoaderListener iLoaderListener = listener;
                if (iLoaderListener != null) {
                    iLoaderListener.onChangeSkinStart();
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Resources doInBackground(String... params) {
                try {
                    if (params.length == 1) {
                        String str = params[0];
                        if (TextUtils.isEmpty(str)) {
                            BydLogHelper.logI(SkinManager.this.TAG, "doInBackground", "skinPath is null");
                            return null;
                        }
                        File file = new File(str);
                        if (!file.exists()) {
                            BydLogHelper.logI(SkinManager.this.TAG, "doInBackground", "file not exist");
                            return null;
                        }
                        BydLogHelper.logI(SkinManager.this.TAG, "doInBackground", "skinPath is null？ // " + str + ", file.exists() / " + file.exists());
                        SkinManager.this.mSkinPackageName = SkinManager.this.mContext.getPackageManager().getPackageArchiveInfo(str, 1).packageName;
                        AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
                        Method method = assetManager.getClass().getMethod("addAssetPath", String.class);
                        method.setAccessible(true);
                        method.invoke(assetManager, str);
                        Resources resources = SkinManager.this.mContext.getResources();
                        Resources resources2 = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());
                        BydLogHelper.logI(SkinManager.this.TAG, "skin pkgName=" + SkinManager.this.mSkinPackageName);
                        return resources2;
                    }
                } catch (Exception unused) {
                }
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Resources result) {
                SkinManager.this.notifyResoureState(result, listener);
                SkinManager.this.notifySkinUpdate();
            }
        }.execute(skinPath);
    }

    public void notifyResoureState(Resources result, ILoaderListener listener) {
        if (result == null) {
            BydLogHelper.logI(this.TAG, "changeResource", "result == null");
        }
        this.mResources = result;
        if (result != null) {
            BydLogHelper.logI(this.TAG, "changeResource", "success");
            if (listener != null) {
                listener.onChangeSkinSuccess();
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
            if (listener != null) {
                listener.onChangeSkinSuccess();
                return;
            }
            return;
        }
        BydLogHelper.logI(this.TAG, "changeResource", "fail");
        if (listener != null) {
            listener.onChangeSkinFail();
        }
    }

    public Resources updateResource(Configuration config) {
        if (this.mResources == null && BydApplication.getContext() != null) {
            this.mResources = BydApplication.getContext().getResources();
        }
        Context context = this.mContext;
        if (context != null) {
            this.mResources.updateConfiguration(config, context.getResources().getDisplayMetrics());
        }
        return this.mResources;
    }
}
