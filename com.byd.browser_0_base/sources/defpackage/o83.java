package defpackage;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

/* JADX INFO: compiled from: TextViewCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class o83 {

    /* JADX INFO: compiled from: TextViewCompat.java */
    public static class a implements ActionMode.Callback {
        public a() {
            throw null;
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            throw null;
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            throw null;
        }

        @Override // android.view.ActionMode.Callback
        public final void onDestroyActionMode(ActionMode actionMode) {
            throw null;
        }

        @Override // android.view.ActionMode.Callback
        public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            throw null;
        }
    }

    public static ActionMode.Callback a(ActionMode.Callback callback) {
        if (!(callback instanceof a)) {
            return callback;
        }
        ((a) callback).getClass();
        return null;
    }
}
