package android.view.inspector;

/* JADX INFO: loaded from: classes3.dex */
public interface InspectionCompanionProvider {
    <T> InspectionCompanion<T> provide(Class<T> cls);
}
