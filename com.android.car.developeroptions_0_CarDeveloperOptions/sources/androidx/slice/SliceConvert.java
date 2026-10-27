package androidx.slice;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import androidx.collection.ArraySet;
import androidx.core.graphics.drawable.IconCompat;
import androidx.slice.Slice;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class SliceConvert {
    private static android.app.slice.SliceSpec unwrap(SliceSpec sliceSpec) {
        if (sliceSpec == null) {
            return null;
        }
        return new android.app.slice.SliceSpec(sliceSpec.getType(), sliceSpec.getRevision());
    }

    static Set<android.app.slice.SliceSpec> unwrap(Set<SliceSpec> set) {
        ArraySet arraySet = new ArraySet();
        if (set != null) {
            Iterator<SliceSpec> it = set.iterator();
            while (it.hasNext()) {
                arraySet.add(unwrap(it.next()));
            }
        }
        return arraySet;
    }

    public static Slice wrap(android.app.slice.Slice slice, Context context) {
        if (slice == null || slice.getUri() == null) {
            return null;
        }
        Slice.Builder builder = new Slice.Builder(slice.getUri());
        builder.addHints(slice.getHints());
        builder.setSpec(wrap(slice.getSpec()));
        for (android.app.slice.SliceItem sliceItem : slice.getItems()) {
            switch (sliceItem.getFormat()) {
                case "slice":
                    builder.addSubSlice(wrap(sliceItem.getSlice(), context), sliceItem.getSubType());
                    break;
                case "image":
                    try {
                        builder.addIcon(IconCompat.createFromIcon(context, sliceItem.getIcon()), sliceItem.getSubType(), sliceItem.getHints());
                        break;
                    } catch (Resources.NotFoundException e) {
                        Log.w("SliceConvert", "The icon resource isn't available.", e);
                        break;
                    } catch (IllegalArgumentException e2) {
                        Log.w("SliceConvert", "The icon resource isn't available.", e2);
                        break;
                    }
                    break;
                case "input":
                    builder.addRemoteInput(sliceItem.getRemoteInput(), sliceItem.getSubType(), sliceItem.getHints());
                    break;
                case "action":
                    builder.addAction(sliceItem.getAction(), wrap(sliceItem.getSlice(), context), sliceItem.getSubType());
                    break;
                case "text":
                    builder.addText(sliceItem.getText(), sliceItem.getSubType(), sliceItem.getHints());
                    break;
                case "int":
                    builder.addInt(sliceItem.getInt(), sliceItem.getSubType(), sliceItem.getHints());
                    break;
                case "long":
                    builder.addLong(sliceItem.getLong(), sliceItem.getSubType(), sliceItem.getHints());
                    break;
            }
        }
        return builder.build();
    }

    private static SliceSpec wrap(android.app.slice.SliceSpec sliceSpec) {
        if (sliceSpec == null) {
            return null;
        }
        return new SliceSpec(sliceSpec.getType(), sliceSpec.getRevision());
    }
}
