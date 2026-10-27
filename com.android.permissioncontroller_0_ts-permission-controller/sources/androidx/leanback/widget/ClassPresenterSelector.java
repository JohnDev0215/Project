package androidx.leanback.widget;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class ClassPresenterSelector extends PresenterSelector {
    private final ArrayList<Presenter> mPresenters = new ArrayList<>();
    private final HashMap<Class<?>, Object> mClassMap = new HashMap<>();

    public ClassPresenterSelector addClassPresenter(Class<?> cls, Presenter presenter) {
        this.mClassMap.put(cls, presenter);
        if (!this.mPresenters.contains(presenter)) {
            this.mPresenters.add(presenter);
        }
        return this;
    }

    public ClassPresenterSelector addClassPresenterSelector(Class<?> cls, PresenterSelector presenterSelector) {
        this.mClassMap.put(cls, presenterSelector);
        Presenter[] innerPresenters = presenterSelector.getPresenters();
        for (int i = 0; i < innerPresenters.length; i++) {
            if (!this.mPresenters.contains(innerPresenters[i])) {
                this.mPresenters.add(innerPresenters[i]);
            }
        }
        return this;
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object item) {
        Object presenter;
        Presenter innerPresenter;
        Class<?> cls = item.getClass();
        do {
            presenter = this.mClassMap.get(cls);
            if ((presenter instanceof PresenterSelector) && (innerPresenter = ((PresenterSelector) presenter).getPresenter(item)) != null) {
                return innerPresenter;
            }
            cls = cls.getSuperclass();
            if (presenter != null) {
                break;
            }
        } while (cls != null);
        return (Presenter) presenter;
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter[] getPresenters() {
        ArrayList<Presenter> arrayList = this.mPresenters;
        return (Presenter[]) arrayList.toArray(new Presenter[arrayList.size()]);
    }
}
