package ts.car.service.locationext.data;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class GnssExtMmfData {
    public int count;
    public final int feedBackNodeMax = 6;
    public ArrayList<GnssExtFeedbackNodes> feedbackNodesArray = new ArrayList<>();
    public int nodesNum;
    public long ticktime;

    public GnssExtMmfData() {
        for (int i = 0; i < 6; i++) {
            this.feedbackNodesArray.add(new GnssExtFeedbackNodes());
        }
    }
}
