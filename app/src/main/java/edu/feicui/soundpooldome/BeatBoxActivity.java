package edu.feicui.soundpooldome;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import edu.feicui.soundpooldome.beatbox.BeatBoxFragment;

public class BeatBoxActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return BeatBoxFragment.newInstance();
    }


}
