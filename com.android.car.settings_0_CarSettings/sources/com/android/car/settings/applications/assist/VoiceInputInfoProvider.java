package com.android.car.settings.applications.assist;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.service.voice.VoiceInteractionServiceInfo;
import android.util.AttributeSet;
import android.util.Xml;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import com.android.car.settings.common.Logger;
import com.android.internal.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes5.dex */
public class VoiceInputInfoProvider {
    private static final Logger LOG = new Logger((Class<?>) VoiceInputInfoProvider.class);
    static final Intent VOICE_INTERACTION_SERVICE_TAG = new Intent("android.service.voice.VoiceInteractionService");
    static final Intent VOICE_RECOGNITION_SERVICE_TAG = new Intent("android.speech.RecognitionService");
    private final Context mContext;
    private final Map<ComponentName, VoiceInputInfo> mComponentToInfoMap = new ArrayMap();
    private final List<VoiceInteractionInfo> mVoiceInteractionInfoList = new ArrayList();
    private final List<VoiceRecognitionInfo> mVoiceRecognitionInfoList = new ArrayList();
    private final Set<ComponentName> mRecognitionServiceNames = new ArraySet();

    public VoiceInputInfoProvider(Context context) {
        this.mContext = context;
        loadVoiceInteractionServices();
        loadVoiceRecognitionServices();
    }

    public List<VoiceInteractionInfo> getVoiceInteractionInfoList() {
        return this.mVoiceInteractionInfoList;
    }

    public List<VoiceRecognitionInfo> getVoiceRecognitionInfoList() {
        return this.mVoiceRecognitionInfoList;
    }

    public VoiceInputInfo getInfoForComponent(ComponentName key) {
        return this.mComponentToInfoMap.getOrDefault(key, null);
    }

    private void loadVoiceInteractionServices() {
        List<ResolveInfo> mAvailableVoiceInteractionServices = this.mContext.getPackageManager().queryIntentServices(VOICE_INTERACTION_SERVICE_TAG, 128);
        for (ResolveInfo resolveInfo : mAvailableVoiceInteractionServices) {
            VoiceInteractionServiceInfo interactionServiceInfo = new VoiceInteractionServiceInfo(this.mContext.getPackageManager(), resolveInfo.serviceInfo);
            if (interactionServiceInfo.getParseError() != null) {
                LOG.w("Error in VoiceInteractionService " + resolveInfo.serviceInfo.packageName + "/" + resolveInfo.serviceInfo.name + ": " + interactionServiceInfo.getParseError());
            } else {
                VoiceInteractionInfo voiceInteractionInfo = new VoiceInteractionInfo(this.mContext, interactionServiceInfo);
                this.mVoiceInteractionInfoList.add(voiceInteractionInfo);
                if (interactionServiceInfo.getRecognitionService() != null) {
                    this.mRecognitionServiceNames.add(new ComponentName(resolveInfo.serviceInfo.packageName, interactionServiceInfo.getRecognitionService()));
                }
                this.mComponentToInfoMap.put(new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name), voiceInteractionInfo);
            }
        }
        Collections.sort(this.mVoiceInteractionInfoList);
    }

    private void loadVoiceRecognitionServices() {
        List<ResolveInfo> mAvailableRecognitionServices = this.mContext.getPackageManager().queryIntentServices(VOICE_RECOGNITION_SERVICE_TAG, 128);
        for (ResolveInfo resolveInfo : mAvailableRecognitionServices) {
            ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
            VoiceRecognitionInfo voiceRecognitionInfo = new VoiceRecognitionInfo(this.mContext, resolveInfo.serviceInfo);
            this.mVoiceRecognitionInfoList.add(voiceRecognitionInfo);
            this.mRecognitionServiceNames.add(componentName);
            this.mComponentToInfoMap.put(componentName, voiceRecognitionInfo);
        }
        Collections.sort(this.mVoiceRecognitionInfoList);
    }

    static abstract class VoiceInputInfo implements Comparable {
        private final Context mContext;
        private final ServiceInfo mServiceInfo;

        abstract CharSequence getLabel();

        protected abstract String getSettingsActivity();

        VoiceInputInfo(Context context, ServiceInfo serviceInfo) {
            this.mContext = context;
            this.mServiceInfo = serviceInfo;
        }

        protected Context getContext() {
            return this.mContext;
        }

        protected ServiceInfo getServiceInfo() {
            return this.mServiceInfo;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object o) {
            return getTag().toString().compareTo(((VoiceInputInfo) o).getTag().toString());
        }

        ComponentName getSettingsActivityComponentName() {
            String activity = getSettingsActivity();
            if (activity != null) {
                return new ComponentName(this.mServiceInfo.packageName, activity);
            }
            return null;
        }

        String getPackageName() {
            return this.mServiceInfo.packageName;
        }

        ComponentName getComponentName() {
            return new ComponentName(this.mServiceInfo.packageName, this.mServiceInfo.name);
        }

        protected CharSequence getTag() {
            return this.mServiceInfo.loadLabel(this.mContext.getPackageManager());
        }
    }

    static class VoiceInteractionInfo extends VoiceInputInfo {
        private final VoiceInteractionServiceInfo mInteractionServiceInfo;

        VoiceInteractionInfo(Context context, VoiceInteractionServiceInfo info) {
            super(context, info.getServiceInfo());
            this.mInteractionServiceInfo = info;
        }

        String getRecognitionService() {
            return this.mInteractionServiceInfo.getRecognitionService();
        }

        @Override // com.android.car.settings.applications.assist.VoiceInputInfoProvider.VoiceInputInfo
        protected String getSettingsActivity() {
            return this.mInteractionServiceInfo.getSettingsActivity();
        }

        @Override // com.android.car.settings.applications.assist.VoiceInputInfoProvider.VoiceInputInfo
        CharSequence getLabel() {
            return getServiceInfo().applicationInfo.loadLabel(getContext().getPackageManager());
        }
    }

    static class VoiceRecognitionInfo extends VoiceInputInfo {
        VoiceRecognitionInfo(Context context, ServiceInfo serviceInfo) {
            super(context, serviceInfo);
        }

        @Override // com.android.car.settings.applications.assist.VoiceInputInfoProvider.VoiceInputInfo
        protected String getSettingsActivity() {
            return getServiceSettingsActivity(getServiceInfo());
        }

        @Override // com.android.car.settings.applications.assist.VoiceInputInfoProvider.VoiceInputInfo
        CharSequence getLabel() {
            return getTag();
        }

        private String getServiceSettingsActivity(ServiceInfo serviceInfo) {
            int type;
            XmlResourceParser parser = null;
            String settingActivity = null;
            try {
                try {
                    try {
                        parser = serviceInfo.loadXmlMetaData(getContext().getPackageManager(), "android.speech");
                    } catch (PackageManager.NameNotFoundException e) {
                        VoiceInputInfoProvider.LOG.e("error parsing recognition service meta-data", e);
                        if (0 != 0) {
                        }
                    } catch (XmlPullParserException e2) {
                        VoiceInputInfoProvider.LOG.e("error parsing recognition service meta-data", e2);
                        if (0 != 0) {
                        }
                    }
                } catch (IOException e3) {
                    VoiceInputInfoProvider.LOG.e("error parsing recognition service meta-data", e3);
                    if (0 != 0) {
                    }
                }
                if (parser == null) {
                    throw new XmlPullParserException("No android.speech meta-data for " + serviceInfo.packageName);
                }
                Resources res = getContext().getPackageManager().getResourcesForApplication(serviceInfo.applicationInfo);
                AttributeSet attrs = Xml.asAttributeSet(parser);
                do {
                    type = parser.next();
                    if (type == 1) {
                        break;
                    }
                } while (type != 2);
                String nodeName = parser.getName();
                if (!"recognition-service".equals(nodeName)) {
                    throw new XmlPullParserException("Meta-data does not start with recognition-service tag");
                }
                TypedArray array = res.obtainAttributes(attrs, R.styleable.RecognitionService);
                settingActivity = array.getString(0);
                array.recycle();
                return settingActivity;
            } finally {
                if (0 != 0) {
                    parser.close();
                }
            }
        }
    }
}
