package ts.car.service.threedmodel;

/* JADX INFO: loaded from: classes2.dex */
public interface IThreedModelListener {
    void onAppStarted(String str);

    void onBoolValueNotify(String str, boolean z);

    void onDoubleValueNotify(String str, double d);

    void onFloatValueNotify(String str, float f);

    void onIntValueNotify(String str, int i);

    void onStringValueNotify(String str, String str2);
}
