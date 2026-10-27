package p;

import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Observable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class r81 implements s81 {
    public final io.reactivex.rxjava3.subjects.b a;
    public final /* synthetic */ xy1 b;

    public r81(ie2 ie2Var) {
        this.b = ie2Var;
        Optional optionalA = Optional.a();
        Objects.requireNonNull(optionalA, "defaultValue is null");
        this.a = new io.reactivex.rxjava3.subjects.b(optionalA);
    }

    @Override // p.s81
    public final Observable a() {
        Optional optionalB = Optional.b(this.b.get());
        io.reactivex.rxjava3.subjects.b bVar = this.a;
        bVar.onNext(optionalB);
        Observable observableDistinctUntilChanged = bVar.distinctUntilChanged();
        v03.i(observableDistinctUntilChanged, "subject.distinctUntilChanged()");
        return observableDistinctUntilChanged;
    }

    @Override // p.s81
    public final Object getLast() {
        Object obj = this.b.get();
        this.a.onNext(Optional.b(obj));
        return obj;
    }
}
