package androidx.appcompat.widget;

import android.R;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.bz;
import defpackage.qb2;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class SuggestionsAdapter extends qb2 implements View.OnClickListener {
    public static final /* synthetic */ int B = 0;
    public int A;
    public final SearchView o;
    public final SearchableInfo p;
    public final Context q;
    public final WeakHashMap<String, Drawable.ConstantState> r;
    public final int s;
    public int t;
    public ColorStateList u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;

    public static final class ChildViewCache {
        public final TextView a;
        public final TextView b;
        public final ImageView c;
        public final ImageView d;
        public final ImageView e;

        public ChildViewCache(View view) {
            this.a = (TextView) view.findViewById(R.id.text1);
            this.b = (TextView) view.findViewById(R.id.text2);
            this.c = (ImageView) view.findViewById(R.id.icon1);
            this.d = (ImageView) view.findViewById(R.id.icon2);
            this.e = (ImageView) view.findViewById(androidx.appcompat.R.id.edit_query);
        }
    }

    public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout());
        this.t = 1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.o = searchView;
        this.p = searchableInfo;
        this.s = searchView.getSuggestionCommitIconResId();
        this.q = context;
        this.r = weakHashMap;
    }

    public static String j(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            return null;
        }
    }

    @Override // defpackage.j20, k20.a
    public final void a(Cursor cursor) {
        try {
            super.a(cursor);
            if (cursor != null) {
                this.v = cursor.getColumnIndex("suggest_text_1");
                this.w = cursor.getColumnIndex("suggest_text_2");
                this.x = cursor.getColumnIndex("suggest_text_2_url");
                this.y = cursor.getColumnIndex("suggest_icon_1");
                this.z = cursor.getColumnIndex("suggest_icon_2");
                this.A = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    @Override // defpackage.j20, k20.a
    public final String c(Cursor cursor) {
        String strJ;
        String strJ2;
        if (cursor == null) {
            return null;
        }
        String strJ3 = j(cursor, cursor.getColumnIndex("suggest_intent_query"));
        if (strJ3 != null) {
            return strJ3;
        }
        SearchableInfo searchableInfo = this.p;
        if (searchableInfo.shouldRewriteQueryFromData() && (strJ2 = j(cursor, cursor.getColumnIndex("suggest_intent_data"))) != null) {
            return strJ2;
        }
        if (!searchableInfo.shouldRewriteQueryFromText() || (strJ = j(cursor, cursor.getColumnIndex("suggest_text_1"))) == null) {
            return null;
        }
        return strJ;
    }

    @Override // k20.a
    public final Cursor d(CharSequence charSequence) {
        String string = charSequence == null ? "" : charSequence.toString();
        SearchView searchView = this.o;
        if (searchView.getVisibility() != 0 || searchView.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor cursorI = i(this.p, string);
            if (cursorI == null) {
                return null;
            }
            cursorI.getCount();
            return cursorI;
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0139  */
    @Override // defpackage.j20
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(android.view.View r21, android.database.Cursor r22) throws java.io.FileNotFoundException {
        /*
            Method dump skipped, instruction units count: 428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SuggestionsAdapter.e(android.view.View, android.database.Cursor):void");
    }

    @Override // defpackage.j20
    public final View f(ViewGroup viewGroup) {
        View viewInflate = this.n.inflate(this.l, viewGroup, false);
        viewInflate.setTag(new ChildViewCache(viewInflate));
        ((ImageView) viewInflate.findViewById(androidx.appcompat.R.id.edit_query)).setImageResource(this.s);
        return viewInflate;
    }

    public final Drawable g(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.q.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    identifier = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else {
                if (size != 2) {
                    throw new FileNotFoundException("More than two path segments: " + uri);
                }
                identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (identifier != 0) {
                return resourcesForApplication.getDrawable(identifier);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    @Override // defpackage.j20, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View viewInflate = this.n.inflate(this.m, viewGroup, false);
            if (viewInflate != null) {
                ((ChildViewCache) viewInflate.getTag()).a.setText(e.toString());
            }
            return viewInflate;
        }
    }

    @Override // defpackage.j20, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View viewF = this.f(viewGroup);
            ((ChildViewCache) viewF.getTag()).a.setText(e.toString());
            return viewF;
        }
    }

    public final Drawable h(String str) throws FileNotFoundException {
        WeakHashMap<String, Drawable.ConstantState> weakHashMap = this.r;
        Context context = this.q;
        Drawable drawableG = null;
        if (str != null && !str.isEmpty() && !"0".equals(str)) {
            try {
                int i = Integer.parseInt(str);
                String str2 = "android.resource://" + context.getPackageName() + "/" + i;
                Drawable.ConstantState constantState = weakHashMap.get(str2);
                Drawable drawableNewDrawable = constantState == null ? null : constantState.newDrawable();
                if (drawableNewDrawable != null) {
                    return drawableNewDrawable;
                }
                Object obj = bz.a;
                Drawable drawable = context.getDrawable(i);
                if (drawable != null) {
                    weakHashMap.put(str2, drawable.getConstantState());
                }
                return drawable;
            } catch (Resources.NotFoundException unused) {
                Log.w("SuggestionsAdapter", "Icon resource not found: ".concat(str));
                return null;
            } catch (NumberFormatException unused2) {
                Drawable.ConstantState constantState2 = weakHashMap.get(str);
                Drawable drawableNewDrawable2 = constantState2 == null ? null : constantState2.newDrawable();
                if (drawableNewDrawable2 != null) {
                    return drawableNewDrawable2;
                }
                Uri uri = Uri.parse(str);
                try {
                    if ("android.resource".equals(uri.getScheme())) {
                        try {
                            drawableG = g(uri);
                        } catch (Resources.NotFoundException unused3) {
                            throw new FileNotFoundException("Resource does not exist: " + uri);
                        }
                    } else {
                        InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
                        if (inputStreamOpenInputStream == null) {
                            throw new FileNotFoundException("Failed to open " + uri);
                        }
                        try {
                            drawableG = Drawable.createFromStream(inputStreamOpenInputStream, null);
                        } finally {
                            try {
                                inputStreamOpenInputStream.close();
                            } catch (IOException e) {
                                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e);
                            }
                        }
                    }
                } catch (FileNotFoundException e2) {
                    Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e2.getMessage());
                }
                if (drawableG != null) {
                    weakHashMap.put(str, drawableG.getConstantState());
                }
            }
        }
        return drawableG;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public final boolean hasStableIds() {
        return false;
    }

    public final Cursor i(SearchableInfo searchableInfo, String str) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder builderFragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            builderFragment.appendEncodedPath(suggestPath);
        }
        builderFragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            builderFragment.appendPath(str);
        }
        builderFragment.appendQueryParameter("limit", String.valueOf(50));
        return this.q.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr, null);
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        Cursor cursor = this.g;
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        Cursor cursor = this.g;
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null) {
            extras.getBoolean("in_progress");
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.o.o((CharSequence) tag);
        }
    }
}
