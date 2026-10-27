package com.android.textclassifier.common.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.textclassifier.common.base.TcLog;
import com.google.android.textclassifier.NamedVariant;
import com.google.android.textclassifier.RemoteActionTemplate;
import com.google.common.collect.ImmutableList;

/* JADX INFO: loaded from: classes.dex */
public final class TemplateIntentFactory {
    public ImmutableList<LabeledIntent> create(RemoteActionTemplate[] remoteActionTemplateArr) {
        if (remoteActionTemplateArr == null || remoteActionTemplateArr.length == 0) {
            return ImmutableList.of();
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        for (RemoteActionTemplate remoteActionTemplate : remoteActionTemplateArr) {
            if (!isValidTemplate(remoteActionTemplate)) {
                TcLog.w("TemplateIntentFactory", "Invalid RemoteActionTemplate skipped.");
            } else {
                String str = remoteActionTemplate.titleWithoutEntity;
                String str2 = remoteActionTemplate.titleWithEntity;
                String str3 = remoteActionTemplate.description;
                String str4 = remoteActionTemplate.descriptionWithAppName;
                Intent intentCreateIntent = createIntent(remoteActionTemplate);
                Integer num = remoteActionTemplate.requestCode;
                builder.add(new LabeledIntent(str, str2, str3, str4, intentCreateIntent, num == null ? 0 : num.intValue()));
            }
        }
        return builder.build();
    }

    private static boolean isValidTemplate(RemoteActionTemplate remoteActionTemplate) {
        if (remoteActionTemplate == null) {
            TcLog.w("TemplateIntentFactory", "Invalid RemoteActionTemplate: is null");
            return false;
        }
        if (TextUtils.isEmpty(remoteActionTemplate.titleWithEntity) && TextUtils.isEmpty(remoteActionTemplate.titleWithoutEntity)) {
            TcLog.w("TemplateIntentFactory", "Invalid RemoteActionTemplate: title is null");
            return false;
        }
        if (TextUtils.isEmpty(remoteActionTemplate.description)) {
            TcLog.w("TemplateIntentFactory", "Invalid RemoteActionTemplate: description is null");
            return false;
        }
        if (!TextUtils.isEmpty(remoteActionTemplate.packageName)) {
            TcLog.w("TemplateIntentFactory", "Invalid RemoteActionTemplate: package name is set");
            return false;
        }
        if (!TextUtils.isEmpty(remoteActionTemplate.action)) {
            return true;
        }
        TcLog.w("TemplateIntentFactory", "Invalid RemoteActionTemplate: intent action not set");
        return false;
    }

    private static Intent createIntent(RemoteActionTemplate remoteActionTemplate) {
        Intent intent = new Intent(remoteActionTemplate.action);
        intent.setDataAndType(TextUtils.isEmpty(remoteActionTemplate.data) ? null : Uri.parse(remoteActionTemplate.data).normalizeScheme(), TextUtils.isEmpty(remoteActionTemplate.type) ? null : Intent.normalizeMimeType(remoteActionTemplate.type));
        Integer num = remoteActionTemplate.flags;
        intent.setFlags(num == null ? 0 : num.intValue());
        if (!TextUtils.isEmpty(remoteActionTemplate.packageName)) {
            intent.setPackage(remoteActionTemplate.packageName);
        }
        String[] strArr = remoteActionTemplate.category;
        if (strArr != null) {
            for (String str : strArr) {
                if (str != null) {
                    intent.addCategory(str);
                }
            }
        }
        intent.putExtras(nameVariantsToBundle(remoteActionTemplate.extras));
        if (!intent.hasExtra("android.view.textclassifier.extra.FROM_TEXT_CLASSIFIER")) {
            intent.putExtra("android.view.textclassifier.extra.FROM_TEXT_CLASSIFIER", Bundle.EMPTY);
        }
        return intent;
    }

    public static Bundle nameVariantsToBundle(NamedVariant[] namedVariantArr) {
        if (namedVariantArr == null) {
            return Bundle.EMPTY;
        }
        Bundle bundle = new Bundle();
        for (NamedVariant namedVariant : namedVariantArr) {
            if (namedVariant != null) {
                switch (namedVariant.getType()) {
                    case 1:
                        bundle.putInt(namedVariant.getName(), namedVariant.getInt());
                        break;
                    case 2:
                        bundle.putLong(namedVariant.getName(), namedVariant.getLong());
                        break;
                    case 3:
                        bundle.putFloat(namedVariant.getName(), namedVariant.getFloat());
                        break;
                    case 4:
                        bundle.putDouble(namedVariant.getName(), namedVariant.getDouble());
                        break;
                    case 5:
                        bundle.putBoolean(namedVariant.getName(), namedVariant.getBool());
                        break;
                    case 6:
                        bundle.putString(namedVariant.getName(), namedVariant.getString());
                        break;
                    case NamedVariant.TYPE_STRING_ARRAY /* 7 */:
                        bundle.putStringArray(namedVariant.getName(), namedVariant.getStringArray());
                        break;
                    case NamedVariant.TYPE_FLOAT_ARRAY /* 8 */:
                        bundle.putFloatArray(namedVariant.getName(), namedVariant.getFloatArray());
                        break;
                    case NamedVariant.TYPE_INT_ARRAY /* 9 */:
                        bundle.putIntArray(namedVariant.getName(), namedVariant.getIntArray());
                        break;
                    case NamedVariant.TYPE_NAMED_VARIANT_ARRAY /* 10 */:
                        bundle.putBundle(namedVariant.getName(), nameVariantsToBundle(namedVariant.getNamedVariantArray()));
                        break;
                    default:
                        TcLog.w("TemplateIntentFactory", "Unsupported type found in nameVariantsToBundle : " + namedVariant.getType());
                        break;
                }
            }
        }
        return bundle;
    }
}
