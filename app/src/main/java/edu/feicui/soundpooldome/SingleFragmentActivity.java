package edu.feicui.soundpooldome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 通用超类
 * Created by Administrator on 2016/11/21.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {


    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    protected abstract Fragment createFragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        ButterKnife.bind(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment=fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }


    }
}
