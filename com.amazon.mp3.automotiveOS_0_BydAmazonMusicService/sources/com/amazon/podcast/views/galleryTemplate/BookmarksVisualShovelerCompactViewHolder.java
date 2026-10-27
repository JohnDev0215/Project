package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.WidgetElementsInterface.v1_0.BookmarksElement;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
final class BookmarksVisualShovelerCompactViewHolder extends RecyclerView.ViewHolder {
    private final LifecycleOwner lifecycleOwner;
    private final BookmarksVisualShovelerCompactView visualShovelerCompactView;

    public BookmarksVisualShovelerCompactViewHolder(final LifecycleOwner lifecycleOwner, final BookmarksVisualShovelerCompactView itemView) {
        super(itemView);
        this.lifecycleOwner = lifecycleOwner;
        this.visualShovelerCompactView = itemView;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void bind(final BookmarksElement element) {
        BookmarksVisualShovelerCompactView bookmarksVisualShovelerCompactView = this.visualShovelerCompactView;
        bookmarksVisualShovelerCompactView.bind(bookmarksVisualShovelerCompactView, this.lifecycleOwner, element);
    }

    public void handleOnViewed() {
        this.visualShovelerCompactView.handleOnViewed();
    }
}
