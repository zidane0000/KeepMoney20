package tw.edu.niu.keepmoney20;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bigdata_lab on 2018/3/25.
 */

public class StatisticsFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragstatistics , container , false);
        return myView;
    }
}
