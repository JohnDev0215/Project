package defpackage;

import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SuggestionProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public interface dw2 extends ee0 {
    @Override // defpackage.ee0
    default boolean b() {
        return true;
    }

    void c(int i, AutocompleteMatch autocompleteMatch, PropertyModel propertyModel);

    boolean d(AutocompleteMatch autocompleteMatch, int i);
}
