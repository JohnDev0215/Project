package p;

import com.spotify.automotive.pathfinder.type.ContentRatingLabel;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class lz implements y4 {
    public static final List c = v03.B("label");

    public static kz b(u71 u71Var, com.apollographql.apollo3.api.c cVar) {
        ContentRatingLabel contentRatingLabel;
        v03.j(u71Var, "reader");
        v03.j(cVar, "customScalarAdapters");
        ContentRatingLabel contentRatingLabel2 = null;
        while (u71Var.W(c) == 0) {
            String strU = u71Var.u();
            v03.h(strU);
            ContentRatingLabel[] contentRatingLabelArrValues = ContentRatingLabel.values();
            int length = contentRatingLabelArrValues.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    contentRatingLabel = null;
                    break;
                }
                contentRatingLabel = contentRatingLabelArrValues[i];
                if (v03.b(contentRatingLabel.a(), strU)) {
                    break;
                }
                i++;
            }
            contentRatingLabel2 = contentRatingLabel == null ? ContentRatingLabel.UNKNOWN__ : contentRatingLabel;
        }
        v03.h(contentRatingLabel2);
        return new kz(contentRatingLabel2);
    }
}
