package com.android.textclassifier;

import android.app.RemoteAction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextLinks;
import androidx.core.util.Pair;
import com.google.android.textclassifier.AnnotatorModel;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ExtrasUtils {
    static Bundle createForeignLanguageExtra(String str, float f, int i) {
        Bundle bundle = new Bundle();
        bundle.putString("entity-type", str);
        bundle.putFloat("score", f);
        bundle.putInt("model-version", i);
        bundle.putString("model-name", "langId_v" + i);
        return bundle;
    }

    static void putForeignLanguageExtra(Bundle bundle, Bundle bundle2) {
        bundle.putParcelable("foreign-language", bundle2);
    }

    public static Bundle getForeignLanguageExtra(TextClassification textClassification) {
        if (textClassification == null) {
            return null;
        }
        return textClassification.getExtras().getBundle("foreign-language");
    }

    static Pair<String, Float> getTopLanguage(Intent intent) {
        Bundle bundleExtra;
        Bundle bundle;
        if (intent == null || (bundleExtra = intent.getBundleExtra("android.view.textclassifier.extra.FROM_TEXT_CLASSIFIER")) == null || (bundle = bundleExtra.getBundle("text-languages")) == null) {
            return null;
        }
        String[] stringArray = bundle.getStringArray("entity-type");
        float[] floatArray = bundle.getFloatArray("score");
        if (stringArray == null || floatArray == null || stringArray.length == 0 || stringArray.length != floatArray.length) {
            return null;
        }
        int i = 0;
        for (int i2 = 1; i2 < stringArray.length; i2++) {
            if (floatArray[i] < floatArray[i2]) {
                i = i2;
            }
        }
        return Pair.create(stringArray[i], Float.valueOf(floatArray[i]));
    }

    static void putActionsIntents(Bundle bundle, ArrayList<Intent> arrayList) {
        bundle.putParcelableArrayList("actions-intents", arrayList);
    }

    public static void putActionIntent(Bundle bundle, Intent intent) {
        bundle.putParcelable("action-intent", intent);
    }

    public static Intent getActionIntent(Bundle bundle) {
        return (Intent) bundle.getParcelable("action-intent");
    }

    public static void putSerializedEntityData(Bundle bundle, byte[] bArr) {
        bundle.putByteArray("serialized-entities-data", bArr);
    }

    public static void putEntitiesExtras(Bundle bundle, Bundle bundle2) {
        bundle.putParcelable("entities-extras", bundle2);
    }

    public static ArrayList<Intent> getActionsIntents(TextClassification textClassification) {
        if (textClassification == null) {
            return null;
        }
        return textClassification.getExtras().getParcelableArrayList("actions-intents");
    }

    public static RemoteAction findAction(TextClassification textClassification, String str) {
        ArrayList<Intent> actionsIntents;
        if (textClassification != null && str != null && (actionsIntents = getActionsIntents(textClassification)) != null) {
            int size = actionsIntents.size();
            for (int i = 0; i < size; i++) {
                Intent intent = actionsIntents.get(i);
                if (intent != null && str.equals(intent.getAction())) {
                    return textClassification.getActions().get(i);
                }
            }
        }
        return null;
    }

    public static RemoteAction findTranslateAction(TextClassification textClassification) {
        return findAction(textClassification, "android.intent.action.TRANSLATE");
    }

    public static String getEntityType(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return bundle.getString("entity-type");
    }

    public static float getScore(Bundle bundle) {
        if (bundle == null) {
            return -1.0f;
        }
        return bundle.getFloat("score", -1.0f);
    }

    public static void putEntities(Bundle bundle, AnnotatorModel.ClassificationResult[] classificationResultArr) {
        if (classificationResultArr == null || classificationResultArr.length == 0) {
            return;
        }
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (AnnotatorModel.ClassificationResult classificationResult : classificationResultArr) {
            if (classificationResult != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("entity-type", classificationResult.getCollection());
                bundle2.putByteArray("serialized-entities-data", classificationResult.getSerializedEntityData());
                arrayList.add(bundle2);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        bundle.putParcelableArrayList("entities", arrayList);
    }

    public static List<Bundle> getEntities(Bundle bundle) {
        return bundle.getParcelableArrayList("entities");
    }

    public static boolean isSerializedEntityDataEnabled(TextLinks.Request request) {
        return request.getExtras().getBoolean("is-serialized-entity-data-enabled");
    }

    public static void putIsSerializedEntityDataEnabled(Bundle bundle, boolean z) {
        bundle.putBoolean("is-serialized-entity-data-enabled", z);
    }
}
