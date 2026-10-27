package com.byd.music.view.commonAdapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public final View itemView;
    private SparseArray<View> views;

    public RecyclerViewHolder(View view) {
        super(view);
        this.itemView = view;
        this.views = new SparseArray<>();
    }

    private View findViewById(int i) {
        return this.itemView.findViewById(i);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public <T extends View> T getView(int i) {
        T t = (T) this.views.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) findViewById(i);
        this.views.put(i, t2);
        return t2;
    }

    public void setText(int i, int i2) {
        ((TextView) getView(i)).setText(i2);
    }

    public void setText(int i, String str) {
        ((TextView) getView(i)).setText(str);
    }
}
